package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;

public class RemoveById implements Command{
    private Storage storage;
    private int id;

    public RemoveById(Storage storage, int id){
        this.storage = storage;
        this.id = id;
    }

    @Override
    public void execute() {
        this.storage.remove(new Vehicle(this.id));
    }
}
