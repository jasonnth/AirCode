package org.spongycastle.crypto.digests;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.ExtendedDigest;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Memoable;

public class MD2Digest implements ExtendedDigest, Memoable {
    private static final int DIGEST_LENGTH = 16;

    /* renamed from: S */
    private static final byte[] f6514S = {41, 46, 67, -55, -94, ISO7816.INS_LOAD_KEY_FILE, 124, 1, 61, 54, 84, ISOFileInfo.f6308A1, -20, -16, 6, 19, ISOFileInfo.FCP_BYTE, -89, 5, -13, ISO7816.INS_GET_RESPONSE, -57, 115, ISOFileInfo.SECURITY_ATTR_COMPACT, -104, -109, 43, -39, PSSSigner.TRAILER_IMPLICIT, 76, -126, ISO7816.INS_GET_DATA, PassportService.SF_COM, -101, 87, 60, -3, -44, ISO7816.INS_CREATE_FILE, 22, 103, CVCAFile.CAR_TAG, ISOFileInfo.FCI_BYTE, 24, ISOFileInfo.LCS_BYTE, 23, -27, 18, -66, 78, -60, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_PUT_DATA, -98, -34, 73, ISOFileInfo.f6307A0, -5, -11, ISOFileInfo.CHANNEL_SECURITY, -69, 47, -18, 122, -87, 104, 121, -111, 21, -78, 7, 63, -108, ISO7816.INS_ENVELOPE, 16, -119, PassportService.SF_DG11, ISO7816.INS_MSE, 95, 33, ISOFileInfo.DATA_BYTES1, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 93, -102, 90, -112, ISO7816.INS_INCREASE, 39, 53, 62, -52, -25, -65, -9, -105, 3, -1, 25, ISO7816.INS_DECREASE, ISO7816.INS_READ_RECORD2, 72, ISOFileInfo.f6310A5, -75, -47, -41, 94, -110, ISO7816.INS_PSO, -84, 86, -86, -58, 79, -72, 56, ISO7816.INS_WRITE_RECORD, -106, -92, 125, ISO7816.INS_READ_RECORD_STAMPED, 118, -4, 107, ISO7816.INS_APPEND_RECORD, -100, 116, 4, -15, 69, -99, ISO7816.INS_MANAGE_CHANNEL, 89, ISOFileInfo.FMD_BYTE, 113, ISOFileInfo.FCI_EXT, ISO7816.INS_VERIFY, -122, 91, -49, 101, -26, 45, -88, 2, 27, 96, 37, -83, -82, ISO7816.INS_READ_BINARY, -71, -10, PassportService.SF_CVCA, 70, 97, 105, ISO7816.INS_DECREASE_STAMPED, 64, 126, 15, 85, 71, -93, 35, -35, 81, -81, 58, -61, 92, -7, -50, -70, -59, -22, 38, ISO7816.INS_UNBLOCK_CHV, 83, 13, 110, ISOFileInfo.PROP_INFO, 40, -124, 9, -45, -33, -51, -12, 65, ISOFileInfo.DATA_BYTES2, 77, 82, 106, ISO7816.INS_UPDATE_RECORD, 55, -56, 108, -63, ISOFileInfo.f6311AB, -6, ISO7816.INS_CHANGE_CHV, -31, 123, 8, PassportService.SF_DG12, -67, ISO7816.INS_READ_BINARY2, 74, 120, -120, -107, ISOFileInfo.SECURITY_ATTR_EXP, -29, 99, -24, 109, -23, -53, -43, -2, 59, 0, PassportService.SF_SOD, 57, -14, -17, -73, 14, 102, 88, ISO7816.INS_WRITE_BINARY, ISO7816.INS_DELETE_FILE, -90, 119, 114, -8, -21, 117, 75, 10, 49, ISO7816.INS_REHABILITATE_CHV, 80, ISO7816.INS_READ_BINARY_STAMPED, -113, -19, 31, 26, -37, -103, ISOFileInfo.ENV_TEMP_EF, 51, -97, 17, ISOFileInfo.FILE_IDENTIFIER, 20};

    /* renamed from: C */
    private byte[] f6515C = new byte[16];
    private int COff;

    /* renamed from: M */
    private byte[] f6516M = new byte[16];

    /* renamed from: X */
    private byte[] f6517X = new byte[48];
    private int mOff;
    private int xOff;

