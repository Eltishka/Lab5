package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;

public class Update implements Command{
    private Storage storage;
    private Vehicle el;
    private int id;

    public <T extends Vehicle> Update(Storage<T> storage, T el, int id){
        this.storage = storage;
        this.el = el;
        this.id = id;
    }

    @Override
    public void execute() {
        this.el.setId(id);
        boolean res = this.storage.remove(el);

        if(res)
            new Add(storage, el).execute();
    }
}
