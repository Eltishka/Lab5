package server;

import objectspace.Vehicle;

import java.util.ArrayList;

/**
 * Реквест хранит три поля: имя команды, ее аргумент и элемент
 */
public class Request {
    public final String command_name;
    public final String argument;
    public final Vehicle element;


    public Request(String command, ArrayList<String> element) {
        String[] commandParts = command.split(" ");
        this.command_name = commandParts[0];

        Vehicle v = null;
        if(element.size() > 0)
            v = Vehicle.parseVehicle(element.toArray(new String[5]));

        this.element = v;

        if(commandParts.length > 1)
            this.argument = commandParts[1];
        else
            this.argument = "";
    }
}
