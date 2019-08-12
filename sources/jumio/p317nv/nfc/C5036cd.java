package jumio.p317nv.nfc;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/* renamed from: jumio.nv.nfc.cd */
/* compiled from: PktHeaderBitReader */
public class C5036cd {

    /* renamed from: a */
    C5065df f5384a;

    /* renamed from: b */
    ByteArrayInputStream f5385b;

    /* renamed from: c */
    boolean f5386c = true;

    /* renamed from: d */
    int f5387d;

    /* renamed from: e */
    int f5388e;

    /* renamed from: f */
    int f5389f;

    C5036cd(C5065df dfVar) {
        this.f5384a = dfVar;
    }

    C5036cd(ByteArrayInputStream byteArrayInputStream) {
        this.f5385b = byteArrayInputStream;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo47069a() throws IOException {
        if (this.f5388e == 0) {
            if (this.f5387d != 255) {
                if (this.f5386c) {
                    this.f5387d = this.f5385b.read();
                } else {
                    this.f5387d = this.f5384a.mo47125g();
                }
                this.f5388e = 8;
                if (this.f5387d == 255) {
                    if (this.f5386c) {
                        this.f5389f = this.f5385b.read();
                    } else {
                        this.f5389f = this.f5384a.mo47125g();
                    }
                }
            } else {
                this.f5387d = this.f5389f;
                this.f5388e = 7;
            }
        }
        int i = this.f5387d;
        int i2 = this.f5388e - 1;
        this.f5388e = i2;
        return (i >> i2) & 1;
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public final int mo47070a(int i) throws IOException {
        if (i <= this.f5388e) {
            int i2 = this.f5387d;
            int i3 = this.f5388e - i;
            this.f5388e = i3;
            return (i2 >> i3) & ((1 << i) - 1);
        }
        int i4 = 0;
        do {
            i -= this.f5388e;
            i4 = (i4 << this.f5388e) | mo47070a(this.f5388e);
            if (this.f5387d != 255) {
                if (this.f5386c) {
                    this.f5387d = this.f5385b.read();
                } else {
                    this.f5387d = this.f5384a.mo47125g();
                }
                this.f5388e = 8;
                if (this.f5387d == 255) {
                    if (this.f5386c) {
                        this.f5389f = this.f5385b.read();
                    } else {
                        this.f5389f = this.f5384a.mo47125g();
                    }
                }
            } else {
                this.f5387d = this.f5389f;
                this.f5388e = 7;
            }
        } while (i > this.f5388e);
        int i5 = i4 << i;
        int i6 = this.f5387d;
        int i7 = this.f5388e - i;
        this.f5388e = i7;
        return i5 | ((i6 >> i7) & ((1 << i) - 1));
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: b */
    public void mo47071b() {
        this.f5387d = 0;
        this.f5388e = 0;
    }
}
