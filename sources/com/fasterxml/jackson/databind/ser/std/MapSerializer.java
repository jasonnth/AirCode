package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties.Value;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonMapFormatVisitor;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

@JacksonStdImpl
public class MapSerializer extends ContainerSerializer<Map<?, ?>> implements ContextualSerializer {
    protected static final JavaType UNSPECIFIED_TYPE = TypeFactory.unknownType();
    protected PropertySerializerMap _dynamicValueSerializers;
    protected final Object _filterId;
    protected final Set<String> _ignoredEntries;
    protected JsonSerializer<Object> _keySerializer;
    protected final JavaType _keyType;
    protected final BeanProperty _property;
    protected final boolean _sortKeys;
    protected final Object _suppressableValue;
    protected JsonSerializer<Object> _valueSerializer;
    protected final JavaType _valueType;
    protected final boolean _valueTypeIsStatic;
    protected final TypeSerializer _valueTypeSerializer;

    protected MapSerializer(Set<String> ignoredEntries, JavaType keyType, JavaType valueType, boolean valueTypeIsStatic, TypeSerializer vts, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer) {
        super(Map.class, false);
        if (ignoredEntries == null || ignoredEntries.isEmpty()) {
            ignoredEntries = null;
        }
        this._ignoredEntries = ignoredEntries;
        this._keyType = keyType;
        this._valueType = valueType;
        this._valueTypeIsStatic = valueTypeIsStatic;
        this._valueTypeSerializer = vts;
        this._keySerializer = keySerializer;
        this._valueSerializer = valueSerializer;
        this._dynamicValueSerializers = PropertySerializerMap.emptyForProperties();
        this._property = null;
        this._filterId = null;
        this._sortKeys = false;
        this._suppressableValue = null;
    }

    /* access modifiers changed from: protected */
    public void _ensureOverride() {
        if (getClass() != MapSerializer.class) {
            throw new IllegalStateException("Missing override in class " + getClass().getName());
        }
    }

    protected MapSerializer(MapSerializer src, BeanProperty property, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer, Set<String> ignoredEntries) {
        super(Map.class, false);
        if (ignoredEntries == null || ignoredEntries.isEmpty()) {
            ignoredEntries = null;
        }
        this._ignoredEntries = ignoredEntries;
        this._keyType = src._keyType;
        this._valueType = src._valueType;
        this._valueTypeIsStatic = src._valueTypeIsStatic;
        this._valueTypeSerializer = src._valueTypeSerializer;
        this._keySerializer = keySerializer;
        this._valueSerializer = valueSerializer;
        this._dynamicValueSerializers = src._dynamicValueSerializers;
        this._property = property;
        this._filterId = src._filterId;
        this._sortKeys = src._sortKeys;
        this._suppressableValue = src._suppressableValue;
    }

    protected MapSerializer(MapSerializer src, TypeSerializer vts, Object suppressableValue) {
        super(Map.class, false);
        this._ignoredEntries = src._ignoredEntries;
        this._keyType = src._keyType;
        this._valueType = src._valueType;
        this._valueTypeIsStatic = src._valueTypeIsStatic;
        this._valueTypeSerializer = vts;
        this._keySerializer = src._keySerializer;
        this._valueSerializer = src._valueSerializer;
        this._dynamicValueSerializers = src._dynamicValueSerializers;
        this._property = src._property;
        this._filterId = src._filterId;
        this._sortKeys = src._sortKeys;
        if (suppressableValue == Include.NON_ABSENT) {
            suppressableValue = this._valueType.isReferenceType() ? Include.NON_EMPTY : Include.NON_NULL;
        }
        this._suppressableValue = suppressableValue;
    }

    protected MapSerializer(MapSerializer src, Object filterId, boolean sortKeys) {
        super(Map.class, false);
        this._ignoredEntries = src._ignoredEntries;
        this._keyType = src._keyType;
        this._valueType = src._valueType;
        this._valueTypeIsStatic = src._valueTypeIsStatic;
        this._valueTypeSerializer = src._valueTypeSerializer;
        this._keySerializer = src._keySerializer;
        this._valueSerializer = src._valueSerializer;
        this._dynamicValueSerializers = src._dynamicValueSerializers;
        this._property = src._property;
        this._filterId = filterId;
        this._sortKeys = sortKeys;
        this._suppressableValue = src._suppressableValue;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public MapSerializer _withValueTypeSerializer(TypeSerializer vts) {
        if (this._valueTypeSerializer == vts) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, vts, (Object) null);
    }

