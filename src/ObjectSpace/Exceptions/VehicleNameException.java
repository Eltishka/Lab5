package ObjectSpace.Exceptions;

public class VehicleNameException extends ArgumentVehicleException{
    public VehicleNameException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public VehicleNameException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
