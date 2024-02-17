package server.filework;

import objectspace.Coordinates;
import objectspace.FuelType;
import objectspace.Vehicle;
import objectspace.VehicleType;
import server.database.Storage;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Класс, реализующий StorageLoader. Служит для загрузки коллекции из XML-файла
 * @see StorageLoader
 * @author Piromant
 */
public class XMLLoader implements StorageLoader{
    /**
     * @see FileReader
     */
    private FileReader fileReader;
    /**
     * Имя файла, из которого загружается коллекция
     */
    private String fileName;

    public XMLLoader(FileReader fileReader, String fileName){
        this.fileReader = fileReader;
        this.fileName = fileName;
    }

    @Override
    public Storage<Vehicle> loadStorage() throws IOException, ParseException, NullPointerException {
        Storage<Vehicle> storage = new Storage<>();
        ArrayList<String> fileContent = new ArrayList<String>(Arrays.asList(fileReader.readWholeFile(fileName).split("\n")));

        String name = "";
        int id = 0;
        Coordinates coord = null;
        Date creationDate = null;
        Long enginePower = 0L;
        VehicleType type = null;
        FuelType fuelType;

        for (String value : fileContent) {
            if (value.contains("<name>")) {
                String s = value.trim();
                name = s.substring(6, s.length() - 7);
            } else if (value.contains("<id>")) {
                String s = value.trim();
                id = Integer.parseInt(s.substring(4, s.length() - 5));
            } else if (value.contains("<coordinates>")) {
                String s = value.trim().replace("(", "").replace(")", "");
                String[] args = s.substring(13, s.length() - 14).split(", ");
                coord = new Coordinates(Double.parseDouble(args[0]), Long.parseLong(args[1]));
            } else if (value.contains("<creation_date>")) {
                String s = value.trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
                dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Moscow"));
                creationDate = dateFormat.parse(s.substring(15, s.length() - 16));
            } else if (value.contains("<engine_power>")) {
                String s = value.trim();

                enginePower = Long.parseLong(s.substring(14, s.length() - 15));
            } else if (value.contains("<vehicle_type>")) {
                String s = value.trim();
                type = VehicleType.valueOf(s.substring(14, s.length() - 15));
            } else if (value.contains("<fuel_type>")) {
                String s = value.trim();
                fuelType = FuelType.valueOf(s.substring(11, s.length() - 12));
                Vehicle v = new Vehicle(name, coord, enginePower, type, fuelType);
                v.setId(id);
                v.setCreationDate(creationDate);
                storage.add(v);
            }
        }
        return storage;
    }
}
