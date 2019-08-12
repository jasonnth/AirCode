package jumio.p317nv.nfc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Hashtable;
import java.util.Vector;
import jumio.p317nv.nfc.C5018bw.C5019a;
import jumio.p317nv.nfc.C5018bw.C5020b;
import jumio.p317nv.nfc.C5018bw.C5021c;
import jumio.p317nv.nfc.C5018bw.C5022d;
import jumio.p317nv.nfc.C5018bw.C5023e;
import jumio.p317nv.nfc.C5018bw.C5024f;
import jumio.p317nv.nfc.C5018bw.C5025g;
import jumio.p317nv.nfc.C5018bw.C5026h;
import jumio.p317nv.nfc.C5018bw.C5027i;

/* renamed from: jumio.nv.nfc.cb */
/* compiled from: HeaderDecoder */
public class C5034cb {

    /* renamed from: e */
    private static final String[][] f5331e = null;

    /* renamed from: a */
    public int[] f5332a;

    /* renamed from: b */
    boolean f5333b;

    /* renamed from: c */
    public int f5334c;

    /* renamed from: d */
    public Vector f5335d;

    /* renamed from: f */
    private C5018bw f5336f;

    /* renamed from: g */
    private String f5337g = "";

    /* renamed from: h */
    private int f5338h;

    /* renamed from: i */
    private int f5339i = 0;

    /* renamed from: j */
    private int f5340j = 0;

    /* renamed from: k */
    private int f5341k = 0;

    /* renamed from: l */
    private int f5342l = 0;

    /* renamed from: m */
    private int f5343m = 0;

    /* renamed from: n */
    private int f5344n = 0;

    /* renamed from: o */
    private int[][] f5345o = null;

    /* renamed from: p */
    private Hashtable f5346p = null;

    /* renamed from: q */
    private int f5347q;

    /* renamed from: r */
    private int f5348r = -1;

    /* renamed from: s */
    private int f5349s = -1;

    /* renamed from: t */
    private C5039cg f5350t;

    /* renamed from: u */
    private byte[][] f5351u;

    /* renamed from: v */
    private byte[][][][] f5352v;

    /* renamed from: w */
    private ByteArrayOutputStream[] f5353w;

    /* renamed from: a */
    public int mo47029a() {
        return this.f5336f.f5156a.mo46996b();
    }

    /* renamed from: b */
    public int mo47039b() {
        return this.f5336f.f5156a.mo46994a();
    }

    /* renamed from: c */
    public final int mo47042c() {
        return this.f5336f.f5156a.f5225c - this.f5336f.f5156a.f5227e;
    }

    /* renamed from: d */
    public final int mo47045d() {
        return this.f5336f.f5156a.f5226d - this.f5336f.f5156a.f5228f;
    }

    /* renamed from: e */
    public final int mo47048e() {
        return this.f5336f.f5156a.f5227e;
    }

    /* renamed from: f */
    public final int mo47050f() {
        return this.f5336f.f5156a.f5228f;
    }

    /* renamed from: g */
    public final int mo47051g() {
        return this.f5336f.f5156a.f5229g;
    }

    /* renamed from: h */
    public final int mo47052h() {
        return this.f5336f.f5156a.f5230h;
    }

    /* renamed from: a */
    public final C5053cu mo47033a(C5053cu cuVar) {
        if (cuVar == null) {
            return new C5053cu(this.f5336f.f5156a.f5231i, this.f5336f.f5156a.f5232j);
        }
        cuVar.f5475a = this.f5336f.f5156a.f5231i;
        cuVar.f5476b = this.f5336f.f5156a.f5232j;
        return cuVar;
    }

    /* renamed from: a */
    public final int mo47030a(int i) {
        return this.f5336f.f5156a.mo46995a(i);
    }

    /* renamed from: i */
    public final int mo47053i() {
        return this.f5347q;
    }

    /* renamed from: b */
    public final int mo47040b(int i) {
        return this.f5336f.f5156a.f5235m[i];
    }

    /* renamed from: c */
    public final int mo47043c(int i) {
        return this.f5336f.f5156a.f5236n[i];
    }

    /* renamed from: a */
    public final C5070dk mo47034a(C5069dj djVar, int[] iArr, C5039cg cgVar) {
        return new C5072dm(djVar, iArr, cgVar);
    }

    /* renamed from: j */
    public final int mo47054j() {
        return this.f5348r;
    }

    /* renamed from: k */
    public final int mo47055k() {
        return this.f5349s;
    }

    /* renamed from: l */
    public final boolean mo47056l() {
        return this.f5333b;
    }

    /* renamed from: a */
    private C5094eh m3364a(DataInputStream dataInputStream, int[] iArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        iArr[0] = readUnsignedByte;
        if (readUnsignedByte >= 128) {
            throw new C5014bs("Custom filters not supported");
        }
        switch (readUnsignedByte) {
            case 0:
                return new C5096ej();
            case 1:
                return new C5098el();
            default:
                throw new C5017bv("Specified wavelet filter not JPEG 2000 part I compliant");
        }
    }

    /* renamed from: a */
    public void mo47037a(DataInputStream dataInputStream, String str) throws IOException {
        if (dataInputStream.available() != 0) {
        }
    }

    /* renamed from: a */
    private void m3365a(DataInputStream dataInputStream) throws IOException {
        C5027i a = this.f5336f.mo46980a();
        this.f5336f.f5156a = a;
        a.f5223a = dataInputStream.readUnsignedShort();
        a.f5224b = dataInputStream.readUnsignedShort();
        if (a.f5224b > 2) {
            throw new Error("Codestream capabiities not JPEG 2000 - Part I compliant");
        }
        a.f5225c = dataInputStream.readInt();
        a.f5226d = dataInputStream.readInt();
        if (a.f5225c <= 0 || a.f5226d <= 0) {
            throw new IOException("JJ2000 does not support images whose width and/or height not in the range: 1 -- (2^31)-1");
        }
        a.f5227e = dataInputStream.readInt();
        a.f5228f = dataInputStream.readInt();
        if (a.f5227e < 0 || a.f5228f < 0) {
            throw new IOException("JJ2000 does not support images offset not in the range: 0 -- (2^31)-1");
        }
        a.f5229g = dataInputStream.readInt();
        a.f5230h = dataInputStream.readInt();
        if (a.f5229g <= 0 || a.f5230h <= 0) {
            throw new IOException("JJ2000 does not support tiles whose width and/or height are not in  the range: 1 -- (2^31)-1");
        }
        a.f5231i = dataInputStream.readInt();
        a.f5232j = dataInputStream.readInt();
        if (a.f5231i < 0 || a.f5232j < 0) {
            throw new IOException("JJ2000 does not support tiles whose offset is not in  the range: 0 -- (2^31)-1");
        }
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        a.f5233k = readUnsignedShort;
        this.f5347q = readUnsignedShort;
        if (this.f5347q < 1 || this.f5347q > 16384) {
            throw new IllegalArgumentException("Number of component out of range 1--16384: " + this.f5347q);
        }
        a.f5234l = new int[this.f5347q];
        a.f5235m = new int[this.f5347q];
        a.f5236n = new int[this.f5347q];
        for (int i = 0; i < this.f5347q; i++) {
            a.f5234l[i] = dataInputStream.readUnsignedByte();
            a.f5235m[i] = dataInputStream.readUnsignedByte();
            a.f5236n[i] = dataInputStream.readUnsignedByte();
        }
        mo47037a(dataInputStream, "SIZ marker");
        this.f5338h = a.mo46997c();
        this.f5350t = new C5039cg(this.f5338h, this.f5347q);
    }

