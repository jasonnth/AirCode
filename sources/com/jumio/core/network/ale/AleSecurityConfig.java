package com.jumio.core.network.ale;

import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import com.jumio.commons.obfuscate.StringObfuscater;
import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.PassportService;
import org.jmrtd.lds.CVCAFile;
import org.spongycastle.crypto.signers.PSSSigner;

public class AleSecurityConfig {
    public static final String ALE_KEY_ID_EU = StringObfuscater.format(new byte[]{ISO7816.INS_READ_BINARY_STAMPED, 14, -119, -60, 43, ISO7816.INS_UPDATE_RECORD, 105, -3, ISO7816.INS_PSO, -13, 8, ISOFileInfo.f6307A0, 18, -82, -120, -110, 6, -6, 54, -124, ISO7816.INS_READ_BINARY2, 24, -75, -71, ISO7816.INS_GET_RESPONSE, 78, -19, 75, 57, -39, ISO7816.INS_UPDATE_BINARY, -93, -34, 79, 31, 110}, 5106852764819745678L);
    public static final String ALE_KEY_ID_US = StringObfuscater.format(new byte[]{-4, -44, -7, 99, -112, 75, 108, 89, -111, ISO7816.INS_DECREASE, 93, 76, 75, ISO7816.INS_MSE, ISOFileInfo.DATA_BYTES2, 33, -98, -87, 109, -70, -35, -87, -89, 40, 46, 85, -2, 7, -8, -99, 31, 49, 116, -66, 54, -18}, 7560213161391980577L);
    public static final String ALE_PUBLIC_KEY_EU = StringObfuscater.format(new byte[]{-101, 95, 59, 76, 110, 90, 125, 73, 81, 117, ISOFileInfo.f6307A0, 21, -43, ISO7816.INS_DECREASE, 37, -93, 89, -102, ISO7816.INS_VERIFY, 109, 106, 91, -29, -51, -29, -126, ISO7816.INS_GET_DATA, ISOFileInfo.SECURITY_ATTR_EXP, -3, 116, -23, -88, -34, -31, 61, ISOFileInfo.PROP_INFO, 35, 101, -19, 74, 82, ISO7816.INS_CHANGE_CHV, 25, -111, 63, 86, -84, 40, PassportService.SF_CVCA, ISO7816.INS_DECREASE, ISO7816.INS_MANAGE_CHANNEL, 58, -15, ISO7816.INS_MSE, ISO7816.INS_CHANGE_CHV, -84, 75, -3, 90, 13, -94, -67, 76, -107, 120, -1, 92, 122, 77, 78, 86, 21, PassportService.SF_DG12, ISO7816.INS_MSE, 49, 75, ISOFileInfo.FMD_BYTE, ISOFileInfo.LCS_BYTE, -35, 46, 74, -47, 124, 27, -83, 77, 10, 4, -29, -99, -98, -87, ISOFileInfo.FCI_EXT, 75, 92, -112, -82, 6, -72, 72, 104, -69, ISOFileInfo.f6307A0, 40, 51, -100, 72, 64, -97, -65, -43, -8, 5, -97, -53, ISOFileInfo.FCI_EXT, ISO7816.INS_DECREASE, -97, 60, ISO7816.INS_READ_RECORD2, -83, 81, 0, 25, -99, -34, -24, 109, ISO7816.INS_INCREASE, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, ISO7816.INS_INCREASE, -53, -41, 47, -37, -109, 99, 26, 96, -4, 7, ISOFileInfo.f6311AB, -98, ISOFileInfo.SECURITY_ATTR_COMPACT, -92, -58, -126, ISO7816.INS_UPDATE_BINARY, 121, 125, 107, -4, 92, 21, -70, ISO7816.INS_CREATE_FILE, 71, -17, -5, ISO7816.INS_PUT_DATA, -92, -81, 14, -24, PSSSigner.TRAILER_IMPLICIT, -65, -66, 106, 67, 74, 35, -55, -53, 15, 37, -87, -55, 83, -60, 16, ISO7816.INS_INCREASE, 120, -107, 107, 79, -53, 47, ISO7816.INS_INCREASE, 82, 65, 95, -41, 64, -24, -124, -29, -124, -112, ISOFileInfo.DATA_BYTES2, 51, 123, 47, 13, -21, 41, 105, ISO7816.INS_MANAGE_CHANNEL, 65, -51, 72, -63, -45, 63, 121, -70, 120, 2, 3, -15, 67, -69, ISOFileInfo.f6310A5, -5, 27, -112, 63, -78, 2, -75, -20, 116, ISOFileInfo.f6310A5, -57, 101, 123, 55, -61, 73, -98, -52, 39, PassportService.SF_COM, 57, 27, 17, ISOFileInfo.LCS_BYTE, -35, 82, -19, 49, -49, 104, 0, -105, -24, 24, -49, -7, 57, 101, 92, -88, -53, -56, 72, -119, ISOFileInfo.FCI_EXT, -23, 49, -65, ISO7816.INS_READ_RECORD2, ISOFileInfo.FCI_EXT, 109, 110, 105, 0, 92, -26, PassportService.SF_COM, 54, 27, -14, -82, ISO7816.INS_LOAD_KEY_FILE, 47, -111, ISO7816.INS_VERIFY, ISO7816.INS_GET_DATA, 104, -113, 2, 123, -105, -99, -24, 37, 76, -119, -16, -94, -113, 1, ISOFileInfo.PROP_INFO, 4, -92, -10, ISO7816.INS_READ_BINARY, 21, -122, 14, ISOFileInfo.f6311AB, -29, -18, -88, ISO7816.INS_INCREASE, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, 77, 78, ISO7816.INS_READ_RECORD2, PassportService.SF_DG11, 6, 91, ISO7816.INS_INCREASE, -3, 55, 63, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, ISO7816.INS_UPDATE_BINARY, 14, 23, -6, ISOFileInfo.DATA_BYTES1, -13, 93, 58, -19, -113, 114, -83, -103, 91, 23, ISO7816.INS_WRITE_BINARY, 78, 109, 126, ISO7816.INS_PSO, ISO7816.INS_DELETE_FILE, 35, ISO7816.INS_READ_BINARY, 9, 22, 86, 92, 69, 123, -119, ISO7816.INS_CHANGE_CHV, -47, -37, 93, 63, -97, 115, ISOFileInfo.ENV_TEMP_EF, 8, -5, -15, 117, 95, -41, 99, -22, 6, -83, 124, PSSSigner.TRAILER_IMPLICIT, 117, -89, 103, -60, -6, 51, -56, 120, ISO7816.INS_MSE, 7, -94, 41, -45, 56, ISO7816.INS_INCREASE, 118, -93, -82, -99, 96, 123, 69, 109, -106, 106, ISO7816.INS_GET_RESPONSE, -43, 60, 35, 91, -78, 2, ISOFileInfo.PROP_INFO, -69, -104, -22, 125, 27, 106, 4, PassportService.SF_CVCA, ISO7816.INS_INCREASE, -100, 82, ISOFileInfo.FCI_EXT, -53, -11, 2, 114, ISOFileInfo.f6310A5, 53, -51, 113, 16, 37, 75, -113, ISOFileInfo.f6310A5, ISOFileInfo.CHANNEL_SECURITY, -119, -111, 15, ISO7816.INS_WRITE_RECORD, -94, 91, 110, 47, -50, 45, 120, 54, -41, 124, -52, ISO7816.INS_MSE, 33, -24, -93, -102, 84, 69}, 8592865346577331660L).replaceAll("\\\\n", "\n");
    public static final String ALE_PUBLIC_KEY_US = StringObfuscater.format(new byte[]{118, -6, 96, ISO7816.INS_WRITE_RECORD, ISO7816.INS_READ_RECORD_STAMPED, 49, 0, ISO7816.INS_WRITE_BINARY, ISOFileInfo.FCI_BYTE, 71, -86, 0, -15, 33, -81, 60, 63, 80, 81, -8, 103, 102, -20, -52, -8, 26, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, ISO7816.INS_WRITE_BINARY, -120, -72, 49, 17, -99, -101, -7, -60, 15, PassportService.SF_DG12, -1, ISO7816.INS_DECREASE_STAMPED, 80, 113, 115, -52, -63, -106, ISOFileInfo.f6311AB, -102, ISO7816.INS_READ_RECORD_STAMPED, 24, 15, -70, 114, -16, -87, -59, 59, ISOFileInfo.PROP_INFO, 39, -94, -58, -1, ISO7816.INS_WRITE_RECORD, 69, 85, PassportService.SF_SOD, -14, 73, 125, ISO7816.INS_REHABILITATE_CHV, CVCAFile.CAR_TAG, -57, 88, 104, ISO7816.INS_READ_RECORD2, 26, -107, -21, 103, 95, -22, 45, 81, -83, 56, -102, 9, ISOFileInfo.f6307A0, -21, 106, 21, ISO7816.INS_DELETE_FILE, ISO7816.INS_DECREASE_STAMPED, -39, 126, 58, 45, ISOFileInfo.FMD_BYTE, 114, 18, -15, ISOFileInfo.FCI_BYTE, ISOFileInfo.FCI_BYTE, -13, 40, 108, -25, -26, -66, -105, -22, -20, ISOFileInfo.FCI_EXT, -31, -22, -53, -75, 118, 6, 43, 114, 90, 65, 65, 53, 4, ISO7816.INS_REHABILITATE_CHV, ISO7816.INS_MANAGE_CHANNEL, -44, -57, 125, -6, -88, CVCAFile.CAR_TAG, -24, 61, 104, -37, 104, -100, 67, -120, 96, 45, 81, -44, 16, AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY, -124, ISO7816.INS_READ_BINARY2, -19, -50, 7, -108, ISOFileInfo.FMD_BYTE, -14, -43, ISO7816.INS_REHABILITATE_CHV, ISOFileInfo.f6308A1, -107, 16, 121, -75, 67, 115, 39, -7, 2, -13, -69, -29, 114, -14, 76, -51, 55, 54, ISO7816.INS_DELETE_FILE, 65, ISO7816.INS_DECREASE, PassportService.SF_DG12, 3, 38, ISOFileInfo.ENV_TEMP_EF, -65, -23, ISOFileInfo.PROP_INFO, 91, -37, 56, -97, 114, -72, -8, ISO7816.INS_VERIFY, -26, -50, -124, ISO7816.INS_DELETE_FILE, -33, PassportService.SF_COM, ISO7816.INS_REHABILITATE_CHV, -99, 110, -11, ISOFileInfo.DATA_BYTES2, 75, 97, -57, ISO7816.INS_WRITE_BINARY, 40, -111, -6, 57, -97, 65, -52, -90, ISO7816.INS_CHANGE_CHV, 14, -126, -107, 8, 104, 123, 13, -98, 123, -98, ISOFileInfo.LCS_BYTE, 99, 51, -10, 55, 1, 123, ISO7816.INS_VERIFY, 84, -37, 35, -63, 46, 114, 23, 43, 8, -86, 94, 37, -6, -109, 76, ISOFileInfo.f6307A0, -56, ISO7816.INS_WRITE_RECORD, 105, 83, -67, 93, 19, ISO7816.INS_WRITE_RECORD, -1, ISOFileInfo.FCI_BYTE, 49, 24, 123, -70, ISO7816.INS_PSO, 2, -51, 62, 107, 26, -63, 77, 55, 51, ISO7816.INS_UPDATE_RECORD, 62, ISO7816.INS_DECREASE_STAMPED, -111, 9, -7, -105, -104, 21, 61, 108, -7, 72, ISO7816.INS_INCREASE, ISOFileInfo.CHANNEL_SECURITY, ISO7816.INS_INCREASE, -107, PassportService.SF_CVCA, 108, ISO7816.INS_DECREASE_STAMPED, 77, 7, 5, 9, 15, 104, ISO7816.INS_GET_RESPONSE, -66, ISOFileInfo.f6310A5, 62, -78, ISOFileInfo.DATA_BYTES1, -113, -19, -89, 22, 8, -110, ISOFileInfo.f6311AB, ISOFileInfo.DATA_BYTES2, ISO7816.INS_READ_RECORD2, 43, 118, 123, -94, ISO7816.INS_MANAGE_CHANNEL, 14, -71, -94, 110, 72, 120, 56, 107, ISOFileInfo.f6311AB, ISO7816.INS_WRITE_BINARY, 18, -49, -50, -109, 117, ISO7816.INS_DECREASE, PassportService.SF_DG12, -24, 89, 118, -87, -93, 65, 69, ISOFileInfo.FCI_EXT, -34, 106, ISO7816.INS_PUT_DATA, 91, -23, -59, ISOFileInfo.LCS_BYTE, 88, -82, PassportService.SF_CVCA, -61, -18, 83, -5, 58, 113, ISO7816.INS_MANAGE_CHANNEL, 24, 15, 121, -33, 86, 23, PSSSigner.TRAILER_IMPLICIT, ISO7816.INS_GET_RESPONSE, -101, -55, -101, -61, -106, -16, -86, 120, 84, 69, 23, ISOFileInfo.ENV_TEMP_EF, ISO7816.INS_ENVELOPE, 63, ISOFileInfo.SECURITY_ATTR_EXP, -112, -81, -89, 125, 10, -57, 33, -102, -89, 69, 76, 13, 13, 82, -119, 23, ISOFileInfo.FILE_IDENTIFIER, 5, 76, 2, ISO7816.INS_ENVELOPE, -66, ISOFileInfo.f6307A0, 71, -57, 24, ISO7816.INS_APPEND_RECORD, 122, ISO7816.INS_INCREASE, -56, -75, -94, -93, 56, 118, -43, 23, -24, -41, -98, -29, 64, 72, -53, -27, ISOFileInfo.FCI_EXT, -84, 105, -9, -4, -110, 33, ISO7816.INS_INCREASE, -99, -92, 10, 115, -2, ISOFileInfo.SECURITY_ATTR_EXP, 107, PassportService.SF_DG11, 7, -83, -52, -99, ISOFileInfo.PROP_INFO, 97, -122, 7, 82}, -3493099073704348026L).replaceAll("\\\\n", "\n");
}