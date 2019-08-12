package com.mparticle.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.util.Log;
import com.mparticle.MParticle.Environment;
import com.mparticle.MParticle.LogLevel;

/* renamed from: com.mparticle.internal.a */
class C4603a {

    /* renamed from: k */
    public static LogLevel f3733k = LogLevel.NONE;

    /* renamed from: n */
    private static Environment f3734n = Environment.Production;

    /* renamed from: a */
    public String f3735a = null;

    /* renamed from: b */
    public String f3736b = null;

    /* renamed from: c */
    public boolean f3737c;

    /* renamed from: d */
    public int f3738d = 60;

    /* renamed from: e */
    public int f3739e = 600;

    /* renamed from: f */
    public boolean f3740f = false;

    /* renamed from: g */
    public String f3741g = null;

    /* renamed from: h */
    public boolean f3742h = false;

    /* renamed from: i */
    public boolean f3743i = false;

    /* renamed from: j */
    public int f3744j = 100;

    /* renamed from: l */
    private final Context f3745l;

    /* renamed from: m */
    private String f3746m = null;

    public C4603a(Context context, Environment environment, SharedPreferences sharedPreferences, String str, String str2) {
        this.f3745l = context;
        if (environment != null && environment != Environment.AutoDetect) {
            f3734n = environment;
        } else if (MPUtility.isAppDebuggable(context)) {
            f3734n = Environment.Development;
        } else {
            f3734n = Environment.Production;
        }
        if (MPUtility.isAppDebuggable(context)) {
            f3733k = LogLevel.DEBUG;
        }
        if (!MPUtility.isEmpty(str)) {
            this.f3735a = str;
        } else {
            this.f3735a = mo44855a("mp_key", this.f3735a);
            if (this.f3735a == null) {
                String string = sharedPreferences.getString("mp::config::apikey", this.f3735a);
                this.f3735a = string;
                if (string == null) {
                    Log.e("mParticle SDK", "Configuration issue: No API key passed to start() or configured as mp_key in resources!");
                    this.f3736b = "";
                }
            }
        }
        if (!MPUtility.isEmpty(str2)) {
            this.f3736b = str2;
        } else {
            this.f3736b = mo44855a("mp_secret", this.f3736b);
            if (this.f3736b == null) {
                String string2 = sharedPreferences.getString("mp::config::apisecret", this.f3736b);
                this.f3736b = string2;
                if (string2 == null) {
                    Log.e("mParticle SDK", "Configuration issue: No API secret passed to start() or configured as mp_secret in resources!");
                    this.f3736b = "";
                }
            }
        }
        sharedPreferences.edit().putString("mp::config::apikey", this.f3735a).putString("mp::config::apisecret", this.f3736b).apply();
        this.f3737c = mo44857a("mp_reportUncaughtExceptions", false);
        String a = mo44855a("mp_environment", (String) null);
        if (a != null) {
            if (a.toLowerCase().contains("dev")) {
                ConfigManager.log(LogLevel.WARNING, "Forcing SDK into development mode based on configuration XML key: mp_environment and value: " + a);
                f3734n = Environment.Development;
            } else if (a.toLowerCase().contains("prod")) {
                ConfigManager.log(LogLevel.WARNING, "Forcing SDK into production mode based on configuration XML key: mp_environment and value: " + a);
                f3734n = Environment.Production;
            }
            if (f3734n == Environment.Development) {
                f3733k = LogLevel.DEBUG;
            }
        }
        this.f3743i = mo44857a("mp_enableAutoTracking", false);
        this.f3738d = mo44854a("mp_sessionTimeout", 60);
    }

    /* renamed from: a */
    public void mo44856a() {
        this.f3739e = mo44854a("mp_productionUploadInterval", 600);
        this.f3740f = mo44857a("mp_enablePush", false);
        if (this.f3740f) {
            this.f3746m = mo44855a("mp_pushSenderId", (String) null);
            if (this.f3746m == null) {
                ConfigManager.log(LogLevel.ERROR, "Configuration issue: Push is enabled but no sender id is specified.");
            }
        }
        this.f3742h = mo44857a("mp_enableLicenseCheck", false);
        if (this.f3742h) {
            this.f3741g = mo44855a("mp_appLicenseKey", "");
            if (this.f3741g == null) {
                ConfigManager.log(LogLevel.ERROR, "Configuration issue: Licensing enabled but no license key specified.");
            }
        }
    }

    /* renamed from: b */
    private int m2218b(String str, String str2) {
        return this.f3745l.getResources().getIdentifier(str, str2, this.f3745l.getPackageName());
    }

    /* renamed from: a */
    public String mo44855a(String str, String str2) {
        int b = m2218b(str, "string");
        if (b != 0) {
            try {
                return this.f3745l.getResources().getString(b);
            } catch (NotFoundException e) {
                return str2;
            }
        } else if (str2 == null) {
            return str2;
        } else {
            ConfigManager.log(LogLevel.DEBUG, String.format("Configuration: No string resource for: %s, using default: %s", new Object[]{str, str2}));
            return str2;
        }
    }

    /* renamed from: a */
    public boolean mo44857a(String str, boolean z) {
        int b = m2218b(str, "bool");
        if (b == 0) {
            ConfigManager.log(LogLevel.DEBUG, String.format("Configuration: No string resource for: %s, using default: %b", new Object[]{str, Boolean.valueOf(z)}));
            return z;
        }
        try {
            return this.f3745l.getResources().getBoolean(b);
        } catch (NotFoundException e) {
            return z;
        }
    }

    /* renamed from: a */
    public int mo44854a(String str, int i) {
        int b = m2218b(str, "integer");
        if (b == 0) {
            ConfigManager.log(LogLevel.DEBUG, String.format("Configuration: No string resource for: %s, using default: %d", new Object[]{str, Integer.valueOf(i)}));
            return i;
        }
        try {
            return this.f3745l.getResources().getInteger(b);
        } catch (NotFoundException e) {
            return i;
        }
    }

    /* renamed from: b */
    public static Environment m2219b() {
        return f3734n;
    }

    /* renamed from: c */
    public String mo44858c() {
        if (MPUtility.isEmpty(this.f3746m)) {
            this.f3746m = mo44855a("mp_pushSenderId", (String) null);
        }
        return this.f3746m;
    }
}
