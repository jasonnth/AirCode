package p004bo.app;

import java.util.List;

/* renamed from: bo.app.en */
public final class C0485en extends C0486eo implements C0474ed {
    public C0485en(List<C0474ed> list) {
        super(list);
    }

    /* renamed from: a */
    public boolean mo7026a(C0495ex exVar) {
        boolean z = false;
        for (C0474ed a : this.f447a) {
            if (!a.mo7026a(exVar)) {
                return false;
            }
            z = true;
        }
        return z;
    }
}
