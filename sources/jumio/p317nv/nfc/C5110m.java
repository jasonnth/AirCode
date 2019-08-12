package jumio.p317nv.nfc;

import android.util.SparseArray;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import org.jmrtd.lds.DG11File;

/* renamed from: jumio.nv.nfc.m */
/* compiled from: MrtdPersonalDetails */
public class C5110m implements Serializable {

    /* renamed from: a */
    public final String f5616a;

    /* renamed from: b */
    public final String f5617b;

    /* renamed from: c */
    public final List<String> f5618c;

    /* renamed from: d */
    public final List<String> f5619d;

    /* renamed from: e */
    public final List<String> f5620e;

    /* renamed from: f */
    public final String f5621f;

    /* renamed from: g */
    public final String f5622g;

    /* renamed from: h */
    public final List<String> f5623h;

    /* renamed from: i */
    public final String f5624i;

    /* renamed from: j */
    public final byte[] f5625j;

    /* renamed from: k */
    public final String f5626k;

    /* renamed from: l */
    public final String f5627l;

    /* renamed from: m */
    public Date f5628m;

    public C5110m(DG11File dG11File) {
        this.f5616a = dG11File.getCustodyInformation();
        this.f5628m = dG11File.getFullDateOfBirth();
        this.f5617b = dG11File.getNameOfHolder();
        List<String> otherNames = dG11File.getOtherNames();
        if (otherNames != null && otherNames.size() == 0) {
            otherNames = null;
        }
        this.f5618c = otherNames;
        this.f5619d = dG11File.getOtherValidTDNumbers();
        this.f5620e = dG11File.getPermanentAddress();
        this.f5621f = dG11File.getPersonalNumber();
        this.f5622g = dG11File.getPersonalSummary();
        this.f5623h = dG11File.getPlaceOfBirth();
        this.f5624i = dG11File.getProfession();
        this.f5625j = dG11File.getProofOfCitizenship();
        this.f5626k = dG11File.getTelephone();
        this.f5627l = dG11File.getTitle();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f5616a != null ? this.f5616a + "\n" : "").append(this.f5628m != null ? this.f5628m.toString() + "\n" : "").append(this.f5617b != null ? this.f5617b + "\n" : "").append(this.f5618c != null ? m3759a(this.f5618c) + "\n" : "").append(this.f5619d != null ? m3759a(this.f5619d) + "\n" : "").append(this.f5620e != null ? m3759a(this.f5620e) + "\n" : "").append(this.f5621f != null ? this.f5621f + "\n" : "").append(this.f5622g != null ? this.f5622g + "\n" : "").append(this.f5623h != null ? m3759a(this.f5623h) + "\n" : "").append(this.f5624i != null ? this.f5624i + "\n" : "").append(this.f5625j != null ? Arrays.toString(this.f5625j) + "\n" : "").append(this.f5626k != null ? this.f5626k + "\n" : "").append(this.f5627l != null ? this.f5627l + "\n" : "");
        return sb.toString();
    }

    /* renamed from: a */
    private String m3759a(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String str : list) {
            sb.append(str + ", ");
        }
        return sb.toString();
    }

    /* renamed from: a */
    public SparseArray<Object> mo47192a() {
        SparseArray<Object> sparseArray = new SparseArray<>();
        sparseArray.put(0, this.f5616a);
        sparseArray.put(1, this.f5628m);
        sparseArray.put(2, this.f5617b);
        sparseArray.put(3, this.f5618c);
        sparseArray.put(4, this.f5619d);
        sparseArray.put(5, this.f5620e);
        sparseArray.put(6, this.f5621f);
        sparseArray.put(7, this.f5622g);
        sparseArray.put(8, this.f5623h);
        sparseArray.put(9, this.f5624i);
        sparseArray.put(10, this.f5625j);
        sparseArray.put(11, this.f5626k);
        sparseArray.put(12, this.f5627l);
        return sparseArray;
    }
}
