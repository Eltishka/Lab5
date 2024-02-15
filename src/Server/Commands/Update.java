package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

public class Update implements Command{
    private Storage storage;
    private Vehicle el;
    private int id;
    private InfoSender infoSender;

    public <T extends Vehicle> Update(Storage<T> storage, T el, int id, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.id = id;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        this.el.setId(id);
        boolean res = this.storage.remove(el);

        if(res) {
            this.storage.add(el);
            this.infoSender.sendLine("Элемент обновлен");
        } else {
            this.infoSender.sendLine("Такого элемента в коллекции нет");
        }
    }
}
