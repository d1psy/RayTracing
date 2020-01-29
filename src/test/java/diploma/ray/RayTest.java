package diploma.ray;

import diploma.Creator;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.math.IntersectionCollection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RayTest {

    private Point origin;
    private Vector direction;
    private Ray ray;
    private Ray transformedRay;
    private Sphere sphere;
    private List<Intersection> intersections;
    private Matrix transformation;

    @Given("Origin point {double} {double} {double}")
    public void createOriginPoint(double x, double y, double z) {
        origin = new Point(x, y, z);
    }

    @Given("Direction vector {double} {double} {double}")
    public void createDirectionVector(double x, double y, double z) {
        direction = new Vector(x, y, z);
    }

    @Given("Create ray origin {double} {double} {double} direction {double} {double} {double}")
    public void createRay(double oX, double oY, double oZ, double dX, double dY, double dZ) {
        origin = new Point(oX, oY, oZ);
        direction = new Vector(dX, dY, dZ);
        ray = new Ray(origin, direction);
    }

    @Given("Create sphere {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @Given("Translation matrix {double} {double} {double}")
    public void createTranslationMatrix(double x, double y, double z) {
        transformation = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Scaling matrix {double} {double} {double}")
    public void createScalingMatrix(double x, double y, double z) {
        transformation = Creator.createScalingMatrix(x, y, z);
    }

    @When("Create ray")
    public void createRayWithPrecreated() {
        ray = new Ray(origin, direction);
    }

    @When("Intersects")
    public void createIntersections() {
        intersections = ray.getIntersection(sphere);
    }

    @When("Transform ray")
    public void transformRay() {
        transformedRay = ray.transform(transformation);
    }

    @Then("Ray origin is origin")
    public void compareOrigins() {
        assertEquals(origin, ray.getOrigin());
    }

    @Then("Ray direction is direction")
    public void compareDirections() {
        assertEquals(direction, ray.getDirection());
    }

    @Then("Position {double} is point {double} {double} {double}")
    public void positionIsPoint(double time, double x, double y, double z) {
        Point expected = new Point(x, y, z);
        assertEquals(expected, ray.position(time));
    }

    @Then("Count is {int}")
    public void countEquals(int count) {
        assertEquals(count, intersections.size());
    }

    @Then("Count {int} is {double}")
    public void countAtPointerEquals(int pointer, double position) {
        assertEquals(position, intersections.get(pointer).getTime());
    }

    @Then("Transformed ray origin {double} {double} {double}")
    public void transformedRayOrigin(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, transformedRay.getOrigin());
    }

    @Then("Transformed ray direction {double} {double} {double}")
    public void transformedRayDirection(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, transformedRay.getDirection());
    }
}
