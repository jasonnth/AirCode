package jumio.p317nv.nfc;

/* renamed from: jumio.nv.nfc.at */
/* compiled from: RestrictedICCProfile */
public abstract class C4987at {

    /* renamed from: a */
    protected static final String f5029a = System.getProperty("line.separator");

    /* renamed from: b */
    public C4999be[] f5030b = new C4999be[1];

    /* renamed from: c */
    public C5006bk[] f5031c = null;

    /* renamed from: a */
    public abstract int mo46960a();

    /* renamed from: b */
    public static C4987at m3271b(C4999be beVar, C4999be beVar2, C4999be beVar3, C5006bk bkVar, C5006bk bkVar2, C5006bk bkVar3) {
        return C4985ar.m3266a(beVar, beVar2, beVar3, bkVar, bkVar2, bkVar3);
    }

    /* renamed from: b */
    public static C4987at m3270b(C4999be beVar) {
        return C4986as.m3268a(beVar);
    }

    protected C4987at(C4999be beVar) {
        this.f5030b[0] = beVar;
    }

    protected C4987at(C4999be beVar, C4999be beVar2, C4999be beVar3, C5006bk bkVar, C5006bk bkVar2, C5006bk bkVar3) {
        this.f5030b[0] = beVar;
        this.f5030b[1] = beVar2;
        this.f5030b[2] = beVar3;
        this.f5031c[0] = bkVar;
        this.f5031c[1] = bkVar2;
        this.f5031c[2] = bkVar3;
    }
}
