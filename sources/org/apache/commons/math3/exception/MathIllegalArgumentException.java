package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.ExceptionContext;
import org.apache.commons.math3.exception.util.Localizable;

public class MathIllegalArgumentException extends IllegalArgumentException {
    private final ExceptionContext context = new ExceptionContext(this);

    public MathIllegalArgumentException(Localizable pattern, Object... args) {
        this.context.addMessage(pattern, args);
    }

    public String getMessage() {
        return this.context.getMessage();
    }

    public String getLocalizedMessage() {
        return this.context.getLocalizedMessage();
    }
}
