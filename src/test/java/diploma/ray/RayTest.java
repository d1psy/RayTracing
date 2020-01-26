package diploma.ray;

import diploma.Creator;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.math.IntersectionCollection;
import diploma.math.RayMath;
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

    @Given("Origin point {float} {float} {float}")
    public void createOriginPoint(float x, float y, float z) {
        origin = new Point(x, y, z);
    }

    @Given("Direction vector {float} {float} {float}")
    public void createDirectionVector(float x, float y, float z) {
        direction = new Vector(x, y, z);
    }

    @Given("Create ray origin {float} {float} {float} direction {float} {float} {float}")
    public void createRay(float oX, float oY, float oZ, float dX, float dY, float dZ) {
        origin = new Point(oX, oY, oZ);
        direction = new Vector(dX, dY, dZ);
        ray = new Ray(origin, direction);
    }

    @Given("Create sphere {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @Given("Translation matrix {float} {float} {float}")
    public void createTranslationMatrix(float x, float y, float z) {
        transformation = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Scaling matrix {float} {float} {float}")
    public void createScalingMatrix(float x, float y, float z) {
        transformation = Creator.createScalingMatrix(x, y, z);
    }

    @When("Create ray")
    public void createRayWithPrecreated() {
        ray = new Ray(origin, direction);
    }

    @When("Intersects")
    public void createIntersections() {
        intersections = IntersectionCollection.getIntersection(ray, sphere);
    }

    @When("Transform ray")
    public void transformRay() {
        transformedRay = RayMath.transform(ray, transformation);
    }

    @Then("Ray origin is origin")
    public void compareOrigins() {
        assertEquals(origin, ray.getOrigin());
    }

    @Then("Ray direction is direction")
    public void compareDirections() {
        assertEquals(direction, ray.getDirection());
    }

    @Then("Position {float} is point {float} {float} {float}")
    public void positionIsPoint(float time, float x, float y, float z) {
        Point expected = new Point(x, y, z);
        assertEquals(expected, RayMath.position(ray, time));
    }

    @Then("Count is {int}")
    public void countEquals(int count) {
        assertEquals(count, intersections.size());
    }

    @Then("Count {int} is {float}")
    public void countAtPointerEquals(int pointer, float position) {
        assertEquals(position, intersections.get(pointer).getTime());
    }

    @Then("Transformed ray origin {float} {float} {float}")
    public void transformedRayOrigin(float x, float y, float z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, transformedRay.getOrigin());
    }

    @Then("Transformed ray direction {float} {float} {float}")
    public void transformedRayDirection(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, transformedRay.getDirection());
    }
}
