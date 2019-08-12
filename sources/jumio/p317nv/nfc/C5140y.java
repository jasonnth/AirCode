package jumio.p317nv.nfc;

import com.facebook.internal.AnalyticsEvents;
import java.io.IOException;

/* renamed from: jumio.nv.nfc.y */
/* compiled from: ColorSpace */
public class C5140y {

    /* renamed from: a */
    public static final String f5717a = System.getProperty("line.separator");

    /* renamed from: d */
    public static final C5143c f5718d = new C5143c("profiled");

    /* renamed from: e */
    public static final C5143c f5719e = new C5143c("enumerated");

    /* renamed from: f */
    public static final C5141a f5720f = new C5141a("sRGB");

    /* renamed from: g */
    public static final C5141a f5721g = new C5141a("GreyScale");

    /* renamed from: h */
    public static final C5141a f5722h = new C5141a("sYCC");

    /* renamed from: i */
    public static final C5141a f5723i = new C5141a("Illegal");

    /* renamed from: j */
    public static final C5141a f5724j = new C5141a(AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN);

    /* renamed from: b */
    public C5079dt f5725b;

    /* renamed from: c */
    public C5034cb f5726c;

    /* renamed from: k */
    private C4978ak f5727k = null;

    /* renamed from: l */
    private C4975ah f5728l = null;

    /* renamed from: m */
    private C4974ag f5729m = null;

    /* renamed from: n */
    private C4973af f5730n = null;

    /* renamed from: o */
    private C4976ai f5731o = null;

    /* renamed from: p */
    private C5065df f5732p = null;

    /* renamed from: jumio.nv.nfc.y$a */
    /* compiled from: ColorSpace */
    public static class C5141a extends C5142b {
        public C5141a(String str) {
            super(str);
        }
    }

    /* renamed from: jumio.nv.nfc.y$b */
    /* compiled from: ColorSpace */
    public static class C5142b {

        /* renamed from: a */
        public final String f5733a;

        public C5142b(String str) {
            this.f5733a = str;
        }
    }

    /* renamed from: jumio.nv.nfc.y$c */
    /* compiled from: ColorSpace */
    public static class C5143c extends C5142b {
        public C5143c(String str) {
            super(str);
        }
    }

    /* renamed from: a */
    public byte[] mo47259a() {
        return this.f5729m.mo46951c();
    }

    public C5140y(C5065df dfVar, C5034cb cbVar, C5079dt dtVar) throws IOException, C5144z {
        this.f5725b = dtVar;
        this.f5732p = dfVar;
        this.f5726c = cbVar;
        mo47260b();
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final void mo47260b() throws C5144z, IOException {
        byte[] bArr = new byte[16];
        int i = 0;
        int i2 = 0;
        while (true) {
            this.f5732p.mo47121a(i2);
            this.f5732p.mo47122a(bArr, 0, 16);
            long e = (long) C4981an.m3258e(bArr, 0);
            if (e == 1) {
                e = C4981an.m3259f(bArr, 8);
            }
            int e2 = C4981an.m3258e(bArr, 4);
            if (i == 0 && e2 != 1783636000) {
                throw new C5144z("first box in image not signature");
            } else if (i == 1 && e2 != 1718909296) {
                throw new C5144z("second box in image not file");
            } else if (e2 == 1785737827) {
                throw new C5144z("header box not found in image");
            } else if (e2 == 1785737832) {
                long j = ((long) i2) + e;
                if (e == 1) {
                    i2 += 8;
                }
                int i3 = i2 + 8;
                while (((long) i3) < j) {
                    this.f5732p.mo47121a(i3);
                    this.f5732p.mo47122a(bArr, 0, 16);
                    long e3 = (long) C4981an.m3258e(bArr, 0);
                    if (e3 == 1) {
                        throw new C5144z("Extended length boxes not supported");
                    }
                    switch (C4981an.m3258e(bArr, 4)) {
                        case 1667523942:
                            this.f5730n = new C4973af(this.f5732p, i3);
                            break;
                        case 1668112752:
                            this.f5728l = new C4975ah(this.f5732p, i3);
                            break;
                        case 1668246642:
                            this.f5729m = new C4974ag(this.f5732p, i3);
                            break;
                        case 1768449138:
                            this.f5731o = new C4976ai(this.f5732p, i3);
                            break;
                        case 1885564018:
                            this.f5727k = new C4978ak(this.f5732p, i3);
                            break;
                    }
                    i3 = (int) (e3 + ((long) i3));
                }
                if (this.f5731o == null) {
                    throw new C5144z("image header box not found");
                } else if ((this.f5727k == null && this.f5728l != null) || (this.f5727k != null && this.f5728l == null)) {
                    throw new C5144z("palette box and component mapping box inconsistency");
                } else {
                    return;
                }
            } else {
                i++;
                i2 = (int) (e + ((long) i2));
            }
        }
    }

    /* renamed from: a */
    public int mo47258a(int i) {
        return this.f5730n == null ? i : this.f5730n.mo46948a(i + 1);
    }

    /* renamed from: c */
    public C5143c mo47261c() {
        return this.f5729m.mo46949a();
    }

    /* renamed from: d */
    public C5141a mo47262d() {
        return this.f5729m.mo46950b();
    }

    /* renamed from: e */
    public C4978ak mo47263e() {
        return this.f5727k;
    }
}
