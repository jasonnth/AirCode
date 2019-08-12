package okhttp3.internal.http;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody extends ResponseBody {
    private final Headers headers;
    private final BufferedSource source;

    public RealResponseBody(Headers headers2, BufferedSource source2) {
        this.headers = headers2;
        this.source = source2;
    }

    public MediaType contentType() {
        String contentType = this.headers.get(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE);
        if (contentType != null) {
            return MediaType.parse(contentType);
        }
        return null;
    }

    public long contentLength() {
        return HttpHeaders.contentLength(this.headers);
    }

    public BufferedSource source() {
        return this.source;
    }
}
