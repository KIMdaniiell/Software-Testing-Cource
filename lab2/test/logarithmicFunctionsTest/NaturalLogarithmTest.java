package test.logarithmicFunctionsTest;

import main.mathFunctions.logarithmicFunctions.NaturalLogarithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class NaturalLogarithmTest {
    static NaturalLogarithm naturalLogarithm;
    final static int DECIMAL_PLACES = 10;

    @BeforeAll
    public static void initCosinePrecise() {
        naturalLogarithm = new NaturalLogarithm(DECIMAL_PLACES);
    }

    @Test
    void testOne () {
        assertEquals(0, naturalLogarithm.apply(1D));
    }

    @Test
    void testE () {
        assertEquals(Math.log(Math.E), naturalLogarithm.apply(Math.E));
    }

    @Test
    void testTwenty () {
        assertEquals(round(Math.log(20D), DECIMAL_PLACES-2), round(naturalLogarithm.apply(20D), DECIMAL_PLACES-2));
    }

    @Test
    void testZero () {
        assertEquals(null, naturalLogarithm.apply(0D));
    }

    @Test
    void testNegative () {
        assertEquals(null, naturalLogarithm.apply(-10D));
    }


    private double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }
}
