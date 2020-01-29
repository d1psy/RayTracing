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
import diploma.material.Material;
import diploma.math.IntersectionCollection;
import diploma.ray.Camera;
import diploma.ray.Light;
import diploma.ray.Ray;
import diploma.ray.World;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        GeometryObject floor = new Sphere(1);
        floor.setTransformation(Creator.createScalingMatrix(10, 0.01, 10));
        floor.setMaterial(new Material(new Color(1, 0.9, 0.9)));
        floor.getMaterial().setSpecular(0);

        GeometryObject leftWall = new Sphere(2);
        Matrix leftWallTransform = Creator.createTranslationMatrix(0, 0, 5)
                .multiply(Creator.yRotationMatrix(-4))
                .multiply(Creator.xRotationMatrix(2))
                .multiply(Creator.createScalingMatrix(10, 0.01, 10));
        leftWall.setTransformation(leftWallTransform);
        leftWall.setMaterial(floor.getMaterial());

        GeometryObject rightWall = new Sphere(3);
        Matrix rightWallTransform = Creator.createTranslationMatrix(0, 0, 5)
                .multiply(Creator.yRotationMatrix(4))
                .multiply(Creator.xRotationMatrix(2))
                .multiply(Creator.createScalingMatrix(10, 0.01, 10));
        rightWall.setTransformation(rightWallTransform);
        rightWall.setMaterial(floor.getMaterial());

        GeometryObject middle = new Sphere(4);
        middle.setTransformation(Creator.createTranslationMatrix(-0.5, 1, 0.5));
        middle.getMaterial().setColor(new Color(0.1, 1, 0.5));
        middle.getMaterial().setDiffuse(0.7);
        middle.getMaterial().setSpecular(0.3);

        GeometryObject right = new Sphere(5);
        right.setTransformation(Creator.createTranslationMatrix(1.5, 0.5, -0.5).multiply(Creator.createScalingMatrix(0.5, 0.5, 0.5)));
        right.getMaterial().setColor(new Color(0.5, 1, 0.1));
        right.getMaterial().setDiffuse(0.7);
        right.getMaterial().setSpecular(0.3);

        GeometryObject left = new Sphere(6);
        left.setTransformation(Creator.createTranslationMatrix(-1.5, 0.33, -0.75).multiply(Creator.createScalingMatrix(0.33, 0.33, 0.33)));
        left.getMaterial().setColor(new Color(1, 0.8, 0.1));
        left.getMaterial().setDiffuse(0.7);
        left.getMaterial().setSpecular(0.3);
        Light pointLight = new Light(new Color(1, 1, 1), new Point(-10, 10, -10));
        World world = new World(pointLight);

        world.addGeometryObject(floor);
        world.addGeometryObject(leftWall);
        world.addGeometryObject(rightWall);
        world.addGeometryObject(middle);
        world.addGeometryObject(right);
        world.addGeometryObject(left);

        Camera camera = new Camera(1000, 500, 3);
        Tuple from = new Point(0, 1.5, -5);
        Matrix transform = from.viewTransform(new Point(0.2, 1, -2), new Vector(0, 1, 0));
        camera.setTransform(transform);
        Canvas image = camera.render(world);

        File fnew = new File("test.ppm");
        FileWriter f2;
        try {
            f2 = new FileWriter(fnew, false);
            f2.write(image.toPPM());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
