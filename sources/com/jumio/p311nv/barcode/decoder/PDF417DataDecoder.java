package com.jumio.p311nv.barcode.decoder;

import android.annotation.SuppressLint;
import com.jumio.p311nv.barcode.decoder.exception.PDF417DecoderException;
import com.jumio.p311nv.barcode.enums.EyeColor;
import com.jumio.p311nv.enums.NVGender;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressLint({"SimpleDateFormat"})
/* renamed from: com.jumio.nv.barcode.decoder.PDF417DataDecoder */
public class PDF417DataDecoder {
    private static final String ADDRESS_CITY = "DAI";
    private static final String ADDRESS_CITY_2000 = "DAN";
    private static final String ADDRESS_STATE = "DAJ";
    private static final String ADDRESS_STATE_2000 = "DAO";
    private static final String ADDRESS_STREET_1 = "DAG";
    private static final String ADDRESS_STREET_1_2000 = "DAL";
    private static final String ADDRESS_STREET_2 = "DAH";
    private static final String ADDRESS_STREET_2_2000 = "DAM";
    private static final String ADDRESS_ZIP = "DAK";
    private static final String ADDRESS_ZIP_2000 = "DAP";
    private static final String COMPLIANCE_INDICATOR = "@";
    private static final int COMPLIANCE_INDICATOR_LENGTH = 1;
    private static final String DATE_OF_BIRTH = "DBB";
    private static final String ENDORSEMENT_CODES = "DCD";
    private static final String EXPIRY_DATE = "DBA";
    private static final String EYE_COLOR = "DAY";
    private static final String EYE_COLOR_BLACK = "BLK";
    private static final String EYE_COLOR_BLUE = "BLU";
    private static final String EYE_COLOR_BROWN = "BRO";
    private static final String EYE_COLOR_BROWN_LEGACY_1 = "BR";
    private static final String EYE_COLOR_BROWN_LEGACY_2 = "BRN";
    private static final String EYE_COLOR_DICHROMATIC = "DIC";
    private static final String EYE_COLOR_GRAY = "GRY";
    private static final String EYE_COLOR_GREEN = "GRN";
    private static final String EYE_COLOR_HAZEL = "HAZ";
    private static final String EYE_COLOR_HAZEL_LEGACY_1 = "HZL";
    private static final String EYE_COLOR_MAROON = "MAR";
    private static final String EYE_COLOR_PINK = "PNK";
    private static final String EYE_COLOR_UNKNOWN = "UNK";
    private static final String FALLBACK_RECORD_SEPARATOR = ",";
    private static final String FIRST_NAME = "DAC";
    private static final String GIVEN_NAME = "DCT";
    private static final String HEADER_REGEX = "@([\\x00-\\x1F\\x21-\\x40\\x5B-\\xFF]{2,3})[A-Z ]*([0-9]{6})([0-9]{2})[0-9]*";
    private static final String HEIGHT = "DAU";
    private static final String ID_NUMBER = "DAQ";
    private static final String IIN_COLORADO = "636020";
    private static final String ISSUE_DATE = "DBD";
    private static final String ISSUING_COUNTRY = "DCG";
    private static final String LAST_NAME = "DCS";
    private static final String LAST_NAME_2000 = "DAB";
    private static final int MAGSTRIPE_ADDRESS_MAX_LENGTH = 29;
    private static final int MAGSTRIPE_CDS_VERSION_LENGTH = 1;
    private static final int MAGSTRIPE_CITY_MAX_LENGTH = 13;
    private static final int MAGSTRIPE_CLASS_LENGTH = 2;
    private static final int MAGSTRIPE_DATE_OF_BIRTH_LENGTH = 8;
    private static final int MAGSTRIPE_ENDORSEMENTS_LENGTH = 4;
    private static final int MAGSTRIPE_EXPIRY_DATE_LENGTH = 4;
    private static final String MAGSTRIPE_FIELD_SEPERATOR = "^";
    private static final String MAGSTRIPE_IDNO_SEPERATOR = "=";
    private static final int MAGSTRIPE_IIN_LENGTH = 6;
    private static final int MAGSTRIPE_JURISDICATION_VERSION_LENGTH = 1;
    private static final String MAGSTRIPE_LINE_SEPERATOR = "\\$";
    private static final int MAGSTRIPE_NAME_MAX_LENGTH = 35;
    private static final int MAGSTRIPE_POSTAL_CODE_LENGTH = 11;
    private static final int MAGSTRIPE_RESTRICTION_LENGTH = 10;
    private static final int MAGSTRIPE_SEX_LENGTH = 1;
    private static final int MAGSTRIPE_STATE_LENGTH = 2;
    private static final String MAGSTRIPE_TRACK1_START = "%";
    private static final String MAGSTRIPE_TRACK2_START = ";";
    private static final String MAGSTRIPE_TRACK3_START = "%";
    private static final String MIDDLE_NAME = "DAD";
    private static final String NAME = "DAA";
    private static final String RESTRICTION_CODES = "DCB";
    private static final String SEX = "DBC";
    private static final String SUBFILE_DESIGNATOR_REGEX = "^((?:[A-Z]{2}[0-9]{8})*)";
    private static final String SUBFILE_TYPE_REGEX = "([A-Z]{2})";
    private static final String VEHICLE_CLASS = "DCA";
    private final String FORMAT_DDMM;
    private final String FORMAT_MMDD;
    private final String FORMAT_YYYY;
    private final String REGEX_DDMM;
    private final String REGEX_MMDD;
    private final String REGEX_YYYY;
    private int aamvaVersion;
    private PDF417Data data;
    private SimpleDateFormat dateFormat;
    private char[] delimiter;
    private int index;
    private String issuerIdentificationNumber;
    private String rawData;
    private ArrayList<String> subfiles;

