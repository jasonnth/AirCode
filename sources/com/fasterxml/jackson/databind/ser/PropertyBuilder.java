package com.fasterxml.jackson.databind.ser;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonInclude.Value;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Typing;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.util.Annotations;
import com.fasterxml.jackson.databind.util.ClassUtil;

public class PropertyBuilder {
    private static final Object NO_DEFAULT_MARKER = Boolean.FALSE;
    protected final AnnotationIntrospector _annotationIntrospector;
    protected final BeanDescription _beanDesc;
    protected final SerializationConfig _config;
    protected Object _defaultBean;
    protected final Value _defaultInclusion;
    protected final boolean _useRealPropertyDefaults;

    public PropertyBuilder(SerializationConfig config, BeanDescription beanDesc) {
        this._config = config;
        this._beanDesc = beanDesc;
        Value inclPerType = Value.merge(beanDesc.findPropertyInclusion(Value.empty()), config.getDefaultPropertyInclusion(beanDesc.getBeanClass(), Value.empty()));
        this._defaultInclusion = Value.merge(config.getDefaultPropertyInclusion(), inclPerType);
        this._useRealPropertyDefaults = inclPerType.getValueInclusion() == Include.NON_DEFAULT;
        this._annotationIntrospector = this._config.getAnnotationIntrospector();
    }

