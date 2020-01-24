package diploma.play;

import diploma.geometry.Tuple;

public class Environment {

    private Tuple gravity;
    private Tuple wind;

    public Environment (Tuple gravity, Tuple wind) {
        this.gravity = gravity;
        this.wind = wind;
    }

    public Tuple getGravity() {
        return gravity;
    }

    public Tuple getWind() {
        return wind;
    }

    public void setGravity(Tuple gravity) {
        this.gravity = gravity;
    }

    public void setWind(Tuple wind) {
        this.wind = wind;
    }
}
