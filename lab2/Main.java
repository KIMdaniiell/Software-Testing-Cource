import main.LabFunction;
import main.csvInputOutput.CSVReader;
import main.csvInputOutput.CSVWriter;
import main.mathFunctions.logarithmicFunctions.BasedLogarithm;
import main.mathFunctions.logarithmicFunctions.NaturalLogarithm;
import main.mathFunctions.trigonometricFunctions.Cosecant;
import main.mathFunctions.trigonometricFunctions.Cosine;
import main.mathFunctions.trigonometricFunctions.Secant;
import main.mathFunctions.trigonometricFunctions.Sine;

public class Main {

    private static double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }

    public static void main(String[] args) {
        int DECIMAL_PLACES = 10;
        int DECIMAL_PLACES_comp = 9;
        Cosine cos = new Cosine(5);
        Sine sin = new Sine(cos);
        Cosecant csc = new Cosecant(sin);
        Secant sec = new Secant(cos);

        NaturalLogarithm naturalLogarithm = new NaturalLogarithm(7);
        BasedLogarithm basedLogarithm = new BasedLogarithm(9);

        LabFunction labFunction = new LabFunction(10);

        double[] angles = {0, 30, 45, 60,
                90, 120, 135, 150,
                180, 210, 225, 240,
                270, 300, 315, 330,
                360, 90+360};


        CSVReader csvReader = new CSVReader("src/res/inputs/cos_in_data.csv");

        /*System.out.println("Angle values:");
        for (Double d: csvReader.readFromFile()){
            System.out.println("\t"+d);
        }*/


        // Cosine with predefined angles
        /*for (double angle: angles) {
            double result = cos.apply( Math.toRadians(angle) );
            System.out.printf("cos(%.0f) = %.5f%n", angle, result);
        }*/

        // Cosine with angle range [-200;200] and step = 0.05
        /*cos.setDataFile("cos_out_data.csv");
        for (double angle = -200; angle<=200; angle++) {
            Double result = cos.applyWithWrite(angle*0.05);
            System.out.printf("cos(%.2f) = %.8f%n", angle*0.05, result);
        }*/


        // Sine with predefined angles
        /*for (double angle: angles) {
            double result = sin.apply( Math.toRadians(angle) );
            System.out.printf("sin(%.0f) = %.5f%n", angle, result);
        }*/

        // Sine with angle range [-200;200] and step = 0.05
        /*sin.setDataFile("sin_out_data.csv");
        for (double angle = -200; angle<=200; angle++) {
            Double result = sin.applyWithWrite(angle*0.05);
            System.out.printf("%.2f; %.8f%n", angle*0.05, result);
        }*/


        // Cosecant with predefined angles
        /*for (double angle: angles) {
            Double result = csc.apply( Math.toRadians(angle) );
            System.out.printf("csc(%.0f) = %.5f%n", angle, result);
        }*/

        // Cosecant with angle range [-200;200] and step = 0.05
        /*csc.setDataFile("csc_out_data.csv");
        for (double i = -200; i<=200; i++) {
            Double result = csc.applyWithWrite(i*0.05);
            System.out.printf("%.2f; %.8f%n", i*0.05, result);
        }*/


        // Secant with predefined angles
        /*for (double angle: angles) {
            Double result = sec.apply( Math.toRadians(angle) );
            System.out.printf("sec(%.0f) = %.5f%n", angle, result);
        }*/

        // Secant with angle range [-200;200] and step = 0.05
        /*sec.setDataFile("sec_out_data.csv");
        for (double angle = -200; angle<=200; angle++) {
            Double result = sec.applyWithWrite(angle*0.05);
            System.out.printf("%.2f; %.8f%n", angle*0.05, result);
        }*/


        // Natural Logarithm with argument range [-0.1;2) and step = 0.05
        /*for (double i = -0.1; i<2; i+=0.05) {
            Double result = naturalLogarithm.apply(i);
            if (null == result) {
                System.out.printf("ln(%.2f) = %.5f\t%.5f%n", i, result, Math.log(i));
            } else {
                System.out.printf("ln(%.2f) = %.5f\t%.5f\t%.7f%n", i, result, Math.log(i), Math.log(i)-result);
            }
        }*/


        // Natural Logarithm (Base = 407) with argument range [-0.1;2) and step = 0.05
        /*for (double i = -0.1; i<2; i+=0.05) {
            Double result = basedLogarithm.base(407).apply(i);
            if (null == result) {
                System.out.printf("ln407(%.2f) = %.5f\t%.5f%n", i, result, Math.log(i)/Math.log(407));
            } else {
                System.out.printf("ln407(%.2f) = %.5f\t%.5f\t%.7f%n", i, result, Math.log(i)/Math.log(407), Math.log(i)/Math.log(407)-result);
            }
        }*/

        // Natural Logarithm (Base = 7) with argument range [-0.1;2) and step = 0.05
        /*for (double i = -0.1; i<2; i+=0.05) {
            Double result = basedLogarithm.base(7).apply(i);
            if (null == result) {
                System.out.printf("ln7(%.2f) = %.5f\t%.5f%n", i, result, Math.log(i)/Math.log(7));
            } else {
                System.out.printf("ln7(%.2f) = %.5f\t%.5f\t%.7f%n", i, result, Math.log(i)/Math.log(7), Math.log(i)/Math.log(7)-result);
            }
        }*/


//        labFunction.setDataFile("newoutdata.csv");
//        for (double i = -200; i<=200; i++) {
//            Double result = labFunction.applyWithWrite(i*0.05);
//            System.out.printf("%.2f; %.8f%n", i*0.05, result);
//        }



//        for (double angle: angles) {
//            double neg_angle = -angle;
//
//            Double result = labFunction.apply( Math.toRadians(neg_angle) );
//            if (null != result) {
//                System.out.printf("f(%.0f) = %.10f%n", neg_angle, result);
//            } else {
//                System.out.printf("f(%.0f) = ?%n", neg_angle);
//            }
//        }


    }
}
