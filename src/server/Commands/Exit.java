package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;

/**
 * 
 * Реализация команды exit
 * @author Piromant
 */
public class Exit extends Command{
    public <T extends Vehicle> Exit(Storage<T> storage, String argument, T el) {
        super(storage, argument, el);
    }

    /**
     * Метод, завершающий работу программы без сохранения коллекции
     */
    @Override
    public Response execute() {
        System.exit(0);
        return new Response();
    }

    @Override
    public String getHelp() {
        return "Завершает программу (без сохранения в файл)";
    }
}
