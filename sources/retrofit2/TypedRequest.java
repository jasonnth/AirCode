package retrofit2;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public abstract class TypedRequest {
    protected final Object body;
    protected final BodyEncoding bodyEncoding;
    protected final CallAdapter callAdapter;
    protected final TypedRawCallFactory<Object> callFactory;
    protected final List<Field> fields;
    protected final Map<String, String> headers;
    protected boolean isCancelled;
    protected final Method method;
    protected final List<Part> parts;
    protected final String path;
    protected final List<Query> query;
    protected final ParameterizedType returnType;
    protected final Object tag;

    enum BodyEncoding {
        NONE,
        MULTIPART,
        FORM_URL_ENCODED
    }

    public static abstract class Builder {
        protected Object body;
        protected BodyEncoding bodyEncoding;
        protected List<Field> fields;
        protected Map<String, String> headers;
        protected Method method;
        protected List<Part> parts;
        protected String path;
        protected List<Query> query;
        protected Type responseType;
        protected final Retrofit retrofit;
        protected Object tag;

        /* access modifiers changed from: protected */
        public abstract TypedRequest newRequest();

        public Builder(Retrofit retrofit3, TypedRequest request) {
            this(retrofit3);
            this.path = request.path;
            this.method = request.method;
            this.tag = request.tag;
            this.body = request.body;
            this.responseType = request.returnType.getActualTypeArguments()[0];
            if (this.query != null && !this.query.isEmpty()) {
                this.query = request.query;
            }
            if (this.headers != null && !this.headers.isEmpty()) {
                this.headers = request.headers;
            }
            if (this.fields != null && !this.fields.isEmpty()) {
                this.fields = request.fields;
                this.bodyEncoding = BodyEncoding.FORM_URL_ENCODED;
            }
            if (this.parts != null && !this.parts.isEmpty()) {
                this.parts = request.parts;
                this.bodyEncoding = BodyEncoding.MULTIPART;
            }
        }

        public Builder(Retrofit retrofit3) {
            this.bodyEncoding = BodyEncoding.NONE;
            this.query = Collections.emptyList();
            this.headers = Collections.emptyMap();
            this.parts = Collections.emptyList();
            this.fields = Collections.emptyList();
            this.retrofit = retrofit3;
        }

        public Builder path(String path2) {
            this.path = path2;
            return this;
        }

        public Builder method(Method method2) {
            this.method = (Method) Utils.checkNotNull(method2, "method == null");
            return this;
        }

        public Builder tag(Object tag2) {
            this.tag = tag2;
            return this;
        }

        public Builder body(Object body2) {
            this.body = body2;
            return this;
        }

        public Builder queryParams(List<Query> query2) {
            this.query = (List) Utils.checkNotNull(query2, "query == null");
            return this;
        }

        public Builder headers(Map<String, String> headers2) {
            this.headers = (Map) Utils.checkNotNull(headers2, "headers == null");
            return this;
        }

        public Builder parts(List<Part> parts2) {
            this.parts = (List) Utils.checkNotNull(parts2, "parts == null");
            this.bodyEncoding = BodyEncoding.MULTIPART;
            return this;
        }

        public Builder fields(List<Field> fields2) {
            this.fields = (List) Utils.checkNotNull(fields2, "fields == null");
            this.bodyEncoding = BodyEncoding.FORM_URL_ENCODED;
            return this;
        }

        public Builder responseType(Type responseType2) {
            this.responseType = responseType2;
            return this;
        }

        public TypedRequest build() {
            boolean requestHasBody;
            boolean gotBody;
            boolean gotField;
            boolean gotPart;
            Utils.checkNotNull(this.path, "path == null");
            Utils.checkNotNull(this.method, "method == null");
            Utils.checkNotNull(this.responseType, "responseType == null");
            if (this.path.length() == 0 || this.path.charAt(0) != '/') {
                throw new IllegalArgumentException("URL path \"" + this.path + "\" must start with '/'.");
            }
            if (this.method == Method.PATCH || this.method == Method.POST || this.method == Method.PUT) {
                requestHasBody = true;
            } else {
                requestHasBody = false;
            }
            if (this.body != null) {
                gotBody = true;
            } else {
                gotBody = false;
            }
            if (!this.fields.isEmpty()) {
                gotField = true;
            } else {
                gotField = false;
            }
            if (!this.parts.isEmpty()) {
                gotPart = true;
            } else {
                gotPart = false;
            }
            if (this.bodyEncoding == BodyEncoding.NONE && !requestHasBody && gotBody) {
                throw new IllegalArgumentException("Non-body HTTP method cannot contain body.");
            } else if (this.bodyEncoding == BodyEncoding.FORM_URL_ENCODED && !gotField) {
                throw new IllegalArgumentException("Form-encoded method must contain at least one field.");
            } else if (this.bodyEncoding != BodyEncoding.MULTIPART || gotPart) {
                return newRequest();
            } else {
                throw new IllegalArgumentException("Multipart method must contain at least one part.");
            }
        }
    }

    TypedRequest(Retrofit retrofit, ParameterizedType returnType2, BodyEncoding bodyEncoding2, String path2, Method method2, Object body2, Object tag2, List<Query> query2, Map<String, String> headers2, List<Part> parts2, List<Field> fields2) {
        this.returnType = returnType2;
        this.path = path2;
        this.method = method2;
        this.body = body2;
        this.tag = tag2;
        this.query = query2;
        this.headers = headers2;
        this.parts = parts2;
        this.fields = fields2;
        this.bodyEncoding = bodyEncoding2;
        this.callAdapter = retrofit.callAdapter(returnType2, new Annotation[0]);
        this.callFactory = new TypedRawCallFactory<>(retrofit, this.callAdapter.responseType(), this);
    }

    public String path() {
        return this.path;
    }

    public Method method() {
        return this.method;
    }

    public Object body() {
        return this.body;
    }

    public Object tag() {
        return this.tag;
    }

    public List<Query> queryParams() {
        return this.query;
    }

    public Map<String, String> headers() {
        return this.headers;
    }

    public List<Part> parts() {
        return this.parts;
    }

    public List<Field> fields() {
        return this.fields;
    }

    public BodyEncoding bodyEncoding() {
        return this.bodyEncoding;
    }

    public void cancel() {
        this.isCancelled = true;
    }

    public boolean isCancelled() {
        return this.isCancelled;
    }

    public ParameterizedType returnType() {
        return this.returnType;
    }

    public <T> T newCall() {
        return this.callAdapter.adapt(new OkHttpCall(this.callFactory, null));
    }

    private RuntimeException requestError(String message, Object... args) {
        if (args.length > 0) {
            message = String.format(message, args);
        }
        return new IllegalArgumentException(getClass().getSimpleName() + ": " + message);
    }
}
