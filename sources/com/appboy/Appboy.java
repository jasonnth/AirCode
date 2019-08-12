package com.appboy;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import com.airbnb.android.lib.utils.GiftCardUtils;
import com.airbnb.android.utils.AirbnbConstants;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.events.FeedUpdatedEvent;
import com.appboy.events.IEventSubscriber;
import com.appboy.events.InAppMessageEvent;
import com.appboy.models.outgoing.AppboyProperties;
import com.appboy.support.AppboyImageUtils;
import com.appboy.support.AppboyLogger;
import com.appboy.support.PermissionUtils;
import com.appboy.support.StringUtils;
import com.appboy.support.ValidationUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.spongycastle.crypto.tls.CipherSuite;
import p004bo.app.C0325a;
import p004bo.app.C0340ab;
import p004bo.app.C0343ac;
import p004bo.app.C0361au;
import p004bo.app.C0362av;
import p004bo.app.C0365ax;
import p004bo.app.C0366ay;
import p004bo.app.C0374bc;
import p004bo.app.C0380bi;
import p004bo.app.C0381bj;
import p004bo.app.C0386bo;
import p004bo.app.C0391bt;
import p004bo.app.C0397bz;
import p004bo.app.C0444dh;
import p004bo.app.C0448dk;
import p004bo.app.C0455dp;
import p004bo.app.C0463dv;
import p004bo.app.C0494ew;
import p004bo.app.C0495ex;
import p004bo.app.C0500fb;
import p004bo.app.C0511fl;
import p004bo.app.C0530fw;
import p004bo.app.C0532fy;
import p004bo.app.C0540ge.C0542a;
import p004bo.app.C0543gf;
import p004bo.app.C0544gg;
import p004bo.app.C0544gg.C0546a;
import p004bo.app.C0565gr;
import p004bo.app.C0581h;
import p004bo.app.C0593hj;
import p004bo.app.C0629r;

public class Appboy implements IAppboy {

    /* renamed from: A */
    private static volatile IAppboyNotificationFactory f2709A;

    /* renamed from: B */
    private static volatile boolean f2710B = false;

    /* renamed from: C */
    private static volatile boolean f2711C = false;

    /* renamed from: D */
    private static volatile boolean f2712D = false;
    /* access modifiers changed from: private */

    /* renamed from: g */
    public static final String f2713g = AppboyLogger.getAppboyLogTag(Appboy.class);

    /* renamed from: h */
    private static final Set<String> f2714h;

    /* renamed from: i */
    private static final Set<String> f2715i = new HashSet(Arrays.asList(new String[]{"android.permission.ACCESS_NETWORK_STATE", "android.permission.INTERNET"}));

    /* renamed from: j */
    private static volatile Appboy f2716j = null;

    /* renamed from: y */
    private static final Object f2717y = new Object();

    /* renamed from: z */
    private static volatile IAppboyEndpointProvider f2718z;

    /* renamed from: E */
    private IAppboyNavigator f2719E;

    /* renamed from: a */
    volatile C0463dv f2720a;

    /* renamed from: b */
    volatile C0444dh f2721b;

    /* renamed from: c */
    volatile C0511fl f2722c;

    /* renamed from: d */
    volatile C0448dk f2723d;

    /* renamed from: e */
    volatile C0365ax f2724e;

    /* renamed from: f */
    final AppboyConfigurationProvider f2725f;

    /* renamed from: k */
    private final Context f2726k;
    /* access modifiers changed from: private */

    /* renamed from: l */
    public final C0543gf f2727l;
    /* access modifiers changed from: private */

    /* renamed from: m */
    public final C0340ab f2728m;

    /* renamed from: n */
    private volatile AppboyUser f2729n;

    /* renamed from: o */
    private volatile C0325a f2730o;

    /* renamed from: p */
    private volatile C0343ac f2731p;

    /* renamed from: q */
    private volatile ThreadPoolExecutor f2732q;

    /* renamed from: r */
    private final C0581h f2733r;

    /* renamed from: s */
    private final C0380bi f2734s;

    /* renamed from: t */
    private final C0362av f2735t;

