package com.mparticle.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import com.airbnb.android.core.analytics.MParticleAnalytics;
import com.mparticle.MPEvent;
import com.mparticle.MParticle;
import com.mparticle.MParticle.Environment;
import com.mparticle.MParticle.InstallType;
import com.mparticle.MParticle.LogLevel;
import com.mparticle.UserAttributeListener;
import com.mparticle.commerce.CommerceEvent;
import com.mparticle.internal.C4609g.C4611a;
import com.mparticle.internal.PushRegistrationHelper.PushRegistration;
import com.mparticle.messaging.CloudAction;
import com.mparticle.messaging.MPCloudNotificationMessage;
import com.mparticle.messaging.ProviderCloudMessage;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageManager implements ReportingManager, C4621n {

    /* renamed from: c */
    private static Context f3697c = null;

    /* renamed from: d */
    private static SharedPreferences f3698d = null;

    /* renamed from: h */
    private static final HandlerThread f3699h = new HandlerThread("mParticleMessageHandler", 10);

    /* renamed from: i */
    private static final HandlerThread f3700i = new HandlerThread("mParticleUploadHandler", 10);

    /* renamed from: l */
    private static BroadcastReceiver f3701l;

    /* renamed from: m */
    private static String f3702m = "offline";
    /* access modifiers changed from: private */

    /* renamed from: n */
    public static double f3703n;

    /* renamed from: o */
    private static long f3704o = MPUtility.millitime();

    /* renamed from: p */
    private static TelephonyManager f3705p;

    /* renamed from: a */
    public C4627r f3706a;

    /* renamed from: b */
    InstallType f3707b = InstallType.AutoDetect;

    /* renamed from: e */
    private final C4604b f3708e = new C4604b();

    /* renamed from: f */
    private AppStateManager f3709f;

    /* renamed from: g */
    private ConfigManager f3710g = null;

    /* renamed from: j */
    private C4620m f3711j;

    /* renamed from: k */
    private Location f3712k;

    /* renamed from: q */
    private boolean f3713q = true;

    /* renamed from: com.mparticle.internal.MessageManager$a */
    public class C4597a {

        /* renamed from: a */
        public final long f3718a;

        /* renamed from: b */
        public final long f3719b;

        public C4597a(long j, long j2) {
            this.f3718a = j;
            this.f3719b = j2;
        }
    }

    /* renamed from: com.mparticle.internal.MessageManager$b */
    private class C4598b extends BroadcastReceiver {
        private C4598b() {
        }

        public void onReceive(Context appContext, Intent intent) {
            try {
                if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                    MessageManager.this.mo44803a(((ConnectivityManager) appContext.getSystemService("connectivity")).getActiveNetworkInfo());
                } else if ("android.intent.action.BATTERY_CHANGED".equals(intent.getAction())) {
                    MessageManager.f3703n = ((double) intent.getIntExtra("level", -1)) / ((double) intent.getIntExtra("scale", -1));
                }
            } catch (Exception e) {
            }
        }
    }

    /* renamed from: com.mparticle.internal.MessageManager$c */
    static class C4599c {

        /* renamed from: a */
        String f3722a;

        /* renamed from: b */
        long f3723b;

        C4599c() {
        }
    }

    /* renamed from: com.mparticle.internal.MessageManager$d */
    static class C4600d {

        /* renamed from: a */
        Map<String, String> f3724a;

        /* renamed from: b */
        Map<String, List<String>> f3725b;

        /* renamed from: c */
        long f3726c;

        C4600d() {
        }
    }

    static {
        f3699h.start();
        f3700i.start();
    }

    public MessageManager() {
    }

    public MessageManager(Context appContext, ConfigManager configManager, InstallType installType, AppStateManager appStateManager) {
        f3697c = appContext.getApplicationContext();
        this.f3710g = configManager;
        this.f3709f = appStateManager;
        this.f3709f.setMessageManager(this);
        C4617j jVar = new C4617j(appContext);
        this.f3711j = new C4620m(f3699h.getLooper(), this, jVar, appContext);
        this.f3706a = new C4627r(appContext, f3700i.getLooper(), configManager, jVar, appStateManager, this);
        f3698d = appContext.getSharedPreferences(MParticleAnalytics.M_PARTICLE_SHARED_PREFERENCES, 0);
        this.f3707b = installType;
    }

    /* renamed from: t */
    private static TelephonyManager m2158t() {
        if (f3705p == null) {
            f3705p = (TelephonyManager) f3697c.getSystemService("phone");
        }
        return f3705p;
    }

    /* renamed from: a */
    public static JSONObject m2153a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (!MParticle.isDevicePerformanceMetricsDisabled()) {
            jSONObject.put("cpu", MPUtility.getCpuUsage());
            jSONObject.put("fds", MPUtility.getAvailableInternalDisk());
            jSONObject.put("efds", MPUtility.getAvailableExternalDisk());
            Runtime runtime = Runtime.getRuntime();
            jSONObject.put("amt", runtime.totalMemory());
            jSONObject.put("ama", runtime.freeMemory());
            jSONObject.put("amm", runtime.maxMemory());
        }
        jSONObject.put("sma", MPUtility.getAvailableMemory(f3697c));
        jSONObject.put("tsm", m2154b());
        jSONObject.put("bl", f3703n);
        jSONObject.put("tss", MPUtility.millitime() - f3704o);
        String gpsEnabled = MPUtility.getGpsEnabled(f3697c);
        if (gpsEnabled != null) {
            jSONObject.put("gps", Boolean.parseBoolean(gpsEnabled));
        }
        jSONObject.put("dct", f3702m);
        int orientation = MPUtility.getOrientation(f3697c);
        jSONObject.put("so", orientation);
        jSONObject.put("sbo", orientation);
        jSONObject.put("sml", MPUtility.isSystemMemoryLow(f3697c));
        jSONObject.put("smt", m2155c());
        jSONObject.put("ant", m2158t().getNetworkType());
        return jSONObject;
    }

    /* renamed from: b */
    public static long m2154b() {
        long j = f3698d.getLong("mp::totalmem", -1);
        if (j >= 0) {
            return j;
        }
        long totalMemory = MPUtility.getTotalMemory(f3697c);
        Editor edit = f3698d.edit();
        edit.putLong("mp::totalmem", totalMemory);
        edit.apply();
        return totalMemory;
    }

    /* renamed from: c */
    public static long m2155c() {
        long j = f3698d.getLong("mp::memthreshold", -1);
        if (j >= 0) {
            return j;
        }
        long systemMemoryThreshold = MPUtility.getSystemMemoryThreshold(f3697c);
        Editor edit = f3698d.edit();
        edit.putLong("mp::memthreshold", systemMemoryThreshold);
        edit.apply();
        return systemMemoryThreshold;
    }

    /* renamed from: d */
    public C4609g mo44817d() throws JSONException {
        return new C4611a("fr", this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3729c).mo44883b(f3702m).mo44882a();
    }

    /* renamed from: e */
    public C4609g mo44819e() {
        try {
            C4609g a = new C4611a("ss", this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3729c).mo44882a();
            Editor edit = f3698d.edit();
            long j = f3698d.getLong("mp::time_in_fg", 0);
            if (j > 0) {
                a.put("psl", j / 1000);
                edit.remove("mp::time_in_fg");
            }
            String string = f3698d.getString("mp::session::previous_id", "");
            edit.putString("mp::session::previous_id", this.f3709f.getSession().f3728b);
            if (!MPUtility.isEmpty(string)) {
                a.put("pid", string);
            }
            long j2 = f3698d.getLong("mp::session::previous_start", -1);
            edit.putLong("mp::session::previous_start", this.f3709f.getSession().f3729c);
            if (j2 > 0) {
                a.put("pss", j2);
            }
            edit.apply();
            this.f3713q = !f3698d.contains(new StringBuilder().append("mp::firstrun::").append(this.f3710g.getApiKey()).toString());
            if (this.f3713q) {
                f3698d.edit().putBoolean("mp::firstrun::" + this.f3710g.getApiKey(), false).apply();
                try {
                    this.f3711j.sendMessage(this.f3711j.obtainMessage(0, mo44817d()));
                } catch (JSONException e) {
                    ConfigManager.log(LogLevel.WARNING, "Failed to create First Run Message");
                }
            } else {
                this.f3711j.sendEmptyMessage(4);
            }
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
            mo44820f();
            return a;
        } catch (JSONException e2) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle start session message");
            return null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public void mo44820f() {
        int g = mo44821g() + 1;
        if (g >= 21474836) {
            g = 0;
        }
        f3698d.edit().putInt("mp::breadcrumbs::sessioncount", g).apply();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public int mo44821g() {
        return f3698d.getInt("mp::breadcrumbs::sessioncount", 0);
    }

    /* renamed from: a */
    public void mo44804a(Session session) {
        try {
            Editor edit = f3698d.edit();
            edit.putLong("mp::time_in_fg", session.mo44853f());
            edit.apply();
            this.f3711j.sendMessage(this.f3711j.obtainMessage(2, session));
        } catch (Exception e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to send update session end message");
        }
    }

    /* renamed from: b */
    public void mo44813b(Session session) {
        mo44804a(session);
        this.f3711j.sendMessage(this.f3711j.obtainMessage(3, 1, 1, session.f3728b));
    }

    /* renamed from: a */
    public C4609g mo44789a(MPEvent mPEvent, String str) {
        if (mPEvent != null) {
            try {
                C4609g a = new C4611a("e", this.f3709f.getSession(), this.f3712k).mo44879a(mPEvent.getEventName()).mo44877a(this.f3709f.getSession().f3730d).mo44878a(mPEvent.getLength()).mo44880a(mPEvent.getCustomFlags()).mo44881a(MPUtility.enforceAttributeConstraints(mPEvent.getInfo())).mo44882a();
                a.put("et", mPEvent.getEventType());
                a.put("est", a.mo44871a());
                if (str != null) {
                    a.put("cn", str);
                }
                int i = f3698d.getInt("mp::events::counter", 0);
                a.put("en", i);
                f3698d.edit().putInt("mp::events::counter", i + 1).apply();
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle log event message");
            }
        }
        return null;
    }

    /* renamed from: a */
    public C4609g mo44791a(CommerceEvent commerceEvent) {
        if (commerceEvent != null) {
            try {
                C4609g a = new C4611a(commerceEvent, this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3730d).mo44882a();
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle log event message");
            }
        }
        return null;
    }

    /* renamed from: h */
    static void m2157h() {
        f3698d.edit().putInt("mp::events::counter", 0).apply();
    }

    /* renamed from: a */
    public C4609g mo44790a(MPEvent mPEvent, boolean z) {
        if (!(mPEvent == null || mPEvent.getEventName() == null)) {
            try {
                C4609g a = new C4611a("v", this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3730d).mo44879a(mPEvent.getEventName()).mo44880a(mPEvent.getCustomFlags()).mo44881a(MPUtility.enforceAttributeConstraints(mPEvent.getInfo())).mo44882a();
                a.put("est", this.f3709f.getSession().f3730d);
                a.put("el", 0);
                a.put("t", z ? "activity_started" : "activity_stopped");
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle log event message");
            }
        }
        return null;
    }

    /* renamed from: a */
    public C4609g mo44792a(String str) {
        if (str != null) {
            try {
                C4609g a = new C4611a("bc", this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3730d).mo44882a();
                a.put("est", this.f3709f.getSession().f3730d);
                a.put("sn", mo44821g());
                a.put("l", str);
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                this.f3711j.sendMessage(this.f3711j.obtainMessage(5, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle breadcrumb message");
            }
        }
        return null;
    }

    /* renamed from: a */
    public C4609g mo44788a(long j, boolean z) {
        try {
            C4609g a = new C4611a("o", this.f3709f.getSession(), this.f3712k).mo44877a(j).mo44882a();
            a.put("s", z);
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
            return a;
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle opt out message");
            return null;
        }
    }

    /* renamed from: a */
    public C4609g mo44796a(String str, Throwable th, JSONObject jSONObject) {
        return mo44797a(str, th, jSONObject, true);
    }

    /* renamed from: a */
    public C4609g mo44797a(String str, Throwable th, JSONObject jSONObject, boolean z) {
        try {
            C4609g a = new C4611a("x", this.f3709f.getSession(), this.f3712k).mo44877a(this.f3709f.getSession().f3730d).mo44881a(jSONObject).mo44882a();
            if (th != null) {
                a.put("m", th.getMessage());
                a.put("s", z ? "error" : "fatal");
                a.put("c", th.getClass().getCanonicalName());
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                a.put("st", stringWriter.toString());
                a.put("eh", String.valueOf(z));
                a.put("sn", mo44821g());
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } else if (str == null) {
                return a;
            } else {
                a.put("s", "error");
                a.put("m", str);
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            }
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle error message");
            return null;
        }
    }

    /* renamed from: a */
    public C4609g mo44787a(long j, String str, String str2, long j2, long j3, long j4, String str3) {
        if (!MPUtility.isEmpty(str2) && !MPUtility.isEmpty(str)) {
            try {
                C4609g a = new C4611a("npe", this.f3709f.getSession(), this.f3712k).mo44877a(j).mo44882a();
                a.put("v", str);
                a.put("url", str2);
                a.put("te", j2);
                a.put("bo", j3);
                a.put("bi", j4);
                if (str3 != null) {
                    a.put("d", str3);
                }
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle network performance message");
            }
        }
        return null;
    }

    /* renamed from: a */
    public C4609g mo44798a(String str, boolean z) {
        if (!MPUtility.isEmpty(str)) {
            try {
                C4609g a = new C4611a("pr", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44882a();
                a.put("to", str);
                a.put("tot", "google");
                a.put("r", z);
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle push registration message");
            }
        }
        return null;
    }

    /* renamed from: i */
    public void mo44822i() {
        Session session = this.f3709f.getSession();
        if (session.f3731e != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("sid", this.f3709f.getSession().f3728b);
                jSONObject.put("attrs", session.f3731e);
                this.f3711j.sendMessage(this.f3711j.obtainMessage(1, jSONObject));
            } catch (JSONException e) {
                ConfigManager.log(LogLevel.WARNING, "Failed to send update session attributes message");
            }
        }
    }

    /* renamed from: j */
    public void mo44823j() {
        this.f3706a.removeMessages(1);
        this.f3706a.sendEmptyMessageDelayed(1, 10000);
    }

    /* renamed from: k */
    public void mo44824k() {
        this.f3706a.sendMessage(this.f3706a.obtainMessage(1, 1, 0));
    }

    /* renamed from: a */
    public void mo44802a(Location location) {
        this.f3712k = location;
        ConfigManager.log(LogLevel.DEBUG, "Received location update: " + location);
    }

    /* renamed from: a */
    public C4609g mo44795a(String str, String str2, String str3, String str4, String str5, long j, long j2, int i) {
        int i2;
        if (!MPUtility.isEmpty(str)) {
            try {
                C4609g a = new C4611a("ast", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44882a();
                a.put("t", str);
                if (str2 != null) {
                    a.put("cn", str2);
                }
                boolean z = f3698d.getBoolean("mp::crashed_in_foreground", false);
                if (str.equals("app_init") || str.equals("app_fore")) {
                    f3698d.edit().putBoolean("mp::crashed_in_foreground", true).apply();
                    a.put("lr", str3);
                    a.put("lpr", str4);
                    a.put("srp", str5);
                    if (j > 0) {
                        a.put("pft", j);
                    }
                    if (j2 > 0) {
                        a.put("tls", j2);
                    }
                    if (i >= 0) {
                        a.put("nsi", i);
                    }
                    this.f3711j.sendMessage(this.f3711j.obtainMessage(7, new C4597a(a.mo44871a(), this.f3710g.getInfluenceOpenTimeoutMillis())));
                }
                if (str.equals("app_init")) {
                    Editor edit = f3698d.edit();
                    if (!this.f3713q) {
                        a.put("sc", z);
                    }
                    int i3 = 0;
                    try {
                        i2 = f3697c.getPackageManager().getPackageInfo(f3697c.getPackageName(), 0).versionCode;
                    } catch (NameNotFoundException e) {
                        i2 = i3;
                    }
                    boolean z2 = i2 != f3698d.getInt("mp::initupgrade", i2);
                    edit.putInt("mp::initupgrade", i2).apply();
                    if (!z2) {
                        if (this.f3707b == InstallType.KnownUpgrade) {
                            z2 = true;
                        } else if (this.f3707b == InstallType.KnownInstall) {
                            z2 = false;
                        }
                    }
                    a.put("ifr", this.f3713q);
                    a.put("iu", z2);
                }
                if (str.equals("app_back")) {
                    f3698d.edit().putBoolean("mp::crashed_in_foreground", false).apply();
                    this.f3711j.sendMessage(this.f3711j.obtainMessage(8, Long.valueOf(a.mo44871a())));
                }
                this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
                return a;
            } catch (JSONException e2) {
                ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle state transition message");
            }
        }
        return null;
    }

    /* renamed from: a */
    public void mo44807a(ProviderCloudMessage providerCloudMessage, String str) {
        try {
            C4609g a = new C4611a("pm", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44879a("gcm").mo44882a();
            a.put("pay", providerCloudMessage.getRedactedJsonPayload().toString());
            a.put("t", "received");
            PushRegistration latestPushRegistration = PushRegistrationHelper.getLatestPushRegistration(f3697c);
            if (!(latestPushRegistration == null || latestPushRegistration.instanceId == null || latestPushRegistration.instanceId.length() <= 0)) {
                a.put("to", latestPushRegistration.instanceId);
            }
            a.put("as", str);
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
        } catch (JSONException e) {
        }
    }

    /* renamed from: a */
    public void mo44801a(int i, String str, CloudAction cloudAction, String str2, int i2) {
        try {
            C4609g a = new C4611a("pm", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44879a("gcm").mo44882a();
            a.put("pay", str);
            a.put("bhv", i2);
            a.put("content_id", i);
            if (cloudAction == null || cloudAction.getActionIdInt() == i) {
                a.put("t", "received");
            } else {
                a.put("t", "action");
                a.put("aid", cloudAction.getActionIdentifier());
                String title = cloudAction.getTitle();
                if (MPUtility.isEmpty(title)) {
                    title = cloudAction.getActionIdentifier();
                }
                a.put("an", title);
            }
            PushRegistration latestPushRegistration = PushRegistrationHelper.getLatestPushRegistration(f3697c);
            if (!(latestPushRegistration == null || latestPushRegistration.instanceId == null || latestPushRegistration.instanceId.length() <= 0)) {
                a.put("to", latestPushRegistration.instanceId);
            }
            a.put("as", str2);
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
        } catch (JSONException e) {
        }
    }

    /* renamed from: b */
    public void mo44815b(String str) {
        try {
            C4609g a = new C4611a("pro", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44882a();
            a.put("t", str);
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle log event message");
        }
    }

    /* renamed from: a */
    public C4609g mo44793a(String str, long j, long j2, long j3, JSONObject jSONObject) throws JSONException {
        int i = f3698d.getInt("mp::events::counter", 0);
        m2157h();
        Session session = new Session();
        session.f3728b = str;
        session.f3729c = j;
        C4609g a = new C4611a("se", session, this.f3712k).mo44877a(j2).mo44881a(jSONObject).mo44882a();
        a.put("en", i);
        a.put("sl", j3);
        a.put("slx", j2 - j);
        a.put("cs", m2153a());
        return a;
    }

    /* JADX INFO: finally extract failed */
    /* renamed from: a */
    public C4609g mo44799a(JSONObject jSONObject, JSONObject jSONObject2, JSONArray jSONArray) {
        try {
            C4609g a = new C4611a("uic", this.f3709f.getSession(), this.f3712k).mo44877a(System.currentTimeMillis()).mo44882a();
            if (jSONObject != null) {
                a.put("ni", jSONObject);
            } else {
                a.put("ni", JSONObject.NULL);
            }
            if (jSONObject2 != null) {
                a.put("oi", jSONObject2);
            } else {
                a.put("oi", JSONObject.NULL);
            }
            a.put("ui", jSONArray);
            this.f3711j.sendMessage(this.f3711j.obtainMessage(0, a));
            JSONArray b = mo44812b(jSONArray);
            if (b != null) {
                mo44810a(b);
            }
            mo44810a(jSONArray);
            return a;
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle user-identity-change message");
            mo44810a(jSONArray);
            return null;
        } catch (Throwable th) {
            mo44810a(jSONArray);
            throw th;
        }
    }

    /* renamed from: a */
    public C4609g mo44794a(String str, Object obj, Object obj2, boolean z, boolean z2, long j) {
        try {
            C4609g a = new C4611a("uac", this.f3709f.getSession(), this.f3712k).mo44877a(j).mo44882a();
            a.put("n", str);
            if (obj == 0 || z) {
                obj = JSONObject.NULL;
            } else if (obj instanceof List) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < ((List) obj).size(); i++) {
                    jSONArray.put((String) ((List) obj).get(i));
                }
                obj = jSONArray;
            }
            a.put("nv", obj);
            if (obj2 == 0) {
                obj2 = JSONObject.NULL;
            } else if (obj2 instanceof List) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i2 = 0; i2 < ((List) obj2).size(); i2++) {
                    jSONArray2.put((String) ((List) obj2).get(i2));
                }
                obj2 = jSONArray2;
            }
            a.put("ov", obj2);
            a.put("d", z);
            a.put("na", z2);
            a.put("ua", C4627r.m2344a());
            this.f3711j.handleMessage(this.f3711j.obtainMessage(0, a));
            return a;
        } catch (JSONException e) {
            ConfigManager.log(LogLevel.WARNING, "Failed to create mParticle user-attribute-change message");
            return null;
        }
    }

    /* renamed from: l */
    public String mo44825l() {
        return this.f3710g.getApiKey();
    }

    /* renamed from: m */
    public void mo44828m() {
        try {
            if (f3701l == null) {
                Intent registerReceiver = f3697c.getApplicationContext().registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
                f3703n = ((double) registerReceiver.getIntExtra("level", -1)) / ((double) registerReceiver.getIntExtra("scale", -1));
                f3701l = new C4598b();
                IntentFilter intentFilter = new IntentFilter("android.intent.action.BATTERY_CHANGED");
                if (MPUtility.checkPermission(f3697c, "android.permission.ACCESS_NETWORK_STATE")) {
                    mo44803a(((ConnectivityManager) f3697c.getSystemService("connectivity")).getActiveNetworkInfo());
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                }
                f3697c.registerReceiver(f3701l, intentFilter);
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: n */
    public void mo44829n() {
        this.f3706a.removeMessages(1);
        MParticle.getInstance().upload();
    }

    /* renamed from: a */
    public void mo44805a(C4609g gVar) {
        if (this.f3710g.shouldTrigger(gVar)) {
            this.f3706a.removeMessages(5);
            this.f3706a.sendMessageDelayed(this.f3706a.obtainMessage(5, 1, 0), 5000);
        }
    }

    /* renamed from: o */
    public void mo44830o() {
        this.f3706a.sendEmptyMessage(4);
    }

    /* renamed from: p */
    public void mo44831p() {
        this.f3706a.sendEmptyMessageDelayed(6, 20000);
    }

    /* renamed from: a */
    public void mo44806a(MPCloudNotificationMessage mPCloudNotificationMessage, String str) {
        Message obtainMessage = this.f3711j.obtainMessage(6, mPCloudNotificationMessage);
        Bundle bundle = new Bundle();
        bundle.putString("appstate", str);
        obtainMessage.setData(bundle);
        this.f3711j.sendMessage(obtainMessage);
    }

    /* renamed from: b */
    public void mo44814b(ProviderCloudMessage providerCloudMessage, String str) {
        Message obtainMessage = this.f3711j.obtainMessage(6, providerCloudMessage);
        Bundle bundle = new Bundle();
        bundle.putString("appstate", str);
        obtainMessage.setData(bundle);
        this.f3711j.sendMessage(obtainMessage);
    }

    public void log(JsonReportingMessage reportingMessage) {
        if (reportingMessage != null) {
            ArrayList arrayList = new ArrayList(1);
            arrayList.add(reportingMessage);
            logAll(arrayList);
        }
    }

    public void logAll(List<? extends JsonReportingMessage> messageList) {
        if (messageList != null && messageList.size() > 0) {
            boolean equals = ConfigManager.getEnvironment().equals(Environment.Development);
            String str = this.f3709f.getSession().f3728b;
            int i = 0;
            while (true) {
                int i2 = i;
                if (i2 < messageList.size()) {
                    ((JsonReportingMessage) messageList.get(i2)).setDevMode(equals);
                    ((JsonReportingMessage) messageList.get(i2)).setSessionId(str);
                    i = i2 + 1;
                } else {
                    this.f3711j.sendMessage(this.f3711j.obtainMessage(9, messageList));
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public Map<String, Object> mo44800a(final UserAttributeListener userAttributeListener) {
        HashMap hashMap = new HashMap();
        if (userAttributeListener == null || Looper.getMainLooper() != Looper.myLooper()) {
            TreeMap a = this.f3711j.mo44910a();
            TreeMap b = this.f3711j.mo44912b();
            if (userAttributeListener != null) {
                userAttributeListener.onUserAttributesReceived(a, b);
            }
            if (a != null) {
                hashMap.putAll(a);
            }
            if (b == null) {
                return hashMap;
            }
            hashMap.putAll(b);
            return hashMap;
        }
        new AsyncTask<Void, Void, C4600d>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public C4600d doInBackground(Void... voidArr) {
                return MessageManager.this.m2159u();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(C4600d dVar) {
                if (userAttributeListener != null) {
                    userAttributeListener.onUserAttributesReceived(dVar.f3724a, dVar.f3725b);
                }
            }
        }.execute(new Void[0]);
        return null;
    }

    /* renamed from: b */
    public Map<String, String> mo44811b(final UserAttributeListener userAttributeListener) {
        if (userAttributeListener == null || Looper.getMainLooper() != Looper.myLooper()) {
            TreeMap a = this.f3711j.mo44910a();
            if (userAttributeListener == null) {
                return a;
            }
            userAttributeListener.onUserAttributesReceived(a, this.f3711j.mo44912b());
            return a;
        }
        new AsyncTask<Void, Void, C4600d>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public C4600d doInBackground(Void... voidArr) {
                return MessageManager.this.m2159u();
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(C4600d dVar) {
                if (userAttributeListener != null) {
                    userAttributeListener.onUserAttributesReceived(dVar.f3724a, dVar.f3725b);
                }
            }
        }.execute(new Void[0]);
        return null;
    }

    /* renamed from: q */
    public Map<String, List<String>> mo44832q() {
        return this.f3711j.mo44912b();
    }

    /* renamed from: c */
    public void mo44816c(String str) {
        C4599c cVar = new C4599c();
        cVar.f3722a = str;
        cVar.f3723b = System.currentTimeMillis();
        this.f3711j.sendMessage(this.f3711j.obtainMessage(10, cVar));
    }

    /* renamed from: d */
    public void mo44818d(String str) {
        JSONArray jSONArray;
        try {
            jSONArray = new JSONArray(f3698d.getString("mp::deleted_user_attrs::" + this.f3710g.getApiKey(), null));
        } catch (Exception e) {
            jSONArray = new JSONArray();
        }
        jSONArray.put(str);
        f3698d.edit().putString("mp::deleted_user_attrs::" + this.f3710g.getApiKey(), jSONArray.toString()).apply();
    }

    /* renamed from: a */
    public void mo44809a(String str, Object obj) {
        C4600d dVar = new C4600d();
        dVar.f3726c = System.currentTimeMillis();
        if (obj instanceof List) {
            dVar.f3725b = new HashMap();
            dVar.f3725b.put(str, (List) obj);
        } else {
            dVar.f3724a = new HashMap();
            dVar.f3724a.put(str, (String) obj);
        }
        this.f3711j.sendMessage(this.f3711j.obtainMessage(11, dVar));
    }

    /* renamed from: a */
    public void mo44808a(String str, int i) {
        Message obtainMessage = this.f3711j.obtainMessage(12, str);
        obtainMessage.arg1 = i;
        this.f3711j.sendMessage(obtainMessage);
    }

    /* renamed from: r */
    public synchronized C4604b mo44833r() {
        return this.f3708e;
    }

    /* renamed from: a */
    public void mo44810a(JSONArray jSONArray) {
        f3698d.edit().putString("mp::user_ids::" + this.f3710g.getApiKey(), jSONArray.toString()).apply();
    }

    /* renamed from: s */
    public JSONArray mo44834s() {
        try {
            JSONArray jSONArray = new JSONArray(f3698d.getString("mp::user_ids::" + this.f3710g.getApiKey(), null));
            if (!m2156c(jSONArray)) {
                return jSONArray;
            }
            mo44810a(jSONArray);
            return jSONArray;
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public JSONArray mo44812b(JSONArray jSONArray) {
        try {
            JSONArray s = mo44834s();
            if (s.length() == 0) {
                return null;
            }
            JSONArray jSONArray2 = new JSONArray(jSONArray.toString());
            HashSet hashSet = new HashSet();
            for (int i = 0; i < jSONArray2.length(); i++) {
                if (jSONArray2.getJSONObject(i).optBoolean("f")) {
                    hashSet.add(Integer.valueOf(jSONArray2.getJSONObject(i).getInt("n")));
                }
            }
            if (hashSet.size() <= 0) {
                return null;
            }
            for (int i2 = 0; i2 < s.length(); i2++) {
                if (hashSet.contains(Integer.valueOf(s.getJSONObject(i2).getInt("n")))) {
                    s.getJSONObject(i2).put("f", false);
                }
            }
            return s;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: c */
    private static boolean m2156c(JSONArray jSONArray) {
        boolean z = false;
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (!jSONObject.has("dfs")) {
                    jSONObject.put("dfs", 0);
                    z = true;
                }
                if (!jSONObject.has("f")) {
                    jSONObject.put("f", true);
                    z = true;
                }
                i++;
            } catch (JSONException e) {
            }
        }
        return z;
    }

    /* access modifiers changed from: private */
    /* renamed from: u */
    public C4600d m2159u() {
        C4600d dVar = new C4600d();
        dVar.f3724a = this.f3711j.mo44910a();
        dVar.f3725b = this.f3711j.mo44912b();
        return dVar;
    }

    /* renamed from: a */
    public void mo44803a(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            String typeName = networkInfo.getTypeName();
            if (networkInfo.getSubtype() != 0) {
                typeName = typeName + "/" + networkInfo.getSubtypeName();
            }
            f3702m = typeName.toLowerCase(Locale.US);
            this.f3706a.mo44934c(networkInfo.isConnectedOrConnecting());
            return;
        }
        f3702m = "offline";
        this.f3706a.mo44934c(false);
    }
}
