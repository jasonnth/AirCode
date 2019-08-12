package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p307io.SerializedString;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap;
import com.fasterxml.jackson.databind.ser.impl.PropertySerializerMap.SerializerAndMapResult;
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.std.BeanSerializerBase;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.HashMap;

@JacksonStdImpl
public class BeanPropertyWriter extends PropertyWriter implements Serializable {
    public static final Object MARKER_FOR_EMPTY = Include.NON_EMPTY;
    protected transient Method _accessorMethod;
    protected final JavaType _cfgSerializationType;
    protected final transient Annotations _contextAnnotations;
    protected final JavaType _declaredType;
    protected transient PropertySerializerMap _dynamicSerializers;
    protected transient Field _field;
    protected final Class<?>[] _includeInViews;
    protected transient HashMap<Object, Object> _internalSettings;
    protected final AnnotatedMember _member;
    protected final SerializedString _name;
    protected JavaType _nonTrivialBaseType;
    protected JsonSerializer<Object> _nullSerializer;
    protected JsonSerializer<Object> _serializer;
    protected final boolean _suppressNulls;
    protected final Object _suppressableValue;
    protected TypeSerializer _typeSerializer;
    protected final PropertyName _wrapperName;

    public BeanPropertyWriter(BeanPropertyDefinition propDef, AnnotatedMember member, Annotations contextAnnotations, JavaType declaredType, JsonSerializer<?> ser, TypeSerializer typeSer, JavaType serType, boolean suppressNulls, Object suppressableValue) {
        super(propDef);
        this._member = member;
        this._contextAnnotations = contextAnnotations;
        this._name = new SerializedString(propDef.getName());
        this._wrapperName = propDef.getWrapperName();
        this._includeInViews = propDef.findViews();
        this._declaredType = declaredType;
        this._serializer = ser;
        this._dynamicSerializers = ser == null ? PropertySerializerMap.emptyForProperties() : null;
        this._typeSerializer = typeSer;
        this._cfgSerializationType = serType;
        if (member instanceof AnnotatedField) {
            this._accessorMethod = null;
            this._field = (Field) member.getMember();
        } else if (member instanceof AnnotatedMethod) {
            this._accessorMethod = (Method) member.getMember();
            this._field = null;
        } else {
            this._accessorMethod = null;
            this._field = null;
        }
        this._suppressNulls = suppressNulls;
        this._suppressableValue = suppressableValue;
        this._nullSerializer = null;
    }

    protected BeanPropertyWriter() {
        super(PropertyMetadata.STD_REQUIRED_OR_OPTIONAL);
        this._member = null;
        this._contextAnnotations = null;
        this._name = null;
        this._wrapperName = null;
        this._includeInViews = null;
        this._declaredType = null;
        this._serializer = null;
        this._dynamicSerializers = null;
        this._typeSerializer = null;
        this._cfgSerializationType = null;
        this._accessorMethod = null;
        this._field = null;
        this._suppressNulls = false;
        this._suppressableValue = null;
        this._nullSerializer = null;
    }

    protected BeanPropertyWriter(BeanPropertyWriter base) {
        this(base, base._name);
    }

    protected BeanPropertyWriter(BeanPropertyWriter base, PropertyName name) {
        super((PropertyWriter) base);
        this._name = new SerializedString(name.getSimpleName());
        this._wrapperName = base._wrapperName;
        this._contextAnnotations = base._contextAnnotations;
        this._declaredType = base._declaredType;
        this._member = base._member;
        this._accessorMethod = base._accessorMethod;
        this._field = base._field;
        this._serializer = base._serializer;
        this._nullSerializer = base._nullSerializer;
        if (base._internalSettings != null) {
            this._internalSettings = new HashMap<>(base._internalSettings);
        }
        this._cfgSerializationType = base._cfgSerializationType;
        this._dynamicSerializers = base._dynamicSerializers;
        this._suppressNulls = base._suppressNulls;
        this._suppressableValue = base._suppressableValue;
        this._includeInViews = base._includeInViews;
        this._typeSerializer = base._typeSerializer;
        this._nonTrivialBaseType = base._nonTrivialBaseType;
    }

    protected BeanPropertyWriter(BeanPropertyWriter base, SerializedString name) {
        super((PropertyWriter) base);
        this._name = name;
        this._wrapperName = base._wrapperName;
        this._member = base._member;
        this._contextAnnotations = base._contextAnnotations;
        this._declaredType = base._declaredType;
        this._accessorMethod = base._accessorMethod;
        this._field = base._field;
        this._serializer = base._serializer;
        this._nullSerializer = base._nullSerializer;
        if (base._internalSettings != null) {
            this._internalSettings = new HashMap<>(base._internalSettings);
        }
        this._cfgSerializationType = base._cfgSerializationType;
        this._dynamicSerializers = base._dynamicSerializers;
        this._suppressNulls = base._suppressNulls;
        this._suppressableValue = base._suppressableValue;
        this._includeInViews = base._includeInViews;
        this._typeSerializer = base._typeSerializer;
        this._nonTrivialBaseType = base._nonTrivialBaseType;
    }

