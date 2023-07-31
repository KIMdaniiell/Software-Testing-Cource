package main.mathFunctions.trigonometricFunctions;

import main.mathFunctions.MathFunction;

public class Cosecant extends MathFunction {
    private Sine sine;
    boolean argInGrad;

    public Cosecant (Sine sine) {
        super(sine.getDecimalPlaces());
        this.sine = sine;
        this.argInGrad = false;
    }

    @Override
    public Double apply(Double x) {
        double result;

        if (null == x || x.isInfinite() || x.isNaN()) {
            System.out.println("Cosecant: [Error] angle value is undefined or is not a number.");
            return null;
        }

        if (argInGrad) {
            //System.out.printf("%.10f GRAD -> ",  x);
            x = Math.toRadians(x);
            //System.out.printf("%.10f PI%n", x);
        }

        x = normalize(x);

        //if (x % Math.PI == 0) {
        if (sine.apply(x) == 0) {
            System.out.println("Cosecant: [Error] Cosecant is not defined at multiples of π.");
            return null;
        }

        result = 1/sine.apply(x);

        return result;
    }

    private double normalize(double angle) {
        return angle % (2 * Math.PI);
    }

    public Cosecant inGrad() {
        this.argInGrad = true;
        return this;
    }

    public Cosecant inRad() {
        this.argInGrad = false;
        return this;
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        if (null != sine) {
            sine.setDecimalPlaces(decimalPlaces);
        }
        super.setDecimalPlaces(decimalPlaces);
    }
}
