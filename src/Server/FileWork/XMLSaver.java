package Server.FileWork;

import ObjectSpace.Vehicle;
import java.io.*;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class XMLSaver implements FileSaver {

    public XMLSaver(){}
    private Queue<String> createFileContent(Collection<Vehicle> storage) {
        Queue<String> xml = new LinkedList<>();
        xml.add("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.add("<Storage>\n");
        for(Vehicle el: storage) {
            xml.add("\t<Vehicle>\n");

            xml.add("\t\t<id>");
            xml.add(el.getId().toString());
            xml.add("</id>\n");

            xml.add("\t\t<name>");
            xml.add(el.getName());
            xml.add("</name>\n");

            xml.add("\t\t<coordinates>");
            xml.add(el.getCoordinates().toString());
            xml.add("</coordinates>\n");

            xml.add("\t\t<creation_date>");
            xml.add(el.getCreationDate().toString());
            xml.add("</creation_date>\n");

            xml.add("\t\t<engine_power>");
            xml.add(el.getEnginePower().toString());
            xml.add("</engine_power>\n");

            xml.add("\t\t<vehicle_type>");
            xml.add(el.getType().toString());
            xml.add("</vehicle_type>\n");

            xml.add("\t\t<fuel_type>");
            xml.add(el.getFuelType().toString());
            xml.add("</fuel_type>\n");

            xml.add("\t</Vehicle>\n");
        }
        xml.add("</Storage>");
        return xml;
    }

    @Override
    public void save(String fileName, Collection arr) throws IOException, SecurityException {
        BufferedWriter writter = new BufferedWriter(new FileWriter(fileName));
        Queue<String> xml = this.createFileContent(arr);
        while (!xml.isEmpty()) {
            String line = xml.poll();
            writter.write(line);
        }
        writter.close();

    }
}
