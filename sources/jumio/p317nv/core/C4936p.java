package jumio.p317nv.core;

import com.jumio.core.data.document.ScanSide;
import com.jumio.p311nv.data.country.Country;
import com.jumio.p311nv.data.document.NVDocumentType;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/* renamed from: jumio.nv.core.p */
/* compiled from: TemplateMatcherStorage */
public interface C4936p extends Serializable {
    /* renamed from: a */
    void mo46881a(String str, byte[] bArr) throws IOException;

    /* renamed from: a */
    boolean mo46882a(Country country, NVDocumentType nVDocumentType, ScanSide scanSide);

    /* renamed from: a */
    boolean mo46883a(String str);

    /* renamed from: b */
    List<String> mo46884b(Country country, NVDocumentType nVDocumentType, ScanSide scanSide);
}
