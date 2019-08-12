package org.spongycastle.crypto.prng;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.signers.PSSSigner;
import org.spongycastle.util.Pack;

public class VMPCRandomGenerator implements RandomGenerator {

    /* renamed from: P */
    private byte[] f6868P = {-69, ISO7816.INS_UNBLOCK_CHV, ISOFileInfo.FCP_BYTE, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, -75, -86, -44, 13, ISOFileInfo.DATA_BYTES2, -2, -78, -126, -53, ISOFileInfo.f6307A0, ISOFileInfo.f6308A1, 8, 24, 113, 86, -24, 73, 2, 16, -60, -34, 53, ISOFileInfo.f6310A5, -20, ISOFileInfo.DATA_BYTES1, 18, -72, 105, ISO7816.INS_PUT_DATA, 47, 117, -52, -94, 9, 54, 3, 97, 45, -3, ISO7816.INS_CREATE_FILE, -35, 5, 67, -112, -83, -56, -31, -81, 87, -101, 76, ISO7816.INS_LOAD_KEY_FILE, 81, -82, 80, ISOFileInfo.PROP_INFO, 60, 10, ISO7816.INS_DELETE_FILE, -13, -100, 38, 35, 83, -55, ISOFileInfo.FILE_IDENTIFIER, -105, 70, ISO7816.INS_READ_BINARY2, -103, ISOFileInfo.FMD_BYTE, 49, 119, -43, PassportService.SF_SOD, ISO7816.INS_UPDATE_BINARY, 120, -67, 94, ISO7816.INS_READ_BINARY, ISOFileInfo.LCS_BYTE, ISO7816.INS_MSE, 56, -8, 104, 43, ISO7816.INS_PSO, -59, -45, -9, PSSSigner.TRAILER_IMPLICIT, ISOFileInfo.FCI_BYTE, -33, 4, -27, -107, 62, 37, -122, -90, PassportService.SF_DG11, -113, -15, ISO7816.INS_CHANGE_CHV, 14, -41, 64, ISO7816.INS_READ_RECORD2, -49, 126, 6, 21, -102, 77, PassportService.SF_CVCA, -93, -37, ISO7816.INS_INCREASE, -110, 88, 17, 39, -12, 89, ISO7816.INS_WRITE_BINARY, 78, 106, 23, 91, -84, -1, 7, ISO7816.INS_GET_RESPONSE, 101, 121, -4, -57, -51, 118, CVCAFile.CAR_TAG, 93, -25, 58, ISO7816.INS_DECREASE_STAMPED, 122, ISO7816.INS_DECREASE, 40, 15, 115, 1, -7, -47, ISO7816.INS_WRITE_RECORD, 25, -23, -111, -71, 90, -19, 65, 109, ISO7816.INS_READ_BINARY_STAMPED, -61, -98, -65, 99, -6, 31, 51, 96, 71, -119, -16, -106, 26, 95, -109, 61, 55, 75, -39, -88, -63, 27, -10, 57, ISOFileInfo.SECURITY_ATTR_EXP, -73, PassportService.SF_DG12, ISO7816.INS_VERIFY, -50, -120, 110, ISO7816.INS_READ_RECORD_STAMPED, 116, ISOFileInfo.CHANNEL_SECURITY, ISOFileInfo.ENV_TEMP_EF, 22, 41, -14, ISOFileInfo.FCI_EXT, -11, -21, ISO7816.INS_MANAGE_CHANNEL, -29, -5, 85, -97, -58, ISO7816.INS_REHABILITATE_CHV, 74, 69, 125, ISO7816.INS_APPEND_RECORD, 107, 92, 108, 102, -87, ISOFileInfo.SECURITY_ATTR_COMPACT, -18, -124, 19, -89, PassportService.SF_COM, -99, ISO7816.INS_UPDATE_RECORD, 103, 72, -70, 46, -26, -92, ISOFileInfo.f6311AB, 124, -108, 0, 33, -17, -22, -66, ISO7816.INS_GET_DATA, 114, 79, 82, -104, 63, ISO7816.INS_ENVELOPE, 20, 123, 59, 84};

    /* renamed from: n */
    private byte f6869n = 0;

    /* renamed from: s */
    private byte f6870s = -66;

    public void addSeedMaterial(byte[] seed) {
        for (byte b : seed) {
            this.f6870s = this.f6868P[(this.f6870s + this.f6868P[this.f6869n & 255] + b) & 255];
            byte temp = this.f6868P[this.f6869n & 255];
            this.f6868P[this.f6869n & 255] = this.f6868P[this.f6870s & 255];
            this.f6868P[this.f6870s & 255] = temp;
            this.f6869n = (byte) ((this.f6869n + 1) & 255);
        }
    }

    public void addSeedMaterial(long seed) {
        addSeedMaterial(Pack.longToBigEndian(seed));
    }

    public void nextBytes(byte[] bytes) {
        nextBytes(bytes, 0, bytes.length);
    }

    public void nextBytes(byte[] bytes, int start, int len) {
        synchronized (this.f6868P) {
            int end = start + len;
            for (int i = start; i != end; i++) {
                this.f6870s = this.f6868P[(this.f6870s + this.f6868P[this.f6869n & 255]) & 255];
                bytes[i] = this.f6868P[(this.f6868P[this.f6868P[this.f6870s & 255] & 255] + 1) & 255];
                byte temp = this.f6868P[this.f6869n & 255];
                this.f6868P[this.f6869n & 255] = this.f6868P[this.f6870s & 255];
                this.f6868P[this.f6870s & 255] = temp;
                this.f6869n = (byte) ((this.f6869n + 1) & 255);
            }
        }
    }
}
