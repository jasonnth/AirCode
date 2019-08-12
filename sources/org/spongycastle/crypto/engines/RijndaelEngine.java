package org.spongycastle.crypto.engines;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import com.airbnb.android.managelisting.settings.DatesModalActivity;
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

public class RijndaelEngine implements BlockCipher {
    private static final int MAXKC = 64;
    private static final int MAXROUNDS = 14;

    /* renamed from: S */
    private static final byte[] f6692S = {99, 124, 119, 123, -14, 107, ISOFileInfo.FCI_BYTE, -59, ISO7816.INS_DECREASE, 1, 103, 43, -2, -41, ISOFileInfo.f6311AB, 118, ISO7816.INS_GET_DATA, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, ISO7816.INS_GET_RESPONSE, -73, -3, -109, 38, 54, 63, -9, -52, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.f6310A5, -27, -15, 113, ISO7816.INS_LOAD_KEY_FILE, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, ISOFileInfo.DATA_BYTES1, ISO7816.INS_APPEND_RECORD, -21, 39, -78, 117, 9, ISOFileInfo.FILE_IDENTIFIER, ISO7816.INS_UNBLOCK_CHV, 26, 27, 110, 90, ISOFileInfo.f6307A0, 82, 59, ISO7816.INS_UPDATE_BINARY, ISO7816.INS_READ_RECORD2, 41, -29, 47, -124, 83, -47, 0, -19, ISO7816.INS_VERIFY, -4, ISO7816.INS_READ_BINARY2, 91, 106, -53, -66, 57, 74, 76, 88, -49, ISO7816.INS_WRITE_BINARY, -17, -86, -5, 67, 77, 51, ISOFileInfo.PROP_INFO, 69, -7, 2, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_READ_RECORD_STAMPED, ISO7816.INS_PUT_DATA, 33, 16, -1, -13, ISO7816.INS_WRITE_RECORD, -51, PassportService.SF_DG12, 19, -20, 95, -105, ISO7816.INS_REHABILITATE_CHV, 23, -60, -89, 126, 61, ISOFileInfo.FMD_BYTE, 93, 25, 115, 96, ISOFileInfo.DATA_BYTES2, 79, ISO7816.INS_UPDATE_RECORD, ISO7816.INS_MSE, ISO7816.INS_PSO, -112, -120, 70, -18, -72, 20, -34, 94, PassportService.SF_DG11, -37, ISO7816.INS_CREATE_FILE, ISO7816.INS_INCREASE, 58, 10, 73, 6, ISO7816.INS_CHANGE_CHV, 92, ISO7816.INS_ENVELOPE, -45, -84, ISOFileInfo.FCP_BYTE, -111, -107, ISO7816.INS_DELETE_FILE, 121, -25, -56, 55, 109, ISOFileInfo.ENV_TEMP_EF, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, PassportService.SF_CVCA, -90, ISO7816.INS_READ_BINARY_STAMPED, -58, -24, -35, 116, 31, 75, -67, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.LCS_BYTE, ISO7816.INS_MANAGE_CHANNEL, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, PassportService.SF_SOD, -98, -31, -8, -104, 17, 105, -39, ISOFileInfo.CHANNEL_SECURITY, -108, -101, PassportService.SF_COM, ISOFileInfo.FCI_EXT, -23, -50, 85, 40, -33, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.f6308A1, -119, 13, -65, -26, CVCAFile.CAR_TAG, 104, 65, -103, 45, 15, ISO7816.INS_READ_BINARY, 84, -69, 22};

