package org.jmrtd.lds;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;
import net.p318sf.scuba.tlv.TLVUtil;
import net.p318sf.scuba.util.Hex;
import p005cn.jpush.android.JPushConstants;

public class DG12File extends DataGroup {
    public static final int CONTENT_SPECIFIC_CONSTRUCTED_TAG = 160;
    public static final int COUNT_TAG = 2;
    public static final int DATE_AND_TIME_OF_PERSONALIZATION = 24405;
    public static final int DATE_OF_ISSUE_TAG = 24358;
    public static final int ENDORSEMENTS_AND_OBSERVATIONS_TAG = 24347;
    public static final int IMAGE_OF_FRONT_TAG = 24349;
    public static final int IMAGE_OF_REAR_TAG = 24350;
    public static final int ISSUING_AUTHORITY_TAG = 24345;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int NAME_OF_OTHER_PERSON_TAG = 24346;
    public static final int PERSONALIZATION_SYSTEM_SERIAL_NUMBER_TAG = 24406;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
    private static final SimpleDateFormat SDTF = new SimpleDateFormat("yyyyMMddhhmmss");
    private static final int TAG_LIST_TAG = 92;
    public static final int TAX_OR_EXIT_REQUIREMENTS_TAG = 24348;
    private static final long serialVersionUID = -1979367459379125674L;
    private Date dateAndTimeOfPersonalization;
    private Date dateOfIssue;
    private String endorseMentsAndObservations;
    private byte[] imageOfFront;
    private byte[] imageOfRear;
    private String issuingAuthority;
    private List<String> namesOfOtherPersons;
    private String personalizationSystemSerialNumber;
    private List<Integer> tagPresenceList;
    private String taxOrExitRequirements;

    public DG12File(String str, Date date, List<String> list, String str2, String str3, byte[] bArr, byte[] bArr2, Date date2, String str4) {
        super(108);
        this.issuingAuthority = str;
        this.dateOfIssue = date;
        this.namesOfOtherPersons = list == null ? new ArrayList() : new ArrayList(list);
        this.endorseMentsAndObservations = this.endorseMentsAndObservations;
        this.taxOrExitRequirements = str3;
        this.imageOfFront = bArr;
        this.imageOfRear = bArr2;
        this.dateAndTimeOfPersonalization = date2;
        this.personalizationSystemSerialNumber = str4;
    }