    /* Debug info: failed to restart local var, previous not found, register: 2 */
    public BeanPropertyWriter rename(NameTransformer transformer) {
        String newName = transformer.transform(this._name.getValue());
        return newName.equals(this._name.toString()) ? this : _new(PropertyName.construct(newName));
    }

    /* access modifiers changed from: protected */
    public BeanPropertyWriter _new(PropertyName newName) {
        return new BeanPropertyWriter(this, newName);
    }

    public void assignTypeSerializer(TypeSerializer typeSer) {
        this._typeSerializer = typeSer;
    }

    public void assignSerializer(JsonSerializer<Object> ser) {
        if (this._serializer == null || this._serializer == ser) {
            this._serializer = ser;
            return;
        }
        throw new IllegalStateException("Can not override serializer");
    }

    public void assignNullSerializer(JsonSerializer<Object> nullSer) {
        if (this._nullSerializer == null || this._nullSerializer == nullSer) {
            this._nullSerializer = nullSer;
            return;
        }
        throw new IllegalStateException("Can not override null serializer");
    }

    public BeanPropertyWriter unwrappingWriter(NameTransformer unwrapper) {
        return new UnwrappingBeanPropertyWriter(this, unwrapper);
    }

    public void setNonTrivialBaseType(JavaType t) {
        this._nonTrivialBaseType = t;
    }

    public void fixAccess(SerializationConfig config) {
        this._member.fixAccess(config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
    }

    /* access modifiers changed from: 0000 */
    public Object readResolve() {
        if (this._member instanceof AnnotatedField) {
            this._accessorMethod = null;
            this._field = (Field) this._member.getMember();
        } else if (this._member instanceof AnnotatedMethod) {
            this._accessorMethod = (Method) this._member.getMember();
            this._field = null;
        }
        if (this._serializer == null) {
            this._dynamicSerializers = PropertySerializerMap.emptyForProperties();
        }
        return this;
    }

    public String getName() {
        return this._name.getValue();
    }

    public JavaType getType() {
        return this._declaredType;
    }

    public <A extends Annotation> A getAnnotation(Class<A> acls) {
        if (this._member == null) {
            return null;
        }
        return this._member.getAnnotation(acls);
    }

    public AnnotatedMember getMember() {
        return this._member;
    }

    /* access modifiers changed from: protected */
    public void _depositSchemaProperty(ObjectNode propertiesNode, JsonNode schemaNode) {
        propertiesNode.set(getName(), schemaNode);
    }

    public boolean hasSerializer() {
        return this._serializer != null;
    }

    public boolean hasNullSerializer() {
        return this._nullSerializer != null;
    }

    public TypeSerializer getTypeSerializer() {
        return this._typeSerializer;
    }

    public boolean willSuppressNulls() {
        return this._suppressNulls;
    }

    public boolean wouldConflictWithName(PropertyName name) {
        if (this._wrapperName != null) {
            return this._wrapperName.equals(name);
        }
        return name.hasSimpleName(this._name.getValue()) && !name.hasNamespace();
    }

    public JsonSerializer<Object> getSerializer() {
        return this._serializer;
    }

    public JavaType getSerializationType() {
        return this._cfgSerializationType;
    }

    public Class<?>[] getViews() {
        return this._includeInViews;
    }

    public void serializeAsField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        Object value;
        if (this._accessorMethod == null) {
            value = this._field.get(bean);
        } else {
            value = this._accessorMethod.invoke(bean, new Object[0]);
        }
        if (value != null) {
            JsonSerializer<Object> ser = this._serializer;
            if (ser == null) {
                Class<?> cls = value.getClass();
                PropertySerializerMap m = this._dynamicSerializers;
                ser = m.serializerFor(cls);
                if (ser == null) {
                    ser = _findAndAddDynamic(m, cls, prov);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (ser.isEmpty(prov, value)) {
                        return;
                    }
                } else if (this._suppressableValue.equals(value)) {
                    return;
                }
            }
            if (value != bean || !_handleSelfReference(bean, gen, prov, ser)) {
                gen.writeFieldName((SerializableString) this._name);
                if (this._typeSerializer == null) {
                    ser.serialize(value, gen, prov);
                } else {
                    ser.serializeWithType(value, gen, prov, this._typeSerializer);
                }
            }
        } else if (this._nullSerializer != null) {
            gen.writeFieldName((SerializableString) this._name);
            this._nullSerializer.serialize(null, gen, prov);
        }
    }

