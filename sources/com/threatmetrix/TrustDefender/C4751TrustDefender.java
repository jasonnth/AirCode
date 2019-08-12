package com.threatmetrix.TrustDefender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;
import android.support.p000v4.media.session.PlaybackStateCompat;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: com.threatmetrix.TrustDefender.TrustDefender */
public class C4751TrustDefender {
    /* access modifiers changed from: private */

    /* renamed from: d */
    public static final String f4100d = C4834w.m2892a(C4751TrustDefender.class);

    /* renamed from: e */
    private static final boolean f4101e;

    /* renamed from: f */
    private static final Executor f4102f = Executors.newFixedThreadPool(6);

    /* renamed from: g */
    private static final Lock f4103g = new ReentrantLock();

    /* renamed from: h */
    private static volatile C4751TrustDefender f4104h = null;

    /* renamed from: i */
    private static boolean f4105i;

    /* renamed from: j */
    private static boolean f4106j;

    /* renamed from: k */
    private static boolean f4107k;
    /* access modifiers changed from: private */

    /* renamed from: A */
    public volatile boolean f4108A = true;

    /* renamed from: B */
    private volatile long f4109B = 0;
    /* access modifiers changed from: private */

    /* renamed from: C */
    public volatile int f4110C = 0;

    /* renamed from: D */
    private volatile C4818k f4111D = null;

    /* renamed from: E */
    private volatile boolean f4112E = false;
    /* access modifiers changed from: private */

    /* renamed from: F */
    public Timer f4113F;
    /* access modifiers changed from: private */

    /* renamed from: G */
    public int f4114G;

    /* renamed from: H */
    private final ArrayList<C4837y> f4115H = new ArrayList<>();

    /* renamed from: I */
    private final ReadWriteLock f4116I = new ReentrantReadWriteLock();

    /* renamed from: J */
    private final Lock f4117J = this.f4116I.readLock();

    /* renamed from: K */
    private final Lock f4118K = this.f4116I.writeLock();

    /* renamed from: L */
    private final C4780ap f4119L = new C4780ap();

    /* renamed from: M */
    private final boolean f4120M = true;

    /* renamed from: N */
    private final C4757d f4121N = new C4757d();

    /* renamed from: a */
    final C4785ar f4122a = new C4785ar("");

    /* renamed from: b */
    final C4764ad f4123b = new C4764ad();

    /* renamed from: c */
    volatile C4777am f4124c = null;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public Context f4125l = null;

    /* renamed from: m */
    private int f4126m = 0;
    /* access modifiers changed from: private */

    /* renamed from: n */
    public int f4127n = 30000;
    /* access modifiers changed from: private */

    /* renamed from: o */
    public volatile AtomicLong f4128o = new AtomicLong(0);

    /* renamed from: p */
    private volatile int f4129p = 10000;

    /* renamed from: q */
    private volatile int f4130q = 10000;

    /* renamed from: r */
    private volatile int f4131r = 0;

    /* renamed from: s */
    private volatile boolean f4132s = true;

    /* renamed from: t */
    private volatile boolean f4133t = false;

    /* renamed from: u */
    private volatile String f4134u = null;

    /* renamed from: v */
    private volatile Thread f4135v = null;

    /* renamed from: w */
    private volatile EndNotifierBase f4136w = null;
    /* access modifiers changed from: private */

    /* renamed from: x */
    public volatile C4772ak f4137x = null;
    /* access modifiers changed from: private */

    /* renamed from: y */
    public volatile C4790d f4138y = null;
    /* access modifiers changed from: private */

    /* renamed from: z */
    public volatile boolean f4139z = true;

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$a */
    final class C4754a implements Runnable {

        /* renamed from: a */
        final ProfilingResult f4147a;

        /* renamed from: b */
        final EndNotifierBase f4148b;

        C4754a(ProfilingResult t, EndNotifierBase n) {
            this.f4147a = t;
            this.f4148b = n;
        }