    public DG12File(InputStream inputStream) throws IOException {
        super(108, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (tLVInputStream.readTag() != 92) {
            throw new IllegalArgumentException("Expected tag list in DG12");
        }
        int readLength = tLVInputStream.readLength();
        int i = 0;
        int i2 = readLength / 2;
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(tLVInputStream.readValue());
        ArrayList<Integer> arrayList = new ArrayList<>(i2 + 1);
        while (i < readLength) {
            int readTag = new TLVInputStream(byteArrayInputStream).readTag();
            i += TLVUtil.getTagLength(readTag);
            arrayList.add(Integer.valueOf(readTag));
        }
        for (Integer intValue : arrayList) {
            readField(intValue.intValue(), tLVInputStream);
        }
    }

    public List<Integer> getTagPresenceList() {
        if (this.tagPresenceList != null) {
            return this.tagPresenceList;
        }
        this.tagPresenceList = new ArrayList(10);
        if (this.issuingAuthority != null) {
            this.tagPresenceList.add(Integer.valueOf(ISSUING_AUTHORITY_TAG));
        }
        if (this.dateOfIssue != null) {
            this.tagPresenceList.add(Integer.valueOf(24358));
        }
        if (this.namesOfOtherPersons != null && this.namesOfOtherPersons.size() > 0) {
            this.tagPresenceList.add(Integer.valueOf(NAME_OF_OTHER_PERSON_TAG));
        }
        if (this.endorseMentsAndObservations != null) {
            this.tagPresenceList.add(Integer.valueOf(ENDORSEMENTS_AND_OBSERVATIONS_TAG));
        }
        if (this.taxOrExitRequirements != null) {
            this.tagPresenceList.add(Integer.valueOf(TAX_OR_EXIT_REQUIREMENTS_TAG));
        }
        if (this.imageOfFront != null) {
            this.tagPresenceList.add(Integer.valueOf(IMAGE_OF_FRONT_TAG));
        }
        if (this.imageOfRear != null) {
            this.tagPresenceList.add(Integer.valueOf(IMAGE_OF_REAR_TAG));
        }
        if (this.dateAndTimeOfPersonalization != null) {
            this.tagPresenceList.add(Integer.valueOf(DATE_AND_TIME_OF_PERSONALIZATION));
        }
        if (this.personalizationSystemSerialNumber != null) {
            this.tagPresenceList.add(Integer.valueOf(PERSONALIZATION_SYSTEM_SERIAL_NUMBER_TAG));
        }
        return this.tagPresenceList;
    }

    private void readField(int i, TLVInputStream tLVInputStream) throws IOException {
        int readTag = tLVInputStream.readTag();
        if (readTag == 160) {
            tLVInputStream.readLength();
            int readTag2 = tLVInputStream.readTag();
            if (readTag2 != 2) {
                throw new IllegalArgumentException("Expected " + Integer.toHexString(2) + ", found " + Integer.toHexString(readTag2));
            }
            int readLength = tLVInputStream.readLength();
            if (readLength != 1) {
                throw new IllegalArgumentException("Expected length 1 count length, found " + readLength);
            }
            byte[] readValue = tLVInputStream.readValue();
            if (readValue == null || readValue.length != 1) {
                throw new IllegalArgumentException("Number of content specific fields should be encoded in single byte, found " + Arrays.toString(readValue));
            }
            byte b = readValue[0] & 255;
            for (int i2 = 0; i2 < b; i2++) {
                int readTag3 = tLVInputStream.readTag();
                if (readTag3 != 24346) {
                    throw new IllegalArgumentException("Expected " + Integer.toHexString(NAME_OF_OTHER_PERSON_TAG) + ", found " + Integer.toHexString(readTag3));
                }
                tLVInputStream.readLength();
                parseNameOfOtherPerson(tLVInputStream.readValue());
            }
        } else if (readTag != i) {
            throw new IllegalArgumentException("Expected " + Integer.toHexString(i) + ", but found " + Integer.toHexString(readTag));
        } else {
            tLVInputStream.readLength();
            byte[] readValue2 = tLVInputStream.readValue();
            switch (readTag) {
                case ISSUING_AUTHORITY_TAG /*24345*/:
                    parseIssuingAuthority(readValue2);
                    return;
                case NAME_OF_OTHER_PERSON_TAG /*24346*/:
                    parseNameOfOtherPerson(readValue2);
                    return;
                case ENDORSEMENTS_AND_OBSERVATIONS_TAG /*24347*/:
                    parseEndorsementsAndObservations(readValue2);
                    return;
                case TAX_OR_EXIT_REQUIREMENTS_TAG /*24348*/:
                    parseTaxOrExitRequirements(readValue2);
                    return;
                case IMAGE_OF_FRONT_TAG /*24349*/:
                    parseImageOfFront(readValue2);
                    return;
                case IMAGE_OF_REAR_TAG /*24350*/:
                    parseImageOfRear(readValue2);
                    return;
                case 24358:
                    parseDateOfIssue(readValue2);
                    return;
                case DATE_AND_TIME_OF_PERSONALIZATION /*24405*/:
                    parseDateAndTimeOfPersonalization(readValue2);
                    return;
                case PERSONALIZATION_SYSTEM_SERIAL_NUMBER_TAG /*24406*/:
                    parsePersonalizationSystemSerialNumber(readValue2);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown field tag in DG12: " + Integer.toHexString(readTag));
            }
        }
    }

    private void parsePersonalizationSystemSerialNumber(byte[] bArr) {
        try {
            this.personalizationSystemSerialNumber = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.personalizationSystemSerialNumber = new String(bArr).trim();
        }
    }

    private void parseDateAndTimeOfPersonalization(byte[] bArr) {
        try {
            this.dateAndTimeOfPersonalization = SDTF.parse(Hex.bytesToHexString(bArr).trim());
        } catch (ParseException e) {
            throw new IllegalArgumentException(e.toString());
        }
    }

    private void parseImageOfFront(byte[] bArr) {
        this.imageOfFront = bArr;
    }

    private void parseImageOfRear(byte[] bArr) {
        this.imageOfRear = bArr;
    }

    private void parseTaxOrExitRequirements(byte[] bArr) {
        try {
            this.taxOrExitRequirements = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.taxOrExitRequirements = new String(bArr).trim();
        }
    }

    private void parseEndorsementsAndObservations(byte[] bArr) {
        try {
            this.endorseMentsAndObservations = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.endorseMentsAndObservations = new String(bArr).trim();
        }
    }

    private synchronized void parseNameOfOtherPerson(byte[] bArr) {
        if (this.namesOfOtherPersons == null) {
            this.namesOfOtherPersons = new ArrayList();
        }
        try {
            this.namesOfOtherPersons.add(new String(bArr, JPushConstants.ENCODING_UTF_8).trim());
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.namesOfOtherPersons.add(new String(bArr).trim());
        }
        return;
    }

    private void parseDateOfIssue(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("Wrong date format");
        }
        if (bArr.length == 8) {
            try {
                this.dateOfIssue = SDF.parse(new String(bArr, JPushConstants.ENCODING_UTF_8).trim());
                return;
            } catch (UnsupportedEncodingException e) {
                LOGGER.severe("Exception: " + e.getMessage());
            } catch (ParseException e2) {
                LOGGER.severe("Exception: " + e2.getMessage());
            }
        }
        LOGGER.warning("DG12 date of issue is not in expected ccyymmdd ASCII format");
        if (bArr.length == 4) {
            try {
                this.dateOfIssue = SDF.parse(Hex.bytesToHexString(bArr).trim());
                return;
            } catch (ParseException e3) {
                LOGGER.severe("Exception: " + e3.getMessage());
            }
        }
        throw new IllegalArgumentException("Wrong date format");
    }

