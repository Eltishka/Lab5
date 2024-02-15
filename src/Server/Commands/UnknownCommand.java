package Server.Commands;

import ObjectSpace.Vehicle;
import Server.InfoSender;

public class UnknownCommand implements Command {
    private InfoSender infoSender;
    private String command;
    public UnknownCommand(String command, InfoSender infoSender){
        this.infoSender = infoSender;
        this.command = command;
    }

    @Override
    public void execute() {
        this.infoSender.sendLine("команды \"" + this.command + "\" нет, чтобы вывести список комманд используйте help");
    }
}
