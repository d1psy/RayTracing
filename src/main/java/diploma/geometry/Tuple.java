package diploma.geometry;

public class Tuple {

    private float x;
    private float y;
    private float z;
    private float w;

    public Tuple(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void negate() {
        x = -x;
        y = -y;
        z = -z;
        w = -w;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tuple) {
            return x == ((Tuple) obj).getX() && y == ((Tuple) obj).getY()
                    && z == ((Tuple) obj).getZ() && w == ((Tuple) obj).getW();
        }
        return false;
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y + " Z: " + z;
    }
}
