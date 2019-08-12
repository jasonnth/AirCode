package com.google.i18n.phonenumbers;

import com.facebook.appevents.AppEventsConstants;
import com.google.i18n.phonenumbers.NumberParseException.ErrorType;
import com.google.i18n.phonenumbers.Phonemetadata.NumberFormat;
import com.google.i18n.phonenumbers.Phonemetadata.PhoneMetadata;
import com.google.i18n.phonenumbers.Phonemetadata.PhoneNumberDesc;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber.CountryCodeSource;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.spongycastle.pqc.math.linearalgebra.Matrix;

public class PhoneNumberUtil {
    private static final Map<Character, Character> ALL_PLUS_NUMBER_GROUPING_SYMBOLS;
    private static final Map<Character, Character> ALPHA_MAPPINGS;
    private static final Map<Character, Character> ALPHA_PHONE_MAPPINGS;
    private static final Pattern CAPTURING_DIGIT_PATTERN = Pattern.compile("(\\p{Nd})");
    private static final Pattern CC_PATTERN = Pattern.compile("\\$CC");
    static final MetadataLoader DEFAULT_METADATA_LOADER = new MetadataLoader() {
        public InputStream loadMetadata(String metadataFileName) {
            return PhoneNumberUtil.class.getResourceAsStream(metadataFileName);
        }
    };
    private static final Map<Character, Character> DIALLABLE_CHAR_MAPPINGS;
    private static final Pattern EXTN_PATTERN = Pattern.compile("(?:" + EXTN_PATTERNS_FOR_PARSING + ")$", 66);
    static final String EXTN_PATTERNS_FOR_MATCHING;
    private static final String EXTN_PATTERNS_FOR_PARSING;
    private static final Pattern FG_PATTERN = Pattern.compile("\\$FG");
    private static final Pattern FIRST_GROUP_ONLY_PREFIX_PATTERN = Pattern.compile("\\(?\\$1\\)?");
    private static final Pattern FIRST_GROUP_PATTERN = Pattern.compile("(\\$\\d)");
    private static final Set<Integer> GEO_MOBILE_COUNTRIES;
    private static final Set<Integer> GEO_MOBILE_COUNTRIES_WITHOUT_MOBILE_AREA_CODES;
    private static final Map<Integer, String> MOBILE_TOKEN_MAPPINGS;
    static final Pattern NON_DIGITS_PATTERN = Pattern.compile("(\\D+)");
    private static final Pattern NP_PATTERN = Pattern.compile("\\$NP");
    static final Pattern PLUS_CHARS_PATTERN = Pattern.compile("[+＋]+");
    static final Pattern SECOND_NUMBER_START_PATTERN = Pattern.compile("[\\\\/] *x");
    private static final Pattern SEPARATOR_PATTERN = Pattern.compile("[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～]+");
    private static final Pattern UNIQUE_INTERNATIONAL_PREFIX = Pattern.compile("[\\d]+(?:[~⁓∼～][\\d]+)?");
    static final Pattern UNWANTED_END_CHAR_PATTERN = Pattern.compile("[[\\P{N}&&\\P{L}]&&[^#]]+$");
    private static final String VALID_ALPHA = (Arrays.toString(ALPHA_MAPPINGS.keySet().toArray()).replaceAll("[, \\[\\]]", "") + Arrays.toString(ALPHA_MAPPINGS.keySet().toArray()).toLowerCase().replaceAll("[, \\[\\]]", ""));
    private static final Pattern VALID_ALPHA_PHONE_PATTERN = Pattern.compile("(?:.*?[A-Za-z]){3}.*");
    private static final String VALID_PHONE_NUMBER = ("\\p{Nd}{2}|[+＋]*+(?:[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*]*\\p{Nd}){3,}[-x‐-―−ー－-／  ­​⁠　()（）［］.\\[\\]/~⁓∼～*" + VALID_ALPHA + "\\p{Nd}" + "]*");
    private static final Pattern VALID_PHONE_NUMBER_PATTERN = Pattern.compile(VALID_PHONE_NUMBER + "(?:" + EXTN_PATTERNS_FOR_PARSING + ")?", 66);
    private static final Pattern VALID_START_CHAR_PATTERN = Pattern.compile("[+＋\\p{Nd}]");
    private static PhoneNumberUtil instance = null;
    private static final Logger logger = Logger.getLogger(PhoneNumberUtil.class.getName());
    private final Map<Integer, List<String>> countryCallingCodeToRegionCodeMap;
    private final Set<Integer> countryCodesForNonGeographicalRegion = new HashSet();
    private final MetadataSource metadataSource;
    private final Set<String> nanpaRegions = new HashSet(35);
    private final RegexCache regexCache = new RegexCache(100);
    private final Set<String> supportedRegions = new HashSet(320);

    public enum PhoneNumberFormat {
        E164,
        INTERNATIONAL,
        NATIONAL,
        RFC3966
    }

    /* renamed from: com.google.i18n.phonenumbers.PhoneNumberUtil$3 */
    static /* synthetic */ class C14813 {

        /* renamed from: $SwitchMap$com$google$i18n$phonenumbers$Phonenumber$PhoneNumber$CountryCodeSource */
        static final /* synthetic */ int[] f1380x9de98e3a = new int[CountryCodeSource.values().length];

        static {
            f1379xa7892e7c = new int[PhoneNumberType.values().length];
            try {
                f1379xa7892e7c[PhoneNumberType.PREMIUM_RATE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.TOLL_FREE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.MOBILE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.FIXED_LINE.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.FIXED_LINE_OR_MOBILE.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.SHARED_COST.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.VOIP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.PERSONAL_NUMBER.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.PAGER.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.UAN.ordinal()] = 10;
            } catch (NoSuchFieldError e10) {
            }
            try {
                f1379xa7892e7c[PhoneNumberType.VOICEMAIL.ordinal()] = 11;
            } catch (NoSuchFieldError e11) {
            }
            f1378xd187ceb9 = new int[PhoneNumberFormat.values().length];
            try {
                f1378xd187ceb9[PhoneNumberFormat.E164.ordinal()] = 1;
            } catch (NoSuchFieldError e12) {
            }
            try {
                f1378xd187ceb9[PhoneNumberFormat.INTERNATIONAL.ordinal()] = 2;
            } catch (NoSuchFieldError e13) {
            }
            try {
                f1378xd187ceb9[PhoneNumberFormat.RFC3966.ordinal()] = 3;
            } catch (NoSuchFieldError e14) {
            }
            try {
                f1378xd187ceb9[PhoneNumberFormat.NATIONAL.ordinal()] = 4;
            } catch (NoSuchFieldError e15) {
            }
            try {
                f1380x9de98e3a[CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN.ordinal()] = 1;
            } catch (NoSuchFieldError e16) {
            }
            try {
                f1380x9de98e3a[CountryCodeSource.FROM_NUMBER_WITH_IDD.ordinal()] = 2;
            } catch (NoSuchFieldError e17) {
            }
            try {
                f1380x9de98e3a[CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN.ordinal()] = 3;
            } catch (NoSuchFieldError e18) {
            }
            try {
                f1380x9de98e3a[CountryCodeSource.FROM_DEFAULT_COUNTRY.ordinal()] = 4;
            } catch (NoSuchFieldError e19) {
            }
        }
    }

