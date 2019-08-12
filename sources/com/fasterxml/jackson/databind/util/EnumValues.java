package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class EnumValues implements Serializable {
    private final Class<Enum<?>> _enumClass;
    private final SerializableString[] _textual;
    private final Enum<?>[] _values;

    private EnumValues(Class<Enum<?>> enumClass, SerializableString[] textual) {
        this._enumClass = enumClass;
        this._values = (Enum[]) enumClass.getEnumConstants();
        this._textual = textual;
    }

    public static EnumValues constructFromName(MapperConfig<?> config, Class<Enum<?>> enumClass) {
        Class<? extends Enum<?>> enumCls = ClassUtil.findEnumType(enumClass);
        Enum<?>[] enumValues = (Enum[]) enumCls.getEnumConstants();
        if (enumValues == null) {
            throw new IllegalArgumentException("Can not determine enum constants for Class " + enumClass.getName());
        }
        String[] names = config.getAnnotationIntrospector().findEnumValues(enumCls, enumValues, new String[enumValues.length]);
        SerializableString[] textual = new SerializableString[enumValues.length];
        int len = enumValues.length;
        for (int i = 0; i < len; i++) {
            Enum<?> en = enumValues[i];
            String name = names[i];
            if (name == null) {
                name = en.name();
            }
            textual[en.ordinal()] = config.compileString(name);
        }
        return new EnumValues(enumClass, textual);
    }

    public SerializableString serializedValueFor(Enum<?> key) {
        return this._textual[key.ordinal()];
    }

    public Collection<SerializableString> values() {
        return Arrays.asList(this._textual);
    }

    public List<Enum<?>> enums() {
        return Arrays.asList(this._values);
    }

    public Class<Enum<?>> getEnumClass() {
        return this._enumClass;
    }
}
