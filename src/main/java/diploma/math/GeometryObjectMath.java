package diploma.math;

import diploma.geometry.Sphere;
import diploma.geometry.Tuple;

public class GeometryObjectMath {

    public static Tuple normalAt(Sphere sphere, Tuple point) {
        Tuple objectPoint = MatrixMath.multiplyByTuple(MatrixMath.inverse(sphere.getTransformation()), point);
        Tuple objectNormal = RayMath.subtract(objectPoint, sphere.getPosition());
        Tuple worldNormal = MatrixMath.multiplyByTuple(MatrixMath.transpose(MatrixMath.inverse(sphere.getTransformation())), objectNormal);
        worldNormal.setW(0.0f);
        return RayMath.normalize(worldNormal);
    }
}
