package com.airbnb.airrequest;

import android.content.Context;
import com.airbnb.airrequest.Interceptor.Factory;
import com.facebook.common.util.UriUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.p309io.ByteStreams;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import okhttp3.HttpUrl;
import okhttp3.HttpUrl.Builder;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import p005cn.jpush.android.JPushConstants;
import retrofit2.Field;
import retrofit2.Query;
import retrofit2.Response;
import retrofit2.Retrofit;

final class Utils {
    Utils() {
    }

    private static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException();
        }
    }

    private static Builder addQueryParams(Builder builder, List<Query> queryParams) {
        for (Query entry : queryParams) {
            String queryName = entry.name();
            String queryValue = entry.value();
            if (queryValue != null) {
                if (!entry.encoded()) {
                    builder.addEncodedQueryParameter(urlEncode(queryName), urlEncode(queryValue));
                } else {
                    builder.addEncodedQueryParameter(queryName, queryValue);
                }
            }
        }
        return builder;
    }

    static AirRequest intercept(List<? extends Factory> interceptorFactories, AirRequest request) {
        AirRequest newRequest = request;
        for (Factory factory : interceptorFactories) {
            Interceptor interceptor = factory.interceptorFor(request.getClass());
            if (interceptor != null) {
                newRequest = interceptor.intercept(newRequest);
            }
        }
        return newRequest;
    }

    static <T> T checkNotNull(T reference, String message) {
        if (reference != null) {
            return reference;
        }
        throw new NullPointerException(message + " == null");
    }

    static String convertPathAndQueryParams(AirRequest airRequest) {
        List<Query> queryParams = new ArrayList<>();
        if (airRequest.getQueryParams() != null) {
            queryParams.addAll(airRequest.getQueryParams());
        }
        HttpUrl uri = HttpUrl.get(addQueryParams(new Builder().scheme(UriUtil.HTTP_SCHEME).host("localhost").addPathSegments(airRequest.getPathPrefix() + airRequest.getPath()), queryParams).build().uri());
        return uri.query() != null ? uri.encodedPath() + "?" + uri.encodedQuery() : uri.encodedPath();
    }

    static Map<String, String> sanitizeHeaders(AirRequest airRequest) {
        Set<Entry<String, String>> entries = airRequest.getHeaders().entrySet();
        ImmutableMap.Builder<String, String> builder = new ImmutableMap.Builder<>();
        for (Entry<String, String> header : entries) {
            builder.put(header.getKey(), Util.toHumanReadableAscii((String) header.getValue()));
        }
        return builder.build();
    }

    static Type responseType(final Type type) {
        return new ParameterizedType() {
            public Type[] getActualTypeArguments() {
                return new Type[]{type};
            }

            public Type getOwnerType() {
                return null;
            }

            public Type getRawType() {
                return Response.class;
            }
        };
    }

    static List<Field> convertFields(Set<Query> params) {
        List<Field> fields = new ArrayList<>(params.size());
        for (Query query : params) {
            fields.add(new Field(query.name(), query.value(), query.encoded()));
        }
        return fields;
    }

    static Object convertBody(Retrofit retrofit, AirRequest immutableRequest) {
        Object body = immutableRequest.getBody();
        if (body == null) {
            return null;
        }
        MediaType mediaType = MediaType.parse(immutableRequest.getContentType());
        if (body instanceof String) {
            return RequestBody.create(mediaType, (String) body);
        }
        try {
            try {
                return retrofit.requestBodyConverter(body.getClass(), new Annotation[0], new Annotation[0]).convert(body);
            } catch (IOException e) {
                throw new RuntimeException("Unable to convert " + body + " to RequestBody");
            }
        } catch (RuntimeException e2) {
            throw new RuntimeException("Unable to create body converter for " + body.getClass().getSimpleName(), e2);
        }
    }

    static Class<?> getRawType(Type type) {
        if (type == null) {
            throw new NullPointerException("type == null");
        } else if (type instanceof Class) {
            return (Class) type;
        } else {
            if (type instanceof ParameterizedType) {
                Type rawType = ((ParameterizedType) type).getRawType();
                if (rawType instanceof Class) {
                    return (Class) rawType;
                }
                throw new IllegalArgumentException();
            } else if (type instanceof GenericArrayType) {
                return Array.newInstance(getRawType(((GenericArrayType) type).getGenericComponentType()), 0).getClass();
            } else {
                if (type instanceof TypeVariable) {
                    return Object.class;
                }
                if (type instanceof WildcardType) {
                    return getRawType(((WildcardType) type).getUpperBounds()[0]);
                }
                throw new IllegalArgumentException("Expected a Class, ParameterizedType, or GenericArrayType, but <" + type + "> is of type " + type.getClass().getName());
            }
        }
    }

    static byte[] getAssetAsByteArray(Context context, String fileName) {
        InputStream is = null;
        try {
            is = context.getResources().getAssets().open(fileName);
            byte[] byteArray = ByteStreams.toByteArray(is);
            Util.closeQuietly((Closeable) is);
            return byteArray;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Throwable th) {
            Util.closeQuietly((Closeable) is);
            throw th;
        }
    }
}
