package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

public class Info implements Command{
    private InfoSender infoSender;
    private Storage storage;

    public <T extends Vehicle> Info(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }
    @Override
    public void execute() {
        this.infoSender.sendLine("Тип коллекции " + storage.getClass());
        this.infoSender.sendLine("Дата создания " + this.storage.getCreationDate());
        this.infoSender.sendLine("Количество элементов " + storage.size());
    }
}
