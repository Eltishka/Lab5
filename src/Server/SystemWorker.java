package Server;

public class SystemWorker {
    public void exit(){
        System.exit(0);
    }

    public String readVar(String var){
        return System.getenv(var);
    }
}
