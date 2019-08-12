package com.fasterxml.jackson.databind.deser.impl;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import java.io.IOException;
import java.util.HashMap;

public final class PropertyBasedCreator {
    protected final SettableBeanProperty[] _allProperties;
    protected final int _propertyCount;
    protected final HashMap<String, SettableBeanProperty> _propertyLookup;
    protected final ValueInstantiator _valueInstantiator;

    static class CaseInsensitiveMap extends HashMap<String, SettableBeanProperty> {
        CaseInsensitiveMap() {
        }

        public SettableBeanProperty get(Object key0) {
            return (SettableBeanProperty) super.get(((String) key0).toLowerCase());
        }

        public SettableBeanProperty put(String key, SettableBeanProperty value) {
            return (SettableBeanProperty) super.put(key.toLowerCase(), value);
        }
    }

    protected PropertyBasedCreator(ValueInstantiator valueInstantiator, SettableBeanProperty[] creatorProps, boolean caseInsensitive) {
        this._valueInstantiator = valueInstantiator;
        if (caseInsensitive) {
            this._propertyLookup = new CaseInsensitiveMap();
        } else {
            this._propertyLookup = new HashMap<>();
        }
        int len = creatorProps.length;
        this._propertyCount = len;
        this._allProperties = new SettableBeanProperty[len];
        for (int i = 0; i < len; i++) {
            SettableBeanProperty prop = creatorProps[i];
            this._allProperties[i] = prop;
            this._propertyLookup.put(prop.getName(), prop);
        }
    }

    public static PropertyBasedCreator construct(DeserializationContext ctxt, ValueInstantiator valueInstantiator, SettableBeanProperty[] srcProps) throws JsonMappingException {
        int len = srcProps.length;
        SettableBeanProperty[] creatorProps = new SettableBeanProperty[len];
        for (int i = 0; i < len; i++) {
            SettableBeanProperty prop = srcProps[i];
            if (!prop.hasValueDeserializer()) {
                prop = prop.withValueDeserializer(ctxt.findContextualValueDeserializer(prop.getType(), prop));
            }
            creatorProps[i] = prop;
        }
        return new PropertyBasedCreator(valueInstantiator, creatorProps, ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES));
    }

    public SettableBeanProperty findCreatorProperty(String name) {
        return (SettableBeanProperty) this._propertyLookup.get(name);
    }

    public PropertyValueBuffer startBuilding(JsonParser p, DeserializationContext ctxt, ObjectIdReader oir) {
        return new PropertyValueBuffer(p, ctxt, this._propertyCount, oir);
    }

    public Object build(DeserializationContext ctxt, PropertyValueBuffer buffer) throws IOException {
        Object bean = this._valueInstantiator.createFromObjectWith(ctxt, this._allProperties, buffer);
        if (bean != null) {
            bean = buffer.handleIdValue(ctxt, bean);
            for (PropertyValue pv = buffer.buffered(); pv != null; pv = pv.next) {
                pv.assign(bean);
            }
        }
        return bean;
    }
}
