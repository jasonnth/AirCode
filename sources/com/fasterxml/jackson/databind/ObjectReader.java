package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.filter.FilteringParserDelegate;
import com.fasterxml.jackson.core.filter.TokenFilter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;
import com.fasterxml.jackson.databind.deser.DefaultDeserializationContext;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;
import com.fasterxml.jackson.databind.type.SimpleType;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

public class ObjectReader extends ObjectCodec implements Serializable {
    private static final JavaType JSON_NODE_TYPE = SimpleType.constructUnsafe(JsonNode.class);
    protected final DeserializationConfig _config;
    protected final DefaultDeserializationContext _context;
    protected final DataFormatReaders _dataFormatReaders;
    private final TokenFilter _filter;
    protected final InjectableValues _injectableValues;
    protected final JsonFactory _parserFactory;
    protected final JsonDeserializer<Object> _rootDeserializer;
    protected final ConcurrentHashMap<JavaType, JsonDeserializer<Object>> _rootDeserializers;
    protected final FormatSchema _schema;
    protected final boolean _unwrapRoot;
    protected final Object _valueToUpdate;
    protected final JavaType _valueType;

    protected ObjectReader(ObjectMapper mapper, DeserializationConfig config, JavaType valueType, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues) {
        this._config = config;
        this._context = mapper._deserializationContext;
        this._rootDeserializers = mapper._rootDeserializers;
        this._parserFactory = mapper._jsonFactory;
        this._valueType = valueType;
        this._valueToUpdate = valueToUpdate;
        if (valueToUpdate == null || !valueType.isArrayType()) {
            this._schema = schema;
            this._injectableValues = injectableValues;
            this._unwrapRoot = config.useRootWrapping();
            this._rootDeserializer = _prefetchRootDeserializer(valueType);
            this._dataFormatReaders = null;
            this._filter = null;
            return;
        }
        throw new IllegalArgumentException("Can not update an array value");
    }

    protected ObjectReader(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        this._config = config;
        this._context = base._context;
        this._rootDeserializers = base._rootDeserializers;
        this._parserFactory = base._parserFactory;
        this._valueType = valueType;
        this._rootDeserializer = rootDeser;
        this._valueToUpdate = valueToUpdate;
        if (valueToUpdate == null || !valueType.isArrayType()) {
            this._schema = schema;
            this._injectableValues = injectableValues;
            this._unwrapRoot = config.useRootWrapping();
            this._dataFormatReaders = dataFormatReaders;
            this._filter = base._filter;
            return;
        }
        throw new IllegalArgumentException("Can not update an array value");
    }

    /* access modifiers changed from: protected */
    public ObjectReader _new(ObjectReader base, DeserializationConfig config, JavaType valueType, JsonDeserializer<Object> rootDeser, Object valueToUpdate, FormatSchema schema, InjectableValues injectableValues, DataFormatReaders dataFormatReaders) {
        return new ObjectReader(base, config, valueType, rootDeser, valueToUpdate, schema, injectableValues, dataFormatReaders);
    }

    /* access modifiers changed from: protected */
    public JsonToken _initForReading(DeserializationContext ctxt, JsonParser p) throws IOException {
        if (this._schema != null) {
            p.setSchema(this._schema);
        }
        this._config.initialize(p);
        JsonToken t = p.getCurrentToken();
        if (t == null) {
            t = p.nextToken();
            if (t == null) {
                ctxt.reportMissingContent(null, new Object[0]);
            }
        }
        return t;
    }

    /* Debug info: failed to restart local var, previous not found, register: 9 */
    public ObjectReader forType(JavaType valueType) {
        if (valueType != null && valueType.equals(this._valueType)) {
            return this;
        }
        JsonDeserializer<Object> rootDeser = _prefetchRootDeserializer(valueType);
        DataFormatReaders det = this._dataFormatReaders;
        if (det != null) {
            det = det.withType(valueType);
        }
        return _new(this, this._config, valueType, rootDeser, this._valueToUpdate, this._schema, this._injectableValues, det);
    }

    public ObjectReader forType(Class<?> valueType) {
        return forType(this._config.constructType(valueType));
    }

    public ObjectReader forType(TypeReference<?> valueTypeRef) {
        return forType(this._config.getTypeFactory().constructType(valueTypeRef.getType()));
    }

    public JsonFactory getFactory() {
        return this._parserFactory;
    }

    public <T> T readValue(JsonParser p) throws IOException {
        return _bind(p, this._valueToUpdate);
    }

    public <T> T readValue(JsonParser p, Class<T> valueType) throws IOException {
        return forType(valueType).readValue(p);
    }

    public <T> T readValue(JsonParser p, TypeReference<?> valueTypeRef) throws IOException {
        return forType(valueTypeRef).readValue(p);
    }

    public JsonParser treeAsTokens(TreeNode n) {
        return new TreeTraversingParser((JsonNode) n, this);
    }

