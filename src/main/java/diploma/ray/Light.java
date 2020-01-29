package diploma.ray;

import diploma.geometry.Color;
import diploma.geometry.Point;
import diploma.geometry.Tuple;

public class Light {

    private Tuple intensity;
    private Tuple position;

    public Light(Tuple intensity, Tuple position) {
        this.intensity = intensity;
        this.position = position;
    }

    public Tuple getIntensity() {
        return intensity;
    }

    public Tuple getPosition() {
        return position;
    }

    public void setIntensity(Color intensity) {
        this.intensity = intensity;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Light) {
            return getIntensity().equals(((Light) obj).getIntensity()) && getPosition().equals(((Light) obj).getPosition());
        }
        return false;
    }
}
