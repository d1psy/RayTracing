package diploma.geometry;

import diploma.Creator;
import diploma.math.GeometryObjectMath;
import diploma.math.MatrixMath;
import diploma.math.RayMath;
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

    @Given("Set transform translation {float} {float} {float}")
    public void setTranslation(float x, float y, float z) {
        transform = Creator.createTranslationMatrix(x, y, z);
        sphere.setTransformation(transform);
    }

    @Given("Multiply scaling {float} {float} {float} by rotation {float}")
    public void multiplyScalingByRotation(float x, float y, float z, float r) {
        Matrix rotation = Creator.zRotationMatrix(r);
        Matrix scaling = Creator.createScalingMatrix(x, y, z);
        transform = MatrixMath.multiply(scaling, rotation);
    }

    @Given("Set transform of existing")
    public void setTransformOfExisting() {
        sphere.setTransformation(transform);
    }

    @When("Normal sphere point {float} {float} {float}")
    public void createNormalSphere(float x, float y, float z) {
        Tuple point = new Point(x, y, z);
        normal = GeometryObjectMath.normalAt(sphere, point);
    }

    @Then("Normal gives vector {float} {float} {float}")
    public void normalGivesVector(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, normal);
    }

    @Then("Normalize normal gives normal")
    public void normalizeNormal() {
        assertEquals(normal, RayMath.normalize(normal));
    }
}
