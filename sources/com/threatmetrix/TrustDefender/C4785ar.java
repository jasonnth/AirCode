package com.threatmetrix.TrustDefender;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.location.Location;
import android.os.Looper;
import android.support.p000v4.media.session.PlaybackStateCompat;
import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.airbnb.android.utils.AirbnbConstants;
import com.facebook.appevents.AppEventsConstants;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONException;
import org.json.JSONObject;
import p005cn.jpush.android.JPushConstants;

@TargetApi(9)
/* renamed from: com.threatmetrix.TrustDefender.ar */
class C4785ar {

    /* renamed from: Y */
    private static final String f4268Y = C4834w.m2892a(C4785ar.class);

    /* renamed from: a */
    public static String f4269a = "4.0-90";

    /* renamed from: A */
    String f4270A = null;

    /* renamed from: B */
    String f4271B = null;

    /* renamed from: C */
    String f4272C = null;

    /* renamed from: D */
    String f4273D = null;

    /* renamed from: E */
    String f4274E = null;

    /* renamed from: F */
    String f4275F = null;

    /* renamed from: G */
    String f4276G = "";

    /* renamed from: H */
    String f4277H = null;

    /* renamed from: I */
    String f4278I = null;

    /* renamed from: J */
    String f4279J = null;

    /* renamed from: K */
    String[] f4280K = null;

    /* renamed from: L */
    ArrayList<String> f4281L = null;

    /* renamed from: M */
    C4771aj f4282M = null;

    /* renamed from: N */
    Location f4283N = null;

    /* renamed from: O */
    Context f4284O = null;

    /* renamed from: P */
    C4794e f4285P = null;

    /* renamed from: Q */
    C4789c f4286Q = new C4789c();

    /* renamed from: R */
    C4835x f4287R = null;

    /* renamed from: S */
    long f4288S = 0;

    /* renamed from: T */
    int f4289T = 0;

    /* renamed from: U */
    long f4290U = 0;

    /* renamed from: V */
    volatile THMStatusCode f4291V = THMStatusCode.THM_OK;

    /* renamed from: W */
    volatile long f4292W = 0;

    /* renamed from: X */
    volatile long f4293X = 0;

    /* renamed from: b */
    int f4294b = 0;

    /* renamed from: c */
    int f4295c = 0;

    /* renamed from: d */
    int f4296d = 0;

    /* renamed from: e */
    int f4297e = 0;

    /* renamed from: f */
    int f4298f = 0;

    /* renamed from: g */
    int f4299g = 0;

    /* renamed from: h */
    AtomicLong f4300h = new AtomicLong(0);

    /* renamed from: i */
    long f4301i = 0;

    /* renamed from: j */
    long f4302j = 0;

    /* renamed from: k */
    long f4303k = 0;

    /* renamed from: l */
    boolean f4304l = false;

    /* renamed from: m */
    String f4305m = null;

    /* renamed from: n */
    String f4306n = null;

    /* renamed from: o */
    String f4307o = null;

    /* renamed from: p */
    String f4308p = null;

    /* renamed from: q */
    String f4309q = null;

    /* renamed from: r */
    String f4310r = null;

    /* renamed from: s */
    String f4311s = null;

    /* renamed from: t */
    String f4312t = null;

    /* renamed from: u */
    String f4313u = null;

    /* renamed from: v */
    String f4314v = null;

    /* renamed from: w */
    String f4315w = null;

    /* renamed from: x */
    String f4316x = null;

    /* renamed from: y */
    String f4317y = null;

    /* renamed from: z */
    String f4318z = null;

