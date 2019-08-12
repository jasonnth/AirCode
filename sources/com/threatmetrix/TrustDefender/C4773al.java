package com.threatmetrix.TrustDefender;

import android.content.Context;
import android.location.Location;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.al */
class C4773al extends C4820l {
    /* access modifiers changed from: private */

    /* renamed from: O */
    public static final String f4232O = C4834w.m2892a(C4773al.class);
    /* access modifiers changed from: private */

    /* renamed from: P */
    public Object f4233P;

    /* renamed from: Q */
    private Object f4234Q;

    /* renamed from: R */
    private Object f4235R;

    /* renamed from: S */
    private Object f4236S;

    /* renamed from: T */
    private Object f4237T;
    /* access modifiers changed from: private */

    /* renamed from: U */
    public C4779ao f4238U;
    /* access modifiers changed from: private */

    /* renamed from: V */
    public C4780ap f4239V;
    /* access modifiers changed from: private */

    /* renamed from: W */
    public volatile boolean f4240W = false;
    /* access modifiers changed from: private */

    /* renamed from: X */
    public final Object f4241X = new Object();

    /* renamed from: com.threatmetrix.TrustDefender.al$a */
    class C4774a implements InvocationHandler {
        C4774a() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if ("onConnected".equals(method.getName())) {
                if (!C4820l.m2831h()) {
                    return null;
                }
                Object locationResult = C4787at.m2741a(C4787at.m2740a((Object) null, C4820l.f4584o), C4820l.f4560G, C4773al.this.f4233P);
                if (locationResult != null) {
                    C4773al.this.f4238U.onLocationChanged((Location) locationResult);
                }
                synchronized (C4773al.this.f4241X) {
                    C4773al.this.mo46068a(C4821a.LOCATION_SERVICES_CONNECTED);
                    if (!C4773al.this.f4240W) {
                        C4773al.this.mo45982i();
                    }
                }
                return null;
            } else if ("onConnectionSuspended".equals(method.getName())) {
                if (args == null || args.length <= 0) {
                    return null;
                }
                C4834w.m2899b(C4773al.f4232O, "Connection to the Google Service is suspended, the error code is " + args[0]);
                return null;
            } else if (!"onConnectionFailed".equals(method.getName())) {
                return C4773al.this.mo46065a(method, args);
            } else {
                if (args == null || args.length <= 0) {
                    return null;
                }
                C4834w.m2899b(C4773al.f4232O, "Connection to the Google Service is failed. The error code is " + args[0]);
                return null;
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.al$b */
    class C4775b implements InvocationHandler {
        C4775b() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!"onLocationChanged".equals(method.getName())) {
                return C4773al.this.mo46065a(method, args);
            }
            if (args != null && args.length > 0) {
                C4773al.this.f4238U.onLocationChanged(args[0]);
            }
            return null;
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.al$c */
    class C4776c implements InvocationHandler {
        C4776c() {
        }

        public final Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (!"onResult".equals(method.getName())) {
                return C4773al.this.mo46065a(method, args);
            }
            if (args != null && args.length > 0) {
                Object result = C4787at.m2741a(args[0], C4820l.f4562I, new Object[0]);
                synchronized (this) {
                    if (result != null) {
                        if (((Boolean) result).booleanValue() && C4773al.this.mo46070b() != C4821a.LOCATION_LISTENER_REGISTERED) {
                            C4773al.this.mo46068a(C4821a.LOCATION_LISTENER_REGISTERED);
                            C4773al.this.f4239V.mo46000e();
                        }
                    }
                }
            }
            return null;
        }
    }

    C4773al() {
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized void mo45980a(Context context, long interval, long fastestInterval, int accuracy, C4779ao tdLocationListener, C4780ap tdLocationManager) {
        if (mo46064a(context) == C4821a.API_AVAILABLE) {
            this.f4239V = tdLocationManager;
            this.f4238U = tdLocationListener;
            mo46067a(context, interval, fastestInterval, accuracy, new C4774a(), new C4775b(), new C4775b());
            this.f4237T = mo46076g();
            this.f4234Q = mo46073d();
            this.f4233P = mo46072c();
            this.f4235R = mo46074e();
            this.f4236S = mo46075f();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: i */
    public final void mo45982i() {
        InvocationHandler resultCallbackHandler = new C4776c();
        mo46069a(this.f4234Q, this.f4236S, resultCallbackHandler);
        mo46069a(this.f4235R, this.f4237T, resultCallbackHandler);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public final synchronized void mo45983j() {
        mo46066a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo45981a(boolean isPaused) {
        this.f4240W = isPaused;
    }
}
