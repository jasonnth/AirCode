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
import org.spongycastle.crypto.tls.CipherSuite;

public final class TwofishEngine implements BlockCipher {
    private static final int BLOCK_SIZE = 16;
    private static final int GF256_FDBK = 361;
    private static final int GF256_FDBK_2 = 180;
    private static final int GF256_FDBK_4 = 90;
    private static final int INPUT_WHITEN = 0;
    private static final int MAX_KEY_BITS = 256;
    private static final int MAX_ROUNDS = 16;
    private static final int OUTPUT_WHITEN = 4;

    /* renamed from: P */
    private static final byte[][] f6718P = {new byte[]{-87, 103, ISO7816.INS_READ_RECORD2, -24, 4, -3, -93, 118, -102, -110, ISOFileInfo.DATA_BYTES1, 120, ISO7816.INS_DELETE_FILE, -35, -47, 56, 13, -58, 53, -104, 24, -9, -20, 108, 67, 117, 55, 38, -6, 19, -108, 72, -14, ISO7816.INS_WRITE_BINARY, ISOFileInfo.SECURITY_ATTR_EXP, ISO7816.INS_DECREASE, -124, 84, -33, 35, 25, 91, 61, 89, -13, -82, -94, -126, 99, 1, ISOFileInfo.FILE_IDENTIFIER, 46, -39, 81, -101, 124, -90, -21, ISOFileInfo.f6310A5, -66, 22, PassportService.SF_DG12, -29, 97, ISO7816.INS_GET_RESPONSE, ISOFileInfo.SECURITY_ATTR_COMPACT, 58, -11, 115, ISO7816.INS_UNBLOCK_CHV, 37, PassportService.SF_DG11, -69, 78, -119, 107, 83, 106, ISO7816.INS_READ_BINARY_STAMPED, -15, -31, -26, -67, 69, ISO7816.INS_APPEND_RECORD, -12, ISO7816.INS_READ_RECORD_STAMPED, 102, -52, -107, 3, 86, -44, PassportService.SF_CVCA, PassportService.SF_COM, -41, -5, -61, ISOFileInfo.CHANNEL_SECURITY, -75, -23, -49, -65, -70, -22, 119, 57, -81, 51, -55, ISOFileInfo.FCP_BYTE, 113, ISOFileInfo.DATA_BYTES2, 121, 9, -83, ISO7816.INS_CHANGE_CHV, -51, -7, ISO7816.INS_LOAD_KEY_FILE, -27, -59, -71, 77, ISO7816.INS_REHABILITATE_CHV, 8, -122, -25, ISOFileInfo.f6308A1, PassportService.SF_SOD, -86, -19, 6, ISO7816.INS_MANAGE_CHANNEL, -78, ISO7816.INS_WRITE_RECORD, 65, 123, ISOFileInfo.f6307A0, 17, 49, ISO7816.INS_ENVELOPE, 39, -112, ISO7816.INS_VERIFY, -10, 96, -1, -106, 92, ISO7816.INS_READ_BINARY2, ISOFileInfo.f6311AB, -98, -100, 82, 27, 95, -109, 10, -17, -111, ISOFileInfo.PROP_INFO, 73, -18, 45, 79, -113, 59, 71, ISOFileInfo.FCI_EXT, 109, 70, ISO7816.INS_UPDATE_BINARY, 62, 105, ISOFileInfo.FMD_BYTE, ISO7816.INS_PSO, -50, -53, 47, -4, -105, 5, 122, -84, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, -43, 26, 75, 14, -89, 90, 40, 20, 63, 41, -120, 60, 76, 2, -72, ISO7816.INS_PUT_DATA, ISO7816.INS_READ_BINARY, 23, 85, 31, ISOFileInfo.LCS_BYTE, 125, 87, -57, ISOFileInfo.ENV_TEMP_EF, 116, -73, -60, -97, 114, 126, 21, ISO7816.INS_MSE, 18, 88, 7, -103, ISO7816.INS_DECREASE_STAMPED, 110, 80, -34, 104, 101, PSSSigner.TRAILER_IMPLICIT, -37, -8, -56, -88, 43, 64, ISO7816.INS_UPDATE_RECORD, -2, ISO7816.INS_INCREASE, -92, ISO7816.INS_GET_DATA, 16, 33, -16, -45, 93, 15, 0, ISOFileInfo.FCI_BYTE, -99, 54, CVCAFile.CAR_TAG, 74, 94, -63, ISO7816.INS_CREATE_FILE}, new byte[]{117, -13, -58, -12, -37, 123, -5, -56, 74, -45, -26, 107, 69, 125, -24, 75, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_INCREASE, ISO7816.INS_LOAD_KEY_FILE, -3, 55, 113, -15, -31, ISO7816.INS_DECREASE, 15, -8, 27, ISOFileInfo.FCI_EXT, -6, 6, 63, 94, -70, -82, 91, ISOFileInfo.LCS_BYTE, 0, PSSSigner.TRAILER_IMPLICIT, -99, 109, -63, ISO7816.INS_READ_BINARY2, 14, ISOFileInfo.DATA_BYTES1, 93, ISO7816.INS_WRITE_RECORD, -43, ISOFileInfo.f6307A0, -124, 7, 20, -75, -112, ISO7816.INS_UNBLOCK_CHV, -93, -78, 115, 76, 84, -110, 116, 54, 81, 56, ISO7816.INS_READ_BINARY, -67, 90, -4, 96, ISOFileInfo.FCP_BYTE, -106, 108, CVCAFile.CAR_TAG, -9, 16, 124, 40, 39, ISOFileInfo.SECURITY_ATTR_COMPACT, 19, -107, -100, -57, ISO7816.INS_CHANGE_CHV, 70, 59, ISO7816.INS_MANAGE_CHANNEL, ISO7816.INS_GET_DATA, -29, ISOFileInfo.PROP_INFO, -53, 17, ISO7816.INS_WRITE_BINARY, -109, -72, -90, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_VERIFY, -1, -97, 119, -61, -52, 3, ISOFileInfo.FCI_BYTE, 8, -65, 64, -25, 43, ISO7816.INS_APPEND_RECORD, 121, PassportService.SF_DG12, -86, -126, 65, 58, -22, -71, ISO7816.INS_DELETE_FILE, -102, -92, -105, 126, ISO7816.INS_PUT_DATA, 122, 23, 102, -108, ISOFileInfo.f6308A1, PassportService.SF_SOD, 61, -16, -34, ISO7816.INS_READ_RECORD2, PassportService.SF_DG11, 114, -89, PassportService.SF_CVCA, -17, -47, 83, 62, -113, 51, 38, 95, -20, 118, ISO7816.INS_PSO, 73, ISOFileInfo.DATA_BYTES2, -120, -18, 33, -60, 26, -21, -39, -59, 57, -103, -51, -83, 49, ISOFileInfo.SECURITY_ATTR_EXP, 1, 24, 35, -35, 31, 78, 45, -7, 72, 79, -14, 101, ISOFileInfo.CHANNEL_SECURITY, 120, 92, 88, 25, ISOFileInfo.ENV_TEMP_EF, -27, -104, 87, 103, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 5, ISOFileInfo.FMD_BYTE, -81, 99, ISO7816.INS_READ_RECORD_STAMPED, -2, -11, -73, 60, ISOFileInfo.f6310A5, -50, -23, 104, ISO7816.INS_REHABILITATE_CHV, ISO7816.INS_CREATE_FILE, 77, 67, 105, 41, 46, -84, 21, 89, -88, 10, -98, 110, 71, -33, ISO7816.INS_DECREASE_STAMPED, 53, 106, -49, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, -55, ISO7816.INS_GET_RESPONSE, -101, -119, -44, -19, ISOFileInfo.f6311AB, 18, -94, 13, 82, -69, 2, 47, -87, -41, 97, PassportService.SF_COM, ISO7816.INS_READ_BINARY_STAMPED, 80, 4, -10, ISO7816.INS_ENVELOPE, 22, 37, -122, 86, 85, 9, -66, -111}};
    private static final int P_00 = 1;
    private static final int P_01 = 0;
    private static final int P_02 = 0;
    private static final int P_03 = 1;
    private static final int P_04 = 1;
    private static final int P_10 = 0;
    private static final int P_11 = 0;
    private static final int P_12 = 1;
    private static final int P_13 = 1;
    private static final int P_14 = 0;
    private static final int P_20 = 1;
    private static final int P_21 = 1;
    private static final int P_22 = 0;
    private static final int P_23 = 0;
    private static final int P_24 = 0;
    private static final int P_30 = 0;
    private static final int P_31 = 1;
    private static final int P_32 = 1;
    private static final int P_33 = 0;
    private static final int P_34 = 1;
    private static final int ROUNDS = 16;
    private static final int ROUND_SUBKEYS = 8;
    private static final int RS_GF_FDBK = 333;
    private static final int SK_BUMP = 16843009;
    private static final int SK_ROTL = 9;
    private static final int SK_STEP = 33686018;
    private static final int TOTAL_SUBKEYS = 40;
    private boolean encrypting = false;
    private int[] gMDS0 = new int[256];
    private int[] gMDS1 = new int[256];
    private int[] gMDS2 = new int[256];
    private int[] gMDS3 = new int[256];
    private int[] gSBox;
    private int[] gSubKeys;
    private int k64Cnt = 0;
    private byte[] workingKey = null;