    public Annotations getClassAnnotations() {
        return this._beanDesc.getClassAnnotations();
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0125  */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0141  */
    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.ser.BeanPropertyWriter buildWriter(com.fasterxml.jackson.databind.SerializerProvider r24, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition r25, com.fasterxml.jackson.databind.JavaType r26, com.fasterxml.jackson.databind.JsonSerializer<?> r27, com.fasterxml.jackson.databind.jsontype.TypeSerializer r28, com.fasterxml.jackson.databind.jsontype.TypeSerializer r29, com.fasterxml.jackson.databind.introspect.AnnotatedMember r30, boolean r31) throws com.fasterxml.jackson.databind.JsonMappingException {
        /*
            r23 = this;
            r0 = r23
            r1 = r30
            r2 = r31
            r3 = r26
            com.fasterxml.jackson.databind.JavaType r11 = r0.findSerializationType(r1, r2, r3)     // Catch:{ JsonMappingException -> 0x00db }
            if (r29 == 0) goto L_0x004a
            if (r11 != 0) goto L_0x0012
            r11 = r26
        L_0x0012:
            com.fasterxml.jackson.databind.JavaType r15 = r11.getContentType()
            if (r15 != 0) goto L_0x0041
            r0 = r23
            com.fasterxml.jackson.databind.BeanDescription r5 = r0._beanDesc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "serialization type "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r11)
            java.lang.String r7 = " has no content"
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.String r6 = r6.toString()
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r0 = r24
            r1 = r25
            r0.reportBadPropertyDefinition(r5, r1, r6, r7)
        L_0x0041:
            r0 = r29
            com.fasterxml.jackson.databind.JavaType r11 = r11.withContentTypeHandler(r0)
            r11.getContentType()
        L_0x004a:
            r22 = 0
            r12 = 0
            if (r11 != 0) goto L_0x00f3
            r14 = r26
        L_0x0051:
            r0 = r23
            com.fasterxml.jackson.databind.SerializationConfig r5 = r0._config
            java.lang.Class r6 = r14.getRawClass()
            r0 = r23
            com.fasterxml.jackson.annotation.JsonInclude$Value r7 = r0._defaultInclusion
            com.fasterxml.jackson.annotation.JsonInclude$Value r18 = r5.getDefaultPropertyInclusion(r6, r7)
            com.fasterxml.jackson.annotation.JsonInclude$Value r5 = r25.findInclusion()
            r0 = r18
            com.fasterxml.jackson.annotation.JsonInclude$Value r18 = r0.withOverrides(r5)
            com.fasterxml.jackson.annotation.JsonInclude$Include r19 = r18.getValueInclusion()
            com.fasterxml.jackson.annotation.JsonInclude$Include r5 = com.fasterxml.jackson.annotation.JsonInclude.Include.USE_DEFAULTS
            r0 = r19
            if (r0 != r5) goto L_0x0077
            com.fasterxml.jackson.annotation.JsonInclude$Include r19 = com.fasterxml.jackson.annotation.JsonInclude.Include.ALWAYS
        L_0x0077:
            int[] r5 = com.fasterxml.jackson.databind.ser.PropertyBuilder.C39951.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include
            int r6 = r19.ordinal()
            r5 = r5[r6]
            switch(r5) {
                case 1: goto L_0x00f6;
                case 2: goto L_0x0153;
                case 3: goto L_0x0160;
                case 4: goto L_0x0167;
                default: goto L_0x0082;
            }
        L_0x0082:
            boolean r5 = r14.isContainerType()
            if (r5 == 0) goto L_0x016a
            r0 = r23
            com.fasterxml.jackson.databind.SerializationConfig r5 = r0._config
            com.fasterxml.jackson.databind.SerializationFeature r6 = com.fasterxml.jackson.databind.SerializationFeature.WRITE_EMPTY_JSON_ARRAYS
            boolean r5 = r5.isEnabled(r6)
            if (r5 != 0) goto L_0x016a
            java.lang.Object r22 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            r13 = r22
        L_0x0098:
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r4 = new com.fasterxml.jackson.databind.ser.BeanPropertyWriter
            r0 = r23
            com.fasterxml.jackson.databind.BeanDescription r5 = r0._beanDesc
            com.fasterxml.jackson.databind.util.Annotations r7 = r5.getClassAnnotations()
            r5 = r25
            r6 = r30
            r8 = r26
            r9 = r27
            r10 = r28
            r4.<init>(r5, r6, r7, r8, r9, r10, r11, r12, r13)
            r0 = r23
            com.fasterxml.jackson.databind.AnnotationIntrospector r5 = r0._annotationIntrospector
            r0 = r30
            java.lang.Object r20 = r5.findNullSerializer(r0)
            if (r20 == 0) goto L_0x00c8
            r0 = r24
            r1 = r30
            r2 = r20
            com.fasterxml.jackson.databind.JsonSerializer r5 = r0.serializerInstance(r1, r2)
            r4.assignNullSerializer(r5)
        L_0x00c8:
            r0 = r23
            com.fasterxml.jackson.databind.AnnotationIntrospector r5 = r0._annotationIntrospector
            r0 = r30
            com.fasterxml.jackson.databind.util.NameTransformer r21 = r5.findUnwrappingNameTransformer(r0)
            if (r21 == 0) goto L_0x00da
            r0 = r21
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r4 = r4.unwrappingWriter(r0)
        L_0x00da:
            return r4
        L_0x00db:
            r17 = move-exception
            r0 = r23
            com.fasterxml.jackson.databind.BeanDescription r5 = r0._beanDesc
            java.lang.String r6 = r17.getMessage()
            r7 = 0
            java.lang.Object[] r7 = new java.lang.Object[r7]
            r0 = r24
            r1 = r25
            java.lang.Object r5 = r0.reportBadPropertyDefinition(r5, r1, r6, r7)
            com.fasterxml.jackson.databind.ser.BeanPropertyWriter r5 = (com.fasterxml.jackson.databind.ser.BeanPropertyWriter) r5
            r4 = r5
            goto L_0x00da
        L_0x00f3:
            r14 = r11
            goto L_0x0051
        L_0x00f6:
            r0 = r23
            boolean r5 = r0._useRealPropertyDefaults
            if (r5 == 0) goto L_0x0139
            java.lang.Object r16 = r23.getDefaultBean()
            if (r16 == 0) goto L_0x0139
            com.fasterxml.jackson.databind.MapperFeature r5 = com.fasterxml.jackson.databind.MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS
            r0 = r24
            boolean r5 = r0.isEnabled(r5)
            if (r5 == 0) goto L_0x011b
            r0 = r23
            com.fasterxml.jackson.databind.SerializationConfig r5 = r0._config
            com.fasterxml.jackson.databind.MapperFeature r6 = com.fasterxml.jackson.databind.MapperFeature.OVERRIDE_PUBLIC_ACCESS_MODIFIERS
            boolean r5 = r5.isEnabled(r6)
            r0 = r30
            r0.fixAccess(r5)
        L_0x011b:
            r0 = r30
            r1 = r16
            java.lang.Object r22 = r0.getValue(r1)     // Catch:{ Exception -> 0x012a }
        L_0x0123:
            if (r22 != 0) goto L_0x0141
            r12 = 1
            r13 = r22
            goto L_0x0098
        L_0x012a:
            r17 = move-exception
            java.lang.String r5 = r25.getName()
            r0 = r23
            r1 = r17
            r2 = r16
            r0._throwWrapped(r1, r5, r2)
            goto L_0x0123
        L_0x0139:
            r0 = r23
            java.lang.Object r22 = r0.getDefaultValue(r14)
            r12 = 1
            goto L_0x0123
        L_0x0141:
            java.lang.Class r5 = r22.getClass()
            boolean r5 = r5.isArray()
            if (r5 == 0) goto L_0x016e
            java.lang.Object r22 = com.fasterxml.jackson.databind.util.ArrayBuilders.getArrayComparator(r22)
            r13 = r22
            goto L_0x0098
        L_0x0153:
            r12 = 1
            boolean r5 = r14.isReferenceType()
            if (r5 == 0) goto L_0x016a
            java.lang.Object r22 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            r13 = r22
            goto L_0x0098
        L_0x0160:
            r12 = 1
            java.lang.Object r22 = com.fasterxml.jackson.databind.ser.BeanPropertyWriter.MARKER_FOR_EMPTY
            r13 = r22
            goto L_0x0098
        L_0x0167:
            r12 = 1
            goto L_0x0082
        L_0x016a:
            r13 = r22
            goto L_0x0098
        L_0x016e:
            r13 = r22
            goto L_0x0098
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder.buildWriter(com.fasterxml.jackson.databind.SerializerProvider, com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition, com.fasterxml.jackson.databind.JavaType, com.fasterxml.jackson.databind.JsonSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.jsontype.TypeSerializer, com.fasterxml.jackson.databind.introspect.AnnotatedMember, boolean):com.fasterxml.jackson.databind.ser.BeanPropertyWriter");
    }

    /* access modifiers changed from: protected */
    public JavaType findSerializationType(Annotated a, boolean useStaticTyping, JavaType declaredType) throws JsonMappingException {
        JavaType secondary = this._annotationIntrospector.refineSerializationType(this._config, a, declaredType);
        if (secondary != declaredType) {
            Class<?> serClass = secondary.getRawClass();
            Class<?> rawDeclared = declaredType.getRawClass();
            if (!serClass.isAssignableFrom(rawDeclared) && !rawDeclared.isAssignableFrom(serClass)) {
                throw new IllegalArgumentException("Illegal concrete-type annotation for method '" + a.getName() + "': class " + serClass.getName() + " not a super-type of (declared) class " + rawDeclared.getName());
            }
            useStaticTyping = true;
            declaredType = secondary;
        }
        Typing typing = this._annotationIntrospector.findSerializationTyping(a);
        if (!(typing == null || typing == Typing.DEFAULT_TYPING)) {
            useStaticTyping = typing == Typing.STATIC;
        }
        if (useStaticTyping) {
            return declaredType.withStaticTyping();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultBean() {
        Object def = this._defaultBean;
        if (def == null) {
            def = this._beanDesc.instantiateBean(this._config.canOverrideAccessModifiers());
            if (def == null) {
                def = NO_DEFAULT_MARKER;
            }
            this._defaultBean = def;
        }
        if (def == NO_DEFAULT_MARKER) {
            return null;
        }
        return this._defaultBean;
    }

    /* access modifiers changed from: protected */
    public Object getDefaultValue(JavaType type) {
        Class<?> cls = type.getRawClass();
        Class<?> prim = ClassUtil.primitiveType(cls);
        if (prim != null) {
            return ClassUtil.defaultValue(prim);
        }
        if (type.isContainerType() || type.isReferenceType()) {
            return Include.NON_EMPTY;
        }
        if (cls == String.class) {
            return "";
        }
        return null;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.lang.Exception, code=java.lang.Throwable, for r5v0, types: [java.lang.Throwable, java.lang.Exception] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object _throwWrapped(java.lang.Throwable r5, java.lang.String r6, java.lang.Object r7) {
        /*
            r4 = this;
            r0 = r5
        L_0x0001:
            java.lang.Throwable r1 = r0.getCause()
            if (r1 == 0) goto L_0x000c
            java.lang.Throwable r0 = r0.getCause()
            goto L_0x0001
        L_0x000c:
            boolean r1 = r0 instanceof java.lang.Error
            if (r1 == 0) goto L_0x0013
            java.lang.Error r0 = (java.lang.Error) r0
            throw r0
        L_0x0013:
            boolean r1 = r0 instanceof java.lang.RuntimeException
            if (r1 == 0) goto L_0x001a
            java.lang.RuntimeException r0 = (java.lang.RuntimeException) r0
            throw r0
        L_0x001a:
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Failed to get property '"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.StringBuilder r2 = r2.append(r6)
            java.lang.String r3 = "' of default "
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.Class r3 = r7.getClass()
            java.lang.String r3 = r3.getName()
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r3 = " instance"
            java.lang.StringBuilder r2 = r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.ser.PropertyBuilder._throwWrapped(java.lang.Exception, java.lang.String, java.lang.Object):java.lang.Object");
    }
}
