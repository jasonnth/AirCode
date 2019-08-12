package com.nytimes.android.external.cache;

import java.io.Serializable;

public abstract class Equivalence<T> {

    static final class Equals extends Equivalence<Object> implements Serializable {
        static final Equals INSTANCE = new Equals();

        Equals() {
        }

        /* access modifiers changed from: protected */
        public boolean doEquivalent(Object a, Object b) {
            return a.equals(b);
        }

        /* access modifiers changed from: protected */
        public int doHash(Object o) {
            return o.hashCode();
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    /* renamed from: com.nytimes.android.external.cache.Equivalence$Identity */
    static final class C4634Identity extends Equivalence<Object> implements Serializable {
        static final C4634Identity INSTANCE = new C4634Identity();

        C4634Identity() {
        }

        /* access modifiers changed from: protected */
        public boolean doEquivalent(Object a, Object b) {
            return false;
        }

        /* access modifiers changed from: protected */
        public int doHash(Object o) {
            return System.identityHashCode(o);
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    /* access modifiers changed from: protected */
    public abstract boolean doEquivalent(T t, T t2);

    /* access modifiers changed from: protected */
    public abstract int doHash(T t);

    protected Equivalence() {
    }

    public final boolean equivalent(T a, T b) {
        if (a == b) {
            return true;
        }
        if (a == null || b == null) {
            return false;
        }
        return doEquivalent(a, b);
    }

    public final int hash(T t) {
        if (t == null) {
            return 0;
        }
        return doHash(t);
    }

    public static Equivalence<Object> equals() {
        return Equals.INSTANCE;
    }

    public static Equivalence<Object> identity() {
        return C4634Identity.INSTANCE;
    }
}
