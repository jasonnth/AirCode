package com.apollographql.apollo.internal.cache.http;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import com.apollographql.apollo.api.internal.Utils;
import com.apollographql.apollo.cache.http.HttpCacheRecord;
import com.apollographql.apollo.cache.http.HttpCacheRecordEditor;
import com.apollographql.apollo.cache.http.HttpCacheStore;
import com.apollographql.apollo.internal.util.ApolloLogger;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okio.ForwardingSource;
import okio.Sink;
import okio.Source;

public final class HttpCache {
    private final HttpCacheStore cacheStore;
    private final ApolloLogger logger;

    public HttpCache(HttpCacheStore cacheStore2, ApolloLogger logger2) {
        this.cacheStore = (HttpCacheStore) Utils.checkNotNull(cacheStore2, "cacheStore can't be null");
        this.logger = (ApolloLogger) Utils.checkNotNull(logger2, "logger can't be null");
    }

    public void remove(String cacheKey) throws IOException {
        this.cacheStore.remove(cacheKey);
    }

    public void removeQuietly(String cacheKey) {
        try {
            remove(cacheKey);
        } catch (Exception ignore) {
            this.logger.mo27168w(ignore, "Failed to remove cached record for key: %s", cacheKey);
        }
    }

    public Response read(String cacheKey) {
        return read(cacheKey, false);
    }

    public Response read(String cacheKey, boolean expireAfterRead) {
        try {
            HttpCacheRecord responseCacheRecord = this.cacheStore.cacheRecord(cacheKey);
            if (responseCacheRecord == null) {
                return null;
            }
            final HttpCacheRecord cacheRecord = responseCacheRecord;
            final boolean z = expireAfterRead;
            final String str = cacheKey;
            Source cacheResponseSource = new ForwardingSource(responseCacheRecord.bodySource()) {
                public void close() throws IOException {
                    super.close();
                    HttpCache.this.closeQuietly(cacheRecord);
                    if (z) {
                        HttpCache.this.removeQuietly(str);
                    }
                }
            };
            Response response = new ResponseHeaderRecord(responseCacheRecord.headerSource()).response();
            return response.newBuilder().body(new CacheResponseBody(cacheResponseSource, response.header(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE), response.header("Content-Length"))).build();
        } catch (Exception e) {
            closeQuietly((HttpCacheRecord) null);
            this.logger.mo27167e(e, "Failed to read http cache entry for key: %s", cacheKey);
            return null;
        }
    }

