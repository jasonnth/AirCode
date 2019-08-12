package com.fasterxml.jackson.core.json;

import com.facebook.soloader.MinElf;
import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.p307io.CharTypes;
import com.fasterxml.jackson.core.p307io.CharacterEscapes;
import com.fasterxml.jackson.core.p307io.IOContext;
import com.fasterxml.jackson.core.p307io.NumberOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import net.p318sf.scuba.smartcards.ISO7816;
import org.spongycastle.asn1.eac.CertificateBody;

public class UTF8JsonGenerator extends JsonGeneratorImpl {
    private static final byte[] FALSE_BYTES = {102, 97, 108, 115, 101};
    private static final byte[] HEX_CHARS = CharTypes.copyHexBytes();
    private static final byte[] NULL_BYTES = {110, 117, 108, 108};
    private static final byte[] TRUE_BYTES = {116, 114, 117, 101};
    protected boolean _bufferRecyclable;
    protected char[] _charBuffer;
    protected final int _charBufferLength;
    protected byte[] _outputBuffer;
    protected final int _outputEnd;
    protected final int _outputMaxContiguous;
    protected final OutputStream _outputStream;
    protected int _outputTail;
    protected byte _quoteChar = ISO7816.INS_MSE;

    public UTF8JsonGenerator(IOContext ctxt, int features, ObjectCodec codec, OutputStream out) {
        super(ctxt, features, codec);
        this._outputStream = out;
        this._bufferRecyclable = true;
        this._outputBuffer = ctxt.allocWriteEncodingBuffer();
        this._outputEnd = this._outputBuffer.length;
        this._outputMaxContiguous = this._outputEnd >> 3;
        this._charBuffer = ctxt.allocConcatBuffer();
        this._charBufferLength = this._charBuffer.length;
        if (isEnabled(Feature.ESCAPE_NON_ASCII)) {
            setHighestNonEscapedChar(CertificateBody.profileType);
        }
    }

