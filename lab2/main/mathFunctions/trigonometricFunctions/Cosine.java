package main.mathFunctions.trigonometricFunctions;

import main.mathFunctions.Factorial;
import main.mathFunctions.MathFunction;

public class Cosine extends MathFunction {
    boolean argInGrad;

    public Cosine(int decimalPlaces) {
        super(decimalPlaces);
        this.argInGrad = false;
    }

    @Override
    public Double apply(Double x) {
        double partialSum = 0;  // Also known as 'частичная сумма числового ряда'
        double term = 1;        // Also known as 'общий член ряда'

        if (null == x || x.isInfinite() || x.isNaN()) {
            System.out.println("[Warning] angle value is invalid.");
            return null;
        }

        if (argInGrad) {
            //System.out.printf("%.10f GRAD -> ",  x);
            x = Math.toRadians(x);
            //System.out.printf("%.10f PI%n", x);
        }

        /* Cosine is an even function: cos(-x) = cos(x) */
        x = Math.abs(normalize(x));

        /* Unit circle properties: cos(2PI - x) = cos(x) */
        //x = x > Math.PI ? 2 * Math.PI - x : x;

        for (int i = 0; Math.abs(term) > precision; i++) {
            /*
                Using sequential division instead of factorial.
                It reduces rounding error.
                Example of using plain factorial division:
                    term = Math.pow(-1, i) * Math.pow(x, 2 * i) / new Factorial().apply(2 * (long)(i));
            */
            term = new Factorial().divideByFactorial(Math.pow(-1, i) * Math.pow(x, 2 * i), 2 * (long) (i));
            partialSum += term;
        }

        return round(partialSum);
    }

    public Cosine inGrad() {
        this.argInGrad = true;
        return this;
    }

    public Cosine inRad() {
        this.argInGrad = false;
        return this;
    }

    private double normalize(double angle) {
        return angle % (2 * Math.PI);
    }
}