    public MapSerializer withResolved(BeanProperty property, JsonSerializer<?> keySerializer, JsonSerializer<?> valueSerializer, Set<String> ignored, boolean sortKeys) {
        _ensureOverride();
        MapSerializer ser = new MapSerializer(this, property, keySerializer, valueSerializer, ignored);
        if (sortKeys != ser._sortKeys) {
            return new MapSerializer(ser, this._filterId, sortKeys);
        }
        return ser;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public MapSerializer withFilterId(Object filterId) {
        if (this._filterId == filterId) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, filterId, this._sortKeys);
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public MapSerializer withContentInclusion(Object suppressableValue) {
        if (suppressableValue == this._suppressableValue) {
            return this;
        }
        _ensureOverride();
        return new MapSerializer(this, this._valueTypeSerializer, suppressableValue);
    }

    public static MapSerializer construct(Set<String> ignoredEntries, JavaType mapType, boolean staticValueType, TypeSerializer vts, JsonSerializer<Object> keySerializer, JsonSerializer<Object> valueSerializer, Object filterId) {
        JavaType keyType;
        JavaType valueType;
        if (mapType == null) {
            valueType = UNSPECIFIED_TYPE;
            keyType = valueType;
        } else {
            keyType = mapType.getKeyType();
            valueType = mapType.getContentType();
        }
        if (!staticValueType) {
            staticValueType = valueType != null && valueType.isFinal();
        } else if (valueType.getRawClass() == Object.class) {
            staticValueType = false;
        }
        MapSerializer ser = new MapSerializer(ignoredEntries, keyType, valueType, staticValueType, vts, keySerializer, valueSerializer);
        if (filterId != null) {
            return ser.withFilterId(filterId);
        }
        return ser;
    }

    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        JsonSerializer<?> keySer;
        JsonSerializer<Object> jsonSerializer = null;
        JsonSerializer<Object> jsonSerializer2 = null;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember propertyAcc = property == null ? null : property.getMember();
        Object suppressableValue = this._suppressableValue;
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
        Include incl = findIncludeOverrides(provider, property, Map.class).getContentInclusion();
        if (!(incl == null || incl == Include.USE_DEFAULTS)) {
            suppressableValue = incl;
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
        Set<String> ignored = this._ignoredEntries;
        boolean sortKeys = false;
        if (!(intr == null || propertyAcc == null)) {
            Value ignorals = intr.findPropertyIgnorals(propertyAcc);
            if (ignorals != null) {
                Set<String> newIgnored = ignorals.findIgnoredForSerialization();
                if (newIgnored != null && !newIgnored.isEmpty()) {
                    ignored = ignored == null ? new HashSet<>() : new HashSet<>(ignored);
                    for (String str : newIgnored) {
                        ignored.add(str);
                    }
                }
            }
            Boolean b = intr.findSerializationSortAlphabetically(propertyAcc);
            sortKeys = b != null && b.booleanValue();
        }
        JsonFormat.Value format = findFormatOverrides(provider, property, Map.class);
        if (format != null) {
            Boolean B = format.getFeature(Feature.WRITE_SORTED_MAP_ENTRIES);
            if (B != null) {
                sortKeys = B.booleanValue();
            }
        }
        MapSerializer mser = withResolved(property, keySer, ser, ignored, sortKeys);
        if (suppressableValue != this._suppressableValue) {
            mser = mser.withContentInclusion(suppressableValue);
        }
        if (property == null) {
            return mser;
        }
        AnnotatedMember m = property.getMember();
        if (m == null) {
            return mser;
        }
        Object filterId = intr.findFilterId(m);
        if (filterId != null) {
            return mser.withFilterId(filterId);
        }
        return mser;
    }

    public boolean isEmpty(SerializerProvider prov, Map<?, ?> value) {
        if (value == null || value.isEmpty()) {
            return true;
        }
        Object supp = this._suppressableValue;
        if (supp == null || supp == Include.ALWAYS) {
            return false;
        }
        JsonSerializer<Object> valueSer = this._valueSerializer;
        if (valueSer != null) {
            for (Object elemValue : value.values()) {
                if (elemValue != null && !valueSer.isEmpty(prov, elemValue)) {
                    return false;
                }
            }
            return true;
        }
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        for (Object elemValue2 : value.values()) {
            if (elemValue2 != null) {
                Class<?> cc = elemValue2.getClass();
                JsonSerializer<Object> valueSer2 = serializers.serializerFor(cc);
                if (valueSer2 == null) {
                    try {
                        valueSer2 = _findAndAddDynamic(serializers, cc, prov);
                        serializers = this._dynamicValueSerializers;
                    } catch (JsonMappingException e) {
                        return false;
                    }
                }
                if (!valueSer2.isEmpty(prov, elemValue2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean hasSingleElement(Map<?, ?> value) {
        return value.size() == 1;
    }

    public void serialize(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject(value);
        if (!value.isEmpty()) {
            Object suppressableValue = this._suppressableValue;
            if (suppressableValue == Include.ALWAYS) {
                suppressableValue = null;
            } else if (suppressableValue == null && !provider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
                suppressableValue = Include.NON_NULL;
            }
            if (this._sortKeys || provider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                value = _orderEntries(value, gen, provider, suppressableValue);
            }
            if (this._filterId != null) {
                PropertyFilter pf = findPropertyFilter(provider, this._filterId, value);
                if (pf != null) {
                    serializeFilteredFields(value, gen, provider, pf, suppressableValue);
                }
            }
            if (suppressableValue != null) {
                serializeOptionalFields(value, gen, provider, suppressableValue);
            } else if (this._valueSerializer != null) {
                serializeFieldsUsing(value, gen, provider, this._valueSerializer);
            } else {
                serializeFields(value, gen, provider);
            }
        }
        gen.writeEndObject();
    }

    public void serializeWithType(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForObject(value, gen);
        gen.setCurrentValue(value);
        if (!value.isEmpty()) {
            Object suppressableValue = this._suppressableValue;
            if (suppressableValue == Include.ALWAYS) {
                suppressableValue = null;
            } else if (suppressableValue == null && !provider.isEnabled(SerializationFeature.WRITE_NULL_MAP_VALUES)) {
                suppressableValue = Include.NON_NULL;
            }
            if (this._sortKeys || provider.isEnabled(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)) {
                value = _orderEntries(value, gen, provider, suppressableValue);
            }
            if (this._filterId != null) {
                PropertyFilter pf = findPropertyFilter(provider, this._filterId, value);
                if (pf != null) {
                    serializeFilteredFields(value, gen, provider, pf, suppressableValue);
                }
            }
            if (suppressableValue != null) {
                serializeOptionalFields(value, gen, provider, suppressableValue);
            } else if (this._valueSerializer != null) {
                serializeFieldsUsing(value, gen, provider, this._valueSerializer);
            } else {
                serializeFields(value, gen, provider);
            }
        }
        typeSer.writeTypeSuffixForObject(value, gen);
    }

    public void serializeFields(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(value, gen, provider, null);
            return;
        }
        JsonSerializer<Object> keySerializer = this._keySerializer;
        Set<String> ignored = this._ignoredEntries;
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        for (Entry<?, ?> entry : value.entrySet()) {
            Object valueElem = entry.getValue();
            Object keyElem = entry.getKey();
            if (keyElem == null) {
                provider.findNullKeySerializer(this._keyType, this._property).serialize(null, gen, provider);
            } else if (ignored == null || !ignored.contains(keyElem)) {
                keySerializer.serialize(keyElem, gen, provider);
            }
            if (valueElem == null) {
                provider.defaultSerializeNull(gen);
            } else {
                JsonSerializer<Object> serializer = this._valueSerializer;
                if (serializer == null) {
                    Class<?> cc = valueElem.getClass();
                    serializer = serializers.serializerFor(cc);
                    if (serializer == null) {
                        if (this._valueType.hasGenericTypes()) {
                            serializer = _findAndAddDynamic(serializers, provider.constructSpecializedType(this._valueType, cc), provider);
                        } else {
                            serializer = _findAndAddDynamic(serializers, cc, provider);
                        }
                        serializers = this._dynamicValueSerializers;
                    }
                }
                try {
                    serializer.serialize(valueElem, gen, provider);
                } catch (Exception e) {
                    wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
                }
            }
        }
    }

    public void serializeOptionalFields(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider, Object suppressableValue) throws IOException {
        JsonSerializer<Object> keySerializer;
        JsonSerializer<Object> valueSer;
        if (this._valueTypeSerializer != null) {
            serializeTypedFields(value, gen, provider, suppressableValue);
            return;
        }
        Set<String> ignored = this._ignoredEntries;
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        for (Entry<?, ?> entry : value.entrySet()) {
            Object keyElem = entry.getKey();
            if (keyElem == null) {
                keySerializer = provider.findNullKeySerializer(this._keyType, this._property);
            } else if (ignored == null || !ignored.contains(keyElem)) {
                keySerializer = this._keySerializer;
            }
            Object valueElem = entry.getValue();
            if (valueElem != null) {
                valueSer = this._valueSerializer;
                if (valueSer == null) {
                    Class<?> cc = valueElem.getClass();
                    valueSer = serializers.serializerFor(cc);
                    if (valueSer == null) {
                        if (this._valueType.hasGenericTypes()) {
                            valueSer = _findAndAddDynamic(serializers, provider.constructSpecializedType(this._valueType, cc), provider);
                        } else {
                            valueSer = _findAndAddDynamic(serializers, cc, provider);
                        }
                        serializers = this._dynamicValueSerializers;
                    }
                }
                if (suppressableValue == Include.NON_EMPTY && valueSer.isEmpty(provider, valueElem)) {
                }
            } else if (suppressableValue == null) {
                valueSer = provider.getDefaultNullValueSerializer();
            }
            try {
                keySerializer.serialize(keyElem, gen, provider);
                valueSer.serialize(valueElem, gen, provider);
            } catch (Exception e) {
                wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
            }
        }
    }

    public void serializeFieldsUsing(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider, JsonSerializer<Object> ser) throws IOException {
        JsonSerializer<Object> keySerializer = this._keySerializer;
        Set<String> ignored = this._ignoredEntries;
        TypeSerializer typeSer = this._valueTypeSerializer;
        for (Entry<?, ?> entry : value.entrySet()) {
            Object keyElem = entry.getKey();
            if (ignored == null || !ignored.contains(keyElem)) {
                if (keyElem == null) {
                    provider.findNullKeySerializer(this._keyType, this._property).serialize(null, gen, provider);
                } else {
                    keySerializer.serialize(keyElem, gen, provider);
                }
                Object valueElem = entry.getValue();
                if (valueElem == null) {
                    provider.defaultSerializeNull(gen);
                } else if (typeSer == null) {
                    try {
                        ser.serialize(valueElem, gen, provider);
                    } catch (Exception e) {
                        wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
                    }
                } else {
                    ser.serializeWithType(valueElem, gen, provider, typeSer);
                }
            }
        }
    }

    public void serializeFilteredFields(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider, PropertyFilter filter, Object suppressableValue) throws IOException {
        JsonSerializer<Object> keySerializer;
        JsonSerializer<Object> valueSer;
        Set<String> ignored = this._ignoredEntries;
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        MapProperty prop = new MapProperty(this._valueTypeSerializer, this._property);
        for (Entry<?, ?> entry : value.entrySet()) {
            Object keyElem = entry.getKey();
            if (ignored == null || !ignored.contains(keyElem)) {
                if (keyElem == null) {
                    keySerializer = provider.findNullKeySerializer(this._keyType, this._property);
                } else {
                    keySerializer = this._keySerializer;
                }
                Object valueElem = entry.getValue();
                if (valueElem != null) {
                    valueSer = this._valueSerializer;
                    if (valueSer == null) {
                        Class<?> cc = valueElem.getClass();
                        valueSer = serializers.serializerFor(cc);
                        if (valueSer == null) {
                            if (this._valueType.hasGenericTypes()) {
                                valueSer = _findAndAddDynamic(serializers, provider.constructSpecializedType(this._valueType, cc), provider);
                            } else {
                                valueSer = _findAndAddDynamic(serializers, cc, provider);
                            }
                            serializers = this._dynamicValueSerializers;
                        }
                    }
                    if (suppressableValue == Include.NON_EMPTY && valueSer.isEmpty(provider, valueElem)) {
                    }
                } else if (suppressableValue == null) {
                    valueSer = provider.getDefaultNullValueSerializer();
                }
                prop.reset(keyElem, keySerializer, valueSer);
                try {
                    filter.serializeAsField(valueElem, gen, provider, prop);
                } catch (Exception e) {
                    wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
                }
            }
        }
    }

    public void serializeTypedFields(Map<?, ?> value, JsonGenerator gen, SerializerProvider provider, Object suppressableValue) throws IOException {
        JsonSerializer<Object> keySerializer;
        JsonSerializer<Object> valueSer;
        Set<String> ignored = this._ignoredEntries;
        PropertySerializerMap serializers = this._dynamicValueSerializers;
        for (Entry<?, ?> entry : value.entrySet()) {
            Object keyElem = entry.getKey();
            if (keyElem == null) {
                keySerializer = provider.findNullKeySerializer(this._keyType, this._property);
            } else if (ignored == null || !ignored.contains(keyElem)) {
                keySerializer = this._keySerializer;
            }
            Object valueElem = entry.getValue();
            if (valueElem != null) {
                JsonSerializer<Object> jsonSerializer = this._valueSerializer;
                Class<?> cc = valueElem.getClass();
                valueSer = serializers.serializerFor(cc);
                if (valueSer == null) {
                    if (this._valueType.hasGenericTypes()) {
                        valueSer = _findAndAddDynamic(serializers, provider.constructSpecializedType(this._valueType, cc), provider);
                    } else {
                        valueSer = _findAndAddDynamic(serializers, cc, provider);
                    }
                    serializers = this._dynamicValueSerializers;
                }
                if (suppressableValue == Include.NON_EMPTY && valueSer.isEmpty(provider, valueElem)) {
                }
            } else if (suppressableValue == null) {
                valueSer = provider.getDefaultNullValueSerializer();
            }
            keySerializer.serialize(keyElem, gen, provider);
            try {
                valueSer.serializeWithType(valueElem, gen, provider, this._valueTypeSerializer);
            } catch (Exception e) {
                wrapAndThrow(provider, (Throwable) e, (Object) value, "" + keyElem);
            }
        }
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) {
        return createSchemaNode("object", true);
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        JsonMapFormatVisitor v2 = visitor == null ? null : visitor.expectMapFormat(typeHint);
        if (v2 != null) {
            v2.keyFormat(this._keySerializer, this._keyType);
            JsonSerializer<Object> jsonSerializer = this._valueSerializer;
            if (jsonSerializer == null) {
                jsonSerializer = _findAndAddDynamic(this._dynamicValueSerializers, this._valueType, visitor.getProvider());
            }
            v2.valueFormat(jsonSerializer, this._valueType);
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

    /* access modifiers changed from: protected */
    public Map<?, ?> _orderEntries(Map<?, ?> input, JsonGenerator gen, SerializerProvider provider, Object suppressableValue) throws IOException {
        if (input instanceof SortedMap) {
            return input;
        }
        if (!_hasNullKey(input)) {
            return new TreeMap<>(input);
        }
        TreeMap<Object, Object> result = new TreeMap<>();
        for (Entry<?, ?> entry : input.entrySet()) {
            Object key = entry.getKey();
            if (key == null) {
                _writeNullKeyedEntry(gen, provider, suppressableValue, entry.getValue());
            } else {
                result.put(key, entry.getValue());
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public boolean _hasNullKey(Map<?, ?> input) {
        return (input instanceof HashMap) && input.containsKey(null);
    }

    /* access modifiers changed from: protected */
    public void _writeNullKeyedEntry(JsonGenerator gen, SerializerProvider provider, Object suppressableValue, Object value) throws IOException {
        JsonSerializer<Object> valueSer;
        JsonSerializer<Object> keySerializer = provider.findNullKeySerializer(this._keyType, this._property);
        if (value != null) {
            valueSer = this._valueSerializer;
            if (valueSer == null) {
                Class<?> cc = value.getClass();
                valueSer = this._dynamicValueSerializers.serializerFor(cc);
                if (valueSer == null) {
                    if (this._valueType.hasGenericTypes()) {
                        valueSer = _findAndAddDynamic(this._dynamicValueSerializers, provider.constructSpecializedType(this._valueType, cc), provider);
                    } else {
                        valueSer = _findAndAddDynamic(this._dynamicValueSerializers, cc, provider);
                    }
                }
            }
            if (suppressableValue == Include.NON_EMPTY && valueSer.isEmpty(provider, value)) {
                return;
            }
        } else if (suppressableValue == null) {
            valueSer = provider.getDefaultNullValueSerializer();
        } else {
            return;
        }
        try {
            keySerializer.serialize(null, gen, provider);
            valueSer.serialize(value, gen, provider);
        } catch (Exception e) {
            wrapAndThrow(provider, (Throwable) e, value, "");
        }
    }
}
