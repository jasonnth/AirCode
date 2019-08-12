package org.spongycastle.crypto.engines;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.BlockCipher;
import org.spongycastle.crypto.CipherParameters;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.OutputLengthException;
import org.spongycastle.crypto.params.KeyParameter;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Pack;

public class SM4Engine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: CK */
    private static final int[] f6700CK = {462357, 472066609, 943670861, 1415275113, 1886879365, -1936483679, -1464879427, -993275175, -521670923, -66909679, 404694573, 876298825, 1347903077, 1819507329, -2003855715, -1532251463, -1060647211, -589042959, -117504499, 337322537, 808926789, 1280531041, 1752135293, -2071227751, -1599623499, -1128019247, -656414995, -184876535, 269950501, 741554753, 1213159005, 1684763257};

    /* renamed from: FK */
    private static final int[] f6701FK = {-1548633402, 1453994832, 1736282519, -1301273892};
    private static final byte[] Sbox = {ISO7816.INS_UPDATE_BINARY, -112, -23, -2, -52, -31, 61, -73, 22, ISO7816.INS_READ_RECORD_STAMPED, 20, ISO7816.INS_ENVELOPE, 40, -5, ISO7816.INS_UNBLOCK_CHV, 5, 43, 103, -102, 118, ISO7816.INS_PSO, -66, 4, -61, -86, ISO7816.INS_REHABILITATE_CHV, 19, 38, 73, -122, 6, -103, -100, CVCAFile.CAR_TAG, 80, -12, -111, -17, -104, 122, 51, 84, PassportService.SF_DG11, 67, -19, -49, -84, ISOFileInfo.FCP_BYTE, ISO7816.INS_DELETE_FILE, ISO7816.INS_READ_RECORD2, PassportService.SF_CVCA, -87, -55, 8, -24, -107, ISOFileInfo.DATA_BYTES1, -33, -108, -6, 117, -113, 63, -90, 71, 7, -89, -4, -13, 115, 23, -70, ISOFileInfo.FILE_IDENTIFIER, 89, 60, 25, -26, ISOFileInfo.PROP_INFO, 79, -88, 104, 107, ISOFileInfo.DATA_BYTES2, -78, 113, ISOFileInfo.FMD_BYTE, ISO7816.INS_PUT_DATA, ISOFileInfo.SECURITY_ATTR_EXP, -8, -21, 15, 75, ISO7816.INS_MANAGE_CHANNEL, 86, -99, 53, PassportService.SF_COM, ISO7816.INS_CHANGE_CHV, 14, 94, 99, 88, -47, -94, 37, ISO7816.INS_MSE, 124, 59, 1, 33, 120, ISOFileInfo.FCI_EXT, -44, 0, 70, 87, -97, -45, 39, 82, 76, 54, 2, -25, ISOFileInfo.f6307A0, -60, -56, -98, -22, -65, ISOFileInfo.LCS_BYTE, ISO7816.INS_WRITE_RECORD, 64, -57, 56, -75, -93, -9, -14, -50, -7, 97, 21, ISOFileInfo.f6308A1, ISO7816.INS_CREATE_FILE, -82, 93, -92, -101, ISO7816.INS_DECREASE_STAMPED, 26, 85, -83, -109, ISO7816.INS_INCREASE, ISO7816.INS_DECREASE, -11, ISOFileInfo.SECURITY_ATTR_COMPACT, ISO7816.INS_READ_BINARY2, -29, PassportService.SF_SOD, -10, ISO7816.INS_APPEND_RECORD, 46, -126, 102, ISO7816.INS_GET_DATA, 96, ISO7816.INS_GET_RESPONSE, 41, 35, ISOFileInfo.f6311AB, 13, 83, 78, ISOFileInfo.FCI_BYTE, -43, -37, 55, 69, -34, -3, ISOFileInfo.CHANNEL_SECURITY, 47, 3, -1, 106, 114, 109, 108, 91, 81, ISOFileInfo.ENV_TEMP_EF, 27, -81, -110, -69, -35, PSSSigner.TRAILER_IMPLICIT, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 17, -39, 92, 65, 31, 16, 90, ISO7816.INS_LOAD_KEY_FILE, 10, -63, 49, -120, ISOFileInfo.f6310A5, -51, 123, -67, 45, 116, ISO7816.INS_WRITE_BINARY, 18, -72, -27, ISO7816.INS_READ_BINARY_STAMPED, ISO7816.INS_READ_BINARY, -119, 105, -105, 74, PassportService.SF_DG12, -106, 119, 126, 101, -71, -15, 9, -59, 110, -58, -124, 24, -16, 125, -20, 58, ISO7816.INS_UPDATE_RECORD, 77, ISO7816.INS_VERIFY, 121, -18, 95, 62, -41, -53, 57, 72};

    /* renamed from: X */
    private final int[] f6702X = new int[4];

    /* renamed from: rk */
    private int[] f6703rk;

    private int rotateLeft(int x, int bits) {
        return (x << bits) | (x >>> (-bits));
    }

    private int tau(int A) {
        return ((Sbox[(A >> 24) & 255] & 255) << 24) | ((Sbox[(A >> 16) & 255] & 255) << 16) | ((Sbox[(A >> 8) & 255] & 255) << 8) | (Sbox[A & 255] & 255);
    }

    private int L_ap(int B) {
        return (rotateLeft(B, 13) ^ B) ^ rotateLeft(B, 23);
    }

    private int T_ap(int Z) {
        return L_ap(tau(Z));
    }

    private int[] expandKey(boolean forEncryption, byte[] key) {
        int[] rk = new int[32];
        int[] MK = {Pack.bigEndianToInt(key, 0), Pack.bigEndianToInt(key, 4), Pack.bigEndianToInt(key, 8), Pack.bigEndianToInt(key, 12)};
        int[] K = {MK[0] ^ f6701FK[0], MK[1] ^ f6701FK[1], MK[2] ^ f6701FK[2], MK[3] ^ f6701FK[3]};
        if (forEncryption) {
            rk[0] = K[0] ^ T_ap(((K[1] ^ K[2]) ^ K[3]) ^ f6700CK[0]);
            rk[1] = K[1] ^ T_ap(((K[2] ^ K[3]) ^ rk[0]) ^ f6700CK[1]);
            rk[2] = K[2] ^ T_ap(((K[3] ^ rk[0]) ^ rk[1]) ^ f6700CK[2]);
            rk[3] = K[3] ^ T_ap(((rk[0] ^ rk[1]) ^ rk[2]) ^ f6700CK[3]);
            for (int i = 4; i < 32; i++) {
                rk[i] = rk[i - 4] ^ T_ap(((rk[i - 3] ^ rk[i - 2]) ^ rk[i - 1]) ^ f6700CK[i]);
            }
        } else {
            rk[31] = K[0] ^ T_ap(((K[1] ^ K[2]) ^ K[3]) ^ f6700CK[0]);
            rk[30] = K[1] ^ T_ap(((K[2] ^ K[3]) ^ rk[31]) ^ f6700CK[1]);
            rk[29] = K[2] ^ T_ap(((K[3] ^ rk[31]) ^ rk[30]) ^ f6700CK[2]);
            rk[28] = K[3] ^ T_ap(((rk[31] ^ rk[30]) ^ rk[29]) ^ f6700CK[3]);
            for (int i2 = 27; i2 >= 0; i2--) {
                rk[i2] = rk[i2 + 4] ^ T_ap(((rk[i2 + 3] ^ rk[i2 + 2]) ^ rk[i2 + 1]) ^ f6700CK[31 - i2]);
            }
        }
        return rk;
    }

    /* renamed from: L */
    private int m4025L(int B) {
        return (((rotateLeft(B, 2) ^ B) ^ rotateLeft(B, 10)) ^ rotateLeft(B, 18)) ^ rotateLeft(B, 24);
    }

    /* renamed from: T */
    private int m4027T(int Z) {
        return m4025L(tau(Z));
    }

    /* renamed from: R */
    private void m4026R(int[] A, int off) {
        int off0 = off;
        int off1 = off + 1;
        int off2 = off + 2;
        int off3 = off + 3;
        A[off0] = A[off0] ^ A[off3];
        A[off3] = A[off0] ^ A[off3];
        A[off0] = A[off0] ^ A[off3];
        A[off1] = A[off1] ^ A[off2];
        A[off2] = A[off1] ^ A[off2];
        A[off1] = A[off1] ^ A[off2];
    }

    /* renamed from: F0 */
    private int m4021F0(int[] X, int rk) {
        return X[0] ^ m4027T(((X[1] ^ X[2]) ^ X[3]) ^ rk);
    }

    /* renamed from: F1 */
    private int m4022F1(int[] X, int rk) {
        return X[1] ^ m4027T(((X[2] ^ X[3]) ^ X[0]) ^ rk);
    }

    /* renamed from: F2 */
    private int m4023F2(int[] X, int rk) {
        return X[2] ^ m4027T(((X[3] ^ X[0]) ^ X[1]) ^ rk);
    }

    /* renamed from: F3 */
    private int m4024F3(int[] X, int rk) {
        return X[3] ^ m4027T(((X[0] ^ X[1]) ^ X[2]) ^ rk);
    }

    public void init(boolean forEncryption, CipherParameters params) throws IllegalArgumentException {
        if (params instanceof KeyParameter) {
            byte[] key = ((KeyParameter) params).getKey();
            if (key.length != 16) {
                throw new IllegalArgumentException("SM4 requires a 128 bit key");
            }
            this.f6703rk = expandKey(forEncryption, key);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to SM4 init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "SM4";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) throws DataLengthException, IllegalStateException {
        if (this.f6703rk == null) {
            throw new IllegalStateException("SM4 not initialised");
        } else if (inOff + 16 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 16 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            this.f6702X[0] = Pack.bigEndianToInt(in, inOff);
            this.f6702X[1] = Pack.bigEndianToInt(in, inOff + 4);
            this.f6702X[2] = Pack.bigEndianToInt(in, inOff + 8);
            this.f6702X[3] = Pack.bigEndianToInt(in, inOff + 12);
            for (int i = 0; i < 32; i += 4) {
                this.f6702X[0] = m4021F0(this.f6702X, this.f6703rk[i]);
                this.f6702X[1] = m4022F1(this.f6702X, this.f6703rk[i + 1]);
                this.f6702X[2] = m4023F2(this.f6702X, this.f6703rk[i + 2]);
                this.f6702X[3] = m4024F3(this.f6702X, this.f6703rk[i + 3]);
            }
            m4026R(this.f6702X, 0);
            Pack.intToBigEndian(this.f6702X[0], out, outOff);
            Pack.intToBigEndian(this.f6702X[1], out, outOff + 4);
            Pack.intToBigEndian(this.f6702X[2], out, outOff + 8);
            Pack.intToBigEndian(this.f6702X[3], out, outOff + 12);
            return 16;
        }
    }

    public void reset() {
    }
}
