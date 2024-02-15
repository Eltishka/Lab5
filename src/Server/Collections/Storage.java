package Server.Collections;

import ObjectSpace.Vehicle;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;

public class Storage<T extends Vehicle> extends LinkedHashSet<T> {

    private Date creationDate;
    public Storage(Collection<T> collection) {
        super(collection);
        this.creationDate = new Date();
    }

    public Storage() {
        super();
        this.creationDate = new Date();
    }

    public Date getCreationDate() {
        return creationDate;
    }

}