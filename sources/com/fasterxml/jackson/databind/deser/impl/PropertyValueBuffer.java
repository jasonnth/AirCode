package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.SettableAnyProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import java.io.IOException;
import java.util.BitSet;

public class PropertyValueBuffer {
    protected PropertyValue _buffered;
    protected final DeserializationContext _context;
    protected final Object[] _creatorParameters;
    protected Object _idValue;
    protected final ObjectIdReader _objectIdReader;
    protected int _paramsNeeded;
    protected int _paramsSeen;
    protected final BitSet _paramsSeenBig;
    protected final JsonParser _parser;

    public PropertyValueBuffer(JsonParser p, DeserializationContext ctxt, int paramCount, ObjectIdReader oir) {
        this._parser = p;
        this._context = ctxt;
        this._paramsNeeded = paramCount;
        this._objectIdReader = oir;
        this._creatorParameters = new Object[paramCount];
        if (paramCount < 32) {
            this._paramsSeenBig = null;
        } else {
            this._paramsSeenBig = new BitSet();
        }
    }

    public Object[] getParameters(SettableBeanProperty[] props) throws JsonMappingException {
        if (this._paramsNeeded > 0) {
            if (this._paramsSeenBig != null) {
                int len = this._creatorParameters.length;
                int ix = 0;
                while (true) {
                    int ix2 = this._paramsSeenBig.nextClearBit(ix);
                    if (ix2 >= len) {
                        break;
                    }
                    this._creatorParameters[ix2] = _findMissing(props[ix2]);
                    ix = ix2 + 1;
                }
            } else {
                int mask = this._paramsSeen;
                int ix3 = 0;
                int len2 = this._creatorParameters.length;
                while (ix3 < len2) {
                    if ((mask & 1) == 0) {
                        this._creatorParameters[ix3] = _findMissing(props[ix3]);
                    }
                    ix3++;
                    mask >>= 1;
                }
            }
        }
        if (this._context.isEnabled(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES)) {
            for (int ix4 = 0; ix4 < props.length; ix4++) {
                if (this._creatorParameters[ix4] == null) {
                    this._context.reportMappingException("Null value for creator property '%s'; DeserializationFeature.FAIL_ON_NULL_FOR_CREATOR_PARAMETERS enabled", props[ix4].getName(), Integer.valueOf(props[ix4].getCreatorIndex()));
                }
            }
        }
        return this._creatorParameters;
    }

    /* access modifiers changed from: protected */
    public Object _findMissing(SettableBeanProperty prop) throws JsonMappingException {
        if (prop.getInjectableValueId() != null) {
            return this._context.findInjectableValue(prop.getInjectableValueId(), prop, null);
        }
        if (prop.isRequired()) {
            this._context.reportMappingException("Missing required creator property '%s' (index %d)", prop.getName(), Integer.valueOf(prop.getCreatorIndex()));
        }
        if (this._context.isEnabled(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES)) {
            this._context.reportMappingException("Missing creator property '%s' (index %d); DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES enabled", prop.getName(), Integer.valueOf(prop.getCreatorIndex()));
        }
        return prop.getValueDeserializer().getNullValue(this._context);
    }

    public boolean readIdProperty(String propName) throws IOException {
        if (this._objectIdReader == null || !propName.equals(this._objectIdReader.propertyName.getSimpleName())) {
            return false;
        }
        this._idValue = this._objectIdReader.readObjectReference(this._parser, this._context);
        return true;
    }

    public Object handleIdValue(DeserializationContext ctxt, Object bean) throws IOException {
        if (this._objectIdReader == null) {
            return bean;
        }
        if (this._idValue != null) {
            ctxt.findObjectId(this._idValue, this._objectIdReader.generator, this._objectIdReader.resolver).bindItem(bean);
            SettableBeanProperty idProp = this._objectIdReader.idProperty;
            if (idProp != null) {
                return idProp.setAndReturn(bean, this._idValue);
            }
            return bean;
        }
        ctxt.reportUnresolvedObjectId(this._objectIdReader, bean);
        return bean;
    }

    /* access modifiers changed from: protected */
    public PropertyValue buffered() {
        return this._buffered;
    }

    public boolean assignParameter(SettableBeanProperty prop, Object value) {
        int ix = prop.getCreatorIndex();
        this._creatorParameters[ix] = value;
        if (this._paramsSeenBig == null) {
            int old = this._paramsSeen;
            int newValue = old | (1 << ix);
            if (old == newValue) {
                return false;
            }
            this._paramsSeen = newValue;
            int i = this._paramsNeeded - 1;
            this._paramsNeeded = i;
            if (i > 0) {
                return false;
            }
            if (this._objectIdReader == null || this._idValue != null) {
                return true;
            }
            return false;
        } else if (this._paramsSeenBig.get(ix)) {
            return false;
        } else {
            this._paramsSeenBig.set(ix);
            int i2 = this._paramsNeeded - 1;
            this._paramsNeeded = i2;
            if (i2 <= 0) {
            }
            return false;
        }
    }

    public void bufferProperty(SettableBeanProperty prop, Object value) {
        this._buffered = new Regular(this._buffered, value, prop);
    }

    public void bufferAnyProperty(SettableAnyProperty prop, String propName, Object value) {
        this._buffered = new Any(this._buffered, value, prop, propName);
    }

    public void bufferMapProperty(Object key, Object value) {
        this._buffered = new Map(this._buffered, value, key);
    }
}
