package server.Commands;
import server.Response;
/**
 * 
 * Интерфейс команды, который реализуют все команды
 * @author Piromant
 */
public interface Command {
    Response execute();
}
