package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;

import java.util.TreeSet;
/**
 * 
 * Реализация команды add_if_max
 * @author Piromant
 */
public class AddIfMax implements Command{
    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * Элемент, который добавляется в колекцию
     */
    private Vehicle el;
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public <T extends Vehicle> AddIfMax(Storage<T> storage, T el, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.infoSender = infoSender;
    }

    /**
     * Метод, добавляющий элемент в коллекцию в случае, если его сила двигателя больше, чем у любого элемента коллекции, и выводящий результат (добавлен или не добавлен)
     */
    @Override
    public void execute() {
        TreeSet<Vehicle> sortedCollection = new TreeSet<>(storage);
        if(this.storage.size() == 0 || sortedCollection.last().compareTo(el) < 0)
            new Add(this.storage, this.el, this.infoSender).execute();
        else
            this.infoSender.sendLine("Элемент не был добавлен");
    }
}
