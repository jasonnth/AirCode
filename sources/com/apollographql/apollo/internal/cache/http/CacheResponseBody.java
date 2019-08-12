package com.apollographql.apollo.internal.cache.http;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;

final class CacheResponseBody extends ResponseBody {
    private final String contentLength;
    private final String contentType;
    private BufferedSource responseBodySource;

    CacheResponseBody(Source responseBodySource2, String contentType2, String contentLength2) {
        this.responseBodySource = Okio.buffer(responseBodySource2);
        this.contentType = contentType2;
        this.contentLength = contentLength2;
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
        return this.responseBodySource;
    }
}