    public PDF417DataDecoder() {
        this.REGEX_YYYY = "^(?:(?:19|20)\\d{2})$";
        this.REGEX_MMDD = "^(?:0[1-9]|1[0-2])(?:0[1-9]|[12][0-9]|3[01])$";
        this.REGEX_DDMM = "^(?:0[1-9]|[12][0-9]|3[01])(?:0[1-9]|1[0-2])$";
        this.FORMAT_YYYY = "yyyy";
        this.FORMAT_MMDD = "MMdd";
        this.FORMAT_DDMM = "ddMM";
        this.dateFormat = null;
        this.rawData = "";
        this.issuerIdentificationNumber = "";
        this.aamvaVersion = -1;
        this.index = 0;
        this.data = null;
        this.dateFormat = new SimpleDateFormat();
        this.subfiles = new ArrayList<>();
    }

    @SuppressLint({"NewApi"})
    public PDF417Data decode(byte[] bArr, Charset charset) throws PDF417DecoderException {
        return decode(new String(bArr, charset));
    }

    public PDF417Data decode(String str) throws PDF417DecoderException {
        if (str == null) {
            throw new PDF417DecoderException("Empty data");
        }
        this.rawData = str;
        if (str.substring(this.index, this.index + 1).equals(COMPLIANCE_INDICATOR)) {
            this.index++;
            parseHeader();
            parseSubfileDesignator();
            parseBarcode();
        } else {
            parseMagstripe();
        }
        return this.data;
    }

    private void parseHeader() {
        Matcher matcher = Pattern.compile(HEADER_REGEX).matcher(this.rawData);
        String str = "";
        if (matcher.find()) {
            String group = matcher.group(1);
            this.delimiter = new char[group.length()];
            group.getChars(0, group.length(), this.delimiter, 0);
            this.issuerIdentificationNumber = matcher.group(2);
            this.aamvaVersion = Integer.parseInt(matcher.group(3));
        }
        this.rawData = this.rawData.replaceAll(HEADER_REGEX, "");
        this.index = 0;
    }

    private void parseSubfileDesignator() {
        Matcher matcher = Pattern.compile(SUBFILE_DESIGNATOR_REGEX).matcher(this.rawData);
        this.subfiles.clear();
        if (matcher.find()) {
            Matcher matcher2 = Pattern.compile(SUBFILE_TYPE_REGEX).matcher(matcher.group(1));
            while (matcher2.find()) {
                this.subfiles.add(matcher2.group(1));
            }
        }
        this.rawData = this.rawData.replaceAll(SUBFILE_DESIGNATOR_REGEX, "");
        this.index = 0;
    }

    private int getNearestSeparator() throws PDF417DecoderException {
        if (this.delimiter == null || this.rawData == null) {
            throw new PDF417DecoderException("Delimiter or rawdata not set");
        } else if (this.index >= this.rawData.length()) {
            return -1;
        } else {
            int length = this.rawData.length();
            for (char indexOf : this.delimiter) {
                int indexOf2 = this.rawData.indexOf(indexOf, this.index);
                if (indexOf2 < length && indexOf2 != -1) {
                    length = indexOf2;
                }
            }
            return length;
        }
    }

