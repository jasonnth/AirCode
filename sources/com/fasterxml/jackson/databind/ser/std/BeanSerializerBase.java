package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitable;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.fasterxml.jackson.databind.jsonschema.SchemaAware;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ser.AnyGetterWriter;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerBuilder;
import com.fasterxml.jackson.databind.ser.ContainerSerializer;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;
import com.fasterxml.jackson.databind.ser.impl.ObjectIdWriter;
import com.fasterxml.jackson.databind.ser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.ser.impl.WritableObjectId;
import com.fasterxml.jackson.databind.util.Converter;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

public abstract class BeanSerializerBase extends StdSerializer<Object> implements JsonFormatVisitable, SchemaAware, ContextualSerializer, ResolvableSerializer {
    protected static final PropertyName NAME_FOR_OBJECT_REF = new PropertyName("#object-ref");
    protected static final BeanPropertyWriter[] NO_PROPS = new BeanPropertyWriter[0];
    protected final AnyGetterWriter _anyGetterWriter;
    protected final BeanPropertyWriter[] _filteredProps;
    protected final ObjectIdWriter _objectIdWriter;
    protected final Object _propertyFilterId;
    protected final BeanPropertyWriter[] _props;
    protected final Shape _serializationShape;
    protected final AnnotatedMember _typeId;

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase asArraySerializer();

    public abstract BeanSerializerBase withFilterId(Object obj);

    /* access modifiers changed from: protected */
    public abstract BeanSerializerBase withIgnorals(Set<String> set);

    public abstract BeanSerializerBase withObjectIdWriter(ObjectIdWriter objectIdWriter);

    protected BeanSerializerBase(JavaType type, BeanSerializerBuilder builder, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        Shape shape = null;
        super(type);
        this._props = properties;
        this._filteredProps = filteredProperties;
        if (builder == null) {
            this._typeId = null;
            this._anyGetterWriter = null;
            this._propertyFilterId = null;
            this._objectIdWriter = null;
            this._serializationShape = null;
            return;
        }
        this._typeId = builder.getTypeId();
        this._anyGetterWriter = builder.getAnyGetter();
        this._propertyFilterId = builder.getFilterId();
        this._objectIdWriter = builder.getObjectIdWriter();
        Value format = builder.getBeanDescription().findExpectedFormat(null);
        if (format != null) {
            shape = format.getShape();
        }
        this._serializationShape = shape;
    }

    public BeanSerializerBase(BeanSerializerBase src, BeanPropertyWriter[] properties, BeanPropertyWriter[] filteredProperties) {
        super(src._handledType);
        this._props = properties;
        this._filteredProps = filteredProperties;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter) {
        this(src, objectIdWriter, src._propertyFilterId);
    }

