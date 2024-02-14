package Server.FileWork;

import ObjectSpace.Vehicle;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Collection;

public class XMLSaver implements FileSaver { //TODO  REFACTOR

    private Document createFile(Collection<Vehicle> storage) {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = null;
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("VehicleStorage");
        doc.appendChild(rootElement);

        for(Vehicle el: storage) {
            Element vehicle = doc.createElement("Vehicle");
            rootElement.appendChild(vehicle);

            Element id = doc.createElement("id");
            id.setTextContent(Integer.valueOf(el.getId()).toString());
            vehicle.appendChild(id);

            Element name = doc.createElement("name");
            name.setTextContent(el.getName());
            vehicle.appendChild(name);

            Element coords = doc.createElement("coordinates");
            coords.setTextContent(el.getCoordinates().toString());
            vehicle.appendChild(coords);

            Element creationDate = doc.createElement("creationDate");
            creationDate.setTextContent(el.getCreationDate().toString());
            vehicle.appendChild(creationDate);

            Element enginePower = doc.createElement("enginePower");
            enginePower.setTextContent(el.getEnginePower().toString());
            vehicle.appendChild(enginePower);

            Element type = doc.createElement("type");
            type.setTextContent(el.getType().toString());
            vehicle.appendChild(type);

            Element fuelType = doc.createElement("fuelType");
            fuelType.setTextContent(el.getFuelType().toString());
            vehicle.appendChild(fuelType);
        }
        return doc;
    }


    @Override
    public void save(String fileName, Collection arr) {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }

        // pretty print
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        Document doc = createFile(arr);

        DOMSource source = new DOMSource(doc);

        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            StreamResult result = new StreamResult(fos);
            try {
                transformer.transform(source, result);
            } catch (TransformerException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
