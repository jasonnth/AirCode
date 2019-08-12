package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;

public interface BeanProperty {
    public static final Value EMPTY_FORMAT = new Value();
    public static final JsonInclude.Value EMPTY_INCLUDE = JsonInclude.Value.empty();

    public static class Std implements BeanProperty {
        protected final Annotations _contextAnnotations;
        protected final AnnotatedMember _member;
        protected final PropertyMetadata _metadata;
        protected final PropertyName _name;
        protected final JavaType _type;
        protected final PropertyName _wrapperName;

        public Std(PropertyName name, JavaType type, PropertyName wrapperName, Annotations contextAnnotations, AnnotatedMember member, PropertyMetadata metadata) {
            this._name = name;
            this._type = type;
            this._wrapperName = wrapperName;
            this._metadata = metadata;
            this._member = member;
            this._contextAnnotations = contextAnnotations;
        }

        public <A extends Annotation> A getAnnotation(Class<A> acls) {
            if (this._member == null) {
                return null;
            }
            return this._member.getAnnotation(acls);
        }

        public Value findPropertyFormat(MapperConfig<?> config, Class<?> baseType) {
            Value v0 = config.getDefaultPropertyFormat(baseType);
            AnnotationIntrospector intr = config.getAnnotationIntrospector();
            if (intr == null || this._member == null) {
                return v0;
            }
            Value v = intr.findFormat(this._member);
            return v != null ? v0.withOverrides(v) : v0;
        }

        public JsonInclude.Value findPropertyInclusion(MapperConfig<?> config, Class<?> baseType) {
            JsonInclude.Value v0 = config.getDefaultPropertyInclusion(baseType);
            AnnotationIntrospector intr = config.getAnnotationIntrospector();
            if (intr == null || this._member == null) {
                return v0;
            }
            JsonInclude.Value v = intr.findPropertyInclusion(this._member);
            return v != null ? v0.withOverrides(v) : v0;
        }

        public JavaType getType() {
            return this._type;
        }

        public PropertyName getWrapperName() {
            return this._wrapperName;
        }

        public PropertyMetadata getMetadata() {
            return this._metadata;
        }

        public AnnotatedMember getMember() {
            return this._member;
        }

        public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor, SerializerProvider provider) {
            throw new UnsupportedOperationException("Instances of " + getClass().getName() + " should not get visited");
        }
    }

    void depositSchemaProperty(JsonObjectFormatVisitor jsonObjectFormatVisitor, SerializerProvider serializerProvider) throws JsonMappingException;

    Value findPropertyFormat(MapperConfig<?> mapperConfig, Class<?> cls);

    JsonInclude.Value findPropertyInclusion(MapperConfig<?> mapperConfig, Class<?> cls);

    <A extends Annotation> A getAnnotation(Class<A> cls);

    AnnotatedMember getMember();

    PropertyMetadata getMetadata();

    JavaType getType();
}
