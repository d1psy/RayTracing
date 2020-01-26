package diploma.geometry;

public class Matrix {

    private int rows;
    private int columns;
    private float[][] data;

    public Matrix() {

    }

    public Matrix(int rows, int columns, float... data) {
        this.rows = rows;
        this.columns = columns;
        this.data = new float[rows][columns];
        int pointer = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.data[i][j] = data[pointer++];
            }
        }
    }

    public Matrix(float[][] data) {
        this.rows = data.length;
        this.columns = data[0].length;
        this.data = data;
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public float[][] getData() {
        return data;
    }

    public void setData(float[][] data) {
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
