package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* renamed from: com.threatmetrix.TrustDefender.l */
class C4820l {

    /* renamed from: A */
    static Method f4554A;

    /* renamed from: B */
    static Method f4555B;

    /* renamed from: C */
    static Method f4556C;

    /* renamed from: D */
    static Method f4557D;

    /* renamed from: E */
    static Method f4558E;

    /* renamed from: F */
    static Method f4559F;

    /* renamed from: G */
    static Method f4560G;

    /* renamed from: H */
    static Method f4561H;

    /* renamed from: I */
    static Method f4562I;

    /* renamed from: J */
    static Object f4563J;

    /* renamed from: K */
    static Object f4564K;

    /* renamed from: L */
    static Object f4565L;

    /* renamed from: M */
    static Object f4566M;

    /* renamed from: N */
    static boolean f4567N;

    /* renamed from: O */
    private static final String f4568O = C4834w.m2892a(C4820l.class);

    /* renamed from: U */
    private static String f4569U = null;

    /* renamed from: a */
    static Class<?> f4570a;

    /* renamed from: b */
    static Class<?> f4571b;

    /* renamed from: c */
    static Class<?> f4572c;

    /* renamed from: d */
    static Class<?> f4573d;

    /* renamed from: e */
    static Class<?> f4574e;

    /* renamed from: f */
    static Class<?> f4575f;

    /* renamed from: g */
    static Class<?> f4576g;

    /* renamed from: h */
    static Class<?> f4577h;

    /* renamed from: i */
    static Class<?> f4578i;

    /* renamed from: j */
    static Class<?> f4579j;

    /* renamed from: k */
    static Class<?> f4580k;

    /* renamed from: l */
    static Class<?> f4581l;

    /* renamed from: m */
    static Class<?> f4582m;

    /* renamed from: n */
    static Field f4583n;

    /* renamed from: o */
    static Field f4584o;

    /* renamed from: p */
    static Field f4585p;

    /* renamed from: q */
    static Field f4586q;

    /* renamed from: r */
    static Field f4587r;

    /* renamed from: s */
    static Field f4588s;

    /* renamed from: t */
    static Method f4589t;

    /* renamed from: u */
    static Method f4590u;

    /* renamed from: v */
    static Method f4591v;

    /* renamed from: w */
    static Method f4592w;

    /* renamed from: x */
    static Method f4593x;

    /* renamed from: y */
    static Method f4594y;

    /* renamed from: z */
    static Method f4595z;

    /* renamed from: P */
    private Object f4596P;

    /* renamed from: Q */
    private Object f4597Q;

    /* renamed from: R */
    private Object f4598R;

    /* renamed from: S */
    private Object f4599S;

    /* renamed from: T */
    private Object f4600T;

    /* renamed from: V */
    private volatile C4821a f4601V = C4821a.API_NOT_INITIATED;

    /* renamed from: com.threatmetrix.TrustDefender.l$a */
    enum C4821a {
        LIBRARY_NOT_AVAILABLE(false),
        API_NOT_INITIATED(false),
        API_AVAILABLE(false),
        NOT_AVAILABLE(false),
        LOCATION_SERVICES_CONNECT_REQUEST_SENT(false),
        LOCATION_SERVICES_CONNECTED(true),
        LOCATION_UPDATE_REGISTER_REQUEST_SENT(true),
        LOCATION_LISTENER_REGISTERED(true),
        LOCATION_SERVICES_PAUSED(true),
        LOCATION_SERVICES_DISCONNECTED(false);
        

        /* renamed from: k */
        private boolean f4613k;

        private C4821a(boolean isAccessible) {
            this.f4613k = isAccessible;
        }

        /* access modifiers changed from: 0000 */
        /* renamed from: a */
        public final boolean mo46077a() {
            return this.f4613k;
        }
    }

    C4820l() {
    }

