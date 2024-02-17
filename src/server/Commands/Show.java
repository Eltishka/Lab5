package server.Commands;

import server.database.Storage;
import server.utilities.InfoSender;
/**
 * 
 * Реализация команды show
 * @author Piromant
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
