package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.util.Annotations;
import java.lang.annotation.Annotation;
import java.util.HashMap;

public final class AnnotationMap implements Annotations {
    protected HashMap<Class<?>, Annotation> _annotations;

    public AnnotationMap() {
    }

    private AnnotationMap(HashMap<Class<?>, Annotation> a) {
        this._annotations = a;
    }

    public <A extends Annotation> A get(Class<A> cls) {
        if (this._annotations == null) {
            return null;
        }
        return (Annotation) this._annotations.get(cls);
    }

    public boolean has(Class<?> cls) {
        if (this._annotations == null) {
            return false;
        }
        return this._annotations.containsKey(cls);
    }

    public boolean hasOneOf(Class<? extends Annotation>[] annoClasses) {
        if (this._annotations != null) {
            for (Class<? extends Annotation> containsKey : annoClasses) {
                if (this._annotations.containsKey(containsKey)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static AnnotationMap merge(AnnotationMap primary, AnnotationMap secondary) {
        if (primary == null || primary._annotations == null || primary._annotations.isEmpty()) {
            return secondary;
        }
        if (secondary == null || secondary._annotations == null || secondary._annotations.isEmpty()) {
            return primary;
        }
        HashMap<Class<?>, Annotation> annotations = new HashMap<>();
        for (Annotation ann : secondary._annotations.values()) {
            annotations.put(ann.annotationType(), ann);
        }
        for (Annotation ann2 : primary._annotations.values()) {
            annotations.put(ann2.annotationType(), ann2);
        }
        return new AnnotationMap(annotations);
    }

    public int size() {
        if (this._annotations == null) {
            return 0;
        }
        return this._annotations.size();
    }

    public boolean addIfNotPresent(Annotation ann) {
        if (this._annotations != null && this._annotations.containsKey(ann.annotationType())) {
            return false;
        }
        _add(ann);
        return true;
    }

    public boolean add(Annotation ann) {
        return _add(ann);
    }

    public String toString() {
        if (this._annotations == null) {
            return "[null]";
        }
        return this._annotations.toString();
    }

    /* access modifiers changed from: protected */
    public final boolean _add(Annotation ann) {
        if (this._annotations == null) {
            this._annotations = new HashMap<>();
        }
        Annotation previous = (Annotation) this._annotations.put(ann.annotationType(), ann);
        return previous == null || !previous.equals(ann);
    }
}
