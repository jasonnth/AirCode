package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.cl */
public class C0412cl {

    /* renamed from: a */
    public static boolean f281a = false;
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static final String f282b = AppboyLogger.getAppboyLogTag(C0412cl.class);
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final SharedPreferences f283c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public C0365ax f284d;

    /* renamed from: bo.app.cl$a */
    class C0414a extends AsyncTask<Void, Void, Void> {
        private C0414a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (!C0412cl.this.f283c.getBoolean("piqqueue", false) || C0412cl.f281a) {
                AppboyLogger.m1737i(C0412cl.f282b, "No piq requests queued.");
            } else if (C0412cl.this.f284d != null) {
                C0412cl.this.f284d.mo6775e();
                C0412cl.f281a = true;
            } else {
                AppboyLogger.m1737i(C0412cl.f282b, "Not calling placeIQ because Appboy manager is null.");
            }
            return null;
        }
    }

    public C0412cl(Context context, String str, C0365ax axVar) {
        String str2;
        this.f284d = axVar;
        if (str == null) {
            AppboyLogger.m1735e(f282b, "PlaceIQManager received null api key.");
            str2 = "";
        } else {
            str2 = "." + str;
        }
        this.f283c = context.getSharedPreferences("com.appboy.storage.piqqueue" + str2, 0);
        if (!f281a) {
            new C0414a().execute(new Void[0]);
        } else {
            AppboyLogger.m1737i(f282b, "Not calling piq because it has already been attempted this app run");
        }
    }

    /* renamed from: a */
    public void mo6904a() {
        AppboyLogger.m1737i(f282b, "Clearing placeIQ request.");
        Editor edit = this.f283c.edit();
        edit.remove("piqqueue");
        edit.apply();
    }

    /* renamed from: b */
    public void mo6905b() {
        AppboyLogger.m1737i(f282b, "Queuing placeIQ request.");
        Editor edit = this.f283c.edit();
        edit.putBoolean("piqqueue", true);
        edit.apply();
    }
}
