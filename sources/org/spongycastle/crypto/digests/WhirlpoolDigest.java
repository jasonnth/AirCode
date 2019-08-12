package org.spongycastle.crypto.digests;

import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.enums.HelpCenterTopic;
import com.airbnb.android.managelisting.settings.DatesModalActivity;
import com.facebook.imageutils.JfifUtil;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.cbeff.ISO781611;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.asn1.eac.CertificateBody;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Memoable;

public final class WhirlpoolDigest implements ExtendedDigest, Memoable {
    private static final int BITCOUNT_ARRAY_SIZE = 32;
    private static final int BYTE_LENGTH = 64;

    /* renamed from: C0 */
    private static final long[] f6601C0 = new long[256];

    /* renamed from: C1 */
    private static final long[] f6602C1 = new long[256];

    /* renamed from: C2 */
    private static final long[] f6603C2 = new long[256];

    /* renamed from: C3 */
    private static final long[] f6604C3 = new long[256];

    /* renamed from: C4 */
    private static final long[] f6605C4 = new long[256];

    /* renamed from: C5 */
    private static final long[] f6606C5 = new long[256];

    /* renamed from: C6 */
    private static final long[] f6607C6 = new long[256];

    /* renamed from: C7 */
    private static final long[] f6608C7 = new long[256];
    private static final int DIGEST_LENGTH_BYTES = 64;
    private static final short[] EIGHT = new short[32];
    private static final int REDUCTION_POLYNOMIAL = 285;
    private static final int ROUNDS = 10;
    private static final int[] SBOX = {24, 35, 198, 232, 135, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA256, 1, 79, 54, CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, 210, 245, EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY, 111, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 82, 96, 188, CipherSuite.TLS_DH_anon_WITH_SEED_CBC_SHA, 142, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, 12, EACTags.SECURITY_ENVIRONMENT_TEMPLATE, 53, 29, 224, JfifUtil.MARKER_RST7, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 46, 75, 254, 87, 21, LDSFile.EF_SOD_TAG, 55, 229, CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 240, 74, JfifUtil.MARKER_SOS, 88, 201, 41, 10, CipherSuite.TLS_PSK_WITH_NULL_SHA384, 160, 107, 133, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 93, 16, 244, 203, 62, 5, 103, 228, 39, 65, CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 125, 149, JfifUtil.MARKER_SOI, 251, 238, EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE, 102, 221, 23, 71, 158, CityRegistrationController.RC_CHOOSE_PHOTO, 45, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 7, CipherSuite.TLS_RSA_PSK_WITH_AES_256_GCM_SHA384, 90, ISO781611.CREATION_DATE_AND_TIME_TAG, 51, 99, 2, CipherSuite.TLS_DHE_PSK_WITH_AES_128_GCM_SHA256, 113, 200, 25, 73, JfifUtil.MARKER_EOI, 242, 227, 91, 136, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 38, 50, CipherSuite.TLS_PSK_WITH_NULL_SHA256, 233, 15, 213, 128, 190, 205, 52, 72, 255, EACTags.SECURITY_SUPPORT_TEMPLATE, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 95, 32, 104, 26, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, 180, 84, CipherSuite.TLS_RSA_PSK_WITH_3DES_EDE_CBC_SHA, 34, 100, 241, 115, 18, 64, 8, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 236, 219, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA, 61, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 0, 207, 43, LDSFile.EF_DG4_TAG, ISO781611.BIOMETRIC_SUBTYPE_TAG, 214, 27, CipherSuite.TLS_DHE_PSK_WITH_NULL_SHA384, CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA384, 106, 80, 69, 243, 48, 239, 63, 85, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, HelpCenterTopic.HOST_PAYOUT, 101, CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA256, 47, 192, 222, 28, 253, 77, CipherSuite.TLS_RSA_PSK_WITH_RC4_128_SHA, LDSFile.EF_DG2_TAG, 6, CipherSuite.TLS_PSK_WITH_RC4_128_SHA, CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA256, 230, 14, 31, 98, 212, 168, 150, 249, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 37, 89, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 114, 57, 76, 94, EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY, 56, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 209, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 226, 97, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 33, CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256, 30, 67, 199, 252, 4, 81, CipherSuite.TLS_DHE_DSS_WITH_SEED_CBC_SHA, 109, 13, 250, DatesModalActivity.RESULT_CODE, EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE, 36, 59, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 206, 17, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, 78, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, 235, 60, 129, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA, 247, CipherSuite.TLS_RSA_PSK_WITH_NULL_SHA384, 19, 44, Primes.SMALL_FACTOR_LIMIT, 231, 110, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 3, 86, 68, CertificateBody.profileType, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 42, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 83, 220, 11, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 108, 49, 116, 246, 70, CipherSuite.TLS_RSA_PSK_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA, 20, JfifUtil.MARKER_APP1, 22, 58, 105, 9, 112, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA256, JfifUtil.MARKER_RST0, 237, 204, 66, CipherSuite.TLS_DH_RSA_WITH_SEED_CBC_SHA, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 40, 92, 248, 134};

