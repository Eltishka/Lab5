package UserInterface;

import ObjectSpace.FuelType;
import ObjectSpace.Vehicle;
import ObjectSpace.VehicleException;
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

        System.out.println("Введите имя (непустая строка)");
        args.add(sc.nextLine());

        args.add(getArgumentWithRules("Введите координаты в формате: x y (x - целое, y - число с дробной частью, оба больше нуля)", arg -> arg.matches("\\d+(\\.?\\d*) \\d+")));

        args.add(getArgumentWithRules("Введите силу двигателя (неотрицательное целое число больше нуля):", arg -> arg.matches("[1-9]\\d*")));

        List<VehicleType> possibleTypes = Arrays.asList(VehicleType.values());
        ArrayList<String> possibleTypesStr = new ArrayList<>();
        Iterator<VehicleType> it = possibleTypes.iterator();
        while(it.hasNext()){
            possibleTypesStr.add(it.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа средства передвижения из представленных" + possibleTypesStr.toString() + ":", arg -> possibleTypesStr.contains(arg)));

        List<FuelType> possibleFuelTypes = Arrays.asList(FuelType.values());
        ArrayList<String> possibleFuelTypesStr = new ArrayList<>();
        Iterator<FuelType> fuelIt = possibleFuelTypes.iterator();
        while(fuelIt.hasNext()){
            possibleFuelTypesStr.add(fuelIt.next().toString());
        }
        args.add(getArgumentWithRules("Введите типа топлива из представленных" + possibleFuelTypesStr.toString() + ":", arg -> possibleFuelTypesStr.contains(arg)));

        return args;
    }

    public void readFromConsole() {
        LinkedList<Pair<String, ArrayList<String>>> test = new LinkedList<>();
        ArrayList<String> test_args = new ArrayList<>();
        /*test_args.add("asd");
        test_args.add("1 1");
        test_args.add("124");
        test_args.add("asf");
        test_args.add("asf");
        test.add(new Pair("add", test_args));
        try {
            test.forEach(comm -> commandExecuter.executeCommand(comm.getFirst(), comm.getSecond()));
        }
        catch (VehicleException e){
            e.printStackTrace();
            e.getCause().printStackTrace();
            return;
        }*/
        String command = "";
        ArrayList<String> argument = new ArrayList<>();
        while(!command.equals("exit")) {
            try {
                command = sc.nextLine();
                if (command.contains("add") || command.contains("update"))
                    argument = this.readArguments();
                commandExecuter.executeCommand(command, argument);
            }
            catch (NoSuchElementException e){
                sc.close();
                commandExecuter.executeCommand("exit", argument);
            }
        }
    }


    public void start(){
        readFromConsole();
    }


}
