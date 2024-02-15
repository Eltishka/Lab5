package ObjectSpace.Exceptions;

public class FuelTypeException extends ArgumentVehicleException{
    public FuelTypeException(String msg, int argumentNumber){
        super(msg, argumentNumber);
    }
    public FuelTypeException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause, argumentNumber);
    }
}
