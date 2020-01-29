package diploma.geometry;

import diploma.math.IntersectionCollection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntersectionTest {

    private GeometryObject sphere;
    private Intersection intersection;
    private Intersection secondIntersection;
    private Intersection thirdIntersection;
    private Intersection forthIntersection;
    private List<Intersection> intersections;
    private Intersection hit;

    @Given("Create intersection sphere {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @When("Intersection {double} sphere")
    public void createSphereIntersection(double time) {
        intersection = new Intersection(time, sphere);
    }

    @When("Second intersection {double} sphere")
    public void createSecondSphereIntersection(double time) {
        secondIntersection = new Intersection(time, sphere);
    }

    @When("Third intersection {double} sphere")
    public void createThirdSphereIntersection(double time) {
        thirdIntersection = new Intersection(time, sphere);
    }

    @When("Forth intersection {double} sphere")
    public void createForthSphereIntersection(double time) {
        forthIntersection = new Intersection(time, sphere);
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

    @Then("Intersection time {double}")
    public void intersectionTimeEquals(double expected) {
        assertEquals(expected, intersection.getTime());
    }

    @Then("Object is sphere")
    public void objectIsSphere() {
        assertEquals(sphere, intersection.getGeometryObject());
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
}
