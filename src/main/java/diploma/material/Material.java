package diploma.material;

import diploma.geometry.Color;
import diploma.geometry.Tuple;
import diploma.ray.Light;

public class Material {

    private Tuple color;
    private double ambient;
    private double diffuse;
    private double specular;
    private double shininess;
    private double reflectivity;
    private double transparency;
    private double refractiveIndex;

    public Material(Color color) {
        this.color = color;
        ambient = 0.1;
        diffuse = 0.9;
        specular = 0.9;
        shininess = 200.0;
        reflectivity = 0;
        transparency = 0;
        refractiveIndex = 1;
    }

    public Tuple lighting(Light light, Tuple position, Tuple eyeVector, Tuple normalVector, boolean inShadow) {
        Tuple effectiveColor = getColor().multiplyTuple(light.getIntensity());
        Tuple lightVector = light.getPosition().subtract(position).normalize();
        Tuple ambient = effectiveColor.multiply(getAmbient());
        if (inShadow) {
            return ambient;
        }
        double dot = lightVector.dotProduct(normalVector, "#.#");
        Tuple diffuse;
        Tuple specular;
        Tuple reflectVector;
        double reflectDotEye;
        if (dot < 0) {
            diffuse = new Color(0.0, 0.0, 0.0);
            specular = new Color(0.0, 0.0, 0.0);
        } else {
            diffuse = effectiveColor.multiply(getDiffuse()).multiply(dot);
            reflectVector = lightVector.negate().reflect(normalVector);
            reflectDotEye = reflectVector.dotProduct(eyeVector, "#.#");
            if (reflectDotEye <= 0) {
                specular = new Color(0.0, 0.0, 0.0);
            } else {
                double factor = Math.pow(reflectDotEye, getShininess());
                specular = light.getIntensity().multiply(getSpecular()).multiply(factor);
            }
        }
        return ambient.add(diffuse).add(specular);
    }

    public Tuple getColor() {
        return color;
    }

    public double getAmbient() {
        return ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public double getShininess() {
        return shininess;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setAmbient(double ambient) {
        this.ambient = ambient;
    }

    public void setDiffuse(double diffuse) {
        this.diffuse = diffuse;
    }

    public void setSpecular(double specular) {
        this.specular = specular;
    }

    public void setShininess(double shininess) {
        this.shininess = shininess;
    }

    public double getReflectivity() {
        return reflectivity;
    }

    public void setReflectivity(double reflectivity) {
        this.reflectivity = reflectivity;
    }

    public double getTransparency() {
        return transparency;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }

    public double getRefractiveIndex() {
        return refractiveIndex;
    }

    public void setRefractiveIndex(double refractiveIndex) throws Exception {
        if (refractiveIndex < 1) {
            throw new Exception("Refractive index can't be less than 1");
        }
        this.refractiveIndex = refractiveIndex;
    }
}