    protected BeanSerializerBase(BeanSerializerBase src, ObjectIdWriter objectIdWriter, Object filterId) {
        super(src._handledType);
        this._props = src._props;
        this._filteredProps = src._filteredProps;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = objectIdWriter;
        this._propertyFilterId = filterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, Set<String> toIgnore) {
        BeanPropertyWriter[] beanPropertyWriterArr = null;
        super(src._handledType);
        BeanPropertyWriter[] propsIn = src._props;
        BeanPropertyWriter[] fpropsIn = src._filteredProps;
        int len = propsIn.length;
        ArrayList<BeanPropertyWriter> propsOut = new ArrayList<>(len);
        ArrayList arrayList = fpropsIn == null ? null : new ArrayList(len);
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter bpw = propsIn[i];
            if (toIgnore == null || !toIgnore.contains(bpw.getName())) {
                propsOut.add(bpw);
                if (fpropsIn != null) {
                    arrayList.add(fpropsIn[i]);
                }
            }
        }
        this._props = (BeanPropertyWriter[]) propsOut.toArray(new BeanPropertyWriter[propsOut.size()]);
        if (arrayList != null) {
            beanPropertyWriterArr = (BeanPropertyWriter[]) arrayList.toArray(new BeanPropertyWriter[arrayList.size()]);
        }
        this._filteredProps = beanPropertyWriterArr;
        this._typeId = src._typeId;
        this._anyGetterWriter = src._anyGetterWriter;
        this._objectIdWriter = src._objectIdWriter;
        this._propertyFilterId = src._propertyFilterId;
        this._serializationShape = src._serializationShape;
    }

    protected BeanSerializerBase(BeanSerializerBase src, NameTransformer unwrapper) {
        this(src, rename(src._props, unwrapper), rename(src._filteredProps, unwrapper));
    }

    private static final BeanPropertyWriter[] rename(BeanPropertyWriter[] props, NameTransformer transformer) {
        if (props == null || props.length == 0 || transformer == null || transformer == NameTransformer.NOP) {
            return props;
        }
        int len = props.length;
        BeanPropertyWriter[] result = new BeanPropertyWriter[len];
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter bpw = props[i];
            if (bpw != null) {
                result[i] = bpw.rename(transformer);
            }
        }
        return result;
    }

    public void resolve(SerializerProvider provider) throws JsonMappingException {
        int filteredCount;
        if (this._filteredProps == null) {
            filteredCount = 0;
        } else {
            filteredCount = this._filteredProps.length;
        }
        int len = this._props.length;
        for (int i = 0; i < len; i++) {
            BeanPropertyWriter prop = this._props[i];
            if (!prop.willSuppressNulls() && !prop.hasNullSerializer()) {
                JsonSerializer<Object> nullSer = provider.findNullValueSerializer(prop);
                if (nullSer != null) {
                    prop.assignNullSerializer(nullSer);
                    if (i < filteredCount) {
                        BeanPropertyWriter w2 = this._filteredProps[i];
                        if (w2 != null) {
                            w2.assignNullSerializer(nullSer);
                        }
                    }
                }
            }
            if (!prop.hasSerializer()) {
                JsonSerializer<Object> ser = findConvertingSerializer(provider, prop);
                if (ser == null) {
                    JavaType type = prop.getSerializationType();
                    if (type == null) {
                        type = prop.getType();
                        if (!type.isFinal()) {
                            if (type.isContainerType() || type.containedTypeCount() > 0) {
                                prop.setNonTrivialBaseType(type);
                            }
                        }
                    }
                    ser = provider.findValueSerializer(type, (BeanProperty) prop);
                    if (type.isContainerType()) {
                        TypeSerializer typeSer = (TypeSerializer) type.getContentType().getTypeHandler();
                        if (typeSer != null && (ser instanceof ContainerSerializer)) {
                            ser = ((ContainerSerializer) ser).withValueTypeSerializer(typeSer);
                        }
                    }
                }
                prop.assignSerializer(ser);
                if (i < filteredCount) {
                    BeanPropertyWriter w22 = this._filteredProps[i];
                    if (w22 != null) {
                        w22.assignSerializer(ser);
                    }
                }
            }
        }
        if (this._anyGetterWriter != null) {
            this._anyGetterWriter.resolve(provider);
        }
    }

    /* access modifiers changed from: protected */
    public JsonSerializer<Object> findConvertingSerializer(SerializerProvider provider, BeanPropertyWriter prop) throws JsonMappingException {
        JsonSerializer<?> ser = null;
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        if (intr == null) {
            return null;
        }
        AnnotatedMember m = prop.getMember();
        if (m == null) {
            return null;
        }
        Object convDef = intr.findSerializationConverter(m);
        if (convDef == null) {
            return null;
        }
        Converter<Object, Object> conv = provider.converterInstance(prop.getMember(), convDef);
        JavaType delegateType = conv.getOutputType(provider.getTypeFactory());
        if (!delegateType.isJavaLangObject()) {
            ser = provider.findValueSerializer(delegateType, (BeanProperty) prop);
        }
        return new StdDelegatingSerializer<>(conv, delegateType, ser);
    }

    public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
        AnnotationIntrospector intr = provider.getAnnotationIntrospector();
        AnnotatedMember accessor = (property == null || intr == null) ? null : property.getMember();
        SerializationConfig config = provider.getConfig();
        Value format = findFormatOverrides(provider, property, handledType());
        Shape shape = null;
        if (format != null && format.hasShape()) {
            shape = format.getShape();
            if (!(shape == Shape.ANY || shape == this._serializationShape || !this._handledType.isEnum())) {
                switch (shape) {
                    case STRING:
                    case NUMBER:
                    case NUMBER_INT:
                        return provider.handlePrimaryContextualization(EnumSerializer.construct(this._handledType, provider.getConfig(), config.introspectClassAnnotations(this._handledType), format), property);
                }
            }
        }
        ObjectIdWriter oiw = this._objectIdWriter;
        Set<String> ignoredProps = null;
        Object newFilterId = null;
        if (accessor != null) {
            JsonIgnoreProperties.Value ignorals = intr.findPropertyIgnorals(accessor);
            if (ignorals != null) {
                ignoredProps = ignorals.findIgnoredForSerialization();
            }
            ObjectIdInfo objectIdInfo = intr.findObjectIdInfo(accessor);
            if (objectIdInfo != null) {
                ObjectIdInfo objectIdInfo2 = intr.findObjectReferenceInfo(accessor, objectIdInfo);
                Class<?> implClass = objectIdInfo2.getGeneratorType();
                JavaType idType = provider.getTypeFactory().findTypeParameters(provider.constructType(implClass), ObjectIdGenerator.class)[0];
                if (implClass == PropertyGenerator.class) {
                    String propName = objectIdInfo2.getPropertyName().getSimpleName();
                    int i = 0;
                    int len = this._props.length;
                    while (i != len) {
                        BeanPropertyWriter prop = this._props[i];
                        if (propName.equals(prop.getName())) {
                            BeanPropertyWriter idProp = prop;
                            if (i > 0) {
                                System.arraycopy(this._props, 0, this._props, 1, i);
                                this._props[0] = idProp;
                                if (this._filteredProps != null) {
                                    BeanPropertyWriter fp = this._filteredProps[i];
                                    System.arraycopy(this._filteredProps, 0, this._filteredProps, 1, i);
                                    this._filteredProps[0] = fp;
                                }
                            }
                            oiw = ObjectIdWriter.construct(idProp.getType(), (PropertyName) null, new PropertyBasedObjectIdGenerator<>(objectIdInfo2, idProp), objectIdInfo2.getAlwaysAsId());
                        } else {
                            i++;
                        }
                    }
                    throw new IllegalArgumentException("Invalid Object Id definition for " + this._handledType.getName() + ": can not find property with name '" + propName + "'");
                }
                oiw = ObjectIdWriter.construct(idType, objectIdInfo2.getPropertyName(), provider.objectIdGeneratorInstance(accessor, objectIdInfo2), objectIdInfo2.getAlwaysAsId());
            } else if (oiw != null) {
                oiw = this._objectIdWriter.withAlwaysAsId(intr.findObjectReferenceInfo(accessor, new ObjectIdInfo(NAME_FOR_OBJECT_REF, null, null, null)).getAlwaysAsId());
            }
            Object filterId = intr.findFilterId(accessor);
            if (filterId != null && (this._propertyFilterId == null || !filterId.equals(this._propertyFilterId))) {
                newFilterId = filterId;
            }
        }
        BeanSerializerBase contextual = this;
        if (oiw != null) {
            ObjectIdWriter oiw2 = oiw.withSerializer(provider.findValueSerializer(oiw.idType, property));
            if (oiw2 != this._objectIdWriter) {
                contextual = contextual.withObjectIdWriter(oiw2);
            }
        }
        if (ignoredProps != null && !ignoredProps.isEmpty()) {
            contextual = contextual.withIgnorals(ignoredProps);
        }
        if (newFilterId != null) {
            contextual = contextual.withFilterId(newFilterId);
        }
        if (shape == null) {
            shape = this._serializationShape;
        }
        if (shape == Shape.ARRAY) {
            return contextual.asArraySerializer();
        }
        return contextual;
    }

    public Iterator<PropertyWriter> properties() {
        return Arrays.asList(this._props).iterator();
    }

    public boolean usesObjectId() {
        return this._objectIdWriter != null;
    }

    public void serializeWithType(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        if (this._objectIdWriter != null) {
            gen.setCurrentValue(bean);
            _serializeWithObjectId(bean, gen, provider, typeSer);
            return;
        }
        String typeStr = this._typeId == null ? null : _customTypeId(bean);
        if (typeStr == null) {
            typeSer.writeTypePrefixForObject(bean, gen);
        } else {
            typeSer.writeCustomTypePrefixForObject(bean, gen, typeStr);
        }
        gen.setCurrentValue(bean);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(bean, gen, provider);
        } else {
            serializeFields(bean, gen, provider);
        }
        if (typeStr == null) {
            typeSer.writeTypeSuffixForObject(bean, gen);
        } else {
            typeSer.writeCustomTypeSuffixForObject(bean, gen, typeStr);
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, boolean startEndObject) throws IOException {
        ObjectIdWriter w = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w.generator);
        if (!objectId.writeAsId(gen, provider, w)) {
            Object id = objectId.generateId(bean);
            if (w.alwaysAsId) {
                w.serializer.serialize(id, gen, provider);
                return;
            }
            if (startEndObject) {
                gen.writeStartObject(bean);
            }
            objectId.writeAsField(gen, provider, w);
            if (this._propertyFilterId != null) {
                serializeFieldsFiltered(bean, gen, provider);
            } else {
                serializeFields(bean, gen, provider);
            }
            if (startEndObject) {
                gen.writeEndObject();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void _serializeWithObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        ObjectIdWriter w = this._objectIdWriter;
        WritableObjectId objectId = provider.findObjectId(bean, w.generator);
        if (!objectId.writeAsId(gen, provider, w)) {
            Object id = objectId.generateId(bean);
            if (w.alwaysAsId) {
                w.serializer.serialize(id, gen, provider);
            } else {
                _serializeObjectId(bean, gen, provider, typeSer, objectId);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _serializeObjectId(Object bean, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer, WritableObjectId objectId) throws IOException {
        ObjectIdWriter w = this._objectIdWriter;
        String typeStr = this._typeId == null ? null : _customTypeId(bean);
        if (typeStr == null) {
            typeSer.writeTypePrefixForObject(bean, gen);
        } else {
            typeSer.writeCustomTypePrefixForObject(bean, gen, typeStr);
        }
        objectId.writeAsField(gen, provider, w);
        if (this._propertyFilterId != null) {
            serializeFieldsFiltered(bean, gen, provider);
        } else {
            serializeFields(bean, gen, provider);
        }
        if (typeStr == null) {
            typeSer.writeTypeSuffixForObject(bean, gen);
        } else {
            typeSer.writeCustomTypeSuffixForObject(bean, gen, typeStr);
        }
    }

    /* access modifiers changed from: protected */
    public final String _customTypeId(Object bean) {
        Object typeId = this._typeId.getValue(bean);
        if (typeId == null) {
            return "";
        }
        return typeId instanceof String ? (String) typeId : typeId.toString();
    }

    /* access modifiers changed from: protected */
    public void serializeFields(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException {
        BeanPropertyWriter[] props;
        if (this._filteredProps == null || provider.getActiveView() == null) {
            props = this._props;
        } else {
            props = this._filteredProps;
        }
        int i = 0;
        try {
            int len = props.length;
            while (i < len) {
                BeanPropertyWriter prop = props[i];
                if (prop != null) {
                    prop.serializeAsField(bean, gen, provider);
                }
                i++;
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndSerialize(bean, gen, provider);
            }
        } catch (Exception e) {
            wrapAndThrow(provider, (Throwable) e, bean, i == props.length ? "[anySetter]" : props[i].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException mapE = new JsonMappingException((Closeable) gen, "Infinite recursion (StackOverflowError)", (Throwable) e2);
            mapE.prependPath(new Reference(bean, i == props.length ? "[anySetter]" : props[i].getName()));
            throw mapE;
        }
    }

    /* access modifiers changed from: protected */
    public void serializeFieldsFiltered(Object bean, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonGenerationException {
        BeanPropertyWriter[] props;
        if (this._filteredProps == null || provider.getActiveView() == null) {
            props = this._props;
        } else {
            props = this._filteredProps;
        }
        PropertyFilter filter = findPropertyFilter(provider, this._propertyFilterId, bean);
        if (filter == null) {
            serializeFields(bean, gen, provider);
            return;
        }
        try {
            for (BeanPropertyWriter prop : props) {
                if (prop != null) {
                    filter.serializeAsField(bean, gen, provider, prop);
                }
            }
            if (this._anyGetterWriter != null) {
                this._anyGetterWriter.getAndFilter(bean, gen, provider, filter);
            }
        } catch (Exception e) {
            wrapAndThrow(provider, (Throwable) e, bean, 0 == props.length ? "[anySetter]" : props[0].getName());
        } catch (StackOverflowError e2) {
            JsonMappingException mapE = new JsonMappingException((Closeable) gen, "Infinite recursion (StackOverflowError)", (Throwable) e2);
            mapE.prependPath(new Reference(bean, 0 == props.length ? "[anySetter]" : props[0].getName()));
            throw mapE;
        }
    }

    @Deprecated
    public JsonNode getSchema(SerializerProvider provider, Type typeHint) throws JsonMappingException {
        PropertyFilter filter;
        ObjectNode o = createSchemaNode("object", true);
        JsonSerializableSchema ann = (JsonSerializableSchema) this._handledType.getAnnotation(JsonSerializableSchema.class);
        if (ann != null) {
            String id = ann.mo14229id();
            if (id != null && id.length() > 0) {
                o.put("id", id);
            }
        }
        ObjectNode propertiesNode = o.objectNode();
        if (this._propertyFilterId != null) {
            filter = findPropertyFilter(provider, this._propertyFilterId, null);
        } else {
            filter = null;
        }
        for (BeanPropertyWriter prop : this._props) {
            if (filter == null) {
                prop.depositSchemaProperty(propertiesNode, provider);
            } else {
                filter.depositSchemaProperty((PropertyWriter) prop, propertiesNode, provider);
            }
        }
        o.set("properties", propertiesNode);
        return o;
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper visitor, JavaType typeHint) throws JsonMappingException {
        BeanPropertyWriter[] props;
        Class<?> view = null;
        if (visitor != null) {
            JsonObjectFormatVisitor objectVisitor = visitor.expectObjectFormat(typeHint);
            if (objectVisitor != null) {
                SerializerProvider provider = visitor.getProvider();
                if (this._propertyFilterId != null) {
                    PropertyFilter filter = findPropertyFilter(visitor.getProvider(), this._propertyFilterId, null);
                    for (BeanPropertyWriter depositSchemaProperty : this._props) {
                        filter.depositSchemaProperty((PropertyWriter) depositSchemaProperty, objectVisitor, provider);
                    }
                    return;
                }
                if (!(this._filteredProps == null || provider == null)) {
                    view = provider.getActiveView();
                }
                if (view != null) {
                    props = this._filteredProps;
                } else {
                    props = this._props;
                }
                for (BeanPropertyWriter prop : props) {
                    if (prop != null) {
                        prop.depositSchemaProperty(objectVisitor, provider);
                    }
                }
            }
        }
    }
}
