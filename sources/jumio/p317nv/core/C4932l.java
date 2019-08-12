package jumio.p317nv.core;

import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.DocumentType;
import com.jumio.p311nv.data.document.NVDocumentType;
import java.util.List;

/* renamed from: jumio.nv.core.l */
/* compiled from: CountryDoctypeDatabase */
public interface C4932l {
    /* renamed from: a */
    Country mo46873a(String str);

    /* renamed from: a */
    List<DocumentType> mo46874a(Country country);

    /* renamed from: a */
    List<Country> mo46875a(NVDocumentType... nVDocumentTypeArr);

    /* renamed from: a */
    void mo46876a(Country country, DocumentType... documentTypeArr);

    /* renamed from: a */
    boolean mo46877a();

    /* renamed from: b */
    Country mo46878b(String str);

    /* renamed from: b */
    List<Country> mo46879b();

    /* renamed from: b */
    boolean mo46880b(Country country);
}
