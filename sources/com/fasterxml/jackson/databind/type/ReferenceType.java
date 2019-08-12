package com.fasterxml.jackson.databind.type;

import com.fasterxml.jackson.databind.JavaType;

public class ReferenceType extends SimpleType {
    protected final JavaType _anchorType;
    protected final JavaType _referencedType;

    protected ReferenceType(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType refType, JavaType anchorType, Object valueHandler, Object typeHandler, boolean asStatic) {
        super(cls, bindings, superClass, superInts, refType.hashCode(), valueHandler, typeHandler, asStatic);
        this._referencedType = refType;
        if (anchorType == 0) {
            anchorType = this;
        }
        this._anchorType = anchorType;
    }

    public static ReferenceType construct(Class<?> cls, TypeBindings bindings, JavaType superClass, JavaType[] superInts, JavaType refType) {
        return new ReferenceType(cls, bindings, superClass, superInts, refType, null, null, null, false);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public JavaType withContentType(JavaType contentType) {
        if (this._referencedType == contentType) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, contentType, this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public ReferenceType withTypeHandler(Object h) {
        if (h == this._typeHandler) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._anchorType, this._valueHandler, h, this._asStatic);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public ReferenceType withContentTypeHandler(Object h) {
        return h == this._referencedType.getTypeHandler() ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withTypeHandler(h), this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public ReferenceType withValueHandler(Object h) {
        if (h == this._valueHandler) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType, this._anchorType, h, this._typeHandler, this._asStatic);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public ReferenceType withContentValueHandler(Object h) {
        if (h == this._referencedType.getValueHandler()) {
            return this;
        }
        return new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withValueHandler(h), this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* Debug info: failed to restart local var, previous not found, register: 10 */
    public ReferenceType withStaticTyping() {
        return this._asStatic ? this : new ReferenceType(this._class, this._bindings, this._superClass, this._superInterfaces, this._referencedType.withStaticTyping(), this._anchorType, this._valueHandler, this._typeHandler, true);
    }

    public JavaType refine(Class<?> rawType, TypeBindings bindings, JavaType superClass, JavaType[] superInterfaces) {
        return new ReferenceType(rawType, this._bindings, superClass, superInterfaces, this._referencedType, this._anchorType, this._valueHandler, this._typeHandler, this._asStatic);
    }

    /* access modifiers changed from: protected */
    public String buildCanonicalName() {
        StringBuilder sb = new StringBuilder();
        sb.append(this._class.getName());
        sb.append('<');
        sb.append(this._referencedType.toCanonical());
        return sb.toString();
    }

    public JavaType getContentType() {
        return this._referencedType;
    }

    public JavaType getReferencedType() {
        return this._referencedType;
    }

    public boolean hasContentType() {
        return true;
    }

    public boolean isReferenceType() {
        return true;
    }

    public StringBuilder getGenericSignature(StringBuilder sb) {
        _classSignature(this._class, sb, false);
        sb.append('<');
        StringBuilder sb2 = this._referencedType.getGenericSignature(sb);
        sb2.append(">;");
        return sb2;
    }

    public String toString() {
        return "[reference type, class " + buildCanonicalName() + '<' + this._referencedType + '>' + ']';
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != getClass()) {
            return false;
        }
        ReferenceType other = (ReferenceType) o;
        if (other._class == this._class) {
            return this._referencedType.equals(other._referencedType);
        }
        return false;
    }
}
