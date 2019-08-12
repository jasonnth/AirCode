package jumio.p317nv.ocr;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.jumio.clientlib.impl.livenessAndTM.TemplateInfo;
import com.jumio.commons.camera.CameraManager.PreviewProperties;
import com.jumio.commons.enums.ScreenAngle;
import com.jumio.commons.math.MathUtils;
import com.jumio.commons.math.TrigonometryUtils;
import com.jumio.commons.math.Vector2D;
import java.util.ArrayList;
import java.util.List;

/* renamed from: jumio.nv.ocr.a */
/* compiled from: TemplateInfoWrapper */
public class C5145a implements Parcelable {
    public static final Creator<C5145a> CREATOR = new Creator<C5145a>() {
        /* renamed from: a */
        public C5145a createFromParcel(Parcel parcel) {
            return new C5145a(parcel);
        }

        /* renamed from: a */
        public C5145a[] newArray(int i) {
            return new C5145a[i];
        }
    };

    /* renamed from: a */
    public static double f5734a = 0.6d;

    /* renamed from: b */
    public static double f5735b = 0.75d;

    /* renamed from: A */
    private float f5736A;

    /* renamed from: B */
    private float f5737B;

    /* renamed from: C */
    private int f5738C;

    /* renamed from: D */
    private int f5739D;

    /* renamed from: E */
    private PreviewProperties f5740E;

    /* renamed from: F */
    private boolean f5741F;

    /* renamed from: G */
    private int f5742G;

    /* renamed from: H */
    private float f5743H;

    /* renamed from: I */
    private float f5744I;

    /* renamed from: J */
    private float f5745J;

    /* renamed from: K */
    private float f5746K;

    /* renamed from: L */
    private ScreenAngle f5747L;

    /* renamed from: M */
    private boolean f5748M;

    /* renamed from: N */
    private boolean f5749N;

    /* renamed from: c */
    private final float f5750c;

    /* renamed from: d */
    private final float f5751d;

    /* renamed from: e */
    private final float f5752e;

    /* renamed from: f */
    private final float f5753f;

    /* renamed from: g */
    private final float f5754g;

    /* renamed from: h */
    private final float f5755h;

    /* renamed from: i */
    private final float f5756i;

    /* renamed from: j */
    private final float f5757j;

    /* renamed from: k */
    private final String f5758k;

    /* renamed from: l */
    private final String f5759l;

    /* renamed from: m */
    private final String f5760m;

    /* renamed from: n */
    private final String f5761n;

    /* renamed from: o */
    private final int f5762o;

    /* renamed from: p */
    private final int f5763p;

    /* renamed from: q */
    private final double f5764q;

    /* renamed from: r */
    private long f5765r;

    /* renamed from: s */
    private float f5766s;

    /* renamed from: t */
    private float f5767t;

    /* renamed from: u */
    private float f5768u;

    /* renamed from: v */
    private float f5769v;

    /* renamed from: w */
    private float f5770w;

    /* renamed from: x */
    private float f5771x;

    /* renamed from: y */
    private float f5772y;

    /* renamed from: z */
    private float f5773z;

    public C5145a(TemplateInfo templateInfo) {
        PointF pointF = new PointF(templateInfo.getPolygon().getTLx(), templateInfo.getPolygon().getTLy());
        PointF pointF2 = new PointF(templateInfo.getPolygon().getTRx(), templateInfo.getPolygon().getTRy());
        PointF pointF3 = new PointF(templateInfo.getPolygon().getBRx(), templateInfo.getPolygon().getBRy());
        PointF pointF4 = new PointF(templateInfo.getPolygon().getBLx(), templateInfo.getPolygon().getBLy());
        PointF[] inflatePolygon = MathUtils.inflatePolygon(pointF, pointF2, pointF3, pointF4, (int) Math.ceil((double) (Math.max(Math.max((float) new Vector2D(pointF2.x, pointF2.y, pointF.x, pointF.y).length(), (float) new Vector2D(pointF3.x, pointF3.y, pointF4.x, pointF4.y).length()), Math.max((float) new Vector2D(pointF2.x, pointF2.y, pointF3.x, pointF3.y).length(), (float) new Vector2D(pointF.x, pointF.y, pointF4.x, pointF4.y).length())) * 0.0f)));
        this.f5752e = inflatePolygon[0].x;
        this.f5753f = inflatePolygon[0].y;
        this.f5750c = inflatePolygon[1].x;
        this.f5751d = inflatePolygon[1].y;
        this.f5754g = inflatePolygon[2].x;
        this.f5755h = inflatePolygon[2].y;
        this.f5756i = inflatePolygon[3].x;
        this.f5757j = inflatePolygon[3].y;
        this.f5758k = templateInfo.getCountry();
        this.f5759l = templateInfo.getState();
        this.f5760m = templateInfo.getDocumentType();
        this.f5761n = templateInfo.getRegion();
        this.f5762o = templateInfo.getFrameIndex();
        this.f5763p = templateInfo.getMatchesCount();
        this.f5764q = templateInfo.getTransformError();
        this.f5738C = templateInfo.getTemplateWidth();
        this.f5739D = templateInfo.getTemplateHeight();
    }

