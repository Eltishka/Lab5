package ObjectSpace;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Random;

public class Vehicle implements Comparable<Vehicle> {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Long enginePower; //Поле не может быть null, Значение поля должно быть больше 0
    private VehicleType type; //Поле не может быть null
    private FuelType fuelType; //Поле не может быть null

    private static Random randomGenerator = new Random();
    public Vehicle(String name, Coordinates coordinates, Long enginePower, VehicleType type, FuelType fuelType) throws VehicleException {
        this.id = randomGenerator.nextInt(Integer.MAX_VALUE) + 1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = new Date();
        this.enginePower = enginePower;
        this.type = type;
        this.fuelType = fuelType;

        if(name == null || coordinates == null || enginePower == null || type == null || fuelType == null){
            throw new VehicleException("Никакое поле не может быть null", new NullPointerException());
        }

        if(enginePower < 0){
            throw new VehicleException("enginePower должен быть больше 0", new IllegalArgumentException(enginePower.toString()));
        }
    }


    public Vehicle(int id){
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Long getEnginePower() {
        return enginePower;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setEnginePower(Long enginePower) {
        this.enginePower = enginePower;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }


    public VehicleType getType() {
        return type;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    @Override
    public int compareTo(Vehicle o) {
        return this.enginePower.compareTo(o.enginePower);
    }

    @Override
    public boolean equals(Object v){
        return this.id.equals(((Vehicle) v).getId());
    }

    @Override
    public int hashCode(){
        return id;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coordinates=" + coordinates +
                ", creationDate=" + creationDate +
                ", enginePower=" + enginePower +
                ", type=" + type +
                ", fuelType=" + fuelType +
                '}';
    }

    public static Vehicle parseVehicle(String[] args) throws VehicleException{
        if(args.length < 5){
            throw new VehicleException("Недостаточно аргументов для создания объекта", new IllegalArgumentException(Integer.valueOf(args.length).toString()));
        }

        try {

            String name = args[0];
            String[] coord = args[1].split(" ");
            Double x = Double.parseDouble(coord[0]);
            Long y = Long.parseLong(coord[1]);
            Coordinates coordinates = new Coordinates(x, y);
            Long enginePower = Long.parseLong(args[2]);
            VehicleType type = VehicleType.valueOf(args[3]);
            FuelType fuelType = FuelType.valueOf(args[4]);
            return new Vehicle(name, coordinates, enginePower, type, fuelType);
        }
        catch (Exception e){
            throw new VehicleException("Неверный ввод аргументов объекта", e);
        }

    }
}