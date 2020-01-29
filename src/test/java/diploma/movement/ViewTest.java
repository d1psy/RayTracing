package diploma.movement;

import diploma.Creator;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Tuple;
import diploma.geometry.Vector;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTest {

    private Tuple from;
    private Tuple to;
    private Tuple up;
    private Matrix transform;

    @Given("From point {double} {double} {double}")
    public void createFromPoint(double x, double y, double z) {
        from = new Point(x, y, z);
    }

    @Given("To point {double} {double} {double}")
    public void createToPoint(double x, double y, double z) {
        to = new Point(x, y, z);
    }

    @Given("Up vector {double} {double} {double}")
    public void createUpPoint(double x, double y, double z) {
        up = new Vector(x, y, z);
    }

    @When("View transform")
    public void createTransfrom() {
        transform = from.viewTransform(to, up);
    }

    @Then("Transform is identity matrix")
    public void transformIsIdentity() {
        assertEquals(Creator.createIdentityMatrix(), transform);
    }

    @Then("Transform is scaling {double} {double} {double}")
    public void transformIsScaling(double x, double y, double z) {
        assertEquals(Creator.createScalingMatrix(x, y, z), transform);
    }

    @Then("Transform is translation {double} {double} {double}")
    public void transformIsTranslation(double x, double y, double z) {
        assertEquals(Creator.createTranslationMatrix(x, y, z), transform);
    }

    @Then("Transform is {int} x {int} matrix {string}")
    public void transformComparesToMatrix(int row, int col, String sequence) {
        Matrix expected = new Matrix(row, col, createMatrixSequence(row, col, sequence));
        assertEquals(expected, transform);
    }

    private double[] createMatrixSequence(int rows, int columns, String sequence) {
        double[] parsedSequence = new double[rows * columns];
        int current = 0;
        for (String str : sequence.split(" ")) {
            parsedSequence[current++] = Double.parseDouble(str);
        }
        return parsedSequence;
    }
}
