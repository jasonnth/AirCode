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
import java.util.StringTokenizer;
import java.util.logging.Logger;
import net.p318sf.scuba.tlv.TLVInputStream;
import net.p318sf.scuba.tlv.TLVOutputStream;
import net.p318sf.scuba.tlv.TLVUtil;
import net.p318sf.scuba.util.Hex;
import p005cn.jpush.android.JPushConstants;

public class DG11File extends DataGroup {
    public static final int CONTENT_SPECIFIC_CONSTRUCTED_TAG = 160;
    public static final int COUNT_TAG = 2;
    public static final int CUSTODY_INFORMATION_TAG = 24344;
    public static final int FULL_DATE_OF_BIRTH_TAG = 24363;
    public static final int FULL_NAME_TAG = 24334;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    public static final int OTHER_NAME_TAG = 24335;
    public static final int OTHER_VALID_TD_NUMBERS_TAG = 24343;
    public static final int PERMANENT_ADDRESS_TAG = 24386;
    public static final int PERSONAL_NUMBER_TAG = 24336;
    public static final int PERSONAL_SUMMARY_TAG = 24341;
    public static final int PLACE_OF_BIRTH_TAG = 24337;
    public static final int PROFESSION_TAG = 24339;
    public static final int PROOF_OF_CITIZENSHIP_TAG = 24342;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyyMMdd");
    public static final int TAG_LIST_TAG = 92;
    public static final int TELEPHONE_TAG = 24338;
    public static final int TITLE_TAG = 24340;
    private static final long serialVersionUID = 8566312538928662937L;
    private String custodyInformation;
    private Date fullDateOfBirth;
    private String nameOfHolder;
    private List<String> otherNames;
    private List<String> otherValidTDNumbers;
    private List<String> permanentAddress;
    private String personalNumber;
    private String personalSummary;
    private List<String> placeOfBirth;
    private String profession;
    private byte[] proofOfCitizenship;
    private List<Integer> tagPresenceList;
    private String telephone;
    private String title;

    public DG11File(String str, List<String> list, String str2, Date date, List<String> list2, List<String> list3, String str3, String str4, String str5, String str6, byte[] bArr, List<String> list4, String str7) {
        super(107);
        this.nameOfHolder = str;
        this.otherNames = list == null ? new ArrayList() : new ArrayList(list);
        this.personalNumber = str2;
        this.fullDateOfBirth = date;
        this.placeOfBirth = list2 == null ? new ArrayList() : new ArrayList(list2);
        this.permanentAddress = list3;
        this.telephone = str3;
        this.profession = str4;
        this.title = str5;
        this.personalSummary = str6;
        this.proofOfCitizenship = bArr;
        this.otherValidTDNumbers = list4 == null ? new ArrayList() : new ArrayList(list4);
        this.custodyInformation = str7;
    }

    public DG11File(InputStream inputStream) throws IOException {
        super(107, inputStream);
    }

