package com.airbnb.android.aireventlogger;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Interceptor.Chain;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;
import okio.BufferedSink;
import okio.GzipSink;
import okio.Okio;
import okio.Sink;

class GzipRequestInterceptor implements Interceptor {
    static final String CONTENT_ENCODING_HEADER = "Content-Encoding";
    static final String ENCODE_WITH_HEADER = "X-Encode-With";
    static final String GZIP = "gzip";

    GzipRequestInterceptor() {
    }

    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
        if (originalRequest.body() == null || originalRequest.header(CONTENT_ENCODING_HEADER) != null || originalRequest.header(ENCODE_WITH_HEADER) == null || !originalRequest.header(ENCODE_WITH_HEADER).equals(GZIP)) {
            return chain.proceed(originalRequest);
        }
        return chain.proceed(originalRequest.newBuilder().removeHeader(ENCODE_WITH_HEADER).header(CONTENT_ENCODING_HEADER, GZIP).method(originalRequest.method(), forceContentLength(gzip(originalRequest.body()))).build());
    }

    private RequestBody forceContentLength(final RequestBody requestBody) throws IOException {
        final Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);
        return new RequestBody() {
            public MediaType contentType() {
                return requestBody.contentType();
            }

            public long contentLength() {
                return buffer.size();
            }

            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(buffer.snapshot());
            }
        };
    }

    private RequestBody gzip(final RequestBody body) {
        return new RequestBody() {
            public MediaType contentType() {
                return body.contentType();
            }

            public long contentLength() {
                return -1;
            }

            public void writeTo(BufferedSink sink) throws IOException {
                BufferedSink gzipSink = Okio.buffer((Sink) new GzipSink(sink));
                body.writeTo(gzipSink);
                gzipSink.close();
            }
        };
    }
}
