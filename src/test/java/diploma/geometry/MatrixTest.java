package diploma.geometry;

import diploma.Creator;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixTest {

    private Matrix matrix;
    private Matrix secondMatrix;
    private Matrix thirdMatrix;
    private Matrix submatrix;
    private Tuple tuple;

    @Given("The following {int} x {int} matrix {string}")
    public void createMatrix(int rows, int columns, String sequence) {
        matrix = new Matrix(rows, columns, createMatrixSequence(rows, columns, sequence));
    }

    @Given("Second matrix {int} x {int} {string}")
    public void createSecondMatrix(int rows, int columns, String sequence) {
        secondMatrix = new Matrix(rows, columns, createMatrixSequence(rows, columns, sequence));
    }

    @Given("Third matrix is multiplication")
    public void createThirdMatrix() {
        thirdMatrix = matrix.multiply(secondMatrix);
    }

    @Given("Submatrix {int} {int}")
    public void createSubmatrix(int row, int column) {
        submatrix = matrix.submatrix(row, column);
    }

    @Given("Inverse matrix")
    public void inverseMatrix() {
        secondMatrix = matrix.inverse();
    }

    @Given("Tuple {double} {double} {double} {double}")
    public void createTuple(double x, double y, double z, double w) {
        tuple = new Tuple(x, y, z, w);
    }

    @Then("Matrix {int} {int} = {double}")
    public void assertMatrixPointEquals(int row, int column, double expected) {
        assertEquals(expected, matrix.getData()[row][column]);
    }

    @Then("Matrices are equal")
    public void matrixAreEqual() {
        assertEquals(matrix, secondMatrix);
    }

    @Then("Matrices are not equal")
    public void matrixAreNotEqual() {
        assertNotEquals(matrix, secondMatrix);
    }

    @Then("Multiply {int} x {int} matrix equals {string}")
    public void multiplyMatrix(int rows, int columns, String sequence) {
        Matrix expected = new Matrix(rows, columns, createMatrixSequence(rows, columns, sequence));
            assertEquals(expected, matrix.multiply(secondMatrix));
    }

    @Then("Multiply matrix by tuple equals {double} {double} {double} {double}")
    public void multiplyByTiple(double x, double y, double z, double w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = matrix.multiplyByTuple(tuple);
        assertEquals(expected, actual);
    }

    @Then("Multiply by identity matrix equals original")
    public void multiplyByIdentityMatrix() {
        Matrix identity = Creator.createIdentityMatrix();
        assertEquals(matrix, matrix.multiply(identity));
    }

    @Then("Multiply identity matrix by tuple equals tuple")
    public void multiplyIdentityMatrixByTuple() {
        Matrix identity = Creator.createIdentityMatrix();
        assertEquals(tuple, identity.multiplyByTuple(tuple));
    }

    @Then("Transpose matrix equals {string}")
    public void transposeMatrix(String sequence) {
        Matrix expected = new Matrix(4, 4, createMatrixSequence(4, 4, sequence));
        assertEquals(expected, matrix.transpose());
    }

    @Then("Determinant = {double}")
    public void determine(double expected) {
        assertEquals(expected, matrix.getDeterminant());
    }

    @Then("Submatrix {int} {int} is following {int} x {int} {string}")
    public void submatrix(int delRow, int delColumn, int row, int column, String sequence) {
        Matrix expected = new Matrix(row, column, createMatrixSequence(row, column, sequence));
        assertEquals(expected, matrix.submatrix(delRow, delColumn));
    }

    @Then("Determinant of submatrix = {double}")
    public void determinantOfSubmatrix(double expected) {
        assertEquals(expected, submatrix.getDeterminant());
    }

    @Then("Minor of matrix {int} {int} {double}")
    public void minorOfMatrix(int delRow, int delColumn, double expected) {
        assertEquals(expected, matrix.getMinor(delRow, delColumn));
    }

    @Then("Cofactor of matrix {int} {int} {double}")
    public void cofactorOfMatirx(int delRow, int delColumn, double expected) {
        assertEquals(expected, matrix.getCofactor(delRow, delColumn));
    }

    @Then("Matrix is invertible")
    public void matrixIsInvertible() {
        assertTrue(matrix.isInvertible());
    }

    @Then("Matrix is not invertible")
    public void matrixIsNotInvertible() {
        assertFalse(matrix.isInvertible());
    }

    @Then("Inverse {int} {int} = {double}")
    public void inverseMatrixColRow(int row, int col, double expected)  {
        assertEquals(expected, secondMatrix.getData()[row][col]);
    }

    @Then("Inverse is the following {int} x {int} matrix {string}")
    public void inverseIsFollowing(int rows, int columns, String sequence) {
        Matrix expected = new Matrix(rows, columns, createMatrixSequence(rows, columns, sequence));
        assertEquals(expected, secondMatrix);
    }

    @Then("First matrix is third multiplied by inversed second")
    public void getFirstMatrixFromThirdAndInversedSecond() {
        Matrix actual = thirdMatrix.multiply(secondMatrix.inverse());
        assertEquals(matrix, actual);
    }

    private double[] createMatrixSequence(int rows, int columns, String sequence) {
        double[] parsedSequence = new double[rows * columns];
        int current = 0;
        for(String str : sequence.split(" ")) {
            parsedSequence[current++] = Double.parseDouble(str);
        }
        return parsedSequence;
    }
}
