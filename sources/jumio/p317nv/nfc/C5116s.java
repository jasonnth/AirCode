package jumio.p317nv.nfc;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import net.p318sf.scuba.smartcards.CardServiceException;
import org.jmrtd.PassportService;

/* renamed from: jumio.nv.nfc.s */
/* compiled from: MockPassportReader */
public class C5116s extends C5105i {

    /* renamed from: b */
    private C5115r f5651b;

    public C5116s(C5115r rVar, PassportService passportService, String str, Date date, Date date2, String str2) throws CardServiceException, GeneralSecurityException {
        super(passportService, str, date, date2, str2);
        this.f5651b = rVar;
    }

    /* renamed from: a */
    public C5112o mo47170a() throws CardServiceException {
        if (this.f5651b.containsKey(C5113p.INIT)) {
            return this.f5651b.get(C5113p.INIT);
        }
        return super.mo47170a();
    }

    /* renamed from: b */
    public C5112o mo47175b() {
        return this.f5651b.containsKey(C5113p.BAC_CHECK) ? this.f5651b.get(C5113p.BAC_CHECK) : super.mo47175b();
    }

    /* renamed from: c */
    public List<C5112o> mo47176c() throws IOException {
        if (!this.f5651b.containsKey(C5113p.PASSIVE_AUTH_HASH_CHECK)) {
            return super.mo47176c();
        }
        ArrayList arrayList = new ArrayList();
        C5112o a = this.f5651b.get(C5113p.PASSIVE_AUTH_HASH_CHECK);
        if (a.mo47210e() != null) {
            for (Entry entry : ((SortedMap) a.mo47210e()).entrySet()) {
                arrayList.add(new C5112o(C5113p.PASSIVE_AUTH_HASH_CHECK, (C5114q) entry.getValue(), entry.getKey()));
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public C5112o mo47171a(C5032c cVar, boolean z) {
        return this.f5651b.containsKey(C5113p.READ_LDS) ? this.f5651b.get(C5113p.READ_LDS) : super.mo47171a(cVar, z);
    }

    /* renamed from: a */
    public C5112o mo47173a(boolean z) throws CardServiceException {
        return this.f5651b.containsKey(C5113p.READ_LDS) ? this.f5651b.get(C5113p.READ_LDS) : super.mo47173a(z);
    }

    /* renamed from: d */
    public C5112o mo47177d() {
        return this.f5651b.containsKey(C5113p.PASSIVE_AUTH_DSC_CHECK) ? this.f5651b.get(C5113p.PASSIVE_AUTH_DSC_CHECK) : super.mo47177d();
    }

    /* renamed from: e */
    public C5112o mo47178e() {
        return this.f5651b.containsKey(C5113p.ACTIVE_AUTH_CHECK) ? this.f5651b.get(C5113p.ACTIVE_AUTH_CHECK) : super.mo47178e();
    }

    /* renamed from: f */
    public C5112o mo47179f() {
        return this.f5651b.containsKey(C5113p.FACE_IMAGE) ? this.f5651b.get(C5113p.FACE_IMAGE) : super.mo47179f();
    }

    /* renamed from: g */
    public C5112o mo47180g() {
        return this.f5651b.containsKey(C5113p.ADDITIONAL_DATA) ? this.f5651b.get(C5113p.ADDITIONAL_DATA) : super.mo47180g();
    }

    /* renamed from: h */
    public String mo47181h() {
        return null;
    }

    /* renamed from: a */
    public C5112o mo47172a(C5102h hVar) {
        return this.f5651b.containsKey(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK) ? this.f5651b.get(C5113p.PASSIVE_AUTH_ROOT_CERT_CHECK) : super.mo47172a(hVar);
    }
}
