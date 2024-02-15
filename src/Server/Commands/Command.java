package Server.Commands;

import ObjectSpace.Vehicle;

public interface Command {
    <T extends Vehicle> void execute();
}
