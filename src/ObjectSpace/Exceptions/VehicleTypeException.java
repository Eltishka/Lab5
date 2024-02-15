package ObjectSpace.Exceptions;

public class VehicleTypeException extends ArgumentVehicleException{
    public VehicleTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public VehicleTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