    public void writeFieldName(String name) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(name);
            return;
        }
        int status = this._writeContext.writeFieldName(name);
        if (status == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (status == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = ISO7816.INS_UNBLOCK_CHV;
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(name, false);
            return;
        }
        int len = name.length();
        if (len > this._charBufferLength) {
            _writeStringSegments(name, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
        if (len <= this._outputMaxContiguous) {
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(name, 0, len);
        } else {
            _writeStringSegments(name, 0, len);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr3[i3] = this._quoteChar;
    }

    public void writeFieldName(SerializableString name) throws IOException {
        if (this._cfgPrettyPrinter != null) {
            _writePPFieldName(name);
            return;
        }
        int status = this._writeContext.writeFieldName(name.getValue());
        if (status == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (status == 1) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = ISO7816.INS_UNBLOCK_CHV;
        }
        if (this._cfgUnqNames) {
            _writeUnq(name);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
        int len = name.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (len < 0) {
            _writeBytes(name.asQuotedUTF8());
        } else {
            this._outputTail += len;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr3 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr3[i3] = this._quoteChar;
    }

    private final void _writeUnq(SerializableString name) throws IOException {
        int len = name.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (len < 0) {
            _writeBytes(name.asQuotedUTF8());
        } else {
            this._outputTail += len;
        }
    }

    public final void writeStartArray() throws IOException {
        _verifyValueWrite("start an array");
        this._writeContext = this._writeContext.createChildArrayContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartArray(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 91;
    }

    public final void writeEndArray() throws IOException {
        if (!this._writeContext.inArray()) {
            _reportError("Current context not Array but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndArray(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 93;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    public final void writeStartObject() throws IOException {
        _verifyValueWrite("start an object");
        this._writeContext = this._writeContext.createChildObjectContext();
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    public void writeStartObject(Object forValue) throws IOException {
        _verifyValueWrite("start an object");
        JsonWriteContext ctxt = this._writeContext.createChildObjectContext();
        this._writeContext = ctxt;
        if (forValue != null) {
            ctxt.setCurrentValue(forValue);
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeStartObject(this);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = 123;
    }

    public final void writeEndObject() throws IOException {
        if (!this._writeContext.inObject()) {
            _reportError("Current context not Object but " + this._writeContext.typeDesc());
        }
        if (this._cfgPrettyPrinter != null) {
            this._cfgPrettyPrinter.writeEndObject(this, this._writeContext.getEntryCount());
        } else {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = 125;
        }
        this._writeContext = this._writeContext.clearAndGetParent();
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(String name) throws IOException {
        int status = this._writeContext.writeFieldName(name);
        if (status == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (status == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            _writeStringSegments(name, false);
            return;
        }
        int len = name.length();
        if (len > this._charBufferLength) {
            _writeStringSegments(name, true);
            return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        name.getChars(0, len, this._charBuffer, 0);
        if (len <= this._outputMaxContiguous) {
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(this._charBuffer, 0, len);
        } else {
            _writeStringSegments(this._charBuffer, 0, len);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    /* access modifiers changed from: protected */
    public final void _writePPFieldName(SerializableString name) throws IOException {
        boolean addQuotes = true;
        int status = this._writeContext.writeFieldName(name.getValue());
        if (status == 4) {
            _reportError("Can not write a field name, expecting a value");
        }
        if (status == 1) {
            this._cfgPrettyPrinter.writeObjectEntrySeparator(this);
        } else {
            this._cfgPrettyPrinter.beforeObjectEntries(this);
        }
        if (this._cfgUnqNames) {
            addQuotes = false;
        }
        if (addQuotes) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
        }
        _writeBytes(name.asQuotedUTF8());
        if (addQuotes) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
        }
    }

    public void writeString(String text) throws IOException {
        _verifyValueWrite("write a string");
        if (text == null) {
            _writeNull();
            return;
        }
        int len = text.length();
        if (len > this._outputMaxContiguous) {
            _writeStringSegments(text, true);
            return;
        }
        if (this._outputTail + len >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        _writeStringSegment(text, 0, len);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public void writeString(char[] text, int offset, int len) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        if (len <= this._outputMaxContiguous) {
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(text, offset, len);
        } else {
            _writeStringSegments(text, offset, len);
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public final void writeString(SerializableString text) throws IOException {
        _verifyValueWrite("write a string");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        int len = text.appendQuotedUTF8(this._outputBuffer, this._outputTail);
        if (len < 0) {
            _writeBytes(text.asQuotedUTF8());
        } else {
            this._outputTail += len;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public void writeRaw(String text) throws IOException {
        int len = text.length();
        char[] buf = this._charBuffer;
        if (len <= buf.length) {
            text.getChars(0, len, buf, 0);
            writeRaw(buf, 0, len);
            return;
        }
        writeRaw(text, 0, len);
    }

    public void writeRaw(String text, int offset, int len) throws IOException {
        char[] buf = this._charBuffer;
        int cbufLen = buf.length;
        if (len <= cbufLen) {
            text.getChars(offset, offset + len, buf, 0);
            writeRaw(buf, 0, len);
            return;
        }
        int maxChunk = Math.min(cbufLen, (this._outputEnd >> 2) + (this._outputEnd >> 4));
        int maxBytes = maxChunk * 3;
        while (len > 0) {
            int len2 = Math.min(maxChunk, len);
            text.getChars(offset, offset + len2, buf, 0);
            if (this._outputTail + maxBytes > this._outputEnd) {
                _flushBuffer();
            }
            if (len2 > 1) {
                char ch = buf[len2 - 1];
                if (ch >= 55296 && ch <= 56319) {
                    len2--;
                }
            }
            _writeRawSegment(buf, 0, len2);
            offset += len2;
            len -= len2;
        }
    }

    public void writeRaw(SerializableString text) throws IOException {
        byte[] raw = text.asUnquotedUTF8();
        if (raw.length > 0) {
            _writeBytes(raw);
        }
    }

    public void writeRawValue(SerializableString text) throws IOException {
        _verifyValueWrite("write a raw (unencoded) value");
        byte[] raw = text.asUnquotedUTF8();
        if (raw.length > 0) {
            _writeBytes(raw);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001f, code lost:
        r2 = r8 + 1;
        r0 = r7[r8];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0025, code lost:
        if (r0 >= 2048) goto L_0x0057;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        r3 = r6._outputBuffer;
        r4 = r6._outputTail;
        r6._outputTail = r4 + 1;
        r3[r4] = (byte) ((r0 >> 6) | 192);
        r3 = r6._outputBuffer;
        r4 = r6._outputTail;
        r6._outputTail = r4 + 1;
        r3[r4] = (byte) ((r0 & '?') | 128);
        r8 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0057, code lost:
        r8 = _outputRawMultiByteChar(r0, r7, r2, r9);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void writeRaw(char[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            int r3 = r9 + r9
            int r1 = r3 + r9
            int r3 = r6._outputTail
            int r3 = r3 + r1
            int r4 = r6._outputEnd
            if (r3 <= r4) goto L_0x0016
            int r3 = r6._outputEnd
            if (r3 >= r1) goto L_0x0013
            r6._writeSegmentedRaw(r7, r8, r9)
        L_0x0012:
            return
        L_0x0013:
            r6._flushBuffer()
        L_0x0016:
            int r9 = r9 + r8
        L_0x0017:
            if (r8 >= r9) goto L_0x0012
        L_0x0019:
            char r0 = r7[r8]
            r3 = 127(0x7f, float:1.78E-43)
            if (r0 <= r3) goto L_0x0047
            int r2 = r8 + 1
            char r0 = r7[r8]
            r3 = 2048(0x800, float:2.87E-42)
            if (r0 >= r3) goto L_0x0057
            byte[] r3 = r6._outputBuffer
            int r4 = r6._outputTail
            int r5 = r4 + 1
            r6._outputTail = r5
            int r5 = r0 >> 6
            r5 = r5 | 192(0xc0, float:2.69E-43)
            byte r5 = (byte) r5
            r3[r4] = r5
            byte[] r3 = r6._outputBuffer
            int r4 = r6._outputTail
            int r5 = r4 + 1
            r6._outputTail = r5
            r5 = r0 & 63
            r5 = r5 | 128(0x80, float:1.794E-43)
            byte r5 = (byte) r5
            r3[r4] = r5
            r8 = r2
            goto L_0x0017
        L_0x0047:
            byte[] r3 = r6._outputBuffer
            int r4 = r6._outputTail
            int r5 = r4 + 1
            r6._outputTail = r5
            byte r5 = (byte) r0
            r3[r4] = r5
            int r8 = r8 + 1
            if (r8 < r9) goto L_0x0019
            goto L_0x0012
        L_0x0057:
            int r8 = r6._outputRawMultiByteChar(r0, r7, r2, r9)
            goto L_0x0017
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator.writeRaw(char[], int, int):void");
    }

    public void writeRaw(char ch) throws IOException {
        if (this._outputTail + 3 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bbuf = this._outputBuffer;
        if (ch <= 127) {
            int i = this._outputTail;
            this._outputTail = i + 1;
            bbuf[i] = (byte) ch;
        } else if (ch < 2048) {
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bbuf[i2] = (byte) ((ch >> 6) | 192);
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bbuf[i3] = (byte) ((ch & '?') | 128);
        } else {
            _outputRawMultiByteChar(ch, null, 0, 0);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0052, code lost:
        r9 = _outputRawMultiByteChar(r1, r8, r4, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0014, code lost:
        if ((r7._outputTail + 3) < r7._outputEnd) goto L_0x0019;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0016, code lost:
        _flushBuffer();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0019, code lost:
        r4 = r9 + 1;
        r1 = r8[r9];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001f, code lost:
        if (r1 >= 2048) goto L_0x0052;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0021, code lost:
        r5 = r7._outputTail;
        r7._outputTail = r5 + 1;
        r0[r5] = (byte) ((r1 >> 6) | 192);
        r5 = r7._outputTail;
        r7._outputTail = r5 + 1;
        r0[r5] = (byte) ((r1 & '?') | 128);
        r9 = r4;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void _writeSegmentedRaw(char[] r8, int r9, int r10) throws java.io.IOException {
        /*
            r7 = this;
            int r2 = r7._outputEnd
            byte[] r0 = r7._outputBuffer
            int r3 = r9 + r10
        L_0x0006:
            if (r9 >= r3) goto L_0x0051
        L_0x0008:
            char r1 = r8[r9]
            r5 = 128(0x80, float:1.794E-43)
            if (r1 < r5) goto L_0x003d
            int r5 = r7._outputTail
            int r5 = r5 + 3
            int r6 = r7._outputEnd
            if (r5 < r6) goto L_0x0019
            r7._flushBuffer()
        L_0x0019:
            int r4 = r9 + 1
            char r1 = r8[r9]
            r5 = 2048(0x800, float:2.87E-42)
            if (r1 >= r5) goto L_0x0052
            int r5 = r7._outputTail
            int r6 = r5 + 1
            r7._outputTail = r6
            int r6 = r1 >> 6
            r6 = r6 | 192(0xc0, float:2.69E-43)
            byte r6 = (byte) r6
            r0[r5] = r6
            int r5 = r7._outputTail
            int r6 = r5 + 1
            r7._outputTail = r6
            r6 = r1 & 63
            r6 = r6 | 128(0x80, float:1.794E-43)
            byte r6 = (byte) r6
            r0[r5] = r6
            r9 = r4
            goto L_0x0006
        L_0x003d:
            int r5 = r7._outputTail
            if (r5 < r2) goto L_0x0044
            r7._flushBuffer()
        L_0x0044:
            int r5 = r7._outputTail
            int r6 = r5 + 1
            r7._outputTail = r6
            byte r6 = (byte) r1
            r0[r5] = r6
            int r9 = r9 + 1
            if (r9 < r3) goto L_0x0008
        L_0x0051:
            return
        L_0x0052:
            int r9 = r7._outputRawMultiByteChar(r1, r8, r4, r3)
            goto L_0x0006
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeSegmentedRaw(char[], int, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0008, code lost:
        r1 = r7 + 1;
        r0 = r6[r7];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        if (r0 >= 2048) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0010, code lost:
        r2 = r5._outputBuffer;
        r3 = r5._outputTail;
        r5._outputTail = r3 + 1;
        r2[r3] = (byte) ((r0 >> 6) | 192);
        r2 = r5._outputBuffer;
        r3 = r5._outputTail;
        r5._outputTail = r3 + 1;
        r2[r3] = (byte) ((r0 & '?') | 128);
        r7 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0040, code lost:
        r7 = _outputRawMultiByteChar(r0, r6, r1, r8);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void _writeRawSegment(char[] r6, int r7, int r8) throws java.io.IOException {
        /*
            r5 = this;
        L_0x0000:
            if (r7 >= r8) goto L_0x003f
        L_0x0002:
            char r0 = r6[r7]
            r2 = 127(0x7f, float:1.78E-43)
            if (r0 <= r2) goto L_0x0030
            int r1 = r7 + 1
            char r0 = r6[r7]
            r2 = 2048(0x800, float:2.87E-42)
            if (r0 >= r2) goto L_0x0040
            byte[] r2 = r5._outputBuffer
            int r3 = r5._outputTail
            int r4 = r3 + 1
            r5._outputTail = r4
            int r4 = r0 >> 6
            r4 = r4 | 192(0xc0, float:2.69E-43)
            byte r4 = (byte) r4
            r2[r3] = r4
            byte[] r2 = r5._outputBuffer
            int r3 = r5._outputTail
            int r4 = r3 + 1
            r5._outputTail = r4
            r4 = r0 & 63
            r4 = r4 | 128(0x80, float:1.794E-43)
            byte r4 = (byte) r4
            r2[r3] = r4
            r7 = r1
            goto L_0x0000
        L_0x0030:
            byte[] r2 = r5._outputBuffer
            int r3 = r5._outputTail
            int r4 = r3 + 1
            r5._outputTail = r4
            byte r4 = (byte) r0
            r2[r3] = r4
            int r7 = r7 + 1
            if (r7 < r8) goto L_0x0002
        L_0x003f:
            return
        L_0x0040:
            int r7 = r5._outputRawMultiByteChar(r0, r6, r1, r8)
            goto L_0x0000
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.json.UTF8JsonGenerator._writeRawSegment(char[], int, int):void");
    }

    public void writeBinary(Base64Variant b64variant, byte[] data, int offset, int len) throws IOException, JsonGenerationException {
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        _writeBinary(b64variant, data, offset, offset + len);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException, JsonGenerationException {
        int bytes;
        _verifyValueWrite("write a binary value");
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        byte[] encodingBuffer = this._ioContext.allocBase64Buffer();
        if (dataLength < 0) {
            try {
                bytes = _writeBinary(b64variant, data, encodingBuffer);
            } catch (Throwable th) {
                this._ioContext.releaseBase64Buffer(encodingBuffer);
                throw th;
            }
        } else {
            int missing = _writeBinary(b64variant, data, encodingBuffer, dataLength);
            if (missing > 0) {
                _reportError("Too few bytes available: missing " + missing + " bytes (out of " + dataLength + ")");
            }
            bytes = dataLength;
        }
        this._ioContext.releaseBase64Buffer(encodingBuffer);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
        return bytes;
    }

    public void writeNumber(short s) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 6 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedShort(s);
        } else {
            this._outputTail = NumberOutput.outputInt((int) s, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedShort(short s) throws IOException {
        if (this._outputTail + 8 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt((int) s, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public void writeNumber(int i) throws IOException {
        _verifyValueWrite("write a number");
        if (this._outputTail + 11 >= this._outputEnd) {
            _flushBuffer();
        }
        if (this._cfgNumbersAsStrings) {
            _writeQuotedInt(i);
        } else {
            this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        }
    }

    private final void _writeQuotedInt(int i) throws IOException {
        if (this._outputTail + 13 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr[i2] = this._quoteChar;
        this._outputTail = NumberOutput.outputInt(i, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bArr2[i3] = this._quoteChar;
    }

    public void writeNumber(long l) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedLong(l);
            return;
        }
        if (this._outputTail + 21 >= this._outputEnd) {
            _flushBuffer();
        }
        this._outputTail = NumberOutput.outputLong(l, this._outputBuffer, this._outputTail);
    }

    private final void _writeQuotedLong(long l) throws IOException {
        if (this._outputTail + 23 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        this._outputTail = NumberOutput.outputLong(l, this._outputBuffer, this._outputTail);
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public void writeNumber(BigInteger value) throws IOException {
        _verifyValueWrite("write a number");
        if (value == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(value.toString());
        } else {
            writeRaw(value.toString());
        }
    }

    public void writeNumber(double d) throws IOException {
        if (this._cfgNumbersAsStrings || ((Double.isNaN(d) || Double.isInfinite(d)) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(d));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(String.valueOf(d));
    }

    public void writeNumber(float f) throws IOException {
        if (this._cfgNumbersAsStrings || ((Float.isNaN(f) || Float.isInfinite(f)) && Feature.QUOTE_NON_NUMERIC_NUMBERS.enabledIn(this._features))) {
            writeString(String.valueOf(f));
            return;
        }
        _verifyValueWrite("write a number");
        writeRaw(String.valueOf(f));
    }

    public void writeNumber(BigDecimal value) throws IOException {
        _verifyValueWrite("write a number");
        if (value == null) {
            _writeNull();
        } else if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(_asString(value));
        } else {
            writeRaw(_asString(value));
        }
    }

    public void writeNumber(String encodedValue) throws IOException {
        _verifyValueWrite("write a number");
        if (this._cfgNumbersAsStrings) {
            _writeQuotedRaw(encodedValue);
        } else {
            writeRaw(encodedValue);
        }
    }

    private final void _writeQuotedRaw(String value) throws IOException {
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = this._quoteChar;
        writeRaw(value);
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr2 = this._outputBuffer;
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bArr2[i2] = this._quoteChar;
    }

    public void writeBoolean(boolean state) throws IOException {
        _verifyValueWrite("write a boolean value");
        if (this._outputTail + 5 >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] keyword = state ? TRUE_BYTES : FALSE_BYTES;
        int len = keyword.length;
        System.arraycopy(keyword, 0, this._outputBuffer, this._outputTail, len);
        this._outputTail += len;
    }

    public void writeNull() throws IOException {
        _verifyValueWrite("write a null");
        _writeNull();
    }

    /* access modifiers changed from: protected */
    public final void _verifyValueWrite(String typeMsg) throws IOException {
        byte b;
        int status = this._writeContext.writeValue();
        if (this._cfgPrettyPrinter != null) {
            _verifyPrettyValueWrite(typeMsg, status);
            return;
        }
        switch (status) {
            case 1:
                b = ISO7816.INS_UNBLOCK_CHV;
                break;
            case 2:
                b = 58;
                break;
            case 3:
                if (this._rootValueSeparator != null) {
                    byte[] raw = this._rootValueSeparator.asUnquotedUTF8();
                    if (raw.length > 0) {
                        _writeBytes(raw);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                _reportCantWriteValueExpectName(typeMsg);
                return;
            default:
                return;
        }
        if (this._outputTail >= this._outputEnd) {
            _flushBuffer();
        }
        byte[] bArr = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bArr[i] = b;
    }

    public void flush() throws IOException {
        _flushBuffer();
        if (this._outputStream != null && isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
            this._outputStream.flush();
        }
    }

    public void close() throws IOException {
        super.close();
        if (this._outputBuffer != null && isEnabled(Feature.AUTO_CLOSE_JSON_CONTENT)) {
            while (true) {
                JsonStreamContext ctxt = getOutputContext();
                if (!ctxt.inArray()) {
                    if (!ctxt.inObject()) {
                        break;
                    }
                    writeEndObject();
                } else {
                    writeEndArray();
                }
            }
        }
        _flushBuffer();
        this._outputTail = 0;
        if (this._outputStream != null) {
            if (this._ioContext.isResourceManaged() || isEnabled(Feature.AUTO_CLOSE_TARGET)) {
                this._outputStream.close();
            } else if (isEnabled(Feature.FLUSH_PASSED_TO_STREAM)) {
                this._outputStream.flush();
            }
        }
        _releaseBuffers();
    }

    /* access modifiers changed from: protected */
    public void _releaseBuffers() {
        byte[] buf = this._outputBuffer;
        if (buf != null && this._bufferRecyclable) {
            this._outputBuffer = null;
            this._ioContext.releaseWriteEncodingBuffer(buf);
        }
        char[] cbuf = this._charBuffer;
        if (cbuf != null) {
            this._charBuffer = null;
            this._ioContext.releaseConcatBuffer(cbuf);
        }
    }

    private final void _writeBytes(byte[] bytes) throws IOException {
        int len = bytes.length;
        if (this._outputTail + len > this._outputEnd) {
            _flushBuffer();
            if (len > 512) {
                this._outputStream.write(bytes, 0, len);
                return;
            }
        }
        System.arraycopy(bytes, 0, this._outputBuffer, this._outputTail, len);
        this._outputTail += len;
    }

    private final void _writeStringSegments(String text, boolean addQuotes) throws IOException {
        if (addQuotes) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bArr[i] = this._quoteChar;
        }
        int left = text.length();
        int offset = 0;
        while (left > 0) {
            int len = Math.min(this._outputMaxContiguous, left);
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(text, offset, len);
            offset += len;
            left -= len;
        }
        if (addQuotes) {
            if (this._outputTail >= this._outputEnd) {
                _flushBuffer();
            }
            byte[] bArr2 = this._outputBuffer;
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bArr2[i2] = this._quoteChar;
        }
    }

    private final void _writeStringSegments(char[] cbuf, int offset, int totalLen) throws IOException {
        do {
            int len = Math.min(this._outputMaxContiguous, totalLen);
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(cbuf, offset, len);
            offset += len;
            totalLen -= len;
        } while (totalLen > 0);
    }

    private final void _writeStringSegments(String text, int offset, int totalLen) throws IOException {
        do {
            int len = Math.min(this._outputMaxContiguous, totalLen);
            if (this._outputTail + len > this._outputEnd) {
                _flushBuffer();
            }
            _writeStringSegment(text, offset, len);
            offset += len;
            totalLen -= len;
        } while (totalLen > 0);
    }

    private final void _writeStringSegment(char[] cbuf, int offset, int len) throws IOException {
        int len2 = len + offset;
        int outputPtr = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int outputPtr2 = outputPtr;
        while (offset < len2) {
            char ch = cbuf[offset];
            if (ch > 127 || escCodes[ch] != 0) {
                break;
            }
            int outputPtr3 = outputPtr2 + 1;
            outputBuffer[outputPtr2] = (byte) ch;
            offset++;
            outputPtr2 = outputPtr3;
        }
        this._outputTail = outputPtr2;
        if (offset >= len2) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(cbuf, offset, len2);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(cbuf, offset, len2);
        } else {
            _writeStringSegmentASCII2(cbuf, offset, len2);
        }
    }

    private final void _writeStringSegment(String text, int offset, int len) throws IOException {
        int len2 = len + offset;
        int outputPtr = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int outputPtr2 = outputPtr;
        while (offset < len2) {
            int ch = text.charAt(offset);
            if (ch > 127 || escCodes[ch] != 0) {
                break;
            }
            int outputPtr3 = outputPtr2 + 1;
            outputBuffer[outputPtr2] = (byte) ch;
            offset++;
            outputPtr2 = outputPtr3;
        }
        this._outputTail = outputPtr2;
        if (offset >= len2) {
            return;
        }
        if (this._characterEscapes != null) {
            _writeCustomStringSegment2(text, offset, len2);
        } else if (this._maximumNonEscapedChar == 0) {
            _writeStringSegment2(text, offset, len2);
        } else {
            _writeStringSegmentASCII2(text, offset, len2);
        }
    }

    private final void _writeStringSegment2(char[] cbuf, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            char ch = cbuf[offset2];
            if (ch > 127) {
                if (ch <= 2047) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                    int outputPtr5 = outputPtr4 + 1;
                    outputBuffer[outputPtr4] = (byte) ((ch & '?') | 128);
                    outputPtr = outputPtr5;
                } else {
                    outputPtr = _outputMultiByteChar(ch, outputPtr3);
                }
                outputPtr3 = outputPtr;
                offset2 = offset3;
            } else if (escCodes[ch] == 0) {
                int outputPtr6 = outputPtr3 + 1;
                outputBuffer[outputPtr3] = (byte) ch;
                outputPtr3 = outputPtr6;
                offset2 = offset3;
            } else {
                int escape = escCodes[ch];
                if (escape > 0) {
                    int outputPtr7 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = 92;
                    outputPtr3 = outputPtr7 + 1;
                    outputBuffer[outputPtr7] = (byte) escape;
                    offset2 = offset3;
                } else {
                    outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                    offset2 = offset3;
                }
            }
        }
        this._outputTail = outputPtr3;
    }

    private final void _writeStringSegment2(String text, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            int ch = text.charAt(offset2);
            if (ch > 127) {
                if (ch <= 2047) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                    int outputPtr5 = outputPtr4 + 1;
                    outputBuffer[outputPtr4] = (byte) ((ch & 63) | 128);
                    outputPtr = outputPtr5;
                } else {
                    outputPtr = _outputMultiByteChar(ch, outputPtr3);
                }
                outputPtr3 = outputPtr;
                offset2 = offset3;
            } else if (escCodes[ch] == 0) {
                int outputPtr6 = outputPtr3 + 1;
                outputBuffer[outputPtr3] = (byte) ch;
                outputPtr3 = outputPtr6;
                offset2 = offset3;
            } else {
                int escape = escCodes[ch];
                if (escape > 0) {
                    int outputPtr7 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = 92;
                    outputPtr3 = outputPtr7 + 1;
                    outputBuffer[outputPtr7] = (byte) escape;
                    offset2 = offset3;
                } else {
                    outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                    offset2 = offset3;
                }
            }
        }
        this._outputTail = outputPtr3;
    }

    private final void _writeStringSegmentASCII2(char[] cbuf, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int maxUnescaped = this._maximumNonEscapedChar;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            char ch = cbuf[offset2];
            if (ch <= 127) {
                if (escCodes[ch] == 0) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ch;
                    outputPtr3 = outputPtr4;
                    offset2 = offset3;
                } else {
                    int escape = escCodes[ch];
                    if (escape > 0) {
                        int outputPtr5 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = 92;
                        outputPtr3 = outputPtr5 + 1;
                        outputBuffer[outputPtr5] = (byte) escape;
                        offset2 = offset3;
                    } else {
                        outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                        offset2 = offset3;
                    }
                }
            } else if (ch > maxUnescaped) {
                outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                offset2 = offset3;
            } else {
                if (ch <= 2047) {
                    int outputPtr6 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                    int outputPtr7 = outputPtr6 + 1;
                    outputBuffer[outputPtr6] = (byte) ((ch & '?') | 128);
                    outputPtr = outputPtr7;
                } else {
                    outputPtr = _outputMultiByteChar(ch, outputPtr3);
                }
                outputPtr3 = outputPtr;
                offset2 = offset3;
            }
        }
        this._outputTail = outputPtr3;
    }

    private final void _writeStringSegmentASCII2(String text, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int maxUnescaped = this._maximumNonEscapedChar;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            int ch = text.charAt(offset2);
            if (ch <= 127) {
                if (escCodes[ch] == 0) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ch;
                    outputPtr3 = outputPtr4;
                    offset2 = offset3;
                } else {
                    int escape = escCodes[ch];
                    if (escape > 0) {
                        int outputPtr5 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = 92;
                        outputPtr3 = outputPtr5 + 1;
                        outputBuffer[outputPtr5] = (byte) escape;
                        offset2 = offset3;
                    } else {
                        outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                        offset2 = offset3;
                    }
                }
            } else if (ch > maxUnescaped) {
                outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                offset2 = offset3;
            } else {
                if (ch <= 2047) {
                    int outputPtr6 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                    int outputPtr7 = outputPtr6 + 1;
                    outputBuffer[outputPtr6] = (byte) ((ch & 63) | 128);
                    outputPtr = outputPtr7;
                } else {
                    outputPtr = _outputMultiByteChar(ch, outputPtr3);
                }
                outputPtr3 = outputPtr;
                offset2 = offset3;
            }
        }
        this._outputTail = outputPtr3;
    }

    private final void _writeCustomStringSegment2(char[] cbuf, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int maxUnescaped = this._maximumNonEscapedChar <= 0 ? MinElf.PN_XNUM : this._maximumNonEscapedChar;
        CharacterEscapes customEscapes = this._characterEscapes;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            char ch = cbuf[offset2];
            if (ch <= 127) {
                if (escCodes[ch] == 0) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ch;
                    outputPtr3 = outputPtr4;
                    offset2 = offset3;
                } else {
                    int escape = escCodes[ch];
                    if (escape > 0) {
                        int outputPtr5 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = 92;
                        outputPtr3 = outputPtr5 + 1;
                        outputBuffer[outputPtr5] = (byte) escape;
                        offset2 = offset3;
                    } else if (escape == -2) {
                        SerializableString esc = customEscapes.getEscapeSequence(ch);
                        if (esc == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(ch) + ", although was supposed to have one");
                        }
                        outputPtr3 = _writeCustomEscape(outputBuffer, outputPtr3, esc, end - offset3);
                        offset2 = offset3;
                    } else {
                        outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                        offset2 = offset3;
                    }
                }
            } else if (ch > maxUnescaped) {
                outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                offset2 = offset3;
            } else {
                SerializableString esc2 = customEscapes.getEscapeSequence(ch);
                if (esc2 != null) {
                    outputPtr3 = _writeCustomEscape(outputBuffer, outputPtr3, esc2, end - offset3);
                    offset2 = offset3;
                } else {
                    if (ch <= 2047) {
                        int outputPtr6 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                        int outputPtr7 = outputPtr6 + 1;
                        outputBuffer[outputPtr6] = (byte) ((ch & '?') | 128);
                        outputPtr = outputPtr7;
                    } else {
                        outputPtr = _outputMultiByteChar(ch, outputPtr3);
                    }
                    outputPtr3 = outputPtr;
                    offset2 = offset3;
                }
            }
        }
        this._outputTail = outputPtr3;
    }

    private final void _writeCustomStringSegment2(String text, int offset, int end) throws IOException {
        int outputPtr;
        if (this._outputTail + ((end - offset) * 6) > this._outputEnd) {
            _flushBuffer();
        }
        int outputPtr2 = this._outputTail;
        byte[] outputBuffer = this._outputBuffer;
        int[] escCodes = this._outputEscapes;
        int maxUnescaped = this._maximumNonEscapedChar <= 0 ? MinElf.PN_XNUM : this._maximumNonEscapedChar;
        CharacterEscapes customEscapes = this._characterEscapes;
        int outputPtr3 = outputPtr2;
        int offset2 = offset;
        while (offset2 < end) {
            int offset3 = offset2 + 1;
            int ch = text.charAt(offset2);
            if (ch <= 127) {
                if (escCodes[ch] == 0) {
                    int outputPtr4 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) ch;
                    outputPtr3 = outputPtr4;
                    offset2 = offset3;
                } else {
                    int escape = escCodes[ch];
                    if (escape > 0) {
                        int outputPtr5 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = 92;
                        outputPtr3 = outputPtr5 + 1;
                        outputBuffer[outputPtr5] = (byte) escape;
                        offset2 = offset3;
                    } else if (escape == -2) {
                        SerializableString esc = customEscapes.getEscapeSequence(ch);
                        if (esc == null) {
                            _reportError("Invalid custom escape definitions; custom escape not found for character code 0x" + Integer.toHexString(ch) + ", although was supposed to have one");
                        }
                        outputPtr3 = _writeCustomEscape(outputBuffer, outputPtr3, esc, end - offset3);
                        offset2 = offset3;
                    } else {
                        outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                        offset2 = offset3;
                    }
                }
            } else if (ch > maxUnescaped) {
                outputPtr3 = _writeGenericEscape(ch, outputPtr3);
                offset2 = offset3;
            } else {
                SerializableString esc2 = customEscapes.getEscapeSequence(ch);
                if (esc2 != null) {
                    outputPtr3 = _writeCustomEscape(outputBuffer, outputPtr3, esc2, end - offset3);
                    offset2 = offset3;
                } else {
                    if (ch <= 2047) {
                        int outputPtr6 = outputPtr3 + 1;
                        outputBuffer[outputPtr3] = (byte) ((ch >> 6) | 192);
                        int outputPtr7 = outputPtr6 + 1;
                        outputBuffer[outputPtr6] = (byte) ((ch & 63) | 128);
                        outputPtr = outputPtr7;
                    } else {
                        outputPtr = _outputMultiByteChar(ch, outputPtr3);
                    }
                    outputPtr3 = outputPtr;
                    offset2 = offset3;
                }
            }
        }
        this._outputTail = outputPtr3;
    }

    private final int _writeCustomEscape(byte[] outputBuffer, int outputPtr, SerializableString esc, int remainingChars) throws IOException, JsonGenerationException {
        byte[] raw = esc.asUnquotedUTF8();
        int len = raw.length;
        if (len > 6) {
            return _handleLongCustomEscape(outputBuffer, outputPtr, this._outputEnd, raw, remainingChars);
        }
        System.arraycopy(raw, 0, outputBuffer, outputPtr, len);
        return outputPtr + len;
    }

    private final int _handleLongCustomEscape(byte[] outputBuffer, int outputPtr, int outputEnd, byte[] raw, int remainingChars) throws IOException, JsonGenerationException {
        int len = raw.length;
        if (outputPtr + len > outputEnd) {
            this._outputTail = outputPtr;
            _flushBuffer();
            int outputPtr2 = this._outputTail;
            if (len > outputBuffer.length) {
                this._outputStream.write(raw, 0, len);
                return outputPtr2;
            }
            System.arraycopy(raw, 0, outputBuffer, outputPtr2, len);
            outputPtr = outputPtr2 + len;
        }
        if ((remainingChars * 6) + outputPtr <= outputEnd) {
            return outputPtr;
        }
        _flushBuffer();
        return this._outputTail;
    }

    /* access modifiers changed from: protected */
    public final void _writeBinary(Base64Variant b64variant, byte[] input, int inputPtr, int inputEnd) throws IOException, JsonGenerationException {
        int safeInputEnd = inputEnd - 3;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        int inputPtr2 = inputPtr;
        while (inputPtr2 <= safeInputEnd) {
            if (this._outputTail > safeOutputEnd) {
                _flushBuffer();
            }
            int inputPtr3 = inputPtr2 + 1;
            int inputPtr4 = inputPtr3 + 1;
            int inputPtr5 = inputPtr4 + 1;
            this._outputTail = b64variant.encodeBase64Chunk((((input[inputPtr2] << 8) | (input[inputPtr3] & 255)) << 8) | (input[inputPtr4] & 255), this._outputBuffer, this._outputTail);
            chunksBeforeLF--;
            if (chunksBeforeLF <= 0) {
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 92;
                byte[] bArr2 = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr2[i2] = 110;
                chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
            }
            inputPtr2 = inputPtr5;
        }
        int inputLeft = inputEnd - inputPtr2;
        if (inputLeft > 0) {
            if (this._outputTail > safeOutputEnd) {
                _flushBuffer();
            }
            int inputPtr6 = inputPtr2 + 1;
            int b24 = input[inputPtr2] << 16;
            if (inputLeft == 2) {
                b24 |= (input[inputPtr6] & 255) << 8;
                int i3 = inputPtr6 + 1;
            }
            this._outputTail = b64variant.encodeBase64Partial(b24, inputLeft, this._outputBuffer, this._outputTail);
            return;
        }
    }

    /* access modifiers changed from: protected */
    public final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer, int bytesLeft) throws IOException, JsonGenerationException {
        int amount;
        int inputPtr = 0;
        int inputEnd = 0;
        int lastFullOffset = -3;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        while (bytesLeft > 2) {
            if (inputPtr > lastFullOffset) {
                inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
                inputPtr = 0;
                if (inputEnd < 3) {
                    break;
                }
                lastFullOffset = inputEnd - 3;
            }
            if (this._outputTail > safeOutputEnd) {
                _flushBuffer();
            }
            int inputPtr2 = inputPtr + 1;
            int inputPtr3 = inputPtr2 + 1;
            int inputPtr4 = inputPtr3 + 1;
            bytesLeft -= 3;
            this._outputTail = b64variant.encodeBase64Chunk((((readBuffer[inputPtr] << 8) | (readBuffer[inputPtr2] & 255)) << 8) | (readBuffer[inputPtr3] & 255), this._outputBuffer, this._outputTail);
            chunksBeforeLF--;
            if (chunksBeforeLF <= 0) {
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 92;
                byte[] bArr2 = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr2[i2] = 110;
                chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
            }
            inputPtr = inputPtr4;
        }
        if (bytesLeft <= 0) {
            return bytesLeft;
        }
        int inputEnd2 = _readMore(data, readBuffer, inputPtr, inputEnd, bytesLeft);
        if (inputEnd2 <= 0) {
            return bytesLeft;
        }
        if (this._outputTail > safeOutputEnd) {
            _flushBuffer();
        }
        int inputPtr5 = 0 + 1;
        int b24 = readBuffer[0] << 16;
        if (inputPtr5 < inputEnd2) {
            b24 |= (readBuffer[inputPtr5] & 255) << 8;
            amount = 2;
        } else {
            amount = 1;
        }
        this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
        int i3 = inputPtr5;
        return bytesLeft - amount;
    }

    /* access modifiers changed from: protected */
    public final int _writeBinary(Base64Variant b64variant, InputStream data, byte[] readBuffer) throws IOException, JsonGenerationException {
        int inputPtr = 0;
        int inputEnd = 0;
        int lastFullOffset = -3;
        int bytesDone = 0;
        int safeOutputEnd = this._outputEnd - 6;
        int chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
        while (true) {
            if (inputPtr > lastFullOffset) {
                inputEnd = _readMore(data, readBuffer, inputPtr, inputEnd, readBuffer.length);
                inputPtr = 0;
                if (inputEnd < 3) {
                    break;
                }
                lastFullOffset = inputEnd - 3;
            }
            if (this._outputTail > safeOutputEnd) {
                _flushBuffer();
            }
            int inputPtr2 = inputPtr + 1;
            int inputPtr3 = inputPtr2 + 1;
            int inputPtr4 = inputPtr3 + 1;
            bytesDone += 3;
            this._outputTail = b64variant.encodeBase64Chunk((((readBuffer[inputPtr] << 8) | (readBuffer[inputPtr2] & 255)) << 8) | (readBuffer[inputPtr3] & 255), this._outputBuffer, this._outputTail);
            chunksBeforeLF--;
            if (chunksBeforeLF <= 0) {
                byte[] bArr = this._outputBuffer;
                int i = this._outputTail;
                this._outputTail = i + 1;
                bArr[i] = 92;
                byte[] bArr2 = this._outputBuffer;
                int i2 = this._outputTail;
                this._outputTail = i2 + 1;
                bArr2[i2] = 110;
                chunksBeforeLF = b64variant.getMaxLineLength() >> 2;
            }
            inputPtr = inputPtr4;
        }
        if (0 >= inputEnd) {
            return bytesDone;
        }
        if (this._outputTail > safeOutputEnd) {
            _flushBuffer();
        }
        int inputPtr5 = 0 + 1;
        int b24 = readBuffer[0] << 16;
        int amount = 1;
        if (inputPtr5 < inputEnd) {
            b24 |= (readBuffer[inputPtr5] & 255) << 8;
            amount = 2;
        }
        int bytesDone2 = bytesDone + amount;
        this._outputTail = b64variant.encodeBase64Partial(b24, amount, this._outputBuffer, this._outputTail);
        int i3 = inputPtr5;
        return bytesDone2;
    }

    private final int _readMore(InputStream in, byte[] readBuffer, int inputPtr, int inputEnd, int maxRead) throws IOException {
        int i = 0;
        int inputPtr2 = inputPtr;
        while (inputPtr2 < inputEnd) {
            int i2 = i + 1;
            int inputPtr3 = inputPtr2 + 1;
            readBuffer[i] = readBuffer[inputPtr2];
            i = i2;
            inputPtr2 = inputPtr3;
        }
        int inputEnd2 = i;
        int maxRead2 = Math.min(maxRead, readBuffer.length);
        do {
            int length = maxRead2 - inputEnd2;
            if (length == 0) {
                break;
            }
            int count = in.read(readBuffer, inputEnd2, length);
            if (count < 0) {
                return inputEnd2;
            }
            inputEnd2 += count;
        } while (inputEnd2 < 3);
        return inputEnd2;
    }

    private final int _outputRawMultiByteChar(int ch, char[] cbuf, int inputOffset, int inputEnd) throws IOException {
        if (ch < 55296 || ch > 57343) {
            byte[] bbuf = this._outputBuffer;
            int i = this._outputTail;
            this._outputTail = i + 1;
            bbuf[i] = (byte) ((ch >> 12) | 224);
            int i2 = this._outputTail;
            this._outputTail = i2 + 1;
            bbuf[i2] = (byte) (((ch >> 6) & 63) | 128);
            int i3 = this._outputTail;
            this._outputTail = i3 + 1;
            bbuf[i3] = (byte) ((ch & 63) | 128);
            return inputOffset;
        }
        if (inputOffset >= inputEnd || cbuf == null) {
            _reportError(String.format("Split surrogate on writeRaw() input (last character): first character 0x%4x", new Object[]{Integer.valueOf(ch)}));
        }
        _outputSurrogates(ch, cbuf[inputOffset]);
        return inputOffset + 1;
    }

    /* access modifiers changed from: protected */
    public final void _outputSurrogates(int surr1, int surr2) throws IOException {
        int c = _decodeSurrogate(surr1, surr2);
        if (this._outputTail + 4 > this._outputEnd) {
            _flushBuffer();
        }
        byte[] bbuf = this._outputBuffer;
        int i = this._outputTail;
        this._outputTail = i + 1;
        bbuf[i] = (byte) ((c >> 18) | 240);
        int i2 = this._outputTail;
        this._outputTail = i2 + 1;
        bbuf[i2] = (byte) (((c >> 12) & 63) | 128);
        int i3 = this._outputTail;
        this._outputTail = i3 + 1;
        bbuf[i3] = (byte) (((c >> 6) & 63) | 128);
        int i4 = this._outputTail;
        this._outputTail = i4 + 1;
        bbuf[i4] = (byte) ((c & 63) | 128);
    }

    private final int _outputMultiByteChar(int ch, int outputPtr) throws IOException {
        byte[] bbuf = this._outputBuffer;
        if (ch < 55296 || ch > 57343) {
            int outputPtr2 = outputPtr + 1;
            bbuf[outputPtr] = (byte) ((ch >> 12) | 224);
            int outputPtr3 = outputPtr2 + 1;
            bbuf[outputPtr2] = (byte) (((ch >> 6) & 63) | 128);
            int outputPtr4 = outputPtr3 + 1;
            bbuf[outputPtr3] = (byte) ((ch & 63) | 128);
            return outputPtr4;
        }
        int outputPtr5 = outputPtr + 1;
        bbuf[outputPtr] = 92;
        int outputPtr6 = outputPtr5 + 1;
        bbuf[outputPtr5] = 117;
        int outputPtr7 = outputPtr6 + 1;
        bbuf[outputPtr6] = HEX_CHARS[(ch >> 12) & 15];
        int outputPtr8 = outputPtr7 + 1;
        bbuf[outputPtr7] = HEX_CHARS[(ch >> 8) & 15];
        int outputPtr9 = outputPtr8 + 1;
        bbuf[outputPtr8] = HEX_CHARS[(ch >> 4) & 15];
        int outputPtr10 = outputPtr9 + 1;
        bbuf[outputPtr9] = HEX_CHARS[ch & 15];
        return outputPtr10;
    }

    private final void _writeNull() throws IOException {
        if (this._outputTail + 4 >= this._outputEnd) {
            _flushBuffer();
        }
        System.arraycopy(NULL_BYTES, 0, this._outputBuffer, this._outputTail, 4);
        this._outputTail += 4;
    }

    private int _writeGenericEscape(int charToEscape, int outputPtr) throws IOException {
        int outputPtr2;
        byte[] bbuf = this._outputBuffer;
        int outputPtr3 = outputPtr + 1;
        bbuf[outputPtr] = 92;
        int outputPtr4 = outputPtr3 + 1;
        bbuf[outputPtr3] = 117;
        if (charToEscape > 255) {
            int hi = (charToEscape >> 8) & 255;
            int outputPtr5 = outputPtr4 + 1;
            bbuf[outputPtr4] = HEX_CHARS[hi >> 4];
            outputPtr2 = outputPtr5 + 1;
            bbuf[outputPtr5] = HEX_CHARS[hi & 15];
            charToEscape &= 255;
        } else {
            int outputPtr6 = outputPtr4 + 1;
            bbuf[outputPtr4] = ISO7816.INS_DECREASE;
            outputPtr2 = outputPtr6 + 1;
            bbuf[outputPtr6] = ISO7816.INS_DECREASE;
        }
        int outputPtr7 = outputPtr2 + 1;
        bbuf[outputPtr2] = HEX_CHARS[charToEscape >> 4];
        int outputPtr8 = outputPtr7 + 1;
        bbuf[outputPtr7] = HEX_CHARS[charToEscape & 15];
        return outputPtr8;
    }

    /* access modifiers changed from: protected */
    public final void _flushBuffer() throws IOException {
        int len = this._outputTail;
        if (len > 0) {
            this._outputTail = 0;
            this._outputStream.write(this._outputBuffer, 0, len);
        }
    }
}
