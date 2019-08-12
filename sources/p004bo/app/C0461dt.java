package p004bo.app;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: bo.app.dt */
public final class C0461dt {
    /* renamed from: a */
    public static Method m534a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Method m535a(String str, String str2, Class<?>... clsArr) {
        try {
            return m534a(Class.forName(str), str2, clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static Object m533a(Object obj, Method method, Object... objArr) {
        boolean z = false;
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            return z;
        }
    }
}
