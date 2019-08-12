package com.threatmetrix.TrustDefender;

import android.content.Context;
import java.util.concurrent.TimeUnit;

public class Config extends C4788b {

    /* renamed from: a */
    private int f4052a = 30;

    /* renamed from: b */
    private int f4053b = 0;

    /* renamed from: c */
    private int f4054c = 30000;

    /* renamed from: d */
    private boolean f4055d = true;

    /* renamed from: e */
    private boolean f4056e = false;

    /* renamed from: f */
    private int f4057f = 0;

    /* renamed from: g */
    private int f4058g = 10000;

    /* renamed from: h */
    private boolean f4059h = false;

    /* renamed from: i */
    private int f4060i = ((int) TimeUnit.MINUTES.toSeconds(3));

    /* renamed from: j */
    private int f4061j = 1;

    /* renamed from: k */
    private long f4062k = 261374;

    /* renamed from: l */
    private long f4063l = 900000;

    /* renamed from: m */
    private long f4064m = 3600000;

    /* renamed from: n */
    private boolean f4065n = false;

    /* renamed from: o */
    private boolean f4066o = false;

    /* renamed from: p */
    private boolean f4067p = false;

    /* renamed from: q */
    private boolean f4068q = false;

    /* renamed from: r */
    private String f4069r = null;

    /* renamed from: s */
    private String f4070s = C4785ar.m2699i();

    /* renamed from: t */
    private String f4071t = null;

    /* renamed from: u */
    private Context f4072u = null;

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final boolean mo45831a() {
        return this.f4059h;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public final boolean mo45832b() {
        return this.f4056e;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: c */
    public final int mo45833c() {
        return this.f4052a;
    }

    public Config setTimeout(int timeout) {
        this.f4052a = timeout;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: d */
    public final String mo45834d() {
        return this.f4069r;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: e */
    public final boolean mo45835e() {
        return this.f4065n;
    }

    public Config setRegisterForLocationServices(boolean registerForLocationServices) {
        this.f4065n = registerForLocationServices;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: f */
    public final long mo45836f() {
        return this.f4063l;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: g */
    public final long mo45837g() {
        return this.f4064m;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: h */
    public final int mo45838h() {
        return this.f4061j;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: i */
    public final long mo45839i() {
        long options = this.f4062k;
        if (this.f4067p) {
            options &= -39;
        }
        if (this.f4068q) {
            return options & -12289;
        }
        return options;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: j */
    public final Context mo45840j() {
        return this.f4072u;
    }

    public Config setContext(Context context) {
        this.f4072u = context;
        return this;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: k */
    public final int mo45841k() {
        return this.f4057f;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: l */
    public final int mo45842l() {
        return this.f4058g;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: m */
    public final int mo45843m() {
        return this.f4053b;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: n */
    public final int mo45844n() {
        return this.f4054c;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: o */
    public final boolean mo45845o() {
        return this.f4055d;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: p */
    public final String mo45846p() {
        return this.f4070s;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: q */
    public final boolean mo45847q() {
        return this.f4066o;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: r */
    public final int mo45848r() {
        return this.f4060i;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: s */
    public final String mo45849s() {
        return this.f4071t;
    }

    public Config setOrgId(String m_orgId) {
        this.f4071t = m_orgId;
        return this;
    }
}
