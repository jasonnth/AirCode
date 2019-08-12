package retrofit2;

import com.airbnb.android.core.net.ApiRequestHeadersInterceptor;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;
import okhttp3.FormBody.Builder;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.BufferedSink;
import p005cn.jpush.android.JPushConstants;

final class TypedRequestRawRequestBuilder {
    private static final Headers NO_HEADERS = Headers.m3948of(new String[0]);
    private RequestBody body;
    private String contentTypeHeader;
    private Builder formBuilder;
    private final boolean hasBody;
    private Headers.Builder headers;
    private MultipartBody.Builder multipartBuilder;
    private StringBuilder queryParams;
    private String relativeUrl;
    private final TypedRequest request;
    private final String requestMethod;
    private final Retrofit retrofit;
    private final HttpUrl.Builder urlBuilder;

    private static class MediaTypeOverridingRequestBody extends RequestBody {
        private final RequestBody delegate;
        private final MediaType mediaType;

        MediaTypeOverridingRequestBody(RequestBody delegate2, String mediaType2) {
            this.delegate = delegate2;
            this.mediaType = MediaType.parse(mediaType2);
        }

        public MediaType contentType() {
            return this.mediaType;
        }

        public long contentLength() throws IOException {
            return this.delegate.contentLength();
        }

        public void writeTo(BufferedSink sink) throws IOException {
            this.delegate.writeTo(sink);
        }
    }

    TypedRequestRawRequestBuilder(Retrofit retrofit3, TypedRequest request2) {
        this.retrofit = retrofit3;
        this.urlBuilder = retrofit3.baseUrl().newBuilder();
        this.request = request2;
        this.requestMethod = request2.method().name();
        this.hasBody = "PATCH".equals(this.requestMethod) || "POST".equals(this.requestMethod) || "PUT".equals(this.requestMethod);
        if (request2.bodyEncoding() == BodyEncoding.FORM_URL_ENCODED) {
            this.formBuilder = new Builder();
        } else if (request2.bodyEncoding() == BodyEncoding.MULTIPART) {
            this.multipartBuilder = new MultipartBody.Builder();
            this.multipartBuilder.setType(MultipartBody.FORM);
        }
        addPath();
        addBody();
        addHeaders();
        addQueryParams();
        addFields();
        addParts();
    }

    private void addFields() {
        if (this.request.bodyEncoding() == BodyEncoding.FORM_URL_ENCODED) {
            for (Field field : this.request.fields()) {
                String entryKey = field.name();
                if (entryKey == null) {
                    throw new IllegalArgumentException("Parameter field map contained null key.");
                }
                Object entryValue = field.value();
                if (entryValue != null) {
                    if (!field.encoded()) {
                        this.formBuilder.add(entryKey, entryValue.toString());
                    } else {
                        this.formBuilder.addEncoded(entryKey, entryValue.toString());
                    }
                }
            }
        }
    }

    private void addParts() {
        if (this.request.bodyEncoding() == BodyEncoding.MULTIPART) {
            for (Part part : this.request.parts()) {
                String entryKey = part.name();
                if (entryKey == null) {
                    throw new IllegalArgumentException("Part map contained null key.");
                }
                Object entryValue = part.value();
                StringBuilder contentDisposition = new StringBuilder();
                contentDisposition.append("form-data; name=\"");
                contentDisposition.append(entryKey);
                contentDisposition.append("\"");
                if (part.filename() != null) {
                    contentDisposition.append("; filename=\"");
                    contentDisposition.append(part.filename());
                    contentDisposition.append("\"");
                }
                Headers headers2 = Headers.m3948of("Content-Disposition", contentDisposition.toString(), "Content-Transfer-Encoding", part.encoding());
                if (entryValue != null) {
                    try {
                        this.multipartBuilder.addPart(headers2, (RequestBody) this.retrofit.requestBodyConverter(Utils.getRawType(entryValue.getClass()), new Annotation[0], new Annotation[0]).convert(entryValue));
                    } catch (IOException e) {
                        throw new RuntimeException("Unable to convert " + this.body + " to RequestBody");
                    }
                }
            }
        }
    }

