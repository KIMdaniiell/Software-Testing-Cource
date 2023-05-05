package test.task_1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import main.task_1.Cosine;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CosineTest {

    @Test
    @DisplayName("Init Cosine object with precision values")
    void initCosine() {
        /* Init with zero precision value*/
        Cosine cosineZero = new Cosine(0);
        assertEquals(0, cosineZero.getDecimal_places());
        assertEquals(1.0D, cosineZero.getPrecision());


        /* Init with negative precision value*/
        Cosine cosineNegOne = new Cosine(-1);
        assertEquals(0, cosineNegOne.getDecimal_places());
        assertEquals(1.0D, cosineNegOne.getPrecision());

        Cosine cosineNegTen = new Cosine(-10);
        assertEquals(0, cosineNegTen.getDecimal_places());
        assertEquals(1.0D, cosineNegTen.getPrecision());


        /* Init with positive precision value */
        Cosine cosineOne = new Cosine(1);
        assertEquals(1, cosineOne.getDecimal_places());
        assertEquals(0.1D, cosineOne.getPrecision());

        Cosine cosineTen = new Cosine(10);
        assertEquals(10, cosineTen.getDecimal_places());
        assertEquals(0.0000000001D, cosineTen.getPrecision());
    }

    @Test
    @DisplayName("Calculate cosine in extrema point with precision value = 10")
    void calculateCosineExtremaPoint() {
        final int DECIMAL_PLACES = 10;
        Cosine cosine = new Cosine(DECIMAL_PLACES);

        /* Check center*/
        assertEquals(1, cosine.apply(0D));

        /* Check relative maxima*/
        assertEquals(1, cosine.apply(Math.toRadians(360)));
        assertEquals(1, cosine.apply(Math.toRadians(3600)));
        assertEquals(1, cosine.apply(Math.toRadians(-360)));
        assertEquals(1, cosine.apply(Math.toRadians(-3600)));

        /* Check relative minima*/
        assertEquals(-1, cosine.apply(Math.toRadians(180)));
        assertEquals(-1, cosine.apply(Math.toRadians(180 + 3600)));
        assertEquals(-1, cosine.apply(Math.toRadians(-180)));
        assertEquals(-1, cosine.apply(Math.toRadians(-180 - 3600)));

        /* Check concave upward*/
        assertEquals(0, cosine.apply(Math.toRadians(270)));
        assertEquals(0, cosine.apply(Math.toRadians(270 + 3600)));
        assertEquals(0, cosine.apply(Math.toRadians(-90)));
        assertEquals(0, cosine.apply(Math.toRadians(-90 - 3600)));

        /* Check concave downward*/
        assertEquals(0, cosine.apply(Math.toRadians(90)));
        assertEquals(0, cosine.apply(Math.toRadians(90 + 3600)));
        assertEquals(0, cosine.apply(Math.toRadians(-270)));
        assertEquals(0, cosine.apply(Math.toRadians(-270 - 3600)));
    }

    @Test
    @DisplayName("Calculate cosine in equivalence area with precision value = 10")
    void calculateCosineEquivalenceArea() {
        final int DECIMAL_PLACES = 10;
        Cosine cosine = new Cosine(DECIMAL_PLACES);

        /* Check center positive*/
        assertEquals(round(Math.pow(3, 0.5)/2, DECIMAL_PLACES), cosine.apply(Math.toRadians(30)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(45)));
        assertEquals(0.5D, cosine.apply(Math.toRadians(60)));

        /* Check center negative*/
        assertEquals(round(Math.pow(3, 0.5)/2, DECIMAL_PLACES), cosine.apply(Math.toRadians(-30)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(-45)));
        assertEquals(0.5D, cosine.apply(Math.toRadians(-60)));

        /* Check area 1*/
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(45)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(45+360)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(45+360*100)));

        /* Check area 2*/
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(135)));
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(135+360)));
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(135+360*100)));

        /* Check area 3*/
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(225)));
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(225+360)));
        assertEquals(round(-Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(225+360*100)));

        /* Check area 4*/
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(315)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(315+360)));
        assertEquals(round(Math.pow(2, -0.5), DECIMAL_PLACES), cosine.apply(Math.toRadians(315+360*100)));

        /* Check "even" property: cosine is an even function: cos(-x) = cos(x) */
        assertEquals(cosine.apply(Math.toRadians(-45)), cosine.apply(Math.toRadians(45)));
        assertEquals(cosine.apply(Math.toRadians(-135)), cosine.apply(Math.toRadians(135)));
        assertEquals(cosine.apply(Math.toRadians(-225)), cosine.apply(Math.toRadians(225)));
        assertEquals(cosine.apply(Math.toRadians(-315)), cosine.apply(Math.toRadians(315)));

    }

    @Test
    @DisplayName("Calculate cosine in with various precision values")
    void calculateCosineVaryingPrecision() {
        /* Init with zero precision value*/
        Cosine cosineZero = new Cosine(0);
        assertEquals(0, cosineZero.apply(Math.toRadians(45)));

        /* Init with negative precision value*/
        Cosine cosineNegOne = new Cosine(-1);
        assertEquals(0, cosineNegOne.apply(Math.toRadians(45)));

        Cosine cosineNegTen = new Cosine(-10);
        assertEquals(0, cosineNegTen.apply(Math.toRadians(45)));


        /* Init with positive precision value */
        Cosine cosineOne = new Cosine(1);
        assertEquals(0.7D, cosineOne.apply(Math.toRadians(45)));

        Cosine cosineHex = new Cosine(16);
        assertEquals(0.7071067812D, round(cosineHex.apply(Math.toRadians(45)), 10));
        assertEquals(0.6819983601D, round(cosineHex.apply(Math.toRadians(407)), 10));
        assertEquals(0.0D, cosineHex.apply(Math.toRadians(90)));
        assertEquals(1.0D, cosineHex.apply(Math.toRadians(0)));

    }

    private double round(double d, int precision) {
        double p = Math.pow(10, precision);
        return Math.round(d*p)/p;
    }
}