    static {
        f4563J = Integer.valueOf(105);
        f4564K = Integer.valueOf(104);
        f4565L = Integer.valueOf(100);
        f4566M = Integer.valueOf(102);
        f4567N = false;
        Class<?> b = C4787at.m2745b("com.google.android.gms.common.GooglePlayServicesUtil");
        f4570a = b;
        if (b == null) {
            Class<?> b2 = C4787at.m2745b("com.google.android.gms.common.GoogleApiAvailability");
            f4571b = b2;
            if (b2 == null) {
                return;
            }
        }
        Class<?> b3 = C4787at.m2745b("com.google.android.gms.common.api.GoogleApiClient$Builder");
        f4572c = b3;
        if (b3 != null) {
            Class<?> b4 = C4787at.m2745b("com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks");
            f4573d = b4;
            if (b4 != null) {
                Class<?> b5 = C4787at.m2745b("com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener");
                f4574e = b5;
                if (b5 != null) {
                    Class<?> b6 = C4787at.m2745b("com.google.android.gms.location.LocationServices");
                    f4575f = b6;
                    if (b6 != null) {
                        Class<?> b7 = C4787at.m2745b("com.google.android.gms.common.api.Api");
                        f4576g = b7;
                        if (b7 != null) {
                            Class<?> b8 = C4787at.m2745b("com.google.android.gms.common.api.GoogleApiClient");
                            f4577h = b8;
                            if (b8 != null) {
                                Class<?> b9 = C4787at.m2745b("com.google.android.gms.location.LocationRequest");
                                f4578i = b9;
                                if (b9 != null) {
                                    Class<?> b10 = C4787at.m2745b("com.google.android.gms.location.LocationListener");
                                    f4579j = b10;
                                    if (b10 != null) {
                                        Class<?> b11 = C4787at.m2745b("com.google.android.gms.common.api.PendingResult");
                                        f4580k = b11;
                                        if (b11 != null) {
                                            Class<?> b12 = C4787at.m2745b("com.google.android.gms.common.api.Status");
                                            f4581l = b12;
                                            if (b12 != null) {
                                                Class<?> b13 = C4787at.m2745b("com.google.android.gms.common.api.ResultCallback");
                                                f4582m = b13;
                                                if (b13 != null) {
                                                    Field a = C4787at.m2742a((Class) f4575f, "API");
                                                    f4583n = a;
                                                    if (a != null) {
                                                        Field a2 = C4787at.m2742a((Class) f4575f, "FusedLocationApi");
                                                        f4584o = a2;
                                                        if (a2 != null) {
                                                            Field a3 = C4787at.m2742a((Class) f4578i, "PRIORITY_NO_POWER");
                                                            f4585p = a3;
                                                            if (a3 != null) {
                                                                Field a4 = C4787at.m2742a((Class) f4578i, "PRIORITY_LOW_POWER");
                                                                f4586q = a4;
                                                                if (a4 != null) {
                                                                    Field a5 = C4787at.m2742a((Class) f4578i, "PRIORITY_HIGH_ACCURACY");
                                                                    f4587r = a5;
                                                                    if (a5 != null) {
                                                                        Field a6 = C4787at.m2742a((Class) f4578i, "PRIORITY_BALANCED_POWER_ACCURACY");
                                                                        f4588s = a6;
                                                                        if (a6 != null) {
                                                                            Method a7 = C4787at.m2743a((Class) f4572c, "build", new Class[0]);
                                                                            f4594y = a7;
                                                                            if (a7 != null) {
                                                                                Method a8 = C4787at.m2743a((Class) f4577h, "connect", new Class[0]);
                                                                                f4595z = a8;
                                                                                if (a8 != null) {
                                                                                    Method a9 = C4787at.m2743a((Class) f4581l, "isSuccess", new Class[0]);
                                                                                    f4562I = a9;
                                                                                    if (a9 != null) {
                                                                                        Method a10 = C4787at.m2743a((Class) f4577h, "disconnect", new Class[0]);
                                                                                        f4554A = a10;
                                                                                        if (a10 != null) {
                                                                                            Method a11 = C4787at.m2743a((Class) f4572c, "addApi", f4576g);
                                                                                            f4593x = a11;
                                                                                            if (a11 != null) {
                                                                                                Method a12 = C4787at.m2743a((Class) f4578i, "setPriority", Integer.TYPE);
                                                                                                f4557D = a12;
                                                                                                if (a12 != null) {
                                                                                                    Method a13 = C4787at.m2743a((Class) f4578i, "setInterval", Long.TYPE);
                                                                                                    f4555B = a13;
                                                                                                    if (a13 != null) {
                                                                                                        Method a14 = C4787at.m2743a((Class) f4578i, "setFastestInterval", Long.TYPE);
                                                                                                        f4556C = a14;
                                                                                                        if (a14 != null) {
                                                                                                            Method a15 = C4787at.m2743a((Class) f4572c, "addConnectionCallbacks", f4573d);
                                                                                                            f4591v = a15;
                                                                                                            if (a15 != null) {
                                                                                                                Method a16 = C4787at.m2743a((Class) f4572c, "addOnConnectionFailedListener", f4574e);
                                                                                                                f4592w = a16;
                                                                                                                if (a16 != null) {
                                                                                                                    Method a17 = C4787at.m2743a((Class) f4580k, "setResultCallback", f4582m);
                                                                                                                    f4561H = a17;
                                                                                                                    if (a17 != null) {
                                                                                                                        Method a18 = C4787at.m2743a((Class) f4570a, "isGooglePlayServicesAvailable", Context.class);
                                                                                                                        f4590u = a18;
                                                                                                                        if (a18 == null) {
                                                                                                                            Method a19 = C4787at.m2743a((Class) f4571b, "isGooglePlayServicesAvailable", Context.class);
                                                                                                                            f4590u = a19;
                                                                                                                            if (a19 == null) {
                                                                                                                                Method a20 = C4787at.m2743a((Class) f4571b, "getInstance", new Class[0]);
                                                                                                                                f4589t = a20;
                                                                                                                                if (a20 == null) {
                                                                                                                                    return;
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                        Method a21 = C4787at.m2743a(f4584o.getType(), "getLastLocation", f4577h);
                                                                                                                        f4560G = a21;
                                                                                                                        if (a21 != null) {
                                                                                                                            Method a22 = C4787at.m2743a(f4584o.getType(), "removeLocationUpdates", f4577h, f4579j);
                                                                                                                            f4559F = a22;
                                                                                                                            if (a22 != null) {
                                                                                                                                Method a23 = C4787at.m2743a(f4584o.getType(), "requestLocationUpdates", f4577h, f4578i, f4579j);
                                                                                                                                f4558E = a23;
                                                                                                                                if (a23 != null) {
                                                                                                                                    Object a24 = C4787at.m2740a((Object) null, f4585p);
                                                                                                                                    f4563J = a24;
                                                                                                                                    if (a24 != null) {
                                                                                                                                        Object a25 = C4787at.m2740a((Object) null, f4586q);
                                                                                                                                        f4564K = a25;
                                                                                                                                        if (a25 != null) {
                                                                                                                                            Object a26 = C4787at.m2740a((Object) null, f4587r);
                                                                                                                                            f4565L = a26;
                                                                                                                                            if (a26 != null) {
                                                                                                                                                Object a27 = C4787at.m2740a((Object) null, f4588s);
                                                                                                                                                f4566M = a27;
                                                                                                                                                if (a27 != null) {
                                                                                                                                                    f4567N = true;
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                        }
                                                                                                                    }
                                                                                                                }
                                                                                                            }
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized C4821a mo46064a(Context context) {
        C4821a aVar;
        if (!f4567N || this.f4601V == C4821a.LIBRARY_NOT_AVAILABLE) {
            aVar = C4821a.LIBRARY_NOT_AVAILABLE;
            this.f4601V = aVar;
        } else {
            if (this.f4601V == C4821a.API_NOT_INITIATED || this.f4601V == C4821a.NOT_AVAILABLE) {
                Object isAvailable = C4787at.m2741a((Object) null, f4590u, context);
                if (isAvailable == null || !isAvailable.equals(Integer.valueOf(0))) {
                    Object instance = C4787at.m2741a((Object) null, f4589t, new Object[0]);
                    if (instance != null) {
                        Object isAvailable2 = C4787at.m2741a(instance, f4590u, context);
                        if (isAvailable2 == null) {
                            aVar = C4821a.NOT_AVAILABLE;
                            this.f4601V = aVar;
                        } else if (isAvailable2.equals(Integer.valueOf(0))) {
                            aVar = C4821a.API_AVAILABLE;
                            this.f4601V = aVar;
                        }
                    }
                } else {
                    aVar = C4821a.API_AVAILABLE;
                    this.f4601V = aVar;
                }
            }
            aVar = this.f4601V;
        }
        return aVar;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final Object mo46065a(Method method, Object[] args) {
        if ("toString".equals(method.getName())) {
            return method.getName();
        }
        if ("hashCode".equals(method.getName())) {
            return Integer.valueOf(hashCode());
        }
        if (!InternalLogger.EVENT_PARAM_EXTRAS_EQUALS.equals(method.getName())) {
            return null;
        }
        if (args == null || args.length != 2) {
            return Boolean.valueOf(true);
        }
        return Boolean.valueOf(args[0].equals(args[1]));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized void mo46067a(Context context, long interval, long fastestInterval, int accuracy, InvocationHandler connectCallBackHandler, InvocationHandler locationListener, InvocationHandler passiveLocationListener) {
        if (this.f4601V == C4821a.API_AVAILABLE) {
            Object builderInstance = C4787at.m2738a((Class) f4572c, new Class[]{Context.class}, new Object[]{context});
            if (builderInstance != null) {
                Object proxy = C4787at.m2739a(f4573d.getClassLoader(), (Class<?>[]) new Class[]{f4573d}, connectCallBackHandler);
                Object builderInstance2 = C4787at.m2741a(builderInstance, f4591v, proxy);
                if (builderInstance2 != null) {
                    Object failedListenerProxy = C4787at.m2739a(f4574e.getClassLoader(), (Class<?>[]) new Class[]{f4574e}, connectCallBackHandler);
                    Object builderInstance3 = C4787at.m2741a(builderInstance2, f4592w, failedListenerProxy);
                    if (builderInstance3 != null) {
                        Object builderInstance4 = C4787at.m2741a(builderInstance3, f4593x, C4787at.m2740a((Object) null, f4583n));
                        if (builderInstance4 != null) {
                            this.f4596P = C4787at.m2741a(builderInstance4, f4594y, new Object[0]);
                            if (this.f4596P != null) {
                                Object valueOf = Integer.valueOf(102);
                                if (f4567N) {
                                    switch (accuracy) {
                                        case 1:
                                        case 2:
                                            valueOf = f4566M;
                                            break;
                                        case 3:
                                            valueOf = f4565L;
                                            break;
                                        default:
                                            valueOf = f4566M;
                                            break;
                                    }
                                }
                                this.f4597Q = m2830a(interval, fastestInterval, ((Integer) valueOf).intValue());
                                this.f4598R = m2830a(interval, fastestInterval, ((Integer) f4563J).intValue());
                                if (!(this.f4597Q == null || this.f4598R == null)) {
                                    this.f4599S = C4787at.m2739a(f4579j.getClassLoader(), (Class<?>[]) new Class[]{f4579j}, locationListener);
                                    this.f4600T = C4787at.m2739a(f4579j.getClassLoader(), (Class<?>[]) new Class[]{f4579j}, passiveLocationListener);
                                    C4787at.m2741a(this.f4596P, f4595z, new Object[0]);
                                    this.f4601V = C4821a.LOCATION_SERVICES_CONNECT_REQUEST_SENT;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized void mo46069a(Object locationRequestInstance, Object fusedLocationListener, InvocationHandler resultCallbackHandler) {
        if (f4567N && this.f4601V.mo46077a()) {
            Object pendingResult = C4787at.m2741a(C4787at.m2740a((Object) null, f4584o), f4558E, this.f4596P, locationRequestInstance, fusedLocationListener);
            this.f4601V = C4821a.LOCATION_UPDATE_REGISTER_REQUEST_SENT;
            if (!(this.f4601V == C4821a.LOCATION_LISTENER_REGISTERED || pendingResult == null)) {
                Object resultCallbackProxy = C4787at.m2739a(f4582m.getClassLoader(), (Class<?>[]) new Class[]{f4582m}, resultCallbackHandler);
                C4787at.m2741a(pendingResult, f4561H, resultCallbackProxy);
            }
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final synchronized void mo46066a() {
        if (f4567N && this.f4601V.mo46077a() && this.f4601V != C4821a.LOCATION_SERVICES_PAUSED) {
            C4787at.m2741a(C4787at.m2740a((Object) null, f4584o), f4559F, this.f4596P, this.f4599S);
            this.f4601V = C4821a.LOCATION_SERVICES_PAUSED;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final String mo46071b(Context context) {
        if (f4569U == null && mo46064a(context) == C4821a.API_AVAILABLE) {
            Class advertisingClass = C4787at.m2745b("com.google.android.gms.ads.identifier.AdvertisingIdClient");
            if (advertisingClass == null) {
                return null;
            }
            Class advertisingInfoClass = C4787at.m2745b("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            if (advertisingInfoClass == null) {
                return null;
            }
            Method getInfoMethod = C4787at.m2743a(advertisingInfoClass, "getId", new Class[0]);
            if (getInfoMethod == null) {
                return null;
            }
            Method getAdvertisingInfoMethod = C4787at.m2743a(advertisingClass, "getAdvertisingIdInfo", Context.class);
            if (getAdvertisingInfoMethod == null) {
                return null;
            }
            Object advertisingInfo = C4787at.m2741a((Object) null, getAdvertisingInfoMethod, context);
            if (advertisingInfo != null) {
                Object id = C4787at.m2741a(advertisingInfo, getInfoMethod, new Object[0]);
                if (id != null) {
                    String valueOf = String.valueOf(id);
                    f4569U = valueOf;
                    return valueOf;
                }
            }
        }
        return f4569U;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final C4821a mo46070b() {
        return this.f4601V;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46068a(C4821a m_GooglePlayApiStatus) {
        this.f4601V = m_GooglePlayApiStatus;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final Object mo46072c() {
        return this.f4596P;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final Object mo46073d() {
        return this.f4597Q;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final Object mo46074e() {
        return this.f4598R;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final Object mo46075f() {
        return this.f4599S;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final Object mo46076g() {
        return this.f4600T;
    }

    /* renamed from: h */
    static boolean m2831h() {
        return f4567N;
    }

    /* renamed from: a */
    private static Object m2830a(long interval, long fastestInterval, int priority) {
        if (!f4567N) {
            return null;
        }
        Object locReqInstance = C4787at.m2738a((Class) f4578i, (Class[]) null, (Object[]) null);
        C4787at.m2741a(locReqInstance, f4555B, Long.valueOf(interval));
        C4787at.m2741a(locReqInstance, f4556C, Long.valueOf(fastestInterval));
        C4787at.m2741a(locReqInstance, f4557D, Integer.valueOf(priority));
        return locReqInstance;
    }
}
