package diploma;

import diploma.geometry.Matrix;

public class Creator {

    public static Matrix createIdentityMatrix() {
        float[][] data = new float[4][4];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                if (i == j) {
                    data[i][j] = 1;
                } else {
                    data[i][j] = 0;
                }
            }
        }
        return new Matrix(data);
    }

    public static Matrix createTranslationMatrix(float x, float y, float z) {
        Matrix result = createIdentityMatrix();
        result.getData()[0][3] = x;
        result.getData()[1][3] = y;
        result.getData()[2][3] = z;
        return result;
    }

    public static Matrix createScalingMatrix(float x, float y, float z) {
        Matrix result = createIdentityMatrix();
        result.getData()[0][0] = x;
        result.getData()[1][1] = y;
        result.getData()[2][2] = z;
        return result;
    }

    public static Matrix createShearingMatrix(float x1, float x2, float y1, float y2, float z1, float z2) {
        Matrix result = createIdentityMatrix();
        result.getData()[0][1] = x1;
        result.getData()[0][2] = x2;
        result.getData()[1][0] = y1;
        result.getData()[1][2] = y2;
        result.getData()[2][0] = z1;
        result.getData()[2][1] = z2;
        return result;
    }

    public static Matrix xRotationMatrix(float r) {
        Matrix result = createIdentityMatrix();
        result.getData()[1][1] = (float) Math.cos(Math.PI/r);
        result.getData()[1][2] = -(float) Math.sin(Math.PI/r);
        result.getData()[2][1] = (float) Math.sin(Math.PI/r);
        result.getData()[2][2] = (float) Math.cos(Math.PI/r);
        return result;
    }

    public static Matrix yRotationMatrix(float r) {
        Matrix result = createIdentityMatrix();
        result.getData()[0][0] = (float) Math.cos(Math.PI/r);
        result.getData()[0][2] = (float) Math.sin(Math.PI/r);
        result.getData()[2][0] = -(float) Math.sin(Math.PI/r);
        result.getData()[2][2] = (float) Math.cos(Math.PI/r);
        return result;
    }

    public static Matrix zRotationMatrix(float r) {
        Matrix result = createIdentityMatrix();
        result.getData()[0][0] = (float) Math.cos(Math.PI/r);
        result.getData()[0][1] = -(float) Math.sin(Math.PI/r);
        result.getData()[1][0] = (float) Math.sin(Math.PI/r);
        result.getData()[1][1] = (float) Math.cos(Math.PI/r);
        return result;
    }
}
