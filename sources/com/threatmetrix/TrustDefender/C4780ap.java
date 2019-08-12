package com.threatmetrix.TrustDefender;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* renamed from: com.threatmetrix.TrustDefender.ap */
class C4780ap {
    /* access modifiers changed from: private */

    /* renamed from: a */
    public static final String f4248a = C4834w.m2892a(C4780ap.class);

    /* renamed from: b */
    private int f4249b;

    /* renamed from: c */
    private long f4250c;

    /* renamed from: d */
    private long f4251d;

    /* renamed from: e */
    private boolean f4252e = false;

    /* renamed from: f */
    private boolean f4253f = false;

    /* renamed from: g */
    private boolean f4254g = false;

    /* renamed from: h */
    private boolean f4255h = false;

    /* renamed from: i */
    private Context f4256i;

    /* renamed from: j */
    private Location f4257j;

    /* renamed from: k */
    private LocationManager f4258k;

    /* renamed from: l */
    private C4779ao f4259l;

    /* renamed from: m */
    private C4773al f4260m;

    /* renamed from: n */
    private C4781a f4261n = C4781a.NONE;

    /* renamed from: com.threatmetrix.TrustDefender.ap$a */
    enum C4781a {
        NONE,
        FINE,
        COARSE
    }

    /* renamed from: com.threatmetrix.TrustDefender.ap$b */
    class C4782b extends BroadcastReceiver {
        C4782b() {
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.BATTERY_LOW".equals(intent.getAction())) {
                C4780ap.this.mo45992a();
            } else {
                C4780ap.this.mo45996b();
            }
        }
    }

    /* renamed from: com.threatmetrix.TrustDefender.ap$c */
    class C4783c extends BroadcastReceiver {
        C4783c() {
        }

        public final void onReceive(Context context, Intent intent) {
            if (C4806g.m2790a()) {
                try {
                    Object connectivityService = context.getSystemService("connectivity");
                    if (connectivityService != null && (connectivityService instanceof ConnectivityManager)) {
                        NetworkInfo activeNetwork = ((ConnectivityManager) connectivityService).getActiveNetworkInfo();
                        if (!(activeNetwork != null && activeNetwork.isConnectedOrConnecting())) {
                            C4780ap.this.mo45992a();
                        } else {
                            C4780ap.this.mo45996b();
                        }
                    }
                } catch (SecurityException e) {
                    C4780ap.f4248a;
                } catch (Exception e2) {
                    C4834w.m2901c(C4780ap.f4248a, e2.getMessage());
                }
            }
        }
    }

    C4780ap() {
    }

    /* renamed from: a */
    public final void mo45993a(Context context, long lowPowerUpdateTime, long highPowerUpdateTime, int accuracy) {
        if (!C4805f.m2789b()) {
            this.f4255h = false;
        }
        if (this.f4255h) {
            this.f4256i = context;
            this.f4250c = lowPowerUpdateTime;
            this.f4251d = highPowerUpdateTime;
            this.f4249b = accuracy;
            this.f4259l = new C4779ao();
            C4808i packageManager = new C4808i(context);
            if (packageManager.mo46048a("android.permission.ACCESS_COARSE_LOCATION", context.getPackageName())) {
                this.f4261n = C4781a.COARSE;
            }
            if (packageManager.mo46048a("android.permission.ACCESS_FINE_LOCATION", context.getPackageName())) {
                this.f4261n = C4781a.FINE;
            }
            try {
                this.f4260m = new C4773al();
                this.f4260m.mo45980a(this.f4256i, this.f4251d, this.f4250c, this.f4249b, this.f4259l, this);
                m2678j();
                if (m2676h() || m2675g()) {
                    IntentFilter intentFilter = new IntentFilter();
                    intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                    this.f4256i.registerReceiver(new C4783c(), intentFilter);
                    if (this.f4254g) {
                        IntentFilter intentFilter2 = new IntentFilter();
                        intentFilter2.addAction("android.intent.action.BATTERY_LOW");
                        intentFilter2.addAction("android.intent.action.BATTERY_OKAY");
                        intentFilter2.addAction("android.intent.action.ACTION_POWER_CONNECTED");
                        this.f4256i.registerReceiver(new C4782b(), intentFilter2);
                    }
                }
            } catch (SecurityException e) {
                String str = f4248a;
                this.f4255h = false;
            } catch (Exception e2) {
                C4834w.m2901c(f4248a, e2.getMessage());
                this.f4255h = false;
            }
        }
    }

    /* renamed from: a */
    public final void mo45992a() {
        if (this.f4255h && !this.f4252e) {
            if (this.f4260m != null) {
                this.f4260m.mo45981a(true);
            }
            if (m2676h()) {
                this.f4260m.mo45983j();
                C4834w.m2901c(f4248a, "paused FUSED location services");
            } else {
                mo46000e();
                C4834w.m2901c(f4248a, "paused NON-FUSED location services");
            }
            this.f4252e = true;
        }
    }

    /* renamed from: b */
    public final void mo45996b() {
        if (this.f4255h && this.f4252e) {
            if (this.f4260m != null) {
                this.f4260m.mo45981a(false);
            }
            if (m2676h()) {
                C4834w.m2901c(f4248a, "resuming FUSED location services");
                this.f4260m.mo45982i();
            } else {
                C4834w.m2901c(f4248a, "resuming NON-FUSED location services");
                m2678j();
            }
            this.f4252e = false;
        }
    }

    /* renamed from: c */
    public final Location mo45998c() {
        Location loc = this.f4257j;
        if (loc != null || this.f4259l == null || !this.f4255h) {
            return loc;
        }
        return this.f4259l.mo45987a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo45994a(Location location) {
        m2673a(location, true);
    }

    /* renamed from: a */
    private void m2673a(Location location, boolean isManualLocation) {
        if (location != null) {
            this.f4257j = new Location(location);
            this.f4253f = isManualLocation;
            return;
        }
        this.f4257j = null;
        this.f4253f = false;
    }

    /* renamed from: d */
    public final boolean mo45999d() {
        return this.f4253f;
    }

    /* renamed from: a */
    public final void mo45995a(boolean isRegisterForLocationServices) {
        if (!C4805f.m2788a() || !C4805f.m2789b()) {
            this.f4255h = false;
        }
        this.f4255h = isRegisterForLocationServices;
    }

    /* renamed from: g */
    private boolean m2675g() {
        return (this.f4258k == null || this.f4259l == null) ? false : true;
    }

    /* renamed from: h */
    private boolean m2676h() {
        return this.f4260m != null && this.f4260m.mo46070b().mo46077a();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo45997b(boolean m_disableLocSerOnBatteryLow) {
        this.f4254g = m_disableLocSerOnBatteryLow;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final void mo46000e() {
        if (m2675g()) {
            try {
                this.f4258k.removeUpdates(this.f4259l);
            } catch (SecurityException e) {
                String str = f4248a;
            } catch (Exception e2) {
                C4834w.m2901c(f4248a, e2.getMessage());
            }
        }
    }

    /* renamed from: i */
    private void m2677i() {
        float bestAccuracy = Float.MAX_VALUE;
        long bestTime = 0;
        Location bestResult = null;
        C4834w.m2901c(f4248a, "Attempting to find an existing location to prime things");
        try {
            for (String provider : this.f4258k.getAllProviders()) {
                if (provider == null) {
                    String str = f4248a;
                } else {
                    C4834w.m2901c(f4248a, "getLastLocation() : " + provider);
                    if (this.f4261n != C4781a.COARSE || provider.equals("network")) {
                        Location location = this.f4258k.getLastKnownLocation(provider);
                        if (location != null) {
                            C4834w.m2901c(f4248a, "getLastLocation() : " + location.getProvider() + ":" + location.getLatitude() + ":" + location.getLongitude() + ":" + location.getAccuracy());
                            float accuracy = location.getAccuracy();
                            long time = location.getTime();
                            if (time > this.f4250c && accuracy < bestAccuracy) {
                                bestResult = location;
                                bestAccuracy = accuracy;
                                bestTime = time;
                            } else if (time < this.f4250c && bestAccuracy == Float.MAX_VALUE && time > bestTime) {
                                bestResult = location;
                                bestTime = time;
                            }
                        }
                    }
                }
            }
        } catch (SecurityException e) {
            String str2 = f4248a;
        } catch (Exception e2) {
            C4834w.m2901c(f4248a, e2.getMessage());
        }
        if (bestResult != null) {
            m2673a(bestResult, false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0067, code lost:
        r3 = f4248a;
        r21.f4258k = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0108, code lost:
        r16 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0109, code lost:
        com.threatmetrix.TrustDefender.C4834w.m2901c(f4248a, r16.getMessage());
        r21.f4258k = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x01f1, code lost:
        r16 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x01f2, code lost:
        com.threatmetrix.TrustDefender.C4834w.m2899b(f4248a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x020c, code lost:
        r16 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x020d, code lost:
        com.threatmetrix.TrustDefender.C4834w.m2899b(f4248a, "Failed to register locationServices: " + r16.getMessage());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:?, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0108 A[ExcHandler: Exception (r16v0 'e' java.lang.Exception A[CUSTOM_DECLARE]), Splitter:B:7:0x004c] */
    /* renamed from: j */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m2678j() {
        /*
            r21 = this;
            r0 = r21
            boolean r3 = r0.f4255h
            if (r3 == 0) goto L_0x000c
            r0 = r21
            com.threatmetrix.TrustDefender.ao r3 = r0.f4259l
            if (r3 != 0) goto L_0x000d
        L_0x000c:
            return
        L_0x000d:
            boolean r3 = r21.m2676h()
            if (r3 != 0) goto L_0x000c
            java.lang.String r3 = f4248a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "registerLocationServices: low power "
            r4.<init>(r5)
            r0 = r21
            long r8 = r0.f4250c
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r5 = " high power "
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r21
            long r8 = r0.f4251d
            java.lang.StringBuilder r4 = r4.append(r8)
            java.lang.String r5 = " with accuracy "
            java.lang.StringBuilder r4 = r4.append(r5)
            r0 = r21
            int r5 = r0.f4249b
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)
            r0 = r21
            android.content.Context r3 = r0.f4256i     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = "location"
            java.lang.Object r18 = r3.getSystemService(r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r18 == 0) goto L_0x005d
            r0 = r18
            boolean r3 = r0 instanceof android.location.LocationManager     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r3 != 0) goto L_0x006f
        L_0x005d:
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = "Location Manager is not available"
            com.threatmetrix.TrustDefender.C4834w.m2894a(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x000c
        L_0x0066:
            r3 = move-exception
            java.lang.String r3 = f4248a
            r3 = 0
            r0 = r21
            r0.f4258k = r3
            goto L_0x000c
        L_0x006f:
            android.location.LocationManager r18 = (android.location.LocationManager) r18     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r18
            r1 = r21
            r1.f4258k = r0     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r21
            android.location.LocationManager r3 = r0.f4258k     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r21
            com.threatmetrix.TrustDefender.ao r4 = r0.f4259l     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3.removeUpdates(r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            android.location.Criteria r13 = new android.location.Criteria     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r13.<init>()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r21
            int r3 = r0.f4249b     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r13.setAccuracy(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setAltitudeRequired(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setBearingAccuracy(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setCostAllowed(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setSpeedAccuracy(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setSpeedRequired(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 0
            r13.setVerticalAccuracy(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            android.location.Criteria r7 = new android.location.Criteria     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r7.<init>()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 1
            r7.setPowerRequirement(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3 = 2
            r7.setAccuracy(r3)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r21
            android.location.LocationManager r3 = r0.f4258k     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r4 = 1
            java.lang.String r17 = r3.getBestProvider(r13, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r17 == 0) goto L_0x00d5
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "fine provider: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r17
            java.lang.StringBuilder r4 = r4.append(r0)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
        L_0x00d5:
            r0 = r21
            android.location.LocationManager r3 = r0.f4258k     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r4 = 1
            java.lang.String r2 = r3.getBestProvider(r7, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r2 == 0) goto L_0x00f5
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "course provider: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r2)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
        L_0x00f5:
            if (r17 != 0) goto L_0x0119
            if (r2 != 0) goto L_0x0119
            r3 = 0
            r0 = r21
            r0.f4258k = r3     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = "Unable to find location provider, possibly incorrect permissions. Check that android.permission.ACCESS_COARSE_LOCATION or android.permission.ACCESS_FINE_LOCATION is set"
            com.threatmetrix.TrustDefender.C4834w.m2894a(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x000c
        L_0x0108:
            r16 = move-exception
            java.lang.String r3 = f4248a
            java.lang.String r4 = r16.getMessage()
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)
            r3 = 0
            r0 = r21
            r0.f4258k = r3
            goto L_0x000c
        L_0x0119:
            r21.m2677i()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r20 = 0
            if (r17 == 0) goto L_0x0128
            if (r2 == 0) goto L_0x0128
            r0 = r17
            boolean r20 = r0.equals(r2)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
        L_0x0128:
            r19 = 0
            r0 = r21
            android.location.Location r3 = r0.f4257j     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r3 == 0) goto L_0x013b
            r0 = r21
            com.threatmetrix.TrustDefender.ao r3 = r0.f4259l     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r0 = r21
            android.location.Location r4 = r0.f4257j     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            r3.onLocationChanged(r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
        L_0x013b:
            r0 = r21
            com.threatmetrix.TrustDefender.ap$a r3 = r0.f4261n     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.ap$a r4 = com.threatmetrix.TrustDefender.C4780ap.C4781a.NONE     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r3 == r4) goto L_0x0177
            r0 = r21
            android.location.LocationManager r3 = r0.f4258k     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r0 = r21
            long r4 = r0.f4250c     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r6 = 0
            r0 = r21
            com.threatmetrix.TrustDefender.ao r8 = r0.f4259l     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            android.os.Looper r9 = android.os.Looper.getMainLooper()     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r3.requestLocationUpdates(r4, r6, r7, r8, r9)     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r19 = 1
            java.lang.String r3 = f4248a     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            java.lang.String r5 = "LocationManager created: "
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r0 = r21
            android.location.LocationManager r5 = r0.f4258k     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            r6 = 1
            java.lang.String r5 = r5.getBestProvider(r7, r6)     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)     // Catch:{ IllegalArgumentException -> 0x01bb, SecurityException -> 0x01d6, Exception -> 0x0108 }
        L_0x0177:
            if (r20 != 0) goto L_0x01b2
            r0 = r21
            com.threatmetrix.TrustDefender.ap$a r3 = r0.f4261n     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.ap$a r4 = com.threatmetrix.TrustDefender.C4780ap.C4781a.NONE     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            if (r3 == r4) goto L_0x01b2
            r0 = r21
            android.location.LocationManager r9 = r0.f4258k     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r0 = r21
            long r10 = r0.f4251d     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r12 = 0
            r0 = r21
            com.threatmetrix.TrustDefender.ao r14 = r0.f4259l     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r15 = 0
            r9.requestLocationUpdates(r10, r12, r13, r14, r15)     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r19 = 1
            java.lang.String r3 = f4248a     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            java.lang.String r5 = "LocationManager created: "
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r0 = r21
            android.location.LocationManager r5 = r0.f4258k     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            r6 = 1
            java.lang.String r5 = r5.getBestProvider(r13, r6)     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2901c(r3, r4)     // Catch:{ IllegalArgumentException -> 0x01f1, SecurityException -> 0x020c, Exception -> 0x0108 }
        L_0x01b2:
            if (r19 != 0) goto L_0x000c
            r3 = 0
            r0 = r21
            r0.f4258k = r3     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x000c
        L_0x01bb:
            r16 = move-exception
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = r16.getMessage()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2899b(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x0177
        L_0x01d6:
            r16 = move-exception
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = r16.getMessage()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2899b(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x0177
        L_0x01f1:
            r16 = move-exception
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = r16.getMessage()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2899b(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x01b2
        L_0x020c:
            r16 = move-exception
            java.lang.String r3 = f4248a     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = "Failed to register locationServices: "
            r4.<init>(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r5 = r16.getMessage()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.StringBuilder r4 = r4.append(r5)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            java.lang.String r4 = r4.toString()     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            com.threatmetrix.TrustDefender.C4834w.m2899b(r3, r4)     // Catch:{ SecurityException -> 0x0066, Exception -> 0x0108 }
            goto L_0x01b2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.C4780ap.m2678j():void");
    }
}
