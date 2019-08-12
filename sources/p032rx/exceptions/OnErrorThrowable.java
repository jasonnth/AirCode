package p032rx.exceptions;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import p032rx.plugins.RxJavaPlugins;

/* renamed from: rx.exceptions.OnErrorThrowable */
public final class OnErrorThrowable extends RuntimeException {
    private final Object value;

    /* renamed from: rx.exceptions.OnErrorThrowable$OnNextValue */
    public static class OnNextValue extends RuntimeException {
        private final Object value;

        /* renamed from: rx.exceptions.OnErrorThrowable$OnNextValue$Primitives */
        static final class Primitives {
            static final Set<Class<?>> INSTANCE = create();

            private static Set<Class<?>> create() {
                Set<Class<?>> set = new HashSet<>();
                set.add(Boolean.class);
                set.add(Character.class);
                set.add(Byte.class);
                set.add(Short.class);
                set.add(Integer.class);
                set.add(Long.class);
                set.add(Float.class);
                set.add(Double.class);
                return set;
            }
        }

        public OnNextValue(Object value2) {
            Object message;
            super("OnError while emitting onNext value: " + renderValue(value2));
            if (value2 instanceof Serializable) {
                message = value2;
            } else {
                try {
                    message = String.valueOf(value2);
                } catch (Throwable ex) {
                    message = ex.getMessage();
                }
            }
            this.value = message;
        }

        public Object getValue() {
            return this.value;
        }

        static String renderValue(Object value2) {
            if (value2 == null) {
                return "null";
            }
            if (Primitives.INSTANCE.contains(value2.getClass())) {
                return value2.toString();
            }
            if (value2 instanceof String) {
                return (String) value2;
            }
            if (value2 instanceof Enum) {
                return ((Enum) value2).name();
            }
            String pluggedRendering = RxJavaPlugins.getInstance().getErrorHandler().handleOnNextValueRendering(value2);
            if (pluggedRendering != null) {
                return pluggedRendering;
            }
            return value2.getClass().getName() + ".class";
        }
    }

    public static Throwable addValueAsLastCause(Throwable e, Object value2) {
        if (e == null) {
            e = new NullPointerException();
        }
        Throwable lastCause = Exceptions.getFinalCause(e);
        if (!(lastCause instanceof OnNextValue) || ((OnNextValue) lastCause).getValue() != value2) {
            Exceptions.addCause(e, new OnNextValue(value2));
        }
        return e;
    }
}
