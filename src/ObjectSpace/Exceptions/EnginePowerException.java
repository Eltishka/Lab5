package ObjectSpace.Exceptions;

/**
 * @author Piromant
 * Класс, расширяющий ArgumentVehicleException, обозначающий ошибку в работе с силой двигателя
 * @see ObjectSpace.Vehicle
 */
public class EnginePowerException extends ArgumentVehicleException{
    public EnginePowerException(String msg, int argumentNumber){
            super(msg, argumentNumber);
        }
    public EnginePowerException(String msg, Throwable cause, int argumentNumber){
            super(msg, cause, argumentNumber);
        }
}
