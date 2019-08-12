package com.airbnb.android.aireventlogger;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import okio.BufferedSink;

class OkHttpEventUpload extends AirEventUpload {
    private OkHttpClient client;

    OkHttpEventUpload(String userAgent) {
        super(userAgent);
    }

    public void uploadData(final PendingEvents pendingEvents) throws AirEventUploadException {
        Builder requestBuilder = new Builder().url(pendingEvents.endpoint());
        if (this.userAgent != null) {
            requestBuilder.addHeader("User-Agent", Util.toHumanReadableAscii(this.userAgent));
        }
        if (pendingEvents.compressionType() == CompressionType.GZIP) {
            requestBuilder.addHeader("X-Encode-With", "gzip");
        }
        RequestBody body = new RequestBody() {
            public MediaType contentType() {
                return MediaType.parse(pendingEvents.contentType());
            }

            public long contentLength() throws IOException {
                return (long) pendingEvents.data().length();
            }

            public void writeTo(BufferedSink sink) throws IOException {
                pendingEvents.data().writeTo(sink.outputStream());
            }
        };
        if (this.client == null) {
            this.client = new OkHttpClient.Builder().addInterceptor(new GzipRequestInterceptor()).build();
        }
        Response response = null;
        try {
            Response response2 = this.client.newCall(requestBuilder.post(body).build()).execute();
            if (!response2.isSuccessful()) {
                throw new AirEventUploadException(response2.code());
            } else if (response2 != null && response2.body() != null) {
                response2.body().close();
            }
        } catch (IOException e) {
            throw new AirEventUploadException(-1, e);
        } catch (Throwable th) {
            if (!(response == null || response.body() == null)) {
                response.body().close();
            }
            throw th;
        }
    }
}
