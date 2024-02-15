package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

public class Add implements Command{

    private Storage storage;
    private Vehicle el;
    private InfoSender infoSender;

    public <T extends Vehicle> Add(Storage<T> storage, T el, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        this.storage.add(el);
        this.infoSender.sendLine("Элемент добавлен");
    }
}
