package p004bo.app;

import android.app.AlarmManager;
import android.content.Context;
import android.support.p000v4.app.NotificationCompat;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.AppboyLogger;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* renamed from: bo.app.dv */
public final class C0463dv {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f388a = AppboyLogger.getAppboyLogTag(C0463dv.class);

    /* renamed from: b */
    private static final int f389b = Runtime.getRuntime().availableProcessors();

    /* renamed from: c */
    private static final int f390c = (f389b * 2);

    /* renamed from: d */
    private static final int f391d = (f389b * 4);

    /* renamed from: e */
    private final C0451dl f392e;

    /* renamed from: f */
    private final C0442df f393f;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public final C0340ab f394g = new C0340ab(this.f399l);
    /* access modifiers changed from: private */

    /* renamed from: h */
    public final C0615j f395h;

    /* renamed from: i */
    private final C0365ax f396i;

    /* renamed from: j */
    private final C0444dh f397j;

    /* renamed from: k */
    private final C0326aa f398k;

    /* renamed from: l */
    private final ThreadPoolExecutor f399l;

    /* renamed from: m */
    private final C0446di f400m;

    /* renamed from: n */
    private final C0625n f401n;

    /* renamed from: o */
    private final C0325a f402o;

    /* renamed from: p */
    private final C0447dj f403p;
    /* access modifiers changed from: private */

    /* renamed from: q */
    public final C0620m f404q;

    /* renamed from: r */
    private final C0368az f405r;

    /* renamed from: s */
    private final C0379bh f406s;

    /* renamed from: t */
    private final C0376be<C0406cg> f407t;

    /* renamed from: u */
    private final C0511fl f408u;

    /* renamed from: v */
    private final C0448dk f409v;

    public C0463dv(Context context, C0581h hVar, AppboyConfigurationProvider appboyConfigurationProvider, C0343ac acVar, C0362av avVar, C0380bi biVar, boolean z, boolean z2) {
        C0435dd a;
        String a2 = hVar.mo7244a();
        C0358ar arVar = new C0358ar();
        this.f399l = new ThreadPoolExecutor(f390c, f391d, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(64), arVar);
        this.f409v = new C0448dk(context, appboyConfigurationProvider.getAppboyApiKey().toString(), this.f394g);
        if (a2.equals("")) {
            this.f392e = new C0451dl(context, this.f409v);
            this.f393f = new C0442df(context);
            a = C0435dd.m418a(context, null);
        } else {
            this.f392e = new C0451dl(context, a2, appboyConfigurationProvider.getAppboyApiKey().toString(), this.f409v);
            this.f393f = new C0442df(context, a2, appboyConfigurationProvider.getAppboyApiKey().toString());
            a = C0435dd.m418a(context, a2);
        }
        this.f407t = new C0371ba(context);
        this.f407t.mo6793b();
        C0372bb bbVar = new C0372bb(context, appboyConfigurationProvider, a2, avVar, biVar, this.f393f, this.f409v, this.f407t, this.f394g);
        this.f402o = new C0325a(appboyConfigurationProvider, bbVar);
        this.f402o.mo6704a(a2);
        this.f401n = new C0625n(this.f392e, bbVar);
        C0360at atVar = new C0360at(arVar);
        arVar.mo6751a(new C0359as(this.f394g));
        this.f403p = new C0447dj(a);
        C0436de deVar = new C0436de(this.f403p, atVar);
        this.f400m = new C0443dg(deVar, this.f394g);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        this.f405r = new C0368az(this.f400m, this.f394g, context, alarmManager, appboyConfigurationProvider.getSessionTimeoutSeconds(), this.f409v);
        this.f397j = new C0444dh(context, a2);
        C0432da daVar = new C0432da(this.f402o, C0370b.m137a(), this.f394g, acVar, this.f399l, this.f397j, this.f409v);
        this.f404q = new C0620m(context, this.f394g, new C0618k(), alarmManager, new C0619l(context));
        this.f404q.mo7313a(this.f394g);
        this.f404q.mo7314a(z2);
        this.f395h = new C0615j(appboyConfigurationProvider, this.f394g, daVar, this.f401n, arVar, z);
        this.f396i = new C0365ax(this.f405r, this.f395h, this.f394g, bbVar, appboyConfigurationProvider, context, daVar, z, z2);
        this.f408u = new C0511fl(context, this.f396i, this.f399l, acVar, appboyConfigurationProvider, a2, appboyConfigurationProvider.getAppboyApiKey().toString());
        if (!z && (daVar instanceof C0432da)) {
            daVar.mo6927a((C0375bd) this.f396i);
        }
        this.f397j.mo6957a(this.f396i);
        this.f401n.mo7323a((C0375bd) this.f396i);
        this.f406s = new C0363aw(context, this.f396i, appboyConfigurationProvider, this.f409v);
        C0436de deVar2 = deVar;
        Context context2 = context;
        this.f398k = new C0326aa(this.f406s, deVar2, this.f395h, this.f405r, this.f396i, context2, this.f392e, this.f393f, this.f409v, this.f408u, new C0412cl(context, appboyConfigurationProvider.getAppboyApiKey().toString(), this.f396i));
    }

    /* renamed from: a */
    public C0448dk mo6996a() {
        return this.f409v;
    }

    /* renamed from: c */
    public C0325a mo6997c() {
        return this.f402o;
    }

    /* renamed from: d */
    public C0326aa mo6998d() {
        return this.f398k;
    }

    /* renamed from: e */
    public C0365ax mo6999e() {
        return this.f396i;
    }

    /* renamed from: f */
    public C0615j mo7000f() {
        return this.f395h;
    }

    /* renamed from: g */
    public C0340ab mo7001g() {
        return this.f394g;
    }

    /* renamed from: h */
    public C0451dl mo7002h() {
        return this.f392e;
    }

    /* renamed from: i */
    public C0442df mo7003i() {
        return this.f393f;
    }

    /* renamed from: j */
    public ThreadPoolExecutor mo7004j() {
        return this.f399l;
    }

    /* renamed from: k */
    public C0446di mo7005k() {
        return this.f400m;
    }

    /* renamed from: l */
    public C0444dh mo7006l() {
        return this.f397j;
    }

    /* renamed from: m */
    public C0379bh mo7007m() {
        return this.f406s;
    }

    /* renamed from: n */
    public C0511fl mo7008n() {
        return this.f408u;
    }

    /* renamed from: o */
    public void mo7009o() {
        this.f399l.execute(new Runnable() {
            public void run() {
                try {
                    C0463dv.this.f395h.mo7304a(C0463dv.this.f394g);
                } catch (Exception e) {
                    AppboyLogger.m1740w(C0463dv.f388a, "Exception while shutting down dispatch manager. Continuing.", e);
                }
                try {
                    C0463dv.this.f404q.mo7316b();
                } catch (Exception e2) {
                    AppboyLogger.m1740w(C0463dv.f388a, "Exception while un-registering data refresh broadcast receivers. Continuing.", e2);
                }
            }
        });
    }
}
