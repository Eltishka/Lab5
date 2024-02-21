package server.database;

import objectspace.Vehicle;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
/**
 * Класс коллекции, расширяющий LinkedHashSet. Основное отличие в хранение и предоставлении даты создания
 * @author Piromant
 */
public class Storage<T extends Vehicle> extends LinkedHashSet<T> {

    /**
     * ДАта создания коллекции
     */
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