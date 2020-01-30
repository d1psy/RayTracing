package diploma.ray;

import diploma.geometry.Color;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Vector;
import diploma.material.Material;
import diploma.math.Computation;
import diploma.math.IntersectionCollection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefractionsTest {

    private GeometryObject firstSphere;
    private GeometryObject secondSphere;
    private GeometryObject thirdSphere;
    private Ray ray;
    private List<Intersection> intersections;
    private Computation computation;

    @Given("Glass sphere with transform scaling {double} {double} {double} and material refractive {double}")
    public void glass_sphere_with_transform_scaling_and_material_refractive(double x, double y, double z, double refractive) {
        firstSphere = new Sphere(1);
        Material material = new Material(new Color(1, 1,1));
        try {
            material.setRefractiveIndex(refractive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        firstSphere.setMaterial(material);
    }

    @Given("Second glass sphere with transform translation {double} {double} {double} and material refractive {double}")
    public void second_glass_sphere_with_transform_translation_and_material_refractive(double x, double y, double z, double refractive) {
        secondSphere = new Sphere(2);
        Material material = new Material(new Color(1, 1,1));
        try {
            material.setRefractiveIndex(refractive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        secondSphere.setMaterial(material);
    }
    
    @Given("Third glass sphere with transform translation {double} {double} {double} and material refractive {double}")
    public void third_glass_sphere_with_transform_translation_and_material_refractive(double x, double y, double z, double refractive) {
        thirdSphere = new Sphere(3);
        Material material = new Material(new Color(1, 1,1));
        try {
            material.setRefractiveIndex(refractive);
        } catch (Exception e) {
            e.printStackTrace();
        }
        thirdSphere.setMaterial(material);
    }
    
    @Given("Glass ray point {double} {double} {double} vector {double} {double} {double}")
    public void glass_ray_point_vector(double px, double py, double pz, double vx, double vy, double vz) {
        ray = new Ray(new Point(px, py, pz), new Vector(vx, vy, vz));
    }
    
    @Given("Intersections {double} first {double} second {double} third {double} second {double} third {double} first")
    public void intersections_first_second_third_second_third_first(double t1, double t2, double t3, double t4, double t5, double t6) {
        intersections = new ArrayList<>();
        intersections.add(new Intersection(t1, firstSphere));
        intersections.add(new Intersection(t2, secondSphere));
        intersections.add(new Intersection(t3, thirdSphere));
        intersections.add(new Intersection(t4, secondSphere));
        intersections.add(new Intersection(t5, thirdSphere));
        intersections.add(new Intersection(t6, firstSphere));
    }
    
    @When("Prepare computations for glass {int}")
    public void prepare_computations_for_glass(int index) {
        computation = new Computation(intersections.get(index), ray, intersections);
    }
    
    @Then("Computations first {double}")
    public void computationsFirst(double expected) {
        assertEquals(expected, computation.getN1());
    }
    
    @Then("Computations second {double}")
    public void computationsSecond(double expected) {
        assertEquals(expected, computation.getN2());
    }
    
}
