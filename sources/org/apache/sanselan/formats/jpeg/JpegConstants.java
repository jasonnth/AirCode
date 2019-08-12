package org.apache.sanselan.formats.jpeg;

import net.p318sf.scuba.smartcards.ISO7816;
import net.p318sf.scuba.smartcards.ISOFileInfo;
import org.jmrtd.lds.CVCAFile;

public interface JpegConstants {
    public static final byte[] CONST_8BIM = {56, CVCAFile.CAR_TAG, 73, 77};
    public static final byte[] EOI = {-1, -39};
    public static final byte[] EXIF_IDENTIFIER_CODE = {69, 120, 105, 102};
    public static final byte[] JFIF0_SIGNATURE = {74, 70, 73, 70, 0};
    public static final byte[] JFIF0_SIGNATURE_ALTERNATIVE = {74, 70, 73, 70, ISO7816.INS_VERIFY};
    public static final int[] MARKERS = {65498, 224, 65504, 65505, 65506, 65517, 65518, 65519, 65504, 65472, 65473, 65474, 65475, 65476, 65477, 65478, 65479, 65480, 65481, 65482, 65483, 65484, 65485, 65486, 65487};
    public static final byte[] PHOTOSHOP_IDENTIFICATION_STRING = {80, 104, ISOFileInfo.FCI_BYTE, 116, ISOFileInfo.FCI_BYTE, 115, 104, ISOFileInfo.FCI_BYTE, ISO7816.INS_MANAGE_CHANNEL, ISO7816.INS_VERIFY, 51, 46, ISO7816.INS_DECREASE, 0};
    public static final byte[] SOI = {-1, ISO7816.INS_LOAD_KEY_FILE};
    public static final byte[] XMP_IDENTIFIER = {104, 116, 116, ISO7816.INS_MANAGE_CHANNEL, 58, 47, 47, 110, 115, 46, 97, ISOFileInfo.FMD_BYTE, ISOFileInfo.FCI_BYTE, ISOFileInfo.FCP_BYTE, 101, 46, 99, ISOFileInfo.FCI_BYTE, 109, 47, 120, 97, ISO7816.INS_MANAGE_CHANNEL, 47, 49, 46, ISO7816.INS_DECREASE, 47, 0};
    public static final byte[] icc_profile_label = {73, 67, 67, 95, 80, 82, 79, 70, 73, 76, 69, 0};
}
