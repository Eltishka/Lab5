package Server.Commands;

import Server.Collections.Storage;
import Server.FileWork.FileReader;
import Server.FileWork.FileSaver;
import Server.InfoSender;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Save implements Command{
    private final FileSaver fileSaver;
    private final Storage storage;
    private final String fileName;
    private final InfoSender infoSender;

    public Save(FileSaver fileSaver, String fileName, Storage storage, InfoSender infoSender){
        this.fileSaver = fileSaver;
        this.storage = storage;
        this.infoSender = infoSender;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        try {
            this.fileSaver.save(fileName, this.storage);
        } catch (FileNotFoundException e) {
            infoSender.sendLine("Файл не найден");
        } catch (SecurityException e){
            infoSender.sendLine("Не хватает прав для доступа к файлу");
        } catch (Exception e) {
            infoSender.sendLine("Непредвиденная ошибка");
            infoSender.sendLine(e.getMessage());
        }
    }
}
