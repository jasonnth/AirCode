package jumio.p317nv.nfc;

import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import org.jmrtd.lds.MRZInfo;

/* renamed from: jumio.nv.nfc.n */
/* compiled from: PassportDataDetails */
public class C5111n implements Serializable {

    /* renamed from: a */
    private MRZInfo f5629a;

    /* renamed from: b */
    private C5109l f5630b;

    /* renamed from: c */
    private C5110m f5631c;

    /* renamed from: a */
    public void mo47195a(C5109l lVar) {
        this.f5630b = lVar;
    }

    /* renamed from: a */
    public void mo47196a(C5110m mVar) {
        this.f5631c = mVar;
    }

    /* renamed from: a */
    public void mo47197a(MRZInfo mRZInfo) {
        this.f5629a = mRZInfo;
    }

    public String toString() {
        return "Additional document details: \n" + (this.f5631c != null ? this.f5631c.toString() : "") + (this.f5630b != null ? this.f5630b.toString() : "");
    }

    /* renamed from: a */
    public C5109l mo47194a() {
        return this.f5630b;
    }

    /* renamed from: b */
    public C5110m mo47198b() {
        return this.f5631c;
    }

    /* renamed from: c */
    public MRZInfo mo47199c() {
        return this.f5629a;
    }

    /* renamed from: d */
    public int[] mo47200d() {
        if (mo47198b() == null && mo47194a() == null) {
            return new int[0];
        }
        ArrayList arrayList = new ArrayList();
        if (mo47198b() != null) {
            SparseArray a = mo47198b().mo47192a();
            for (int i = 0; i < a.size(); i++) {
                if (a.valueAt(i) != null) {
                    arrayList.add(Integer.valueOf(a.keyAt(i)));
                }
            }
        }
        if (mo47194a() != null) {
            SparseArray a2 = mo47194a().mo47190a();
            for (int i2 = 0; i2 < a2.size(); i2++) {
                if (a2.valueAt(i2) != null) {
                    arrayList.add(Integer.valueOf(a2.keyAt(i2)));
                }
            }
        }
        int[] iArr = new int[arrayList.size()];
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            iArr[i3] = ((Integer) arrayList.get(i3)).intValue();
        }
        return iArr;
    }
}
