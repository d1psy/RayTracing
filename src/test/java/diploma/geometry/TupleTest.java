package diploma.geometry;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {

    private Tuple tuple;
    private Tuple secondTuple;

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
        tuple = tuple.normalize();
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
        Tuple actual = tuple.add(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("subtract equals {word} {float}, {float}, {float}")
    public void subtractAndCompareTuples(String type, float x, float y, float z) {
        Tuple expected;
        Tuple actual = tuple.subtract(secondTuple);
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
        Tuple actual = tuple.multiply(mult);
        assertEquals(expected, actual);
    }

    @Then("Divide tuple by {float} equals {float}, {float}, {float}, {float}")
    public void divideTuple(float mult, float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = tuple.divide(mult);
        assertEquals(expected, actual);
    }

    @Then("Magnitude equals {float}")
    public void compareMagnitude(float mag) {
        float actual = tuple.magnitude();
        assertEquals(mag, actual);
    }

    @Then("Normalize equals {float}, {float}, {float}")
    public void compareNormalized(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = tuple.normalize();
        assertEquals(expected, actual);
    }

    @Then("Dot equals {float}")
    public void dotEquals(float dot) {
        float actual = tuple.dotProduct(secondTuple);
        assertEquals(dot, actual);
    }

    @Then("Cross vectors {float}, {float}, {float}")
    public void crossVectors(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = tuple.cross(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Reverse cross vectors {float}, {float}, {float}")
    public void reverseCrossVectors(float x, float y, float z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = secondTuple.cross(tuple);
        assertEquals(expected, actual);
    }
}
