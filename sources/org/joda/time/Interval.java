package org.joda.time;

import java.io.Serializable;
import org.joda.time.base.BaseInterval;

public final class Interval extends BaseInterval implements Serializable, ReadableInterval {
    public Interval(ReadableInstant readableInstant, ReadableInstant readableInstant2) {
        super(readableInstant, readableInstant2);
    }
}
