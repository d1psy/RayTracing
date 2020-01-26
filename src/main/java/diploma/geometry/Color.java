package diploma.geometry;

public class Color extends Tuple {

    public Color() {
        super(0.0f, 0.0f, 0.0f, 0.0f);
    }

    public Color(float red, float green, float blue) {
        super(red, green, blue, 0);
    }
}
