package main.mathFunctions.logarithmicFunctions;

import main.mathFunctions.MathFunction;

public class BasedLogarithm extends MathFunction {
    private final NaturalLogarithm naturalLogarithm;
    private Double denominator;                             // Also known as 'знаменатель'

    public BasedLogarithm (int decimalPlaces) {
        super(decimalPlaces);
        this.naturalLogarithm = new NaturalLogarithm(decimalPlaces);
    }

    @Override
    public Double apply(Double x) {
        if (null == this.denominator) {
            System.out.println("[Warning] logarithm base is not initialized.");
            return null;
        }

        if (null == x || x.isNaN() || x <= 0) {
            System.out.println("[Warning] angle value is invalid.");
            return null;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }

        double numerator = naturalLogarithm.apply(x);


        return round(numerator/denominator);
    }

    public BasedLogarithm base(double base) {
        if (base == 1) {
            System.out.println("[Error] Logarithm base can not be equal 1.");
            return null;
        }

        this.denominator = naturalLogarithm.apply(base);
        return this;
    }
}
