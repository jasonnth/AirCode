package com.jumio.commons.view;

import android.graphics.Path;

public class SVGParser {
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Path createPathFromSvgString(java.lang.String r28) {
        /*
            int r22 = r28.length()
            com.jumio.commons.view.SVGParserHelper r23 = new com.jumio.commons.view.SVGParserHelper
            r9 = 0
            r0 = r23
            r1 = r28
            r0.<init>(r1, r9)
            r23.skipWhitespace()
            android.graphics.Path r2 = new android.graphics.Path
            r2.<init>()
            r10 = 0
            r11 = 0
            r20 = 0
            r21 = 0
            r25 = 0
            r26 = 0
            r24 = 0
        L_0x0022:
            r0 = r23
            int r9 = r0.pos
            r0 = r22
            if (r9 >= r0) goto L_0x018a
            r0 = r23
            int r9 = r0.pos
            r0 = r28
            char r19 = r0.charAt(r9)
            switch(r19) {
                case 43: goto L_0x004b;
                case 44: goto L_0x0037;
                case 45: goto L_0x004b;
                case 46: goto L_0x0037;
                case 47: goto L_0x0037;
                case 48: goto L_0x004b;
                case 49: goto L_0x004b;
                case 50: goto L_0x004b;
                case 51: goto L_0x004b;
                case 52: goto L_0x004b;
                case 53: goto L_0x004b;
                case 54: goto L_0x004b;
                case 55: goto L_0x004b;
                case 56: goto L_0x004b;
                case 57: goto L_0x004b;
                default: goto L_0x0037;
            }
        L_0x0037:
            r23.advance()
            r24 = r19
        L_0x003c:
            r27 = 0
            switch(r19) {
                case 65: goto L_0x015e;
                case 67: goto L_0x00fc;
                case 72: goto L_0x00ce;
                case 76: goto L_0x00b2;
                case 77: goto L_0x007b;
                case 83: goto L_0x012d;
                case 86: goto L_0x00e5;
                case 90: goto L_0x009d;
                case 97: goto L_0x015e;
                case 99: goto L_0x00fc;
                case 104: goto L_0x00ce;
                case 108: goto L_0x00b2;
                case 109: goto L_0x007b;
                case 115: goto L_0x012d;
                case 118: goto L_0x00e5;
                case 122: goto L_0x009d;
                default: goto L_0x0041;
            }
        L_0x0041:
            if (r27 != 0) goto L_0x0047
            r20 = r10
            r21 = r11
        L_0x0047:
            r23.skipWhitespace()
            goto L_0x0022
        L_0x004b:
            r9 = 109(0x6d, float:1.53E-43)
            r0 = r24
            if (r0 == r9) goto L_0x0057
            r9 = 77
            r0 = r24
            if (r0 != r9) goto L_0x005d
        L_0x0057:
            int r9 = r24 + -1
            char r0 = (char) r9
            r19 = r0
            goto L_0x003c
        L_0x005d:
            r9 = 99
            r0 = r24
            if (r0 == r9) goto L_0x0069
            r9 = 67
            r0 = r24
            if (r0 != r9) goto L_0x006c
        L_0x0069:
            r19 = r24
            goto L_0x003c
        L_0x006c:
            r9 = 108(0x6c, float:1.51E-43)
            r0 = r24
            if (r0 == r9) goto L_0x0078
            r9 = 76
            r0 = r24
            if (r0 != r9) goto L_0x0037
        L_0x0078:
            r19 = r24
            goto L_0x003c
        L_0x007b:
            float r7 = r23.nextFloat()
            float r8 = r23.nextFloat()
            r9 = 109(0x6d, float:1.53E-43)
            r0 = r19
            if (r0 != r9) goto L_0x0093
            float r25 = r25 + r7
            float r26 = r26 + r8
            r2.rMoveTo(r7, r8)
            float r10 = r10 + r7
            float r11 = r11 + r8
            goto L_0x0041
        L_0x0093:
            r25 = r7
            r26 = r8
            r2.moveTo(r7, r8)
            r10 = r7
            r11 = r8
            goto L_0x0041
        L_0x009d:
            r2.close()
            r0 = r25
            r1 = r26
            r2.moveTo(r0, r1)
            r10 = r25
            r11 = r26
            r20 = r25
            r21 = r26
            r27 = 1
            goto L_0x0041
        L_0x00b2:
            float r7 = r23.nextFloat()
            float r8 = r23.nextFloat()
            r9 = 108(0x6c, float:1.51E-43)
            r0 = r19
            if (r0 != r9) goto L_0x00c7
            r2.rLineTo(r7, r8)
            float r10 = r10 + r7
            float r11 = r11 + r8
            goto L_0x0041
        L_0x00c7:
            r2.lineTo(r7, r8)
            r10 = r7
            r11 = r8
            goto L_0x0041
        L_0x00ce:
            float r7 = r23.nextFloat()
            r9 = 104(0x68, float:1.46E-43)
            r0 = r19
            if (r0 != r9) goto L_0x00df
            r9 = 0
            r2.rLineTo(r7, r9)
            float r10 = r10 + r7
            goto L_0x0041
        L_0x00df:
            r2.lineTo(r7, r11)
            r10 = r7
            goto L_0x0041
        L_0x00e5:
            float r8 = r23.nextFloat()
            r9 = 118(0x76, float:1.65E-43)
            r0 = r19
            if (r0 != r9) goto L_0x00f6
            r9 = 0
            r2.rLineTo(r9, r8)
            float r11 = r11 + r8
            goto L_0x0041
        L_0x00f6:
            r2.lineTo(r10, r8)
            r11 = r8
            goto L_0x0041
        L_0x00fc:
            r27 = 1
            float r3 = r23.nextFloat()
            float r4 = r23.nextFloat()
            float r5 = r23.nextFloat()
            float r6 = r23.nextFloat()
            float r7 = r23.nextFloat()
            float r8 = r23.nextFloat()
            r9 = 99
            r0 = r19
            if (r0 != r9) goto L_0x0122
            float r3 = r3 + r10
            float r5 = r5 + r10
            float r7 = r7 + r10
            float r4 = r4 + r11
            float r6 = r6 + r11
            float r8 = r8 + r11
        L_0x0122:
            r2.cubicTo(r3, r4, r5, r6, r7, r8)
            r20 = r5
            r21 = r6
            r10 = r7
            r11 = r8
            goto L_0x0041
        L_0x012d:
            r27 = 1
            float r5 = r23.nextFloat()
            float r6 = r23.nextFloat()
            float r7 = r23.nextFloat()
            float r8 = r23.nextFloat()
            r9 = 115(0x73, float:1.61E-43)
            r0 = r19
            if (r0 != r9) goto L_0x0149
            float r5 = r5 + r10
            float r7 = r7 + r10
            float r6 = r6 + r11
            float r8 = r8 + r11
        L_0x0149:
            r9 = 1073741824(0x40000000, float:2.0)
            float r9 = r9 * r10
            float r3 = r9 - r20
            r9 = 1073741824(0x40000000, float:2.0)
            float r9 = r9 * r11
            float r4 = r9 - r21
            r2.cubicTo(r3, r4, r5, r6, r7, r8)
            r20 = r5
            r21 = r6
            r10 = r7
            r11 = r8
            goto L_0x0041
        L_0x015e:
            float r14 = r23.nextFloat()
            float r15 = r23.nextFloat()
            float r16 = r23.nextFloat()
            float r9 = r23.nextFloat()
            int r0 = (int) r9
            r17 = r0
            float r9 = r23.nextFloat()
            int r0 = (int) r9
            r18 = r0
            float r7 = r23.nextFloat()
            float r8 = r23.nextFloat()
            r9 = r2
            r12 = r7
            r13 = r8
            drawArc(r9, r10, r11, r12, r13, r14, r15, r16, r17, r18)
            r10 = r7
            r11 = r8
            goto L_0x0041
        L_0x018a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.view.SVGParser.createPathFromSvgString(java.lang.String):android.graphics.Path");
    }

    private static void drawArc(Path p, float lastX, float lastY, float x, float y, float rx, float ry, float theta, int largeArc, int sweepArc) {
    }
}
