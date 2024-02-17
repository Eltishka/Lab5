package Server.Commands;

import Server.Collections.Storage;
import Server.FileWork.FileSaver;
import Server.Utilities.InfoSender;

import java.io.FileNotFoundException;
/**
 * @author Piromant
 * Реализация команды save
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
    /**
     * @see InfoSender
     */
    private final InfoSender infoSender;

    public Save(FileSaver fileSaver, String fileName, Storage storage, InfoSender infoSender){
        this.fileSaver = fileSaver;
        this.storage = storage;
        this.infoSender = infoSender;
        this.fileName = fileName;
    }

    /**
     * Метод, сохраняющий коллекцию в файл и выводящий что нет, если сохранение не удается
     */
    @Override
    public void execute() {
        try {
            this.fileSaver.save(fileName, this.storage);
            this.infoSender.sendLine("Файл сохранен");
        } catch (FileNotFoundException e) {
            infoSender.sendLine("Файл не найден");
        } catch (SecurityException e){
            infoSender.sendLine("Не хватает прав для доступа к файлу");
        } catch (NullPointerException e){
            infoSender.sendLine("Не удалось получить инофрмацию об имени файл, возможно переменная окружения SAVEFILE не опрделена");
        }
        catch (Exception e) {
            infoSender.sendLine("Непредвиденная ошибка");
            infoSender.sendLine(e.getMessage());
        }

    }
}
