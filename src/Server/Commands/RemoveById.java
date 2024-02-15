package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import javax.persistence.criteria.CriteriaBuilder;

public class RemoveById implements Command{
    private Storage storage;
    private int id;
    private InfoSender infoSender;

    public RemoveById(Storage storage, int id, InfoSender infoSender){
        this.storage = storage;
        this.id = id;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        boolean res = this.storage.remove(new Vehicle(this.id));
        if(res)
            this.infoSender.sendLine("Элемент удален");
        else
            this.infoSender.sendLine("Такого элемента в коллекции нет");
    }
}
