package diploma.ray;

import diploma.Creator;
import diploma.geometry.Canvas;
import diploma.geometry.Matrix;
import diploma.geometry.Point;
import diploma.geometry.Tuple;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Camera {

    private int hsize;
    private int vsize;
    private double fov;
    private double halfWidth;
    private double halfHeight;
    private double pixelSize;
    private Matrix transform;

    public Camera(int hsize, int vsize, double fov) {
        this.hsize = hsize;
        this.vsize = vsize;
        this.fov = Math.PI/fov;
        this.pixelSize = calculatePixelSize();
        transform = Creator.createIdentityMatrix();
    }

    public Ray rayForPixel(double px, double py) {
        double xoffset = (px + 0.5) * pixelSize;
        double yoffset = (py + 0.5) * pixelSize;
        double worldX = halfWidth - xoffset;
        double worldY = halfHeight - yoffset;
        Tuple pixel = transform.inverse().multiplyByTuple(new Point(worldX, worldY, -1));
        Tuple origin = transform.inverse().multiplyByTuple(new Point(0, 0, 0));
        Tuple direction = pixel.subtract(origin).normalize();
        return new Ray(origin, direction);
    }

    public Canvas render(World world) {
        Canvas image = new Canvas(getHsize(), getVsize());
        for (int i = 0; i < getVsize() - 1; i++) {
            for (int j = 0; j < getHsize() - 1; j++) {
                Ray ray = rayForPixel(j, i);
                Tuple color = world.colorAt(ray);
                image.writeToPixel(j, i, color);
            }
        }
        return image;
    }

    private double calculatePixelSize() {
        double halfView = Math.tan(fov/2);
        double aspect = (double)hsize/(double)vsize;
        if (aspect >= 1) {
            halfWidth = halfView;
            halfHeight = format(halfView/aspect);
        } else {
            halfWidth = format(halfView*aspect);
            halfHeight = halfView;
        }
        return format((halfWidth*2)/hsize);
    }

    private double format(double value) {
        return Double.parseDouble(new DecimalFormat("#.###", DecimalFormatSymbols.getInstance(Locale.US)).format((value)));
    }

    public int getHsize() {
        return hsize;
    }

    public int getVsize() {
        return vsize;
    }

    public double getFov() {
        return fov;
    }

    public double getHalfWidth() {
        return halfWidth;
    }

    public double getHalfHeight() {
        return halfHeight;
    }

    public Matrix getTransform() {
        return transform;
    }

    public double getPixelSize() {
        return pixelSize;
    }

    public void setHsize(int hsize) {
        this.hsize = hsize;
    }

    public void setVsize(int vsize) {
        this.vsize = vsize;
    }

    public void setFov(double fov) {
        this.fov = fov;
    }

    public void setTransform(Matrix transform) {
        this.transform = transform;
    }

    public void setPixelSize(double pixelSize) {
        this.pixelSize = pixelSize;
    }

    public void setHalfWidth(double halfWidth) {
        this.halfWidth = halfWidth;
    }

    public void setHalfHeight(double halfHeight) {
        this.halfHeight = halfHeight;
    }
}
