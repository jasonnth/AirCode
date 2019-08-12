package com.apollographql.apollo.internal.cache.http;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.http.HttpCacheRecordEditor;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

final class ResponseBodyProxy extends ResponseBody {
    private final String contentLength;
    private final String contentType;
    private final Source responseBodySource;

    private static class ProxySource implements Source {
        final HttpCacheRecordEditor cacheRecordEditor;
        boolean closed;
        final ApolloLogger logger;
        final ResponseBodyCacheSink responseBodyCacheSink;
        final Source responseBodySource;

        ProxySource(HttpCacheRecordEditor cacheRecordEditor2, Source responseBodySource2, final ApolloLogger logger2) {
            this.cacheRecordEditor = cacheRecordEditor2;
            this.responseBodySource = responseBodySource2;
            this.logger = logger2;
            this.responseBodyCacheSink = new ResponseBodyCacheSink(Okio.buffer(cacheRecordEditor2.bodySink())) {
                /* access modifiers changed from: 0000 */
                public void onException(Exception e) {
                    ProxySource.this.abortCacheQuietly();
                    logger2.mo27168w(e, "Operation failed", new Object[0]);
                }
            };
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            try {
                long bytesRead = this.responseBodySource.read(sink, byteCount);
                if (bytesRead == -1) {
                    if (!this.closed) {
                        this.closed = true;
                        commitCache();
                    }
                    return -1;
                }
                this.responseBodyCacheSink.copyFrom(sink, sink.size() - bytesRead, bytesRead);
                return bytesRead;
            } catch (IOException e) {
                if (!this.closed) {
                    this.closed = true;
                    abortCacheQuietly();
                }
                throw e;
            }
        }

        public Timeout timeout() {
            return this.responseBodySource.timeout();
        }

        public void close() throws IOException {
            if (!this.closed) {
                this.closed = true;
                if (Util.discard(this, 100, TimeUnit.MILLISECONDS)) {
                    this.responseBodySource.close();
                    commitCache();
                    return;
                }
                this.responseBodySource.close();
                abortCacheQuietly();
            }
        }

        private void commitCache() {
            try {
                this.responseBodyCacheSink.close();
                this.cacheRecordEditor.commit();
            } catch (Exception e) {
                Util.closeQuietly((Closeable) this.responseBodyCacheSink);
                abortCacheQuietly();
                this.logger.mo27167e(e, "Failed to commit cache changes", new Object[0]);
            }
        }

        /* access modifiers changed from: private */
        public void abortCacheQuietly() {
            Util.closeQuietly((Closeable) this.responseBodyCacheSink);
            try {
                this.cacheRecordEditor.abort();
            } catch (Exception ignore) {
                this.logger.mo27168w(ignore, "Failed to abort cache edit", new Object[0]);
            }
        }
    }

    ResponseBodyProxy(HttpCacheRecordEditor cacheRecordEditor, Response sourceResponse, ApolloLogger logger) {
        Utils.checkNotNull(cacheRecordEditor, "cacheRecordEditor == null");
        Utils.checkNotNull(sourceResponse, "sourceResponse == null");
        Utils.checkNotNull(logger, "logger == null");
        this.contentType = sourceResponse.header(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE);
        this.contentLength = sourceResponse.header("Content-Length");
        this.responseBodySource = new ProxySource(cacheRecordEditor, sourceResponse.body().source(), logger);
    }

    public MediaType contentType() {
        if (this.contentType != null) {
            return MediaType.parse(this.contentType);
        }
        return null;
    }

    public long contentLength() {
        try {
            if (this.contentLength != null) {
                return Long.parseLong(this.contentLength);
            }
            return -1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public BufferedSource source() {
        return Okio.buffer(this.responseBodySource);
    }
}