    /* renamed from: Si */
    private static final byte[] f6693Si = {82, 9, 106, -43, ISO7816.INS_DECREASE, 54, ISOFileInfo.f6310A5, 56, -65, 64, -93, -98, ISOFileInfo.DATA_BYTES2, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.CHANNEL_SECURITY, 67, ISO7816.INS_REHABILITATE_CHV, -60, -34, -23, -53, 84, 123, -108, ISO7816.INS_INCREASE, -90, ISO7816.INS_ENVELOPE, 35, 61, -18, 76, -107, PassportService.SF_DG11, CVCAFile.CAR_TAG, -6, -61, 78, 8, 46, ISOFileInfo.f6308A1, 102, 40, -39, ISO7816.INS_CHANGE_CHV, -78, 118, 91, -94, 73, 109, ISOFileInfo.SECURITY_ATTR_EXP, -47, 37, 114, -8, -10, ISOFileInfo.FMD_BYTE, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, ISO7816.INS_READ_RECORD_STAMPED, -110, 108, ISO7816.INS_MANAGE_CHANNEL, 72, 80, -3, -19, -71, ISO7816.INS_PUT_DATA, 94, 21, 70, 87, -89, ISOFileInfo.ENV_TEMP_EF, -99, -124, -112, ISO7816.INS_LOAD_KEY_FILE, ISOFileInfo.f6311AB, 0, ISOFileInfo.SECURITY_ATTR_COMPACT, PSSSigner.TRAILER_IMPLICIT, -45, 10, -9, ISO7816.INS_DELETE_FILE, 88, 5, -72, ISO7816.INS_READ_RECORD2, 69, 6, ISO7816.INS_WRITE_BINARY, ISO7816.INS_UNBLOCK_CHV, PassportService.SF_COM, -113, ISO7816.INS_GET_DATA, 63, 15, 2, -63, -81, -67, 3, 1, 19, ISOFileInfo.LCS_BYTE, 107, 58, -111, 17, 65, 79, 103, ISO7816.INS_UPDATE_RECORD, -22, -105, -14, -49, -50, -16, ISO7816.INS_READ_BINARY_STAMPED, -26, 115, -106, -84, 116, ISO7816.INS_MSE, -25, -83, 53, ISOFileInfo.PROP_INFO, ISO7816.INS_APPEND_RECORD, -7, 55, -24, PassportService.SF_CVCA, 117, -33, 110, 71, -15, 26, 113, PassportService.SF_SOD, 41, -59, -119, ISOFileInfo.FCI_BYTE, -73, ISOFileInfo.FCP_BYTE, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, ISO7816.INS_WRITE_RECORD, 121, ISO7816.INS_VERIFY, -102, -37, ISO7816.INS_GET_RESPONSE, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, ISO7816.INS_READ_BINARY2, 18, 16, 89, 39, ISOFileInfo.DATA_BYTES1, -20, 95, 96, 81, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, ISOFileInfo.f6307A0, ISO7816.INS_CREATE_FILE, 59, 77, -82, ISO7816.INS_PSO, -11, ISO7816.INS_READ_BINARY, -56, -21, -69, 60, ISOFileInfo.FILE_IDENTIFIER, 83, -103, 97, 23, 43, 4, 126, -70, 119, ISO7816.INS_UPDATE_BINARY, 38, -31, 105, 20, 99, 85, 33, PassportService.SF_DG12, 125};
    private static final byte[] aLogtable = {0, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, ISOFileInfo.f6308A1, -8, 19, 53, 95, -31, 56, 72, ISO7816.INS_LOAD_KEY_FILE, 115, -107, -92, -9, 2, 6, 10, PassportService.SF_COM, ISO7816.INS_MSE, 102, -86, -27, ISO7816.INS_DECREASE_STAMPED, 92, ISO7816.INS_DELETE_FILE, 55, 89, -21, 38, 106, -66, -39, ISO7816.INS_MANAGE_CHANNEL, -112, ISOFileInfo.f6311AB, -26, 49, 83, -11, 4, PassportService.SF_DG12, 20, 60, ISO7816.INS_REHABILITATE_CHV, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, ISO7816.INS_CREATE_FILE, 59, 77, -41, ISOFileInfo.FCP_BYTE, -90, -15, 8, 24, 40, 120, -120, ISOFileInfo.FILE_IDENTIFIER, -98, -71, ISO7816.INS_WRITE_BINARY, 107, -67, ISO7816.INS_UPDATE_RECORD, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, ISOFileInfo.DATA_BYTES2, -104, ISO7816.INS_READ_RECORD2, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, ISO7816.INS_DECREASE, 80, -16, PassportService.SF_DG11, PassportService.SF_SOD, 39, 105, -69, ISO7816.INS_UPDATE_BINARY, 97, -93, -2, 25, 43, 125, ISOFileInfo.FCI_EXT, -110, -83, -20, 47, 113, -109, -82, -23, ISO7816.INS_VERIFY, 96, ISOFileInfo.f6307A0, -5, 22, 58, 78, ISO7816.INS_WRITE_RECORD, 109, -73, ISO7816.INS_ENVELOPE, 93, -25, ISO7816.INS_INCREASE, 86, -6, 21, 63, 65, -61, 94, ISO7816.INS_APPEND_RECORD, 61, 71, -55, 64, ISO7816.INS_GET_RESPONSE, 91, -19, ISO7816.INS_UNBLOCK_CHV, 116, -100, -65, ISO7816.INS_PUT_DATA, 117, -97, -70, -43, ISOFileInfo.FMD_BYTE, -84, -17, ISO7816.INS_PSO, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, ISOFileInfo.CHANNEL_SECURITY, -119, ISOFileInfo.DATA_BYTES1, -101, ISO7816.INS_READ_RECORD_STAMPED, -63, 88, -24, 35, 101, -81, -22, 37, ISOFileInfo.FCI_BYTE, ISO7816.INS_READ_BINARY2, -56, 67, -59, 84, -4, 31, 33, 99, ISOFileInfo.f6310A5, -12, 7, 9, 27, 45, 119, -103, ISO7816.INS_READ_BINARY, -53, 70, ISO7816.INS_GET_DATA, 69, -49, 74, -34, 121, ISOFileInfo.SECURITY_ATTR_EXP, -122, -111, -88, -29, 62, CVCAFile.CAR_TAG, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.SECURITY_ATTR_COMPACT, -113, ISOFileInfo.LCS_BYTE, ISOFileInfo.PROP_INFO, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, PassportService.SF_CVCA, ISO7816.INS_CHANGE_CHV, 108, ISO7816.INS_READ_BINARY_STAMPED, -57, 82, -10, 1, 3, 5, 15, 17, 51, 85, -1, 26, 46, 114, -106, ISOFileInfo.f6308A1, -8, 19, 53, 95, -31, 56, 72, ISO7816.INS_LOAD_KEY_FILE, 115, -107, -92, -9, 2, 6, 10, PassportService.SF_COM, ISO7816.INS_MSE, 102, -86, -27, ISO7816.INS_DECREASE_STAMPED, 92, ISO7816.INS_DELETE_FILE, 55, 89, -21, 38, 106, -66, -39, ISO7816.INS_MANAGE_CHANNEL, -112, ISOFileInfo.f6311AB, -26, 49, 83, -11, 4, PassportService.SF_DG12, 20, 60, ISO7816.INS_REHABILITATE_CHV, -52, 79, -47, 104, -72, -45, 110, -78, -51, 76, -44, 103, -87, ISO7816.INS_CREATE_FILE, 59, 77, -41, ISOFileInfo.FCP_BYTE, -90, -15, 8, 24, 40, 120, -120, ISOFileInfo.FILE_IDENTIFIER, -98, -71, ISO7816.INS_WRITE_BINARY, 107, -67, ISO7816.INS_UPDATE_RECORD, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, ISOFileInfo.DATA_BYTES2, -104, ISO7816.INS_READ_RECORD2, -50, 73, -37, 118, -102, -75, -60, 87, -7, 16, ISO7816.INS_DECREASE, 80, -16, PassportService.SF_DG11, PassportService.SF_SOD, 39, 105, -69, ISO7816.INS_UPDATE_BINARY, 97, -93, -2, 25, 43, 125, ISOFileInfo.FCI_EXT, -110, -83, -20, 47, 113, -109, -82, -23, ISO7816.INS_VERIFY, 96, ISOFileInfo.f6307A0, -5, 22, 58, 78, ISO7816.INS_WRITE_RECORD, 109, -73, ISO7816.INS_ENVELOPE, 93, -25, ISO7816.INS_INCREASE, 86, -6, 21, 63, 65, -61, 94, ISO7816.INS_APPEND_RECORD, 61, 71, -55, 64, ISO7816.INS_GET_RESPONSE, 91, -19, ISO7816.INS_UNBLOCK_CHV, 116, -100, -65, ISO7816.INS_PUT_DATA, 117, -97, -70, -43, ISOFileInfo.FMD_BYTE, -84, -17, ISO7816.INS_PSO, 126, -126, -99, PSSSigner.TRAILER_IMPLICIT, -33, 122, ISOFileInfo.CHANNEL_SECURITY, -119, ISOFileInfo.DATA_BYTES1, -101, ISO7816.INS_READ_RECORD_STAMPED, -63, 88, -24, 35, 101, -81, -22, 37, ISOFileInfo.FCI_BYTE, ISO7816.INS_READ_BINARY2, -56, 67, -59, 84, -4, 31, 33, 99, ISOFileInfo.f6310A5, -12, 7, 9, 27, 45, 119, -103, ISO7816.INS_READ_BINARY, -53, 70, ISO7816.INS_GET_DATA, 69, -49, 74, -34, 121, ISOFileInfo.SECURITY_ATTR_EXP, -122, -111, -88, -29, 62, CVCAFile.CAR_TAG, -58, 81, -13, 14, 18, 54, 90, -18, 41, 123, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.SECURITY_ATTR_COMPACT, -113, ISOFileInfo.LCS_BYTE, ISOFileInfo.PROP_INFO, -108, -89, -14, 13, 23, 57, 75, -35, 124, -124, -105, -94, -3, PassportService.SF_CVCA, ISO7816.INS_CHANGE_CHV, 108, ISO7816.INS_READ_BINARY_STAMPED, -57, 82, -10, 1};
    private static final byte[] logtable = {0, 0, 25, 1, ISO7816.INS_INCREASE, 2, 26, -58, 75, -57, 27, 104, 51, -18, -33, 3, ISOFileInfo.FMD_BYTE, 4, ISO7816.INS_CREATE_FILE, 14, ISO7816.INS_DECREASE_STAMPED, ISOFileInfo.ENV_TEMP_EF, ISOFileInfo.DATA_BYTES2, -17, 76, 113, 8, -56, -8, 105, PassportService.SF_CVCA, -63, 125, ISO7816.INS_ENVELOPE, PassportService.SF_SOD, -75, -7, -71, 39, 106, 77, ISO7816.INS_DELETE_FILE, -90, 114, -102, -55, 9, 120, 101, 47, ISOFileInfo.LCS_BYTE, 5, 33, 15, -31, ISO7816.INS_CHANGE_CHV, 18, -16, -126, 69, 53, -109, ISO7816.INS_PUT_DATA, ISOFileInfo.CHANNEL_SECURITY, -106, -113, -37, -67, 54, ISO7816.INS_WRITE_BINARY, -50, -108, 19, 92, ISO7816.INS_WRITE_RECORD, -15, 64, 70, ISOFileInfo.FILE_IDENTIFIER, 56, 102, -35, -3, ISO7816.INS_DECREASE, -65, 6, ISOFileInfo.SECURITY_ATTR_EXP, ISOFileInfo.FCP_BYTE, ISO7816.INS_READ_RECORD2, 37, ISO7816.INS_APPEND_RECORD, -104, ISO7816.INS_MSE, -120, -111, 16, 126, 110, 72, -61, -93, ISO7816.INS_READ_RECORD_STAMPED, PassportService.SF_COM, CVCAFile.CAR_TAG, 58, 107, 40, 84, -6, ISOFileInfo.PROP_INFO, 61, -70, 43, 121, 10, 21, -101, -97, 94, ISO7816.INS_GET_DATA, 78, -44, -84, -27, -13, 115, -89, 87, -81, 88, -88, 80, -12, -22, ISO7816.INS_UPDATE_BINARY, 116, 79, -82, -23, -43, -25, -26, -83, -24, ISO7816.INS_UNBLOCK_CHV, -41, 117, 122, -21, 22, PassportService.SF_DG11, -11, 89, -53, 95, ISO7816.INS_READ_BINARY, -100, -87, 81, ISOFileInfo.f6307A0, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, PassportService.SF_DG12, -10, ISOFileInfo.FCI_BYTE, 23, -60, 73, -20, ISO7816.INS_LOAD_KEY_FILE, 67, 31, 45, -92, 118, 123, -73, -52, -69, 62, 90, -5, 96, ISO7816.INS_READ_BINARY2, -122, 59, 82, ISOFileInfo.f6308A1, 108, -86, 85, 41, -99, -105, -78, ISOFileInfo.FCI_EXT, -112, 97, -66, ISO7816.INS_UPDATE_RECORD, -4, PSSSigner.TRAILER_IMPLICIT, -107, -49, -51, 55, 63, 91, -47, 83, 57, -124, 60, 65, -94, 109, 71, 20, ISO7816.INS_PSO, -98, 93, 86, -14, -45, ISOFileInfo.f6311AB, ISO7816.INS_REHABILITATE_CHV, 17, -110, -39, 35, ISO7816.INS_VERIFY, 46, -119, ISO7816.INS_READ_BINARY_STAMPED, 124, -72, 38, 119, -103, -29, ISOFileInfo.f6310A5, 103, 74, -19, -34, -59, 49, -2, 24, 13, 99, ISOFileInfo.SECURITY_ATTR_COMPACT, ISOFileInfo.DATA_BYTES1, ISO7816.INS_GET_RESPONSE, -9, ISO7816.INS_MANAGE_CHANNEL, 7};
    private static final int[] rcon = {1, 2, 4, 8, 16, 32, 64, 128, 27, 54, 108, JfifUtil.MARKER_SOI, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, 77, CipherSuite.TLS_DHE_RSA_WITH_SEED_CBC_SHA, 47, 94, 188, 99, 198, CipherSuite.TLS_DH_DSS_WITH_SEED_CBC_SHA, 53, 106, 212, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA384, 125, 250, 239, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA};
    static byte[][] shifts0 = {new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, 24}, new byte[]{0, 8, 16, ISO7816.INS_VERIFY}, new byte[]{0, 8, 24, ISO7816.INS_VERIFY}};
    static byte[][] shifts1 = {new byte[]{0, 24, 16, 8}, new byte[]{0, ISO7816.INS_VERIFY, 24, 16}, new byte[]{0, 40, ISO7816.INS_VERIFY, 24}, new byte[]{0, ISO7816.INS_DECREASE, 40, 24}, new byte[]{0, 56, 40, ISO7816.INS_VERIFY}};

    /* renamed from: A0 */
    private long f6694A0;

    /* renamed from: A1 */
    private long f6695A1;

    /* renamed from: A2 */
    private long f6696A2;

    /* renamed from: A3 */
    private long f6697A3;

    /* renamed from: BC */
    private int f6698BC;
    private long BC_MASK;
    private int ROUNDS;
    private int blockBits;
    private boolean forEncryption;
    private byte[] shifts0SC;
    private byte[] shifts1SC;
    private long[][] workingKey;

    private byte mul0x2(int b) {
        if (b != 0) {
            return aLogtable[(logtable[b] & 255) + 25];
        }
        return 0;
    }

    private byte mul0x3(int b) {
        if (b != 0) {
            return aLogtable[(logtable[b] & 255) + 1];
        }
        return 0;
    }

    private byte mul0x9(int b) {
        if (b >= 0) {
            return aLogtable[b + 199];
        }
        return 0;
    }

    private byte mul0xb(int b) {
        if (b >= 0) {
            return aLogtable[b + 104];
        }
        return 0;
    }

    private byte mul0xd(int b) {
        if (b >= 0) {
            return aLogtable[b + 238];
        }
        return 0;
    }

    private byte mul0xe(int b) {
        if (b >= 0) {
            return aLogtable[b + DatesModalActivity.RESULT_CODE];
        }
        return 0;
    }

    private void KeyAddition(long[] rk) {
        this.f6694A0 ^= rk[0];
        this.f6695A1 ^= rk[1];
        this.f6696A2 ^= rk[2];
        this.f6697A3 ^= rk[3];
    }

    private long shift(long r, int shift) {
        return ((r >>> shift) | (r << (this.f6698BC - shift))) & this.BC_MASK;
    }

    private void ShiftRow(byte[] shiftsSC) {
        this.f6695A1 = shift(this.f6695A1, shiftsSC[1]);
        this.f6696A2 = shift(this.f6696A2, shiftsSC[2]);
        this.f6697A3 = shift(this.f6697A3, shiftsSC[3]);
    }

    private long applyS(long r, byte[] box) {
        long res = 0;
        for (int j = 0; j < this.f6698BC; j += 8) {
            res |= ((long) (box[(int) ((r >> j) & 255)] & 255)) << j;
        }
        return res;
    }

    private void Substitution(byte[] box) {
        this.f6694A0 = applyS(this.f6694A0, box);
        this.f6695A1 = applyS(this.f6695A1, box);
        this.f6696A2 = applyS(this.f6696A2, box);
        this.f6697A3 = applyS(this.f6697A3, box);
    }

    private void MixColumn() {
        long r3 = 0;
        long r2 = 0;
        long r1 = 0;
        long r0 = 0;
        for (int j = 0; j < this.f6698BC; j += 8) {
            int a0 = (int) ((this.f6694A0 >> j) & 255);
            int a1 = (int) ((this.f6695A1 >> j) & 255);
            int a2 = (int) ((this.f6696A2 >> j) & 255);
            int a3 = (int) ((this.f6697A3 >> j) & 255);
            r0 |= ((long) ((((mul0x2(a0) ^ mul0x3(a1)) ^ a2) ^ a3) & 255)) << j;
            r1 |= ((long) ((((mul0x2(a1) ^ mul0x3(a2)) ^ a3) ^ a0) & 255)) << j;
            r2 |= ((long) ((((mul0x2(a2) ^ mul0x3(a3)) ^ a0) ^ a1) & 255)) << j;
            r3 |= ((long) ((((mul0x2(a3) ^ mul0x3(a0)) ^ a1) ^ a2) & 255)) << j;
        }
        this.f6694A0 = r0;
        this.f6695A1 = r1;
        this.f6696A2 = r2;
        this.f6697A3 = r3;
    }

    private void InvMixColumn() {
        long r3 = 0;
        long r2 = 0;
        long r1 = 0;
        long r0 = 0;
        for (int j = 0; j < this.f6698BC; j += 8) {
            int a0 = (int) ((this.f6694A0 >> j) & 255);
            int a1 = (int) ((this.f6695A1 >> j) & 255);
            int a2 = (int) ((this.f6696A2 >> j) & 255);
            int a3 = (int) ((this.f6697A3 >> j) & 255);
            int a02 = a0 != 0 ? logtable[a0 & 255] & 255 : -1;
            int a12 = a1 != 0 ? logtable[a1 & 255] & 255 : -1;
            int a22 = a2 != 0 ? logtable[a2 & 255] & 255 : -1;
            int a32 = a3 != 0 ? logtable[a3 & 255] & 255 : -1;
            r0 |= ((long) ((((mul0xe(a02) ^ mul0xb(a12)) ^ mul0xd(a22)) ^ mul0x9(a32)) & 255)) << j;
            r1 |= ((long) ((((mul0xe(a12) ^ mul0xb(a22)) ^ mul0xd(a32)) ^ mul0x9(a02)) & 255)) << j;
            r2 |= ((long) ((((mul0xe(a22) ^ mul0xb(a32)) ^ mul0xd(a02)) ^ mul0x9(a12)) & 255)) << j;
            r3 |= ((long) ((((mul0xe(a32) ^ mul0xb(a02)) ^ mul0xd(a12)) ^ mul0x9(a22)) & 255)) << j;
        }
        this.f6694A0 = r0;
        this.f6695A1 = r1;
        this.f6696A2 = r2;
        this.f6697A3 = r3;
    }

    private long[][] generateWorkingKey(byte[] key) {
        int KC;
        int rconpointer = 0;
        int keyBits = key.length * 8;
        byte[][] tk = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{4, 64});
        long[][] W = (long[][]) Array.newInstance(Long.TYPE, new int[]{15, 4});
        switch (keyBits) {
            case 128:
                KC = 4;
                break;
            case 160:
                KC = 5;
                break;
            case 192:
                KC = 6;
                break;
            case 224:
                KC = 7;
                break;
            case 256:
                KC = 8;
                break;
            default:
                throw new IllegalArgumentException("Key length not 128/160/192/224/256 bits.");
        }
        if (keyBits >= this.blockBits) {
            this.ROUNDS = KC + 6;
        } else {
            this.ROUNDS = (this.f6698BC / 8) + 6;
        }
        int index = 0;
        int i = 0;
        while (i < key.length) {
            int index2 = index + 1;
            tk[i % 4][i / 4] = key[index];
            i++;
            index = index2;
        }
        int t = 0;
        int j = 0;
        while (j < KC && t < (this.ROUNDS + 1) * (this.f6698BC / 8)) {
            for (int i2 = 0; i2 < 4; i2++) {
                long[] jArr = W[t / (this.f6698BC / 8)];
                jArr[i2] = jArr[i2] | (((long) (tk[i2][j] & 255)) << ((t * 8) % this.f6698BC));
            }
            j++;
            t++;
        }
        while (t < (this.ROUNDS + 1) * (this.f6698BC / 8)) {
            for (int i3 = 0; i3 < 4; i3++) {
                byte[] bArr = tk[i3];
                bArr[0] = (byte) (bArr[0] ^ f6692S[tk[(i3 + 1) % 4][KC - 1] & 255]);
            }
            byte[] bArr2 = tk[0];
            int rconpointer2 = rconpointer + 1;
            bArr2[0] = (byte) (bArr2[0] ^ rcon[rconpointer]);
            if (KC <= 6) {
                for (int j2 = 1; j2 < KC; j2++) {
                    for (int i4 = 0; i4 < 4; i4++) {
                        byte[] bArr3 = tk[i4];
                        bArr3[j2] = (byte) (bArr3[j2] ^ tk[i4][j2 - 1]);
                    }
                }
            } else {
                for (int j3 = 1; j3 < 4; j3++) {
                    for (int i5 = 0; i5 < 4; i5++) {
                        byte[] bArr4 = tk[i5];
                        bArr4[j3] = (byte) (bArr4[j3] ^ tk[i5][j3 - 1]);
                    }
                }
                for (int i6 = 0; i6 < 4; i6++) {
                    byte[] bArr5 = tk[i6];
                    bArr5[4] = (byte) (bArr5[4] ^ f6692S[tk[i6][3] & 255]);
                }
                for (int j4 = 5; j4 < KC; j4++) {
                    for (int i7 = 0; i7 < 4; i7++) {
                        byte[] bArr6 = tk[i7];
                        bArr6[j4] = (byte) (bArr6[j4] ^ tk[i7][j4 - 1]);
                    }
                }
            }
            int j5 = 0;
            while (j5 < KC && t < (this.ROUNDS + 1) * (this.f6698BC / 8)) {
                for (int i8 = 0; i8 < 4; i8++) {
                    long[] jArr2 = W[t / (this.f6698BC / 8)];
                    jArr2[i8] = jArr2[i8] | (((long) (tk[i8][j5] & 255)) << ((t * 8) % this.f6698BC));
                }
                j5++;
                t++;
            }
            rconpointer = rconpointer2;
        }
        return W;
    }

    public RijndaelEngine() {
        this(128);
    }

    public RijndaelEngine(int blockBits2) {
        switch (blockBits2) {
            case 128:
                this.f6698BC = 32;
                this.BC_MASK = 4294967295L;
                this.shifts0SC = shifts0[0];
                this.shifts1SC = shifts1[0];
                break;
            case 160:
                this.f6698BC = 40;
                this.BC_MASK = 1099511627775L;
                this.shifts0SC = shifts0[1];
                this.shifts1SC = shifts1[1];
                break;
            case 192:
                this.f6698BC = 48;
                this.BC_MASK = 281474976710655L;
                this.shifts0SC = shifts0[2];
                this.shifts1SC = shifts1[2];
                break;
            case 224:
                this.f6698BC = 56;
                this.BC_MASK = 72057594037927935L;
                this.shifts0SC = shifts0[3];
                this.shifts1SC = shifts1[3];
                break;
            case 256:
                this.f6698BC = 64;
                this.BC_MASK = -1;
                this.shifts0SC = shifts0[4];
                this.shifts1SC = shifts1[4];
                break;
            default:
                throw new IllegalArgumentException("unknown blocksize to Rijndael");
        }
        this.blockBits = blockBits2;
    }

    public void init(boolean forEncryption2, CipherParameters params) {
        if (params instanceof KeyParameter) {
            this.workingKey = generateWorkingKey(((KeyParameter) params).getKey());
            this.forEncryption = forEncryption2;
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to Rijndael init - " + params.getClass().getName());
    }

    public String getAlgorithmName() {
        return "Rijndael";
    }

    public int getBlockSize() {
        return this.f6698BC / 2;
    }

    public int processBlock(byte[] in, int inOff, byte[] out, int outOff) {
        if (this.workingKey == null) {
            throw new IllegalStateException("Rijndael engine not initialised");
        } else if ((this.f6698BC / 2) + inOff > in.length) {
            throw new DataLengthException("input buffer too short");
        } else if ((this.f6698BC / 2) + outOff > out.length) {
            throw new OutputLengthException("output buffer too short");
        } else {
            if (this.forEncryption) {
                unpackBlock(in, inOff);
                encryptBlock(this.workingKey);
                packBlock(out, outOff);
            } else {
                unpackBlock(in, inOff);
                decryptBlock(this.workingKey);
                packBlock(out, outOff);
            }
            return this.f6698BC / 2;
        }
    }

    public void reset() {
    }

    private void unpackBlock(byte[] bytes, int off) {
        int index = off;
        int index2 = index + 1;
        this.f6694A0 = (long) (bytes[index] & 255);
        int index3 = index2 + 1;
        this.f6695A1 = (long) (bytes[index2] & 255);
        int index4 = index3 + 1;
        this.f6696A2 = (long) (bytes[index3] & 255);
        int index5 = index4 + 1;
        this.f6697A3 = (long) (bytes[index4] & 255);
        for (int j = 8; j != this.f6698BC; j += 8) {
            int index6 = index5 + 1;
            this.f6694A0 |= ((long) (bytes[index5] & 255)) << j;
            int index7 = index6 + 1;
            this.f6695A1 |= ((long) (bytes[index6] & 255)) << j;
            int index8 = index7 + 1;
            this.f6696A2 |= ((long) (bytes[index7] & 255)) << j;
            index5 = index8 + 1;
            this.f6697A3 |= ((long) (bytes[index8] & 255)) << j;
        }
    }

    private void packBlock(byte[] bytes, int off) {
        int index = off;
        for (int j = 0; j != this.f6698BC; j += 8) {
            int index2 = index + 1;
            bytes[index] = (byte) ((int) (this.f6694A0 >> j));
            int index3 = index2 + 1;
            bytes[index2] = (byte) ((int) (this.f6695A1 >> j));
            int index4 = index3 + 1;
            bytes[index3] = (byte) ((int) (this.f6696A2 >> j));
            index = index4 + 1;
            bytes[index4] = (byte) ((int) (this.f6697A3 >> j));
        }
    }

    private void encryptBlock(long[][] rk) {
        KeyAddition(rk[0]);
        for (int r = 1; r < this.ROUNDS; r++) {
            Substitution(f6692S);
            ShiftRow(this.shifts0SC);
            MixColumn();
            KeyAddition(rk[r]);
        }
        Substitution(f6692S);
        ShiftRow(this.shifts0SC);
        KeyAddition(rk[this.ROUNDS]);
    }

    private void decryptBlock(long[][] rk) {
        KeyAddition(rk[this.ROUNDS]);
        Substitution(f6693Si);
        ShiftRow(this.shifts1SC);
        for (int r = this.ROUNDS - 1; r > 0; r--) {
            KeyAddition(rk[r]);
            InvMixColumn();
            Substitution(f6693Si);
            ShiftRow(this.shifts1SC);
        }
        KeyAddition(rk[0]);
    }
}
