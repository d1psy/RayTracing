package diploma.geometry;

public class Color extends Tuple {

    public Color() {
        super(0.0, 0.0, 0.0, 0.0);
    }

    public Color(double red, double green, double blue) {
        super(red, green, blue, 0);
    }

    public Color hadamardProduct(Color color) {
        double red = getX() * color.getX();
        double green = getY() * color.getY();
        double blue = getZ() * color.getZ();
        return new Color(red, green, blue);
    }
}
