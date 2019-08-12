package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector.MixInResolver;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.LRUMap;
import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

public class BasicClassIntrospector extends ClassIntrospector implements Serializable {
    protected static final BasicBeanDescription BOOLEAN_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Boolean.TYPE), AnnotatedClass.constructWithoutSuperTypes(Boolean.TYPE, null));
    protected static final BasicBeanDescription INT_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Integer.TYPE), AnnotatedClass.constructWithoutSuperTypes(Integer.TYPE, null));
    protected static final BasicBeanDescription LONG_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(Long.TYPE), AnnotatedClass.constructWithoutSuperTypes(Long.TYPE, null));
    protected static final BasicBeanDescription STRING_DESC = BasicBeanDescription.forOtherUse(null, SimpleType.constructUnsafe(String.class), AnnotatedClass.constructWithoutSuperTypes(String.class, null));
    @Deprecated
    public static final BasicClassIntrospector instance = new BasicClassIntrospector();
    protected final LRUMap<JavaType, BasicBeanDescription> _cachedFCA = new LRUMap<>(16, 64);

    public BasicBeanDescription forSerialization(SerializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findStdTypeDesc(type);
        if (desc == null) {
            desc = _findStdJdkCollectionDesc(cfg, type);
            if (desc == null) {
                desc = BasicBeanDescription.forSerialization(collectProperties(cfg, type, r, true, "set"));
            }
            this._cachedFCA.putIfAbsent(type, desc);
        }
        return desc;
    }

    public BasicBeanDescription forDeserialization(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findStdTypeDesc(type);
        if (desc == null) {
            desc = _findStdJdkCollectionDesc(cfg, type);
            if (desc == null) {
                desc = BasicBeanDescription.forDeserialization(collectProperties(cfg, type, r, false, "set"));
            }
            this._cachedFCA.putIfAbsent(type, desc);
        }
        return desc;
    }

    public BasicBeanDescription forDeserializationWithBuilder(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = BasicBeanDescription.forDeserialization(collectPropertiesWithBuilder(cfg, type, r, false));
        this._cachedFCA.putIfAbsent(type, desc);
        return desc;
    }

    public BasicBeanDescription forCreation(DeserializationConfig cfg, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findStdTypeDesc(type);
        if (desc != null) {
            return desc;
        }
        BasicBeanDescription desc2 = _findStdJdkCollectionDesc(cfg, type);
        if (desc2 != null) {
            return desc2;
        }
        return BasicBeanDescription.forDeserialization(collectProperties(cfg, type, r, false, "set"));
    }

    public BasicBeanDescription forClassAnnotations(MapperConfig<?> config, JavaType type, MixInResolver r) {
        BasicBeanDescription desc = _findStdTypeDesc(type);
        if (desc != null) {
            return desc;
        }
        BasicBeanDescription desc2 = (BasicBeanDescription) this._cachedFCA.get(type);
        if (desc2 != null) {
            return desc2;
        }
        BasicBeanDescription desc3 = BasicBeanDescription.forOtherUse(config, type, AnnotatedClass.construct(type, config, r));
        this._cachedFCA.put(type, desc3);
        return desc3;
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector collectProperties(MapperConfig<?> config, JavaType type, MixInResolver r, boolean forSerialization, String mutatorPrefix) {
        return constructPropertyCollector(config, AnnotatedClass.construct(type, config, r), type, forSerialization, mutatorPrefix);
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector collectPropertiesWithBuilder(MapperConfig<?> config, JavaType type, MixInResolver r, boolean forSerialization) {
        AnnotationIntrospector ai;
        if (config.isAnnotationProcessingEnabled()) {
            ai = config.getAnnotationIntrospector();
        } else {
            ai = null;
        }
        AnnotatedClass ac = AnnotatedClass.construct(type, config, r);
        Value builderConfig = ai == null ? null : ai.findPOJOBuilderConfig(ac);
        return constructPropertyCollector(config, ac, type, forSerialization, builderConfig == null ? "with" : builderConfig.withPrefix);
    }

    /* access modifiers changed from: protected */
    public POJOPropertiesCollector constructPropertyCollector(MapperConfig<?> config, AnnotatedClass ac, JavaType type, boolean forSerialization, String mutatorPrefix) {
        return new POJOPropertiesCollector(config, forSerialization, type, ac, mutatorPrefix);
    }

    /* access modifiers changed from: protected */
    public BasicBeanDescription _findStdTypeDesc(JavaType type) {
        Class<?> cls = type.getRawClass();
        if (cls.isPrimitive()) {
            if (cls == Boolean.TYPE) {
                return BOOLEAN_DESC;
            }
            if (cls == Integer.TYPE) {
                return INT_DESC;
            }
            if (cls == Long.TYPE) {
                return LONG_DESC;
            }
        } else if (cls == String.class) {
            return STRING_DESC;
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean _isStdJDKCollection(JavaType type) {
        if (!type.isContainerType() || type.isArrayType()) {
            return false;
        }
        Class<?> raw = type.getRawClass();
        String pkgName = ClassUtil.getPackageName(raw);
        if (pkgName == null) {
            return false;
        }
        if (!pkgName.startsWith("java.lang") && !pkgName.startsWith("java.util")) {
            return false;
        }
        if (Collection.class.isAssignableFrom(raw) || Map.class.isAssignableFrom(raw)) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public BasicBeanDescription _findStdJdkCollectionDesc(MapperConfig<?> cfg, JavaType type) {
        if (_isStdJDKCollection(type)) {
            return BasicBeanDescription.forOtherUse(cfg, type, AnnotatedClass.construct(type, cfg));
        }
        return null;
    }
}
