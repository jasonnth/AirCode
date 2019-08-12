package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.configuration.AppboyConfigurationProvider;

/* renamed from: bo.app.bj */
public class C0381bj implements C0380bi {

    /* renamed from: a */
    private final AppboyConfigurationProvider f180a;

    /* renamed from: b */
    private final SharedPreferences f181b;

    public C0381bj(Context context, AppboyConfigurationProvider appboyConfigurationProvider) {
        this.f180a = appboyConfigurationProvider;
        this.f181b = context.getSharedPreferences("com.appboy.push_registration", 0);
    }

    /* renamed from: a */
    public synchronized String mo6801a() {
        String str = null;
        synchronized (this) {
            if (!this.f181b.contains("version_code") || this.f180a.getVersionCode() == this.f181b.getInt("version_code", Integer.MIN_VALUE)) {
                str = this.f181b.getString("registration_id", null);
            }
        }
        return str;
    }

    /* renamed from: a */
    public synchronized void mo6802a(String str) {
        if (str == null) {
            throw new NullPointerException();
        }
        Editor edit = this.f181b.edit();
        edit.putString("registration_id", str);
        edit.putInt("version_code", this.f180a.getVersionCode());
        edit.apply();
    }

    /* renamed from: b */
    public synchronized void mo6803b() {
        Editor edit = this.f181b.edit();
        edit.putString("registration_id", "");
        edit.apply();
    }
}
