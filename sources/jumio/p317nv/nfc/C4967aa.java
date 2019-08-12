package jumio.p317nv.nfc;

import java.io.IOException;
import jumio.p317nv.nfc.C5140y.C5141a;

/* renamed from: jumio.nv.nfc.aa */
/* compiled from: ColorSpaceMapper */
public abstract class C4967aa extends C5058cz implements C5051cs {

    /* renamed from: a */
    protected static final String f4935a = System.getProperty("line.separator");

    /* renamed from: s */
    private static final String[][] f4936s = null;

    /* renamed from: b */
    protected C5056cx[] f4937b;

    /* renamed from: c */
    protected C5055cw[] f4938c;

    /* renamed from: d */
    protected C5056cx[] f4939d;

    /* renamed from: e */
    protected C5055cw[] f4940e;

    /* renamed from: f */
    protected int[][] f4941f;

    /* renamed from: g */
    protected float[][] f4942g;

    /* renamed from: h */
    protected float[][] f4943h;

    /* renamed from: i */
    protected int[][] f4944i;

    /* renamed from: j */
    protected int[] f4945j = null;

    /* renamed from: k */
    protected int[] f4946k = null;

    /* renamed from: l */
    protected int[] f4947l = null;

    /* renamed from: m */
    protected C5079dt f4948m = null;

    /* renamed from: n */
    protected C5140y f4949n = null;

    /* renamed from: o */
    protected int f4950o = 0;

    /* renamed from: p */
    protected C5051cs f4951p = null;

    /* renamed from: q */
    protected C5054cv[] f4952q = null;

    /* renamed from: r */
    protected C4968a f4953r = new C4968a();

    /* renamed from: jumio.nv.nfc.aa$a */
    /* compiled from: ColorSpaceMapper */
    public class C4968a {

        /* renamed from: b */
        private int f4955b = -1;

        /* renamed from: c */
        private int f4956c = -1;

        /* renamed from: d */
        private int f4957d = -1;

        /* renamed from: e */
        private int f4958e = -1;

        /* renamed from: f */
        private int f4959f = -1;

        /* renamed from: g */
        private int f4960g = -1;

        /* renamed from: h */
        private int f4961h = -1;

        public C4968a() {
            mo46937a();
        }

        /* renamed from: a */
        public void mo46937a() {
            this.f4961h = -1;
            this.f4960g = -1;
            this.f4959f = -1;
            this.f4958e = -1;
            this.f4957d = -1;
            this.f4956c = -1;
        }
    }

    /* renamed from: a */
    public static String[][] m3190a() {
        return f4936s;
    }

    /* renamed from: a */
    protected static void m3188a(C5054cv cvVar) {
        switch (cvVar.mo47101a()) {
            case 3:
                if (cvVar.mo47103b() == null || ((int[]) cvVar.mo47103b()).length < cvVar.f5479c * cvVar.f5480d) {
                    cvVar.mo47102a(new int[(cvVar.f5479c * cvVar.f5480d)]);
                    return;
                }
                return;
            case 4:
                if (cvVar.mo47103b() == null || ((float[]) cvVar.mo47103b()).length < cvVar.f5479c * cvVar.f5480d) {
                    cvVar.mo47102a(new float[(cvVar.f5479c * cvVar.f5480d)]);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException("Invalid output datablock type");
        }
    }

    /* renamed from: a */
    protected static void m3189a(C5054cv cvVar, C5054cv cvVar2) {
        cvVar.f5481e = 0;
        cvVar.f5480d = cvVar2.f5480d;
        cvVar.f5479c = cvVar2.f5479c;
        cvVar.f5477a = cvVar2.f5477a;
        cvVar.f5478b = cvVar2.f5478b;
        cvVar.f5482f = cvVar2.f5479c;
        m3188a(cvVar);
    }

    /* renamed from: b */
    public static C5051cs m3191b(C5051cs csVar, C5140y yVar) throws IOException, C5144z, C4982ao {
        C5079dt dtVar = yVar.f5725b;
        C5079dt dtVar2 = yVar.f5725b;
        dtVar.mo47133a('I', C5079dt.m3577a(f4936s));
        if (yVar.mo47261c() == C5140y.f5718d) {
            return C4984aq.m3262a(csVar, yVar);
        }
        C5141a d = yVar.mo47262d();
        if (d == C5140y.f5720f) {
            return C4969ab.m3197a(csVar, yVar);
        }
        if (d == C5140y.f5721g) {
            return C4969ab.m3197a(csVar, yVar);
        }
        if (d == C5140y.f5722h) {
            return C4972ae.m3223a(csVar, yVar);
        }
        if (d == C5140y.f5724j) {
            return null;
        }
        throw new C5144z("Bad color space specification in image");
    }

    protected C4967aa(C5051cs csVar, C5140y yVar) throws C5144z {
        super(csVar);
        this.f4951p = csVar;
        this.f4949n = yVar;
        m3192h();
    }

    /* renamed from: h */
    private void m3192h() throws C5144z {
        this.f4948m = this.f4949n.f5725b;
        this.f4950o = this.f4951p.mo46939b();
        this.f4945j = new int[this.f4950o];
        this.f4946k = new int[this.f4950o];
        this.f4947l = new int[this.f4950o];
        this.f4952q = new C5054cv[this.f4950o];
        this.f4937b = new C5056cx[this.f4950o];
        this.f4938c = new C5055cw[this.f4950o];
        this.f4939d = new C5056cx[this.f4950o];
        this.f4940e = new C5055cw[this.f4950o];
        this.f4941f = new int[this.f4950o][];
        this.f4942g = new float[this.f4950o][];
        this.f4944i = new int[this.f4950o][];
        this.f4943h = new float[this.f4950o][];
        this.f4941f = new int[this.f4950o][];
        this.f4942g = new float[this.f4950o][];
        for (int i = 0; i < this.f4950o; i++) {
            this.f4945j[i] = 1 << (this.f4951p.mo46940b(i) - 1);
            this.f4946k[i] = (1 << this.f4951p.mo46940b(i)) - 1;
            this.f4947l[i] = this.f4951p.mo46934a(i);
            this.f4937b[i] = new C5056cx();
            this.f4938c[i] = new C5055cw();
            this.f4939d[i] = new C5056cx();
            this.f4939d[i].f5483g = this.f4937b[i].f5483g;
            this.f4940e[i] = new C5055cw();
            this.f4940e[i].f5483g = this.f4938c[i].f5483g;
        }
    }

    /* renamed from: a */
    public int mo46934a(int i) {
        return this.f4951p.mo46934a(i);
    }

    /* renamed from: a */
    public C5054cv mo46935a(C5054cv cvVar, int i) {
        return this.f4951p.mo46935a(cvVar, i);
    }

    /* renamed from: b */
    public C5054cv mo46936b(C5054cv cvVar, int i) {
        return this.f4951p.mo46936b(cvVar, i);
    }
}
