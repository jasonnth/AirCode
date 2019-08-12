package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NoDataException extends MathIllegalArgumentException {
    public NoDataException() {
        this(LocalizedFormats.NO_DATA);
    }

    public NoDataException(Localizable specific) {
        super(specific, new Object[0]);
    }
}