    public TwofishEngine() {
        int[] m1 = new int[2];
        int[] mX = new int[2];
        int[] mY = new int[2];
        for (int i = 0; i < 256; i++) {
            int j = f6718P[0][i] & 255;
            m1[0] = j;
            mX[0] = Mx_X(j) & 255;
            mY[0] = Mx_Y(j) & 255;
            int j2 = f6718P[1][i] & 255;
            m1[1] = j2;
            mX[1] = Mx_X(j2) & 255;
            mY[1] = Mx_Y(j2) & 255;
            this.gMDS0[i] = m1[1] | (mX[1] << 8) | (mY[1] << 16) | (mY[1] << 24);
            this.gMDS1[i] = mY[0] | (mY[0] << 8) | (mX[0] << 16) | (m1[0] << 24);
            this.gMDS2[i] = mX[1] | (mY[1] << 8) | (m1[1] << 16) | (mY[1] << 24);
            this.gMDS3[i] = mX[0] | (m1[0] << 8) | (mY[0] << 16) | (mX[0] << 24);
        }
    }

    public void init(boolean encrypting2, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.encrypting = encrypting2;
            this.workingKey = ((KeyParameter) params).getKey();
            this.k64Cnt = this.workingKey.length / 8;
            setKey(this.workingKey);
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Twofish init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Twofish";
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Twofish not initialised");
        } else if (inOff + 16 > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if (outOff + 16 > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.encrypting) {
                encryptBlock(in, inOff, out, outOff);
            } else {
                decryptBlock(in, inOff, out, outOff);
            }
            return 16;
        }
    }

    public void reset() {
        if (this.workingKey != null) {
            setKey(this.workingKey);
        }
    }

    public int getBlockSize() {
        return 16;
    }

    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setKey(byte[] r26) {
        /*
            r25 = this;
            r19 = 4
            r0 = r19
            int[] r14 = new int[r0]
            r19 = 4
            r0 = r19
            int[] r15 = new int[r0]
            r19 = 4
            r0 = r19
            int[] r0 = new int[r0]
            r18 = r0
            r19 = 40
            r0 = r19
            int[] r0 = new int[r0]
            r19 = r0
            r0 = r19
            r1 = r25
            r1.gSubKeys = r0
            r0 = r25
            int r0 = r0.k64Cnt
            r19 = r0
            r20 = 1
            r0 = r19
            r1 = r20
            if (r0 >= r1) goto L_0x0039
            java.lang.IllegalArgumentException r19 = new java.lang.IllegalArgumentException
            java.lang.String r20 = "Key size less than 64 bits"
            r19.<init>(r20)
            throw r19
        L_0x0039:
            r0 = r25
            int r0 = r0.k64Cnt
            r19 = r0
            r20 = 4
            r0 = r19
            r1 = r20
            if (r0 <= r1) goto L_0x0050
            java.lang.IllegalArgumentException r19 = new java.lang.IllegalArgumentException
            java.lang.String r20 = "Key size larger than 256 bits"
            r19.<init>(r20)
            throw r19
        L_0x0050:
            r9 = 0
        L_0x0051:
            r0 = r25
            int r0 = r0.k64Cnt
            r19 = r0
            r0 = r19
            if (r9 >= r0) goto L_0x0094
            int r16 = r9 * 8
            r0 = r25
            r1 = r26
            r2 = r16
            int r19 = r0.BytesTo32Bits(r1, r2)
            r14[r9] = r19
            int r19 = r16 + 4
            r0 = r25
            r1 = r26
            r2 = r19
            int r19 = r0.BytesTo32Bits(r1, r2)
            r15[r9] = r19
            r0 = r25
            int r0 = r0.k64Cnt
            r19 = r0
            int r19 = r19 + -1
            int r19 = r19 - r9
            r20 = r14[r9]
            r21 = r15[r9]
            r0 = r25
            r1 = r20
            r2 = r21
            int r20 = r0.RS_MDS_Encode(r1, r2)
            r18[r19] = r20
            int r9 = r9 + 1
            goto L_0x0051
        L_0x0094:
            r9 = 0
        L_0x0095:
            r19 = 20
            r0 = r19
            if (r9 >= r0) goto L_0x00dc
            r19 = 33686018(0x2020202, float:9.551468E-38)
            int r17 = r9 * r19
            r0 = r25
            r1 = r17
            int r3 = r0.F32(r1, r14)
            r19 = 16843009(0x1010101, float:2.3694278E-38)
            int r19 = r19 + r17
            r0 = r25
            r1 = r19
            int r4 = r0.F32(r1, r15)
            int r19 = r4 << 8
            int r20 = r4 >>> 24
            r4 = r19 | r20
            int r3 = r3 + r4
            r0 = r25
            int[] r0 = r0.gSubKeys
            r19 = r0
            int r20 = r9 * 2
            r19[r20] = r3
            int r3 = r3 + r4
            r0 = r25
            int[] r0 = r0.gSubKeys
            r19 = r0
            int r20 = r9 * 2
            int r20 = r20 + 1
            int r21 = r3 << 9
            int r22 = r3 >>> 23
            r21 = r21 | r22
            r19[r20] = r21
            int r9 = r9 + 1
            goto L_0x0095
        L_0x00dc:
            r19 = 0
            r10 = r18[r19]
            r19 = 1
            r11 = r18[r19]
            r19 = 2
            r12 = r18[r19]
            r19 = 3
            r13 = r18[r19]
            r19 = 1024(0x400, float:1.435E-42)
            r0 = r19
            int[] r0 = new int[r0]
            r19 = r0
            r0 = r19
            r1 = r25
            r1.gSBox = r0
            r9 = 0
        L_0x00fb:
            r19 = 256(0x100, float:3.59E-43)
            r0 = r19
            if (r9 >= r0) goto L_0x037b
            r8 = r9
            r7 = r9
            r6 = r9
            r5 = r9
            r0 = r25
            int r0 = r0.k64Cnt
            r19 = r0
            r19 = r19 & 3
            switch(r19) {
                case 0: goto L_0x01c3;
                case 1: goto L_0x0113;
                case 2: goto L_0x0273;
                case 3: goto L_0x021b;
                default: goto L_0x0110;
            }
        L_0x0110:
            int r9 = r9 + 1
            goto L_0x00fb
        L_0x0113:
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r25
            int[] r0 = r0.gMDS0
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 0
            r22 = r22[r23]
            byte r22 = r22[r5]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4031b0(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            int r20 = r20 + 1
            r0 = r25
            int[] r0 = r0.gMDS1
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 0
            r22 = r22[r23]
            byte r22 = r22[r6]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4032b1(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r20
            int r0 = r0 + 512
            r20 = r0
            r0 = r25
            int[] r0 = r0.gMDS2
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 1
            r22 = r22[r23]
            byte r22 = r22[r7]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4033b2(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r20
            int r0 = r0 + 513
            r20 = r0
            r0 = r25
            int[] r0 = r0.gMDS3
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 1
            r22 = r22[r23]
            byte r22 = r22[r8]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4034b3(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            goto L_0x0110
        L_0x01c3:
            byte[][] r19 = f6718P
            r20 = 1
            r19 = r19[r20]
            byte r19 = r19[r5]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4031b0(r13)
            r5 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 0
            r19 = r19[r20]
            byte r19 = r19[r6]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4032b1(r13)
            r6 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 0
            r19 = r19[r20]
            byte r19 = r19[r7]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4033b2(r13)
            r7 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 1
            r19 = r19[r20]
            byte r19 = r19[r8]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4034b3(r13)
            r8 = r19 ^ r20
        L_0x021b:
            byte[][] r19 = f6718P
            r20 = 1
            r19 = r19[r20]
            byte r19 = r19[r5]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4031b0(r12)
            r5 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 1
            r19 = r19[r20]
            byte r19 = r19[r6]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4032b1(r12)
            r6 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 0
            r19 = r19[r20]
            byte r19 = r19[r7]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4033b2(r12)
            r7 = r19 ^ r20
            byte[][] r19 = f6718P
            r20 = 0
            r19 = r19[r20]
            byte r19 = r19[r8]
            r0 = r19
            r0 = r0 & 255(0xff, float:3.57E-43)
            r19 = r0
            r0 = r25
            int r20 = r0.m4034b3(r12)
            r8 = r19 ^ r20
        L_0x0273:
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r25
            int[] r0 = r0.gMDS0
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 0
            r22 = r22[r23]
            byte[][] r23 = f6718P
            r24 = 0
            r23 = r23[r24]
            byte r23 = r23[r5]
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r25
            int r24 = r0.m4031b0(r11)
            r23 = r23 ^ r24
            byte r22 = r22[r23]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4031b0(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            int r20 = r20 + 1
            r0 = r25
            int[] r0 = r0.gMDS1
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 0
            r22 = r22[r23]
            byte[][] r23 = f6718P
            r24 = 1
            r23 = r23[r24]
            byte r23 = r23[r6]
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r25
            int r24 = r0.m4032b1(r11)
            r23 = r23 ^ r24
            byte r22 = r22[r23]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4032b1(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r20
            int r0 = r0 + 512
            r20 = r0
            r0 = r25
            int[] r0 = r0.gMDS2
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 1
            r22 = r22[r23]
            byte[][] r23 = f6718P
            r24 = 0
            r23 = r23[r24]
            byte r23 = r23[r7]
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r25
            int r24 = r0.m4033b2(r11)
            r23 = r23 ^ r24
            byte r22 = r22[r23]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4033b2(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            r0 = r25
            int[] r0 = r0.gSBox
            r19 = r0
            int r20 = r9 * 2
            r0 = r20
            int r0 = r0 + 513
            r20 = r0
            r0 = r25
            int[] r0 = r0.gMDS3
            r21 = r0
            byte[][] r22 = f6718P
            r23 = 1
            r22 = r22[r23]
            byte[][] r23 = f6718P
            r24 = 1
            r23 = r23[r24]
            byte r23 = r23[r8]
            r0 = r23
            r0 = r0 & 255(0xff, float:3.57E-43)
            r23 = r0
            r0 = r25
            int r24 = r0.m4034b3(r11)
            r23 = r23 ^ r24
            byte r22 = r22[r23]
            r0 = r22
            r0 = r0 & 255(0xff, float:3.57E-43)
            r22 = r0
            r0 = r25
            int r23 = r0.m4034b3(r10)
            r22 = r22 ^ r23
            r21 = r21[r22]
            r19[r20] = r21
            goto L_0x0110
        L_0x037b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.engines.TwofishEngine.setKey(byte[]):void");
    }

    private void encryptBlock(byte[] src, int srcIndex, byte[] dst, int dstIndex) {
        int x0 = BytesTo32Bits(src, srcIndex) ^ this.gSubKeys[0];
        int x1 = BytesTo32Bits(src, srcIndex + 4) ^ this.gSubKeys[1];
        int x2 = BytesTo32Bits(src, srcIndex + 8) ^ this.gSubKeys[2];
        int x3 = BytesTo32Bits(src, srcIndex + 12) ^ this.gSubKeys[3];
        int k = 8;
        for (int r = 0; r < 16; r += 2) {
            int t0 = Fe32_0(x0);
            int t1 = Fe32_3(x1);
            int k2 = k + 1;
            int x22 = x2 ^ ((t0 + t1) + this.gSubKeys[k]);
            x2 = (x22 >>> 1) | (x22 << 31);
            int k3 = k2 + 1;
            x3 = ((x3 << 1) | (x3 >>> 31)) ^ (((t1 * 2) + t0) + this.gSubKeys[k2]);
            int t02 = Fe32_0(x2);
            int t12 = Fe32_3(x3);
            int k4 = k3 + 1;
            int x02 = x0 ^ ((t02 + t12) + this.gSubKeys[k3]);
            x0 = (x02 >>> 1) | (x02 << 31);
            k = k4 + 1;
            x1 = ((x1 << 1) | (x1 >>> 31)) ^ (((t12 * 2) + t02) + this.gSubKeys[k4]);
        }
        Bits32ToBytes(this.gSubKeys[4] ^ x2, dst, dstIndex);
        Bits32ToBytes(this.gSubKeys[5] ^ x3, dst, dstIndex + 4);
        Bits32ToBytes(this.gSubKeys[6] ^ x0, dst, dstIndex + 8);
        Bits32ToBytes(this.gSubKeys[7] ^ x1, dst, dstIndex + 12);
    }

    private void decryptBlock(byte[] src, int srcIndex, byte[] dst, int dstIndex) {
        int x2 = BytesTo32Bits(src, srcIndex) ^ this.gSubKeys[4];
        int x3 = BytesTo32Bits(src, srcIndex + 4) ^ this.gSubKeys[5];
        int x0 = BytesTo32Bits(src, srcIndex + 8) ^ this.gSubKeys[6];
        int x1 = BytesTo32Bits(src, srcIndex + 12) ^ this.gSubKeys[7];
        int k = 39;
        for (int r = 0; r < 16; r += 2) {
            int t0 = Fe32_0(x2);
            int t1 = Fe32_3(x3);
            int k2 = k - 1;
            int x12 = x1 ^ (((t1 * 2) + t0) + this.gSubKeys[k]);
            int k3 = k2 - 1;
            x0 = ((x0 << 1) | (x0 >>> 31)) ^ ((t0 + t1) + this.gSubKeys[k2]);
            x1 = (x12 >>> 1) | (x12 << 31);
            int t02 = Fe32_0(x0);
            int t12 = Fe32_3(x1);
            int k4 = k3 - 1;
            int x32 = x3 ^ (((t12 * 2) + t02) + this.gSubKeys[k3]);
            k = k4 - 1;
            x2 = ((x2 << 1) | (x2 >>> 31)) ^ ((t02 + t12) + this.gSubKeys[k4]);
            x3 = (x32 >>> 1) | (x32 << 31);
        }
        Bits32ToBytes(this.gSubKeys[0] ^ x0, dst, dstIndex);
        Bits32ToBytes(this.gSubKeys[1] ^ x1, dst, dstIndex + 4);
        Bits32ToBytes(this.gSubKeys[2] ^ x2, dst, dstIndex + 8);
        Bits32ToBytes(this.gSubKeys[3] ^ x3, dst, dstIndex + 12);
    }

    private int F32(int x, int[] k32) {
        int b0 = m4031b0(x);
        int b1 = m4032b1(x);
        int b2 = m4033b2(x);
        int b3 = m4034b3(x);
        int k0 = k32[0];
        int k1 = k32[1];
        int k2 = k32[2];
        int k3 = k32[3];
        switch (this.k64Cnt & 3) {
            case 0:
                b0 = (f6718P[1][b0] & 255) ^ m4031b0(k3);
                b1 = (f6718P[0][b1] & 255) ^ m4032b1(k3);
                b2 = (f6718P[0][b2] & 255) ^ m4033b2(k3);
                b3 = (f6718P[1][b3] & 255) ^ m4034b3(k3);
                break;
            case 1:
                return ((this.gMDS0[(f6718P[0][b0] & 255) ^ m4031b0(k0)] ^ this.gMDS1[(f6718P[0][b1] & 255) ^ m4032b1(k0)]) ^ this.gMDS2[(f6718P[1][b2] & 255) ^ m4033b2(k0)]) ^ this.gMDS3[(f6718P[1][b3] & 255) ^ m4034b3(k0)];
            case 2:
                break;
            case 3:
                break;
            default:
                return 0;
        }
        b0 = (f6718P[1][b0] & 255) ^ m4031b0(k2);
        b1 = (f6718P[1][b1] & 255) ^ m4032b1(k2);
        b2 = (f6718P[0][b2] & 255) ^ m4033b2(k2);
        b3 = (f6718P[0][b3] & 255) ^ m4034b3(k2);
        return ((this.gMDS0[(f6718P[0][(f6718P[0][b0] & 255) ^ m4031b0(k1)] & 255) ^ m4031b0(k0)] ^ this.gMDS1[(f6718P[0][(f6718P[1][b1] & 255) ^ m4032b1(k1)] & 255) ^ m4032b1(k0)]) ^ this.gMDS2[(f6718P[1][(f6718P[0][b2] & 255) ^ m4033b2(k1)] & 255) ^ m4033b2(k0)]) ^ this.gMDS3[(f6718P[1][(f6718P[1][b3] & 255) ^ m4034b3(k1)] & 255) ^ m4034b3(k0)];
    }

    private int RS_MDS_Encode(int k0, int k1) {
        int r = k1;
        for (int i = 0; i < 4; i++) {
            r = RS_rem(r);
        }
        int r2 = r ^ k0;
        for (int i2 = 0; i2 < 4; i2++) {
            r2 = RS_rem(r2);
        }
        return r2;
    }

    private int RS_rem(int x) {
        int i;
        int i2 = 0;
        int b = (x >>> 24) & 255;
        int i3 = b << 1;
        if ((b & 128) != 0) {
            i = RS_GF_FDBK;
        } else {
            i = 0;
        }
        int g2 = (i ^ i3) & 255;
        int i4 = b >>> 1;
        if ((b & 1) != 0) {
            i2 = CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256;
        }
        int g3 = (i2 ^ i4) ^ g2;
        return ((((x << 8) ^ (g3 << 24)) ^ (g2 << 16)) ^ (g3 << 8)) ^ b;
    }

    private int LFSR1(int x) {
        return ((x & 1) != 0 ? 180 : 0) ^ (x >> 1);
    }

    private int LFSR2(int x) {
        int i = 0;
        int i2 = ((x & 2) != 0 ? 180 : 0) ^ (x >> 2);
        if ((x & 1) != 0) {
            i = 90;
        }
        return i ^ i2;
    }

    private int Mx_X(int x) {
        return LFSR2(x) ^ x;
    }

    private int Mx_Y(int x) {
        return (LFSR1(x) ^ x) ^ LFSR2(x);
    }

    /* renamed from: b0 */
    private int m4031b0(int x) {
        return x & 255;
    }

    /* renamed from: b1 */
    private int m4032b1(int x) {
        return (x >>> 8) & 255;
    }

    /* renamed from: b2 */
    private int m4033b2(int x) {
        return (x >>> 16) & 255;
    }

    /* renamed from: b3 */
    private int m4034b3(int x) {
        return (x >>> 24) & 255;
    }

    private int Fe32_0(int x) {
        return ((this.gSBox[((x & 255) * 2) + 0] ^ this.gSBox[(((x >>> 8) & 255) * 2) + 1]) ^ this.gSBox[(((x >>> 16) & 255) * 2) + 512]) ^ this.gSBox[(((x >>> 24) & 255) * 2) + 513];
    }

    private int Fe32_3(int x) {
        return ((this.gSBox[(((x >>> 24) & 255) * 2) + 0] ^ this.gSBox[((x & 255) * 2) + 1]) ^ this.gSBox[(((x >>> 8) & 255) * 2) + 512]) ^ this.gSBox[(((x >>> 16) & 255) * 2) + 513];
    }

    private int BytesTo32Bits(byte[] b, int p) {
        return (b[p] & 255) | ((b[p + 1] & 255) << 8) | ((b[p + 2] & 255) << 16) | ((b[p + 3] & 255) << 24);
    }

    private void Bits32ToBytes(int in, byte[] b, int offset) {
        b[offset] = (byte) in;
        b[offset + 1] = (byte) (in >> 8);
        b[offset + 2] = (byte) (in >> 16);
        b[offset + 3] = (byte) (in >> 24);
    }
}
