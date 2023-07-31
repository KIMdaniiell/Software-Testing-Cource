package main.mathFunctions.logarithmicFunctions;

import main.mathFunctions.MathFunction;

public class NaturalLogarithm extends MathFunction {

    public NaturalLogarithm(int decimalPlaces) {
        super(decimalPlaces);
    }


    @Override
    public Double apply(Double x) {
        double partialSum = 0;  // Also known as 'частичная сумма числового ряда'
        double term;            // Also known as 'общий член ряда'

        if (null == x || x.isNaN() || x <= 0) {
            System.out.println("[Warning] (NaturalLogarithm) argument value is invalid.");
            return null;
        }
        if (x == Double.POSITIVE_INFINITY) {
            return Double.POSITIVE_INFINITY;
        }


        term = Double.POSITIVE_INFINITY;
        for (int i = 0; Math.abs(term) > precision; i++) {
            double step = 2*i+1;
            term = Math.pow((x - 1)/(x + 1), step)/step;
            partialSum += 2 * term;
        }

        return round(partialSum);
    }
}
