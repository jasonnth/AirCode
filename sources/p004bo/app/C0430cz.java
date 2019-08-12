package p004bo.app;

import android.net.Uri;
import java.util.Iterator;
import java.util.Map;

/* renamed from: bo.app.cz */
public abstract class C0430cz implements C0425cv {

    /* renamed from: a */
    public final Uri f315a;

    /* renamed from: b */
    private Map<String, String> f316b;

    protected C0430cz(Uri uri, Map<String, String> map) {
        this.f316b = map;
        this.f315a = Uri.parse(uri + mo6926g());
    }

    /* renamed from: b */
    public Uri mo6916b() {
        return this.f315a;
    }

    /* renamed from: g */
    public String mo6926g() {
        if (this.f316b == null || this.f316b.size() == 0) {
            return "";
        }
        String str = "?";
        Iterator it = this.f316b.keySet().iterator();
        while (true) {
            String str2 = str;
            if (!it.hasNext()) {
                return str2.substring(0, str2.length() - 1);
            }
            String str3 = (String) it.next();
            str = str2 + str3 + "=" + ((String) this.f316b.get(str3)) + "&";
        }
    }
}
