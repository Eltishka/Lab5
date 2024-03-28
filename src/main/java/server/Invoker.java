package server;

import commands.Command;
import commands.Help;
import commands.History;
import commands.UnknownCommand;
import objectspace.Vehicle;
import server.database.Storage;

import java.util.Deque;
import java.util.HashMap;

public class Invoker {
    private static Invoker invoker;

    private HashMap<String, Class<? extends Command>> commandMap;

    private Invoker(){
        this.commandMap = new HashMap<>();
    }

    public static Invoker getAccess(){
        if(invoker == null)
            invoker = new Invoker();
        return invoker;
    }

    public Class get(String name){
        if(!commandMap.keySet().contains(name))
            return UnknownCommand.class;
        return commandMap.get(name);
    }

    public void register(String name, Class<? extends Command> command){
        this.commandMap.put(name, command);

    }

    public <T extends Vehicle> Command getCommandToExecute(String commandName, Storage<T> storage, String argument, T el, Deque history) {
        Command instance = new UnknownCommand(storage, argument, el, commandName);
        if(this.commandMap.containsKey(commandName)) {
            try {
                Class<? extends  Command> command = this.commandMap.get(commandName);
                if(command.equals(History.class)){
                    instance = (Command) command.getConstructors()[0].newInstance(storage, argument, el, history);
                } else if(command.equals(Help.class)){
                    instance = (Command) command.getConstructors()[0].newInstance(storage, argument, el, commandMap);
                }
                else{
                    instance = (Command) command.getConstructors()[0].newInstance(storage, argument, el);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        }
        return instance;

    }
}
