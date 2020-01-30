package diploma.geometry;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaneTest {

    private GeometryObject plane;
    private Tuple vector;

    @Given("Plane")
    public void createPlace() {
        plane = new Plane(1);
    }

    @When("Plane normal at {double} {double} {double}")
    public void createVector(double x, double y, double z) {
        vector = plane.normalAt(new Point(x, y, z));
    }

    @Then("Plane normal vector {double} {double} {double}")
    public void compareVector(double x, double y, double z) {
        assertEquals(new Vector(x, y ,z), vector);
    }
}
