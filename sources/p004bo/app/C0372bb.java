package p004bo.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.models.outgoing.Environment;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TimeZone;

/* renamed from: bo.app.bb */
public class C0372bb implements C0377bf {
    /* access modifiers changed from: private */

    /* renamed from: b */
    public static final String f164b = AppboyLogger.getAppboyLogTag(C0372bb.class);
    /* access modifiers changed from: private */

    /* renamed from: l */
    public static boolean f165l = false;

    /* renamed from: a */
    final SharedPreferences f166a;
    /* access modifiers changed from: private */

    /* renamed from: c */
    public final Context f167c;

    /* renamed from: d */
    private final C0378bg f168d;

    /* renamed from: e */
    private final C0380bi f169e;

    /* renamed from: f */
    private final Environment f170f;

    /* renamed from: g */
    private final C0442df f171g;

    /* renamed from: h */
    private final C0448dk f172h;

    /* renamed from: i */
    private final C0376be<C0406cg> f173i;

    /* renamed from: j */
    private final C0343ac f174j;

    /* renamed from: k */
    private String f175k;

    /* renamed from: a */
    public static boolean m148a() {
        return f165l;
    }

    public C0372bb(Context context, AppboyConfigurationProvider appboyConfigurationProvider, String str, C0378bg bgVar, C0380bi biVar, C0442df dfVar, C0448dk dkVar, C0376be<C0406cg> beVar, C0343ac acVar) {
        if (context == null) {
            throw new NullPointerException();
        }
        this.f167c = context;
        this.f168d = bgVar;
        this.f169e = biVar;
        this.f171g = dfVar;
        this.f172h = dkVar;
        this.f170f = m145a(dkVar);
        this.f173i = beVar;
        this.f174j = acVar;
        this.f166a = context.getSharedPreferences("com.appboy.storage.device_ad_info" + StringUtils.getCacheFileSuffix(context, str, appboyConfigurationProvider.getAppboyApiKey().toString()), 0);
        if (this.f172h != null && this.f172h.mo6967a()) {
            m152h();
        }
    }

    /* renamed from: b */
    public C0399ca mo6794b() {
        Locale m = m157m();
        String str = null;
        if (this.f169e != null) {
            str = this.f169e.mo6801a();
        }
        return new C0399ca(Integer.valueOf(m153i()), m154j(), m155k(), m156l(), m.getLanguage(), m.getCountry(), m158n().getID(), m159o(), str, this.f173i.mo6790a());
    }

    /* renamed from: c */
    public C0399ca mo6795c() {
        this.f171g.mo6950a(mo6794b());
        return (C0399ca) this.f171g.mo6931b();
    }

    /* renamed from: d */
    public Environment mo6796d() {
        return this.f170f;
    }

    /* renamed from: e */
    public String mo6797e() {
        String a = this.f168d.mo6755a();
        if (a == null) {
            AppboyLogger.m1735e(f164b, "Error reading deviceId, received a null value.");
        }
        return a;
    }

    /* renamed from: f */
    public String mo6798f() {
        if (this.f175k == null) {
            this.f175k = this.f166a.getString("a", null);
        }
        return this.f175k;
    }

    /* renamed from: h */
    private void m152h() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    C0372bb.f165l = C0372bb.this.f166a.getBoolean("ac", false);
                    Method a = C0461dt.m535a("com.google.android.gms.common.GooglePlayServicesUtil", "isGooglePlayServicesAvailable", (Class<?>[]) new Class[]{Context.class});
                    if (a != null) {
                        Object a2 = C0461dt.m533a((Object) null, a, C0372bb.this.f167c);
                        if ((a2 instanceof Integer) && ((Integer) a2).intValue() == 0) {
                            Method a3 = C0461dt.m535a("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", (Class<?>[]) new Class[]{Context.class});
                            if (a3 != null) {
                                Object a4 = C0461dt.m533a((Object) null, a3, C0372bb.this.f167c);
                                if (a4 != null) {
                                    Method a5 = C0461dt.m534a(a4.getClass(), "getId", (Class<?>[]) new Class[0]);
                                    Method a6 = C0461dt.m534a(a4.getClass(), "isLimitAdTrackingEnabled", (Class<?>[]) new Class[0]);
                                    if (a5 != null && a6 != null) {
                                        if (((Boolean) C0461dt.m533a(a4, a6, new Object[0])).booleanValue()) {
                                            AppboyLogger.m1737i(C0372bb.f164b, "Google Play Services limit ad tracking enabled. User is opted out of interest-based ads. Not requesting Advertising Id.");
                                        } else {
                                            C0372bb.this.m147a((String) C0461dt.m533a(a4, a5, new Object[0]));
                                        }
                                    }
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    AppboyLogger.m1736e(C0372bb.f164b, "Failed to get ad id.", e);
                }
            }
        }).start();
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void m147a(String str) {
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m1735e(f164b, "Received null ad id, doing nothing.");
            return;
        }
        this.f175k = StringUtils.MD5(str);
        String string = this.f166a.getString("a", "");
        if (!string.equals(this.f175k)) {
            AppboyLogger.m1737i(f164b, "Advertising Id did not match stored Advertising Id. Replacing stored Advertising Id and requesting new PlaceIQ Id.");
            this.f174j.mo6736a(C0418cp.f288a, C0418cp.class);
            Editor edit = this.f166a.edit();
            edit.putString("a", this.f175k);
            if (!StringUtils.isNullOrBlank(string)) {
                f165l = true;
                edit.putBoolean("ac", true);
            }
            edit.apply();
            return;
        }
        AppboyLogger.m1737i(f164b, "Google Play Services Advertising Id matched stored Advertising Id.");
    }

    /* renamed from: i */
    private int m153i() {
        return VERSION.SDK_INT;
    }

    /* renamed from: j */
    private String m154j() {
        return Build.CPU_ABI;
    }

    /* renamed from: k */
    private String m155k() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f167c.getSystemService("phone");
        switch (telephonyManager.getPhoneType()) {
            case 0:
                return null;
            case 1:
            case 2:
                return telephonyManager.getNetworkOperatorName();
            default:
                AppboyLogger.m1739w(f164b, "Unknown phone type");
                return null;
        }
    }

    /* renamed from: l */
    private String m156l() {
        return Build.MODEL;
    }

    /* renamed from: m */
    private Locale m157m() {
        return Locale.getDefault();
    }

    /* renamed from: n */
    private TimeZone m158n() {
        return TimeZone.getDefault();
    }

    /* renamed from: o */
    private C0402cc m159o() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) this.f167c.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return new C0402cc(displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.xdpi, displayMetrics.ydpi, displayMetrics.densityDpi);
    }

    /* renamed from: a */
    private Environment m145a(C0448dk dkVar) {
        String packageName = this.f167c.getPackageName();
        PackageInfo b = m150b(packageName);
        return new Environment("1.18.0", b.versionCode, b.versionName, packageName, dkVar);
    }

    /* renamed from: b */
    private PackageInfo m150b(String str) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = this.f167c.getPackageManager().getPackageInfo(str, 0);
        } catch (NameNotFoundException e) {
            AppboyLogger.m1736e(f164b, String.format("Unable to inspect package [%s]", new Object[]{str}), e);
        }
        ApplicationInfo applicationInfo = this.f167c.getApplicationInfo();
        if (packageInfo == null) {
            return this.f167c.getPackageManager().getPackageArchiveInfo(applicationInfo.sourceDir, 0);
        }
        return packageInfo;
    }
}
