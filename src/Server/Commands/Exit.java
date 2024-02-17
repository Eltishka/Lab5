package Server.Commands;

/**
 * 
 * Реализация команды exit
 * @author Piromant
 */
public class Exit implements Command{
    /**
     * Метод, завершающий работу программы без сохранения коллекции
     */
    @Override
    public void execute() {
        System.exit(0);
    }
}
