package Server.Commands;

import Server.Collections.Storage;
import Server.InfoSender;

public class Show implements Command{
    private Storage storage;
    private InfoSender infoSender;

    public Show(Storage storage, InfoSender infoSender){
        this.storage = storage;
        this.infoSender = infoSender;
    }
    @Override
    public void execute() {
        this.infoSender.sendMultiLines(storage);
    }
}
