package com.squareup.moshi;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

final class Util {
    public static final Set<Annotation> NO_ANNOTATIONS = Collections.emptySet();

    public static boolean typesMatch(Type pattern, Type candidate) {
        return pattern.equals(candidate);
    }

    public static Set<? extends Annotation> jsonAnnotations(AnnotatedElement annotatedElement) {
        return jsonAnnotations(annotatedElement.getAnnotations());
    }

    public static Set<? extends Annotation> jsonAnnotations(Annotation[] annotations) {
        Set<Annotation> result = null;
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().isAnnotationPresent(JsonQualifier.class)) {
                if (result == null) {
                    result = new LinkedHashSet<>();
                }
                result.add(annotation);
            }
        }
        return result != null ? Collections.unmodifiableSet(result) : NO_ANNOTATIONS;
    }
}