    /* renamed from: _K */
    private long[] f6609_K = new long[8];

    /* renamed from: _L */
    private long[] f6610_L = new long[8];
    private short[] _bitCount = new short[32];
    private long[] _block = new long[8];
    private byte[] _buffer = new byte[64];
    private int _bufferPos = 0;
    private long[] _hash = new long[8];
    private final long[] _rc = new long[11];
    private long[] _state = new long[8];

    static {
        EIGHT[31] = 8;
    }

    public WhirlpoolDigest() {
        for (int i = 0; i < 256; i++) {
            int v1 = SBOX[i];
            int v2 = maskWithReductionPolynomial(v1 << 1);
            int v4 = maskWithReductionPolynomial(v2 << 1);
            int v5 = v4 ^ v1;
            int v8 = maskWithReductionPolynomial(v4 << 1);
            int v9 = v8 ^ v1;
            f6601C0[i] = packIntoLong(v1, v1, v4, v1, v8, v5, v2, v9);
            f6602C1[i] = packIntoLong(v9, v1, v1, v4, v1, v8, v5, v2);
            f6603C2[i] = packIntoLong(v2, v9, v1, v1, v4, v1, v8, v5);
            f6604C3[i] = packIntoLong(v5, v2, v9, v1, v1, v4, v1, v8);
            f6605C4[i] = packIntoLong(v8, v5, v2, v9, v1, v1, v4, v1);
            f6606C5[i] = packIntoLong(v1, v8, v5, v2, v9, v1, v1, v4);
            f6607C6[i] = packIntoLong(v4, v1, v8, v5, v2, v9, v1, v1);
            f6608C7[i] = packIntoLong(v1, v4, v1, v8, v5, v2, v9, v1);
        }
        this._rc[0] = 0;
        for (int r = 1; r <= 10; r++) {
            int i2 = (r - 1) * 8;
            this._rc[r] = (((((((f6601C0[i2] & -72057594037927936L) ^ (f6602C1[i2 + 1] & 71776119061217280L)) ^ (f6603C2[i2 + 2] & 280375465082880L)) ^ (f6604C3[i2 + 3] & 1095216660480L)) ^ (f6605C4[i2 + 4] & 4278190080L)) ^ (f6606C5[i2 + 5] & 16711680)) ^ (f6607C6[i2 + 6] & 65280)) ^ (f6608C7[i2 + 7] & 255);
        }
    }

    private long packIntoLong(int b7, int b6, int b5, int b4, int b3, int b2, int b1, int b0) {
        return (((((((((long) b7) << 56) ^ (((long) b6) << 48)) ^ (((long) b5) << 40)) ^ (((long) b4) << 32)) ^ (((long) b3) << 24)) ^ (((long) b2) << 16)) ^ (((long) b1) << 8)) ^ ((long) b0);
    }

    private int maskWithReductionPolynomial(int input) {
        int rv = input;
        if (((long) rv) >= 256) {
            return rv ^ REDUCTION_POLYNOMIAL;
        }
        return rv;
    }

    public WhirlpoolDigest(WhirlpoolDigest originalDigest) {
        reset(originalDigest);
    }

    public String getAlgorithmName() {
        return "Whirlpool";
    }

    public int getDigestSize() {
        return 64;
    }

    public int doFinal(byte[] out, int outOff) {
        finish();
        for (int i = 0; i < 8; i++) {
            convertLongToByteArray(this._hash[i], out, (i * 8) + outOff);
        }
        reset();
        return getDigestSize();
    }

