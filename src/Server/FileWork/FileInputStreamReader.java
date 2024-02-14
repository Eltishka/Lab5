package Server.FileWork;

import Server.InfoSender;
import Server.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileInputStreamReader extends FileReader {
    InputStreamReader reader;

    public FileInputStreamReader(InfoSender infoSender) {
        super(infoSender);
    }

    private void close(){
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                infoSender.sendLine(e.getStackTrace());
            }
        }
    }

    private void openFile(String fileName){
        try {
            this.reader = new InputStreamReader(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            infoSender.sendLine("Файл " + fileName + " не найден");
            infoSender.sendLine(e.getStackTrace());
        }
    }
    private String readLine(){
        String str = null;
        try {
            int ch = this.reader.read();
            str = (ch != -1) ? "" : null;

            while (ch != (int)'\n' && ch != -1) {
                str += (char)ch;
                ch = this.reader.read();

            }
        }
        catch (IOException e){
            infoSender.sendLine(e.getStackTrace());
        }

        return str;
    }

    private ArrayList<String> readArguments(){
        ArrayList<String> args = new ArrayList<String>(5);
        for(int i = 0; i < 5; i++){
            args.add(this.readLine());
            if(args.get(i) == "exit")
                break;
        }
        return args;
    }

    @Override
    public ArrayList<String> readWholeFile(String fileName){

        this.openFile(fileName);
        ArrayList<String> res = new ArrayList<>();
        String str = readLine();
        while (str != null){
            res.add(str);
            str = readLine();
        }
        this.close();
        return res;
    }
    @Override
    public LinkedList<Pair<String, ArrayList<String>>> readCommandsFromFile(String fileName) {

        this.openFile(fileName);
        LinkedList<Pair<String, ArrayList<String>>> commandList = new LinkedList<Pair<String, ArrayList<String>>>();
        String command = "";
        ArrayList<String> argument;
        while (true) {
            command = this.readLine();
            argument = new ArrayList<String>(5);
            if(command == "exit" || command == null || argument.contains("exit"))
                break;

            if (command.contains("add") || command.contains("update"))
                argument = this.readArguments();
            commandList.add(new Pair(command, argument));
        }
        this.close();
        return commandList;

    }
}
