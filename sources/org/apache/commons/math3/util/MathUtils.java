package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.NullArgumentException;

public final class MathUtils {
    public static void checkNotNull(Object o) throws NullArgumentException {
        if (o == null) {
            throw new NullArgumentException();
        }
    }
}
