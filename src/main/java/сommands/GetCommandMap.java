package сommands;

import dataexchange.Response;
import objectspace.Vehicle;
import server.database.Storage;

public class GetCommandMap extends Command{
    public <T extends Vehicle> GetCommandMap(Storage<T> storage, String argument, T el) {
        super(storage, argument, el);
    }

    @Override
    public Response execute() {
        return null;
    }

    @Override
    public String getHelp() {
        return null;
    }


}
