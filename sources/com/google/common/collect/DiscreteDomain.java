package com.google.common.collect;

import com.google.common.primitives.Ints;
import java.io.Serializable;
import java.lang.Comparable;
import java.util.NoSuchElementException;

public abstract class DiscreteDomain<C extends Comparable> {
    final boolean supportsFastOffset;

    private static final class IntegerDomain extends DiscreteDomain<Integer> implements Serializable {
        /* access modifiers changed from: private */
        public static final IntegerDomain INSTANCE = new IntegerDomain();

        IntegerDomain() {
            super(true);
        }

        public Integer next(Integer value) {
            int i = value.intValue();
            if (i == Integer.MAX_VALUE) {
                return null;
            }
            return Integer.valueOf(i + 1);
        }

        public Integer previous(Integer value) {
            int i = value.intValue();
            if (i == Integer.MIN_VALUE) {
                return null;
            }
            return Integer.valueOf(i - 1);
        }

        /* access modifiers changed from: 0000 */
        public Integer offset(Integer origin, long distance) {
            CollectPreconditions.checkNonnegative(distance, "distance");
            return Integer.valueOf(Ints.checkedCast(origin.longValue() + distance));
        }

        public long distance(Integer start, Integer end) {
            return ((long) end.intValue()) - ((long) start.intValue());
        }

        public Integer minValue() {
            return Integer.valueOf(Integer.MIN_VALUE);
        }

        public Integer maxValue() {
            return Integer.valueOf(Integer.MAX_VALUE);
        }

        private Object readResolve() {
            return INSTANCE;
        }

        public String toString() {
            return "DiscreteDomain.integers()";
        }
    }

    public abstract long distance(C c, C c2);

    public abstract C next(C c);

    public abstract C previous(C c);

    public static DiscreteDomain<Integer> integers() {
        return IntegerDomain.INSTANCE;
    }

    private DiscreteDomain(boolean supportsFastOffset2) {
        this.supportsFastOffset = supportsFastOffset2;
    }

    /* access modifiers changed from: 0000 */
    public C offset(C origin, long distance) {
        CollectPreconditions.checkNonnegative(distance, "distance");
        for (long i = 0; i < distance; i++) {
            origin = next(origin);
        }
        return origin;
    }

    public C minValue() {
        throw new NoSuchElementException();
    }

    public C maxValue() {
        throw new NoSuchElementException();
    }
}
