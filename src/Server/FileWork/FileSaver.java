package Server.FileWork;

import ObjectSpace.Vehicle;

import java.io.IOException;
import java.util.Collection;
/**
 * @author Piromant
 * Интерфейс для сохранения коллекции
 */
public interface FileSaver {
    /**
     * Метод сохраняющий коллекцию в файл в формате
     * @param fileName имя файла, в который будет идти сохранение
     * @param arr колекция, которая будет сохранена
     * @throws IOException
     * @throws SecurityException
     */
    void save(String fileName, Collection<Vehicle> arr) throws IOException, SecurityException;
}
