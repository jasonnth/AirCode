package com.fasterxml.jackson.core.p307io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* renamed from: com.fasterxml.jackson.core.io.UTF8Writer */
public final class UTF8Writer extends Writer {
    private final IOContext _context;
    private OutputStream _out;
    private byte[] _outBuffer;
    private final int _outBufferEnd = (this._outBuffer.length - 4);
    private int _outPtr = 0;
    private int _surrogate;

    public UTF8Writer(IOContext ctxt, OutputStream out) {
        this._context = ctxt;
        this._out = out;
        this._outBuffer = ctxt.allocWriteEncodingBuffer();
    }

    public Writer append(char c) throws IOException {
        write((int) c);
        return this;
    }

    public void close() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            OutputStream out = this._out;
            this._out = null;
            byte[] buf = this._outBuffer;
            if (buf != null) {
                this._outBuffer = null;
                this._context.releaseWriteEncodingBuffer(buf);
            }
            out.close();
            int code = this._surrogate;
            this._surrogate = 0;
            if (code > 0) {
                illegalSurrogate(code);
            }
        }
    }

    public void flush() throws IOException {
        if (this._out != null) {
            if (this._outPtr > 0) {
                this._out.write(this._outBuffer, 0, this._outPtr);
                this._outPtr = 0;
            }
            this._out.flush();
        }
    }

    public void write(char[] cbuf) throws IOException {
        write(cbuf, 0, cbuf.length);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r0v0, types: [char, int] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=char, code=int, for r0v3, types: [char, int] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void write(char[] r13, int r14, int r15) throws java.io.IOException {
        /*
            r12 = this;
            r11 = 128(0x80, float:1.794E-43)
            r9 = 2
            if (r15 >= r9) goto L_0x000e
            r9 = 1
            if (r15 != r9) goto L_0x000d
            char r9 = r13[r14]
            r12.write(r9)
        L_0x000d:
            return
        L_0x000e:
            int r9 = r12._surrogate
            if (r9 <= 0) goto L_0x0020
            int r3 = r14 + 1
            char r8 = r13[r14]
            int r15 = r15 + -1
            int r9 = r12.convertSurrogate(r8)
            r12.write(r9)
            r14 = r3
        L_0x0020:
            int r6 = r12._outPtr
            byte[] r4 = r12._outBuffer
            int r5 = r12._outBufferEnd
            int r15 = r15 + r14
            r3 = r14
        L_0x0028:
            if (r3 >= r15) goto L_0x00f1
            if (r6 < r5) goto L_0x0033
            java.io.OutputStream r9 = r12._out
            r10 = 0
            r9.write(r4, r10, r6)
            r6 = 0
        L_0x0033:
            int r14 = r3 + 1
            char r0 = r13[r3]
            if (r0 >= r11) goto L_0x00ed
            int r7 = r6 + 1
            byte r9 = (byte) r0
            r4[r6] = r9
            int r1 = r15 - r14
            int r2 = r5 - r7
            if (r1 <= r2) goto L_0x0045
            r1 = r2
        L_0x0045:
            int r1 = r1 + r14
            r3 = r14
        L_0x0047:
            if (r3 < r1) goto L_0x004b
            r6 = r7
            goto L_0x0028
        L_0x004b:
            int r14 = r3 + 1
            char r0 = r13[r3]
            if (r0 < r11) goto L_0x006c
            r3 = r14
        L_0x0052:
            r9 = 2048(0x800, float:2.87E-42)
            if (r0 >= r9) goto L_0x0074
            int r6 = r7 + 1
            int r9 = r0 >> 6
            r9 = r9 | 192(0xc0, float:2.69E-43)
            byte r9 = (byte) r9
            r4[r7] = r9
            int r7 = r6 + 1
            r9 = r0 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r6] = r9
            r6 = r7
            r14 = r3
        L_0x006a:
            r3 = r14
            goto L_0x0028
        L_0x006c:
            int r6 = r7 + 1
            byte r9 = (byte) r0
            r4[r7] = r9
            r7 = r6
            r3 = r14
            goto L_0x0047
        L_0x0074:
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r0 < r9) goto L_0x007e
            r9 = 57343(0xdfff, float:8.0355E-41)
            if (r0 <= r9) goto L_0x009c
        L_0x007e:
            int r6 = r7 + 1
            int r9 = r0 >> 12
            r9 = r9 | 224(0xe0, float:3.14E-43)
            byte r9 = (byte) r9
            r4[r7] = r9
            int r7 = r6 + 1
            int r9 = r0 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r6] = r9
            int r6 = r7 + 1
            r9 = r0 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r7] = r9
            goto L_0x0028
        L_0x009c:
            r9 = 56319(0xdbff, float:7.892E-41)
            if (r0 <= r9) goto L_0x00a6
            r12._outPtr = r7
            illegalSurrogate(r0)
        L_0x00a6:
            r12._surrogate = r0
            if (r3 < r15) goto L_0x00b0
            r6 = r7
            r14 = r3
        L_0x00ac:
            r12._outPtr = r6
            goto L_0x000d
        L_0x00b0:
            int r14 = r3 + 1
            char r9 = r13[r3]
            int r0 = r12.convertSurrogate(r9)
            r9 = 1114111(0x10ffff, float:1.561202E-39)
            if (r0 <= r9) goto L_0x00c2
            r12._outPtr = r7
            illegalSurrogate(r0)
        L_0x00c2:
            int r6 = r7 + 1
            int r9 = r0 >> 18
            r9 = r9 | 240(0xf0, float:3.36E-43)
            byte r9 = (byte) r9
            r4[r7] = r9
            int r7 = r6 + 1
            int r9 = r0 >> 12
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r6] = r9
            int r6 = r7 + 1
            int r9 = r0 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r7] = r9
            int r7 = r6 + 1
            r9 = r0 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r4[r6] = r9
            r6 = r7
            goto L_0x006a
        L_0x00ed:
            r7 = r6
            r3 = r14
            goto L_0x0052
        L_0x00f1:
            r14 = r3
            goto L_0x00ac
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p307io.UTF8Writer.write(char[], int, int):void");
    }

    public void write(int c) throws IOException {
        int ptr;
        if (this._surrogate > 0) {
            c = convertSurrogate(c);
        } else if (c >= 55296 && c <= 57343) {
            if (c > 56319) {
                illegalSurrogate(c);
            }
            this._surrogate = c;
            return;
        }
        if (this._outPtr >= this._outBufferEnd) {
            this._out.write(this._outBuffer, 0, this._outPtr);
            this._outPtr = 0;
        }
        if (c < 128) {
            byte[] bArr = this._outBuffer;
            int i = this._outPtr;
            this._outPtr = i + 1;
            bArr[i] = (byte) c;
            return;
        }
        int ptr2 = this._outPtr;
        if (c < 2048) {
            int ptr3 = ptr2 + 1;
            this._outBuffer[ptr2] = (byte) ((c >> 6) | 192);
            ptr = ptr3 + 1;
            this._outBuffer[ptr3] = (byte) ((c & 63) | 128);
        } else if (c <= 65535) {
            int ptr4 = ptr2 + 1;
            this._outBuffer[ptr2] = (byte) ((c >> 12) | 224);
            int ptr5 = ptr4 + 1;
            this._outBuffer[ptr4] = (byte) (((c >> 6) & 63) | 128);
            int ptr6 = ptr5 + 1;
            this._outBuffer[ptr5] = (byte) ((c & 63) | 128);
            ptr = ptr6;
        } else {
            if (c > 1114111) {
                illegalSurrogate(c);
            }
            int ptr7 = ptr2 + 1;
            this._outBuffer[ptr2] = (byte) ((c >> 18) | 240);
            int ptr8 = ptr7 + 1;
            this._outBuffer[ptr7] = (byte) (((c >> 12) & 63) | 128);
            int ptr9 = ptr8 + 1;
            this._outBuffer[ptr8] = (byte) (((c >> 6) & 63) | 128);
            ptr = ptr9 + 1;
            this._outBuffer[ptr9] = (byte) ((c & 63) | 128);
        }
        this._outPtr = ptr;
    }

    public void write(String str) throws IOException {
        write(str, 0, str.length());
    }

    public void write(String str, int off, int len) throws IOException {
        int outPtr;
        int off2;
        int off3;
        if (len >= 2) {
            if (this._surrogate > 0) {
                int off4 = off + 1;
                len--;
                write(convertSurrogate(str.charAt(off)));
                off = off4;
            }
            int outPtr2 = this._outPtr;
            byte[] outBuf = this._outBuffer;
            int outBufLast = this._outBufferEnd;
            int len2 = len + off;
            int off5 = off;
            while (true) {
                if (off5 >= len2) {
                    int i = off5;
                    break;
                }
                if (outPtr2 >= outBufLast) {
                    this._out.write(outBuf, 0, outPtr2);
                    outPtr2 = 0;
                }
                int off6 = off5 + 1;
                int c = str.charAt(off5);
                if (c < 128) {
                    outPtr = outPtr2 + 1;
                    outBuf[outPtr2] = (byte) c;
                    int maxInCount = len2 - off6;
                    int maxOutCount = outBufLast - outPtr;
                    if (maxInCount > maxOutCount) {
                        maxInCount = maxOutCount;
                    }
                    int maxInCount2 = maxInCount + off6;
                    off5 = off6;
                    while (off5 < maxInCount2) {
                        int off7 = off5 + 1;
                        c = str.charAt(off5);
                        if (c >= 128) {
                            off2 = off7;
                        } else {
                            int outPtr3 = outPtr + 1;
                            outBuf[outPtr] = (byte) c;
                            outPtr = outPtr3;
                            off5 = off7;
                        }
                    }
                    outPtr2 = outPtr;
                } else {
                    outPtr = outPtr2;
                    off2 = off6;
                }
                if (c < 2048) {
                    int outPtr4 = outPtr + 1;
                    outBuf[outPtr] = (byte) ((c >> 6) | 192);
                    int outPtr5 = outPtr4 + 1;
                    outBuf[outPtr4] = (byte) ((c & 63) | 128);
                    outPtr2 = outPtr5;
                    off3 = off5;
                } else if (c < 55296 || c > 57343) {
                    int outPtr6 = outPtr + 1;
                    outBuf[outPtr] = (byte) ((c >> 12) | 224);
                    int outPtr7 = outPtr6 + 1;
                    outBuf[outPtr6] = (byte) (((c >> 6) & 63) | 128);
                    outPtr2 = outPtr7 + 1;
                    outBuf[outPtr7] = (byte) ((c & 63) | 128);
                } else {
                    if (c > 56319) {
                        this._outPtr = outPtr;
                        illegalSurrogate(c);
                    }
                    this._surrogate = c;
                    if (off5 >= len2) {
                        outPtr2 = outPtr;
                        int i2 = off5;
                        break;
                    }
                    off3 = off5 + 1;
                    int c2 = convertSurrogate(str.charAt(off5));
                    if (c2 > 1114111) {
                        this._outPtr = outPtr;
                        illegalSurrogate(c2);
                    }
                    int outPtr8 = outPtr + 1;
                    outBuf[outPtr] = (byte) ((c2 >> 18) | 240);
                    int outPtr9 = outPtr8 + 1;
                    outBuf[outPtr8] = (byte) (((c2 >> 12) & 63) | 128);
                    int outPtr10 = outPtr9 + 1;
                    outBuf[outPtr9] = (byte) (((c2 >> 6) & 63) | 128);
                    int outPtr11 = outPtr10 + 1;
                    outBuf[outPtr10] = (byte) ((c2 & 63) | 128);
                    outPtr2 = outPtr11;
                }
                off5 = off3;
            }
            this._outPtr = outPtr2;
        } else if (len == 1) {
            write((int) str.charAt(off));
        }
    }

    /* access modifiers changed from: protected */
    public int convertSurrogate(int secondPart) throws IOException {
        int firstPart = this._surrogate;
        this._surrogate = 0;
        if (secondPart >= 56320 && secondPart <= 57343) {
            return 65536 + ((firstPart - 55296) << 10) + (secondPart - 56320);
        }
        throw new IOException("Broken surrogate pair: first char 0x" + Integer.toHexString(firstPart) + ", second 0x" + Integer.toHexString(secondPart) + "; illegal combination");
    }

    protected static void illegalSurrogate(int code) throws IOException {
        throw new IOException(illegalSurrogateDesc(code));
    }

    protected static String illegalSurrogateDesc(int code) {
        if (code > 1114111) {
            return "Illegal character point (0x" + Integer.toHexString(code) + ") to output; max is 0x10FFFF as per RFC 4627";
        }
        if (code < 55296) {
            return "Illegal character point (0x" + Integer.toHexString(code) + ") to output";
        }
        if (code <= 56319) {
            return "Unmatched first part of surrogate pair (0x" + Integer.toHexString(code) + ")";
        }
        return "Unmatched second part of surrogate pair (0x" + Integer.toHexString(code) + ")";
    }
}
