package jumio.p317nv.barcode;

import android.graphics.Point;
import android.graphics.Rect;

/* renamed from: jumio.nv.barcode.a */
/* compiled from: RecognitionResult */
public class C4880a {

    /* renamed from: a */
    private Point[] f4691a;

    /* renamed from: b */
    private int f4692b;

    /* renamed from: c */
    private int f4693c;

    public C4880a(Point[] pointArr, int i, int i2) {
        this.f4691a = pointArr;
        mo46786b(i2);
        mo46785a(i);
    }

    /* renamed from: a */
    public void mo46785a(int i) {
        this.f4692b = i;
    }

    /* renamed from: b */
    public void mo46786b(int i) {
        this.f4693c = i;
    }

    /* renamed from: a */
    public Rect mo46784a() {
        int i;
        if (this.f4691a == null || this.f4691a.length < 4) {
            return new Rect(0, 0, this.f4692b, this.f4693c);
        }
        int i2 = this.f4691a[0].x < this.f4691a[1].x ? this.f4691a[0].x : this.f4691a[1].x;
        int i3 = this.f4691a[1].y < this.f4691a[2].y ? this.f4691a[1].y : this.f4691a[2].y;
        int i4 = this.f4691a[3].x > this.f4691a[2].x ? this.f4691a[3].x : this.f4691a[2].x;
        int i5 = this.f4691a[0].y > this.f4691a[3].y ? this.f4691a[0].y : this.f4691a[3].y;
        if (i5 < i3) {
            i = i5;
            i5 = i3;
        } else {
            i = i3;
        }
        if (i2 <= i4) {
            int i6 = i4;
            i4 = i2;
            i2 = i6;
        }
        return new Rect(i4, i, i2, i5);
    }
}
