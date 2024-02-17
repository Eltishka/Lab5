package server.Commands;

import objectspace.Vehicle;
import server.database.Storage;
import server.utilities.InfoSender;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
/**
 * 
 * Реализация команды print_field_descending_engine_power
 * @author Piromant
 */
public class PrintFieldDescendingEnginePower implements Command{
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;
    /**
     * @see Storage
     */
    private Storage<? extends Vehicle> storage;

    public <T extends Vehicle> PrintFieldDescendingEnginePower(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    /**
     * Метод, выводящий все элементы коллекции в порядке убывания их силы двигателя
     */
    @Override
    public void execute() {
        if(this.storage.size() == 0){
            this.infoSender.sendLine("Коллекция пуста");
            return;
        }
        LinkedHashSet<? extends Vehicle> res = this.storage.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
        this.infoSender.sendMultiLines(res);
    }
}
