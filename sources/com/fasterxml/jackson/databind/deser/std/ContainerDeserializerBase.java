package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public abstract class ContainerDeserializerBase<T> extends StdDeserializer<T> {
    public abstract JsonDeserializer<Object> getContentDeserializer();

    protected ContainerDeserializerBase(JavaType selfType) {
        super(selfType);
    }

    public SettableBeanProperty findBackReference(String refName) {
        JsonDeserializer<Object> valueDeser = getContentDeserializer();
        if (valueDeser != null) {
            return valueDeser.findBackReference(refName);
        }
        throw new IllegalArgumentException("Can not handle managed/back reference '" + refName + "': type: container deserializer of type " + getClass().getName() + " returned null for 'getContentDeserializer()'");
    }

    /* access modifiers changed from: protected */
    public void wrapAndThrow(Throwable t, Object ref, String key) throws IOException {
        while ((t instanceof InvocationTargetException) && t.getCause() != null) {
            t = t.getCause();
        }
        if (t instanceof Error) {
            throw ((Error) t);
        } else if (!(t instanceof IOException) || (t instanceof JsonMappingException)) {
            if (key == null) {
                key = "N/A";
            }
            throw JsonMappingException.wrapWithPath(t, ref, key);
        } else {
            throw ((IOException) t);
        }
    }
}
