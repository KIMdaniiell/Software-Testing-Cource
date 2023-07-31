package main;

import main.mathFunctions.MathFunction;
import main.mathFunctions.trigonometricFunctions.*;
import main.mathFunctions.logarithmicFunctions.*;


public class LabFunction extends MathFunction {
    Cosine cosine;
    Sine sine;
    Cosecant cosecant;
    Secant secant;
    NaturalLogarithm naturalLogarithm;
    BasedLogarithm basedLogarithm2;
    BasedLogarithm basedLogarithm5;
    boolean showDebugInfo;


    public LabFunction(int decimalPlaces) {
        super(decimalPlaces);
        this.cosine = new Cosine(decimalPlaces);
        this.sine = new Sine(cosine);
        this.cosecant = new Cosecant(sine);
        this.secant = new Secant(cosine);
        this.naturalLogarithm = new NaturalLogarithm(decimalPlaces);
        this.basedLogarithm2 = new BasedLogarithm(decimalPlaces).base(2);
        this.basedLogarithm5 = new BasedLogarithm(decimalPlaces).base(5);
        this.showDebugInfo = false;
    }

    public LabFunction(int decimalPlaces, Cosine cos, Sine sine, Cosecant cosecant,
                       Secant secant, NaturalLogarithm naturalLogarithm,
                       BasedLogarithm basedLogarithm2, BasedLogarithm basedLogarithm5) {
        super(decimalPlaces);
        this.cosine = cos;
        this.sine = sine;
        this.cosecant = cosecant;
        this.secant = secant;
        this.naturalLogarithm = naturalLogarithm;
        this.basedLogarithm2 = basedLogarithm2;
        this.basedLogarithm5 = basedLogarithm5;
        this.showDebugInfo = false;
    }

    @Override
    public Double apply(Double x) {

        if (null == x || x.isNaN()) {
            System.err.println("LabFunction: [Error] value is undefined or is not a number.");
            System.exit(1);
            return null;
        }

        if (x <= 0) {
            return applyForNonPositive(x);
        } else {
            return applyForPositive(x);
        }
    }

    private Double applyForNonPositive(Double x) {

        if (x.isInfinite()) {
            System.out.println("LabFunction-Left: [Error] value is infinite " +
                    "but trigonometric functions are not defined at infinity points.");
            return null;
        }

        Double csc = cosecant.apply(x);
        Double sin = sine.apply(x);
        Double sec = secant.apply(x);


        if (showDebugInfo) {
            System.out.printf("Left%n");
            System.out.printf("scs = %.10f%n", csc);
            System.out.printf("sin = %.10f%n", sin);
            System.out.printf("sec = %.10f%n", sec);
        }

        if (null == csc || null == sec || null == sin) {
            System.out.println("LabFunction-Left: [Error] Unable to calculate the result " +
                    "because function is not defined at given point");
            return null;
        }

        if (showDebugInfo) {
            System.out.println();
            System.out.printf("scs*sin = %.10f%n", csc * sin);
            System.out.printf("scs*sin - sec = %.10f%n", csc * sin - sec);
            System.out.printf("(csc * sin - sec) * sec = %.10f%n", (csc * sin - sec) * sec);
        }

        // Оригинальный вид: (((((csc(x) * sin(x)) - sec(x)) * sec(x)) ^ 3) ^ 2)
        // Упрощенный вид:      ((csc(x) * sin(x) - sec(x)) * sec(x)) ^ 6
        return Math.pow((csc * sin - sec) * sec, 6);
    }

    private Double applyForPositive(Double x) {
        if (x == 1) {
            System.out.println("LabFunction-Right: [Error] Zero division! ");
            return null;
        }

        Double ln = naturalLogarithm.apply(x);
        Double log_2 = basedLogarithm2.apply(x);
        Double log_5 = basedLogarithm5.apply(x);

        if (showDebugInfo) {
            System.out.printf("Right%n");
            System.out.printf("ln = %.10f%n", ln);
            System.out.printf("log_2 = %.10f%n", log_2);
            System.out.printf("log_5 = %.10f%n", log_5);
        }

        if (null == ln || null == log_2 || null == log_5) {
            System.out.println("LabFunction-Right: [Error] ");
            return null;
        }

        if (showDebugInfo) {
            System.out.println();
            System.out.printf("log_5(x) + ln(x) = %.10f%n", log_5 + ln);
            System.out.printf("(log_5(x) + ln(x)) * log_2(x) = %.10f%n", (log_5 + ln) * log_2);
            System.out.printf("(log_5(x) + ln(x)) * log_2(x) * ln(x) = %.10f%n", (log_5 + ln) * log_2 * ln);
            System.out.printf("(log_5(x) + ln(x)) * log_2(x) * ln(x) * ln(x) = %.10f%n", (log_5 + ln) * log_2 * ln * ln);
            System.out.printf("(log_5(x) + ln(x)) * log_2(x) * ln(x) * ln(x) / ln(x) = %.10f%n", (log_5 + ln) * log_2 * ln * ln / ln);
        }

        // Оригинальный вид: (((((log_5(x) + ln(x)) * log_2(x)) * ln(x)) * ln(x)) / ln(x))
        // Упрощенный вид: (log_5(x) + ln(x)) * log_2(x) * ln(x) * ln(x) / ln(x)
        return (log_5 + ln) * log_2 * ln * ln / ln;
    }

    public LabFunction showDebug(){
        this.showDebugInfo = true;
        return this;
    }

    public LabFunction hideDebug(){
        this.showDebugInfo = false;
        return this;
    }
}
