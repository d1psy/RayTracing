package diploma.ray;

import diploma.Creator;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Vector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CameraTest {

    private Camera camera;
    private int hsize;
    private int vsize;
    private double fov;
    private Ray ray;

    @Given("Hsize {int}")
    public void createHsize(int size) {
        hsize = size;
    }

    @Given("Vsize {int}")
    public void createVsize(int size) {
        vsize = size;
    }

    @Given("FoV {double}")
    public void createFov(double fov) {
        this.fov = fov;
    }

    @When("Create camera")
    public void createCamera() {
        camera = new Camera(hsize, vsize, fov);
    }

    @When("Create ray for pixel {double} {double}")
    public void createRayForPixel(double x, double y) {
        ray = camera.rayForPixel(x, y);
    }

    @When("Camera transform is y rotation {double} by translation {double} {double} {double}")
    public void setNewCameraTransform(double r, double x, double y, double z) {
        Matrix transform = Creator.yRotationMatrix(r).multiply(Creator.createTranslationMatrix(x, y, z));
        camera.setTransform(transform);
    }

    @Then("Hsize is {int}")
    public void compareHsize(int expected) {
        assertEquals(expected, camera.getHsize());
    }

    @Then("Vsize is {int}")
    public void compareVsize(int expected) {
        assertEquals(expected, camera.getVsize());
    }

    @Then("FoV is {double}")
    public void compareFov(double expected) {
        assertEquals(Math.PI/expected, camera.getFov());
    }

    @Then("Camera transform is identity matrix")
    public void compareHsize() {
        assertEquals(Creator.createIdentityMatrix(), camera.getTransform());
    }

    @Then("Camera pixel size is {double}")
    public void cameraPixelSize(Double expected) {
        assertEquals(expected, camera.getPixelSize());
    }

    @Then("Camera ray origin {double} {double} {double}")
    public void cameraRayOriginCompares(double x, double y, double z) {
        assertEquals(new Point(x, y, z), ray.getOrigin());
    }

    @Then("Camera ray direction {double} {double} {double}")
    public void cameraRayDirectionCompares(double x, double y, double z) {
        assertEquals(new Vector(x, y, z), ray.getDirection());
    }
}