    private void addPath() {
        String url = this.request.path();
        String query = null;
        int question = this.request.path().indexOf(63);
        if (question != -1 && question < this.request.path().length() - 1) {
            url = this.request.path().substring(0, question);
            query = this.request.path().substring(question + 1);
        }
        this.relativeUrl = url;
        if (query != null) {
            this.queryParams = new StringBuilder().append('?').append(query);
        }
    }

    private void addBody() {
        Object body2 = this.request.body();
        if (body2 instanceof RequestBody) {
            this.body = (RequestBody) body2;
        } else if (body2 != null) {
            try {
                this.body = (RequestBody) this.retrofit.requestBodyConverter(body2.getClass(), new Annotation[0], new Annotation[0]).convert(body2);
            } catch (IOException e) {
                throw new RuntimeException("Unable to convert " + body2 + " to RequestBody");
            }
        }
    }

    private void addHeaders() {
        Map<String, String> headers2 = this.request.headers();
        if (headers2 != null) {
            this.headers = parseHeaders(headers2).newBuilder();
        }
    }

    /* access modifiers changed from: 0000 */
    public Headers parseHeaders(Map<String, String> headers2) {
        Headers.Builder builder = new Headers.Builder();
        for (Entry<String, String> entry : headers2.entrySet()) {
            String entryKey = (String) entry.getKey();
            if (entryKey == null) {
                throw new IllegalArgumentException("Header map contained null key.");
            }
            String entryValue = (String) entry.getValue();
            if (entryValue != null) {
                if (ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE.equalsIgnoreCase(entryKey)) {
                    this.contentTypeHeader = entryValue;
                } else {
                    builder.add(entryKey, entryValue);
                }
            }
        }
        return builder.build();
    }

    private void addQueryParam(String name, String value, boolean encoded) {
        if (name == null) {
            throw new IllegalArgumentException("Query param name must not be null.");
        } else if (value == null) {
            throw new IllegalArgumentException("Query param \"" + name + "\" value must not be null.");
        } else {
            try {
                StringBuilder queryParams2 = this.queryParams;
                if (queryParams2 == null) {
                    queryParams2 = new StringBuilder();
                    this.queryParams = queryParams2;
                }
                queryParams2.append(queryParams2.length() > 0 ? '&' : '?');
                if (!encoded) {
                    name = URLEncoder.encode(name, JPushConstants.ENCODING_UTF_8);
                    value = URLEncoder.encode(value, JPushConstants.ENCODING_UTF_8);
                }
                queryParams2.append(name).append('=').append(value);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unable to convert query parameter \"" + name + "\" value to UTF-8: " + value, e);
            }
        }
    }

    private void addQueryParams() {
        for (Query query : this.request.queryParams()) {
            String entryKey = query.name();
            if (entryKey == null) {
                throw new IllegalArgumentException("Parameter query map contained null key.");
            }
            String entryValue = query.value();
            if (entryValue != null) {
                addQueryParam(entryKey, entryValue, query.encoded());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public Request build() {
        String apiUrl = this.urlBuilder.build().toString();
        StringBuilder url = new StringBuilder(apiUrl);
        if (apiUrl.endsWith("/")) {
            url.deleteCharAt(url.length() - 1);
        }
        url.append(this.relativeUrl);
        StringBuilder queryParams2 = this.queryParams;
        if (queryParams2 != null) {
            url.append(queryParams2);
        }
        RequestBody body2 = this.body;
        if (body2 == null) {
            if (this.formBuilder != null) {
                body2 = this.formBuilder.build();
            } else if (this.multipartBuilder != null) {
                body2 = this.multipartBuilder.build();
            } else if (this.hasBody) {
                body2 = RequestBody.create((MediaType) null, new byte[0]);
            }
        }
        Headers.Builder headerBuilder = this.headers;
        if (this.contentTypeHeader != null) {
            if (body2 != null) {
                body2 = new MediaTypeOverridingRequestBody(body2, this.contentTypeHeader);
            } else {
                if (headerBuilder == null) {
                    headerBuilder = new Headers.Builder();
                }
                headerBuilder.add(ApiRequestHeadersInterceptor.HEADER_CONTENT_TYPE, this.contentTypeHeader);
            }
        }
        return new Request.Builder().url(url.toString()).method(this.requestMethod, body2).headers(headerBuilder != null ? headerBuilder.build() : NO_HEADERS).tag(this.request.tag()).build();
    }
}
