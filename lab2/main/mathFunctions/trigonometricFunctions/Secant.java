package main.mathFunctions.trigonometricFunctions;

import main.mathFunctions.MathFunction;

public class Secant extends MathFunction {
    private Cosine cosine;
    boolean argInGrad;

    public Secant (Cosine cosine) {
        super(cosine.getDecimalPlaces());
        this.cosine = cosine;
        this.argInGrad = false;
    }

    @Override
    public Double apply(Double x) {
        double result;

        if (null == x || x.isInfinite() || x.isNaN()) {
            System.out.println("Secant: [Error] angle value is undefined or is not a number.");
            return null;
        }

        if (argInGrad) {
            //System.out.printf("%.10f GRAD -> ",  x);
            x = Math.toRadians(x);
            //System.out.printf("%.10f PI%n", x);
        }

        x = normalize(x);

        //if (Math.abs(x) == Math.PI/2 || Math.abs(x) == 3*Math.PI/2) {
        if (cosine.apply(x) == 0) {
            System.out.println("Secant: [Error] Secant is not defined at odd multiples of π/2.");
            return null;
        }

        result = 1/cosine.apply(x);

        return round(result);
    }

    private double normalize(double angle) {
        return angle % (2 * Math.PI);
    }

    public Secant inGrad() {
        this.argInGrad = true;
        return this;
    }

    public Secant inRad() {
        this.argInGrad = false;
        return this;
    }

    @Override
    public void setDecimalPlaces(int decimalPlaces) {
        if (null != cosine) {
            cosine.setDecimalPlaces(decimalPlaces);
        }
        super.setDecimalPlaces(decimalPlaces);
    }
}
