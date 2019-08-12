package com.google.android.exoplayer.upstream;

import android.text.TextUtils;
import com.google.android.exoplayer.util.Predicate;
import com.google.android.exoplayer.util.Util;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface HttpDataSource extends UriDataSource {
    public static final Predicate<String> REJECT_PAYWALL_TYPES = new Predicate<String>() {
        public boolean evaluate(String contentType) {
            String contentType2 = Util.toLowerInvariant(contentType);
            return !TextUtils.isEmpty(contentType2) && (!contentType2.contains("text") || contentType2.contains("text/vtt")) && !contentType2.contains("html") && !contentType2.contains("xml");
        }
    };

    public static class HttpDataSourceException extends IOException {
        public final DataSpec dataSpec;
        public final int type;

        public HttpDataSourceException(String message, DataSpec dataSpec2, int type2) {
            super(message);
            this.dataSpec = dataSpec2;
            this.type = type2;
        }

        public HttpDataSourceException(IOException cause, DataSpec dataSpec2, int type2) {
            super(cause);
            this.dataSpec = dataSpec2;
            this.type = type2;
        }

        public HttpDataSourceException(String message, IOException cause, DataSpec dataSpec2, int type2) {
            super(message, cause);
            this.dataSpec = dataSpec2;
            this.type = type2;
        }
    }

    public static final class InvalidContentTypeException extends HttpDataSourceException {
        public final String contentType;

        public InvalidContentTypeException(String contentType2, DataSpec dataSpec) {
            super("Invalid content type: " + contentType2, dataSpec, 1);
            this.contentType = contentType2;
        }
    }

    public static final class InvalidResponseCodeException extends HttpDataSourceException {
        public final Map<String, List<String>> headerFields;
        public final int responseCode;

        public InvalidResponseCodeException(int responseCode2, Map<String, List<String>> headerFields2, DataSpec dataSpec) {
            super("Response code: " + responseCode2, dataSpec, 1);
            this.responseCode = responseCode2;
            this.headerFields = headerFields2;
        }
    }
}
