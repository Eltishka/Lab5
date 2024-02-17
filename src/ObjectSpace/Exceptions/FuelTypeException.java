package ObjectSpace.Exceptions;

/**
 * Класс, расширяющий ArgumentVehicleException, обозначающий ошибку в работе с типом топлива (FuelType)
 * @see ObjectSpace.FuelType
 * @author Piromant
 */
public class FuelTypeException extends ArgumentVehicleException{
    public FuelTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public FuelTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
