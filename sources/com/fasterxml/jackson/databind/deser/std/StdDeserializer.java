package com.fasterxml.jackson.databind.deser.std;

import com.facebook.appevents.AppEventsConstants;
import com.fasterxml.jackson.annotation.JsonFormat.Feature;
import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.p307io.NumberInput;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.Converter;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

public abstract class StdDeserializer<T> extends JsonDeserializer<T> implements Serializable {
    protected static final int F_MASK_INT_COERCIONS = (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.getMask() | DeserializationFeature.USE_LONG_FOR_INTS.getMask());
    private static final long serialVersionUID = 1;
    protected final Class<?> _valueClass;

    protected StdDeserializer(Class<?> vc) {
        this._valueClass = vc;
    }

    protected StdDeserializer(JavaType valueType) {
        this._valueClass = valueType == null ? null : valueType.getRawClass();
    }

    protected StdDeserializer(StdDeserializer<?> src) {
        this._valueClass = src._valueClass;
    }

    public Class<?> handledType() {
        return this._valueClass;
    }

    @Deprecated
    public final Class<?> getValueClass() {
        return this._valueClass;
    }

    public JavaType getValueType() {
        return null;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultDeserializer(JsonDeserializer<?> deserializer) {
        return ClassUtil.isJacksonStdImpl((Object) deserializer);
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultKeyDeserializer(KeyDeserializer keyDeser) {
        return ClassUtil.isJacksonStdImpl((Object) keyDeser);
    }

    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return typeDeserializer.deserializeTypedFromAny(p, ctxt);
    }

    /* access modifiers changed from: protected */
    public final boolean _parseBooleanPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_TRUE) {
            return true;
        }
        if (t == JsonToken.VALUE_FALSE || t == JsonToken.VALUE_NULL) {
            return false;
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return _parseBooleanFromInt(p, ctxt);
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if ("true".equals(text) || "True".equals(text)) {
                return true;
            }
            if (InternalLogger.EVENT_PARAM_EXTRAS_FALSE.equals(text) || "False".equals(text) || text.length() == 0 || _hasTextualNull(text)) {
                return false;
            }
            Boolean b = (Boolean) ctxt.handleWeirdStringValue(this._valueClass, text, "only \"true\" or \"false\" recognized", new Object[0]);
            if (b != null) {
                return b.booleanValue();
            }
            return false;
        } else if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            return ((Boolean) ctxt.handleUnexpectedToken(this._valueClass, p)).booleanValue();
        } else {
            p.nextToken();
            boolean parsed = _parseBooleanPrimitive(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final Boolean _parseBoolean(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_TRUE) {
            return Boolean.TRUE;
        }
        if (t == JsonToken.VALUE_FALSE) {
            return Boolean.FALSE;
        }
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return Boolean.valueOf(_parseBooleanFromInt(p, ctxt));
        }
        if (t == JsonToken.VALUE_NULL) {
            return (Boolean) getNullValue(ctxt);
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if ("true".equals(text) || "True".equals(text)) {
                return Boolean.TRUE;
            }
            if (InternalLogger.EVENT_PARAM_EXTRAS_FALSE.equals(text) || "False".equals(text)) {
                return Boolean.FALSE;
            }
            if (text.length() == 0) {
                return (Boolean) getEmptyValue(ctxt);
            }
            if (_hasTextualNull(text)) {
                return (Boolean) getNullValue(ctxt);
            }
            return (Boolean) ctxt.handleWeirdStringValue(this._valueClass, text, "only \"true\" or \"false\" recognized", new Object[0]);
        } else if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            return (Boolean) ctxt.handleUnexpectedToken(this._valueClass, p);
        } else {
            p.nextToken();
            Boolean parsed = _parseBoolean(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public boolean _parseBooleanFromInt(JsonParser p, DeserializationContext ctxt) throws IOException {
        return !AppEventsConstants.EVENT_PARAM_VALUE_NO.equals(p.getText());
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public boolean _parseBooleanFromOther(JsonParser p, DeserializationContext ctxt) throws IOException {
        return _parseBooleanFromInt(p, ctxt);
    }

    /* access modifiers changed from: protected */
    public Byte _parseByte(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return Byte.valueOf(p.getByteValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (_hasTextualNull(text)) {
                return (Byte) getNullValue(ctxt);
            }
            try {
                if (text.length() == 0) {
                    return (Byte) getEmptyValue(ctxt);
                }
                int value = NumberInput.parseInt(text);
                if (value < -128 || value > 255) {
                    return (Byte) ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value can not be represented as 8-bit value", new Object[0]);
                }
                return Byte.valueOf((byte) value);
            } catch (IllegalArgumentException e) {
                return (Byte) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Byte value", new Object[0]);
            }
        } else if (t == JsonToken.VALUE_NUMBER_FLOAT) {
            if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                _failDoubleToIntCoercion(p, ctxt, "Byte");
            }
            return Byte.valueOf(p.getByteValue());
        } else if (t == JsonToken.VALUE_NULL) {
            return (Byte) getNullValue(ctxt);
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return (Byte) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
            p.nextToken();
            Byte parsed = _parseByte(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public Short _parseShort(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return Short.valueOf(p.getShortValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            try {
                if (text.length() == 0) {
                    return (Short) getEmptyValue(ctxt);
                }
                if (_hasTextualNull(text)) {
                    return (Short) getNullValue(ctxt);
                }
                int value = NumberInput.parseInt(text);
                if (value < -32768 || value > 32767) {
                    return (Short) ctxt.handleWeirdStringValue(this._valueClass, text, "overflow, value can not be represented as 16-bit value", new Object[0]);
                }
                return Short.valueOf((short) value);
            } catch (IllegalArgumentException e) {
                return (Short) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Short value", new Object[0]);
            }
        } else if (t == JsonToken.VALUE_NUMBER_FLOAT) {
            if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                _failDoubleToIntCoercion(p, ctxt, "Short");
            }
            return Short.valueOf(p.getShortValue());
        } else if (t == JsonToken.VALUE_NULL) {
            return (Short) getNullValue(ctxt);
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return (Short) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
            p.nextToken();
            Short parsed = _parseShort(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final short _parseShortPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        int value = _parseIntPrimitive(p, ctxt);
        if (value >= -32768 && value <= 32767) {
            return (short) value;
        }
        Number v = (Number) ctxt.handleWeirdStringValue(this._valueClass, String.valueOf(value), "overflow, value can not be represented as 16-bit value", new Object[0]);
        if (v == null) {
            return 0;
        }
        return v.shortValue();
    }

    /* access modifiers changed from: protected */
    public final int _parseIntPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        if (p.hasToken(JsonToken.VALUE_NUMBER_INT)) {
            return p.getIntValue();
        }
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (_hasTextualNull(text)) {
                return 0;
            }
            try {
                int len = text.length();
                if (len > 9) {
                    long l = Long.parseLong(text);
                    if (l >= -2147483648L && l <= 2147483647L) {
                        return (int) l;
                    }
                    Number v = (Number) ctxt.handleWeirdStringValue(this._valueClass, text, "Overflow: numeric value (%s) out of range of int (%d -%d)", text, Integer.valueOf(Integer.MIN_VALUE), Integer.valueOf(Integer.MAX_VALUE));
                    if (v == null) {
                        return 0;
                    }
                    return v.intValue();
                } else if (len == 0) {
                    return 0;
                } else {
                    return NumberInput.parseInt(text);
                }
            } catch (IllegalArgumentException e) {
                Number v2 = (Number) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid int value", new Object[0]);
                if (v2 == null) {
                    return 0;
                }
                return v2.intValue();
            }
        } else if (t == JsonToken.VALUE_NUMBER_FLOAT) {
            if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                _failDoubleToIntCoercion(p, ctxt, "int");
            }
            return p.getValueAsInt();
        } else if (t == JsonToken.VALUE_NULL) {
            return 0;
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return ((Number) ctxt.handleUnexpectedToken(this._valueClass, p)).intValue();
            }
            p.nextToken();
            int parsed = _parseIntPrimitive(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final Integer _parseInteger(JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (p.getCurrentTokenId()) {
            case 3:
                if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    p.nextToken();
                    Integer parsed = _parseInteger(p, ctxt);
                    if (p.nextToken() != JsonToken.END_ARRAY) {
                        handleMissingEndArrayForSingle(p, ctxt);
                    }
                    return parsed;
                }
                break;
            case 6:
                String text = p.getText().trim();
                try {
                    int len = text.length();
                    if (_hasTextualNull(text)) {
                        return (Integer) getNullValue(ctxt);
                    }
                    if (len > 9) {
                        long l = Long.parseLong(text);
                        if (l < -2147483648L || l > 2147483647L) {
                            return (Integer) ctxt.handleWeirdStringValue(this._valueClass, text, "Overflow: numeric value (" + text + ") out of range of Integer (" + Integer.MIN_VALUE + " - " + Integer.MAX_VALUE + ")", new Object[0]);
                        }
                        return Integer.valueOf((int) l);
                    } else if (len == 0) {
                        return (Integer) getEmptyValue(ctxt);
                    } else {
                        return Integer.valueOf(NumberInput.parseInt(text));
                    }
                } catch (IllegalArgumentException e) {
                    return (Integer) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Integer value", new Object[0]);
                }
            case 7:
                return Integer.valueOf(p.getIntValue());
            case 8:
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    _failDoubleToIntCoercion(p, ctxt, "Integer");
                }
                return Integer.valueOf(p.getValueAsInt());
            case 11:
                return (Integer) getNullValue(ctxt);
        }
        return (Integer) ctxt.handleUnexpectedToken(this._valueClass, p);
    }

    /* access modifiers changed from: protected */
    public final Long _parseLong(JsonParser p, DeserializationContext ctxt) throws IOException {
        switch (p.getCurrentTokenId()) {
            case 3:
                if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    p.nextToken();
                    Long parsed = _parseLong(p, ctxt);
                    if (p.nextToken() != JsonToken.END_ARRAY) {
                        handleMissingEndArrayForSingle(p, ctxt);
                    }
                    return parsed;
                }
                break;
            case 6:
                String text = p.getText().trim();
                if (text.length() == 0) {
                    return (Long) getEmptyValue(ctxt);
                }
                if (_hasTextualNull(text)) {
                    return (Long) getNullValue(ctxt);
                }
                try {
                    return Long.valueOf(NumberInput.parseLong(text));
                } catch (IllegalArgumentException e) {
                    return (Long) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Long value", new Object[0]);
                }
            case 7:
                return Long.valueOf(p.getLongValue());
            case 8:
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    _failDoubleToIntCoercion(p, ctxt, "Long");
                }
                return Long.valueOf(p.getValueAsLong());
            case 11:
                return (Long) getNullValue(ctxt);
        }
        return (Long) ctxt.handleUnexpectedToken(this._valueClass, p);
    }

    /* access modifiers changed from: protected */
    public final long _parseLongPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        long j = 0;
        switch (p.getCurrentTokenId()) {
            case 3:
                if (ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                    p.nextToken();
                    long parsed = _parseLongPrimitive(p, ctxt);
                    if (p.nextToken() != JsonToken.END_ARRAY) {
                        handleMissingEndArrayForSingle(p, ctxt);
                    }
                    return parsed;
                }
                break;
            case 6:
                String text = p.getText().trim();
                if (text.length() == 0 || _hasTextualNull(text)) {
                    return j;
                }
                try {
                    return NumberInput.parseLong(text);
                } catch (IllegalArgumentException e) {
                    Number v = (Number) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid long value", new Object[0]);
                    if (v != null) {
                        return v.longValue();
                    }
                    return j;
                }
            case 7:
                return p.getLongValue();
            case 8:
                if (!ctxt.isEnabled(DeserializationFeature.ACCEPT_FLOAT_AS_INT)) {
                    _failDoubleToIntCoercion(p, ctxt, "long");
                }
                return p.getValueAsLong();
            case 11:
                return j;
        }
        return ((Number) ctxt.handleUnexpectedToken(this._valueClass, p)).longValue();
    }

    /* access modifiers changed from: protected */
    public final Float _parseFloat(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
            return Float.valueOf(p.getFloatValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (text.length() == 0) {
                return (Float) getEmptyValue(ctxt);
            }
            if (_hasTextualNull(text)) {
                return (Float) getNullValue(ctxt);
            }
            switch (text.charAt(0)) {
                case '-':
                    if (_isNegInf(text)) {
                        return Float.valueOf(Float.NEGATIVE_INFINITY);
                    }
                    break;
                case 'I':
                    if (_isPosInf(text)) {
                        return Float.valueOf(Float.POSITIVE_INFINITY);
                    }
                    break;
                case 'N':
                    if (_isNaN(text)) {
                        return Float.valueOf(Float.NaN);
                    }
                    break;
            }
            try {
                return Float.valueOf(Float.parseFloat(text));
            } catch (IllegalArgumentException e) {
                return (Float) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Float value", new Object[0]);
            }
        } else if (t == JsonToken.VALUE_NULL) {
            return (Float) getNullValue(ctxt);
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return (Float) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
            p.nextToken();
            Float parsed = _parseFloat(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final float _parseFloatPrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        float f = 0.0f;
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
            return p.getFloatValue();
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (text.length() == 0 || _hasTextualNull(text)) {
                return f;
            }
            switch (text.charAt(0)) {
                case '-':
                    if (_isNegInf(text)) {
                        return Float.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if (_isPosInf(text)) {
                        return Float.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if (_isNaN(text)) {
                        return Float.NaN;
                    }
                    break;
            }
            try {
                return Float.parseFloat(text);
            } catch (IllegalArgumentException e) {
                Number v = (Number) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid float value", new Object[0]);
                if (v != null) {
                    return v.floatValue();
                }
                return f;
            }
        } else if (t == JsonToken.VALUE_NULL) {
            return f;
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return ((Number) ctxt.handleUnexpectedToken(this._valueClass, p)).floatValue();
            }
            p.nextToken();
            float parsed = _parseFloatPrimitive(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final Double _parseDouble(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
            return Double.valueOf(p.getDoubleValue());
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (text.length() == 0) {
                return (Double) getEmptyValue(ctxt);
            }
            if (_hasTextualNull(text)) {
                return (Double) getNullValue(ctxt);
            }
            switch (text.charAt(0)) {
                case '-':
                    if (_isNegInf(text)) {
                        return Double.valueOf(Double.NEGATIVE_INFINITY);
                    }
                    break;
                case 'I':
                    if (_isPosInf(text)) {
                        return Double.valueOf(Double.POSITIVE_INFINITY);
                    }
                    break;
                case 'N':
                    if (_isNaN(text)) {
                        return Double.valueOf(Double.NaN);
                    }
                    break;
            }
            try {
                return Double.valueOf(parseDouble(text));
            } catch (IllegalArgumentException e) {
                return (Double) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid Double value", new Object[0]);
            }
        } else if (t == JsonToken.VALUE_NULL) {
            return (Double) getNullValue(ctxt);
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return (Double) ctxt.handleUnexpectedToken(this._valueClass, p);
            }
            p.nextToken();
            Double parsed = _parseDouble(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public final double _parseDoublePrimitive(JsonParser p, DeserializationContext ctxt) throws IOException {
        double d = 0.0d;
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT || t == JsonToken.VALUE_NUMBER_FLOAT) {
            return p.getDoubleValue();
        }
        if (t == JsonToken.VALUE_STRING) {
            String text = p.getText().trim();
            if (text.length() == 0 || _hasTextualNull(text)) {
                return d;
            }
            switch (text.charAt(0)) {
                case '-':
                    if (_isNegInf(text)) {
                        return Double.NEGATIVE_INFINITY;
                    }
                    break;
                case 'I':
                    if (_isPosInf(text)) {
                        return Double.POSITIVE_INFINITY;
                    }
                    break;
                case 'N':
                    if (_isNaN(text)) {
                        return Double.NaN;
                    }
                    break;
            }
            try {
                return parseDouble(text);
            } catch (IllegalArgumentException e) {
                Number v = (Number) ctxt.handleWeirdStringValue(this._valueClass, text, "not a valid double value", new Object[0]);
                if (v != null) {
                    return v.doubleValue();
                }
                return d;
            }
        } else if (t == JsonToken.VALUE_NULL) {
            return d;
        } else {
            if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
                return ((Number) ctxt.handleUnexpectedToken(this._valueClass, p)).doubleValue();
            }
            p.nextToken();
            double parsed = _parseDoublePrimitive(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return parsed;
        }
    }

    /* access modifiers changed from: protected */
    public Date _parseDate(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_NUMBER_INT) {
            return new Date(p.getLongValue());
        }
        if (t == JsonToken.VALUE_NULL) {
            return (Date) getNullValue(ctxt);
        }
        if (t == JsonToken.VALUE_STRING) {
            return _parseDate(p.getText().trim(), ctxt);
        }
        if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            return (Date) ctxt.handleUnexpectedToken(this._valueClass, p);
        }
        p.nextToken();
        Date parsed = _parseDate(p, ctxt);
        if (p.nextToken() != JsonToken.END_ARRAY) {
            handleMissingEndArrayForSingle(p, ctxt);
        }
        return parsed;
    }

    /* access modifiers changed from: protected */
    public Date _parseDate(String value, DeserializationContext ctxt) throws IOException {
        try {
            if (value.length() == 0) {
                return (Date) getEmptyValue(ctxt);
            }
            if (_hasTextualNull(value)) {
                return (Date) getNullValue(ctxt);
            }
            return ctxt.parseDate(value);
        } catch (IllegalArgumentException iae) {
            return (Date) ctxt.handleWeirdStringValue(this._valueClass, value, "not a valid representation (error: %s)", iae.getMessage());
        }
    }

    protected static final double parseDouble(String numStr) throws NumberFormatException {
        if ("2.2250738585072012e-308".equals(numStr)) {
            return Double.MIN_NORMAL;
        }
        return Double.parseDouble(numStr);
    }

    /* access modifiers changed from: protected */
    public final String _parseString(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            return p.getText();
        }
        if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            String value = p.getValueAsString();
            if (value != null) {
                return value;
            }
            return (String) ctxt.handleUnexpectedToken(String.class, p);
        }
        p.nextToken();
        String _parseString = _parseString(p, ctxt);
        if (p.nextToken() == JsonToken.END_ARRAY) {
            return _parseString;
        }
        handleMissingEndArrayForSingle(p, ctxt);
        return _parseString;
    }

    /* access modifiers changed from: protected */
    public T _deserializeFromEmpty(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.START_ARRAY) {
            if (ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT)) {
                if (p.nextToken() == JsonToken.END_ARRAY) {
                    return null;
                }
                return ctxt.handleUnexpectedToken(handledType(), p);
            }
        } else if (t == JsonToken.VALUE_STRING && ctxt.isEnabled(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT) && p.getText().trim().isEmpty()) {
            return null;
        }
        return ctxt.handleUnexpectedToken(handledType(), p);
    }

    /* access modifiers changed from: protected */
    public boolean _hasTextualNull(String value) {
        return "null".equals(value);
    }

    /* access modifiers changed from: protected */
    public final boolean _isNegInf(String text) {
        return "-Infinity".equals(text) || "-INF".equals(text);
    }

    /* access modifiers changed from: protected */
    public final boolean _isPosInf(String text) {
        return "Infinity".equals(text) || "INF".equals(text);
    }

    /* access modifiers changed from: protected */
    public final boolean _isNaN(String text) {
        return "NaN".equals(text);
    }

    /* access modifiers changed from: protected */
    public Object _coerceIntegral(JsonParser p, DeserializationContext ctxt) throws IOException {
        int feats = ctxt.getDeserializationFeatures();
        if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(feats)) {
            return p.getBigIntegerValue();
        }
        if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(feats)) {
            return Long.valueOf(p.getLongValue());
        }
        return p.getBigIntegerValue();
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<Object> findDeserializer(DeserializationContext ctxt, JavaType type, BeanProperty property) throws JsonMappingException {
        return ctxt.findContextualValueDeserializer(type, property);
    }

    /* access modifiers changed from: protected */
    public final boolean _isIntNumber(String text) {
        int i;
        int len = text.length();
        if (len <= 0) {
            return false;
        }
        char c = text.charAt(0);
        if (c == '-' || c == '+') {
            i = 1;
        } else {
            i = 0;
        }
        while (i < len) {
            int ch = text.charAt(i);
            if (ch > 57 || ch < 48) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public JsonDeserializer<?> findConvertingContentDeserializer(DeserializationContext ctxt, BeanProperty prop, JsonDeserializer<?> existingDeserializer) throws JsonMappingException {
        AnnotationIntrospector intr = ctxt.getAnnotationIntrospector();
        if (!(intr == null || prop == null)) {
            AnnotatedMember member = prop.getMember();
            if (member != null) {
                Object convDef = intr.findDeserializationContentConverter(member);
                if (convDef != null) {
                    Converter<Object, Object> conv = ctxt.converterInstance(prop.getMember(), convDef);
                    JavaType delegateType = conv.getInputType(ctxt.getTypeFactory());
                    if (existingDeserializer == null) {
                        existingDeserializer = ctxt.findContextualValueDeserializer(delegateType, prop);
                    }
                    return new StdDelegatingDeserializer(conv, delegateType, existingDeserializer);
                }
            }
        }
        return existingDeserializer;
    }

    /* access modifiers changed from: protected */
    public Value findFormatOverrides(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults) {
        if (prop != null) {
            return prop.findPropertyFormat(ctxt.getConfig(), typeForDefaults);
        }
        return ctxt.getDefaultPropertyFormat(typeForDefaults);
    }

    /* access modifiers changed from: protected */
    public Boolean findFormatFeature(DeserializationContext ctxt, BeanProperty prop, Class<?> typeForDefaults, Feature feat) {
        Value format = findFormatOverrides(ctxt, prop, typeForDefaults);
        if (format != null) {
            return format.getFeature(feat);
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void handleUnknownProperty(JsonParser p, DeserializationContext ctxt, Object instanceOrClass, String propName) throws IOException {
        if (instanceOrClass == null) {
            instanceOrClass = handledType();
        }
        if (!ctxt.handleUnknownProperty(p, this, instanceOrClass, propName)) {
            p.skipChildren();
        }
    }

    /* access modifiers changed from: protected */
    public void handleMissingEndArrayForSingle(JsonParser p, DeserializationContext ctxt) throws IOException {
        ctxt.reportWrongTokenException(p, JsonToken.END_ARRAY, "Attempted to unwrap single value array for single '%s' value but there was more than a single value in the array", handledType().getName());
    }

    /* access modifiers changed from: protected */
    public void _failDoubleToIntCoercion(JsonParser p, DeserializationContext ctxt, String type) throws IOException {
        ctxt.reportMappingException("Can not coerce a floating-point value ('%s') into %s; enable `DeserializationFeature.ACCEPT_FLOAT_AS_INT` to allow", p.getValueAsString(), type);
    }
}
