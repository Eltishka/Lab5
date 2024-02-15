package Server.Commands;

import Server.InfoSender;

import java.util.Deque;

public class History implements Command{
    Deque<String> history;
    InfoSender infoSender;

    public History(Deque<String> history, InfoSender infoSender){
        this.history = history;
        this.infoSender = infoSender;
    }

    @Override
    public void execute() {
        this.infoSender.sendMultiLines(history);
    }
}
