package server.Commands;

import objectspace.Vehicle;
import server.Response;
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

    public <T extends Vehicle> Update(Storage<T> storage, T el, int id){
        this.storage = storage;
        this.el = el;
        this.id = id;
    }

    /**
     * Метод, обнавляющий элемент в коллекции по его id и выводящий результат
     */
    @Override
    public Response execute() {
        this.el.setId(id);
        boolean res = this.storage.remove(el);

        if(res) {
            this.storage.add(el);
            return new Response("Элемент обновлен");
        } else {
            return new Response("Элемента с таким id в коллекции нет");
        }
    }
}
