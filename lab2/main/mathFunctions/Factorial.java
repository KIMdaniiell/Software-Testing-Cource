package main.mathFunctions;

import java.util.function.UnaryOperator;

public class Factorial implements UnaryOperator<Long>{

    @Override
    public Long apply(Long l) {
        long factorial = 1;

        /* Factorial is a function defined on a set of non—negative integers. */
        if (null == l || l < 0) {
            return null;
        }

        for (long n = l; n > 0; n--) {
            factorial *= n;
        }

        return factorial;
    }

    /* Works better if dividing by smaller doubles as there is sequential division inside */
    public Double divideByFactorial (Double dividend, Long divisor) {
        Double quotient = dividend;

        /* Factorial is a function defined on a set of non—negative integers. */
        if (null == dividend || null == divisor || divisor < 0) {
            return null;
        }

        for (long n = divisor; n > 0; n--) {
            quotient /= n;
        }

        return quotient;
    }
}
