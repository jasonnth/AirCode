package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class POJOPropertiesCollector {
    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList<AnnotatedMember> _anyGetters;
    protected LinkedList<AnnotatedMember> _anySetterField;
    protected LinkedList<AnnotatedMethod> _anySetters;
    protected final AnnotatedClass _classDef;
    protected boolean _collected;
    protected final MapperConfig<?> _config;
    protected LinkedList<POJOPropertyBuilder> _creatorProperties;
    protected final boolean _forSerialization;
    protected HashSet<String> _ignoredPropertyNames;
    protected LinkedHashMap<Object, AnnotatedMember> _injectables;
    protected LinkedList<AnnotatedMethod> _jsonValueGetters;
    protected final String _mutatorPrefix;
    protected LinkedHashMap<String, POJOPropertyBuilder> _properties;
    protected final boolean _stdBeanNaming;
    protected final JavaType _type;
    protected final VisibilityChecker<?> _visibilityChecker;

    protected POJOPropertiesCollector(MapperConfig<?> config, boolean forSerialization, JavaType type, AnnotatedClass classDef, String mutatorPrefix) {
        this._config = config;
        this._stdBeanNaming = config.isEnabled(MapperFeature.USE_STD_BEAN_NAMING);
        this._forSerialization = forSerialization;
        this._type = type;
        this._classDef = classDef;
        if (mutatorPrefix == null) {
            mutatorPrefix = "set";
        }
        this._mutatorPrefix = mutatorPrefix;
        this._annotationIntrospector = config.isAnnotationProcessingEnabled() ? this._config.getAnnotationIntrospector() : null;
        if (this._annotationIntrospector == null) {
            this._visibilityChecker = this._config.getDefaultVisibilityChecker();
        } else {
            this._visibilityChecker = this._annotationIntrospector.findAutoDetectVisibility(classDef, this._config.getDefaultVisibilityChecker());
        }
    }

    public MapperConfig<?> getConfig() {
        return this._config;
    }

    public JavaType getType() {
        return this._type;
    }

    public AnnotatedClass getClassDef() {
        return this._classDef;
    }

    public List<BeanPropertyDefinition> getProperties() {
        return new ArrayList(getPropertyMap().values());
    }

    public Map<Object, AnnotatedMember> getInjectables() {
        if (!this._collected) {
            collectAll();
        }
        return this._injectables;
    }

    public AnnotatedMethod getJsonValueMethod() {
        if (!this._collected) {
            collectAll();
        }
        if (this._jsonValueGetters == null) {
            return null;
        }
        if (this._jsonValueGetters.size() > 1) {
            reportProblem("Multiple value properties defined (" + this._jsonValueGetters.get(0) + " vs " + this._jsonValueGetters.get(1) + ")");
        }
        return (AnnotatedMethod) this._jsonValueGetters.get(0);
    }

    public AnnotatedMember getAnyGetter() {
        if (!this._collected) {
            collectAll();
        }
        if (this._anyGetters == null) {
            return null;
        }
        if (this._anyGetters.size() > 1) {
            reportProblem("Multiple 'any-getters' defined (" + this._anyGetters.get(0) + " vs " + this._anyGetters.get(1) + ")");
        }
        return (AnnotatedMember) this._anyGetters.getFirst();
    }

    public AnnotatedMember getAnySetterField() {
        if (!this._collected) {
            collectAll();
        }
        if (this._anySetterField == null) {
            return null;
        }
        if (this._anySetterField.size() > 1) {
            reportProblem("Multiple 'any-Setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetterField.get(1) + ")");
        }
        return (AnnotatedMember) this._anySetterField.getFirst();
    }

    public AnnotatedMethod getAnySetterMethod() {
        if (!this._collected) {
            collectAll();
        }
        if (this._anySetters == null) {
            return null;
        }
        if (this._anySetters.size() > 1) {
            reportProblem("Multiple 'any-setters' defined (" + this._anySetters.get(0) + " vs " + this._anySetters.get(1) + ")");
        }
        return (AnnotatedMethod) this._anySetters.getFirst();
    }

    public Set<String> getIgnoredPropertyNames() {
        return this._ignoredPropertyNames;
    }

    public ObjectIdInfo getObjectIdInfo() {
        if (this._annotationIntrospector == null) {
            return null;
        }
        ObjectIdInfo info = this._annotationIntrospector.findObjectIdInfo(this._classDef);
        if (info != null) {
            return this._annotationIntrospector.findObjectReferenceInfo(this._classDef, info);
        }
        return info;
    }

    /* access modifiers changed from: protected */
    public Map<String, POJOPropertyBuilder> getPropertyMap() {
        if (!this._collected) {
            collectAll();
        }
        return this._properties;
    }

    /* access modifiers changed from: protected */
    public void collectAll() {
        LinkedHashMap<String, POJOPropertyBuilder> props = new LinkedHashMap<>();
        _addFields(props);
        _addMethods(props);
        if (!this._classDef.isNonStaticInnerClass()) {
            _addCreators(props);
        }
        _addInjectables(props);
        _removeUnwantedProperties(props);
        for (POJOPropertyBuilder property : props.values()) {
            property.mergeAnnotations(this._forSerialization);
        }
        _removeUnwantedAccessor(props);
        _renameProperties(props);
        PropertyNamingStrategy naming = _findNamingStrategy();
        if (naming != null) {
            _renameUsing(props, naming);
        }
        for (POJOPropertyBuilder property2 : props.values()) {
            property2.trimByVisibility();
        }
        if (this._config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME)) {
            _renameWithWrappers(props);
        }
        _sortProperties(props);
        this._properties = props;
        this._collected = true;
    }

    /* access modifiers changed from: protected */
    public void _addFields(Map<String, POJOPropertyBuilder> props) {
        PropertyName pn;
        AnnotationIntrospector ai = this._annotationIntrospector;
        boolean pruneFinalFields = !this._forSerialization && !this._config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS);
        boolean transientAsIgnoral = this._config.isEnabled(MapperFeature.PROPAGATE_TRANSIENT_MARKER);
        for (AnnotatedField f : this._classDef.fields()) {
            String implName = ai == null ? null : ai.findImplicitPropertyName(f);
            if (implName == null) {
                implName = f.getName();
            }
            if (ai == null) {
                pn = null;
            } else if (this._forSerialization) {
                pn = ai.findNameForSerialization(f);
            } else {
                pn = ai.findNameForDeserialization(f);
            }
            boolean hasName = pn != null;
            boolean nameExplicit = hasName;
            if (nameExplicit && pn.isEmpty()) {
                pn = _propNameFromSimple(implName);
                nameExplicit = false;
            }
            boolean visible = pn != null;
            if (!visible) {
                visible = this._visibilityChecker.isFieldVisible(f);
            }
            boolean ignored = ai != null && ai.hasIgnoreMarker(f);
            if (f.isTransient() && !hasName) {
                visible = false;
                if (transientAsIgnoral) {
                    ignored = true;
                }
            }
            if (!pruneFinalFields || pn != null || ignored || !Modifier.isFinal(f.getModifiers())) {
                if (f.hasAnnotation(JsonAnySetter.class)) {
                    if (this._anySetterField == null) {
                        this._anySetterField = new LinkedList<>();
                    }
                    this._anySetterField.add(f);
                }
                _property(props, implName).addField(f, pn, nameExplicit, visible, ignored);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addCreators(Map<String, POJOPropertyBuilder> props) {
        if (this._annotationIntrospector != null) {
            for (AnnotatedConstructor ctor : this._classDef.getConstructors()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int len = ctor.getParameterCount();
                for (int i = 0; i < len; i++) {
                    _addCreatorParam(props, ctor.getParameter(i));
                }
            }
            for (AnnotatedMethod factory : this._classDef.getStaticMethods()) {
                if (this._creatorProperties == null) {
                    this._creatorProperties = new LinkedList<>();
                }
                int len2 = factory.getParameterCount();
                for (int i2 = 0; i2 < len2; i2++) {
                    _addCreatorParam(props, factory.getParameter(i2));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addCreatorParam(Map<String, POJOPropertyBuilder> props, AnnotatedParameter param) {
        boolean expl;
        String impl = this._annotationIntrospector.findImplicitPropertyName(param);
        if (impl == null) {
            impl = "";
        }
        PropertyName pn = this._annotationIntrospector.findNameForDeserialization(param);
        if (pn == null || pn.isEmpty()) {
            expl = false;
        } else {
            expl = true;
        }
        if (!expl) {
            if (!impl.isEmpty() && this._annotationIntrospector.hasCreatorAnnotation(param.getOwner())) {
                pn = PropertyName.construct(impl);
            } else {
                return;
            }
        }
        POJOPropertyBuilder prop = (!expl || !impl.isEmpty()) ? _property(props, impl) : _property(props, pn);
        prop.addCtor(param, pn, expl, true, false);
        this._creatorProperties.add(prop);
    }

    /* access modifiers changed from: protected */
    public void _addMethods(Map<String, POJOPropertyBuilder> props) {
        AnnotationIntrospector ai = this._annotationIntrospector;
        for (AnnotatedMethod m : this._classDef.memberMethods()) {
            int argCount = m.getParameterCount();
            if (argCount == 0) {
                _addGetterMethod(props, m, ai);
            } else if (argCount == 1) {
                _addSetterMethod(props, m, ai);
            } else if (argCount == 2 && ai != null && ai.hasAnySetterAnnotation(m)) {
                if (this._anySetters == null) {
                    this._anySetters = new LinkedList<>();
                }
                this._anySetters.add(m);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _addGetterMethod(Map<String, POJOPropertyBuilder> props, AnnotatedMethod m, AnnotationIntrospector ai) {
        boolean nameExplicit;
        boolean visible;
        boolean ignore = false;
        String implName = null;
        if (m.hasReturnType()) {
            if (ai != null) {
                if (ai.hasAnyGetterAnnotation(m)) {
                    if (this._anyGetters == null) {
                        this._anyGetters = new LinkedList<>();
                    }
                    this._anyGetters.add(m);
                    return;
                } else if (ai.hasAsValueAnnotation(m)) {
                    if (this._jsonValueGetters == null) {
                        this._jsonValueGetters = new LinkedList<>();
                    }
                    this._jsonValueGetters.add(m);
                    return;
                }
            }
            PropertyName pn = ai == null ? null : ai.findNameForSerialization(m);
            if (pn != null) {
                nameExplicit = true;
            } else {
                nameExplicit = false;
            }
            if (!nameExplicit) {
                if (ai != null) {
                    implName = ai.findImplicitPropertyName(m);
                }
                if (implName == null) {
                    implName = BeanUtil.okNameForRegularGetter(m, m.getName(), this._stdBeanNaming);
                }
                if (implName == null) {
                    implName = BeanUtil.okNameForIsGetter(m, m.getName(), this._stdBeanNaming);
                    if (implName != null) {
                        visible = this._visibilityChecker.isIsGetterVisible(m);
                    } else {
                        return;
                    }
                } else {
                    visible = this._visibilityChecker.isGetterVisible(m);
                }
            } else {
                if (ai != null) {
                    implName = ai.findImplicitPropertyName(m);
                }
                if (implName == null) {
                    implName = BeanUtil.okNameForGetter(m, this._stdBeanNaming);
                }
                if (implName == null) {
                    implName = m.getName();
                }
                if (pn.isEmpty()) {
                    pn = _propNameFromSimple(implName);
                    nameExplicit = false;
                }
                visible = true;
            }
            if (ai != null) {
                ignore = ai.hasIgnoreMarker(m);
            }
            _property(props, implName).addGetter(m, pn, nameExplicit, visible, ignore);
        }
    }

    /* access modifiers changed from: protected */
    public void _addSetterMethod(Map<String, POJOPropertyBuilder> props, AnnotatedMethod m, AnnotationIntrospector ai) {
        boolean nameExplicit;
        boolean visible;
        boolean ignore = false;
        String implName = null;
        PropertyName pn = ai == null ? null : ai.findNameForDeserialization(m);
        if (pn != null) {
            nameExplicit = true;
        } else {
            nameExplicit = false;
        }
        if (!nameExplicit) {
            if (ai != null) {
                implName = ai.findImplicitPropertyName(m);
            }
            if (implName == null) {
                implName = BeanUtil.okNameForMutator(m, this._mutatorPrefix, this._stdBeanNaming);
            }
            if (implName != null) {
                visible = this._visibilityChecker.isSetterVisible(m);
            } else {
                return;
            }
        } else {
            if (ai != null) {
                implName = ai.findImplicitPropertyName(m);
            }
            if (implName == null) {
                implName = BeanUtil.okNameForMutator(m, this._mutatorPrefix, this._stdBeanNaming);
            }
            if (implName == null) {
                implName = m.getName();
            }
            if (pn.isEmpty()) {
                pn = _propNameFromSimple(implName);
                nameExplicit = false;
            }
            visible = true;
        }
        if (ai != null) {
            ignore = ai.hasIgnoreMarker(m);
        }
        _property(props, implName).addSetter(m, pn, nameExplicit, visible, ignore);
    }

    /* access modifiers changed from: protected */
    public void _addInjectables(Map<String, POJOPropertyBuilder> map) {
        AnnotationIntrospector ai = this._annotationIntrospector;
        if (ai != null) {
            for (AnnotatedField f : this._classDef.fields()) {
                _doAddInjectable(ai.findInjectableValueId(f), f);
            }
            for (AnnotatedMethod m : this._classDef.memberMethods()) {
                if (m.getParameterCount() == 1) {
                    _doAddInjectable(ai.findInjectableValueId(m), m);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _doAddInjectable(Object id, AnnotatedMember m) {
        if (id != null) {
            if (this._injectables == null) {
                this._injectables = new LinkedHashMap<>();
            }
            if (((AnnotatedMember) this._injectables.put(id, m)) != null) {
                throw new IllegalArgumentException("Duplicate injectable value with id '" + String.valueOf(id) + "' (of type " + id.getClass().getName() + ")");
            }
        }
    }

    private PropertyName _propNameFromSimple(String simpleName) {
        return PropertyName.construct(simpleName, null);
    }

    /* access modifiers changed from: protected */
    public void _removeUnwantedProperties(Map<String, POJOPropertyBuilder> props) {
        Iterator<POJOPropertyBuilder> it = props.values().iterator();
        while (it.hasNext()) {
            POJOPropertyBuilder prop = (POJOPropertyBuilder) it.next();
            if (!prop.anyVisible()) {
                it.remove();
            } else if (prop.anyIgnorals()) {
                if (!prop.isExplicitlyIncluded()) {
                    it.remove();
                    _collectIgnorals(prop.getName());
                } else {
                    prop.removeIgnored();
                    if (!this._forSerialization && !prop.couldDeserialize()) {
                        _collectIgnorals(prop.getName());
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _removeUnwantedAccessor(Map<String, POJOPropertyBuilder> props) {
        boolean inferMutators = this._config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS);
        for (POJOPropertyBuilder prop : props.values()) {
            Access acc = prop.removeNonVisible(inferMutators);
            if (!this._forSerialization && acc == Access.READ_ONLY) {
                _collectIgnorals(prop.getName());
            }
        }
    }

    private void _collectIgnorals(String name) {
        if (!this._forSerialization) {
            if (this._ignoredPropertyNames == null) {
                this._ignoredPropertyNames = new HashSet<>();
            }
            this._ignoredPropertyNames.add(name);
        }
    }

    /* access modifiers changed from: protected */
    public void _renameProperties(Map<String, POJOPropertyBuilder> props) {
        Iterator<Entry<String, POJOPropertyBuilder>> it = props.entrySet().iterator();
        LinkedList<POJOPropertyBuilder> renamed = null;
        while (it.hasNext()) {
            POJOPropertyBuilder prop = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            Collection<PropertyName> l = prop.findExplicitNames();
            if (!l.isEmpty()) {
                it.remove();
                if (renamed == null) {
                    renamed = new LinkedList<>();
                }
                if (l.size() == 1) {
                    renamed.add(prop.withName((PropertyName) l.iterator().next()));
                } else {
                    renamed.addAll(prop.explode(l));
                }
            }
        }
        if (renamed != null) {
            Iterator i$ = renamed.iterator();
            while (i$.hasNext()) {
                POJOPropertyBuilder prop2 = (POJOPropertyBuilder) i$.next();
                String name = prop2.getName();
                POJOPropertyBuilder old = (POJOPropertyBuilder) props.get(name);
                if (old == null) {
                    props.put(name, prop2);
                } else {
                    old.addAll(prop2);
                }
                _updateCreatorProperty(prop2, this._creatorProperties);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _renameUsing(Map<String, POJOPropertyBuilder> propMap, PropertyNamingStrategy naming) {
        POJOPropertyBuilder[] arr$;
        String simpleName;
        POJOPropertyBuilder[] props = (POJOPropertyBuilder[]) propMap.values().toArray(new POJOPropertyBuilder[propMap.size()]);
        propMap.clear();
        for (POJOPropertyBuilder prop : props) {
            PropertyName fullName = prop.getFullName();
            String rename = null;
            if (!prop.isExplicitlyNamed() || this._config.isEnabled(MapperFeature.ALLOW_EXPLICIT_PROPERTY_RENAMING)) {
                if (this._forSerialization) {
                    if (prop.hasGetter()) {
                        rename = naming.nameForGetterMethod(this._config, prop.getGetter(), fullName.getSimpleName());
                    } else if (prop.hasField()) {
                        rename = naming.nameForField(this._config, prop.getField(), fullName.getSimpleName());
                    }
                } else if (prop.hasSetter()) {
                    rename = naming.nameForSetterMethod(this._config, prop.getSetter(), fullName.getSimpleName());
                } else if (prop.hasConstructorParameter()) {
                    rename = naming.nameForConstructorParameter(this._config, prop.getConstructorParameter(), fullName.getSimpleName());
                } else if (prop.hasField()) {
                    rename = naming.nameForField(this._config, prop.getField(), fullName.getSimpleName());
                } else if (prop.hasGetter()) {
                    rename = naming.nameForGetterMethod(this._config, prop.getGetter(), fullName.getSimpleName());
                }
            }
            if (rename == null || fullName.hasSimpleName(rename)) {
                simpleName = fullName.getSimpleName();
            } else {
                prop = prop.withSimpleName(rename);
                simpleName = rename;
            }
            POJOPropertyBuilder old = (POJOPropertyBuilder) propMap.get(simpleName);
            if (old == null) {
                propMap.put(simpleName, prop);
            } else {
                old.addAll(prop);
            }
            _updateCreatorProperty(prop, this._creatorProperties);
        }
    }

    /* access modifiers changed from: protected */
    public void _renameWithWrappers(Map<String, POJOPropertyBuilder> props) {
        Iterator<Entry<String, POJOPropertyBuilder>> it = props.entrySet().iterator();
        LinkedList<POJOPropertyBuilder> renamed = null;
        while (it.hasNext()) {
            POJOPropertyBuilder prop = (POJOPropertyBuilder) ((Entry) it.next()).getValue();
            AnnotatedMember member = prop.getPrimaryMember();
            if (member != null) {
                PropertyName wrapperName = this._annotationIntrospector.findWrapperName(member);
                if (wrapperName != null && wrapperName.hasSimpleName() && !wrapperName.equals(prop.getFullName())) {
                    if (renamed == null) {
                        renamed = new LinkedList<>();
                    }
                    renamed.add(prop.withName(wrapperName));
                    it.remove();
                }
            }
        }
        if (renamed != null) {
            Iterator i$ = renamed.iterator();
            while (i$.hasNext()) {
                POJOPropertyBuilder prop2 = (POJOPropertyBuilder) i$.next();
                String name = prop2.getName();
                POJOPropertyBuilder old = (POJOPropertyBuilder) props.get(name);
                if (old == null) {
                    props.put(name, prop2);
                } else {
                    old.addAll(prop2);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _sortProperties(Map<String, POJOPropertyBuilder> props) {
        boolean sort;
        Map<String, POJOPropertyBuilder> all;
        Collection<POJOPropertyBuilder> cr;
        String[] arr$;
        AnnotationIntrospector intr = this._annotationIntrospector;
        Boolean alpha = intr == null ? null : intr.findSerializationSortAlphabetically(this._classDef);
        if (alpha == null) {
            sort = this._config.shouldSortPropertiesAlphabetically();
        } else {
            sort = alpha.booleanValue();
        }
        String[] propertyOrder = intr == null ? null : intr.findSerializationPropertyOrder(this._classDef);
        if (sort || this._creatorProperties != null || propertyOrder != null) {
            int size = props.size();
            if (sort) {
                all = new TreeMap<>();
            } else {
                all = new LinkedHashMap<>(size + size);
            }
            for (POJOPropertyBuilder prop : props.values()) {
                all.put(prop.getName(), prop);
            }
            Map<String, POJOPropertyBuilder> ordered = new LinkedHashMap<>(size + size);
            if (propertyOrder != null) {
                for (String name : propertyOrder) {
                    POJOPropertyBuilder w = (POJOPropertyBuilder) all.get(name);
                    if (w == null) {
                        Iterator i$ = props.values().iterator();
                        while (true) {
                            if (!i$.hasNext()) {
                                break;
                            }
                            POJOPropertyBuilder prop2 = (POJOPropertyBuilder) i$.next();
                            if (name.equals(prop2.getInternalName())) {
                                w = prop2;
                                name = prop2.getName();
                                break;
                            }
                        }
                    }
                    if (w != null) {
                        ordered.put(name, w);
                    }
                }
            }
            if (this._creatorProperties != null) {
                if (sort) {
                    TreeMap<String, POJOPropertyBuilder> sorted = new TreeMap<>();
                    Iterator i$2 = this._creatorProperties.iterator();
                    while (i$2.hasNext()) {
                        POJOPropertyBuilder prop3 = (POJOPropertyBuilder) i$2.next();
                        sorted.put(prop3.getName(), prop3);
                    }
                    cr = sorted.values();
                } else {
                    cr = this._creatorProperties;
                }
                for (POJOPropertyBuilder prop4 : cr) {
                    String name2 = prop4.getName();
                    if (all.containsKey(name2)) {
                        ordered.put(name2, prop4);
                    }
                }
            }
            ordered.putAll(all);
            props.clear();
            props.putAll(ordered);
        }
    }

    /* access modifiers changed from: protected */
    public void reportProblem(String msg) {
        throw new IllegalArgumentException("Problem with definition of " + this._classDef + ": " + msg);
    }

    /* access modifiers changed from: protected */
    public POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> props, PropertyName name) {
        return _property(props, name.getSimpleName());
    }

    /* access modifiers changed from: protected */
    public POJOPropertyBuilder _property(Map<String, POJOPropertyBuilder> props, String implName) {
        POJOPropertyBuilder prop = (POJOPropertyBuilder) props.get(implName);
        if (prop != null) {
            return prop;
        }
        POJOPropertyBuilder prop2 = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, PropertyName.construct(implName));
        props.put(implName, prop2);
        return prop2;
    }

    private PropertyNamingStrategy _findNamingStrategy() {
        Object namingDef = this._annotationIntrospector == null ? null : this._annotationIntrospector.findNamingStrategy(this._classDef);
        if (namingDef == null) {
            return this._config.getPropertyNamingStrategy();
        }
        if (namingDef instanceof PropertyNamingStrategy) {
            return (PropertyNamingStrategy) namingDef;
        }
        if (!(namingDef instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned PropertyNamingStrategy definition of type " + namingDef.getClass().getName() + "; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead");
        }
        Class<?> namingClass = (Class) namingDef;
        if (namingClass == PropertyNamingStrategy.class) {
            return null;
        }
        if (!PropertyNamingStrategy.class.isAssignableFrom(namingClass)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + namingClass.getName() + "; expected Class<PropertyNamingStrategy>");
        }
        HandlerInstantiator hi = this._config.getHandlerInstantiator();
        if (hi != null) {
            PropertyNamingStrategy pns = hi.namingStrategyInstance(this._config, this._classDef, namingClass);
            if (pns != null) {
                return pns;
            }
        }
        return (PropertyNamingStrategy) ClassUtil.createInstance(namingClass, this._config.canOverrideAccessModifiers());
    }

    /* access modifiers changed from: protected */
    public void _updateCreatorProperty(POJOPropertyBuilder prop, List<POJOPropertyBuilder> creatorProperties) {
        if (creatorProperties != null) {
            int len = creatorProperties.size();
            for (int i = 0; i < len; i++) {
                if (((POJOPropertyBuilder) creatorProperties.get(i)).getInternalName().equals(prop.getInternalName())) {
                    creatorProperties.set(i, prop);
                    return;
                }
            }
        }
    }
}
