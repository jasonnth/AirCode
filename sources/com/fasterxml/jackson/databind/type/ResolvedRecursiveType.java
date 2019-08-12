package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ResolvedRecursiveType extends TypeBase {
    protected JavaType _referencedType;

    public ResolvedRecursiveType(Class<?> erasedType, TypeBindings bindings) {
        super(erasedType, bindings, null, null, 0, null, null, false);
    }

    public void setReference(JavaType ref) {
        if (this._referencedType != null) {
            throw new IllegalStateException("Trying to re-set self reference; old value = " + this._referencedType + ", new = " + ref);
        }
        this._referencedType = ref;
    }

    public JavaType getSelfReferencedType() {
        return this._referencedType;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        return this._referencedType.getGenericSignature(sb);
    }

    public JavaType withContentType(JavaType contentType) {
        return this;
    }

    public JavaType withTypeHandler(Object h) {
        return this;
    }

    public JavaType withContentTypeHandler(Object h) {
        return this;
    }

    public JavaType withValueHandler(Object h) {
        return this;
    }

    public JavaType withContentValueHandler(Object h) {
        return this;
    }

    public JavaType withStaticTyping() {
        return this;
    }

    public JavaType refine(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return null;
    }

    public boolean isContainerType() {
        return false;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40).append("[recursive type; ");
        if (this._referencedType == null) {
            sb.append("UNRESOLVED");
        } else {
            sb.append(this._referencedType.getRawClass().getName());
        }
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (this._referencedType == null) {
            return false;
        }
        if (o.getClass() != getClass() || !this._referencedType.equals(((ResolvedRecursiveType) o).getSelfReferencedType())) {
            return false;
        }
        return true;
    }
}
