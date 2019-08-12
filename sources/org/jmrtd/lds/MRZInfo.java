package org.jmrtd.lds;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.logging.Logger;
import net.p318sf.scuba.data.Gender;
import net.p318sf.scuba.smartcards.ISO7816;
import org.spongycastle.asn1.eac.EACTags;
import p005cn.jpush.android.JPushConstants;

public class MRZInfo extends AbstractLDSInfo {
    public static final int DOC_TYPE_ID1 = 1;
    public static final int DOC_TYPE_ID2 = 2;
    public static final int DOC_TYPE_ID3 = 3;
    public static final int DOC_TYPE_UNSPECIFIED = 0;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    private static final String MRZ_CHARS = "<0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final long serialVersionUID = 7054965914471297804L;
    private char compositeCheckDigit;
    private String dateOfBirth;
    private char dateOfBirthCheckDigit;
    private String dateOfExpiry;
    private char dateOfExpiryCheckDigit;
    private String documentCode;
    private String documentNumber;
    private char documentNumberCheckDigit;
    private int documentType;
    private Gender gender;
    private String issuingState;
    private String mrzLine1;
    private String mrzLine2;
    private String mrzLine3;
    private String nationality;
    private String optionalData1;
    private String optionalData2;
    private String primaryIdentifier;
    private String secondaryIdentifier;

    public /* bridge */ /* synthetic */ byte[] getEncoded() {
        return super.getEncoded();
    }

    public MRZInfo(String str, String str2, String str3, String str4, String str5, String str6, String str7, Gender gender2, String str8, String str9) {
        if (str == null || str.length() < 1 || str.length() > 2 || (!str.startsWith("P") && !str.startsWith("V"))) {
            throw new IllegalArgumentException("Wrong document code: " + str);
        }
        this.documentType = getDocumentTypeFromDocumentCode(str);
        this.documentCode = trimFillerChars(str);
        this.issuingState = str2;
        this.primaryIdentifier = str3;
        this.secondaryIdentifier = str4;
        this.documentNumber = trimFillerChars(str5);
        this.nationality = str6;
        this.dateOfBirth = str7;
        this.gender = gender2;
        this.dateOfExpiry = str8;
        if (str9 == null || equalsModuloFillerChars(str9, "")) {
            this.optionalData1 = "";
        } else if (str9.length() == 15) {
            this.optionalData1 = str9;
        } else if (str9.length() <= 14) {
            this.optionalData1 = mrzFormat(str9, 14) + checkDigit(str9, true);
        } else {
            throw new IllegalArgumentException("Wrong personal number: " + str9);
        }
        checkDigit();
    }

    public MRZInfo(String str, String str2, String str3, String str4, String str5, Gender gender2, String str6, String str7, String str8, String str9, String str10) {
        if (str == null || str.length() < 1 || str.length() > 2 || (!str.startsWith("C") && !str.startsWith("I") && !str.startsWith("A"))) {
            throw new IllegalArgumentException("Wrong document code: " + str);
        }
        this.documentType = getDocumentTypeFromDocumentCode(str);
        this.documentCode = trimFillerChars(str);
        this.issuingState = str2;
        this.primaryIdentifier = str9;
        this.secondaryIdentifier = str10;
        this.documentNumber = trimFillerChars(str3);
        this.nationality = str7;
        this.dateOfBirth = str5;
        this.gender = gender2;
        this.dateOfExpiry = str6;
        if (str4 == null || str4.length() > 15) {
            throw new IllegalArgumentException("Wrong optional data 1: " + (str4 == null ? "null" : "\"" + str4 + "\""));
        }
        this.optionalData1 = str4;
        this.optionalData2 = str8;
        checkDigit();
    }

