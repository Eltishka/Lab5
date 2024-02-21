package server.Commands;

import server.Response;
import server.utilities.InfoSender;
/**
 * 
 * Реализация "неизветсной" команды, то есть той, которой нет в списке команд
 * @author Piromant
 */
public class UnknownCommand implements Command {

    /**
     * Имя несуществующей команды
     */
    private String command;
    public UnknownCommand(String command){
        this.command = command;
    }

    /**
     * Метод, выводящий информацию о несуществовании команды
     */
    @Override
    public Response execute() {
        return new Response("команды \"" + this.command + "\" нет, чтобы вывести список комманд используйте help");
    }
}