    public MD2Digest() {
        reset();
    }

    public MD2Digest(MD2Digest t) {
        copyIn(t);
    }

    private void copyIn(MD2Digest t) {
        System.arraycopy(t.f6517X, 0, this.f6517X, 0, t.f6517X.length);
        this.xOff = t.xOff;
        System.arraycopy(t.f6516M, 0, this.f6516M, 0, t.f6516M.length);
        this.mOff = t.mOff;
        System.arraycopy(t.f6515C, 0, this.f6515C, 0, t.f6515C.length);
        this.COff = t.COff;
    }

    public String getAlgorithmName() {
        return "MD2";
    }

    public int getDigestSize() {
        return 16;
    }

    public int doFinal(byte[] out, int outOff) {
        byte paddingByte = (byte) (this.f6516M.length - this.mOff);
        for (int i = this.mOff; i < this.f6516M.length; i++) {
            this.f6516M[i] = paddingByte;
        }
        processCheckSum(this.f6516M);
        processBlock(this.f6516M);
        processBlock(this.f6515C);
        System.arraycopy(this.f6517X, this.xOff, out, outOff, 16);
        reset();
        return 16;
    }

    public void reset() {
        this.xOff = 0;
        for (int i = 0; i != this.f6517X.length; i++) {
            this.f6517X[i] = 0;
        }
        this.mOff = 0;
        for (int i2 = 0; i2 != this.f6516M.length; i2++) {
            this.f6516M[i2] = 0;
        }
        this.COff = 0;
        for (int i3 = 0; i3 != this.f6515C.length; i3++) {
            this.f6515C[i3] = 0;
        }
    }

    public void update(byte in) {
        byte[] bArr = this.f6516M;
        int i = this.mOff;
        this.mOff = i + 1;
        bArr[i] = in;
        if (this.mOff == 16) {
            processCheckSum(this.f6516M);
            processBlock(this.f6516M);
            this.mOff = 0;
        }
    }

    public void update(byte[] in, int inOff, int len) {
        while (this.mOff != 0 && len > 0) {
            update(in[inOff]);
            inOff++;
            len--;
        }
        while (len > 16) {
            System.arraycopy(in, inOff, this.f6516M, 0, 16);
            processCheckSum(this.f6516M);
            processBlock(this.f6516M);
            len -= 16;
            inOff += 16;
        }
        while (len > 0) {
            update(in[inOff]);
            inOff++;
            len--;
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v0, types: [byte] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=byte, code=int, for r0v2, types: [byte] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void processCheckSum(byte[] r7) {
        /*
            r6 = this;
            byte[] r2 = r6.f6515C
            r3 = 15
            byte r0 = r2[r3]
            r1 = 0
        L_0x0007:
            r2 = 16
            if (r1 >= r2) goto L_0x0023
            byte[] r2 = r6.f6515C
            byte r3 = r2[r1]
            byte[] r4 = f6514S
            byte r5 = r7[r1]
            r5 = r5 ^ r0
            r5 = r5 & 255(0xff, float:3.57E-43)
            byte r4 = r4[r5]
            r3 = r3 ^ r4
            byte r3 = (byte) r3
            r2[r1] = r3
            byte[] r2 = r6.f6515C
            byte r0 = r2[r1]
            int r1 = r1 + 1
            goto L_0x0007
        L_0x0023:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.spongycastle.crypto.digests.MD2Digest.processCheckSum(byte[]):void");
    }

    /* access modifiers changed from: protected */
    public void processBlock(byte[] m) {
        for (int i = 0; i < 16; i++) {
            this.f6517X[i + 16] = m[i];
            this.f6517X[i + 32] = (byte) (m[i] ^ this.f6517X[i]);
        }
        int t = 0;
        for (int j = 0; j < 18; j++) {
            for (int k = 0; k < 48; k++) {
                byte[] bArr = this.f6517X;
                byte t2 = (byte) (bArr[k] ^ f6514S[t]);
                bArr[k] = t2;
                t = t2 & 255;
            }
            t = (t + j) % 256;
        }
    }

    public int getByteLength() {
        return 16;
    }

    public Memoable copy() {
        return new MD2Digest(this);
    }

    public void reset(Memoable other) {
        copyIn((MD2Digest) other);
    }
}
