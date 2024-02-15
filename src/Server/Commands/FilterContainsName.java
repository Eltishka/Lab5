package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import java.util.stream.Collectors;

public class FilterContainsName implements Command{
    private Storage<? extends Vehicle> storage;
    private String s;
    private InfoSender infoSender;

    public <T extends Vehicle> FilterContainsName(Storage<T> storage, String s, InfoSender infoSender){
        this.s = s;
        this.storage = storage;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        Storage<Vehicle> res = this.storage.stream().filter(el -> el.getName().contains(s)).collect(Collectors.toCollection(Storage::new));
        infoSender.sendMultiLines(res);
    }

}
