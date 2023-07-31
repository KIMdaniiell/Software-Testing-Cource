package main.mathFunctions.trigonometricFunctions;

import main.mathFunctions.MathFunction;

public class Sine extends MathFunction {
    private Cosine cosine;
    boolean argInGrad;

    public Sine(Cosine cosine) {
        super(cosine.getDecimalPlaces());
        this.cosine = cosine;
        this.argInGrad = false;
    }

    @Override
    public Double apply(Double x) {
        double result;

        if (null == x || x.isInfinite() || x.isNaN()) {
            System.out.println("[Warning] angle value is invalid.");
            return null;
        }

        if (argInGrad) {
            //System.out.printf("%.10f GRAD -> ",  x);
            x = Math.toRadians(x);
            //System.out.printf("%.10f PI%n", x);
        }

        /* Sine is an odd function: sin(-x) = -sin(x) */
        //isPositive = x >= 0 & Math.abs(normalize(x))<= Math.PI;
        //x = Math.abs(normalize(x));
        x = normalize(x);

        result = cosine.apply(Math.PI/2 - x);

        return result;
    }

    private double normalize(double angle) {
        return angle % (2 * Math.PI);
    }

    public Sine inGrad() {
        this.argInGrad = true;
        return this;
    }

    public Sine inRad() {
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
