package server.Commands;

import server.Response;
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

    public History(Deque<Pair<String, Command>> history){
        this.history = history;
    }

    /**
     * Метод, выводящий последние 7 команд
     */
    @Override
    public Response execute() {
        return new Response(history.stream().map(Pair::getFirst).collect(Collectors.toCollection(LinkedList::new)).toArray());
    }
}
