package org.apache.commons.math3.exception;

import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays.OrderDirection;

public class NonMonotonicSequenceException extends MathIllegalNumberException {
    private final OrderDirection direction;
    private final int index;
    private final Number previous;
    private final boolean strict;

    public NonMonotonicSequenceException(Number wrong, Number previous2, int index2, OrderDirection direction2, boolean strict2) {
        LocalizedFormats localizedFormats = direction2 == OrderDirection.INCREASING ? strict2 ? LocalizedFormats.NOT_STRICTLY_INCREASING_SEQUENCE : LocalizedFormats.NOT_INCREASING_SEQUENCE : strict2 ? LocalizedFormats.NOT_STRICTLY_DECREASING_SEQUENCE : LocalizedFormats.NOT_DECREASING_SEQUENCE;
        super(localizedFormats, wrong, previous2, Integer.valueOf(index2), Integer.valueOf(index2 - 1));
        this.direction = direction2;
        this.strict = strict2;
        this.index = index2;
        this.previous = previous2;
    }
}
