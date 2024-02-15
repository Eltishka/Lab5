package ObjectSpace.Exceptions;

public abstract class ArgumentVehicleException extends Exception{
    public final int argumentNumber;

    public ArgumentVehicleException(String msg, int argumentNumber){
        super(msg);
        this.argumentNumber = argumentNumber;
    }
    public ArgumentVehicleException(String msg, Throwable cause, int argumentNumber){
        super(msg, cause);
        this.argumentNumber = argumentNumber;
    }

}
