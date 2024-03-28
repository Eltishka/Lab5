package dataexchange;

/**
 * Класс ответа команды
 * @author Piromant
 */
public class Response {

    private final Object[] response;

    public Response(Object... response) {
        this.response = response;
    }

    public Object[] getResponse(){
        return this.response;
    }
}
