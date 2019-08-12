package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.support.StringUtils;

/* renamed from: bo.app.h */
public class C0581h {

    /* renamed from: a */
    private final SharedPreferences f786a;

    public C0581h(Context context) {
        this.f786a = context.getSharedPreferences("com.appboy.offline.storagemap", 0);
    }

    /* renamed from: a */
    public String mo7244a() {
        return this.f786a.getString("last_user", "");
    }

    /* renamed from: a */
    public void mo7245a(String str) {
        StringUtils.checkNotNullOrEmpty(str);
        Editor edit = this.f786a.edit();
        edit.putString("last_user", str);
        edit.apply();
    }

    /* renamed from: b */
    public void mo7246b(String str) {
        StringUtils.checkNotNullOrEmpty(str);
        Editor edit = this.f786a.edit();
        edit.putString("default_user", str);
        edit.putString("last_user", str);
        edit.apply();
    }
}
