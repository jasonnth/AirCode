package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;

public class MathInternalError extends MathIllegalStateException {
    public MathInternalError() {
        getContext().addMessage(LocalizedFormats.INTERNAL_ERROR, "https://issues.apache.org/jira/browse/MATH");
    }
}
