package server;

import objectspace.Vehicle;

import java.util.ArrayList;

/**
 * Реквест хранит четыре поля: имя команды, ее аргумент, элемент и отправителя
 */
public class Request {
    public final String command_name;
    public final String argument;
    public final Vehicle element;
    public final Object sender;

    public Request(String command, ArrayList<String> element, Object sender) {
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

        this.sender = sender;
    }
}
