package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 * Реализация команды clear
 * @author Piromant
 */
public class Clear implements Command{
    /**
     * @see Storage
     */
    private Storage storage;


    public <T extends Vehicle> Clear(Storage<T> storage){
        this.storage = storage;
    }

    /**
     * Метод, очищающий коллекцию
     */
    @Override
    public Response execute() {
        this.storage.clear();
        return new Response("Коллекция очищена");
    }
}
