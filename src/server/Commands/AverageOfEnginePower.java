package server.Commands;

import objectspace.Vehicle;
import server.Response;
import server.database.Storage;
import server.utilities.InfoSender;
/**

 * Реализация команды average_of_engine_power
 * @author Piromant
 */
public class AverageOfEnginePower implements Command{
    /**
     * @see Storage
     */
    private Storage<? extends Vehicle> storage;

    public <T extends Vehicle> AverageOfEnginePower(Storage<T> storage){
        this.storage = storage;
    }

    /**
     * Метод, выводящий среднее значение силы двигателя по всем элементам коллекции
     */
    @Override
    public Response execute() {
        double res = this.storage.stream().mapToDouble(Vehicle::getEnginePower).sum();
        if(res > 0)
             res /= this.storage.size();
        return new Response(res);
    }
}
