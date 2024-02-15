package UserInterface;

import ObjectSpace.FuelType;
import ObjectSpace.VehicleType;
import Server.CommandExecuter;
import Server.Pair;


import java.util.*;

public class Terminal {
    private CommandExecuter commandExecuter;
    private Scanner sc;

    public Terminal(){
        this.commandExecuter = CommandExecuter.getAccess();
        this.sc = new Scanner(System.in);
    }

    private String getArgumentWithRules(String msg, ArgumentChecker<String> checker){
        String arg = "";
        do{
            System.out.println(msg);
            arg = this.sc.nextLine();
            if(arg.equals("exit"))
                this.commandExecuter.executeCommand("exit", new ArrayList<>());
        }while (!checker.check(arg));
        return arg;
    }
    private ArrayList<String> readArguments(){
        ArrayList<String> args = new ArrayList<String>();

        args.add(getArgumentWithRules("Введите имя (непустая строка)",
                                            arg -> !arg.equals("")));

        args.add(getArgumentWithRules("Введите координаты в формате: x y (x - число с дробной частью, оба больше нуля, y - целое)",
                                            arg -> arg.matches("\\d+(\\.?\\d*) \\d+")));

        args.add(getArgumentWithRules("Введите силу двигателя (неотрицательное целое число больше нуля):",
                                            arg -> arg.matches("[1-9]\\d*")));

        List<VehicleType> possibleTypes = Arrays.asList(VehicleType.values());
        ArrayList<String> possibleTypesStr = new ArrayList<>();
        Iterator<VehicleType> it = possibleTypes.iterator();
        while(it.hasNext()){
            possibleTypesStr.add(it.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа средства передвижения из представленных" + possibleTypesStr.toString() + ":",
                                            arg -> possibleTypesStr.contains(arg)));

        List<FuelType> possibleFuelTypes = Arrays.asList(FuelType.values());
        ArrayList<String> possibleFuelTypesStr = new ArrayList<>();
        Iterator<FuelType> fuelIt = possibleFuelTypes.iterator();
        while(fuelIt.hasNext()){
            possibleFuelTypesStr.add(fuelIt.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа топлива из представленных" + possibleFuelTypesStr.toString() + ":",
                                            arg -> possibleFuelTypesStr.contains(arg)));

        return args;
    }

    public void readFromConsole() {
        LinkedList<Pair<String, ArrayList<String>>> test = new LinkedList<>();
        ArrayList<String> test_args = new ArrayList<>();
        String command = "";
        ArrayList<String> element = new ArrayList<>();
        while(!command.equals("exit")) {
            try {
                command = sc.nextLine();
                String[] commandToCheck = command.split(" ");
                if(commandToCheck[0].equals("update") || commandToCheck[0].equals("remove_by_id")){
                    if(commandToCheck.length == 1){
                        System.out.println("id должен быть введен через пробел после команды");
                        continue;
                    }
                    else if(!commandToCheck[1].matches("[1-9]\\d*")) {
                        System.out.println("id должен быть целым неотрицательным числом");
                        continue;
                    }
                }
                if (commandToCheck[0].equals("add") || commandToCheck[0].equals("update"))
                    element = this.readArguments();
                commandExecuter.executeCommand(command, element);
            }
            catch (NoSuchElementException e){
                sc.close();
                commandExecuter.executeCommand("exit", element);
            }
        }
    }


    public void start(){
        readFromConsole();
    }


}
