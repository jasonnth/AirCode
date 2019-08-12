package com.fasterxml.jackson.databind;

import java.io.Serializable;

public class PropertyMetadata implements Serializable {
    public static final PropertyMetadata STD_OPTIONAL = new PropertyMetadata(Boolean.FALSE, null, null, null);
    public static final PropertyMetadata STD_REQUIRED = new PropertyMetadata(Boolean.TRUE, null, null, null);
    public static final PropertyMetadata STD_REQUIRED_OR_OPTIONAL = new PropertyMetadata(null, null, null, null);
    protected final String _defaultValue;
    protected final String _description;
    protected final Integer _index;
    protected final Boolean _required;

    protected PropertyMetadata(Boolean req, String desc, Integer index, String def) {
        this._required = req;
        this._description = desc;
        this._index = index;
        if (def == null || def.isEmpty()) {
            def = null;
        }
        this._defaultValue = def;
    }

    public static PropertyMetadata construct(Boolean req, String desc, Integer index, String defaultValue) {
        if (desc != null || index != null || defaultValue != null) {
            return new PropertyMetadata(req, desc, index, defaultValue);
        }
        if (req == null) {
            return STD_REQUIRED_OR_OPTIONAL;
        }
        return req.booleanValue() ? STD_REQUIRED : STD_OPTIONAL;
    }

    /* access modifiers changed from: protected */
    public Object readResolve() {
        if (this._description != null || this._index != null || this._defaultValue != null) {
            return this;
        }
        if (this._required == null) {
            return STD_REQUIRED_OR_OPTIONAL;
        }
        return this._required.booleanValue() ? STD_REQUIRED : STD_OPTIONAL;
    }

    public PropertyMetadata withDescription(String desc) {
        return new PropertyMetadata(this._required, desc, this._index, this._defaultValue);
    }

    public boolean isRequired() {
        return this._required != null && this._required.booleanValue();
    }
}
