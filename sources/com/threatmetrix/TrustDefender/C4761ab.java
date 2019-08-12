package com.threatmetrix.TrustDefender;

import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Protocol;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/* renamed from: com.threatmetrix.TrustDefender.ab */
class C4761ab implements C4777am {

    /* renamed from: c */
    private static final C4762a f4168c = new C4762a();

    /* renamed from: d */
    private static final String f4169d = C4834w.m2892a(C4761ab.class);

    /* renamed from: a */
    OkHttpClient f4170a;

    /* renamed from: b */
    String f4171b;

    /* renamed from: com.threatmetrix.TrustDefender.ab$a */
    static final class C4762a implements Interceptor {
        C4762a() {
        }
    }

    C4761ab() {
    }

    /* renamed from: a */
    public final void mo45946a(int timeout, String userAgent, boolean enablePostCompression, boolean enableCustomTLSFactory) {
        C4834w.m2901c(f4169d, "Creating OkHttpClient instance");
        this.f4170a = new OkHttpClient();
        if (C4800a.f4363c >= C4801b.f4373i && C4800a.f4363c < C4801b.f4378n && enableCustomTLSFactory) {
            this.f4170a.setSslSocketFactory(new TLSSocketFactory());
        }
        this.f4170a.setConnectTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f4170a.setWriteTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f4170a.setReadTimeout((long) timeout, TimeUnit.MILLISECONDS);
        this.f4170a.setFollowRedirects(true);
        this.f4170a.setFollowSslRedirects(true);
        this.f4170a.setConnectionPool(new ConnectionPool(3, 30000));
        this.f4171b = userAgent;
        C4765ae proxyWrapper = new C4765ae();
        if (proxyWrapper.mo45962a() != null) {
            this.f4170a.setProxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyWrapper.mo45962a(), proxyWrapper.mo45963b())));
        }
        this.f4170a.interceptors().add(f4168c);
        ArrayList<Protocol> plist = new ArrayList<>();
        plist.add(Protocol.HTTP_1_1);
        this.f4170a.setProtocols(plist);
        this.f4170a.setRetryOnConnectionFailure(true);
    }

    /* renamed from: a */
    public final OkHttpClient mo45944a() {
        return this.f4170a;
    }

    /* renamed from: b */
    public final String mo45947b() {
        return this.f4171b;
    }

    /* renamed from: a */
    public final C4784aq mo45945a(C4794e state) {
        return new C4763ac(this, state);
    }
}
