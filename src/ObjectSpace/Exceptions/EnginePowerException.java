package ObjectSpace.Exceptions;

public class EnginePowerException extends ArgumentVehicleException{
    public EnginePowerException(String msg, int argumentNumber){
            super(msg, argumentNumber);
        }
    public EnginePowerException(String msg, Throwable cause, int argumentNumber){
            super(msg, cause, argumentNumber);
        }
}
