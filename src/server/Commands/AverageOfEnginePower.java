package server.Commands;

import objectspace.Vehicle;
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
    /**
     * @see InfoSender
     */
    private InfoSender infoSender;

    public <T extends Vehicle> AverageOfEnginePower(Storage<T> storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }

    /**
     * Метод, выводящий среднее значение силы двигателя по всем элементам коллекции
     */
    @Override
    public void execute() {
        double res = this.storage.stream().mapToDouble(Vehicle::getEnginePower).sum();
        if(res > 0)
             res /= this.storage.size();
        infoSender.sendLine(res);
    }
}
