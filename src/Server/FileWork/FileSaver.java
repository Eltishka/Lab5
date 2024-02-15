package Server.FileWork;

import java.io.IOException;
import java.util.Collection;

public interface FileSaver {
    void save(String fileName, Collection arr) throws IOException;
}
