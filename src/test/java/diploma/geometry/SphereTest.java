package diploma.geometry;

import diploma.Creator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SphereTest {

    private Sphere sphere;
    private Tuple normal;
    private Matrix transform;

    @Given("Create new sphere {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @Given("Set transform translation {double} {double} {double}")
    public void setTranslation(double x, double y, double z) {
        transform = Creator.createTranslationMatrix(x, y, z);
        sphere.setTransformation(transform);
    }

    @Given("Multiply scaling {double} {double} {double} by rotation {double}")
    public void multiplyScalingByRotation(double x, double y, double z, double r) {
        Matrix rotation = Creator.zRotationMatrix(r);
        Matrix scaling = Creator.createScalingMatrix(x, y, z);
        transform = scaling.multiply(rotation);
    }

    @Given("Set transform of existing")
    public void setTransformOfExisting() {
        sphere.setTransformation(transform);
    }

    @When("Normal sphere point {double} {double} {double}")
    public void createNormalSphere(double x, double y, double z) {
        Tuple point = new Point(x, y, z);
        normal = sphere.normalAt(point);
    }

    @Then("Normal gives vector {double} {double} {double}")
    public void normalGivesVector(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, normal);
    }

    @Then("Normalize normal gives normal")
    public void normalizeNormal() {
        assertEquals(normal, normal.normalize());
    }
}
