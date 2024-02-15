package Server.FileWork;

import Server.InfoSender;
import Server.Pair;

import java.util.ArrayList;
import java.util.LinkedList;

public interface FileReader {
    LinkedList<Pair<String, ArrayList<String>>> readCommandsFromFile(String fileName);

}
