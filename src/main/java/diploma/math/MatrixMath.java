package diploma.math;

import diploma.geometry.Matrix;
import diploma.geometry.Tuple;

import static diploma.math.FloatUtils.format;

public class MatrixMath {

    public static Tuple multiplyByTuple(Matrix matrix, Tuple tuple) {
        Matrix tupleMatrix = multiply(matrix, new Matrix(4, 1, tuple.getX(), tuple.getY(), tuple.getZ(), tuple.getW()));
        return new Tuple(tupleMatrix.getData()[0][0], tupleMatrix.getData()[1][0], tupleMatrix.getData()[2][0], tupleMatrix.getData()[3][0]);
    }

    public static Matrix multiply(Matrix m1, Matrix m2) {

        float[][] result = new float[m1.getData().length][m2.getData()[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = format(multiplyMatricesCell(m1.getData(), m2.getData(), row, col), "#.#");
            }
        }
        return new Matrix(result);
    }

    public static Matrix transpose(Matrix matrix) {
        float[][] data = new float[4][4];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                data[j][i] = matrix.getData()[i][j];
            }
        }
        return new Matrix(data);
    }

    public static float getDeterminant(Matrix matrix) {
        float[][] data = matrix.getData();
        if (matrix.getData().length == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        }
        if (matrix.getData().length == 3) {
            return data[0][0] * getCofactor(matrix, 0, 0)
                    + data[0][1] * getCofactor(matrix, 0, 1)
                    + data[0][2] * getCofactor(matrix, 0, 2);
        } else {
            return data[0][0] * getCofactor(matrix, 0, 0)
                    + data[0][1] * getCofactor(matrix, 0, 1)
                    + data[0][2] * getCofactor(matrix, 0, 2)
                    + data[0][3] * getCofactor(matrix, 0, 3);
        }
    }

    public static Matrix submatrix(Matrix matrix, int row, int col) {
        float[][] data = new float[matrix.getRows() - 1][matrix.getColumns() - 1];
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getColumns(); j++) {
                if (i < row) {
                    if (j < col) {
                        data[i][j] = matrix.getData()[i][j];
                    } else if (j > col) {
                        data[i][j - 1] = matrix.getData()[i][j];
                    }
                } else if (i > row) {
                    if (j < col) {
                        data[i - 1][j] = matrix.getData()[i][j];
                    } else if (j > col) {
                        data[i - 1][j - 1] = matrix.getData()[i][j];
                    }
                }
            }
        }
        return new Matrix(data);
    }

    public static float getMinor(Matrix matrix, int row, int col) {
        Matrix subMatrix = submatrix(matrix, row, col);
        return getDeterminant(subMatrix);
    }

    public static float getCofactor(Matrix matrix, int row, int col) {
        Matrix subMatrix = submatrix(matrix, row, col);
        if (row % 2 == col % 2) {
            return getDeterminant(subMatrix);
        } else {
            return -getDeterminant(subMatrix);
        }
    }

    public static Matrix inverse(Matrix matrix) {
        if (isInvertible(matrix)) {
            float[][] data = new float[matrix.getRows()][matrix.getColumns()];
            float determinant = getDeterminant(matrix);
            for (int i = 0; i < matrix.getRows(); i++) {
                for (int j = 0; j < matrix.getColumns(); j++) {
                    float cofactor = getCofactor(matrix, i, j);
                    data[j][i] = format(cofactor/determinant, "#.#####");
                }
            }
            return new Matrix(data);
        }
        return null;
    }

    public static boolean isInvertible(Matrix matrix) {
        return getDeterminant(matrix) != 0;
    }

    private static float multiplyMatricesCell(float[][] firstMatrix, float[][] secondMatrix, int row, int col) {
        float cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }
}