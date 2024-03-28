import client.Terminal;

public class ClientStarter {
    public static void main(String[] args){
        Terminal terminal = new Terminal("localhost",  5555);
        terminal.start();
    }
}
