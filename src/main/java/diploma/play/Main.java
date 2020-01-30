package diploma.play;

import diploma.Creator;
import diploma.geometry.Canvas;
import diploma.geometry.Color;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Plane;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.geometry.Wall;
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
        for (int i = 10; i > -10; i--) {
            GeometryObject floor = new Plane(1);
            floor.setTransformation(Creator.createTranslationMatrix(0, 0, 0));
            Material floorMaterial = new Material(new Color(1, 0.9, 0.9));
            floorMaterial.setReflectivity(0);
            floor.setMaterial(floorMaterial);
            floor.getMaterial().setSpecular(0);

            GeometryObject leftWall = new Wall(2);
            Matrix leftWallTransform = Creator.createTranslationMatrix(-5, 0, 0);//Creator.createTranslationMatrix(-4, 0, 0).multiply(Creator.zRotationMatrix(3));
            leftWall.setTransformation(leftWallTransform);
            leftWall.setMaterial(new Material(new Color(1, 0.9, 0.9)));

            GeometryObject rightWall = new Wall(3);
            Matrix rightWallTransform = Creator.createTranslationMatrix(5, 0, 0);
            rightWall.setTransformation(rightWallTransform);
            rightWall.setMaterial(new Material(new Color(1, 0.9, 0.9)));

            GeometryObject middle = new Sphere(4);
            middle.setTransformation(Creator.createTranslationMatrix(-0.5, 1, 0.5));
            middle.getMaterial().setColor(new Color(1, 1, 1));
            middle.getMaterial().setDiffuse(0.7);
            middle.getMaterial().setSpecular(0.3);
            middle.getMaterial().setReflectivity(0);
            middle.getMaterial().setTransparency(1);
            try {
                middle.getMaterial().setRefractiveIndex(1.3333);
            } catch (Exception e) {
                e.printStackTrace();
            }

            GeometryObject right = new Sphere(5);
            right.setTransformation(Creator.createTranslationMatrix(i, 1, 5).multiply(Creator.createScalingMatrix(0.5, 0.5, 0.5)));
            right.getMaterial().setColor(new Color(0.5, 1, 0.1));
            right.getMaterial().setDiffuse(0.7);
            right.getMaterial().setSpecular(0.3);
            right.getMaterial().setReflectivity(0);

            GeometryObject left = new Sphere(6);
            left.setTransformation(Creator.createTranslationMatrix(-1.5, 0.33, -0.75).multiply(Creator.createScalingMatrix(0.33, 0.33, 0.33)));
            left.getMaterial().setColor(new Color(1, 0.8, 0.1));
            left.getMaterial().setDiffuse(0.7);
            left.getMaterial().setSpecular(0.3);
            left.getMaterial().setReflectivity(1);

            Light pointLight = new Light(new Color(1, 1, 1), new Point(-20, 10, -20));
            World world = new World(pointLight);

            world.addGeometryObject(floor);
//        world.addGeometryObject(leftWall);
//        world.addGeometryObject(rightWall);
            world.addGeometryObject(middle);
            world.addGeometryObject(right);
//        world.addGeometryObject(left);

            Camera camera = new Camera(1000, 500, 2);
            Tuple from = new Point(0, 1.5, -5);
            Matrix transform = from.viewTransform(new Point(0.2, 1, -2), new Vector(0, 1, 0));
            camera.setTransform(transform);
            long start = System.currentTimeMillis();
            Canvas image = camera.render(world);
            long end = System.currentTimeMillis() - start;
            System.out.println("Time to render - " + end / 1000F);

            File fnew = new File("test" + i + ".ppm");
            FileWriter f2;
            try {
                f2 = new FileWriter(fnew, false);
                f2.write(image.toPPM());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
