package diploma.geometry;

import java.lang.Math;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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

    public Tuple add(Tuple tuple) {
        return new Tuple(x + tuple.getX(), y + tuple.getY(), z + tuple.getZ(), w + tuple.getW());
    }

    public Tuple subtract(Tuple tuple) {
        if (w - tuple.getW() == 0) {
            return new Vector(x - tuple.getX(), y - tuple.getY(), z - tuple.getZ());
        } else {
            return new Point(x - tuple.getX(), y - tuple.getY(), z - tuple.getZ());
        }
    }

    public Tuple multiply(float mult) {
        return new Tuple(x*mult, y*mult, z*mult, w*mult);
    }

    public Tuple divide(float div) {
        return new Tuple(x/div, y/div, z/div, w/div);
    }

    public float magnitude() {
        return format((float)Math.sqrt(x*x + y*y + z*z + w*w));
    }

    public Tuple normalize() {
        float mag = magnitude();
        return new Tuple(format(x/mag), format(y/mag), format(z/mag), format(w/mag));
    }

    private float format(float x) {
        return Float.parseFloat(new DecimalFormat("#.#####", DecimalFormatSymbols.getInstance(Locale.US)).format(x));
    }

    public float dotProduct(Tuple tuple) {
        return x*tuple.getX() + y*tuple.getY() + z*tuple.getZ() + w*tuple.getW();
    }

    public Tuple cross(Tuple tuple) {
        float x = this.y*tuple.getZ() - this.z*tuple.getY();
        float y = this.z*tuple.getX() - this.x*tuple.getZ();
        float z = this.x*tuple.getY() - this.y*tuple.getX();
        return new Tuple(x, y, z, w);
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