    public <T extends TreeNode> T readTree(JsonParser p) throws IOException {
        return _bindAsTree(p);
    }

    public <T> T readValue(InputStream src) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(this._dataFormatReaders.findFormat(src), false);
        }
        return _bindAndClose(_considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(Reader src) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(src);
        }
        return _bindAndClose(_considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(String src) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(src);
        }
        return _bindAndClose(_considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(byte[] src) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            return _detectBindAndClose(src, 0, src.length);
        }
        return _bindAndClose(_considerFilter(this._parserFactory.createParser(src), false));
    }

    public <T> T readValue(JsonNode src) throws IOException, JsonProcessingException {
        if (this._dataFormatReaders != null) {
            _reportUndetectableSource(src);
        }
        return _bindAndClose(_considerFilter(treeAsTokens(src), false));
    }

    public <T> T treeToValue(TreeNode n, Class<T> valueType) throws JsonProcessingException {
        try {
            return readValue(treeAsTokens(n), valueType);
        } catch (JsonProcessingException e) {
            throw e;
        } catch (IOException e2) {
            throw new IllegalArgumentException(e2.getMessage(), e2);
        }
    }

    public void writeValue(JsonGenerator gen, Object value) throws IOException, JsonProcessingException {
        throw new UnsupportedOperationException("Not implemented for ObjectReader");
    }

    /* access modifiers changed from: protected */
    public Object _bind(JsonParser p, Object valueToUpdate) throws IOException {
        Object result;
        DeserializationContext ctxt = createDeserializationContext(p);
        JsonToken t = _initForReading(ctxt, p);
        if (t == JsonToken.VALUE_NULL) {
            if (valueToUpdate == null) {
                result = _findRootDeserializer(ctxt).getNullValue(ctxt);
            } else {
                result = valueToUpdate;
            }
        } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
            result = valueToUpdate;
        } else {
            JsonDeserializer<Object> deser = _findRootDeserializer(ctxt);
            if (this._unwrapRoot) {
                result = _unwrapAndDeserialize(p, ctxt, this._valueType, deser);
            } else if (valueToUpdate == null) {
                result = deser.deserialize(p, ctxt);
            } else {
                deser.deserialize(p, ctxt, valueToUpdate);
                result = valueToUpdate;
            }
        }
        p.clearCurrentToken();
        return result;
    }

    /* access modifiers changed from: protected */
    public JsonParser _considerFilter(JsonParser p, boolean multiValue) {
        return (this._filter == null || FilteringParserDelegate.class.isInstance(p)) ? p : new FilteringParserDelegate(p, this._filter, false, multiValue);
    }

    /* access modifiers changed from: protected */
    public Object _bindAndClose(JsonParser p0) throws IOException {
        Object result;
        JsonParser p = p0;
        Throwable th = null;
        try {
            DeserializationContext ctxt = createDeserializationContext(p);
            JsonToken t = _initForReading(ctxt, p);
            if (t == JsonToken.VALUE_NULL) {
                if (this._valueToUpdate == null) {
                    result = _findRootDeserializer(ctxt).getNullValue(ctxt);
                } else {
                    result = this._valueToUpdate;
                }
            } else if (t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
                result = this._valueToUpdate;
            } else {
                JsonDeserializer<Object> deser = _findRootDeserializer(ctxt);
                if (this._unwrapRoot) {
                    result = _unwrapAndDeserialize(p, ctxt, this._valueType, deser);
                } else if (this._valueToUpdate == null) {
                    result = deser.deserialize(p, ctxt);
                } else {
                    deser.deserialize(p, ctxt, this._valueToUpdate);
                    result = this._valueToUpdate;
                }
            }
            if (p != null) {
                if (th != null) {
                    try {
                        p.close();
                    } catch (Throwable x2) {
                        th.addSuppressed(x2);
                    }
                } else {
                    p.close();
                }
            }
            return result;
        } catch (Throwable th2) {
            Throwable th3 = th2;
            th = r6;
            th = th3;
        }
        throw th;
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
    }

    /* access modifiers changed from: protected */
    public JsonNode _bindAsTree(JsonParser p) throws IOException {
        JsonNode result;
        DeserializationContext ctxt = createDeserializationContext(p);
        JsonToken t = _initForReading(ctxt, p);
        if (t == JsonToken.VALUE_NULL || t == JsonToken.END_ARRAY || t == JsonToken.END_OBJECT) {
            result = NullNode.instance;
        } else {
            JsonDeserializer<Object> deser = _findTreeDeserializer(ctxt);
            if (this._unwrapRoot) {
                result = (JsonNode) _unwrapAndDeserialize(p, ctxt, JSON_NODE_TYPE, deser);
            } else {
                result = (JsonNode) deser.deserialize(p, ctxt);
            }
        }
        p.clearCurrentToken();
        return result;
    }

    /* access modifiers changed from: protected */
    public Object _unwrapAndDeserialize(JsonParser p, DeserializationContext ctxt, JavaType rootType, JsonDeserializer<Object> deser) throws IOException {
        Object result;
        String expSimpleName = this._config.findRootName(rootType).getSimpleName();
        if (p.getCurrentToken() != JsonToken.START_OBJECT) {
            ctxt.reportWrongTokenException(p, JsonToken.START_OBJECT, "Current token not START_OBJECT (needed to unwrap root name '%s'), but %s", expSimpleName, p.getCurrentToken());
        }
        if (p.nextToken() != JsonToken.FIELD_NAME) {
            ctxt.reportWrongTokenException(p, JsonToken.FIELD_NAME, "Current token not FIELD_NAME (to contain expected root name '%s'), but %s", expSimpleName, p.getCurrentToken());
        }
        String actualName = p.getCurrentName();
        if (!expSimpleName.equals(actualName)) {
            ctxt.reportMappingException("Root name '%s' does not match expected ('%s') for type %s", actualName, expSimpleName, rootType);
        }
        p.nextToken();
        if (this._valueToUpdate == null) {
            result = deser.deserialize(p, ctxt);
        } else {
            deser.deserialize(p, ctxt, this._valueToUpdate);
            result = this._valueToUpdate;
        }
        if (p.nextToken() != JsonToken.END_OBJECT) {
            ctxt.reportWrongTokenException(p, JsonToken.END_OBJECT, "Current token not END_OBJECT (to match wrapper object with root name '%s'), but %s", expSimpleName, p.getCurrentToken());
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(byte[] src, int offset, int length) throws IOException {
        Match match = this._dataFormatReaders.findFormat(src, offset, length);
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        return match.getReader()._bindAndClose(match.createParserWithMatch());
    }

    /* access modifiers changed from: protected */
    public Object _detectBindAndClose(Match match, boolean forceClosing) throws IOException {
        if (!match.hasMatch()) {
            _reportUnkownFormat(this._dataFormatReaders, match);
        }
        JsonParser p = match.createParserWithMatch();
        if (forceClosing) {
            p.enable(Feature.AUTO_CLOSE_SOURCE);
        }
        return match.getReader()._bindAndClose(p);
    }

    /* access modifiers changed from: protected */
    public void _reportUnkownFormat(DataFormatReaders detector, Match match) throws JsonProcessingException {
        throw new JsonParseException(null, "Can not detect format from input, does not look like any of detectable formats " + detector.toString());
    }

    /* access modifiers changed from: protected */
    public DefaultDeserializationContext createDeserializationContext(JsonParser p) {
        return this._context.createInstance(this._config, p, this._injectableValues);
    }

    /* access modifiers changed from: protected */
    public void _reportUndetectableSource(Object src) throws JsonProcessingException {
        throw new JsonParseException(null, "Can not use source of type " + src.getClass().getName() + " with format auto-detection: must be byte- not char-based");
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findRootDeserializer(DeserializationContext ctxt) throws JsonMappingException {
        if (this._rootDeserializer != null) {
            return this._rootDeserializer;
        }
        JavaType t = this._valueType;
        if (t == null) {
            ctxt.reportMappingException("No value type configured for ObjectReader", new Object[0]);
        }
        JsonDeserializer<Object> deser = (JsonDeserializer) this._rootDeserializers.get(t);
        if (deser != null) {
            return deser;
        }
        JsonDeserializer<Object> deser2 = ctxt.findRootValueDeserializer(t);
        if (deser2 == null) {
            ctxt.reportMappingException("Can not find a deserializer for type %s", t);
        }
        this._rootDeserializers.put(t, deser2);
        return deser2;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findTreeDeserializer(DeserializationContext ctxt) throws JsonMappingException {
        JsonDeserializer<Object> deser = (JsonDeserializer) this._rootDeserializers.get(JSON_NODE_TYPE);
        if (deser == null) {
            deser = ctxt.findRootValueDeserializer(JSON_NODE_TYPE);
            if (deser == null) {
                ctxt.reportMappingException("Can not find a deserializer for type %s", JSON_NODE_TYPE);
            }
            this._rootDeserializers.put(JSON_NODE_TYPE, deser);
        }
        return deser;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _prefetchRootDeserializer(JavaType valueType) {
        JsonDeserializer<Object> deser = null;
        if (valueType != null && this._config.isEnabled(DeserializationFeature.EAGER_DESERIALIZER_FETCH)) {
            deser = (JsonDeserializer) this._rootDeserializers.get(valueType);
            if (deser == null) {
                try {
                    deser = createDeserializationContext(null).findRootValueDeserializer(valueType);
                    if (deser != null) {
                        this._rootDeserializers.put(valueType, deser);
                    }
                } catch (JsonProcessingException e) {
                }
            }
        }
        return deser;
    }
}
