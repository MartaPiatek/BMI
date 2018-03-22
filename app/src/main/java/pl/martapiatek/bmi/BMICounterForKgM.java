package pl.martapiatek.bmi;


public class BMICounterForKgM extends BMICounter {

    public BMICounterForKgM(double mass, double height) {
        super(mass, height);
    }

    @Override
    public double count() throws IllegalArgumentException {
        if(isDataValid()){
            return getMass() /(getHeight() * getHeight());
        }

        else
        {
            throw new IllegalArgumentException("Wrong data");
        }
    }

    @Override
    public boolean isMassValid() {
        return getMass() > 5 && getMass() < 250 ;
    }

    @Override
    public boolean isHeightValid() {
        return getHeight() > 0;
    }





    public boolean isDataValid() {
        return isMassValid() && isHeightValid();
    }



}
