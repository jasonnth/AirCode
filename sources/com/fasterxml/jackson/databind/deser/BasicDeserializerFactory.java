package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.deser.impl.CreatorCollector;
import com.fasterxml.jackson.databind.deser.std.ArrayBlockingQueueDeserializer;
import com.fasterxml.jackson.databind.deser.std.AtomicReferenceDeserializer;
import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fasterxml.jackson.databind.deser.std.EnumDeserializer;
import com.fasterxml.jackson.databind.deser.std.EnumSetDeserializer;
import com.fasterxml.jackson.databind.deser.std.JdkDeserializers;
import com.fasterxml.jackson.databind.deser.std.JsonLocationInstantiator;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.deser.std.MapEntryDeserializer;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import com.fasterxml.jackson.databind.deser.std.ObjectArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.PrimitiveArrayDeserializers;
import com.fasterxml.jackson.databind.deser.std.StdKeyDeserializers;
import com.fasterxml.jackson.databind.deser.std.StringArrayDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringCollectionDeserializer;
import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import com.fasterxml.jackson.databind.deser.std.TokenBufferDeserializer;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;
import com.fasterxml.jackson.databind.ext.OptionalHandlerFactory;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.AnnotatedWithParams;
import com.fasterxml.jackson.databind.introspect.BasicBeanDescription;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.POJOPropertyBuilder;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeResolverBuilder;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.EnumResolver;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import icepick.Icepick;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

public abstract class BasicDeserializerFactory extends DeserializerFactory implements Serializable {
    private static final Class<?> CLASS_CHAR_BUFFER = CharSequence.class;
    private static final Class<?> CLASS_ITERABLE = Iterable.class;
    private static final Class<?> CLASS_MAP_ENTRY = Entry.class;
    private static final Class<?> CLASS_OBJECT = Object.class;
    private static final Class<?> CLASS_STRING = String.class;
    protected static final PropertyName UNWRAPPED_CREATOR_PARAM_NAME = new PropertyName("@JsonUnwrapped");
    static final HashMap<String, Class<? extends Collection>> _collectionFallbacks = new HashMap<>();
    static final HashMap<String, Class<? extends Map>> _mapFallbacks = new HashMap<>();
    protected final DeserializerFactoryConfig _factoryConfig;

    /* access modifiers changed from: protected */
    public abstract DeserializerFactory withConfig(DeserializerFactoryConfig deserializerFactoryConfig);

