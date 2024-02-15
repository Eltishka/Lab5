package Server;

import ObjectSpace.*;
import ObjectSpace.Exceptions.CoordinatesException;
import ObjectSpace.Exceptions.EnginePowerException;
import ObjectSpace.Exceptions.VehicleException;
import Server.Collections.Storage;
import Server.Commands.*;
import Server.FileWork.FileInputStreamReader;
import Server.FileWork.FileReader;
import Server.FileWork.FileSaver;
import Server.FileWork.XMLSaver;

import javax.swing.*;
import java.util.*;

public class CommandExecuter {

    private static CommandExecuter server;
    private Storage<Vehicle> storage;
    private LinkedList<String> history;
    private FileReader fileReader;
    private InfoSender infoSender;
    private FileSaver fileSaver;

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
        this.fileSaver = new XMLSaver();
    }

    public void executeCommand(String command, ArrayList<String> element) {

        String[] commandParts = command.split(" ");
        String command_name = commandParts[0];
        String argument = "";

        if(element.contains("exit"))
            (new Exit()).execute();

        Vehicle v = null;
        if(element.size() > 0) {
            try {
                v = Vehicle.parseVehicle(element.toArray(new String[5]));
            } catch (VehicleException e) {
                Throwable cause = e.getCause();
                if (cause.getCause() instanceof NumberFormatException) {
                    if (cause instanceof CoordinatesException) {
                        infoSender.sendLine("Невозможно создать объект. Слимшком большее число в координатах");
                    }
                    else {
                        infoSender.sendLine("Невозможно создать объект. Слимшком большее число в значении силы двигателя");
                    }
                }
                else{
                    infoSender.sendLine(e.getMessage());
                }
            }
        }


        if(commandParts.length > 1)
            argument = commandParts[1];



        Command commandToExecute = switch (command_name) {
            case("help") -> new Help(this.infoSender);
            case("info") -> new Info(this.storage, this.infoSender);
            case("show") -> new Show(this.storage, this.infoSender);
            case("add") -> new Add(this.storage, v, this.infoSender);
            case("update") -> new Update(this.storage, v, Integer.parseInt(argument), this.infoSender);
            case("remove_by_id") -> new RemoveById(this.storage, Integer.parseInt(argument), this.infoSender);
            case("clear") -> new Clear(this.storage, this.infoSender);
            case("save") -> new Save(this.fileSaver, "save.txt", this.storage, this.infoSender);
            case("execute_script") -> new ExecuteScript(this.fileReader, argument, this.infoSender);
            case("exit") -> new Exit();
            case("add_if_max") -> new AddIfMax(this.storage, v, this.infoSender);
            case("add_if_min") -> new AddIfMin(this.storage, v, this.infoSender);
            case("history") -> new History(this.history, this.infoSender);
            case("average_of_engine_power") -> new AverageOfEnginePower(this.storage, this.infoSender);
            case("filter_contains_name") -> new FilterContainsName(this.storage, argument, this.infoSender);
            case("print_field_descending_engine_power") -> new PrintFieldDescendingEnginePower(this.storage, this.infoSender);
            default -> new UnknownCommand(command, this.infoSender);
        };

        commandToExecute.execute();
        if(history.size() == 7)
            history.removeFirst();
        if(!(commandToExecute instanceof UnknownCommand))
            history.add(command_name);
    }
}
