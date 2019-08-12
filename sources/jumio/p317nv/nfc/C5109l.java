package jumio.p317nv.nfc;

import android.util.SparseArray;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.jmrtd.lds.DG12File;

/* renamed from: jumio.nv.nfc.l */
/* compiled from: MrtdDocumentDetails */
public class C5109l implements Serializable {

    /* renamed from: a */
    public final String f5608a;

    /* renamed from: b */
    public final byte[] f5609b;

    /* renamed from: c */
    public final byte[] f5610c;

    /* renamed from: d */
    public final String f5611d;

    /* renamed from: e */
    public final List<String> f5612e;

    /* renamed from: f */
    public final String f5613f;

    /* renamed from: g */
    public Date f5614g;

    /* renamed from: h */
    public Date f5615h;

    public C5109l(DG12File dG12File) {
        this.f5614g = dG12File.getDateAndTimeOfPersonalization();
        this.f5615h = dG12File.getDateOfIssue();
        this.f5608a = dG12File.getEndorsementsAndObservations();
        this.f5609b = dG12File.getImageOfFront();
        this.f5610c = dG12File.getImageOfRear();
        this.f5611d = dG12File.getIssuingAuthority();
        this.f5612e = dG12File.getNamesOfOtherPersons();
        this.f5613f = dG12File.getPersonalizationSystemSerialNumber();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5614g != null ? this.f5614g.toString() + "\n" : "").append(this.f5615h != null ? this.f5615h.toString() + "\n" : "").append(this.f5608a != null ? this.f5608a + "\n" : "").append(this.f5609b != null ? "front image length: " + this.f5609b.length + "\n" : "").append(this.f5610c != null ? "back image length: " + this.f5610c.length + "\n" : "").append(this.f5611d != null ? this.f5611d + "\n" : "").append(this.f5612e != null ? m3757a(this.f5612e) + "\n" : "").append(this.f5613f != null ? this.f5613f + "\n" : "");
        return sb.toString();
    }

    /* renamed from: a */
    private String m3757a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str + ", ");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public SparseArray<Object> mo47190a() {
        SparseArray<Object> sparseArray = new SparseArray<>();
        sparseArray.put(13, this.f5614g);
        sparseArray.put(14, this.f5615h);
        sparseArray.put(15, this.f5608a);
        sparseArray.put(16, this.f5609b);
        sparseArray.put(17, this.f5610c);
        sparseArray.put(18, this.f5611d);
        sparseArray.put(19, this.f5612e);
        sparseArray.put(20, this.f5613f);
        return sparseArray;
    }
}