    /* renamed from: b */
    private void m3369b(DataInputStream dataInputStream) throws IOException {
        C5022d i = this.f5336f.mo46988i();
        this.f5336f.f5164i = i;
        i.f5193a = dataInputStream.readUnsignedShort();
        i.f5194b = new int[this.f5347q];
        i.f5195c = new int[this.f5347q];
        for (int i2 = 0; i2 < this.f5347q; i2++) {
            i.f5194b[i2] = dataInputStream.readUnsignedShort();
            i.f5195c[i2] = dataInputStream.readUnsignedShort();
        }
        mo47037a(dataInputStream, "CRG marker");
    }

    /* renamed from: a */
    private void m3367a(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        C5021c j = this.f5336f.mo46989j();
        j.f5189a = dataInputStream.readUnsignedShort();
        j.f5190b = dataInputStream.readUnsignedShort();
        switch (j.f5190b) {
            case 1:
                j.f5191c = new byte[(j.f5189a - 4)];
                for (int i3 = 0; i3 < j.f5189a - 4; i3++) {
                    j.f5191c[i3] = dataInputStream.readByte();
                }
                break;
            default:
                dataInputStream.skipBytes(j.f5189a - 4);
                break;
        }
        if (z) {
            this.f5336f.f5165j.put("main_" + i2, j);
        } else {
            this.f5336f.f5165j.put("t" + i + "_" + i2, j);
        }
        mo47037a(dataInputStream, "COM marker");
    }

    /* renamed from: b */
    private void m3370b(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        int intValue;
        int i3;
        int i4;
        int i5;
        int intValue2;
        int i6;
        int i7;
        int i8;
        float[][] fArr = null;
        C5025g f = this.f5336f.mo46985f();
        f.f5212a = dataInputStream.readUnsignedShort();
        f.f5213b = dataInputStream.readUnsignedByte();
        int b = f.mo46993b();
        int a = f.mo46992a();
        if (z) {
            this.f5336f.f5161f.put("main", f);
            switch (a) {
                case 0:
                    this.f5350t.f5403c.mo46973a("reversible");
                    break;
                case 1:
                    this.f5350t.f5403c.mo46973a("derived");
                    break;
                case 2:
                    this.f5350t.f5403c.mo46973a("expounded");
                    break;
                default:
                    throw new C5017bv("Unknown or unsupported quantization style in Sqcd field, QCD marker main header");
            }
        } else {
            this.f5336f.f5161f.put("t" + i, f);
            switch (a) {
                case 0:
                    this.f5350t.f5403c.mo46976b(i, (Object) "reversible");
                    break;
                case 1:
                    this.f5350t.f5403c.mo46976b(i, (Object) "derived");
                    break;
                case 2:
                    this.f5350t.f5403c.mo46976b(i, (Object) "expounded");
                    break;
                default:
                    throw new C5017bv("Unknown or unsupported quantization style in Sqcd field, QCD marker, tile header");
            }
        }
        C5073dn dnVar = new C5073dn();
        if (a == 0) {
            if (z) {
                intValue2 = ((Integer) this.f5350t.f5407g.mo46974b()).intValue();
            } else {
                intValue2 = ((Integer) this.f5350t.f5407g.mo46979d(i)).intValue();
            }
            int[][] iArr = new int[(intValue2 + 1)][];
            dnVar.f5523a = iArr;
            f.f5214c = (int[][]) Array.newInstance(Integer.TYPE, new int[]{intValue2 + 1, 4});
            for (int i9 = 0; i9 <= intValue2; i9++) {
                if (i9 == 0) {
                    i7 = 0;
                    i8 = 1;
                } else {
                    if (1 > intValue2 - i9) {
                        i6 = 1 - (intValue2 - i9);
                    } else {
                        i6 = 1;
                    }
                    i7 = 1 << ((i6 - 1) << 1);
                    i8 = 1 << (i6 << 1);
                }
                iArr[i9] = new int[i8];
                while (i7 < i8) {
                    int[] iArr2 = f.f5214c[i9];
                    int readUnsignedByte = dataInputStream.readUnsignedByte();
                    iArr2[i7] = readUnsignedByte;
                    iArr[i9][i7] = (readUnsignedByte >> 3) & 31;
                    i7++;
                }
            }
        } else {
            if (a == 1) {
                intValue = 0;
            } else if (z) {
                intValue = ((Integer) this.f5350t.f5407g.mo46974b()).intValue();
            } else {
                intValue = ((Integer) this.f5350t.f5407g.mo46979d(i)).intValue();
            }
            int[][] iArr3 = new int[(intValue + 1)][];
            dnVar.f5523a = iArr3;
            float[][] fArr2 = new float[(intValue + 1)][];
            dnVar.f5524b = fArr2;
            f.f5214c = (int[][]) Array.newInstance(Integer.TYPE, new int[]{intValue + 1, 4});
            for (int i10 = 0; i10 <= intValue; i10++) {
                if (i10 == 0) {
                    i4 = 0;
                    i5 = 1;
                } else {
                    if (1 > intValue - i10) {
                        i3 = 1 - (intValue - i10);
                    } else {
                        i3 = 1;
                    }
                    i4 = 1 << ((i3 - 1) << 1);
                    i5 = 1 << (i3 << 1);
                }
                iArr3[i10] = new int[i5];
                fArr2[i10] = new float[i5];
                while (i4 < i5) {
                    int[] iArr4 = f.f5214c[i10];
                    int readUnsignedShort = dataInputStream.readUnsignedShort();
                    iArr4[i4] = readUnsignedShort;
                    iArr3[i10][i4] = (readUnsignedShort >> 11) & 31;
                    fArr2[i10][i4] = (-1.0f - (((float) (readUnsignedShort & 2047)) / 2048.0f)) / ((float) (-1 << iArr3[i10][i4]));
                    i4++;
                }
            }
        }
        if (z) {
            this.f5350t.f5404d.mo46973a(dnVar);
            this.f5350t.f5405e.mo46973a(new Integer(b));
        } else {
            this.f5350t.f5404d.mo46976b(i, (Object) dnVar);
            this.f5350t.f5405e.mo46976b(i, (Object) new Integer(b));
        }
        mo47037a(dataInputStream, "QCD marker");
    }