    public void serializeAsOmittedField(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        if (!gen.canOmitFields()) {
            gen.writeOmittedField(this._name.getValue());
        }
    }

    public void serializeAsElement(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        Object value = this._accessorMethod == null ? this._field.get(bean) : this._accessorMethod.invoke(bean, new Object[0]);
        if (value != null) {
            JsonSerializer<Object> ser = this._serializer;
            if (ser == null) {
                Class<?> cls = value.getClass();
                PropertySerializerMap map = this._dynamicSerializers;
                ser = map.serializerFor(cls);
                if (ser == null) {
                    ser = _findAndAddDynamic(map, cls, prov);
                }
            }
            if (this._suppressableValue != null) {
                if (MARKER_FOR_EMPTY == this._suppressableValue) {
                    if (ser.isEmpty(prov, value)) {
                        serializeAsPlaceholder(bean, gen, prov);
                        return;
                    }
                } else if (this._suppressableValue.equals(value)) {
                    serializeAsPlaceholder(bean, gen, prov);
                    return;
                }
            }
            if (value == bean && _handleSelfReference(bean, gen, prov, ser)) {
                return;
            }
            if (this._typeSerializer == null) {
                ser.serialize(value, gen, prov);
            } else {
                ser.serializeWithType(value, gen, prov, this._typeSerializer);
            }
        } else if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, gen, prov);
        } else {
            gen.writeNull();
        }
    }

    public void serializeAsPlaceholder(Object bean, JsonGenerator gen, SerializerProvider prov) throws Exception {
        if (this._nullSerializer != null) {
            this._nullSerializer.serialize(null, gen, prov);
        } else {
            gen.writeNull();
        }
    }

    public void depositSchemaProperty(JsonObjectFormatVisitor v, SerializerProvider provider) throws JsonMappingException {
        if (v == null) {
            return;
        }
        if (isRequired()) {
            v.property(this);
        } else {
            v.optionalProperty(this);
        }
    }

    @Deprecated
    public void depositSchemaProperty(ObjectNode propertiesNode, SerializerProvider provider) throws JsonMappingException {
        JsonNode schemaNode;
        JavaType propType = getSerializationType();
        Type hint = propType == null ? getType() : propType.getRawClass();
        JsonSerializer<Object> ser = getSerializer();
        if (ser == null) {
            ser = provider.findValueSerializer(getType(), (BeanProperty) this);
        }
        boolean isOptional = !isRequired();
        if (ser instanceof SchemaAware) {
            schemaNode = ((SchemaAware) ser).getSchema(provider, hint, isOptional);
        } else {
            schemaNode = JsonSchema.getDefaultSchemaNode();
        }
        _depositSchemaProperty(propertiesNode, schemaNode);
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> _findAndAddDynamic(PropertySerializerMap map, Class<?> type, SerializerProvider provider) throws JsonMappingException {
        SerializerAndMapResult result;
        if (this._nonTrivialBaseType != null) {
            result = map.findAndAddPrimarySerializer(provider.constructSpecializedType(this._nonTrivialBaseType, type), provider, (BeanProperty) this);
        } else {
            result = map.findAndAddPrimarySerializer(type, provider, (BeanProperty) this);
        }
        if (map != result.map) {
            this._dynamicSerializers = result.map;
        }
        return result.serializer;
    }

    public final Object get(Object bean) throws Exception {
        return this._accessorMethod == null ? this._field.get(bean) : this._accessorMethod.invoke(bean, new Object[0]);
    }

    /* access modifiers changed from: protected */
    public boolean _handleSelfReference(Object bean, JsonGenerator gen, SerializerProvider prov, JsonSerializer<?> ser) throws JsonMappingException {
        if (prov.isEnabled(SerializationFeature.FAIL_ON_SELF_REFERENCES) && !ser.usesObjectId() && (ser instanceof BeanSerializerBase)) {
            prov.reportMappingProblem("Direct self-reference leading to cycle", new Object[0]);
        }
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("property '").append(getName()).append("' (");
        if (this._accessorMethod != null) {
            sb.append("via method ").append(this._accessorMethod.getDeclaringClass().getName()).append("#").append(this._accessorMethod.getName());
        } else if (this._field != null) {
            sb.append("field \"").append(this._field.getDeclaringClass().getName()).append("#").append(this._field.getName());
        } else {
            sb.append("virtual");
        }
        if (this._serializer == null) {
            sb.append(", no static serializer");
        } else {
            sb.append(", static serializer of type " + this._serializer.getClass().getName());
        }
        sb.append(')');
        return sb.toString();
    }
}