    private C5145a(Parcel parcel) {
        boolean z;
        boolean z2;
        boolean z3 = true;
        this.f5758k = parcel.readString();
        this.f5760m = parcel.readString();
        this.f5761n = parcel.readString();
        this.f5759l = parcel.readString();
        this.f5762o = parcel.readInt();
        this.f5763p = parcel.readInt();
        float[] fArr = new float[8];
        parcel.readFloatArray(fArr);
        this.f5752e = fArr[0];
        this.f5753f = fArr[1];
        this.f5750c = fArr[2];
        this.f5751d = fArr[3];
        this.f5756i = fArr[4];
        this.f5757j = fArr[5];
        this.f5754g = fArr[6];
        this.f5755h = fArr[7];
        this.f5764q = (double) parcel.readFloat();
        this.f5736A = parcel.readFloat();
        this.f5737B = parcel.readFloat();
        this.f5745J = parcel.readFloat();
        this.f5746K = parcel.readFloat();
        this.f5743H = parcel.readFloat();
        this.f5744I = parcel.readFloat();
        this.f5747L = ScreenAngle.values()[parcel.readInt()];
        this.f5738C = parcel.readInt();
        this.f5739D = parcel.readInt();
        this.f5740E = (PreviewProperties) parcel.readSerializable();
        if (parcel.readByte() == 1) {
            z = true;
        } else {
            z = false;
        }
        this.f5748M = z;
        if (parcel.readByte() == 1) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.f5749N = z2;
        if (parcel.readByte() != 1) {
            z3 = false;
        }
        this.f5741F = z3;
        this.f5742G = parcel.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2;
        int i3;
        int i4 = 1;
        parcel.writeString(mo47277g());
        parcel.writeString(mo47276f());
        parcel.writeString(mo47279i());
        parcel.writeString(mo47278h());
        parcel.writeInt(mo47281k());
        parcel.writeInt(mo47282l());
        parcel.writeFloatArray(mo47275e());
        parcel.writeDouble(mo47283m());
        parcel.writeFloat(mo47284n());
        parcel.writeFloat(mo47285o());
        parcel.writeFloat(this.f5745J);
        parcel.writeFloat(this.f5746K);
        parcel.writeFloat(this.f5743H);
        parcel.writeFloat(this.f5744I);
        parcel.writeInt(this.f5747L.getValue());
        parcel.writeInt(this.f5738C);
        parcel.writeInt(this.f5739D);
        parcel.writeSerializable(this.f5740E);
        if (this.f5748M) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        parcel.writeByte((byte) i2);
        if (this.f5749N) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        parcel.writeByte((byte) i3);
        if (!this.f5741F) {
            i4 = 0;
        }
        parcel.writeByte((byte) i4);
        parcel.writeInt(this.f5742G);
    }

    /* renamed from: a */
    public boolean mo47269a() {
        boolean z;
        List b = mo47270b();
        boolean z2 = false;
        int size = b.size();
        int i = 0;
        while (i < size) {
            double d = (((double) (((PointF) b.get((i + 2) % size)).x - ((PointF) b.get((i + 1) % size)).x)) * ((double) (((PointF) b.get(i)).y - ((PointF) b.get((i + 1) % size)).y))) - (((double) (((PointF) b.get((i + 2) % size)).y - ((PointF) b.get((i + 1) % size)).y)) * ((double) (((PointF) b.get(i)).x - ((PointF) b.get((i + 1) % size)).x)));
            if (i != 0) {
                if (z2 != (d > 0.0d)) {
                    return false;
                }
                z = z2;
            } else if (d > 0.0d) {
                z = true;
            } else {
                z = false;
            }
            i++;
            z2 = z;
        }
        return true;
    }

