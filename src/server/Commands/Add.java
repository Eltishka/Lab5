package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;

/**
 * 
 * Реализация команды add
 * @author Piromant
 */
public class Add implements Command{

    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * Элемент, который добавляется в колекцию
     */
    private Vehicle el;

    public <T extends Vehicle> Add(Storage<T> storage, T el){
        this.storage = storage;
        this.el = el;
    }

    /**
     * Метод, добавляющий элемент в коллекцию и выводящий результат (добавлен или не добавлен)
     */
    @Override
    public Response execute() {
        if(this.storage.add(el))
            return new Response("Элемент добавлен");
        else
            return new Response("Элемент не был добавлен");
    }
}
