package main.mathFunctions;

import main.csvInputOutput.CSVWriter;

import java.util.function.UnaryOperator;

public abstract class MathFunction implements UnaryOperator<Double> {
    protected double precision;        // The maximum absolute error of the approximations.
    protected int decimalPlaces;       // The number of decimal places that the result will be rounded to.
    protected CSVWriter csvWriter;

    public MathFunction(int decimalPlaces)  {
        setDecimalPlaces(decimalPlaces);
    }

    public Double applyWithWrite (Double x) {
        Double result = this.apply(x);
        if (null != result) {
            writeToFile(x, result);
        }
        return result;
    }

    /* Rounds double-digit to the specified number of decimal digits (decimalPlaces) */
    public double round (double digit) {
        double p = Math.pow(10D, this.decimalPlaces);
        return Math.round(digit*p)/p;
    }

    public void setDecimalPlaces(int decimalPlaces) {
        if (decimalPlaces < 0) {
            decimalPlaces = 0;
            System.err.println("[Warning] decimalPlaces value can't be negative. decimalPlaces initialized with 0");
        }

        this.decimalPlaces = decimalPlaces;
        this.precision = 1/Math.pow(10D, decimalPlaces);
    }

    public double getPrecision() {
        return precision;
    }

    public int getDecimalPlaces() {
        return decimalPlaces;
    }

    public void setDataFile (String path){
        this.csvWriter = new CSVWriter(path);
    }

    public void writeToFile (Double digit, Double result){
        csvWriter.writeToFile(digit + "; "+ result);
    }
}

