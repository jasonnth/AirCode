package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.base.ParserBase;
import com.fasterxml.jackson.core.p307io.CharTypes;
import com.fasterxml.jackson.core.p307io.IOContext;
import com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import net.p318sf.scuba.smartcards.ISO7816;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.asn1.eac.EACTags;

public class UTF8StreamJsonParser extends ParserBase {
    protected static final int[] _icLatin1 = CharTypes.getInputCodeLatin1();
    private static final int[] _icUTF8 = CharTypes.getInputCodeUtf8();
    protected boolean _bufferRecyclable;
    protected byte[] _inputBuffer;
    protected InputStream _inputStream;
    protected int _nameStartCol;
    protected int _nameStartOffset;
    protected int _nameStartRow;
    protected ObjectCodec _objectCodec;
    private int _quad1;
    protected int[] _quadBuffer = new int[16];
    protected final ByteQuadsCanonicalizer _symbols;
    protected boolean _tokenIncomplete;

    public UTF8StreamJsonParser(IOContext ctxt, int features, InputStream in, ObjectCodec codec, ByteQuadsCanonicalizer sym, byte[] inputBuffer, int start, int end, boolean bufferRecyclable) {
        super(ctxt, features);
        this._inputStream = in;
        this._objectCodec = codec;
        this._symbols = sym;
        this._inputBuffer = inputBuffer;
        this._inputPtr = start;
        this._inputEnd = end;
        this._currInputRowStart = start;
        this._currInputProcessed = (long) (-start);
        this._bufferRecyclable = bufferRecyclable;
    }

    public ObjectCodec getCodec() {
        return this._objectCodec;
    }

    /* access modifiers changed from: protected */
    public final boolean _loadMore() throws IOException {
        int bufSize = this._inputEnd;
        this._currInputProcessed += (long) this._inputEnd;
        this._currInputRowStart -= this._inputEnd;
        this._nameStartOffset -= bufSize;
        if (this._inputStream == null) {
            return false;
        }
        int space = this._inputBuffer.length;
        if (space == 0) {
            return false;
        }
        int count = this._inputStream.read(this._inputBuffer, 0, space);
        if (count > 0) {
            this._inputPtr = 0;
            this._inputEnd = count;
            return true;
        }
        _closeInput();
        if (count != 0) {
            return false;
        }
        throw new IOException("InputStream.read() returned 0 characters when trying to read " + this._inputBuffer.length + " bytes");
    }

