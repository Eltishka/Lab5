package client;

import dataexchange.Request;
import objectspace.FuelType;
import objectspace.VehicleType;
import objectspace.exceptions.VehicleException;
import server.CommandExecuter;

import java.util.*;
/**
 *
 * Класс для работы с пользователем, принимания команд в текстовом виде и передачи их в исполнитель команд
 * @author Piromant
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
        System.out.println(msg);
        arg = this.sc.nextLine();
        while (!checker.check(arg)){
            System.out.println("Неверный формат ввода. Попробуйте еще раз.");
            System.out.println(msg);
            arg = this.sc.nextLine();
        }
        return arg;
    }

    /**
     * Метод, читающий информацию для элемента коллекции
     * @return Массив строк - аргументов для создания нового элемента
     */
    private ArrayList<String> readElement(){
        ArrayList<String> args = new ArrayList<String>();

        args.add(getArgumentWithRules("Введите имя (непустая строка)",
                                            arg -> !arg.matches("\\s*")));

        args.add(getArgumentWithRules("Введите координаты в формате: x y (x - число с дробной частью типа double, оба больше нуля, y - целое формата long)",
                                            arg -> ArgumentValidator.checkCoordinates(arg)));

        args.add(getArgumentWithRules("Введите силу двигателя (неотрицательное целое число больше нуля и мень 2^63):",
                                            arg -> ArgumentValidator.checkEnginePower(arg)));

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
                if(this.commandExecuter.checkIfNeedId(commandToCheck[0])){
                    if(!ArgumentValidator.checkId(commandToCheck))
                        continue;
                }
                if (this.commandExecuter.checkIfNeedElement(commandToCheck[0]))
                    element = this.readElement();
                Request request = new Request(command, element, this);
                commandExecuter.executeCommand(request);
            }
            catch (VehicleException e) {
                System.out.println(e.getMessage() + " " + e.getCause().getMessage());
            }
            catch (NoSuchElementException e){
                sc.close();
                System.out.println("Программа завершена");
                Request request = new Request("exit", element, this);
                commandExecuter.executeCommand(request);
            }
        }
    }


    /**
     * Метод запуска
     */
    public void start(){
        System.out.println("Здравсвтуйте, для получения справки по командам введите help");
        readFromConsole();
    }


}
