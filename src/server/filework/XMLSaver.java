package server.filework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import objectspace.Vehicle;
import server.database.Storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
/**
 * Класс, реализующий интерфейс FileSaver, сохраняет коллекцию в XML формате
 * @author Piromant
 */
public class XMLSaver implements FileSaver {

    /**
     * Метод сохраняющий коллекцию в файл в формате xml
     * @param fileName имя файла, в который будет идти сохранение
     * @param arr колекция, которая будет сохранена
     * @throws IOException
     * @throws SecurityException
     */
    @Override
    public void save(String fileName, Storage<Vehicle> arr) throws IOException, SecurityException {

        ObjectMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File(fileName), arr);

    }
}
