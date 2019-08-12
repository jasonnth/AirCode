package p004bo.app;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: bo.app.hu */
public class C0607hu {

    /* renamed from: a */
    private boolean f817a;

    /* renamed from: b */
    private StringBuilder f818b;

    /* renamed from: c */
    private String f819c;

    /* renamed from: d */
    private Object f820d;

    /* renamed from: e */
    private Object f821e;

    /* renamed from: f */
    private final List<C0604hr> f822f;

    /* renamed from: g */
    private final List<C0604hr> f823g;

    /* renamed from: h */
    private final List<C0604hr> f824h;

    public C0607hu() {
        this(true, null);
    }

    private C0607hu(boolean z, String str) {
        this.f822f = new ArrayList();
        this.f823g = new ArrayList();
        this.f824h = new ArrayList();
        this.f817a = z;
        if (str == null) {
            str = "";
        }
        this.f818b = new StringBuilder(str);
    }

    /* renamed from: a */
    public boolean mo7284a() {
        return this.f817a;
    }

    /* renamed from: b */
    public boolean mo7286b() {
        return !this.f817a;
    }

    /* renamed from: a */
    public void mo7283a(String str) {
        this.f817a = false;
        if (this.f818b.length() == 0) {
            this.f818b.append(str);
        } else {
            this.f818b.append(" ; ").append(str);
        }
    }

    /* renamed from: a */
    public C0607hu mo7282a(String str, Object obj, Object obj2) {
        this.f822f.add(new C0604hr(str, obj, obj2));
        this.f819c = str;
        this.f820d = obj;
        this.f821e = obj2;
        mo7283a(m1085b(str, obj, obj2));
        return this;
    }

    /* renamed from: b */
    private String m1085b(String str, Object obj, Object obj2) {
        return str + "\nExpected: " + m1084a(obj) + "\n     got: " + m1084a(obj2) + "\n";
    }

    /* renamed from: a */
    public C0607hu mo7281a(String str, Object obj) {
        this.f823g.add(new C0604hr(str, obj, null));
        mo7283a(m1086c(str, obj));
        return this;
    }

    /* renamed from: c */
    private String m1086c(String str, Object obj) {
        return str + "\nExpected: " + m1084a(obj) + "\n     but none found\n";
    }

    /* renamed from: b */
    public C0607hu mo7285b(String str, Object obj) {
        this.f824h.add(new C0604hr(str, null, obj));
        mo7283a(m1087d(str, obj));
        return this;
    }

    /* renamed from: d */
    private String m1087d(String str, Object obj) {
        return str + "\nUnexpected: " + m1084a(obj) + "\n";
    }

    /* renamed from: a */
    private static String m1084a(Object obj) {
        if (obj instanceof JSONArray) {
            return "a JSON array";
        }
        if (obj instanceof JSONObject) {
            return "a JSON object";
        }
        return obj.toString();
    }

    public String toString() {
        return this.f818b.toString();
    }
}
