package p004bo.app;

/* renamed from: bo.app.gp */
public class C0563gp {

    /* renamed from: a */
    private final int f743a;

    /* renamed from: b */
    private final int f744b;

    public C0563gp(int i, int i2) {
        this.f743a = i;
        this.f744b = i2;
    }

    public C0563gp(int i, int i2, int i3) {
        if (i3 % 180 == 0) {
            this.f743a = i;
            this.f744b = i2;
            return;
        }
        this.f743a = i2;
        this.f744b = i;
    }

    /* renamed from: a */
    public int mo7183a() {
        return this.f743a;
    }

    /* renamed from: b */
    public int mo7186b() {
        return this.f744b;
    }

    /* renamed from: a */
    public C0563gp mo7185a(int i) {
        return new C0563gp(this.f743a / i, this.f744b / i);
    }

    /* renamed from: a */
    public C0563gp mo7184a(float f) {
        return new C0563gp((int) (((float) this.f743a) * f), (int) (((float) this.f744b) * f));
    }

    public String toString() {
        return this.f743a + "x" + this.f744b;
    }
}
