package Server;

import ObjectSpace.*;
import Server.Collections.Storage;
import Server.Commands.*;
import Server.FileWork.FileInputStreamReader;
import Server.FileWork.FileReader;
import Server.FileWork.FileSaver;
import Server.FileWork.XMLSaver;

import java.util.*;

public class CommandExecuter {

    private static CommandExecuter server;
    private Storage<Vehicle> storage;
    private LinkedList<String> history;
    private FileReader fileReader;
    private InfoSender infoSender;
    private FileSaver fileSaver;
    private Date storageCreationDate;
    private Map<String, Command> commandMap;

    public static CommandExecuter getAccess(){
        if(server == null)
            server = new CommandExecuter();
        return server;
    }

    private CommandExecuter(){
        this.storage = new Storage<>();
        this.history = new LinkedList<>();
        this.infoSender = new OutStreamInfoSender();
        this.fileReader = new FileInputStreamReader(infoSender);
        this.storageCreationDate = new Date();
        this.fileSaver = new XMLSaver();
    }

    private void info() {
        this.infoSender.sendLine("Тип коллекции " + storage.getClass());
        this.infoSender.sendLine("Дата создания " + this.storageCreationDate);
        this.infoSender.sendLine("Количество элементов " + storage.size());
    }


    public void executeCommandList(LinkedList<Pair<String, ArrayList<String>>> commandList){

    }

    public void executeCommand(String command, ArrayList<String> element) {

        String[] commandParts = command.split(" ");
        String command_name = commandParts[0];
        String argument = "";

        if(element.contains("exit"))
            (new Exit()).execute();

        Vehicle v = null;
        if(element.size() > 0)
            v = Vehicle.parseVehicle(element.toArray(new String[5]));

        if(commandParts.length > 1)
            argument = commandParts[1];



        Command commandToExecute = switch (command_name) {
            case("help") -> new Help(this.infoSender);//this.infoSender.sendMultiLines(this.fileReader.readWholeFile("C:\\Users\\Piromant\\Desktop\\Програ\\Lab5\\src\\Server\\commands.txt"));
            case("info") -> new Info(this.storage, this.infoSender);//this.info();
            case("show") -> new Show(this.storage, this.infoSender);//this.infoSender.sendMultiLines(this.storage);
            case("add") -> new Add(this.storage, v);//this.storage.add(v);
            case("update") -> new Update(this.storage, v, Integer.parseInt(argument));//this.storage.update(Integer.parseInt(argument), v);
            case("remove_by_id") -> new RemoveById(this.storage, Integer.parseInt(argument));//this.storage.removeById(Integer.parseInt(argument));
            case("clear") -> new Clear(this.storage);//this.storage.clear();
            case("save") -> new Save(this.fileSaver, "save.txt", this.storage, this.infoSender);//this.fileSaver.save(this.systemWorker.readVar(""), this.storage);
            case("execute_script") -> new ExecuteScript(this.fileReader, argument, this.infoSender);//this.executeCommandList(this.fileReader.readCommandsFromFile(this.systemWorker.readVar(argument)));
            case("exit") -> new Exit();//this.systemWorker.exit();
            case("add_if_max") -> new AddIfMax(this.storage, v);//this.storage.addIfMax(v);
            case("add_if_min") -> new AddIfMin(this.storage, v);//this.storage.addIfMin(v);
            case("history") -> new History(this.history, this.infoSender);//this.infoSender.sendMultiLines(this.history);
            case("average_of_engine_power") -> new AverageOfEnginePower(this.storage, this.infoSender);//this.infoSender.sendLine(this.storage.averageOfEnginePower());
            case("filter_contains_name") -> new FilterContainsName(this.storage, argument, this.infoSender);//this.infoSender.sendMultiLines(this.storage.filterContainsName(argument));
            case("print_field_descending_engine_power") -> new PrintFieldDescendingEnginePower(this.storage, this.infoSender);//this.infoSender.sendMultiLines(this.storage.FieldDescendingEnginePower());
            default -> new UnknownCommand(command, this.infoSender);
        };

        commandToExecute.execute();
        if(history.size() == 7)
            history.removeFirst();
        if(!(commandToExecute instanceof UnknownCommand))
            history.add(command_name);
    }
}
