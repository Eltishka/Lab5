package server.Commands;

import server.utilities.InfoSender;
import server.utilities.Pair;

import java.util.Deque;
import java.util.LinkedList;
import java.util.stream.Collectors;
/**
 * 
 * Реализация команды history
 * @author Piromant
 */
public class History implements Command{
    /**
     * История команд в виде пар (Имя, Объект класса команды)
     */
    Deque<Pair<String, Command>> history;
    /**
     * @see InfoSender
     */
    InfoSender infoSender;

    public History(Deque<Pair<String, Command>> history, InfoSender infoSender){
        this.history = history;
        this.infoSender = infoSender;
    }

    /**
     * Метод, выводящий последние 7 команд
     */
    @Override
    public void execute() {
        this.infoSender.sendMultiLines(history.stream().map(Pair::getFirst).collect(Collectors.toCollection(LinkedList::new)));
    }
}
