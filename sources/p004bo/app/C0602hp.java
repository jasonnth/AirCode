package p004bo.app;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import java.io.IOException;

/* renamed from: bo.app.hp */
public final class C0602hp {
    /* renamed from: a */
    public static File m1069a(Context context) {
        return m1071a(context, true);
    }

    /* renamed from: a */
    public static File m1071a(Context context, boolean z) {
        String str;
        File file = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException e) {
            str = "";
        } catch (IncompatibleClassChangeError e2) {
            str = "";
        }
        if (z && "mounted".equals(str) && m1074d(context)) {
            file = m1073c(context);
        }
        if (file == null) {
            file = context.getCacheDir();
        }
        if (file != null) {
            return file;
        }
        String str2 = "/data/data/" + context.getPackageName() + "/cache/";
        C0599hn.m1064c("Can't define system cache directory! '%s' will be used.", str2);
        return new File(str2);
    }

    /* renamed from: b */
    public static File m1072b(Context context) {
        return m1070a(context, "uil-images");
    }

    /* renamed from: a */
    public static File m1070a(Context context, String str) {
        File a = m1069a(context);
        File file = new File(a, str);
        return (file.exists() || file.mkdir()) ? file : a;
    }

    /* renamed from: c */
    private static File m1073c(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), InternalLogger.EVENT_PARAM_SDK_ANDROID), "data"), context.getPackageName()), "cache");
        if (file.exists()) {
            return file;
        }
        if (!file.mkdirs()) {
            C0599hn.m1064c("Unable to create external cache directory", new Object[0]);
            return null;
        }
        try {
            new File(file, ".nomedia").createNewFile();
            return file;
        } catch (IOException e) {
            C0599hn.m1063b("Can't create \".nomedia\" file in application external cache directory", new Object[0]);
            return file;
        }
    }

    /* renamed from: d */
    private static boolean m1074d(Context context) {
        return context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
}
