package ObjectSpace.Exceptions;

/**
 * @author Piromant
 * Абстрактный класс ошибки при работе аргументами объекта типа Vehicle
 * @see ObjectSpace.Vehicle
 */
public abstract class ArgumentVehicleException extends Exception{

    /**
     * Поле равное порядковому номеру аргумента в вводе, то есть каким по счету вводится аргумент
     */
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
