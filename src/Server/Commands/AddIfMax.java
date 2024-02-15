package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.TreeSet;

public class AddIfMax implements Command{
    private Storage storage;
    private Vehicle el;
    private InfoSender infoSender;

    public <T extends Vehicle> AddIfMax(Storage<T> storage, T el, InfoSender infoSender){
        this.storage = storage;
        this.el = el;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        TreeSet<Vehicle> sortedCollection = new TreeSet<>(storage);
        if(this.storage.size() == 0 || sortedCollection.last().getEnginePower() > el.getEnginePower())
            new Add(this.storage, this.el, this.infoSender).execute();
        else
            this.infoSender.sendLine("Элемент не был добавлен");
    }
}
