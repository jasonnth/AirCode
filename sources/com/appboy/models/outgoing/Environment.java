package com.appboy.models.outgoing;

import com.appboy.support.AppboyLogger;
import org.json.JSONException;
import org.json.JSONObject;
import p004bo.app.C0401cb;
import p004bo.app.C0448dk;
import p004bo.app.C0455dp;

public class Environment {

    /* renamed from: a */
    private static final String f2889a = AppboyLogger.getAppboyLogTag(Environment.class);

    /* renamed from: b */
    private final String f2890b;

    /* renamed from: c */
    private final int f2891c;

    /* renamed from: d */
    private final String f2892d;

    /* renamed from: e */
    private final String f2893e;

    /* renamed from: f */
    private C0448dk f2894f;

    /* renamed from: g */
    private final Object f2895g;

    public Environment(String appboySdkVersion, int versionCode, String versionName, String packageName, C0448dk serverConfigStorageProvider) {
        this(appboySdkVersion, versionCode, versionName, packageName);
        this.f2894f = serverConfigStorageProvider;
    }

    public Environment(String appboySdkVersion, int versionCode, String versionName, String packageName) {
        this.f2895g = new Object();
        this.f2890b = appboySdkVersion;
        this.f2891c = versionCode;
        this.f2892d = versionName;
        this.f2893e = packageName;
    }

    public JSONObject forStatelessJsonPut() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sdk_version", this.f2890b);
            jSONObject.put("now", C0455dp.m522b());
            jSONObject.put("version_code", this.f2891c);
            jSONObject.put("version_name", this.f2892d);
            jSONObject.put("package_name", this.f2893e);
            jSONObject.put("no_acks", true);
        } catch (JSONException e) {
            AppboyLogger.m1734d(f2889a, "Caught exception creating environment Json.", e);
        }
        return jSONObject;
    }

    public C0401cb dispatch() {
        C0401cb cbVar;
        synchronized (this.f2895g) {
            String str = null;
            if (this.f2894f != null && this.f2894f.mo6977k()) {
                str = String.valueOf(this.f2894f.mo6972f());
                if (!"1.18.0".equals(this.f2894f.mo6973g())) {
                    str = String.valueOf(0);
                    this.f2894f.mo6978l();
                }
                this.f2894f.mo6966a(false);
            }
            cbVar = new C0401cb(this.f2890b, this.f2891c, this.f2892d, this.f2893e, str);
        }
        return cbVar;
    }
}
