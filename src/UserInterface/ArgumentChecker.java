package UserInterface;

@FunctionalInterface
public interface ArgumentChecker<String> {
    public boolean check(String arg);
}
