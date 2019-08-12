package p031pl.droidsonroids.gif;

import android.content.Context;
import android.os.Build.VERSION;

/* renamed from: pl.droidsonroids.gif.LibraryLoader */
public class LibraryLoader {
    private static Context sAppContext;

    private static Context getContext() {
        if (sAppContext == null) {
            try {
                sAppContext = (Context) Class.forName("android.app.ActivityThread").getDeclaredMethod("currentApplication", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception e) {
                throw new RuntimeException("LibraryLoader not initialized. Call LibraryLoader.initialize() before using library classes.", e);
            }
        }
        return sAppContext;
    }

    static void loadLibrary(Context context, String library) {
        try {
            System.loadLibrary(library);
        } catch (UnsatisfiedLinkError e) {
            if (VERSION.SDK_INT < 9) {
                throw e;
            }
            if ("pl_droidsonroids_gif_surface".equals(library)) {
                loadLibrary(context, "pl_droidsonroids_gif");
            }
            if (context == null) {
                context = getContext();
            }
            ReLinker.loadLibrary(context, library);
        }
    }
}
