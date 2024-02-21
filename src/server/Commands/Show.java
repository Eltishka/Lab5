package server.Commands;

import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 * 
 * Реализация команды show
 * @author Piromant
 */
public class Show implements Command{
    /**
     * @see Storage
     */
    private Storage storage;


    public Show(Storage storage){
        this.storage = storage;
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
}
