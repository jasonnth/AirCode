package jumio.p317nv.nfc;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Color;
import java.io.IOException;

/* renamed from: jumio.nv.nfc.dd */
/* compiled from: ImgWriterBitmapPPM */
public class C5063dd extends C5062dc {

    /* renamed from: d */
    private int[] f5506d = new int[3];

    /* renamed from: e */
    private int[] f5507e;

    /* renamed from: f */
    private int[] f5508f = new int[3];

    /* renamed from: g */
    private int[] f5509g = new int[3];

    /* renamed from: h */
    private C5056cx f5510h = new C5056cx();

    /* renamed from: i */
    private byte[] f5511i;

    public C5063dd(C5051cs csVar, int i, int i2, int i3) throws IOException {
        if (i < 0 || i >= csVar.mo46939b() || i2 < 0 || i2 >= csVar.mo46939b() || i3 < 0 || i3 >= csVar.mo46939b() || csVar.mo46940b(i) > 8 || csVar.mo46940b(i2) > 8 || csVar.mo46940b(i3) > 8) {
            throw new IllegalArgumentException("Invalid component indexes");
        }
        this.f5504b = csVar.mo46943d(i);
        this.f5505c = csVar.mo46942c(i);
        if (this.f5504b == csVar.mo46943d(i2) && this.f5504b == csVar.mo46943d(i3) && this.f5505c == csVar.mo46942c(i2) && this.f5505c == csVar.mo46942c(i3)) {
            this.f5504b = csVar.mo47107c();
            this.f5505c = csVar.mo47109d();
            this.f5503a = csVar;
            this.f5508f[0] = i;
            this.f5508f[1] = i2;
            this.f5508f[2] = i3;
            this.f5509g[0] = csVar.mo46934a(i);
            this.f5509g[1] = csVar.mo46934a(i2);
            this.f5509g[2] = csVar.mo46934a(i3);
            this.f5506d[0] = 1 << (csVar.mo46940b(i) - 1);
            this.f5506d[1] = 1 << (csVar.mo46940b(i2) - 1);
            this.f5506d[2] = 1 << (csVar.mo46940b(i3) - 1);
            this.f5507e = new int[(this.f5504b * this.f5505c)];
            return;
        }
        throw new IllegalArgumentException("All components must have the same dimensions and no subsampling");
    }

    /* renamed from: a */
    public Bitmap mo47113a() {
        try {
            return Bitmap.createBitmap(this.f5507e, this.f5504b, this.f5505c, Config.ARGB_8888);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public void mo47116a(int i, int i2, int i3, int i4) throws IOException {
        int g = this.f5503a.mo46946g(this.f5508f[0]) - ((int) Math.ceil(((double) this.f5503a.mo47111f()) / ((double) this.f5503a.mo46944e(this.f5508f[0]))));
        int h = this.f5503a.mo46947h(this.f5508f[0]) - ((int) Math.ceil(((double) this.f5503a.mo47112g()) / ((double) this.f5503a.mo46945f(this.f5508f[0]))));
        if (this.f5510h.f5485h != null && this.f5510h.f5485h.length < i3) {
            this.f5510h.f5485h = null;
        }
        if (this.f5511i == null || this.f5511i.length < i3 * 3) {
            this.f5511i = new byte[(i3 * 3)];
        }
        for (int i5 = 0; i5 < i4; i5++) {
            int i6 = 0;
            while (true) {
                int i7 = i6;
                if (i7 >= 3) {
                    break;
                }
                int b = (1 << this.f5503a.mo46940b(this.f5508f[i7])) - 1;
                int i8 = this.f5506d[i7];
                this.f5510h.f5477a = i;
                this.f5510h.f5478b = i2 + i5;
                this.f5510h.f5479c = i3;
                this.f5510h.f5480d = 1;
                do {
                    this.f5510h = (C5056cx) this.f5503a.mo46936b(this.f5510h, this.f5508f[i7]);
                } while (this.f5510h.f5483g);
                int i9 = this.f5509g[i7];
                if (i9 == 0) {
                    int i10 = (this.f5510h.f5481e + i3) - 1;
                    for (int i11 = (((i3 * 3) - 1) + i7) - 2; i11 >= 0; i11 -= 3) {
                        int i12 = this.f5510h.f5485h[i10] + i8;
                        byte[] bArr = this.f5511i;
                        if (i12 < 0) {
                            i12 = 0;
                        } else if (i12 > b) {
                            i12 = b;
                        }
                        bArr[i11] = (byte) i12;
                        i10--;
                    }
                } else {
                    int i13 = (this.f5510h.f5481e + i3) - 1;
                    for (int i14 = (((i3 * 3) - 1) + i7) - 2; i14 >= 0; i14 -= 3) {
                        int i15 = (this.f5510h.f5485h[i13] >>> i9) + i8;
                        byte[] bArr2 = this.f5511i;
                        if (i15 < 0) {
                            i15 = 0;
                        } else if (i15 > b) {
                            i15 = b;
                        }
                        bArr2[i14] = (byte) i15;
                        i13--;
                    }
                }
                i6 = i7 + 1;
            }
            int i16 = (this.f5504b * (i2 + h + i5)) + i + g;
            int i17 = 0;
            while (i17 < i3 * 3) {
                int i18 = i16 + 1;
                this.f5507e[i16] = Color.rgb(this.f5511i[i17 + 0] & 255, this.f5511i[i17 + 1] & 255, this.f5511i[i17 + 2] & 255);
                i17 += 3;
                i16 = i18;
            }
        }
    }

    /* renamed from: b */
    public void mo47114b() throws IOException {
        int i;
        int e = this.f5503a.mo47110e();
        int b = this.f5503a.mo46941b(e, 0);
        int a = this.f5503a.mo46938a(e, 0);
        for (int i2 = 0; i2 < a; i2 += 64) {
            if (a - i2 < 64) {
                i = a - i2;
            } else {
                i = 64;
            }
            mo47116a(0, i2, b, i);
        }
    }
}
