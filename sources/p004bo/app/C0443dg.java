package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.util.Collection;
import java.util.Collections;

/* renamed from: bo.app.dg */
public class C0443dg implements C0446di {

    /* renamed from: a */
    private static final String f349a = AppboyLogger.getAppboyLogTag(C0443dg.class);

    /* renamed from: b */
    private final C0446di f350b;

    /* renamed from: c */
    private final C0343ac f351c;

    public C0443dg(C0446di diVar, C0343ac acVar) {
        this.f350b = diVar;
        this.f351c = acVar;
    }

    /* renamed from: a */
    public void mo6938a(C0391bt btVar) {
        try {
            this.f350b.mo6938a(btVar);
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to upsert active session in the storage.", e);
            mo6954a(this.f351c, (Throwable) e);
        }
    }

    /* renamed from: a */
    public C0391bt mo6937a() {
        try {
            return this.f350b.mo6937a();
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to get the active session from the storage.", e);
            mo6954a(this.f351c, (Throwable) e);
            return null;
        }
    }

    /* renamed from: b */
    public void mo6941b(C0391bt btVar) {
        try {
            this.f350b.mo6941b(btVar);
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to delete the sealed session from the storage.", e);
            mo6954a(this.f351c, (Throwable) e);
        }
    }

    /* renamed from: b */
    public Collection<C0391bt> mo6940b() {
        try {
            return this.f350b.mo6940b();
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to fetch all sealed sessions from the storage.", e);
            mo6954a(this.f351c, (Throwable) e);
            return Collections.EMPTY_LIST;
        }
    }

    /* renamed from: a */
    public void mo6939a(C0391bt btVar, C0386bo boVar) {
        try {
            this.f350b.mo6939a(btVar, boVar);
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to add single event to session due to unexpected exception.", e);
            mo6954a(this.f351c, (Throwable) e);
        }
    }

    /* access modifiers changed from: 0000 */
    /* renamed from: a */
    public void mo6954a(C0343ac acVar, Throwable th) {
        try {
            acVar.mo6736a(new C0356ap("A database exception has occurred. Please view the stack trace for more details.", th), C0356ap.class);
        } catch (Exception e) {
            AppboyLogger.m1736e(f349a, "Failed to log throwable.", e);
        }
    }
}
