package Server;

import java.util.Collection;

public class OutStreamInfoSender implements InfoSender {

    @Override
    public void sendLine(Object msg){
        System.out.println(msg);
    }

    @Override
    public void sendMultiLines(Collection msg){
        for(Object o: msg)
            sendLine(o);
    }



}
