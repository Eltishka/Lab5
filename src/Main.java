import ObjectSpace.Exceptions.VehicleException;
import UserInterface.Terminal;

public class Main {
    public static void main(String[] args) throws VehicleException {
        Terminal terminal = new Terminal();
        terminal.start();
    }
}
