package com.threatmetrix.TrustDefender;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import okio.Sink;

/* renamed from: com.threatmetrix.TrustDefender.z */
class C4838z implements C4777am {

    /* renamed from: c */
    private static final C4839a f4680c = new C4839a();

    /* renamed from: d */
    private static final String f4681d = C4834w.m2892a(C4838z.class);

    /* renamed from: a */
    OkHttpClient f4682a;

    /* renamed from: b */
    String f4683b;

    /* renamed from: com.threatmetrix.TrustDefender.z$a */
    static final class C4839a implements Interceptor {
        C4839a() {
        }

        public final Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            if (originalRequest.body() == null || originalRequest.header("Content-Encoding") != null) {
                return chain.proceed(originalRequest);
            }
            Builder header = originalRequest.newBuilder().header("Content-Encoding", "gzip");
            String method = originalRequest.method();
            final RequestBody body = originalRequest.body();
            final C48412 r5 = new RequestBody() {
                public final MediaType contentType() {
                    return body.contentType();
                }

                public final long contentLength() {
                    return -1;
                }

                public final void writeTo(BufferedSink sink) throws IOException {
                    BufferedSink gzipSink = Okio.buffer((Sink) new GzipSink(sink));
                    body.writeTo(gzipSink);
                    gzipSink.close();
                }
            };
            final Buffer buffer = new Buffer();
            r5.writeTo(buffer);
            return chain.proceed(header.method(method, new RequestBody() {
                public final MediaType contentType() {
                    return r5.contentType();
                }

                public final long contentLength() {
                    return buffer.size();
                }

                public final void writeTo(BufferedSink sink) throws IOException {
                    sink.write(buffer.snapshot());
                }
            }).build());
        }
    }

    C4838z() {
    }

    /* renamed from: a */
    public final void mo45946a(int timeout, String userAgent, boolean enablePostCompression, boolean enableCustomTLSFactory) {
        C4834w.m2901c(f4681d, "Creating OkHttpClient instance");
        this.f4683b = userAgent;
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        builder.connectTimeout((long) timeout, TimeUnit.MILLISECONDS).writeTimeout((long) timeout, TimeUnit.MILLISECONDS).readTimeout((long) timeout, TimeUnit.MILLISECONDS).followRedirects(true).followSslRedirects(true).connectionPool(new ConnectionPool(3, 30, TimeUnit.MICROSECONDS));
        if (C4800a.f4363c >= C4801b.f4373i && C4800a.f4363c < C4801b.f4378n && enableCustomTLSFactory) {
            builder.sslSocketFactory(new TLSSocketFactory());
        }
        C4765ae proxyWrapper = new C4765ae();
        if (proxyWrapper.mo45962a() != null) {
            builder.proxy(new Proxy(Type.HTTP, new InetSocketAddress(proxyWrapper.mo45962a(), proxyWrapper.mo45963b())));
        }
        builder.interceptors().add(f4680c);
        ArrayList<Protocol> plist = new ArrayList<>();
        plist.add(Protocol.HTTP_1_1);
        builder.protocols(plist).retryOnConnectionFailure(true);
        this.f4682a = builder.build();
    }

    /* renamed from: a */
    public final OkHttpClient mo46105a() {
        return this.f4682a;
    }

    /* renamed from: b */
    public final String mo46106b() {
        return this.f4683b;
    }

    /* renamed from: a */
    public final C4784aq mo45945a(C4794e state) {
        return new C4760aa(this, state);
    }
}