    /* renamed from: u */
    private final C0374bc f2736u;

    /* renamed from: v */
    private final C0361au f2737v;

    /* renamed from: w */
    private final Object f2738w = new Object();

    /* renamed from: x */
    private final Object f2739x = new Object();

    static {
        String[] strArr = new String[CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384];
        strArr[0] = "AED";
        strArr[1] = "AFN";
        strArr[2] = "ALL";
        strArr[3] = "AMD";
        strArr[4] = "ANG";
        strArr[5] = "AOA";
        strArr[6] = "ARS";
        strArr[7] = "AUD";
        strArr[8] = "AWG";
        strArr[9] = "AZN";
        strArr[10] = "BAM";
        strArr[11] = "BBD";
        strArr[12] = "BDT";
        strArr[13] = "BGN";
        strArr[14] = "BHD";
        strArr[15] = "BIF";
        strArr[16] = "BMD";
        strArr[17] = "BND";
        strArr[18] = "BOB";
        strArr[19] = "BRL";
        strArr[20] = "BSD";
        strArr[21] = "BTC";
        strArr[22] = "BTN";
        strArr[23] = "BWP";
        strArr[24] = "BYR";
        strArr[25] = "BZD";
        strArr[26] = "CAD";
        strArr[27] = "CDF";
        strArr[28] = "CHF";
        strArr[29] = "CLF";
        strArr[30] = "CLP";
        strArr[31] = AirbnbConstants.CURRENCY_CHINA;
        strArr[32] = "COP";
        strArr[33] = "CRC";
        strArr[34] = "CUC";
        strArr[35] = "CUP";
        strArr[36] = "CVE";
        strArr[37] = "CZK";
        strArr[38] = "DJF";
        strArr[39] = "DKK";
        strArr[40] = "DOP";
        strArr[41] = "DZD";
        strArr[42] = "EEK";
        strArr[43] = "EGP";
        strArr[44] = "ERN";
        strArr[45] = "ETB";
        strArr[46] = AirbnbConstants.CURRENCY_EURO;
        strArr[47] = "FJD";
        strArr[48] = "FKP";
        strArr[49] = "GBP";
        strArr[50] = "GEL";
        strArr[51] = "GGP";
        strArr[52] = "GHS";
        strArr[53] = "GIP";
        strArr[54] = "GMD";
        strArr[55] = "GNF";
        strArr[56] = "GTQ";
        strArr[57] = "GYD";
        strArr[58] = "HKD";
        strArr[59] = "HNL";
        strArr[60] = "HRK";
        strArr[61] = "HTG";
        strArr[62] = "HUF";
        strArr[63] = "IDR";
        strArr[64] = "ILS";
        strArr[65] = "IMP";
        strArr[66] = AirbnbConstants.CURRENCY_INDIA;
        strArr[67] = "IQD";
        strArr[68] = "IRR";
        strArr[69] = "ISK";
        strArr[70] = "JEP";
        strArr[71] = "JMD";
        strArr[72] = "JOD";
        strArr[73] = "JPY";
        strArr[74] = "KES";
        strArr[75] = "KGS";
        strArr[76] = "KHR";
        strArr[77] = "KMF";
        strArr[78] = "KPW";
        strArr[79] = "KRW";
        strArr[80] = "KWD";
        strArr[81] = "KYD";
        strArr[82] = "KZT";
        strArr[83] = "LAK";
        strArr[84] = "LBP";
        strArr[85] = "LKR";
        strArr[86] = "LRD";
        strArr[87] = "LSL";
        strArr[88] = "LTL";
        strArr[89] = "LVL";
        strArr[90] = "LYD";
        strArr[91] = "MAD";
        strArr[92] = "MDL";
        strArr[93] = "MGA";
        strArr[94] = "MKD";
        strArr[95] = "MMK";
        strArr[96] = "MNT";
        strArr[97] = "MOP";
        strArr[98] = "MRO";
        strArr[99] = "MTL";
        strArr[100] = "MUR";
        strArr[101] = "MVR";
        strArr[102] = "MWK";
        strArr[103] = "MXN";
        strArr[104] = "MYR";
        strArr[105] = "MZN";
        strArr[106] = "NAD";
        strArr[107] = "NGN";
        strArr[108] = "NIO";
        strArr[109] = "NOK";
        strArr[110] = "NPR";
        strArr[111] = "NZD";
        strArr[112] = "OMR";
        strArr[113] = "PAB";
        strArr[114] = "PEN";
        strArr[115] = "PGK";
        strArr[116] = "PHP";
        strArr[117] = "PKR";
        strArr[118] = "PLN";
        strArr[119] = "PYG";
        strArr[120] = "QAR";
        strArr[121] = "RON";
        strArr[122] = "RSD";
        strArr[123] = "RUB";
        strArr[124] = "RWF";
        strArr[125] = "SAR";
        strArr[126] = "SBD";
        strArr[127] = "SCR";
        strArr[128] = "SDG";
        strArr[129] = "SEK";
        strArr[130] = "SGD";
        strArr[131] = "SHP";
        strArr[132] = "SLL";
        strArr[133] = "SOS";
        strArr[134] = "SRD";
        strArr[135] = "STD";
        strArr[136] = "SVC";
        strArr[137] = "SYP";
        strArr[138] = "SZL";
        strArr[139] = "THB";
        strArr[140] = "TJS";
        strArr[141] = "TMT";
        strArr[142] = "TND";
        strArr[143] = "TOP";
        strArr[144] = "TRY";
        strArr[145] = "TTD";
        strArr[146] = "TWD";
        strArr[147] = "TZS";
        strArr[148] = "UAH";
        strArr[149] = "UGX";
        strArr[150] = GiftCardUtils.SUPPORTED_CURRENCY_USD;
        strArr[151] = "UYU";
        strArr[152] = "UZS";
        strArr[153] = "VEF";
        strArr[154] = "VND";
        strArr[155] = "VUV";
        strArr[156] = "WST";
        strArr[157] = "XAF";
        strArr[158] = "XAG";
        strArr[159] = "XAU";
        strArr[160] = "XCD";
        strArr[161] = "XDR";
        strArr[162] = "XOF";
        strArr[163] = "XPD";
        strArr[164] = "XPF";
        strArr[165] = "XPT";
        strArr[166] = "YER";
        strArr[167] = "ZAR";
        strArr[168] = "ZMK";
        strArr[169] = "ZMW";
        strArr[170] = "ZWL";
        f2714h = new HashSet(Arrays.asList(strArr));
    }

