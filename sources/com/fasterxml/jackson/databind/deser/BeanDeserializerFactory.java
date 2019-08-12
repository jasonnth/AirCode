package com.fasterxml.jackson.databind.deser;

import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import com.fasterxml.jackson.annotation.ObjectIdResolver;
import com.fasterxml.jackson.databind.AbstractTypeResolver;
import com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.BeanProperty.Std;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder.Value;
import com.fasterxml.jackson.databind.cfg.ConfigOverride;
import com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig;
import com.fasterxml.jackson.databind.deser.impl.ErrorThrowingDeserializer;
import com.fasterxml.jackson.databind.deser.impl.FieldProperty;
import com.fasterxml.jackson.databind.deser.impl.MethodProperty;
import com.fasterxml.jackson.databind.deser.impl.ObjectIdReader;
import com.fasterxml.jackson.databind.deser.impl.PropertyBasedObjectIdGenerator;
import com.fasterxml.jackson.databind.deser.impl.SetterlessProperty;
import com.fasterxml.jackson.databind.deser.std.ThrowableDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.AnnotatedParameter;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.introspect.ObjectIdInfo;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.SimpleBeanPropertyDefinition;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class BeanDeserializerFactory extends BasicDeserializerFactory implements Serializable {
    private static final Class<?>[] INIT_CAUSE_PARAMS = {Throwable.class};
    private static final Class<?>[] NO_VIEWS = new Class[0];
    public static final BeanDeserializerFactory instance = new BeanDeserializerFactory(new DeserializerFactoryConfig());

    public BeanDeserializerFactory(DeserializerFactoryConfig config) {
        super(config);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public DeserializerFactory withConfig(DeserializerFactoryConfig config) {
        if (this._factoryConfig == config) {
            return this;
        }
        if (getClass() == BeanDeserializerFactory.class) {
            return new BeanDeserializerFactory(config);
        }
        throw new IllegalStateException("Subtype of BeanDeserializerFactory (" + getClass().getName() + ") has not properly overridden method 'withAdditionalDeserializers': can not instantiate subtype with " + "additional deserializer definitions");
    }

    public JsonDeserializer<Object> createBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        JsonDeserializer<Object> custom = _findCustomBeanDeserializer(type, config, beanDesc);
        if (custom != null) {
            return custom;
        }
        if (type.isThrowable()) {
            return buildThrowableDeserializer(ctxt, type, beanDesc);
        }
        if (type.isAbstract() && !type.isPrimitive() && !type.isEnumType()) {
            JavaType concreteType = materializeAbstractType(ctxt, type, beanDesc);
            if (concreteType != null) {
                return buildBeanDeserializer(ctxt, concreteType, config.introspect(concreteType));
            }
        }
        JsonDeserializer<Object> deser = findStdDeserializer(ctxt, type, beanDesc);
        if (deser != null) {
            return deser;
        }
        if (!isPotentialBeanType(type.getRawClass())) {
            return null;
        }
        return buildBeanDeserializer(ctxt, type, beanDesc);
    }

    public JsonDeserializer<Object> createBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription beanDesc, Class<?> builderClass) throws JsonMappingException {
        return buildBuilderBasedDeserializer(ctxt, valueType, ctxt.getConfig().introspectForBuilder(ctxt.constructType(builderClass)));
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findStdDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deser = findDefaultDeserializer(ctxt, type, beanDesc);
        if (deser != null && this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                deser = mod.modifyDeserializer(ctxt.getConfig(), beanDesc, deser);
            }
        }
        return deser;
    }

    /* access modifiers changed from: protected */
    public JavaType materializeAbstractType(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        for (AbstractTypeResolver r : this._factoryConfig.abstractTypeResolvers()) {
            JavaType concrete = r.resolveAbstractType(ctxt.getConfig(), beanDesc);
            if (concrete != null) {
                return concrete;
            }
        }
        return null;
    }

    public JsonDeserializer<Object> buildBeanDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        JsonDeserializer<?> deserializer;
        try {
            ValueInstantiator valueInstantiator = findValueInstantiator(ctxt, beanDesc);
            BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, beanDesc);
            builder.setValueInstantiator(valueInstantiator);
            addBeanProps(ctxt, beanDesc, builder);
            addObjectIdReader(ctxt, beanDesc, builder);
            addReferenceProperties(ctxt, beanDesc, builder);
            addInjectables(ctxt, beanDesc, builder);
            DeserializationConfig config = ctxt.getConfig();
            if (this._factoryConfig.hasDeserializerModifiers()) {
                for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                    builder = mod.updateBuilder(config, beanDesc, builder);
                }
            }
            if (!type.isAbstract() || valueInstantiator.canInstantiate()) {
                deserializer = builder.build();
            } else {
                deserializer = builder.buildAbstract();
            }
            if (!this._factoryConfig.hasDeserializerModifiers()) {
                return deserializer;
            }
            for (BeanDeserializerModifier mod2 : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod2.modifyDeserializer(config, beanDesc, deserializer);
            }
            return deserializer;
        } catch (NoClassDefFoundError error) {
            return new ErrorThrowingDeserializer(error);
        }
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> buildBuilderBasedDeserializer(DeserializationContext ctxt, JavaType valueType, BeanDescription builderDesc) throws JsonMappingException {
        ValueInstantiator valueInstantiator = findValueInstantiator(ctxt, builderDesc);
        DeserializationConfig config = ctxt.getConfig();
        BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, builderDesc);
        builder.setValueInstantiator(valueInstantiator);
        addBeanProps(ctxt, builderDesc, builder);
        addObjectIdReader(ctxt, builderDesc, builder);
        addReferenceProperties(ctxt, builderDesc, builder);
        addInjectables(ctxt, builderDesc, builder);
        Value builderConfig = builderDesc.findPOJOBuilderConfig();
        String buildMethodName = builderConfig == null ? "build" : builderConfig.buildMethodName;
        AnnotatedMethod buildMethod = builderDesc.findMethod(buildMethodName, null);
        if (buildMethod != null && config.canOverrideAccessModifiers()) {
            ClassUtil.checkAndFixAccess(buildMethod.getMember(), config.isEnabled(MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS));
        }
        builder.setPOJOBuilder(buildMethod, builderConfig);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                builder = mod.updateBuilder(config, builderDesc, builder);
            }
        }
        JsonDeserializer<?> deserializer = builder.buildBuilderBased(valueType, buildMethodName);
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod2 : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod2.modifyDeserializer(config, builderDesc, deserializer);
            }
        }
        return deserializer;
    }

    /* access modifiers changed from: protected */
    public void addObjectIdReader(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        JavaType idType;
        SettableBeanProperty idProp;
        ObjectIdGenerator<?> gen;
        ObjectIdInfo objectIdInfo = beanDesc.getObjectIdInfo();
        if (objectIdInfo != null) {
            Class<?> implClass = objectIdInfo.getGeneratorType();
            ObjectIdResolver resolver = ctxt.objectIdResolverInstance(beanDesc.getClassInfo(), objectIdInfo);
            if (implClass == PropertyGenerator.class) {
                PropertyName propName = objectIdInfo.getPropertyName();
                idProp = builder.findProperty(propName);
                if (idProp == null) {
                    throw new IllegalArgumentException("Invalid Object Id definition for " + beanDesc.getBeanClass().getName() + ": can not find property with name '" + propName + "'");
                }
                idType = idProp.getType();
                gen = new PropertyBasedObjectIdGenerator<>(objectIdInfo.getScope());
            } else {
                idType = ctxt.getTypeFactory().findTypeParameters(ctxt.constructType(implClass), ObjectIdGenerator.class)[0];
                idProp = null;
                gen = ctxt.objectIdGeneratorInstance(beanDesc.getClassInfo(), objectIdInfo);
            }
            builder.setObjectIdReader(ObjectIdReader.construct(idType, objectIdInfo.getPropertyName(), gen, ctxt.findRootValueDeserializer(idType), idProp, resolver));
        }
    }

    public JsonDeserializer<Object> buildThrowableDeserializer(DeserializationContext ctxt, JavaType type, BeanDescription beanDesc) throws JsonMappingException {
        DeserializationConfig config = ctxt.getConfig();
        BeanDeserializerBuilder builder = constructBeanDeserializerBuilder(ctxt, beanDesc);
        builder.setValueInstantiator(findValueInstantiator(ctxt, beanDesc));
        addBeanProps(ctxt, beanDesc, builder);
        AnnotatedMethod am = beanDesc.findMethod("initCause", INIT_CAUSE_PARAMS);
        if (am != null) {
            SettableBeanProperty prop = constructSettableProperty(ctxt, beanDesc, SimpleBeanPropertyDefinition.construct(ctxt.getConfig(), am, new PropertyName("cause")), am.getParameterType(0));
            if (prop != null) {
                builder.addOrReplaceProperty(prop, true);
            }
        }
        builder.addIgnorable("localizedMessage");
        builder.addIgnorable("suppressed");
        builder.addIgnorable("message");
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod : this._factoryConfig.deserializerModifiers()) {
                builder = mod.updateBuilder(config, beanDesc, builder);
            }
        }
        JsonDeserializer<?> deserializer = builder.build();
        if (deserializer instanceof BeanDeserializer) {
            deserializer = new ThrowableDeserializer<>((BeanDeserializer) deserializer);
        }
        if (this._factoryConfig.hasDeserializerModifiers()) {
            for (BeanDeserializerModifier mod2 : this._factoryConfig.deserializerModifiers()) {
                deserializer = mod2.modifyDeserializer(config, beanDesc, deserializer);
            }
        }
        return deserializer;
    }

    /* access modifiers changed from: protected */
    public BeanDeserializerBuilder constructBeanDeserializerBuilder(DeserializationContext ctxt, BeanDescription beanDesc) {
        return new BeanDeserializerBuilder(beanDesc, ctxt.getConfig());
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e2  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void addBeanProps(com.fasterxml.jackson.databind.DeserializationContext r38, com.fasterxml.jackson.databind.BeanDescription r39, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder r40) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r37 = this;
            com.fasterxml.jackson.databind.JavaType r5 = r39.getType()
            boolean r5 = r5.isAbstract()
            if (r5 != 0) goto L_0x0057
            r24 = 1
        L_0x000c:
            if (r24 == 0) goto L_0x005a
            com.fasterxml.jackson.databind.deser.ValueInstantiator r5 = r40.getValueInstantiator()
            com.fasterxml.jackson.databind.DeserializationConfig r6 = r38.getConfig()
            com.fasterxml.jackson.databind.deser.SettableBeanProperty[] r16 = r5.getFromObjectArguments(r6)
        L_0x001a:
            if (r16 == 0) goto L_0x005d
            r18 = 1
        L_0x001e:
            com.fasterxml.jackson.databind.DeserializationConfig r5 = r38.getConfig()
            java.lang.Class r6 = r39.getBeanClass()
            com.fasterxml.jackson.databind.introspect.AnnotatedClass r7 = r39.getClassInfo()
            com.fasterxml.jackson.annotation.JsonIgnoreProperties$Value r21 = r5.getDefaultPropertyIgnorals(r6, r7)
            if (r21 == 0) goto L_0x0060
            boolean r22 = r21.getIgnoreUnknown()
            r0 = r40
            r1 = r22
            r0.setIgnoreUnknownProperties(r1)
            java.util.Set r10 = r21.getIgnored()
            java.util.Iterator r19 = r10.iterator()
        L_0x0043:
            boolean r5 = r19.hasNext()
            if (r5 == 0) goto L_0x0064
            java.lang.Object r32 = r19.next()
            java.lang.String r32 = (java.lang.String) r32
            r0 = r40
            r1 = r32
            r0.addIgnorable(r1)
            goto L_0x0043
        L_0x0057:
            r24 = 0
            goto L_0x000c
        L_0x005a:
            r16 = 0
            goto L_0x001a
        L_0x005d:
            r18 = 0
            goto L_0x001e
        L_0x0060:
            java.util.Set r10 = java.util.Collections.emptySet()
        L_0x0064:
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r12 = r39.findAnySetter()
            r11 = 0
            if (r12 == 0) goto L_0x009c
            r0 = r37
            r1 = r38
            r2 = r39
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r5 = r0.constructAnySetter(r1, r2, r12)
            r0 = r40
            r0.setAnySetter(r5)
        L_0x007a:
            if (r12 != 0) goto L_0x00b2
            if (r11 != 0) goto L_0x00b2
            java.util.Set r23 = r39.getIgnoredPropertyNames()
            if (r23 == 0) goto L_0x00b2
            java.util.Iterator r19 = r23.iterator()
        L_0x0088:
            boolean r5 = r19.hasNext()
            if (r5 == 0) goto L_0x00b2
            java.lang.Object r32 = r19.next()
            java.lang.String r32 = (java.lang.String) r32
            r0 = r40
            r1 = r32
            r0.addIgnorable(r1)
            goto L_0x0088
        L_0x009c:
            com.fasterxml.jackson.databind.introspect.AnnotatedMember r11 = r39.findAnySetterField()
            if (r11 == 0) goto L_0x007a
            r0 = r37
            r1 = r38
            r2 = r39
            com.fasterxml.jackson.databind.deser.SettableAnyProperty r5 = r0.constructAnySetter(r1, r2, r11)
            r0 = r40
            r0.setAnySetter(r5)
            goto L_0x007a
        L_0x00b2:
            com.fasterxml.jackson.databind.MapperFeature r5 = com.fasterxml.jackson.databind.MapperFeature.USE_GETTERS_AS_SETTERS
            r0 = r38
            boolean r5 = r0.isEnabled(r5)
            if (r5 == 0) goto L_0x0109
            com.fasterxml.jackson.databind.MapperFeature r5 = com.fasterxml.jackson.databind.MapperFeature.AUTO_DETECT_GETTERS
            r0 = r38
            boolean r5 = r0.isEnabled(r5)
            if (r5 == 0) goto L_0x0109
            r35 = 1
        L_0x00c8:
            java.util.List r9 = r39.findProperties()
            r5 = r37
            r6 = r38
            r7 = r39
            r8 = r40
            java.util.List r31 = r5.filterBeanProps(r6, r7, r8, r9, r10)
            r0 = r37
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r5 = r0._factoryConfig
            boolean r5 = r5.hasDeserializerModifiers()
            if (r5 == 0) goto L_0x010c
            r0 = r37
            com.fasterxml.jackson.databind.cfg.DeserializerFactoryConfig r5 = r0._factoryConfig
            java.lang.Iterable r5 = r5.deserializerModifiers()
            java.util.Iterator r19 = r5.iterator()
        L_0x00ee:
            boolean r5 = r19.hasNext()
            if (r5 == 0) goto L_0x010c
            java.lang.Object r26 = r19.next()
            com.fasterxml.jackson.databind.deser.BeanDeserializerModifier r26 = (com.fasterxml.jackson.databind.deser.BeanDeserializerModifier) r26
            com.fasterxml.jackson.databind.DeserializationConfig r5 = r38.getConfig()
            r0 = r26
            r1 = r39
            r2 = r31
            java.util.List r31 = r0.updateProperties(r5, r1, r2)
            goto L_0x00ee
        L_0x0109:
            r35 = 0
            goto L_0x00c8
        L_0x010c:
            java.util.Iterator r19 = r31.iterator()
        L_0x0110:
            boolean r5 = r19.hasNext()
            if (r5 == 0) goto L_0x022c
            java.lang.Object r30 = r19.next()
            com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r30 = (com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition) r30
            r29 = 0
            boolean r5 = r30.hasSetter()
            if (r5 == 0) goto L_0x018e
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r5 = r30.getSetter()
            r6 = 0
            com.fasterxml.jackson.databind.JavaType r33 = r5.getParameterType(r6)
            r0 = r37
            r1 = r38
            r2 = r39
            r3 = r30
            r4 = r33
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r29 = r0.constructSettableProperty(r1, r2, r3, r4)
        L_0x013b:
            if (r18 == 0) goto L_0x0208
            boolean r5 = r30.hasConstructorParameter()
            if (r5 == 0) goto L_0x0208
            java.lang.String r28 = r30.getName()
            r15 = 0
            if (r16 == 0) goto L_0x016c
            r13 = r16
            int r0 = r13.length
            r25 = r0
            r20 = 0
        L_0x0151:
            r0 = r20
            r1 = r25
            if (r0 >= r1) goto L_0x016c
            r14 = r13[r20]
            java.lang.String r5 = r14.getName()
            r0 = r28
            boolean r5 = r0.equals(r5)
            if (r5 == 0) goto L_0x01dd
            boolean r5 = r14 instanceof com.fasterxml.jackson.databind.deser.CreatorProperty
            if (r5 == 0) goto L_0x01dd
            r15 = r14
            com.fasterxml.jackson.databind.deser.CreatorProperty r15 = (com.fasterxml.jackson.databind.deser.CreatorProperty) r15
        L_0x016c:
            if (r15 != 0) goto L_0x01f8
            java.util.ArrayList r27 = new java.util.ArrayList
            r27.<init>()
            r13 = r16
            int r0 = r13.length
            r25 = r0
            r20 = 0
        L_0x017a:
            r0 = r20
            r1 = r25
            if (r0 >= r1) goto L_0x01e1
            r14 = r13[r20]
            java.lang.String r5 = r14.getName()
            r0 = r27
            r0.add(r5)
            int r20 = r20 + 1
            goto L_0x017a
        L_0x018e:
            boolean r5 = r30.hasField()
            if (r5 == 0) goto L_0x01ab
            com.fasterxml.jackson.databind.introspect.AnnotatedField r5 = r30.getField()
            com.fasterxml.jackson.databind.JavaType r33 = r5.getType()
            r0 = r37
            r1 = r38
            r2 = r39
            r3 = r30
            r4 = r33
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r29 = r0.constructSettableProperty(r1, r2, r3, r4)
            goto L_0x013b
        L_0x01ab:
            if (r35 == 0) goto L_0x013b
            boolean r5 = r30.hasGetter()
            if (r5 == 0) goto L_0x013b
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r17 = r30.getGetter()
            java.lang.Class r34 = r17.getRawType()
            java.lang.Class<java.util.Collection> r5 = java.util.Collection.class
            r0 = r34
            boolean r5 = r5.isAssignableFrom(r0)
            if (r5 != 0) goto L_0x01cf
            java.lang.Class<java.util.Map> r5 = java.util.Map.class
            r0 = r34
            boolean r5 = r5.isAssignableFrom(r0)
            if (r5 == 0) goto L_0x013b
        L_0x01cf:
            r0 = r37
            r1 = r38
            r2 = r39
            r3 = r30
            com.fasterxml.jackson.databind.deser.SettableBeanProperty r29 = r0.constructSetterlessProperty(r1, r2, r3)
            goto L_0x013b
        L_0x01dd:
            int r20 = r20 + 1
            goto L_0x0151
        L_0x01e1:
            java.lang.String r5 = "Could not find creator property with name '%s' (known Creator properties: %s)"
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r7 = 0
            r6[r7] = r28
            r7 = 1
            r6[r7] = r27
            r0 = r38
            r1 = r39
            r2 = r30
            r0.reportBadPropertyDefinition(r1, r2, r5, r6)
            goto L_0x0110
        L_0x01f8:
            if (r29 == 0) goto L_0x01ff
            r0 = r29
            r15.setFallbackSetter(r0)
        L_0x01ff:
            r29 = r15
            r0 = r40
            r0.addCreatorProperty(r15)
            goto L_0x0110
        L_0x0208:
            if (r29 == 0) goto L_0x0110
            java.lang.Class[] r36 = r30.findViews()
            if (r36 != 0) goto L_0x021c
            com.fasterxml.jackson.databind.MapperFeature r5 = com.fasterxml.jackson.databind.MapperFeature.DEFAULT_VIEW_INCLUSION
            r0 = r38
            boolean r5 = r0.isEnabled(r5)
            if (r5 != 0) goto L_0x021c
            java.lang.Class<?>[] r36 = NO_VIEWS
        L_0x021c:
            r0 = r29
            r1 = r36
            r0.setViews(r1)
            r0 = r40
            r1 = r29
            r0.addProperty(r1)
            goto L_0x0110
        L_0x022c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.deser.BeanDeserializerFactory.addBeanProps(com.fasterxml.jackson.databind.DeserializationContext, com.fasterxml.jackson.databind.BeanDescription, com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder):void");
    }

    /* access modifiers changed from: protected */
    public List<BeanPropertyDefinition> filterBeanProps(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder, List<BeanPropertyDefinition> propDefsIn, Set<String> ignored) throws JsonMappingException {
        ArrayList<BeanPropertyDefinition> result = new ArrayList<>(Math.max(4, propDefsIn.size()));
        HashMap<Class<?>, Boolean> ignoredTypes = new HashMap<>();
        for (BeanPropertyDefinition property : propDefsIn) {
            String name = property.getName();
            if (!ignored.contains(name)) {
                if (!property.hasConstructorParameter()) {
                    Class<?> rawPropertyType = null;
                    if (property.hasSetter()) {
                        rawPropertyType = property.getSetter().getRawParameterType(0);
                    } else if (property.hasField()) {
                        rawPropertyType = property.getField().getRawType();
                    }
                    if (rawPropertyType != null && isIgnorableType(ctxt.getConfig(), beanDesc, rawPropertyType, ignoredTypes)) {
                        builder.addIgnorable(name);
                    }
                }
                result.add(property);
            }
        }
        return result;
    }

    /* access modifiers changed from: protected */
    public void addReferenceProperties(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        JavaType type;
        Map<String, AnnotatedMember> refs = beanDesc.findBackReferenceProperties();
        if (refs != null) {
            for (Entry<String, AnnotatedMember> en : refs.entrySet()) {
                String name = (String) en.getKey();
                AnnotatedMember m = (AnnotatedMember) en.getValue();
                if (m instanceof AnnotatedMethod) {
                    type = ((AnnotatedMethod) m).getParameterType(0);
                } else {
                    type = m.getType();
                    if (m instanceof AnnotatedParameter) {
                        ctxt.reportBadTypeDefinition(beanDesc, "Can not bind back references as Creator parameters: type %s (reference '%s', parameter index #%d)", beanDesc.getBeanClass().getName(), name, Integer.valueOf(((AnnotatedParameter) m).getIndex()));
                    }
                }
                builder.addBackReferenceProperty(name, constructSettableProperty(ctxt, beanDesc, SimpleBeanPropertyDefinition.construct(ctxt.getConfig(), m, PropertyName.construct(name)), type));
            }
        }
    }

    /* access modifiers changed from: protected */
    public void addInjectables(DeserializationContext ctxt, BeanDescription beanDesc, BeanDeserializerBuilder builder) throws JsonMappingException {
        Map<Object, AnnotatedMember> raw = beanDesc.findInjectables();
        if (raw != null) {
            for (Entry<Object, AnnotatedMember> entry : raw.entrySet()) {
                AnnotatedMember m = (AnnotatedMember) entry.getValue();
                builder.addInjectable(PropertyName.construct(m.getName()), m.getType(), beanDesc.getClassAnnotations(), m, entry.getKey());
            }
        }
    }

    /* access modifiers changed from: protected */
    public SettableAnyProperty constructAnySetter(DeserializationContext ctxt, BeanDescription beanDesc, AnnotatedMember mutator) throws JsonMappingException {
        JavaType type = null;
        if (mutator instanceof AnnotatedMethod) {
            type = ((AnnotatedMethod) mutator).getParameterType(1);
        } else if (mutator instanceof AnnotatedField) {
            type = ((AnnotatedField) mutator).getType().getContentType();
        }
        JavaType type2 = resolveMemberAndTypeAnnotations(ctxt, mutator, type);
        Std prop = new Std(PropertyName.construct(mutator.getName()), type2, null, beanDesc.getClassAnnotations(), mutator, PropertyMetadata.STD_OPTIONAL);
        JsonDeserializer<Object> deser = findDeserializerFromAnnotation(ctxt, mutator);
        if (deser == null) {
            deser = (JsonDeserializer) type2.getValueHandler();
        }
        if (deser != null) {
            deser = ctxt.handlePrimaryContextualization(deser, prop, type2);
        }
        return new SettableAnyProperty(prop, mutator, type2, deser, (TypeDeserializer) type2.getTypeHandler());
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSettableProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef, JavaType propType0) throws JsonMappingException {
        SettableBeanProperty prop;
        AnnotatedMember mutator = propDef.getNonConstructorMutator();
        if (mutator == null) {
            ctxt.reportBadPropertyDefinition(beanDesc, propDef, "No non-constructor mutator available", new Object[0]);
        }
        JavaType type = resolveMemberAndTypeAnnotations(ctxt, mutator, propType0);
        TypeDeserializer typeDeser = (TypeDeserializer) type.getTypeHandler();
        if (mutator instanceof AnnotatedMethod) {
            prop = new MethodProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedMethod) mutator);
        } else {
            prop = new FieldProperty(propDef, type, typeDeser, beanDesc.getClassAnnotations(), (AnnotatedField) mutator);
        }
        JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, mutator);
        if (deser == null) {
            deser = (JsonDeserializer) type.getValueHandler();
        }
        if (deser != null) {
            prop = prop.withValueDeserializer(ctxt.handlePrimaryContextualization(deser, prop, type));
        }
        ReferenceProperty ref = propDef.findReferenceType();
        if (ref != null && ref.isManagedReference()) {
            prop.setManagedReferenceName(ref.getName());
        }
        ObjectIdInfo objectIdInfo = propDef.findObjectIdInfo();
        if (objectIdInfo != null) {
            prop.setObjectIdInfo(objectIdInfo);
        }
        return prop;
    }

    /* access modifiers changed from: protected */
    public SettableBeanProperty constructSetterlessProperty(DeserializationContext ctxt, BeanDescription beanDesc, BeanPropertyDefinition propDef) throws JsonMappingException {
        AnnotatedMethod getter = propDef.getGetter();
        JavaType type = resolveMemberAndTypeAnnotations(ctxt, getter, getter.getType());
        SettableBeanProperty prop = new SetterlessProperty(propDef, type, (TypeDeserializer) type.getTypeHandler(), beanDesc.getClassAnnotations(), getter);
        JsonDeserializer<?> deser = findDeserializerFromAnnotation(ctxt, getter);
        if (deser == null) {
            deser = (JsonDeserializer) type.getValueHandler();
        }
        if (deser != null) {
            return prop.withValueDeserializer(ctxt.handlePrimaryContextualization(deser, prop, type));
        }
        return prop;
    }

    /* access modifiers changed from: protected */
    public boolean isPotentialBeanType(Class<?> type) {
        String typeStr = ClassUtil.canBeABeanType(type);
        if (typeStr != null) {
            throw new IllegalArgumentException("Can not deserialize Class " + type.getName() + " (of type " + typeStr + ") as a Bean");
        } else if (ClassUtil.isProxyType(type)) {
            throw new IllegalArgumentException("Can not deserialize Proxy class " + type.getName() + " as a Bean");
        } else {
            String typeStr2 = ClassUtil.isLocalType(type, true);
            if (typeStr2 == null) {
                return true;
            }
            throw new IllegalArgumentException("Can not deserialize Class " + type.getName() + " (of type " + typeStr2 + ") as a Bean");
        }
    }

    /* access modifiers changed from: protected */
    public boolean isIgnorableType(DeserializationConfig config, BeanDescription beanDesc, Class<?> type, Map<Class<?>, Boolean> ignoredTypes) {
        Boolean status = (Boolean) ignoredTypes.get(type);
        if (status != null) {
            return status.booleanValue();
        }
        ConfigOverride override = config.findConfigOverride(type);
        if (override != null) {
            status = override.getIsIgnoredType();
        }
        if (status == null) {
            status = config.getAnnotationIntrospector().isIgnorableType(config.introspectClassAnnotations(type).getClassInfo());
            if (status == null) {
                status = Boolean.FALSE;
            }
        }
        ignoredTypes.put(type, status);
        return status.booleanValue();
    }
}
