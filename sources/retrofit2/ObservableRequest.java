package retrofit2;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import p032rx.Observable;

public final class ObservableRequest extends TypedRequest {

    public static final class Builder extends retrofit2.TypedRequest.Builder {
        public Builder(Retrofit retrofit) {
            super(retrofit);
        }

        public Builder(Retrofit retrofit, ObservableRequest observableRequest) {
            super(retrofit, observableRequest);
        }

        /* access modifiers changed from: protected */
        public TypedRequest newRequest() {
            return new ObservableRequest(this.retrofit, new ParameterizedType() {
                public Type[] getActualTypeArguments() {
                    return new Type[]{Builder.this.responseType};
                }

                public Type getRawType() {
                    return Observable.class;
                }

                public Type getOwnerType() {
                    return null;
                }
            }, this.bodyEncoding, this.path, this.method, this.body, this.tag, this.query, this.headers, this.parts, this.fields);
        }

        public Builder path(String path) {
            return (Builder) super.path(path);
        }

        public Builder method(Method method) {
            return (Builder) super.method(method);
        }

        public Builder tag(Object tag) {
            return (Builder) super.tag(tag);
        }

        public Builder body(Object body) {
            return (Builder) super.body(body);
        }

        public Builder queryParams(List<Query> query) {
            return (Builder) super.queryParams(query);
        }

        public Builder headers(Map<String, String> headers) {
            return (Builder) super.headers(headers);
        }

        public Builder parts(List<Part> parts) {
            return (Builder) super.parts(parts);
        }

        public Builder fields(List<Field> fields) {
            return (Builder) super.fields(fields);
        }

        public Builder responseType(Type responseType) {
            return (Builder) super.responseType(responseType);
        }

        public ObservableRequest build() {
            return (ObservableRequest) super.build();
        }
    }

    ObservableRequest(Retrofit retrofit, ParameterizedType returnType, BodyEncoding bodyEncoding, String path, Method method, Object body, Object tag, List<Query> query, Map<String, String> headers, List<Part> parts, List<Field> fields) {
        super(retrofit, returnType, bodyEncoding, path, method, body, tag, query, headers, parts, fields);
    }

    public Builder newBuilder(Retrofit retrofit) {
        return new Builder(retrofit, this);
    }
}
