package com.facebook.react.modules.network;

import android.content.Context;
import android.net.Uri;
import com.facebook.common.logging.FLog;
import com.facebook.react.common.ReactConstants;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;
import okio.Okio;
import okio.Source;

class RequestBodyUtil {
    private static final String CONTENT_ENCODING_GZIP = "gzip";

    RequestBodyUtil() {
    }

    public static boolean isGzipEncoding(String encodingType) {
        return CONTENT_ENCODING_GZIP.equalsIgnoreCase(encodingType);
    }

    public static InputStream getFileInputStream(Context context, String fileContentUriStr) {
        try {
            return context.getContentResolver().openInputStream(Uri.parse(fileContentUriStr));
        } catch (Exception e) {
            FLog.m1808e(ReactConstants.TAG, "Could not retrieve file for contentUri " + fileContentUriStr, (Throwable) e);
            return null;
        }
    }

    public static RequestBody createGzip(MediaType mediaType, String body) {
        ByteArrayOutputStream gzipByteArrayOutputStream = new ByteArrayOutputStream();
        try {
            OutputStream gzipOutputStream = new GZIPOutputStream(gzipByteArrayOutputStream);
            gzipOutputStream.write(body.getBytes());
            gzipOutputStream.close();
            return RequestBody.create(mediaType, gzipByteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }

    public static RequestBody create(final MediaType mediaType, final InputStream inputStream) {
        return new RequestBody() {
            public MediaType contentType() {
                return mediaType;
            }

            public long contentLength() {
                try {
                    return (long) inputStream.available();
                } catch (IOException e) {
                    return 0;
                }
            }

            public void writeTo(BufferedSink sink) throws IOException {
                Source source = null;
                try {
                    source = Okio.source(inputStream);
                    sink.writeAll(source);
                } finally {
                    Util.closeQuietly((Closeable) source);
                }
            }
        };
    }

    public static ProgressRequestBody createProgressRequest(RequestBody requestBody, ProgressListener listener) {
        return new ProgressRequestBody(requestBody, listener);
    }

    public static RequestBody getEmptyBody(String method) {
        if (method.equals("POST") || method.equals("PUT") || method.equals("PATCH")) {
            return RequestBody.create((MediaType) null, ByteString.EMPTY);
        }
        return null;
    }
}
