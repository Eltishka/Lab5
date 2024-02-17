package UserInterface;

import ObjectSpace.Exceptions.VehicleException;
import ObjectSpace.FuelType;
import ObjectSpace.VehicleType;
import Server.CommandExecuter;
import Server.Utilities.Pair;

import java.util.*;
/**
 * @author Piromant
 * Класс для работы с пользователем, принимания команд в текстовом виде и передачи их в исполнитель команд
 */
public class Terminal {
    /**
     * Исполнитель команд
     * @see CommandExecuter
     */
    private CommandExecuter commandExecuter;
    /**
     * Сканер для считывания введенных данных
     */
    private Scanner sc;

    /**
     * Конструктор, получающий доступ к исполнителю команд
     */
    public Terminal(){
        this.commandExecuter = CommandExecuter.getAccess();
        this.sc = new Scanner(System.in);
    }

    /**
     *
     * @param msg Сообщение, которое говорит пользователю что и как вводить
     * @param checker Чекер для проверки аргументов на валидность
     * @see ArgumentChecker
     * @return Строка - валидный аргумент
     */
    private String getArgumentWithRules(String msg, ArgumentChecker<String> checker){
        String arg = "";
        do{
            System.out.println(msg);
            arg = this.sc.nextLine();
        }while (!checker.check(arg));
        return arg;
    }

    /**
     * Метод, читающий информацию для элемента коллекции
     * @return Массив строк - аргументов для создания нового элемента
     */
    private ArrayList<String> readElement(){
        ArrayList<String> args = new ArrayList<String>();

        args.add(getArgumentWithRules("Введите имя (непустая строка)",
                                            arg -> !arg.equals("")));

        args.add(getArgumentWithRules("Введите координаты в формате: x y (x - число с дробной частью, оба больше нуля, y - целое)",
                                            arg -> arg.matches("\\d+(\\.?\\d*) \\d+\\s*")));

        args.add(getArgumentWithRules("Введите силу двигателя (неотрицательное целое число больше нуля):",
                                            arg -> arg.matches("[1-9]\\d*\\s*")));

        List<VehicleType> possibleTypes = Arrays.asList(VehicleType.values());
        ArrayList<String> possibleTypesStr = new ArrayList<>();
        Iterator<VehicleType> it = possibleTypes.iterator();
        while(it.hasNext()){
            possibleTypesStr.add(it.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа средства передвижения из представленных" + possibleTypesStr.toString() + ":",
                                            arg -> possibleTypesStr.contains(arg.trim())));

        List<FuelType> possibleFuelTypes = Arrays.asList(FuelType.values());
        ArrayList<String> possibleFuelTypesStr = new ArrayList<>();
        Iterator<FuelType> fuelIt = possibleFuelTypes.iterator();
        while(fuelIt.hasNext()){
            possibleFuelTypesStr.add(fuelIt.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа топлива из представленных" + possibleFuelTypesStr.toString() + ":",
                                            arg -> possibleFuelTypesStr.contains(arg.trim())));

        return args;
    }

    /**
     * Проверяет на валидность аргумент команды, если тот должен быть id
     * @param commandToCheck команда, аргумент которой нужно проверить
     * @return true, если id валиден, false в ином случае
     */
    private boolean checkValideId(String[] commandToCheck){
        if(commandToCheck.length == 1){
            System.out.println("id должен быть введен через пробел после команды");
            return false;
        }
        else if(!commandToCheck[1].matches("[1-9]\\d*\\s*")) {
            System.out.println("id должен быть целым неотрицательным числом");
            return false;
        }
        try {
            Integer.parseInt(commandToCheck[1]);
        } catch (Exception e) {
            System.out.println("Слишком большое значение id, невозможно  выполнить команду");
            return false;
        }
        return true;
    }
    /**
     * Основной метод для работы с пользователем и чтения введенных команд
     */
    public void readFromConsole() {
        String command = "";
        while(!command.equals("exit")) {
            ArrayList<String> element = new ArrayList<>();
            try {
                command = sc.nextLine();
                if(command.equals(""))
                    continue;
                String[] commandToCheck = command.split(" ");
                if(commandToCheck[0].equals("update") || commandToCheck[0].equals("remove_by_id")){
                    if(!checkValideId(commandToCheck))
                        continue;
                }
                if (commandToCheck[0].equals("add") || commandToCheck[0].equals("update") || commandToCheck[0].equals("add_if_min") || commandToCheck[0].equals("add_if_max"))
                    element = this.readElement();
                commandExecuter.executeCommand(command, element);
            }
            catch (VehicleException e) {
                System.out.println(e.getMessage() + " " + e.getCause().getMessage());
            }
            catch (NoSuchElementException e){
                sc.close();
                System.out.println("Программа завершена");
                commandExecuter.executeCommand("exit", element);
            }
        }
    }


    /**
     * Метод запуска
     */
    public void start(){
        readFromConsole();
    }


}
