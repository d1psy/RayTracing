package diploma.geometry;

import diploma.Creator;

public class Tuple {

    private double x;
    private double y;
    private double z;
    private double w;

    public Tuple(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Tuple add(Tuple tuple) {
        return new Tuple(getX() + tuple.getX(),
                getY() + tuple.getY(),
                getZ() + tuple.getZ(),
                getW() + tuple.getW());
    }

    public Tuple subtract(Tuple tuple) {
        return new Tuple(getX() - tuple.getX(),
                getY() - tuple.getY(),
                getZ() - tuple.getZ(),
                getW() - tuple.getW());
    }

    public Tuple multiply(double mult) {
        return new Tuple(getX() * mult, getY() * mult, getZ() * mult, getW() * mult);
    }

    public Tuple multiplyTuple(Tuple tuple) {
        return new Tuple(getX() * tuple.getX(),
                getY() * tuple.getY(),
                getZ() * tuple.getZ(),
                getW() * tuple.getW());
    }

    public Tuple divide(double div) {
        return new Tuple(getX() / div, getY() / div, getZ() / div, getW() / div);
    }

    public double dotProduct(Tuple t2, String pattern) {
        return getX() * t2.getX()
                + getY() * t2.getY()
                + getZ() * t2.getZ()
                + getW() * t2.getW();
    }

    public Tuple normalize() {
        double mag = magnitude();
        return new Tuple(getX() / mag, getY() / mag, getZ() / mag, getW() / mag);
    }

    public double magnitude() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ() + getW() * getW());
    }

    public Tuple cross(Tuple t2) {
        double x = getY() * t2.getZ() - getZ() * t2.getY();
        double y = getZ() * t2.getX() - getX() * t2.getZ();
        double z = getX() * t2.getY() - getY() * t2.getX();
        return new Tuple(x, y, z, getW());
    }

    public Tuple reflect(Tuple tuple) {
        double dot = dotProduct(tuple, "#.#");
        return subtract(tuple.multiply(dot * 2));
    }

    public Tuple negate() {
        return new Tuple(-x, -y, -z, -w);
    }

    public Matrix viewTransform(Tuple to, Tuple up) {
        Tuple forward = to.subtract(this).normalize();
        Tuple upn = up.normalize();
        Tuple left = forward.cross(upn);
        Tuple trueUp = left.cross(forward);
        double[][] data =
                {{left.getX(), left.getY(), left.getZ(), 0},
                        {trueUp.getX(), trueUp.getY(), trueUp.getZ(), 0},
                        {-forward.getX(), -forward.getY(), -forward.getZ(), 0},
                        {0, 0, 0, 1}};

        Matrix orientation = new Matrix(data);
        return orientation.multiply(Creator.createTranslationMatrix(-getX(), -getY(), -getZ()));
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getW() {
        return w;
    }

    public void setW(double w) {
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
