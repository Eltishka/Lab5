import ObjectSpace.Exceptions.VehicleException;
import UserInterface.Terminal;

/**
 * Точка запуска программы
 */
public class Main {
    public static void main(String[] args) throws VehicleException {
        Terminal terminal = new Terminal();
        terminal.start();
    }
}
