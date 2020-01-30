package diploma.geometry;

import diploma.math.Computation;
import diploma.math.IntersectionCollection;
import diploma.ray.Ray;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntersectionTest {

    private GeometryObject shape;
    private Intersection intersection;
    private Intersection secondIntersection;
    private Intersection thirdIntersection;
    private Intersection forthIntersection;
    private List<Intersection> intersections;
    private Intersection hit;
    private Ray ray;
    private Computation computation;

    @Given("Create intersection sphere {int}")
    public void createSphere(int id) {
        shape = new Sphere(id);
    }

    @Given("Create shape plane")
    public void createPlane() {
        shape = new Plane(1);
    }

    @Given("Create ray for plane point {double} {double} {double} vector {double} {double} {double}")
    public void createRay(double px, double py, double pz, double vx, double vy, double vz) {
        ray = new Ray(new Point(px, py, pz), new Vector(vx, vy, vz));
    }

    @When("Intersection {double} shape")
    public void createShapeIntersection(double time) {
        intersection = new Intersection(time, shape);
    }

    @When("Second intersection {double} sphere")
    public void createSecondSphereIntersection(double time) {
        secondIntersection = new Intersection(time, shape);
    }

    @When("Third intersection {double} sphere")
    public void createThirdSphereIntersection(double time) {
        thirdIntersection = new Intersection(time, shape);
    }

    @When("Forth intersection {double} sphere")
    public void createForthSphereIntersection(double time) {
        forthIntersection = new Intersection(time, shape);
    }

    @When("List from two intersections")
    public void createIntersectionList() {
        intersections = new ArrayList<>();
        intersections.add(intersection);
        intersections.add(secondIntersection);
    }

    @When("List from four intersections")
    public void createIntersectionListFromFour() {
        intersections = new ArrayList<>();
        intersections.add(intersection);
        intersections.add(secondIntersection);
        intersections.add(thirdIntersection);
        intersections.add(forthIntersection);
    }

    @When("Hit intersections list")
    public void createHitOfIntersections() {
        hit = IntersectionCollection.hit(intersections);
    }

    @When("Prepare computations for plane")
    public void prepareComputations() {
        computation = new Computation(intersection, ray, intersections);
    }

    @Then("Intersection time {double}")
    public void intersectionTimeEquals(double expected) {
        assertEquals(expected, intersection.getTime());
    }

    @Then("Object is sphere")
    public void objectIsSphere() {
        assertEquals(shape, intersection.getGeometryObject());
    }

    @Then("List size is {int}")
    public void compareListSize(int expected) {
        assertEquals(expected, intersections.size());
    }

    @Then("List time {int} is {double}")
    public void listTimeIs(int pointer, double expected) {
        assertEquals(expected, intersections.get(pointer).getTime());
    }

    @Then("Expected intersection {int}")
    public void expectedIntersection(int pointer) {
        assertEquals(intersections.get(pointer), hit);
    }

    @Then("Expected intersection is nothing")
    public void expectedIntersectionIsNothing() {
        assertNull(hit);
    }

    @Then("Reflect is vector {double} {double} {double}")
    public void reflectIsVector(double x, double y, double z) {
        assertEquals(new Vector(x, y, z), computation.getReflectVector());
    }
}
