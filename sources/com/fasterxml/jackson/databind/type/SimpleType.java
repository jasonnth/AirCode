package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class SimpleType extends TypeBase {
    protected SimpleType(Class<?> cls) {
        this(cls, TypeBindings.emptyBindings(), null, null);
    }

    protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts) {
        this(cls, bindings, superClass, superInts, null, null, false);
    }

    protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(cls, bindings, superClass, superInts, 0, valueHandler, typeHandler, asStatic);
    }

    protected SimpleType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, int extraHash, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(cls, bindings, superClass, superInts, extraHash, valueHandler, typeHandler, asStatic);
    }

    public static SimpleType constructUnsafe(Class<?> raw) {
        return new SimpleType(raw, null, null, null, null, null, false);
    }

    public JavaType withContentType(JavaType contentType) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContentType()");
    }

    /* Debug info: failed to restart local var, previous not found, register: 8 */
    public SimpleType withTypeHandler(Object h) {
        if (this._typeHandler == h) {
            return this;
        }
        return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, h, this._asStatic);
    }

    public JavaType withContentTypeHandler(Object h) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenTypeHandler()");
    }

    /* Debug info: failed to restart local var, previous not found, register: 8 */
    public SimpleType withValueHandler(Object h) {
        if (h == this._valueHandler) {
            return this;
        }
        return new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, h, this._typeHandler, this._asStatic);
    }

    public SimpleType withContentValueHandler(Object h) {
        throw new IllegalArgumentException("Simple types have no content types; can not call withContenValueHandler()");
    }

    /* Debug info: failed to restart local var, previous not found, register: 8 */
    public SimpleType withStaticTyping() {
        return this._asStatic ? this : new SimpleType(this._class, this._bindings, this._superClass, this._superInterfaces, this._valueHandler, this._typeHandler, true);
    }

    public JavaType refine(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return null;
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        int count = this._bindings.size();
        if (count > 0) {
            sb.append('<');
            for (int i = 0; i < count; i++) {
                JavaType t = containedType(i);
                if (i > 0) {
                    sb.append(',');
                }
                sb.append(t.toCanonical());
            }
            sb.append('>');
        }
        return sb.toString();
    }

    public boolean isContainerType() {
        return false;
    }

    public boolean hasContentType() {
        return false;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        int count = this._bindings.size();
        if (count > 0) {
            sb.append('<');
            for (int i = 0; i < count; i++) {
                sb = containedType(i).getGenericSignature(sb);
            }
            sb.append('>');
        }
        sb.append(';');
        return sb;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(40);
        sb.append("[simple type, class ").append(buildCanonicalName()).append(']');
        return sb.toString();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        SimpleType other = (SimpleType) o;
        if (other._class == this._class) {
            return this._bindings.equals(other._bindings);
        }
        return false;
    }
}
