package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;

import java.util.stream.Collectors;
/**
 * 
 * Реализация команды filter_contains_name
 * @author Piromant
 */
public class FilterContainsName implements Command{
    /**
     * @see Storage
     */
    private Storage<? extends Vehicle> storage;
    /**
     * Строка, содержание которой проверяется в именах элементов
     */
    private String pattern;

    public <T extends Vehicle> FilterContainsName(Storage<T> storage, String pattern){
        this.pattern = pattern;
        this.storage = storage;
    }

    /**
     * Метод, выводящий все элементы коллекции, имена которых содержат заданную строку pattern
     */
    @Override
    public Response execute() {
        Storage<? extends Vehicle> res = this.storage.stream().filter(el -> el.getName()
                .contains(this.pattern)).collect(Collectors.toCollection(Storage::new));
        if(res.size() == 0){
            return new Response("Совпадений не обнаружено");
        }
        return new Response(res.toArray());
    }

}
