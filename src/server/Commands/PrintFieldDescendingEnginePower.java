package server.Commands;

import objectspace.Vehicle;
import server.Response;
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
     * @see Storage
     */
    private Storage<? extends Vehicle> storage;

    public <T extends Vehicle> PrintFieldDescendingEnginePower(Storage<T> storage){
        this.storage = storage;
    }

    /**
     * Метод, выводящий все элементы коллекции в порядке убывания их силы двигателя
     */
    @Override
    public Response execute() {
        if(this.storage.size() == 0){
            return new Response("Коллекция пуста");
        }
        LinkedHashSet<? extends Vehicle> res = this.storage.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(LinkedHashSet::new));
        return new Response(res.toArray());
    }
}
