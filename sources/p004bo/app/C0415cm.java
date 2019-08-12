package p004bo.app;

import android.net.Uri;
import com.appboy.models.ResponseError;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.cm */
public final class C0415cm extends C0430cz {
    public C0415cm(String str, String str2) {
        super(Uri.parse(str), m354a(str2));
    }

    /* renamed from: a */
    private static Map<String, String> m354a(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("DT", "GM");
        hashMap.put("DI", str);
        return hashMap;
    }

    /* renamed from: a */
    public C0632u mo6908a() {
        return C0632u.GET;
    }

    /* renamed from: a */
    public void mo6910a(C0343ac acVar, C0392bu buVar) {
    }

    /* renamed from: a */
    public void mo6909a(C0343ac acVar) {
    }

    /* renamed from: a */
    public void mo6911a(C0343ac acVar, ResponseError responseError) {
    }
}
