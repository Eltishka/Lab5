package ObjectSpace;

/**
 * @author Piromant
 * Класс координат в формате (x, y)
 */
public class Coordinates {
    private Double x; //Поле не может быть null
    private Long y; //Поле не может быть null

    public Coordinates(Double x, Long y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString(){
        return "(" + x.toString() + ", " + y.toString() + ")";
    }
}