    public static Appboy getInstance(Context context) {
        if (f2716j == null) {
            synchronized (Appboy.class) {
                if (f2716j == null) {
                    f2716j = new Appboy(context);
                }
            }
        }
        return f2716j;
    }

    Appboy(Context context) {
        long nanoTime = System.nanoTime();
        AppboyLogger.m1733d(f2713g, "Appboy SDK Initializing");
        this.f2726k = context.getApplicationContext();
        this.f2733r = new C0581h(this.f2726k);
        this.f2725f = new AppboyConfigurationProvider(this.f2726k);
        this.f2735t = new C0362av(this.f2726k);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 1, TimeUnit.SECONDS, new LinkedBlockingQueue(10));
        this.f2728m = new C0340ab(threadPoolExecutor);
        if (!this.f2725f.getIsUilImageCacheDisabled()) {
            int imageLoaderCacheSize = AppboyImageUtils.getImageLoaderCacheSize();
            AppboyLogger.m1733d(f2713g, "Setting maximum in-memory image cache size in bytes to: " + imageLoaderCacheSize);
            C0544gg b = new C0546a(this.f2726k).mo7143a(3).mo7142a().mo7144a((C0530fw) new C0532fy()).mo7150c(50).mo7147b(imageLoaderCacheSize).mo7146a(C0565gr.LIFO).mo7145a(new C0542a().mo7132a(true).mo7134b(true).mo7133a()).mo7149b();
            this.f2727l = C0543gf.m861a();
            this.f2727l.mo7136a(b);
            this.f2727l.mo7140a(f2711C);
        } else {
            this.f2727l = null;
        }
        this.f2734s = new C0381bj(this.f2726k, this.f2725f);
        if (!this.f2725f.isGcmMessagingRegistrationEnabled()) {
            AppboyLogger.m1737i(f2713g, "Automatic GCM registration not enabled in appboy.xml. Appboy will not register for GCM.");
            this.f2736u = null;
        } else if (C0374bc.m166a(this.f2726k, this.f2725f)) {
            AppboyLogger.m1737i(f2713g, "Google Cloud Messaging found. Setting up Google Cloud Messaging");
            this.f2736u = new C0374bc(this.f2726k, this.f2734s);
            String gcmSenderId = this.f2725f.getGcmSenderId();
            if (gcmSenderId != null) {
                this.f2736u.mo6800a(gcmSenderId);
            } else {
                AppboyLogger.m1735e(f2713g, "GCM Sender Id not found, not registering with GCM Server");
            }
        } else {
            AppboyLogger.m1735e(f2713g, "GCM manifest requirements not met. Appboy will not register for GCM.");
            this.f2736u = null;
        }
        if (!this.f2725f.isAdmMessagingRegistrationEnabled()) {
            AppboyLogger.m1737i(f2713g, "Automatic ADM registration not enabled in appboy.xml. Appboy will not register for ADM.");
            this.f2737v = null;
        } else if (C0361au.m73a(this.f2726k)) {
            AppboyLogger.m1737i(f2713g, "Amazon Device Messaging found. Setting up Amazon Device Messaging");
            this.f2737v = new C0361au(this.f2726k, this.f2734s);
            this.f2737v.mo6754a();
        } else {
            AppboyLogger.m1735e(f2713g, "ADM manifest requirements not met. Appboy will not register for ADM.");
            this.f2737v = null;
        }
        C0463dv dvVar = new C0463dv(this.f2726k, this.f2733r, this.f2725f, this.f2728m, this.f2735t, this.f2734s, f2710B, f2711C);
        m1708a(dvVar);
        C0366ay.m110a().mo6778a(this.f2732q, dvVar.mo7005k(), dvVar.mo7000f(), dvVar.mo6999e());
        threadPoolExecutor.execute(new Runnable() {
            public void run() {
                Appboy.this.m1713b();
            }
        });
        long nanoTime2 = System.nanoTime();
        AppboyLogger.m1733d(f2713g, String.format("Appboy loaded in %d ms.", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS))}));
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public void m1713b() {
        boolean z;
        boolean z2 = false;
        boolean z3 = true;
        for (String str : f2715i) {
            if (!PermissionUtils.hasPermission(this.f2726k, str)) {
                AppboyLogger.m1735e(f2713g, String.format("The Appboy SDK requires the permission %s. Check your app manifest.", new Object[]{str}));
                z = false;
            } else {
                z = z3;
            }
            z3 = z;
        }
        if (this.f2725f.getAppboyApiKey().toString().equals("")) {
            AppboyLogger.m1735e(f2713g, "The Appboy SDK requires a non-empty API key. Check your appboy.xml.");
        } else {
            z2 = z3;
        }
        if (!z2) {
            AppboyLogger.m1735e(f2713g, "The Appboy SDK is not integrated correctly. Please visit http://documentation.appboy.com/SDK_Integration/Android");
        }
    }

    /* renamed from: a */
    private void m1708a(C0463dv dvVar) {
        synchronized (this.f2738w) {
            synchronized (this.f2739x) {
                this.f2720a = dvVar;
                this.f2730o = dvVar.mo6997c();
                this.f2724e = dvVar.mo6999e();
                this.f2723d = dvVar.mo6996a();
                this.f2729n = new AppboyUser(dvVar.mo7002h(), dvVar.mo6999e(), this.f2733r.mo7244a(), dvVar.mo7007m(), this.f2723d);
                dvVar.mo6998d().mo6707a(dvVar.mo7001g());
                dvVar.mo7000f().mo7303a();
                this.f2731p = dvVar.mo7001g();
                this.f2732q = dvVar.mo7004j();
                this.f2721b = dvVar.mo7006l();
                this.f2722c = dvVar.mo7008n();
            }
        }
    }

    public boolean openSession(Activity activity) {
        boolean z = false;
        synchronized (this.f2739x) {
            try {
                if (!this.f2724e.mo6762a(activity).mo6828a().equals(this.f2724e.mo6771b())) {
                    z = true;
                }
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to open session.", e);
                m1711a((Throwable) e);
            }
        }
        return z;
    }

    public boolean closeSession(Activity activity) {
        boolean z = false;
        synchronized (this.f2739x) {
            try {
                C0391bt b = this.f2724e.mo6770b(activity);
                if (b != null) {
                    AppboyLogger.m1737i(f2713g, "Closed session with ID: " + b.mo6828a());
                    z = true;
                }
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to close session.", e);
                m1711a((Throwable) e);
            }
        }
        return z;
    }

    public boolean submitFeedback(String replyToEmail, String message, boolean isReportingABug) {
        boolean z;
        synchronized (this.f2739x) {
            try {
                this.f2724e.mo6766a(replyToEmail, message, isReportingABug);
                z = true;
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to submit feedback: " + message, e);
                m1711a((Throwable) e);
                z = false;
            }
        }
        return z;
    }

    public boolean logCustomEvent(String eventName) {
        boolean logCustomEvent;
        synchronized (this.f2739x) {
            logCustomEvent = logCustomEvent(eventName, null);
        }
        return logCustomEvent;
    }

    public boolean logCustomEvent(String eventName, AppboyProperties properties) {
        synchronized (this.f2739x) {
            try {
                if (StringUtils.isNullOrBlank(eventName)) {
                    AppboyLogger.m1739w(f2713g, "The custom event name cannot be null or contain only whitespaces. Ignoring custom event.");
                    return false;
                } else if (this.f2723d.mo6974h().contains(eventName)) {
                    AppboyLogger.m1739w(f2713g, String.format("The custom event is a blacklisted custom event: %s. Ignoring custom event.", new Object[]{eventName}));
                    return false;
                } else {
                    String eventName2 = ValidationUtils.ensureAppboyFieldLength(eventName);
                    C0397bz a = C0397bz.m281a(eventName2, properties);
                    if (!this.f2724e.mo6769a((C0386bo) a)) {
                        return false;
                    }
                    this.f2722c.mo7059a((C0495ex) new C0494ew(eventName2, properties, a));
                    return true;
                }
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to log custom event: " + eventName, e);
                m1711a((Throwable) e);
                return false;
            }
        }
    }

    public boolean logPurchase(String productId, String currencyCode, BigDecimal price) {
        return logPurchase(productId, currencyCode, price, 1);
    }

    public boolean logPurchase(String productId, String currencyCode, BigDecimal price, AppboyProperties properties) {
        return logPurchase(productId, currencyCode, price, 1, properties);
    }

    public boolean logPurchase(String productId, String currencyCode, BigDecimal price, int quantity) {
        return logPurchase(productId, currencyCode, price, quantity, null);
    }

    public boolean logPurchase(String productId, String currencyCode, BigDecimal price, int quantity, AppboyProperties properties) {
        synchronized (this.f2739x) {
            try {
                if (StringUtils.isNullOrBlank(productId)) {
                    AppboyLogger.m1739w(f2713g, "The productId is empty, not logging in-app purchase to Appboy.");
                    return false;
                } else if (this.f2723d.mo6976j().contains(productId)) {
                    AppboyLogger.m1739w(f2713g, String.format("The productId is a blacklisted productId: %s, not logging in-app purchase to Appboy.", new Object[]{productId}));
                    return false;
                } else if (currencyCode == null) {
                    AppboyLogger.m1739w(f2713g, String.format("The currencyCode is null. Expected one of %s. Not logging in-app purchase to Appboy.", new Object[]{f2714h}));
                    return false;
                } else {
                    String currencyCode2 = currencyCode.trim().toUpperCase(Locale.US);
                    if (!f2714h.contains(currencyCode2)) {
                        AppboyLogger.m1739w(f2713g, String.format("The currencyCode %s is invalid. Expected one of %s. Not logging in-app purchase to Appboy.", new Object[]{currencyCode2, f2714h}));
                        return false;
                    } else if (price == null) {
                        AppboyLogger.m1739w(f2713g, "The price is null. Not logging in-app purchase to Appboy.");
                        return false;
                    } else if (quantity <= 0) {
                        AppboyLogger.m1739w(f2713g, "The requested purchase quantity is less than zero. Not logging in-app purchase to Appboy.");
                        return false;
                    } else if (quantity > 100) {
                        AppboyLogger.m1739w(f2713g, "The requested purchase quantity is greater than the maximum of 100. Not logging in-app purchase to Appboy.");
                        return false;
                    } else {
                        String productId2 = ValidationUtils.ensureAppboyFieldLength(productId);
                        C0397bz a = C0397bz.m287a(productId2, currencyCode2, price, quantity, properties);
                        if (!this.f2724e.mo6769a((C0386bo) a)) {
                            return false;
                        }
                        this.f2722c.mo7059a((C0495ex) new C0500fb(productId2, properties, a));
                        return true;
                    }
                }
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to log purchase event of " + productId, e);
                m1711a((Throwable) e);
                return false;
            }
        }
    }

    public boolean logPushNotificationOpened(String campaignId) {
        try {
            if (!StringUtils.isNullOrBlank(campaignId)) {
                return this.f2724e.mo6769a((C0386bo) C0397bz.m294b(campaignId));
            }
            AppboyLogger.m1739w(f2713g, "Campaign ID cannot be null or blank");
            return false;
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to log opened push.", e);
            m1711a((Throwable) e);
            return false;
        }
    }

    public boolean logPushNotificationOpened(Intent intent) {
        Throwable e;
        boolean z;
        try {
            String stringExtra = intent.getStringExtra("cid");
            if (!StringUtils.isNullOrBlank(stringExtra)) {
                AppboyLogger.m1737i(f2713g, "Logging push click to Appboy. Campaign Id: " + stringExtra);
                z = logPushNotificationOpened(stringExtra);
            } else {
                AppboyLogger.m1737i(f2713g, "No campaign Id associated with this notification. Not logging push click to Appboy.");
                z = false;
            }
            try {
                if (intent.hasExtra("ab_push_fetch_test_triggers_key") && intent.getStringExtra("ab_push_fetch_test_triggers_key").equals("true")) {
                    AppboyLogger.m1737i(f2713g, "Push contained key for fetching test triggers, fetching triggers.");
                    this.f2724e.mo6768a(C0629r.TRIGGERS);
                }
            } catch (Exception e2) {
                e = e2;
                AppboyLogger.m1740w(f2713g, "Error logging push notification", e);
                return z;
            }
        } catch (Exception e3) {
            Throwable th = e3;
            z = false;
            e = th;
            AppboyLogger.m1740w(f2713g, "Error logging push notification", e);
            return z;
        }
        return z;
    }

    public boolean logPushNotificationActionClicked(String campaignId, String actionId) {
        boolean z = false;
        try {
            if (StringUtils.isNullOrBlank(campaignId)) {
                AppboyLogger.m1739w(f2713g, "Campaign ID cannot be null or blank");
                return z;
            } else if (!StringUtils.isNullOrBlank(actionId)) {
                return this.f2724e.mo6769a((C0386bo) C0397bz.m295b(campaignId, actionId));
            } else {
                AppboyLogger.m1739w(f2713g, "Action ID cannot be null or blank");
                return z;
            }
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to log push notification action clicked.", e);
            m1711a((Throwable) e);
            return z;
        }
    }

    public boolean logFeedDisplayed() {
        try {
            return this.f2724e.mo6769a((C0386bo) C0397bz.m304e());
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to log that the feed was displayed.", e);
            m1711a((Throwable) e);
            return false;
        }
    }

    public boolean logFeedbackDisplayed() {
        try {
            return this.f2724e.mo6769a((C0386bo) C0397bz.m306f());
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to log that feedback was displayed.", e);
            m1711a((Throwable) e);
            return false;
        }
    }

    public void requestFeedRefreshFromCache() {
        this.f2732q.execute(new Runnable() {
            public void run() {
                try {
                    Appboy.this.f2728m.mo6736a(Appboy.this.f2721b.mo6955a(), FeedUpdatedEvent.class);
                } catch (JSONException e) {
                    AppboyLogger.m1740w(Appboy.f2713g, "Failed to retrieve and publish feed from offline cache.", e);
                }
            }
        });
    }

    public void requestFeedRefresh() {
        synchronized (this.f2739x) {
            try {
                this.f2724e.mo6768a(C0629r.FEED);
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to request refresh of feed.", e);
                m1711a((Throwable) e);
            }
        }
    }

    public void requestImmediateDataFlush() {
        synchronized (this.f2739x) {
            try {
                this.f2724e.mo6768a(new C0629r[0]);
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to request data flush.", e);
                m1711a((Throwable) e);
            }
        }
    }

    public void subscribeToNewInAppMessages(IEventSubscriber<InAppMessageEvent> subscriber) {
        try {
            this.f2728m.mo6737a(subscriber, InAppMessageEvent.class);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to add subscriber to new in-app messages.", e);
            m1711a((Throwable) e);
        }
    }

    public void subscribeToFeedUpdates(IEventSubscriber<FeedUpdatedEvent> subscriber) {
        try {
            this.f2728m.mo6737a(subscriber, FeedUpdatedEvent.class);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to add subscriber for feed updates.", e);
            m1711a((Throwable) e);
        }
    }

    public <T> void removeSingleSubscription(IEventSubscriber<T> subscriber, Class<T> eventClass) {
        try {
            this.f2728m.mo6738b(subscriber, eventClass);
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to remove " + eventClass.getName() + " subscriber.", e);
            m1711a((Throwable) e);
        }
    }

    public AppboyUser changeUser(String userId) {
        synchronized (this.f2738w) {
            try {
                if (StringUtils.isNullOrEmpty(userId)) {
                    AppboyLogger.m1735e(f2713g, "ArgumentException: userId passed to changeUser was null or empty. The current user will remain the active user.");
                    AppboyUser appboyUser = this.f2729n;
                    return appboyUser;
                }
                String userId2 = this.f2729n.getUserId();
                if (userId2.equals(userId)) {
                    AppboyLogger.m1737i(f2713g, String.format("Received request to change current user %s to the same user id. Doing nothing.", new Object[]{userId}));
                } else {
                    boolean equals = userId2.equals("");
                    if (equals) {
                        AppboyLogger.m1737i(f2713g, String.format("Changing anonymous user to %s.", new Object[]{userId}));
                        this.f2733r.mo7246b(userId);
                        this.f2729n.mo27216a(userId);
                        this.f2730o.mo6704a(userId);
                    } else {
                        AppboyLogger.m1737i(f2713g, String.format("Changing current user %s to new user %s.", new Object[]{userId2, userId}));
                        this.f2728m.mo6736a(new FeedUpdatedEvent(new ArrayList(), userId, false, C0455dp.m515a()), FeedUpdatedEvent.class);
                    }
                    this.f2724e.mo6773c();
                    this.f2733r.mo7245a(userId);
                    C0463dv dvVar = this.f2720a;
                    m1708a(new C0463dv(this.f2726k, this.f2733r, this.f2725f, this.f2728m, this.f2735t, this.f2734s, f2710B, f2711C));
                    this.f2720a.mo7003i().mo6952c();
                    this.f2724e.mo6761a();
                    if (equals) {
                        this.f2724e.mo6769a((C0386bo) C0397bz.m279a(userId));
                    } else {
                        this.f2724e.mo6769a((C0386bo) C0397bz.m282a(userId2, userId));
                    }
                    this.f2724e.mo6768a(C0629r.FEED);
                    dvVar.mo7009o();
                }
                return this.f2729n;
            } catch (Exception e) {
                AppboyLogger.m1740w(f2713g, "Failed to set external id to: " + userId, e);
                m1711a((Throwable) e);
            }
        }
    }

    public AppboyUser getCurrentUser() {
        AppboyUser appboyUser;
        synchronized (this.f2738w) {
            appboyUser = this.f2729n;
        }
        return appboyUser;
    }

    public void registerAppboyPushMessages(String registrationId) {
        try {
            if (StringUtils.isNullOrBlank(registrationId)) {
                AppboyLogger.m1739w(f2713g, "Push registration ID must not be null or blank. Not registering for push messages from Appboy.");
                return;
            }
            AppboyLogger.m1737i(f2713g, "Push token " + registrationId + " registered and immediately being flushed.");
            this.f2734s.mo6802a(registrationId);
            requestImmediateDataFlush();
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to set the registration ID.", e);
            m1711a((Throwable) e);
        }
    }

    public void unregisterAppboyPushMessages() {
        try {
            this.f2734s.mo6803b();
        } catch (Exception e) {
            AppboyLogger.m1740w(f2713g, "Failed to unset the registration ID.", e);
            m1711a((Throwable) e);
        }
    }

    public void fetchAndRenderImage(final String imageUrl, final ImageView imageView, final boolean respectAspectRatio) {
        if (this.f2727l == null) {
            AppboyLogger.m1739w(f2713g, "Uil LRU memory and disc cache unavailable. Could not fetch and render image.");
        } else {
            imageView.post(new Runnable() {
                public void run() {
                    Appboy.this.f2727l.mo7137a(Uri.parse(imageUrl).toString(), imageView, new C0593hj() {
                        /* renamed from: a */
                        public void mo7271a(String str, View view, Bitmap bitmap) {
                            super.mo7271a(str, view, bitmap);
                            float height = (float) bitmap.getHeight();
                            if (height != 0.0f && !respectAspectRatio) {
                                final float width = ((float) bitmap.getWidth()) / height;
                                ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
                                if (viewTreeObserver.isAlive()) {
                                    viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
                                        public void onGlobalLayout() {
                                            int width = imageView.getWidth();
                                            imageView.setLayoutParams(new LayoutParams(width, (int) (((float) width) / width)));
                                            Appboy.this.m1707a(imageView.getViewTreeObserver(), this);
                                        }
                                    });
                                }
                            }
                        }
                    });
                }
            });
        }
    }

    public IAppboyNavigator getAppboyNavigator() {
        return this.f2719E;
    }

    /* renamed from: a */
    private void m1711a(Throwable th) {
        try {
            this.f2731p.mo6736a(th, Throwable.class);
        } catch (Exception e) {
            AppboyLogger.m1736e(f2713g, "Failed to log throwable.", e);
        }
    }

    /* access modifiers changed from: private */
    @TargetApi(16)
    /* renamed from: a */
    public void m1707a(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        if (VERSION.SDK_INT < 16) {
            viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
        } else {
            viewTreeObserver.removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public static boolean getOutboundNetworkRequestsOffline() {
        return f2711C;
    }

    public static void setAppboyEndpointProvider(IAppboyEndpointProvider appboyEndpointProvider) {
        synchronized (f2717y) {
            f2718z = appboyEndpointProvider;
        }
    }

    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.net.Uri getAppboyApiEndpoint(android.net.Uri r3) {
        /*
            java.lang.Object r1 = f2717y
            monitor-enter(r1)
            com.appboy.IAppboyEndpointProvider r0 = f2718z     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x001b
            com.appboy.IAppboyEndpointProvider r0 = f2718z     // Catch:{ Exception -> 0x0012 }
            android.net.Uri r0 = r0.getApiEndpoint(r3)     // Catch:{ Exception -> 0x0012 }
            if (r0 == 0) goto L_0x001b
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
            r3 = r0
        L_0x0011:
            return r3
        L_0x0012:
            r0 = move-exception
            java.lang.String r0 = f2713g     // Catch:{ all -> 0x001d }
            java.lang.String r2 = "Caught exception trying to get an Appboy API endpoint from the AppboyEndpointProvider. Using the original URI"
            com.appboy.support.AppboyLogger.m1735e(r0, r2)     // Catch:{ all -> 0x001d }
        L_0x001b:
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
            goto L_0x0011
        L_0x001d:
            r0 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x001d }
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appboy.Appboy.getAppboyApiEndpoint(android.net.Uri):android.net.Uri");
    }

    public static IAppboyNotificationFactory getCustomAppboyNotificationFactory() {
        return f2709A;
    }
}
