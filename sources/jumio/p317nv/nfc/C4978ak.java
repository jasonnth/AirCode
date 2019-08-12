package jumio.p317nv.nfc;

import java.io.IOException;

/* renamed from: jumio.nv.nfc.ak */
/* compiled from: PaletteBox */
public final class C4978ak extends C4977aj {

    /* renamed from: a */
    private int f4997a;

    /* renamed from: b */
    private int f4998b;

    /* renamed from: c */
    private short[] f4999c;

    /* renamed from: d */
    private int[][] f5000d;

    static {
        f4991i = 1885564018;
    }

    public C4978ak(C5065df dfVar, int i) throws IOException, C5144z {
        super(dfVar, i);
        mo46955a();
    }

    /* JADX WARNING: type inference failed for: r4v1, types: [byte[]] */
    /* JADX WARNING: type inference failed for: r3v15, types: [byte] */
    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r3v15, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte[], code=null, for r4v1, types: [byte[]] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo46955a() throws java.io.IOException, jumio.p317nv.nfc.C5144z {
        /*
            r9 = this;
            r4 = 65535(0xffff, float:9.1834E-41)
            r8 = 2
            r7 = 1
            r1 = 0
            r0 = 4
            byte[] r0 = new byte[r0]
            jumio.nv.nfc.df r2 = r9.f4993k
            int r3 = r9.f4996n
            r2.mo47121a(r3)
            jumio.nv.nfc.df r2 = r9.f4993k
            r3 = 3
            r2.mo47122a(r0, r1, r3)
            short r2 = jumio.p317nv.nfc.C4981an.m3257d(r0, r1)
            r2 = r2 & r4
            r9.f4997a = r2
            byte r0 = r0[r8]
            r0 = r0 & r4
            r9.f4998b = r0
            int r0 = r9.f4998b
            short[] r0 = new short[r0]
            r9.f4999c = r0
            int r0 = r9.f4998b
            byte[] r2 = new byte[r0]
            jumio.nv.nfc.df r0 = r9.f4993k
            int r3 = r9.f4998b
            r0.mo47122a(r2, r1, r3)
            r0 = r1
        L_0x0034:
            int r3 = r9.f4998b
            if (r0 >= r3) goto L_0x0044
            short[] r3 = r9.f4999c
            byte r4 = r2[r0]
            r4 = r4 & 4095(0xfff, float:5.738E-42)
            short r4 = (short) r4
            r3[r0] = r4
            int r0 = r0 + 1
            goto L_0x0034
        L_0x0044:
            int r0 = r9.f4997a
            int r2 = r9.f4998b
            int r0 = r0 * r2
            int[][] r0 = new int[r0][]
            r9.f5000d = r0
            byte[] r4 = new byte[r8]
            r0 = r1
        L_0x0050:
            int r2 = r9.f4997a
            if (r0 >= r2) goto L_0x00bb
            int[][] r2 = r9.f5000d
            int r3 = r9.f4998b
            int[] r3 = new int[r3]
            r2[r0] = r3
            r2 = r1
        L_0x005d:
            int r3 = r9.f4998b
            if (r2 >= r3) goto L_0x00b8
            short r5 = r9.mo46958b(r2)
            boolean r6 = r9.mo46956a(r2)
            int r3 = r9.m3242c(r2)
            switch(r3) {
                case 1: goto L_0x0079;
                case 2: goto L_0x0097;
                default: goto L_0x0070;
            }
        L_0x0070:
            jumio.nv.nfc.z r0 = new jumio.nv.nfc.z
            java.lang.String r1 = "palettes greater than 16 bits deep not supported"
            r0.<init>(r1)
            throw r0
        L_0x0079:
            jumio.nv.nfc.df r3 = r9.f4993k
            r3.mo47122a(r4, r1, r7)
            byte r3 = r4[r1]
        L_0x0080:
            if (r6 == 0) goto L_0x00ac
            int r6 = r5 + -1
            int r6 = r7 << r6
            r6 = r6 & r3
            if (r6 != 0) goto L_0x00a1
            int r5 = r7 << r5
            int r5 = r5 + -1
            int[][] r6 = r9.f5000d
            r6 = r6[r0]
            r3 = r3 & r5
            r6[r2] = r3
        L_0x0094:
            int r2 = r2 + 1
            goto L_0x005d
        L_0x0097:
            jumio.nv.nfc.df r3 = r9.f4993k
            r3.mo47122a(r4, r1, r8)
            short r3 = jumio.p317nv.nfc.C4981an.m3257d(r4, r1)
            goto L_0x0080
        L_0x00a1:
            r6 = -1
            int r5 = r6 << r5
            int[][] r6 = r9.f5000d
            r6 = r6[r0]
            r3 = r3 | r5
            r6[r2] = r3
            goto L_0x0094
        L_0x00ac:
            int r5 = r7 << r5
            int r5 = r5 + -1
            int[][] r6 = r9.f5000d
            r6 = r6[r0]
            r3 = r3 & r5
            r6[r2] = r3
            goto L_0x0094
        L_0x00b8:
            int r0 = r0 + 1
            goto L_0x0050
        L_0x00bb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C4978ak.mo46955a():void");
    }

    /* renamed from: b */
    public int mo46957b() {
        return this.f4998b;
    }

    /* renamed from: a */
    public boolean mo46956a(int i) {
        return (this.f4999c[i] & 128) != 0;
    }

    /* renamed from: b */
    public short mo46958b(int i) {
        return (short) ((this.f4999c[i] & 127) + 1);
    }

    /* renamed from: a */
    public int mo46954a(int i, int i2) {
        return this.f5000d[i2][i];
    }

    /* renamed from: c */
    private int m3242c(int i) {
        short b = mo46958b(i);
        return (b % 8) + (b / 8) == 0 ? 0 : 1;
    }
}
