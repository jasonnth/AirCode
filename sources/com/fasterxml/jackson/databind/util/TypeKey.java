package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.databind.JavaType;

public class TypeKey {
    protected Class<?> _class;
    protected int _hashCode;
    protected boolean _isTyped;
    protected JavaType _type;

    public TypeKey() {
    }

    public TypeKey(Class<?> key, boolean typed) {
        this._class = key;
        this._type = null;
        this._isTyped = typed;
        this._hashCode = typed ? typedHash(key) : untypedHash(key);
    }

    public TypeKey(JavaType key, boolean typed) {
        this._type = key;
        this._class = null;
        this._isTyped = typed;
        this._hashCode = typed ? typedHash(key) : untypedHash(key);
    }

    public static final int untypedHash(Class<?> cls) {
        return cls.getName().hashCode();
    }

    public static final int typedHash(Class<?> cls) {
        return cls.getName().hashCode() + 1;
    }

    public static final int untypedHash(JavaType type) {
        return type.hashCode() - 1;
    }

    public static final int typedHash(JavaType type) {
        return type.hashCode() - 2;
    }

    public boolean isTyped() {
        return this._isTyped;
    }

    public Class<?> getRawType() {
        return this._class;
    }

    public JavaType getType() {
        return this._type;
    }

    public final int hashCode() {
        return this._hashCode;
    }

    public final String toString() {
        if (this._class != null) {
            return "{class: " + this._class.getName() + ", typed? " + this._isTyped + "}";
        }
        return "{type: " + this._type + ", typed? " + this._isTyped + "}";
    }

    public final boolean equals(Object o) {
        boolean z = true;
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }
        TypeKey other = (TypeKey) o;
        if (other._isTyped != this._isTyped) {
            return false;
        }
        if (this._class == null) {
            return this._type.equals(other._type);
        }
        if (other._class != this._class) {
            z = false;
        }
        return z;
    }
}
