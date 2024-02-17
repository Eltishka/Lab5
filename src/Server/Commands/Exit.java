package Server.Commands;

/**
 * @author Piromant
 * Реализация команды exit
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
