package com.fasterxml.jackson.databind.ser.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

@JacksonStdImpl
public class MapEntrySerializer extends ContainerSerializer<Entry<?, ?>> implements ContextualSerializer {
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final JavaType _entryType;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    public MapEntrySerializer(JavaType type, JavaType keyType, JavaType valueType, boolean staticTyping, TypeSerializer vts, BeanProperty property) {
        super(type);
        this._entryType = type;
        this._keyType = keyType;
        this._valueType = valueType;
        this._valueTypeIsStatic = staticTyping;
        this._valueTypeSerializer = vts;
        this._property = property;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
    }

    protected MapEntrySerializer(MapEntrySerializer src, BeanProperty property, TypeSerializer vts, JsonSerializer<?> keySer, JsonSerializer<?> valueSer) {
        super(Map.class, false);
        this._entryType = src._entryType;
        this._keyType = src._keyType;
        this._valueType = src._valueType;
        this._valueTypeIsStatic = src._valueTypeIsStatic;
        this._valueTypeSerializer = src._valueTypeSerializer;
        this._keySerializer = keySer;
        this._valueSerializer = valueSer;
        this._dynamicValueSerializers = src._dynamicValueSerializers;
        this._property = src._property;
    }

    public ContainerSerializer<?> _withValueTypeSerializer(TypeSerializer vts) {
        return new MapEntrySerializer(this, this._property, vts, this._keySerializer, this._valueSerializer);
    }

    public MapEntrySerializer withResolved(BeanProperty property, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer) {
        return new MapEntrySerializer(this, property, this._valueTypeSerializer, keySerializer, valueSerializer);
    }

    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonSerializer<?> keySer;
        JsonSerializer<Object> jsonSerializer = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember propertyAcc = property == null ? null : property.getMember();
        if (!(propertyAcc == null || intr == null)) {
            Object serDef = intr.findKeySerializer(propertyAcc);
            if (serDef != null) {
                jsonSerializer2 = provider.serializerInstance(propertyAcc, serDef);
            }
            Object serDef2 = intr.findContentSerializer(propertyAcc);
            if (serDef2 != null) {
                jsonSerializer = provider.serializerInstance(propertyAcc, serDef2);
            }
        }
        if (jsonSerializer == null) {
            jsonSerializer = this._valueSerializer;
        }
        JsonSerializer<?> ser = findConvertingContentSerializer(provider, property, jsonSerializer);
        if (ser != null) {
            ser = provider.handleSecondaryContextualization(ser, property);
        } else if (this._valueTypeIsStatic && !this._valueType.isJavaLangObject()) {
            ser = provider.findValueSerializer(this._valueType, property);
        }
        if (jsonSerializer2 == null) {
            jsonSerializer2 = this._keySerializer;
        }
        if (jsonSerializer2 == null) {
            keySer = provider.findKeySerializer(this._keyType, property);
        } else {
            keySer = provider.handleSecondaryContextualization(jsonSerializer2, property);
        }
        return withResolved(property, keySer, ser);
    }

    public boolean hasSingleElement(Entry<?, ?> entry) {
        return true;
    }

    public boolean isEmpty(SerializerProvider prov, Entry<?, ?> value) {
        return value == null;
    }

    public void serialize(Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject(value);
        if (this._valueSerializer != null) {
            serializeUsing(value, gen, provider, this._valueSerializer);
        } else {
            serializeDynamic(value, gen, provider);
        }
        gen.writeEndObject();
    }

    public void serializeWithType(Entry<?, ?> value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForObject(value, gen);
        gen.setCurrentValue(value);
        if (this._valueSerializer != null) {
            serializeUsing(value, gen, provider, this._valueSerializer);
        } else {
            serializeDynamic(value, gen, provider);
        }
        typeSer.writeTypeSuffixForObject(value, gen);
    }

    /* access modifiers changed from: protected */
    public void serializeDynamic(Entry<?, ?> value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        JsonSerializer<Object> keySerializer = this._keySerializer;
        boolean skipNulls = !provider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        TypeSerializer vts = this._valueTypeSerializer;
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        Object valueElem = value.getValue();
        Object keyElem = value.getKey();
        if (keyElem == null) {
            provider.findNullKeySerializer(this._keyType, this._property).serialize(null, jgen, provider);
        } else if (!skipNulls || valueElem != null) {
            keySerializer.serialize(keyElem, jgen, provider);
        } else {
            return;
        }
        if (valueElem == null) {
            provider.defaultSerializeNull(jgen);
            return;
        }
        Class<?> cc = valueElem.getClass();
        JsonSerializer<Object> ser = serializers.serializerFor(cc);
        if (ser == null) {
            if (this._valueType.hasGenericTypes()) {
                ser = _findAndAddDynamic(serializers, provider.constructSpecializedType(this._valueType, cc), provider);
            } else {
                ser = _findAndAddDynamic(serializers, cc, provider);
            }
            PropertySerializerMap serializers2 = this._dynamicValueSerializers;
        }
        if (vts == null) {
            try {
                ser.serialize(valueElem, jgen, provider);
            } catch (Exception e) {
                wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
            }
        } else {
            ser.serializeWithType(valueElem, jgen, provider, vts);
        }
    }

    /* access modifiers changed from: protected */
    public void serializeUsing(Entry<?, ?> value, JsonGenerator jgen, SerializerProvider provider, JsonSerializer<Object> ser) throws IOException, JsonGenerationException {
        JsonSerializer<Object> keySerializer = this._keySerializer;
        TypeSerializer vts = this._valueTypeSerializer;
        boolean skipNulls = !provider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES);
        Object valueElem = value.getValue();
        Object keyElem = value.getKey();
        if (keyElem == null) {
            provider.findNullKeySerializer(this._keyType, this._property).serialize(null, jgen, provider);
        } else if (!skipNulls || valueElem != null) {
            keySerializer.serialize(keyElem, jgen, provider);
        } else {
            return;
        }
        if (valueElem == null) {
            provider.defaultSerializeNull(jgen);
        } else if (vts == null) {
            try {
                ser.serialize(valueElem, jgen, provider);
            } catch (Exception e) {
                wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
            }
        } else {
            ser.serializeWithType(valueElem, jgen, provider, vts);
        }
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
        SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
        if (map != result.map) {
            this._dynamicValueSerializers = result.map;
        }
        return result.serializer;
    }

    /* access modifiers changed from: protected */
    public final JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, JavaType type, SerializerProvider provider) throws JsonMappingException {
        SerializerAndMapResult result = map.findAndAddSecondarySerializer(type, provider, this._property);
        if (map != result.map) {
            this._dynamicValueSerializers = result.map;
        }
        return result.serializer;
    }
}