    /* renamed from: b */
    public List<PointF> mo47270b() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(mo47289s());
        arrayList.add(mo47290t());
        arrayList.add(mo47292v());
        arrayList.add(mo47291u());
        return arrayList;
    }

    /* renamed from: c */
    public boolean mo47272c() {
        float scaleFactor = this.f5740E.getScaleFactor();
        if (scaleFactor < 1.0f) {
            float f = 1.0f / scaleFactor;
        }
        float f2 = (float) this.f5740E.surface.width;
        float f3 = (float) this.f5740E.surface.height;
        float n = (mo47284n() / 2.0f) - (f2 / 2.0f);
        if (this.f5768u < 0.0f || this.f5768u > f2 || this.f5772y < 0.0f || this.f5772y > f2 || this.f5766s < 0.0f || this.f5766s > f2 || this.f5770w < 0.0f || this.f5770w > f2 || this.f5769v < 0.0f || this.f5769v > f3 || this.f5773z < 0.0f || this.f5773z > f3 || this.f5767t < 0.0f || this.f5767t > f3 || this.f5771x < 0.0f || this.f5771x > f3) {
            return false;
        }
        return true;
    }

    /* renamed from: d */
    public boolean mo47273d() {
        float n = mo47284n() / mo47285o();
        float w = ((float) mo47293w()) / ((float) mo47295x());
        boolean z = (n > 1.0f && w > 1.0f) || (n < 1.0f && w < 1.0f);
        float f = (float) this.f5740E.surface.width;
        float f2 = (float) this.f5740E.surface.height;
        if (!z || this.f5748M) {
            if (((double) Math.max((int) Math.round(TrigonometryUtils.getDistanceBetweenTwoPoints(new Point(Math.round(this.f5766s), Math.round(this.f5767t)), new Point(Math.round(this.f5768u), Math.round(this.f5769v)))), (int) Math.round(TrigonometryUtils.getDistanceBetweenTwoPoints(new Point(Math.round(this.f5772y), Math.round(this.f5773z)), new Point(Math.round(this.f5770w), Math.round(this.f5771x)))))) < ((double) f) * f5735b) {
                return true;
            }
            return false;
        } else if (((double) mo47286p().height()) >= ((double) f2) * f5734a) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: e */
    public float[] mo47275e() {
        return new float[]{this.f5752e, this.f5753f, this.f5750c, this.f5751d, this.f5754g, this.f5755h, this.f5756i, this.f5757j};
    }

    /* renamed from: a */
    public void mo47266a(long j) {
        this.f5765r = j;
    }

    /* renamed from: f */
    public String mo47276f() {
        return this.f5760m;
    }

    /* renamed from: g */
    public String mo47277g() {
        return this.f5758k;
    }

    /* renamed from: h */
    public String mo47278h() {
        return this.f5759l;
    }

    /* renamed from: i */
    public String mo47279i() {
        return this.f5761n;
    }

    /* renamed from: j */
    public boolean mo47280j() {
        return this.f5749N;
    }

    /* renamed from: k */
    public int mo47281k() {
        return this.f5762o;
    }

    /* renamed from: l */
    public int mo47282l() {
        return this.f5763p;
    }

    /* renamed from: m */
    public double mo47283m() {
        return this.f5764q;
    }

    /* renamed from: n */
    public float mo47284n() {
        return this.f5736A;
    }

    /* renamed from: a */
    public void mo47264a(float f) {
        this.f5736A = f;
    }

    /* renamed from: o */
    public float mo47285o() {
        return this.f5737B;
    }

    /* renamed from: b */
    public void mo47271b(float f) {
        this.f5737B = f;
    }

    /* renamed from: p */
    public Rect mo47286p() {
        Rect rect = new Rect();
        rect.top = (int) MathUtils.min(this.f5769v, this.f5767t, this.f5773z, this.f5771x);
        rect.left = (int) MathUtils.min(this.f5768u, this.f5766s, this.f5772y, this.f5770w);
        rect.bottom = (int) MathUtils.max(this.f5769v, this.f5767t, this.f5773z, this.f5771x);
        rect.right = (int) MathUtils.max(this.f5768u, this.f5766s, this.f5770w, this.f5772y);
        return rect;
    }

    /* renamed from: q */
    public Rect mo47287q() {
        Rect rect = new Rect();
        rect.top = (int) MathUtils.min(this.f5753f, this.f5751d, this.f5757j, this.f5755h);
        rect.left = (int) MathUtils.min(this.f5752e, this.f5750c, this.f5756i, this.f5754g);
        rect.bottom = (int) MathUtils.max(this.f5753f, this.f5751d, this.f5757j, this.f5755h);
        rect.right = (int) MathUtils.max(this.f5752e, this.f5750c, this.f5754g, this.f5756i);
        return rect;
    }

    /* renamed from: r */
    public PointF mo47288r() {
        PointF pointF = new PointF();
        Vector2D vector2D = new Vector2D((double) this.f5768u, (double) this.f5769v);
        Vector2D add = vector2D.add(new Vector2D((double) this.f5770w, (double) this.f5771x).subtract(vector2D).scale(0.5d));
        pointF.x = (float) add.getX();
        pointF.y = (float) add.getY();
        return pointF;
    }

    /* renamed from: a */
    private PointF m3862a(PointF pointF, PreviewProperties previewProperties) {
        float f;
        float f2;
        boolean z = false;
        int i = previewProperties.orientation;
        boolean z2 = i == 180 || i == 270;
        if (i == 90 || i == 270) {
            z = true;
        }
        float f3 = this.f5743H;
        float f4 = this.f5744I;
        float f5 = this.f5745J;
        float f6 = this.f5746K;
        if (z) {
            if (z2) {
                f = (pointF.y * f3) - f5;
                f2 = ((float) previewProperties.surface.height) - ((pointF.x * f4) - f6);
            } else {
                f = ((float) previewProperties.surface.width) - ((f3 * pointF.y) - f5);
                f2 = (pointF.x * f4) - f6;
            }
        } else if (z2) {
            f = ((float) previewProperties.surface.width) - ((f3 * pointF.x) - f5);
            f2 = ((float) previewProperties.surface.height) - ((pointF.y * f4) - f6);
        } else {
            f = (pointF.x * f3) - f5;
            f2 = (pointF.y * f4) - f6;
        }
        pointF.x = f;
        pointF.y = f2;
        return pointF;
    }

    /* renamed from: a */
    public void mo47267a(PreviewProperties previewProperties, boolean z, boolean z2, boolean z3) {
        this.f5740E = previewProperties;
        this.f5748M = z;
        this.f5749N = z2;
        mo47271b((float) previewProperties.preview.height);
        mo47264a((float) previewProperties.preview.width);
        this.f5743H = ((float) previewProperties.scaledPreview.width) / ((float) previewProperties.preview.width);
        this.f5744I = ((float) previewProperties.scaledPreview.height) / ((float) previewProperties.preview.height);
        this.f5745J = ((float) (previewProperties.scaledPreview.width - previewProperties.surface.width)) / 2.0f;
        this.f5746K = ((float) (previewProperties.scaledPreview.height - previewProperties.surface.height)) / 2.0f;
        PointF a = m3862a(new PointF(this.f5750c, this.f5751d), previewProperties);
        this.f5766s = a.x;
        this.f5767t = a.y;
        PointF a2 = m3862a(new PointF(this.f5752e, this.f5753f), previewProperties);
        this.f5768u = a2.x;
        this.f5769v = a2.y;
        PointF a3 = m3862a(new PointF(this.f5754g, this.f5755h), previewProperties);
        this.f5770w = a3.x;
        this.f5771x = a3.y;
        PointF a4 = m3862a(new PointF(this.f5756i, this.f5757j), previewProperties);
        this.f5772y = a4.x;
        this.f5773z = a4.y;
    }

    /* renamed from: s */
    public PointF mo47289s() {
        return new PointF(this.f5768u, this.f5769v);
    }

    /* renamed from: t */
    public PointF mo47290t() {
        return new PointF(this.f5766s, this.f5767t);
    }

    /* renamed from: u */
    public PointF mo47291u() {
        return new PointF(this.f5772y, this.f5773z);
    }

    /* renamed from: v */
    public PointF mo47292v() {
        return new PointF(this.f5770w, this.f5771x);
    }

    /* renamed from: w */
    public int mo47293w() {
        return this.f5738C;
    }

    /* renamed from: x */
    public int mo47295x() {
        return this.f5739D;
    }

    /* renamed from: y */
    public float mo47296y() {
        return (float) this.f5740E.surface.width;
    }

    /* renamed from: z */
    public float mo47297z() {
        return (float) this.f5740E.surface.height;
    }

    /* renamed from: a */
    public void mo47268a(boolean z) {
        this.f5741F = z;
    }

    /* renamed from: a */
    public void mo47265a(int i) {
        this.f5742G = i;
    }
}
