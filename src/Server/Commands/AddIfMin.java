package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;

import java.util.TreeSet;

public class AddIfMin implements Command{

    private Storage storage;
    private Vehicle el;

    public <T extends Vehicle> AddIfMin(Storage<T> storage, T el){
        this.storage = storage;
        this.el = el;
    }

    @Override
    public void execute() {
        TreeSet<Vehicle> sortedCollection = new TreeSet<>(storage);
        if(this.storage.size() == 0 || sortedCollection.first().getEnginePower() > el.getEnginePower())
            new Add(this.storage, el).execute();
    }
}
