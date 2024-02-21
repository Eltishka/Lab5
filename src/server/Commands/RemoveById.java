package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 *
 * Реализация команды remove_by_id
 * @author Piromant
 */
public class RemoveById implements Command{
    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * id элемента, который будет удален
     */
    private int id;

    public RemoveById(Storage storage, int id){
        this.storage = storage;
        this.id = id;
    }

    /**
     * Метод, удаляющий элемент по его id и выводящий результат операци
     */
    @Override
    public Response execute() {
        boolean res = this.storage.remove(new Vehicle(this.id));
        if(res)
            return new Response("Элемент удален");
        else
            return new Response("Элемента с таким id в коллекции нет");
    }
}