    /* renamed from: c */
    private void m3372c(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        int i3;
        int intValue;
        int i4;
        int i5;
        int i6;
        int intValue2;
        int i7;
        int i8;
        int i9;
        float[][] fArr = null;
        C5024f g = this.f5336f.mo46986g();
        g.f5205a = dataInputStream.readUnsignedShort();
        if (this.f5347q < 257) {
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            g.f5206b = readUnsignedByte;
            i3 = readUnsignedByte;
        } else {
            int readUnsignedShort = dataInputStream.readUnsignedShort();
            g.f5206b = readUnsignedShort;
            i3 = readUnsignedShort;
        }
        if (i3 >= this.f5347q) {
            throw new C5017bv("Invalid component index in QCC marker");
        }
        g.f5207c = dataInputStream.readUnsignedByte();
        int b = g.mo46991b();
        int a = g.mo46990a();
        if (z) {
            this.f5336f.f5162g.put("main_c" + i3, g);
            switch (a) {
                case 0:
                    this.f5350t.f5403c.mo46972a(i3, (Object) "reversible");
                    break;
                case 1:
                    this.f5350t.f5403c.mo46972a(i3, (Object) "derived");
                    break;
                case 2:
                    this.f5350t.f5403c.mo46972a(i3, (Object) "expounded");
                    break;
                default:
                    throw new C5017bv("Unknown or unsupported quantization style in Sqcd field, QCD marker, main header");
            }
        } else {
            this.f5336f.f5162g.put("t" + i + "_c" + i3, g);
            switch (a) {
                case 0:
                    this.f5350t.f5403c.mo46971a(i, i3, "reversible");
                    break;
                case 1:
                    this.f5350t.f5403c.mo46971a(i, i3, "derived");
                    break;
                case 2:
                    this.f5350t.f5403c.mo46971a(i, i3, "expounded");
                    break;
                default:
                    throw new C5017bv("Unknown or unsupported quantization style in Sqcd field, QCD marker, main header");
            }
        }
        C5073dn dnVar = new C5073dn();
        if (a == 0) {
            if (z) {
                intValue2 = ((Integer) this.f5350t.f5407g.mo46977c(i3)).intValue();
            } else {
                intValue2 = ((Integer) this.f5350t.f5407g.mo46970a(i, i3)).intValue();
            }
            int[][] iArr = new int[(intValue2 + 1)][];
            dnVar.f5523a = iArr;
            g.f5208d = (int[][]) Array.newInstance(Integer.TYPE, new int[]{intValue2 + 1, 4});
            for (int i10 = 0; i10 <= intValue2; i10++) {
                if (i10 == 0) {
                    i8 = 0;
                    i9 = 1;
                } else {
                    if (1 > intValue2 - i10) {
                        i7 = 1 - (intValue2 - i10);
                    } else {
                        i7 = 1;
                    }
                    i8 = 1 << ((i7 - 1) << 1);
                    i9 = 1 << (i7 << 1);
                }
                iArr[i10] = new int[i9];
                while (i8 < i9) {
                    int[] iArr2 = g.f5208d[i10];
                    int readUnsignedByte2 = dataInputStream.readUnsignedByte();
                    iArr2[i8] = readUnsignedByte2;
                    iArr[i10][i8] = (readUnsignedByte2 >> 3) & 31;
                    i8++;
                }
            }
        } else {
            if (a == 1) {
                intValue = 0;
            } else if (z) {
                intValue = ((Integer) this.f5350t.f5407g.mo46977c(i3)).intValue();
            } else {
                intValue = ((Integer) this.f5350t.f5407g.mo46970a(i, i3)).intValue();
            }
            float[][] fArr2 = new float[(intValue + 1)][];
            dnVar.f5524b = fArr2;
            int[][] iArr3 = new int[(intValue + 1)][];
            dnVar.f5523a = iArr3;
            g.f5208d = (int[][]) Array.newInstance(Integer.TYPE, new int[]{intValue + 1, 4});
            for (int i11 = 0; i11 <= intValue; i11++) {
                if (i11 == 0) {
                    i5 = 0;
                    i6 = 1;
                } else {
                    if (1 > intValue - i11) {
                        i4 = 1 - (intValue - i11);
                    } else {
                        i4 = 1;
                    }
                    i5 = 1 << ((i4 - 1) << 1);
                    i6 = 1 << (i4 << 1);
                }
                iArr3[i11] = new int[i6];
                fArr2[i11] = new float[i6];
                while (i5 < i6) {
                    int[] iArr4 = g.f5208d[i11];
                    int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                    iArr4[i5] = readUnsignedShort2;
                    iArr3[i11][i5] = (readUnsignedShort2 >> 11) & 31;
                    fArr2[i11][i5] = (-1.0f - (((float) (readUnsignedShort2 & 2047)) / 2048.0f)) / ((float) (-1 << iArr3[i11][i5]));
                    i5++;
                }
            }
        }
        if (z) {
            this.f5350t.f5404d.mo46972a(i3, (Object) dnVar);
            this.f5350t.f5405e.mo46972a(i3, (Object) new Integer(b));
        } else {
            this.f5350t.f5404d.mo46971a(i, i3, dnVar);
            this.f5350t.f5405e.mo46971a(i, i3, new Integer(b));
        }
        mo47037a(dataInputStream, "QCC marker");
    }

