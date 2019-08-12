package p004bo.app;

import android.graphics.Bitmap;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;

/* renamed from: bo.app.ga */
public class C0535ga implements C0533fz {

    /* renamed from: a */
    private final C0533fz f568a;

    /* renamed from: b */
    private final Comparator<String> f569b;

    public C0535ga(C0533fz fzVar, Comparator<String> comparator) {
        this.f568a = fzVar;
        this.f569b = comparator;
    }

    /* renamed from: a */
    public boolean mo7103a(String str, Bitmap bitmap) {
        String str2;
        synchronized (this.f568a) {
            Iterator it = this.f568a.mo7102a().iterator();
            while (true) {
                if (!it.hasNext()) {
                    str2 = null;
                    break;
                }
                str2 = (String) it.next();
                if (this.f569b.compare(str, str2) == 0) {
                    break;
                }
            }
            if (str2 != null) {
                this.f568a.mo7104b(str2);
            }
        }
        return this.f568a.mo7103a(str, bitmap);
    }

    /* renamed from: a */
    public Bitmap mo7101a(String str) {
        return this.f568a.mo7101a(str);
    }

    /* renamed from: b */
    public Bitmap mo7104b(String str) {
        return this.f568a.mo7104b(str);
    }

    /* renamed from: a */
    public Collection<String> mo7102a() {
        return this.f568a.mo7102a();
    }
}
