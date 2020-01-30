package diploma.movement;

import diploma.Creator;
import diploma.geometry.GeometryObject;
import diploma.geometry.Intersection;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Sphere;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import diploma.math.IntersectionCollection;
import diploma.ray.Ray;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TranslationTest {

    private Matrix firstTransform;
    private Matrix secondTransform;
    private Matrix thirdTransform;
    private Matrix resultOfMult;
    private Tuple firstPoint;
    private Tuple secondPoint;
    private Tuple thirdPoint;
    private Tuple forthPoint;
    private Vector vector;
    private GeometryObject sphere;
    private List<Intersection> intersections;
    private Ray ray;

    @Given("Translation {double} {double} {double}")
    public void createTranslation(double x, double y, double z) {
        firstTransform = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Third translation {double} {double} {double}")
    public void createThirdTranslation(double x, double y, double z) {
        thirdTransform = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Scaling {double} {double} {double}")
    public void createScaling(double x, double y, double z) {
        firstTransform = Creator.createScalingMatrix(x, y, z);
    }

    @Given("Second scaling {double} {double} {double}")
    public void createSecondScaling(double x, double y, double z) {
        secondTransform = Creator.createScalingMatrix(x, y, z);
    }

    @Given("Rotation by x pi by {double}")
    public void createRotationByX(double r) {
        firstTransform = Creator.xRotationMatrix(r);
    }

    @Given("Rotation by y pi by {double}")
    public void createRotationByY(double r) {
        firstTransform = Creator.yRotationMatrix(r);
    }

    @Given("Rotation by z pi by {double}")
    public void createRotationByZ(double r) {
        firstTransform = Creator.zRotationMatrix(r);
    }

    @Given("Shearing {double} {double} {double} {double} {double} {double}")
    public void createShearing(double x1, double x2, double y1, double y2, double z1, double z2) {
        firstTransform = Creator.createShearingMatrix(x1, x2, y1, y2, z1, z2);
    }

    @Given("Point {double} {double} {double}")
    public void createPoint(double x, double y, double z) {
        firstPoint = new Point(x, y, z);
    }

    @Given("Vector {double} {double} {double}")
    public void createVector(double x, double y, double z) {
        vector = new Vector(x, y, z);
    }

    @Given("Inverse transform")
    public void inverseTranslation() {
        firstTransform = firstTransform.inverse();
    }

    @Given("Create sphere for transform {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @Given("Create ray {double} {double} {double} {double} {double} {double}")
    public void createRay(double oX, double oY, double oZ, double dX, double dY, double dZ) {
        Tuple origin = new Point(oX, oY, oZ);
        Tuple direction = new Vector(dX, dY, dZ);
        ray = new Ray(origin, direction);
    }

    @When("Second point multiply")
    public void secondPointMultiply() {
        secondPoint = firstTransform.multiplyByTuple(firstPoint);
    }

    @When("Third point multiply")
    public void thirdPointMultiply() {
        thirdPoint = secondTransform.multiplyByTuple(secondPoint);
    }

    @When("Forth point multiply")
    public void forthPointMultiply() {
        forthPoint = thirdTransform.multiplyByTuple(thirdPoint);
    }

    @When("Multiply three matrices")
    public void tripleMultiplication() {
        resultOfMult = thirdTransform.multiply(secondTransform).multiply(firstTransform);
    }

    @When("Set transformation for sphere")
    public void setTransformationForSphere() {
        sphere.setTransformation(firstTransform);
    }

    @When("Intersection ray sphere")
    public void setIntersections() {
        intersections = sphere.getIntersection(ray);
    }

    @Then("Transforming a point gives {double} {double} {double}")
    public void multiplyByPoint(double x, double y, double z) {
        Point expected = new Point(x, y, z);
        assertEquals(expected, firstTransform.multiplyByTuple(firstPoint));
    }

    @Then("Transforming a vector gives vector")
    public void multiplyByVector() {
        assertEquals(vector, firstTransform.multiplyByTuple(vector));
    }

    @Then("Transforming a vector gives {double} {double} {double}")
    public void multiplyScalingByVector(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, firstTransform.multiplyByTuple(vector));
    }

    @Then("Second point {double} {double} {double}")
    public void secondPointEquals(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, secondPoint);
    }

    @Then("Third point {double} {double} {double}")
    public void thirdPointEquals(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, thirdPoint);
    }

    @Then("Forth point {double} {double} {double}")
    public void forthPointEquals(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, forthPoint);
    }

    @Then("Three matrices multiplication on point {double} {double} {double}")
    public void threeOnPoint(double x, double y, double z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, resultOfMult.multiplyByTuple(firstPoint));
    }

    @Then("Transformed sphere equals identityMatrix")
    public void transformedSphereIsIdentity() {
        assertEquals(Creator.createIdentityMatrix(), sphere.getTransformation());
    }

    @Then("Transformed sphere equals translation {double} {double} {double}")
    public void transformedSphereIsTranslation(double x, double y, double z) {
        assertEquals(Creator.createTranslationMatrix(x, y, z), sphere.getTransformation());
    }

    @Then("Size of intersections is {int}")
    public void sizeOfIntersections(int expected) {
        assertEquals(expected, intersections.size());
    }

    @Then("Intersections time {int} is {double}")
    public void intersectionsTime(int pointer, double expected) {
        assertEquals(expected, intersections.get(pointer).getTime());
    }
}
