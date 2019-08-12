package org.apache.commons.math3.analysis.polynomials;

import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class PolynomialSplineFunction {
    private final double[] knots;

    /* renamed from: n */
    private final int f6322n;
    private final PolynomialFunction[] polynomials;

    public PolynomialSplineFunction(double[] knots2, PolynomialFunction[] polynomials2) throws NullArgumentException, NumberIsTooSmallException, DimensionMismatchException, NonMonotonicSequenceException {
        if (knots2 == null || polynomials2 == null) {
            throw new NullArgumentException();
        } else if (knots2.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NOT_ENOUGH_POINTS_IN_SPLINE_PARTITION, Integer.valueOf(2), Integer.valueOf(knots2.length), false);
        } else if (knots2.length - 1 != polynomials2.length) {
            throw new DimensionMismatchException(polynomials2.length, knots2.length);
        } else {
            MathArrays.checkOrder(knots2);
            this.f6322n = knots2.length - 1;
            this.knots = new double[(this.f6322n + 1)];
            System.arraycopy(knots2, 0, this.knots, 0, this.f6322n + 1);
            this.polynomials = new PolynomialFunction[this.f6322n];
            System.arraycopy(polynomials2, 0, this.polynomials, 0, this.f6322n);
        }
    }

    public double value(double v) {
        if (v < this.knots[0] || v > this.knots[this.f6322n]) {
            throw new OutOfRangeException(Double.valueOf(v), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.f6322n]));
        }
        int i = Arrays.binarySearch(this.knots, v);
        if (i < 0) {
            i = (-i) - 2;
        }
        if (i >= this.polynomials.length) {
            i--;
        }
        return this.polynomials[i].value(v - this.knots[i]);
    }
}
