package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.PrettyPrinter;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.p307io.SegmentedStringWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.cfg.BaseSettings;
import com.fasterxml.jackson.databind.cfg.ConfigOverrides;
import com.fasterxml.jackson.databind.deser.BeanDeserializerFactory;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.introspect.BasicClassIntrospector;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.SimpleMixInResolver;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker.Std;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import com.fasterxml.jackson.databind.jsontype.impl.StdSubtypeResolver;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.POJONode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.ser.BeanSerializerFactory;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider;
import com.fasterxml.jackson.databind.ser.DefaultSerializerProvider.Impl;
import com.fasterxml.jackson.databind.ser.SerializerFactory;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.RootNameLookup;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.Closeable;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectMapper extends ObjectCodec implements Serializable {
    protected static final AnnotationIntrospector DEFAULT_ANNOTATION_INTROSPECTOR = new JacksonAnnotationIntrospector();
    protected static final BaseSettings DEFAULT_BASE = new BaseSettings(null, DEFAULT_ANNOTATION_INTROSPECTOR, STD_VISIBILITY_CHECKER, null, TypeFactory.defaultInstance(), null, StdDateFormat.instance, null, Locale.getDefault(), null, Base64Variants.getDefaultVariant());
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected static final VisibilityChecker<?> STD_VISIBILITY_CHECKER = Std.defaultInstance();
    protected DeserializationConfig _deserializationConfig;
    protected DefaultDeserializationContext _deserializationContext;
    protected InjectableValues _injectableValues;
    protected final JsonFactory _jsonFactory;
    protected SimpleMixInResolver _mixIns;
    protected ConfigOverrides _propertyOverrides;
    protected Set<Object> _registeredModuleTypes;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected SerializationConfig _serializationConfig;
    protected SerializerFactory _serializerFactory;
    protected DefaultSerializerProvider _serializerProvider;
    protected SubtypeResolver _subtypeResolver;
    protected TypeFactory _typeFactory;

    public ObjectMapper() {
        this(null, null, null);
    }

    public ObjectMapper(JsonFactory jf) {
        this(jf, null, null);
    }

    public ObjectMapper(JsonFactory jf, DefaultSerializerProvider sp, DefaultDeserializationContext dc) {
        this._rootDeserializers = new ConcurrentHashMap<>(64, 0.6f, 2);
        if (jf == null) {
            this._jsonFactory = new MappingJsonFactory(this);
        } else {
            this._jsonFactory = jf;
            if (jf.getCodec() == null) {
                this._jsonFactory.setCodec(this);
            }
        }
        this._subtypeResolver = new StdSubtypeResolver();
        RootNameLookup rootNames = new RootNameLookup();
        this._typeFactory = TypeFactory.defaultInstance();
        SimpleMixInResolver mixins = new SimpleMixInResolver(null);
        this._mixIns = mixins;
        BaseSettings base = DEFAULT_BASE.withClassIntrospector(defaultClassIntrospector());
        ConfigOverrides propOverrides = new ConfigOverrides();
        this._propertyOverrides = propOverrides;
        this._serializationConfig = new SerializationConfig(base, this._subtypeResolver, mixins, rootNames, propOverrides);
        this._deserializationConfig = new DeserializationConfig(base, this._subtypeResolver, mixins, rootNames, propOverrides);
        boolean needOrder = this._jsonFactory.requiresPropertyOrdering();
        if (this._serializationConfig.isEnabled(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY) ^ needOrder) {
            configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, needOrder);
        }
        if (sp == null) {
            sp = new Impl();
        }
        this._serializerProvider = sp;
        if (dc == null) {
            dc = new DefaultDeserializationContext.Impl(BeanDeserializerFactory.instance);
        }
        this._deserializationContext = dc;
        this._serializerFactory = BeanSerializerFactory.instance;
    }

    /* access modifiers changed from: protected */
    public ClassIntrospector defaultClassIntrospector() {
        return new BasicClassIntrospector();
    }

    /* access modifiers changed from: protected */
    public ObjectReader _newReader(DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
        return new ObjectReader(this, config, valueType, valueToUpdate, schema, injectableValues);
    }

    /* access modifiers changed from: protected */
    public ObjectWriter _newWriter(SerializationConfig config, JavaType rootType, PrettyPrinter pp) {
        return new ObjectWriter(this, config, rootType, pp);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r6._registeredModuleTypes.add(r2) == false) goto L_0x0021;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.ObjectMapper registerModule(com.fasterxml.jackson.databind.Module r7) {
        /*
            r6 = this;
            com.fasterxml.jackson.databind.MapperFeature r4 = com.fasterxml.jackson.databind.MapperFeature.IGNORE_DUPLICATE_MODULE_REGISTRATIONS
            boolean r4 = r6.isEnabled(r4)
            if (r4 == 0) goto L_0x0022
            java.lang.Object r2 = r7.getTypeId()
            if (r2 == 0) goto L_0x0022
            java.util.Set<java.lang.Object> r4 = r6._registeredModuleTypes
            if (r4 != 0) goto L_0x0019
            java.util.LinkedHashSet r4 = new java.util.LinkedHashSet
            r4.<init>()
            r6._registeredModuleTypes = r4
        L_0x0019:
            java.util.Set<java.lang.Object> r4 = r6._registeredModuleTypes
            boolean r4 = r4.add(r2)
            if (r4 != 0) goto L_0x0022
        L_0x0021:
            return r6
        L_0x0022:
            java.lang.String r1 = r7.getModuleName()
            if (r1 != 0) goto L_0x0031
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Module without defined name"
            r4.<init>(r5)
            throw r4
        L_0x0031:
            com.fasterxml.jackson.core.Version r3 = r7.version()
            if (r3 != 0) goto L_0x0040
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Module without defined version"
            r4.<init>(r5)
            throw r4
        L_0x0040:
            r0 = r6
            com.fasterxml.jackson.databind.ObjectMapper$1 r4 = new com.fasterxml.jackson.databind.ObjectMapper$1
            r4.<init>(r0)
            r7.setupModule(r4)
            goto L_0x0021
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ObjectMapper.registerModule(com.fasterxml.jackson.databind.Module):com.fasterxml.jackson.databind.ObjectMapper");
    }

    public ObjectMapper registerModules(Module... modules) {
        for (Module module : modules) {
            registerModule(module);
        }
        return this;
    }

    public SerializationConfig getSerializationConfig() {
        return this._serializationConfig;
    }

    public DeserializationConfig getDeserializationConfig() {
        return this._deserializationConfig;
    }

    public ObjectMapper addMixIn(Class<?> target, Class<?> mixinSource) {
        this._mixIns.addLocalDefinition(target, mixinSource);
        return this;
    }

    public ObjectMapper setVisibility(VisibilityChecker<?> vc) {
        this._deserializationConfig = this._deserializationConfig.with(vc);
        this._serializationConfig = this._serializationConfig.with(vc);
        return this;
    }

    public SubtypeResolver getSubtypeResolver() {
        return this._subtypeResolver;
    }

    public ObjectMapper setPropertyNamingStrategy(PropertyNamingStrategy s) {
        this._serializationConfig = this._serializationConfig.with(s);
        this._deserializationConfig = this._deserializationConfig.with(s);
        return this;
    }

    public ObjectMapper setSerializationInclusion(Include incl) {
        setPropertyInclusion(Value.construct(incl, Include.USE_DEFAULTS));
        return this;
    }

    public ObjectMapper setPropertyInclusion(Value incl) {
        this._serializationConfig = this._serializationConfig.withPropertyInclusion(incl);
        return this;
    }

    public void registerSubtypes(NamedType... types) {
        getSubtypeResolver().registerSubtypes(types);
    }

    public TypeFactory getTypeFactory() {
        return this._typeFactory;
    }

    public JsonNodeFactory getNodeFactory() {
        return this._deserializationConfig.getNodeFactory();
    }

    public JsonFactory getFactory() {
        return this._jsonFactory;
    }

    @Deprecated
    public JsonFactory getJsonFactory() {
        return getFactory();
    }

    public boolean isEnabled(MapperFeature f) {
        return this._serializationConfig.isEnabled(f);
    }

    public ObjectMapper configure(MapperFeature f, boolean state) {
        SerializationConfig without;
        DeserializationConfig without2;
        if (state) {
            without = this._serializationConfig.with(f);
        } else {
            without = this._serializationConfig.without(f);
        }
        this._serializationConfig = without;
        if (state) {
            without2 = this._deserializationConfig.with(f);
        } else {
            without2 = this._deserializationConfig.without(f);
        }
        this._deserializationConfig = without2;
        return this;
    }

    public boolean isEnabled(DeserializationFeature f) {
        return this._deserializationConfig.isEnabled(f);
    }

    public ObjectMapper configure(DeserializationFeature f, boolean state) {
        this._deserializationConfig = state ? this._deserializationConfig.with(f) : this._deserializationConfig.without(f);
        return this;
    }

    public <T> T readValue(JsonParser p, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), p, this._typeFactory.constructType((Type) valueType));
    }

    public <T> T readValue(JsonParser p, TypeReference<?> valueTypeRef) throws IOException, JsonParseException, JsonMappingException {
        return _readValue(getDeserializationConfig(), p, this._typeFactory.constructType(valueTypeRef));
    }

    public <T extends TreeNode> T readTree(JsonParser p) throws IOException, JsonProcessingException {
        DeserializationConfig cfg = getDeserializationConfig();
        if (p.getCurrentToken() == null && p.nextToken() == null) {
            return null;
        }
        T t = (JsonNode) _readValue(cfg, p, JSON_NODE_TYPE);
        if (t == null) {
            t = getNodeFactory().nullNode();
        }
        return t;
    }

    public JsonNode readTree(String content) throws IOException, JsonProcessingException {
        JsonNode n = (JsonNode) _readMapAndClose(this._jsonFactory.createParser(content), JSON_NODE_TYPE);
        return n == null ? NullNode.instance : n;
    }

    public void writeValue(JsonGenerator g, Object value) throws IOException, JsonGenerationException, JsonMappingException {
        SerializationConfig config = getSerializationConfig();
        if (config.isEnabled(SerializationFeature.INDENT_OUTPUT) && g.getPrettyPrinter() == null) {
            g.setPrettyPrinter(config.constructDefaultPrettyPrinter());
        }
        if (!config.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(value instanceof Closeable)) {
            _serializerProvider(config).serializeValue(g, value);
            if (config.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                g.flush();
                return;
            }
            return;
        }
        _writeCloseableValue(g, value, config);
    }

    public JsonParser treeAsTokens(TreeNode n) {
        return new TreeTraversingParser((JsonNode) n, this);
    }

    public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        if (valueType != Object.class) {
            try {
                if (valueType.isAssignableFrom(n.getClass())) {
                    return n;
                }
            } catch (JsonProcessingException e) {
                throw e;
            } catch (IOException e2) {
                throw new IllegalArgumentException(e2.getMessage(), e2);
            }
        }
        if (n.asToken() == JsonToken.VALUE_EMBEDDED_OBJECT && (n instanceof POJONode)) {
            Object ob = ((POJONode) n).getPojo();
            if (ob == null || valueType.isInstance(ob)) {
                return ob;
            }
        }
        return readValue(treeAsTokens(n), valueType);
    }

    public <T> T readValue(String content, Class<T> valueType) throws IOException, JsonParseException, JsonMappingException {
        return _readMapAndClose(this._jsonFactory.createParser(content), this._typeFactory.constructType((Type) valueType));
    }

    public String writeValueAsString(Object value) throws JsonProcessingException {
        SegmentedStringWriter sw = new SegmentedStringWriter(this._jsonFactory._getBufferRecycler());
        try {
            _configAndWriteValue(this._jsonFactory.createGenerator(sw), value);
            return sw.getAndClear();
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw JsonMappingException.fromUnexpectedIOE(e2);
        }
    }

    public ObjectWriter writerFor(JavaType rootType) {
        return _newWriter(getSerializationConfig(), rootType, null);
    }

    @Deprecated
    public ObjectWriter writerWithType(JavaType rootType) {
        return _newWriter(getSerializationConfig(), rootType, null);
    }

    public ObjectReader readerFor(JavaType type) {
        return _newReader(getDeserializationConfig(), type, null, null, this._injectableValues);
    }

    @Deprecated
    public ObjectReader reader(JavaType type) {
        return _newReader(getDeserializationConfig(), type, null, null, this._injectableValues);
    }

    public <T> T convertValue(Object fromValue, Class<T> toValueType) throws IllegalArgumentException {
        if (fromValue == null) {
            return null;
        }
        return _convert(fromValue, this._typeFactory.constructType((Type) toValueType));
    }

    /* access modifiers changed from: protected */
    public Object _convert(Object fromValue, JavaType toValueType) throws IllegalArgumentException {
        Object obj;
        Class<?> targetType = toValueType.getRawClass();
        if (targetType != Object.class && !toValueType.hasGenericTypes() && targetType.isAssignableFrom(fromValue.getClass())) {
            return fromValue;
        }
        TokenBuffer buf = new TokenBuffer((ObjectCodec) this, false);
        if (isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
            buf = buf.forceUseOfBigDecimal(true);
        }
        try {
            _serializerProvider(getSerializationConfig().without(SerializationFeature.WRAP_ROOT_VALUE)).serializeValue(buf, fromValue);
            JsonParser p = buf.asParser();
            DeserializationConfig deserConfig = getDeserializationConfig();
            JsonToken t = _initForReading(p);
            if (t == JsonToken.VALUE_NULL) {
                DeserializationContext ctxt = createDeserializationContext(p, deserConfig);
                obj = _findRootDeserializer(ctxt, toValueType).getNullValue(ctxt);
            } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
                obj = null;
            } else {
                DeserializationContext ctxt2 = createDeserializationContext(p, deserConfig);
                obj = _findRootDeserializer(ctxt2, toValueType).deserialize(p, ctxt2);
            }
            p.close();
            return obj;
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /* access modifiers changed from: protected */
    public DefaultSerializerProvider _serializerProvider(SerializationConfig config) {
        return this._serializerProvider.createInstance(config, this._serializerFactory);
    }

    /* access modifiers changed from: protected */
    public final void _configAndWriteValue(JsonGenerator g, Object value) throws IOException {
        SerializationConfig cfg = getSerializationConfig();
        cfg.initialize(g);
        if (!cfg.isEnabled(SerializationFeature.CLOSE_CLOSEABLE) || !(value instanceof Closeable)) {
            try {
                _serializerProvider(cfg).serializeValue(g, value);
                g.close();
            } catch (Exception e) {
                ClassUtil.closeOnFailAndThrowAsIAE(g, e);
            }
        } else {
            _configAndWriteCloseable(g, value, cfg);
        }
    }

    private final void _configAndWriteCloseable(JsonGenerator g, Object value, SerializationConfig cfg) throws IOException {
        Closeable toClose = (Closeable) value;
        try {
            _serializerProvider(cfg).serializeValue(g, value);
            Closeable tmpToClose = toClose;
            toClose = null;
            tmpToClose.close();
            g.close();
        } catch (Exception e) {
            ClassUtil.closeOnFailAndThrowAsIAE(g, toClose, e);
        }
    }

    private final void _writeCloseableValue(JsonGenerator g, Object value, SerializationConfig cfg) throws IOException {
        Closeable toClose = (Closeable) value;
        try {
            _serializerProvider(cfg).serializeValue(g, value);
            if (cfg.isEnabled(SerializationFeature.FLUSH_AFTER_WRITE_VALUE)) {
                g.flush();
            }
            toClose.close();
        } catch (Exception e) {
            ClassUtil.closeOnFailAndThrowAsIAE(null, toClose, e);
        }
    }

    /* access modifiers changed from: protected */
    public DefaultDeserializationContext createDeserializationContext(JsonParser p, DeserializationConfig cfg) {
        return this._deserializationContext.createInstance(cfg, p, this._injectableValues);
    }

    /* access modifiers changed from: protected */
    public Object _readValue(DeserializationConfig cfg, JsonParser p, JavaType valueType) throws IOException {
        Object result;
        JsonToken t = _initForReading(p);
        if (t == JsonToken.VALUE_NULL) {
            DeserializationContext ctxt = createDeserializationContext(p, cfg);
            result = _findRootDeserializer(ctxt, valueType).getNullValue(ctxt);
        } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
            result = null;
        } else {
            DeserializationContext ctxt2 = createDeserializationContext(p, cfg);
            JsonDeserializer<Object> deser = _findRootDeserializer(ctxt2, valueType);
            if (cfg.useRootWrapping()) {
                result = _unwrapAndDeserialize(p, ctxt2, cfg, valueType, deser);
            } else {
                result = deser.deserialize(p, ctxt2);
            }
        }
        p.clearCurrentToken();
        return result;
    }

    /* access modifiers changed from: protected */
    public Object _readMapAndClose(JsonParser p0, JavaType valueType) throws IOException {
        Throwable th;
        Object result;
        JsonParser p = p0;
        Throwable th2 = null;
        try {
            JsonToken t = _initForReading(p);
            if (t == JsonToken.VALUE_NULL) {
                DeserializationContext ctxt = createDeserializationContext(p, getDeserializationConfig());
                result = _findRootDeserializer(ctxt, valueType).getNullValue(ctxt);
            } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
                result = null;
            } else {
                DeserializationConfig cfg = getDeserializationConfig();
                DeserializationContext ctxt2 = createDeserializationContext(p, cfg);
                JsonDeserializer<Object> deser = _findRootDeserializer(ctxt2, valueType);
                if (cfg.useRootWrapping()) {
                    result = _unwrapAndDeserialize(p, ctxt2, cfg, valueType, deser);
                } else {
                    result = deser.deserialize(p, ctxt2);
                }
                ctxt2.checkUnresolvedObjectId();
            }
            p.clearCurrentToken();
            if (p != null) {
                if (th2 != null) {
                    try {
                        p.close();
                    } catch (Throwable x2) {
                        th2.addSuppressed(x2);
                    }
                } else {
                    p.close();
                }
            }
            return result;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            th = r0;
            th = th4;
        }
        if (p != null) {
            if (th != null) {
                try {
                    p.close();
                } catch (Throwable x22) {
                    th.addSuppressed(x22);
                }
            } else {
                p.close();
            }
        }
        throw th;
        throw th;
    }

    /* access modifiers changed from: protected */
    public JsonToken _initForReading(JsonParser p) throws IOException {
        this._deserializationConfig.initialize(p);
        JsonToken t = p.getCurrentToken();
        if (t == null) {
            t = p.nextToken();
            if (t == null) {
                throw JsonMappingException.from(p, "No content to map due to end-of-input");
            }
        }
        return t;
    }

    /* access modifiers changed from: protected */
    public Object _unwrapAndDeserialize(JsonParser p, DeserializationContext ctxt, DeserializationConfig config, JavaType rootType, JsonDeserializer<Object> deser) throws IOException {
        String expSimpleName = config.findRootName(rootType).getSimpleName();
        if (p.getCurrentToken() != JsonToken.START_OBJECT) {
            ctxt.reportWrongTokenException(p, JsonToken.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name '%s'), but %s", expSimpleName, p.getCurrentToken());
        }
        if (p.nextToken() != JsonToken.FIELD_NAME) {
            ctxt.reportWrongTokenException(p, JsonToken.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name '" + expSimpleName + "'), but " + p.getCurrentToken(), new Object[0]);
        }
        String actualName = p.getCurrentName();
        if (!expSimpleName.equals(actualName)) {
            ctxt.reportMappingException("Root name '%s' does not match expected ('%s') for type %s", actualName, expSimpleName, rootType);
        }
        p.nextToken();
        Object result = deser.deserialize(p, ctxt);
        if (p.nextToken() != JsonToken.END_OBJECT) {
            ctxt.reportWrongTokenException(p, JsonToken.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name '%s'), but %s", expSimpleName, p.getCurrentToken());
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt, JavaType valueType) throws JsonMappingException {
        JsonDeserializer<Object> deser = (JsonDeserializer) this._rootDeserializers.get(valueType);
        if (deser != null) {
            return deser;
        }
        JsonDeserializer<Object> deser2 = ctxt.findRootValueDeserializer(valueType);
        if (deser2 == null) {
            throw JsonMappingException.from(ctxt, "Can not find a deserializer for type " + valueType);
        }
        this._rootDeserializers.put(valueType, deser2);
        return deser2;
    }
}
