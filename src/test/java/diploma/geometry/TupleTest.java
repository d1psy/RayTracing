package diploma.geometry;

import diploma.math.RayMath;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {

    private Tuple tuple;
    private Tuple secondTuple;
    private Tuple reflectedVector;

    @Given("Tuple x={float}, y={float}, z={float}, w={float}")
    public void createTuple(float x, float y, float z, float w) {
        tuple = new Tuple(x, y, z, w);
    }

    @Given("Point x={float}, y={float}, z={float}")
    public void createPoint(float x, float y, float z) {
        tuple = new Point(x, y, z);
    }

    @Given("Vector x={float}, y={float}, z={float}")
    public void createVector(float x, float y, float z) {
        tuple = new Vector(x, y, z);
    }

    @Given("Color red={float} green={float} blue={float}")
    public void createColor(float red, float green, float blue) {
        tuple = new Color(red, green, blue);
    }

    @Given("Second tuple x={float}, y={float}, z={float}, w={float}")
    public void createSecondTuple(float x, float y, float z, float w) {
        secondTuple = new Tuple(x, y, z, w);
    }

    @Given("Second point x={float}, y={float}, z={float}")
    public void createSecondPoint(float x, float y, float z) {
        secondTuple = new Point(x, y, z);
    }

    @Given("Second vector x={float}, y={float}, z={float}")
    public void createSecondTuple(float x, float y, float z) {
        secondTuple = new Vector(x, y, z);
    }

    @Given("Second color red={float} green={float} blue={float}")
    public void createSecondColor(float red, float green, float blue) {
        secondTuple = new Color(red, green, blue);
    }

    @Given("{word} axis={float}")
    public void createTwoTuples(String type, float axis) {
        if (type.equalsIgnoreCase("point")) {
            tuple = new Tuple(axis, axis, axis, 1.0f);
            secondTuple = new Point(axis, axis, axis);
        } else {
            tuple = new Tuple(axis, axis, axis, 0.0f);
            secondTuple = new Vector(axis, axis, axis);
        }
    }

    @When("Normalize")
    public void normalizeVector() {
        tuple = RayMath.normalize(tuple);
    }

    @When("Reflect vectors")
    public void reflectVectors() {
        reflectedVector = RayMath.reflect(tuple, secondTuple);
    }

    @Then("get x = {float}")
    public void xIsSet(float x) {
        assertEquals(tuple.getX(), x);
    }

    @Then("get y = {float}")
    public void yIsSet(float y) {
        assertEquals(tuple.getY(), y);
    }

    @Then("get z = {float}")
    public void zIsSet(float z) {
        assertEquals(tuple.getZ(), z);
    }

    @Then("get w = {float}")
    public void wIsSet(float w) {
        assertEquals(tuple.getW(), w);
    }

    @Then("is a {word} {word}")
    public void pointOrVector(String type, String val) {
        boolean bool = Boolean.parseBoolean(val);
        float value;

        if((type.equals("vector") && bool) || (type.equals("point") && !bool)) {
            value = 0.0f;
        } else {
            value = 1.0f;
        }
        assertEquals(tuple.getW(), value);
    }

    @Then("are equal")
    public void sameTuples(){
        assertEquals(tuple, secondTuple);
    }

    @Then("combine tuples equals {float}, {float}, {float}, {float}")
    public void combineAndCompareTuples(float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = RayMath.add(tuple, secondTuple);
        assertEquals(expected, actual);
    }

    @Then("subtract equals {word} {float}, {float}, {float}")
    public void subtractAndCompareTuples(String type, float x, float y, float z) {
        Tuple expected;
        Tuple actual = RayMath.subtract(tuple, secondTuple);
        if (type.equalsIgnoreCase("vector")) {
            expected = new Vector(x, y, z);
        } else {
            expected = new Point(x, y, z);
        }
        assertEquals(expected, actual);
    }

    @Then("Negate tuple x={float}, y={float}, z={float}, w={float}")
    public void negateTuple(float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        tuple.negate();
        assertEquals(expected, tuple);
    }

    @Then("Multiply tuple by {float} equals {float}, {float}, {float}, {float}")
    public void multiplyTuple(float mult, float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = RayMath.multiply(tuple, mult);
        assertEquals(expected, actual);
    }

    @Then("Divide tuple by {float} equals {float}, {float}, {float}, {float}")
    public void divideTuple(float mult, float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = RayMath.divide(tuple, mult);
        assertEquals(expected, actual);
    }

    @Then("Magnitude equals {float}")
    public void compareMagnitude(float mag) {
        float actual = RayMath.magnitude(tuple);
        assertEquals(mag, actual);
    }

    @Then("Normalize equals {float}, {float}, {float}")
    public void compareNormalized(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = RayMath.normalize(tuple);
        assertEquals(expected, actual);
    }

    @Then("Dot equals {float}")
    public void dotEquals(float dot) {
        float actual = RayMath.dotProduct(tuple, secondTuple);
        assertEquals(dot, actual);
    }

    @Then("Cross vectors {float}, {float}, {float}")
    public void crossVectors(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = RayMath.cross(tuple, secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Reverse cross vectors {float}, {float}, {float}")
    public void reverseCrossVectors(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = RayMath.cross(secondTuple, tuple);
        assertEquals(expected, actual);
    }

    @Then("Red is {float}")
    public void redEquals(float expected) {
        assertEquals(expected, tuple.getX());
    }

    @Then("Green is {float}")
    public void greenEquals(float expected) {
        assertEquals(expected, tuple.getY());
    }

    @Then("Blue is {float}")
    public void blueEquals(float expected) {
        assertEquals(expected, tuple.getZ());
    }

    @Then("Adding colors equals {float} {float} {float}")
    public void addingColors(float red, float green, float blue) {
        Tuple expected = new Color(red, green, blue);
        Tuple actual = RayMath.add(tuple, secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Subtracting colors equals {float} {float} {float}")
    public void subtractingColors(float red, float green, float blue) {
        Tuple expected = new Color(red, green, blue);
        Tuple actual = RayMath.subtract(tuple, secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Multiply tuples equals {float}, {float}, {float}, {float}")
    public void multTuples(float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = RayMath.multiplyTuples(tuple, secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Reflected vector {float} {float} {float}")
    public void reflected_vector(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, reflectedVector);
    }
}
