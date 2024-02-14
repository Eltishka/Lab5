package ObjectSpace;

public class VehicleException extends RuntimeException{
    public VehicleException(String msg){
        super(msg);
    }
    public VehicleException(String msg, Throwable cause){
        super(msg, cause);
    }
}
