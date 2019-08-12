package com.airbnb.android.aireventlogger;

import com.microsoft.thrifty.Struct;
import com.microsoft.thrifty.protocol.BinaryProtocol;
import com.microsoft.thrifty.transport.BufferTransport;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.UUID;
import okio.Buffer;

final class Utils {
    private static String sessionID;

    Utils() {
    }

    static void closeQuietly(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }

    static <T> T throwIfNull(T item, String message) {
        if (item != null) {
            return item;
        }
        throw new NullPointerException(message);
    }

    static boolean hasOkHttpOnClasspath() {
        return hasOnClassPath("okhttp3.OkHttpClient");
    }

    static boolean hasJacksonOnClassPath() {
        return hasOnClassPath("com.fasterxml.jackson.databind.ObjectMapper");
    }

    private static boolean hasOnClassPath(String klass) {
        try {
            Class.forName(klass);
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static String getSessionID() {
        if (sessionID == null) {
            sessionID = UUID.randomUUID().toString();
        }
        return sessionID;
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

    static Buffer toBuffer(Struct event) throws IOException {
        Buffer buffer = new Buffer();
        event.write(new BinaryProtocol(new BufferTransport(buffer)));
        return buffer;
    }
}