    public Interceptor interceptor() {
        return new HttpCacheInterceptor(this, this.logger);
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public okhttp3.Response cacheProxy(okhttp3.Response r8, java.lang.String r9) {
        /*
            r7 = this;
            r0 = 0
            com.apollographql.apollo.cache.http.HttpCacheStore r3 = r7.cacheStore     // Catch:{ Exception -> 0x0031 }
            com.apollographql.apollo.cache.http.HttpCacheRecordEditor r0 = r3.cacheRecordEditor(r9)     // Catch:{ Exception -> 0x0031 }
            if (r0 == 0) goto L_0x002b
            okio.Sink r2 = r0.headerSink()     // Catch:{ Exception -> 0x0031 }
            com.apollographql.apollo.internal.cache.http.ResponseHeaderRecord r3 = new com.apollographql.apollo.internal.cache.http.ResponseHeaderRecord     // Catch:{ all -> 0x002c }
            r3.<init>(r8)     // Catch:{ all -> 0x002c }
            r3.writeTo(r2)     // Catch:{ all -> 0x002c }
            r7.closeQuietly(r2)     // Catch:{ Exception -> 0x0031 }
            okhttp3.Response$Builder r3 = r8.newBuilder()     // Catch:{ Exception -> 0x0031 }
            com.apollographql.apollo.internal.cache.http.ResponseBodyProxy r4 = new com.apollographql.apollo.internal.cache.http.ResponseBodyProxy     // Catch:{ Exception -> 0x0031 }
            com.apollographql.apollo.internal.util.ApolloLogger r5 = r7.logger     // Catch:{ Exception -> 0x0031 }
            r4.<init>(r0, r8, r5)     // Catch:{ Exception -> 0x0031 }
            okhttp3.Response$Builder r3 = r3.body(r4)     // Catch:{ Exception -> 0x0031 }
            okhttp3.Response r8 = r3.build()     // Catch:{ Exception -> 0x0031 }
        L_0x002b:
            return r8
        L_0x002c:
            r3 = move-exception
            r7.closeQuietly(r2)     // Catch:{ Exception -> 0x0031 }
            throw r3     // Catch:{ Exception -> 0x0031 }
        L_0x0031:
            r1 = move-exception
            r7.abortQuietly(r0)
            com.apollographql.apollo.internal.util.ApolloLogger r3 = r7.logger
            java.lang.String r4 = "Failed to proxy http response for key: %s"
            r5 = 1
            java.lang.Object[] r5 = new java.lang.Object[r5]
            r6 = 0
            r5[r6] = r9
            r3.mo27167e(r1, r4, r5)
            goto L_0x002b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo.internal.cache.http.HttpCache.cacheProxy(okhttp3.Response, java.lang.String):okhttp3.Response");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(okhttp3.Response r9, java.lang.String r10) {
        /*
            r8 = this;
            r1 = 0
            com.apollographql.apollo.cache.http.HttpCacheStore r4 = r8.cacheStore     // Catch:{ Exception -> 0x002b }
            com.apollographql.apollo.cache.http.HttpCacheRecordEditor r1 = r4.cacheRecordEditor(r10)     // Catch:{ Exception -> 0x002b }
            if (r1 == 0) goto L_0x0025
            okio.Sink r3 = r1.headerSink()     // Catch:{ Exception -> 0x002b }
            com.apollographql.apollo.internal.cache.http.ResponseHeaderRecord r4 = new com.apollographql.apollo.internal.cache.http.ResponseHeaderRecord     // Catch:{ all -> 0x0026 }
            r4.<init>(r9)     // Catch:{ all -> 0x0026 }
            r4.writeTo(r3)     // Catch:{ all -> 0x0026 }
            r8.closeQuietly(r3)     // Catch:{ Exception -> 0x002b }
            okio.Sink r0 = r1.bodySink()     // Catch:{ Exception -> 0x002b }
            com.apollographql.apollo.internal.cache.http.Utils.copyResponseBody(r9, r0)     // Catch:{ all -> 0x003e }
            r8.closeQuietly(r0)     // Catch:{ Exception -> 0x002b }
            r1.commit()     // Catch:{ Exception -> 0x002b }
        L_0x0025:
            return
        L_0x0026:
            r4 = move-exception
            r8.closeQuietly(r3)     // Catch:{ Exception -> 0x002b }
            throw r4     // Catch:{ Exception -> 0x002b }
        L_0x002b:
            r2 = move-exception
            r8.abortQuietly(r1)
            com.apollographql.apollo.internal.util.ApolloLogger r4 = r8.logger
            java.lang.String r5 = "Failed to cache http response for key: %s"
            r6 = 1
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r10
            r4.mo27167e(r2, r5, r6)
            goto L_0x0025
        L_0x003e:
            r4 = move-exception
            r8.closeQuietly(r0)     // Catch:{ Exception -> 0x002b }
            throw r4     // Catch:{ Exception -> 0x002b }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apollographql.apollo.internal.cache.http.HttpCache.write(okhttp3.Response, java.lang.String):void");
    }

    /* access modifiers changed from: private */
    public void closeQuietly(HttpCacheRecord cacheRecord) {
        if (cacheRecord != null) {
            try {
                cacheRecord.close();
            } catch (Exception ignore) {
                this.logger.mo27168w(ignore, "Failed to close cache record", new Object[0]);
            }
        }
    }

    private void abortQuietly(HttpCacheRecordEditor cacheRecordEditor) {
        if (cacheRecordEditor != null) {
            try {
                cacheRecordEditor.abort();
            } catch (Exception ignore) {
                this.logger.mo27168w(ignore, "Failed to abort cache record edit", new Object[0]);
            }
        }
    }

    private void closeQuietly(Sink sink) {
        try {
            sink.close();
        } catch (Exception ignore) {
            this.logger.mo27168w(ignore, "Failed to close sink", new Object[0]);
        }
    }
}
