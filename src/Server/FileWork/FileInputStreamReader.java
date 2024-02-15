package Server.FileWork;

import Server.InfoSender;
import Server.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class FileInputStreamReader implements FileReader {
    InputStreamReader reader;
    InfoSender infoSender;

    public FileInputStreamReader(InfoSender infoSender) {
        this.infoSender = infoSender;
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

    private boolean openFile(String fileName){
        try {
            this.reader = new InputStreamReader(new FileInputStream(fileName));
            return true;
        } catch (FileNotFoundException | NullPointerException e) {
            infoSender.sendLine("Файл не найден");
            return false;
        } catch (SecurityException e){
            infoSender.sendLine("Не хватает прав для доступа к файлу");
            return false;
        }
    }
    private String readLine(){
        String str = null;
        try {
            int ch = this.reader.read();
            str = (ch != -1) ? "" : null;

            while (ch != (int)'\r' && ch != -1) {

                if(ch != (int)'\n') {
                    str += (char) ch;
                }
                else if(!str.equals("")) {
                    break;
                }
                ch = this.reader.read();

            }
        }
        catch (IOException e){
            infoSender.sendLine(e.getStackTrace());
        }

        return str;
    }

    private ArrayList<String> readArguments(){
        ArrayList<String> args = new ArrayList<String>();
        for(int i = 0; i < 5; i++){
            args.add(this.readLine());
            if(args.get(i) == "exit")
                break;
        }
        return args;
    }


    @Override
    public LinkedList<Pair<String, ArrayList<String>>> readCommandsFromFile(String fileName) {

        LinkedList<Pair<String, ArrayList<String>>> commandList = new LinkedList<Pair<String, ArrayList<String>>>();
        if(!this.openFile(fileName))
            return commandList;
        String command = "";
        ArrayList<String> argument;
        while (true) {
            command = this.readLine();
            argument = new ArrayList<String>();
            if(command == "exit" || command == null || argument.contains("exit"))
                break;
            if (command.equals("add") || command.contains("update"))
                argument = this.readArguments();
            commandList.add(new Pair(command, argument));
        }
        this.close();
        return commandList;

    }
}
