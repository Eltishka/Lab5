package Server.Commands;

import ObjectSpace.Exceptions.ArgumentVehicleException;
import ObjectSpace.Exceptions.VehicleException;
import Server.CommandExecuter;
import Server.FileWork.FileReader;
import Server.InfoSender;
import Server.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public class ExecuteScript implements Command{
    private CommandExecuter commandExecuter;
    private FileReader fileReader;
    private String fileName;
    private InfoSender infoSender;

    public ExecuteScript(FileReader fileReader, String fileName, InfoSender infoSender){
        this.commandExecuter = CommandExecuter.getAccess();
        this.fileReader = fileReader;
        this.fileName = fileName;
        this.infoSender = infoSender;
    }
    @Override
    public void execute() {
        LinkedList<Pair<String, ArrayList<String>>> commandList = fileReader.readCommandsFromFile(fileName);
        ListIterator<Pair<String, ArrayList<String>>> it = commandList.listIterator();
        try {
            while (it.hasNext()) {
                Pair<String, ArrayList<String>> command = it.next();
                this.commandExecuter.executeCommand(command.getFirst(), command.getSecond());
            }
        }
        catch (VehicleException e){
            int commandError = it.previousIndex();
            this.infoSender.sendLine("Ошибка в команде на " + (commandError + 1) + " строке: " + e.getMessage());
            Throwable cause = e.getCause();
            if(cause instanceof ArgumentVehicleException){
                int errorLine = commandError + ((ArgumentVehicleException) cause).argumentNumber + 1;
                this.infoSender.sendLine("Строка " + errorLine + ": " + cause.getMessage());
            }
            else if(cause instanceof IllegalArgumentException){
                this.infoSender.sendLine("Требуется 5 аргументов соответствующих требованиям");
            }
            else{
                this.infoSender.sendLine("Непредвиденная ошибка");
                this.infoSender.sendLine(e.getMessage());
            }
        }
        catch (NumberFormatException e){
            int commandError = it.previousIndex();
            this.infoSender.sendLine("Аргумент команды на " + (commandError + 1) + " строке должен быть целым числом");
        }
        this.infoSender.sendLine("Скрипт выполнен");
    }
}
