package Server;

import java.util.Collection;

public interface InfoSender {
    public void sendLine(Object msg);
    public void sendMultiLines(Collection msg);
}
