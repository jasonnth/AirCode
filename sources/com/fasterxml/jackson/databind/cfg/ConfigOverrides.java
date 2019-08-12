package com.fasterxml.jackson.databind.cfg;

import java.io.Serializable;
import java.util.Map;

public class ConfigOverrides implements Serializable {
    protected Map<Class<?>, Object> _overrides = null;

    public ConfigOverride findOverride(Class<?> type) {
        if (this._overrides == null) {
            return null;
        }
        return (ConfigOverride) this._overrides.get(type);
    }
}
