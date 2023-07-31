package test.trigonometricFunctionsTest;

import main.mathFunctions.trigonometricFunctions.Cosine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import main.mathFunctions.trigonometricFunctions.Sine;


public class SineTest {
    static Sine sine;
    final static int DECIMAL_PLACES = 10;

    @BeforeAll
    public static void initCosinePrecise() {
        sine = new Sine(new Cosine(DECIMAL_PLACES));
    }

    @ParameterizedTest
    @ValueSource( ints = {0, 360,3600, -360, -3600})
    void testRelativeMaxima (int angle) {
        assertEquals(0, sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {90, 90 + 3600, -270, -270 - 3600})
    void testConcaveDownward (int angle) {
        assertEquals(1, sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {180, 180 + 3600, -180, -180 - 3600})
    void testRelativeMinima (int angle) {
        assertEquals(0, sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {270, 270 + 3600, -90, -90 - 3600})
    void testConcaveUpward (int angle) {
        assertEquals(-1, sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {30, 180-30, 30+360, 180-30-360})
    void testAngle30 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(30)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {45, 180-45, 45+360, 180-45-360})
    void testAngle45 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(45)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {60, 180-60, 60+360, 180-60-360})
    void testAngle60 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(60)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {120, 180-120, 120+360, 180-120-360})
    void testAngle120 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(120)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {135, 180-135, 135+360, 180-135-360})
    void testAngle135 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(135)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    @ParameterizedTest
    @ValueSource( ints = {150, 180-150, 150+360, 180-150-360})
    void testAngle150 (int angle) {
        assertEquals(round(Math.sin(Math.toRadians(150)), DECIMAL_PLACES), sine.apply(Math.toRadians(angle)));
    }

    private double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }
}
