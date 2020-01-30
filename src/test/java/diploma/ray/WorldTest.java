package diploma.ray;

import diploma.Creator;
import diploma.geometry.Color;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.material.Material;
import diploma.math.Computation;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WorldTest {

    private World world;
    private Light light;
    private GeometryObject shape;
    private GeometryObject firstSphere;
    private GeometryObject secondSphere;
    private GeometryObject inner;
    private GeometryObject outer;
    private Ray ray;
    private Intersection intersection;
    private Computation computation;
    private List<Intersection> intersectionList;
    private Tuple shadeHit;
    private Tuple colorAt;
    private Tuple pointInWorld;

    @Given("Create world")
    public void createWorld() {
        world = new World();
    }

    @Given("Light point {double} {double} {double} and color {double} {double} {double}")
    public void createLight(double x, double y, double z, double red, double green, double blue) {
        light = new Light(new Color(red, green, blue), new Point(x, y, z));
    }

    @Given("Create sphere color {double} {double} {double} diffuse {double} specular {double}")
    public void createFirstSphere(double red, double green, double blue, double diffuse, double specular) {
        firstSphere = new Sphere(1);
        Color color = new Color(red, green, blue);
        Material material = new Material(color);
        material.setDiffuse(diffuse);
        material.setSpecular(specular);
        firstSphere.setMaterial(material);
    }

    @Given("Create second sphere scaling {double} {double} {double}")
    public void createSecondSphere(double x, double y, double z) {
        secondSphere = new Sphere(2);
        secondSphere.setTransformation(Creator.createScalingMatrix(x, y, z));
    }

    @Given("Ray in world point {double} {double} {double} vector {double} {double} {double}")
    public void createRay(double px, double py, double pz, double vx, double vy, double vz) {
        ray = new Ray(new Point(px, py, pz), new Vector(vx, vy, vz));
    }

    @Given("Create sphere shape")
    public void createShapeSphere() {
        shape = new Sphere(1);
    }

    @Given("Get intersection from shape {double}")
    public void createIntersection(double time) {
        intersection = new Intersection(time, shape);
    }

    @Given("Shape is first object")
    public void setFirstSphereToShape() {
        shape = firstSphere;
    }

    @Given("Shape is second object")
    public void setSecondSphereToShape() {
        shape = secondSphere;
    }

    @Given("Set new light to world")
    public void setNewLight() {
        world.setLight(light);
    }

    @Given("Outer is first in world")
    public void setOuter() {
        outer = world.getGeometryObjects().get(0);
    }

    @Given("Outer material ambient is {double}")
    public void setOuterMaterial(double ambient) {
        outer.getMaterial().setAmbient(ambient);
    }

    @Given("Inner is second in world")
    public void setInner() {
        inner = world.getGeometryObjects().get(1);
    }

    @Given("Inner material ambient is {double}")
    public void setInnerMaterial(double ambient) {
        inner.getMaterial().setAmbient(ambient);
    }

    @Given("Point in world {double} {double} {double}")
    public void createPointInWorld(double x, double y, double z) {
        pointInWorld = new Point(x, y, z);
    }

    @When("Add light and spheres")
    public void addLightAndSpheres() {
        world.setLight(light);
        world.addGeometryObject(firstSphere);
        world.addGeometryObject(secondSphere);
    }

    @When("Intersect world")
    public void intersectWorld() {
        intersectionList = world.getWorldIntersections(ray);
    }

    @When("Prepare computations")
    public void prepareComputations() {
        computation = new Computation(intersection, ray);
    }

    @When("Shade hit")
    public void shadeHit() {
        shadeHit = world.shadeHit(computation, 1);
    }

    @When("Color at")
    public void colorAt() {
        colorAt = world.colorAt(ray, 1);
    }

    @Then("World contains light {double} {double} {double} {double} {double} {double}")
    public void worldContainsLight(double x, double y, double z, double red, double green, double blue) {
        Light expected = new Light(new Color(red, green, blue), new Point(x, y, z));
        assertEquals(expected, world.getLight());
    }

    @Then("World contains first sphere")
    public void worldContainsFirstSphere() {
        assertEquals(firstSphere, world.getGeometryObjects().get(0));
    }

    @Then("World contains second sphere")
    public void worldContainsSecondSphere() {
        assertEquals(secondSphere, world.getGeometryObjects().get(1));
    }

    @Then("Intersections count {int}")
    public void intersectionCount(int expected) {
        assertEquals(expected, intersectionList.size());
    }

    @Then("Intersection world {int} is {double}")
    public void intersectionAtPosition(int pos, double expected) {
        assertEquals(expected, intersectionList.get(pos).getTime());
    }

    @Then("Computations time is intersections time")
    public void computationsTime() {
        assertEquals(intersection.getTime(), computation.getTime());
    }

    @Then("Computations object is intersections object")
    public void computationsObject() {
        assertEquals(intersection.getGeometryObject(), computation.getObject());
    }

    @Then("Computations point is {double} {double} {double}")
    public void computationsPoint(double x, double y, double z) {
        assertEquals(new Point(x, y, z), computation.getPoint());
    }

    @Then("Computations eye vector is {double} {double} {double}")
    public void computationsEyeVector(double x, double y, double z) {
        assertEquals(new Vector(x, y, z), computation.getEyeVector());
    }

    @Then("Computations normal vector is {double} {double} {double}")
    public void computationsNormalVector(double x, double y, double z) {
        assertEquals(new Vector(x, y, z), computation.getNormalVector());
    }

    @Then("Computation inside false")
    public void computationInsideFalse() {
        assertFalse(computation.isInside());
    }

    @Then("Computation inside true")
    public void computationInsideTrue() {
        assertTrue(computation.isInside());
    }

    @Then("Computation color {double} {double} {double}")
    public void computationColorIs(double red, double green, double blue) {
        assertEquals(new Color(red, green, blue), shadeHit);
    }

    @Then("World color is {double} {double} {double}")
    public void worldColorIs(double red, double green, double blue) {
        assertEquals(new Color(red, green, blue), colorAt);
    }

    @Then ("Color is inner")
    public void colorIsInner() {
        assertEquals(inner.getMaterial().getColor(), colorAt);
    }

    @Then("Is shadowed")
    public void isShadowed() {
        assertTrue(world.isShadowed(pointInWorld));
    }

    @Then("Is not shadowed")
    public void isNotShadowed() {
        assertFalse(world.isShadowed(pointInWorld));
    }
}
