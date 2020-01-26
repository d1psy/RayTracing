package diploma.geometry;

import diploma.Creator;
import diploma.math.MatrixMath;
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
        thirdMatrix = MatrixMath.multiply(matrix, secondMatrix);
    }

    @Given("Submatrix {int} {int}")
    public void createSubmatrix(int row, int column) {
        submatrix = MatrixMath.submatrix(matrix, row, column);
    }

    @Given("Inverse matrix")
    public void inverseMatrix() {
        secondMatrix = MatrixMath.inverse(matrix);
    }

    @Given("Tuple {float} {float} {float} {float}")
    public void createTuple(float x, float y, float z, float w) {
        tuple = new Tuple(x, y, z, w);
    }

    @Then("Matrix {int} {int} = {float}")
    public void assertMatrixPointEquals(int row, int column, float expected) {
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
            assertEquals(expected, MatrixMath.multiply(matrix, secondMatrix));
    }

    @Then("Multiply matrix by tuple equals {float} {float} {float} {float}")
    public void multiplyByTiple(float x, float y, float z, float w) {
        Tuple expected = new Tuple(x, y, z, w);
        Tuple actual = MatrixMath.multiplyByTuple(matrix, tuple);
        assertEquals(expected, actual);
    }

    @Then("Multiply by identity matrix equals original")
    public void multiplyByIdentityMatrix() {
        Matrix identity = Creator.createIdentityMatrix();
        assertEquals(matrix, MatrixMath.multiply(matrix, identity));
    }

    @Then("Multiply identity matrix by tuple equals tuple")
    public void multiplyIdentityMatrixByTuple() {
        Matrix identity = Creator.createIdentityMatrix();
        assertEquals(tuple, MatrixMath.multiplyByTuple(identity, tuple));
    }

    @Then("Transpose matrix equals {string}")
    public void transposeMatrix(String sequence) {
        Matrix expected = new Matrix(4, 4, createMatrixSequence(4, 4, sequence));
        assertEquals(expected, MatrixMath.transpose(matrix));
    }

    @Then("Determinant = {float}")
    public void determine(float expected) {
        assertEquals(expected, MatrixMath.getDeterminant(matrix));
    }

    @Then("Submatrix {int} {int} is following {int} x {int} {string}")
    public void submatrix(int delRow, int delColumn, int row, int column, String sequence) {
        Matrix expected = new Matrix(row, column, createMatrixSequence(row, column, sequence));
        assertEquals(expected, MatrixMath.submatrix(matrix, delRow, delColumn));
    }

    @Then("Determinant of submatrix = {float}")
    public void determinantOfSubmatrix(float expected) {
        assertEquals(expected, MatrixMath.getDeterminant(submatrix));
    }

    @Then("Minor of matrix {int} {int} {float}")
    public void minorOfMatrix(int delRow, int delColumn, float expected) {
        assertEquals(expected, MatrixMath.getMinor(matrix, delRow, delColumn));
    }

    @Then("Cofactor of matrix {int} {int} {float}")
    public void cofactorOfMatirx(int delRow, int delColumn, float expected) {
        assertEquals(expected, MatrixMath.getCofactor(matrix, delRow, delColumn));
    }

    @Then("Matrix is invertible")
    public void matrixIsInvertible() {
        assertTrue(MatrixMath.isInvertible(matrix));
    }

    @Then("Matrix is not invertible")
    public void matrixIsNotInvertible() {
        assertFalse(MatrixMath.isInvertible(matrix));
    }

    @Then("Inverse {int} {int} = {float}")
    public void inverseMatrixColRow(int row, int col, float expected)  {
        assertEquals(expected, secondMatrix.getData()[row][col]);
    }

    @Then("Inverse is the following {int} x {int} matrix {string}")
    public void inverseIsFollowing(int rows, int columns, String sequence) {
        Matrix expected = new Matrix(rows, columns, createMatrixSequence(rows, columns, sequence));
        assertEquals(expected, secondMatrix);
    }

    @Then("First matrix is third multiplied by inversed second")
    public void getFirstMatrixFromThirdAndInversedSecond() {
        Matrix actual = MatrixMath.multiply(thirdMatrix, MatrixMath.inverse(secondMatrix));
        assertEquals(matrix, actual);
    }

    private float[] createMatrixSequence(int rows, int columns, String sequence) {
        float[] parsedSequence = new float[rows * columns];
        int current = 0;
        for(String str : sequence.split(" ")) {
            parsedSequence[current++] = Float.parseFloat(str);
        }
        return parsedSequence;
    }
}
