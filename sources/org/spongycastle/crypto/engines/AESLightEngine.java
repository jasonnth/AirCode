package org.spongycastle.crypto.engines;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import com.facebook.imageutils.JfifUtil;
import java.lang.reflect.Array;
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
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.util.Pack;

public class AESLightEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;

    /* renamed from: S */
    private static final byte[] f6638S = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.f6311AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, 63, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.f6310A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, ISOFileInfo.DATA_BYTES1, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, 26, 27, 110, 90, ISOFileInfo.f6307A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, ISO7816.INS_VERIFY, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, 88, -49, ISO7816.INS_WRITE_BINARY, -17, -86, -5, 67, 77, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, PassportService.SF_DG12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, 23, -60, -89, 126, 61, ISOFileInfo.FMD_BYTE, 93, 25, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, 20, -34, 94, PassportService.SF_DG11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, PassportService.SF_CVCA, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, 31, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, PassportService.SF_SOD, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, PassportService.SF_COM, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.f6308A1, -119, 13, -65, -26, CVCAFile.CAR_TAG, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, -69, 22};

    /* renamed from: Si */
    private static final byte[] f6639Si = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.f6310A5, 56, -65, 64, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, 61, -18, 76, -107, PassportService.SF_DG11, CVCAFile.CAR_TAG, -6, -61, 78, 8, 46, ISOFileInfo.f6308A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, ISOFileInfo.FMD_BYTE, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, 21, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.f6311AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, ISO7816.INS_DELETE_FILE, 88, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, PassportService.SF_COM, -113, ISO7816.INS_GET_DATA, 63, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, PassportService.SF_CVCA, 117, -33, 110, 71, -15, 26, 113, PassportService.SF_SOD, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, ISO7816.INS_VERIFY, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, 18, 16, 89, 39, ISOFileInfo.DATA_BYTES1, -20, 95, 96, 81, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, ISOFileInfo.f6307A0, ISO7816.INS_CREATE_FILE, 59, 77, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, -69, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, 23, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, 20, 99, 85, 33, PassportService.SF_DG12, 125};

    /* renamed from: m1 */
    private static final int f6640m1 = -2139062144;

    /* renamed from: m2 */
    private static final int f6641m2 = 2139062143;

    /* renamed from: m3 */
    private static final int f6642m3 = 27;

    /* renamed from: m4 */
    private static final int f6643m4 = -1061109568;

    /* renamed from: m5 */
    private static final int f6644m5 = 1061109567;
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA};

    /* renamed from: C0 */
    private int f6645C0;

    /* renamed from: C1 */
    private int f6646C1;

    /* renamed from: C2 */
    private int f6647C2;

    /* renamed from: C3 */
    private int f6648C3;
    private int ROUNDS;
    private int[][] WorkingKey = null;
    private boolean forEncryption;

    private static int shift(int r, int shift) {
        return (r >>> shift) | (r << (-shift));
    }

    private static int FFmulX(int x) {
        return ((f6641m2 & x) << 1) ^ (((f6640m1 & x) >>> 7) * 27);
    }

    private static int FFmulX2(int x) {
        int t1 = x & f6643m4;
        int t12 = t1 ^ (t1 >>> 1);
        return ((t12 >>> 2) ^ ((f6644m5 & x) << 2)) ^ (t12 >>> 5);
    }

    private static int mcol(int x) {
        int t0 = shift(x, 8);
        int t1 = x ^ t0;
        return (shift(t1, 16) ^ t0) ^ FFmulX(t1);
    }

    private static int inv_mcol(int x) {
        int t0 = x;
        int t1 = t0 ^ shift(t0, 8);
        int t02 = t0 ^ FFmulX(t1);
        int t12 = t1 ^ FFmulX2(t02);
        return t02 ^ (shift(t12, 16) ^ t12);
    }

    private static int subWord(int x) {
        return (f6638S[x & 255] & 255) | ((f6638S[(x >> 8) & 255] & 255) << 8) | ((f6638S[(x >> 16) & 255] & 255) << 16) | (f6638S[(x >> 24) & 255] << 24);
    }

    private int[][] generateWorkingKey(byte[] key, boolean forEncryption2) {
        int keyLen = key.length;
        if (keyLen < 16 || keyLen > 32 || (keyLen & 7) != 0) {
            throw new IllegalArgumentException("Key length not 128/192/256 bits.");
        }
        int KC = keyLen >> 2;
        this.ROUNDS = KC + 6;
        int[][] W = (int[][]) Array.newInstance(Integer.TYPE, new int[]{this.ROUNDS + 1, 4});
        switch (KC) {
            case 4:
                int t0 = Pack.littleEndianToInt(key, 0);
                W[0][0] = t0;
                int t1 = Pack.littleEndianToInt(key, 4);
                W[0][1] = t1;
                int t2 = Pack.littleEndianToInt(key, 8);
                W[0][2] = t2;
                int t3 = Pack.littleEndianToInt(key, 12);
                W[0][3] = t3;
                for (int i = 1; i <= 10; i++) {
                    t0 ^= subWord(shift(t3, 8)) ^ rcon[i - 1];
                    W[i][0] = t0;
                    t1 ^= t0;
                    W[i][1] = t1;
                    t2 ^= t1;
                    W[i][2] = t2;
                    t3 ^= t2;
                    W[i][3] = t3;
                }
                break;
            case 6:
                int t02 = Pack.littleEndianToInt(key, 0);
                W[0][0] = t02;
                int t12 = Pack.littleEndianToInt(key, 4);
                W[0][1] = t12;
                int t22 = Pack.littleEndianToInt(key, 8);
                W[0][2] = t22;
                int t32 = Pack.littleEndianToInt(key, 12);
                W[0][3] = t32;
                int t4 = Pack.littleEndianToInt(key, 16);
                W[1][0] = t4;
                int t5 = Pack.littleEndianToInt(key, 20);
                W[1][1] = t5;
                int rcon2 = 1 << 1;
                int t03 = t02 ^ (subWord(shift(t5, 8)) ^ 1);
                W[1][2] = t03;
                int t13 = t12 ^ t03;
                W[1][3] = t13;
                int t23 = t22 ^ t13;
                W[2][0] = t23;
                int t33 = t32 ^ t23;
                W[2][1] = t33;
                int t42 = t4 ^ t33;
                W[2][2] = t42;
                int t52 = t5 ^ t42;
                W[2][3] = t52;
                for (int i2 = 3; i2 < 12; i2 += 3) {
                    int u = subWord(shift(t52, 8)) ^ rcon2;
                    int rcon3 = rcon2 << 1;
                    int t04 = t03 ^ u;
                    W[i2][0] = t04;
                    int t14 = t13 ^ t04;
                    W[i2][1] = t14;
                    int t24 = t23 ^ t14;
                    W[i2][2] = t24;
                    int t34 = t33 ^ t24;
                    W[i2][3] = t34;
                    int t43 = t42 ^ t34;
                    W[i2 + 1][0] = t43;
                    int t53 = t52 ^ t43;
                    W[i2 + 1][1] = t53;
                    int u2 = subWord(shift(t53, 8)) ^ rcon3;
                    rcon2 = rcon3 << 1;
                    t03 = t04 ^ u2;
                    W[i2 + 1][2] = t03;
                    t13 = t14 ^ t03;
                    W[i2 + 1][3] = t13;
                    t23 = t24 ^ t13;
                    W[i2 + 2][0] = t23;
                    t33 = t34 ^ t23;
                    W[i2 + 2][1] = t33;
                    t42 = t43 ^ t33;
                    W[i2 + 2][2] = t42;
                    t52 = t53 ^ t42;
                    W[i2 + 2][3] = t52;
                }
                int t05 = t03 ^ (subWord(shift(t52, 8)) ^ rcon2);
                W[12][0] = t05;
                int t15 = t13 ^ t05;
                W[12][1] = t15;
                int t25 = t23 ^ t15;
                W[12][2] = t25;
                W[12][3] = t33 ^ t25;
                break;
            case 8:
                int t06 = Pack.littleEndianToInt(key, 0);
                W[0][0] = t06;
                int t16 = Pack.littleEndianToInt(key, 4);
                W[0][1] = t16;
                int t26 = Pack.littleEndianToInt(key, 8);
                W[0][2] = t26;
                int t35 = Pack.littleEndianToInt(key, 12);
                W[0][3] = t35;
                int t44 = Pack.littleEndianToInt(key, 16);
                W[1][0] = t44;
                int t54 = Pack.littleEndianToInt(key, 20);
                W[1][1] = t54;
                int t6 = Pack.littleEndianToInt(key, 24);
                W[1][2] = t6;
                int t7 = Pack.littleEndianToInt(key, 28);
                W[1][3] = t7;
                int rcon4 = 1;
                for (int i3 = 2; i3 < 14; i3 += 2) {
                    int u3 = subWord(shift(t7, 8)) ^ rcon4;
                    rcon4 <<= 1;
                    t06 ^= u3;
                    W[i3][0] = t06;
                    t16 ^= t06;
                    W[i3][1] = t16;
                    t26 ^= t16;
                    W[i3][2] = t26;
                    t35 ^= t26;
                    W[i3][3] = t35;
                    t44 ^= subWord(t35);
                    W[i3 + 1][0] = t44;
                    t54 ^= t44;
                    W[i3 + 1][1] = t54;
                    t6 ^= t54;
                    W[i3 + 1][2] = t6;
                    t7 ^= t6;
                    W[i3 + 1][3] = t7;
                }
                int t07 = t06 ^ (subWord(shift(t7, 8)) ^ rcon4);
                W[14][0] = t07;
                int t17 = t16 ^ t07;
                W[14][1] = t17;
                int t27 = t26 ^ t17;
                W[14][2] = t27;
                W[14][3] = t35 ^ t27;
                break;
            default:
                throw new IllegalStateException("Should never get here");
        }
        if (!forEncryption2) {
            for (int j = 1; j < this.ROUNDS; j++) {
                for (int i4 = 0; i4 < 4; i4++) {
                    W[j][i4] = inv_mcol(W[j][i4]);
                }
            }
        }
        return W;
    }

    public void init(boolean forEncryption2, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.WorkingKey = generateWorkingKey(((KeyParameter) params).getKey(), forEncryption2);
            this.forEncryption = forEncryption2;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to AES init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "AES";
    }

    public int getBlockSize() {
        return 16;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.WorkingKey == null) {
            throw new IllegalStateException("AES engine not initialised");
        } else if (inOff + 16 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 16 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.forEncryption) {
                unpackBlock(in, inOff);
                encryptBlock(this.WorkingKey);
                packBlock(out, outOff);
            } else {
                unpackBlock(in, inOff);
                decryptBlock(this.WorkingKey);
                packBlock(out, outOff);
            }
            return 16;
        }
    }

    public void reset() {
    }

    private void unpackBlock(byte[] bytes, int off) {
        int index = off;
        int index2 = index + 1;
        this.f6645C0 = bytes[index] & 255;
        int index3 = index2 + 1;
        this.f6645C0 |= (bytes[index2] & 255) << 8;
        int index4 = index3 + 1;
        this.f6645C0 |= (bytes[index3] & 255) << 16;
        int index5 = index4 + 1;
        this.f6645C0 |= bytes[index4] << 24;
        int index6 = index5 + 1;
        this.f6646C1 = bytes[index5] & 255;
        int index7 = index6 + 1;
        this.f6646C1 |= (bytes[index6] & 255) << 8;
        int index8 = index7 + 1;
        this.f6646C1 |= (bytes[index7] & 255) << 16;
        int index9 = index8 + 1;
        this.f6646C1 |= bytes[index8] << 24;
        int index10 = index9 + 1;
        this.f6647C2 = bytes[index9] & 255;
        int index11 = index10 + 1;
        this.f6647C2 |= (bytes[index10] & 255) << 8;
        int index12 = index11 + 1;
        this.f6647C2 |= (bytes[index11] & 255) << 16;
        int index13 = index12 + 1;
        this.f6647C2 |= bytes[index12] << 24;
        int index14 = index13 + 1;
        this.f6648C3 = bytes[index13] & 255;
        int index15 = index14 + 1;
        this.f6648C3 |= (bytes[index14] & 255) << 8;
        int index16 = index15 + 1;
        this.f6648C3 |= (bytes[index15] & 255) << 16;
        int i = index16 + 1;
        this.f6648C3 |= bytes[index16] << 24;
    }

    private void packBlock(byte[] bytes, int off) {
        int index = off;
        int index2 = index + 1;
        bytes[index] = (byte) this.f6645C0;
        int index3 = index2 + 1;
        bytes[index2] = (byte) (this.f6645C0 >> 8);
        int index4 = index3 + 1;
        bytes[index3] = (byte) (this.f6645C0 >> 16);
        int index5 = index4 + 1;
        bytes[index4] = (byte) (this.f6645C0 >> 24);
        int index6 = index5 + 1;
        bytes[index5] = (byte) this.f6646C1;
        int index7 = index6 + 1;
        bytes[index6] = (byte) (this.f6646C1 >> 8);
        int index8 = index7 + 1;
        bytes[index7] = (byte) (this.f6646C1 >> 16);
        int index9 = index8 + 1;
        bytes[index8] = (byte) (this.f6646C1 >> 24);
        int index10 = index9 + 1;
        bytes[index9] = (byte) this.f6647C2;
        int index11 = index10 + 1;
        bytes[index10] = (byte) (this.f6647C2 >> 8);
        int index12 = index11 + 1;
        bytes[index11] = (byte) (this.f6647C2 >> 16);
        int index13 = index12 + 1;
        bytes[index12] = (byte) (this.f6647C2 >> 24);
        int index14 = index13 + 1;
        bytes[index13] = (byte) this.f6648C3;
        int index15 = index14 + 1;
        bytes[index14] = (byte) (this.f6648C3 >> 8);
        int index16 = index15 + 1;
        bytes[index15] = (byte) (this.f6648C3 >> 16);
        int i = index16 + 1;
        bytes[index16] = (byte) (this.f6648C3 >> 24);
    }

    private void encryptBlock(int[][] KW) {
        int t0 = this.f6645C0 ^ KW[0][0];
        int t1 = this.f6646C1 ^ KW[0][1];
        int t2 = this.f6647C2 ^ KW[0][2];
        int r = 1;
        int i = this.f6648C3;
        int i2 = KW[0][3];
        while (true) {
            int r3 = i ^ i2;
            if (r < this.ROUNDS - 1) {
                int r0 = mcol((((f6638S[t0 & 255] & 255) ^ ((f6638S[(t1 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t2 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r3 >> 24) & 255] << 24)) ^ KW[r][0];
                int r1 = mcol((((f6638S[t1 & 255] & 255) ^ ((f6638S[(t2 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r3 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t0 >> 24) & 255] << 24)) ^ KW[r][1];
                int r2 = mcol((((f6638S[t2 & 255] & 255) ^ ((f6638S[(r3 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t0 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t1 >> 24) & 255] << 24)) ^ KW[r][2];
                int r4 = r + 1;
                int r32 = mcol((((f6638S[r3 & 255] & 255) ^ ((f6638S[(t0 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t1 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t2 >> 24) & 255] << 24)) ^ KW[r][3];
                t0 = mcol((((f6638S[r0 & 255] & 255) ^ ((f6638S[(r1 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r2 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r32 >> 24) & 255] << 24)) ^ KW[r4][0];
                t1 = mcol((((f6638S[r1 & 255] & 255) ^ ((f6638S[(r2 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r32 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r0 >> 24) & 255] << 24)) ^ KW[r4][1];
                t2 = mcol((((f6638S[r2 & 255] & 255) ^ ((f6638S[(r32 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r0 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r1 >> 24) & 255] << 24)) ^ KW[r4][2];
                i = mcol((((f6638S[r32 & 255] & 255) ^ ((f6638S[(r0 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r1 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r2 >> 24) & 255] << 24));
                r = r4 + 1;
                i2 = KW[r4][3];
            } else {
                int r02 = mcol((((f6638S[t0 & 255] & 255) ^ ((f6638S[(t1 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t2 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r3 >> 24) & 255] << 24)) ^ KW[r][0];
                int r12 = mcol((((f6638S[t1 & 255] & 255) ^ ((f6638S[(t2 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r3 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t0 >> 24) & 255] << 24)) ^ KW[r][1];
                int r22 = mcol((((f6638S[t2 & 255] & 255) ^ ((f6638S[(r3 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t0 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t1 >> 24) & 255] << 24)) ^ KW[r][2];
                int r5 = r + 1;
                int r33 = mcol((((f6638S[r3 & 255] & 255) ^ ((f6638S[(t0 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(t1 >> 16) & 255] & 255) << 16)) ^ (f6638S[(t2 >> 24) & 255] << 24)) ^ KW[r][3];
                this.f6645C0 = ((((f6638S[r02 & 255] & 255) ^ ((f6638S[(r12 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r22 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r33 >> 24) & 255] << 24)) ^ KW[r5][0];
                this.f6646C1 = ((((f6638S[r12 & 255] & 255) ^ ((f6638S[(r22 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r33 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r02 >> 24) & 255] << 24)) ^ KW[r5][1];
                this.f6647C2 = ((((f6638S[r22 & 255] & 255) ^ ((f6638S[(r33 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r02 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r12 >> 24) & 255] << 24)) ^ KW[r5][2];
                this.f6648C3 = ((((f6638S[r33 & 255] & 255) ^ ((f6638S[(r02 >> 8) & 255] & 255) << 8)) ^ ((f6638S[(r12 >> 16) & 255] & 255) << 16)) ^ (f6638S[(r22 >> 24) & 255] << 24)) ^ KW[r5][3];
                return;
            }
        }
    }

    private void decryptBlock(int[][] KW) {
        int t0 = this.f6645C0 ^ KW[this.ROUNDS][0];
        int t1 = this.f6646C1 ^ KW[this.ROUNDS][1];
        int t2 = this.f6647C2 ^ KW[this.ROUNDS][2];
        int r3 = this.f6648C3 ^ KW[this.ROUNDS][3];
        int r = this.ROUNDS - 1;
        while (r > 1) {
            int r0 = inv_mcol((((f6639Si[t0 & 255] & 255) ^ ((f6639Si[(r3 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t2 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t1 >> 24) & 255] << 24)) ^ KW[r][0];
            int r1 = inv_mcol((((f6639Si[t1 & 255] & 255) ^ ((f6639Si[(t0 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r3 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t2 >> 24) & 255] << 24)) ^ KW[r][1];
            int r2 = inv_mcol((((f6639Si[t2 & 255] & 255) ^ ((f6639Si[(t1 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t0 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r3 >> 24) & 255] << 24)) ^ KW[r][2];
            int r4 = r - 1;
            int r32 = inv_mcol((((f6639Si[r3 & 255] & 255) ^ ((f6639Si[(t2 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t1 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t0 >> 24) & 255] << 24)) ^ KW[r][3];
            t0 = inv_mcol((((f6639Si[r0 & 255] & 255) ^ ((f6639Si[(r32 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r2 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r1 >> 24) & 255] << 24)) ^ KW[r4][0];
            t1 = inv_mcol((((f6639Si[r1 & 255] & 255) ^ ((f6639Si[(r0 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r32 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r2 >> 24) & 255] << 24)) ^ KW[r4][1];
            t2 = inv_mcol((((f6639Si[r2 & 255] & 255) ^ ((f6639Si[(r1 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r0 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r32 >> 24) & 255] << 24)) ^ KW[r4][2];
            r = r4 - 1;
            r3 = inv_mcol((((f6639Si[r32 & 255] & 255) ^ ((f6639Si[(r2 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r1 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r0 >> 24) & 255] << 24)) ^ KW[r4][3];
        }
        int r02 = inv_mcol((((f6639Si[t0 & 255] & 255) ^ ((f6639Si[(r3 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t2 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t1 >> 24) & 255] << 24)) ^ KW[r][0];
        int r12 = inv_mcol((((f6639Si[t1 & 255] & 255) ^ ((f6639Si[(t0 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r3 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t2 >> 24) & 255] << 24)) ^ KW[r][1];
        int r22 = inv_mcol((((f6639Si[t2 & 255] & 255) ^ ((f6639Si[(t1 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t0 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r3 >> 24) & 255] << 24)) ^ KW[r][2];
        int r33 = inv_mcol((((f6639Si[r3 & 255] & 255) ^ ((f6639Si[(t2 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(t1 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(t0 >> 24) & 255] << 24)) ^ KW[r][3];
        this.f6645C0 = ((((f6639Si[r02 & 255] & 255) ^ ((f6639Si[(r33 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r22 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r12 >> 24) & 255] << 24)) ^ KW[0][0];
        this.f6646C1 = ((((f6639Si[r12 & 255] & 255) ^ ((f6639Si[(r02 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r33 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r22 >> 24) & 255] << 24)) ^ KW[0][1];
        this.f6647C2 = ((((f6639Si[r22 & 255] & 255) ^ ((f6639Si[(r12 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r02 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r33 >> 24) & 255] << 24)) ^ KW[0][2];
        this.f6648C3 = ((((f6639Si[r33 & 255] & 255) ^ ((f6639Si[(r22 >> 8) & 255] & 255) << 8)) ^ ((f6639Si[(r12 >> 16) & 255] & 255) << 16)) ^ (f6639Si[(r02 >> 24) & 255] << 24)) ^ KW[0][3];
    }
}
