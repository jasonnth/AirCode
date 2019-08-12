package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.eg */
/* compiled from: SubbandSyn */
public class C5093eg extends C5081dv {

    /* renamed from: p */
    public C5094eh f5564p;

    /* renamed from: q */
    public C5094eh f5565q;

    /* renamed from: r */
    public int f5566r = 0;

    /* renamed from: s */
    private C5093eg f5567s;

    /* renamed from: t */
    private C5093eg f5568t;

    /* renamed from: u */
    private C5093eg f5569u;

    /* renamed from: v */
    private C5093eg f5570v;

    /* renamed from: w */
    private C5093eg f5571w;

    public C5093eg() {
    }

    public C5093eg(int i, int i2, int i3, int i4, int i5, C5082dw[] dwVarArr, C5082dw[] dwVarArr2) {
        super(i, i2, i3, i4, i5, dwVarArr, dwVarArr2);
    }

    /* renamed from: a */
    public C5081dv mo47138a() {
        return this.f5567s;
    }

    /* renamed from: b */
    public C5081dv mo47141b() {
        return this.f5568t;
    }

    /* renamed from: c */
    public C5081dv mo47142c() {
        return this.f5569u;
    }

    /* renamed from: d */
    public C5081dv mo47143d() {
        return this.f5570v;
    }

    /* renamed from: e */
    public C5081dv mo47144e() {
        return this.f5571w;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public C5081dv mo47140a(C5082dw dwVar, C5082dw dwVar2) {
        if (this.f5535a) {
            throw new IllegalArgumentException();
        }
        this.f5535a = true;
        this.f5564p = (C5094eh) dwVar;
        this.f5565q = (C5094eh) dwVar2;
        this.f5568t = new C5093eg();
        this.f5570v = new C5093eg();
        this.f5569u = new C5093eg();
        this.f5571w = new C5093eg();
        this.f5568t.f5567s = this;
        this.f5569u.f5567s = this;
        this.f5570v.f5567s = this;
        this.f5571w.f5567s = this;
        mo47145f();
        return this.f5568t;
    }

    /* renamed from: g */
    public C5082dw mo47153g() {
        return this.f5564p;
    }

    /* renamed from: h */
    public C5082dw mo47154h() {
        return this.f5564p;
    }
}
