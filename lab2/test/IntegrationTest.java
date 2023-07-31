package test;

import main.LabFunction;
import main.csvInputOutput.CSVReader;
import main.mathFunctions.logarithmicFunctions.BasedLogarithm;
import main.mathFunctions.logarithmicFunctions.NaturalLogarithm;
import main.mathFunctions.trigonometricFunctions.Cosecant;
import main.mathFunctions.trigonometricFunctions.Cosine;
import main.mathFunctions.trigonometricFunctions.Secant;
import main.mathFunctions.trigonometricFunctions.Sine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.mockito.Mockito;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class IntegrationTest {
    final static int PRIMARY_DECIMAL_PLACES = 10;
    final static int DECIMAL_PLACES = 5;
    /*static Cosine cosine;
    static Sine sine;
    static Secant secant;
    static Cosecant cosecant;
    static NaturalLogarithm naturalLogarithm;
    static BasedLogarithm basedLogarithm;*/

    static Cosine cosineMock;
    static Sine sineMock;
    static Secant secantMock;
    static Cosecant cosecantMock;
    static NaturalLogarithm naturalLogarithmMock;
    static BasedLogarithm based2LogarithmMock;
    static BasedLogarithm based5LogarithmMock;

    @BeforeAll
    public static void initTest() {
        cosineMock = Mockito.mock(Cosine.class);
        sineMock = Mockito.mock(Sine.class);
        secantMock = Mockito.mock(Secant.class);
        cosecantMock = Mockito.mock(Cosecant.class);
        naturalLogarithmMock = Mockito.mock(NaturalLogarithm.class);
        based2LogarithmMock = Mockito.mock(BasedLogarithm.class);
        based5LogarithmMock = Mockito.mock(BasedLogarithm.class);

        // Cosine
        LinkedList<Double> digits = new CSVReader("src/res/inputs/cos_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(cosineMock.apply(in)).thenReturn(out);
        }

        // Sine
        digits = new CSVReader("src/res/inputs/sin_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(sineMock.apply(in)).thenReturn(out);
        }

        //Secant
        digits = new CSVReader("src/res/inputs/sec_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(secantMock.apply(in)).thenReturn(out);
        }

        //Secant nulls
        digits = new CSVReader("src/res/inputs/sec_in_data_nulls.csv").readFromFile();
        for (int i = 0; i < digits.size(); i++){
            double in = digits.get(i);
            Mockito.when(secantMock.apply(in)).thenReturn(null);
        }

        //Cosecant
        digits = new CSVReader("src/res/inputs/csc_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(cosecantMock.apply(in)).thenReturn(out);
        }

        //Cosecant nulls
        digits = new CSVReader("src/res/inputs/csc_in_data_nulls.csv").readFromFile();
        for (int i = 0; i < digits.size(); i++){
            double in = digits.get(i);
            Mockito.when(cosecantMock.apply(in)).thenReturn(null);
        }

        // Natural Logarithm
        digits = new CSVReader("src/res/inputs/ln_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(naturalLogarithmMock.apply(in)).thenReturn(out);
        }

        // Natural Logarithm nulls
        digits = new CSVReader("src/res/inputs/ln_in_data_nulls.csv").readFromFile();
        for (int i = 0; i < digits.size(); i++){
            double in = digits.get(i);
            Mockito.when(naturalLogarithmMock.apply(in)).thenReturn(null);
        }

        // Based 2 Logarithm
        digits = new CSVReader("src/res/inputs/log2_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(based2LogarithmMock.apply(in)).thenReturn(out);
        }

        // Based 5 Logarithm
        digits = new CSVReader("src/res/inputs/log5_in_data.csv").readFromFile();
        for (int i = 0; i < digits.size(); i+= 2){
            double in = digits.get(i);
            double out = digits.get(i+1);
            Mockito.when(based5LogarithmMock.apply(in)).thenReturn(out);
        }
    }


    // Mock calls

    /*@ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/cos_in_data.csv")
    void testCos(double x, double expected) {
        //cosine = new Cosine(PRIMARY_DECIMAL_PLACES);
        //cosine = cosineMock;
        //assertEquals(expected, cosine.apply(Math.toRadians(x)));
        assertEquals(expected, cosineMock.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/sin_in_data.csv")
    void testSin(double x, double expected) {
        //sine = new Sine(new Cosine(PRIMARY_DECIMAL_PLACES));
//        sine = new Sine(cosineMock);
//        assertEquals(expected, sine.apply(Math.toRadians(x)));
        assertEquals(expected, sineMock.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/csc_in_data.csv")
    void testCsc(double x, double expected) {
//        cosecant = new Cosecant(new Sine(new Cosine(PRIMARY_DECIMAL_PLACES)));
//        assertEquals(expected, round(cosecant.apply(Math.toRadians(x)), DECIMAL_PLACES));
        assertEquals(
                round(expected, DECIMAL_PLACES),
                round(cosecantMock.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/csc_in_data_nulls.csv")
    void testCscNulls(double x) {
        //cosecant = new Cosecant(new Sine(new Cosine(PRIMARY_DECIMAL_PLACES)));
        //assertNull(cosecant.apply(Math.toRadians(x)));
        assertNull(cosecantMock.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/sec_in_data.csv")
    void testSec(double x, double expected) {
        //secant = new Secant(new Cosine(PRIMARY_DECIMAL_PLACES));
        //assertEquals(expected, round(secant.apply(Math.toRadians(x)), DECIMAL_PLACES));
        assertEquals(round(expected, DECIMAL_PLACES),
                round(secantMock.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/sec_in_data_nulls.csv")
    void testSecNulls(double x) {
        //secant = new Secant(new Cosine(PRIMARY_DECIMAL_PLACES));
        //assertNull(secant.apply(Math.toRadians(x)));
        assertNull(secantMock.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/ln_in_data.csv")
    void testLn(double x, double expected) {
//        naturalLogarithm = new NaturalLogarithm(PRIMARY_DECIMAL_PLACES);
//        assertEquals(expected, round(naturalLogarithm.apply(x), DECIMAL_PLACES));
        assertEquals(round(expected, DECIMAL_PLACES),
                round(naturalLogarithmMock.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/ln_in_data_nulls.csv")
    void testLnNulls(double x) {
        //naturalLogarithm = new NaturalLogarithm(PRIMARY_DECIMAL_PLACES);
        //assertNull(naturalLogarithm.apply(x));
        assertNull(naturalLogarithmMock.apply(x));
    }*/


    // Mock objects integration

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left.csv")
    void testLabFunctionLeft(double x, double expected) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left_nulls.csv")
    void testLabFunctionLeftNulls(double x) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right.csv")
    void testLabFunctionLRight(double x, double expected) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right_nulls.csv")
    void testLabFunctionRightNulls(double x) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }


    // Right part (x is non-negative) modules integration

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right.csv")
    void testLabFunctionLRightModule1(double x, double expected) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                new NaturalLogarithm(PRIMARY_DECIMAL_PLACES), based2LogarithmMock, based5LogarithmMock);
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right_nulls.csv")
    void testLabFunctionRightNullsModule1(double x) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                new NaturalLogarithm(PRIMARY_DECIMAL_PLACES), based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right.csv")
    void testLabFunctionLRightModules2(double x, double expected) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                new NaturalLogarithm(PRIMARY_DECIMAL_PLACES),
                new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(2),
                new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(5));
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_right_nulls.csv")
    void testLabFunctionRightNullsModules2(double x) {
        LabFunction labFunction = new LabFunction(DECIMAL_PLACES,
                cosineMock, sineMock, cosecantMock, secantMock,
                new NaturalLogarithm(PRIMARY_DECIMAL_PLACES),
                new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(2),
                new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(5));
        assertNull(labFunction.apply(x));
    }


    // Left part (x is negative) modules integration

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left.csv")
    void testLabFunctionLeftModule1(double x, double expected) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                new Cosine(PRIMARY_DECIMAL_PLACES), sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left_nulls.csv")
    void testLabFunctionLeftNullsModule1(double x) {
        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                new Cosine(PRIMARY_DECIMAL_PLACES), sineMock, cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left.csv")
    void testLabFunctionLeftModules2(double x, double expected) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos,sin,
                cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock).showDebug();
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left_nulls.csv")
    void testLabFunctionLeftNullsModules2(double x) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos, sin,
                cosecantMock, secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left.csv")
    void testLabFunctionLeftModules3(double x, double expected) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos,sin,csc,
                secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock).showDebug();
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left_nulls.csv")
    void testLabFunctionLeftNullsModules3(double x) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos, sin, csc,
                secantMock,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left.csv")
    void testLabFunctionLeftModules4(double x, double expected) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();
        Secant sec = new Secant(cos).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos,sin,csc,sec,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock).showDebug();
        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_left_nulls.csv")
    void testLabFunctionLeftNullsModules4(double x) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();
        Secant sec = new Secant(cos).inGrad();

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos, sin, csc, sec,
                naturalLogarithmMock, based2LogarithmMock, based5LogarithmMock);
        assertNull(labFunction.apply(x));
    }


    // All modules integration

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data.csv")
    void testLabFunctionAllModules(double x, double expected) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();
        Secant sec = new Secant(cos).inGrad();
        NaturalLogarithm ln = new NaturalLogarithm(PRIMARY_DECIMAL_PLACES);
        BasedLogarithm log_2 =new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(2);
        BasedLogarithm log_5 = new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(5);

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos,sin,csc,sec,ln, log_2, log_5);

        assertEquals(round(expected, DECIMAL_PLACES),
                round(labFunction.apply(x), DECIMAL_PLACES));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "../res/inputs/lab_func/lab_func_in_data_nulls.csv")
    void testLabFunctionAllModulesNulls(double x) {
        Cosine cos = new Cosine(PRIMARY_DECIMAL_PLACES);
        Sine sin = new Sine(cos).inGrad();
        Cosecant csc = new Cosecant(new Sine(cos)).inGrad();
        Secant sec = new Secant(cos).inGrad();
        NaturalLogarithm ln = new NaturalLogarithm(PRIMARY_DECIMAL_PLACES);
        BasedLogarithm log_2 =new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(2);
        BasedLogarithm log_5 = new BasedLogarithm(PRIMARY_DECIMAL_PLACES).base(5);

        LabFunction labFunction = new LabFunction(PRIMARY_DECIMAL_PLACES,
                cos,sin,csc,sec,ln, log_2, log_5);

        assertNull(labFunction.apply(x));
    }

    private double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }
}
