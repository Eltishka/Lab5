package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;

import java.util.stream.Collectors;
/**
 * @author Piromant
 * Реализация команды filter_contains_name
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
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public <T extends Vehicle> FilterContainsName(Storage<T> storage, String pattern, InfoSender infoSender){
        this.pattern = pattern;
        this.storage = storage;
        this.infoSender = infoSender;
    }

    /**
     * Метод, выводящий все элементы коллекции, имена которых содержат заданную строку pattern
     */
    @Override
    public void execute() {
        Storage<? extends Vehicle> res = this.storage.stream().filter(el -> el.getName().contains(this.pattern)).collect(Collectors.toCollection(Storage::new));
        if(res.size() == 0){
            this.infoSender.sendLine("Совпадений не обнаружено");
            return;
        }
        infoSender.sendMultiLines(res);
    }

}
