package com.squareup.moshi;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

abstract class ClassFactory<T> {
    ClassFactory() {
    }

    public static <T> ClassFactory<T> get(final Class<?> rawType) {
        try {
            final Constructor<?> constructor = rawType.getDeclaredConstructor(new Class[0]);
            constructor.setAccessible(true);
            return new ClassFactory<T>() {
                public String toString() {
                    return rawType.getName();
                }
            };
        } catch (NoSuchMethodException e) {
            try {
                Class<?> unsafeClass = Class.forName("sun.misc.Unsafe");
                Field f = unsafeClass.getDeclaredField("theUnsafe");
                f.setAccessible(true);
                final Object unsafe = f.get(null);
                final Method allocateInstance = unsafeClass.getMethod("allocateInstance", new Class[]{Class.class});
                return new ClassFactory<T>() {
                    public String toString() {
                        return rawType.getName();
                    }
                };
            } catch (IllegalAccessException e2) {
                throw new AssertionError();
            } catch (ClassNotFoundException | NoSuchFieldException | NoSuchMethodException e3) {
                try {
                    Method getConstructorId = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[]{Class.class});
                    getConstructorId.setAccessible(true);
                    final int constructorId = ((Integer) getConstructorId.invoke(null, new Object[]{Object.class})).intValue();
                    final Method newInstance = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Integer.TYPE});
                    newInstance.setAccessible(true);
                    return new ClassFactory<T>() {
                        public String toString() {
                            return rawType.getName();
                        }
                    };
                } catch (IllegalAccessException e4) {
                    throw new AssertionError();
                } catch (InvocationTargetException e5) {
                    throw new RuntimeException(e5);
                } catch (NoSuchMethodException e6) {
                    try {
                        final Method newInstance2 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[]{Class.class, Class.class});
                        newInstance2.setAccessible(true);
                        return new ClassFactory<T>() {
                            public String toString() {
                                return rawType.getName();
                            }
                        };
                    } catch (Exception e7) {
                        throw new IllegalArgumentException("cannot construct instances of " + rawType.getName());
                    }
                }
            }
        }
    }
}