    private void parseBarcode() throws PDF417DecoderException {
        String str;
        this.data = new PDF417Data();
        int nearestSeparator = getNearestSeparator();
        while (true) {
            int i = nearestSeparator;
            if (i != -1) {
                String substring = this.rawData.substring(this.index, i);
                int i2 = 0;
                while (true) {
                    if (i2 >= this.subfiles.size()) {
                        str = substring;
                        break;
                    } else if (substring.startsWith((String) this.subfiles.get(i2))) {
                        str = substring.substring(((String) this.subfiles.get(i2)).length());
                        break;
                    } else {
                        i2++;
                    }
                }
                if (str.startsWith(NAME)) {
                    String substring2 = str.substring(NAME.length());
                    if (substring2.contains(FALLBACK_RECORD_SEPARATOR)) {
                        String[] split = substring2.split(FALLBACK_RECORD_SEPARATOR);
                        if (this.aamvaVersion != 1 || !IIN_COLORADO.equals(this.issuerIdentificationNumber)) {
                            if (split.length > 0) {
                                this.data.setLastName(split[0]);
                            }
                            if (split.length > 1) {
                                this.data.setFirstName(split[1]);
                            }
                            if (split.length > 2) {
                                this.data.setMiddleName(split[2]);
                            }
                        } else {
                            if (split.length > 0) {
                                this.data.setFirstName(split[0]);
                            }
                            if (split.length > 2) {
                                this.data.setMiddleName(split[1]);
                            }
                            if (split.length > 1) {
                                this.data.setLastName(split[split.length - 1]);
                            }
                        }
                    } else {
                        String[] split2 = substring2.split(" ");
                        if (split2.length > 0) {
                            if (split2.length == 1) {
                                this.data.setLastName(split2[0]);
                            } else if (split2.length == 2) {
                                this.data.setFirstName(split2[0]);
                                this.data.setLastName(split2[1]);
                            } else {
                                this.data.setFirstName(split2[0]);
                                this.data.setMiddleName(split2[1]);
                                String str2 = "";
                                StringBuilder sb = new StringBuilder();
                                for (int i3 = 2; i3 < split2.length; i3++) {
                                    sb.append(split2[i3]);
                                    sb.append(" ");
                                }
                                this.data.setLastName(sb.toString());
                            }
                        }
                    }
                } else if (str.startsWith(FIRST_NAME)) {
                    this.data.setFirstName(str.substring(FIRST_NAME.length()));
                } else if (str.startsWith(GIVEN_NAME)) {
                    this.data.setFirstName(str.substring(GIVEN_NAME.length()).replaceAll(FALLBACK_RECORD_SEPARATOR, " "));
                } else if (str.startsWith(LAST_NAME) || str.startsWith(LAST_NAME_2000)) {
                    this.data.setLastName(str.substring(LAST_NAME.length()));
                } else if (str.startsWith(MIDDLE_NAME)) {
                    this.data.setMiddleName(str.substring(MIDDLE_NAME.length()));
                } else if (str.startsWith(SEX)) {
                    String substring3 = str.substring(SEX.length());
                    if ("M".equals(substring3) || "1".equals(substring3)) {
                        this.data.setGender(NVGender.M);
                    } else if ("F".equals(substring3) || "2".equals(substring3)) {
                        this.data.setGender(NVGender.F);
                    }
                } else if (str.startsWith(DATE_OF_BIRTH)) {
                    this.data.setDateOfBirth(parseDate(str.substring(DATE_OF_BIRTH.length()), true));
                } else if (str.startsWith(EYE_COLOR)) {
                    String substring4 = str.substring(EYE_COLOR.length());
                    if (substring4.equals(EYE_COLOR_BLACK)) {
                        this.data.setEyeColor(EyeColor.BLACK);
                    } else if (substring4.equals(EYE_COLOR_BLUE)) {
                        this.data.setEyeColor(EyeColor.BLUE);
                    } else if (substring4.equals(EYE_COLOR_BROWN) || substring4.equals("BR") || substring4.equals(EYE_COLOR_BROWN_LEGACY_2)) {
                        this.data.setEyeColor(EyeColor.BROWN);
                    } else if (substring4.equals(EYE_COLOR_GRAY)) {
                        this.data.setEyeColor(EyeColor.GRAY);
                    } else if (substring4.equals(EYE_COLOR_GREEN)) {
                        this.data.setEyeColor(EyeColor.GREEN);
                    } else if (substring4.equals(EYE_COLOR_HAZEL) || substring4.equals(EYE_COLOR_HAZEL_LEGACY_1)) {
                        this.data.setEyeColor(EyeColor.HAZEL);
                    } else if (substring4.equals(EYE_COLOR_MAROON)) {
                        this.data.setEyeColor(EyeColor.MAROON);
                    } else if (substring4.equals(EYE_COLOR_PINK)) {
                        this.data.setEyeColor(EyeColor.PINK);
                    } else if (substring4.equals(EYE_COLOR_DICHROMATIC)) {
                        this.data.setEyeColor(EyeColor.DICHROMATIC);
                    } else if (substring4.equals(EYE_COLOR_UNKNOWN)) {
                        this.data.setEyeColor(EyeColor.UNKNOWN);
                    }
                } else if (str.startsWith(HEIGHT)) {
                    this.data.setHeight(str.substring(HEIGHT.length()));
                } else if (str.startsWith(ADDRESS_STREET_1) || str.startsWith(ADDRESS_STREET_1_2000)) {
                    this.data.setAddressStreet1(str.substring(ADDRESS_STREET_1.length()));
                } else if (str.startsWith(ADDRESS_STREET_2) || str.startsWith(ADDRESS_STREET_2_2000)) {
                    this.data.setAddressStreet2(str.substring(ADDRESS_STREET_2.length()));
                } else if (str.startsWith(ADDRESS_CITY) || str.startsWith(ADDRESS_CITY_2000)) {
                    this.data.setAddressCity(str.substring(ADDRESS_CITY.length()));
                } else if (str.startsWith(ADDRESS_STATE) || str.startsWith(ADDRESS_STATE_2000)) {
                    this.data.setAddressState(str.substring(ADDRESS_CITY.length()));
                } else if (str.startsWith(ADDRESS_ZIP) || str.startsWith(ADDRESS_ZIP_2000)) {
                    this.data.setAddressZip(str.substring(ADDRESS_ZIP.length()));
                } else if (str.startsWith(ID_NUMBER)) {
                    this.data.setIdNumber(str.substring(ID_NUMBER.length()));
                } else if (str.startsWith(ISSUING_COUNTRY)) {
                    this.data.setIssuingCountry(str.substring(ISSUING_COUNTRY.length()));
                } else if (str.startsWith(ISSUE_DATE)) {
                    this.data.setIssueDate(parseDate(str.substring(ISSUE_DATE.length()), true));
                } else if (str.startsWith(EXPIRY_DATE)) {
                    this.data.setExpiryDate(parseDate(str.substring(EXPIRY_DATE.length()), false));
                } else if (str.startsWith(VEHICLE_CLASS)) {
                    this.data.setVehicleClass(str.substring(VEHICLE_CLASS.length()));
                } else if (str.startsWith(RESTRICTION_CODES)) {
                    this.data.setRestrictionCodes(str.substring(RESTRICTION_CODES.length()));
                } else if (str.startsWith(ENDORSEMENT_CODES)) {
                    this.data.setEndorsementCodes(str.substring(ENDORSEMENT_CODES.length()));
                } else {
                    this.data.addUnparsedData(str, "\n");
                }
                this.index = i + 1;
                nearestSeparator = getNearestSeparator();
            } else {
                return;
            }
        }
    }

