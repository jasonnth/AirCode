package p032rx.internal.util;

import p032rx.functions.Func1;

/* renamed from: rx.internal.util.UtilityFunctions */
public final class UtilityFunctions {

    /* renamed from: rx.internal.util.UtilityFunctions$AlwaysTrue */
    enum AlwaysTrue implements Func1<Object, Boolean> {
        INSTANCE;

        public Boolean call(Object o) {
            return Boolean.valueOf(true);
        }
    }

    /* renamed from: rx.internal.util.UtilityFunctions$Identity */
    enum C5498Identity implements Func1<Object, Object> {
        INSTANCE;

        public Object call(Object o) {
            return o;
        }
    }

    public static <T> Func1<? super T, Boolean> alwaysTrue() {
        return AlwaysTrue.INSTANCE;
    }

    public static <T> Func1<T, T> identity() {
        return C5498Identity.INSTANCE;
    }
}
