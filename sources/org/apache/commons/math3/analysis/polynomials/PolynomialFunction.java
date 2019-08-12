package org.apache.commons.math3.analysis.polynomials;

import com.facebook.appevents.AppEventsConstants;
import java.io.Serializable;
import java.util.Arrays;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class PolynomialFunction implements Serializable {
    private final double[] coefficients;

    public PolynomialFunction(double[] c) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(c);
        int n = c.length;
        if (n == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        while (n > 1 && c[n - 1] == 0.0d) {
            n--;
        }
        this.coefficients = new double[n];
        System.arraycopy(c, 0, this.coefficients, 0, n);
    }

    public double value(double x) {
        return evaluate(this.coefficients, x);
    }

    protected static double evaluate(double[] coefficients2, double argument) throws NullArgumentException, NoDataException {
        MathUtils.checkNotNull(coefficients2);
        int n = coefficients2.length;
        if (n == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        }
        double result = coefficients2[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            result = (argument * result) + coefficients2[j];
        }
        return result;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        if (this.coefficients[0] != 0.0d) {
            s.append(toString(this.coefficients[0]));
        } else if (this.coefficients.length == 1) {
            return AppEventsConstants.EVENT_PARAM_VALUE_NO;
        }
        for (int i = 1; i < this.coefficients.length; i++) {
            if (this.coefficients[i] != 0.0d) {
                if (s.length() > 0) {
                    if (this.coefficients[i] < 0.0d) {
                        s.append(" - ");
                    } else {
                        s.append(" + ");
                    }
                } else if (this.coefficients[i] < 0.0d) {
                    s.append("-");
                }
                double absAi = FastMath.abs(this.coefficients[i]);
                if (absAi - 1.0d != 0.0d) {
                    s.append(toString(absAi));
                    s.append(' ');
                }
                s.append("x");
                if (i > 1) {
                    s.append('^');
                    s.append(Integer.toString(i));
                }
            }
        }
        return s.toString();
    }

    private static String toString(double coeff) {
        String c = Double.toString(coeff);
        if (c.endsWith(".0")) {
            return c.substring(0, c.length() - 2);
        }
        return c;
    }

    public int hashCode() {
        return Arrays.hashCode(this.coefficients) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PolynomialFunction)) {
            return false;
        }
        if (!Arrays.equals(this.coefficients, ((PolynomialFunction) obj).coefficients)) {
            return false;
        }
        return true;
    }
}
