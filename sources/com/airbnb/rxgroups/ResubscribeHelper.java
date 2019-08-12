package com.airbnb.rxgroups;

import icepick.Icepick;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

class ResubscribeHelper {
    private static final Map<Class<?>, Constructor<?>> BINDINGS = new LinkedHashMap();

    static void initializeAutoTaggingAndResubscription(Object target, ObservableGroup group) {
        Class<?> cls = target.getClass();
        String clsName = cls.getName();
        while (cls != null && !clsName.startsWith(Icepick.ANDROID_PREFIX) && !clsName.startsWith(Icepick.JAVA_PREFIX)) {
            initializeAutoTaggingAndResubscriptionInTargetClassOnly(target, cls, group);
            cls = cls.getSuperclass();
            if (cls != null) {
                clsName = cls.getName();
            }
        }
    }

    static void initializeAutoTaggingAndResubscriptionInTargetClassOnly(Object target, Class<?> targetClass, ObservableGroup group) {
        Constructor<?> constructor = findConstructorForClass(targetClass, group);
        if (constructor != null) {
            invokeConstructor(constructor, target, group);
        }
    }

    private static void invokeConstructor(Constructor<?> constructor, Object target, ObservableGroup group) {
        try {
            constructor.newInstance(new Object[]{target, group});
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Unable to invoke " + constructor, e);
        } catch (InstantiationException e2) {
            throw new RuntimeException("Unable to invoke " + constructor, e2);
        } catch (InvocationTargetException e3) {
            Throwable cause = e3.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw new RuntimeException("Unable to create resubscribeAll instance.", cause);
            }
        }
    }

    private static Constructor<?> findConstructorForClass(Class<?> cls, ObservableGroup group) {
        Constructor<?> bindingCtor;
        Constructor<?> bindingCtor2 = (Constructor) BINDINGS.get(cls);
        if (bindingCtor2 != null || BINDINGS.containsKey(cls)) {
            return bindingCtor2;
        }
        String clsName = cls.getName();
        if (clsName.startsWith(Icepick.ANDROID_PREFIX) || clsName.startsWith(Icepick.JAVA_PREFIX)) {
            BINDINGS.put(cls, bindingCtor2);
            return null;
        }
        try {
            bindingCtor = Class.forName(clsName + "_ObservableResubscriber").getConstructor(new Class[]{cls, ObservableGroup.class});
        } catch (ClassNotFoundException e) {
            bindingCtor = findConstructorForClass(cls.getSuperclass(), group);
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e2);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }
}
