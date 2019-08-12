package p004bo.app;

import android.annotation.TargetApi;
import android.graphics.BitmapFactory.Options;
import android.os.Build.VERSION;

/* renamed from: bo.app.gz */
public class C0580gz {

    /* renamed from: a */
    private final String f776a;

    /* renamed from: b */
    private final String f777b;

    /* renamed from: c */
    private final String f778c;

    /* renamed from: d */
    private final C0563gp f779d;

    /* renamed from: e */
    private final C0562go f780e;

    /* renamed from: f */
    private final C0566gs f781f;

    /* renamed from: g */
    private final C0586hd f782g;

    /* renamed from: h */
    private final Object f783h;

    /* renamed from: i */
    private final boolean f784i;

    /* renamed from: j */
    private final Options f785j = new Options();

    public C0580gz(String str, String str2, String str3, C0563gp gpVar, C0566gs gsVar, C0586hd hdVar, C0540ge geVar) {
        this.f776a = str;
        this.f777b = str2;
        this.f778c = str3;
        this.f779d = gpVar;
        this.f780e = geVar.mo7120j();
        this.f781f = gsVar;
        this.f782g = hdVar;
        this.f783h = geVar.mo7124n();
        this.f784i = geVar.mo7123m();
        m978a(geVar.mo7121k(), this.f785j);
    }

    /* renamed from: a */
    private void m978a(Options options, Options options2) {
        options2.inDensity = options.inDensity;
        options2.inDither = options.inDither;
        options2.inInputShareable = options.inInputShareable;
        options2.inJustDecodeBounds = options.inJustDecodeBounds;
        options2.inPreferredConfig = options.inPreferredConfig;
        options2.inPurgeable = options.inPurgeable;
        options2.inSampleSize = options.inSampleSize;
        options2.inScaled = options.inScaled;
        options2.inScreenDensity = options.inScreenDensity;
        options2.inTargetDensity = options.inTargetDensity;
        options2.inTempStorage = options.inTempStorage;
        if (VERSION.SDK_INT >= 10) {
            m979b(options, options2);
        }
        if (VERSION.SDK_INT >= 11) {
            m980c(options, options2);
        }
    }

    @TargetApi(10)
    /* renamed from: b */
    private void m979b(Options options, Options options2) {
        options2.inPreferQualityOverSpeed = options.inPreferQualityOverSpeed;
    }

    @TargetApi(11)
    /* renamed from: c */
    private void m980c(Options options, Options options2) {
        options2.inBitmap = options.inBitmap;
        options2.inMutable = options.inMutable;
    }

    /* renamed from: a */
    public String mo7235a() {
        return this.f776a;
    }

    /* renamed from: b */
    public String mo7236b() {
        return this.f777b;
    }

    /* renamed from: c */
    public C0563gp mo7237c() {
        return this.f779d;
    }

    /* renamed from: d */
    public C0562go mo7238d() {
        return this.f780e;
    }

    /* renamed from: e */
    public C0566gs mo7239e() {
        return this.f781f;
    }

    /* renamed from: f */
    public C0586hd mo7240f() {
        return this.f782g;
    }

    /* renamed from: g */
    public Object mo7241g() {
        return this.f783h;
    }

    /* renamed from: h */
    public boolean mo7242h() {
        return this.f784i;
    }

    /* renamed from: i */
    public Options mo7243i() {
        return this.f785j;
    }
}
