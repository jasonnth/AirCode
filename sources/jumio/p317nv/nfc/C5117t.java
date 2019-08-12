package jumio.p317nv.nfc;

import com.jumio.commons.PersistWith;
import com.jumio.p311nv.enums.EPassportStatus;
import com.jumio.p311nv.models.MrtdDataModel;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jmrtd.lds.MRZInfo;

@PersistWith("MrtdDataModel")
/* renamed from: jumio.nv.nfc.t */
/* compiled from: EPassportDataModel */
public class C5117t implements MrtdDataModel {

    /* renamed from: a */
    private List<C5112o> f5652a;

    /* renamed from: b */
    private C5111n f5653b;

    /* renamed from: c */
    private String f5654c;

    public C5117t(List<C5112o> list, C5111n nVar, String str) {
        this.f5652a = list;
        this.f5653b = nVar;
        this.f5654c = str;
    }

    public String getMrzString() {
        return this.f5654c;
    }

    public String getMrzFirstName() {
        return m3795c().getSecondaryIdentifier().replace("<", " ").trim();
    }

    /* renamed from: c */
    private MRZInfo m3795c() {
        return this.f5653b.mo47199c();
    }

    public String getMrzLastName() {
        return m3795c().getPrimaryIdentifier();
    }

    public String getMrzPersonalNumber() {
        return m3795c().getPersonalNumber();
    }

    public String getMrzIdNumber() {
        return m3795c().getDocumentNumber();
    }

    public String getMrzIssuingCountry() {
        return m3795c().getIssuingState();
    }

    public String getMrzOriginatingCountry() {
        return m3795c().getNationality();
    }

    public int getBacCheckResult() {
        return m3793a(C5113p.BAC_CHECK);
    }

    public int getDscCheckResult() {
        return m3793a(C5113p.PASSIVE_AUTH_DSC_CHECK);
    }

    public int getActiveAuthResult() {
        return m3793a(C5113p.ACTIVE_AUTH_CHECK);
    }

    public Map<String, Integer> getHtCheckResults() {
        C5112o b = m3794b(C5113p.PASSIVE_AUTH_HASH_CHECK);
        if (b == null) {
            return null;
        }
        TreeMap treeMap = new TreeMap();
        for (Entry entry : ((SortedMap) b.mo47210e()).entrySet()) {
            treeMap.put(String.valueOf(entry.getKey()), Integer.valueOf(((C5114q) entry.getValue()).ordinal()));
        }
        return treeMap;
    }

    public int[] getEncodedDataItems() {
        return mo47220b().mo47200d();
    }

    public int getRootCertCheck() {
        return m3793a(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK);
    }

    public EPassportStatus getVerificationStatus() {
        Iterator it;
        if (this.f5652a == null || this.f5652a.isEmpty()) {
            return EPassportStatus.NOT_AVAILABLE;
        }
        EPassportStatus ePassportStatus = EPassportStatus.VERIFIED;
        for (C5112o d : this.f5652a) {
            if (d.mo47209d()) {
                switch (((C5112o) it.next()).mo47202a()) {
                    case PASSIVE_AUTH_DSC_CHECK:
                    case PASSIVE_AUTH_HASH_CHECK:
                    case PASSIVE_AUTH_ROOT_CERT_CHECK:
                        return EPassportStatus.DENIED;
                }
            }
        }
        return ePassportStatus;
    }

    public String getPlaceOfBirth() {
        if (mo47220b() == null || mo47220b().mo47198b() == null || mo47220b().mo47198b().f5623h == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String append : mo47220b().mo47198b().f5623h) {
            sb.append(append);
        }
        return sb.toString();
    }

    /* renamed from: a */
    private int m3793a(C5113p pVar) {
        C5112o b = m3794b(pVar);
        if (b != null) {
            return b.mo47207b().ordinal();
        }
        return 2;
    }

    /* renamed from: b */
    private C5112o m3794b(C5113p pVar) {
        for (C5112o oVar : this.f5652a) {
            if (oVar.mo47202a() == pVar) {
                return oVar;
            }
        }
        return null;
    }

    /* renamed from: a */
    public List<C5112o> mo47219a() {
        return this.f5652a;
    }

    /* renamed from: b */
    public C5111n mo47220b() {
        return this.f5653b;
    }
}
