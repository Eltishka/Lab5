package server.Commands;

import server.utilities.InfoSender;
/**
 * 
 * Реализация "неизветсной" команды, то есть той, которой нет в списке команд
 * @author Piromant
 */
public class UnknownCommand implements Command {
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;
    /**
     * Имя несуществующей команды
     */
    private String command;
    public UnknownCommand(String command, InfoSender infoSender){
        this.infoSender = infoSender;
        this.command = command;
    }

    /**
     * Метод, выводящий информацию о несуществовании команды
     */
    @Override
    public void execute() {
        this.infoSender.sendLine("команды \"" + this.command + "\" нет, чтобы вывести список комманд используйте help");
    }
}