    static {
        _mapFallbacks.put(Map.class.getName(), LinkedHashMap.class);
        _mapFallbacks.put(ConcurrentMap.class.getName(), ConcurrentHashMap.class);
        _mapFallbacks.put(SortedMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(NavigableMap.class.getName(), TreeMap.class);
        _mapFallbacks.put(ConcurrentNavigableMap.class.getName(), ConcurrentSkipListMap.class);
        _collectionFallbacks.put(Collection.class.getName(), ArrayList.class);
        _collectionFallbacks.put(List.class.getName(), ArrayList.class);
        _collectionFallbacks.put(Set.class.getName(), HashSet.class);
        _collectionFallbacks.put(SortedSet.class.getName(), TreeSet.class);
        _collectionFallbacks.put(Queue.class.getName(), LinkedList.class);
        _collectionFallbacks.put("java.util.Deque", LinkedList.class);
        _collectionFallbacks.put("java.util.NavigableSet", TreeSet.class);
    }

    protected BasicDeserializerFactory(DeserializerFactoryConfig config) {
        this._factoryConfig = config;
    }

    public final DeserializerFactory withAdditionalDeserializers(Deserializers additional) {
        return withConfig(this._factoryConfig.withAdditionalDeserializers(additional));
    }

    public final DeserializerFactory withAdditionalKeyDeserializers(KeyDeserializers additional) {
        return withConfig(this._factoryConfig.withAdditionalKeyDeserializers(additional));
    }

    public final DeserializerFactory withDeserializerModifier(BeanDeserializerModifier modifier) {
        return withConfig(this._factoryConfig.withDeserializerModifier(modifier));
    }

    public final DeserializerFactory withAbstractTypeResolver(AbstractTypeResolver resolver) {
        return withConfig(this._factoryConfig.withAbstractTypeResolver(resolver));
    }

    public final DeserializerFactory withValueInstantiators(ValueInstantiators instantiators) {
        return withConfig(this._factoryConfig.withValueInstantiators(instantiators));
    }

    public JavaType mapAbstractType(DeserializationConfig config, JavaType type) throws JsonMappingException {
        JavaType next;
        while (true) {
            next = _mapAbstractType2(config, type);
            if (next == null) {
                return type;
            }
            Class<?> prevCls = type.getRawClass();
            Class<?> nextCls = next.getRawClass();
            if (prevCls != nextCls && prevCls.isAssignableFrom(nextCls)) {
                type = next;
            }
        }
        throw new IllegalArgumentException("Invalid abstract type resolution from " + type + " to " + next + ": latter is not a subtype of former");
    }

    private JavaType _mapAbstractType2(DeserializationConfig config, JavaType type) throws JsonMappingException {
        Class<?> currClass = type.getRawClass();
        if (this._factoryConfig.hasAbstractTypeResolvers()) {
            for (AbstractTypeResolver resolver : this._factoryConfig.abstractTypeResolvers()) {
                JavaType concrete = resolver.findTypeMapping(config, type);
                if (concrete != null && concrete.getRawClass() != currClass) {
                    return concrete;
                }
            }
        }
        return null;
    }

    public ValueInstantiator findValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        ValueInstantiator instantiator = null;
        AnnotatedClass ac = beanDesc.getClassInfo();
        Object instDef = ctxt.getAnnotationIntrospector().findValueInstantiator(ac);
        if (instDef != null) {
            instantiator = _valueInstantiatorInstance(config, ac, instDef);
        }
        if (instantiator == null) {
            instantiator = _findStdValueInstantiator(config, beanDesc);
            if (instantiator == null) {
                instantiator = _constructDefaultValueInstantiator(ctxt, beanDesc);
            }
        }
        if (this._factoryConfig.hasValueInstantiators()) {
            for (ValueInstantiators insts : this._factoryConfig.valueInstantiators()) {
                instantiator = insts.findValueInstantiator(config, beanDesc, instantiator);
                if (instantiator == null) {
                    ctxt.reportMappingException("Broken registered ValueInstantiators (of type %s): returned null ValueInstantiator", insts.getClass().getName());
                }
            }
        }
        if (instantiator.getIncompleteParameter() == null) {
            return instantiator;
        }
        AnnotatedParameter nonAnnotatedParam = instantiator.getIncompleteParameter();
        throw new IllegalArgumentException("Argument #" + nonAnnotatedParam.getIndex() + " of constructor " + nonAnnotatedParam.getOwner() + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
    }

    private ValueInstantiator _findStdValueInstantiator(DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        if (beanDesc.getBeanClass() == JsonLocation.class) {
            return new JsonLocationInstantiator();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public ValueInstantiator _constructDefaultValueInstantiator(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        CreatorCollector creators = new CreatorCollector(beanDesc, ctxt.getConfig());
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        DeserializationConfig config = ctxt.getConfig();
        VisibilityChecker<?> vchecker = intr.findAutoDetectVisibility(beanDesc.getClassInfo(), config.getDefaultVisibilityChecker());
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorDefs = _findCreatorsFromProperties(ctxt, beanDesc);
        _addDeserializerFactoryMethods(ctxt, beanDesc, vchecker, intr, creators, creatorDefs);
        if (beanDesc.getType().isConcrete()) {
            _addDeserializerConstructors(ctxt, beanDesc, vchecker, intr, creators, creatorDefs);
        }
        return creators.constructValueInstantiator(config);
    }

    /* access modifiers changed from: protected */
    public Map<AnnotatedWithParams, BeanPropertyDefinition[]> _findCreatorsFromProperties(DeserializationContext ctxt, BeanDescription beanDesc) throws JsonMappingException {
        Map<AnnotatedWithParams, BeanPropertyDefinition[]> result = Collections.emptyMap();
        for (BeanPropertyDefinition propDef : beanDesc.findProperties()) {
            Iterator<AnnotatedParameter> it = propDef.getConstructorParameters();
            while (true) {
                if (it.hasNext()) {
                    AnnotatedParameter param = (AnnotatedParameter) it.next();
                    AnnotatedWithParams owner = param.getOwner();
                    BeanPropertyDefinition[] defs = (BeanPropertyDefinition[]) result.get(owner);
                    int index = param.getIndex();
                    if (defs == null) {
                        if (result.isEmpty()) {
                            result = new LinkedHashMap<>();
                        }
                        defs = new BeanPropertyDefinition[owner.getParameterCount()];
                        result.put(owner, defs);
                    } else if (defs[index] != null) {
                        throw new IllegalStateException("Conflict: parameter #" + index + " of " + owner + " bound to more than one property; " + defs[index] + " vs " + propDef);
                    }
                    defs[index] = propDef;
                }
            }
        }
        return result;
    }

    public ValueInstantiator _valueInstantiatorInstance(DeserializationConfig config, Annotated annotated, Object instDef) throws JsonMappingException {
        if (instDef == null) {
            return null;
        }
        if (instDef instanceof ValueInstantiator) {
            return (ValueInstantiator) instDef;
        }
        if (!(instDef instanceof Class)) {
            throw new IllegalStateException("AnnotationIntrospector returned key deserializer definition of type " + instDef.getClass().getName() + "; expected type KeyDeserializer or Class<KeyDeserializer> instead");
        }
        Class<?> instClass = (Class) instDef;
        if (ClassUtil.isBogusClass(instClass)) {
            return null;
        }
        if (!ValueInstantiator.class.isAssignableFrom(instClass)) {
            throw new IllegalStateException("AnnotationIntrospector returned Class " + instClass.getName() + "; expected Class<ValueInstantiator>");
        }
        HandlerInstantiator hi = config.getHandlerInstantiator();
        if (hi != null) {
            ValueInstantiator inst = hi.valueInstantiatorInstance(config, annotated, instClass);
            if (inst != null) {
                return inst;
            }
        }
        return (ValueInstantiator) ClassUtil.createInstance(instClass, config.canOverrideAccessModifiers());
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerConstructors(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams) throws JsonMappingException {
        AnnotatedConstructor defaultCtor = beanDesc.findDefaultConstructor();
        if (defaultCtor != null && (!creators.hasDefaultCreator() || intr.hasCreatorAnnotation(defaultCtor))) {
            creators.setDefaultCreator(defaultCtor);
        }
        if (!beanDesc.isNonStaticInnerClass()) {
            LinkedList linkedList = null;
            for (AnnotatedConstructor ctor : beanDesc.getConstructors()) {
                boolean isCreator = intr.hasCreatorAnnotation(ctor);
                BeanPropertyDefinition[] propDefs = (BeanPropertyDefinition[]) creatorParams.get(ctor);
                int argCount = ctor.getParameterCount();
                if (argCount == 1) {
                    BeanPropertyDefinition argDef = propDefs == null ? null : propDefs[0];
                    if (_checkIfCreatorPropertyBased(intr, ctor, argDef)) {
                        SettableBeanProperty[] properties = new SettableBeanProperty[1];
                        PropertyName name = argDef == null ? null : argDef.getFullName();
                        AnnotatedParameter arg = ctor.getParameter(0);
                        properties[0] = constructCreatorProperty(ctxt, beanDesc, name, 0, arg, intr.findInjectableValueId(arg));
                        creators.addPropertyCreator(ctor, isCreator, properties);
                    } else {
                        _handleSingleArgumentConstructor(ctxt, beanDesc, vchecker, intr, creators, ctor, isCreator, vchecker.isCreatorVisible(ctor));
                        if (argDef != null) {
                            ((POJOPropertyBuilder) argDef).removeConstructors();
                        }
                    }
                } else {
                    AnnotatedParameter nonAnnotatedParam = null;
                    SettableBeanProperty[] properties2 = new SettableBeanProperty[argCount];
                    int explicitNameCount = 0;
                    int implicitWithCreatorCount = 0;
                    int injectCount = 0;
                    int i = 0;
                    while (i < argCount) {
                        AnnotatedParameter param = ctor.getParameter(i);
                        BeanPropertyDefinition propDef = propDefs == null ? null : propDefs[i];
                        Object injectId = intr.findInjectableValueId(param);
                        PropertyName name2 = propDef == null ? null : propDef.getFullName();
                        if (propDef != null && propDef.isExplicitlyNamed()) {
                            explicitNameCount++;
                            properties2[i] = constructCreatorProperty(ctxt, beanDesc, name2, i, param, injectId);
                        } else if (injectId != null) {
                            injectCount++;
                            properties2[i] = constructCreatorProperty(ctxt, beanDesc, name2, i, param, injectId);
                        } else if (intr.findUnwrappingNameTransformer(param) != null) {
                            properties2[i] = constructCreatorProperty(ctxt, beanDesc, UNWRAPPED_CREATOR_PARAM_NAME, i, param, null);
                            explicitNameCount++;
                        } else if (isCreator && name2 != null && !name2.isEmpty()) {
                            implicitWithCreatorCount++;
                            properties2[i] = constructCreatorProperty(ctxt, beanDesc, name2, i, param, injectId);
                        } else if (nonAnnotatedParam == null) {
                            nonAnnotatedParam = param;
                        }
                        i++;
                    }
                    int namedCount = explicitNameCount + implicitWithCreatorCount;
                    if (isCreator || explicitNameCount > 0 || injectCount > 0) {
                        if (namedCount + injectCount == argCount) {
                            creators.addPropertyCreator(ctor, isCreator, properties2);
                        } else if (explicitNameCount == 0 && injectCount + 1 == argCount) {
                            creators.addDelegatingCreator(ctor, isCreator, properties2);
                        } else {
                            PropertyName impl = _findImplicitParamName(nonAnnotatedParam, intr);
                            if (impl == null || impl.isEmpty()) {
                                throw new IllegalArgumentException("Argument #" + nonAnnotatedParam.getIndex() + " of constructor " + ctor + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                            }
                        }
                    }
                    if (!creators.hasDefaultCreator()) {
                        if (linkedList == null) {
                            linkedList = new LinkedList();
                        }
                        linkedList.add(ctor);
                    }
                }
            }
            if (linkedList != null && !creators.hasDelegatingCreator() && !creators.hasPropertyBasedCreator()) {
                _checkImplicitlyNamedConstructors(ctxt, beanDesc, vchecker, intr, creators, linkedList);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void _checkImplicitlyNamedConstructors(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, List<AnnotatedConstructor> implicitCtors) throws JsonMappingException {
        AnnotatedConstructor found = null;
        SettableBeanProperty[] foundProps = null;
        Iterator i$ = implicitCtors.iterator();
        while (true) {
            if (!i$.hasNext()) {
                break;
            }
            AnnotatedConstructor ctor = (AnnotatedConstructor) i$.next();
            if (vchecker.isCreatorVisible(ctor)) {
                int argCount = ctor.getParameterCount();
                SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
                int i = 0;
                while (true) {
                    if (i < argCount) {
                        AnnotatedParameter param = ctor.getParameter(i);
                        PropertyName name = _findParamName(param, intr);
                        if (name == null || name.isEmpty()) {
                            break;
                        }
                        properties[i] = constructCreatorProperty(ctxt, beanDesc, name, param.getIndex(), param, null);
                        i++;
                    } else if (found != null) {
                        found = null;
                        break;
                    } else {
                        found = ctor;
                        foundProps = properties;
                    }
                }
            }
        }
        if (found != null) {
            creators.addPropertyCreator(found, false, foundProps);
            BasicBeanDescription bbd = (BasicBeanDescription) beanDesc;
            SettableBeanProperty[] arr$ = foundProps;
            int len$ = arr$.length;
            for (int i$2 = 0; i$2 < len$; i$2++) {
                SettableBeanProperty prop = arr$[i$2];
                PropertyName pn = prop.getFullName();
                if (!bbd.hasProperty(pn)) {
                    bbd.addProperty(SimpleBeanPropertyDefinition.construct(ctxt.getConfig(), prop.getMember(), pn));
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _checkIfCreatorPropertyBased(AnnotationIntrospector intr, AnnotatedWithParams creator, BeanPropertyDefinition propDef) {
        Mode mode = intr.findCreatorBinding(creator);
        if (mode == Mode.PROPERTIES) {
            return true;
        }
        if (mode == Mode.DELEGATING) {
            return false;
        }
        if ((propDef != null && propDef.isExplicitlyNamed()) || intr.findInjectableValueId(creator.getParameter(0)) != null) {
            return true;
        }
        if (propDef != null) {
            String implName = propDef.getName();
            if (implName != null && !implName.isEmpty() && propDef.couldSerialize()) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentConstructor(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> visibilityChecker, AnnotationIntrospector intr, CreatorCollector creators, AnnotatedConstructor ctor, boolean isCreator, boolean isVisible) throws JsonMappingException {
        Class<?> type = ctor.getRawParameterType(0);
        if (type == String.class || type == CharSequence.class) {
            if (!isCreator && !isVisible) {
                return true;
            }
            creators.addStringCreator(ctor, isCreator);
            return true;
        } else if (type == Integer.TYPE || type == Integer.class) {
            if (!isCreator && !isVisible) {
                return true;
            }
            creators.addIntCreator(ctor, isCreator);
            return true;
        } else if (type == Long.TYPE || type == Long.class) {
            if (!isCreator && !isVisible) {
                return true;
            }
            creators.addLongCreator(ctor, isCreator);
            return true;
        } else if (type == Double.TYPE || type == Double.class) {
            if (!isCreator && !isVisible) {
                return true;
            }
            creators.addDoubleCreator(ctor, isCreator);
            return true;
        } else if (type == Boolean.TYPE || type == Boolean.class) {
            if (!isCreator && !isVisible) {
                return true;
            }
            creators.addBooleanCreator(ctor, isCreator);
            return true;
        } else if (!isCreator) {
            return false;
        } else {
            creators.addDelegatingCreator(ctor, isCreator, null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public void _addDeserializerFactoryMethods(DeserializationContext ctxt, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, Map<AnnotatedWithParams, BeanPropertyDefinition[]> creatorParams) throws JsonMappingException {
        BeanPropertyDefinition argDef;
        DeserializationConfig config = ctxt.getConfig();
        for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
            boolean isCreator = intr.hasCreatorAnnotation(factory);
            int argCount = factory.getParameterCount();
            if (argCount != 0) {
                BeanPropertyDefinition[] propDefs = (BeanPropertyDefinition[]) creatorParams.get(factory);
                if (argCount == 1) {
                    if (propDefs == null) {
                        argDef = null;
                    } else {
                        argDef = propDefs[0];
                    }
                    if (!_checkIfCreatorPropertyBased(intr, factory, argDef)) {
                        _handleSingleArgumentFactory(config, beanDesc, vchecker, intr, creators, factory, isCreator);
                        if (argDef != null) {
                            ((POJOPropertyBuilder) argDef).removeConstructors();
                        }
                    }
                } else if (!isCreator) {
                    continue;
                }
                AnnotatedParameter nonAnnotatedParam = null;
                SettableBeanProperty[] properties = new SettableBeanProperty[argCount];
                int implicitNameCount = 0;
                int explicitNameCount = 0;
                int injectCount = 0;
                int i = 0;
                while (i < argCount) {
                    AnnotatedParameter param = factory.getParameter(i);
                    BeanPropertyDefinition propDef = propDefs == null ? null : propDefs[i];
                    Object injectId = intr.findInjectableValueId(param);
                    PropertyName name = propDef == null ? null : propDef.getFullName();
                    if (propDef != null && propDef.isExplicitlyNamed()) {
                        explicitNameCount++;
                        properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    } else if (injectId != null) {
                        injectCount++;
                        properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    } else if (intr.findUnwrappingNameTransformer(param) != null) {
                        properties[i] = constructCreatorProperty(ctxt, beanDesc, UNWRAPPED_CREATOR_PARAM_NAME, i, param, null);
                        implicitNameCount++;
                    } else if (isCreator && name != null && !name.isEmpty()) {
                        implicitNameCount++;
                        properties[i] = constructCreatorProperty(ctxt, beanDesc, name, i, param, injectId);
                    } else if (nonAnnotatedParam == null) {
                        nonAnnotatedParam = param;
                    }
                    i++;
                }
                int namedCount = explicitNameCount + implicitNameCount;
                if (isCreator || explicitNameCount > 0 || injectCount > 0) {
                    if (namedCount + injectCount == argCount) {
                        creators.addPropertyCreator(factory, isCreator, properties);
                    } else if (explicitNameCount == 0 && injectCount + 1 == argCount) {
                        creators.addDelegatingCreator(factory, isCreator, properties);
                    } else {
                        throw new IllegalArgumentException("Argument #" + nonAnnotatedParam.getIndex() + " of factory method " + factory + " has no property name annotation; must have name when multiple-parameter constructor annotated as Creator");
                    }
                }
            } else if (isCreator) {
                creators.setDefaultCreator(factory);
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean _handleSingleArgumentFactory(DeserializationConfig config, BeanDescription beanDesc, VisibilityChecker<?> vchecker, AnnotationIntrospector intr, CreatorCollector creators, AnnotatedMethod factory, boolean isCreator) throws JsonMappingException {
        Class<?> type = factory.getRawParameterType(0);
        if (type == String.class || type == CharSequence.class) {
            if (!isCreator && !vchecker.isCreatorVisible(factory)) {
                return true;
            }
            creators.addStringCreator(factory, isCreator);
            return true;
        } else if (type == Integer.TYPE || type == Integer.class) {
            if (!isCreator && !vchecker.isCreatorVisible(factory)) {
                return true;
            }
            creators.addIntCreator(factory, isCreator);
            return true;
        } else if (type == Long.TYPE || type == Long.class) {
            if (!isCreator && !vchecker.isCreatorVisible(factory)) {
                return true;
            }
            creators.addLongCreator(factory, isCreator);
            return true;
        } else if (type == Double.TYPE || type == Double.class) {
            if (!isCreator && !vchecker.isCreatorVisible(factory)) {
                return true;
            }
            creators.addDoubleCreator(factory, isCreator);
            return true;
        } else if (type == Boolean.TYPE || type == Boolean.class) {
            if (!isCreator && !vchecker.isCreatorVisible(factory)) {
                return true;
            }
            creators.addBooleanCreator(factory, isCreator);
            return true;
        } else if (!isCreator) {
            return false;
        } else {
            creators.addDelegatingCreator(factory, isCreator, null);
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructCreatorProperty(DeserializationContext ctxt, BeanDescription beanDesc, PropertyName name, int index, AnnotatedParameter param, Object injectableValueId) throws JsonMappingException {
        PropertyMetadata metadata;
        DeserializationConfig config = ctxt.getConfig();
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr == null) {
            metadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
        } else {
            metadata = PropertyMetadata.construct(intr.hasRequiredMarker(param), intr.findPropertyDescription(param), intr.findPropertyIndex(param), intr.findPropertyDefaultValue(param));
        }
        JavaType type = resolveMemberAndTypeAnnotations(ctxt, param, param.getType());
        Std property = new Std(name, type, intr.findWrapperName(param), beanDesc.getClassAnnotations(), param, metadata);
        TypeDeserializer typeDeser = (TypeDeserializer) type.getTypeHandler();
        if (typeDeser == null) {
            typeDeser = findTypeDeserializer(config, type);
        }
        SettableBeanProperty prop = new CreatorProperty(name, type, property.getWrapperName(), typeDeser, beanDesc.getClassAnnotations(), param, index, injectableValueId, metadata);
        JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, param);
        if (deser == null) {
            deser = (JsonDeserializer) type.getValueHandler();
        }
        if (deser != null) {
            return prop.withValueDeserializer(ctxt.handlePrimaryContextualization(deser, prop, type));
        }
        return prop;
    }

    /* access modifiers changed from: protected */
    public PropertyName _findParamName(AnnotatedParameter param, AnnotationIntrospector intr) {
        if (!(param == null || intr == null)) {
            PropertyName name = intr.findNameForDeserialization(param);
            if (name != null) {
                return name;
            }
            String str = intr.findImplicitPropertyName(param);
            if (str != null && !str.isEmpty()) {
                return PropertyName.construct(str);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public PropertyName _findImplicitParamName(AnnotatedParameter param, AnnotationIntrospector intr) {
        String str = intr.findImplicitPropertyName(param);
        if (str == null || str.isEmpty()) {
            return null;
        }
        return PropertyName.construct(str);
    }

    public JsonDeserializer<?> createArrayDeserializer(DeserializationContext ctxt, ArrayType type, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        JavaType elemType = type.getContentType();
        JsonDeserializer<Object> contentDeser = (JsonDeserializer) elemType.getValueHandler();
        TypeDeserializer elemTypeDeser = (TypeDeserializer) elemType.getTypeHandler();
        if (elemTypeDeser == null) {
            elemTypeDeser = findTypeDeserializer(config, elemType);
        }
        JsonDeserializer<?> deser = _findCustomArrayDeserializer(type, config, beanDesc, elemTypeDeser, contentDeser);
        if (deser == null) {
            if (contentDeser == null) {
                Class<?> raw = elemType.getRawClass();
                if (elemType.isPrimitive()) {
                    return PrimitiveArrayDeserializers.forType(raw);
                }
                if (raw == String.class) {
                    return StringArrayDeserializer.instance;
                }
            }
            deser = new ObjectArrayDeserializer<>(type, contentDeser, elemTypeDeser);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyArrayDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    public JsonDeserializer<?> createCollectionDeserializer(DeserializationContext ctxt, CollectionType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType contentType = type.getContentType();
        JsonDeserializer<Object> contentDeser = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer) contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> deser = _findCustomCollectionDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
        if (deser == null) {
            Class<?> collectionClass = type.getRawClass();
            if (contentDeser == null && EnumSet.class.isAssignableFrom(collectionClass)) {
                deser = new EnumSetDeserializer<>(contentType, null);
            }
        }
        if (deser == null) {
            if (type.isInterface() || type.isAbstract()) {
                CollectionType implType = _mapAbstractCollectionType(type, config);
                if (implType != null) {
                    type = implType;
                    beanDesc = config.introspectForCreation(type);
                } else if (type.getTypeHandler() == null) {
                    throw new IllegalArgumentException("Can not find a deserializer for non-concrete Collection type " + type);
                } else {
                    deser = AbstractDeserializer.constructForNonPOJO(beanDesc);
                }
            }
            if (deser == null) {
                ValueInstantiator inst = findValueInstantiator(ctxt, beanDesc);
                if (!inst.canCreateUsingDefault() && type.getRawClass() == ArrayBlockingQueue.class) {
                    return new ArrayBlockingQueueDeserializer(type, contentDeser, contentTypeDeser, inst);
                }
                if (contentType.getRawClass() == String.class) {
                    deser = new StringCollectionDeserializer<>(type, contentDeser, inst);
                } else {
                    deser = new CollectionDeserializer<>(type, contentDeser, contentTypeDeser, inst);
                }
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyCollectionDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    /* access modifiers changed from: protected */
    public CollectionType _mapAbstractCollectionType(JavaType type, DeserializationConfig config) {
        Class<?> collectionClass = (Class) _collectionFallbacks.get(type.getRawClass().getName());
        if (collectionClass == null) {
            return null;
        }
        return (CollectionType) config.constructSpecializedType(type, collectionClass);
    }

    public JsonDeserializer<?> createCollectionLikeDeserializer(DeserializationContext ctxt, CollectionLikeType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType contentType = type.getContentType();
        JsonDeserializer<Object> contentDeser = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer) contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> deser = _findCustomCollectionLikeDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyCollectionLikeDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    /* JADX WARNING: type inference failed for: r18v0, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r18v1 */
    /* JADX WARNING: type inference failed for: r18v2, types: [com.fasterxml.jackson.databind.JsonDeserializer<?>] */
    /* JADX WARNING: type inference failed for: r18v3 */
    /* JADX WARNING: type inference failed for: r3v0, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r18v4, types: [com.fasterxml.jackson.databind.JsonDeserializer] */
    /* JADX WARNING: type inference failed for: r18v5 */
    /* JADX WARNING: type inference failed for: r18v6 */
    /* JADX WARNING: type inference failed for: r11v0, types: [com.fasterxml.jackson.databind.deser.std.MapDeserializer] */
    /* JADX WARNING: type inference failed for: r18v7 */
    /* JADX WARNING: type inference failed for: r18v8, types: [com.fasterxml.jackson.databind.deser.AbstractDeserializer] */
    /* JADX WARNING: type inference failed for: r0v9, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARNING: type inference failed for: r18v10 */
    /* JADX WARNING: type inference failed for: r18v11 */
    /* JADX WARNING: type inference failed for: r18v12 */
    /* JADX WARNING: type inference failed for: r0v11, types: [com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r18v0, names: [deser], types: [com.fasterxml.jackson.databind.JsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer<?>]
      assigns: [com.fasterxml.jackson.databind.JsonDeserializer, ?[OBJECT, ARRAY], com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer, com.fasterxml.jackson.databind.deser.AbstractDeserializer]
      uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], com.fasterxml.jackson.databind.JsonDeserializer<?>, ?[OBJECT, ARRAY], com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer]
      mth insns count: 101
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.JsonDeserializer<?> createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext r28, com.fasterxml.jackson.databind.type.MapType r29, com.fasterxml.jackson.databind.BeanDescription r30) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r27 = this;
            com.fasterxml.jackson.databind.DeserializationConfig r6 = r28.getConfig()
            com.fasterxml.jackson.databind.JavaType r23 = r29.getKeyType()
            com.fasterxml.jackson.databind.JavaType r17 = r29.getContentType()
            java.lang.Object r10 = r17.getValueHandler()
            com.fasterxml.jackson.databind.JsonDeserializer r10 = (com.fasterxml.jackson.databind.JsonDeserializer) r10
            java.lang.Object r8 = r23.getValueHandler()
            com.fasterxml.jackson.databind.KeyDeserializer r8 = (com.fasterxml.jackson.databind.KeyDeserializer) r8
            java.lang.Object r9 = r17.getTypeHandler()
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r9 = (com.fasterxml.jackson.databind.jsontype.TypeDeserializer) r9
            if (r9 != 0) goto L_0x0028
            r0 = r27
            r1 = r17
            com.fasterxml.jackson.databind.jsontype.TypeDeserializer r9 = r0.findTypeDeserializer(r6, r1)
        L_0x0028:
            r4 = r27
            r5 = r29
            r7 = r30
            com.fasterxml.jackson.databind.JsonDeserializer r18 = r4._findCustomMapDeserializer(r5, r6, r7, r8, r9, r10)
            if (r18 != 0) goto L_0x00bb
            java.lang.Class r25 = r29.getRawClass()
            java.lang.Class<java.util.EnumMap> r4 = java.util.EnumMap.class
            r0 = r25
            boolean r4 = r4.isAssignableFrom(r0)
            if (r4 == 0) goto L_0x0061
            java.lang.Class r24 = r23.getRawClass()
            if (r24 == 0) goto L_0x004e
            boolean r4 = r24.isEnum()
            if (r4 != 0) goto L_0x0057
        L_0x004e:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.String r5 = "Can not construct EnumMap; generic (key) type not available"
            r4.<init>(r5)
            throw r4
        L_0x0057:
            com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer r18 = new com.fasterxml.jackson.databind.deser.std.EnumMapDeserializer
            r4 = 0
            r0 = r18
            r1 = r29
            r0.<init>(r1, r4, r10, r9)
        L_0x0061:
            if (r18 != 0) goto L_0x00bb
            boolean r4 = r29.isInterface()
            if (r4 != 0) goto L_0x006f
            boolean r4 = r29.isAbstract()
            if (r4 == 0) goto L_0x008f
        L_0x006f:
            java.util.HashMap<java.lang.String, java.lang.Class<? extends java.util.Map>> r4 = _mapFallbacks
            java.lang.String r5 = r25.getName()
            java.lang.Object r19 = r4.get(r5)
            java.lang.Class r19 = (java.lang.Class) r19
            if (r19 == 0) goto L_0x00ea
            r25 = r19
            r0 = r29
            r1 = r25
            com.fasterxml.jackson.databind.JavaType r29 = r6.constructSpecializedType(r0, r1)
            com.fasterxml.jackson.databind.type.MapType r29 = (com.fasterxml.jackson.databind.type.MapType) r29
            r0 = r29
            com.fasterxml.jackson.databind.BeanDescription r30 = r6.introspectForCreation(r0)
        L_0x008f:
            if (r18 != 0) goto L_0x00bb
            r0 = r27
            r1 = r28
            r2 = r30
            com.fasterxml.jackson.databind.deser.ValueInstantiator r13 = r0.findValueInstantiator(r1, r2)
            com.fasterxml.jackson.databind.deser.std.MapDeserializer r11 = new com.fasterxml.jackson.databind.deser.std.MapDeserializer
            r12 = r29
            r14 = r8
            r15 = r10
            r16 = r9
            r11.<init>(r12, r13, r14, r15, r16)
            java.lang.Class<java.util.Map> r4 = java.util.Map.class
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r5 = r30.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIgnoreProperties$Value r21 = r6.getDefaultPropertyIgnorals(r4, r5)
            if (r21 != 0) goto L_0x0112
            r22 = 0
        L_0x00b4:
            r0 = r22
            r11.setIgnorableProperties(r0)
            r18 = r11
        L_0x00bb:
            r0 = r27
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r4 = r0._factoryConfig
            boolean r4 = r4.hasDeserializerModifiers()
            if (r4 == 0) goto L_0x0117
            r0 = r27
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r4 = r0._factoryConfig
            java.lang.Iterable r4 = r4.deserializerModifiers()
            java.util.Iterator r20 = r4.iterator()
        L_0x00d1:
            boolean r4 = r20.hasNext()
            if (r4 == 0) goto L_0x0117
            java.lang.Object r26 = r20.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r26 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r26
            r0 = r26
            r1 = r29
            r2 = r30
            r3 = r18
            com.fasterxml.jackson.databind.JsonDeserializer r18 = r0.modifyMapDeserializer(r6, r1, r2, r3)
            goto L_0x00d1
        L_0x00ea:
            java.lang.Object r4 = r29.getTypeHandler()
            if (r4 != 0) goto L_0x010c
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r7 = "Can not find a deserializer for non-concrete Map type "
            java.lang.StringBuilder r5 = r5.append(r7)
            r0 = r29
            java.lang.StringBuilder r5 = r5.append(r0)
            java.lang.String r5 = r5.toString()
            r4.<init>(r5)
            throw r4
        L_0x010c:
            com.fasterxml.jackson.databind.deser.AbstractDeserializer r18 = com.fasterxml.jackson.databind.deser.AbstractDeserializer.constructForNonPOJO(r30)
            goto L_0x008f
        L_0x0112:
            java.util.Set r22 = r21.findIgnoredForDeserialization()
            goto L_0x00b4
        L_0x0117:
            return r18
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BasicDeserializerFactory.createMapDeserializer(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.type.MapType, com.fasterxml.jackson.databind.BeanDescription):com.fasterxml.jackson.databind.JsonDeserializer");
    }

    public JsonDeserializer<?> createMapLikeDeserializer(DeserializationContext ctxt, MapLikeType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType keyType = type.getKeyType();
        JavaType contentType = type.getContentType();
        DeserializationConfig config = ctxt.getConfig();
        JsonDeserializer<Object> contentDeser = (JsonDeserializer) contentType.getValueHandler();
        KeyDeserializer keyDes = (KeyDeserializer) keyType.getValueHandler();
        TypeDeserializer contentTypeDeser = (TypeDeserializer) contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> deser = _findCustomMapLikeDeserializer(type, config, beanDesc, keyDes, contentTypeDeser, contentDeser);
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyMapLikeDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    public JsonDeserializer<?> createEnumDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        Class<?> enumClass = type.getRawClass();
        JsonDeserializer<?> deser = _findCustomEnumDeserializer(enumClass, config, beanDesc);
        if (deser == null) {
            ValueInstantiator valueInstantiator = _constructDefaultValueInstantiator(ctxt, beanDesc);
            SettableBeanProperty[] creatorProps = valueInstantiator == null ? null : valueInstantiator.getFromObjectArguments(ctxt.getConfig());
            Iterator i$ = beanDesc.getFactoryMethods().iterator();
            while (true) {
                if (!i$.hasNext()) {
                    break;
                }
                AnnotatedMethod factory = (AnnotatedMethod) i$.next();
                if (ctxt.getAnnotationIntrospector().hasCreatorAnnotation(factory)) {
                    if (factory.getParameterCount() == 0) {
                        deser = EnumDeserializer.deserializerForNoArgsCreator(config, enumClass, factory);
                        break;
                    } else if (factory.getRawReturnType().isAssignableFrom(enumClass)) {
                        deser = EnumDeserializer.deserializerForCreator(config, enumClass, factory, valueInstantiator, creatorProps);
                        break;
                    }
                }
            }
            if (deser == null) {
                deser = new EnumDeserializer<>(constructEnumResolver(enumClass, config, beanDesc.findJsonValueMethod()));
            }
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyEnumDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    public JsonDeserializer<?> createTreeDeserializer(DeserializationConfig config, JavaType nodeType, BeanDescription beanDesc) throws JsonMappingException {
        Class<? extends JsonNode> nodeClass = nodeType.getRawClass();
        JsonDeserializer<?> custom = _findCustomTreeNodeDeserializer(nodeClass, config, beanDesc);
        return custom != null ? custom : JsonNodeDeserializer.getDeserializer(nodeClass);
    }

    public JsonDeserializer<?> createReferenceDeserializer(DeserializationContext ctxt, ReferenceType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType contentType = type.getContentType();
        JsonDeserializer<Object> contentDeser = (JsonDeserializer) contentType.getValueHandler();
        DeserializationConfig config = ctxt.getConfig();
        TypeDeserializer contentTypeDeser = (TypeDeserializer) contentType.getTypeHandler();
        if (contentTypeDeser == null) {
            contentTypeDeser = findTypeDeserializer(config, contentType);
        }
        JsonDeserializer<?> deser = _findCustomReferenceDeserializer(type, config, beanDesc, contentTypeDeser, contentDeser);
        if (deser == null && AtomicReference.class.isAssignableFrom(type.getRawClass())) {
            return new AtomicReferenceDeserializer(type, contentTypeDeser, contentDeser);
        }
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyReferenceDeserializer(config, type, beanDesc, deser);
            }
        }
        return deser;
    }

    public TypeDeserializer findTypeDeserializer(DeserializationConfig config, JavaType baseType) throws JsonMappingException {
        AnnotatedClass ac = config.introspectClassAnnotations(baseType.getRawClass()).getClassInfo();
        TypeResolverBuilder<?> b = config.getAnnotationIntrospector().findTypeResolver(config, ac, baseType);
        Collection<NamedType> subtypes = null;
        if (b == null) {
            b = config.getDefaultTyper(baseType);
            if (b == null) {
                return null;
            }
        } else {
            subtypes = config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, ac);
        }
        if (b.getDefaultImpl() == null && baseType.isAbstract()) {
            JavaType defaultType = mapAbstractType(config, baseType);
            if (!(defaultType == null || defaultType.getRawClass() == baseType.getRawClass())) {
                b = b.defaultImpl(defaultType.getRawClass());
            }
        }
        return b.buildTypeDeserializer(config, baseType, subtypes);
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findOptionalStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        return OptionalHandlerFactory.instance.findDeserializer(type, ctxt.getConfig(), beanDesc);
    }

    public KeyDeserializer createKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        KeyDeserializer deser = null;
        if (this._factoryConfig.hasKeyDeserializers()) {
            BeanDescription beanDesc = config.introspectClassAnnotations(type.getRawClass());
            for (KeyDeserializers d : this._factoryConfig.keyDeserializers()) {
                deser = d.findKeyDeserializer(type, config, beanDesc);
                if (deser != null) {
                    break;
                }
            }
        }
        if (deser == null) {
            if (type.isEnumType()) {
                deser = _createEnumKeyDeserializer(ctxt, type);
            } else {
                deser = StdKeyDeserializers.findStringBasedKeyDeserializer(config, type);
            }
        }
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyKeyDeserializer(config, type, deser);
            }
        }
        return deser;
    }

    private KeyDeserializer _createEnumKeyDeserializer(DeserializationContext ctxt, JavaType type) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        Class<?> enumClass = type.getRawClass();
        BeanDescription beanDesc = config.introspect(type);
        KeyDeserializer des = findKeyDeserializerFromAnnotation(ctxt, beanDesc.getClassInfo());
        if (des != null) {
            return des;
        }
        JsonDeserializer<?> custom = _findCustomEnumDeserializer(enumClass, config, beanDesc);
        if (custom != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, custom);
        }
        JsonDeserializer<?> valueDesForKey = findDeserializerFromAnnotation(ctxt, beanDesc.getClassInfo());
        if (valueDesForKey != null) {
            return StdKeyDeserializers.constructDelegatingKeyDeserializer(config, type, valueDesForKey);
        }
        EnumResolver enumRes = constructEnumResolver(enumClass, config, beanDesc.findJsonValueMethod());
        AnnotationIntrospector ai = config.getAnnotationIntrospector();
        for (AnnotatedMethod factory : beanDesc.getFactoryMethods()) {
            if (ai.hasCreatorAnnotation(factory)) {
                if (factory.getParameterCount() != 1 || !factory.getRawReturnType().isAssignableFrom(enumClass)) {
                    throw new IllegalArgumentException("Unsuitable method (" + factory + ") decorated with @JsonCreator (for Enum type " + enumClass.getName() + ")");
                } else if (factory.getRawParameterType(0) != String.class) {
                    throw new IllegalArgumentException("Parameter #0 type for factory method (" + factory + ") not suitable, must be java.lang.String");
                } else {
                    if (config.canOverrideAccessModifiers()) {
                        ClassUtil.checkAndFixAccess(factory.getMember(), ctxt.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
                    }
                    return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes, factory);
                }
            }
        }
        return StdKeyDeserializers.constructEnumKeyDeserializer(enumRes);
    }

    public TypeDeserializer findPropertyTypeDeserializer(DeserializationConfig config, JavaType baseType, AnnotatedMember annotated) throws JsonMappingException {
        TypeResolverBuilder<?> b = config.getAnnotationIntrospector().findPropertyTypeResolver(config, annotated, baseType);
        if (b == null) {
            return findTypeDeserializer(config, baseType);
        }
        return b.buildTypeDeserializer(config, baseType, config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, annotated, baseType));
    }

    public TypeDeserializer findPropertyContentTypeDeserializer(DeserializationConfig config, JavaType containerType, AnnotatedMember propertyEntity) throws JsonMappingException {
        TypeResolverBuilder<?> b = config.getAnnotationIntrospector().findPropertyContentTypeResolver(config, propertyEntity, containerType);
        JavaType contentType = containerType.getContentType();
        if (b == null) {
            return findTypeDeserializer(config, contentType);
        }
        return b.buildTypeDeserializer(config, contentType, config.getSubtypeResolver().collectAndResolveSubtypesByTypeId(config, propertyEntity, contentType));
    }

    public JsonDeserializer<?> findDefaultDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JavaType mt;
        JavaType lt;
        Class<?> rawType = type.getRawClass();
        if (rawType == CLASS_OBJECT) {
            DeserializationConfig config = ctxt.getConfig();
            if (this._factoryConfig.hasAbstractTypeResolvers()) {
                lt = _findRemappedType(config, List.class);
                mt = _findRemappedType(config, Map.class);
            } else {
                mt = null;
                lt = null;
            }
            return new UntypedObjectDeserializer(lt, mt);
        } else if (rawType == CLASS_STRING || rawType == CLASS_CHAR_BUFFER) {
            return StringDeserializer.instance;
        } else {
            if (rawType == CLASS_ITERABLE) {
                TypeFactory tf = ctxt.getTypeFactory();
                JavaType[] tps = tf.findTypeParameters(type, CLASS_ITERABLE);
                return createCollectionDeserializer(ctxt, tf.constructCollectionType(Collection.class, (tps == null || tps.length != 1) ? TypeFactory.unknownType() : tps[0]), beanDesc);
            } else if (rawType == CLASS_MAP_ENTRY) {
                JavaType kt = type.containedType(0);
                if (kt == null) {
                    kt = TypeFactory.unknownType();
                }
                JavaType vt = type.containedType(1);
                if (vt == null) {
                    vt = TypeFactory.unknownType();
                }
                TypeDeserializer vts = (TypeDeserializer) vt.getTypeHandler();
                if (vts == null) {
                    vts = findTypeDeserializer(ctxt.getConfig(), vt);
                }
                return new MapEntryDeserializer(type, (KeyDeserializer) kt.getValueHandler(), (JsonDeserializer) vt.getValueHandler(), vts);
            } else {
                String clsName = rawType.getName();
                if (rawType.isPrimitive() || clsName.startsWith(Icepick.JAVA_PREFIX)) {
                    JsonDeserializer<?> deser = NumberDeserializers.find(rawType, clsName);
                    if (deser == null) {
                        deser = DateDeserializers.find(rawType, clsName);
                    }
                    if (deser != null) {
                        return deser;
                    }
                }
                if (rawType == TokenBuffer.class) {
                    return new TokenBufferDeserializer();
                }
                JsonDeserializer<?> deser2 = findOptionalStdDeserializer(ctxt, type, beanDesc);
                return deser2 == null ? JdkDeserializers.find(rawType, clsName) : deser2;
            }
        }
    }

    /* access modifiers changed from: protected */
    public JavaType _findRemappedType(DeserializationConfig config, Class<?> rawType) throws JsonMappingException {
        JavaType type = mapAbstractType(config, config.constructType(rawType));
        if (type == null || type.hasRawClass(rawType)) {
            return null;
        }
        return type;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomTreeNodeDeserializer(Class<? extends JsonNode> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findTreeNodeDeserializer(type, config, beanDesc);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomReferenceDeserializer(ReferenceType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer contentTypeDeserializer, JsonDeserializer<?> contentDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findReferenceDeserializer(type, config, beanDesc, contentTypeDeserializer, contentDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> _findCustomBeanDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findBeanDeserializer(type, config, beanDesc);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomArrayDeserializer(ArrayType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findArrayDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionDeserializer(CollectionType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findCollectionDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomCollectionLikeDeserializer(CollectionLikeType type, DeserializationConfig config, BeanDescription beanDesc, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findCollectionLikeDeserializer(type, config, beanDesc, elementTypeDeserializer, elementDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomEnumDeserializer(Class<?> type, DeserializationConfig config, BeanDescription beanDesc) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findEnumDeserializer(type, config, beanDesc);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapDeserializer(MapType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findMapDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> _findCustomMapLikeDeserializer(MapLikeType type, DeserializationConfig config, BeanDescription beanDesc, KeyDeserializer keyDeserializer, TypeDeserializer elementTypeDeserializer, JsonDeserializer<?> elementDeserializer) throws JsonMappingException {
        for (Deserializers d : this._factoryConfig.deserializers()) {
            JsonDeserializer<?> deser = d.findMapLikeDeserializer(type, config, beanDesc, keyDeserializer, elementTypeDeserializer, elementDeserializer);
            if (deser != null) {
                return deser;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null) {
            Object deserDef = intr.findDeserializer(ann);
            if (deserDef != null) {
                return ctxt.deserializerInstance(ann, deserDef);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public KeyDeserializer findKeyDeserializerFromAnnotation(DeserializationContext ctxt, Annotated ann) throws JsonMappingException {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr != null) {
            Object deserDef = intr.findKeyDeserializer(ann);
            if (deserDef != null) {
                return ctxt.keyDeserializerInstance(ann, deserDef);
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public JavaType resolveMemberAndTypeAnnotations(DeserializationContext ctxt, AnnotatedMember member, JavaType type) throws JsonMappingException {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (intr == null) {
            return type;
        }
        if (type.isMapLikeType() && type.getKeyType() != null) {
            KeyDeserializer kd = ctxt.keyDeserializerInstance(member, intr.findKeyDeserializer(member));
            if (kd != null) {
                type = ((MapLikeType) type).withKeyValueHandler(kd);
                type.getKeyType();
            }
        }
        if (type.hasContentType()) {
            JsonDeserializer<?> cd = ctxt.deserializerInstance(member, intr.findContentDeserializer(member));
            if (cd != null) {
                type = type.withContentValueHandler(cd);
            }
            TypeDeserializer contentTypeDeser = findPropertyContentTypeDeserializer(ctxt.getConfig(), type, member);
            if (contentTypeDeser != null) {
                type = type.withContentTypeHandler(contentTypeDeser);
            }
        }
        TypeDeserializer valueTypeDeser = findPropertyTypeDeserializer(ctxt.getConfig(), type, member);
        if (valueTypeDeser != null) {
            type = type.withTypeHandler(valueTypeDeser);
        }
        return intr.refineDeserializationType(ctxt.getConfig(), member, type);
    }

    /* access modifiers changed from: protected */
    public EnumResolver constructEnumResolver(Class<?> enumClass, DeserializationConfig config, AnnotatedMethod jsonValueMethod) {
        if (jsonValueMethod == null) {
            return EnumResolver.constructUnsafe(enumClass, config.getAnnotationIntrospector());
        }
        Method accessor = jsonValueMethod.getAnnotated();
        if (config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(accessor, config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        return EnumResolver.constructUnsafeUsingMethod(enumClass, accessor, config.getAnnotationIntrospector());
    }
}
