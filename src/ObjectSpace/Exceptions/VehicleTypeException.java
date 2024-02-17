package ObjectSpace.Exceptions;

/**
 * @author Piromant
 * Класс, расширяющий RuntimeException, обозначающий ошибку в работе с VehicleType
 * @see ObjectSpace.Vehicle
 */
public class VehicleTypeException extends ArgumentVehicleException{
    public VehicleTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public VehicleTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