    /* renamed from: d */
    private void m3373d(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        C5020b c = this.f5336f.mo46982c();
        c.f5177a = dataInputStream.readUnsignedShort();
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        c.f5178b = readUnsignedByte;
        if ((readUnsignedByte & 1) != 0) {
            this.f5333b = true;
            readUnsignedByte &= -2;
        } else {
            this.f5333b = false;
        }
        if (z) {
            this.f5336f.f5158c.put("main", c);
            if ((readUnsignedByte & 2) != 0) {
                this.f5350t.f5415o.mo46973a(new Boolean("true"));
                readUnsignedByte &= -3;
            } else {
                this.f5350t.f5415o.mo46973a(new Boolean(InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
            }
        } else {
            this.f5336f.f5158c.put("t" + i, c);
            if ((readUnsignedByte & 2) != 0) {
                this.f5350t.f5415o.mo46976b(i, (Object) new Boolean("true"));
                readUnsignedByte &= -3;
            } else {
                this.f5350t.f5415o.mo46976b(i, (Object) new Boolean(InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
            }
        }
        if (z) {
            if ((readUnsignedByte & 4) != 0) {
                this.f5350t.f5416p.mo46973a(new Boolean("true"));
                readUnsignedByte &= -5;
            } else {
                this.f5350t.f5416p.mo46973a(new Boolean(InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
            }
        } else if ((readUnsignedByte & 4) != 0) {
            this.f5350t.f5416p.mo46976b(i, (Object) new Boolean("true"));
            readUnsignedByte &= -5;
        } else {
            this.f5350t.f5416p.mo46976b(i, (Object) new Boolean(InternalLogger.EVENT_PARAM_EXTRAS_FALSE));
        }
        if ((readUnsignedByte & 8) != 0) {
            if (this.f5348r == -1 || this.f5348r != 0) {
                this.f5348r = 1;
                readUnsignedByte &= -9;
            } else {
                throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
            }
        } else if (this.f5348r == -1 || this.f5348r != 1) {
            this.f5348r = 0;
        } else {
            throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
        }
        if ((readUnsignedByte & 16) != 0) {
            if (this.f5349s == -1 || this.f5349s != 0) {
                this.f5349s = 1;
                int i3 = readUnsignedByte & -17;
            } else {
                throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
            }
        } else if (this.f5349s == -1 || this.f5349s != 1) {
            this.f5349s = 0;
        } else {
            throw new IllegalArgumentException("Code-block partition origin redefined in new COD marker segment. Not supported by JJ2000");
        }
        c.f5179c = dataInputStream.readUnsignedByte();
        c.f5180d = dataInputStream.readUnsignedShort();
        if (c.f5180d <= 0 || c.f5180d > 65535) {
            throw new C5017bv("Number of layers out of range: 1--65535");
        }
        c.f5181e = dataInputStream.readUnsignedByte();
        int readUnsignedByte2 = dataInputStream.readUnsignedByte();
        c.f5182f = readUnsignedByte2;
        if (readUnsignedByte2 > 32) {
            throw new C5017bv("Number of decomposition levels out of range: 0--32");
        }
        Integer[] numArr = new Integer[2];
        c.f5183g = dataInputStream.readUnsignedByte();
        numArr[0] = new Integer(1 << (c.f5183g + 2));
        if (numArr[0].intValue() < 4 || numArr[0].intValue() > 1024) {
            throw new C5017bv("Non-valid code-block width in SPcod field, COD marker");
        }
        c.f5184h = dataInputStream.readUnsignedByte();
        numArr[1] = new Integer(1 << (c.f5184h + 2));
        if (numArr[1].intValue() < 4 || numArr[1].intValue() > 1024) {
            throw new C5017bv("Non-valid code-block height in SPcod field, COD marker");
        } else if (numArr[0].intValue() * numArr[1].intValue() > 4096) {
            throw new C5017bv("Non-valid code-block area in SPcod field, COD marker");
        } else {
            if (z) {
                this.f5350t.f5417q.mo46973a((Object) numArr);
            } else {
                this.f5350t.f5417q.mo46976b(i, numArr);
            }
            int readUnsignedByte3 = dataInputStream.readUnsignedByte();
            c.f5185i = readUnsignedByte3;
            if ((readUnsignedByte3 & -64) != 0) {
                throw new C5017bv("Unknown \"code-block style\" in SPcod field, COD marker: 0x" + Integer.toHexString(readUnsignedByte3));
            }
            C5094eh[] ehVarArr = {m3364a(dataInputStream, c.f5186j)};
            C5094eh[][] ehVarArr2 = {ehVarArr, new C5094eh[]{ehVarArr[0]}};
            Vector[] vectorArr = {new Vector(), new Vector()};
            if (!this.f5333b) {
                vectorArr[0].addElement(new Integer(32768));
                vectorArr[1].addElement(new Integer(32768));
            } else {
                c.f5187k = new int[(readUnsignedByte2 + 1)];
                for (int i4 = readUnsignedByte2; i4 >= 0; i4--) {
                    int[] iArr = c.f5187k;
                    int i5 = readUnsignedByte2 - i4;
                    int readUnsignedByte4 = dataInputStream.readUnsignedByte();
                    iArr[i5] = readUnsignedByte4;
                    vectorArr[0].insertElementAt(new Integer(1 << (readUnsignedByte4 & 15)), 0);
                    vectorArr[1].insertElementAt(new Integer(1 << ((readUnsignedByte4 & 240) >> 4)), 0);
                }
            }
            if (z) {
                this.f5350t.f5414n.mo46973a(vectorArr);
            } else {
                this.f5350t.f5414n.mo46976b(i, (Object) vectorArr);
            }
            this.f5333b = true;
            mo47037a(dataInputStream, "COD marker");
            if (z) {
                this.f5350t.f5406f.mo46973a(ehVarArr2);
                this.f5350t.f5407g.mo46973a(new Integer(readUnsignedByte2));
                this.f5350t.f5410j.mo46973a(new Integer(readUnsignedByte3));
                this.f5350t.f5411k.mo46973a(new Integer(c.f5181e));
                this.f5350t.f5408h.mo46973a(new Integer(c.f5180d));
                this.f5350t.f5409i.mo46973a(new Integer(c.f5179c));
                return;
            }
            this.f5350t.f5406f.mo46976b(i, (Object) ehVarArr2);
            this.f5350t.f5407g.mo46976b(i, (Object) new Integer(readUnsignedByte2));
            this.f5350t.f5410j.mo46976b(i, (Object) new Integer(readUnsignedByte3));
            this.f5350t.f5411k.mo46976b(i, (Object) new Integer(c.f5181e));
            this.f5350t.f5408h.mo46976b(i, (Object) new Integer(c.f5180d));
            this.f5350t.f5409i.mo46976b(i, (Object) new Integer(c.f5179c));
        }
    }

    /* renamed from: e */
    private void m3374e(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        int readUnsignedShort;
        C5019a d = this.f5336f.mo46983d();
        d.f5167a = dataInputStream.readUnsignedShort();
        if (this.f5347q < 257) {
            readUnsignedShort = dataInputStream.readUnsignedByte();
            d.f5168b = readUnsignedShort;
        } else {
            readUnsignedShort = dataInputStream.readUnsignedShort();
            d.f5168b = readUnsignedShort;
        }
        if (readUnsignedShort >= this.f5347q) {
            throw new C5017bv("Invalid component index in QCC marker");
        }
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        d.f5169c = readUnsignedByte;
        if ((readUnsignedByte & 1) != 0) {
            this.f5333b = true;
            int i3 = readUnsignedByte & -2;
        } else {
            this.f5333b = false;
        }
        int readUnsignedByte2 = dataInputStream.readUnsignedByte();
        d.f5170d = readUnsignedByte2;
        Integer[] numArr = new Integer[2];
        d.f5171e = dataInputStream.readUnsignedByte();
        numArr[0] = new Integer(1 << (d.f5171e + 2));
        if (numArr[0].intValue() < 4 || numArr[0].intValue() > 1024) {
            throw new C5017bv("Non-valid code-block width in SPcod field, COC marker");
        }
        d.f5172f = dataInputStream.readUnsignedByte();
        numArr[1] = new Integer(1 << (d.f5172f + 2));
        if (numArr[1].intValue() < 4 || numArr[1].intValue() > 1024) {
            throw new C5017bv("Non-valid code-block height in SPcod field, COC marker");
        } else if (numArr[0].intValue() * numArr[1].intValue() > 4096) {
            throw new C5017bv("Non-valid code-block area in SPcod field, COC marker");
        } else {
            if (z) {
                this.f5350t.f5417q.mo46972a(readUnsignedShort, numArr);
            } else {
                this.f5350t.f5417q.mo46971a(i, readUnsignedShort, (Object) numArr);
            }
            int readUnsignedByte3 = dataInputStream.readUnsignedByte();
            d.f5173g = readUnsignedByte3;
            if ((readUnsignedByte3 & -64) != 0) {
                throw new C5017bv("Unknown \"code-block context\" in SPcoc field, COC marker: 0x" + Integer.toHexString(readUnsignedByte3));
            }
            C5094eh[] ehVarArr = {m3364a(dataInputStream, d.f5174h)};
            C5094eh[][] ehVarArr2 = {ehVarArr, new C5094eh[]{ehVarArr[0]}};
            Vector[] vectorArr = {new Vector(), new Vector()};
            if (!this.f5333b) {
                vectorArr[0].addElement(new Integer(32768));
                vectorArr[1].addElement(new Integer(32768));
            } else {
                d.f5175i = new int[(readUnsignedByte2 + 1)];
                for (int i4 = readUnsignedByte2; i4 >= 0; i4--) {
                    int[] iArr = d.f5175i;
                    int readUnsignedByte4 = dataInputStream.readUnsignedByte();
                    iArr[i4] = readUnsignedByte4;
                    vectorArr[0].insertElementAt(new Integer(1 << (readUnsignedByte4 & 15)), 0);
                    vectorArr[1].insertElementAt(new Integer(1 << ((readUnsignedByte4 & 240) >> 4)), 0);
                }
            }
            if (z) {
                this.f5350t.f5414n.mo46972a(readUnsignedShort, (Object) vectorArr);
            } else {
                this.f5350t.f5414n.mo46971a(i, readUnsignedShort, vectorArr);
            }
            this.f5333b = true;
            mo47037a(dataInputStream, "COD marker");
            if (z) {
                this.f5336f.f5159d.put("main_c" + readUnsignedShort, d);
                this.f5350t.f5406f.mo46972a(readUnsignedShort, (Object) ehVarArr2);
                this.f5350t.f5407g.mo46972a(readUnsignedShort, (Object) new Integer(readUnsignedByte2));
                this.f5350t.f5410j.mo46972a(readUnsignedShort, (Object) new Integer(readUnsignedByte3));
                return;
            }
            this.f5336f.f5159d.put("t" + i + "_c" + readUnsignedShort, d);
            this.f5350t.f5406f.mo46971a(i, readUnsignedShort, ehVarArr2);
            this.f5350t.f5407g.mo46971a(i, readUnsignedShort, new Integer(readUnsignedByte2));
            this.f5350t.f5410j.mo46971a(i, readUnsignedShort, new Integer(readUnsignedByte3));
        }
    }

    /* renamed from: f */
    private void m3375f(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        int i3;
        C5023e eVar;
        int[][] iArr;
        boolean z2 = this.f5347q >= 256;
        if (z || this.f5336f.f5163h.get("t" + i) == null) {
            eVar = this.f5336f.mo46987h();
            i3 = 0;
        } else {
            C5023e eVar2 = (C5023e) this.f5336f.f5163h.get("t" + i);
            eVar = eVar2;
            i3 = eVar2.f5198b.length;
        }
        eVar.f5197a = dataInputStream.readUnsignedShort();
        int i4 = (eVar.f5197a - 2) / ((z2 ? 4 : 2) + 5);
        int i5 = i3 + i4;
        if (i3 != 0) {
            iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i5, 6});
            int[] iArr2 = new int[i5];
            int[] iArr3 = new int[i5];
            int[] iArr4 = new int[i5];
            int[] iArr5 = new int[i5];
            int[] iArr6 = new int[i5];
            int[] iArr7 = new int[i5];
            int[][] iArr8 = (int[][]) this.f5350t.f5412l.mo46979d(i);
            for (int i6 = 0; i6 < i3; i6++) {
                iArr[i6] = iArr8[i6];
                iArr2[i6] = eVar.f5198b[i6];
                iArr3[i6] = eVar.f5199c[i6];
                iArr4[i6] = eVar.f5200d[i6];
                iArr5[i6] = eVar.f5201e[i6];
                iArr6[i6] = eVar.f5202f[i6];
                iArr7[i6] = eVar.f5203g[i6];
            }
            eVar.f5198b = iArr2;
            eVar.f5199c = iArr3;
            eVar.f5200d = iArr4;
            eVar.f5201e = iArr5;
            eVar.f5202f = iArr6;
            eVar.f5203g = iArr7;
        } else {
            iArr = (int[][]) Array.newInstance(Integer.TYPE, new int[]{i4, 6});
            eVar.f5198b = new int[i4];
            eVar.f5199c = new int[i4];
            eVar.f5200d = new int[i4];
            eVar.f5201e = new int[i4];
            eVar.f5202f = new int[i4];
            eVar.f5203g = new int[i4];
        }
        while (i3 < i5) {
            int[] iArr9 = iArr[i3];
            int[] iArr10 = eVar.f5198b;
            int readUnsignedByte = dataInputStream.readUnsignedByte();
            iArr10[i3] = readUnsignedByte;
            iArr9[0] = readUnsignedByte;
            if (z2) {
                int[] iArr11 = iArr[i3];
                int[] iArr12 = eVar.f5199c;
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                iArr12[i3] = readUnsignedShort;
                iArr11[1] = readUnsignedShort;
            } else {
                int[] iArr13 = iArr[i3];
                int[] iArr14 = eVar.f5199c;
                int readUnsignedByte2 = dataInputStream.readUnsignedByte();
                iArr14[i3] = readUnsignedByte2;
                iArr13[1] = readUnsignedByte2;
            }
            int[] iArr15 = iArr[i3];
            int[] iArr16 = eVar.f5200d;
            int readUnsignedShort2 = dataInputStream.readUnsignedShort();
            iArr16[i3] = readUnsignedShort2;
            iArr15[2] = readUnsignedShort2;
            if (iArr[i3][2] < 1) {
                throw new C5017bv("LYEpoc value must be greater than 1 in POC marker segment of tile " + i + ", tile-part " + i2);
            }
            int[] iArr17 = iArr[i3];
            int[] iArr18 = eVar.f5201e;
            int readUnsignedByte3 = dataInputStream.readUnsignedByte();
            iArr18[i3] = readUnsignedByte3;
            iArr17[3] = readUnsignedByte3;
            if (iArr[i3][3] <= iArr[i3][0]) {
                throw new C5017bv("REpoc value must be greater than RSpoc in POC marker segment of tile " + i + ", tile-part " + i2);
            }
            if (z2) {
                int[] iArr19 = iArr[i3];
                int[] iArr20 = eVar.f5202f;
                int readUnsignedShort3 = dataInputStream.readUnsignedShort();
                iArr20[i3] = readUnsignedShort3;
                iArr19[4] = readUnsignedShort3;
            } else {
                int[] iArr21 = eVar.f5202f;
                int readUnsignedByte4 = dataInputStream.readUnsignedByte();
                iArr21[i3] = readUnsignedByte4;
                if (readUnsignedByte4 == 0) {
                    iArr[i3][4] = 0;
                } else {
                    iArr[i3][4] = readUnsignedByte4;
                }
            }
            if (iArr[i3][4] <= iArr[i3][1]) {
                throw new C5017bv("CEpoc value must be greater than CSpoc in POC marker segment of tile " + i + ", tile-part " + i2);
            }
            int[] iArr22 = iArr[i3];
            int[] iArr23 = eVar.f5203g;
            int readUnsignedByte5 = dataInputStream.readUnsignedByte();
            iArr23[i3] = readUnsignedByte5;
            iArr22[5] = readUnsignedByte5;
            i3++;
        }
        mo47037a(dataInputStream, "POC marker");
        if (z) {
            this.f5336f.f5163h.put("main", eVar);
            this.f5350t.f5412l.mo46973a(iArr);
            return;
        }
        this.f5336f.f5163h.put("t" + i, eVar);
        this.f5350t.f5412l.mo46976b(i, (Object) iArr);
    }

    /* renamed from: g */
    private void m3376g(DataInputStream dataInputStream, boolean z, int i, int i2) throws IOException {
        int readUnsignedShort;
        C5026h e = this.f5336f.mo46984e();
        e.f5218a = dataInputStream.readUnsignedShort();
        if (this.f5347q < 257) {
            readUnsignedShort = dataInputStream.readUnsignedByte();
        } else {
            readUnsignedShort = dataInputStream.readUnsignedShort();
        }
        e.f5219b = readUnsignedShort;
        if (readUnsignedShort >= this.f5347q) {
            throw new C5017bv("Invalid component index in RGN marker" + readUnsignedShort);
        }
        e.f5220c = dataInputStream.readUnsignedByte();
        if (e.f5220c != 0) {
            throw new C5017bv("Unknown or unsupported Srgn parameter in ROI marker");
        }
        if (this.f5350t.f5402b == null) {
            this.f5350t.f5402b = new C5074do(this.f5338h, this.f5347q, 2);
        }
        e.f5221d = dataInputStream.readUnsignedByte();
        if (z) {
            this.f5336f.f5160e.put("main_c" + readUnsignedShort, e);
            this.f5350t.f5402b.mo46972a(readUnsignedShort, (Object) new Integer(e.f5221d));
        } else {
            this.f5336f.f5160e.put("t" + i + "_c" + readUnsignedShort, e);
            this.f5350t.f5402b.mo46971a(i, readUnsignedShort, new Integer(e.f5221d));
        }
        mo47037a(dataInputStream, "RGN marker");
    }

    /* renamed from: c */
    private void m3371c(DataInputStream dataInputStream) throws IOException {
        if (this.f5351u == null) {
            this.f5351u = new byte[this.f5344n][];
            this.f5335d = new Vector();
            this.f5350t.f5418r.mo46973a(new Boolean(true));
        }
        int readUnsignedShort = dataInputStream.readUnsignedShort() - 3;
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        this.f5351u[readUnsignedByte] = new byte[readUnsignedShort];
        dataInputStream.read(this.f5351u[readUnsignedByte], 0, readUnsignedShort);
        mo47037a(dataInputStream, "PPM marker");
    }

    /* renamed from: a */
    private void m3366a(DataInputStream dataInputStream, int i, int i2) throws IOException {
        if (this.f5352v == null) {
            this.f5352v = new byte[this.f5338h][][][];
        }
        if (this.f5352v[i] == null) {
            this.f5352v[i] = new byte[this.f5332a[i]][][];
        }
        if (this.f5352v[i][i2] == null) {
            this.f5352v[i][i2] = new byte[this.f5345o[i][i2]][];
        }
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        byte[] bArr = new byte[(readUnsignedShort - 3)];
        dataInputStream.read(bArr);
        this.f5352v[i][i2][readUnsignedByte] = bArr;
        mo47037a(dataInputStream, "PPT marker");
        this.f5350t.f5418r.mo46976b(i, (Object) new Boolean(true));
    }

    /* renamed from: a */
    private void m3368a(short s, C5065df dfVar) throws IOException {
        if (this.f5339i != 0 || s == -175) {
            String str = "";
            if (this.f5346p == null) {
                this.f5346p = new Hashtable();
            }
            switch (s) {
                case -175:
                    if ((this.f5339i & 1) == 0) {
                        this.f5339i |= 1;
                        str = "SIZ";
                        break;
                    } else {
                        throw new C5017bv("More than one SIZ marker segment found in main header");
                    }
                case -174:
                    if ((this.f5339i & 2) == 0) {
                        this.f5339i |= 2;
                        str = "COD";
                        break;
                    } else {
                        throw new C5017bv("More than one COD marker found in main header");
                    }
                case -173:
                    this.f5339i |= 4;
                    StringBuilder append = new StringBuilder().append("COC");
                    int i = this.f5340j;
                    this.f5340j = i + 1;
                    str = append.append(i).toString();
                    break;
                case -171:
                    if ((this.f5339i & 16) == 0) {
                        this.f5339i |= 16;
                        break;
                    } else {
                        throw new C5017bv("More than one TLM marker found in main header");
                    }
                case -169:
                    if ((this.f5339i & 32) == 0) {
                        this.f5339i |= 32;
                        str = "PLM";
                        break;
                    } else {
                        throw new C5017bv("More than one PLM marker found in main header");
                    }
                case -168:
                    throw new C5017bv("PLT found in main header");
                case -164:
                    if ((this.f5339i & 8) == 0) {
                        this.f5339i |= 8;
                        str = "QCD";
                        break;
                    } else {
                        throw new C5017bv("More than one QCD marker found in main header");
                    }
                case -163:
                    this.f5339i |= 256;
                    StringBuilder append2 = new StringBuilder().append("QCC");
                    int i2 = this.f5341k;
                    this.f5341k = i2 + 1;
                    str = append2.append(i2).toString();
                    break;
                case -162:
                    this.f5339i |= 512;
                    StringBuilder append3 = new StringBuilder().append("RGN");
                    int i3 = this.f5343m;
                    this.f5343m = i3 + 1;
                    str = append3.append(i3).toString();
                    break;
                case -161:
                    if ((this.f5339i & 1024) == 0) {
                        this.f5339i |= 1024;
                        str = "POC";
                        break;
                    } else {
                        throw new C5017bv("More than one POC marker segment found in main header");
                    }
                case -160:
                    this.f5339i |= 16384;
                    StringBuilder append4 = new StringBuilder().append("PPM");
                    int i4 = this.f5344n;
                    this.f5344n = i4 + 1;
                    str = append4.append(i4).toString();
                    break;
                case -159:
                    throw new C5017bv("PPT found in main header");
                case -157:
                    if ((this.f5339i & 65536) == 0) {
                        this.f5339i |= 65536;
                        str = "CRG";
                        break;
                    } else {
                        throw new C5017bv("More than one CRG marker found in main header");
                    }
                case -156:
                    this.f5339i |= 2048;
                    StringBuilder append5 = new StringBuilder().append("COM");
                    int i5 = this.f5342l;
                    this.f5342l = i5 + 1;
                    str = append5.append(i5).toString();
                    break;
                case -112:
                    if ((this.f5339i & 64) != 0) {
                        throw new C5017bv("More than one SOT marker found right after main or tile header");
                    }
                    this.f5339i |= 64;
                    return;
                case -109:
                    throw new C5017bv("SOD found in main header");
                case -39:
                    throw new C5017bv("EOC found in main header");
                default:
                    str = "UNKNOWN";
                    break;
            }
            if (s < -208 || s > -193) {
                int b = dfVar.mo47118b();
                byte[] bArr = new byte[b];
                bArr[0] = (byte) ((b >> 8) & 255);
                bArr[1] = (byte) (b & 255);
                dfVar.mo47122a(bArr, 2, b - 2);
                if (!str.equals("UNKNOWN")) {
                    this.f5346p.put(str, bArr);
                    return;
                }
                return;
            }
            return;
        }
        throw new C5017bv("First marker after SOC must be SIZ " + Integer.toHexString(s));
    }

    /* renamed from: a */
    public void mo47038a(short s, C5065df dfVar, int i, int i2) throws IOException {
        String str;
        String str2 = "";
        if (this.f5346p == null) {
            this.f5346p = new Hashtable();
        }
        switch (s) {
            case -175:
                throw new C5017bv("SIZ found in tile-part header");
            case -174:
                if ((this.f5339i & 2) == 0) {
                    this.f5339i |= 2;
                    str = "COD";
                    break;
                } else {
                    throw new C5017bv("More than one COD marker found in tile-part header");
                }
            case -173:
                this.f5339i |= 4;
                StringBuilder append = new StringBuilder().append("COC");
                int i3 = this.f5340j;
                this.f5340j = i3 + 1;
                str = append.append(i3).toString();
                break;
            case -171:
                throw new C5017bv("TLM found in tile-part header");
            case -169:
                throw new C5017bv("PLM found in tile-part header");
            case -168:
                if ((this.f5339i & 32) == 0) {
                    str = "UNKNOWN";
                    break;
                } else {
                    throw new C5017bv("PLT marker found eventhough PLM marker found in main header");
                }
            case -164:
                if ((this.f5339i & 8) == 0) {
                    this.f5339i |= 8;
                    str = "QCD";
                    break;
                } else {
                    throw new C5017bv("More than one QCD marker found in tile-part header");
                }
            case -163:
                this.f5339i |= 256;
                StringBuilder append2 = new StringBuilder().append("QCC");
                int i4 = this.f5341k;
                this.f5341k = i4 + 1;
                str = append2.append(i4).toString();
                break;
            case -162:
                this.f5339i |= 512;
                StringBuilder append3 = new StringBuilder().append("RGN");
                int i5 = this.f5343m;
                this.f5343m = i5 + 1;
                str = append3.append(i5).toString();
                break;
            case -161:
                if ((this.f5339i & 1024) == 0) {
                    this.f5339i |= 1024;
                    str = "POC";
                    break;
                } else {
                    throw new C5017bv("More than one POC marker segment found in tile-part header");
                }
            case -160:
                throw new C5017bv("PPM found in tile-part header");
            case -159:
                this.f5339i |= 32768;
                if (this.f5345o == null) {
                    this.f5345o = new int[this.f5338h][];
                }
                if (this.f5345o[i] == null) {
                    this.f5345o[i] = new int[this.f5332a[i]];
                }
                StringBuilder append4 = new StringBuilder().append("PPT");
                int[] iArr = this.f5345o[i];
                int i6 = iArr[i2];
                iArr[i2] = i6 + 1;
                str = append4.append(i6).toString();
                break;
            case -157:
                throw new C5017bv("CRG marker found in tile-part header");
            case -156:
                this.f5339i |= 2048;
                StringBuilder append5 = new StringBuilder().append("COM");
                int i7 = this.f5342l;
                this.f5342l = i7 + 1;
                str = append5.append(i7).toString();
                break;
            case -112:
                throw new C5017bv("Second SOT marker segment found in tile-part header");
            case -109:
                this.f5339i |= 8192;
                return;
            case -39:
                throw new C5017bv("EOC found in tile-part header");
            default:
                str = "UNKNOWN";
                break;
        }
        int b = dfVar.mo47118b();
        byte[] bArr = new byte[b];
        bArr[0] = (byte) ((b >> 8) & 255);
        bArr[1] = (byte) (b & 255);
        dfVar.mo47122a(bArr, 2, b - 2);
        if (!str.equals("UNKNOWN")) {
            this.f5346p.put(str, bArr);
        }
    }

    /* renamed from: q */
    private void m3378q() throws IOException {
        if ((this.f5339i & 1) != 0) {
            m3365a(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("SIZ"))));
        }
        if ((this.f5339i & 2048) != 0) {
            for (int i = 0; i < this.f5342l; i++) {
                m3367a(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COM" + i))), true, 0, i);
            }
        }
        if ((this.f5339i & 65536) != 0) {
            m3369b(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("CRG"))));
        }
        if ((this.f5339i & 2) != 0) {
            m3373d(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COD"))), true, 0, 0);
        }
        if ((this.f5339i & 4) != 0) {
            for (int i2 = 0; i2 < this.f5340j; i2++) {
                m3374e(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COC" + i2))), true, 0, 0);
            }
        }
        if ((this.f5339i & 512) != 0) {
            for (int i3 = 0; i3 < this.f5343m; i3++) {
                m3376g(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("RGN" + i3))), true, 0, 0);
            }
        }
        if ((this.f5339i & 8) != 0) {
            m3370b(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("QCD"))), true, 0, 0);
        }
        if ((this.f5339i & 256) != 0) {
            for (int i4 = 0; i4 < this.f5341k; i4++) {
                m3372c(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("QCC" + i4))), true, 0, 0);
            }
        }
        if ((this.f5339i & 1024) != 0) {
            m3375f(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("POC"))), true, 0, 0);
        }
        if ((this.f5339i & 16384) != 0) {
            for (int i5 = 0; i5 < this.f5344n; i5++) {
                m3371c(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("PPM" + i5))));
            }
        }
        this.f5346p = null;
    }

    /* renamed from: a */
    public void mo47036a(int i, int i2) throws IOException {
        if ((this.f5339i & 2) != 0) {
            m3373d(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COD"))), false, i, i2);
        }
        if ((this.f5339i & 4) != 0) {
            for (int i3 = 0; i3 < this.f5340j; i3++) {
                m3374e(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COC" + i3))), false, i, i2);
            }
        }
        if ((this.f5339i & 512) != 0) {
            for (int i4 = 0; i4 < this.f5343m; i4++) {
                m3376g(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("RGN" + i4))), false, i, i2);
            }
        }
        if ((this.f5339i & 8) != 0) {
            m3370b(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("QCD"))), false, i, i2);
        }
        if ((this.f5339i & 256) != 0) {
            for (int i5 = 0; i5 < this.f5341k; i5++) {
                m3372c(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("QCC" + i5))), false, i, i2);
            }
        }
        if ((this.f5339i & 1024) != 0) {
            m3375f(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("POC"))), false, i, i2);
        }
        if ((this.f5339i & 2048) != 0) {
            for (int i6 = 0; i6 < this.f5342l; i6++) {
                m3367a(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("COM" + i6))), false, i, i6);
            }
        }
        if ((this.f5339i & 32768) != 0) {
            for (int i7 = 0; i7 < this.f5345o[i][i2]; i7++) {
                m3366a(new DataInputStream(new ByteArrayInputStream((byte[]) this.f5346p.get("PPT" + i7))), i, i2);
            }
        }
        this.f5346p = null;
    }

    /* renamed from: m */
    public C5039cg mo47057m() {
        return this.f5350t;
    }

    public C5034cb(C5065df dfVar, C5079dt dtVar, C5018bw bwVar) throws IOException {
        this.f5336f = bwVar;
        dtVar.mo47133a('H', C5079dt.m3577a(f5331e));
        this.f5334c = dfVar.mo47123e();
        if (dfVar.mo47117a() != -177) {
            throw new C5017bv("SOC marker segment not  found at the beginning of the codestream.");
        }
        this.f5339i = 0;
        do {
            m3368a(dfVar.mo47117a(), dfVar);
        } while ((this.f5339i & 64) == 0);
        dfVar.mo47121a(dfVar.mo47123e() - 2);
        m3378q();
    }

    /* renamed from: a */
    public C5047co mo47031a(C5045cm cmVar, C5079dt dtVar) {
        dtVar.mo47133a('C', C5079dt.m3577a(C5047co.m3446a()));
        return new C5049cq(cmVar, this.f5350t, dtVar.mo47135b("Cer"), false, dtVar.mo47136c("m_quit"));
    }

    /* renamed from: a */
    public C5051cs mo47032a(C5051cs csVar, C5140y yVar) throws IOException, C4982ao, C5144z {
        return C4967aa.m3191b(csVar, yVar);
    }

    /* renamed from: b */
    public C5051cs mo47041b(C5051cs csVar, C5140y yVar) throws IOException, C5144z {
        return C5139x.m3843a(csVar, yVar);
    }

    /* renamed from: c */
    public C5051cs mo47044c(C5051cs csVar, C5140y yVar) throws IOException, C5144z {
        return C4970ac.m3200a(csVar, yVar);
    }

    /* renamed from: d */
    public C5051cs mo47047d(C5051cs csVar, C5140y yVar) throws IOException, C5144z {
        return C4971ad.m3214a(csVar, yVar);
    }

    /* renamed from: a */
    public C5075dp mo47035a(C5069dj djVar, C5079dt dtVar, C5039cg cgVar) {
        return C5075dp.m3558a(djVar, dtVar, cgVar);
    }

    /* renamed from: n */
    public void mo47058n() {
        this.f5339i &= 16416;
        this.f5340j = 0;
        this.f5341k = 0;
        this.f5342l = 0;
        this.f5343m = 0;
    }

    /* renamed from: o */
    public static String[][] m3377o() {
        return f5331e;
    }

    /* renamed from: d */
    public ByteArrayInputStream mo47046d(int i) throws IOException {
        if (this.f5353w == null) {
            this.f5353w = new ByteArrayOutputStream[this.f5338h];
            for (int i2 = this.f5338h - 1; i2 >= 0; i2--) {
                this.f5353w[i2] = new ByteArrayOutputStream();
            }
            if (this.f5344n != 0) {
                int size = this.f5335d.size();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                for (int i3 = 0; i3 < this.f5344n; i3++) {
                    byteArrayOutputStream.write(this.f5351u[i3]);
                }
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                for (int i4 = 0; i4 < size; i4++) {
                    int intValue = ((Integer) this.f5335d.elementAt(i4)).intValue();
                    byte[] bArr = new byte[((byteArrayInputStream.read() << 24) | (byteArrayInputStream.read() << 16) | (byteArrayInputStream.read() << 8) | byteArrayInputStream.read())];
                    byteArrayInputStream.read(bArr);
                    this.f5353w[intValue].write(bArr);
                }
            } else {
                for (int i5 = this.f5338h - 1; i5 >= 0; i5--) {
                    for (int i6 = 0; i6 < this.f5332a[i5]; i6++) {
                        for (int i7 = 0; i7 < this.f5345o[i5][i6]; i7++) {
                            this.f5353w[i5].write(this.f5352v[i5][i6][i7]);
                        }
                    }
                }
            }
        }
        return new ByteArrayInputStream(this.f5353w[i].toByteArray());
    }

    /* renamed from: e */
    public void mo47049e(int i) {
        if (this.f5344n != 0) {
            this.f5335d.addElement(new Integer(i));
        }
    }

    /* renamed from: p */
    public int mo47059p() {
        return this.f5339i;
    }
}
