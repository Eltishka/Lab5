package ObjectSpace.Exceptions;

/**
 * Класс, расширяющий RuntimeException, обозначающий ошибку в работе с VehicleType
 * @see ObjectSpace.Vehicle
 * @author Piromant
 */
public class VehicleTypeException extends ArgumentVehicleException{
    public VehicleTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public VehicleTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
