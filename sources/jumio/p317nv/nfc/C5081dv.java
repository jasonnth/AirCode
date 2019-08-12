package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.dv */
/* compiled from: Subband */
public abstract class C5081dv {

    /* renamed from: a */
    public boolean f5535a;

    /* renamed from: b */
    public int f5536b;

    /* renamed from: c */
    public int f5537c;

    /* renamed from: d */
    public int f5538d;

    /* renamed from: e */
    public C5053cu f5539e = null;

    /* renamed from: f */
    public int f5540f;

    /* renamed from: g */
    public int f5541g = 0;

    /* renamed from: h */
    public int f5542h;

    /* renamed from: i */
    public int f5543i;

    /* renamed from: j */
    public int f5544j;

    /* renamed from: k */
    public int f5545k;

    /* renamed from: l */
    public int f5546l;

    /* renamed from: m */
    public int f5547m;

    /* renamed from: n */
    public int f5548n;

    /* renamed from: o */
    public int f5549o;

    /* renamed from: a */
    public abstract C5081dv mo47138a();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public abstract C5081dv mo47140a(C5082dw dwVar, C5082dw dwVar2);

    /* renamed from: b */
    public abstract C5081dv mo47141b();

    /* renamed from: c */
    public abstract C5081dv mo47142c();

    /* renamed from: d */
    public abstract C5081dv mo47143d();

    /* renamed from: e */
    public abstract C5081dv mo47144e();

    /* access modifiers changed from: protected */
    /* renamed from: f */
    public void mo47145f() {
        C5081dv b = mo47141b();
        C5081dv c = mo47142c();
        C5081dv d = mo47143d();
        C5081dv e = mo47144e();
        b.f5537c = this.f5537c + 1;
        b.f5542h = (this.f5542h + 1) >> 1;
        b.f5543i = (this.f5543i + 1) >> 1;
        b.f5544j = this.f5544j;
        b.f5545k = this.f5545k;
        b.f5546l = (((this.f5542h + this.f5546l) + 1) >> 1) - b.f5542h;
        b.f5547m = (((this.f5543i + this.f5547m) + 1) >> 1) - b.f5543i;
        b.f5538d = this.f5536b == 0 ? this.f5538d - 1 : this.f5538d;
        b.f5540f = this.f5540f;
        b.f5541g = this.f5541g << 2;
        c.f5536b = 1;
        c.f5537c = b.f5537c;
        c.f5542h = this.f5542h >> 1;
        c.f5543i = b.f5543i;
        c.f5544j = this.f5544j + b.f5546l;
        c.f5545k = this.f5545k;
        c.f5546l = ((this.f5542h + this.f5546l) >> 1) - c.f5542h;
        c.f5547m = b.f5547m;
        c.f5538d = this.f5538d;
        c.f5540f = this.f5540f + 1;
        c.f5541g = (this.f5541g << 2) + 1;
        d.f5536b = 2;
        d.f5537c = b.f5537c;
        d.f5542h = b.f5542h;
        d.f5543i = this.f5543i >> 1;
        d.f5544j = this.f5544j;
        d.f5545k = this.f5545k + b.f5547m;
        d.f5546l = b.f5546l;
        d.f5547m = ((this.f5543i + this.f5547m) >> 1) - d.f5543i;
        d.f5538d = this.f5538d;
        d.f5540f = this.f5540f + 1;
        d.f5541g = (this.f5541g << 2) + 2;
        e.f5536b = 3;
        e.f5537c = b.f5537c;
        e.f5542h = c.f5542h;
        e.f5543i = d.f5543i;
        e.f5544j = c.f5544j;
        e.f5545k = d.f5545k;
        e.f5546l = c.f5546l;
        e.f5547m = d.f5547m;
        e.f5538d = this.f5538d;
        e.f5540f = this.f5540f + 2;
        e.f5541g = (this.f5541g << 2) + 3;
    }

    public C5081dv() {
    }

    public C5081dv(int i, int i2, int i3, int i4, int i5, C5082dw[] dwVarArr, C5082dw[] dwVarArr2) {
        this.f5546l = i;
        this.f5547m = i2;
        this.f5542h = i3;
        this.f5543i = i4;
        this.f5538d = i5;
        for (int i6 = 0; i6 < i5; i6++) {
            this = this.mo47140a(dwVarArr[this.f5538d <= dwVarArr.length ? this.f5538d - 1 : dwVarArr.length - 1], dwVarArr2[(this.f5538d <= dwVarArr2.length ? this.f5538d : dwVarArr2.length) - 1]);
        }
    }

    /* renamed from: a */
    public C5081dv mo47139a(int i, int i2) {
        if (i > this.f5538d || i < 0) {
            throw new IllegalArgumentException("Resolution level index out of range");
        } else if (i == this.f5538d && i2 == this.f5541g) {
            return this;
        } else {
            if (this.f5541g != 0) {
                this = mo47138a();
            }
            while (this.f5538d > i) {
                this = this.mo47141b();
            }
            while (this.f5538d < i) {
                this = this.mo47138a();
            }
            switch (i2) {
                case 1:
                    return this.mo47142c();
                case 2:
                    return this.mo47143d();
                case 3:
                    return this.mo47144e();
                default:
                    return this;
            }
        }
    }
}
