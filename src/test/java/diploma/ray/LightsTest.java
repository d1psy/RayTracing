package diploma.ray;

import diploma.geometry.Color;
import diploma.geometry.Point;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.material.Material;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightsTest {

    private Tuple intensity;
    private Tuple position;
    private Light light;
    private Light pointLight;
    private Material material;
    private Tuple eyeVector;
    private Tuple normalVector;
    private Tuple result;

    @Given("Intensity {double} {double} {double}")
    public void createIntensity(double red, double green, double blue) {
        intensity = new Color(red, green, blue);
    }

    @Given("Light position {double} {double} {double}")
    public void createPosition(double x, double y, double z) {
        position = new Point(x, y, z);
    }

    @Given("Material {double} {double} {double}")
    public void createMaterial(double red, double green, double blue) {
        material = new Material(new Color(red, green, blue));
    }

    @Given("Eye vector {double} {double} {double}")
    public void createEyeVector(double x, double y, double z) {
        eyeVector = new Vector(x, y, z);
    }

    @Given("Normal vector {double} {double} {double}")
    public void createNormalVector(double x, double y, double z) {
        normalVector = new Vector(x, y, z);
    }

    @Given("Point light point {double} {double} {double} color {double} {double} {double}")
    public void createNormalVector(double x, double y, double z, double red, double green, double blue) {
        Tuple point = new Point(x, y, z);
        Tuple color = new Color(red, green, blue);
        pointLight = new Light(color, point);
    }
    @When("Create light")
    public void createLight() {
        light = new Light(intensity, position);
    }

    @When("Create lighting result")
    public void createLightingResult() {
        result = material.lighting(pointLight, position, eyeVector, normalVector);
    }

    @Then("Intensity is {double} {double} {double}")
    public void compareIntensity(double red, double green, double blue) {
        Tuple expected = new Color(red, green, blue);
        assertEquals(expected, light.getIntensity());
    }

    @Then("Light position is {double} {double} {double}")
    public void comparePosition(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, light.getPosition());
    }

    @Then("Material color {double} {double} {double}")
    public void materialColorEquals(double red, double green, double blue) {
        Tuple expected = new Color(red, green, blue);
        assertEquals(expected, material.getColor());
    }

    @Then("Material ambient is {double}")
    public void materialAmbientEquals(double expected) {
        assertEquals(expected, material.getAmbient());
    }

    @Then("Material diffuse is {double}")
    public void materialDiffuseEquals(double expected) {
        assertEquals(expected, material.getDiffuse());
    }

    @Then("Material specular is {double}")
    public void materialSpecularEquals(double expected) {
        assertEquals(expected, material.getSpecular());
    }

    @Then("Material shininess is {double}")
    public void materialShininessEquals(double expected) {
        assertEquals(expected, material.getShininess());
    }

    @Then("Result is color {double} {double} {double}")
    public void resultIsColor(double red, double green, double blue) {
        Tuple expected = new Color(red, green, blue);
        assertEquals(expected, result);
    }
}
