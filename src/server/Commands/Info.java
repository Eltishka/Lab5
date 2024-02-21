package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Реализация команды info
 * @author Piromant
 */
public class Info implements Command{

    /**
     * @see Storage
     */
    private Storage storage;

    public <T extends Vehicle> Info(Storage<T> storage){
        this.storage = storage;
    }
    /**
     * Метод, выводящий типа, дату создания и количество элементов коллекци
     */
    @Override
    public Response execute() {
        List<String> response = new LinkedList<>();
        response.add("Тип коллекции " + storage.getClass());
        response.add("Дата создания " + this.storage.getCreationDate());
        response.add("Количество элементов " + storage.size());
        return new Response(response.toArray());
    }
}
