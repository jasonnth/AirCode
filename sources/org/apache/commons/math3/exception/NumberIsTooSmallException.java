package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;

public class NumberIsTooSmallException extends MathIllegalNumberException {
    private final boolean boundIsAllowed;
    private final Number min;

    public NumberIsTooSmallException(Localizable specific, Number wrong, Number min2, boolean boundIsAllowed2) {
        super(specific, wrong, min2);
        this.min = min2;
        this.boundIsAllowed = boundIsAllowed2;
    }
}