    public void reset() {
        this._bufferPos = 0;
        Arrays.fill(this._bitCount, 0);
        Arrays.fill(this._buffer, 0);
        Arrays.fill(this._hash, 0);
        Arrays.fill(this.f6609_K, 0);
        Arrays.fill(this.f6610_L, 0);
        Arrays.fill(this._block, 0);
        Arrays.fill(this._state, 0);
    }

    private void processFilledBuffer(byte[] in, int inOff) {
        for (int i = 0; i < this._state.length; i++) {
            this._block[i] = bytesToLongFromBuffer(this._buffer, i * 8);
        }
        processBlock();
        this._bufferPos = 0;
        Arrays.fill(this._buffer, 0);
    }

    private long bytesToLongFromBuffer(byte[] buffer, int startPos) {
        return ((((long) buffer[startPos + 0]) & 255) << 56) | ((((long) buffer[startPos + 1]) & 255) << 48) | ((((long) buffer[startPos + 2]) & 255) << 40) | ((((long) buffer[startPos + 3]) & 255) << 32) | ((((long) buffer[startPos + 4]) & 255) << 24) | ((((long) buffer[startPos + 5]) & 255) << 16) | ((((long) buffer[startPos + 6]) & 255) << 8) | (((long) buffer[startPos + 7]) & 255);
    }

    private void convertLongToByteArray(long inputLong, byte[] outputArray, int offSet) {
        for (int i = 0; i < 8; i++) {
            outputArray[offSet + i] = (byte) ((int) ((inputLong >> (56 - (i * 8))) & 255));
        }
    }

    /* access modifiers changed from: protected */
    public void processBlock() {
        for (int i = 0; i < 8; i++) {
            long[] jArr = this._state;
            long j = this._block[i];
            long[] jArr2 = this.f6609_K;
            long j2 = this._hash[i];
            jArr2[i] = j2;
            jArr[i] = j ^ j2;
        }
        for (int round = 1; round <= 10; round++) {
            for (int i2 = 0; i2 < 8; i2++) {
                this.f6610_L[i2] = 0;
                long[] jArr3 = this.f6610_L;
                jArr3[i2] = jArr3[i2] ^ f6601C0[((int) (this.f6609_K[(i2 + 0) & 7] >>> 56)) & 255];
                long[] jArr4 = this.f6610_L;
                jArr4[i2] = jArr4[i2] ^ f6602C1[((int) (this.f6609_K[(i2 - 1) & 7] >>> 48)) & 255];
                long[] jArr5 = this.f6610_L;
                jArr5[i2] = jArr5[i2] ^ f6603C2[((int) (this.f6609_K[(i2 - 2) & 7] >>> 40)) & 255];
                long[] jArr6 = this.f6610_L;
                jArr6[i2] = jArr6[i2] ^ f6604C3[((int) (this.f6609_K[(i2 - 3) & 7] >>> 32)) & 255];
                long[] jArr7 = this.f6610_L;
                jArr7[i2] = jArr7[i2] ^ f6605C4[((int) (this.f6609_K[(i2 - 4) & 7] >>> 24)) & 255];
                long[] jArr8 = this.f6610_L;
                jArr8[i2] = jArr8[i2] ^ f6606C5[((int) (this.f6609_K[(i2 - 5) & 7] >>> 16)) & 255];
                long[] jArr9 = this.f6610_L;
                jArr9[i2] = jArr9[i2] ^ f6607C6[((int) (this.f6609_K[(i2 - 6) & 7] >>> 8)) & 255];
                long[] jArr10 = this.f6610_L;
                jArr10[i2] = jArr10[i2] ^ f6608C7[((int) this.f6609_K[(i2 - 7) & 7]) & 255];
            }
            System.arraycopy(this.f6610_L, 0, this.f6609_K, 0, this.f6609_K.length);
            long[] jArr11 = this.f6609_K;
            jArr11[0] = jArr11[0] ^ this._rc[round];
            for (int i3 = 0; i3 < 8; i3++) {
                this.f6610_L[i3] = this.f6609_K[i3];
                long[] jArr12 = this.f6610_L;
                jArr12[i3] = jArr12[i3] ^ f6601C0[((int) (this._state[(i3 + 0) & 7] >>> 56)) & 255];
                long[] jArr13 = this.f6610_L;
                jArr13[i3] = jArr13[i3] ^ f6602C1[((int) (this._state[(i3 - 1) & 7] >>> 48)) & 255];
                long[] jArr14 = this.f6610_L;
                jArr14[i3] = jArr14[i3] ^ f6603C2[((int) (this._state[(i3 - 2) & 7] >>> 40)) & 255];
                long[] jArr15 = this.f6610_L;
                jArr15[i3] = jArr15[i3] ^ f6604C3[((int) (this._state[(i3 - 3) & 7] >>> 32)) & 255];
                long[] jArr16 = this.f6610_L;
                jArr16[i3] = jArr16[i3] ^ f6605C4[((int) (this._state[(i3 - 4) & 7] >>> 24)) & 255];
                long[] jArr17 = this.f6610_L;
                jArr17[i3] = jArr17[i3] ^ f6606C5[((int) (this._state[(i3 - 5) & 7] >>> 16)) & 255];
                long[] jArr18 = this.f6610_L;
                jArr18[i3] = jArr18[i3] ^ f6607C6[((int) (this._state[(i3 - 6) & 7] >>> 8)) & 255];
                long[] jArr19 = this.f6610_L;
                jArr19[i3] = jArr19[i3] ^ f6608C7[((int) this._state[(i3 - 7) & 7]) & 255];
            }
            System.arraycopy(this.f6610_L, 0, this._state, 0, this._state.length);
        }
        for (int i4 = 0; i4 < 8; i4++) {
            long[] jArr20 = this._hash;
            jArr20[i4] = jArr20[i4] ^ (this._state[i4] ^ this._block[i4]);
        }
    }

