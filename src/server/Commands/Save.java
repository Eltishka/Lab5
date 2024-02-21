package server.Commands;

import server.Response;
import server.database.Storage;
import server.filework.FileSaver;
import server.utilities.InfoSender;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * Реализация команды save
 * @author Piromant
 */
public class Save implements Command{
    /**
     * @see FileSaver
     */
    private final FileSaver fileSaver;
    /**
     * @see Storage
     */
    private final Storage storage;
    /**
     * Имя файла, в который будет сохранена коллекция
     */
    private final String fileName;

    public Save(FileSaver fileSaver, String fileName, Storage storage){
        this.fileSaver = fileSaver;
        this.storage = storage;
        this.fileName = fileName;
    }

    /**
     * Метод, сохраняющий коллекцию в файл и выводящий что нет, если сохранение не удается
     */
    @Override
    public Response execute() {
        List<String> response = new LinkedList<>();
        try {
            this.fileSaver.save(fileName, this.storage);
            response.add("Файл сохранен");
        } catch (FileNotFoundException e) {
            response.add("Файл не найден");
        } catch (SecurityException e){
            response.add("Не хватает прав для доступа к файлу");
        } catch (NullPointerException e){
            response.add("Не удалось получить инофрмацию об имени файл, возможно переменная окружения SAVEFILE не опрделена");
        }
        catch (Exception e) {
            response.add("Непредвиденная ошибка");
            response.add(e.getMessage());
        }
        return new Response(response.toArray());

    }
}
