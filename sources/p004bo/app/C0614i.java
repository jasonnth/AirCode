package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.i */
public class C0614i {

    /* renamed from: a */
    private static final String f828a = AppboyLogger.getAppboyLogTag(C0614i.class);

    /* renamed from: b */
    private final SharedPreferences f829b;

    public C0614i(Context context) {
        this.f829b = context.getSharedPreferences("com.appboy.override.configuration.cache", 0);
    }

    /* renamed from: a */
    public String mo7300a(String str, String str2) {
        return this.f829b.getString(str, str2);
    }

    /* renamed from: a */
    public int mo7299a(String str, int i) {
        return this.f829b.getInt(str, i);
    }

    /* renamed from: a */
    public boolean mo7302a(String str, boolean z) {
        return this.f829b.getBoolean(str, z);
    }

    /* renamed from: a */
    public boolean mo7301a(String str) {
        return this.f829b.contains(str);
    }
}
