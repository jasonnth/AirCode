package p004bo.app;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.outgoing.Feedback;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.ax */
public class C0365ax implements C0375bd {

    /* renamed from: a */
    private static final String f123a = AppboyLogger.getAppboyLogTag(C0365ax.class);

    /* renamed from: b */
    private AtomicInteger f124b = new AtomicInteger(0);

    /* renamed from: c */
    private AtomicInteger f125c = new AtomicInteger(0);

    /* renamed from: d */
    private volatile String f126d = "";

    /* renamed from: e */
    private final C0368az f127e;

    /* renamed from: f */
    private final C0627p f128f;

    /* renamed from: g */
    private final C0343ac f129g;

    /* renamed from: h */
    private final C0377bf f130h;

    /* renamed from: i */
    private final AppboyConfigurationProvider f131i;

    /* renamed from: j */
    private final C0426cw f132j;

    /* renamed from: k */
    private boolean f133k = false;

    /* renamed from: l */
    private boolean f134l = false;

    /* renamed from: m */
    private Class<? extends Activity> f135m = null;

    /* renamed from: n */
    private final SharedPreferences f136n;

    public C0365ax(C0368az azVar, C0627p pVar, C0343ac acVar, C0377bf bfVar, AppboyConfigurationProvider appboyConfigurationProvider, Context context, C0426cw cwVar, boolean z, boolean z2) {
        this.f127e = azVar;
        this.f128f = pVar;
        this.f129g = acVar;
        this.f130h = bfVar;
        this.f131i = appboyConfigurationProvider;
        this.f132j = cwVar;
        this.f136n = context.getSharedPreferences("com.appboy.stored.push.clicks", 0);
        this.f133k = z;
        this.f134l = z2;
    }

    /* renamed from: a */
    public C0391bt mo6761a() {
        C0391bt a = this.f127e.mo6783a();
        this.f128f.mo7305a(a);
        AppboyLogger.m1737i(f123a, "Completed the openSession call. Starting or continuing session " + a.mo6828a());
        return a;
    }

    /* renamed from: a */
    public C0391bt mo6762a(Activity activity) {
        C0391bt a = mo6761a();
        this.f135m = activity.getClass();
        return a;
    }

    /* renamed from: b */
    public C0391bt mo6770b(Activity activity) {
        if (this.f135m == null || activity.getClass().equals(this.f135m)) {
            return this.f127e.mo6785b();
        }
        return null;
    }

    /* renamed from: b */
    public C0395bx mo6771b() {
        return this.f127e.mo6786c();
    }

    /* renamed from: c */
    public void mo6773c() {
        this.f135m = null;
        this.f127e.mo6788e();
    }

    /* renamed from: a */
    public boolean mo6769a(C0386bo boVar) {
        if (boVar == null) {
            AppboyLogger.m1735e(f123a, "Appboy manager received null event.");
            throw new NullPointerException();
        }
        if (boVar.mo6824b().equals(C0631t.PUSH_NOTIFICATION_TRACKING) || boVar.mo6824b().equals(C0631t.PUSH_NOTIFICATION_ACTION_TRACKING)) {
            if (this.f127e.mo6786c() == null || this.f127e.mo6787d()) {
                mo6772b(boVar);
                return true;
            }
            m93c(boVar);
        }
        C0391bt a = this.f127e.mo6784a(boVar);
        if (a == null) {
            return false;
        }
        this.f128f.mo7305a(a);
        if (a.mo6839g()) {
            mo6768a(new C0629r[0]);
        }
        AppboyLogger.m1733d(f123a, "Logged event: " + boVar.toString());
        return true;
    }

    /* renamed from: a */
    public void mo6767a(Throwable th) {
        try {
            if (m92b(th)) {
                AppboyLogger.m1739w(f123a, "Not logging duplicate error.");
            } else {
                mo6769a((C0386bo) C0397bz.m289a(th, mo6771b()));
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f123a, String.format("Failed to create error event from %s.", new Object[]{th}), e);
        } catch (Exception e2) {
            AppboyLogger.m1736e(f123a, "Failed to log error.", e2);
        }
    }

    /* renamed from: a */
    public void mo6763a(C0356ap apVar) {
        try {
            if (m92b((Throwable) apVar)) {
                AppboyLogger.m1739w(f123a, "Not logging duplicate database exception.");
            } else {
                mo6769a((C0386bo) C0397bz.m277a(apVar, mo6771b()));
            }
        } catch (JSONException e) {
            AppboyLogger.m1736e(f123a, String.format("Failed to create database exception event from %s.", new Object[]{apVar}), e);
        } catch (Exception e2) {
            AppboyLogger.m1736e(f123a, "Failed to log error.", e2);
        }
    }

