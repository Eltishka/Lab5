package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import java.util.TreeSet;

public class AverageOfEnginePower implements Command{
    private Storage<? extends Vehicle> storage;
    private InfoSender infoSender;

    public <T extends Vehicle> AverageOfEnginePower(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        double res = this.storage.stream().mapToDouble(Vehicle::getEnginePower).sum() / this.storage.size();
        infoSender.sendLine(res);
    }
}
