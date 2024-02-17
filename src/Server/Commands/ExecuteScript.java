package Server.Commands;

import ObjectSpace.Exceptions.ArgumentVehicleException;
import ObjectSpace.Exceptions.VehicleException;
import Server.CommandExecuter;
import Server.FileWork.FileReader;
import Server.Utilities.InfoSender;
import Server.Utilities.Pair;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
/**
 * @author Piromant
 * Реализация команды execute_script
 */
public class ExecuteScript implements Command{
    /**
     * @see CommandExecuter
     */
    private CommandExecuter commandExecuter;
    /**
     * @see FileReader
     */
    private FileReader fileReader;
    /**
     * Имя файла, из которого читаются команды
     */
    private String fileName;
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public ExecuteScript(FileReader fileReader, String fileName, InfoSender infoSender){
        this.commandExecuter = CommandExecuter.getAccess();
        this.fileReader = fileReader;
        this.fileName = fileName;
        this.infoSender = infoSender;
    }
    /**
     * Метод, считывающий команды с файла и проверяющий их на валидность, затем передающий их в исполнитель команд для исполнения
     */
    @Override
    public void execute() {

        LinkedList<Pair<String, ArrayList<String>>> commandList = new LinkedList<>();
        try {
            commandList = fileReader.readCommandsFromFile(fileName);
        } catch (FileNotFoundException | NullPointerException e) {
            infoSender.sendLine("Файл не найден");
            return;
        } catch (SecurityException e){
            infoSender.sendLine("Не хватает прав для доступа к файлу");
            return;
        } catch (IOException e){
            infoSender.sendLine(e.getStackTrace());
            return;
        }

        ListIterator<Pair<String, ArrayList<String>>> it = commandList.listIterator();
        try {
            while (it.hasNext()) {
                Pair<String, ArrayList<String>> command = it.next();

                this.commandExecuter.executeCommand(command.getFirst(), command.getSecond());
            }
            this.infoSender.sendLine("Скрипт Выполнен");
        }
        catch (VehicleException e){
            int commandError = it.previousIndex();
            this.infoSender.sendLine("Скрипт Выполнен до "+ (commandError + 1) + " строки:");
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
            this.infoSender.sendLine("Аргумент команды на " + (commandError + 1) + " строке должен быть целым числом меньшим 2^32");
        }

    }
}