    /* renamed from: a */
    public void mo6766a(String str, String str2, boolean z) {
        if (str == null || !ValidationUtils.isValidEmailAddress(str)) {
            throw new IllegalArgumentException("Reply to email address is invalid");
        } else if (StringUtils.isNullOrBlank(str2)) {
            throw new IllegalArgumentException("Feedback message cannot be null or blank");
        } else {
            this.f128f.mo7306a((C0424cu) new C0423ct(this.f131i.getBaseUrlForRequests(), new Feedback(str2, str, z, this.f130h.mo6796d(), this.f130h.mo6794b())));
        }
    }

    /* renamed from: a */
    public void mo6768a(C0629r... rVarArr) {
        this.f128f.mo7306a((C0424cu) new C0422cs(this.f131i.getBaseUrlForRequests(), rVarArr));
    }

    /* renamed from: b */
    private boolean m92b(Throwable th) {
        this.f124b.getAndIncrement();
        if (this.f126d.equals(th.getMessage()) && this.f125c.get() > 3 && this.f124b.get() < 100) {
            return true;
        }
        if (this.f126d.equals(th.getMessage())) {
            this.f125c.getAndIncrement();
        } else {
            this.f125c.set(0);
        }
        if (this.f124b.get() >= 100) {
            this.f124b.set(0);
        }
        this.f126d = th.getMessage();
        return false;
    }

    /* renamed from: d */
    public void mo6774d() {
        if (this.f127e.mo6786c() != null && !this.f127e.mo6787d()) {
            for (String string : this.f136n.getAll().keySet()) {
                String string2 = this.f136n.getString(string, null);
                if (!StringUtils.isNullOrEmpty(string2)) {
                    try {
                        mo6769a((C0386bo) C0397bz.m290a(new JSONObject(string2)));
                    } catch (JSONException e) {
                        AppboyLogger.m1740w(f123a, "Could not log pending AppboyEvent from shared preferences storage. Serialized string is: " + string2, e);
                    }
                }
            }
            Editor edit = this.f136n.edit();
            edit.clear();
            edit.apply();
        }
    }

    /* renamed from: c */
    private void m93c(C0386bo boVar) {
        JSONObject c = boVar.mo6825c();
        if (c != null) {
            String optString = c.optString("cid", null);
            if (boVar.mo6824b().equals(C0631t.PUSH_NOTIFICATION_TRACKING)) {
                this.f129g.mo6736a(new C0353am(optString, boVar), C0353am.class);
                return;
            }
            return;
        }
        AppboyLogger.m1739w(f123a, "Event json was null. Not logging push logged trigger event.");
    }

    /* renamed from: a */
    public void mo6764a(C0469dz dzVar, C0495ex exVar) {
        this.f128f.mo7306a((C0424cu) new C0433db(this.f131i.getBaseUrlForRequests(), dzVar, exVar));
    }

    /* renamed from: a */
    public void mo6765a(C0495ex exVar) {
        this.f129g.mo6736a(new C0354an(exVar), C0354an.class);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public void mo6772b(C0386bo boVar) {
        if (!(boVar.mo6824b().equals(C0631t.PUSH_NOTIFICATION_TRACKING) || boVar.mo6824b().equals(C0631t.PUSH_NOTIFICATION_ACTION_TRACKING))) {
            AppboyLogger.m1733d(f123a, "The IAppboyEvent was not push click event. Not storing to SharedPreferences file.");
            return;
        }
        Editor edit = this.f136n.edit();
        edit.putString(Double.toString(boVar.mo6823a()), boVar.mo6826d());
        edit.apply();
    }

    /* renamed from: e */
    public void mo6775e() {
        if (this.f130h.mo6798f() == null) {
            AppboyLogger.m1737i(f123a, "Advertising Id was null. Not requesting piq id.");
        } else if (this.f133k) {
            AppboyLogger.m1737i(f123a, "Appboy network is mocked. Not requesting piq id.");
        } else if (this.f134l) {
            AppboyLogger.m1737i(f123a, "Appboy outbound network requests are disabled. Not requesting piq id.");
        } else {
            AppboyLogger.m1737i(f123a, "Advertising Id present. Will request piq id.");
            this.f132j.mo6923a(new C0415cm("https://appboy.data.placeiq.com/dataex/id/", this.f130h.mo6798f()));
        }
    }

    /* renamed from: f */
    public boolean mo6776f() {
        return this.f134l;
    }
}
