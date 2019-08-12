package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/* renamed from: com.threatmetrix.TrustDefender.aa */
class C4760aa implements C4784aq {

    /* renamed from: a */
    private static final String f4159a = C4834w.m2892a(C4760aa.class);

    /* renamed from: b */
    private THMStatusCode f4160b = THMStatusCode.THM_NotYet;

    /* renamed from: c */
    private C4794e f4161c;

    /* renamed from: d */
    private Map<String, String> f4162d = new HashMap();

    /* renamed from: e */
    private Request f4163e = null;

    /* renamed from: f */
    private C4838z f4164f = null;

    /* renamed from: g */
    private OkHttpClient f4165g = null;

    /* renamed from: h */
    private Response f4166h = null;

    /* renamed from: i */
    private Call f4167i = null;

    C4760aa(C4838z client, C4794e state) {
        this.f4161c = state;
        this.f4164f = client;
        this.f4165g = client.mo46105a();
    }

    /* renamed from: a */
    public final void mo45937a(Map<String, String> headers) {
        if (headers != null) {
            this.f4162d.putAll(headers);
        }
    }

    /* renamed from: a */
    public final long mo45934a(String url) {
        m2573b(url, null);
        if (this.f4166h == null || this.f4160b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f4166h.code();
    }

    /* renamed from: a */
    public final long mo45935a(String url, C4823n params) {
        m2573b(url, params);
        if (this.f4166h == null || this.f4160b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f4166h.code();
    }

    /* renamed from: b */
    private void m2573b(String url, C4823n params) {
        Builder builder = new Builder().url(url);
        this.f4162d.put("User-Agent", this.f4164f.mo46106b());
        for (Entry<String, String> entry : this.f4162d.entrySet()) {
            if (entry.getValue() == null) {
                C4834w.m2901c(f4159a, "null value for " + ((String) entry.getKey()));
            } else {
                builder.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (params != null) {
            FormBody.Builder bodyBuilder = new FormBody.Builder();
            for (String key : params.keySet()) {
                String value = (String) params.get(key);
                if (C4770ai.m2633f(value)) {
                    Integer length = params.mo46084a(key);
                    if (length != null && value.length() > length.intValue()) {
                        value = value.substring(0, length.intValue());
                    }
                    if (length == null && params.mo46080a() != 0 && value.length() > params.mo46080a()) {
                        value = value.substring(0, params.mo46080a());
                    }
                    bodyBuilder.add(key, value);
                }
            }
            builder.post(bodyBuilder.build());
        }
        synchronized (this) {
            this.f4163e = builder.build();
        }
        try {
            this.f4167i = this.f4165g.newCall(this.f4163e);
            this.f4166h = this.f4167i.execute();
            this.f4160b = THMStatusCode.THM_OK;
        } catch (IOException e) {
            if (e.getCause() instanceof CertificateException) {
                this.f4160b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof SSLPeerUnverifiedException) {
                this.f4160b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof UnknownHostException) {
                this.f4160b = THMStatusCode.THM_HostNotFound_Error;
            } else if (e instanceof SocketTimeoutException) {
                this.f4160b = THMStatusCode.THM_NetworkTimeout_Error;
            } else if (this.f4160b == THMStatusCode.THM_NotYet) {
                this.f4160b = THMStatusCode.THM_Connection_Error;
            }
            if (this.f4161c == null || !this.f4161c.mo45949a()) {
                C4834w.m2895a(f4159a, "Failed to retrieve URI", (Throwable) e);
                return;
            }
            C4834w.m2901c(f4159a, "Connection interrupted due to cancel!");
            mo45939c();
        } catch (RuntimeException e2) {
            C4834w.m2895a(f4159a, "Caught runtime exception:", (Throwable) e2);
            this.f4160b = THMStatusCode.THM_Connection_Error;
        }
    }

    /* renamed from: a */
    public final String mo45936a() {
        if (this.f4163e != null) {
            return this.f4163e.url().toString();
        }
        return null;
    }

    /* renamed from: b */
    public final String mo45938b() {
        if (this.f4163e != null) {
            return this.f4163e.url().host();
        }
        return null;
    }

    /* renamed from: c */
    public final void mo45939c() {
        if (this.f4167i != null) {
            this.f4167i.cancel();
        }
    }

    /* renamed from: d */
    public final InputStream mo45940d() throws IOException {
        if (this.f4166h == null) {
            return null;
        }
        return this.f4166h.body().byteStream();
    }

    /* renamed from: e */
    public final void mo45941e() {
        if (this.f4166h != null) {
            this.f4166h.body().close();
        }
    }

    /* renamed from: f */
    public final THMStatusCode mo45942f() {
        return this.f4160b;
    }

    /* renamed from: g */
    public final int mo45943g() {
        if (this.f4166h != null) {
            return this.f4166h.code();
        }
        return -1;
    }
}
