package jumio.p317nv.nfc;

import java.util.Vector;

/* renamed from: jumio.nv.nfc.cj */
/* compiled from: PrecinctSizeSpec */
public class C5042cj extends C5013br {

    /* renamed from: a */
    private C5012bq f5425a;

    public C5042cj(int i, int i2, byte b, C5012bq bqVar) {
        super(i, i2, b);
        this.f5425a = bqVar;
    }

    /* renamed from: a */
    public int mo47078a(int i, int i2, int i3) {
        int intValue;
        Vector[] vectorArr;
        boolean z = true;
        boolean z2 = i != -1;
        if (i2 == -1) {
            z = false;
        }
        if (z2 && z) {
            intValue = ((Integer) this.f5425a.mo46970a(i, i2)).intValue();
            vectorArr = (Vector[]) mo46970a(i, i2);
        } else if (z2 && !z) {
            intValue = ((Integer) this.f5425a.mo46979d(i)).intValue();
            vectorArr = (Vector[]) mo46979d(i);
        } else if (z2 || !z) {
            intValue = ((Integer) this.f5425a.mo46974b()).intValue();
            vectorArr = (Vector[]) mo46974b();
        } else {
            intValue = ((Integer) this.f5425a.mo46977c(i2)).intValue();
            vectorArr = (Vector[]) mo46977c(i2);
        }
        int i4 = intValue - i3;
        if (vectorArr[0].size() > i4) {
            return ((Integer) vectorArr[0].elementAt(i4)).intValue();
        }
        return ((Integer) vectorArr[0].elementAt(vectorArr[0].size() - 1)).intValue();
    }

    /* renamed from: b */
    public int mo47079b(int i, int i2, int i3) {
        int intValue;
        Vector[] vectorArr;
        boolean z = false;
        boolean z2 = i != -1;
        if (i2 != -1) {
            z = true;
        }
        if (z2 && z) {
            intValue = ((Integer) this.f5425a.mo46970a(i, i2)).intValue();
            vectorArr = (Vector[]) mo46970a(i, i2);
        } else if (z2 && !z) {
            intValue = ((Integer) this.f5425a.mo46979d(i)).intValue();
            vectorArr = (Vector[]) mo46979d(i);
        } else if (z2 || !z) {
            intValue = ((Integer) this.f5425a.mo46974b()).intValue();
            vectorArr = (Vector[]) mo46974b();
        } else {
            intValue = ((Integer) this.f5425a.mo46977c(i2)).intValue();
            vectorArr = (Vector[]) mo46977c(i2);
        }
        int i4 = intValue - i3;
        if (vectorArr[1].size() > i4) {
            return ((Integer) vectorArr[1].elementAt(i4)).intValue();
        }
        return ((Integer) vectorArr[1].elementAt(vectorArr[1].size() - 1)).intValue();
    }
}
