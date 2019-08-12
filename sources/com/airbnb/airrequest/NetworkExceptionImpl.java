package com.airbnb.airrequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.Closeable;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.List;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Converter;
import retrofit2.Converter.Factory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkExceptionImpl extends RuntimeException implements NetworkException {
    private final String bodyString;
    private final Object errorResponse;
    private final Response<?> response;
    private final ResponseBody responseBody;

    public NetworkExceptionImpl(Object errorResponse2) {
        this.errorResponse = errorResponse2;
        this.response = null;
        this.bodyString = null;
        this.responseBody = null;
    }

    public NetworkExceptionImpl(Retrofit retrofit, Response<?> response2, Type errorResponseType) {
        this.response = (Response) Utils.checkNotNull(response2, "response");
        this.responseBody = cloneResponseBody(response2.errorBody());
        this.bodyString = getBodyAsString(this.responseBody);
        this.errorResponse = getBodyAs(retrofit, errorResponseType, this.responseBody);
    }

    public NetworkExceptionImpl(Throwable cause) {
        super(cause);
        this.errorResponse = null;
        this.bodyString = null;
        this.response = null;
        this.responseBody = null;
    }

    public <T> T errorResponse() {
        return this.errorResponse;
    }

    public boolean hasErrorResponse() {
        return this.errorResponse != null;
    }

    public okhttp3.Response rawResponse() {
        if (this.response != null) {
            return this.response.raw();
        }
        return null;
    }

    public ResponseBody responseBody() {
        return this.responseBody;
    }

    public String bodyString() {
        return this.bodyString;
    }

    public int statusCode() {
        if (this.response != null) {
            return this.response.code();
        }
        return -1;
    }

    private static String getBodyAsString(ResponseBody responseBody2) {
        String str = null;
        if (responseBody2 == null) {
            return str;
        }
        try {
            return responseBody2.string();
        } catch (IOException e) {
            return str;
        }
    }

    private static ResponseBody cloneResponseBody(final ResponseBody body) {
        if (body == null) {
            return null;
        }
        final Buffer buffer = new Buffer();
        BufferedSource source = null;
        try {
            source = body.source();
            buffer.writeAll(source);
            Util.closeQuietly((Closeable) source);
            return new ResponseBody() {
                public MediaType contentType() {
                    return body.contentType();
                }

                public long contentLength() {
                    return buffer.size();
                }

                public BufferedSource source() {
                    return buffer.clone();
                }
            };
        } catch (IOException e) {
            Util.closeQuietly((Closeable) source);
            return null;
        } catch (Throwable th) {
            Util.closeQuietly((Closeable) source);
            throw th;
        }
    }

    private static <T> T getBodyAs(Retrofit retrofit, Type responseType, ResponseBody body) {
        T t = null;
        if (body == null) {
            return t;
        }
        try {
            return resolveResponseBodyConverter(retrofit, responseType).convert(body);
        } catch (JsonProcessingException e) {
            return t;
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }

    private static Converter<ResponseBody, ?> resolveResponseBodyConverter(Retrofit retrofit, Type type) {
        List<Factory> converterFactories = retrofit.converterFactories();
        for (Factory factory : converterFactories) {
            Converter<ResponseBody, ?> converter = factory.responseBodyConverter(type, new Annotation[0], retrofit);
            if (converter != null) {
                return converter;
            }
        }
        StringBuilder builder = new StringBuilder("Could not locate ResponseBody converter for ").append(type).append(". Tried:");
        for (Factory converterFactory : converterFactories) {
            builder.append("\n * ").append(converterFactory.getClass().getName());
        }
        throw new IllegalArgumentException(builder.toString());
    }
}
