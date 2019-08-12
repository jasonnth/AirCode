package p004bo.app;

import android.util.Base64;
import com.appboy.support.AppboyLogger;
import com.appboy.support.StringUtils;

/* renamed from: bo.app.fe */
public abstract class C0503fe implements C0495ex {

    /* renamed from: a */
    private static final String f472a = AppboyLogger.getAppboyLogTag(C0503fe.class);

    /* renamed from: b */
    private long f473b;

    /* renamed from: c */
    private long f474c;

    /* renamed from: d */
    private C0386bo f475d;

    protected C0503fe() {
        this.f474c = C0455dp.m523c();
        this.f473b = this.f474c / 1000;
    }

    protected C0503fe(C0386bo boVar) {
        this();
        this.f475d = boVar;
    }

    /* renamed from: c */
    public long mo7049c() {
        return this.f473b;
    }

    /* renamed from: d */
    public long mo7050d() {
        return this.f474c;
    }

    /* renamed from: e */
    public C0386bo mo7051e() {
        return this.f475d;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public String mo7057a(String str) {
        String str2 = null;
        if (StringUtils.isNullOrBlank(str)) {
            return str2;
        }
        try {
            return new String(Base64.decode(str, 0)).split("_")[0];
        } catch (Exception e) {
            AppboyLogger.m1736e(f472a, String.format("Unexpected error decoding Base64 encoded campaign Id %s", new Object[]{str}), e);
            return str2;
        }
    }
}
