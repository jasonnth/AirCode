package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.ea */
/* compiled from: InvWTAdapter */
public abstract class C5087ea implements C5085dz {

    /* renamed from: a */
    protected C5039cg f5553a;

    /* renamed from: b */
    protected C5091ee f5554b;

    /* renamed from: c */
    protected int f5555c;

    /* renamed from: d */
    protected int f5556d;

    protected C5087ea(C5091ee eeVar, C5039cg cgVar) {
        this.f5554b = eeVar;
        this.f5553a = cgVar;
        this.f5556d = cgVar.f5407g.mo46967a();
    }

    /* renamed from: i */
    public void mo47152i(int i) {
        if (i < 0) {
            throw new IllegalArgumentException("Resolution level index cannot be negative.");
        }
        this.f5555c = i;
    }

    /* renamed from: c */
    public int mo47107c() {
        return this.f5554b.mo47010c(this.f5555c);
    }

    /* renamed from: d */
    public int mo47109d() {
        return this.f5554b.mo47014d(this.f5555c);
    }

    /* renamed from: b */
    public int mo46939b() {
        return this.f5554b.mo47009c();
    }

    /* renamed from: e */
    public int mo46944e(int i) {
        return this.f5554b.mo46999a(i);
    }

    /* renamed from: f */
    public int mo46945f(int i) {
        return this.f5554b.mo47005b(i);
    }

    /* renamed from: b */
    public int mo46941b(int i, int i2) {
        return this.f5554b.mo47001a(i, i2, this.f5554b.mo47019e(i, i2).f5538d);
    }

    /* renamed from: a */
    public int mo46938a(int i, int i2) {
        return this.f5554b.mo47007b(i, i2, this.f5554b.mo47019e(i, i2).f5538d);
    }

    /* renamed from: d */
    public int mo46943d(int i) {
        return this.f5554b.mo47000a(i, this.f5553a.f5407g.mo46968a(i));
    }

    /* renamed from: c */
    public int mo46942c(int i) {
        return this.f5554b.mo47006b(i, this.f5553a.f5407g.mo46968a(i));
    }

    /* renamed from: c */
    public void mo47108c(int i, int i2) {
        this.f5554b.mo47028f(i, i2);
    }

    /* renamed from: e */
    public int mo47110e() {
        return this.f5554b.mo47013d();
    }

    /* renamed from: g */
    public int mo46946g(int i) {
        return this.f5554b.mo47011c(i, this.f5554b.mo47019e(mo47110e(), i).f5538d);
    }

    /* renamed from: h */
    public int mo46947h(int i) {
        return this.f5554b.mo47015d(i, this.f5554b.mo47019e(mo47110e(), i).f5538d);
    }

    /* renamed from: f */
    public int mo47111f() {
        return this.f5554b.mo47018e(this.f5555c);
    }

    /* renamed from: g */
    public int mo47112g() {
        return this.f5554b.mo47020f(this.f5555c);
    }

    /* renamed from: a */
    public C5053cu mo47106a(C5053cu cuVar) {
        return this.f5554b.mo47008b(cuVar);
    }
}
