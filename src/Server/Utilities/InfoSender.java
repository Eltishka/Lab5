package Server.Utilities;

import java.util.Collection;
/**
 * @author Piromant
 * Интерфейс для отправки информации куда-либо
 */
public interface InfoSender {
    /**
     * Метод для отправки одной строки
     * @param msg в даном случае один объект, которую мы хотим вывести
     */
    void sendLine(Object msg);
    /**
     * Метод для отправки в нескольких строках коллекции
     * @param msg в даном случае коллекция, которую мы хотим вывести
     */
    void sendMultiLines(Collection msg);
}
