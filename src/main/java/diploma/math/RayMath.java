package diploma.math;

import diploma.geometry.Color;
import diploma.geometry.Matrix;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.ray.Ray;

import static diploma.math.FloatUtils.format;

public class RayMath {

    public static Tuple add(Tuple t1, Tuple t2) {
        return new Tuple(format(t1.getX() + t2.getX(), "#.#"),
                format(t1.getY() + t2.getY(), "#.#"),
                format(t1.getZ() + t2.getZ(), "#.#"),
                format(t1.getW() + t2.getW(), "#.#"));
    }

    public static Tuple subtract(Tuple t1, Tuple t2) {
        return new Tuple(format(t1.getX() - t2.getX(), "#.#"),
                format(t1.getY() - t2.getY(), "#.#"),
                format(t1.getZ() - t2.getZ(), "#.#"),
                format(t1.getW() - t2.getW(), "#.#"));
    }

    public static Tuple multiply(Tuple tuple, float mult) {
        return new Tuple(tuple.getX() * mult, tuple.getY() * mult, tuple.getZ() * mult, tuple.getW() * mult);
    }

    public static Tuple multiplyTuples(Tuple t1, Tuple t2) {
        return new Tuple(t1.getX() * t2.getX(),
                t1.getY() * t2.getY(),
                t1.getZ() * t2.getZ(),
                t1.getW() * t2.getW());
    }

    public static Tuple divide(Tuple tuple, float div) {
        return new Tuple(tuple.getX() / div, tuple.getY() / div, tuple.getZ() / div, tuple.getW() / div);
    }

    public static float dotProduct(Tuple t1, Tuple t2) {
        return t1.getX() * t2.getX() + t1.getY() * t2.getY() + t1.getZ() * t2.getZ() + t1.getW() * t2.getW();
    }

    public static Tuple normalize(Tuple tuple) {
        float mag = magnitude(tuple);
        return new Tuple(format(tuple.getX() / mag, "#.#####"), format(tuple.getY() / mag, "#.#####"),
                format(tuple.getZ() / mag, "#.#####"), format(tuple.getW() / mag, "#.#####"));
    }

    public static float magnitude(Tuple tuple) {
        return format((float) Math.sqrt(tuple.getX() * tuple.getX()
                + tuple.getY() * tuple.getY()
                + tuple.getZ() * tuple.getZ()
                + tuple.getW() * tuple.getW()), "#.#####");
    }

    public static Tuple cross(Tuple t1, Tuple t2) {
        float x = t1.getY() * t2.getZ() - t1.getZ() * t2.getY();
        float y = t1.getZ() * t2.getX() - t1.getX() * t2.getZ();
        float z = t1.getX() * t2.getY() - t1.getY() * t2.getX();
        return new Tuple(x, y, z, t1.getW());
    }

    public static Color hadamardProduct(Color c1, Color c2) {
        float red = c1.getX() * c2.getX();
        float green = c1.getY() * c2.getY();
        float blue = c1.getZ() * c2.getZ();
        return new Color(red, green, blue);
    }

    public static Tuple position(Ray ray, float time) {
        return add(ray.getOrigin(), multiply(ray.getDirection(), time));
    }

    public static Ray transform(Ray ray, Matrix matrix) {
        Tuple origin = MatrixMath.multiplyByTuple(matrix, ray.getOrigin());
        Tuple direction = MatrixMath.multiplyByTuple(matrix, ray.getDirection());
        return new Ray(origin, direction);
    }

    public static Tuple reflect(Tuple first, Tuple second) {
        float dot = RayMath.dotProduct(first, second);
        return RayMath.subtract(first, RayMath.multiply(second, dot * 2));
    }
}
