package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;
/**
 * 
 * Реализация команды info
 * @author Piromant
 */
public class Info implements Command{
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;
    /**
     * @see Storage
     */
    private Storage storage;

    public <T extends Vehicle> Info(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }
    /**
     * Метод, выводящий типа, дату создания и количество элементов коллекци
     */
    @Override
    public void execute() {
        this.infoSender.sendLine("Тип коллекции " + storage.getClass());
        this.infoSender.sendLine("Дата создания " + this.storage.getCreationDate());
        this.infoSender.sendLine("Количество элементов " + storage.size());
    }
}