    public MRZInfo(InputStream inputStream, int i) {
        try {
            readObject(inputStream, i);
        } catch (IOException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public MRZInfo(String str) {
        if (str == null) {
            throw new IllegalArgumentException("Null string");
        }
        String replace = str.trim().replace("\n", "");
        try {
            readObject(new ByteArrayInputStream(replace.getBytes(JPushConstants.ENCODING_UTF_8)), replace.length());
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException(e.getMessage());
        } catch (IOException e2) {
            LOGGER.severe("Exception: " + e2.getMessage());
            throw new IllegalArgumentException(e2.getMessage());
        }
    }

    private void readObject(InputStream inputStream, int i) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(inputStream);
        inputStream.mark(i);
        InputStreamReader inputStreamReader = new InputStreamReader(new BufferedInputStream(inputStream));
        char[] cArr = new char[i];
        inputStreamReader.read(cArr, 0, i);
        String str = new String(cArr);
        inputStream.reset();
        this.documentCode = readStringWithFillers(dataInputStream, 2);
        this.documentType = getDocumentTypeFromDocumentCode(this.documentCode);
        switch (i) {
            case 88:
                this.documentType = 3;
                break;
            case 90:
                this.documentType = 1;
                break;
            default:
                this.documentType = getDocumentTypeFromDocumentCode(this.documentCode);
                break;
        }
        if (this.documentType == 1) {
            this.issuingState = readCountry(dataInputStream);
            this.documentNumber = readString(dataInputStream, 9);
            this.documentNumberCheckDigit = (char) dataInputStream.readUnsignedByte();
            this.optionalData1 = readStringWithFillers(dataInputStream, 15);
            if (this.documentNumberCheckDigit == '<') {
                this.documentNumber += this.optionalData1.substring(0, this.optionalData1.length() - 1);
                this.documentNumberCheckDigit = this.optionalData1.charAt(this.optionalData1.length() - 1);
                this.optionalData1 = null;
            }
            this.documentNumber = trimFillerChars(this.documentNumber);
            this.dateOfBirth = readDateOfBirth(dataInputStream);
            this.dateOfBirthCheckDigit = (char) dataInputStream.readUnsignedByte();
            this.gender = readGender(dataInputStream);
            this.dateOfExpiry = readDateOfExpiry(dataInputStream);
            this.dateOfExpiryCheckDigit = (char) dataInputStream.readUnsignedByte();
            this.nationality = readCountry(dataInputStream);
            this.optionalData2 = readString(dataInputStream, 11);
            this.compositeCheckDigit = (char) dataInputStream.readUnsignedByte();
            readNameIdentifiers(readString(dataInputStream, 30));
            return;
        }
        this.mrzLine1 = str.substring(0, i / 2);
        this.mrzLine2 = str.substring(i / 2);
        this.mrzLine3 = "";
        this.issuingState = readCountry(dataInputStream);
        readNameIdentifiers(readString(dataInputStream, 39));
        this.documentNumber = trimFillerChars(readString(dataInputStream, 9));
        this.documentNumberCheckDigit = (char) dataInputStream.readUnsignedByte();
        this.nationality = readCountry(dataInputStream);
        this.dateOfBirth = readDateOfBirth(dataInputStream);
        this.dateOfBirthCheckDigit = (char) dataInputStream.readUnsignedByte();
        this.gender = readGender(dataInputStream);
        this.dateOfExpiry = readDateOfExpiry(dataInputStream);
        this.dateOfExpiryCheckDigit = (char) dataInputStream.readUnsignedByte();
        char readUnsignedByte = (char) dataInputStream.readUnsignedByte();
        this.optionalData1 = mrzFormat(readStringWithFillers(dataInputStream, 14), 14) + readUnsignedByte;
        this.compositeCheckDigit = (char) dataInputStream.readUnsignedByte();
    }

    public void writeObject(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        writeDocumentType(dataOutputStream);
        if (this.documentType == 1) {
            writeIssuingState(dataOutputStream);
            if (this.documentNumber.length() <= 9 || !equalsModuloFillerChars(this.optionalData1, "")) {
                writeString(this.documentNumber, dataOutputStream, 9);
                dataOutputStream.write(this.documentNumberCheckDigit);
                writeString(this.optionalData1, dataOutputStream, 15);
            } else {
                writeString(this.documentNumber.substring(0, 9), dataOutputStream, 9);
                dataOutputStream.write(60);
                writeString(this.documentNumber.substring(9, this.documentNumber.length()) + this.documentNumberCheckDigit + "<", dataOutputStream, 15);
            }
            writeDateOfBirth(dataOutputStream);
            dataOutputStream.write(this.dateOfBirthCheckDigit);
            writeGender(dataOutputStream);
            writeDateOfExpiry(dataOutputStream);
            dataOutputStream.write(this.dateOfExpiryCheckDigit);
            writeNationality(dataOutputStream);
            writeString(this.optionalData2, dataOutputStream, 11);
            dataOutputStream.write(this.compositeCheckDigit);
            writeName(dataOutputStream, 30);
            return;
        }
        writeIssuingState(dataOutputStream);
        writeName(dataOutputStream, 39);
        writeString(this.documentNumber, dataOutputStream, 9);
        dataOutputStream.write(this.documentNumberCheckDigit);
        writeNationality(dataOutputStream);
        writeDateOfBirth(dataOutputStream);
        dataOutputStream.write(this.dateOfBirthCheckDigit);
        writeGender(dataOutputStream);
        writeDateOfExpiry(dataOutputStream);
        dataOutputStream.write(this.dateOfExpiryCheckDigit);
        writeString(this.optionalData1, dataOutputStream, 15);
        dataOutputStream.write(this.compositeCheckDigit);
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
        checkDigit();
    }

    public String getDateOfExpiry() {
        return this.dateOfExpiry;
    }

    public void setDateOfExpiry(String str) {
        this.dateOfExpiry = str;
        checkDigit();
    }

    public String getDocumentNumber() {
        return this.documentNumber;
    }

    public void setDocumentNumber(String str) {
        this.documentNumber = str.trim();
        checkDigit();
    }

    public int getDocumentType() {
        return this.documentType;
    }

    public String getDocumentCode() {
        return this.documentCode;
    }

    public void setDocumentCode(String str) {
        this.documentCode = str;
        this.documentType = getDocumentTypeFromDocumentCode(str);
        if (this.documentType == 1 && this.optionalData2 == null) {
            this.optionalData2 = "";
        }
    }

    public String getIssuingState() {
        return this.issuingState;
    }

    public void setIssuingState(String str) {
        this.issuingState = str;
        checkDigit();
    }

    public String getPrimaryIdentifier() {
        return this.primaryIdentifier;
    }

    public void setPrimaryIdentifier(String str) {
        this.primaryIdentifier = str.trim();
        checkDigit();
    }

    public String getSecondaryIdentifier() {
        return this.secondaryIdentifier;
    }

    public String[] getSecondaryIdentifierComponents() {
        return this.secondaryIdentifier.split(" |<");
    }

    public void setSecondaryIdentifierComponents(String[] strArr) {
        if (strArr == null) {
            this.secondaryIdentifier = null;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < strArr.length; i++) {
                stringBuffer.append(strArr[i]);
                if (i < strArr.length - 1) {
                    stringBuffer.append('<');
                }
            }
        }
        checkDigit();
    }

