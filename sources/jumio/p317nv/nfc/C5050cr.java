package jumio.p317nv.nfc;

import java.io.EOFException;
import java.io.IOException;
import java.util.Vector;

/* renamed from: jumio.nv.nfc.cr */
/* compiled from: FileFormatReader */
public class C5050cr {

    /* renamed from: a */
    public boolean f5471a;

    /* renamed from: b */
    private C5065df f5472b;

    /* renamed from: c */
    private Vector f5473c;

    /* renamed from: d */
    private Vector f5474d;

    public C5050cr(C5065df dfVar) {
        this.f5472b = dfVar;
    }

    /* JADX WARNING: CFG modification limit reached, blocks count: 163 */
    /* renamed from: a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void mo47092a() throws java.io.IOException, java.io.EOFException {
        /*
            r12 = this;
            r10 = 0
            r7 = 1
            r8 = 0
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r0 = r0.mo47119c()     // Catch:{ EOFException -> 0x003d }
            r1 = 12
            if (r0 != r1) goto L_0x0024
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r0 = r0.mo47119c()     // Catch:{ EOFException -> 0x003d }
            r1 = 1783636000(0x6a502020, float:6.290207E25)
            if (r0 != r1) goto L_0x0024
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r0 = r0.mo47119c()     // Catch:{ EOFException -> 0x003d }
            r1 = 218793738(0xd0a870a, float:4.268708E-31)
            if (r0 == r1) goto L_0x0051
        L_0x0024:
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            r1 = 0
            r0.mo47121a(r1)     // Catch:{ EOFException -> 0x003d }
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            short r0 = r0.mo47117a()     // Catch:{ EOFException -> 0x003d }
            r1 = -177(0xffffffffffffff4f, float:NaN)
            if (r0 == r1) goto L_0x0047
            java.lang.Error r0 = new java.lang.Error     // Catch:{ EOFException -> 0x003d }
            java.lang.String r1 = "File is neither valid JP2 file nor valid JPEG 2000 codestream"
            r0.<init>(r1)     // Catch:{ EOFException -> 0x003d }
            throw r0     // Catch:{ EOFException -> 0x003d }
        L_0x003d:
            r0 = move-exception
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "EOF reached before finding Contiguous Codestream Box"
            r0.<init>(r1)
            throw r0
        L_0x0047:
            r0 = 0
            r12.f5471a = r0     // Catch:{ EOFException -> 0x003d }
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            r1 = 0
            r0.mo47121a(r1)     // Catch:{ EOFException -> 0x003d }
        L_0x0050:
            return
        L_0x0051:
            r0 = 1
            r12.f5471a = r0     // Catch:{ EOFException -> 0x003d }
            boolean r0 = r12.mo47096b()     // Catch:{ EOFException -> 0x003d }
            if (r0 != 0) goto L_0x0104
            java.lang.Error r0 = new java.lang.Error     // Catch:{ EOFException -> 0x003d }
            java.lang.String r1 = "Invalid JP2 file: File Type box missing"
            r0.<init>(r1)     // Catch:{ EOFException -> 0x003d }
            throw r0     // Catch:{ EOFException -> 0x003d }
        L_0x0063:
            long r1 = (long) r9     // Catch:{ EOFException -> 0x003d }
            r0 = r12
            r0.mo47097b(r1, r3, r4)     // Catch:{ EOFException -> 0x003d }
            r0 = r6
        L_0x0069:
            if (r8 != 0) goto L_0x0100
            jumio.nv.nfc.df r1 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r2 = r9 + r3
            r1.mo47121a(r2)     // Catch:{ EOFException -> 0x003d }
            r6 = r0
            r0 = r8
        L_0x0074:
            if (r0 != 0) goto L_0x00ef
            jumio.nv.nfc.df r1 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r9 = r1.mo47123e()     // Catch:{ EOFException -> 0x003d }
            jumio.nv.nfc.df r1 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r3 = r1.mo47119c()     // Catch:{ EOFException -> 0x003d }
            int r1 = r9 + r3
            jumio.nv.nfc.df r2 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r2 = r2.mo47124f()     // Catch:{ EOFException -> 0x003d }
            if (r1 != r2) goto L_0x008d
            r0 = r7
        L_0x008d:
            jumio.nv.nfc.df r1 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r1 = r1.mo47119c()     // Catch:{ EOFException -> 0x003d }
            if (r3 != 0) goto L_0x00aa
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r0 = r0.mo47124f()     // Catch:{ EOFException -> 0x003d }
            jumio.nv.nfc.df r2 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            int r2 = r2.mo47123e()     // Catch:{ EOFException -> 0x003d }
            int r3 = r0 - r2
            r8 = r7
            r4 = r10
        L_0x00a5:
            switch(r1) {
                case 1685074537: goto L_0x00da;
                case 1785737827: goto L_0x00bd;
                case 1785737832: goto L_0x00c8;
                case 1969843814: goto L_0x00e9;
                case 1970628964: goto L_0x00e4;
                case 2020437024: goto L_0x00df;
                default: goto L_0x00a8;
            }     // Catch:{ EOFException -> 0x003d }
        L_0x00a8:
            r0 = r6
            goto L_0x0069
        L_0x00aa:
            if (r3 != r7) goto L_0x00ba
            jumio.nv.nfc.df r0 = r12.f5472b     // Catch:{ EOFException -> 0x003d }
            r0.mo47120d()     // Catch:{ EOFException -> 0x003d }
            java.io.IOException r0 = new java.io.IOException     // Catch:{ EOFException -> 0x003d }
            java.lang.String r1 = "File too long."
            r0.<init>(r1)     // Catch:{ EOFException -> 0x003d }
            throw r0     // Catch:{ EOFException -> 0x003d }
        L_0x00ba:
            r8 = r0
            r4 = r10
            goto L_0x00a5
        L_0x00bd:
            if (r6 != 0) goto L_0x0063
            java.lang.Error r0 = new java.lang.Error     // Catch:{ EOFException -> 0x003d }
            java.lang.String r1 = "Invalid JP2 file: JP2Header box not found before Contiguous codestream box "
            r0.<init>(r1)     // Catch:{ EOFException -> 0x003d }
            throw r0     // Catch:{ EOFException -> 0x003d }
        L_0x00c8:
            if (r6 == 0) goto L_0x00d3
            java.lang.Error r0 = new java.lang.Error     // Catch:{ EOFException -> 0x003d }
            java.lang.String r1 = "Invalid JP2 file: Multiple JP2Header boxes found"
            r0.<init>(r1)     // Catch:{ EOFException -> 0x003d }
            throw r0     // Catch:{ EOFException -> 0x003d }
        L_0x00d3:
            long r1 = (long) r9     // Catch:{ EOFException -> 0x003d }
            r0 = r12
            r0.mo47094a(r1, r3, r4)     // Catch:{ EOFException -> 0x003d }
            r0 = r7
            goto L_0x0069
        L_0x00da:
            r12.mo47093a(r3)     // Catch:{ EOFException -> 0x003d }
            r0 = r6
            goto L_0x0069
        L_0x00df:
            r12.mo47095b(r3)     // Catch:{ EOFException -> 0x003d }
            r0 = r6
            goto L_0x0069
        L_0x00e4:
            r12.mo47099c(r3)     // Catch:{ EOFException -> 0x003d }
            r0 = r6
            goto L_0x0069
        L_0x00e9:
            r12.mo47100d(r3)     // Catch:{ EOFException -> 0x003d }
            r0 = r6
            goto L_0x0069
        L_0x00ef:
            java.util.Vector r0 = r12.f5473c
            int r0 = r0.size()
            if (r0 != 0) goto L_0x0050
            java.lang.Error r0 = new java.lang.Error
            java.lang.String r1 = "Invalid JP2 file: Contiguous codestream box missing"
            r0.<init>(r1)
            throw r0
        L_0x0100:
            r6 = r0
            r0 = r8
            goto L_0x0074
        L_0x0104:
            r0 = r8
            r6 = r8
            goto L_0x0074
        */
        throw new UnsupportedOperationException("Method not decompiled: jumio.p317nv.nfc.C5050cr.mo47092a():void");
    }

    /* renamed from: b */
    public boolean mo47096b() throws IOException, EOFException {
        this.f5472b.mo47123e();
        int c = this.f5472b.mo47119c();
        if (c == 0) {
            throw new Error("Zero-length of Profile Box");
        } else if (this.f5472b.mo47119c() != 1718909296) {
            return false;
        } else {
            if (c == 1) {
                this.f5472b.mo47120d();
                throw new IOException("File too long.");
            }
            this.f5472b.mo47119c();
            this.f5472b.mo47119c();
            boolean z = false;
            for (int i = (c - 16) / 4; i > 0; i--) {
                if (this.f5472b.mo47119c() == 1785737760) {
                    z = true;
                }
            }
            if (!z) {
                return false;
            }
            return true;
        }
    }

    /* renamed from: a */
    public boolean mo47094a(long j, int i, long j2) throws IOException, EOFException {
        if (i != 0) {
            return true;
        }
        throw new Error("Zero-length of JP2Header Box");
    }

    /* renamed from: b */
    public boolean mo47097b(long j, int i, long j2) throws IOException, EOFException {
        int e = this.f5472b.mo47123e();
        if (this.f5473c == null) {
            this.f5473c = new Vector();
        }
        this.f5473c.addElement(new Integer(e));
        if (this.f5474d == null) {
            this.f5474d = new Vector();
        }
        this.f5474d.addElement(new Integer(i));
        return true;
    }

    /* renamed from: a */
    public void mo47093a(int i) {
    }

    /* renamed from: b */
    public void mo47095b(int i) {
    }

    /* renamed from: c */
    public void mo47099c(int i) {
    }

    /* renamed from: d */
    public void mo47100d(int i) {
    }

    /* renamed from: c */
    public int mo47098c() {
        return ((Integer) this.f5473c.elementAt(0)).intValue();
    }
}
