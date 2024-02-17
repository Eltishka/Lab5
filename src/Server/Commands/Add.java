package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;

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
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;


    public <T extends Vehicle> Add(Storage<T> storage, T el, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.infoSender = infoSender;
    }

    /**
     * Метод, добавляющий элемент в коллекцию и выводящий результат (добавлен или не добавлен)
     */
    @Override
    public void execute() {
        if(this.storage.add(el))
            this.infoSender.sendLine("Элемент добавлен");
        else
            this.infoSender.sendLine("Элемент не был добавлен");
    }
}
