package Server.Utilities;

import java.util.Collection;
/**
 * @author Piromant
 * Класс реализующий интерфейс InfoSender, отправляет информацию в стандартный поток вывода
 * @see InfoSender
 */
public class OutStreamInfoSender implements InfoSender {

    /**
     * Метод для отправки одной строки в стандартный поток вывода
     * @param msg в даном случае коллекция, которую мы хотим вывести
     */
    @Override
    public void sendLine(Object msg){
        System.out.println(msg);
    }

    /**
     * Метод для отправки коллекции в стандартный поток вывода
     * @param msg в даном случае коллекция, которую мы хотим вывести
     */
    @Override
    public void sendMultiLines(Collection msg){
        for(Object o: msg)
            sendLine(o);
    }



}
