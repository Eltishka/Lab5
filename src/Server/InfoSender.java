package Server;

import java.util.Collection;

public interface InfoSender {
    void sendLine(Object msg);
    void sendMultiLines(Collection msg);
}