    /* access modifiers changed from: protected */
    public void _closeInput() throws IOException {
        if (this._inputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_SOURCE)) {
                this._inputStream.close();
            }
            this._inputStream = null;
        }
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() throws IOException {
        super._releaseBuffers();
        this._symbols.release();
        if (this._bufferRecyclable) {
            byte[] buf = this._inputBuffer;
            if (buf != null) {
                this._inputBuffer = ByteArrayBuilder.NO_BYTES;
                this._ioContext.releaseReadIOBuffer(buf);
            }
        }
    }

    public String getText() throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING) {
            return _getText2(this._currToken);
        }
        if (!this._tokenIncomplete) {
            return this._textBuffer.contentsAsString();
        }
        this._tokenIncomplete = false;
        return _finishAndReturnString();
    }

    public String getValueAsString() throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (!this._tokenIncomplete) {
                return this._textBuffer.contentsAsString();
            }
            this._tokenIncomplete = false;
            return _finishAndReturnString();
        } else if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(null);
        }
    }

    public String getValueAsString(String defValue) throws IOException {
        if (this._currToken == JsonToken.VALUE_STRING) {
            if (!this._tokenIncomplete) {
                return this._textBuffer.contentsAsString();
            }
            this._tokenIncomplete = false;
            return _finishAndReturnString();
        } else if (this._currToken == JsonToken.FIELD_NAME) {
            return getCurrentName();
        } else {
            return super.getValueAsString(defValue);
        }
    }

    public int getValueAsInt() throws IOException {
        JsonToken t = this._currToken;
        if (t != JsonToken.VALUE_NUMBER_INT && t != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(0);
        }
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                return _parseIntValue();
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    public int getValueAsInt(int defValue) throws IOException {
        JsonToken t = this._currToken;
        if (t != JsonToken.VALUE_NUMBER_INT && t != JsonToken.VALUE_NUMBER_FLOAT) {
            return super.getValueAsInt(defValue);
        }
        if ((this._numTypesValid & 1) == 0) {
            if (this._numTypesValid == 0) {
                return _parseIntValue();
            }
            if ((this._numTypesValid & 1) == 0) {
                convertNumberToInt();
            }
        }
        return this._numberInt;
    }

    /* access modifiers changed from: protected */
    public final String _getText2(JsonToken t) {
        if (t == null) {
            return null;
        }
        switch (t.mo34327id()) {
            case 5:
                return this._parsingContext.getCurrentName();
            case 6:
            case 7:
            case 8:
                return this._textBuffer.contentsAsString();
            default:
                return t.asString();
        }
    }

    public char[] getTextCharacters() throws IOException {
        if (this._currToken == null) {
            return null;
        }
        switch (this._currToken.mo34327id()) {
            case 5:
                if (!this._nameCopied) {
                    String name = this._parsingContext.getCurrentName();
                    int nameLen = name.length();
                    if (this._nameCopyBuffer == null) {
                        this._nameCopyBuffer = this._ioContext.allocNameCopyBuffer(nameLen);
                    } else if (this._nameCopyBuffer.length < nameLen) {
                        this._nameCopyBuffer = new char[nameLen];
                    }
                    name.getChars(0, nameLen, this._nameCopyBuffer, 0);
                    this._nameCopied = true;
                }
                return this._nameCopyBuffer;
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return this._currToken.asCharArray();
        }
        return this._textBuffer.getTextBuffer();
    }

    public int getTextLength() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.mo34327id()) {
            case 5:
                return this._parsingContext.getCurrentName().length();
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return this._currToken.asCharArray().length;
        }
        return this._textBuffer.size();
    }

    public int getTextOffset() throws IOException {
        if (this._currToken == null) {
            return 0;
        }
        switch (this._currToken.mo34327id()) {
            case 6:
                if (this._tokenIncomplete) {
                    this._tokenIncomplete = false;
                    _finishString();
                    break;
                }
                break;
            case 7:
            case 8:
                break;
            default:
                return 0;
        }
        return this._textBuffer.getTextOffset();
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
        if (this._currToken != JsonToken.VALUE_STRING && (this._currToken != JsonToken.VALUE_EMBEDDED_OBJECT || this._binaryValue == null)) {
            _reportError("Current token (" + this._currToken + ") not VALUE_STRING or VALUE_EMBEDDED_OBJECT, can not access as binary");
        }
        if (this._tokenIncomplete) {
            try {
                this._binaryValue = _decodeBase64(b64variant);
                this._tokenIncomplete = false;
            } catch (IllegalArgumentException iae) {
                throw _constructError("Failed to decode VALUE_STRING as base64 (" + b64variant + "): " + iae.getMessage());
            }
        } else if (this._binaryValue == null) {
            ByteArrayBuilder builder = _getByteArrayBuilder();
            _decodeBase64(getText(), builder, b64variant);
            this._binaryValue = builder.toByteArray();
        }
        return this._binaryValue;
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
        if (!this._tokenIncomplete || this._currToken != JsonToken.VALUE_STRING) {
            byte[] b = getBinaryValue(b64variant);
            out.write(b);
            return b.length;
        }
        byte[] buf = this._ioContext.allocBase64Buffer();
        try {
            return _readBinary(b64variant, out, buf);
        } finally {
            this._ioContext.releaseBase64Buffer(buf);
        }
    }

    /* access modifiers changed from: protected */
    public int _readBinary(Base64Variant b64variant, OutputStream out, byte[] buffer) throws IOException {
        int outputPtr = 0;
        int outputEnd = buffer.length - 3;
        int outputCount = 0;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int ch = bArr[i] & 255;
            if (ch > 32) {
                int bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (ch == 34) {
                        break;
                    }
                    bits = _decodeBase64Escape(b64variant, ch, 0);
                    if (bits < 0) {
                        continue;
                    }
                }
                if (outputPtr > outputEnd) {
                    outputCount += outputPtr;
                    out.write(buffer, 0, outputPtr);
                    outputPtr = 0;
                }
                int decodedData = bits;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                int ch2 = bArr2[i2] & 255;
                int bits2 = b64variant.decodeBase64Char(ch2);
                if (bits2 < 0) {
                    bits2 = _decodeBase64Escape(b64variant, ch2, 1);
                }
                int decodedData2 = (decodedData << 6) | bits2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int ch3 = bArr3[i3] & 255;
                int bits3 = b64variant.decodeBase64Char(ch3);
                if (bits3 < 0) {
                    if (bits3 != -2) {
                        if (ch3 == 34 && !b64variant.usesPadding()) {
                            int outputPtr2 = outputPtr + 1;
                            buffer[outputPtr] = (byte) (decodedData2 >> 4);
                            outputPtr = outputPtr2;
                            break;
                        }
                        bits3 = _decodeBase64Escape(b64variant, ch3, 2);
                    }
                    if (bits3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i4 = this._inputPtr;
                        this._inputPtr = i4 + 1;
                        int ch4 = bArr4[i4] & 255;
                        if (!b64variant.usesPaddingChar(ch4)) {
                            throw reportInvalidBase64Char(b64variant, ch4, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                        }
                        int outputPtr3 = outputPtr + 1;
                        buffer[outputPtr] = (byte) (decodedData2 >> 4);
                        outputPtr = outputPtr3;
                    }
                }
                int decodedData3 = (decodedData2 << 6) | bits3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int ch5 = bArr5[i5] & 255;
                int bits4 = b64variant.decodeBase64Char(ch5);
                if (bits4 < 0) {
                    if (bits4 != -2) {
                        if (ch5 == 34 && !b64variant.usesPadding()) {
                            int decodedData4 = decodedData3 >> 2;
                            int outputPtr4 = outputPtr + 1;
                            buffer[outputPtr] = (byte) (decodedData4 >> 8);
                            outputPtr = outputPtr4 + 1;
                            buffer[outputPtr4] = (byte) decodedData4;
                            break;
                        }
                        bits4 = _decodeBase64Escape(b64variant, ch5, 3);
                    }
                    if (bits4 == -2) {
                        int decodedData5 = decodedData3 >> 2;
                        int outputPtr5 = outputPtr + 1;
                        buffer[outputPtr] = (byte) (decodedData5 >> 8);
                        outputPtr = outputPtr5 + 1;
                        buffer[outputPtr5] = (byte) decodedData5;
                    }
                }
                int decodedData6 = (decodedData3 << 6) | bits4;
                int outputPtr6 = outputPtr + 1;
                buffer[outputPtr] = (byte) (decodedData6 >> 16);
                int outputPtr7 = outputPtr6 + 1;
                buffer[outputPtr6] = (byte) (decodedData6 >> 8);
                int outputPtr8 = outputPtr7 + 1;
                buffer[outputPtr7] = (byte) decodedData6;
                outputPtr = outputPtr8;
            }
        }
        this._tokenIncomplete = false;
        if (outputPtr <= 0) {
            return outputCount;
        }
        int outputCount2 = outputCount + outputPtr;
        out.write(buffer, 0, outputPtr);
        return outputCount2;
    }

    public JsonToken nextToken() throws IOException {
        JsonToken t;
        if (this._currToken == JsonToken.FIELD_NAME) {
            return _nextAfterName();
        }
        this._numTypesValid = 0;
        if (this._tokenIncomplete) {
            _skipString();
        }
        int i = _skipWSOrEnd();
        if (i < 0) {
            close();
            this._currToken = null;
            return null;
        }
        this._binaryValue = null;
        if (i == 93) {
            _updateLocation();
            if (!this._parsingContext.inArray()) {
                _reportMismatchedEndMarker(i, '}');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken = JsonToken.END_ARRAY;
            this._currToken = jsonToken;
            return jsonToken;
        } else if (i == 125) {
            _updateLocation();
            if (!this._parsingContext.inObject()) {
                _reportMismatchedEndMarker(i, ']');
            }
            this._parsingContext = this._parsingContext.clearAndGetParent();
            JsonToken jsonToken2 = JsonToken.END_OBJECT;
            this._currToken = jsonToken2;
            return jsonToken2;
        } else {
            if (this._parsingContext.expectComma()) {
                if (i != 44) {
                    _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                }
                i = _skipWS();
            }
            if (!this._parsingContext.inObject()) {
                _updateLocation();
                return _nextTokenNotInObject(i);
            }
            _updateNameLocation();
            this._parsingContext.setCurrentName(_parseName(i));
            this._currToken = JsonToken.FIELD_NAME;
            int i2 = _skipColon();
            _updateLocation();
            if (i2 == 34) {
                this._tokenIncomplete = true;
                this._nextToken = JsonToken.VALUE_STRING;
                return this._currToken;
            }
            switch (i2) {
                case 45:
                    t = _parseNegNumber();
                    break;
                case 48:
                case 49:
                case 50:
                case 51:
                case 52:
                case 53:
                case 54:
                case 55:
                case 56:
                case 57:
                    t = _parsePosNumber(i2);
                    break;
                case 91:
                    t = JsonToken.START_ARRAY;
                    break;
                case 102:
                    _matchToken(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 1);
                    t = JsonToken.VALUE_FALSE;
                    break;
                case 110:
                    _matchToken("null", 1);
                    t = JsonToken.VALUE_NULL;
                    break;
                case 116:
                    _matchToken("true", 1);
                    t = JsonToken.VALUE_TRUE;
                    break;
                case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
                    t = JsonToken.START_OBJECT;
                    break;
                default:
                    t = _handleUnexpectedValue(i2);
                    break;
            }
            this._nextToken = t;
            return this._currToken;
        }
    }

    private final JsonToken _nextTokenNotInObject(int i) throws IOException {
        if (i == 34) {
            this._tokenIncomplete = true;
            JsonToken jsonToken = JsonToken.VALUE_STRING;
            this._currToken = jsonToken;
            return jsonToken;
        }
        switch (i) {
            case 45:
                JsonToken _parseNegNumber = _parseNegNumber();
                this._currToken = _parseNegNumber;
                return _parseNegNumber;
            case 48:
            case 49:
            case 50:
            case 51:
            case 52:
            case 53:
            case 54:
            case 55:
            case 56:
            case 57:
                JsonToken _parsePosNumber = _parsePosNumber(i);
                this._currToken = _parsePosNumber;
                return _parsePosNumber;
            case 91:
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken2 = JsonToken.START_ARRAY;
                this._currToken = jsonToken2;
                return jsonToken2;
            case 102:
                _matchToken(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 1);
                JsonToken jsonToken3 = JsonToken.VALUE_FALSE;
                this._currToken = jsonToken3;
                return jsonToken3;
            case 110:
                _matchToken("null", 1);
                JsonToken jsonToken4 = JsonToken.VALUE_NULL;
                this._currToken = jsonToken4;
                return jsonToken4;
            case 116:
                _matchToken("true", 1);
                JsonToken jsonToken5 = JsonToken.VALUE_TRUE;
                this._currToken = jsonToken5;
                return jsonToken5;
            case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                JsonToken jsonToken6 = JsonToken.START_OBJECT;
                this._currToken = jsonToken6;
                return jsonToken6;
            default:
                JsonToken _handleUnexpectedValue = _handleUnexpectedValue(i);
                this._currToken = _handleUnexpectedValue;
                return _handleUnexpectedValue;
        }
    }

    private final JsonToken _nextAfterName() {
        this._nameCopied = false;
        JsonToken t = this._nextToken;
        this._nextToken = null;
        if (t == JsonToken.START_ARRAY) {
            this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
        } else if (t == JsonToken.START_OBJECT) {
            this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
        }
        this._currToken = t;
        return t;
    }

    public String nextFieldName() throws IOException {
        JsonToken t;
        String nameStr = null;
        this._numTypesValid = 0;
        if (this._currToken == JsonToken.FIELD_NAME) {
            _nextAfterName();
        } else {
            if (this._tokenIncomplete) {
                _skipString();
            }
            int i = _skipWSOrEnd();
            if (i < 0) {
                close();
                this._currToken = null;
            } else {
                this._binaryValue = null;
                if (i == 93) {
                    _updateLocation();
                    if (!this._parsingContext.inArray()) {
                        _reportMismatchedEndMarker(i, '}');
                    }
                    this._parsingContext = this._parsingContext.clearAndGetParent();
                    this._currToken = JsonToken.END_ARRAY;
                } else if (i == 125) {
                    _updateLocation();
                    if (!this._parsingContext.inObject()) {
                        _reportMismatchedEndMarker(i, ']');
                    }
                    this._parsingContext = this._parsingContext.clearAndGetParent();
                    this._currToken = JsonToken.END_OBJECT;
                } else {
                    if (this._parsingContext.expectComma()) {
                        if (i != 44) {
                            _reportUnexpectedChar(i, "was expecting comma to separate " + this._parsingContext.typeDesc() + " entries");
                        }
                        i = _skipWS();
                    }
                    if (!this._parsingContext.inObject()) {
                        _updateLocation();
                        _nextTokenNotInObject(i);
                    } else {
                        _updateNameLocation();
                        nameStr = _parseName(i);
                        this._parsingContext.setCurrentName(nameStr);
                        this._currToken = JsonToken.FIELD_NAME;
                        int i2 = _skipColon();
                        _updateLocation();
                        if (i2 == 34) {
                            this._tokenIncomplete = true;
                            this._nextToken = JsonToken.VALUE_STRING;
                        } else {
                            switch (i2) {
                                case 45:
                                    t = _parseNegNumber();
                                    break;
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                    t = _parsePosNumber(i2);
                                    break;
                                case 91:
                                    t = JsonToken.START_ARRAY;
                                    break;
                                case 102:
                                    _matchToken(InternalLogger.EVENT_PARAM_EXTRAS_FALSE, 1);
                                    t = JsonToken.VALUE_FALSE;
                                    break;
                                case 110:
                                    _matchToken("null", 1);
                                    t = JsonToken.VALUE_NULL;
                                    break;
                                case 116:
                                    _matchToken("true", 1);
                                    t = JsonToken.VALUE_TRUE;
                                    break;
                                case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
                                    t = JsonToken.START_OBJECT;
                                    break;
                                default:
                                    t = _handleUnexpectedValue(i2);
                                    break;
                            }
                            this._nextToken = t;
                        }
                    }
                }
            }
        }
        return nameStr;
    }

    public String nextTextValue() throws IOException {
        if (this._currToken == JsonToken.FIELD_NAME) {
            this._nameCopied = false;
            JsonToken t = this._nextToken;
            this._nextToken = null;
            this._currToken = t;
            if (t == JsonToken.VALUE_STRING) {
                if (!this._tokenIncomplete) {
                    return this._textBuffer.contentsAsString();
                }
                this._tokenIncomplete = false;
                return _finishAndReturnString();
            } else if (t == JsonToken.START_ARRAY) {
                this._parsingContext = this._parsingContext.createChildArrayContext(this._tokenInputRow, this._tokenInputCol);
                return null;
            } else if (t != JsonToken.START_OBJECT) {
                return null;
            } else {
                this._parsingContext = this._parsingContext.createChildObjectContext(this._tokenInputRow, this._tokenInputCol);
                return null;
            }
        } else if (nextToken() == JsonToken.VALUE_STRING) {
            return getText();
        } else {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public JsonToken _parsePosNumber(int c) throws IOException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        if (c == 48) {
            c = _verifyNoLeadingZeroes();
        }
        outBuf[0] = (char) c;
        int intLen = 1;
        int outPtr = 1;
        int end = (this._inputPtr + outBuf.length) - 1;
        if (end > this._inputEnd) {
            end = this._inputEnd;
        }
        while (this._inputPtr < end) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            byte b = bArr[i] & 255;
            if (b >= 48 && b <= 57) {
                intLen++;
                int outPtr2 = outPtr + 1;
                outBuf[outPtr] = (char) b;
                outPtr = outPtr2;
            } else if (b == 46 || b == 101 || b == 69) {
                return _parseFloat(outBuf, outPtr, b, false, intLen);
            } else {
                this._inputPtr--;
                this._textBuffer.setCurrentLength(outPtr);
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(b);
                }
                return resetInt(false, intLen);
            }
        }
        return _parseNumber2(outBuf, outPtr, false, intLen);
    }

    /* access modifiers changed from: protected */
    public JsonToken _parseNegNumber() throws IOException {
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int outPtr = 0 + 1;
        outBuf[0] = '-';
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int c = bArr[i] & 255;
        if (c < 48 || c > 57) {
            int i2 = outPtr;
            return _handleInvalidNumberStart(c, true);
        }
        if (c == 48) {
            c = _verifyNoLeadingZeroes();
        }
        int outPtr2 = outPtr + 1;
        outBuf[outPtr] = (char) c;
        int intLen = 1;
        int end = (this._inputPtr + outBuf.length) - 2;
        if (end > this._inputEnd) {
            end = this._inputEnd;
        }
        while (this._inputPtr < end) {
            byte[] bArr2 = this._inputBuffer;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int c2 = bArr2[i3] & 255;
            if (c2 >= 48 && c2 <= 57) {
                intLen++;
                int outPtr3 = outPtr2 + 1;
                outBuf[outPtr2] = (char) c2;
                outPtr2 = outPtr3;
            } else if (c2 == 46 || c2 == 101 || c2 == 69) {
                return _parseFloat(outBuf, outPtr2, c2, true, intLen);
            } else {
                this._inputPtr--;
                this._textBuffer.setCurrentLength(outPtr2);
                if (this._parsingContext.inRoot()) {
                    _verifyRootSpace(c2);
                }
                return resetInt(true, intLen);
            }
        }
        return _parseNumber2(outBuf, outPtr2, true, intLen);
    }

    private final JsonToken _parseNumber2(char[] outBuf, int outPtr, boolean negative, int intPartLength) throws IOException {
        int c;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                c = bArr[i] & 255;
                if (c <= 57 && c >= 48) {
                    if (outPtr >= outBuf.length) {
                        outBuf = this._textBuffer.finishCurrentSegment();
                        outPtr = 0;
                    }
                    int outPtr2 = outPtr + 1;
                    outBuf[outPtr] = (char) c;
                    intPartLength++;
                    outPtr = outPtr2;
                }
            } else {
                this._textBuffer.setCurrentLength(outPtr);
                return resetInt(negative, intPartLength);
            }
        }
        if (c == 46 || c == 101 || c == 69) {
            return _parseFloat(outBuf, outPtr, c, negative, intPartLength);
        }
        this._inputPtr--;
        this._textBuffer.setCurrentLength(outPtr);
        if (this._parsingContext.inRoot()) {
            byte[] bArr2 = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            _verifyRootSpace(bArr2[i2] & 255);
        }
        return resetInt(negative, intPartLength);
    }

    private final int _verifyNoLeadingZeroes() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return 48;
        }
        int ch = this._inputBuffer[this._inputPtr] & 255;
        if (ch < 48 || ch > 57) {
            return 48;
        }
        if (!isEnabled(Feature.ALLOW_NUMERIC_LEADING_ZEROS)) {
            reportInvalidNumber("Leading zeroes not allowed");
        }
        this._inputPtr++;
        if (ch != 48) {
            return ch;
        }
        do {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return ch;
            }
            ch = this._inputBuffer[this._inputPtr] & 255;
            if (ch < 48 || ch > 57) {
                return 48;
            }
            this._inputPtr++;
        } while (ch == 48);
        return ch;
    }

    private final JsonToken _parseFloat(char[] outBuf, int outPtr, int c, boolean negative, int integerPartLength) throws IOException {
        int outPtr2;
        int fractLen = 0;
        boolean eof = false;
        if (c == 46) {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int outPtr3 = outPtr + 1;
            outBuf[outPtr] = (char) c;
            while (true) {
                outPtr = outPtr3;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    eof = true;
                    break;
                }
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                c = bArr[i] & 255;
                if (c < 48 || c > 57) {
                    break;
                }
                fractLen++;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                outPtr3 = outPtr + 1;
                outBuf[outPtr] = (char) c;
            }
            if (fractLen == 0) {
                reportUnexpectedNumberChar(c, "Decimal point not followed by a digit");
            }
        }
        int expLen = 0;
        if (c == 101 || c == 69) {
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int outPtr4 = outPtr + 1;
            outBuf[outPtr] = (char) c;
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr2 = this._inputBuffer;
            int i2 = this._inputPtr;
            this._inputPtr = i2 + 1;
            c = bArr2[i2] & 255;
            if (c == 45 || c == 43) {
                if (outPtr4 >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr2 = 0;
                } else {
                    outPtr2 = outPtr4;
                }
                int outPtr5 = outPtr2 + 1;
                outBuf[outPtr2] = (char) c;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                c = bArr3[i3] & 255;
                outPtr = outPtr5;
            } else {
                outPtr = outPtr4;
            }
            while (true) {
                if (c > 57 || c < 48) {
                    break;
                }
                expLen++;
                if (outPtr >= outBuf.length) {
                    outBuf = this._textBuffer.finishCurrentSegment();
                    outPtr = 0;
                }
                int outPtr6 = outPtr + 1;
                outBuf[outPtr] = (char) c;
                if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                    eof = true;
                    outPtr = outPtr6;
                    break;
                }
                byte[] bArr4 = this._inputBuffer;
                int i4 = this._inputPtr;
                this._inputPtr = i4 + 1;
                c = bArr4[i4] & 255;
                outPtr = outPtr6;
            }
            if (expLen == 0) {
                reportUnexpectedNumberChar(c, "Exponent indicator not followed by a digit");
            }
        }
        if (!eof) {
            this._inputPtr--;
            if (this._parsingContext.inRoot()) {
                _verifyRootSpace(c);
            }
        }
        this._textBuffer.setCurrentLength(outPtr);
        return resetFloat(negative, integerPartLength, fractLen, expLen);
    }

    private final void _verifyRootSpace(int ch) throws IOException {
        this._inputPtr++;
        switch (ch) {
            case 9:
            case 32:
                return;
            case 10:
                this._currInputRow++;
                this._currInputRowStart = this._inputPtr;
                return;
            case 13:
                _skipCR();
                return;
            default:
                _reportMissingRootWS(ch);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public final String _parseName(int i) throws IOException {
        if (i != 34) {
            return _handleOddName(i);
        }
        if (this._inputPtr + 13 > this._inputEnd) {
            return slowParseName();
        }
        byte[] input = this._inputBuffer;
        int[] codes = _icLatin1;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        int q = input[i2] & 255;
        if (codes[q] == 0) {
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = input[i3] & 255;
            if (codes[i4] == 0) {
                int q2 = (q << 8) | i4;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = input[i5] & 255;
                if (codes[i6] == 0) {
                    int q3 = (q2 << 8) | i6;
                    int i7 = this._inputPtr;
                    this._inputPtr = i7 + 1;
                    int i8 = input[i7] & 255;
                    if (codes[i8] == 0) {
                        int q4 = (q3 << 8) | i8;
                        int i9 = this._inputPtr;
                        this._inputPtr = i9 + 1;
                        int i10 = input[i9] & 255;
                        if (codes[i10] == 0) {
                            this._quad1 = q4;
                            return parseMediumName(i10);
                        } else if (i10 == 34) {
                            return findName(q4, 4);
                        } else {
                            return parseName(q4, i10, 4);
                        }
                    } else if (i8 == 34) {
                        return findName(q3, 3);
                    } else {
                        return parseName(q3, i8, 3);
                    }
                } else if (i6 == 34) {
                    return findName(q2, 2);
                } else {
                    return parseName(q2, i6, 2);
                }
            } else if (i4 == 34) {
                return findName(q, 1);
            } else {
                return parseName(q, i4, 1);
            }
        } else if (q == 34) {
            return "";
        } else {
            return parseName(0, q, 0);
        }
    }

    /* access modifiers changed from: protected */
    public final String parseMediumName(int q2) throws IOException {
        byte[] input = this._inputBuffer;
        int[] codes = _icLatin1;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = input[i] & 255;
        if (codes[i2] == 0) {
            int q22 = (q2 << 8) | i2;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = input[i3] & 255;
            if (codes[i4] == 0) {
                int q23 = (q22 << 8) | i4;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = input[i5] & 255;
                if (codes[i6] == 0) {
                    int q24 = (q23 << 8) | i6;
                    int i7 = this._inputPtr;
                    this._inputPtr = i7 + 1;
                    int i8 = input[i7] & 255;
                    if (codes[i8] == 0) {
                        return parseMediumName2(i8, q24);
                    }
                    if (i8 == 34) {
                        return findName(this._quad1, q24, 4);
                    }
                    return parseName(this._quad1, q24, i8, 4);
                } else if (i6 == 34) {
                    return findName(this._quad1, q23, 3);
                } else {
                    return parseName(this._quad1, q23, i6, 3);
                }
            } else if (i4 == 34) {
                return findName(this._quad1, q22, 2);
            } else {
                return parseName(this._quad1, q22, i4, 2);
            }
        } else if (i2 == 34) {
            return findName(this._quad1, q2, 1);
        } else {
            return parseName(this._quad1, q2, i2, 1);
        }
    }

    /* access modifiers changed from: protected */
    public final String parseMediumName2(int q3, int q2) throws IOException {
        byte[] input = this._inputBuffer;
        int[] codes = _icLatin1;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = input[i] & 255;
        if (codes[i2] == 0) {
            int q32 = (q3 << 8) | i2;
            int i3 = this._inputPtr;
            this._inputPtr = i3 + 1;
            int i4 = input[i3] & 255;
            if (codes[i4] == 0) {
                int q33 = (q32 << 8) | i4;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int i6 = input[i5] & 255;
                if (codes[i6] == 0) {
                    int q34 = (q33 << 8) | i6;
                    int i7 = this._inputPtr;
                    this._inputPtr = i7 + 1;
                    int i8 = input[i7] & 255;
                    if (codes[i8] == 0) {
                        return parseLongName(i8, q2, q34);
                    }
                    if (i8 == 34) {
                        return findName(this._quad1, q2, q34, 4);
                    }
                    return parseName(this._quad1, q2, q34, i8, 4);
                } else if (i6 == 34) {
                    return findName(this._quad1, q2, q33, 3);
                } else {
                    return parseName(this._quad1, q2, q33, i6, 3);
                }
            } else if (i4 == 34) {
                return findName(this._quad1, q2, q32, 2);
            } else {
                return parseName(this._quad1, q2, q32, i4, 2);
            }
        } else if (i2 == 34) {
            return findName(this._quad1, q2, q3, 1);
        } else {
            return parseName(this._quad1, q2, q3, i2, 1);
        }
    }

    /* access modifiers changed from: protected */
    public final String parseLongName(int q, int q2, int q3) throws IOException {
        this._quadBuffer[0] = this._quad1;
        this._quadBuffer[1] = q2;
        this._quadBuffer[2] = q3;
        byte[] input = this._inputBuffer;
        int[] codes = _icLatin1;
        int qlen = 3;
        while (this._inputPtr + 4 <= this._inputEnd) {
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = input[i] & 255;
            if (codes[i2] == 0) {
                int q4 = (q << 8) | i2;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = input[i3] & 255;
                if (codes[i4] == 0) {
                    int q5 = (q4 << 8) | i4;
                    int i5 = this._inputPtr;
                    this._inputPtr = i5 + 1;
                    int i6 = input[i5] & 255;
                    if (codes[i6] == 0) {
                        int q6 = (q5 << 8) | i6;
                        int i7 = this._inputPtr;
                        this._inputPtr = i7 + 1;
                        int i8 = input[i7] & 255;
                        if (codes[i8] == 0) {
                            if (qlen >= this._quadBuffer.length) {
                                this._quadBuffer = growArrayBy(this._quadBuffer, qlen);
                            }
                            int qlen2 = qlen + 1;
                            this._quadBuffer[qlen] = q6;
                            q = i8;
                            qlen = qlen2;
                        } else if (i8 == 34) {
                            return findName(this._quadBuffer, qlen, q6, 4);
                        } else {
                            return parseEscapedName(this._quadBuffer, qlen, q6, i8, 4);
                        }
                    } else if (i6 == 34) {
                        return findName(this._quadBuffer, qlen, q5, 3);
                    } else {
                        return parseEscapedName(this._quadBuffer, qlen, q5, i6, 3);
                    }
                } else if (i4 == 34) {
                    return findName(this._quadBuffer, qlen, q4, 2);
                } else {
                    return parseEscapedName(this._quadBuffer, qlen, q4, i4, 2);
                }
            } else if (i2 == 34) {
                return findName(this._quadBuffer, qlen, q, 1);
            } else {
                return parseEscapedName(this._quadBuffer, qlen, q, i2, 1);
            }
        }
        return parseEscapedName(this._quadBuffer, qlen, 0, q, 0);
    }

    /* access modifiers changed from: protected */
    public String slowParseName() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(": was expecting closing '\"' for name", JsonToken.FIELD_NAME);
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 == 34) {
            return "";
        }
        return parseEscapedName(this._quadBuffer, 0, 0, i2, 0);
    }

    private final String parseName(int q1, int ch, int lastQuadBytes) throws IOException {
        return parseEscapedName(this._quadBuffer, 0, q1, ch, lastQuadBytes);
    }

    private final String parseName(int q1, int q2, int ch, int lastQuadBytes) throws IOException {
        this._quadBuffer[0] = q1;
        return parseEscapedName(this._quadBuffer, 1, q2, ch, lastQuadBytes);
    }

    private final String parseName(int q1, int q2, int q3, int ch, int lastQuadBytes) throws IOException {
        this._quadBuffer[0] = q1;
        this._quadBuffer[1] = q2;
        return parseEscapedName(this._quadBuffer, 2, q3, ch, lastQuadBytes);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String parseEscapedName(int[] r8, int r9, int r10, int r11, int r12) throws java.io.IOException {
        /*
            r7 = this;
            r6 = 4
            int[] r0 = _icLatin1
        L_0x0003:
            r3 = r0[r11]
            if (r3 == 0) goto L_0x00d0
            r3 = 34
            if (r11 != r3) goto L_0x002d
            if (r12 <= 0) goto L_0x0020
            int r3 = r8.length
            if (r9 < r3) goto L_0x0017
            int r3 = r8.length
            int[] r8 = growArrayBy(r8, r3)
            r7._quadBuffer = r8
        L_0x0017:
            int r2 = r9 + 1
            int r3 = pad(r10, r12)
            r8[r9] = r3
            r9 = r2
        L_0x0020:
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer r3 = r7._symbols
            java.lang.String r1 = r3.findName(r8, r9)
            if (r1 != 0) goto L_0x002c
            java.lang.String r1 = r7.addName(r8, r9, r12)
        L_0x002c:
            return r1
        L_0x002d:
            r3 = 92
            if (r11 == r3) goto L_0x008c
            java.lang.String r3 = "name"
            r7._throwUnquotedSpace(r11, r3)
        L_0x0037:
            r3 = 127(0x7f, float:1.78E-43)
            if (r11 <= r3) goto L_0x00d0
            if (r12 < r6) goto L_0x00cd
            int r3 = r8.length
            if (r9 < r3) goto L_0x0047
            int r3 = r8.length
            int[] r8 = growArrayBy(r8, r3)
            r7._quadBuffer = r8
        L_0x0047:
            int r2 = r9 + 1
            r8[r9] = r10
            r10 = 0
            r12 = 0
        L_0x004d:
            r3 = 2048(0x800, float:2.87E-42)
            if (r11 >= r3) goto L_0x0091
            int r3 = r10 << 8
            int r4 = r11 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            r10 = r3 | r4
            int r12 = r12 + 1
            r9 = r2
        L_0x005c:
            r3 = r11 & 63
            r11 = r3 | 128(0x80, float:1.794E-43)
            r2 = r9
        L_0x0061:
            if (r12 >= r6) goto L_0x00ba
            int r12 = r12 + 1
            int r3 = r10 << 8
            r10 = r3 | r11
            r9 = r2
        L_0x006a:
            int r3 = r7._inputPtr
            int r4 = r7._inputEnd
            if (r3 < r4) goto L_0x007e
            boolean r3 = r7._loadMore()
            if (r3 != 0) goto L_0x007e
            java.lang.String r3 = " in field name"
            com.fasterxml.jackson.core.JsonToken r4 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            r7._reportInvalidEOF(r3, r4)
        L_0x007e:
            byte[] r3 = r7._inputBuffer
            int r4 = r7._inputPtr
            int r5 = r4 + 1
            r7._inputPtr = r5
            byte r3 = r3[r4]
            r11 = r3 & 255(0xff, float:3.57E-43)
            goto L_0x0003
        L_0x008c:
            char r11 = r7._decodeEscaped()
            goto L_0x0037
        L_0x0091:
            int r3 = r10 << 8
            int r4 = r11 >> 12
            r4 = r4 | 224(0xe0, float:3.14E-43)
            r10 = r3 | r4
            int r12 = r12 + 1
            if (r12 < r6) goto L_0x00cb
            int r3 = r8.length
            if (r2 < r3) goto L_0x00a7
            int r3 = r8.length
            int[] r8 = growArrayBy(r8, r3)
            r7._quadBuffer = r8
        L_0x00a7:
            int r9 = r2 + 1
            r8[r2] = r10
            r10 = 0
            r12 = 0
        L_0x00ad:
            int r3 = r10 << 8
            int r4 = r11 >> 6
            r4 = r4 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
            r10 = r3 | r4
            int r12 = r12 + 1
            goto L_0x005c
        L_0x00ba:
            int r3 = r8.length
            if (r2 < r3) goto L_0x00c4
            int r3 = r8.length
            int[] r8 = growArrayBy(r8, r3)
            r7._quadBuffer = r8
        L_0x00c4:
            int r9 = r2 + 1
            r8[r2] = r10
            r10 = r11
            r12 = 1
            goto L_0x006a
        L_0x00cb:
            r9 = r2
            goto L_0x00ad
        L_0x00cd:
            r2 = r9
            goto L_0x004d
        L_0x00d0:
            r2 = r9
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.parseEscapedName(int[], int, int, int, int):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public String _handleOddName(int ch) throws IOException {
        if (ch == 39 && isEnabled(Feature.ALLOW_SINGLE_QUOTES)) {
            return _parseAposName();
        }
        if (!isEnabled(Feature.ALLOW_UNQUOTED_FIELD_NAMES)) {
            _reportUnexpectedChar((char) _decodeCharForError(ch), "was expecting double-quote to start field name");
        }
        int[] codes = CharTypes.getInputCodeUtf8JsNames();
        if (codes[ch] != 0) {
            _reportUnexpectedChar(ch, "was expecting either valid name character (for unquoted name) or double-quote (for quoted) to start field name");
        }
        int[] quads = this._quadBuffer;
        int qlen = 0;
        int currQuad = 0;
        int currQuadBytes = 0;
        while (true) {
            int qlen2 = qlen;
            if (currQuadBytes < 4) {
                currQuadBytes++;
                currQuad = (currQuad << 8) | ch;
                qlen = qlen2;
            } else {
                if (qlen2 >= quads.length) {
                    quads = growArrayBy(quads, quads.length);
                    this._quadBuffer = quads;
                }
                qlen = qlen2 + 1;
                quads[qlen2] = currQuad;
                currQuad = ch;
                currQuadBytes = 1;
            }
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                _reportInvalidEOF(" in field name", JsonToken.FIELD_NAME);
            }
            ch = this._inputBuffer[this._inputPtr] & 255;
            if (codes[ch] != 0) {
                break;
            }
            this._inputPtr++;
        }
        if (currQuadBytes > 0) {
            if (qlen >= quads.length) {
                quads = growArrayBy(quads, quads.length);
                this._quadBuffer = quads;
            }
            int qlen3 = qlen + 1;
            quads[qlen] = currQuad;
            qlen = qlen3;
        }
        String name = this._symbols.findName(quads, qlen);
        if (name == null) {
            return addName(quads, qlen, currQuadBytes);
        }
        return name;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v4
      assigns: []
      uses: []
      mth insns count: 120
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String _parseAposName() throws java.io.IOException {
        /*
            r13 = this;
            r12 = 39
            r11 = 4
            int r8 = r13._inputPtr
            int r9 = r13._inputEnd
            if (r8 < r9) goto L_0x0017
            boolean r8 = r13._loadMore()
            if (r8 != 0) goto L_0x0017
            java.lang.String r8 = ": was expecting closing ''' for field name"
            com.fasterxml.jackson.core.JsonToken r9 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            r13._reportInvalidEOF(r8, r9)
        L_0x0017:
            byte[] r8 = r13._inputBuffer
            int r9 = r13._inputPtr
            int r10 = r9 + 1
            r13._inputPtr = r10
            byte r8 = r8[r9]
            r0 = r8 & 255(0xff, float:3.57E-43)
            if (r0 != r12) goto L_0x0029
            java.lang.String r4 = ""
        L_0x0028:
            return r4
        L_0x0029:
            int[] r7 = r13._quadBuffer
            r5 = 0
            r2 = 0
            r3 = 0
            int[] r1 = _icLatin1
            r6 = r5
        L_0x0031:
            if (r0 != r12) goto L_0x0054
            if (r3 <= 0) goto L_0x00fc
            int r8 = r7.length
            if (r6 < r8) goto L_0x003f
            int r8 = r7.length
            int[] r7 = growArrayBy(r7, r8)
            r13._quadBuffer = r7
        L_0x003f:
            int r5 = r6 + 1
            int r8 = pad(r2, r3)
            r7[r6] = r8
        L_0x0047:
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer r8 = r13._symbols
            java.lang.String r4 = r8.findName(r7, r5)
            if (r4 != 0) goto L_0x0028
            java.lang.String r4 = r13.addName(r7, r5, r3)
            goto L_0x0028
        L_0x0054:
            r8 = 34
            if (r0 == r8) goto L_0x0091
            r8 = r1[r0]
            if (r8 == 0) goto L_0x0091
            r8 = 92
            if (r0 == r8) goto L_0x00bd
            java.lang.String r8 = "name"
            r13._throwUnquotedSpace(r0, r8)
        L_0x0066:
            r8 = 127(0x7f, float:1.78E-43)
            if (r0 <= r8) goto L_0x0091
            if (r3 < r11) goto L_0x007d
            int r8 = r7.length
            if (r6 < r8) goto L_0x0076
            int r8 = r7.length
            int[] r7 = growArrayBy(r7, r8)
            r13._quadBuffer = r7
        L_0x0076:
            int r5 = r6 + 1
            r7[r6] = r2
            r2 = 0
            r3 = 0
            r6 = r5
        L_0x007d:
            r8 = 2048(0x800, float:2.87E-42)
            if (r0 >= r8) goto L_0x00c2
            int r8 = r2 << 8
            int r9 = r0 >> 6
            r9 = r9 | 192(0xc0, float:2.69E-43)
            r2 = r8 | r9
            int r3 = r3 + 1
            r5 = r6
        L_0x008c:
            r8 = r0 & 63
            r0 = r8 | 128(0x80, float:1.794E-43)
            r6 = r5
        L_0x0091:
            if (r3 >= r11) goto L_0x00eb
            int r3 = r3 + 1
            int r8 = r2 << 8
            r2 = r8 | r0
            r5 = r6
        L_0x009a:
            int r8 = r13._inputPtr
            int r9 = r13._inputEnd
            if (r8 < r9) goto L_0x00ae
            boolean r8 = r13._loadMore()
            if (r8 != 0) goto L_0x00ae
            java.lang.String r8 = " in field name"
            com.fasterxml.jackson.core.JsonToken r9 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            r13._reportInvalidEOF(r8, r9)
        L_0x00ae:
            byte[] r8 = r13._inputBuffer
            int r9 = r13._inputPtr
            int r10 = r9 + 1
            r13._inputPtr = r10
            byte r8 = r8[r9]
            r0 = r8 & 255(0xff, float:3.57E-43)
            r6 = r5
            goto L_0x0031
        L_0x00bd:
            char r0 = r13._decodeEscaped()
            goto L_0x0066
        L_0x00c2:
            int r8 = r2 << 8
            int r9 = r0 >> 12
            r9 = r9 | 224(0xe0, float:3.14E-43)
            r2 = r8 | r9
            int r3 = r3 + 1
            if (r3 < r11) goto L_0x00ff
            int r8 = r7.length
            if (r6 < r8) goto L_0x00d8
            int r8 = r7.length
            int[] r7 = growArrayBy(r7, r8)
            r13._quadBuffer = r7
        L_0x00d8:
            int r5 = r6 + 1
            r7[r6] = r2
            r2 = 0
            r3 = 0
        L_0x00de:
            int r8 = r2 << 8
            int r9 = r0 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            r2 = r8 | r9
            int r3 = r3 + 1
            goto L_0x008c
        L_0x00eb:
            int r8 = r7.length
            if (r6 < r8) goto L_0x00f5
            int r8 = r7.length
            int[] r7 = growArrayBy(r7, r8)
            r13._quadBuffer = r7
        L_0x00f5:
            int r5 = r6 + 1
            r7[r6] = r2
            r2 = r0
            r3 = 1
            goto L_0x009a
        L_0x00fc:
            r5 = r6
            goto L_0x0047
        L_0x00ff:
            r5 = r6
            goto L_0x00de
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._parseAposName():java.lang.String");
    }

    private final String findName(int q1, int lastQuadBytes) throws JsonParseException {
        int q12 = pad(q1, lastQuadBytes);
        String name = this._symbols.findName(q12);
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = q12;
        return addName(this._quadBuffer, 1, lastQuadBytes);
    }

    private final String findName(int q1, int q2, int lastQuadBytes) throws JsonParseException {
        int q22 = pad(q2, lastQuadBytes);
        String name = this._symbols.findName(q1, q22);
        if (name != null) {
            return name;
        }
        this._quadBuffer[0] = q1;
        this._quadBuffer[1] = q22;
        return addName(this._quadBuffer, 2, lastQuadBytes);
    }

    private final String findName(int q1, int q2, int q3, int lastQuadBytes) throws JsonParseException {
        int q32 = pad(q3, lastQuadBytes);
        String name = this._symbols.findName(q1, q2, q32);
        if (name != null) {
            return name;
        }
        int[] quads = this._quadBuffer;
        quads[0] = q1;
        quads[1] = q2;
        quads[2] = pad(q32, lastQuadBytes);
        return addName(quads, 3, lastQuadBytes);
    }

    private final String findName(int[] quads, int qlen, int lastQuad, int lastQuadBytes) throws JsonParseException {
        if (qlen >= quads.length) {
            quads = growArrayBy(quads, quads.length);
            this._quadBuffer = quads;
        }
        int qlen2 = qlen + 1;
        quads[qlen] = pad(lastQuad, lastQuadBytes);
        String name = this._symbols.findName(quads, qlen2);
        if (name == null) {
            return addName(quads, qlen2, lastQuadBytes);
        }
        return name;
    }

    /* JADX WARNING: Removed duplicated region for block: B:35:0x00cd  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00d3 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.lang.String addName(int[] r15, int r16, int r17) throws com.fasterxml.jackson.core.JsonParseException {
        /*
            r14 = this;
            int r12 = r16 << 2
            int r12 = r12 + -4
            int r3 = r12 + r17
            r12 = 4
            r0 = r17
            if (r0 >= r12) goto L_0x00da
            int r12 = r16 + -1
            r10 = r15[r12]
            int r12 = r16 + -1
            int r13 = 4 - r17
            int r13 = r13 << 3
            int r13 = r10 << r13
            r15[r12] = r13
        L_0x0019:
            com.fasterxml.jackson.core.util.TextBuffer r12 = r14._textBuffer
            char[] r4 = r12.emptyAndGetCurrentSegment()
            r7 = 0
            r9 = 0
            r8 = r7
        L_0x0022:
            if (r9 >= r3) goto L_0x00fa
            int r12 = r9 >> 2
            r5 = r15[r12]
            r2 = r9 & 3
            int r12 = 3 - r2
            int r12 = r12 << 3
            int r12 = r5 >> r12
            r5 = r12 & 255(0xff, float:3.57E-43)
            int r9 = r9 + 1
            r12 = 127(0x7f, float:1.78E-43)
            if (r5 <= r12) goto L_0x0112
            r12 = r5 & 224(0xe0, float:3.14E-43)
            r13 = 192(0xc0, float:2.69E-43)
            if (r12 != r13) goto L_0x00dd
            r5 = r5 & 31
            r11 = 1
        L_0x0041:
            int r12 = r9 + r11
            if (r12 <= r3) goto L_0x004d
            java.lang.String r12 = " in field name"
            com.fasterxml.jackson.core.JsonToken r13 = com.fasterxml.jackson.core.JsonToken.FIELD_NAME
            r14._reportInvalidEOF(r12, r13)
        L_0x004d:
            int r12 = r9 >> 2
            r6 = r15[r12]
            r2 = r9 & 3
            int r12 = 3 - r2
            int r12 = r12 << 3
            int r6 = r6 >> r12
            int r9 = r9 + 1
            r12 = r6 & 192(0xc0, float:2.69E-43)
            r13 = 128(0x80, float:1.794E-43)
            if (r12 == r13) goto L_0x0063
            r14._reportInvalidOther(r6)
        L_0x0063:
            int r12 = r5 << 6
            r13 = r6 & 63
            r5 = r12 | r13
            r12 = 1
            if (r11 <= r12) goto L_0x00a9
            int r12 = r9 >> 2
            r6 = r15[r12]
            r2 = r9 & 3
            int r12 = 3 - r2
            int r12 = r12 << 3
            int r6 = r6 >> r12
            int r9 = r9 + 1
            r12 = r6 & 192(0xc0, float:2.69E-43)
            r13 = 128(0x80, float:1.794E-43)
            if (r12 == r13) goto L_0x0082
            r14._reportInvalidOther(r6)
        L_0x0082:
            int r12 = r5 << 6
            r13 = r6 & 63
            r5 = r12 | r13
            r12 = 2
            if (r11 <= r12) goto L_0x00a9
            int r12 = r9 >> 2
            r6 = r15[r12]
            r2 = r9 & 3
            int r12 = 3 - r2
            int r12 = r12 << 3
            int r6 = r6 >> r12
            int r9 = r9 + 1
            r12 = r6 & 192(0xc0, float:2.69E-43)
            r13 = 128(0x80, float:1.794E-43)
            if (r12 == r13) goto L_0x00a3
            r12 = r6 & 255(0xff, float:3.57E-43)
            r14._reportInvalidOther(r12)
        L_0x00a3:
            int r12 = r5 << 6
            r13 = r6 & 63
            r5 = r12 | r13
        L_0x00a9:
            r12 = 2
            if (r11 <= r12) goto L_0x0112
            r12 = 65536(0x10000, float:9.18355E-41)
            int r5 = r5 - r12
            int r12 = r4.length
            if (r8 < r12) goto L_0x00b8
            com.fasterxml.jackson.core.util.TextBuffer r12 = r14._textBuffer
            char[] r4 = r12.expandCurrentSegment()
        L_0x00b8:
            int r7 = r8 + 1
            r12 = 55296(0xd800, float:7.7486E-41)
            int r13 = r5 >> 10
            int r12 = r12 + r13
            char r12 = (char) r12
            r4[r8] = r12
            r12 = 56320(0xdc00, float:7.8921E-41)
            r13 = r5 & 1023(0x3ff, float:1.434E-42)
            r5 = r12 | r13
        L_0x00ca:
            int r12 = r4.length
            if (r7 < r12) goto L_0x00d3
            com.fasterxml.jackson.core.util.TextBuffer r12 = r14._textBuffer
            char[] r4 = r12.expandCurrentSegment()
        L_0x00d3:
            int r8 = r7 + 1
            char r12 = (char) r5
            r4[r7] = r12
            goto L_0x0022
        L_0x00da:
            r10 = 0
            goto L_0x0019
        L_0x00dd:
            r12 = r5 & 240(0xf0, float:3.36E-43)
            r13 = 224(0xe0, float:3.14E-43)
            if (r12 != r13) goto L_0x00e8
            r5 = r5 & 15
            r11 = 2
            goto L_0x0041
        L_0x00e8:
            r12 = r5 & 248(0xf8, float:3.48E-43)
            r13 = 240(0xf0, float:3.36E-43)
            if (r12 != r13) goto L_0x00f3
            r5 = r5 & 7
            r11 = 3
            goto L_0x0041
        L_0x00f3:
            r14._reportInvalidInitial(r5)
            r5 = 1
            r11 = r5
            goto L_0x0041
        L_0x00fa:
            java.lang.String r1 = new java.lang.String
            r12 = 0
            r1.<init>(r4, r12, r8)
            r12 = 4
            r0 = r17
            if (r0 >= r12) goto L_0x0109
            int r12 = r16 + -1
            r15[r12] = r10
        L_0x0109:
            com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer r12 = r14._symbols
            r0 = r16
            java.lang.String r12 = r12.addName(r1, r15, r0)
            return r12
        L_0x0112:
            r7 = r8
            goto L_0x00ca
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser.addName(int[], int, int):java.lang.String");
    }

    /* access modifiers changed from: protected */
    public void _loadMoreGuaranteed() throws IOException {
        if (!_loadMore()) {
            _reportInvalidEOF();
        }
    }

    /* access modifiers changed from: protected */
    public void _finishString() throws IOException {
        int ptr = this._inputPtr;
        if (ptr >= this._inputEnd) {
            _loadMoreGuaranteed();
            ptr = this._inputPtr;
        }
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int max = Math.min(this._inputEnd, outBuf.length + ptr);
        byte[] inputBuffer = this._inputBuffer;
        int outPtr = 0;
        while (true) {
            if (ptr >= max) {
                break;
            }
            int c = inputBuffer[ptr] & 255;
            if (codes[c] == 0) {
                ptr++;
                int outPtr2 = outPtr + 1;
                outBuf[outPtr] = (char) c;
                outPtr = outPtr2;
            } else if (c == 34) {
                this._inputPtr = ptr + 1;
                this._textBuffer.setCurrentLength(outPtr);
                return;
            }
        }
        this._inputPtr = ptr;
        _finishString2(outBuf, outPtr);
    }

    /* access modifiers changed from: protected */
    public String _finishAndReturnString() throws IOException {
        int ptr = this._inputPtr;
        if (ptr >= this._inputEnd) {
            _loadMoreGuaranteed();
            ptr = this._inputPtr;
        }
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        int max = Math.min(this._inputEnd, outBuf.length + ptr);
        byte[] inputBuffer = this._inputBuffer;
        int outPtr = 0;
        while (true) {
            if (ptr >= max) {
                break;
            }
            int c = inputBuffer[ptr] & 255;
            if (codes[c] == 0) {
                ptr++;
                int outPtr2 = outPtr + 1;
                outBuf[outPtr] = (char) c;
                outPtr = outPtr2;
            } else if (c == 34) {
                this._inputPtr = ptr + 1;
                return this._textBuffer.setCurrentAndReturn(outPtr);
            }
        }
        this._inputPtr = ptr;
        _finishString2(outBuf, outPtr);
        return this._textBuffer.contentsAsString();
    }

    private final void _finishString2(char[] outBuf, int outPtr) throws IOException {
        int outPtr2;
        int[] codes = _icUTF8;
        byte[] inputBuffer = this._inputBuffer;
        while (true) {
            int ptr = this._inputPtr;
            if (ptr >= this._inputEnd) {
                _loadMoreGuaranteed();
                ptr = this._inputPtr;
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int max = Math.min(this._inputEnd, (outBuf.length - outPtr) + ptr);
            int ptr2 = ptr;
            int outPtr3 = outPtr;
            while (true) {
                if (ptr2 < max) {
                    int ptr3 = ptr2 + 1;
                    int c = inputBuffer[ptr2] & 255;
                    if (codes[c] != 0) {
                        this._inputPtr = ptr3;
                        if (c == 34) {
                            this._textBuffer.setCurrentLength(outPtr3);
                            return;
                        }
                        switch (codes[c]) {
                            case 1:
                                c = _decodeEscaped();
                                outPtr2 = outPtr3;
                                break;
                            case 2:
                                c = _decodeUtf8_2(c);
                                outPtr2 = outPtr3;
                                break;
                            case 3:
                                if (this._inputEnd - this._inputPtr < 2) {
                                    c = _decodeUtf8_3(c);
                                    outPtr2 = outPtr3;
                                    break;
                                } else {
                                    c = _decodeUtf8_3fast(c);
                                    outPtr2 = outPtr3;
                                    break;
                                }
                            case 4:
                                int c2 = _decodeUtf8_4(c);
                                outPtr2 = outPtr3 + 1;
                                outBuf[outPtr3] = (char) (55296 | (c2 >> 10));
                                if (outPtr2 >= outBuf.length) {
                                    outBuf = this._textBuffer.finishCurrentSegment();
                                    outPtr2 = 0;
                                }
                                c = 56320 | (c2 & 1023);
                                break;
                            default:
                                if (c >= 32) {
                                    _reportInvalidChar(c);
                                    outPtr2 = outPtr3;
                                    break;
                                } else {
                                    _throwUnquotedSpace(c, "string value");
                                    outPtr2 = outPtr3;
                                    break;
                                }
                        }
                        if (outPtr2 >= outBuf.length) {
                            outBuf = this._textBuffer.finishCurrentSegment();
                            outPtr2 = 0;
                        }
                        int outPtr4 = outPtr2 + 1;
                        outBuf[outPtr2] = (char) c;
                        outPtr = outPtr4;
                    } else {
                        int outPtr5 = outPtr3 + 1;
                        outBuf[outPtr3] = (char) c;
                        ptr2 = ptr3;
                        outPtr3 = outPtr5;
                    }
                } else {
                    this._inputPtr = ptr2;
                    outPtr = outPtr3;
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0028  */
    /* JADX WARNING: Removed duplicated region for block: B:5:0x0017  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void _skipString() throws java.io.IOException {
        /*
            r7 = this;
            r6 = 0
            r7._tokenIncomplete = r6
            int[] r1 = _icUTF8
            byte[] r2 = r7._inputBuffer
        L_0x0007:
            int r4 = r7._inputPtr
            int r3 = r7._inputEnd
            if (r4 < r3) goto L_0x004f
            r7._loadMoreGuaranteed()
            int r4 = r7._inputPtr
            int r3 = r7._inputEnd
            r5 = r4
        L_0x0015:
            if (r5 >= r3) goto L_0x0028
            int r4 = r5 + 1
            byte r6 = r2[r5]
            r0 = r6 & 255(0xff, float:3.57E-43)
            r6 = r1[r0]
            if (r6 == 0) goto L_0x004f
            r7._inputPtr = r4
            r6 = 34
            if (r0 != r6) goto L_0x002b
            return
        L_0x0028:
            r7._inputPtr = r5
            goto L_0x0007
        L_0x002b:
            r6 = r1[r0]
            switch(r6) {
                case 1: goto L_0x003b;
                case 2: goto L_0x003f;
                case 3: goto L_0x0043;
                case 4: goto L_0x0047;
                default: goto L_0x0030;
            }
        L_0x0030:
            r6 = 32
            if (r0 >= r6) goto L_0x004b
            java.lang.String r6 = "string value"
            r7._throwUnquotedSpace(r0, r6)
            goto L_0x0007
        L_0x003b:
            r7._decodeEscaped()
            goto L_0x0007
        L_0x003f:
            r7._skipUtf8_2()
            goto L_0x0007
        L_0x0043:
            r7._skipUtf8_3()
            goto L_0x0007
        L_0x0047:
            r7._skipUtf8_4(r0)
            goto L_0x0007
        L_0x004b:
            r7._reportInvalidChar(r0)
            goto L_0x0007
        L_0x004f:
            r5 = r4
            goto L_0x0015
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipString():void");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003d, code lost:
        r4._inputPtr--;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0046, code lost:
        _reportUnexpectedChar(r5, "expected a value");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0052, code lost:
        if (isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES) == false) goto L_0x0004;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:?, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:?, code lost:
        return com.fasterxml.jackson.core.JsonToken.VALUE_NULL;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:?, code lost:
        return _handleApos();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        if (java.lang.Character.isJavaIdentifierStart(r5) == false) goto L_0x0025;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000a, code lost:
        _reportInvalidToken("" + ((char) r5), "('true', 'false' or 'null')");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0025, code lost:
        _reportUnexpectedChar(r5, "expected a valid value (number, String, array, object, 'true', 'false' or 'null')");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0033, code lost:
        if (r4._parsingContext.inArray() != false) goto L_0x0035;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003b, code lost:
        if (isEnabled(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES) == false) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleUnexpectedValue(int r5) throws java.io.IOException {
        /*
            r4 = this;
            r1 = 1
            switch(r5) {
                case 39: goto L_0x004c;
                case 43: goto L_0x0098;
                case 44: goto L_0x0035;
                case 73: goto L_0x0078;
                case 78: goto L_0x0059;
                case 93: goto L_0x002d;
                case 125: goto L_0x0046;
                default: goto L_0x0004;
            }
        L_0x0004:
            boolean r0 = java.lang.Character.isJavaIdentifierStart(r5)
            if (r0 == 0) goto L_0x0025
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = ""
            java.lang.StringBuilder r0 = r0.append(r1)
            char r1 = (char) r5
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "('true', 'false' or 'null')"
            r4._reportInvalidToken(r0, r1)
        L_0x0025:
            java.lang.String r0 = "expected a valid value (number, String, array, object, 'true', 'false' or 'null')"
            r4._reportUnexpectedChar(r5, r0)
            r0 = 0
        L_0x002c:
            return r0
        L_0x002d:
            com.fasterxml.jackson.core.json.JsonReadContext r0 = r4._parsingContext
            boolean r0 = r0.inArray()
            if (r0 == 0) goto L_0x0004
        L_0x0035:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_MISSING_VALUES
            boolean r0 = r4.isEnabled(r0)
            if (r0 == 0) goto L_0x0046
            int r0 = r4._inputPtr
            int r0 = r0 + -1
            r4._inputPtr = r0
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_NULL
            goto L_0x002c
        L_0x0046:
            java.lang.String r0 = "expected a value"
            r4._reportUnexpectedChar(r5, r0)
        L_0x004c:
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_SINGLE_QUOTES
            boolean r0 = r4.isEnabled(r0)
            if (r0 == 0) goto L_0x0004
            com.fasterxml.jackson.core.JsonToken r0 = r4._handleApos()
            goto L_0x002c
        L_0x0059:
            java.lang.String r0 = "NaN"
            r4._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r0 = r4.isEnabled(r0)
            if (r0 == 0) goto L_0x0071
            java.lang.String r0 = "NaN"
            r2 = 9221120237041090560(0x7ff8000000000000, double:NaN)
            com.fasterxml.jackson.core.JsonToken r0 = r4.resetAsNaN(r0, r2)
            goto L_0x002c
        L_0x0071:
            java.lang.String r0 = "Non-standard token 'NaN': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r4._reportError(r0)
            goto L_0x0004
        L_0x0078:
            java.lang.String r0 = "Infinity"
            r4._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r0 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r0 = r4.isEnabled(r0)
            if (r0 == 0) goto L_0x0090
            java.lang.String r0 = "Infinity"
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            com.fasterxml.jackson.core.JsonToken r0 = r4.resetAsNaN(r0, r2)
            goto L_0x002c
        L_0x0090:
            java.lang.String r0 = "Non-standard token 'Infinity': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            r4._reportError(r0)
            goto L_0x0004
        L_0x0098:
            int r0 = r4._inputPtr
            int r1 = r4._inputEnd
            if (r0 < r1) goto L_0x00a9
            boolean r0 = r4._loadMore()
            if (r0 != 0) goto L_0x00a9
            com.fasterxml.jackson.core.JsonToken r0 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_INT
            r4._reportInvalidEOFInValue(r0)
        L_0x00a9:
            byte[] r0 = r4._inputBuffer
            int r1 = r4._inputPtr
            int r2 = r1 + 1
            r4._inputPtr = r2
            byte r0 = r0[r1]
            r0 = r0 & 255(0xff, float:3.57E-43)
            r1 = 0
            com.fasterxml.jackson.core.JsonToken r0 = r4._handleInvalidNumberStart(r0, r1)
            goto L_0x002c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleUnexpectedValue(int):com.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    public JsonToken _handleApos() throws IOException {
        int outPtr = 0;
        char[] outBuf = this._textBuffer.emptyAndGetCurrentSegment();
        int[] codes = _icUTF8;
        byte[] inputBuffer = this._inputBuffer;
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            if (outPtr >= outBuf.length) {
                outBuf = this._textBuffer.finishCurrentSegment();
                outPtr = 0;
            }
            int max = this._inputEnd;
            int max2 = this._inputPtr + (outBuf.length - outPtr);
            if (max2 < max) {
                max = max2;
            }
            while (true) {
                if (this._inputPtr < max) {
                    int i = this._inputPtr;
                    this._inputPtr = i + 1;
                    int c = inputBuffer[i] & 255;
                    if (c != 39 && codes[c] == 0) {
                        int outPtr2 = outPtr + 1;
                        outBuf[outPtr] = (char) c;
                        outPtr = outPtr2;
                    } else if (c == 39) {
                        this._textBuffer.setCurrentLength(outPtr);
                        return JsonToken.VALUE_STRING;
                    } else {
                        switch (codes[c]) {
                            case 1:
                                c = _decodeEscaped();
                                break;
                            case 2:
                                c = _decodeUtf8_2(c);
                                break;
                            case 3:
                                if (this._inputEnd - this._inputPtr < 2) {
                                    c = _decodeUtf8_3(c);
                                    break;
                                } else {
                                    c = _decodeUtf8_3fast(c);
                                    break;
                                }
                            case 4:
                                int c2 = _decodeUtf8_4(c);
                                int outPtr3 = outPtr + 1;
                                outBuf[outPtr] = (char) (55296 | (c2 >> 10));
                                if (outPtr3 >= outBuf.length) {
                                    outBuf = this._textBuffer.finishCurrentSegment();
                                    outPtr = 0;
                                } else {
                                    outPtr = outPtr3;
                                }
                                c = 56320 | (c2 & 1023);
                                break;
                            default:
                                if (c < 32) {
                                    _throwUnquotedSpace(c, "string value");
                                }
                                _reportInvalidChar(c);
                                break;
                        }
                        if (outPtr >= outBuf.length) {
                            outBuf = this._textBuffer.finishCurrentSegment();
                            outPtr = 0;
                        }
                        int outPtr4 = outPtr + 1;
                        outBuf[outPtr] = (char) c;
                        outPtr = outPtr4;
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect condition in loop: B:1:0x0002 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.core.JsonToken _handleInvalidNumberStart(int r5, boolean r6) throws java.io.IOException {
        /*
            r4 = this;
        L_0x0000:
            r1 = 73
            if (r5 != r1) goto L_0x0071
            int r1 = r4._inputPtr
            int r2 = r4._inputEnd
            if (r1 < r2) goto L_0x0015
            boolean r1 = r4._loadMore()
            if (r1 != 0) goto L_0x0015
            com.fasterxml.jackson.core.JsonToken r1 = com.fasterxml.jackson.core.JsonToken.VALUE_NUMBER_FLOAT
            r4._reportInvalidEOFInValue(r1)
        L_0x0015:
            byte[] r1 = r4._inputBuffer
            int r2 = r4._inputPtr
            int r3 = r2 + 1
            r4._inputPtr = r3
            byte r5 = r1[r2]
            r1 = 78
            if (r5 != r1) goto L_0x0041
            if (r6 == 0) goto L_0x003d
            java.lang.String r0 = "-INF"
        L_0x0028:
            r1 = 3
            r4._matchToken(r0, r1)
            com.fasterxml.jackson.core.JsonParser$Feature r1 = com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS
            boolean r1 = r4.isEnabled(r1)
            if (r1 == 0) goto L_0x0052
            if (r6 == 0) goto L_0x004f
            r2 = -4503599627370496(0xfff0000000000000, double:-Infinity)
        L_0x0038:
            com.fasterxml.jackson.core.JsonToken r1 = r4.resetAsNaN(r0, r2)
        L_0x003c:
            return r1
        L_0x003d:
            java.lang.String r0 = "+INF"
            goto L_0x0028
        L_0x0041:
            r1 = 110(0x6e, float:1.54E-43)
            if (r5 != r1) goto L_0x0071
            if (r6 == 0) goto L_0x004b
            java.lang.String r0 = "-Infinity"
        L_0x004a:
            goto L_0x0028
        L_0x004b:
            java.lang.String r0 = "+Infinity"
            goto L_0x004a
        L_0x004f:
            r2 = 9218868437227405312(0x7ff0000000000000, double:Infinity)
            goto L_0x0038
        L_0x0052:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Non-standard token '"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.StringBuilder r1 = r1.append(r0)
            java.lang.String r2 = "': enable JsonParser.Feature.ALLOW_NON_NUMERIC_NUMBERS to allow"
            java.lang.StringBuilder r1 = r1.append(r2)
            java.lang.String r1 = r1.toString()
            r4._reportError(r1)
            goto L_0x0000
        L_0x0071:
            java.lang.String r1 = "expected digit (0-9) to follow minus sign, for valid numeric value"
            r4.reportUnexpectedNumberChar(r5, r1)
            r1 = 0
            goto L_0x003c
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._handleInvalidNumberStart(int, boolean):com.fasterxml.jackson.core.JsonToken");
    }

    /* access modifiers changed from: protected */
    public final void _matchToken(String matchStr, int i) throws IOException {
        int len = matchStr.length();
        if (this._inputPtr + len >= this._inputEnd) {
            _matchToken2(matchStr, i);
            return;
        }
        do {
            if (this._inputBuffer[this._inputPtr] != matchStr.charAt(i)) {
                _reportInvalidToken(matchStr.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < len);
        int ch = this._inputBuffer[this._inputPtr] & 255;
        if (ch >= 48 && ch != 93 && ch != 125) {
            _checkMatchEnd(matchStr, i, ch);
        }
    }

    private final void _matchToken2(String matchStr, int i) throws IOException {
        int len = matchStr.length();
        do {
            if ((this._inputPtr >= this._inputEnd && !_loadMore()) || this._inputBuffer[this._inputPtr] != matchStr.charAt(i)) {
                _reportInvalidToken(matchStr.substring(0, i));
            }
            this._inputPtr++;
            i++;
        } while (i < len);
        if (this._inputPtr < this._inputEnd || _loadMore()) {
            int ch = this._inputBuffer[this._inputPtr] & 255;
            if (ch >= 48 && ch != 93 && ch != 125) {
                _checkMatchEnd(matchStr, i, ch);
            }
        }
    }

    private final void _checkMatchEnd(String matchStr, int i, int ch) throws IOException {
        if (Character.isJavaIdentifierPart((char) _decodeCharForError(ch))) {
            _reportInvalidToken(matchStr.substring(0, i));
        }
    }

    private final int _skipWS() throws IOException {
        while (this._inputPtr < this._inputEnd) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 != 47 && i2 != 35) {
                    return i2;
                }
                this._inputPtr--;
                return _skipWS2();
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
        return _skipWS2();
    }

    private final int _skipWS2() throws IOException {
        int i;
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                i = bArr[i2] & 255;
                if (i > 32) {
                    if (i == 47) {
                        _skipComment();
                    } else if (i != 35 || !_skipYAMLComment()) {
                        return i;
                    }
                } else if (i != 32) {
                    if (i == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i == 13) {
                        _skipCR();
                    } else if (i != 9) {
                        _throwInvalidSpace(i);
                    }
                }
            } else {
                throw _constructError("Unexpected end-of-input within/between " + this._parsingContext.typeDesc() + " entries");
            }
        }
        return i;
    }

    private final int _skipWSOrEnd() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            return _eofAsNextChar();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int i2 = bArr[i] & 255;
        if (i2 <= 32) {
            if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
            while (this._inputPtr < this._inputEnd) {
                byte[] bArr2 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int i4 = bArr2[i3] & 255;
                if (i4 > 32) {
                    if (i4 != 47 && i4 != 35) {
                        return i4;
                    }
                    this._inputPtr--;
                    return _skipWSOrEnd2();
                } else if (i4 != 32) {
                    if (i4 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i4 == 13) {
                        _skipCR();
                    } else if (i4 != 9) {
                        _throwInvalidSpace(i4);
                    }
                }
            }
            return _skipWSOrEnd2();
        } else if (i2 != 47 && i2 != 35) {
            return i2;
        } else {
            this._inputPtr--;
            return _skipWSOrEnd2();
        }
    }

    private final int _skipWSOrEnd2() throws IOException {
        while (true) {
            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                return _eofAsNextChar();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int i2 = bArr[i] & 255;
            if (i2 > 32) {
                if (i2 == 47) {
                    _skipComment();
                } else if (i2 != 35 || !_skipYAMLComment()) {
                    return i2;
                }
            } else if (i2 != 32) {
                if (i2 == 10) {
                    this._currInputRow++;
                    this._currInputRowStart = this._inputPtr;
                } else if (i2 == 13) {
                    _skipCR();
                } else if (i2 != 9) {
                    _throwInvalidSpace(i2);
                }
            }
        }
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v0, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v8, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int _skipColon() throws java.io.IOException {
        /*
            r8 = this;
            r7 = 9
            r6 = 47
            r5 = 35
            r4 = 1
            r3 = 32
            int r1 = r8._inputPtr
            int r1 = r1 + 4
            int r2 = r8._inputEnd
            if (r1 < r2) goto L_0x0017
            r1 = 0
            int r0 = r8._skipColon2(r1)
        L_0x0016:
            return r0
        L_0x0017:
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            byte r0 = r1[r2]
            r1 = 58
            if (r0 != r1) goto L_0x0062
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            int r2 = r2 + 1
            r8._inputPtr = r2
            byte r0 = r1[r2]
            if (r0 <= r3) goto L_0x003d
            if (r0 == r6) goto L_0x0031
            if (r0 != r5) goto L_0x0036
        L_0x0031:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x0036:
            int r1 = r8._inputPtr
            int r1 = r1 + 1
            r8._inputPtr = r1
            goto L_0x0016
        L_0x003d:
            if (r0 == r3) goto L_0x0041
            if (r0 != r7) goto L_0x005d
        L_0x0041:
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            int r2 = r2 + 1
            r8._inputPtr = r2
            byte r0 = r1[r2]
            if (r0 <= r3) goto L_0x005d
            if (r0 == r6) goto L_0x0051
            if (r0 != r5) goto L_0x0056
        L_0x0051:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x0056:
            int r1 = r8._inputPtr
            int r1 = r1 + 1
            r8._inputPtr = r1
            goto L_0x0016
        L_0x005d:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x0062:
            if (r0 == r3) goto L_0x0066
            if (r0 != r7) goto L_0x0070
        L_0x0066:
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            int r2 = r2 + 1
            r8._inputPtr = r2
            byte r0 = r1[r2]
        L_0x0070:
            r1 = 58
            if (r0 != r1) goto L_0x00b8
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            int r2 = r2 + 1
            r8._inputPtr = r2
            byte r0 = r1[r2]
            if (r0 <= r3) goto L_0x0090
            if (r0 == r6) goto L_0x0084
            if (r0 != r5) goto L_0x0089
        L_0x0084:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x0089:
            int r1 = r8._inputPtr
            int r1 = r1 + 1
            r8._inputPtr = r1
            goto L_0x0016
        L_0x0090:
            if (r0 == r3) goto L_0x0094
            if (r0 != r7) goto L_0x00b2
        L_0x0094:
            byte[] r1 = r8._inputBuffer
            int r2 = r8._inputPtr
            int r2 = r2 + 1
            r8._inputPtr = r2
            byte r0 = r1[r2]
            if (r0 <= r3) goto L_0x00b2
            if (r0 == r6) goto L_0x00a4
            if (r0 != r5) goto L_0x00aa
        L_0x00a4:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x00aa:
            int r1 = r8._inputPtr
            int r1 = r1 + 1
            r8._inputPtr = r1
            goto L_0x0016
        L_0x00b2:
            int r0 = r8._skipColon2(r4)
            goto L_0x0016
        L_0x00b8:
            r1 = 0
            int r0 = r8._skipColon2(r1)
            goto L_0x0016
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8StreamJsonParser._skipColon():int");
    }

    private final int _skipColon2(boolean gotColon) throws IOException {
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                if (i2 > 32) {
                    if (i2 == 47) {
                        _skipComment();
                    } else if (i2 != 35 || !_skipYAMLComment()) {
                        if (gotColon) {
                            return i2;
                        }
                        if (i2 != 58) {
                            _reportUnexpectedChar(i2, "was expecting a colon to separate field name and value");
                        }
                        gotColon = true;
                    }
                } else if (i2 != 32) {
                    if (i2 == 10) {
                        this._currInputRow++;
                        this._currInputRowStart = this._inputPtr;
                    } else if (i2 == 13) {
                        _skipCR();
                    } else if (i2 != 9) {
                        _throwInvalidSpace(i2);
                    }
                }
            } else {
                _reportInvalidEOF(" within/between " + this._parsingContext.typeDesc() + " entries", null);
                return -1;
            }
        }
    }

    private final void _skipComment() throws IOException {
        if (!isEnabled(Feature.ALLOW_COMMENTS)) {
            _reportUnexpectedChar(47, "maybe a (non-standard) comment? (not recognized as one since Feature 'ALLOW_COMMENTS' not enabled for parser)");
        }
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in a comment", null);
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        int c = bArr[i] & 255;
        if (c == 47) {
            _skipLine();
        } else if (c == 42) {
            _skipCComment();
        } else {
            _reportUnexpectedChar(c, "was expecting either '*' or '/' for a comment");
        }
    }

    private final void _skipCComment() throws IOException {
        int[] codes = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                int code = codes[i2];
                if (code != 0) {
                    switch (code) {
                        case 2:
                            _skipUtf8_2();
                            continue;
                        case 3:
                            _skipUtf8_3();
                            continue;
                        case 4:
                            _skipUtf8_4(i2);
                            continue;
                        case 10:
                            this._currInputRow++;
                            this._currInputRowStart = this._inputPtr;
                            continue;
                        case 13:
                            _skipCR();
                            continue;
                        case 42:
                            if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                                break;
                            } else if (this._inputBuffer[this._inputPtr] == 47) {
                                this._inputPtr++;
                                return;
                            } else {
                                continue;
                            }
                        default:
                            _reportInvalidChar(i2);
                            continue;
                    }
                }
            }
        }
        _reportInvalidEOF(" in a comment", null);
    }

    private final boolean _skipYAMLComment() throws IOException {
        if (!isEnabled(Feature.ALLOW_YAML_COMMENTS)) {
            return false;
        }
        _skipLine();
        return true;
    }

    private final void _skipLine() throws IOException {
        int[] codes = CharTypes.getInputCodeComment();
        while (true) {
            if (this._inputPtr < this._inputEnd || _loadMore()) {
                byte[] bArr = this._inputBuffer;
                int i = this._inputPtr;
                this._inputPtr = i + 1;
                int i2 = bArr[i] & 255;
                int code = codes[i2];
                if (code != 0) {
                    switch (code) {
                        case 2:
                            _skipUtf8_2();
                            break;
                        case 3:
                            _skipUtf8_3();
                            break;
                        case 4:
                            _skipUtf8_4(i2);
                            break;
                        case 10:
                            this._currInputRow++;
                            this._currInputRowStart = this._inputPtr;
                            return;
                        case 13:
                            _skipCR();
                            return;
                        case 42:
                            break;
                        default:
                            if (code >= 0) {
                                break;
                            } else {
                                _reportInvalidChar(i2);
                                break;
                            }
                    }
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public char _decodeEscaped() throws IOException {
        if (this._inputPtr >= this._inputEnd && !_loadMore()) {
            _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte c = bArr[i];
        switch (c) {
            case 34:
            case 47:
            case 92:
                return (char) c;
            case 98:
                return 8;
            case 102:
                return 12;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            case LDSFile.EF_DG2_TAG /*117*/:
                int value = 0;
                for (int i2 = 0; i2 < 4; i2++) {
                    if (this._inputPtr >= this._inputEnd && !_loadMore()) {
                        _reportInvalidEOF(" in character escape sequence", JsonToken.VALUE_STRING);
                    }
                    byte[] bArr2 = this._inputBuffer;
                    int i3 = this._inputPtr;
                    this._inputPtr = i3 + 1;
                    byte ch = bArr2[i3];
                    int digit = CharTypes.charToHex(ch);
                    if (digit < 0) {
                        _reportUnexpectedChar(ch, "expected a hex-digit for character escape sequence");
                    }
                    value = (value << 4) | digit;
                }
                return (char) value;
            default:
                return _handleUnrecognizedCharacterEscape((char) _decodeCharForError(c));
        }
    }

    /* access modifiers changed from: protected */
    public int _decodeCharForError(int firstByte) throws IOException {
        int needed;
        int c = firstByte & 255;
        if (c <= 127) {
            return c;
        }
        if ((c & 224) == 192) {
            c &= 31;
            needed = 1;
        } else if ((c & 240) == 224) {
            c &= 15;
            needed = 2;
        } else if ((c & 248) == 240) {
            c &= 7;
            needed = 3;
        } else {
            _reportInvalidInitial(c & 255);
            needed = 1;
        }
        int d = nextByte();
        if ((d & 192) != 128) {
            _reportInvalidOther(d & 255);
        }
        int c2 = (c << 6) | (d & 63);
        if (needed <= 1) {
            return c2;
        }
        int d2 = nextByte();
        if ((d2 & 192) != 128) {
            _reportInvalidOther(d2 & 255);
        }
        int c3 = (c2 << 6) | (d2 & 63);
        if (needed <= 2) {
            return c3;
        }
        int d3 = nextByte();
        if ((d3 & 192) != 128) {
            _reportInvalidOther(d3 & 255);
        }
        return (c3 << 6) | (d3 & 63);
    }

    private final int _decodeUtf8_2(int c) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte d = bArr[i];
        if ((d & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d & 255, this._inputPtr);
        }
        return ((c & 31) << 6) | (d & 63);
    }

    private final int _decodeUtf8_3(int c1) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        int c12 = c1 & 15;
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte d = bArr[i];
        if ((d & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d & 255, this._inputPtr);
        }
        int c = (c12 << 6) | (d & 63);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte d2 = bArr2[i2];
        if ((d2 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d2 & 255, this._inputPtr);
        }
        return (c << 6) | (d2 & 63);
    }

    private final int _decodeUtf8_3fast(int c1) throws IOException {
        int c12 = c1 & 15;
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte d = bArr[i];
        if ((d & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d & 255, this._inputPtr);
        }
        int c = (c12 << 6) | (d & 63);
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte d2 = bArr2[i2];
        if ((d2 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d2 & 255, this._inputPtr);
        }
        return (c << 6) | (d2 & 63);
    }

    private final int _decodeUtf8_4(int c) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte d = bArr[i];
        if ((d & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d & 255, this._inputPtr);
        }
        int c2 = ((c & 7) << 6) | (d & 63);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte d2 = bArr2[i2];
        if ((d2 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d2 & 255, this._inputPtr);
        }
        int c3 = (c2 << 6) | (d2 & 63);
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte d3 = bArr3[i3];
        if ((d3 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d3 & 255, this._inputPtr);
        }
        return ((c3 << 6) | (d3 & 63)) - 65536;
    }

    private final void _skipUtf8_2() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte c = bArr[i];
        if ((c & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(c & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_3() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte c = bArr[i];
        if ((c & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(c & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte c2 = bArr2[i2];
        if ((c2 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(c2 & 255, this._inputPtr);
        }
    }

    private final void _skipUtf8_4(int c) throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        byte d = bArr[i];
        if ((d & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr2 = this._inputBuffer;
        int i2 = this._inputPtr;
        this._inputPtr = i2 + 1;
        byte d2 = bArr2[i2];
        if ((d2 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d2 & 255, this._inputPtr);
        }
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr3 = this._inputBuffer;
        int i3 = this._inputPtr;
        this._inputPtr = i3 + 1;
        byte d3 = bArr3[i3];
        if ((d3 & ISO7816.INS_GET_RESPONSE) != 128) {
            _reportInvalidOther(d3 & 255, this._inputPtr);
        }
    }

    /* access modifiers changed from: protected */
    public final void _skipCR() throws IOException {
        if ((this._inputPtr < this._inputEnd || _loadMore()) && this._inputBuffer[this._inputPtr] == 10) {
            this._inputPtr++;
        }
        this._currInputRow++;
        this._currInputRowStart = this._inputPtr;
    }

    private int nextByte() throws IOException {
        if (this._inputPtr >= this._inputEnd) {
            _loadMoreGuaranteed();
        }
        byte[] bArr = this._inputBuffer;
        int i = this._inputPtr;
        this._inputPtr = i + 1;
        return bArr[i] & 255;
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String matchedPart) throws IOException {
        _reportInvalidToken(matchedPart, "'null', 'true', 'false' or NaN");
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidToken(String matchedPart, String msg) throws IOException {
        StringBuilder sb = new StringBuilder(matchedPart);
        while (sb.length() < 256 && (this._inputPtr < this._inputEnd || _loadMore())) {
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            char c = (char) _decodeCharForError(bArr[i]);
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            sb.append(c);
        }
        if (sb.length() == 256) {
            sb.append("...");
        }
        _reportError("Unrecognized token '" + sb.toString() + "': was expecting " + msg);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidChar(int c) throws JsonParseException {
        if (c < 32) {
            _throwInvalidSpace(c);
        }
        _reportInvalidInitial(c);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidInitial(int mask) throws JsonParseException {
        _reportError("Invalid UTF-8 start byte 0x" + Integer.toHexString(mask));
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int mask) throws JsonParseException {
        _reportError("Invalid UTF-8 middle byte 0x" + Integer.toHexString(mask));
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidOther(int mask, int ptr) throws JsonParseException {
        this._inputPtr = ptr;
        _reportInvalidOther(mask);
    }

    public static int[] growArrayBy(int[] arr, int more) {
        if (arr == null) {
            return new int[more];
        }
        return Arrays.copyOf(arr, arr.length + more);
    }

    /* access modifiers changed from: protected */
    public final byte[] _decodeBase64(Base64Variant b64variant) throws IOException {
        ByteArrayBuilder builder = _getByteArrayBuilder();
        while (true) {
            if (this._inputPtr >= this._inputEnd) {
                _loadMoreGuaranteed();
            }
            byte[] bArr = this._inputBuffer;
            int i = this._inputPtr;
            this._inputPtr = i + 1;
            int ch = bArr[i] & 255;
            if (ch > 32) {
                int bits = b64variant.decodeBase64Char(ch);
                if (bits < 0) {
                    if (ch == 34) {
                        return builder.toByteArray();
                    }
                    bits = _decodeBase64Escape(b64variant, ch, 0);
                    if (bits < 0) {
                        continue;
                    }
                }
                int decodedData = bits;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr2 = this._inputBuffer;
                int i2 = this._inputPtr;
                this._inputPtr = i2 + 1;
                int ch2 = bArr2[i2] & 255;
                int bits2 = b64variant.decodeBase64Char(ch2);
                if (bits2 < 0) {
                    bits2 = _decodeBase64Escape(b64variant, ch2, 1);
                }
                int decodedData2 = (decodedData << 6) | bits2;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr3 = this._inputBuffer;
                int i3 = this._inputPtr;
                this._inputPtr = i3 + 1;
                int ch3 = bArr3[i3] & 255;
                int bits3 = b64variant.decodeBase64Char(ch3);
                if (bits3 < 0) {
                    if (bits3 != -2) {
                        if (ch3 != 34 || b64variant.usesPadding()) {
                            bits3 = _decodeBase64Escape(b64variant, ch3, 2);
                        } else {
                            builder.append(decodedData2 >> 4);
                            return builder.toByteArray();
                        }
                    }
                    if (bits3 == -2) {
                        if (this._inputPtr >= this._inputEnd) {
                            _loadMoreGuaranteed();
                        }
                        byte[] bArr4 = this._inputBuffer;
                        int i4 = this._inputPtr;
                        this._inputPtr = i4 + 1;
                        int ch4 = bArr4[i4] & 255;
                        if (!b64variant.usesPaddingChar(ch4)) {
                            throw reportInvalidBase64Char(b64variant, ch4, 3, "expected padding character '" + b64variant.getPaddingChar() + "'");
                        }
                        builder.append(decodedData2 >> 4);
                    }
                }
                int decodedData3 = (decodedData2 << 6) | bits3;
                if (this._inputPtr >= this._inputEnd) {
                    _loadMoreGuaranteed();
                }
                byte[] bArr5 = this._inputBuffer;
                int i5 = this._inputPtr;
                this._inputPtr = i5 + 1;
                int ch5 = bArr5[i5] & 255;
                int bits4 = b64variant.decodeBase64Char(ch5);
                if (bits4 < 0) {
                    if (bits4 != -2) {
                        if (ch5 != 34 || b64variant.usesPadding()) {
                            bits4 = _decodeBase64Escape(b64variant, ch5, 3);
                        } else {
                            builder.appendTwoBytes(decodedData3 >> 2);
                            return builder.toByteArray();
                        }
                    }
                    if (bits4 == -2) {
                        builder.appendTwoBytes(decodedData3 >> 2);
                    }
                }
                builder.appendThreeBytes((decodedData3 << 6) | bits4);
            }
        }
    }

    public JsonLocation getTokenLocation() {
        Object src = this._ioContext.getSourceReference();
        if (this._currToken == JsonToken.FIELD_NAME) {
            return new JsonLocation(src, this._currInputProcessed + ((long) (this._nameStartOffset - 1)), -1, this._nameStartRow, this._nameStartCol);
        }
        return new JsonLocation(src, this._tokenInputTotal - 1, -1, this._tokenInputRow, this._tokenInputCol);
    }

    public JsonLocation getCurrentLocation() {
        return new JsonLocation(this._ioContext.getSourceReference(), this._currInputProcessed + ((long) this._inputPtr), -1, this._currInputRow, (this._inputPtr - this._currInputRowStart) + 1);
    }

    private final void _updateLocation() {
        this._tokenInputRow = this._currInputRow;
        int ptr = this._inputPtr;
        this._tokenInputTotal = this._currInputProcessed + ((long) ptr);
        this._tokenInputCol = ptr - this._currInputRowStart;
    }

    private final void _updateNameLocation() {
        this._nameStartRow = this._currInputRow;
        int ptr = this._inputPtr;
        this._nameStartOffset = ptr;
        this._nameStartCol = ptr - this._currInputRowStart;
    }

    private static final int pad(int q, int bytes) {
        return bytes == 4 ? q : q | (-1 << (bytes << 3));
    }
}
