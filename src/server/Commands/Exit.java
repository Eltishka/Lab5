package server.Commands;

import server.Response;

/**
 * 
 * Реализация команды exit
 * @author Piromant
 */
public class Exit implements Command{
    /**
     * Метод, завершающий работу программы без сохранения коллекции
     */
    @Override
    public Response execute() {
        System.exit(0);
        return new Response();
    }
}
