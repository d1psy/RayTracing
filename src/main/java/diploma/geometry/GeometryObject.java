package diploma.geometry;

import diploma.Creator;

public class GeometryObject {

    private int id;
    private Point position;
    private Matrix transformation;

    public GeometryObject(int id) {
        this.id = id;
        position = new Point(0.0f, 0.0f, 0.0f);
        transformation = Creator.createIdentityMatrix();
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GeometryObject) {
            return ((GeometryObject) obj).getId() == id;
        }
        return false;
    }
}
