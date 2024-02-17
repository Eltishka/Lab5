package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;
/**
 * @author Piromant
 * Реализация команды clear
 */
public class Clear implements Command{
    /**
     * @see Storage
     */
    private Storage storage;

    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public <T extends Vehicle> Clear(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    /**
     * Метод, очищающий коллекцию
     */
    @Override
    public void execute() {
        this.storage.clear();
        this.infoSender.sendLine("Коллекция очищена");
    }
}
