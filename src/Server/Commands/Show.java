package Server.Commands;

import Server.Collections.Storage;
import Server.Utilities.InfoSender;
/**
 * @author Piromant
 * Реализация команды show
 */
public class Show implements Command{
    /**
     * @see Storage
     */
    private Storage storage;
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public Show(Storage storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    /**
     * Метод, выводящий все элементы коллекции в порядке их добавления
     */
    @Override
    public void execute() {
        if(storage.size() > 0)
            this.infoSender.sendMultiLines(storage);
        else
            this.infoSender.sendLine("В коллекции нет элементов");
    }
}
