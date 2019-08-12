package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonIntegerFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNumberFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonStringFormatVisitor;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonValueFormat;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

public abstract class StdSerializer<T> extends JsonSerializer<T> implements JsonFormatVisitable, SchemaAware, Serializable {
    private static final Object CONVERTING_CONTENT_CONVERTER_LOCK = new Object();
    private static final long serialVersionUID = 1;
    protected final Class<T> _handledType;

    public abstract void serialize(T t, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException;

    protected StdSerializer(Class<T> t) {
        this._handledType = t;
    }

    protected StdSerializer(JavaType type) {
        this._handledType = type.getRawClass();
    }

    protected StdSerializer(Class<?> t, boolean dummy) {
        this._handledType = t;
    }

    protected StdSerializer(StdSerializer<?> src) {
        this._handledType = src._handledType;
    }

    public Class<T> handledType() {
        return this._handledType;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        visitor.expectAnyFormat(typeHint);
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        return createSchemaNode("string");
    }

    public JsonNode getSchema(SerializerProvider provider, Type typeHint, boolean isOptional) throws JsonMappingException {
        ObjectNode schema = (ObjectNode) getSchema(provider, typeHint);
        if (!isOptional) {
            schema.put("required", !isOptional);
        }
        return schema;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createObjectNode() {
        return JsonNodeFactory.instance.objectNode();
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String type) {
        ObjectNode schema = createObjectNode();
        schema.put("type", type);
        return schema;
    }

    /* access modifiers changed from: protected */
    public ObjectNode createSchemaNode(String type, boolean isOptional) {
        ObjectNode schema = createSchemaNode(type);
        if (!isOptional) {
            schema.put("required", !isOptional);
        }
        return schema;
    }

    /* access modifiers changed from: protected */
    public void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        if (visitor != null) {
            visitor.expectStringFormat(typeHint);
        }
    }

    /* access modifiers changed from: protected */
    public void visitStringFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonValueFormat format) throws JsonMappingException {
        if (visitor != null) {
            JsonStringFormatVisitor v2 = visitor.expectStringFormat(typeHint);
            if (v2 != null) {
                v2.format(format);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, NumberType numberType) throws JsonMappingException {
        if (visitor != null) {
            JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
            if (v2 != null && numberType != null) {
                v2.numberType(numberType);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitIntFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, NumberType numberType, JsonValueFormat format) throws JsonMappingException {
        if (visitor != null) {
            JsonIntegerFormatVisitor v2 = visitor.expectIntegerFormat(typeHint);
            if (v2 != null) {
                if (numberType != null) {
                    v2.numberType(numberType);
                }
                if (format != null) {
                    v2.format(format);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitFloatFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, NumberType numberType) throws JsonMappingException {
        if (visitor != null) {
            JsonNumberFormatVisitor v2 = visitor.expectNumberFormat(typeHint);
            if (v2 != null) {
                v2.numberType(numberType);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonSerializer<?> itemSerializer, JavaType itemType) throws JsonMappingException {
        if (visitor != null) {
            JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
            if (v2 != null && itemSerializer != null) {
                v2.itemsFormat(itemSerializer, itemType);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void visitArrayFormat(JsonFormatVisitorWrapper visitor, JavaType typeHint, JsonFormatTypes itemType) throws JsonMappingException {
        if (visitor != null) {
            JsonArrayFormatVisitor v2 = visitor.expectArrayFormat(typeHint);
            if (v2 != null) {
                v2.itemsFormat(itemType);
            }
        }
    }

    public void wrapAndThrow(SerializerProvider provider, Throwable t, Object bean, String fieldName) throws IOException {
        while ((t instanceof InvocationTargetException) && t.getCause() != null) {
            t = t.getCause();
        }
        if (t instanceof Error) {
            throw ((Error) t);
        }
        boolean wrap = provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (t instanceof IOException) {
            if (!wrap || !(t instanceof JsonMappingException)) {
                throw ((IOException) t);
            }
        } else if (!wrap && (t instanceof RuntimeException)) {
            throw ((RuntimeException) t);
        }
        throw JsonMappingException.wrapWithPath(t, bean, fieldName);
    }

    public void wrapAndThrow(SerializerProvider provider, Throwable t, Object bean, int index) throws IOException {
        while ((t instanceof InvocationTargetException) && t.getCause() != null) {
            t = t.getCause();
        }
        if (t instanceof Error) {
            throw ((Error) t);
        }
        boolean wrap = provider == null || provider.isEnabled(SerializationFeature.WRAP_EXCEPTIONS);
        if (t instanceof IOException) {
            if (!wrap || !(t instanceof JsonMappingException)) {
                throw ((IOException) t);
            }
        } else if (!wrap && (t instanceof RuntimeException)) {
            throw ((RuntimeException) t);
        }
        throw JsonMappingException.wrapWithPath(t, bean, index);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findConvertingContentSerializer(SerializerProvider provider, BeanProperty prop, JsonSerializer<?> existingSerializer) throws JsonMappingException {
        Object ob = provider.getAttribute(CONVERTING_CONTENT_CONVERTER_LOCK);
        if (ob != null && ob == Boolean.TRUE) {
            return existingSerializer;
        }
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if (!(intr == null || prop == null)) {
            AnnotatedMember m = prop.getMember();
            if (m != null) {
                provider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, Boolean.TRUE);
                try {
                    Object convDef = intr.findSerializationContentConverter(m);
                    if (convDef != null) {
                        Converter<Object, Object> conv = provider.converterInstance(prop.getMember(), convDef);
                        JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
                        if (existingSerializer == null && !delegateType.isJavaLangObject()) {
                            existingSerializer = provider.findValueSerializer(delegateType);
                        }
                        return new StdDelegatingSerializer(conv, delegateType, existingSerializer);
                    }
                } finally {
                    provider.setAttribute(CONVERTING_CONTENT_CONVERTER_LOCK, null);
                }
            }
        }
        return existingSerializer;
    }

    /* access modifiers changed from: protected */
    public PropertyFilter findPropertyFilter(SerializerProvider provider, Object filterId, Object valueToFilter) throws JsonMappingException {
        FilterProvider filters = provider.getFilterProvider();
        if (filters != null) {
            return filters.findPropertyFilter(filterId, valueToFilter);
        }
        throw JsonMappingException.from(provider, "Can not resolve PropertyFilter with id '" + filterId + "'; no FilterProvider configured");
    }

    /* access modifiers changed from: protected */
    public Value findFormatOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyFormat(provider.getConfig(), typeForDefaults);
        }
        return provider.getDefaultPropertyFormat(typeForDefaults);
    }

    /* access modifiers changed from: protected */
    public Boolean findFormatFeature(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults, Feature feat) {
        Value format = findFormatOverrides(provider, prop, typeForDefaults);
        if (format != null) {
            return format.getFeature(feat);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonInclude.Value findIncludeOverrides(SerializerProvider provider, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyInclusion(provider.getConfig(), typeForDefaults);
        }
        return provider.getDefaultPropertyInclusion(typeForDefaults);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<?> findAnnotatedContentSerializer(SerializerProvider serializers, BeanProperty property) throws JsonMappingException {
        if (property != null) {
            AnnotatedMember m = property.getMember();
            AnnotationIntrospector intr = serializers.getAnnotationIntrospector();
            if (m != null) {
                Object serDef = intr.findContentSerializer(m);
                if (serDef != null) {
                    return serializers.serializerInstance(m, serDef);
                }
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultSerializer(JsonSerializer<?> serializer) {
        return ClassUtil.isJacksonStdImpl((Object) serializer);
    }
}
