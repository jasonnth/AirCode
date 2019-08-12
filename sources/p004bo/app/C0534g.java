package p004bo.app;

import com.appboy.support.AppboyLogger;
import java.net.URI;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: bo.app.g */
final class C0534g implements C0431d {

    /* renamed from: a */
    private static final String f566a = AppboyLogger.getAppboyLogTag(C0534g.class);

    /* renamed from: b */
    private final C0431d f567b;

    public C0534g(C0431d dVar) {
        this.f567b = dVar;
    }

    /* renamed from: a */
    public JSONObject mo6883a(URI uri, Map<String, String> map) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            return this.f567b.mo6883a(uri, map);
        } finally {
            long currentTimeMillis2 = System.currentTimeMillis();
            AppboyLogger.m1733d(f566a, String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis2 - currentTimeMillis), C0632u.GET.toString(), uri.toString()}));
        }
    }

    /* renamed from: a */
    public JSONObject mo6884a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            return this.f567b.mo6884a(uri, map, jSONObject);
        } finally {
            long currentTimeMillis2 = System.currentTimeMillis();
            AppboyLogger.m1733d(f566a, String.format("Request Executed in [%dms] [%s:%s]", new Object[]{Long.valueOf(currentTimeMillis2 - currentTimeMillis), C0632u.POST.toString(), uri.toString()}));
        }
    }
}
