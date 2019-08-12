package p004bo.app;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.appboy.events.IEventSubscriber;
import com.appboy.services.AppboyDataSyncService;
import com.appboy.support.AppboyLogger;

/* renamed from: bo.app.m */
public class C0620m {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f845a = AppboyLogger.getAppboyLogTag(C0620m.class);

    /* renamed from: b */
    private final Context f846b;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final C0626o f847c;

    /* renamed from: d */
    private final AlarmManager f848d;

    /* renamed from: e */
    private final C0619l f849e;

    /* renamed from: f */
    private final BroadcastReceiver f850f;

    /* renamed from: g */
    private final PendingIntent f851g;
    /* access modifiers changed from: private */

    /* renamed from: h */
    public C0635x f852h;

    /* renamed from: i */
    private long f853i;

    /* renamed from: j */
    private boolean f854j;

    /* renamed from: k */
    private volatile boolean f855k = false;

    public C0620m(Context context, final C0343ac acVar, C0626o oVar, AlarmManager alarmManager, C0619l lVar) {
        this.f846b = context;
        this.f847c = oVar;
        this.f848d = alarmManager;
        this.f849e = lVar;
        this.f852h = C0635x.NO_SESSION;
        this.f853i = -1;
        if (!C0462du.m536a(this.f846b, AppboyDataSyncService.class)) {
            AppboyLogger.m1735e(f845a, "Appboy periodic data flushing is not available. Declare <service android:name=\"com.appboy.services.AppboyDataSyncService\"/> in your AndroidManifest.xml to enable Appboy periodic data flushing.");
        }
        this.f851g = PendingIntent.getService(this.f846b, 0, new Intent(context.getApplicationContext().getPackageName() + ".REQUEST_DATA_SYNC").setClass(context, AppboyDataSyncService.class), 134217728);
        this.f850f = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                try {
                    C0620m.this.f847c.mo7309a(intent, (ConnectivityManager) context.getSystemService("connectivity"));
                    C0620m.this.mo7317c();
                } catch (Exception e) {
                    AppboyLogger.m1736e(C0620m.f845a, "Failed to process connectivity event.", e);
                    C0620m.this.m1147a(acVar, (Throwable) e);
                }
            }
        };
        AppboyLogger.m1733d(f845a, "Registered broadcast filters");
    }

    /* renamed from: a */
    public void mo7313a(C0340ab abVar) {
        abVar.mo6737a((IEventSubscriber<T>) new IEventSubscriber<C0351ak>() {
            /* renamed from: a */
            public void trigger(C0351ak akVar) {
                C0620m.this.f852h = C0635x.OPEN_SESSION;
                C0620m.this.mo7317c();
            }
        }, C0351ak.class);
        abVar.mo6737a((IEventSubscriber<T>) new IEventSubscriber<C0352al>() {
            /* renamed from: a */
            public void trigger(C0352al alVar) {
                C0620m.this.f852h = C0635x.NO_SESSION;
                C0620m.this.mo7317c();
            }
        }, C0352al.class);
    }

    /* renamed from: a */
    public synchronized void mo7314a(boolean z) {
        this.f854j = z;
        mo7317c();
        if (z) {
            mo7316b();
        } else {
            mo7315a();
        }
    }

    /* renamed from: a */
    public synchronized boolean mo7315a() {
        boolean z = true;
        synchronized (this) {
            if (this.f855k) {
                AppboyLogger.m1733d(f845a, "The data sync policy is already running. Ignoring request.");
                z = false;
            } else {
                AppboyLogger.m1733d(f845a, "Data sync started");
                mo7318d();
                m1145a(3000);
                this.f855k = true;
            }
        }
        return z;
    }

    /* renamed from: b */
    public synchronized boolean mo7316b() {
        boolean z = false;
        synchronized (this) {
            if (!this.f855k) {
                AppboyLogger.m1733d(f845a, "The data sync policy is not running. Ignoring request.");
            } else {
                AppboyLogger.m1733d(f845a, "Data sync stopped");
                m1150g();
                mo7319e();
                this.f855k = false;
                z = true;
            }
        }
        return z;
    }

    /* access modifiers changed from: protected */
    /* renamed from: c */
    public void mo7317c() {
        long j = this.f853i;
        if (this.f852h != C0635x.NO_SESSION && !this.f854j) {
            switch (this.f847c.mo7308a()) {
                case NONE:
                    this.f853i = -1;
                    break;
                case TWO_G:
                    this.f853i = this.f849e.mo7310a();
                    break;
                case FOUR_G:
                case WIFI:
                    this.f853i = this.f849e.mo7312c();
                    break;
                default:
                    this.f853i = this.f849e.mo7311b();
                    break;
            }
        } else {
            this.f853i = -1;
        }
        if (j != this.f853i) {
            m1145a(this.f853i);
            AppboyLogger.m1733d(f845a, String.format("Dispatch state has changed from %d to %d.", new Object[]{Long.valueOf(j), Long.valueOf(this.f853i)}));
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: d */
    public void mo7318d() {
        this.f846b.registerReceiver(this.f850f, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* access modifiers changed from: protected */
    /* renamed from: e */
    public void mo7319e() {
        this.f846b.unregisterReceiver(this.f850f);
    }

    /* renamed from: a */
    private void m1145a(long j) {
        if (this.f848d == null) {
            AppboyLogger.m1733d(f845a, "Alarm manager was null. Ignoring request to reset it.");
        } else if (this.f853i <= 0) {
            AppboyLogger.m1733d(f845a, "Cancelling alarm because delay value was not positive.");
            m1150g();
        } else {
            m1146a(C0455dp.m523c() + j, this.f853i);
        }
    }

    /* renamed from: a */
    private void m1146a(long j, long j2) {
        this.f848d.setInexactRepeating(1, j, j2, this.f851g);
    }

    /* renamed from: g */
    private void m1150g() {
        this.f848d.cancel(this.f851g);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m1147a(C0343ac acVar, Throwable th) {
        try {
            acVar.mo6736a(th, Throwable.class);
        } catch (Exception e) {
            AppboyLogger.m1736e(f845a, "Failed to log throwable.", e);
        }
    }
}
