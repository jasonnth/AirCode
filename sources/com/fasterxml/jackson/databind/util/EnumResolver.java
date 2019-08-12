package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;

public class EnumResolver implements Serializable {
    protected final Enum<?> _defaultValue;
    protected final Class<Enum<?>> _enumClass;
    protected final Enum<?>[] _enums;
    protected final HashMap<String, Enum<?>> _enumsById;

    protected EnumResolver(Class<Enum<?>> enumClass, Enum<?>[] enums, HashMap<String, Enum<?>> map, Enum<?> defaultValue) {
        this._enumClass = enumClass;
        this._enums = enums;
        this._enumsById = map;
        this._defaultValue = defaultValue;
    }

    public static EnumResolver constructFor(Class<Enum<?>> enumCls, AnnotationIntrospector ai) {
        Enum<?>[] enumValues = (Enum[]) enumCls.getEnumConstants();
        if (enumValues == null) {
            throw new IllegalArgumentException("No enum constants for class " + enumCls.getName());
        }
        String[] names = ai.findEnumValues(enumCls, enumValues, new String[enumValues.length]);
        HashMap<String, Enum<?>> map = new HashMap<>();
        int len = enumValues.length;
        for (int i = 0; i < len; i++) {
            String name = names[i];
            if (name == null) {
                name = enumValues[i].name();
            }
            map.put(name, enumValues[i]);
        }
        return new EnumResolver(enumCls, enumValues, map, ai.findDefaultEnumValue(enumCls));
    }

    public static EnumResolver constructUsingToString(Class<Enum<?>> enumCls, AnnotationIntrospector ai) {
        Enum<?>[] enumValues = (Enum[]) enumCls.getEnumConstants();
        HashMap<String, Enum<?>> map = new HashMap<>();
        int i = enumValues.length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Enum<?> e = enumValues[i];
            map.put(e.toString(), e);
        }
        return new EnumResolver(enumCls, enumValues, map, ai == null ? null : ai.findDefaultEnumValue(enumCls));
    }

    public static EnumResolver constructUsingMethod(Class<Enum<?>> enumCls, Method accessor, AnnotationIntrospector ai) {
        Enum<?>[] enumValues = (Enum[]) enumCls.getEnumConstants();
        HashMap<String, Enum<?>> map = new HashMap<>();
        int i = enumValues.length;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            Enum<?> en = enumValues[i];
            try {
                Object o = accessor.invoke(en, new Object[0]);
                if (o != null) {
                    map.put(o.toString(), en);
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Failed to access @JsonValue of Enum value " + en + ": " + e.getMessage());
            }
        }
        return new EnumResolver(enumCls, enumValues, map, ai != null ? ai.findDefaultEnumValue(enumCls) : null);
    }

    public static EnumResolver constructUnsafe(Class<?> rawEnumCls, AnnotationIntrospector ai) {
        return constructFor(rawEnumCls, ai);
    }

    public static EnumResolver constructUnsafeUsingToString(Class<?> rawEnumCls, AnnotationIntrospector ai) {
        return constructUsingToString(rawEnumCls, ai);
    }

    public static EnumResolver constructUnsafeUsingMethod(Class<?> rawEnumCls, Method accessor, AnnotationIntrospector ai) {
        return constructUsingMethod(rawEnumCls, accessor, ai);
    }

    public CompactStringObjectMap constructLookup() {
        return CompactStringObjectMap.construct(this._enumsById);
    }

    public Enum<?> findEnum(String key) {
        return (Enum) this._enumsById.get(key);
    }

    public Enum<?> getDefaultValue() {
        return this._defaultValue;
    }

    public Enum<?>[] getRawEnums() {
        return this._enums;
    }

    public Collection<String> getEnumIds() {
        return this._enumsById.keySet();
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}
