package server;

import java.util.ArrayList;
import java.util.Arrays;

public class Response {

    private final Object[] response;

    public Response(Object... response) {
        this.response = response;
    }

    public Object[] getResponse(){
        return this.response;
    }
}
