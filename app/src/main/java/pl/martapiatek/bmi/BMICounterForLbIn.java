package pl.martapiatek.bmi;


class BMICounterForLbIn extends BMICounter {

    public BMICounterForLbIn(double mass, double height) {
        super(mass, height);
    }

    @Override
    public double count() throws IllegalArgumentException {
        if(isDataValid()){
            return (getMass() /(getHeight() * getHeight())) * 703;
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
