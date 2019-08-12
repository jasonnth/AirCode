package p004bo.app;

import android.content.Context;
import android.content.Intent;

/* renamed from: bo.app.du */
public final class C0462du {
    /* renamed from: a */
    public static boolean m536a(Context context, Class<?> cls) {
        return context.getPackageManager().queryIntentServices(new Intent().setClass(context, cls), 65536).size() > 0;
    }
}
