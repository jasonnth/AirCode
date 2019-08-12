package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;

/* renamed from: bo.app.dk */
public class C0448dk {

    /* renamed from: a */
    private static final String f367a = AppboyLogger.getAppboyLogTag(C0448dk.class);

    /* renamed from: b */
    private final C0343ac f368b;

    /* renamed from: c */
    private final SharedPreferences f369c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final Object f370d = new Object();

    /* renamed from: e */
    private AtomicBoolean f371e = new AtomicBoolean(false);
    /* access modifiers changed from: private */

    /* renamed from: f */
    public C0394bw f372f;

    /* renamed from: g */
    private String f373g;

    /* renamed from: bo.app.dk$a */
    class C0450a extends AsyncTask<Void, Void, Void> {
        private C0450a() {
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            C0394bw bwVar = new C0394bw();
            bwVar.mo6864b(C0448dk.this.mo6975i());
            bwVar.mo6859a(C0448dk.this.mo6974h());
            bwVar.mo6867c(C0448dk.this.mo6976j());
            bwVar.mo6858a(C0448dk.this.mo6972f());
            bwVar.mo6860a(C0448dk.this.mo6967a());
            bwVar.mo6865b(C0448dk.this.mo6969c());
            bwVar.mo6868c(C0448dk.this.mo6968b());
            bwVar.mo6863b(C0448dk.this.mo6970d());
            bwVar.mo6857a(C0448dk.this.mo6971e());
            synchronized (C0448dk.this.f370d) {
                C0448dk.this.f372f = bwVar;
            }
            return null;
        }
    }

    public C0448dk(Context context, String str, C0343ac acVar) {
        String str2;
        if (str == null) {
            AppboyLogger.m1735e(f367a, "ServerConfigStorageProvider received null api key.");
            str2 = "";
        } else {
            str2 = "." + str;
        }
        this.f369c = context.getSharedPreferences("com.appboy.storage.serverconfigstorageprovider" + str2, 0);
        this.f373g = this.f369c.getString("last_configured_appboy_sdk_version", null);
        this.f368b = acVar;
        new C0450a().execute(new Void[0]);
    }

    /* renamed from: a */
    public void mo6965a(C0394bw bwVar) {
        synchronized (this.f370d) {
            if (bwVar.mo6861a() && !mo6967a()) {
                this.f368b.mo6736a(C0418cp.f288a, C0418cp.class);
            }
            this.f372f = bwVar;
        }
        try {
            Editor edit = this.f369c.edit();
            if (bwVar.mo6866c() != null) {
                edit.putString("blacklisted_events", new JSONArray(bwVar.mo6866c()).toString());
            }
            if (bwVar.mo6869d() != null) {
                edit.putString("blacklisted_attributes", new JSONArray(bwVar.mo6869d()).toString());
            }
            if (bwVar.mo6870e() != null) {
                edit.putString("blacklisted_purchases", new JSONArray(bwVar.mo6870e()).toString());
            }
            edit.putLong("config_time", bwVar.mo6862b());
            edit.putBoolean("location_enabled", bwVar.mo6871f());
            edit.putBoolean("location_enabled_set", bwVar.mo6872g());
            edit.putLong("location_time", bwVar.mo6873h());
            edit.putFloat("location_distance", bwVar.mo6874i());
            edit.putBoolean("piq_enabled", bwVar.mo6861a());
            edit.apply();
        } catch (Exception e) {
            AppboyLogger.m1740w(f367a, "Could not persist server config to shared preferences.", e);
        }
    }

    /* renamed from: a */
    public boolean mo6967a() {
        boolean z;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                z = this.f372f.mo6861a();
            } else {
                z = this.f369c.getBoolean("piq_enabled", false);
            }
        }
        return z;
    }

    /* renamed from: b */
    public boolean mo6968b() {
        boolean z;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                z = this.f372f.mo6871f();
            } else {
                z = this.f369c.getBoolean("location_enabled_set", false);
            }
        }
        return z;
    }

    /* renamed from: c */
    public boolean mo6969c() {
        boolean z;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                z = this.f372f.mo6871f();
            } else {
                z = this.f369c.getBoolean("location_enabled", false);
            }
        }
        return z;
    }

    /* renamed from: d */
    public long mo6970d() {
        long j;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                j = this.f372f.mo6873h();
            } else {
                j = this.f369c.getLong("location_time", -1);
            }
        }
        return j;
    }

    /* renamed from: e */
    public float mo6971e() {
        float f;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                f = this.f372f.mo6874i();
            } else {
                f = this.f369c.getFloat("location_distance", -1.0f);
            }
        }
        return f;
    }

    /* renamed from: f */
    public long mo6972f() {
        long j;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                j = this.f372f.mo6862b();
            } else {
                j = this.f369c.getLong("config_time", 0);
            }
        }
        return j;
    }

    /* renamed from: g */
    public String mo6973g() {
        return this.f373g;
    }

    /* renamed from: h */
    public Set<String> mo6974h() {
        Set<String> a;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                a = this.f372f.mo6866c();
            } else {
                a = m479a("blacklisted_events");
            }
            if (a == null) {
                a = new HashSet<>();
            }
        }
        return a;
    }

    /* renamed from: i */
    public Set<String> mo6975i() {
        Set<String> a;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                a = this.f372f.mo6869d();
            } else {
                a = m479a("blacklisted_attributes");
            }
            if (a == null) {
                a = new HashSet<>();
            }
        }
        return a;
    }

    /* renamed from: j */
    public Set<String> mo6976j() {
        Set<String> a;
        synchronized (this.f370d) {
            if (this.f372f != null) {
                a = this.f372f.mo6870e();
            } else {
                a = m479a("blacklisted_purchases");
            }
            if (a == null) {
                a = new HashSet<>();
            }
        }
        return a;
    }

    /* renamed from: k */
    public boolean mo6977k() {
        return this.f371e.get();
    }

    /* renamed from: a */
    public void mo6966a(boolean z) {
        this.f371e.set(z);
    }

    /* renamed from: l */
    public void mo6978l() {
        if (!"1.18.0".equals(this.f373g)) {
            this.f373g = "1.18.0";
            Editor edit = this.f369c.edit();
            edit.putString("last_configured_appboy_sdk_version", "1.18.0");
            edit.apply();
        }
    }

    /* renamed from: a */
    private Set<String> m479a(String str) {
        try {
            String string = this.f369c.getString(str, "");
            if (StringUtils.isNullOrBlank(string)) {
                return null;
            }
            JSONArray jSONArray = new JSONArray(string);
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray.length(); i++) {
                hashSet.add(jSONArray.getString(i));
            }
            return hashSet;
        } catch (Exception e) {
            AppboyLogger.m1740w(f367a, "Experienced exception retrieving blacklisted strings from local storage. Returning null.", e);
            return null;
        }
    }
}
