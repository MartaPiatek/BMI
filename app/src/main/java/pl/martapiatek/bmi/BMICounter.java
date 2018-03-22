package pl.martapiatek.bmi;

public abstract class BMICounter {


    private double mass;
    private double height;

    public BMICounter(double mass, double height) {
        this.mass = mass;
        this.height = height;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    abstract public double count() throws IllegalArgumentException;

    abstract public boolean isMassValid();
    abstract public boolean isHeightValid();

    protected boolean isDataValid() {
        return mass >0 && height >0;
    }

}
