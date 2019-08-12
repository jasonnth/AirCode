package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.SubtypeResolver;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class StdSubtypeResolver extends SubtypeResolver implements Serializable {
    protected LinkedHashSet<NamedType> _registeredSubtypes;

    public void registerSubtypes(NamedType... types) {
        if (this._registeredSubtypes == null) {
            this._registeredSubtypes = new LinkedHashSet<>();
        }
        for (NamedType type : types) {
            this._registeredSubtypes.add(type);
        }
    }

    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        Class<?> rawBase = baseType == null ? property.getRawType() : baseType.getRawClass();
        HashMap<NamedType, NamedType> collected = new HashMap<>();
        if (this._registeredSubtypes != null) {
            Iterator i$ = this._registeredSubtypes.iterator();
            while (i$.hasNext()) {
                NamedType subtype = (NamedType) i$.next();
                if (rawBase.isAssignableFrom(subtype.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, ai, collected);
                }
            }
        }
        Collection<NamedType> st = ai.findSubtypes(property);
        if (st != null) {
            for (NamedType nt : st) {
                _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(nt.getType(), config), nt, config, ai, collected);
            }
        }
        _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(rawBase, config), new NamedType(rawBase, null), config, ai, collected);
        return new ArrayList(collected.values());
    }

    public Collection<NamedType> collectAndResolveSubtypesByClass(MapperConfig<?> config, AnnotatedClass type) {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        HashMap<NamedType, NamedType> subtypes = new HashMap<>();
        if (this._registeredSubtypes != null) {
            Class<?> rawBase = type.getRawType();
            Iterator i$ = this._registeredSubtypes.iterator();
            while (i$.hasNext()) {
                NamedType subtype = (NamedType) i$.next();
                if (rawBase.isAssignableFrom(subtype.getType())) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, ai, subtypes);
                }
            }
        }
        _collectAndResolve(type, new NamedType(type.getRawType(), null), config, ai, subtypes);
        return new ArrayList(subtypes.values());
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedMember property, JavaType baseType) {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        Class<?> rawBase = baseType == null ? property.getRawType() : baseType.getRawClass();
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(rawBase, config), new NamedType(rawBase, null), config, hashSet, linkedHashMap);
        Collection<NamedType> st = ai.findSubtypes(property);
        if (st != null) {
            for (NamedType nt : st) {
                _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(nt.getType(), config), nt, config, hashSet, linkedHashMap);
            }
        }
        if (this._registeredSubtypes != null) {
            Iterator i$ = this._registeredSubtypes.iterator();
            while (i$.hasNext()) {
                NamedType subtype = (NamedType) i$.next();
                if (rawBase.isAssignableFrom(subtype.getType())) {
                    _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, hashSet, linkedHashMap);
                }
            }
        }
        return _combineNamedAndUnnamed(hashSet, linkedHashMap);
    }

    public Collection<NamedType> collectAndResolveSubtypesByTypeId(MapperConfig<?> config, AnnotatedClass type) {
        HashSet hashSet = new HashSet();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        _collectAndResolveByTypeId(type, new NamedType(type.getRawType(), null), config, hashSet, linkedHashMap);
        if (this._registeredSubtypes != null) {
            Class<?> rawBase = type.getRawType();
            Iterator i$ = this._registeredSubtypes.iterator();
            while (i$.hasNext()) {
                NamedType subtype = (NamedType) i$.next();
                if (rawBase.isAssignableFrom(subtype.getType())) {
                    _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, hashSet, linkedHashMap);
                }
            }
        }
        return _combineNamedAndUnnamed(hashSet, linkedHashMap);
    }

    /* access modifiers changed from: protected */
    public void _collectAndResolve(AnnotatedClass annotatedType, NamedType namedType, MapperConfig<?> config, AnnotationIntrospector ai, HashMap<NamedType, NamedType> collectedSubtypes) {
        if (!namedType.hasName()) {
            String name = ai.findTypeName(annotatedType);
            if (name != null) {
                namedType = new NamedType(namedType.getType(), name);
            }
        }
        if (!collectedSubtypes.containsKey(namedType)) {
            collectedSubtypes.put(namedType, namedType);
            Collection<NamedType> st = ai.findSubtypes(annotatedType);
            if (st != null && !st.isEmpty()) {
                for (NamedType subtype : st) {
                    _collectAndResolve(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, ai, collectedSubtypes);
                }
            }
        } else if (namedType.hasName() && !((NamedType) collectedSubtypes.get(namedType)).hasName()) {
            collectedSubtypes.put(namedType, namedType);
        }
    }

    /* access modifiers changed from: protected */
    public void _collectAndResolveByTypeId(AnnotatedClass annotatedType, NamedType namedType, MapperConfig<?> config, Set<Class<?>> typesHandled, Map<String, NamedType> byName) {
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        if (!namedType.hasName()) {
            String name = ai.findTypeName(annotatedType);
            if (name != null) {
                namedType = new NamedType(namedType.getType(), name);
            }
        }
        if (namedType.hasName()) {
            byName.put(namedType.getName(), namedType);
        }
        if (typesHandled.add(namedType.getType())) {
            Collection<NamedType> st = ai.findSubtypes(annotatedType);
            if (st != null && !st.isEmpty()) {
                for (NamedType subtype : st) {
                    _collectAndResolveByTypeId(AnnotatedClass.constructWithoutSuperTypes(subtype.getType(), config), subtype, config, typesHandled, byName);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public Collection<NamedType> _combineNamedAndUnnamed(Set<Class<?>> typesHandled, Map<String, NamedType> byName) {
        ArrayList<NamedType> result = new ArrayList<>(byName.values());
        for (NamedType t : byName.values()) {
            typesHandled.remove(t.getType());
        }
        for (Class<?> cls : typesHandled) {
            result.add(new NamedType(cls));
        }
        return result;
    }
}
