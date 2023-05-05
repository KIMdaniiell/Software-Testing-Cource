package main.task_1;

import java.util.function.UnaryOperator;

public class Cosine implements UnaryOperator<Double> {
    private final double precision;         // The maximum absolute error of the approximations
    private final double decimal_places;

    /* The number of decimal places that the result will be rounded to. */
    public Cosine(int decimal_places) {
        if (decimal_places < 0) {
            decimal_places = 0;
        }

        this.decimal_places = decimal_places;
        this.precision = 1/Math.pow(10D, decimal_places);
    }

    @Override
    public Double apply(Double x) {
        double partial_sum = 0;
        double term = 1;

        if (null == x) {
            return null;
        }

        /* Cosine is an even function: cos(-x) = cos(x) */
        x = Math.abs(normalize(x));
        /* Unit circle properties: cos(2PI - x) = cos(x) */
        x = x > Math.PI ? 2 * Math.PI - x : x;

        for (int i = 0; Math.abs(term) > precision; i++) {
            /* Using sequential division instead of factorial */
            // term = Math.pow(-1, i) * Math.pow(x, 2 * i) / new Factorial().apply(2 * (long)(i));
            term = new Factorial().divideByFactorial(Math.pow(-1, i) * Math.pow(x, 2 * i), 2 * (long) (i));

//            System.err.printf("%d) %.16f\n", i, term);

            partial_sum += term;
        }
        partial_sum = round(partial_sum);
        return partial_sum;
    }

    private double normalize(double angle) {
        return angle % (2 * Math.PI);
    }

    private double round(double d) {
        double p = Math.pow(10D, this.decimal_places);
        return Math.round(d*p)/p;
    }

    public double getPrecision() {
        return precision;
    }

    public double getDecimal_places() {
        return decimal_places;
    }
}