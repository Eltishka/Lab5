package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 * Реализация команды clear
 * @author Piromant
 */
public class Clear extends Command{


    public <T extends Vehicle> Clear(Storage<T> storage, String argument, T el) {
        super(storage, argument, el);
    }

    /**
     * Метод, очищающий коллекцию
     */
    @Override
    public Response execute() {
        this.storage.clear();
        return new Response("Коллекция очищена");
    }

    @Override
    public String getHelp() {
        return "Очищает коллекцию";
    }
}
