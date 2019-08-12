package com.airbnb.epoxy;

import icepick.Icepick;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

class ControllerHelperLookup {
    private static final Map<Class<?>, Constructor<?>> BINDINGS = new LinkedHashMap();
    private static final NoOpControllerHelper NO_OP_CONTROLLER_HELPER = new NoOpControllerHelper();

    static ControllerHelper getHelperForController(EpoxyController controller) {
        Constructor<?> constructor = findConstructorForClass(controller.getClass());
        if (constructor == null) {
            return NO_OP_CONTROLLER_HELPER;
        }
        try {
            return (ControllerHelper) constructor.newInstance(new Object[]{controller});
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
                throw new RuntimeException("Unable to get Epoxy helper class.", cause);
            }
        }
    }

    private static Constructor<?> findConstructorForClass(Class<?> controllerClass) {
        Constructor<?> helperCtor;
        Constructor<?> helperCtor2 = (Constructor) BINDINGS.get(controllerClass);
        if (helperCtor2 != null || BINDINGS.containsKey(controllerClass)) {
            return helperCtor2;
        }
        String clsName = controllerClass.getName();
        if (clsName.startsWith(Icepick.ANDROID_PREFIX) || clsName.startsWith(Icepick.JAVA_PREFIX)) {
            return null;
        }
        try {
            helperCtor = Class.forName(clsName + "_EpoxyHelper").getConstructor(new Class[]{controllerClass});
        } catch (ClassNotFoundException e) {
            helperCtor = findConstructorForClass(controllerClass.getSuperclass());
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Unable to find Epoxy Helper constructor for " + clsName, e2);
        }
        BINDINGS.put(controllerClass, helperCtor);
        return helperCtor;
    }
}
