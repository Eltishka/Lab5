import client.Terminal;
import commands.*;
import objectspace.exceptions.VehicleException;
import server.Invoker;
/**
 * Точка запуска программы
 */
public class Main {
    public static void main(String[] args) throws VehicleException {
        Invoker invoker = Invoker.getAccess();
        invoker.register("add", Add.class);
        invoker.register("add_if_max", AddIfMax.class);
        invoker.register("add_if_min", AddIfMin.class);
        invoker.register("clear", Clear.class);
        invoker.register("save", Save.class);
        invoker.register("average_of_engine_power", AverageOfEnginePower.class);
        invoker.register("execute_script", ExecuteScript.class);
        invoker.register("exit", Exit.class);
        invoker.register("filter_contains_name", FilterContainsName.class);
        invoker.register("help", Help.class);
        invoker.register("history", History.class);
        invoker.register("info", Info.class);
        invoker.register("print_field_descending_engine_power", PrintFieldDescendingEnginePower.class);
        invoker.register("remove_by_id", RemoveById.class);
        invoker.register("show", Show.class);
        invoker.register("update", Update.class);

        Terminal terminal = new Terminal();
        terminal.start();
    }
}
