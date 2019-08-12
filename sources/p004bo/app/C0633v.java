package p004bo.app;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* renamed from: bo.app.v */
public enum C0633v {
    UNKNOWN("unknown"),
    NONE("none"),
    TWO_G("2g"),
    THREE_G("3g"),
    FOUR_G("4g"),
    WIFI("wifi");
    

    /* renamed from: g */
    private static final Map<String, C0633v> f918g = null;

    /* renamed from: h */
    private final String f920h;

    static {
        f918g = new HashMap();
        Iterator it = EnumSet.allOf(C0633v.class).iterator();
        while (it.hasNext()) {
            C0633v vVar = (C0633v) it.next();
            f918g.put(vVar.mo7331a(), vVar);
        }
    }

    private C0633v(String str) {
        this.f920h = str;
    }

    /* renamed from: a */
    public String mo7331a() {
        return this.f920h;
    }
}
