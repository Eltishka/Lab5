package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 * 
 * Реализация команды show
 * @author Piromant
 */
public class Show extends Command{

    public <T extends Vehicle> Show(Storage<T> storage, String argument, T el) {
        super(storage, argument, el);
    }


    /**
     * Метод, выводящий все элементы коллекции в порядке их добавления
     */
    @Override
    public Response execute() {
        if(storage.size() > 0)
            return new Response(storage.toArray());
        else
            return new Response("В коллекции нет элементов");
    }

    @Override
    public String getHelp() {
        return "Выводит  в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
