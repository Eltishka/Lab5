package server.Commands;

import objectspace.Vehicle;
import server.database.Storage;
import server.utilities.InfoSender;
/**
 * 
 * Реализация команды update
 * @author Piromant
 */
public class Update implements Command{
    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * Элемент, поля которого будут использованы для обновления другого элемента
     */
    private Vehicle el;
    /**
     * id элемента, который будет обновлен
     */
    private int id;
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public <T extends Vehicle> Update(Storage<T> storage, T el, int id, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.id = id;
        this.infoSender = infoSender;
    }

    /**
     * Метод, обнавляющий элемент в коллекции по его id и выводящий результат
     */
    @Override
    public void execute() {
        this.el.setId(id);
        boolean res = this.storage.remove(el);

        if(res) {
            this.storage.add(el);
            this.infoSender.sendLine("Элемент обновлен");
        } else {
            this.infoSender.sendLine("Элемента с таким id в коллекции нет");
        }
    }
}
