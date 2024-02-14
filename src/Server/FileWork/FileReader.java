package Server.FileWork;

import Server.InfoSender;
import Server.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class FileReader {
    InfoSender infoSender;
    public abstract LinkedList<Pair<String, ArrayList<String>>> readCommandsFromFile(String fileName);
    public abstract ArrayList<String> readWholeFile(String fileName);

    public FileReader(InfoSender infoSender){
        this.infoSender = infoSender;
    }
}
