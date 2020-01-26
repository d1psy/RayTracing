package diploma.geometry;

import diploma.math.IntersectionCollection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IntersectionTest {

    private GeometryObject sphere;
    private Intersection intersection;
    private Intersection secondIntersection;
    private Intersection thirdIntersection;
    private Intersection forthIntersection;
    private IntersectionCollection intersections;
    private Intersection hit;

    @Given("Create intersection sphere {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @When("Intersection {float} sphere")
    public void createSphereIntersection(float time) {
        intersection = new Intersection(time, sphere);
    }

    @When("Second intersection {float} sphere")
    public void createSecondSphereIntersection(float time) {
        secondIntersection = new Intersection(time, sphere);
    }

    @When("Third intersection {float} sphere")
    public void createThirdSphereIntersection(float time) {
        thirdIntersection = new Intersection(time, sphere);
    }

    @When("Forth intersection {float} sphere")
    public void createForthSphereIntersection(float time) {
        forthIntersection = new Intersection(time, sphere);
    }

    @When("List from two intersections")
    public void createIntersectionList() {
        intersections = new IntersectionCollection(intersection, secondIntersection);
    }

    @When("List from four intersections")
    public void createIntersectionListFromFour() {
        intersections = new IntersectionCollection(intersection, secondIntersection, thirdIntersection, forthIntersection);
    }

    @When("Hit intersections list")
    public void createHitOfIntersections() {
        hit = intersections.hit();
    }

    @Then("Intersection time {float}")
    public void intersectionTimeEquals(float expected) {
        assertEquals(expected, intersection.getTime());
    }

    @Then("Object is sphere")
    public void objectIsSphere() {
        assertEquals(sphere, intersection.getGeometryObject());
    }

    @Then("List size is {int}")
    public void compareListSize(int expected) {
        assertEquals(expected, intersections.getIntersections().size());
    }

    @Then("List time {int} is {float}")
    public void listTimeIs(int pointer, float expected) {
        assertEquals(expected, intersections.getIntersections().get(pointer).getTime());
    }

    @Then("Expected intersection {int}")
    public void expectedIntersection(int pointer) {
        assertEquals(intersections.getIntersections().get(pointer), hit);
    }

    @Then("Expected intersection is nothing")
    public void expectedIntersectionIsNothing() {
        assertNull(hit);
    }
}
