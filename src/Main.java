import objectspace.exceptions.VehicleException;
import userinterface.Terminal;

/**
 * Точка запуска программы
 */
public class Main {
    public static void main(String[] args) throws VehicleException {
        Terminal terminal = new Terminal();
        terminal.start();
    }
}