    /* access modifiers changed from: protected */
    public void readContent(InputStream inputStream) throws IOException {
        TLVInputStream tLVInputStream = inputStream instanceof TLVInputStream ? (TLVInputStream) inputStream : new TLVInputStream(inputStream);
        if (tLVInputStream.readTag() != 92) {
            throw new IllegalArgumentException("Expected tag list in DG11");
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
                if (readTag3 != 24335) {
                    throw new IllegalArgumentException("Expected " + Integer.toHexString(OTHER_NAME_TAG) + ", found " + Integer.toHexString(readTag3));
                }
                tLVInputStream.readLength();
                parseOtherName(tLVInputStream.readValue());
            }
        } else if (readTag != i) {
            throw new IllegalArgumentException("Expected " + Integer.toHexString(i) + ", but found " + Integer.toHexString(readTag));
        } else {
            tLVInputStream.readLength();
            byte[] readValue2 = tLVInputStream.readValue();
            switch (readTag) {
                case FULL_NAME_TAG /*24334*/:
                    parseNameOfHolder(readValue2);
                    return;
                case OTHER_NAME_TAG /*24335*/:
                    parseOtherName(readValue2);
                    return;
                case PERSONAL_NUMBER_TAG /*24336*/:
                    parsePersonalNumber(readValue2);
                    return;
                case PLACE_OF_BIRTH_TAG /*24337*/:
                    parsePlaceOfBirth(readValue2);
                    return;
                case TELEPHONE_TAG /*24338*/:
                    parseTelephone(readValue2);
                    return;
                case PROFESSION_TAG /*24339*/:
                    parseProfession(readValue2);
                    return;
                case TITLE_TAG /*24340*/:
                    parseTitle(readValue2);
                    return;
                case PERSONAL_SUMMARY_TAG /*24341*/:
                    parsePersonalSummary(readValue2);
                    return;
                case PROOF_OF_CITIZENSHIP_TAG /*24342*/:
                    parseProofOfCitizenShip(readValue2);
                    return;
                case OTHER_VALID_TD_NUMBERS_TAG /*24343*/:
                    parseOtherValidTDNumbers(readValue2);
                    return;
                case CUSTODY_INFORMATION_TAG /*24344*/:
                    parseCustodyInformation(readValue2);
                    return;
                case 24363:
                    parseFullDateOfBirth(readValue2);
                    return;
                case 24386:
                    parsePermanentAddress(readValue2);
                    return;
                default:
                    throw new IllegalArgumentException("Unknown field tag in DG11: " + Integer.toHexString(readTag));
            }
        }
    }

    private void parseCustodyInformation(byte[] bArr) {
        try {
            this.custodyInformation = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.custodyInformation = new String(bArr).trim();
        }
    }

    private void parseOtherValidTDNumbers(byte[] bArr) {
        String str;
        String trim = new String(bArr).trim();
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = trim;
        }
        this.otherValidTDNumbers = new ArrayList();
        StringTokenizer stringTokenizer = new StringTokenizer(str, "<");
        while (stringTokenizer.hasMoreTokens()) {
            this.otherValidTDNumbers.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parseProofOfCitizenShip(byte[] bArr) {
        this.proofOfCitizenship = bArr;
    }

    private void parsePersonalSummary(byte[] bArr) {
        try {
            this.personalSummary = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.personalSummary = new String(bArr).trim();
        }
    }

    private void parseTitle(byte[] bArr) {
        try {
            this.title = new String(bArr, JPushConstants.ENCODING_UTF_8).trim();
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.title = new String(bArr).trim();
        }
    }

    private void parseProfession(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        this.profession = str.trim();
    }

    private void parseTelephone(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        this.telephone = str.replace("<", " ").trim();
    }

    private void parsePermanentAddress(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "<");
        this.permanentAddress = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            this.permanentAddress.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parsePlaceOfBirth(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, "<");
        this.placeOfBirth = new ArrayList();
        while (stringTokenizer.hasMoreTokens()) {
            this.placeOfBirth.add(stringTokenizer.nextToken().trim());
        }
    }

    private void parseFullDateOfBirth(byte[] bArr) {
        String str;
        try {
            if (bArr.length == 4) {
                str = Hex.bytesToHexString(bArr);
            } else {
                String str2 = new String(bArr);
                try {
                    str = new String(bArr, JPushConstants.ENCODING_UTF_8);
                } catch (UnsupportedEncodingException e) {
                    LOGGER.severe("Exception: " + e.getMessage());
                    str = str2;
                }
            }
            this.fullDateOfBirth = SDF.parse(str);
        } catch (ParseException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    private synchronized void parseOtherName(byte[] bArr) {
        if (this.otherNames == null) {
            this.otherNames = new ArrayList();
        }
        try {
            this.otherNames.add(new String(bArr, JPushConstants.ENCODING_UTF_8).trim());
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            this.otherNames.add(new String(bArr).trim());
        }
        return;
    }

    private void parsePersonalNumber(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        this.personalNumber = str.trim();
    }

    private void parseNameOfHolder(byte[] bArr) {
        String str;
        String str2 = new String(bArr);
        try {
            str = new String(bArr, JPushConstants.ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            str = str2;
        }
        this.nameOfHolder = str.trim();
    }

    public int getTag() {
        return 107;
    }

    public List<Integer> getTagPresenceList() {
        if (this.tagPresenceList != null) {
            return this.tagPresenceList;
        }
        this.tagPresenceList = new ArrayList(12);
        if (this.nameOfHolder != null) {
            this.tagPresenceList.add(Integer.valueOf(FULL_NAME_TAG));
        }
        if (this.otherNames != null && this.otherNames.size() > 0) {
            this.tagPresenceList.add(Integer.valueOf(OTHER_NAME_TAG));
        }
        if (this.personalNumber != null) {
            this.tagPresenceList.add(Integer.valueOf(PERSONAL_NUMBER_TAG));
        }
        if (this.fullDateOfBirth != null) {
            this.tagPresenceList.add(Integer.valueOf(24363));
        }
        if (this.placeOfBirth != null && this.placeOfBirth.size() > 0) {
            this.tagPresenceList.add(Integer.valueOf(PLACE_OF_BIRTH_TAG));
        }
        if (this.permanentAddress != null && this.permanentAddress.size() > 0) {
            this.tagPresenceList.add(Integer.valueOf(24386));
        }
        if (this.telephone != null) {
            this.tagPresenceList.add(Integer.valueOf(TELEPHONE_TAG));
        }
        if (this.profession != null) {
            this.tagPresenceList.add(Integer.valueOf(PROFESSION_TAG));
        }
        if (this.title != null) {
            this.tagPresenceList.add(Integer.valueOf(TITLE_TAG));
        }
        if (this.personalSummary != null) {
            this.tagPresenceList.add(Integer.valueOf(PERSONAL_SUMMARY_TAG));
        }
        if (this.proofOfCitizenship != null) {
            this.tagPresenceList.add(Integer.valueOf(PROOF_OF_CITIZENSHIP_TAG));
        }
        if (this.otherValidTDNumbers != null && this.otherValidTDNumbers.size() > 0) {
            this.tagPresenceList.add(Integer.valueOf(OTHER_VALID_TD_NUMBERS_TAG));
        }
        if (this.custodyInformation != null) {
            this.tagPresenceList.add(Integer.valueOf(CUSTODY_INFORMATION_TAG));
        }
        return this.tagPresenceList;
    }

    public String getNameOfHolder() {
        return this.nameOfHolder;
    }

    public List<String> getOtherNames() {
        return this.otherNames == null ? new ArrayList() : new ArrayList(this.otherNames);
    }

    public String getPersonalNumber() {
        return this.personalNumber;
    }

    public Date getFullDateOfBirth() {
        return this.fullDateOfBirth;
    }

    public List<String> getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public List<String> getPermanentAddress() {
        return this.permanentAddress;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public String getProfession() {
        return this.profession;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPersonalSummary() {
        return this.personalSummary;
    }

    public byte[] getProofOfCitizenship() {
        return this.proofOfCitizenship;
    }

    public List<String> getOtherValidTDNumbers() {
        return this.otherValidTDNumbers;
    }

    public String getCustodyInformation() {
        return this.custodyInformation;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("DG11File [");
        stringBuffer.append(this.nameOfHolder == null ? "" : this.nameOfHolder);
        stringBuffer.append(", ");
        stringBuffer.append((this.otherNames == null || this.otherNames.size() == 0) ? "[]" : this.otherNames);
        stringBuffer.append(", ");
        stringBuffer.append(this.personalNumber == null ? "" : this.personalNumber);
        stringBuffer.append(", ");
        stringBuffer.append(this.fullDateOfBirth == null ? "" : SDF.format(this.fullDateOfBirth));
        stringBuffer.append(", ");
        stringBuffer.append((this.placeOfBirth == null || this.placeOfBirth.size() == 0) ? "[]" : this.placeOfBirth.toString());
        stringBuffer.append(", ");
        stringBuffer.append((this.permanentAddress == null || this.permanentAddress.size() == 0) ? "[]" : this.permanentAddress.toString());
        stringBuffer.append(", ");
        stringBuffer.append(this.telephone == null ? "" : this.telephone);
        stringBuffer.append(", ");
        stringBuffer.append(this.profession == null ? "" : this.profession);
        stringBuffer.append(", ");
        stringBuffer.append(this.title == null ? "" : this.title);
        stringBuffer.append(", ");
        stringBuffer.append(this.personalSummary == null ? "" : this.personalSummary);
        stringBuffer.append(", ");
        stringBuffer.append(this.proofOfCitizenship == null ? "" : "image (" + this.proofOfCitizenship.length + ")");
        stringBuffer.append(", ");
        stringBuffer.append((this.otherValidTDNumbers == null || this.otherValidTDNumbers.size() == 0) ? "[]" : this.otherValidTDNumbers.toString());
        stringBuffer.append(", ");
        stringBuffer.append(this.custodyInformation == null ? "" : this.custodyInformation);
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
        return toString().equals(((DG11File) obj).toString());
    }

    public int hashCode() {
        return (toString().hashCode() * 13) + 111;
    }

    /* access modifiers changed from: protected */
    public void writeContent(OutputStream outputStream) throws IOException {
        TLVOutputStream tLVOutputStream = outputStream instanceof TLVOutputStream ? (TLVOutputStream) outputStream : new TLVOutputStream(outputStream);
        tLVOutputStream.writeTag(92);
        DataOutputStream dataOutputStream = new DataOutputStream(tLVOutputStream);
        List<Integer> tagPresenceList2 = getTagPresenceList();
        for (Integer intValue : tagPresenceList2) {
            dataOutputStream.writeShort(intValue.intValue());
        }
        dataOutputStream.flush();
        tLVOutputStream.writeValueEnd();
        for (Integer intValue2 : tagPresenceList2) {
            int intValue3 = intValue2.intValue();
            switch (intValue3) {
                case FULL_NAME_TAG /*24334*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.nameOfHolder.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case OTHER_NAME_TAG /*24335*/:
                    if (this.otherNames == null) {
                        this.otherNames = new ArrayList();
                    }
                    tLVOutputStream.writeTag(160);
                    tLVOutputStream.writeTag(2);
                    tLVOutputStream.write(this.otherNames.size());
                    tLVOutputStream.writeValueEnd();
                    for (String str : this.otherNames) {
                        tLVOutputStream.writeTag(OTHER_NAME_TAG);
                        tLVOutputStream.writeValue(str.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    }
                    tLVOutputStream.writeValueEnd();
                    break;
                case PERSONAL_NUMBER_TAG /*24336*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.personalNumber.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case PLACE_OF_BIRTH_TAG /*24337*/:
                    tLVOutputStream.writeTag(intValue3);
                    boolean z = true;
                    for (String str2 : this.placeOfBirth) {
                        if (str2 != null) {
                            if (z) {
                                z = false;
                            } else {
                                tLVOutputStream.write(60);
                            }
                            tLVOutputStream.write(str2.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                        }
                        z = z;
                    }
                    tLVOutputStream.writeValueEnd();
                    break;
                case TELEPHONE_TAG /*24338*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.telephone.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case PROFESSION_TAG /*24339*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.profession.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case TITLE_TAG /*24340*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.title.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case PERSONAL_SUMMARY_TAG /*24341*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.personalSummary.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case PROOF_OF_CITIZENSHIP_TAG /*24342*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.proofOfCitizenship);
                    break;
                case OTHER_VALID_TD_NUMBERS_TAG /*24343*/:
                    tLVOutputStream.writeTag(intValue3);
                    boolean z2 = true;
                    for (String str3 : this.otherValidTDNumbers) {
                        if (str3 != null) {
                            if (z2) {
                                z2 = false;
                            } else {
                                tLVOutputStream.write(60);
                            }
                            tLVOutputStream.write(str3.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                        }
                        z2 = z2;
                    }
                    tLVOutputStream.writeValueEnd();
                    break;
                case CUSTODY_INFORMATION_TAG /*24344*/:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(this.custodyInformation.trim().replace(' ', '<').getBytes(JPushConstants.ENCODING_UTF_8));
                    break;
                case 24363:
                    tLVOutputStream.writeTag(intValue3);
                    tLVOutputStream.writeValue(Hex.hexStringToBytes(SDF.format(this.fullDateOfBirth)));
                    break;
                case 24386:
                    tLVOutputStream.writeTag(intValue3);
                    boolean z3 = true;
                    for (String str4 : this.permanentAddress) {
                        if (str4 != null) {
                            if (z3) {
                                z3 = false;
                            } else {
                                tLVOutputStream.write(60);
                            }
                            tLVOutputStream.write(str4.trim().getBytes(JPushConstants.ENCODING_UTF_8));
                        }
                        z3 = z3;
                    }
                    tLVOutputStream.writeValueEnd();
                    break;
                default:
                    throw new IllegalStateException("Unknown tag in DG11: " + Integer.toHexString(intValue3));
            }
        }
    }
}
