package server;

import server.utilities.InfoSender;
import server.utilities.OutStreamInfoSender;
import server.utilities.Pair;
import objectspace.Vehicle;
import server.database.Storage;
import server.Commands.*;
import server.filework.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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

    /** @see FileReader */
    private FileReader fileReader;
    /** @see InfoSender */
    private InfoSender infoSender;
    /** @see FileSaver */
    private FileSaver fileSaver;

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

        this.history = new LinkedList<>();
        this.infoSender = new OutStreamInfoSender();
        this.fileReader = new FileInputStreamReader();
        this.fileSaver = new XMLSaver();
        try {
            this.storage = (new XMLLoader(fileReader, System.getenv("SAVEFILE"))).loadStorage();
        } catch (IOException | ParseException | NullPointerException e){
            //e.printStackTrace();
            infoSender.sendLine("Невозможно загрузить коллекцию из файла. Файл должен сущетсвовать, имя файла должно хранится в переменной окружения SAVEFILE");
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


        Command commandToExecute = switch (command_name) {
            case("help") -> new Help();
            case("info") -> new Info(this.storage);
            case("show") -> new Show(this.storage);
            case("add") -> new Add(this.storage, v);
            case("update") -> new Update(this.storage, v, Integer.parseInt(argument));
            case("remove_by_id") -> new RemoveById(this.storage, Integer.parseInt(argument));
            case("clear") -> new Clear(this.storage);
            case("save") -> new Save(this.fileSaver, System.getenv("SAVEFILE"), this.storage);
            case("execute_script") -> new ExecuteScript(this.fileReader, argument);
            case("exit") -> new Exit();
            case("add_if_max") -> new AddIfMax(this.storage, v);
            case("add_if_min") -> new AddIfMin(this.storage, v);
            case("history") -> new History(this.history);
            case("average_of_engine_power") -> new AverageOfEnginePower(this.storage);
            case("filter_contains_name") -> new FilterContainsName(this.storage, argument);
            case("print_field_descending_engine_power") -> new PrintFieldDescendingEnginePower(this.storage);
            default -> new UnknownCommand(command_name);
        };

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


}
