package icepick;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import icepick.Injector.Object;
import icepick.Injector.View;
import java.util.LinkedHashMap;
import java.util.Map;

public class Icepick {
    public static final String ANDROID_PREFIX = "android.";
    private static final Object DEFAULT_OBJECT_INJECTOR = new Object();
    private static final View DEFAULT_VIEW_INJECTOR = new View();
    private static final Map<Class<?>, Injector> INJECTORS = new LinkedHashMap();
    public static final String JAVA_PREFIX = "java.";
    public static final String SUFFIX = "$$Icepick";
    private static final String TAG = "Icepick";
    private static boolean debug = false;

    public static void setDebug(boolean z) {
        debug = z;
    }

    private static Injector getInjector(Class<?> cls) throws IllegalAccessException, InstantiationException {
        Injector injector;
        Injector injector2 = (Injector) INJECTORS.get(cls);
        if (injector2 == null) {
            String name = cls.getName();
            if (name.startsWith(ANDROID_PREFIX) || name.startsWith(JAVA_PREFIX)) {
                if (debug) {
                    Log.d(TAG, "MISS: Reached framework class. Abandoning search.");
                }
                return null;
            }
            try {
                injector = (Injector) Class.forName(name + SUFFIX).newInstance();
                if (debug) {
                    Log.d(TAG, "HIT: Class loaded injection class.");
                }
            } catch (ClassNotFoundException e) {
                if (debug) {
                    Log.d(TAG, "Not found. Trying superclass " + cls.getSuperclass().getName());
                }
                injector = getInjector(cls.getSuperclass());
            }
            INJECTORS.put(cls, injector);
            return injector;
        } else if (!debug) {
            return injector2;
        } else {
            Log.d(TAG, "HIT: Cached in injector map.");
            return injector2;
        }
    }

    private static <T extends Injector> T safeGet(Object obj, Injector injector) {
        try {
            Injector injector2 = getInjector(obj.getClass());
            if (injector2 == null) {
                return injector;
            }
            return injector2;
        } catch (Exception e) {
            throw new RuntimeException("Unable to inject state for " + obj, e);
        }
    }

    public static <T> void saveInstanceState(T t, Bundle bundle) {
        ((Object) safeGet(t, DEFAULT_OBJECT_INJECTOR)).save(t, bundle);
    }

    public static <T> void restoreInstanceState(T t, Bundle bundle) {
        ((Object) safeGet(t, DEFAULT_OBJECT_INJECTOR)).restore(t, bundle);
    }

    public static <T extends android.view.View> Parcelable saveInstanceState(T t, Parcelable parcelable) {
        return ((View) safeGet(t, DEFAULT_VIEW_INJECTOR)).save(t, parcelable);
    }

    public static <T extends android.view.View> Parcelable restoreInstanceState(T t, Parcelable parcelable) {
        return ((View) safeGet(t, DEFAULT_VIEW_INJECTOR)).restore(t, parcelable);
    }
}
