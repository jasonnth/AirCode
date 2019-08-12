package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.Serializers.Base;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.ClassKey;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class SimpleSerializers extends Base implements Serializable {
    protected HashMap<ClassKey, JsonSerializer<?>> _classMappings = null;
    protected boolean _hasEnumSerializer = false;
    protected HashMap<ClassKey, JsonSerializer<?>> _interfaceMappings = null;

    public SimpleSerializers() {
    }

    public SimpleSerializers(List<JsonSerializer<?>> sers) {
        addSerializers(sers);
    }

    public void addSerializer(JsonSerializer<?> ser) {
        Class<?> cls = ser.handledType();
        if (cls == null || cls == Object.class) {
            throw new IllegalArgumentException("JsonSerializer of type " + ser.getClass().getName() + " does not define valid handledType() -- must either register with method that takes type argument " + " or make serializer extend 'com.fasterxml.jackson.databind.ser.std.StdSerializer'");
        }
        _addSerializer(cls, ser);
    }

    public <T> void addSerializer(Class<? extends T> type, JsonSerializer<T> ser) {
        _addSerializer(type, ser);
    }

    public void addSerializers(List<JsonSerializer<?>> sers) {
        for (JsonSerializer<?> ser : sers) {
            addSerializer(ser);
        }
    }

    public JsonSerializer<?> findSerializer(SerializationConfig config, JavaType type, BeanDescription beanDesc) {
        JsonSerializer<?> ser;
        Class<?> cls = type.getRawClass();
        ClassKey key = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings != null) {
                JsonSerializer<?> ser2 = (JsonSerializer) this._interfaceMappings.get(key);
                if (ser2 != null) {
                    return ser2;
                }
            }
        } else if (this._classMappings != null) {
            JsonSerializer<?> ser3 = (JsonSerializer) this._classMappings.get(key);
            if (ser3 != null) {
                return ser3;
            }
            if (this._hasEnumSerializer && type.isEnumType()) {
                key.reset(Enum.class);
                JsonSerializer<?> ser4 = (JsonSerializer) this._classMappings.get(key);
                if (ser4 != null) {
                    return ser4;
                }
            }
            for (Class<?> curr = cls; curr != null; curr = curr.getSuperclass()) {
                key.reset(curr);
                JsonSerializer<?> ser5 = (JsonSerializer) this._classMappings.get(key);
                if (ser5 != null) {
                    return ser5;
                }
            }
        }
        if (this._interfaceMappings != null) {
            JsonSerializer<?> ser6 = _findInterfaceMapping(cls, key);
            if (ser6 != null) {
                return ser6;
            }
            if (!cls.isInterface()) {
                do {
                    cls = cls.getSuperclass();
                    if (cls != null) {
                        ser = _findInterfaceMapping(cls, key);
                    }
                } while (ser == null);
                return ser;
            }
        }
        return null;
    }

    public JsonSerializer<?> findArraySerializer(SerializationConfig config, ArrayType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findCollectionSerializer(SerializationConfig config, CollectionType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findCollectionLikeSerializer(SerializationConfig config, CollectionLikeType type, BeanDescription beanDesc, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findMapSerializer(SerializationConfig config, MapType type, BeanDescription beanDesc, JsonSerializer<Object> jsonSerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(config, type, beanDesc);
    }

    public JsonSerializer<?> findMapLikeSerializer(SerializationConfig config, MapLikeType type, BeanDescription beanDesc, JsonSerializer<Object> jsonSerializer, TypeSerializer elementTypeSerializer, JsonSerializer<Object> jsonSerializer2) {
        return findSerializer(config, type, beanDesc);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> _findInterfaceMapping(Class<?> cls, ClassKey key) {
        Class<?>[] arr$;
        for (Class<?> iface : cls.getInterfaces()) {
            key.reset(iface);
            JsonSerializer<?> ser = (JsonSerializer) this._interfaceMappings.get(key);
            if (ser != null) {
                return ser;
            }
            JsonSerializer<?> ser2 = _findInterfaceMapping(iface, key);
            if (ser2 != null) {
                return ser2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void _addSerializer(Class<?> cls, JsonSerializer<?> ser) {
        ClassKey key = new ClassKey(cls);
        if (cls.isInterface()) {
            if (this._interfaceMappings == null) {
                this._interfaceMappings = new HashMap<>();
            }
            this._interfaceMappings.put(key, ser);
            return;
        }
        if (this._classMappings == null) {
            this._classMappings = new HashMap<>();
        }
        this._classMappings.put(key, ser);
        if (cls == Enum.class) {
            this._hasEnumSerializer = true;
        }
    }
}
