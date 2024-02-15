package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.InfoSender;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

public class PrintFieldDescendingEnginePower implements Command{
    private InfoSender infoSender;
    private Storage<? extends Vehicle> storage;

    public <T extends Vehicle> PrintFieldDescendingEnginePower(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        LinkedHashSet<? extends Vehicle> res = this.storage.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
        infoSender.sendMultiLines(res);
    }
}