    public enum PhoneNumberType {
        FIXED_LINE,
        MOBILE,
        FIXED_LINE_OR_MOBILE,
        TOLL_FREE,
        PREMIUM_RATE,
        SHARED_COST,
        VOIP,
        PERSONAL_NUMBER,
        PAGER,
        UAN,
        VOICEMAIL,
        UNKNOWN
    }

    public enum ValidationResult {
        IS_POSSIBLE,
        INVALID_COUNTRY_CODE,
        TOO_SHORT,
        TOO_LONG
    }

    static {
        HashMap<Integer, String> mobileTokenMap = new HashMap<>();
        mobileTokenMap.put(Integer.valueOf(52), "1");
        mobileTokenMap.put(Integer.valueOf(54), "9");
        MOBILE_TOKEN_MAPPINGS = Collections.unmodifiableMap(mobileTokenMap);
        HashSet<Integer> geoMobileCountriesWithoutMobileAreaCodes = new HashSet<>();
        geoMobileCountriesWithoutMobileAreaCodes.add(Integer.valueOf(86));
        GEO_MOBILE_COUNTRIES_WITHOUT_MOBILE_AREA_CODES = Collections.unmodifiableSet(geoMobileCountriesWithoutMobileAreaCodes);
        HashSet<Integer> geoMobileCountries = new HashSet<>();
        geoMobileCountries.add(Integer.valueOf(52));
        geoMobileCountries.add(Integer.valueOf(54));
        geoMobileCountries.add(Integer.valueOf(55));
        geoMobileCountries.add(Integer.valueOf(62));
        geoMobileCountries.addAll(geoMobileCountriesWithoutMobileAreaCodes);
        GEO_MOBILE_COUNTRIES = Collections.unmodifiableSet(geoMobileCountries);
        HashMap<Character, Character> asciiDigitMappings = new HashMap<>();
        asciiDigitMappings.put(Character.valueOf('0'), Character.valueOf('0'));
        asciiDigitMappings.put(Character.valueOf('1'), Character.valueOf('1'));
        asciiDigitMappings.put(Character.valueOf('2'), Character.valueOf('2'));
        asciiDigitMappings.put(Character.valueOf('3'), Character.valueOf('3'));
        asciiDigitMappings.put(Character.valueOf('4'), Character.valueOf('4'));
        asciiDigitMappings.put(Character.valueOf('5'), Character.valueOf('5'));
        asciiDigitMappings.put(Character.valueOf('6'), Character.valueOf('6'));
        asciiDigitMappings.put(Character.valueOf('7'), Character.valueOf('7'));
        asciiDigitMappings.put(Character.valueOf('8'), Character.valueOf('8'));
        asciiDigitMappings.put(Character.valueOf('9'), Character.valueOf('9'));
        HashMap<Character, Character> alphaMap = new HashMap<>(40);
        alphaMap.put(Character.valueOf('A'), Character.valueOf('2'));
        alphaMap.put(Character.valueOf('B'), Character.valueOf('2'));
        alphaMap.put(Character.valueOf('C'), Character.valueOf('2'));
        alphaMap.put(Character.valueOf('D'), Character.valueOf('3'));
        alphaMap.put(Character.valueOf('E'), Character.valueOf('3'));
        alphaMap.put(Character.valueOf('F'), Character.valueOf('3'));
        alphaMap.put(Character.valueOf('G'), Character.valueOf('4'));
        alphaMap.put(Character.valueOf('H'), Character.valueOf('4'));
        alphaMap.put(Character.valueOf('I'), Character.valueOf('4'));
        alphaMap.put(Character.valueOf('J'), Character.valueOf('5'));
        alphaMap.put(Character.valueOf('K'), Character.valueOf('5'));
        alphaMap.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_LT), Character.valueOf('5'));
        alphaMap.put(Character.valueOf('M'), Character.valueOf('6'));
        alphaMap.put(Character.valueOf('N'), Character.valueOf('6'));
        alphaMap.put(Character.valueOf('O'), Character.valueOf('6'));
        alphaMap.put(Character.valueOf('P'), Character.valueOf('7'));
        alphaMap.put(Character.valueOf('Q'), Character.valueOf('7'));
        alphaMap.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_REGULAR), Character.valueOf('7'));
        alphaMap.put(Character.valueOf('S'), Character.valueOf('7'));
        alphaMap.put(Character.valueOf('T'), Character.valueOf('8'));
        alphaMap.put(Character.valueOf(Matrix.MATRIX_TYPE_RANDOM_UT), Character.valueOf('8'));
        alphaMap.put(Character.valueOf('V'), Character.valueOf('8'));
        alphaMap.put(Character.valueOf('W'), Character.valueOf('9'));
        alphaMap.put(Character.valueOf('X'), Character.valueOf('9'));
        alphaMap.put(Character.valueOf('Y'), Character.valueOf('9'));
        alphaMap.put(Character.valueOf(Matrix.MATRIX_TYPE_ZERO), Character.valueOf('9'));
        ALPHA_MAPPINGS = Collections.unmodifiableMap(alphaMap);
        HashMap<Character, Character> combinedMap = new HashMap<>(100);
        combinedMap.putAll(ALPHA_MAPPINGS);
        combinedMap.putAll(asciiDigitMappings);
        ALPHA_PHONE_MAPPINGS = Collections.unmodifiableMap(combinedMap);
        HashMap<Character, Character> diallableCharMap = new HashMap<>();
        diallableCharMap.putAll(asciiDigitMappings);
        diallableCharMap.put(Character.valueOf('+'), Character.valueOf('+'));
        diallableCharMap.put(Character.valueOf('*'), Character.valueOf('*'));
        DIALLABLE_CHAR_MAPPINGS = Collections.unmodifiableMap(diallableCharMap);
        HashMap<Character, Character> allPlusNumberGroupings = new HashMap<>();
        for (Character charValue : ALPHA_MAPPINGS.keySet()) {
            char c = charValue.charValue();
            allPlusNumberGroupings.put(Character.valueOf(Character.toLowerCase(c)), Character.valueOf(c));
            allPlusNumberGroupings.put(Character.valueOf(c), Character.valueOf(c));
        }
        allPlusNumberGroupings.putAll(asciiDigitMappings);
        allPlusNumberGroupings.put(Character.valueOf('-'), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(65293), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8208), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8209), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8210), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8211), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8212), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8213), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf(8722), Character.valueOf('-'));
        allPlusNumberGroupings.put(Character.valueOf('/'), Character.valueOf('/'));
        allPlusNumberGroupings.put(Character.valueOf(65295), Character.valueOf('/'));
        allPlusNumberGroupings.put(Character.valueOf(' '), Character.valueOf(' '));
        allPlusNumberGroupings.put(Character.valueOf(12288), Character.valueOf(' '));
        allPlusNumberGroupings.put(Character.valueOf(8288), Character.valueOf(' '));
        allPlusNumberGroupings.put(Character.valueOf('.'), Character.valueOf('.'));
        allPlusNumberGroupings.put(Character.valueOf(65294), Character.valueOf('.'));
        ALL_PLUS_NUMBER_GROUPING_SYMBOLS = Collections.unmodifiableMap(allPlusNumberGroupings);
        String singleExtnSymbolsForMatching = "xｘ#＃~～";
        EXTN_PATTERNS_FOR_PARSING = createExtnPattern("," + singleExtnSymbolsForMatching);
        EXTN_PATTERNS_FOR_MATCHING = createExtnPattern(singleExtnSymbolsForMatching);
    }

    private static String createExtnPattern(String singleExtnSymbols) {
        return ";ext=(\\p{Nd}{1,7})|[  \\t,]*(?:e?xt(?:ensi(?:ó?|ó))?n?|ｅ?ｘｔｎ?|[" + singleExtnSymbols + "]|int|anexo|ｉｎｔ)" + "[:\\.．]?[  \\t,-]*" + "(\\p{Nd}{1,7})" + "#?|" + "[- ]+(" + "\\p{Nd}" + "{1,5})#";
    }

    PhoneNumberUtil(MetadataSource metadataSource2, Map<Integer, List<String>> countryCallingCodeToRegionCodeMap2) {
        this.metadataSource = metadataSource2;
        this.countryCallingCodeToRegionCodeMap = countryCallingCodeToRegionCodeMap2;
        for (Entry<Integer, List<String>> entry : countryCallingCodeToRegionCodeMap2.entrySet()) {
            List<String> regionCodes = (List) entry.getValue();
            if (regionCodes.size() != 1 || !"001".equals(regionCodes.get(0))) {
                this.supportedRegions.addAll(regionCodes);
            } else {
                this.countryCodesForNonGeographicalRegion.add(entry.getKey());
            }
        }
        if (this.supportedRegions.remove("001")) {
            logger.log(Level.WARNING, "invalid metadata (country calling code was mapped to the non-geo entity as well as specific region(s))");
        }
        this.nanpaRegions.addAll((Collection) countryCallingCodeToRegionCodeMap2.get(Integer.valueOf(1)));
    }

    static String extractPossibleNumber(String number) {
        Matcher m = VALID_START_CHAR_PATTERN.matcher(number);
        if (!m.find()) {
            return "";
        }
        String number2 = number.substring(m.start());
        Matcher trailingCharsMatcher = UNWANTED_END_CHAR_PATTERN.matcher(number2);
        if (trailingCharsMatcher.find()) {
            number2 = number2.substring(0, trailingCharsMatcher.start());
            logger.log(Level.FINER, "Stripped trailing characters: " + number2);
        }
        Matcher secondNumber = SECOND_NUMBER_START_PATTERN.matcher(number2);
        if (secondNumber.find()) {
            return number2.substring(0, secondNumber.start());
        }
        return number2;
    }

    static boolean isViablePhoneNumber(String number) {
        if (number.length() < 2) {
            return false;
        }
        return VALID_PHONE_NUMBER_PATTERN.matcher(number).matches();
    }

    static String normalize(String number) {
        if (VALID_ALPHA_PHONE_PATTERN.matcher(number).matches()) {
            return normalizeHelper(number, ALPHA_PHONE_MAPPINGS, true);
        }
        return normalizeDigitsOnly(number);
    }

    static void normalize(StringBuilder number) {
        number.replace(0, number.length(), normalize(number.toString()));
    }

    public static String normalizeDigitsOnly(String number) {
        return normalizeDigits(number, false).toString();
    }

    static StringBuilder normalizeDigits(String number, boolean keepNonDigits) {
        char[] charArray;
        StringBuilder normalizedDigits = new StringBuilder(number.length());
        for (char c : number.toCharArray()) {
            int digit = Character.digit(c, 10);
            if (digit != -1) {
                normalizedDigits.append(digit);
            } else if (keepNonDigits) {
                normalizedDigits.append(c);
            }
        }
        return normalizedDigits;
    }

    private static String normalizeHelper(String number, Map<Character, Character> normalizationReplacements, boolean removeNonMatches) {
        StringBuilder normalizedNumber = new StringBuilder(number.length());
        for (int i = 0; i < number.length(); i++) {
            char character = number.charAt(i);
            Character newDigit = (Character) normalizationReplacements.get(Character.valueOf(Character.toUpperCase(character)));
            if (newDigit != null) {
                normalizedNumber.append(newDigit);
            } else if (!removeNonMatches) {
                normalizedNumber.append(character);
            }
        }
        return normalizedNumber.toString();
    }

    static synchronized void setInstance(PhoneNumberUtil util) {
        synchronized (PhoneNumberUtil.class) {
            instance = util;
        }
    }

    public static synchronized PhoneNumberUtil getInstance() {
        PhoneNumberUtil phoneNumberUtil;
        synchronized (PhoneNumberUtil.class) {
            if (instance == null) {
                setInstance(createInstance(DEFAULT_METADATA_LOADER));
            }
            phoneNumberUtil = instance;
        }
        return phoneNumberUtil;
    }

    public static PhoneNumberUtil createInstance(MetadataSource metadataSource2) {
        if (metadataSource2 != null) {
            return new PhoneNumberUtil(metadataSource2, CountryCodeToRegionCodeMap.getCountryCodeToRegionCodeMap());
        }
        throw new IllegalArgumentException("metadataSource could not be null.");
    }

    public static PhoneNumberUtil createInstance(MetadataLoader metadataLoader) {
        if (metadataLoader != null) {
            return createInstance((MetadataSource) new MultiFileMetadataSourceImpl(metadataLoader));
        }
        throw new IllegalArgumentException("metadataLoader could not be null.");
    }

    private boolean isValidRegionCode(String regionCode) {
        return regionCode != null && this.supportedRegions.contains(regionCode);
    }

    private boolean hasValidCountryCallingCode(int countryCallingCode) {
        return this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(countryCallingCode));
    }

    public String format(PhoneNumber number, PhoneNumberFormat numberFormat) {
        if (number.getNationalNumber() == 0 && number.hasRawInput()) {
            String rawInput = number.getRawInput();
            if (rawInput.length() > 0) {
                return rawInput;
            }
        }
        StringBuilder formattedNumber = new StringBuilder(20);
        format(number, numberFormat, formattedNumber);
        return formattedNumber.toString();
    }

    public void format(PhoneNumber number, PhoneNumberFormat numberFormat, StringBuilder formattedNumber) {
        formattedNumber.setLength(0);
        int countryCallingCode = number.getCountryCode();
        String nationalSignificantNumber = getNationalSignificantNumber(number);
        if (numberFormat == PhoneNumberFormat.E164) {
            formattedNumber.append(nationalSignificantNumber);
            prefixNumberWithCountryCallingCode(countryCallingCode, PhoneNumberFormat.E164, formattedNumber);
        } else if (!hasValidCountryCallingCode(countryCallingCode)) {
            formattedNumber.append(nationalSignificantNumber);
        } else {
            PhoneMetadata metadata = getMetadataForRegionOrCallingCode(countryCallingCode, getRegionCodeForCountryCode(countryCallingCode));
            formattedNumber.append(formatNsn(nationalSignificantNumber, metadata, numberFormat));
            maybeAppendFormattedExtension(number, metadata, numberFormat, formattedNumber);
            prefixNumberWithCountryCallingCode(countryCallingCode, numberFormat, formattedNumber);
        }
    }

    private PhoneMetadata getMetadataForRegionOrCallingCode(int countryCallingCode, String regionCode) {
        if ("001".equals(regionCode)) {
            return getMetadataForNonGeographicalRegion(countryCallingCode);
        }
        return getMetadataForRegion(regionCode);
    }

    public String getNationalSignificantNumber(PhoneNumber number) {
        StringBuilder nationalNumber = new StringBuilder();
        if (number.isItalianLeadingZero()) {
            char[] zeros = new char[number.getNumberOfLeadingZeros()];
            Arrays.fill(zeros, '0');
            nationalNumber.append(new String(zeros));
        }
        nationalNumber.append(number.getNationalNumber());
        return nationalNumber.toString();
    }

    private void prefixNumberWithCountryCallingCode(int countryCallingCode, PhoneNumberFormat numberFormat, StringBuilder formattedNumber) {
        switch (numberFormat) {
            case E164:
                formattedNumber.insert(0, countryCallingCode).insert(0, '+');
                return;
            case INTERNATIONAL:
                formattedNumber.insert(0, " ").insert(0, countryCallingCode).insert(0, '+');
                return;
            case RFC3966:
                formattedNumber.insert(0, "-").insert(0, countryCallingCode).insert(0, '+').insert(0, "tel:");
                return;
            default:
                return;
        }
    }

    private String formatNsn(String number, PhoneMetadata metadata, PhoneNumberFormat numberFormat) {
        return formatNsn(number, metadata, numberFormat, null);
    }

    private String formatNsn(String number, PhoneMetadata metadata, PhoneNumberFormat numberFormat, String carrierCode) {
        List<NumberFormat> availableFormats;
        if (metadata.intlNumberFormats().size() == 0 || numberFormat == PhoneNumberFormat.NATIONAL) {
            availableFormats = metadata.numberFormats();
        } else {
            availableFormats = metadata.intlNumberFormats();
        }
        NumberFormat formattingPattern = chooseFormattingPatternForNumber(availableFormats, number);
        return formattingPattern == null ? number : formatNsnUsingPattern(number, formattingPattern, numberFormat, carrierCode);
    }

    /* access modifiers changed from: 0000 */
    public NumberFormat chooseFormattingPatternForNumber(List<NumberFormat> availableFormats, String nationalNumber) {
        for (NumberFormat numFormat : availableFormats) {
            int size = numFormat.leadingDigitsPatternSize();
            if ((size == 0 || this.regexCache.getPatternForRegex(numFormat.getLeadingDigitsPattern(size - 1)).matcher(nationalNumber).lookingAt()) && this.regexCache.getPatternForRegex(numFormat.getPattern()).matcher(nationalNumber).matches()) {
                return numFormat;
            }
        }
        return null;
    }

    private String formatNsnUsingPattern(String nationalNumber, NumberFormat formattingPattern, PhoneNumberFormat numberFormat, String carrierCode) {
        String formattedNationalNumber;
        String numberFormatRule = formattingPattern.getFormat();
        Matcher m = this.regexCache.getPatternForRegex(formattingPattern.getPattern()).matcher(nationalNumber);
        String str = "";
        if (numberFormat != PhoneNumberFormat.NATIONAL || carrierCode == null || carrierCode.length() <= 0 || formattingPattern.getDomesticCarrierCodeFormattingRule().length() <= 0) {
            String nationalPrefixFormattingRule = formattingPattern.getNationalPrefixFormattingRule();
            if (numberFormat != PhoneNumberFormat.NATIONAL || nationalPrefixFormattingRule == null || nationalPrefixFormattingRule.length() <= 0) {
                formattedNationalNumber = m.replaceAll(numberFormatRule);
            } else {
                formattedNationalNumber = m.replaceAll(FIRST_GROUP_PATTERN.matcher(numberFormatRule).replaceFirst(nationalPrefixFormattingRule));
            }
        } else {
            formattedNationalNumber = m.replaceAll(FIRST_GROUP_PATTERN.matcher(numberFormatRule).replaceFirst(CC_PATTERN.matcher(formattingPattern.getDomesticCarrierCodeFormattingRule()).replaceFirst(carrierCode)));
        }
        if (numberFormat != PhoneNumberFormat.RFC3966) {
            return formattedNationalNumber;
        }
        Matcher matcher = SEPARATOR_PATTERN.matcher(formattedNationalNumber);
        if (matcher.lookingAt()) {
            formattedNationalNumber = matcher.replaceFirst("");
        }
        return matcher.reset(formattedNationalNumber).replaceAll("-");
    }

    public PhoneNumber getExampleNumber(String regionCode) {
        return getExampleNumberForType(regionCode, PhoneNumberType.FIXED_LINE);
    }

    public PhoneNumber getExampleNumberForType(String regionCode, PhoneNumberType type) {
        if (!isValidRegionCode(regionCode)) {
            logger.log(Level.WARNING, "Invalid or unknown region code provided: " + regionCode);
            return null;
        }
        PhoneNumberDesc desc = getNumberDescByType(getMetadataForRegion(regionCode), type);
        try {
            if (desc.hasExampleNumber()) {
                return parse(desc.getExampleNumber(), regionCode);
            }
            return null;
        } catch (NumberParseException e) {
            logger.log(Level.SEVERE, e.toString());
            return null;
        }
    }

    private void maybeAppendFormattedExtension(PhoneNumber number, PhoneMetadata metadata, PhoneNumberFormat numberFormat, StringBuilder formattedNumber) {
        if (number.hasExtension() && number.getExtension().length() > 0) {
            if (numberFormat == PhoneNumberFormat.RFC3966) {
                formattedNumber.append(";ext=").append(number.getExtension());
            } else if (metadata.hasPreferredExtnPrefix()) {
                formattedNumber.append(metadata.getPreferredExtnPrefix()).append(number.getExtension());
            } else {
                formattedNumber.append(" ext. ").append(number.getExtension());
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public PhoneNumberDesc getNumberDescByType(PhoneMetadata metadata, PhoneNumberType type) {
        switch (type) {
            case PREMIUM_RATE:
                return metadata.getPremiumRate();
            case TOLL_FREE:
                return metadata.getTollFree();
            case MOBILE:
                return metadata.getMobile();
            case FIXED_LINE:
            case FIXED_LINE_OR_MOBILE:
                return metadata.getFixedLine();
            case SHARED_COST:
                return metadata.getSharedCost();
            case VOIP:
                return metadata.getVoip();
            case PERSONAL_NUMBER:
                return metadata.getPersonalNumber();
            case PAGER:
                return metadata.getPager();
            case UAN:
                return metadata.getUan();
            case VOICEMAIL:
                return metadata.getVoicemail();
            default:
                return metadata.getGeneralDesc();
        }
    }

    private PhoneNumberType getNumberTypeHelper(String nationalNumber, PhoneMetadata metadata) {
        if (!isNumberMatchingDesc(nationalNumber, metadata.getGeneralDesc())) {
            return PhoneNumberType.UNKNOWN;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getPremiumRate())) {
            return PhoneNumberType.PREMIUM_RATE;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getTollFree())) {
            return PhoneNumberType.TOLL_FREE;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getSharedCost())) {
            return PhoneNumberType.SHARED_COST;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getVoip())) {
            return PhoneNumberType.VOIP;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getPersonalNumber())) {
            return PhoneNumberType.PERSONAL_NUMBER;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getPager())) {
            return PhoneNumberType.PAGER;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getUan())) {
            return PhoneNumberType.UAN;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getVoicemail())) {
            return PhoneNumberType.VOICEMAIL;
        }
        if (isNumberMatchingDesc(nationalNumber, metadata.getFixedLine())) {
            if (metadata.isSameMobileAndFixedLinePattern()) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            if (isNumberMatchingDesc(nationalNumber, metadata.getMobile())) {
                return PhoneNumberType.FIXED_LINE_OR_MOBILE;
            }
            return PhoneNumberType.FIXED_LINE;
        } else if (metadata.isSameMobileAndFixedLinePattern() || !isNumberMatchingDesc(nationalNumber, metadata.getMobile())) {
            return PhoneNumberType.UNKNOWN;
        } else {
            return PhoneNumberType.MOBILE;
        }
    }

    /* access modifiers changed from: 0000 */
    public PhoneMetadata getMetadataForRegion(String regionCode) {
        if (!isValidRegionCode(regionCode)) {
            return null;
        }
        return this.metadataSource.getMetadataForRegion(regionCode);
    }

    /* access modifiers changed from: 0000 */
    public PhoneMetadata getMetadataForNonGeographicalRegion(int countryCallingCode) {
        if (!this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(countryCallingCode))) {
            return null;
        }
        return this.metadataSource.getMetadataForNonGeographicalRegion(countryCallingCode);
    }

    /* access modifiers changed from: 0000 */
    public boolean isNumberPossibleForDesc(String nationalNumber, PhoneNumberDesc numberDesc) {
        return this.regexCache.getPatternForRegex(numberDesc.getPossibleNumberPattern()).matcher(nationalNumber).matches();
    }

    /* access modifiers changed from: 0000 */
    public boolean isNumberMatchingDesc(String nationalNumber, PhoneNumberDesc numberDesc) {
        return isNumberPossibleForDesc(nationalNumber, numberDesc) && this.regexCache.getPatternForRegex(numberDesc.getNationalNumberPattern()).matcher(nationalNumber).matches();
    }

    public boolean isValidNumber(PhoneNumber number) {
        return isValidNumberForRegion(number, getRegionCodeForNumber(number));
    }

    public boolean isValidNumberForRegion(PhoneNumber number, String regionCode) {
        int countryCode = number.getCountryCode();
        PhoneMetadata metadata = getMetadataForRegionOrCallingCode(countryCode, regionCode);
        if (metadata == null) {
            return false;
        }
        if (("001".equals(regionCode) || countryCode == getCountryCodeForValidRegion(regionCode)) && getNumberTypeHelper(getNationalSignificantNumber(number), metadata) != PhoneNumberType.UNKNOWN) {
            return true;
        }
        return false;
    }

    public String getRegionCodeForNumber(PhoneNumber number) {
        int countryCode = number.getCountryCode();
        List<String> regions = (List) this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(countryCode));
        if (regions == null) {
            logger.log(Level.INFO, "Missing/invalid country_code (" + countryCode + ") for number " + getNationalSignificantNumber(number));
            return null;
        } else if (regions.size() == 1) {
            return (String) regions.get(0);
        } else {
            return getRegionCodeForNumberFromRegionList(number, regions);
        }
    }

    private String getRegionCodeForNumberFromRegionList(PhoneNumber number, List<String> regionCodes) {
        String nationalNumber = getNationalSignificantNumber(number);
        for (String regionCode : regionCodes) {
            PhoneMetadata metadata = getMetadataForRegion(regionCode);
            if (metadata.hasLeadingDigits()) {
                if (this.regexCache.getPatternForRegex(metadata.getLeadingDigits()).matcher(nationalNumber).lookingAt()) {
                    return regionCode;
                }
            } else if (getNumberTypeHelper(nationalNumber, metadata) != PhoneNumberType.UNKNOWN) {
                return regionCode;
            }
        }
        return null;
    }

    public String getRegionCodeForCountryCode(int countryCallingCode) {
        List<String> regionCodes = (List) this.countryCallingCodeToRegionCodeMap.get(Integer.valueOf(countryCallingCode));
        return regionCodes == null ? "ZZ" : (String) regionCodes.get(0);
    }

    private int getCountryCodeForValidRegion(String regionCode) {
        PhoneMetadata metadata = getMetadataForRegion(regionCode);
        if (metadata != null) {
            return metadata.getCountryCode();
        }
        throw new IllegalArgumentException("Invalid region code: " + regionCode);
    }

    private ValidationResult testNumberLengthAgainstPattern(Pattern numberPattern, String number) {
        Matcher numberMatcher = numberPattern.matcher(number);
        if (numberMatcher.matches()) {
            return ValidationResult.IS_POSSIBLE;
        }
        if (numberMatcher.lookingAt()) {
            return ValidationResult.TOO_LONG;
        }
        return ValidationResult.TOO_SHORT;
    }

    private boolean isShorterThanPossibleNormalNumber(PhoneMetadata regionMetadata, String number) {
        return testNumberLengthAgainstPattern(this.regexCache.getPatternForRegex(regionMetadata.getGeneralDesc().getPossibleNumberPattern()), number) == ValidationResult.TOO_SHORT;
    }

    /* access modifiers changed from: 0000 */
    public int extractCountryCode(StringBuilder fullNumber, StringBuilder nationalNumber) {
        if (fullNumber.length() == 0 || fullNumber.charAt(0) == '0') {
            return 0;
        }
        int numberLength = fullNumber.length();
        int i = 1;
        while (i <= 3 && i <= numberLength) {
            int potentialCountryCode = Integer.parseInt(fullNumber.substring(0, i));
            if (this.countryCallingCodeToRegionCodeMap.containsKey(Integer.valueOf(potentialCountryCode))) {
                nationalNumber.append(fullNumber.substring(i));
                return potentialCountryCode;
            }
            i++;
        }
        return 0;
    }

    /* access modifiers changed from: 0000 */
    public int maybeExtractCountryCode(String number, PhoneMetadata defaultRegionMetadata, StringBuilder nationalNumber, boolean keepRawInput, PhoneNumber phoneNumber) throws NumberParseException {
        if (number.length() == 0) {
            return 0;
        }
        StringBuilder fullNumber = new StringBuilder(number);
        String possibleCountryIddPrefix = "NonMatch";
        if (defaultRegionMetadata != null) {
            possibleCountryIddPrefix = defaultRegionMetadata.getInternationalPrefix();
        }
        CountryCodeSource countryCodeSource = maybeStripInternationalPrefixAndNormalize(fullNumber, possibleCountryIddPrefix);
        if (keepRawInput) {
            phoneNumber.setCountryCodeSource(countryCodeSource);
        }
        if (countryCodeSource == CountryCodeSource.FROM_DEFAULT_COUNTRY) {
            if (defaultRegionMetadata != null) {
                int defaultCountryCode = defaultRegionMetadata.getCountryCode();
                String defaultCountryCodeString = String.valueOf(defaultCountryCode);
                String normalizedNumber = fullNumber.toString();
                if (normalizedNumber.startsWith(defaultCountryCodeString)) {
                    StringBuilder potentialNationalNumber = new StringBuilder(normalizedNumber.substring(defaultCountryCodeString.length()));
                    PhoneNumberDesc generalDesc = defaultRegionMetadata.getGeneralDesc();
                    Pattern validNumberPattern = this.regexCache.getPatternForRegex(generalDesc.getNationalNumberPattern());
                    maybeStripNationalPrefixAndCarrierCode(potentialNationalNumber, defaultRegionMetadata, null);
                    Pattern possibleNumberPattern = this.regexCache.getPatternForRegex(generalDesc.getPossibleNumberPattern());
                    if ((!validNumberPattern.matcher(fullNumber).matches() && validNumberPattern.matcher(potentialNationalNumber).matches()) || testNumberLengthAgainstPattern(possibleNumberPattern, fullNumber.toString()) == ValidationResult.TOO_LONG) {
                        nationalNumber.append(potentialNationalNumber);
                        if (keepRawInput) {
                            phoneNumber.setCountryCodeSource(CountryCodeSource.FROM_NUMBER_WITHOUT_PLUS_SIGN);
                        }
                        phoneNumber.setCountryCode(defaultCountryCode);
                        return defaultCountryCode;
                    }
                }
            }
            phoneNumber.setCountryCode(0);
            return 0;
        } else if (fullNumber.length() <= 2) {
            throw new NumberParseException(ErrorType.TOO_SHORT_AFTER_IDD, "Phone number had an IDD, but after this was not long enough to be a viable phone number.");
        } else {
            int potentialCountryCode = extractCountryCode(fullNumber, nationalNumber);
            if (potentialCountryCode != 0) {
                phoneNumber.setCountryCode(potentialCountryCode);
                return potentialCountryCode;
            }
            throw new NumberParseException(ErrorType.INVALID_COUNTRY_CODE, "Country calling code supplied was not recognised.");
        }
    }

    private boolean parsePrefixAsIdd(Pattern iddPattern, StringBuilder number) {
        Matcher m = iddPattern.matcher(number);
        if (!m.lookingAt()) {
            return false;
        }
        int matchEnd = m.end();
        Matcher digitMatcher = CAPTURING_DIGIT_PATTERN.matcher(number.substring(matchEnd));
        if (digitMatcher.find() && normalizeDigitsOnly(digitMatcher.group(1)).equals(AppEventsConstants.EVENT_PARAM_VALUE_NO)) {
            return false;
        }
        number.delete(0, matchEnd);
        return true;
    }

    /* access modifiers changed from: 0000 */
    public CountryCodeSource maybeStripInternationalPrefixAndNormalize(StringBuilder number, String possibleIddPrefix) {
        if (number.length() == 0) {
            return CountryCodeSource.FROM_DEFAULT_COUNTRY;
        }
        Matcher m = PLUS_CHARS_PATTERN.matcher(number);
        if (m.lookingAt()) {
            number.delete(0, m.end());
            normalize(number);
            return CountryCodeSource.FROM_NUMBER_WITH_PLUS_SIGN;
        }
        Pattern iddPattern = this.regexCache.getPatternForRegex(possibleIddPrefix);
        normalize(number);
        return parsePrefixAsIdd(iddPattern, number) ? CountryCodeSource.FROM_NUMBER_WITH_IDD : CountryCodeSource.FROM_DEFAULT_COUNTRY;
    }

    /* access modifiers changed from: 0000 */
    public boolean maybeStripNationalPrefixAndCarrierCode(StringBuilder number, PhoneMetadata metadata, StringBuilder carrierCode) {
        int numberLength = number.length();
        String possibleNationalPrefix = metadata.getNationalPrefixForParsing();
        if (numberLength == 0 || possibleNationalPrefix.length() == 0) {
            return false;
        }
        Matcher prefixMatcher = this.regexCache.getPatternForRegex(possibleNationalPrefix).matcher(number);
        if (!prefixMatcher.lookingAt()) {
            return false;
        }
        Pattern nationalNumberRule = this.regexCache.getPatternForRegex(metadata.getGeneralDesc().getNationalNumberPattern());
        boolean isViableOriginalNumber = nationalNumberRule.matcher(number).matches();
        int numOfGroups = prefixMatcher.groupCount();
        String transformRule = metadata.getNationalPrefixTransformRule();
        if (transformRule != null && transformRule.length() != 0 && prefixMatcher.group(numOfGroups) != null) {
            StringBuilder transformedNumber = new StringBuilder(number);
            transformedNumber.replace(0, numberLength, prefixMatcher.replaceFirst(transformRule));
            if (isViableOriginalNumber && !nationalNumberRule.matcher(transformedNumber.toString()).matches()) {
                return false;
            }
            if (carrierCode != null && numOfGroups > 1) {
                carrierCode.append(prefixMatcher.group(1));
            }
            number.replace(0, number.length(), transformedNumber.toString());
            return true;
        } else if (isViableOriginalNumber && !nationalNumberRule.matcher(number.substring(prefixMatcher.end())).matches()) {
            return false;
        } else {
            if (!(carrierCode == null || numOfGroups <= 0 || prefixMatcher.group(numOfGroups) == null)) {
                carrierCode.append(prefixMatcher.group(1));
            }
            number.delete(0, prefixMatcher.end());
            return true;
        }
    }

    /* access modifiers changed from: 0000 */
    public String maybeStripExtension(StringBuilder number) {
        Matcher m = EXTN_PATTERN.matcher(number);
        if (m.find() && isViablePhoneNumber(number.substring(0, m.start()))) {
            int length = m.groupCount();
            for (int i = 1; i <= length; i++) {
                if (m.group(i) != null) {
                    String extension = m.group(i);
                    number.delete(m.start(), number.length());
                    return extension;
                }
            }
        }
        return "";
    }

    private boolean checkRegionForParsing(String numberToParse, String defaultRegion) {
        if (isValidRegionCode(defaultRegion) || (numberToParse != null && numberToParse.length() != 0 && PLUS_CHARS_PATTERN.matcher(numberToParse).lookingAt())) {
            return true;
        }
        return false;
    }

    public PhoneNumber parse(String numberToParse, String defaultRegion) throws NumberParseException {
        PhoneNumber phoneNumber = new PhoneNumber();
        parse(numberToParse, defaultRegion, phoneNumber);
        return phoneNumber;
    }

    public void parse(String numberToParse, String defaultRegion, PhoneNumber phoneNumber) throws NumberParseException {
        parseHelper(numberToParse, defaultRegion, false, true, phoneNumber);
    }

    static void setItalianLeadingZerosForPhoneNumber(String nationalNumber, PhoneNumber phoneNumber) {
        if (nationalNumber.length() > 1 && nationalNumber.charAt(0) == '0') {
            phoneNumber.setItalianLeadingZero(true);
            int numberOfLeadingZeros = 1;
            while (numberOfLeadingZeros < nationalNumber.length() - 1 && nationalNumber.charAt(numberOfLeadingZeros) == '0') {
                numberOfLeadingZeros++;
            }
            if (numberOfLeadingZeros != 1) {
                phoneNumber.setNumberOfLeadingZeros(numberOfLeadingZeros);
            }
        }
    }

    private void parseHelper(String numberToParse, String defaultRegion, boolean keepRawInput, boolean checkRegion, PhoneNumber phoneNumber) throws NumberParseException {
        int countryCode;
        if (numberToParse == null) {
            throw new NumberParseException(ErrorType.NOT_A_NUMBER, "The phone number supplied was null.");
        } else if (numberToParse.length() > 250) {
            throw new NumberParseException(ErrorType.TOO_LONG, "The string supplied was too long to parse.");
        } else {
            StringBuilder nationalNumber = new StringBuilder();
            buildNationalNumberForParsing(numberToParse, nationalNumber);
            if (!isViablePhoneNumber(nationalNumber.toString())) {
                throw new NumberParseException(ErrorType.NOT_A_NUMBER, "The string supplied did not seem to be a phone number.");
            }
            if (checkRegion) {
                if (!checkRegionForParsing(nationalNumber.toString(), defaultRegion)) {
                    throw new NumberParseException(ErrorType.INVALID_COUNTRY_CODE, "Missing or invalid default region.");
                }
            }
            if (keepRawInput) {
                phoneNumber.setRawInput(numberToParse);
            }
            String extension = maybeStripExtension(nationalNumber);
            if (extension.length() > 0) {
                phoneNumber.setExtension(extension);
            }
            PhoneMetadata regionMetadata = getMetadataForRegion(defaultRegion);
            StringBuilder normalizedNationalNumber = new StringBuilder();
            try {
                countryCode = maybeExtractCountryCode(nationalNumber.toString(), regionMetadata, normalizedNationalNumber, keepRawInput, phoneNumber);
            } catch (NumberParseException e) {
                Matcher matcher = PLUS_CHARS_PATTERN.matcher(nationalNumber.toString());
                if (e.getErrorType() != ErrorType.INVALID_COUNTRY_CODE || !matcher.lookingAt()) {
                    throw new NumberParseException(e.getErrorType(), e.getMessage());
                }
                countryCode = maybeExtractCountryCode(nationalNumber.substring(matcher.end()), regionMetadata, normalizedNationalNumber, keepRawInput, phoneNumber);
                if (countryCode == 0) {
                    throw new NumberParseException(ErrorType.INVALID_COUNTRY_CODE, "Could not interpret numbers after plus-sign.");
                }
            }
            if (countryCode != 0) {
                String phoneNumberRegion = getRegionCodeForCountryCode(countryCode);
                if (!phoneNumberRegion.equals(defaultRegion)) {
                    regionMetadata = getMetadataForRegionOrCallingCode(countryCode, phoneNumberRegion);
                }
            } else {
                normalize(nationalNumber);
                normalizedNationalNumber.append(nationalNumber);
                if (defaultRegion != null) {
                    phoneNumber.setCountryCode(regionMetadata.getCountryCode());
                } else if (keepRawInput) {
                    phoneNumber.clearCountryCodeSource();
                }
            }
            if (normalizedNationalNumber.length() < 2) {
                throw new NumberParseException(ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
            }
            if (regionMetadata != null) {
                StringBuilder carrierCode = new StringBuilder();
                StringBuilder sb = new StringBuilder(normalizedNationalNumber);
                maybeStripNationalPrefixAndCarrierCode(sb, regionMetadata, carrierCode);
                if (!isShorterThanPossibleNormalNumber(regionMetadata, sb.toString())) {
                    normalizedNationalNumber = sb;
                    if (keepRawInput) {
                        phoneNumber.setPreferredDomesticCarrierCode(carrierCode.toString());
                    }
                }
            }
            int lengthOfNationalNumber = normalizedNationalNumber.length();
            if (lengthOfNationalNumber < 2) {
                throw new NumberParseException(ErrorType.TOO_SHORT_NSN, "The string supplied is too short to be a phone number.");
            } else if (lengthOfNationalNumber > 17) {
                throw new NumberParseException(ErrorType.TOO_LONG, "The string supplied is too long to be a phone number.");
            } else {
                setItalianLeadingZerosForPhoneNumber(normalizedNationalNumber.toString(), phoneNumber);
                phoneNumber.setNationalNumber(Long.parseLong(normalizedNationalNumber.toString()));
            }
        }
    }

    private void buildNationalNumberForParsing(String numberToParse, StringBuilder nationalNumber) {
        int indexOfPhoneContext = numberToParse.indexOf(";phone-context=");
        if (indexOfPhoneContext > 0) {
            int phoneContextStart = indexOfPhoneContext + ";phone-context=".length();
            if (numberToParse.charAt(phoneContextStart) == '+') {
                int phoneContextEnd = numberToParse.indexOf(59, phoneContextStart);
                if (phoneContextEnd > 0) {
                    nationalNumber.append(numberToParse.substring(phoneContextStart, phoneContextEnd));
                } else {
                    nationalNumber.append(numberToParse.substring(phoneContextStart));
                }
            }
            int indexOfRfc3966Prefix = numberToParse.indexOf("tel:");
            nationalNumber.append(numberToParse.substring(indexOfRfc3966Prefix >= 0 ? indexOfRfc3966Prefix + "tel:".length() : 0, indexOfPhoneContext));
        } else {
            nationalNumber.append(extractPossibleNumber(numberToParse));
        }
        int indexOfIsdn = nationalNumber.indexOf(";isub=");
        if (indexOfIsdn > 0) {
            nationalNumber.delete(indexOfIsdn, nationalNumber.length());
        }
    }
}
