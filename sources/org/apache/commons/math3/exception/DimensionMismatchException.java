package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class DimensionMismatchException extends MathIllegalNumberException {
    private final int dimension;

    public DimensionMismatchException(Localizable specific, int wrong, int expected) {
        super(specific, Integer.valueOf(wrong), Integer.valueOf(expected));
        this.dimension = expected;
    }

    public DimensionMismatchException(int wrong, int expected) {
        this(LocalizedFormats.DIMENSIONS_MISMATCH_SIMPLE, wrong, expected);
    }
}