    C4785ar(String source) {
        this.f4276G = source;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46003a() {
        this.f4308p = null;
        this.f4294b = 0;
        this.f4295c = 0;
        this.f4316x = null;
        this.f4283N = null;
        this.f4274E = null;
        this.f4275F = null;
        this.f4282M = null;
        this.f4301i = 0;
        this.f4302j = 0;
        this.f4303k = 0;
        this.f4287R = null;
        this.f4298f = 0;
        this.f4304l = false;
        this.f4277H = null;
        this.f4279J = null;
        this.f4296d = 0;
        this.f4297e = 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo46016b() {
        this.f4289T++;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46005a(long options) {
        this.f4288S = options;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46010a(C4789c browserInfo) {
        this.f4286Q = browserInfo;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final String mo46018c() {
        return this.f4305m;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46012a(String apikey) {
        this.f4271B = apikey;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final void mo46017b(String sessionID) {
        this.f4305m = C4770ai.m2634g(sessionID);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46014a(List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.f4281L = new ArrayList<>(list);
        } else if (this.f4281L != null) {
            this.f4281L.clear();
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final THMStatusCode mo46020d() {
        C4834w.m2901c(f4268Y, "getStatus returns: " + this.f4291V.toString());
        return this.f4291V;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46008a(THMStatusCode status) {
        this.f4291V = status;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46007a(Location location, boolean isManualLocation) {
        if (C4805f.m2789b()) {
            this.f4283N = location;
            this.f4304l = isManualLocation;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46011a(C4794e state) {
        this.f4285P = state;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46009a(C4771aj conf) {
        this.f4282M = conf;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final C4771aj mo46022e() {
        return this.f4282M;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46006a(Context context) {
        this.f4284O = context;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final String mo46024f() {
        return this.f4318z;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final String mo46025g() {
        return this.f4317y;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46013a(String browserString, boolean escapeNonAscii) {
        this.f4272C = C4770ai.m2636i(browserString);
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final void mo46015a(AtomicLong options) {
        this.f4300h = options;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: h */
    public final String mo46026h() {
        return "https://" + this.f4318z + "/fp/mobile/conf";
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final boolean mo46019c(String fp_server) {
        if (fp_server == null) {
            fp_server = "h-sdk.online-metrix.net";
        }
        try {
            new URL("https://" + fp_server);
            this.f4318z = fp_server;
            return true;
        } catch (MalformedURLException e) {
            C4834w.m2895a(f4268Y, "Invalid hostname " + fp_server, (Throwable) e);
            return false;
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final boolean mo46021d(String org_id) {
        if (!C4770ai.m2637j(org_id)) {
            C4834w.m2894a(f4268Y, "Invalid org_id");
            return false;
        }
        this.f4317y = org_id;
        return true;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final boolean mo46023e(String packageName) {
        if (C4770ai.m2632e(packageName)) {
            packageName = "TrustDefenderSDK";
        }
        this.f4314v = new StringBuilder(JPushConstants.HTTP_PRE).append(packageName).toString();
        this.f4310r = new StringBuilder(JPushConstants.HTTP_PRE).append(packageName).append("/mobile").toString();
        return true;
    }

    /* renamed from: i */
    static String m2699i() {
        return "h-sdk.online-metrix.net";
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public final C4823n mo46027j() {
        C4823n params = new C4823n();
        params.mo46081a("org_id", this.f4317y);
        params.mo46081a("session_id", this.f4305m);
        params.mo46081a("os", C4826p.m2874f());
        params.mo46081a("osVersion", C4800a.f4361a);
        if (this.f4271B != null && !this.f4271B.isEmpty()) {
            params.mo46081a("api_key", this.f4271B);
        }
        params.mo46081a("sdk_version", f4269a);
        return params;
    }

    /* renamed from: a */
    public final void mo46004a(int bitMask) {
        this.f4299g |= bitMask;
    }

    /* renamed from: k */
    public final void mo46028k() {
        this.f4299g = 0;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public final void mo46029l() throws InterruptedException {
        long gatherInfoStart = C4812m.m2808a();
        C4834w.m2901c(f4268Y, "Getting timezone info");
        if ((this.f4300h.get() & 8) != 0) {
            C4828b tzInfo = new C4828b();
            if (C4826p.m2865a(tzInfo)) {
                this.f4295c = tzInfo.f4644b;
                this.f4294b = tzInfo.f4643a;
            }
        }
        if (this.f4308p == null || this.f4306n == null || this.f4307o == null) {
            String genID = null;
            m2705v();
            if (this.f4307o == null) {
                genID = C4778an.m2664a(this.f4284O);
                m2703t();
                this.f4307o = C4778an.m2667a(genID, m2704u());
            }
            if (this.f4311s == null) {
                this.f4311s = C4778an.m2671c(this.f4284O);
            }
            if (this.f4311s != null && !this.f4311s.isEmpty()) {
                String imei = C4778an.m2671c(this.f4284O);
                if (imei != null && !imei.isEmpty() && !imei.equalsIgnoreCase(this.f4311s)) {
                    this.f4311s = "";
                    mo46004a(4);
                }
            }
            if (this.f4306n == null) {
                if (this.f4309q == null) {
                    this.f4309q = C4778an.m2669b(this.f4284O);
                }
                if (genID == null) {
                    genID = C4778an.m2664a(this.f4284O);
                }
                m2703t();
                this.f4306n = C4778an.m2666a(this.f4309q, genID, this.f4311s, m2704u());
            }
        }
        m2703t();
        if ((this.f4300h.get() & 16) != 0 && (this.f4297e == 0 || this.f4296d == 0)) {
            C4817j display = new C4817j(this.f4284O);
            this.f4296d = display.mo46057a();
            this.f4297e = display.mo46058b();
        }
        m2703t();
        if (this.f4315w == null) {
            this.f4315w = C4826p.m2860a(this.f4284O, this.f4284O);
        }
        if ((this.f4300h.get() & PlaybackStateCompat.ACTION_PLAY_FROM_SEARCH) != 0 && this.f4273D == null) {
            this.f4273D = C4759a.m2571a(this.f4284O);
        }
        if ((this.f4300h.get() & 12288) == 12288) {
            this.f4280K = NativeGatherer.m2512a().mo45873g();
        } else if ((this.f4300h.get() & PlaybackStateCompat.ACTION_PLAY_FROM_URI) != 0) {
            this.f4280K = NativeGatherer.m2512a().mo45870e();
        } else if ((this.f4300h.get() & PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) != 0) {
            this.f4280K = NativeGatherer.m2512a().mo45872f();
        }
        if (this.f4313u == null || this.f4312t == null) {
            StringBuilder fontCount = new StringBuilder();
            this.f4312t = C4826p.m2862a(fontCount);
            if (this.f4312t != null) {
                this.f4313u = fontCount.toString();
            }
            String str = f4268Y;
            new StringBuilder("Got ").append(this.f4313u).append(" fonts gives: ").append(this.f4312t);
        }
        m2703t();
        if (this.f4301i == 0) {
            this.f4301i = C4826p.m2868c();
        }
        m2703t();
        if (this.f4270A == null) {
            this.f4270A = C4826p.m2867b(this.f4284O);
        }
        m2703t();
        if (this.f4302j == 0) {
            this.f4302j = C4826p.m2871d();
        }
        m2703t();
        if (this.f4303k == 0) {
            this.f4303k = C4826p.m2872e();
        }
        m2703t();
        this.f4316x = C4826p.m2859a(this.f4302j, this.f4301i);
        if ((this.f4300h.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_MEDIA_ID) != 0) {
            m2703t();
            this.f4287R = C4826p.m2857a(this.f4284O);
        }
        m2703t();
        this.f4277H = C4826p.m2873e(this.f4284O);
        m2703t();
        this.f4279J = C4826p.m2878h(this.f4284O);
        m2703t();
        if (C4770ai.m2632e(this.f4278I) && (this.f4300h.get() & PlaybackStateCompat.ACTION_PREPARE_FROM_URI) != 0) {
            this.f4278I = C4826p.m2875f(this.f4284O);
        }
        this.f4290U = C4812m.m2808a() - gatherInfoStart;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: m */
    public final Map<String, String> mo46030m() {
        Map<String, String> map = new HashMap<>();
        if (C4770ai.m2633f(this.f4272C)) {
            map.put("User-Agent", this.f4272C);
        }
        try {
            if (C4770ai.m2633f(this.f4309q) || this.f4284O != null) {
                m2705v();
            }
        } catch (InterruptedException e) {
            this.f4308p = null;
        }
        map.put("Cookie", "thx_guid=" + (this.f4308p == null ? "" : this.f4308p));
        map.put("Referer", this.f4314v);
        map.put("Accept-Language", m2702s());
        map.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return map;
    }

    /* renamed from: n */
    static Map<String, String> m2700n() {
        Map<String, String> map = new HashMap<>();
        map.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return map;
    }

    /* renamed from: o */
    public final String mo46031o() {
        StringBuilder host = new StringBuilder();
        host.append(this.f4317y).append("-").append(C4770ai.m2635h(this.f4305m)).append("-mob");
        if (host.length() >= 64) {
            C4834w.m2899b(f4268Y, "combined session id and org id too long for host name fragment");
            return null;
        }
        C4834w.m2901c(f4268Y, "Launching DNS profiling request");
        String prod = "d";
        if (this.f4318z.equals("qa2-h.online-metrix.net")) {
            prod = "q";
        }
        return host.append(".").append(prod).append(".aa.online-metrix.net").toString();
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: p */
    public final Map<String, String> mo46032p() {
        HashMap<String, String> extra_headers = new HashMap<>();
        if (this.f4272C != null && !this.f4272C.isEmpty()) {
            C4834w.m2901c(f4268Y, "Setting User-Agent to " + this.f4272C);
            extra_headers.put("User-Agent", this.f4272C);
        }
        extra_headers.put("Cookie", "thx_guid=" + (this.f4308p == null ? "" : this.f4308p));
        extra_headers.put("Referer", this.f4314v);
        extra_headers.put(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/x-www-form-urlencoded");
        extra_headers.put("Accept-Language", m2702s());
        extra_headers.put("Cache-Control", "no-cache, no-store, must-revalidate, no-transform");
        return extra_headers;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: q */
    public final C4823n mo46033q() throws InterruptedException {
        String sb;
        String c;
        String urlCheckCount = "";
        String foundPaths = null;
        if ((this.f4300h.get() & PlaybackStateCompat.ACTION_PREPARE) != 0) {
            List<String> foundURLs = C4826p.m2864a(this.f4284O, (List<String>) this.f4282M.f4224d);
            if (this.f4282M.f4224d.size() > 0) {
                urlCheckCount = String.valueOf(foundURLs.size());
                foundPaths = C4770ai.m2625a(foundURLs, ";", true);
            }
        }
        String experimentalAttributes = "";
        if ((this.f4282M.f4221a & 256) != 0) {
            experimentalAttributes = C4826p.m2863a((List<URI>) this.f4282M.f4225e);
        }
        C4823n jaParams = new C4823n();
        jaParams.mo46085a(255);
        if (this.f4296d >= this.f4297e) {
            jaParams.mo46081a("f", this.f4296d + "x" + this.f4297e);
        } else {
            jaParams.mo46081a("f", this.f4297e + "x" + this.f4296d);
        }
        jaParams.mo46081a("w", this.f4282M.f4223c);
        jaParams.mo46081a("c", String.valueOf(this.f4294b));
        jaParams.mo46081a("z", String.valueOf(this.f4295c));
        jaParams.mo46083a("lh", this.f4310r, true);
        jaParams.mo46083a("dr", this.f4314v, true);
        if (!this.f4286Q.f4323b.equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            jaParams.mo46083a("p", this.f4286Q.f4322a, true);
            jaParams.mo46083a("pl", this.f4286Q.f4323b, true);
            jaParams.mo46083a("ph", this.f4286Q.f4325d, true);
        }
        jaParams.mo46081a("hh", C4770ai.m2628b(this.f4317y + this.f4305m));
        if (this.f4286Q.f4327f > 0) {
            jaParams.mo46081a("mt", this.f4286Q.f4329h);
            jaParams.mo46081a("mn", String.valueOf(this.f4286Q.f4327f));
        }
        jaParams.mo46083a("mdf", this.f4315w, true);
        jaParams.mo46083a("mds", this.f4316x, true);
        jaParams.mo46083a("imei", this.f4311s, true);
        if (this.f4283N != null) {
            jaParams.mo46081a("tdlat", String.valueOf(this.f4283N.getLatitude()));
            jaParams.mo46081a("tdlon", String.valueOf(this.f4283N.getLongitude()));
            jaParams.mo46081a("tdlacc", String.valueOf(this.f4283N.getAccuracy()));
        }
        if (this.f4281L != null && this.f4281L.size() > 0) {
            int count = 0;
            Iterator i$ = this.f4281L.iterator();
            while (i$.hasNext()) {
                int count2 = count + 1;
                jaParams.mo46083a("aca" + count, (String) i$.next(), true);
                if (count2 >= 5) {
                    break;
                }
                count = count2;
            }
        }
        jaParams.mo46081a("ah", this.f4273D);
        jaParams.mo46081a("la", this.f4282M.f4223c + this.f4307o);
        jaParams.mo46081a("lq", this.f4272C);
        jaParams.mo46081a("fc", this.f4282M.f4223c + this.f4306n);
        jaParams.mo46081a("ftsn", this.f4313u);
        jaParams.mo46083a("fts", this.f4312t, true);
        jaParams.mo46083a("aov", C4800a.f4361a, true);
        jaParams.mo46083a("al", m2702s(), true);
        String str = "alo";
        if (this.f4275F == null) {
            this.f4275F = C4826p.m2866b();
        }
        jaParams.mo46083a(str, this.f4275F, true);
        jaParams.mo46083a("ab", C4799b.f4351e + ", " + C4799b.f4359m, true);
        jaParams.mo46083a("am", C4799b.f4356j, true);
        jaParams.mo46083a("aos", C4826p.m2876g(), true);
        jaParams.mo46083a("cos", C4826p.m2874f(), true);
        jaParams.mo46081a("fg", this.f4306n);
        jaParams.mo46081a("ls", this.f4307o);
        jaParams.mo46081a("gr", urlCheckCount);
        jaParams.mo46082a("grr", foundPaths, Integer.valueOf(1024));
        jaParams.mo46081a("at", "agent_mobile");
        String str2 = "av";
        StringBuilder append = new StringBuilder().append(f4269a);
        if (this.f4276G.isEmpty()) {
            sb = "";
        } else {
            StringBuilder sb2 = new StringBuilder(" : ");
            sb = sb2.append(this.f4276G).toString();
        }
        jaParams.mo46081a(str2, append.append(sb).toString());
        jaParams.mo46081a("mex3", experimentalAttributes);
        if (C4826p.m2869c(this.f4284O)) {
            this.f4298f |= 2;
        }
        if (this.f4283N != null) {
            C4833v vVar = new C4833v(this.f4283N);
            if (vVar.mo46095a()) {
                this.f4298f |= 1;
            }
        }
        if (this.f4304l) {
            this.f4298f |= 4;
        }
        jaParams.mo46081a("mex4", String.valueOf(this.f4298f));
        jaParams.mo46081a("mex5", String.valueOf(this.f4299g));
        jaParams.mo46081a("mex6", String.valueOf(C4826p.m2870d(this.f4284O)));
        jaParams.mo46081a("mex7", this.f4277H == null ? "" : this.f4277H);
        jaParams.mo46081a("mex8", this.f4278I == null ? "" : this.f4278I);
        jaParams.mo46081a("mex10", C4826p.m2877g(this.f4284O));
        jaParams.mo46081a("abt", this.f4301i == 0 ? "" : String.valueOf(this.f4301i));
        jaParams.mo46081a("anv", this.f4270A);
        jaParams.mo46081a("afs", this.f4302j == 0 ? "" : String.valueOf(this.f4302j));
        jaParams.mo46081a("ats", this.f4303k == 0 ? "" : String.valueOf(this.f4303k));
        jaParams.mo46081a("aci", this.f4279J == null ? "" : this.f4279J);
        if (this.f4287R != null) {
            jaParams.mo46081a("wbs", this.f4287R.mo46097b() == null ? "" : this.f4287R.mo46097b());
            jaParams.mo46081a("wss", this.f4287R.mo46096a() == null ? "" : this.f4287R.mo46096a());
            jaParams.mo46081a("wrr", this.f4287R.mo46099d() == null ? "" : this.f4287R.mo46099d());
            String str3 = "wc";
            if (this.f4287R.mo46098c() == null) {
                c = "";
            } else {
                c = this.f4287R.mo46098c();
            }
            jaParams.mo46081a(str3, c);
        }
        long currentTime = System.currentTimeMillis();
        jaParams.mo46081a("atr", m2698b(currentTime));
        jaParams.mo46081a("apd", String.valueOf(currentTime - this.f4292W));
        C4823n postParams = new C4823n();
        postParams.mo46081a("org_id", this.f4317y);
        postParams.mo46081a("session_id", this.f4305m);
        if (this.f4280K != null) {
            StringBuilder apps = new StringBuilder();
            String[] arr$ = this.f4280K;
            int len$ = arr$.length;
            for (int i$2 = 0; i$2 < len$; i$2++) {
                String app = arr$[i$2];
                if (apps.length() > 0) {
                    apps.append(",");
                }
                apps.append(app);
            }
            String str4 = f4268Y;
            new StringBuilder("Found: ").append(apps.toString());
            postParams.mo46081a("ih", apps.toString());
        }
        String encoded = jaParams.mo46086b();
        String str5 = f4268Y;
        new StringBuilder("encoded ja = ").append(jaParams);
        postParams.mo46081a(AirbnbConstants.LANGUAGE_CODE_JAPAN, C4770ai.m2623a(encoded, this.f4305m));
        postParams.mo46081a("h", AppEventsConstants.EVENT_PARAM_VALUE_NO).mo46081a("m", "2");
        return postParams;
    }

    /* renamed from: r */
    static boolean m2701r() {
        StackTraceElement[] stack = Looper.getMainLooper().getThread().getStackTrace();
        for (int i = 0; i < stack.length; i++) {
            String className = stack[i].getClassName();
            if ((className != null && className.contains("de.robv.android.xposed.XposedBridge")) || "XposedBridge.java".equalsIgnoreCase(stack[i].getFileName())) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private String m2698b(long timeOfCall) {
        JSONObject json = new JSONObject();
        try {
            json.put("opts", this.f4288S);
            json.put("dyOpt", this.f4300h);
            json.put("psi", this.f4289T);
            json.put("pr", this.f4290U);
            json.put("cpi", timeOfCall - this.f4292W);
            json.put("lpi", this.f4293X);
            json.put("ori", this.f4296d >= this.f4297e ? "landscape" : "portrait");
            json.put("crc", NativeGatherer.m2512a().mo45877k());
            json.put("matched", NativeGatherer.m2512a().mo45878l());
            String str = "debug";
            ApplicationInfo applicationInfo = this.f4284O.getApplicationInfo();
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            json.put(str, i != 0);
            return json.toString();
        } catch (JSONException e) {
            return "";
        }
    }

    /* renamed from: s */
    private String m2702s() {
        if (this.f4274E == null) {
            this.f4274E = C4826p.m2858a();
        }
        return this.f4274E;
    }

    /* renamed from: t */
    private void m2703t() throws InterruptedException {
        if (this.f4285P != null && this.f4285P.mo45949a()) {
            throw new InterruptedException();
        }
    }

    /* renamed from: u */
    private boolean m2704u() {
        return (this.f4300h.get() & 512) != 0 && C4778an.m2668a();
    }

    /* renamed from: v */
    private void m2705v() throws InterruptedException {
        if (C4770ai.m2632e(this.f4309q)) {
            this.f4309q = C4778an.m2669b(this.f4284O);
        }
        if (C4770ai.m2632e(this.f4308p)) {
            this.f4308p = C4778an.m2670b(this.f4309q, m2704u());
        }
    }
}
