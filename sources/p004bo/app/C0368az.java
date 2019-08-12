package p004bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.SystemClock;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.az */
public class C0368az {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f147a = AppboyLogger.getAppboyLogTag(C0368az.class);
    /* access modifiers changed from: private */

    /* renamed from: b */
    public final Object f148b = new Object();

    /* renamed from: c */
    private final C0446di f149c;
    /* access modifiers changed from: private */

    /* renamed from: d */
    public final C0343ac f150d;

    /* renamed from: e */
    private final Context f151e;

    /* renamed from: f */
    private final AlarmManager f152f;

    /* renamed from: g */
    private final int f153g;

    /* renamed from: h */
    private final String f154h;

    /* renamed from: i */
    private final C0448dk f155i;

    /* renamed from: j */
    private volatile C0391bt f156j;

    /* renamed from: k */
    private volatile boolean f157k = false;

    public C0368az(C0446di diVar, C0343ac acVar, Context context, AlarmManager alarmManager, int i, C0448dk dkVar) {
        this.f149c = diVar;
        this.f150d = acVar;
        this.f151e = context;
        this.f152f = alarmManager;
        this.f153g = i;
        this.f155i = dkVar;
        C03691 r0 = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                synchronized (C0368az.this.f148b) {
                    try {
                        C0368az.this.m128i();
                    } catch (Exception e) {
                        try {
                            C0368az.this.f150d.mo6736a(e, Throwable.class);
                        } catch (Exception e2) {
                            AppboyLogger.m1736e(C0368az.f147a, "Failed to log throwable.", e2);
                        }
                    }
                }
            }
        };
        this.f154h = context.getPackageName() + ".intent.APPBOY_SESSION_SHOULD_SEAL";
        context.registerReceiver(r0, new IntentFilter(this.f154h));
    }

    /* renamed from: a */
    public C0391bt mo6783a() {
        C0391bt btVar;
        synchronized (this.f148b) {
            if (m126g()) {
                this.f149c.mo6938a(this.f156j);
            }
            m130k();
            this.f150d.mo6736a(C0351ak.f96a, C0351ak.class);
            btVar = this.f156j;
        }
        return btVar;
    }

    /* renamed from: b */
    public C0391bt mo6785b() {
        C0391bt btVar;
        synchronized (this.f148b) {
            m126g();
            this.f156j.mo6830a(Double.valueOf(C0455dp.m522b()));
            this.f149c.mo6938a(this.f156j);
            m129j();
            this.f150d.mo6736a(C0352al.f97a, C0352al.class);
            btVar = this.f156j;
        }
        return btVar;
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public p004bo.app.C0391bt mo6784a(p004bo.app.C0386bo r6) {
        /*
            r5 = this;
            r1 = 0
            java.lang.Object r2 = r5.f148b
            monitor-enter(r2)
            r5.m128i()     // Catch:{ all -> 0x0043 }
            bo.app.bt r0 = r5.f156j     // Catch:{ all -> 0x0043 }
            if (r0 == 0) goto L_0x001b
            bo.app.bt r0 = r5.f156j     // Catch:{ all -> 0x0043 }
            r0.mo6832a(r6)     // Catch:{ all -> 0x0043 }
            bo.app.di r0 = r5.f149c     // Catch:{ all -> 0x0043 }
            bo.app.bt r1 = r5.f156j     // Catch:{ all -> 0x0043 }
            r0.mo6939a(r1, r6)     // Catch:{ all -> 0x0043 }
            bo.app.bt r0 = r5.f156j     // Catch:{ all -> 0x0043 }
            monitor-exit(r2)     // Catch:{ all -> 0x0043 }
        L_0x001a:
            return r0
        L_0x001b:
            bo.app.ay r0 = p004bo.app.C0366ay.m110a()     // Catch:{ all -> 0x0043 }
            java.lang.Object r3 = r0.mo6780c()     // Catch:{ all -> 0x0043 }
            monitor-enter(r3)     // Catch:{ all -> 0x0043 }
            bo.app.ay r0 = p004bo.app.C0366ay.m110a()     // Catch:{ all -> 0x005d }
            boolean r0 = r0.mo6781d()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0052
            bo.app.ay r0 = p004bo.app.C0366ay.m110a()     // Catch:{ all -> 0x005d }
            bo.app.bt r0 = r0.mo6779b()     // Catch:{ all -> 0x005d }
            if (r0 == 0) goto L_0x0046
            r0.mo6832a(r6)     // Catch:{ all -> 0x005d }
            bo.app.di r1 = r5.f149c     // Catch:{ all -> 0x005d }
            r1.mo6939a(r0, r6)     // Catch:{ all -> 0x005d }
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            monitor-exit(r2)     // Catch:{ all -> 0x0043 }
            goto L_0x001a
        L_0x0043:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0043 }
            throw r0
        L_0x0046:
            java.lang.String r0 = f147a     // Catch:{ all -> 0x005d }
            java.lang.String r4 = "Could not access a stored session. Dropping event"
            com.appboy.support.AppboyLogger.m1739w(r0, r4)     // Catch:{ all -> 0x005d }
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            monitor-exit(r2)     // Catch:{ all -> 0x0043 }
            r0 = r1
            goto L_0x001a
        L_0x0052:
            bo.app.ay r0 = p004bo.app.C0366ay.m110a()     // Catch:{ all -> 0x005d }
            r0.mo6777a(r6)     // Catch:{ all -> 0x005d }
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            monitor-exit(r2)     // Catch:{ all -> 0x0043 }
            r0 = r1
            goto L_0x001a
        L_0x005d:
            r0 = move-exception
            monitor-exit(r3)     // Catch:{ all -> 0x005d }
            throw r0     // Catch:{ all -> 0x0043 }
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0368az.mo6784a(bo.app.bo):bo.app.bt");
    }

    /* renamed from: c */
    public C0395bx mo6786c() {
        C0395bx a;
        synchronized (this.f148b) {
            m128i();
            if (this.f156j == null) {
                a = null;
            } else {
                a = this.f156j.mo6828a();
            }
        }
        return a;
    }

    /* renamed from: d */
    public boolean mo6787d() {
        boolean z;
        synchronized (this.f148b) {
            z = this.f156j != null && this.f156j.mo6839g();
        }
        return z;
    }

    /* renamed from: e */
    public void mo6788e() {
        synchronized (this.f148b) {
            if (this.f156j != null) {
                this.f156j.mo6841i();
                this.f149c.mo6938a(this.f156j);
                this.f150d.mo6736a(new C0350aj(this.f156j), C0350aj.class);
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:?, code lost:
        return true;
     */
    /* renamed from: g */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean m126g() {
        /*
            r8 = this;
            r1 = 0
            r0 = 1
            java.lang.Object r2 = r8.f148b
            monitor-enter(r2)
            r8.m128i()     // Catch:{ all -> 0x0051 }
            bo.app.bt r3 = r8.f156j     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0014
            bo.app.bt r3 = r8.f156j     // Catch:{ all -> 0x0051 }
            boolean r3 = r3.mo6839g()     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0041
        L_0x0014:
            bo.app.bt r1 = r8.f156j     // Catch:{ all -> 0x0051 }
            bo.app.bt r3 = r8.m127h()     // Catch:{ all -> 0x0051 }
            r8.f156j = r3     // Catch:{ all -> 0x0051 }
            if (r1 == 0) goto L_0x003f
            boolean r3 = r1.mo6845m()     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x003f
            java.lang.String r3 = f147a     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = "Clearing completely dispatched sealed session %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ all -> 0x0051 }
            r6 = 0
            bo.app.bx r7 = r1.mo6828a()     // Catch:{ all -> 0x0051 }
            r5[r6] = r7     // Catch:{ all -> 0x0051 }
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch:{ all -> 0x0051 }
            com.appboy.support.AppboyLogger.m1733d(r3, r4)     // Catch:{ all -> 0x0051 }
            bo.app.di r3 = r8.f149c     // Catch:{ all -> 0x0051 }
            r3.mo6941b(r1)     // Catch:{ all -> 0x0051 }
        L_0x003f:
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
        L_0x0040:
            return r0
        L_0x0041:
            bo.app.bt r3 = r8.f156j     // Catch:{ all -> 0x0051 }
            java.lang.Double r3 = r3.mo6835c()     // Catch:{ all -> 0x0051 }
            if (r3 == 0) goto L_0x0054
            bo.app.bt r1 = r8.f156j     // Catch:{ all -> 0x0051 }
            r3 = 0
            r1.mo6830a(r3)     // Catch:{ all -> 0x0051 }
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
            goto L_0x0040
        L_0x0051:
            r0 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
            throw r0
        L_0x0054:
            monitor-exit(r2)     // Catch:{ all -> 0x0051 }
            r0 = r1
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: p004bo.app.C0368az.m126g():boolean");
    }

    /* renamed from: h */
    private C0391bt m127h() {
        C0391bt btVar = new C0391bt(C0395bx.m273a(), C0455dp.m522b());
        this.f155i.mo6966a(true);
        this.f150d.mo6736a(C0349ai.f94a, C0349ai.class);
        AppboyLogger.m1737i(f147a, "New session created with ID: " + btVar.mo6828a());
        return btVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: i */
    public void m128i() {
        synchronized (this.f148b) {
            if (this.f156j == null && !this.f157k) {
                this.f156j = this.f149c.mo6937a();
                if (this.f156j != null) {
                    AppboyLogger.m1733d(f147a, String.format("Restored session from offline storage: %s", new Object[]{this.f156j.mo6828a().toString()}));
                }
            }
            this.f157k = true;
            if (this.f156j != null && this.f156j.mo6835c() != null && !this.f156j.mo6839g() && (this.f156j.mo6835c().doubleValue() + ((double) ((long) this.f153g))) * 1000.0d <= ((double) C0455dp.m523c())) {
                AppboyLogger.m1737i(f147a, String.format("Session [%s] being sealed because its end time is over the grace period.", new Object[]{this.f156j.mo6828a()}));
                mo6788e();
            }
        }
    }

    /* renamed from: j */
    private void m129j() {
        Intent intent = new Intent(this.f154h);
        intent.putExtra("session_id", this.f156j.toString());
        this.f152f.set(2, SystemClock.elapsedRealtime() + ((long) (this.f153g * 1000)), PendingIntent.getBroadcast(this.f151e, 0, intent, 1073741824));
    }

    /* renamed from: k */
    private void m130k() {
        Intent intent = new Intent(this.f154h);
        intent.putExtra("session_id", this.f156j.toString());
        this.f152f.cancel(PendingIntent.getBroadcast(this.f151e, 0, intent, 1073741824));
    }
}
