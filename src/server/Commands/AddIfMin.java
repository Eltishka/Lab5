package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;

import java.util.TreeSet;
/**
 * Реализация команды add_if_min
 * @author Piromant
 */
public class AddIfMin implements Command{
    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * Элемент, который добавляется в колекцию
     */
    private Vehicle el;


    public <T extends Vehicle> AddIfMin(Storage<T> storage, T el){
        this.storage = storage;
        this.el = el;
    }

    /**
     * Метод, добавляющий элемент в коллекцию в случае, если его сила двигателя меньше, чем у любого элемента коллекции, и выводящий результат (добавлен или не добавлен)
     */
    @Override
    public Response execute() {
        TreeSet<Vehicle> sortedCollection = new TreeSet<>(storage);
        if(this.storage.size() == 0 || sortedCollection.first().compareTo(el) > 0)
            return (new Add(this.storage, this.el)).execute();
        else
            return new Response("Элемент не был добавлен");
    }
}