        public final void run() {
            if (this.f4148b != null && (this.f4148b instanceof EndNotifier)) {
                ((EndNotifier) this.f4148b).complete(this.f4147a);
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$b */
    final class C4755b implements Runnable {

        /* renamed from: a */
        final Thread f4150a;

        C4755b(Thread t) {
            this.f4150a = t;
        }

        public final void run() {
            C4834w.m2901c(C4751TrustDefender.f4100d, "sending interrupt to TID: " + this.f4150a.getId());
            this.f4150a.interrupt();
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$c */
    private enum C4756c {
        doProfileRequest,
        doPackageScan,
        init
    }

    /* renamed from: com.threatmetrix.TrustDefender.TrustDefender$d */
    class C4757d extends BroadcastReceiver {
        C4757d() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                C4751TrustDefender.this.f4108A = false;
                C4834w.m2901c(C4751TrustDefender.f4100d, "Screen is off, any future profiling will be blocked after " + C4751TrustDefender.this.f4114G + " seconds.");
                if (C4751TrustDefender.this.f4113F != null) {
                    C4751TrustDefender.this.f4113F.cancel();
                }
                C4751TrustDefender.this.f4113F = new Timer();
                C4751TrustDefender.this.f4113F.schedule(new TimerTask() {
                    public final void run() {
                        synchronized (this) {
                            if (!C4751TrustDefender.this.f4108A) {
                                C4751TrustDefender.this.f4139z = false;
                                C4751TrustDefender.this.pauseLocationServices(true);
                            }
                        }
                    }
                }, TimeUnit.SECONDS.toMillis((long) C4751TrustDefender.this.f4114G));
            } else if ("android.intent.action.SCREEN_ON".equals(intent.getAction())) {
                synchronized (this) {
                    C4751TrustDefender.this.f4108A = true;
                    C4751TrustDefender.this.f4139z = true;
                    if (C4751TrustDefender.this.f4113F != null) {
                        C4751TrustDefender.this.f4113F.cancel();
                    }
                    C4751TrustDefender.this.pauseLocationServices(false);
                    C4834w.m2901c(C4751TrustDefender.f4100d, "Screen is on profiling is unblocked.");
                }
            }
        }
    }

    static {
        boolean z;
        boolean z2;
        boolean z3 = true;
        f4105i = false;
        f4106j = false;
        f4107k = false;
        String vmVersion = System.getProperty("java.vm.version");
        boolean z4 = vmVersion != null && vmVersion.equals("2.0.0");
        f4101e = z4;
        if (z4) {
            C4834w.m2901c(f4100d, "Broken join() detected, activating fallback routine");
        }
        if (C4787at.m2745b("com.squareup.okhttp.OkHttpClient") != null) {
            z = true;
        } else {
            z = false;
        }
        f4106j = z;
        if (C4787at.m2745b("okhttp3.OkHttpClient") != null) {
            z2 = true;
        } else {
            z2 = false;
        }
        f4105i = z2;
        if (C4787at.m2745b("okio.Okio") == null) {
            z3 = false;
        }
        f4107k = z3;
    }

    private C4751TrustDefender() {
    }

    public static C4751TrustDefender getInstance() {
        if (f4104h != null) {
            return f4104h;
        }
        try {
            f4103g.lock();
            if (f4104h == null) {
                f4104h = new C4751TrustDefender();
            }
            return f4104h;
        } finally {
            f4103g.unlock();
        }
    }

    public THMStatusCode init(final Config config) {
        boolean z;
        if (!this.f4123b.mo45954d()) {
            C4834w.m2901c(f4100d, "Already init'd");
            return THMStatusCode.THM_Already_Initialised;
        } else if (config.mo45840j() == null) {
            this.f4123b.mo45948a(false);
            mo45921a(THMStatusCode.THM_Invalid_Context);
            return this.f4122a.mo46020d();
        } else if ((!f4106j && !f4105i) || !f4107k) {
            C4834w.m2894a(f4100d, "OkHttp library not available, please include the library. For information about how to include the library see http://square.github.io/okhttp/");
            this.f4123b.mo45948a(false);
            mo45921a(THMStatusCode.THM_ThirdPartyLibrary_Not_Found);
            return this.f4122a.mo46020d();
        } else if (!this.f4122a.mo46021d(config.mo45849s())) {
            this.f4123b.mo45948a(false);
            mo45921a(THMStatusCode.THM_Invalid_OrgID);
            return this.f4122a.mo46020d();
        } else if (!this.f4122a.mo46019c(config.mo45846p())) {
            this.f4123b.mo45948a(false);
            mo45921a(THMStatusCode.THM_Invalid_FP_Server);
            return this.f4122a.mo46020d();
        } else {
            C4834w.m2901c(f4100d, "Starting init()");
            m2558d();
            this.f4108A = true;
            this.f4139z = true;
            this.f4122a.mo46028k();
            this.f4125l = config.mo45840j().getApplicationContext();
            this.f4122a.mo46006a(this.f4125l);
            this.f4128o.set(config.mo45839i());
            this.f4122a.mo46005a(this.f4128o.get());
            this.f4129p = config.mo45833c() * 1000;
            this.f4122a.mo46012a(config.mo45834d());
            if (this.f4113F != null) {
                this.f4113F.cancel();
            }
            if (this.f4138y != null) {
                this.f4138y.mo46036a(false);
            }
            C4767ag.m2617b();
            this.f4119L.mo45995a(config.mo45835e());
            this.f4119L.mo45997b(config.mo45847q());
            this.f4119L.mo45993a(this.f4125l, config.mo45836f(), config.mo45837g(), config.mo45838h());
            this.f4131r = config.mo45841k();
            this.f4130q = config.mo45842l();
            this.f4127n = config.mo45844n();
            this.f4126m = config.mo45843m();
            this.f4133t = config.mo45845o();
            if (!config.mo45831a()) {
                z = true;
            } else {
                z = false;
            }
            this.f4132s = z;
            String packageName = this.f4125l.getPackageName();
            String g = this.f4122a.mo46025g();
            if (this.f4134u == null) {
                this.f4134u = packageName + "TDM" + g;
            }
            this.f4122a.mo46023e(packageName);
            this.f4114G = config.mo45848r();
            if (this.f4114G > 0) {
                IntentFilter filter = new IntentFilter();
                filter.addAction("android.intent.action.SCREEN_ON");
                filter.addAction("android.intent.action.SCREEN_OFF");
                this.f4125l.registerReceiver(this.f4121N, filter);
            }
            if ((this.f4128o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
                this.f4111D = new C4818k();
                this.f4112E = this.f4111D.mo46060a(this.f4125l);
            }
            new Thread(new C4815h(this) {
                public final void run() {
                    boolean initWebView = true;
                    boolean initSuccess = true;
                    try {
                        C4834w.m2901c(C4751TrustDefender.f4100d, "Doing slow init stuff");
                        C4785ar arVar = C4751TrustDefender.this.f4122a;
                        if (C4785ar.m2701r()) {
                            C4751TrustDefender.this.f4122a.mo46004a(1);
                        }
                        NativeGatherer.m2512a().mo45859a(C4751TrustDefender.this.f4125l, C4834w.m2898b());
                        C4834w.m2899b(C4751TrustDefender.f4100d, "Native libs: " + (NativeGatherer.m2512a().mo45864b() ? "available" : "unavailable"));
                        C4751TrustDefender.this.mo45923b();
                        if (C4751TrustDefender.this.f4137x != null) {
                            C4834w.m2901c(C4751TrustDefender.f4100d, "applying saved options (" + C4751TrustDefender.this.f4137x.mo45971a() + " / " + C4751TrustDefender.this.f4137x.mo45976b() + ") to " + C4751TrustDefender.this.f4128o);
                            C4751TrustDefender.this.f4128o.set((C4751TrustDefender.this.f4128o.get() & ((C4751TrustDefender.this.f4137x.mo45976b() & 262142) ^ -1)) | (C4751TrustDefender.this.f4137x.mo45971a() & 262142));
                            C4751TrustDefender.f4100d;
                            C4751TrustDefender.this.f4137x.mo45979d();
                            C4751TrustDefender.this.f4110C = C4751TrustDefender.this.f4137x.mo45979d();
                        }
                        C4751TrustDefender.this.f4122a.mo46015a(C4751TrustDefender.this.f4128o);
                        if ((C4751TrustDefender.this.f4128o.get() & 38) == 0) {
                            initWebView = false;
                        }
                        if (C4814o.m2821a()) {
                            C4751TrustDefender.this.f4138y = new C4790d();
                            C4751TrustDefender.this.f4138y.mo46037a(C4751TrustDefender.this.f4125l, initWebView, C4751TrustDefender.this.f4128o.get());
                            C4751TrustDefender.this.f4122a.mo46013a(C4751TrustDefender.this.f4138y.mo46035a(), true);
                        } else {
                            C4751TrustDefender.this.f4138y = null;
                        }
                        C4834w.m2901c(C4751TrustDefender.f4100d, "Creating HTTP Client");
                        if (!C4751TrustDefender.this.m2564g()) {
                            initSuccess = false;
                        }
                        C4834w.m2901c(C4751TrustDefender.f4100d, "HTTP Client created and user agent set");
                        try {
                            C4770ai.m2628b(null);
                        } catch (InterruptedException e) {
                        }
                        if (!NativeGatherer.m2512a().mo45864b() && NativeGatherer.m2512a().mo45876j()) {
                            C4751TrustDefender.this.f4122a.mo46004a(2);
                        }
                        if (!config.mo45832b()) {
                            C4751TrustDefender.this.mo45922a(C4751TrustDefender.this.f4127n, false, false, C4756c.init);
                        }
                        C4751TrustDefender.this.f4123b.mo45948a(initSuccess);
                        C4834w.m2901c(C4751TrustDefender.f4100d, "init completed " + (initSuccess ? "successfully" : "unsuccessfully"));
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        C4751TrustDefender.this.f4123b.mo45948a(initSuccess);
                        C4834w.m2901c(C4751TrustDefender.f4100d, "init completed " + (initSuccess ? "successfully" : "unsuccessfully"));
                        throw th2;
                    }
                }
            }).start();
            return THMStatusCode.THM_OK;
        }
    }

    public THMStatusCode doProfileRequest(ProfilingOptions options) {
        boolean z = true;
        if (!this.f4123b.mo45951b()) {
            mo45921a(THMStatusCode.THM_Internal_Error);
            return this.f4122a.mo46020d();
        } else if (!this.f4123b.mo45956g()) {
            mo45921a(THMStatusCode.THM_NotYet);
            return this.f4122a.mo46020d();
        } else if (!this.f4139z || !m2562f()) {
            mo45921a(THMStatusCode.THM_Blocked);
            this.f4123b.mo45957h();
            return this.f4122a.mo46020d();
        } else {
            if (this.f4109B == 0 || this.f4110C == 0) {
                z = false;
            } else if (this.f4109B + TimeUnit.MILLISECONDS.convert((long) this.f4110C, TimeUnit.MINUTES) <= System.currentTimeMillis()) {
                z = false;
            }
            if (z) {
                mo45921a(THMStatusCode.THM_In_Quiet_Period);
                this.f4123b.mo45957h();
                return this.f4122a.mo46020d();
            } else if (options.mo45906d() == null) {
                mo45921a(THMStatusCode.THM_EndNotifier_NotFound);
                this.f4123b.mo45957h();
                return this.f4122a.mo46020d();
            } else {
                this.f4122a.mo46008a(THMStatusCode.THM_NotYet);
                this.f4122a.f4292W = System.currentTimeMillis();
                C4834w.m2898b();
                try {
                    this.f4118K.lockInterruptibly();
                    C4834w.m2901c(f4100d, "starting profile request using - 4.0-90 options " + this.f4128o + " timeout " + this.f4129p + "ms fp " + this.f4122a.mo46024f() + " java.vm.version " + System.getProperty("java.vm.version"));
                    m2558d();
                    this.f4122a.mo46011a((C4794e) this.f4123b);
                    if (this.f4115H.size() > 0) {
                        C4834w.m2901c(f4100d, "outstanding requests... interrupting");
                        m2553b(true);
                    }
                    this.f4115H.clear();
                    this.f4136w = options.mo45906d();
                    if (!(this.f4111D == null || !this.f4112E || (this.f4128o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) == 0)) {
                        this.f4122a.f4278I = this.f4111D.mo46059a(this.f4129p / 10);
                    }
                    this.f4122a.mo46014a(options.mo45904b());
                    if (C4770ai.m2633f(options.mo45903a())) {
                        this.f4122a.mo46017b(options.mo45903a());
                    } else {
                        this.f4122a.mo46017b(C4770ai.m2621a());
                    }
                    if (C4805f.m2789b()) {
                        this.f4119L.mo45994a(options.mo45905c());
                    }
                    this.f4135v = new Thread(new C4815h(this));
                    this.f4135v.start();
                    return THMStatusCode.THM_OK;
                } catch (InterruptedException e) {
                    if (this.f4135v != null) {
                        this.f4135v.interrupt();
                    }
                    this.f4123b.mo45957h();
                    mo45921a(THMStatusCode.THM_Interrupted_Error);
                    return this.f4122a.mo46020d();
                } finally {
                    this.f4118K.unlock();
                }
            }
        }
    }

    public ProfilingResult getResult() {
        return new ProfilingResult(this.f4122a.mo46018c(), this.f4122a.mo46020d());
    }

    public boolean doPackageScan(int timeout) {
        return mo45922a(timeout, true, true, C4756c.doPackageScan);
    }

    public void pauseLocationServices(boolean pause) {
        if (pause) {
            this.f4119L.mo45992a();
        } else {
            this.f4119L.mo45996b();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo45922a(int timeout, boolean checkProfiling, boolean checkInit, C4756c source) {
        final int package_limit;
        final long allowedScanOptions;
        C4834w.m2901c(f4100d, "doPackageScan(" + source + "): marking scan as started");
        if ((!checkInit || this.f4123b.mo45953c()) && (checkInit || this.f4123b.mo45951b())) {
            if (source == C4756c.doProfileRequest || source == C4756c.init) {
                allowedScanOptions = this.f4128o.get() & PlaybackStateCompat.ACTION_PREPARE;
                package_limit = source == C4756c.init ? this.f4126m : this.f4131r;
            } else {
                allowedScanOptions = this.f4128o.get();
                package_limit = 0;
            }
            if ((28672 & allowedScanOptions) != 0) {
                if (!this.f4123b.mo45952b(checkProfiling)) {
                    C4834w.m2899b(f4100d, "Scan " + (checkProfiling ? "or profile" : "") + " already in progress or cancel requested, aborting");
                    return false;
                }
                final int i = timeout;
                final C4756c cVar = source;
                new Thread(new C4815h(this) {
                    public final void run() {
                        int flags = 0;
                        try {
                            if ((allowedScanOptions & 12288) != 0) {
                                flags = 2;
                            }
                            if (!((allowedScanOptions & PlaybackStateCompat.ACTION_PREPARE) == 0 && (allowedScanOptions & PlaybackStateCompat.ACTION_PLAY_FROM_URI) == 0)) {
                                flags |= 1;
                            }
                            NativeGatherer.m2512a().mo45855a(C4751TrustDefender.this.f4125l, flags, package_limit, i);
                        } catch (InterruptedException e) {
                        } finally {
                            C4834w.m2901c(C4751TrustDefender.f4100d, "doPackageScan(" + cVar + "): complete");
                            C4751TrustDefender.this.f4123b.mo45959l();
                        }
                    }
                }).start();
            }
            return true;
        }
        C4834w.m2894a(f4100d, "doPackageScan(" + source + "): aborted! not inited");
        return false;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo45920a() {
        try {
            this.f4122a.mo46016b();
            C4834w.m2901c(f4100d, "continuing profile request " + (this.f4123b.mo45951b() ? "inited already" : " needs init"));
            if (this.f4123b.mo45949a() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            boolean waitedForScan = false;
            boolean shouldWaitForScan = false;
            if (!this.f4123b.mo45951b()) {
                C4834w.m2901c(f4100d, "Not inited");
                throw new IllegalArgumentException("Not inited");
            }
            boolean initInProgress = this.f4123b.mo45955e();
            boolean scanInProgress = this.f4123b.mo45961n();
            if (initInProgress || scanInProgress) {
                if (this.f4133t) {
                    C4834w.m2901c(f4100d, "Setting flag to for interrupting " + (initInProgress ? "init" : "package") + " scan");
                    this.f4123b.mo45958k();
                } else {
                    shouldWaitForScan = true;
                    waitedForScan = true;
                }
                boolean waitForInitResult = this.f4123b.mo45950a(this.f4129p);
                if (this.f4133t) {
                    NativeGatherer.m2512a().mo45865c();
                    this.f4123b.mo45960m();
                    NativeGatherer.m2512a().mo45867d();
                }
                if (!waitForInitResult) {
                    if (!this.f4123b.mo45949a()) {
                        C4834w.m2894a(f4100d, "Timed out waiting for init thread, aborting");
                        this.f4122a.mo46008a(THMStatusCode.THM_Internal_Error);
                    } else {
                        C4834w.m2894a(f4100d, "Thread interrupted, returning");
                    }
                    if (this.f4123b.mo45949a()) {
                        this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                        Thread.interrupted();
                    }
                    ProfilingResult result = getResult();
                    EndNotifierBase notifier = this.f4136w;
                    this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
                    this.f4123b.mo45957h();
                    f4102f.execute(new C4754a(result, notifier));
                    return;
                } else if (shouldWaitForScan) {
                    this.f4123b.mo45960m();
                }
            }
            if (!waitedForScan && this.f4132s) {
                mo45922a(this.f4130q, false, true, C4756c.doProfileRequest);
            }
            if (this.f4123b.mo45949a() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            }
            C4822m mVar = new C4822m(this.f4124c, this.f4122a.mo46026h(), this.f4122a.mo46027j(), this.f4122a.mo46030m(), this, this.f4123b);
            C4822m configRunner = m2548a((Runnable) mVar) != null ? mVar : null;
            if (configRunner == null) {
                C4834w.m2894a(f4100d, "Failed to connect to server, aborting");
                this.f4122a.mo46008a(THMStatusCode.THM_Internal_Error);
                if (this.f4123b.mo45949a()) {
                    this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                    Thread.interrupted();
                }
                ProfilingResult result2 = getResult();
                EndNotifierBase notifier2 = this.f4136w;
                this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
                this.f4123b.mo45957h();
                f4102f.execute(new C4754a(result2, notifier2));
            } else if (this.f4123b.mo45949a() || Thread.currentThread().isInterrupted()) {
                throw new InterruptedException();
            } else {
                boolean needToWaitForBrowser = false;
                if (this.f4138y != null) {
                    needToWaitForBrowser = this.f4138y.mo46038b();
                    if (needToWaitForBrowser) {
                        this.f4138y.mo46039c();
                    }
                }
                this.f4123b.mo45960m();
                this.f4122a.mo46029l();
                if (this.f4123b.mo45949a() || Thread.currentThread().isInterrupted()) {
                    throw new InterruptedException();
                }
                if (this.f4138y != null && needToWaitForBrowser) {
                    this.f4138y.mo46036a(true);
                    this.f4122a.mo46010a((C4789c) this.f4138y);
                }
                THMStatusCode configStatus = m2546a(false);
                m2559e();
                if (configStatus != THMStatusCode.THM_OK) {
                    C4834w.m2894a(f4100d, "Failed to retrieve config, aborting: " + configStatus.toString());
                    this.f4122a.mo46008a(configStatus);
                    NativeGatherer.m2512a().mo45865c();
                    NativeGatherer.m2512a().mo45867d();
                    if (this.f4123b.mo45949a()) {
                        this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                        Thread.interrupted();
                    }
                    ProfilingResult result3 = getResult();
                    EndNotifierBase notifier3 = this.f4136w;
                    this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
                    this.f4123b.mo45957h();
                    f4102f.execute(new C4754a(result3, notifier3));
                    return;
                }
                this.f4122a.mo46009a(configRunner.f4615a);
                C4771aj confRef = this.f4122a.mo46022e();
                if (confRef != null) {
                    if (this.f4137x == null || this.f4137x.mo45975a(confRef.f4221a, confRef.f4222b, "4.0-90", confRef.f4227g)) {
                        if (this.f4137x != null) {
                            String str = f4100d;
                            new StringBuilder("dynamic enableOptions / disableOptions (").append(confRef.f4221a).append(" / ").append(confRef.f4222b).append(") != saved: m_default values enableOptions / disableOptions / sdk_version / quietPeriod (").append(this.f4137x.mo45971a()).append(" / ").append(this.f4137x.mo45976b()).append(" / ").append(this.f4137x.mo45978c()).append(" / ").append(this.f4137x.mo45979d()).append(")");
                        } else {
                            String str2 = f4100d;
                            new StringBuilder("dynamic enableOptions / disableOptions (").append(confRef.f4221a).append(" / ").append(confRef.f4222b).append(") != saved: m_default is null");
                        }
                        NativeGatherer.m2512a().mo45856a("enableOptions", String.valueOf(confRef.f4221a));
                        NativeGatherer.m2512a().mo45856a("disableOptions", String.valueOf(confRef.f4222b));
                        NativeGatherer.m2512a().mo45856a("sdkVersion", "4.0-90");
                        NativeGatherer.m2512a().mo45856a("quietPeriod", String.valueOf(confRef.f4227g));
                        C4811l lVar = new C4811l(this.f4125l, this.f4134u, 0);
                        lVar.mo46054b("enableOptions", confRef.f4221a);
                        lVar.mo46054b("disableOptions", confRef.f4222b);
                        lVar.mo46055b("sdkVersion", "4.0-90");
                        lVar.mo46053b("quietPeriod", confRef.f4227g);
                        lVar.mo46052a();
                    }
                    this.f4110C = confRef.f4227g;
                } else if (!this.f4123b.mo45949a()) {
                    C4834w.m2894a(f4100d, "Failed to get config, bailing out");
                    if (this.f4123b.mo45949a()) {
                        this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                        Thread.interrupted();
                    }
                    ProfilingResult result4 = getResult();
                    EndNotifierBase notifier4 = this.f4136w;
                    this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
                    this.f4123b.mo45957h();
                    f4102f.execute(new C4754a(result4, notifier4));
                    return;
                }
                if (this.f4123b.mo45949a()) {
                    throw new InterruptedException();
                }
                if ((this.f4128o.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) != 0 && C4770ai.m2633f(this.f4122a.mo46022e().f4226f)) {
                    String str3 = this.f4122a.mo46022e().f4226f;
                    C4785ar arVar = this.f4122a;
                    m2548a((Runnable) new C4824o(this.f4124c, C4825a.GET_CONSUME, str3, null, C4785ar.m2700n(), this, this.f4125l, this.f4123b));
                }
                if ((this.f4128o.get() & PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) != 0) {
                    String fqdn = this.f4122a.mo46031o();
                    if (fqdn != null) {
                        m2548a((Runnable) new C4816i(fqdn));
                    }
                }
                if ((this.f4128o.get() & 64) != 0) {
                    m2548a((Runnable) new C4766af(this.f4122a.mo46024f(), this.f4122a.mo46025g(), this.f4122a.mo46018c(), this.f4122a.mo46022e().f4223c, this.f4129p));
                }
                if (C4805f.m2789b()) {
                    this.f4122a.mo46007a(this.f4119L.mo45998c(), this.f4119L.mo45999d());
                }
                m2548a((Runnable) new C4824o(this.f4124c, C4825a.POST_CONSUME, "https://" + this.f4122a.mo46024f() + "/fp/clear.png", this.f4122a.mo46033q(), this.f4122a.mo46032p(), this, this.f4125l, this.f4123b));
                THMStatusCode status = m2546a(true);
                this.f4122a.mo46008a(status);
                if (status != THMStatusCode.THM_OK) {
                    C4834w.m2899b(f4100d, "Received " + status.getDesc() + " error, profiling will be incomplete");
                    this.f4122a.mo46008a(THMStatusCode.THM_PartialProfile);
                } else {
                    this.f4109B = System.currentTimeMillis();
                }
                m2559e();
                C4834w.m2901c(f4100d, "profile request complete");
                if (this.f4123b.mo45949a()) {
                    this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                    Thread.interrupted();
                }
                ProfilingResult result5 = getResult();
                EndNotifierBase notifier5 = this.f4136w;
                this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
                this.f4123b.mo45957h();
                f4102f.execute(new C4754a(result5, notifier5));
            }
        } catch (InterruptedException e) {
            if (!this.f4123b.mo45949a()) {
                C4834w.m2902c(f4100d, "profile request interrupted", e);
            } else {
                C4834w.m2901c(f4100d, "profile request interrupted due to cancel");
            }
            this.f4122a.mo46008a(THMStatusCode.THM_Internal_Error);
            if (this.f4123b.mo45949a()) {
                this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            ProfilingResult result6 = getResult();
            EndNotifierBase notifier6 = this.f4136w;
            this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
            this.f4123b.mo45957h();
            f4102f.execute(new C4754a(result6, notifier6));
        } catch (Exception e2) {
            this.f4122a.mo46008a(THMStatusCode.THM_Internal_Error);
            C4834w.m2902c(f4100d, "profile request failed", e2);
            if (this.f4123b.mo45949a()) {
                this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            ProfilingResult result7 = getResult();
            EndNotifierBase notifier7 = this.f4136w;
            this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
            this.f4123b.mo45957h();
            f4102f.execute(new C4754a(result7, notifier7));
        } catch (Throwable th) {
            if (this.f4123b.mo45949a()) {
                this.f4122a.mo46008a(THMStatusCode.THM_Interrupted_Error);
                Thread.interrupted();
            }
            ProfilingResult result8 = getResult();
            EndNotifierBase notifier8 = this.f4136w;
            this.f4122a.f4293X = System.currentTimeMillis() - this.f4122a.f4292W;
            this.f4123b.mo45957h();
            f4102f.execute(new C4754a(result8, notifier8));
            throw th;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo45923b() {
        if (NativeGatherer.m2512a().mo45864b()) {
            this.f4137x = new C4772ak();
            try {
                String temp = NativeGatherer.m2512a().mo45866c("enableOptions");
                if (temp != null) {
                    this.f4137x.mo45973a(Long.parseLong(temp));
                }
                String temp2 = NativeGatherer.m2512a().mo45866c("disableOptions");
                if (temp2 != null) {
                    this.f4137x.mo45977b(Long.parseLong(temp2));
                }
                String temp3 = NativeGatherer.m2512a().mo45866c("quietPeriod");
                if (temp3 != null) {
                    this.f4137x.mo45972a(Integer.parseInt(temp3));
                }
                String temp4 = NativeGatherer.m2512a().mo45866c("sdkVersion");
                if (temp4 != null) {
                    this.f4137x.mo45974a(temp4);
                } else {
                    this.f4137x = null;
                }
            } catch (NumberFormatException e) {
                C4834w.m2902c(f4100d, "Options/ quietPeriod are not a number", e);
                this.f4137x = null;
            } catch (InterruptedException e2) {
                C4834w.m2902c(f4100d, "Interrupted", e2);
                this.f4137x = null;
            }
        }
        if (this.f4137x == null || !this.f4137x.mo45978c().equals("4.0-90")) {
            C4811l settings = new C4811l(this.f4125l, this.f4134u, 0);
            try {
                this.f4137x = new C4772ak();
                this.f4137x.mo45973a(settings.mo46050a("enableOptions", 0));
                this.f4137x.mo45977b(settings.mo46050a("disableOptions", 0));
                this.f4137x.mo45974a(settings.mo46051a("sdkVersion", ""));
                this.f4137x.mo45972a(settings.mo46049a("quietPeriod", 0));
            } catch (ClassCastException e3) {
                C4834w.m2902c(f4100d, "Found preference of different type", e3);
                this.f4137x = null;
            }
        }
        if (this.f4137x != null && !this.f4137x.mo45978c().equals("4.0-90")) {
            this.f4137x = null;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo45921a(THMStatusCode status) {
        this.f4122a.mo46008a(status);
    }

    /* renamed from: a */
    private THMStatusCode m2546a(boolean failedOkay) throws InterruptedException {
        THMStatusCode statusCode = THMStatusCode.THM_NotYet;
        try {
            this.f4117J.lockInterruptibly();
            Iterator i$ = this.f4115H.iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                C4837y t = (C4837y) i$.next();
                if (this.f4123b.mo45949a() || Thread.currentThread().isInterrupted()) {
                    statusCode = THMStatusCode.THM_Interrupted_Error;
                } else {
                    t.join((long) this.f4129p);
                    if (t.getState() != State.TERMINATED) {
                        C4834w.m2894a(f4100d, "Connection hasn't completed before the timeout expired, aborting");
                        statusCode = THMStatusCode.THM_Connection_Error;
                        if (!failedOkay) {
                            m2553b(true);
                            break;
                        }
                        m2550a((Thread) t);
                    } else {
                        C4824o runner = t.mo46102a();
                        if (runner != null) {
                            THMStatusCode tempStatus = t.mo46102a().mo46078a();
                            if (tempStatus == THMStatusCode.THM_OK && runner.mo46087b() != 200) {
                                C4834w.m2901c(f4100d, "Connection returned http status code:" + runner.mo46087b());
                                statusCode = THMStatusCode.THM_Connection_Error;
                                if (!failedOkay) {
                                    m2553b(true);
                                    break;
                                }
                            } else if (tempStatus != THMStatusCode.THM_OK) {
                                C4834w.m2901c(f4100d, "Connection returned status :" + runner.mo46078a().getDesc());
                                statusCode = tempStatus;
                                if (!failedOkay) {
                                    m2553b(true);
                                    break;
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            if (!this.f4123b.mo45949a()) {
                if (statusCode == THMStatusCode.THM_NotYet) {
                    statusCode = THMStatusCode.THM_Connection_Error;
                }
                m2553b(true);
                C4834w.m2902c(f4100d, "thread join: this thread = TID " + Thread.currentThread().getId(), e);
            }
        } catch (Throwable th) {
            this.f4117J.unlock();
            throw th;
        }
        this.f4117J.unlock();
        if (statusCode == THMStatusCode.THM_NotYet) {
            return THMStatusCode.THM_OK;
        }
        return statusCode;
    }

    /* renamed from: b */
    private void m2553b(boolean listLocked) {
        if (!listLocked) {
            try {
                this.f4117J.lock();
            } catch (Throwable th) {
                if (!listLocked) {
                    this.f4117J.unlock();
                }
                throw th;
            }
        }
        Iterator i$ = this.f4115H.iterator();
        while (i$.hasNext()) {
            m2550a((Thread) (C4837y) i$.next());
        }
        if (!listLocked) {
            this.f4117J.unlock();
        }
    }

    /* renamed from: a */
    private void m2550a(Thread t) {
        f4102f.execute(new C4755b(t));
    }

    /* renamed from: d */
    private void m2558d() {
        this.f4122a.mo46003a();
        this.f4119L.mo45992a();
    }

    /* renamed from: e */
    private void m2559e() throws InterruptedException {
        try {
            this.f4118K.lockInterruptibly();
            this.f4115H.clear();
        } finally {
            this.f4118K.unlock();
        }
    }

    /* renamed from: a */
    private C4837y m2548a(Runnable r) {
        if (r == null) {
            return null;
        }
        if (this.f4123b.mo45949a()) {
            return null;
        }
        try {
            C4837y t = new C4837y(r);
            if (r instanceof C4824o) {
                C4834w.m2901c(f4100d, "Adding thread ID: " + t.getId() + " for: " + ((C4824o) r).f4623c);
                this.f4118K.lock();
                this.f4115H.add(t);
                this.f4118K.unlock();
            }
            t.start();
            return t;
        } catch (RuntimeException e) {
            String str = f4100d;
            return null;
        } catch (Throwable th) {
            this.f4118K.unlock();
            throw th;
        }
    }

    /* renamed from: f */
    private boolean m2562f() {
        if (!C4809j.m2799a()) {
            return true;
        }
        if (C4800a.f4363c >= C4801b.f4377m) {
            return C4830r.m2882a(this.f4125l);
        }
        try {
            Object powerService = this.f4125l.getSystemService("power");
            if (powerService == null || !(powerService instanceof PowerManager)) {
                return true;
            }
            return ((PowerManager) powerService).isScreenOn();
        } catch (SecurityException e) {
            String str = f4100d;
            return true;
        } catch (Exception e2) {
            C4834w.m2901c(f4100d, e2.getMessage());
            return true;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: g */
    public boolean m2564g() {
        if (f4105i) {
            this.f4124c = new C4838z();
            try {
                C4777am amVar = this.f4124c;
                Context context = this.f4125l;
                amVar.mo45946a(this.f4129p, this.f4122a.f4272C, true, true);
                return true;
            } catch (RuntimeException e) {
                if (e instanceof IllegalStateException) {
                    C4834w.m2894a(f4100d, "Failed to build OkHttp3 client, most probably because of TLS factory");
                    if (!f4106j) {
                        C4834w.m2901c(f4100d, "Okhttp2 is not available going to okhttp3 without TLS");
                        try {
                            C4777am amVar2 = this.f4124c;
                            Context context2 = this.f4125l;
                            amVar2.mo45946a(this.f4129p, this.f4122a.f4272C, true, false);
                            return true;
                        } catch (RuntimeException e2) {
                            C4834w.m2901c(f4100d, "Failed to build OkHttp3 client even without TLS factory");
                        }
                    } else {
                        String str = f4100d;
                    }
                } else {
                    C4834w.m2901c(f4100d, "Failed to build OkHttp3 client");
                }
            }
        }
        if (f4106j) {
            this.f4124c = new C4761ab();
            try {
                C4777am amVar3 = this.f4124c;
                Context context3 = this.f4125l;
                amVar3.mo45946a(this.f4129p, this.f4122a.f4272C, true, true);
                return true;
            } catch (RuntimeException e3) {
                C4834w.m2901c(f4100d, "Failed to build okhttp2 client, init failed.");
            }
        } else {
            C4834w.m2894a(f4100d, "OkHttp3 and okHttp2 libraries can't be found aborting init()");
            return false;
        }
    }
}
