package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter.Factory;
import com.squareup.moshi.JsonReader.Options;
import icepick.Icepick;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

final class ClassJsonAdapter<T> extends JsonAdapter<T> {
    public static final Factory FACTORY = new Factory() {
        public JsonAdapter<?> create(Type type, Set<? extends Annotation> annotations, Moshi moshi) {
            Class<?> rawType = Types.getRawType(type);
            if (rawType.isInterface() || rawType.isEnum()) {
                return null;
            }
            if (isPlatformType(rawType) && !Types.isAllowedPlatformType(rawType)) {
                throw new IllegalArgumentException("Platform " + type + " annotated " + annotations + " requires explicit JsonAdapter to be registered");
            } else if (!annotations.isEmpty()) {
                return null;
            } else {
                if (rawType.getEnclosingClass() == null || Modifier.isStatic(rawType.getModifiers())) {
                    if (Modifier.isAbstract(rawType.getModifiers())) {
                        throw new IllegalArgumentException("Cannot serialize abstract class " + rawType.getName());
                    }
                    ClassFactory<Object> classFactory = ClassFactory.get(rawType);
                    Map<String, FieldBinding<?>> fields = new TreeMap<>();
                    for (Type t = type; t != Object.class; t = Types.getGenericSuperclass(t)) {
                        createFieldBindings(moshi, t, fields);
                    }
                    return new ClassJsonAdapter(classFactory, fields).nullSafe();
                } else if (rawType.getSimpleName().isEmpty()) {
                    throw new IllegalArgumentException("Cannot serialize anonymous class " + rawType.getName());
                } else {
                    throw new IllegalArgumentException("Cannot serialize non-static nested class " + rawType.getName());
                }
            }
        }

        private void createFieldBindings(Moshi moshi, Type type, Map<String, FieldBinding<?>> fieldBindings) {
            Class<?> rawType = Types.getRawType(type);
            boolean platformType = isPlatformType(rawType);
            Field[] declaredFields = rawType.getDeclaredFields();
            int length = declaredFields.length;
            for (int i = 0; i < length; i++) {
                Field field = declaredFields[i];
                if (includeField(platformType, field.getModifiers())) {
                    JsonAdapter<Object> adapter = moshi.adapter(Types.resolve(type, rawType, field.getGenericType()), Util.jsonAnnotations((AnnotatedElement) field));
                    field.setAccessible(true);
                    Json jsonAnnotation = (Json) field.getAnnotation(Json.class);
                    String name = jsonAnnotation != null ? jsonAnnotation.name() : field.getName();
                    FieldBinding<Object> fieldBinding = new FieldBinding<>(name, field, adapter);
                    FieldBinding<?> replaced = (FieldBinding) fieldBindings.put(name, fieldBinding);
                    if (replaced != null) {
                        throw new IllegalArgumentException("Conflicting fields:\n    " + replaced.field + "\n    " + fieldBinding.field);
                    }
                }
            }
        }

        private boolean isPlatformType(Class<?> rawType) {
            String name = rawType.getName();
            return name.startsWith(Icepick.ANDROID_PREFIX) || name.startsWith(Icepick.JAVA_PREFIX) || name.startsWith("javax.") || name.startsWith("kotlin.") || name.startsWith("scala.");
        }

        private boolean includeField(boolean platformType, int modifiers) {
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
                return false;
            }
            if (Modifier.isPublic(modifiers) || Modifier.isProtected(modifiers) || !platformType) {
                return true;
            }
            return false;
        }
    };
    private final ClassFactory<T> classFactory;
    private final FieldBinding<?>[] fieldsArray;
    private final Options options;

    static class FieldBinding<T> {
        final JsonAdapter<T> adapter;
        final Field field;
        final String name;

        public FieldBinding(String name2, Field field2, JsonAdapter<T> adapter2) {
            this.name = name2;
            this.field = field2;
            this.adapter = adapter2;
        }

        /* access modifiers changed from: 0000 */
        public void write(JsonWriter writer, Object value) throws IllegalAccessException, IOException {
            this.adapter.toJson(writer, this.field.get(value));
        }
    }

    ClassJsonAdapter(ClassFactory<T> classFactory2, Map<String, FieldBinding<?>> fieldsMap) {
        this.classFactory = classFactory2;
        this.fieldsArray = (FieldBinding[]) fieldsMap.values().toArray(new FieldBinding[fieldsMap.size()]);
        this.options = Options.m2491of((String[]) fieldsMap.keySet().toArray(new String[fieldsMap.size()]));
    }

    public void toJson(JsonWriter writer, T value) throws IOException {
        FieldBinding<?>[] fieldBindingArr;
        try {
            writer.beginObject();
            for (FieldBinding<?> fieldBinding : this.fieldsArray) {
                writer.name(fieldBinding.name);
                fieldBinding.write(writer, value);
            }
            writer.endObject();
        } catch (IllegalAccessException e) {
            throw new AssertionError();
        }
    }

    public String toString() {
        return "JsonAdapter(" + this.classFactory + ")";
    }
}