    private void parseIssuingAuthority(byte[] bArr) {
        try {
            this.issuingAuthority = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.issuingAuthority = new String(bArr).trim();
        }
    }

    public String getIssuingAuthority() {
        return this.issuingAuthority;
    }

    public Date getDateOfIssue() {
        return this.dateOfIssue;
    }

    public List<String> getNamesOfOtherPersons() {
        return this.namesOfOtherPersons;
    }

    public String getEndorsementsAndObservations() {
        return this.endorseMentsAndObservations;
    }

    public String getTaxOrExitRequirements() {
        return this.taxOrExitRequirements;
    }

    public byte[] getImageOfFront() {
        return this.imageOfFront;
    }

    public byte[] getImageOfRear() {
        return this.imageOfRear;
    }

    public Date getDateAndTimeOfPersonalization() {
        return this.dateAndTimeOfPersonalization;
    }

    public String getPersonalizationSystemSerialNumber() {
        return this.personalizationSystemSerialNumber;
    }

    public int getTag() {
        return 108;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DG12File [");
        stringBuffer.append(this.issuingAuthority == null ? "" : this.issuingAuthority);
        stringBuffer.append(", ");
        stringBuffer.append(this.dateOfIssue == null ? "" : SDF.format(this.dateOfIssue));
        stringBuffer.append(", ");
        stringBuffer.append((this.namesOfOtherPersons == null || this.namesOfOtherPersons.size() == 0) ? "" : this.namesOfOtherPersons);
        stringBuffer.append(", ");
        stringBuffer.append(this.endorseMentsAndObservations == null ? "" : this.endorseMentsAndObservations);
        stringBuffer.append(", ");
        stringBuffer.append(this.taxOrExitRequirements == null ? "" : this.taxOrExitRequirements);
        stringBuffer.append(", ");
        stringBuffer.append(this.imageOfFront == null ? "" : "image (" + this.imageOfFront.length + ")");
        stringBuffer.append(", ");
        stringBuffer.append(this.imageOfRear == null ? "" : "image (" + this.imageOfRear.length + ")");
        stringBuffer.append(", ");
        stringBuffer.append(this.dateAndTimeOfPersonalization == null ? "" : SDF.format(this.dateAndTimeOfPersonalization));
        stringBuffer.append(", ");
        stringBuffer.append(this.personalizationSystemSerialNumber == null ? "" : this.personalizationSystemSerialNumber);
        stringBuffer.append("]");
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!obj.getClass().equals(getClass())) {
            return false;
        }
        return toString().equals(((DG12File) obj).toString());
    }

    public int hashCode() {
        return (toString().hashCode() * 13) + 112;
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(92);
        List<Integer> tagPresenceList2 = getTagPresenceList();
        DataOutputStream dataOutputStream = new DataOutputStream(tLVOutputStream);
        for (Integer intValue : tagPresenceList2) {
            dataOutputStream.writeShort(intValue.intValue());
        }
        dataOutputStream.flush();
        tLVOutputStream.writeValueEnd();
        for (Integer intValue2 : tagPresenceList2) {
            int intValue3 = intValue2.intValue();
            switch (intValue3) {
                case ISSUING_AUTHORITY_TAG /*24345*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.issuingAuthority.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case NAME_OF_OTHER_PERSON_TAG /*24346*/:
                    if (this.namesOfOtherPersons == null) {
                        this.namesOfOtherPersons = new ArrayList();
                    }
                    tLVOutputStream.writeTag(160);
                    tLVOutputStream.writeTag(2);
                    tLVOutputStream.write(this.namesOfOtherPersons.size());
                    tLVOutputStream.writeValueEnd();
                    for (String str : this.namesOfOtherPersons) {
                        tLVOutputStream.writeTag(NAME_OF_OTHER_PERSON_TAG);
                        tLVOutputStream.writeValue(str.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    }
                    tLVOutputStream.writeValueEnd();
                    break;
                case ENDORSEMENTS_AND_OBSERVATIONS_TAG /*24347*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.endorseMentsAndObservations.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case TAX_OR_EXIT_REQUIREMENTS_TAG /*24348*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.taxOrExitRequirements.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case IMAGE_OF_FRONT_TAG /*24349*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.imageOfFront);
                    break;
                case IMAGE_OF_REAR_TAG /*24350*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.imageOfRear);
                    break;
                case 24358:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(new String(SDF.format(this.dateOfIssue)).getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case DATE_AND_TIME_OF_PERSONALIZATION /*24405*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(Hex.hexStringToBytes(SDTF.format(this.dateAndTimeOfPersonalization)));
                    break;
                case PERSONALIZATION_SYSTEM_SERIAL_NUMBER_TAG /*24406*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.personalizationSystemSerialNumber.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                default:
                    throw new IllegalArgumentException("Unknown field tag in DG12: " + Integer.toHexString(intValue3));
            }
        }
    }
}
