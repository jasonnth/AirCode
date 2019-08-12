package com.fasterxml.jackson.core.sym;

import com.fasterxml.jackson.core.JsonFactory.Feature;
import com.fasterxml.jackson.core.util.InternCache;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class ByteQuadsCanonicalizer {
    /* access modifiers changed from: private */
    public int _count;
    private final boolean _failOnDoS;
    /* access modifiers changed from: private */
    public int[] _hashArea;
    private boolean _hashShared;
    /* access modifiers changed from: private */
    public int _hashSize;
    private boolean _intern;
    /* access modifiers changed from: private */
    public int _longNameOffset;
    /* access modifiers changed from: private */
    public String[] _names;
    private transient boolean _needRehash;
    private final ByteQuadsCanonicalizer _parent;
    private int _secondaryStart;
    private final int _seed;
    /* access modifiers changed from: private */
    public int _spilloverEnd;
    private final AtomicReference<TableInfo> _tableInfo;
    /* access modifiers changed from: private */
    public int _tertiaryShift;
    private int _tertiaryStart;

    private static final class TableInfo {
        public final int count;
        public final int longNameOffset;
        public final int[] mainHash;
        public final String[] names;
        public final int size;
        public final int spilloverEnd;
        public final int tertiaryShift;

        public TableInfo(int size2, int count2, int tertiaryShift2, int[] mainHash2, String[] names2, int spilloverEnd2, int longNameOffset2) {
            this.size = size2;
            this.count = count2;
            this.tertiaryShift = tertiaryShift2;
            this.mainHash = mainHash2;
            this.names = names2;
            this.spilloverEnd = spilloverEnd2;
            this.longNameOffset = longNameOffset2;
        }

        public TableInfo(ByteQuadsCanonicalizer src) {
            this.size = src._hashSize;
            this.count = src._count;
            this.tertiaryShift = src._tertiaryShift;
            this.mainHash = src._hashArea;
            this.names = src._names;
            this.spilloverEnd = src._spilloverEnd;
            this.longNameOffset = src._longNameOffset;
        }

        public static TableInfo createInitial(int sz) {
            int hashAreaSize = sz << 3;
            return new TableInfo(sz, 0, ByteQuadsCanonicalizer._calcTertiaryShift(sz), new int[hashAreaSize], new String[(sz << 1)], hashAreaSize - sz, hashAreaSize);
        }
    }

    private ByteQuadsCanonicalizer(int sz, boolean intern, int seed, boolean failOnDoS) {
        this._parent = null;
        this._seed = seed;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        if (sz < 16) {
            sz = 16;
        } else if (((sz - 1) & sz) != 0) {
            int curr = 16;
            while (curr < sz) {
                curr += curr;
            }
            sz = curr;
        }
        this._tableInfo = new AtomicReference<>(TableInfo.createInitial(sz));
    }

    private ByteQuadsCanonicalizer(ByteQuadsCanonicalizer parent, boolean intern, int seed, boolean failOnDoS, TableInfo state) {
        this._parent = parent;
        this._seed = seed;
        this._intern = intern;
        this._failOnDoS = failOnDoS;
        this._tableInfo = null;
        this._count = state.count;
        this._hashSize = state.size;
        this._secondaryStart = this._hashSize << 2;
        this._tertiaryStart = this._secondaryStart + (this._secondaryStart >> 1);
        this._tertiaryShift = state.tertiaryShift;
        this._hashArea = state.mainHash;
        this._names = state.names;
        this._spilloverEnd = state.spilloverEnd;
        this._longNameOffset = state.longNameOffset;
        this._needRehash = false;
        this._hashShared = true;
    }

    public static ByteQuadsCanonicalizer createRoot() {
        long now = System.currentTimeMillis();
        return createRoot((((int) now) + ((int) (now >>> 32))) | 1);
    }

    protected static ByteQuadsCanonicalizer createRoot(int seed) {
        return new ByteQuadsCanonicalizer(64, true, seed, true);
    }

    public ByteQuadsCanonicalizer makeChild(int flags) {
        return new ByteQuadsCanonicalizer(this, Feature.INTERN_FIELD_NAMES.enabledIn(flags), this._seed, Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW.enabledIn(flags), (TableInfo) this._tableInfo.get());
    }

    public void release() {
        if (this._parent != null && maybeDirty()) {
            this._parent.mergeChild(new TableInfo(this));
            this._hashShared = true;
        }
    }

    private void mergeChild(TableInfo childState) {
        int childCount = childState.count;
        TableInfo currState = (TableInfo) this._tableInfo.get();
        if (childCount != currState.count) {
            if (childCount > 6000) {
                childState = TableInfo.createInitial(64);
            }
            this._tableInfo.compareAndSet(currState, childState);
        }
    }

    public boolean maybeDirty() {
        return !this._hashShared;
    }

    public int primaryCount() {
        int count = 0;
        int end = this._secondaryStart;
        for (int offset = 3; offset < end; offset += 4) {
            if (this._hashArea[offset] != 0) {
                count++;
            }
        }
        return count;
    }

    public int secondaryCount() {
        int count = 0;
        int end = this._tertiaryStart;
        for (int offset = this._secondaryStart + 3; offset < end; offset += 4) {
            if (this._hashArea[offset] != 0) {
                count++;
            }
        }
        return count;
    }

    public int tertiaryCount() {
        int count = 0;
        int offset = this._tertiaryStart + 3;
        int end = offset + this._hashSize;
        while (offset < end) {
            if (this._hashArea[offset] != 0) {
                count++;
            }
            offset += 4;
        }
        return count;
    }

    public int spilloverCount() {
        return (this._spilloverEnd - _spilloverStart()) >> 2;
    }

    public int totalCount() {
        int count = 0;
        int end = this._hashSize << 3;
        for (int offset = 3; offset < end; offset += 4) {
            if (this._hashArea[offset] != 0) {
                count++;
            }
        }
        return count;
    }

    public String toString() {
        int pri = primaryCount();
        int sec = secondaryCount();
        int tert = tertiaryCount();
        int spill = spilloverCount();
        return String.format("[%s: size=%d, hashSize=%d, %d/%d/%d/%d pri/sec/ter/spill (=%s), total:%d]", new Object[]{getClass().getName(), Integer.valueOf(this._count), Integer.valueOf(this._hashSize), Integer.valueOf(pri), Integer.valueOf(sec), Integer.valueOf(tert), Integer.valueOf(spill), Integer.valueOf(pri + sec + tert + spill), Integer.valueOf(totalCount())});
    }

    public String findName(int q1) {
        int offset = _calcOffset(calcHash(q1));
        int[] hashArea = this._hashArea;
        int len = hashArea[offset + 3];
        if (len == 1) {
            if (hashArea[offset] == q1) {
                return this._names[offset >> 2];
            }
        } else if (len == 0) {
            return null;
        }
        int offset2 = this._secondaryStart + ((offset >> 3) << 2);
        int len2 = hashArea[offset2 + 3];
        if (len2 == 1) {
            if (hashArea[offset2] == q1) {
                return this._names[offset2 >> 2];
            }
        } else if (len2 == 0) {
            return null;
        }
        return _findSecondary(offset, q1);
    }

    public String findName(int q1, int q2) {
        int offset = _calcOffset(calcHash(q1, q2));
        int[] hashArea = this._hashArea;
        int len = hashArea[offset + 3];
        if (len == 2) {
            if (q1 == hashArea[offset] && q2 == hashArea[offset + 1]) {
                return this._names[offset >> 2];
            }
        } else if (len == 0) {
            return null;
        }
        int offset2 = this._secondaryStart + ((offset >> 3) << 2);
        int len2 = hashArea[offset2 + 3];
        if (len2 == 2) {
            if (q1 == hashArea[offset2] && q2 == hashArea[offset2 + 1]) {
                return this._names[offset2 >> 2];
            }
        } else if (len2 == 0) {
            return null;
        }
        return _findSecondary(offset, q1, q2);
    }

    public String findName(int q1, int q2, int q3) {
        int offset = _calcOffset(calcHash(q1, q2, q3));
        int[] hashArea = this._hashArea;
        int len = hashArea[offset + 3];
        if (len == 3) {
            if (q1 == hashArea[offset] && hashArea[offset + 1] == q2 && hashArea[offset + 2] == q3) {
                return this._names[offset >> 2];
            }
        } else if (len == 0) {
            return null;
        }
        int offset2 = this._secondaryStart + ((offset >> 3) << 2);
        int len2 = hashArea[offset2 + 3];
        if (len2 == 3) {
            if (q1 == hashArea[offset2] && hashArea[offset2 + 1] == q2 && hashArea[offset2 + 2] == q3) {
                return this._names[offset2 >> 2];
            }
        } else if (len2 == 0) {
            return null;
        }
        return _findSecondary(offset, q1, q2, q3);
    }

    public String findName(int[] q, int qlen) {
        if (qlen >= 4) {
            int hash = calcHash(q, qlen);
            int offset = _calcOffset(hash);
            int[] hashArea = this._hashArea;
            int len = hashArea[offset + 3];
            if (hash == hashArea[offset] && len == qlen && _verifyLongName(q, qlen, hashArea[offset + 1])) {
                return this._names[offset >> 2];
            }
            if (len == 0) {
                return null;
            }
            int offset2 = this._secondaryStart + ((offset >> 3) << 2);
            int len2 = hashArea[offset2 + 3];
            if (hash == hashArea[offset2] && len2 == qlen && _verifyLongName(q, qlen, hashArea[offset2 + 1])) {
                return this._names[offset2 >> 2];
            }
            return _findSecondary(offset, hash, q, qlen);
        } else if (qlen == 3) {
            return findName(q[0], q[1], q[2]);
        } else {
            if (qlen == 2) {
                return findName(q[0], q[1]);
            }
            return findName(q[0]);
        }
    }

    private final int _calcOffset(int hash) {
        return (hash & (this._hashSize - 1)) << 2;
    }

    private String _findSecondary(int origOffset, int q1) {
        int offset = this._tertiaryStart + ((origOffset >> (this._tertiaryShift + 2)) << this._tertiaryShift);
        int[] hashArea = this._hashArea;
        int end = offset + (1 << this._tertiaryShift);
        while (offset < end) {
            int len = hashArea[offset + 3];
            if (q1 == hashArea[offset] && 1 == len) {
                return this._names[offset >> 2];
            }
            if (len == 0) {
                return null;
            }
            offset += 4;
        }
        for (int offset2 = _spilloverStart(); offset2 < this._spilloverEnd; offset2 += 4) {
            if (q1 == hashArea[offset2] && 1 == hashArea[offset2 + 3]) {
                return this._names[offset2 >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int origOffset, int q1, int q2) {
        int offset = this._tertiaryStart + ((origOffset >> (this._tertiaryShift + 2)) << this._tertiaryShift);
        int[] hashArea = this._hashArea;
        int end = offset + (1 << this._tertiaryShift);
        while (offset < end) {
            int len = hashArea[offset + 3];
            if (q1 == hashArea[offset] && q2 == hashArea[offset + 1] && 2 == len) {
                return this._names[offset >> 2];
            }
            if (len == 0) {
                return null;
            }
            offset += 4;
        }
        for (int offset2 = _spilloverStart(); offset2 < this._spilloverEnd; offset2 += 4) {
            if (q1 == hashArea[offset2] && q2 == hashArea[offset2 + 1] && 2 == hashArea[offset2 + 3]) {
                return this._names[offset2 >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int origOffset, int q1, int q2, int q3) {
        int offset = this._tertiaryStart + ((origOffset >> (this._tertiaryShift + 2)) << this._tertiaryShift);
        int[] hashArea = this._hashArea;
        int end = offset + (1 << this._tertiaryShift);
        while (offset < end) {
            int len = hashArea[offset + 3];
            if (q1 == hashArea[offset] && q2 == hashArea[offset + 1] && q3 == hashArea[offset + 2] && 3 == len) {
                return this._names[offset >> 2];
            }
            if (len == 0) {
                return null;
            }
            offset += 4;
        }
        for (int offset2 = _spilloverStart(); offset2 < this._spilloverEnd; offset2 += 4) {
            if (q1 == hashArea[offset2] && q2 == hashArea[offset2 + 1] && q3 == hashArea[offset2 + 2] && 3 == hashArea[offset2 + 3]) {
                return this._names[offset2 >> 2];
            }
        }
        return null;
    }

    private String _findSecondary(int origOffset, int hash, int[] q, int qlen) {
        int offset = this._tertiaryStart + ((origOffset >> (this._tertiaryShift + 2)) << this._tertiaryShift);
        int[] hashArea = this._hashArea;
        int end = offset + (1 << this._tertiaryShift);
        while (offset < end) {
            int len = hashArea[offset + 3];
            if (hash == hashArea[offset] && qlen == len && _verifyLongName(q, qlen, hashArea[offset + 1])) {
                return this._names[offset >> 2];
            }
            if (len == 0) {
                return null;
            }
            offset += 4;
        }
        for (int offset2 = _spilloverStart(); offset2 < this._spilloverEnd; offset2 += 4) {
            if (hash == hashArea[offset2] && qlen == hashArea[offset2 + 3] && _verifyLongName(q, qlen, hashArea[offset2 + 1])) {
                return this._names[offset2 >> 2];
            }
        }
        return null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        r2 = r1 + 1;
        r3 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0032, code lost:
        if (r8[r1] == r0[r10]) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0037, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        r2 = r1 + 1;
        r3 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        if (r8[r1] == r0[r10]) goto L_0x0046;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0046, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        r2 = r1 + 1;
        r3 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0050, code lost:
        if (r8[r1] == r0[r10]) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0052, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0055, code lost:
        r1 = r2 + 1;
        r10 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x005d, code lost:
        if (r8[r2] != r0[r3]) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x005f, code lost:
        r2 = r1 + 1;
        r3 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        if (r8[r1] == r0[r10]) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0069, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x006c, code lost:
        r1 = r2 + 1;
        r10 = r3 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0074, code lost:
        if (r8[r2] != r0[r3]) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x001b, code lost:
        r2 = r1 + 1;
        r3 = r10 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        if (r8[r1] == r0[r10]) goto L_0x0028;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0025, code lost:
        r1 = r2;
        r10 = r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean _verifyLongName(int[] r8, int r9, int r10) {
        /*
            r7 = this;
            r4 = 0
            int[] r0 = r7._hashArea
            r1 = 0
            switch(r9) {
                case 4: goto L_0x0048;
                case 5: goto L_0x0039;
                case 6: goto L_0x002a;
                case 7: goto L_0x001b;
                case 8: goto L_0x000c;
                default: goto L_0x0007;
            }
        L_0x0007:
            boolean r4 = r7._verifyLongName2(r8, r9, r10)
        L_0x000b:
            return r4
        L_0x000c:
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x0019
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x0019:
            r1 = r2
            r10 = r3
        L_0x001b:
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x0028
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x0028:
            r1 = r2
            r10 = r3
        L_0x002a:
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x0037
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x0037:
            r1 = r2
            r10 = r3
        L_0x0039:
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x0046
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x0046:
            r1 = r2
            r10 = r3
        L_0x0048:
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x0055
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x0055:
            int r1 = r2 + 1
            r5 = r8[r2]
            int r10 = r3 + 1
            r6 = r0[r3]
            if (r5 != r6) goto L_0x000b
            int r2 = r1 + 1
            r5 = r8[r1]
            int r3 = r10 + 1
            r6 = r0[r10]
            if (r5 == r6) goto L_0x006c
            r1 = r2
            r10 = r3
            goto L_0x000b
        L_0x006c:
            int r1 = r2 + 1
            r5 = r8[r2]
            int r10 = r3 + 1
            r6 = r0[r3]
            if (r5 != r6) goto L_0x000b
            r4 = 1
            goto L_0x000b
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.sym.ByteQuadsCanonicalizer._verifyLongName(int[], int, int):boolean");
    }

    private boolean _verifyLongName2(int[] q, int qlen, int spillOffset) {
        int ix = 0;
        while (true) {
            int ix2 = ix + 1;
            int spillOffset2 = spillOffset + 1;
            if (q[ix] != this._hashArea[spillOffset]) {
                return false;
            }
            if (ix2 >= qlen) {
                return true;
            }
            ix = ix2;
            spillOffset = spillOffset2;
        }
    }

    public String addName(String name, int[] q, int qlen) {
        int offset;
        _verifySharing();
        if (this._intern) {
            name = InternCache.instance.intern(name);
        }
        switch (qlen) {
            case 1:
                offset = _findOffsetForAdd(calcHash(q[0]));
                this._hashArea[offset] = q[0];
                this._hashArea[offset + 3] = 1;
                break;
            case 2:
                offset = _findOffsetForAdd(calcHash(q[0], q[1]));
                this._hashArea[offset] = q[0];
                this._hashArea[offset + 1] = q[1];
                this._hashArea[offset + 3] = 2;
                break;
            case 3:
                offset = _findOffsetForAdd(calcHash(q[0], q[1], q[2]));
                this._hashArea[offset] = q[0];
                this._hashArea[offset + 1] = q[1];
                this._hashArea[offset + 2] = q[2];
                this._hashArea[offset + 3] = 3;
                break;
            default:
                int hash = calcHash(q, qlen);
                offset = _findOffsetForAdd(hash);
                this._hashArea[offset] = hash;
                this._hashArea[offset + 1] = _appendLongName(q, qlen);
                this._hashArea[offset + 3] = qlen;
                break;
        }
        this._names[offset >> 2] = name;
        this._count++;
        _verifyNeedForRehash();
        return name;
    }

    private void _verifyNeedForRehash() {
        if (this._count <= (this._hashSize >> 1)) {
            return;
        }
        if (((this._spilloverEnd - _spilloverStart()) >> 2) > ((this._count + 1) >> 7) || ((double) this._count) > ((double) this._hashSize) * 0.8d) {
            this._needRehash = true;
        }
    }

    private void _verifySharing() {
        if (this._hashShared) {
            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length);
            this._names = (String[]) Arrays.copyOf(this._names, this._names.length);
            this._hashShared = false;
            _verifyNeedForRehash();
        }
        if (this._needRehash) {
            rehash();
        }
    }

    private int _findOffsetForAdd(int hash) {
        int offset = _calcOffset(hash);
        int[] hashArea = this._hashArea;
        if (hashArea[offset + 3] == 0) {
            return offset;
        }
        int offset2 = this._secondaryStart + ((offset >> 3) << 2);
        if (hashArea[offset2 + 3] == 0) {
            return offset2;
        }
        int offset22 = this._tertiaryStart + ((offset >> (this._tertiaryShift + 2)) << this._tertiaryShift);
        int end = offset22 + (1 << this._tertiaryShift);
        while (offset22 < end) {
            if (hashArea[offset22 + 3] == 0) {
                return offset22;
            }
            offset22 += 4;
        }
        int offset3 = this._spilloverEnd;
        this._spilloverEnd += 4;
        if (this._spilloverEnd >= (this._hashSize << 3)) {
            if (this._failOnDoS) {
                _reportTooManyCollisions();
            }
            this._needRehash = true;
        }
        return offset3;
    }

    private int _appendLongName(int[] quads, int qlen) {
        int start = this._longNameOffset;
        if (start + qlen > this._hashArea.length) {
            this._hashArea = Arrays.copyOf(this._hashArea, this._hashArea.length + Math.max((start + qlen) - this._hashArea.length, Math.min(4096, this._hashSize)));
        }
        System.arraycopy(quads, 0, this._hashArea, start, qlen);
        this._longNameOffset += qlen;
        return start;
    }

    public int calcHash(int q1) {
        int hash = q1 ^ this._seed;
        int hash2 = hash + (hash >>> 16);
        int hash3 = hash2 ^ (hash2 << 3);
        return hash3 + (hash3 >>> 12);
    }

    public int calcHash(int q1, int q2) {
        int hash = q1;
        int hash2 = hash + (hash >>> 15);
        int hash3 = ((hash2 ^ (hash2 >>> 9)) + (q2 * 33)) ^ this._seed;
        int hash4 = hash3 + (hash3 >>> 16);
        int hash5 = hash4 ^ (hash4 >>> 4);
        return hash5 + (hash5 << 3);
    }

    public int calcHash(int q1, int q2, int q3) {
        int hash = q1 ^ this._seed;
        int hash2 = (((hash + (hash >>> 9)) * 31) + q2) * 33;
        int hash3 = (hash2 + (hash2 >>> 15)) ^ q3;
        int hash4 = hash3 + (hash3 >>> 4);
        int hash5 = hash4 + (hash4 >>> 15);
        return hash5 ^ (hash5 << 9);
    }

    public int calcHash(int[] q, int qlen) {
        if (qlen < 4) {
            throw new IllegalArgumentException();
        }
        int hash = q[0] ^ this._seed;
        int hash2 = hash + (hash >>> 9) + q[1];
        int hash3 = ((hash2 + (hash2 >>> 15)) * 33) ^ q[2];
        int hash4 = hash3 + (hash3 >>> 4);
        for (int i = 3; i < qlen; i++) {
            int next = q[i];
            hash4 += next ^ (next >> 21);
        }
        int hash5 = hash4 * 65599;
        int hash6 = hash5 + (hash5 >>> 19);
        return hash6 ^ (hash6 << 5);
    }

    private void rehash() {
        this._needRehash = false;
        this._hashShared = false;
        int[] oldHashArea = this._hashArea;
        String[] oldNames = this._names;
        int oldSize = this._hashSize;
        int oldCount = this._count;
        int newSize = oldSize + oldSize;
        int oldEnd = this._spilloverEnd;
        if (newSize > 65536) {
            nukeSymbols(true);
            return;
        }
        this._hashArea = new int[(oldHashArea.length + (oldSize << 3))];
        this._hashSize = newSize;
        this._secondaryStart = newSize << 2;
        this._tertiaryStart = this._secondaryStart + (this._secondaryStart >> 1);
        this._tertiaryShift = _calcTertiaryShift(newSize);
        this._names = new String[(oldNames.length << 1)];
        nukeSymbols(false);
        int copyCount = 0;
        int[] q = new int[16];
        int end = oldEnd;
        for (int offset = 0; offset < end; offset += 4) {
            int len = oldHashArea[offset + 3];
            if (len != 0) {
                copyCount++;
                String name = oldNames[offset >> 2];
                switch (len) {
                    case 1:
                        q[0] = oldHashArea[offset];
                        addName(name, q, 1);
                        break;
                    case 2:
                        q[0] = oldHashArea[offset];
                        q[1] = oldHashArea[offset + 1];
                        addName(name, q, 2);
                        break;
                    case 3:
                        q[0] = oldHashArea[offset];
                        q[1] = oldHashArea[offset + 1];
                        q[2] = oldHashArea[offset + 2];
                        addName(name, q, 3);
                        break;
                    default:
                        if (len > q.length) {
                            q = new int[len];
                        }
                        System.arraycopy(oldHashArea, oldHashArea[offset + 1], q, 0, len);
                        addName(name, q, len);
                        break;
                }
            }
        }
        if (copyCount != oldCount) {
            throw new IllegalStateException("Failed rehash(): old count=" + oldCount + ", copyCount=" + copyCount);
        }
    }

    private void nukeSymbols(boolean fill) {
        this._count = 0;
        this._spilloverEnd = _spilloverStart();
        this._longNameOffset = this._hashSize << 3;
        if (fill) {
            Arrays.fill(this._hashArea, 0);
            Arrays.fill(this._names, null);
        }
    }

    private final int _spilloverStart() {
        int offset = this._hashSize;
        return (offset << 3) - offset;
    }

    /* access modifiers changed from: protected */
    public void _reportTooManyCollisions() {
        if (this._hashSize > 1024) {
            throw new IllegalStateException("Spill-over slots in symbol table with " + this._count + " entries, hash area of " + this._hashSize + " slots is now full (all " + (this._hashSize >> 3) + " slots -- suspect a DoS attack based on hash collisions." + " You can disable the check via `JsonFactory.Feature.FAIL_ON_SYMBOL_HASH_OVERFLOW`");
        }
    }

    static int _calcTertiaryShift(int primarySlots) {
        int tertSlots = primarySlots >> 2;
        if (tertSlots < 64) {
            return 4;
        }
        if (tertSlots <= 256) {
            return 5;
        }
        if (tertSlots <= 1024) {
            return 6;
        }
        return 7;
    }
}
