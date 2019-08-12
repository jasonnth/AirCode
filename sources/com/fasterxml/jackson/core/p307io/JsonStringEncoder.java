package com.fasterxml.jackson.core.p307io;

import com.fasterxml.jackson.core.util.BufferRecycler;
import com.fasterxml.jackson.core.util.ByteArrayBuilder;
import com.fasterxml.jackson.core.util.TextBuffer;
import java.lang.ref.SoftReference;
import org.jmrtd.lds.LDSFile;

/* renamed from: com.fasterxml.jackson.core.io.JsonStringEncoder */
public final class JsonStringEncoder {

    /* renamed from: HB */
    private static final byte[] f3140HB = CharTypes.copyHexBytes();

    /* renamed from: HC */
    private static final char[] f3141HC = CharTypes.copyHexChars();
    protected static final ThreadLocal<SoftReference<JsonStringEncoder>> _threadEncoder = new ThreadLocal<>();
    protected ByteArrayBuilder _bytes;
    protected final char[] _qbuf = new char[6];
    protected TextBuffer _text;

    public JsonStringEncoder() {
        this._qbuf[0] = '\\';
        this._qbuf[2] = '0';
        this._qbuf[3] = '0';
    }

    public static JsonStringEncoder getInstance() {
        SoftReference<JsonStringEncoder> ref = (SoftReference) _threadEncoder.get();
        JsonStringEncoder enc = ref == null ? null : (JsonStringEncoder) ref.get();
        if (enc != null) {
            return enc;
        }
        JsonStringEncoder enc2 = new JsonStringEncoder();
        _threadEncoder.set(new SoftReference(enc2));
        return enc2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x003c, code lost:
        if (r4 >= 0) goto L_0x00a6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x003e, code lost:
        r11 = _appendNumeric(r3, r19._qbuf);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        if ((r12 + r11) <= r14.length) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0057, code lost:
        r7 = r14.length - r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005c, code lost:
        if (r7 <= 0) goto L_0x006d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x005e, code lost:
        java.lang.System.arraycopy(r19._qbuf, 0, r14, r12, r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x006d, code lost:
        r14 = r16.finishCurrentSegment();
        r15 = r11 - r7;
        java.lang.System.arraycopy(r19._qbuf, r7, r14, 0, r15);
        r12 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a6, code lost:
        r11 = _appendNamed(r4, r19._qbuf);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00b5, code lost:
        java.lang.System.arraycopy(r19._qbuf, 0, r14, r12, r11);
        r12 = r12 + r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0032, code lost:
        r9 = r8 + 1;
        r3 = r20.charAt(r8);
        r4 = r6[r3];
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public char[] quoteAsString(java.lang.String r20) {
        /*
            r19 = this;
            r0 = r19
            com.fasterxml.jackson.core.util.TextBuffer r0 = r0._text
            r16 = r0
            if (r16 != 0) goto L_0x0015
            com.fasterxml.jackson.core.util.TextBuffer r16 = new com.fasterxml.jackson.core.util.TextBuffer
            r17 = 0
            r16.<init>(r17)
            r0 = r16
            r1 = r19
            r1._text = r0
        L_0x0015:
            char[] r14 = r16.emptyAndGetCurrentSegment()
            int[] r6 = com.fasterxml.jackson.core.p307io.CharTypes.get7BitOutputEscapes()
            int r5 = r6.length
            r8 = 0
            int r10 = r20.length()
            r12 = 0
        L_0x0024:
            if (r8 >= r10) goto L_0x009a
        L_0x0026:
            r0 = r20
            char r2 = r0.charAt(r8)
            if (r2 >= r5) goto L_0x0085
            r17 = r6[r2]
            if (r17 == 0) goto L_0x0085
            int r9 = r8 + 1
            r0 = r20
            char r3 = r0.charAt(r8)
            r4 = r6[r3]
            if (r4 >= 0) goto L_0x00a6
            r0 = r19
            char[] r0 = r0._qbuf
            r17 = r0
            r0 = r19
            r1 = r17
            int r11 = r0._appendNumeric(r3, r1)
        L_0x004c:
            int r17 = r12 + r11
            int r0 = r14.length
            r18 = r0
            r0 = r17
            r1 = r18
            if (r0 <= r1) goto L_0x00b5
            int r0 = r14.length
            r17 = r0
            int r7 = r17 - r12
            if (r7 <= 0) goto L_0x006d
            r0 = r19
            char[] r0 = r0._qbuf
            r17 = r0
            r18 = 0
            r0 = r17
            r1 = r18
            java.lang.System.arraycopy(r0, r1, r14, r12, r7)
        L_0x006d:
            char[] r14 = r16.finishCurrentSegment()
            int r15 = r11 - r7
            r0 = r19
            char[] r0 = r0._qbuf
            r17 = r0
            r18 = 0
            r0 = r17
            r1 = r18
            java.lang.System.arraycopy(r0, r7, r14, r1, r15)
            r12 = r15
        L_0x0083:
            r8 = r9
            goto L_0x0024
        L_0x0085:
            int r0 = r14.length
            r17 = r0
            r0 = r17
            if (r12 < r0) goto L_0x0091
            char[] r14 = r16.finishCurrentSegment()
            r12 = 0
        L_0x0091:
            int r13 = r12 + 1
            r14[r12] = r2
            int r8 = r8 + 1
            if (r8 < r10) goto L_0x00a4
            r12 = r13
        L_0x009a:
            r0 = r16
            r0.setCurrentLength(r12)
            char[] r17 = r16.contentsAsArray()
            return r17
        L_0x00a4:
            r12 = r13
            goto L_0x0026
        L_0x00a6:
            r0 = r19
            char[] r0 = r0._qbuf
            r17 = r0
            r0 = r19
            r1 = r17
            int r11 = r0._appendNamed(r4, r1)
            goto L_0x004c
        L_0x00b5:
            r0 = r19
            char[] r0 = r0._qbuf
            r17 = r0
            r18 = 0
            r0 = r17
            r1 = r18
            java.lang.System.arraycopy(r0, r1, r14, r12, r11)
            int r12 = r12 + r11
            goto L_0x0083
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p307io.JsonStringEncoder.quoteAsString(java.lang.String):char[]");
    }

    public byte[] quoteAsUTF8(String text) {
        int outputPtr;
        int ch;
        int outputPtr2;
        int outputPtr3;
        int outputPtr4;
        ByteArrayBuilder bb = this._bytes;
        if (bb == null) {
            bb = new ByteArrayBuilder((BufferRecycler) null);
            this._bytes = bb;
        }
        int inputPtr = 0;
        int inputEnd = text.length();
        int outputPtr5 = 0;
        byte[] outputBuffer = bb.resetAndGetFirstSegment();
        loop0:
        while (true) {
            if (inputPtr >= inputEnd) {
                break;
            }
            int[] escCodes = CharTypes.get7BitOutputEscapes();
            while (true) {
                int ch2 = text.charAt(inputPtr);
                if (ch2 <= 127 && escCodes[ch2] == 0) {
                    if (outputPtr5 >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr5 = 0;
                    }
                    int outputPtr6 = outputPtr5 + 1;
                    outputBuffer[outputPtr5] = (byte) ch2;
                    inputPtr++;
                    if (inputPtr >= inputEnd) {
                        outputPtr5 = outputPtr6;
                        break loop0;
                    }
                    outputPtr5 = outputPtr6;
                }
            }
            if (outputPtr5 >= outputBuffer.length) {
                outputBuffer = bb.finishCurrentSegment();
                outputPtr5 = 0;
            }
            int inputPtr2 = inputPtr + 1;
            int ch3 = text.charAt(inputPtr);
            if (ch3 <= 127) {
                outputPtr5 = _appendByte(ch3, escCodes[ch3], bb, outputPtr5);
                outputBuffer = bb.getCurrentSegment();
                inputPtr = inputPtr2;
            } else {
                if (ch3 <= 2047) {
                    int outputPtr7 = outputPtr5 + 1;
                    outputBuffer[outputPtr5] = (byte) ((ch3 >> 6) | 192);
                    ch = (ch3 & 63) | 128;
                    outputPtr2 = outputPtr7;
                    inputPtr = inputPtr2;
                } else if (ch3 < 55296 || ch3 > 57343) {
                    int outputPtr8 = outputPtr5 + 1;
                    outputBuffer[outputPtr5] = (byte) ((ch3 >> 12) | 224);
                    if (outputPtr8 >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr = 0;
                    } else {
                        outputPtr = outputPtr8;
                    }
                    int outputPtr9 = outputPtr + 1;
                    outputBuffer[outputPtr] = (byte) (((ch3 >> 6) & 63) | 128);
                    ch = (ch3 & 63) | 128;
                    outputPtr2 = outputPtr9;
                    inputPtr = inputPtr2;
                } else {
                    if (ch3 > 56319) {
                        _illegal(ch3);
                    }
                    if (inputPtr2 >= inputEnd) {
                        _illegal(ch3);
                    }
                    inputPtr = inputPtr2 + 1;
                    int ch4 = _convert(ch3, text.charAt(inputPtr2));
                    if (ch4 > 1114111) {
                        _illegal(ch4);
                    }
                    int outputPtr10 = outputPtr5 + 1;
                    outputBuffer[outputPtr5] = (byte) ((ch4 >> 18) | 240);
                    if (outputPtr10 >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr3 = 0;
                    } else {
                        outputPtr3 = outputPtr10;
                    }
                    int outputPtr11 = outputPtr3 + 1;
                    outputBuffer[outputPtr3] = (byte) (((ch4 >> 12) & 63) | 128);
                    if (outputPtr11 >= outputBuffer.length) {
                        outputBuffer = bb.finishCurrentSegment();
                        outputPtr4 = 0;
                    } else {
                        outputPtr4 = outputPtr11;
                    }
                    int outputPtr12 = outputPtr4 + 1;
                    outputBuffer[outputPtr4] = (byte) (((ch4 >> 6) & 63) | 128);
                    ch = (ch4 & 63) | 128;
                    outputPtr2 = outputPtr12;
                }
                if (outputPtr2 >= outputBuffer.length) {
                    outputBuffer = bb.finishCurrentSegment();
                    outputPtr2 = 0;
                }
                int outputPtr13 = outputPtr2 + 1;
                outputBuffer[outputPtr2] = (byte) ch;
                outputPtr5 = outputPtr13;
            }
        }
        return this._bytes.completeAndCoalesce(outputPtr5);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        if (r7 < r6) goto L_0x00ed;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0048, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = r5.length;
        r8 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0051, code lost:
        if (r1 >= 2048) goto L_0x0071;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0053, code lost:
        r7 = r8 + 1;
        r5[r8] = (byte) ((r1 >> 6) | 192);
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        if (r7 < r6) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x005f, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = r5.length;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0074, code lost:
        if (r1 < 55296) goto L_0x007b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0079, code lost:
        if (r1 <= 57343) goto L_0x009a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x007b, code lost:
        r7 = r8 + 1;
        r5[r8] = (byte) ((r1 >> 12) | 224);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0084, code lost:
        if (r7 < r6) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0086, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = r5.length;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x008c, code lost:
        r8 = r7 + 1;
        r5[r7] = (byte) (((r1 >> 6) & 63) | 128);
        r7 = r8;
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009d, code lost:
        if (r1 <= 56319) goto L_0x00a2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x009f, code lost:
        _illegal(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a2, code lost:
        if (r4 < r2) goto L_0x00a7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00a4, code lost:
        _illegal(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00a7, code lost:
        r3 = r4 + 1;
        r1 = _convert(r1, r11.charAt(r4));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b4, code lost:
        if (r1 <= 1114111) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b6, code lost:
        _illegal(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00b9, code lost:
        r7 = r8 + 1;
        r5[r8] = (byte) ((r1 >> 18) | 240);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c2, code lost:
        if (r7 < r6) goto L_0x00ca;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c4, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = r5.length;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ca, code lost:
        r8 = r7 + 1;
        r5[r7] = (byte) (((r1 >> 12) & 63) | 128);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00d5, code lost:
        if (r8 < r6) goto L_0x00eb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d7, code lost:
        r5 = r0.finishCurrentSegment();
        r6 = r5.length;
        r7 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00dd, code lost:
        r8 = r7 + 1;
        r5[r7] = (byte) (((r1 >> 6) & 63) | 128);
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00eb, code lost:
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00ed, code lost:
        r8 = r7;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] encodeAsUTF8(java.lang.String r11) {
        /*
            r10 = this;
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = r10._bytes
            if (r0 != 0) goto L_0x000c
            com.fasterxml.jackson.core.util.ByteArrayBuilder r0 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r9 = 0
            r0.<init>(r9)
            r10._bytes = r0
        L_0x000c:
            r3 = 0
            int r2 = r11.length()
            r7 = 0
            byte[] r5 = r0.resetAndGetFirstSegment()
            int r6 = r5.length
            r4 = r3
        L_0x0018:
            if (r4 >= r2) goto L_0x00f0
            int r3 = r4 + 1
            char r1 = r11.charAt(r4)
            r4 = r3
        L_0x0021:
            r9 = 127(0x7f, float:1.78E-43)
            if (r1 > r9) goto L_0x0046
            if (r7 < r6) goto L_0x002d
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
        L_0x002d:
            int r8 = r7 + 1
            byte r9 = (byte) r1
            r5[r7] = r9
            if (r4 < r2) goto L_0x003d
            r7 = r8
            r3 = r4
        L_0x0036:
            com.fasterxml.jackson.core.util.ByteArrayBuilder r9 = r10._bytes
            byte[] r9 = r9.completeAndCoalesce(r7)
            return r9
        L_0x003d:
            int r3 = r4 + 1
            char r1 = r11.charAt(r4)
            r7 = r8
            r4 = r3
            goto L_0x0021
        L_0x0046:
            if (r7 < r6) goto L_0x00ed
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
            r8 = r7
        L_0x004f:
            r9 = 2048(0x800, float:2.87E-42)
            if (r1 >= r9) goto L_0x0071
            int r7 = r8 + 1
            int r9 = r1 >> 6
            r9 = r9 | 192(0xc0, float:2.69E-43)
            byte r9 = (byte) r9
            r5[r8] = r9
            r3 = r4
        L_0x005d:
            if (r7 < r6) goto L_0x0065
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
        L_0x0065:
            int r8 = r7 + 1
            r9 = r1 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r5[r7] = r9
            r7 = r8
            r4 = r3
            goto L_0x0018
        L_0x0071:
            r9 = 55296(0xd800, float:7.7486E-41)
            if (r1 < r9) goto L_0x007b
            r9 = 57343(0xdfff, float:8.0355E-41)
            if (r1 <= r9) goto L_0x009a
        L_0x007b:
            int r7 = r8 + 1
            int r9 = r1 >> 12
            r9 = r9 | 224(0xe0, float:3.14E-43)
            byte r9 = (byte) r9
            r5[r8] = r9
            if (r7 < r6) goto L_0x008c
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
        L_0x008c:
            int r8 = r7 + 1
            int r9 = r1 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r5[r7] = r9
            r7 = r8
            r3 = r4
            goto L_0x005d
        L_0x009a:
            r9 = 56319(0xdbff, float:7.892E-41)
            if (r1 <= r9) goto L_0x00a2
            _illegal(r1)
        L_0x00a2:
            if (r4 < r2) goto L_0x00a7
            _illegal(r1)
        L_0x00a7:
            int r3 = r4 + 1
            char r9 = r11.charAt(r4)
            int r1 = _convert(r1, r9)
            r9 = 1114111(0x10ffff, float:1.561202E-39)
            if (r1 <= r9) goto L_0x00b9
            _illegal(r1)
        L_0x00b9:
            int r7 = r8 + 1
            int r9 = r1 >> 18
            r9 = r9 | 240(0xf0, float:3.36E-43)
            byte r9 = (byte) r9
            r5[r8] = r9
            if (r7 < r6) goto L_0x00ca
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
        L_0x00ca:
            int r8 = r7 + 1
            int r9 = r1 >> 12
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r5[r7] = r9
            if (r8 < r6) goto L_0x00eb
            byte[] r5 = r0.finishCurrentSegment()
            int r6 = r5.length
            r7 = 0
        L_0x00dd:
            int r8 = r7 + 1
            int r9 = r1 >> 6
            r9 = r9 & 63
            r9 = r9 | 128(0x80, float:1.794E-43)
            byte r9 = (byte) r9
            r5[r7] = r9
            r7 = r8
            goto L_0x005d
        L_0x00eb:
            r7 = r8
            goto L_0x00dd
        L_0x00ed:
            r8 = r7
            goto L_0x004f
        L_0x00f0:
            r3 = r4
            goto L_0x0036
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.p307io.JsonStringEncoder.encodeAsUTF8(java.lang.String):byte[]");
    }

    private int _appendNumeric(int value, char[] qbuf) {
        qbuf[1] = 'u';
        qbuf[4] = f3141HC[value >> 4];
        qbuf[5] = f3141HC[value & 15];
        return 6;
    }

    private int _appendNamed(int esc, char[] qbuf) {
        qbuf[1] = (char) esc;
        return 2;
    }

    private int _appendByte(int ch, int esc, ByteArrayBuilder bb, int ptr) {
        bb.setCurrentSegmentLength(ptr);
        bb.append(92);
        if (esc < 0) {
            bb.append(LDSFile.EF_DG2_TAG);
            if (ch > 255) {
                int hi = ch >> 8;
                bb.append(f3140HB[hi >> 4]);
                bb.append(f3140HB[hi & 15]);
                ch &= 255;
            } else {
                bb.append(48);
                bb.append(48);
            }
            bb.append(f3140HB[ch >> 4]);
            bb.append(f3140HB[ch & 15]);
        } else {
            bb.append((byte) esc);
        }
        return bb.getCurrentSegmentLength();
    }

    private static int _convert(int p1, int p2) {
        if (p2 >= 56320 && p2 <= 57343) {
            return 65536 + ((p1 - 55296) << 10) + (p2 - 56320);
        }
        throw new IllegalArgumentException("Broken surrogate pair: first char 0x" + Integer.toHexString(p1) + ", second 0x" + Integer.toHexString(p2) + "; illegal combination");
    }

    private static void _illegal(int c) {
        throw new IllegalArgumentException(UTF8Writer.illegalSurrogateDesc(c));
    }
}
