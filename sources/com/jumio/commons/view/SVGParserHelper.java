package com.jumio.commons.view;

class SVGParserHelper {
    private static final double[] pow10 = new double[128];
    private char current;

    /* renamed from: n */
    private int f3201n;
    public int pos;

    /* renamed from: s */
    private CharSequence f3202s;

    public SVGParserHelper(CharSequence s, int pos2) {
        this.f3202s = s;
        this.pos = pos2;
        this.f3201n = s.length();
        this.current = s.charAt(pos2);
    }

    private char read() {
        if (this.pos < this.f3201n) {
            this.pos++;
        }
        if (this.pos == this.f3201n) {
            return 0;
        }
        return this.f3202s.charAt(this.pos);
    }

    public void skipWhitespace() {
        while (this.pos < this.f3201n && Character.isWhitespace(this.f3202s.charAt(this.pos))) {
            advance();
        }
    }

    public void skipNumberSeparator() {
        while (this.pos < this.f3201n) {
            switch (this.f3202s.charAt(this.pos)) {
                case 9:
                case 10:
                case ' ':
                case ',':
                    advance();
                default:
                    return;
            }
        }
    }

    public void advance() {
        this.current = read();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00b1, code lost:
        r12.current = read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00b9, code lost:
        switch(r12.current) {
            case 48: goto L_0x00c3;
            case 49: goto L_0x00c3;
            case 50: goto L_0x00c3;
            case 51: goto L_0x00c3;
            case 52: goto L_0x00c3;
            case 53: goto L_0x00c3;
            case 54: goto L_0x00c3;
            case 55: goto L_0x00c3;
            case 56: goto L_0x00c3;
            case 57: goto L_0x00c3;
            default: goto L_0x00bc;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00bc, code lost:
        reportUnexpectedCharacterError(r12.current);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00c5, code lost:
        switch(r12.current) {
            case 48: goto L_0x00c9;
            case 49: goto L_0x00d5;
            case 50: goto L_0x00d5;
            case 51: goto L_0x00d5;
            case 52: goto L_0x00d5;
            case 53: goto L_0x00d5;
            case 54: goto L_0x00d5;
            case 55: goto L_0x00d5;
            case 56: goto L_0x00d5;
            case 57: goto L_0x00d5;
            default: goto L_0x00c8;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00c9, code lost:
        r12.current = read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00d1, code lost:
        switch(r12.current) {
            case 48: goto L_0x00c9;
            case 49: goto L_0x00d5;
            case 50: goto L_0x00d5;
            case 51: goto L_0x00d5;
            case 52: goto L_0x00d5;
            case 53: goto L_0x00d5;
            case 54: goto L_0x00d5;
            case 55: goto L_0x00d5;
            case 56: goto L_0x00d5;
            case 57: goto L_0x00d5;
            default: goto L_0x00d4;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00d6, code lost:
        if (r2 >= 3) goto L_0x00e2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00d8, code lost:
        r2 = r2 + 1;
        r0 = (r0 * 10) + (r12.current - '0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00e2, code lost:
        r12.current = read();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00ea, code lost:
        switch(r12.current) {
            case 48: goto L_0x00d5;
            case 49: goto L_0x00d5;
            case 50: goto L_0x00d5;
            case 51: goto L_0x00d5;
            case 52: goto L_0x00d5;
            case 53: goto L_0x00d5;
            case 54: goto L_0x00d5;
            case 55: goto L_0x00d5;
            case 56: goto L_0x00d5;
            case 57: goto L_0x00d5;
            default: goto L_0x00ed;
        };
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        return 0.0f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047 A[LOOP_START, PHI: r1 r4 r5 
      PHI: (r1v1 'expAdj' int) = (r1v0 'expAdj' int), (r1v3 'expAdj' int) binds: [B:16:0x0046, B:20:0x005b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v1 'mant' int) = (r4v0 'mant' int), (r4v2 'mant' int) binds: [B:16:0x0046, B:20:0x005b] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r5v1 'mantDig' int) = (r5v0 'mantDig' int), (r5v2 'mantDig' int) binds: [B:16:0x0046, B:20:0x005b] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0064 A[LOOP_START, PHI: r1 
      PHI: (r1v10 'expAdj' int) = (r1v4 'expAdj' int), (r1v11 'expAdj' int) binds: [B:22:0x0062, B:24:0x006e] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0084 A[LOOP_START, PHI: r1 r4 r5 
      PHI: (r1v7 'expAdj' int) = (r1v6 'expAdj' int), (r1v8 'expAdj' int) binds: [B:61:0x0084, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r4v8 'mant' int) = (r4v4 'mant' int), (r4v9 'mant' int) binds: [B:61:0x0084, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r5v5 'mantDig' int) = (r5v4 'mantDig' int), (r5v6 'mantDig' int) binds: [B:61:0x0084, B:37:0x009a] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0021 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public float parseFloat() {
        /*
            r12 = this;
            r11 = 9
            r8 = 0
            r4 = 0
            r5 = 0
            r6 = 1
            r7 = 0
            r0 = 0
            r2 = 0
            r1 = 0
            r3 = 1
            char r9 = r12.current
            switch(r9) {
                case 43: goto L_0x0019;
                case 44: goto L_0x0010;
                case 45: goto L_0x0018;
                default: goto L_0x0010;
            }
        L_0x0010:
            char r9 = r12.current
            switch(r9) {
                case 46: goto L_0x002d;
                case 47: goto L_0x0015;
                case 48: goto L_0x0020;
                case 49: goto L_0x0046;
                case 50: goto L_0x0046;
                case 51: goto L_0x0046;
                case 52: goto L_0x0046;
                case 53: goto L_0x0046;
                case 54: goto L_0x0046;
                case 55: goto L_0x0046;
                case 56: goto L_0x0046;
                case 57: goto L_0x0046;
                default: goto L_0x0015;
            }
        L_0x0015:
            r8 = 2143289344(0x7fc00000, float:NaN)
        L_0x0017:
            return r8
        L_0x0018:
            r6 = 0
        L_0x0019:
            char r9 = r12.read()
            r12.current = r9
            goto L_0x0010
        L_0x0020:
            r7 = 1
        L_0x0021:
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 46: goto L_0x002d;
                case 48: goto L_0x0021;
                case 49: goto L_0x0046;
                case 50: goto L_0x0046;
                case 51: goto L_0x0046;
                case 52: goto L_0x0046;
                case 53: goto L_0x0046;
                case 54: goto L_0x0046;
                case 55: goto L_0x0046;
                case 56: goto L_0x0046;
                case 57: goto L_0x0046;
                case 69: goto L_0x002d;
                case 101: goto L_0x002d;
                default: goto L_0x002c;
            }
        L_0x002c:
            goto L_0x0017
        L_0x002d:
            char r9 = r12.current
            r10 = 46
            if (r9 != r10) goto L_0x0073
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 48: goto L_0x0062;
                case 49: goto L_0x0084;
                case 50: goto L_0x0084;
                case 51: goto L_0x0084;
                case 52: goto L_0x0084;
                case 53: goto L_0x0084;
                case 54: goto L_0x0084;
                case 55: goto L_0x0084;
                case 56: goto L_0x0084;
                case 57: goto L_0x0084;
                default: goto L_0x003e;
            }
        L_0x003e:
            if (r7 != 0) goto L_0x0073
            char r9 = r12.current
            r12.reportUnexpectedCharacterError(r9)
            goto L_0x0017
        L_0x0046:
            r7 = 1
        L_0x0047:
            if (r5 >= r11) goto L_0x005f
            int r5 = r5 + 1
            int r9 = r4 * 10
            char r10 = r12.current
            int r10 = r10 + -48
            int r4 = r9 + r10
        L_0x0053:
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 48: goto L_0x0047;
                case 49: goto L_0x0047;
                case 50: goto L_0x0047;
                case 51: goto L_0x0047;
                case 52: goto L_0x0047;
                case 53: goto L_0x0047;
                case 54: goto L_0x0047;
                case 55: goto L_0x0047;
                case 56: goto L_0x0047;
                case 57: goto L_0x0047;
                default: goto L_0x005e;
            }
        L_0x005e:
            goto L_0x002d
        L_0x005f:
            int r1 = r1 + 1
            goto L_0x0053
        L_0x0062:
            if (r5 != 0) goto L_0x0084
        L_0x0064:
            char r9 = r12.read()
            r12.current = r9
            int r1 = r1 + -1
            char r9 = r12.current
            switch(r9) {
                case 48: goto L_0x0064;
                case 49: goto L_0x0084;
                case 50: goto L_0x0084;
                case 51: goto L_0x0084;
                case 52: goto L_0x0084;
                case 53: goto L_0x0084;
                case 54: goto L_0x0084;
                case 55: goto L_0x0084;
                case 56: goto L_0x0084;
                case 57: goto L_0x0084;
                default: goto L_0x0071;
            }
        L_0x0071:
            if (r7 == 0) goto L_0x0017
        L_0x0073:
            char r9 = r12.current
            switch(r9) {
                case 69: goto L_0x009e;
                case 101: goto L_0x009e;
                default: goto L_0x0078;
            }
        L_0x0078:
            if (r3 != 0) goto L_0x007b
            int r0 = -r0
        L_0x007b:
            int r0 = r0 + r1
            if (r6 != 0) goto L_0x007f
            int r4 = -r4
        L_0x007f:
            float r8 = buildFloat(r4, r0)
            goto L_0x0017
        L_0x0084:
            if (r5 >= r11) goto L_0x0092
            int r5 = r5 + 1
            int r9 = r4 * 10
            char r10 = r12.current
            int r10 = r10 + -48
            int r4 = r9 + r10
            int r1 = r1 + -1
        L_0x0092:
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 48: goto L_0x0084;
                case 49: goto L_0x0084;
                case 50: goto L_0x0084;
                case 51: goto L_0x0084;
                case 52: goto L_0x0084;
                case 53: goto L_0x0084;
                case 54: goto L_0x0084;
                case 55: goto L_0x0084;
                case 56: goto L_0x0084;
                case 57: goto L_0x0084;
                default: goto L_0x009d;
            }
        L_0x009d:
            goto L_0x0073
        L_0x009e:
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 43: goto L_0x00b1;
                case 44: goto L_0x00a9;
                case 45: goto L_0x00b0;
                case 46: goto L_0x00a9;
                case 47: goto L_0x00a9;
                case 48: goto L_0x00c3;
                case 49: goto L_0x00c3;
                case 50: goto L_0x00c3;
                case 51: goto L_0x00c3;
                case 52: goto L_0x00c3;
                case 53: goto L_0x00c3;
                case 54: goto L_0x00c3;
                case 55: goto L_0x00c3;
                case 56: goto L_0x00c3;
                case 57: goto L_0x00c3;
                default: goto L_0x00a9;
            }
        L_0x00a9:
            char r9 = r12.current
            r12.reportUnexpectedCharacterError(r9)
            goto L_0x0017
        L_0x00b0:
            r3 = 0
        L_0x00b1:
            char r9 = r12.read()
            r12.current = r9
            char r9 = r12.current
            switch(r9) {
                case 48: goto L_0x00c3;
                case 49: goto L_0x00c3;
                case 50: goto L_0x00c3;
                case 51: goto L_0x00c3;
                case 52: goto L_0x00c3;
                case 53: goto L_0x00c3;
                case 54: goto L_0x00c3;
                case 55: goto L_0x00c3;
                case 56: goto L_0x00c3;
                case 57: goto L_0x00c3;
                default: goto L_0x00bc;
            }
        L_0x00bc:
            char r9 = r12.current
            r12.reportUnexpectedCharacterError(r9)
            goto L_0x0017
        L_0x00c3:
            char r8 = r12.current
            switch(r8) {
                case 48: goto L_0x00c9;
                case 49: goto L_0x00d5;
                case 50: goto L_0x00d5;
                case 51: goto L_0x00d5;
                case 52: goto L_0x00d5;
                case 53: goto L_0x00d5;
                case 54: goto L_0x00d5;
                case 55: goto L_0x00d5;
                case 56: goto L_0x00d5;
                case 57: goto L_0x00d5;
                default: goto L_0x00c8;
            }
        L_0x00c8:
            goto L_0x0078
        L_0x00c9:
            char r8 = r12.read()
            r12.current = r8
            char r8 = r12.current
            switch(r8) {
                case 48: goto L_0x00c9;
                case 49: goto L_0x00d5;
                case 50: goto L_0x00d5;
                case 51: goto L_0x00d5;
                case 52: goto L_0x00d5;
                case 53: goto L_0x00d5;
                case 54: goto L_0x00d5;
                case 55: goto L_0x00d5;
                case 56: goto L_0x00d5;
                case 57: goto L_0x00d5;
                default: goto L_0x00d4;
            }
        L_0x00d4:
            goto L_0x0078
        L_0x00d5:
            r8 = 3
            if (r2 >= r8) goto L_0x00e2
            int r2 = r2 + 1
            int r8 = r0 * 10
            char r9 = r12.current
            int r9 = r9 + -48
            int r0 = r8 + r9
        L_0x00e2:
            char r8 = r12.read()
            r12.current = r8
            char r8 = r12.current
            switch(r8) {
                case 48: goto L_0x00d5;
                case 49: goto L_0x00d5;
                case 50: goto L_0x00d5;
                case 51: goto L_0x00d5;
                case 52: goto L_0x00d5;
                case 53: goto L_0x00d5;
                case 54: goto L_0x00d5;
                case 55: goto L_0x00d5;
                case 56: goto L_0x00d5;
                case 57: goto L_0x00d5;
                default: goto L_0x00ed;
            }
        L_0x00ed:
            goto L_0x0078
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.view.SVGParserHelper.parseFloat():float");
    }

    private void reportUnexpectedCharacterError(char c) {
        throw new RuntimeException("Unexpected char '" + c + "'.");
    }

    public static float buildFloat(int mant, int exp) {
        if (exp < -125 || mant == 0) {
            return 0.0f;
        }
        if (exp >= 128) {
            return mant > 0 ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
        }
        if (exp == 0) {
            return (float) mant;
        }
        if (mant >= 67108864) {
            mant++;
        }
        return (float) (exp > 0 ? ((double) mant) * pow10[exp] : ((double) mant) / pow10[-exp]);
    }

    static {
        for (int i = 0; i < pow10.length; i++) {
            pow10[i] = Math.pow(10.0d, (double) i);
        }
    }

    public float nextFloat() {
        skipWhitespace();
        float f = parseFloat();
        skipNumberSeparator();
        return f;
    }
}
