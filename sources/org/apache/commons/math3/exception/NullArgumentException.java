package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.Localizable;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class NullArgumentException extends MathIllegalArgumentException {
    public NullArgumentException() {
        this(LocalizedFormats.NULL_NOT_ALLOWED, new Object[0]);
    }

    public NullArgumentException(Localizable pattern, Object... arguments) {
        super(pattern, arguments);
    }
}
