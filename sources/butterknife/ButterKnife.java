package butterknife;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import icepick.Icepick;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ButterKnife {
    static final Map<Class<?>, Constructor<? extends Unbinder>> BINDINGS = new LinkedHashMap();
    private static boolean debug = false;

    public interface Action<T extends View> {
    }

    public interface Setter<T extends View, V> {
    }

    private ButterKnife() {
        throw new AssertionError("No instances.");
    }

    public static Unbinder bind(Activity target) {
        return createBinding(target, target.getWindow().getDecorView());
    }

    public static Unbinder bind(View target) {
        return createBinding(target, target);
    }

    public static Unbinder bind(Object target, View source) {
        return createBinding(target, source);
    }

    private static Unbinder createBinding(Object target, View source) {
        Class<?> targetClass = target.getClass();
        if (debug) {
            Log.d("ButterKnife", "Looking up binding for " + targetClass.getName());
        }
        Constructor<? extends Unbinder> constructor = findBindingConstructorForClass(targetClass);
        if (constructor == null) {
            return Unbinder.EMPTY;
        }
        try {
            return (Unbinder) constructor.newInstance(new Object[]{target, source});
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
                throw new RuntimeException("Unable to create binding instance.", cause);
            }
        }
    }

    private static Constructor<? extends Unbinder> findBindingConstructorForClass(Class<?> cls) {
        Constructor<? extends Unbinder> bindingCtor;
        Constructor<? extends Unbinder> bindingCtor2 = (Constructor) BINDINGS.get(cls);
        if (bindingCtor2 != null) {
            if (debug) {
                Log.d("ButterKnife", "HIT: Cached in binding map.");
            }
            return bindingCtor2;
        }
        String clsName = cls.getName();
        if (clsName.startsWith(Icepick.ANDROID_PREFIX) || clsName.startsWith(Icepick.JAVA_PREFIX)) {
            if (debug) {
                Log.d("ButterKnife", "MISS: Reached framework class. Abandoning search.");
            }
            return null;
        }
        try {
            bindingCtor = Class.forName(clsName + "_ViewBinding").getConstructor(new Class[]{cls, View.class});
            if (debug) {
                Log.d("ButterKnife", "HIT: Loaded binding class and constructor.");
            }
        } catch (ClassNotFoundException e) {
            if (debug) {
                Log.d("ButterKnife", "Not found. Trying superclass " + cls.getSuperclass().getName());
            }
            bindingCtor = findBindingConstructorForClass(cls.getSuperclass());
        } catch (NoSuchMethodException e2) {
            throw new RuntimeException("Unable to find binding constructor for " + clsName, e2);
        }
        BINDINGS.put(cls, bindingCtor);
        return bindingCtor;
    }

    public static <T extends View> T findById(View view, int id) {
        return view.findViewById(id);
    }

    public static <T extends View> T findById(Activity activity, int id) {
        return activity.findViewById(id);
    }
}
