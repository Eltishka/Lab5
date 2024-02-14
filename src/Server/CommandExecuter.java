package Server;

import ObjectSpace.*;
import Server.Collections.VehicleCollection;
import Server.FileWork.FileInputStreamReader;
import Server.FileWork.FileReader;
import Server.FileWork.FileSaver;
import Server.FileWork.XMLSaver;

import java.util.*;

public class CommandExecuter {

    private static CommandExecuter server;
    private VehicleCollection<Vehicle> storage;
    private LinkedList<String> history;
    private FileReader fileReader;
    private InfoSender infoSender;
    private FileSaver fileSaver;
    private SystemWorker systemWorker;

    public static CommandExecuter getAccess(){
        if(server == null)
            server = new CommandExecuter();
        return server;
    }

    private CommandExecuter(){
        this.storage = new VehicleCollection<Vehicle>();
        this.history = new LinkedList<>();
        this.infoSender = new OutStreamInfoSender();
        this.fileReader = new FileInputStreamReader(infoSender);

        this.fileSaver = new XMLSaver();
        this.systemWorker = new SystemWorker();
    }

    private void info() {
        this.infoSender.sendLine("Тип коллекции " + storage.getClass());
        this.infoSender.sendLine("Дата создания " + storage.getCreationDate());
        this.infoSender.sendLine("Количество элементов " + storage.size());
    }


    public void executeCommandList(LinkedList<Pair<String, ArrayList<String>>> commandList){
        try {
            commandList.forEach(command -> executeCommand(command.getFirst(), command.getSecond()));
        }
        catch (VehicleException e){
            e.printStackTrace();

        }
    }

    public void executeCommand(String command, ArrayList<String> element)  {

        String[] commandParts = command.split(" ");
        String command_name = commandParts[0];
        String argument = "";

        if(commandParts.length > 1)
            argument = commandParts[1];
        if(element.contains("exit"))
            this.systemWorker.exit();

        Vehicle v = null;
        if(element.size() > 0)
            v = Vehicle.parseVehicle(element.toArray(new String[5]));

        switch (command_name) {
            case("help") -> this.infoSender.sendMultiLines(this.fileReader.readWholeFile("C:\\Users\\Piromant\\Desktop\\Програ\\Lab5\\src\\Server\\commands.txt"));
            case("info") -> this.info();
            case("show") -> this.infoSender.sendMultiLines(this.storage);
            case("add") -> this.storage.add(v);
            case("update") -> this.storage.update(Integer.parseInt(argument), v);
            case("remove_by_id") -> this.storage.removeById(Integer.parseInt(argument));
            case("clear") -> this.storage.clear();
            case("save") -> this.fileSaver.save(this.systemWorker.readVar("SAVEFILE"), this.storage);
            case("execute_script") -> this.executeCommandList(this.fileReader.readCommandsFromFile("C:\\Users\\Piromant\\Desktop\\Програ\\Lab5\\src\\test.txt"/*this.systemWorker.readVar(argument)*/));
            case("exit") -> this.systemWorker.exit();
            case("add_if_max") -> this.storage.addIfMax(v);
            case("add_if_min") -> this.storage.addIfMin(v);
            case("history") -> this.infoSender.sendMultiLines(this.history);
            case("average_of_engine_power") -> this.infoSender.sendLine(this.storage.averageOfEnginePower());
            case("filter_contains_name") -> this.infoSender.sendMultiLines(this.storage.filterContainsName(argument));
            case("print_field_descending_engine_power") -> this.infoSender.sendMultiLines(this.storage.FieldDescendingEnginePower());
            default -> {
                this.infoSender.sendLine("Такой команды нет, чтобы вывести список комманд используйте help");
                return;
            }
        }

        if(history.size() == 7)
            history.removeFirst();
        history.add(command_name);
    }
}
