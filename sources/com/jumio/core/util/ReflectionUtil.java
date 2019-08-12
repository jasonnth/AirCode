package com.jumio.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionUtil {
    public static <T> T invokeVoidMethod(Class<?> clazz, String methodName, Object target, Object... params) {
        try {
            Method m = clazz.getDeclaredMethod(methodName, new Class[0]);
            if (!m.isAccessible()) {
                m.setAccessible(true);
            }
            return m.invoke(target, params);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T invokeMethodWithArgs(Class<?> clazz, String methodName, Class<?>[] argTypes, Object target, Object... params) {
        Method m;
        try {
            if (argTypes.length == 1) {
                m = clazz.getDeclaredMethod(methodName, new Class[]{argTypes[0]});
            } else {
                m = clazz.getDeclaredMethod(methodName, argTypes);
            }
            if (!m.isAccessible()) {
                m.setAccessible(true);
            }
            return m.invoke(target, params);
        } catch (Exception e) {
            return null;
        }
    }

    public static Method getMethod(Object target, String methodname, Class<?>... actualParamTypes) throws NoSuchMethodException {
        int i = 0;
        if (actualParamTypes == null || actualParamTypes.length == 0 || actualParamTypes[0] == null) {
            Method[] declaredMethods = target.getClass().getDeclaredMethods();
            int length = declaredMethods.length;
            while (i < length) {
                Method m = declaredMethods[i];
                if (m.getName().contains(methodname)) {
                    return m;
                }
                i++;
            }
            return null;
        }
        Method[] declaredMethods2 = target.getClass().getDeclaredMethods();
        int length2 = declaredMethods2.length;
        while (i < length2) {
            Method dcl = declaredMethods2[i];
            if (dcl.getName().equals(methodname)) {
                Class<?>[] formalParamTypes = dcl.getParameterTypes();
                if (actualParamTypes.length != formalParamTypes.length) {
                    continue;
                } else {
                    boolean match = true;
                    int i2 = 0;
                    while (true) {
                        if (i2 >= actualParamTypes.length) {
                            break;
                        } else if (!formalParamTypes[i2].isAssignableFrom(actualParamTypes[i2])) {
                            match = false;
                            break;
                        } else {
                            i2++;
                        }
                    }
                    if (match) {
                        return dcl;
                    }
                }
            }
            i++;
        }
        return target.getClass().getDeclaredMethod(methodname, actualParamTypes);
    }

    public static void setStaticMember(Class<?> targetClass, String fieldName, boolean forceAccess, Object newValue) throws NoSuchFieldException, IllegalAccessException {
        Field targetField = targetClass.getDeclaredField(fieldName);
        if (forceAccess) {
            targetField.setAccessible(true);
        }
        targetField.set(null, newValue);
    }

    public static Object getStaticMember(Class<?> targetClass, String fieldName, boolean forceAccess) throws NoSuchFieldException, IllegalAccessException {
        Field targetField = targetClass.getDeclaredField(fieldName);
        if (forceAccess) {
            targetField.setAccessible(true);
        }
        return targetField.get(null);
    }
}
