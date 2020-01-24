package diploma.play;

import diploma.geometry.Point;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;

public class Main {

    public static void main(String[] args) {
        Tuple gravity = new Vector(0.0f, -1.0f, 0.0f);
        Tuple wind = new Vector(1.0f, 0.0f, 3.0f);
        Tuple position = new Point(23.0f, 20.0f, 1.0f);
        Tuple velocity = new Vector(0.0f, 0.0f, 7.0f);
        Environment env = new Environment(gravity, wind);
        Projectile proj = new Projectile(position, velocity);

        long t = System.currentTimeMillis();
        long end = t+15000;
        while(System.currentTimeMillis() < end) {
            try {
                proj = tick(env, proj);
                System.out.println("position: " + proj.getPosition() + "  |  velocity: " + proj.getVelocity());
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static Projectile tick(Environment env, Projectile proj) {
        Tuple newPos = proj.getPosition().add(proj.getVelocity());
        Tuple newVelocity = proj.getVelocity().add(env.getGravity()).add(env.getWind());
        return new Projectile(newPos, newVelocity);
    }
}
