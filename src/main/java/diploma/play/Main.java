package diploma.play;

import diploma.Creator;
import diploma.geometry.Canvas;
import diploma.geometry.Color;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.math.IntersectionCollection;
import diploma.math.MatrixMath;
import diploma.math.RayMath;
import diploma.ray.Ray;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Matrix transform = Creator.createIdentityMatrix();
        ; // Matrix.scaling(0.5, 1, 1).shear(1, 0, 0, 0, 0, 0);
        Canvas canvas = new Main(transform).render();
        File fnew = new File("test.ppm");
        FileWriter f2;
        try {
            f2 = new FileWriter(fnew, false);
            f2.write(canvas.toPPM());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final GeometryObject sphere;
    private final float cameraZ;
    private final float filmZ;
    private final double filmSize;
    private final int filmPixels;

    private Main(Matrix transform) {
        GeometryObject sphere = new Sphere(1);
        sphere.setTransformation(transform);
        this.sphere = sphere;
        this.cameraZ = -2.0f;
        this.filmZ = 3;
        this.filmSize = 3;
        this.filmPixels = 150;
    }

    private Canvas render() {
        Canvas canvas = new Canvas(filmPixels, filmPixels);
        Color color = new Color(1.0f, 0.0f, 0.0f);
        for (int i = 0; i < filmPixels; i++) {
            for (int j = 0; j < filmPixels; j++) {
                Ray cameraRay = getCameraRayForPixel(i, j);
                List<Intersection> intersections = IntersectionCollection.getIntersection(cameraRay, sphere);
                if (!intersections.isEmpty()) {
                    Intersection intersection1 = intersections.get(0);
                    Intersection intersection2 = intersections.get(1);

                    IntersectionCollection collection = new IntersectionCollection(intersection1, intersection2);
                    Intersection hit = collection.hit();
                    if (hit != null) {
                        canvas.writeToPixel(i, j, color);
                    }
                }
            }
        }
        return canvas;
    }

    private Ray getCameraRayForPixel(int x, int y) {
        Tuple cameraPos = new Point(0.0f, 0.0f, cameraZ);
        Tuple filmPos = new Point(getFilmPos(x), getFilmPos(y), filmZ);
        Tuple direction = RayMath.normalize(RayMath.subtract(filmPos, cameraPos));
        return new Ray(cameraPos, direction);
    }

    // Compute x/y position of film given pixel index.
    private float getFilmPos(int x) {
        double minFilm = -filmSize;
        double maxFilm = filmSize;
        int minIndex = 0;
        int maxIndex = filmPixels - 1;
        return (float) (minFilm + (double) (x - minIndex) * (maxFilm - minFilm) / (maxIndex - minIndex));
    }

//    public static void main(String[] args) {
//        Tuple origin = new Point(0.0f, 0.0f, -5.0f);
//        Tuple direction = new Vector(0.0f, 0.0f, 1.0f);
//        Matrix scaling = Creator.createScalingMatrix(2.0f, 2.0f, 2.0f);
//        Ray ray = new Ray(origin, direction);
//        System.out.println();
//        System.out.println();
//        Tuple start = new Point(0.0f, 30.0f, 0.0f);
//        Matrix rotationMatrix = Creator.zRotationMatrix(6.0f);
//        Canvas canvas = new Canvas(100, 100);
//        Color color = new Color(1.0f, 0.0f, 0.0f);
//        File fnew = new File("test.ppm");
//        FileWriter f2;
//        int test = 20;
//        int cur = 0;
//        canvas.writeToPixel(50 + (int) start.getX(), 50 + (int) start.getY(), color);
//        while (cur < test) {
//            start = MatrixMath.multiplyByTuple(rotationMatrix, start);
//            canvas.writeToPixel(50 + (int) start.getX(), 50 + (int) start.getY(), color);
//            cur++;
//        }
////
//        try {
//            f2 = new FileWriter(fnew, false);
//            f2.write(canvas.toPPM());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println(canvas.toPPM());

//    }

    private static Projectile tick(Environment env, Projectile proj) {
        Tuple newPos = RayMath.add(proj.getPosition(), proj.getVelocity());
        Tuple newVelocity = RayMath.add(RayMath.add(proj.getVelocity(), env.getGravity()), env.getWind());
        return new Projectile(newPos, newVelocity);
    }
}
