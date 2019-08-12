package jumio.p317nv.nfc;

import android.graphics.Bitmap;
import java.io.IOException;

/* renamed from: jumio.nv.nfc.dc */
/* compiled from: ImgWriterBitmap */
public abstract class C5062dc {

    /* renamed from: a */
    protected C5051cs f5503a;

    /* renamed from: b */
    protected int f5504b;

    /* renamed from: c */
    protected int f5505c;

    /* renamed from: a */
    public abstract Bitmap mo47113a() throws IOException;

    /* renamed from: b */
    public abstract void mo47114b() throws IOException;

    /* renamed from: c */
    public void mo47115c() throws IOException {
        C5053cu a = this.f5503a.mo47106a(null);
        for (int i = 0; i < a.f5476b; i++) {
            for (int i2 = 0; i2 < a.f5475a; i2++) {
                this.f5503a.mo47108c(i2, i);
                mo47114b();
            }
        }
    }
}
