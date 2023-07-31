package test.logarithmicFunctionsTest;

import main.mathFunctions.logarithmicFunctions.BasedLogarithm;
import main.mathFunctions.logarithmicFunctions.NaturalLogarithm;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class BasedLogarithmTest {
    static BasedLogarithm basedLogarithm;
    final static int DECIMAL_PLACES = 10;

    @BeforeAll
    public static void initCosinePrecise() {
        basedLogarithm = new BasedLogarithm(DECIMAL_PLACES).base(Math.E);
    }

    @Test
    void testEBasedOne () {
        assertEquals(0, basedLogarithm.apply(1D));
    }

    @Test
    void testEBasedE () {
        assertEquals(Math.log(Math.E), basedLogarithm.apply(Math.E));
    }

    @Test
    void testEBasedTwenty () {
        assertEquals(round(Math.log(20D), DECIMAL_PLACES-2),
                round(basedLogarithm.apply(20D), DECIMAL_PLACES-2));
    }

    @Test
    void testEBasedZero () {
        assertNull(basedLogarithm.apply(0D));
    }

    @Test
    void testEBasedHundred () {
        assertEquals(2,
                round(basedLogarithm.base(10).apply(100D), DECIMAL_PLACES-2));
    }

    private double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }
}
