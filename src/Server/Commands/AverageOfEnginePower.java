package Server.Commands;

import ObjectSpace.Vehicle;
import Server.Collections.Storage;
import Server.Utilities.InfoSender;
/**
 * @author Piromant
 * Реализация команды average_of_engine_power
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
