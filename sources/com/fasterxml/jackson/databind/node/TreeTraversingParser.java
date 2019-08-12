package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserMinimalBase;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class TreeTraversingParser extends ParserMinimalBase {
    protected boolean _closed;
    protected JsonToken _nextToken;
    protected NodeCursor _nodeCursor;
    protected ObjectCodec _objectCodec;
    protected boolean _startContainer;

    public TreeTraversingParser(JsonNode n, ObjectCodec codec) {
        super(0);
        this._objectCodec = codec;
        if (n.isArray()) {
            this._nextToken = JsonToken.START_ARRAY;
            this._nodeCursor = new ArrayCursor(n, null);
        } else if (n.isObject()) {
            this._nextToken = JsonToken.START_OBJECT;
            this._nodeCursor = new ObjectCursor(n, null);
        } else {
            this._nodeCursor = new RootCursor(n, null);
        }
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    public void close() throws IOException {
        if (!this._closed) {
            this._closed = true;
            this._nodeCursor = null;
            this._currToken = null;
        }
    }

    public JsonToken nextToken() throws IOException, JsonParseException {
        if (this._nextToken != null) {
            this._currToken = this._nextToken;
            this._nextToken = null;
            return this._currToken;
        } else if (this._startContainer) {
            this._startContainer = false;
            if (!this._nodeCursor.currentHasChildren()) {
                this._currToken = this._currToken == JsonToken.START_OBJECT ? JsonToken.END_OBJECT : JsonToken.END_ARRAY;
                return this._currToken;
            }
            this._nodeCursor = this._nodeCursor.iterateChildren();
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                this._startContainer = true;
            }
            return this._currToken;
        } else if (this._nodeCursor == null) {
            this._closed = true;
            return null;
        } else {
            this._currToken = this._nodeCursor.nextToken();
            if (this._currToken != null) {
                if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
                    this._startContainer = true;
                }
                return this._currToken;
            }
            this._currToken = this._nodeCursor.endToken();
            this._nodeCursor = this._nodeCursor.getParent();
            return this._currToken;
        }
    }

    public JsonParser skipChildren() throws IOException, JsonParseException {
        if (this._currToken == JsonToken.START_OBJECT) {
            this._startContainer = false;
            this._currToken = JsonToken.END_OBJECT;
        } else if (this._currToken == JsonToken.START_ARRAY) {
            this._startContainer = false;
            this._currToken = JsonToken.END_ARRAY;
        }
        return this;
    }

    public String getCurrentName() {
        if (this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.getCurrentName();
    }

    public JsonStreamContext getParsingContext() {
        return this._nodeCursor;
    }

    public JsonLocation getTokenLocation() {
        return JsonLocation.f3137NA;
    }

    public JsonLocation getCurrentLocation() {
        return JsonLocation.f3137NA;
    }

    public String getText() {
        if (this._closed) {
            return null;
        }
        switch (this._currToken) {
            case FIELD_NAME:
                return this._nodeCursor.getCurrentName();
            case VALUE_STRING:
                return currentNode().textValue();
            case VALUE_NUMBER_INT:
            case VALUE_NUMBER_FLOAT:
                return String.valueOf(currentNode().numberValue());
            case VALUE_EMBEDDED_OBJECT:
                JsonNode n = currentNode();
                if (n != null && n.isBinary()) {
                    return n.asText();
                }
        }
        if (this._currToken != null) {
            return this._currToken.asString();
        }
        return null;
    }

    public char[] getTextCharacters() throws IOException, JsonParseException {
        return getText().toCharArray();
    }

    public int getTextLength() throws IOException, JsonParseException {
        return getText().length();
    }

    public int getTextOffset() throws IOException, JsonParseException {
        return 0;
    }

    public boolean hasTextCharacters() {
        return false;
    }

    public NumberType getNumberType() throws IOException, JsonParseException {
        JsonNode n = currentNumericNode();
        if (n == null) {
            return null;
        }
        return n.numberType();
    }

    public BigInteger getBigIntegerValue() throws IOException, JsonParseException {
        return currentNumericNode().bigIntegerValue();
    }

    public BigDecimal getDecimalValue() throws IOException, JsonParseException {
        return currentNumericNode().decimalValue();
    }

    public double getDoubleValue() throws IOException, JsonParseException {
        return currentNumericNode().doubleValue();
    }

    public float getFloatValue() throws IOException, JsonParseException {
        return (float) currentNumericNode().doubleValue();
    }

    public long getLongValue() throws IOException, JsonParseException {
        return currentNumericNode().longValue();
    }

    public int getIntValue() throws IOException, JsonParseException {
        return currentNumericNode().intValue();
    }

    public Number getNumberValue() throws IOException, JsonParseException {
        return currentNumericNode().numberValue();
    }

    public Object getEmbeddedObject() {
        if (!this._closed) {
            JsonNode n = currentNode();
            if (n != null) {
                if (n.isPojo()) {
                    return ((POJONode) n).getPojo();
                }
                if (n.isBinary()) {
                    return ((BinaryNode) n).binaryValue();
                }
            }
        }
        return null;
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException, JsonParseException {
        JsonNode n = currentNode();
        if (n != null) {
            byte[] data = n.binaryValue();
            if (data != null) {
                return data;
            }
            if (n.isPojo()) {
                Object ob = ((POJONode) n).getPojo();
                if (ob instanceof byte[]) {
                    return (byte[]) ob;
                }
            }
        }
        return null;
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException, JsonParseException {
        byte[] data = getBinaryValue(b64variant);
        if (data == null) {
            return 0;
        }
        out.write(data, 0, data.length);
        return data.length;
    }

    /* access modifiers changed from: protected */
    public JsonNode currentNode() {
        if (this._closed || this._nodeCursor == null) {
            return null;
        }
        return this._nodeCursor.currentNode();
    }

    /* access modifiers changed from: protected */
    public JsonNode currentNumericNode() throws JsonParseException {
        JsonNode n = currentNode();
        if (n != null && n.isNumber()) {
            return n;
        }
        throw _constructError("Current token (" + (n == null ? null : n.asToken()) + ") not numeric, can not use numeric value accessors");
    }

    /* access modifiers changed from: protected */
    public void _handleEOF() throws JsonParseException {
        _throwInternal();
    }
}
