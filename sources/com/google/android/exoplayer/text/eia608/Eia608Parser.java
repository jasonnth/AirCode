package com.google.android.exoplayer.text.eia608;

import com.airbnb.android.cityregistration.controller.CityRegistrationController;
import com.airbnb.android.core.enums.HelpCenterTopic;
import com.airbnb.android.identity.AccountVerificationOfflineIdController;
import com.airbnb.android.managelisting.settings.DatesModalActivity;
import com.facebook.imageutils.JfifUtil;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.ArrayList;
import net.p318sf.scuba.smartcards.ISO7816;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.asn1.eac.EACTags;
import org.spongycastle.crypto.tls.CipherSuite;
import org.spongycastle.math.Primes;

public final class Eia608Parser {
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, JfifUtil.MARKER_APP1, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, LDSFile.EF_DG2_TAG, LDSFile.EF_DG4_TAG, LDSFile.EF_SOD_TAG, EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY, EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY, EACTags.SECURITY_SUPPORT_TEMPLATE, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, CipherSuite.TLS_PSK_WITH_NULL_SHA256, CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 8482, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384, 9834, 224, 32, 232, 226, HelpCenterTopic.HOST_PAYOUT, 238, 244, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 201, Primes.SMALL_FACTOR_LIMIT, JfifUtil.MARKER_SOS, 220, 252, 8216, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, 42, 39, 8212, CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 8480, 8226, 8220, 8221, 192, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA256, 199, 200, CityRegistrationController.RC_CHOOSE_PHOTO, 203, 235, 206, 207, 239, 212, JfifUtil.MARKER_EOI, 249, 219, CipherSuite.TLS_DHE_PSK_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_128_CBC_SHA256};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA256, 227, 205, 204, 236, 210, 242, 213, 245, EACTags.SECURITY_ENVIRONMENT_TEMPLATE, 125, 92, 94, 95, EACTags.DYNAMIC_AUTHENTIFICATION_TEMPLATE, EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA256, 228, 214, 246, DatesModalActivity.RESULT_CODE, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, CipherSuite.TLS_DH_DSS_WITH_AES_128_GCM_SHA256, 9474, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 229, JfifUtil.MARKER_SOI, 248, 9484, 9488, 9492, 9496};
    private final ArrayList<ClosedCaption> captions = new ArrayList<>();
    private final ParsableBitArray seiBuffer = new ParsableBitArray();
    private final StringBuilder stringBuilder = new StringBuilder();

    Eia608Parser() {
    }

    /* access modifiers changed from: 0000 */
    public boolean canParse(String mimeType) {
        return mimeType.equals("application/eia-608");
    }

    /* access modifiers changed from: 0000 */
    public ClosedCaptionList parse(SampleHolder sampleHolder) {
        if (sampleHolder.size < 10) {
            return null;
        }
        this.captions.clear();
        this.stringBuilder.setLength(0);
        this.seiBuffer.reset(sampleHolder.data.array());
        this.seiBuffer.skipBits(67);
        int ccCount = this.seiBuffer.readBits(5);
        this.seiBuffer.skipBits(8);
        for (int i = 0; i < ccCount; i++) {
            this.seiBuffer.skipBits(5);
            if (!this.seiBuffer.readBit()) {
                this.seiBuffer.skipBits(18);
            } else if (this.seiBuffer.readBits(2) != 0) {
                this.seiBuffer.skipBits(16);
            } else {
                this.seiBuffer.skipBits(1);
                byte ccData1 = (byte) this.seiBuffer.readBits(7);
                this.seiBuffer.skipBits(1);
                byte ccData2 = (byte) this.seiBuffer.readBits(7);
                if (ccData1 != 0 || ccData2 != 0) {
                    if ((ccData1 == 17 || ccData1 == 25) && (ccData2 & ISO7816.INS_MANAGE_CHANNEL) == 48) {
                        this.stringBuilder.append(getSpecialChar(ccData2));
                    } else if ((ccData1 == 18 || ccData1 == 26) && (ccData2 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedEsFrChar(ccData2));
                    } else if ((ccData1 == 19 || ccData1 == 27) && (ccData2 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedPtDeChar(ccData2));
                    } else if (ccData1 < 32) {
                        addCtrl(ccData1, ccData2);
                    } else {
                        this.stringBuilder.append(getChar(ccData1));
                        if (ccData2 >= 32) {
                            this.stringBuilder.append(getChar(ccData2));
                        }
                    }
                }
            }
        }
        addBufferedText();
        if (this.captions.isEmpty()) {
            return null;
        }
        ClosedCaption[] captionArray = new ClosedCaption[this.captions.size()];
        this.captions.toArray(captionArray);
        return new ClosedCaptionList(sampleHolder.timeUs, sampleHolder.isDecodeOnly(), captionArray);
    }

    private static char getChar(byte ccData) {
        return (char) BASIC_CHARACTER_SET[(ccData & AccountVerificationOfflineIdController.PERMISSION_REQUEST_CODE_NETVERIFY) + ISO7816.INS_CREATE_FILE];
    }

    private static char getSpecialChar(byte ccData) {
        return (char) SPECIAL_CHARACTER_SET[ccData & 15];
    }

    private static char getExtendedEsFrChar(byte ccData) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[ccData & 31];
    }

    private static char getExtendedPtDeChar(byte ccData) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[ccData & 31];
    }

    private void addBufferedText() {
        if (this.stringBuilder.length() > 0) {
            this.captions.add(new ClosedCaptionText(this.stringBuilder.toString()));
            this.stringBuilder.setLength(0);
        }
    }

    private void addCtrl(byte ccData1, byte ccData2) {
        addBufferedText();
        this.captions.add(new ClosedCaptionCtrl(ccData1, ccData2));
    }

    private void backspace() {
        addCtrl(20, 33);
    }

    public static boolean isSeiMessageEia608(int payloadType, int payloadLength, ParsableByteArray payload) {
        if (payloadType != 4 || payloadLength < 8) {
            return false;
        }
        int startPosition = payload.getPosition();
        int countryCode = payload.readUnsignedByte();
        int providerCode = payload.readUnsignedShort();
        int userIdentifier = payload.readInt();
        int userDataTypeCode = payload.readUnsignedByte();
        payload.setPosition(startPosition);
        if (countryCode == 181 && providerCode == 49 && userIdentifier == 1195456820 && userDataTypeCode == 3) {
            return true;
        }
        return false;
    }
}