    public void setSecondaryIdentifiers(String str) {
        readSecondaryIdentifiers(str.trim());
        checkDigit();
    }

    public String getNationality() {
        return this.nationality;
    }

    public void setNationality(String str) {
        this.nationality = str;
        checkDigit();
    }

    public String getPersonalNumber() {
        if (this.optionalData1.length() > 14) {
            return trimFillerChars(this.optionalData1.substring(0, 14));
        }
        return trimFillerChars(this.optionalData1);
    }

    public void setPersonalNumber(String str) {
        if (str == null || str.length() > 14) {
            throw new IllegalArgumentException("Wrong personal number");
        }
        this.optionalData1 = mrzFormat(str, 14) + checkDigit(str, true);
    }

    public String getOptionalData1() {
        return this.optionalData1;
    }

    public String getOptionalData2() {
        return this.optionalData2;
    }

    public void setOptionalData2(String str) {
        this.optionalData2 = trimFillerChars(str);
        checkDigit();
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender2) {
        this.gender = gender2;
        checkDigit();
    }

    public String toString() {
        try {
            String str = new String(getEncoded(), JPushConstants.ENCODING_UTF_8);
            switch (str.length()) {
                case 88:
                    return str.substring(0, 44) + "\n" + str.substring(44, 88) + "\n";
                case 90:
                    return str.substring(0, 30) + "\n" + str.substring(30, 60) + "\n" + str.substring(60, 90) + "\n";
                default:
                    return str;
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.severe("Exception: " + e.getMessage());
            throw new IllegalStateException(e.getMessage());
        }
    }

    public int hashCode() {
        return (toString().hashCode() * 2) + 53;
    }

    public boolean equals(Object obj) {
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        MRZInfo mRZInfo = (MRZInfo) obj;
        if ((this.documentCode != null || mRZInfo.documentCode != null) && (this.documentCode == null || !this.documentCode.equals(mRZInfo.documentCode))) {
            return false;
        }
        if ((this.issuingState != null || mRZInfo.issuingState != null) && (this.issuingState == null || !this.issuingState.equals(mRZInfo.issuingState))) {
            return false;
        }
        if ((this.primaryIdentifier != null || mRZInfo.primaryIdentifier != null) && (this.primaryIdentifier == null || !this.primaryIdentifier.equals(mRZInfo.primaryIdentifier))) {
            return false;
        }
        if ((this.secondaryIdentifier != null || mRZInfo.secondaryIdentifier != null) && !equalsModuloFillerChars(this.secondaryIdentifier, mRZInfo.secondaryIdentifier)) {
            return false;
        }
        if ((this.nationality != null || mRZInfo.nationality != null) && (this.nationality == null || !this.nationality.equals(mRZInfo.nationality))) {
            return false;
        }
        if ((this.documentNumber != null || mRZInfo.documentNumber != null) && (this.documentNumber == null || !this.documentNumber.equals(mRZInfo.documentNumber))) {
            return false;
        }
        if ((this.optionalData1 != null || mRZInfo.optionalData1 != null) && ((this.optionalData1 == null || !this.optionalData1.equals(mRZInfo.optionalData1)) && !getPersonalNumber().equals(mRZInfo.getPersonalNumber()))) {
            return false;
        }
        if ((this.dateOfBirth != null || mRZInfo.dateOfBirth != null) && (this.dateOfBirth == null || !this.dateOfBirth.equals(mRZInfo.dateOfBirth))) {
            return false;
        }
        if ((this.gender != null || mRZInfo.gender != null) && (this.gender == null || !this.gender.equals(mRZInfo.gender))) {
            return false;
        }
        if ((this.dateOfExpiry != null || mRZInfo.dateOfExpiry != null) && (this.dateOfExpiry == null || !this.dateOfExpiry.equals(mRZInfo.dateOfExpiry))) {
            return false;
        }
        if ((this.optionalData2 != null || mRZInfo.optionalData2 != null) && (this.optionalData2 == null || !equalsModuloFillerChars(this.optionalData2, mRZInfo.optionalData2))) {
            return false;
        }
        return true;
    }

    public static char checkDigit(String str) {
        return checkDigit(str, false);
    }

    private void readNameIdentifiers(String str) {
        int indexOf = str.indexOf("<<");
        if (indexOf < 0) {
            throw new IllegalArgumentException("Input does not contain primary identifier!");
        }
        this.primaryIdentifier = trimFillerChars(str.substring(0, indexOf));
        readSecondaryIdentifiers(str.substring(str.indexOf("<<") + 2));
    }

    private void readSecondaryIdentifiers(String str) {
        this.secondaryIdentifier = str;
    }

    private void writeString(String str, DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.write(mrzFormat(str, i).getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeIssuingState(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.issuingState.getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeDateOfExpiry(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.dateOfExpiry.getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeGender(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(genderToString().getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeDateOfBirth(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.dateOfBirth.getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeNationality(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.write(this.nationality.getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeName(DataOutputStream dataOutputStream, int i) throws IOException {
        dataOutputStream.write(nameToString(i).getBytes(JPushConstants.ENCODING_UTF_8));
    }

    private void writeDocumentType(DataOutputStream dataOutputStream) throws IOException {
        writeString(this.documentCode, dataOutputStream, 2);
    }

    private String genderToString() {
        switch (this.gender) {
            case MALE:
                return "M";
            case FEMALE:
                return "F";
            default:
                return "<";
        }
    }

    private String nameToString(int i) {
        String[] split = this.primaryIdentifier.split(" |<");
        String[] split2 = this.secondaryIdentifier.split(" |<");
        StringBuffer stringBuffer = new StringBuffer();
        for (String append : split) {
            stringBuffer.append(append);
            stringBuffer.append('<');
        }
        for (String append2 : split2) {
            stringBuffer.append('<');
            stringBuffer.append(append2);
        }
        return mrzFormat(stringBuffer.toString(), i);
    }

    private String readString(DataInputStream dataInputStream, int i) throws IOException {
        byte[] bArr = new byte[i];
        dataInputStream.readFully(bArr);
        return new String(bArr).trim();
    }

    private String readStringWithFillers(DataInputStream dataInputStream, int i) throws IOException {
        return trimFillerChars(readString(dataInputStream, i));
    }

    private String readCountry(DataInputStream dataInputStream) throws IOException {
        return readString(dataInputStream, 3);
    }

    private Gender readGender(DataInputStream dataInputStream) throws IOException {
        String readString = readString(dataInputStream, 1);
        if (readString.equalsIgnoreCase("M")) {
            return Gender.MALE;
        }
        if (readString.equalsIgnoreCase("F")) {
            return Gender.FEMALE;
        }
        return Gender.UNKNOWN;
    }

    private String readDateOfBirth(DataInputStream dataInputStream) throws IOException, NumberFormatException {
        return readString(dataInputStream, 6);
    }

    private String readDateOfExpiry(DataInputStream dataInputStream) throws IOException, NumberFormatException {
        return readString(dataInputStream, 6);
    }

    private static String mrzFormat(String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("Attempting to MRZ format null");
        } else if (str.length() > i) {
            throw new IllegalArgumentException("Argument too wide (" + str.length() + " > " + i + ")");
        } else {
            String trim = str.toUpperCase().trim();
            StringBuffer stringBuffer = new StringBuffer();
            for (int i2 = 0; i2 < trim.length(); i2++) {
                char charAt = trim.charAt(i2);
                if (MRZ_CHARS.indexOf(charAt) == -1) {
                    stringBuffer.append('<');
                } else {
                    stringBuffer.append(charAt);
                }
            }
            while (stringBuffer.length() < i) {
                stringBuffer.append("<");
            }
            return stringBuffer.toString();
        }
    }

    public static boolean equalsModuloFillerChars(String str, String str2) {
        if (str == str2) {
            return true;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        int max = Math.max(str.length(), str2.length());
        return mrzFormat(str, max).equals(mrzFormat(str2, max));
    }

    private static int getDocumentTypeFromDocumentCode(String str) {
        if (str == null || str.length() < 1 || str.length() > 2) {
            throw new IllegalArgumentException("Was expecting 1 or 2 digit document code, got " + str);
        } else if (str.startsWith("A") || str.startsWith("C") || str.startsWith("I") || str.startsWith("V")) {
            return 1;
        } else {
            if (str.startsWith("P")) {
                return 3;
            }
            return 0;
        }
    }

    private static String trimFillerChars(String str) {
        byte[] bytes = str.trim().getBytes();
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 60) {
                bytes[i] = ISO7816.INS_VERIFY;
            }
        }
        return new String(bytes).trim();
    }

    private void checkDigit() {
        this.documentNumberCheckDigit = checkDigit(this.documentNumber);
        this.dateOfBirthCheckDigit = checkDigit(this.dateOfBirth);
        this.dateOfExpiryCheckDigit = checkDigit(this.dateOfExpiry);
        if (this.optionalData1.length() < 15) {
            String mrzFormat = mrzFormat(this.optionalData1, 14);
            this.optionalData1 = mrzFormat + checkDigit(mrzFormat(this.optionalData1, 14), true);
        }
        StringBuffer stringBuffer = new StringBuffer();
        if (this.documentType == 1) {
            stringBuffer.append(this.documentNumber);
            stringBuffer.append(this.documentNumberCheckDigit);
            stringBuffer.append(mrzFormat(this.optionalData1, 15));
            stringBuffer.append(this.dateOfBirth);
            stringBuffer.append(this.dateOfBirthCheckDigit);
            stringBuffer.append(this.dateOfExpiry);
            stringBuffer.append(this.dateOfExpiryCheckDigit);
            stringBuffer.append(mrzFormat(this.optionalData2, 11));
        } else {
            stringBuffer.append(this.documentNumber);
            stringBuffer.append(this.documentNumberCheckDigit);
            stringBuffer.append(this.dateOfBirth);
            stringBuffer.append(this.dateOfBirthCheckDigit);
            stringBuffer.append(this.dateOfExpiry);
            stringBuffer.append(this.dateOfExpiryCheckDigit);
            stringBuffer.append(mrzFormat(this.optionalData1, 15));
        }
        this.compositeCheckDigit = checkDigit(stringBuffer.toString());
    }

    private static char checkDigit(String str, boolean z) {
        byte[] bytes;
        if (str == null) {
            try {
                bytes = new byte[0];
            } catch (NumberFormatException e) {
                LOGGER.severe("Exception: " + e.getMessage());
                throw new IllegalStateException("Error in computing check digit.");
            } catch (UnsupportedEncodingException e2) {
                LOGGER.severe("Exception: " + e2.getMessage());
                throw new IllegalStateException("Error in computing check digit.");
            } catch (Exception e3) {
                LOGGER.severe("Exception: " + e3.getMessage());
                throw new IllegalArgumentException(e3.toString());
            }
        } else {
            bytes = str.getBytes(JPushConstants.ENCODING_UTF_8);
        }
        int[] iArr = {7, 3, 1};
        int i = 0;
        for (int i2 = 0; i2 < bytes.length; i2++) {
            i = (i + (iArr[i2 % 3] * decodeMRZDigit(bytes[i2]))) % 10;
        }
        String num = Integer.toString(i);
        if (num.length() != 1) {
            throw new IllegalStateException("Error in computing check digit.");
        }
        char c = (char) num.getBytes(JPushConstants.ENCODING_UTF_8)[0];
        if (!z || c != '0') {
            return c;
        }
        return '<';
    }

    private static int decodeMRZDigit(byte b) throws NumberFormatException {
        switch (b) {
            case 48:
            case 60:
                return 0;
            case 49:
                return 1;
            case 50:
                return 2;
            case 51:
                return 3;
            case 52:
                return 4;
            case 53:
                return 5;
            case 54:
                return 6;
            case 55:
                return 7;
            case 56:
                return 8;
            case 57:
                return 9;
            case 65:
            case 97:
                return 10;
            case 66:
            case 98:
                return 11;
            case 67:
            case 99:
                return 12;
            case 68:
            case 100:
                return 13;
            case 69:
            case 101:
                return 14;
            case 70:
            case 102:
                return 15;
            case 71:
            case 103:
                return 16;
            case 72:
            case 104:
                return 17;
            case 73:
            case 105:
                return 18;
            case 74:
            case 106:
                return 19;
            case 75:
            case 107:
                return 20;
            case 76:
            case 108:
                return 21;
            case 77:
            case 109:
                return 22;
            case 78:
            case 110:
                return 23;
            case 79:
            case 111:
                return 24;
            case 80:
            case 112:
                return 25;
            case 81:
            case 113:
                return 26;
            case 82:
            case 114:
                return 27;
            case 83:
            case 115:
                return 28;
            case 84:
            case 116:
                return 29;
            case 85:
            case LDSFile.EF_DG2_TAG /*117*/:
                return 30;
            case 86:
            case LDSFile.EF_DG4_TAG /*118*/:
                return 31;
            case 87:
            case LDSFile.EF_SOD_TAG /*119*/:
                return 32;
            case 88:
            case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                return 33;
            case 89:
            case EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY /*121*/:
                return 34;
            case 90:
            case EACTags.SECURITY_SUPPORT_TEMPLATE /*122*/:
                return 35;
            default:
                throw new NumberFormatException("Could not decode MRZ character " + b + " ('" + Character.toString((char) b) + "')");
        }
    }

    public String getMrzLine1() {
        return this.mrzLine1;
    }

    public String getMrzLine2() {
        return this.mrzLine2;
    }

    public String getMrzLine3() {
        return this.mrzLine3;
    }
}
