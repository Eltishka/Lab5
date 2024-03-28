package server;

import commands.*;
import dataexchange.Request;
import dataexchange.Response;
import objectspace.Vehicle;
import server.database.Storage;
import server.filework.*;
import server.utilities.InfoSender;
import server.utilities.OutStreamInfoSender;
import server.utilities.Pair;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.LinkedList;
/**
 * Класс - исполнитель комманд
 * @author Piromant
 */
public class CommandExecuter {

    private static CommandExecuter server;
    /** Коллекция объектов типа Vehicle
     * @see Vehicle
     */
    private Storage<Vehicle> storage;

    /** Список команд, элементы которого пары вида (Название команды, Объект класса команды)
     * @see Pair
     */
    private LinkedList<Pair<String, Command>> history;
    private Invoker invoker;
    /** @see FileReader */
    private FileReader fileReader;
    /** @see InfoSender */
    private InfoSender infoSender;
    /** @see FileSaver */
    private FileSaver fileSaver;

    private LinkedList<String> executedRecursionScript = new LinkedList<>();
    /**
     * Статический метод, предоставляющий доступ к экземпляру класса исполнителя комманд
     */
    public static CommandExecuter getAccess(){
        if(server == null)
            server = new CommandExecuter();
        return server;
    }

    /** Конструктор класса, задающий все параметры и загрудающий коллекцию из файла
     * @see OutStreamInfoSender
     * @see FileInputStreamReader
     * @see XMLSaver
     * @see XMLLoader
     */
    private CommandExecuter()  {
        this.invoker = Invoker.getAccess();
        this.history = new LinkedList<>();
        this.infoSender = new OutStreamInfoSender();
        this.fileReader = new FileInputStreamReader();
        this.fileSaver = new XMLSaver();
        try {
            this.storage = (new XMLLoader(fileReader, System.getenv("SAVEFILE"))).loadStorage();
        } catch (IOException | ParseException | NullPointerException e){
            infoSender.sendLine("Невозможно загрузить коллекцию из файла. Файл должен сущетсвовать, имя файла должно хранится в переменной окружения SAVEFILE. Возможно ошибка в xml-тэгах");
            this.storage = new Storage<>();
        } catch (Exception e){
            this.infoSender.sendLine("Невозможно заргузить коллекцию. Ошибка в файле");
            this.storage = new Storage<>();
        }


    }

    /**
     * Метод, который выбирает команду по ее названию, исполняет ее и записывает в историю команд.
     * Также здесь происходит парсинг объекта типа Vehicle из строк
     * @param request запрос
     */
    public void executeCommand(Request request/*String command, ArrayList<String> element*/) {

        String command_name = request.command_name;
        String argument = request.argument;
        Vehicle v = request.element;


        Command commandToExecute = this.invoker.getCommandToExecute(command_name, this.storage, argument, v, this.history);
        if(request.sender instanceof ExecuteScript) {
            if (commandToExecute instanceof ExecuteScript) {
                if (executedRecursionScript.contains(argument)) {
                    this.infoSender.sendLine("Рекурсия в скрипте! Инструкция пропущена. Скрипт продолжается...");
                    return;
                }
                this.executedRecursionScript.add(argument);
            }
        } else{
            this.executedRecursionScript.clear();
        }
        Response response = commandToExecute.execute();
        this.infoSender.sendMultiLines(Arrays.asList(response.getResponse()));

        if(!(commandToExecute instanceof UnknownCommand))
            this.writeCommandToHistory(new Pair<>(command_name, commandToExecute));

    }

    /**
     * Метод добавляет команду в историю и если история содержит более 7 элементов удаляет первый.
     * @param command Команда в виде пары (Имя Команды, Объект Команды)
     */
    private void writeCommandToHistory(Pair<String, Command> command){
        if(this.history.size() == 7)
            this.history.removeFirst();
        this.history.add(command);
    }

    public boolean checkIfNeedElement(String commandName){
        return CommandUsingElement.class.isAssignableFrom(this.invoker.get(commandName));
    }

    public boolean checkIfNeedId(String commandName){
        return CommandWithId.class.isAssignableFrom(this.invoker.get(commandName));
    }
}
