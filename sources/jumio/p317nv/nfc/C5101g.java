package jumio.p317nv.nfc;

import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import java.security.GeneralSecurityException;
import java.util.Date;
import net.p318sf.scuba.smartcards.CardService;
import net.p318sf.scuba.smartcards.CardServiceException;
import org.jmrtd.PassportService;

/* renamed from: jumio.nv.nfc.g */
/* compiled from: PassportFactory */
public class C5101g {

    /* renamed from: a */
    private static C5115r f5573a;

    /* renamed from: b */
    private Tag f5574b;

    /* renamed from: c */
    private PassportService f5575c;

    /* renamed from: d */
    private IsoDep f5576d;

    public C5101g() {
    }

    public C5101g(Tag tag) {
        this.f5574b = tag;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public PassportService mo47165a(Tag tag) throws CardServiceException {
        if (this.f5575c == null) {
            this.f5576d = IsoDep.get(tag);
            this.f5575c = new PassportService(CardService.getInstance(this.f5576d));
        }
        return this.f5575c;
    }

    /* renamed from: a */
    public C5108k mo47164a(String str, Date date, Date date2, String str2) throws CardServiceException, GeneralSecurityException {
        if (this.f5574b != null) {
            this.f5575c = mo47165a(this.f5574b);
        }
        if (f5573a != null) {
            return new C5116s(f5573a, this.f5575c, str, date, date2, str2);
        }
        if (this.f5574b == null) {
            throw new IllegalStateException("the factory must be constructed with a Tag object!");
        }
        C5105i iVar = new C5105i(this.f5575c, str, date, date2, str2);
        iVar.mo47174a(this.f5576d);
        return iVar;
    }
}
