package com.threatmetrix.TrustDefender;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.threatmetrix.TrustDefender.at */
class C4787at {

    /* renamed from: a */
    private static final String f4321a = C4834w.m2892a(C4787at.class);

    C4787at() {
    }

    /* renamed from: a */
    static Method m2743a(Class clazz, String methodName, Class... args) {
        if (clazz == null) {
            return null;
        }
        boolean z = false;
        try {
            return clazz.getMethod(methodName, args);
        } catch (Throwable th) {
            String str = f4321a;
            return z;
        }
    }

    /* renamed from: b */
    static Method m2746b(Class clazz, String methodName, Class... args) {
        if (clazz == null) {
            return null;
        }
        boolean z = false;
        try {
            return clazz.getDeclaredMethod(methodName, args);
        } catch (Throwable th) {
            String str = f4321a;
            return z;
        }
    }

    /* renamed from: a */
    static <T> T m2741a(Object receiverInstance, Method method, Object... args) {
        T returnValue = null;
        if (method != null) {
            boolean invokeFailed = false;
            try {
                returnValue = method.invoke(receiverInstance, args);
            } catch (Throwable th) {
                invokeFailed = true;
                String str = f4321a;
                method.getName();
            }
            if (!invokeFailed) {
                return returnValue;
            }
        }
        return null;
    }

    /* renamed from: b */
    static Class m2745b(String className) {
        Class<?> c = null;
        try {
            return Class.forName(className);
        } catch (Throwable th) {
            String str = f4321a;
            return c;
        }
    }

    /* renamed from: a */
    static Field m2742a(Class clazz, String fieldName) {
        if (clazz == null) {
            return null;
        }
        boolean z = false;
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (Throwable th) {
            String str = f4321a;
            return z;
        }
    }

    /* renamed from: a */
    static List<String> m2744a(Class clazz) {
        if (clazz == null) {
            return null;
        }
        List<String> ret = null;
        try {
            Field[] fields = clazz.getDeclaredFields();
            if (fields == null || fields.length == 0) {
                return null;
            }
            List<String> ret2 = new ArrayList<>();
            int i = 0;
            while (i < fields.length) {
                try {
                    if (fields[i] != null) {
                        ret2.add(fields[i].getName());
                    }
                    i++;
                } catch (Throwable th) {
                    ret = ret2;
                    String str = f4321a;
                    return ret;
                }
            }
            return ret2;
        } catch (Throwable th2) {
            String str2 = f4321a;
            return ret;
        }
    }

    /* renamed from: a */
    static Object m2740a(Object instance, Field field) {
        if (field == null) {
            return null;
        }
        boolean z = false;
        try {
            return field.get(instance);
        } catch (Throwable th) {
            String str = f4321a;
            field.getName();
            return z;
        }
    }

    /* renamed from: a */
    static Object m2738a(Class clazz, Class[] argsType, Object[] args) {
        Object obj = null;
        if (clazz == null) {
            return obj;
        }
        if (argsType != null && args == null) {
            return obj;
        }
        if (argsType == null && args != null) {
            return obj;
        }
        if (argsType != null && args != null && argsType.length != args.length) {
            return obj;
        }
        try {
            return clazz.getConstructor(argsType).newInstance(args);
        } catch (Throwable th) {
            String str = f4321a;
            return obj;
        }
    }

    /* renamed from: a */
    static Object m2739a(ClassLoader loader, Class<?>[] interfaces, InvocationHandler invocationHandler) {
        try {
            return Proxy.newProxyInstance(loader, interfaces, invocationHandler);
        } catch (Throwable th) {
            String str = f4321a;
            return null;
        }
    }

    /* renamed from: a */
    static Object m2737a(Class clazz, String fieldName, Object instance) {
        Field field = m2742a(clazz, fieldName);
        if (field != null) {
            return m2740a((Object) null, field);
        }
        return null;
    }
}
