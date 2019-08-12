package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SimpleMixInResolver implements MixInResolver, Serializable {
    protected Map<ClassKey, Class<?>> _localMixIns;
    protected final MixInResolver _overrides;

    public SimpleMixInResolver(MixInResolver overrides) {
        this._overrides = overrides;
    }

    public void addLocalDefinition(Class<?> target, Class<?> mixinSource) {
        if (this._localMixIns == null) {
            this._localMixIns = new HashMap();
        }
        this._localMixIns.put(new ClassKey(target), mixinSource);
    }

    public Class<?> findMixInClassFor(Class<?> cls) {
        Class<?> mixin = this._overrides == null ? null : this._overrides.findMixInClassFor(cls);
        if (mixin != null || this._localMixIns == null) {
            return mixin;
        }
        return (Class) this._localMixIns.get(new ClassKey(cls));
    }
}
