package ObjectSpace.Exceptions;

/**
 * @author Piromant
 * Класс, расширяющий ArgumentVehicleException, обозначающий ошибку в работе с типом топлива (FuelType)
 * @see ObjectSpace.FuelType
 */
public class FuelTypeException extends ArgumentVehicleException{
    public FuelTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public FuelTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
