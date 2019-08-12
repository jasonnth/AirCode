package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.UUID;

/* renamed from: bo.app.av */
public class C0362av implements C0378bg {

    /* renamed from: a */
    private final Context f110a;

    public C0362av(Context context) {
        this.f110a = context;
    }

    /* renamed from: a */
    public String mo6755a() {
        SharedPreferences sharedPreferences = this.f110a.getSharedPreferences("com.appboy.device", 0);
        String string = sharedPreferences.getString("device_id", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("device_id", uuid);
        edit.apply();
        return uuid;
    }
}
