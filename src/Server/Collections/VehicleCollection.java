package Server.Collections;

import ObjectSpace.Vehicle;

import java.util.*;


public class VehicleCollection<T extends Vehicle> extends LinkedHashSet<T>{

    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public VehicleCollection(Collection<T> collection) {
        super(collection);
        creationDate = new Date();
    }

    public VehicleCollection() {
        super();
        creationDate = new Date();
    }

    public void update(int id, T el) {
        el.setId(id);
        this.remove(el);
        this.add(el);
    }

    public void removeById(int id){
        this.remove(new Vehicle(id));
    }
    public void addIfMax(T el) {
        TreeSet<T> sortedCollection = new TreeSet<T>(this);
        if(this.size() == 0 || sortedCollection.last().getEnginePower() < el.getEnginePower())
            this.add(el);
    }

    public void addIfMin(T el) {
        TreeSet<T> sortedCollection = new TreeSet<T>(this);
        if(this.size() == 0 || sortedCollection.first().getEnginePower() > el.getEnginePower())
            this.add(el);
    }

    public double averageOfEnginePower() {
        Iterator<T> iterator = this.iterator();
        double sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next().getEnginePower();
        }
        return sum / this.size();
    }

    public VehicleCollection<T> filterContainsName(String s) {
        VehicleCollection<T> result = new VehicleCollection<T>();
        for (T el : this) {
            if (el.getName().contains(s))
                result.add(el);
        }
        return result;
    }

    public VehicleCollection<T> FieldDescendingEnginePower() {
        TreeSet<T> sortedCollection = new TreeSet<T>(Collections.reverseOrder());
        sortedCollection.addAll(this);
        return new VehicleCollection<T>(sortedCollection);

    }

}
