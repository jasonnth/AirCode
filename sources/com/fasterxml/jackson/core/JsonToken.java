package com.fasterxml.jackson.core;

public enum JsonToken {
    NOT_AVAILABLE(null, -1),
    START_OBJECT("{", 1),
    END_OBJECT("}", 2),
    START_ARRAY("[", 3),
    END_ARRAY("]", 4),
    FIELD_NAME(null, 5),
    VALUE_EMBEDDED_OBJECT(null, 12),
    VALUE_STRING(null, 6),
    VALUE_NUMBER_INT(null, 7),
    VALUE_NUMBER_FLOAT(null, 8),
    VALUE_TRUE("true", 9),
    VALUE_FALSE(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 10),
    VALUE_NULL("null", 11);
    
    final int _id;
    final boolean _isBoolean;
    final boolean _isNumber;
    final boolean _isScalar;
    final boolean _isStructEnd;
    final boolean _isStructStart;
    final String _serialized;
    final byte[] _serializedBytes;
    final char[] _serializedChars;

    private JsonToken(String token, int id) {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5 = true;
        if (token == null) {
            this._serialized = null;
            this._serializedChars = null;
            this._serializedBytes = null;
        } else {
            this._serialized = token;
            this._serializedChars = token.toCharArray();
            int len = this._serializedChars.length;
            this._serializedBytes = new byte[len];
            for (int i = 0; i < len; i++) {
                this._serializedBytes[i] = (byte) this._serializedChars[i];
            }
        }
        this._id = id;
        if (id == 10 || id == 9) {
            z = true;
        } else {
            z = false;
        }
        this._isBoolean = z;
        if (id == 7 || id == 8) {
            z2 = true;
        } else {
            z2 = false;
        }
        this._isNumber = z2;
        if (id == 1 || id == 3) {
            z3 = true;
        } else {
            z3 = false;
        }
        this._isStructStart = z3;
        if (id == 2 || id == 4) {
            z4 = true;
        } else {
            z4 = false;
        }
        this._isStructEnd = z4;
        if (this._isStructStart || this._isStructEnd || id == 5 || id == -1) {
            z5 = false;
        }
        this._isScalar = z5;
    }

    /* renamed from: id */
    public final int mo34327id() {
        return this._id;
    }

    public final String asString() {
        return this._serialized;
    }

    public final char[] asCharArray() {
        return this._serializedChars;
    }

    public final boolean isNumeric() {
        return this._isNumber;
    }

    public final boolean isStructStart() {
        return this._isStructStart;
    }

    public final boolean isStructEnd() {
        return this._isStructEnd;
    }

    public final boolean isScalarValue() {
        return this._isScalar;
    }
}
