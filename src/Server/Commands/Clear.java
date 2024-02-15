package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import java.util.TreeSet;

public class Clear implements Command{
    private Storage storage;
    private InfoSender infoSender;

    public <T extends Vehicle> Clear(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        this.storage.clear();
        this.infoSender.sendLine("Коллекция очищена");
    }
}
