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
import diploma.math.MatrixMath;
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

    @Given("Translation {float} {float} {float}")
    public void createTranslation(float x, float y, float z) {
        firstTransform = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Third translation {float} {float} {float}")
    public void createThirdTranslation(float x, float y, float z) {
        thirdTransform = Creator.createTranslationMatrix(x, y, z);
    }

    @Given("Scaling {float} {float} {float}")
    public void createScaling(float x, float y, float z) {
        firstTransform = Creator.createScalingMatrix(x, y, z);
    }

    @Given("Second scaling {float} {float} {float}")
    public void createSecondScaling(float x, float y, float z) {
        secondTransform = Creator.createScalingMatrix(x, y, z);
    }

    @Given("Rotation by x pi by {float}")
    public void createRotationByX(float r) {
        firstTransform = Creator.xRotationMatrix(r);
    }

    @Given("Rotation by y pi by {float}")
    public void createRotationByY(float r) {
        firstTransform = Creator.yRotationMatrix(r);
    }

    @Given("Rotation by z pi by {float}")
    public void createRotationByZ(float r) {
        firstTransform = Creator.zRotationMatrix(r);
    }

    @Given("Shearing {float} {float} {float} {float} {float} {float}")
    public void createShearing(float x1, float x2, float y1, float y2, float z1, float z2) {
        firstTransform = Creator.createShearingMatrix(x1, x2, y1, y2, z1, z2);
    }

    @Given("Point {float} {float} {float}")
    public void createPoint(float x, float y, float z) {
        firstPoint = new Point(x, y, z);
    }

    @Given("Vector {float} {float} {float}")
    public void createVector(float x, float y, float z) {
        vector = new Vector(x, y, z);
    }

    @Given("Inverse transform")
    public void inverseTranslation() {
        firstTransform = MatrixMath.inverse(firstTransform);
    }

    @Given("Create sphere for transform {int}")
    public void createSphere(int id) {
        sphere = new Sphere(id);
    }

    @Given("Create ray {float} {float} {float} {float} {float} {float}")
    public void createRay(float oX, float oY, float oZ, float dX, float dY, float dZ) {
        Tuple origin = new Point(oX, oY, oZ);
        Tuple direction = new Vector(dX, dY, dZ);
        ray = new Ray(origin, direction);
    }

    @When("Second point multiply")
    public void secondPointMultiply() {
        secondPoint = MatrixMath.multiplyByTuple(firstTransform, firstPoint);
    }

    @When("Third point multiply")
    public void thirdPointMultiply() {
        thirdPoint = MatrixMath.multiplyByTuple(secondTransform, secondPoint);
    }

    @When("Forth point multiply")
    public void forthPointMultiply() {
        forthPoint = MatrixMath.multiplyByTuple(thirdTransform, thirdPoint);
    }

    @When("Multiply three matrices")
    public void tripleMultiplication() {
        resultOfMult = MatrixMath.multiply(MatrixMath.multiply(thirdTransform, secondTransform), firstTransform);
    }

    @When("Set transformation for sphere")
    public void setTransformationForSphere() {
        sphere.setTransformation(firstTransform);
    }

    @When("Intersection ray sphere")
    public void setIntersections() {
        intersections = IntersectionCollection.getIntersection(ray, sphere);
    }

    @Then("Transforming a point gives {float} {float} {float}")
    public void multiplyByPoint(float x, float y, float z) {
        Point expected = new Point(x, y, z);
        assertEquals(expected, MatrixMath.multiplyByTuple(firstTransform, firstPoint));
    }

    @Then("Transforming a vector gives vector")
    public void multiplyByVector() {
        assertEquals(vector, MatrixMath.multiplyByTuple(firstTransform, vector));
    }

    @Then("Transforming a vector gives {float} {float} {float}")
    public void multiplyScalingByVector(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, MatrixMath.multiplyByTuple(firstTransform, vector));
    }

    @Then("Second point {float} {float} {float}")
    public void secondPointEquals(float x, float y, float z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, secondPoint);
    }

    @Then("Third point {float} {float} {float}")
    public void thirdPointEquals(float x, float y, float z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, thirdPoint);
    }

    @Then("Forth point {float} {float} {float}")
    public void forthPointEquals(float x, float y, float z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, forthPoint);
    }

    @Then("Three matrices multiplication on point {float} {float} {float}")
    public void threeOnPoint(float x, float y, float z) {
        Tuple expected = new Point(x, y, z);
        assertEquals(expected, MatrixMath.multiplyByTuple(resultOfMult, firstPoint));
    }

    @Then("Transformed sphere equals identityMatrix")
    public void transformedSphereIsIdentity() {
        assertEquals(Creator.createIdentityMatrix(), sphere.getTransformation());
    }

    @Then("Transformed sphere equals translation {float} {float} {float}")
    public void transformedSphereIsTranslation(float x, float y, float z) {
        assertEquals(Creator.createTranslationMatrix(x, y, z), sphere.getTransformation());
    }

    @Then("Size of intersections is {int}")
    public void sizeOfIntersections(int expected) {
        assertEquals(expected, intersections.size());
    }

    @Then("Intersections time {int} is {float}")
    public void intersectionsTime(int pointer, float expected) {
        assertEquals(expected, intersections.get(pointer).getTime());
    }
}