    private PDF417Data parseMagstripe() throws PDF417DecoderException {
        int i;
        int i2;
        int i3;
        boolean z;
        int i4;
        int i5;
        int i6;
        boolean z2;
        int i7 = 1;
        try {
            this.data = new PDF417Data();
            this.index = 0;
            if (this.rawData.startsWith("%")) {
                this.index++;
            }
            this.data.setAddressState(this.rawData.substring(this.index, this.index + 2));
            this.index += 2;
            int indexOf = this.rawData.indexOf(MAGSTRIPE_FIELD_SEPERATOR, this.index) - this.index;
            boolean z3 = indexOf < 13;
            if (z3) {
                i2 = (13 - indexOf) + 0;
                i = indexOf;
            } else {
                i = 13;
                i2 = 0;
            }
            this.data.setAddressCity(this.rawData.substring(this.index, this.index + i));
            int i8 = this.index;
            if (z3) {
                i3 = 1;
            } else {
                i3 = 0;
            }
            this.index = i3 + i + i8;
            int indexOf2 = this.rawData.indexOf(MAGSTRIPE_FIELD_SEPERATOR, this.index) - this.index;
            if (indexOf2 < this.index + 35) {
                z = true;
            } else {
                z = false;
            }
            if (z) {
                i4 = i2 + (35 - indexOf2);
                i5 = indexOf2;
            } else {
                i4 = i2;
                i5 = 35;
            }
            String[] split = this.rawData.substring(this.index, this.index + i5).split(MAGSTRIPE_LINE_SEPERATOR);
            this.data.setLastName(split[0].replaceAll("(\\w*).*", "$1"));
            if (split.length > 1) {
                this.data.setFirstName(split[1]);
            }
            int i9 = this.index;
            if (z) {
                i6 = 1;
            } else {
                i6 = 0;
            }
            this.index = i6 + i5 + i9;
            int indexOf3 = this.rawData.indexOf(MAGSTRIPE_FIELD_SEPERATOR, this.index) - this.index;
            if (indexOf3 < 0 || indexOf3 > i4 + 29) {
                indexOf3 = i4 + 29;
                z2 = false;
            } else {
                z2 = true;
            }
            String[] split2 = this.rawData.substring(this.index, this.index + indexOf3).split(MAGSTRIPE_LINE_SEPERATOR);
            this.data.setAddressStreet1(split2[0]);
            if (split2.length > 1) {
                this.data.setAddressStreet2(split2[1]);
            }
            int i10 = this.index;
            if (!z2) {
                i7 = 0;
            }
            this.index = i7 + indexOf3 + i10;
            if (this.rawData.startsWith(MAGSTRIPE_FIELD_SEPERATOR, this.index)) {
                this.index++;
            }
            if (this.rawData.startsWith(MAGSTRIPE_TRACK2_START, this.index)) {
                this.index++;
            }
            this.issuerIdentificationNumber = this.rawData.substring(this.index, this.index + 6);
            this.index += 6;
            int indexOf4 = this.rawData.indexOf(MAGSTRIPE_IDNO_SEPERATOR, this.index) - this.index;
            this.data.setIdNumber(this.rawData.substring(this.index, this.index + indexOf4));
            this.index = indexOf4 + 1 + this.index;
            this.dateFormat.applyPattern("yyMM");
            Date parse = this.dateFormat.parse(this.rawData.substring(this.index, this.index + 4));
            this.index += 4;
            this.dateFormat.applyPattern("yyyyMMdd");
            Date parse2 = this.dateFormat.parse(this.rawData.substring(this.index, this.index + 8));
            this.data.setDateOfBirth(parse2);
            this.index += 8;
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(parse2);
            int i11 = gregorianCalendar.get(5);
            gregorianCalendar.setTime(parse);
            gregorianCalendar.set(5, i11);
            this.data.setExpiryDate(gregorianCalendar.getTime());
            if (this.rawData.indexOf("%", this.index) != -1) {
                this.index = this.rawData.indexOf("%", this.index) + "%".length();
            }
            this.index++;
            this.index++;
            this.data.setAddressZip(this.rawData.substring(this.index, this.index + 11));
            this.index += 11;
            this.index += 2;
            this.index += 10;
            this.index += 4;
            String substring = this.rawData.substring(this.index, this.index + 1);
            if ("1".equals(substring) || "M".equals(substring)) {
                this.data.setGender(NVGender.M);
            } else if ("2".equals(substring) || "F".equals(substring)) {
                this.data.setGender(NVGender.F);
            }
            return this.data;
        } catch (Exception e) {
            throw new PDF417DecoderException(e, "Incompatible magstripe structure");
        }
    }

