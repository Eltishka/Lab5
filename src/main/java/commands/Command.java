package commands;

import dataexchange.Response;
import objectspace.Vehicle;
import server.database.Storage;

/**
 * 
 * Абстрактный класс команды, который реализуют все команды
 * @author Piromant
 */
public abstract class Command {

    protected Storage storage;
    protected String argument;
    protected Vehicle el;

    public abstract Response execute();
    public abstract String getHelp();

    public <T extends Vehicle> Command(Storage<T> storage, String argument, T el){
        this.storage = storage;
        this.argument = argument;
        this.el = el;
    }
}