    public void update(byte in) {
        this._buffer[this._bufferPos] = in;
        this._bufferPos++;
        if (this._bufferPos == this._buffer.length) {
            processFilledBuffer(this._buffer, 0);
        }
        increment();
    }

    private void increment() {
        int carry = 0;
        for (int i = this._bitCount.length - 1; i >= 0; i--) {
            int sum = (this._bitCount[i] & 255) + EIGHT[i] + carry;
            carry = sum >>> 8;
            this._bitCount[i] = (short) (sum & 255);
        }
    }

    public void update(byte[] in, int inOff, int len) {
        while (len > 0) {
            update(in[inOff]);
            inOff++;
            len--;
        }
    }

    private void finish() {
        byte[] bitLength = copyBitLength();
        byte[] bArr = this._buffer;
        int i = this._bufferPos;
        this._bufferPos = i + 1;
        bArr[i] = (byte) (bArr[i] | ISOFileInfo.DATA_BYTES1);
        if (this._bufferPos == this._buffer.length) {
            processFilledBuffer(this._buffer, 0);
        }
        if (this._bufferPos > 32) {
            while (this._bufferPos != 0) {
                update(0);
            }
        }
        while (this._bufferPos <= 32) {
            update(0);
        }
        System.arraycopy(bitLength, 0, this._buffer, 32, bitLength.length);
        processFilledBuffer(this._buffer, 0);
    }

    private byte[] copyBitLength() {
        byte[] rv = new byte[32];
        for (int i = 0; i < rv.length; i++) {
            rv[i] = (byte) (this._bitCount[i] & 255);
        }
        return rv;
    }

    public int getByteLength() {
        return 64;
    }

    public Memoable copy() {
        return new WhirlpoolDigest(this);
    }

    public void reset(Memoable other) {
        WhirlpoolDigest originalDigest = (WhirlpoolDigest) other;
        System.arraycopy(originalDigest._rc, 0, this._rc, 0, this._rc.length);
        System.arraycopy(originalDigest._buffer, 0, this._buffer, 0, this._buffer.length);
        this._bufferPos = originalDigest._bufferPos;
        System.arraycopy(originalDigest._bitCount, 0, this._bitCount, 0, this._bitCount.length);
        System.arraycopy(originalDigest._hash, 0, this._hash, 0, this._hash.length);
        System.arraycopy(originalDigest.f6609_K, 0, this.f6609_K, 0, this.f6609_K.length);
        System.arraycopy(originalDigest.f6610_L, 0, this.f6610_L, 0, this.f6610_L.length);
        System.arraycopy(originalDigest._block, 0, this._block, 0, this._block.length);
        System.arraycopy(originalDigest._state, 0, this._state, 0, this._state.length);
    }
}
