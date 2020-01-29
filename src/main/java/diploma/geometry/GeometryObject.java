package diploma.geometry;

import diploma.Creator;
import diploma.material.Material;

public class GeometryObject {

    private int id;
    private Point position;
    private Matrix transformation;
    private Material material;

    public GeometryObject(int id) {
        this.id = id;
        position = new Point(0.0, 0.0, 0.0);
        transformation = Creator.createIdentityMatrix();
        material = new Material(new Color(1.0, 1.0, 1.0));
    }

    public Matrix getTransformation() {
        return transformation;
    }

    public int getId() {
        return id;
    }

    public Point getPosition() {
        return position;
    }

    public Material getMaterial() {
        return material;
    }

    public void setTransformation(Matrix transformation) {
        this.transformation = transformation;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Tuple normalAt(Tuple point) {
        Tuple objectPoint = getTransformation().inverse().multiplyByTuple(point);
        Tuple objectNormal = objectPoint.subtract(getPosition());
        Tuple worldNormal = getTransformation().inverse().transpose().multiplyByTuple(objectNormal);
        worldNormal.setW(0.0);
        return worldNormal.normalize();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GeometryObject) {
            return ((GeometryObject) obj).getId() == id;
        }
        return false;
    }
}
