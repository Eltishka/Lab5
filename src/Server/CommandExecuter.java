package Server;

import ObjectSpace.Exceptions.CoordinatesException;
import ObjectSpace.Exceptions.VehicleException;
import Server.Utilities.InfoSender;
import Server.Utilities.OutStreamInfoSender;
import Server.Utilities.Pair;
import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Commands.*;
import Server.FileWork.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
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
        }


    }

    /**
     * Метод, который выбирает команду по ее названию, исполняет ее и записывает в историю команд.
     * Также здесь происходит парсинг объекта типа Vehicle из строк
     * @param command имя команды
     * @param element ArrayList строк для парсинга объекта типа Vehicle
     */
    public void executeCommand(String command, ArrayList<String> element) {

        String[] commandParts = command.split(" ");
        String command_name = commandParts[0];
        String argument = "";

        Vehicle v = null;
        if(element.size() > 0)
            v = Vehicle.parseVehicle(element.toArray(new String[5]));


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
            case("save") -> new Save(this.fileSaver, System.getenv("SAVEFILE"), this.storage, this.infoSender);
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
