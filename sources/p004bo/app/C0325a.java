package p004bo.app;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.appboy.configuration.AppboyConfigurationProvider;
import com.appboy.support.StringUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: bo.app.a */
public final class C0325a implements C0470e {

    /* renamed from: a */
    private final C0377bf f38a;

    /* renamed from: b */
    private final AppboyConfigurationProvider f39b;

    /* renamed from: c */
    private String f40c;

    /* renamed from: d */
    private String f41d;

    /* renamed from: e */
    private String f42e;

    public C0325a(AppboyConfigurationProvider appboyConfigurationProvider, C0377bf bfVar) {
        this.f39b = appboyConfigurationProvider;
        this.f38a = bfVar;
    }

    /* renamed from: a */
    public void mo6704a(String str) {
        this.f42e = str;
    }

    /* renamed from: a */
    public Map<String, String> mo6703a() {
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Encoding", "gzip, deflate");
        hashMap.put(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, "application/json");
        if (m5c() != null) {
            hashMap.put("X-Appboy-Api-Key", m5c());
        }
        if (!StringUtils.isNullOrBlank(this.f42e)) {
            hashMap.put("X-Appboy-User-Identifier", this.f42e);
        }
        hashMap.put("X-Appboy-Device-Identifier", m4b());
        return hashMap;
    }

    /* renamed from: b */
    private synchronized String m4b() {
        if (this.f40c == null) {
            this.f40c = this.f38a.mo6797e();
        }
        return this.f40c;
    }

    /* renamed from: c */
    private synchronized String m5c() {
        if (this.f41d == null) {
            this.f41d = this.f39b.getAppboyApiKey().toString();
        }
        return this.f41d;
    }
}
