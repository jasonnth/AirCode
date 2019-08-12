package com.threatmetrix.TrustDefender;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Request.Builder;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.SSLPeerUnverifiedException;

/* renamed from: com.threatmetrix.TrustDefender.ac */
class C4763ac implements C4784aq {

    /* renamed from: a */
    private static final String f4172a = C4834w.m2892a(C4763ac.class);

    /* renamed from: b */
    private THMStatusCode f4173b = THMStatusCode.THM_NotYet;

    /* renamed from: c */
    private C4794e f4174c;

    /* renamed from: d */
    private Map<String, String> f4175d = new HashMap();

    /* renamed from: e */
    private Request f4176e = null;

    /* renamed from: f */
    private C4761ab f4177f = null;

    /* renamed from: g */
    private OkHttpClient f4178g = null;

    /* renamed from: h */
    private Response f4179h = null;

    /* renamed from: i */
    private Call f4180i = null;

    C4763ac(C4761ab client, C4794e state) {
        this.f4174c = state;
        this.f4177f = client;
        this.f4178g = client.mo45944a();
    }

    /* renamed from: a */
    public final void mo45937a(Map<String, String> headers) {
        if (headers != null) {
            this.f4175d.putAll(headers);
        }
    }

    /* renamed from: a */
    public final long mo45934a(String url) {
        m2588b(url, null);
        if (this.f4179h == null || this.f4173b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f4179h.code();
    }

    /* renamed from: a */
    public final long mo45935a(String url, C4823n params) {
        m2588b(url, params);
        if (this.f4179h == null || this.f4173b != THMStatusCode.THM_OK) {
            return -1;
        }
        return (long) this.f4179h.code();
    }

    /* renamed from: b */
    private void m2588b(String url, C4823n params) {
        Builder builder = new Builder().url(url);
        this.f4175d.put("User-Agent", this.f4177f.mo45947b());
        for (Entry<String, String> entry : this.f4175d.entrySet()) {
            if (entry.getValue() == null) {
                C4834w.m2901c(f4172a, "null value for " + ((String) entry.getKey()));
            } else {
                builder.addHeader((String) entry.getKey(), (String) entry.getValue());
            }
        }
        if (params != null) {
            FormEncodingBuilder bodyBuilder = new FormEncodingBuilder();
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
            this.f4176e = builder.build();
        }
        try {
            this.f4180i = this.f4178g.newCall(this.f4176e);
            this.f4179h = this.f4180i.execute();
            this.f4173b = THMStatusCode.THM_OK;
        } catch (IOException e) {
            if (e.getCause() instanceof CertificateException) {
                this.f4173b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof SSLPeerUnverifiedException) {
                this.f4173b = THMStatusCode.THM_HostVerification_Error;
            } else if (e instanceof UnknownHostException) {
                this.f4173b = THMStatusCode.THM_HostNotFound_Error;
            } else if (e instanceof SocketTimeoutException) {
                this.f4173b = THMStatusCode.THM_NetworkTimeout_Error;
            } else if (this.f4173b == THMStatusCode.THM_NotYet) {
                this.f4173b = THMStatusCode.THM_Connection_Error;
            }
            if (this.f4174c == null || !this.f4174c.mo45949a()) {
                C4834w.m2895a(f4172a, "Failed to retrieve URI", (Throwable) e);
                return;
            }
            C4834w.m2901c(f4172a, "Connection interrupted due to cancel!");
            mo45939c();
        } catch (RuntimeException e2) {
            C4834w.m2895a(f4172a, "Caught runtime exception:", (Throwable) e2);
            this.f4173b = THMStatusCode.THM_Connection_Error;
        }
    }

    /* renamed from: a */
    public final String mo45936a() {
        if (this.f4176e != null) {
            return this.f4176e.url().toString();
        }
        return null;
    }

    /* renamed from: b */
    public final String mo45938b() {
        if (this.f4176e != null) {
            return this.f4176e.url().getHost();
        }
        return null;
    }

    /* renamed from: c */
    public final void mo45939c() {
        if (this.f4180i != null) {
            this.f4180i.cancel();
        }
    }

    /* renamed from: d */
    public final InputStream mo45940d() throws IOException {
        if (this.f4179h == null) {
            return null;
        }
        return this.f4179h.body().byteStream();
    }

    /* renamed from: e */
    public final void mo45941e() {
        if (this.f4179h != null) {
            try {
                this.f4179h.body().close();
            } catch (IOException e) {
                String str = f4172a;
            }
        }
    }

    /* renamed from: f */
    public final THMStatusCode mo45942f() {
        return this.f4173b;
    }

    /* renamed from: g */
    public final int mo45943g() {
        if (this.f4179h != null) {
            return this.f4179h.code();
        }
        return -1;
    }
}
