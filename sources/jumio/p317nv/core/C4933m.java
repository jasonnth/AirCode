package jumio.p317nv.core;

import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;

/* renamed from: jumio.nv.core.m */
/* compiled from: SimpleCountryDoctypeDatabase */
public class C4933m implements Serializable, C4932l {

    /* renamed from: a */
    private HashMap<Country, List<DocumentType>> f4819a = new LinkedHashMap();

    /* renamed from: a */
    public void mo46876a(Country country, DocumentType... documentTypeArr) {
        this.f4819a.put(country, Arrays.asList(documentTypeArr));
    }

    /* renamed from: a */
    public List<DocumentType> mo46874a(Country country) {
        return (List) this.f4819a.get(mo46873a(country.getIsoCode()));
    }

    /* renamed from: a */
    public Country mo46873a(String str) {
        for (Country country : this.f4819a.keySet()) {
            if (country.getIsoCode().equals(str)) {
                return country;
            }
        }
        return null;
    }

    /* renamed from: a */
    public List<Country> mo46875a(NVDocumentType... nVDocumentTypeArr) {
        LinkedList linkedList = new LinkedList();
        for (Entry entry : this.f4819a.entrySet()) {
            for (DocumentType documentType : (List) entry.getValue()) {
                int length = nVDocumentTypeArr.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (!documentType.getId().equals(nVDocumentTypeArr[i])) {
                        i++;
                    } else if (!linkedList.contains(entry.getKey())) {
                        linkedList.add(entry.getKey());
                    }
                }
            }
        }
        return linkedList;
    }

    /* renamed from: b */
    public Country mo46878b(String str) {
        return mo46873a(new Locale("", str).getISO3Country());
    }

    /* renamed from: b */
    public boolean mo46880b(Country country) {
        return this.f4819a.containsKey(mo46873a(country.getIsoCode()));
    }

    /* renamed from: a */
    public boolean mo46877a() {
        return this.f4819a.isEmpty();
    }

    /* renamed from: b */
    public List<Country> mo46879b() {
        return new ArrayList(this.f4819a.keySet());
    }
}