    private Date parseDate(String str, boolean z) {
        if (str.isEmpty()) {
            return null;
        }
        try {
            String replaceAll = str.replaceAll("\\D", "");
            String substring = replaceAll.substring(0, 4);
            String substring2 = replaceAll.substring(4);
            Pattern compile = Pattern.compile("^(?:(?:19|20)\\d{2})$");
            Pattern compile2 = Pattern.compile("^(?:0[1-9]|1[0-2])(?:0[1-9]|[12][0-9]|3[01])$");
            Pattern compile3 = Pattern.compile("^(?:0[1-9]|[12][0-9]|3[01])(?:0[1-9]|1[0-2])$");
            if (compile.matcher(substring).matches() && compile2.matcher(substring2).matches()) {
                this.dateFormat.applyPattern("yyyyMMdd");
            } else if (compile.matcher(substring).matches() && compile3.matcher(substring2).matches()) {
                this.dateFormat.applyPattern("yyyyddMM");
            } else if (compile2.matcher(substring).matches() && compile.matcher(substring2).matches()) {
                this.dateFormat.applyPattern("MMddyyyy");
            } else if (compile3.matcher(substring).matches() && compile.matcher(substring2).matches()) {
                this.dateFormat.applyPattern("ddMMyyyy");
            }
            Date parse = this.dateFormat.parse(replaceAll);
            if (z) {
                try {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    if (parse.after(simpleDateFormat.parse(simpleDateFormat.format(new Date())))) {
                        return null;
                    }
                } catch (Exception e) {
                    return parse;
                }
            }
            return parse;
        } catch (Exception e2) {
            return null;
        }
    }
}
