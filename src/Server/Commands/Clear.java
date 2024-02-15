package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;

import java.util.TreeSet;

public class Clear implements Command{
    private Storage storage;

    public <T extends Vehicle> Clear(Storage<T> storage){
        this.storage = storage;
    }

    @Override
    public void execute() {
        this.storage.clear();
    }
}
