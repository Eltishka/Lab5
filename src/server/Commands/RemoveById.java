package server.Commands;

import objectspace.Vehicle;
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
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public RemoveById(Storage storage, int id, InfoSender infoSender){
        this.storage = storage;
        this.id = id;
        this.infoSender = infoSender;
    }

    /**
     * Метод, удаляющий элемент по его id и выводящий результат операци
     */
    @Override
    public void execute() {
        boolean res = this.storage.remove(new Vehicle(this.id));
        if(res)
            this.infoSender.sendLine("Элемент удален");
        else
            this.infoSender.sendLine("Элемента с таким id в коллекции нет");
    }
}
