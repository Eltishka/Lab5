package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;

public class Add implements Command{

    private Storage storage;
    private Vehicle el;

    public <T extends Vehicle> Add(Storage<T> storage, T el){
        this.storage = storage;
        this.el = el;
    }

    @Override
    public void execute() {
        this.storage.add(el);
    }
}
