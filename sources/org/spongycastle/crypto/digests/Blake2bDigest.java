package org.spongycastle.crypto.digests;

import org.jmrtd.PassportService;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.util.Arrays;

public class Blake2bDigest implements ExtendedDigest {
    private static final int BLOCK_LENGTH_BYTES = 128;
    private static final long[] blake2b_IV = {7640891576956012808L, -4942790177534073029L, 4354685564936845355L, -6534734903238641935L, 5840696475078001361L, -7276294671716946913L, 2270897969802886507L, 6620516959819538809L};
    private static final byte[][] blake2b_sigma = {new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, PassportService.SF_DG11, PassportService.SF_DG12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, PassportService.SF_DG12, 0, 2, PassportService.SF_DG11, 7, 5, 3}, new byte[]{PassportService.SF_DG11, 8, PassportService.SF_DG12, 0, 5, 2, 15, 13, 10, 14, 3, 6, 7, 1, 9, 4}, new byte[]{7, 9, 3, 1, 13, PassportService.SF_DG12, PassportService.SF_DG11, 14, 2, 6, 5, 10, 4, 0, 15, 8}, new byte[]{9, 0, 5, 7, 2, 4, 10, 15, 14, 1, PassportService.SF_DG11, PassportService.SF_DG12, 6, 8, 3, 13}, new byte[]{2, PassportService.SF_DG12, 6, 10, 0, PassportService.SF_DG11, 8, 3, 4, 13, 7, 5, 15, 14, 1, 9}, new byte[]{PassportService.SF_DG12, 5, 1, 15, 14, 13, 4, 10, 0, 7, 6, 3, 9, 2, 8, PassportService.SF_DG11}, new byte[]{13, PassportService.SF_DG11, 7, 14, PassportService.SF_DG12, 1, 3, 9, 5, 0, 15, 4, 8, 6, 2, 10}, new byte[]{6, 15, 14, 9, PassportService.SF_DG11, 3, 0, 8, PassportService.SF_DG12, 2, 13, 7, 1, 4, 10, 5}, new byte[]{10, 2, 8, 4, 7, 6, 1, 5, 15, PassportService.SF_DG11, 9, 14, 3, PassportService.SF_DG12, 13, 0}, new byte[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, PassportService.SF_DG11, PassportService.SF_DG12, 13, 14, 15}, new byte[]{14, 10, 4, 8, 9, 15, 13, 6, 1, PassportService.SF_DG12, 0, 2, PassportService.SF_DG11, 7, 5, 3}};
    private static int rOUNDS = 12;
    private byte[] buffer;
    private int bufferPos;
    private long[] chainValue;
    private int digestLength;

    /* renamed from: f0 */
    private long f6488f0;
    private long[] internalState;
    private byte[] key;
    private int keyLength;
    private byte[] personalization;
    private byte[] salt;

    /* renamed from: t0 */
    private long f6489t0;

    /* renamed from: t1 */
    private long f6490t1;

    public Blake2bDigest() {
        this(512);
    }

    public Blake2bDigest(Blake2bDigest digest) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f6489t0 = 0;
        this.f6490t1 = 0;
        this.f6488f0 = 0;
        this.bufferPos = digest.bufferPos;
        this.buffer = Arrays.clone(digest.buffer);
        this.keyLength = digest.keyLength;
        this.key = Arrays.clone(digest.key);
        this.digestLength = digest.digestLength;
        this.chainValue = Arrays.clone(digest.chainValue);
        this.personalization = Arrays.clone(this.personalization);
    }

    public Blake2bDigest(int digestLength2) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f6489t0 = 0;
        this.f6490t1 = 0;
        this.f6488f0 = 0;
        if (digestLength2 == 160 || digestLength2 == 256 || digestLength2 == 384 || digestLength2 == 512) {
            this.buffer = new byte[128];
            this.keyLength = 0;
            this.digestLength = digestLength2 / 8;
            init();
            return;
        }
        throw new IllegalArgumentException("Blake2b digest restricted to one of [160, 256, 384, 512]");
    }

    public Blake2bDigest(byte[] key2) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f6489t0 = 0;
        this.f6490t1 = 0;
        this.f6488f0 = 0;
        this.buffer = new byte[128];
        if (key2 != null) {
            this.key = new byte[key2.length];
            System.arraycopy(key2, 0, this.key, 0, key2.length);
            if (key2.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = key2.length;
            System.arraycopy(key2, 0, this.buffer, 0, key2.length);
            this.bufferPos = 128;
        }
        this.digestLength = 64;
        init();
    }

    public Blake2bDigest(byte[] key2, int digestLength2, byte[] salt2, byte[] personalization2) {
        this.digestLength = 64;
        this.keyLength = 0;
        this.salt = null;
        this.personalization = null;
        this.key = null;
        this.buffer = null;
        this.bufferPos = 0;
        this.internalState = new long[16];
        this.chainValue = null;
        this.f6489t0 = 0;
        this.f6490t1 = 0;
        this.f6488f0 = 0;
        this.buffer = new byte[128];
        if (digestLength2 < 1 || digestLength2 > 64) {
            throw new IllegalArgumentException("Invalid digest length (required: 1 - 64)");
        }
        this.digestLength = digestLength2;
        if (salt2 != null) {
            if (salt2.length != 16) {
                throw new IllegalArgumentException("salt length must be exactly 16 bytes");
            }
            this.salt = new byte[16];
            System.arraycopy(salt2, 0, this.salt, 0, salt2.length);
        }
        if (personalization2 != null) {
            if (personalization2.length != 16) {
                throw new IllegalArgumentException("personalization length must be exactly 16 bytes");
            }
            this.personalization = new byte[16];
            System.arraycopy(personalization2, 0, this.personalization, 0, personalization2.length);
        }
        if (key2 != null) {
            this.key = new byte[key2.length];
            System.arraycopy(key2, 0, this.key, 0, key2.length);
            if (key2.length > 64) {
                throw new IllegalArgumentException("Keys > 64 are not supported");
            }
            this.keyLength = key2.length;
            System.arraycopy(key2, 0, this.buffer, 0, key2.length);
            this.bufferPos = 128;
        }
        init();
    }

    private void init() {
        if (this.chainValue == null) {
            this.chainValue = new long[8];
            this.chainValue[0] = blake2b_IV[0] ^ ((long) ((this.digestLength | (this.keyLength << 8)) | 16842752));
            this.chainValue[1] = blake2b_IV[1];
            this.chainValue[2] = blake2b_IV[2];
            this.chainValue[3] = blake2b_IV[3];
            this.chainValue[4] = blake2b_IV[4];
            this.chainValue[5] = blake2b_IV[5];
            if (this.salt != null) {
                long[] jArr = this.chainValue;
                jArr[4] = jArr[4] ^ bytes2long(this.salt, 0);
                long[] jArr2 = this.chainValue;
                jArr2[5] = jArr2[5] ^ bytes2long(this.salt, 8);
            }
            this.chainValue[6] = blake2b_IV[6];
            this.chainValue[7] = blake2b_IV[7];
            if (this.personalization != null) {
                long[] jArr3 = this.chainValue;
                jArr3[6] = jArr3[6] ^ bytes2long(this.personalization, 0);
                long[] jArr4 = this.chainValue;
                jArr4[7] = jArr4[7] ^ bytes2long(this.personalization, 8);
            }
        }
    }

    private void initializeInternalState() {
        System.arraycopy(this.chainValue, 0, this.internalState, 0, this.chainValue.length);
        System.arraycopy(blake2b_IV, 0, this.internalState, this.chainValue.length, 4);
        this.internalState[12] = this.f6489t0 ^ blake2b_IV[4];
        this.internalState[13] = this.f6490t1 ^ blake2b_IV[5];
        this.internalState[14] = this.f6488f0 ^ blake2b_IV[6];
        this.internalState[15] = blake2b_IV[7];
    }

    public void update(byte b) {
        if (128 - this.bufferPos == 0) {
            this.f6489t0 += 128;
            if (this.f6489t0 == 0) {
                this.f6490t1++;
            }
            compress(this.buffer, 0);
            Arrays.fill(this.buffer, 0);
            this.buffer[0] = b;
            this.bufferPos = 1;
            return;
        }
        this.buffer[this.bufferPos] = b;
        this.bufferPos++;
    }

    public void update(byte[] message, int offset, int len) {
        if (message != null && len != 0) {
            int remainingLength = 0;
            if (this.bufferPos != 0) {
                remainingLength = 128 - this.bufferPos;
                if (remainingLength < len) {
                    System.arraycopy(message, offset, this.buffer, this.bufferPos, remainingLength);
                    this.f6489t0 += 128;
                    if (this.f6489t0 == 0) {
                        this.f6490t1++;
                    }
                    compress(this.buffer, 0);
                    this.bufferPos = 0;
                    Arrays.fill(this.buffer, 0);
                } else {
                    System.arraycopy(message, offset, this.buffer, this.bufferPos, len);
                    this.bufferPos += len;
                    return;
                }
            }
            int blockWiseLastPos = (offset + len) - 128;
            int messagePos = offset + remainingLength;
            while (messagePos < blockWiseLastPos) {
                this.f6489t0 += 128;
                if (this.f6489t0 == 0) {
                    this.f6490t1++;
                }
                compress(message, messagePos);
                messagePos += 128;
            }
            System.arraycopy(message, messagePos, this.buffer, 0, (offset + len) - messagePos);
            this.bufferPos += (offset + len) - messagePos;
        }
    }

    public int doFinal(byte[] out, int outOffset) {
        this.f6488f0 = -1;
        this.f6489t0 += (long) this.bufferPos;
        if (this.f6489t0 < 0 && ((long) this.bufferPos) > (-this.f6489t0)) {
            this.f6490t1++;
        }
        compress(this.buffer, 0);
        Arrays.fill(this.buffer, 0);
        Arrays.fill(this.internalState, 0);
        int i = 0;
        while (i < this.chainValue.length && i * 8 < this.digestLength) {
            byte[] bytes = long2bytes(this.chainValue[i]);
            if (i * 8 < this.digestLength - 8) {
                System.arraycopy(bytes, 0, out, (i * 8) + outOffset, 8);
            } else {
                System.arraycopy(bytes, 0, out, (i * 8) + outOffset, this.digestLength - (i * 8));
            }
            i++;
        }
        Arrays.fill(this.chainValue, 0);
        reset();
        return this.digestLength;
    }

    public void reset() {
        this.bufferPos = 0;
        this.f6488f0 = 0;
        this.f6489t0 = 0;
        this.f6490t1 = 0;
        this.chainValue = null;
        if (this.key != null) {
            System.arraycopy(this.key, 0, this.buffer, 0, this.key.length);
            this.bufferPos = 128;
        }
        init();
    }

    private void compress(byte[] message, int messagePos) {
        initializeInternalState();
        long[] m = new long[16];
        for (int j = 0; j < 16; j++) {
            m[j] = bytes2long(message, (j * 8) + messagePos);
        }
        for (int round = 0; round < rOUNDS; round++) {
            m3958G(m[blake2b_sigma[round][0]], m[blake2b_sigma[round][1]], 0, 4, 8, 12);
            m3958G(m[blake2b_sigma[round][2]], m[blake2b_sigma[round][3]], 1, 5, 9, 13);
            m3958G(m[blake2b_sigma[round][4]], m[blake2b_sigma[round][5]], 2, 6, 10, 14);
            m3958G(m[blake2b_sigma[round][6]], m[blake2b_sigma[round][7]], 3, 7, 11, 15);
            m3958G(m[blake2b_sigma[round][8]], m[blake2b_sigma[round][9]], 0, 5, 10, 15);
            m3958G(m[blake2b_sigma[round][10]], m[blake2b_sigma[round][11]], 1, 6, 11, 12);
            m3958G(m[blake2b_sigma[round][12]], m[blake2b_sigma[round][13]], 2, 7, 8, 13);
            m3958G(m[blake2b_sigma[round][14]], m[blake2b_sigma[round][15]], 3, 4, 9, 14);
        }
        for (int offset = 0; offset < this.chainValue.length; offset++) {
            this.chainValue[offset] = (this.chainValue[offset] ^ this.internalState[offset]) ^ this.internalState[offset + 8];
        }
    }

    /* renamed from: G */
    private void m3958G(long m1, long m2, int posA, int posB, int posC, int posD) {
        this.internalState[posA] = this.internalState[posA] + this.internalState[posB] + m1;
        this.internalState[posD] = rotr64(this.internalState[posD] ^ this.internalState[posA], 32);
        this.internalState[posC] = this.internalState[posC] + this.internalState[posD];
        this.internalState[posB] = rotr64(this.internalState[posB] ^ this.internalState[posC], 24);
        this.internalState[posA] = this.internalState[posA] + this.internalState[posB] + m2;
        this.internalState[posD] = rotr64(this.internalState[posD] ^ this.internalState[posA], 16);
        this.internalState[posC] = this.internalState[posC] + this.internalState[posD];
        this.internalState[posB] = rotr64(this.internalState[posB] ^ this.internalState[posC], 63);
    }

    private long rotr64(long x, int rot) {
        return (x >>> rot) | (x << (64 - rot));
    }

    private final byte[] long2bytes(long longValue) {
        return new byte[]{(byte) ((int) longValue), (byte) ((int) (longValue >> 8)), (byte) ((int) (longValue >> 16)), (byte) ((int) (longValue >> 24)), (byte) ((int) (longValue >> 32)), (byte) ((int) (longValue >> 40)), (byte) ((int) (longValue >> 48)), (byte) ((int) (longValue >> 56))};
    }

    private final long bytes2long(byte[] byteArray, int offset) {
        return (((long) byteArray[offset]) & 255) | ((((long) byteArray[offset + 1]) & 255) << 8) | ((((long) byteArray[offset + 2]) & 255) << 16) | ((((long) byteArray[offset + 3]) & 255) << 24) | ((((long) byteArray[offset + 4]) & 255) << 32) | ((((long) byteArray[offset + 5]) & 255) << 40) | ((((long) byteArray[offset + 6]) & 255) << 48) | ((((long) byteArray[offset + 7]) & 255) << 56);
    }

    public String getAlgorithmName() {
        return "Blake2b";
    }

    public int getDigestSize() {
        return this.digestLength;
    }

    public int getByteLength() {
        return 128;
    }

    public void clearKey() {
        if (this.key != null) {
            Arrays.fill(this.key, 0);
            Arrays.fill(this.buffer, 0);
        }
    }

    public void clearSalt() {
        if (this.salt != null) {
            Arrays.fill(this.salt, 0);
        }
    }
}
