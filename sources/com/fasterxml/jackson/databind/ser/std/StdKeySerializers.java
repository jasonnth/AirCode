package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.util.EnumValues;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class StdKeySerializers {
    protected static final JsonSerializer<Object> DEFAULT_KEY_SERIALIZER = new StdKeySerializer();
    protected static final JsonSerializer<Object> DEFAULT_STRING_SERIALIZER = new StringKeySerializer();

    public static class Default extends StdSerializer<Object> {
        protected final int _typeId;

        public Default(int typeId, Class<?> type) {
            super(type, false);
            this._typeId = typeId;
        }

        public void serialize(Object value, JsonGenerator g, SerializerProvider provider) throws IOException {
            String key;
            switch (this._typeId) {
                case 1:
                    provider.defaultSerializeDateKey((Date) value, g);
                    return;
                case 2:
                    provider.defaultSerializeDateKey(((Calendar) value).getTimeInMillis(), g);
                    return;
                case 3:
                    g.writeFieldName(((Class) value).getName());
                    return;
                case 4:
                    if (provider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                        key = value.toString();
                    } else {
                        Enum<?> e = (Enum) value;
                        if (provider.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
                            key = String.valueOf(e.ordinal());
                        } else {
                            key = e.name();
                        }
                    }
                    g.writeFieldName(key);
                    return;
                default:
                    g.writeFieldName(value.toString());
                    return;
            }
        }
    }

    public static class Dynamic extends StdSerializer<Object> {
        protected transient PropertySerializerMap _dynamicSerializers = PropertySerializerMap.emptyForProperties();

        public Dynamic() {
            super(String.class, false);
        }

        /* access modifiers changed from: 0000 */
        public Object readResolve() {
            this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
            return this;
        }

        public void serialize(Object value, JsonGenerator g, SerializerProvider provider) throws IOException {
            Class<?> cls = value.getClass();
            PropertySerializerMap m = this._dynamicSerializers;
            JsonSerializer<Object> ser = m.serializerFor(cls);
            if (ser == null) {
                ser = _findAndAddDynamic(m, cls, provider);
            }
            ser.serialize(value, g, provider);
        }

        public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
            visitStringFormat(visitor, typeHint);
        }

        /* access modifiers changed from: protected */
        public JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
            SerializerAndMapResult result = map.findAndAddKeySerializer(type, provider, null);
            if (map != result.map) {
                this._dynamicSerializers = result.map;
            }
            return result.serializer;
        }
    }

    public static class EnumKeySerializer extends StdSerializer<Object> {
        protected final EnumValues _values;

        protected EnumKeySerializer(Class<?> enumType, EnumValues values) {
            super(enumType, false);
            this._values = values;
        }

        public static EnumKeySerializer construct(Class<?> enumType, EnumValues enumValues) {
            return new EnumKeySerializer(enumType, enumValues);
        }

        public void serialize(Object value, JsonGenerator g, SerializerProvider serializers) throws IOException {
            if (serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_TO_STRING)) {
                g.writeFieldName(value.toString());
                return;
            }
            Enum<?> en = (Enum) value;
            if (serializers.isEnabled(SerializationFeature.WRITE_ENUMS_USING_INDEX)) {
                g.writeFieldName(String.valueOf(en.ordinal()));
            } else {
                g.writeFieldName(this._values.serializedValueFor(en));
            }
        }
    }

    public static class StringKeySerializer extends StdSerializer<Object> {
        public StringKeySerializer() {
            super(String.class, false);
        }

        public void serialize(Object value, JsonGenerator g, SerializerProvider provider) throws IOException {
            g.writeFieldName((String) value);
        }
    }

    public static JsonSerializer<Object> getStdKeySerializer(SerializationConfig config, Class<?> rawKeyType, boolean useDefault) {
        if (rawKeyType == null || rawKeyType == Object.class) {
            return new Dynamic();
        }
        if (rawKeyType == String.class) {
            return DEFAULT_STRING_SERIALIZER;
        }
        if (rawKeyType.isPrimitive() || Number.class.isAssignableFrom(rawKeyType)) {
            return new Default(5, rawKeyType);
        }
        if (rawKeyType == Class.class) {
            return new Default(3, rawKeyType);
        }
        if (Date.class.isAssignableFrom(rawKeyType)) {
            return new Default(1, rawKeyType);
        }
        if (Calendar.class.isAssignableFrom(rawKeyType)) {
            return new Default(2, rawKeyType);
        }
        if (rawKeyType == UUID.class) {
            return new Default(5, rawKeyType);
        }
        if (useDefault) {
            return DEFAULT_KEY_SERIALIZER;
        }
        return null;
    }

    public static JsonSerializer<Object> getFallbackKeySerializer(SerializationConfig config, Class<?> rawKeyType) {
        if (rawKeyType != null) {
            if (rawKeyType == Enum.class) {
                return new Dynamic();
            }
            if (rawKeyType.isEnum()) {
                return EnumKeySerializer.construct(rawKeyType, EnumValues.constructFromName(config, rawKeyType));
            }
        }
        return DEFAULT_KEY_SERIALIZER;
    }
}
