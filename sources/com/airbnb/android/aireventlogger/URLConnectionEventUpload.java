package com.airbnb.android.aireventlogger;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.zip.GZIPOutputStream;

class URLConnectionEventUpload extends AirEventUpload {
    URLConnectionEventUpload(String userAgent) {
        super(userAgent);
    }

    public void uploadData(PendingEvents pendingEvents) throws AirEventUploadException {
        HttpURLConnection conn = null;
        int status = -1;
        try {
            conn = (HttpURLConnection) new URL(pendingEvents.endpoint()).openConnection();
            conn.setDoOutput(true);
            conn.addRequestProperty(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, pendingEvents.contentType());
            if (pendingEvents.compressionType() == CompressionType.GZIP) {
                conn.addRequestProperty("Content-Encoding", "gzip");
            }
            if (this.userAgent != null) {
                conn.addRequestProperty("User-Agent", this.userAgent);
            }
            OutputStream out = conn.getOutputStream();
            if (pendingEvents.compressionType() == CompressionType.GZIP) {
                out = new GZIPOutputStream(out);
            }
            pendingEvents.data().writeTo(out);
            out.flush();
            Utils.closeQuietly(out);
        } catch (IOException e) {
            Utils.closeQuietly(null);
        } catch (IOException e2) {
            if (conn != null) {
                conn.disconnect();
            }
        } catch (Throwable th) {
            if (conn != null) {
                conn.disconnect();
            }
            throw th;
        }
        status = conn.getResponseCode();
        if (conn != null) {
            conn.disconnect();
        }
        if (!isSuccessful(status)) {
            throw new AirEventUploadException(status);
        }
    }

    private static boolean isSuccessful(int code) {
        return code >= 200 && code < 300;
    }
}
