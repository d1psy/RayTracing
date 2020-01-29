package diploma.geometry;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class TupleTest {

    private Tuple tuple;
    private Tuple secondTuple;
    private Tuple reflectedVector;

    @Given("Tuple x={double}, y={double}, z={double}, w={double}")
    public void createTuple(double x, double y, double z, double w) {
        tuple = new Tuple(x, y, z, w);
    }

    @Given("Point x={double}, y={double}, z={double}")
    public void createPoint(double x, double y, double z) {
        tuple = new Point(x, y, z);
    }

    @Given("Vector x={double}, y={double}, z={double}")
    public void createVector(double x, double y, double z) {
        tuple = new Vector(x, y, z);
    }

    @Given("Color red={double} green={double} blue={double}")
    public void createColor(double red, double green, double blue) {
        tuple = new Color(red, green, blue);
    }

    @Given("Second tuple x={double}, y={double}, z={double}, w={double}")
    public void createSecondTuple(double x, double y, double z, double w) {
        secondTuple = new Tuple(x, y, z, w);
    }

    @Given("Second point x={double}, y={double}, z={double}")
    public void createSecondPoint(double x, double y, double z) {
        secondTuple = new Point(x, y, z);
    }

    @Given("Second vector x={double}, y={double}, z={double}")
    public void createSecondTuple(double x, double y, double z) {
        secondTuple = new Vector(x, y, z);
    }

    @Given("Second color red={double} green={double} blue={double}")
    public void createSecondColor(double red, double green, double blue) {
        secondTuple = new Color(red, green, blue);
    }

    @Given("{word} axis={double}")
    public void createTwoTuples(String type, double axis) {
        if (type.equalsIgnoreCase("point")) {
            tuple = new Tuple(axis, axis, axis, 1.0);
            secondTuple = new Point(axis, axis, axis);
        } else {
            tuple = new Tuple(axis, axis, axis, 0.0);
            secondTuple = new Vector(axis, axis, axis);
        }
    }

    @When("Normalize")
    public void normalizeVector() {
        tuple = tuple.normalize();
    }

    @When("Reflect vectors")
    public void reflectVectors() {
        reflectedVector = tuple.reflect(secondTuple);
    }

    @Then("get x = {double}")
    public void xIsSet(double x) {
        assertEquals(tuple.getX(), x);
    }

    @Then("get y = {double}")
    public void yIsSet(double y) {
        assertEquals(tuple.getY(), y);
    }

    @Then("get z = {double}")
    public void zIsSet(double z) {
        assertEquals(tuple.getZ(), z);
    }

    @Then("get w = {double}")
    public void wIsSet(double w) {
        assertEquals(tuple.getW(), w);
    }

    @Then("is a {word} {word}")
    public void pointOrVector(String type, String val) {
        boolean bool = Boolean.parseBoolean(val);
        double value;

        if((type.equals("vector") && bool) || (type.equals("point") && !bool)) {
            value = 0.0;
        } else {
            value = 1.0;
        }
        assertEquals(tuple.getW(), value);
    }

    @Then("are equal")
    public void sameTuples(){
        assertEquals(tuple, secondTuple);
    }

    @Then("combine tuples equals {double}, {double}, {double}, {double}")
    public void combineAndCompareTuples(double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = tuple.add(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("subtract equals {word} {double}, {double}, {double}")
    public void subtractAndCompareTuples(String type, double x, double y, double z) {
        Tuple expected;
        Tuple actual = tuple.subtract(secondTuple);
        if (type.equalsIgnoreCase("vector")) {
            expected = new Vector(x, y, z);
        } else {
            expected = new Point(x, y, z);
        }
        assertEquals(expected, actual);
    }

    @Then("Negate tuple x={double}, y={double}, z={double}, w={double}")
    public void negateTuple(double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        assertEquals(expected, tuple.negate());
    }

    @Then("Multiply tuple by {double} equals {double}, {double}, {double}, {double}")
    public void multiplyTuple(double mult, double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = tuple.multiply(mult);
        assertEquals(expected, actual);
    }

    @Then("Divide tuple by {double} equals {double}, {double}, {double}, {double}")
    public void divideTuple(double mult, double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = tuple.divide(mult);
        assertEquals(expected, actual);
    }

    @Then("Magnitude equals {double}")
    public void compareMagnitude(double mag) {
        double actual = tuple.magnitude();
        assertEquals(mag, actual);
    }

    @Then("Normalize equals {double}, {double}, {double}")
    public void compareNormalized(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = tuple.normalize();
        assertEquals(expected, actual);
    }

    @Then("Dot equals {double}")
    public void dotEquals(double dot) {
        double actual = tuple.dotProduct(secondTuple, "#.#####");
        assertEquals(dot, actual);
    }

    @Then("Cross vectors {double}, {double}, {double}")
    public void crossVectors(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = tuple.cross(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Reverse cross vectors {double}, {double}, {double}")
    public void reverseCrossVectors(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        Tuple actual = secondTuple.cross(tuple);
        assertEquals(expected, actual);
    }

    @Then("Red is {double}")
    public void redEquals(double expected) {
        assertEquals(expected, tuple.getX());
    }

    @Then("Green is {double}")
    public void greenEquals(double expected) {
        assertEquals(expected, tuple.getY());
    }

    @Then("Blue is {double}")
    public void blueEquals(double expected) {
        assertEquals(expected, tuple.getZ());
    }

    @Then("Adding colors equals {double} {double} {double}")
    public void addingColors(double red, double green, double blue) {
        Tuple expected = new Color(red, green, blue);
        Tuple actual = tuple.add(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Subtracting colors equals {double} {double} {double}")
    public void subtractingColors(double red, double green, double blue) {
        Tuple expected = new Color(red, green, blue);
        Tuple actual = tuple.subtract(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Multiply tuples equals {double}, {double}, {double}, {double}")
    public void multTuples(double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = tuple.multiplyTuple(secondTuple);
        assertEquals(expected, actual);
    }

    @Then("Reflected vector {double} {double} {double}")
    public void reflectedVector(double x, double y, double z) {
        Tuple expected = new Vector(x, y, z);
        assertEquals(expected, reflectedVector);
    }
}
