package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;
/**
 * @author Piromant
 * Реализация команды print_field_descending_engine_power
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
