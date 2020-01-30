package diploma.geometry;

public class Matrix {

    private int rows;
    private int columns;
    private double[][] data;
    private Matrix inverse;

    public Matrix() {

    }

    public Matrix(int rows, int columns, double... data) {
        this.rows = rows;
        this.columns = columns;
        this.data = new double[rows][columns];
        int pointer = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = data[pointer++];
            }
        }
    }

    public Matrix(double[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = data;
    }

    public Matrix transpose() {
        double[][] data = new double[4][4];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                data[j][i] = getData()[i][j];
            }
        }
        return new Matrix(data);
    }

    public double getDeterminant()  {
        double[][] data = getData();
        if (getData().length == 2) {
            return data[0][0] * data[1][1] - data[0][1] * data[1][0];
        }
        if (getData().length == 3) {
            return data[0][0] * getCofactor(0, 0)
                    + data[0][1] * getCofactor(0, 1)
                    + data[0][2] * getCofactor(0, 2);
        } else {
            return data[0][0] * getCofactor(0, 0)
                    + data[0][1] * getCofactor(0, 1)
                    + data[0][2] * getCofactor(0, 2)
                    + data[0][3] * getCofactor(0, 3);
        }
    }

    public Matrix submatrix(int row, int col) {
        double[][] data = new double[getRows() - 1][getColumns() - 1];
        for (int i = 0; i < getRows(); i++) {
            for (int j = 0; j < getColumns(); j++) {
                if (i < row) {
                    if (j < col) {
                        data[i][j] = getData()[i][j];
                    } else if (j > col) {
                        data[i][j - 1] = getData()[i][j];
                    }
                } else if (i > row) {
                    if (j < col) {
                        data[i - 1][j] = getData()[i][j];
                    } else if (j > col) {
                        data[i - 1][j - 1] = getData()[i][j];
                    }
                }
            }
        }
        return new Matrix(data);
    }

    public double getMinor(int row, int col) {
        Matrix subMatrix = submatrix(row, col);
        return subMatrix.getDeterminant();
    }

    public double getCofactor(int row, int col) {
        Matrix subMatrix = submatrix(row, col);
        if (row % 2 == col % 2) {
            return subMatrix.getDeterminant();
        } else {
            return -subMatrix.getDeterminant();
        }
    }

    public Matrix inverse() {
        if (inverse != null) {
            return inverse;
        }
        if (isInvertible()) {
            double[][] data = new double[getRows()][getColumns()];
            double determinant = getDeterminant();
            for (int i = 0; i < getRows(); i++) {
                for (int j = 0; j < getColumns(); j++) {
                    double cofactor = getCofactor(i, j);
                    data[j][i] = cofactor/determinant;
                }
            }
            inverse = new Matrix(data);
            return new Matrix(data);
        }
        return null;
    }

    public boolean isInvertible() {
        return getDeterminant() != 0;
    }

    public Tuple multiplyByTuple(Tuple tuple) {
        Matrix tupleMatrix = multiply(new Matrix(4, 1, tuple.getX(), tuple.getY(), tuple.getZ(), tuple.getW()));
        return new Tuple(tupleMatrix.getData()[0][0], tupleMatrix.getData()[1][0], tupleMatrix.getData()[2][0], tupleMatrix.getData()[3][0]);
    }

    public Matrix multiply(Matrix matrix) {
        double[][] result = new double[getData().length][matrix.getData()[0].length];
        for (int row = 0; row < result.length; row++) {
            for (int col = 0; col < result[row].length; col++) {
                result[row][col] = multiplyMatricesCell(getData(), matrix.getData(), row, col);
            }
        }
        return new Matrix(result);
    }

    private double multiplyMatricesCell(double[][] firstMatrix, double[][] secondMatrix, int row, int col) {
        double cell = 0;
        for (int i = 0; i < secondMatrix.length; i++) {
            cell += firstMatrix[row][i] * secondMatrix[i][col];
        }
        return cell;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(double[][] data) {
        this.data = data;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Matrix) {
            if (((Matrix) obj).getRows() == rows && ((Matrix) obj).getColumns() == columns) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < rows; j++) {
                        if (((Matrix) obj).getData()[i][j] != data[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
