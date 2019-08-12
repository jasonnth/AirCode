package org.joda.time.format;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.jmrtd.lds.LDSFile;
import org.joda.time.DateTime;
import org.spongycastle.asn1.eac.EACTags;

public class DateTimeFormat {
    private static final ConcurrentHashMap<String, DateTimeFormatter> cPatternCache = new ConcurrentHashMap<>();
    private static final AtomicReferenceArray<DateTimeFormatter> cStyleCache = new AtomicReferenceArray<>(25);

    public static DateTimeFormatter forPattern(String str) {
        return createFormatterForPattern(str);
    }

    private static void parsePatternTo(DateTimeFormatterBuilder dateTimeFormatterBuilder, String str) {
        int length = str.length();
        int[] iArr = new int[1];
        int i = 0;
        while (i < length) {
            iArr[0] = i;
            String parseToken = parseToken(str, iArr);
            int i2 = iArr[0];
            int length2 = parseToken.length();
            if (length2 != 0) {
                char charAt = parseToken.charAt(0);
                switch (charAt) {
                    case '\'':
                        String substring = parseToken.substring(1);
                        if (substring.length() != 1) {
                            dateTimeFormatterBuilder.appendLiteral(new String(substring));
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendLiteral(substring.charAt(0));
                            break;
                        }
                    case 'C':
                        dateTimeFormatterBuilder.appendCenturyOfEra(length2, length2);
                        break;
                    case 'D':
                        dateTimeFormatterBuilder.appendDayOfYear(length2);
                        break;
                    case 'E':
                        if (length2 < 4) {
                            dateTimeFormatterBuilder.appendDayOfWeekShortText();
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendDayOfWeekText();
                            break;
                        }
                    case 'G':
                        dateTimeFormatterBuilder.appendEraText();
                        break;
                    case 'H':
                        dateTimeFormatterBuilder.appendHourOfDay(length2);
                        break;
                    case 'K':
                        dateTimeFormatterBuilder.appendHourOfHalfday(length2);
                        break;
                    case 'M':
                        if (length2 >= 3) {
                            if (length2 < 4) {
                                dateTimeFormatterBuilder.appendMonthOfYearShortText();
                                break;
                            } else {
                                dateTimeFormatterBuilder.appendMonthOfYearText();
                                break;
                            }
                        } else {
                            dateTimeFormatterBuilder.appendMonthOfYear(length2);
                            break;
                        }
                    case 'S':
                        dateTimeFormatterBuilder.appendFractionOfSecond(length2, length2);
                        break;
                    case 'Y':
                    case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                    case EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY /*121*/:
                        if (length2 != 2) {
                            int i3 = 9;
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                if (isNumericToken(parseToken(str, iArr))) {
                                    i3 = length2;
                                }
                                iArr[0] = iArr[0] - 1;
                            }
                            switch (charAt) {
                                case 'Y':
                                    dateTimeFormatterBuilder.appendYearOfEra(length2, i3);
                                    break;
                                case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                                    dateTimeFormatterBuilder.appendWeekyear(length2, i3);
                                    break;
                                case EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY /*121*/:
                                    dateTimeFormatterBuilder.appendYear(length2, i3);
                                    break;
                            }
                        } else {
                            boolean z = true;
                            if (i2 + 1 < length) {
                                iArr[0] = iArr[0] + 1;
                                if (isNumericToken(parseToken(str, iArr))) {
                                    z = false;
                                }
                                iArr[0] = iArr[0] - 1;
                            }
                            switch (charAt) {
                                case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                                    dateTimeFormatterBuilder.appendTwoDigitWeekyear(new DateTime().getWeekyear() - 30, z);
                                    break;
                                default:
                                    dateTimeFormatterBuilder.appendTwoDigitYear(new DateTime().getYear() - 30, z);
                                    break;
                            }
                        }
                    case 'Z':
                        if (length2 != 1) {
                            if (length2 != 2) {
                                dateTimeFormatterBuilder.appendTimeZoneId();
                                break;
                            } else {
                                dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", true, 2, 2);
                                break;
                            }
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneOffset(null, "Z", false, 2, 2);
                            break;
                        }
                    case 'a':
                        dateTimeFormatterBuilder.appendHalfdayOfDayText();
                        break;
                    case 'd':
                        dateTimeFormatterBuilder.appendDayOfMonth(length2);
                        break;
                    case 'e':
                        dateTimeFormatterBuilder.appendDayOfWeek(length2);
                        break;
                    case 'h':
                        dateTimeFormatterBuilder.appendClockhourOfHalfday(length2);
                        break;
                    case 'k':
                        dateTimeFormatterBuilder.appendClockhourOfDay(length2);
                        break;
                    case 'm':
                        dateTimeFormatterBuilder.appendMinuteOfHour(length2);
                        break;
                    case 's':
                        dateTimeFormatterBuilder.appendSecondOfMinute(length2);
                        break;
                    case LDSFile.EF_SOD_TAG /*119*/:
                        dateTimeFormatterBuilder.appendWeekOfWeekyear(length2);
                        break;
                    case EACTags.SECURITY_SUPPORT_TEMPLATE /*122*/:
                        if (length2 < 4) {
                            dateTimeFormatterBuilder.appendTimeZoneShortName(null);
                            break;
                        } else {
                            dateTimeFormatterBuilder.appendTimeZoneName();
                            break;
                        }
                    default:
                        throw new IllegalArgumentException("Illegal pattern component: " + parseToken);
                }
                i = i2 + 1;
            } else {
                return;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0069, code lost:
        r2 = r2 - 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String parseToken(java.lang.String r11, int[] r12) {
        /*
            r10 = 97
            r9 = 90
            r8 = 65
            r7 = 39
            r1 = 0
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            r2 = r12[r1]
            int r4 = r11.length()
            char r0 = r11.charAt(r2)
            if (r0 < r8) goto L_0x001c
            if (r0 <= r9) goto L_0x0022
        L_0x001c:
            if (r0 < r10) goto L_0x0037
            r5 = 122(0x7a, float:1.71E-43)
            if (r0 > r5) goto L_0x0037
        L_0x0022:
            r3.append(r0)
        L_0x0025:
            int r5 = r2 + 1
            if (r5 >= r4) goto L_0x006b
            int r5 = r2 + 1
            char r5 = r11.charAt(r5)
            if (r5 != r0) goto L_0x006b
            r3.append(r0)
            int r2 = r2 + 1
            goto L_0x0025
        L_0x0037:
            r3.append(r7)
            r0 = r1
        L_0x003b:
            if (r2 >= r4) goto L_0x006b
            char r5 = r11.charAt(r2)
            if (r5 != r7) goto L_0x005d
            int r6 = r2 + 1
            if (r6 >= r4) goto L_0x0057
            int r6 = r2 + 1
            char r6 = r11.charAt(r6)
            if (r6 != r7) goto L_0x0057
            int r2 = r2 + 1
            r3.append(r5)
        L_0x0054:
            int r2 = r2 + 1
            goto L_0x003b
        L_0x0057:
            if (r0 != 0) goto L_0x005b
            r0 = 1
            goto L_0x0054
        L_0x005b:
            r0 = r1
            goto L_0x0054
        L_0x005d:
            if (r0 != 0) goto L_0x0072
            if (r5 < r8) goto L_0x0063
            if (r5 <= r9) goto L_0x0069
        L_0x0063:
            if (r5 < r10) goto L_0x0072
            r6 = 122(0x7a, float:1.71E-43)
            if (r5 > r6) goto L_0x0072
        L_0x0069:
            int r2 = r2 + -1
        L_0x006b:
            r12[r1] = r2
            java.lang.String r0 = r3.toString()
            return r0
        L_0x0072:
            r3.append(r5)
            goto L_0x0054
        */
        throw new UnsupportedOperationException("Method not decompiled: org.joda.time.format.DateTimeFormat.parseToken(java.lang.String, int[]):java.lang.String");
    }

    private static boolean isNumericToken(String str) {
        int length = str.length();
        if (length > 0) {
            switch (str.charAt(0)) {
                case 'C':
                case 'D':
                case 'F':
                case 'H':
                case 'K':
                case 'S':
                case 'W':
                case 'Y':
                case 'c':
                case 'd':
                case 'e':
                case 'h':
                case 'k':
                case 'm':
                case 's':
                case LDSFile.EF_SOD_TAG /*119*/:
                case EACTags.COMPATIBLE_TAG_ALLOCATION_AUTHORITY /*120*/:
                case EACTags.COEXISTANT_TAG_ALLOCATION_AUTHORITY /*121*/:
                    return true;
                case 'M':
                    if (length <= 2) {
                        return true;
                    }
                    break;
            }
        }
        return false;
    }

    private static DateTimeFormatter createFormatterForPattern(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid pattern specification");
        }
        DateTimeFormatter dateTimeFormatter = (DateTimeFormatter) cPatternCache.get(str);
        if (dateTimeFormatter != null) {
            return dateTimeFormatter;
        }
        DateTimeFormatterBuilder dateTimeFormatterBuilder = new DateTimeFormatterBuilder();
        parsePatternTo(dateTimeFormatterBuilder, str);
        DateTimeFormatter formatter = dateTimeFormatterBuilder.toFormatter();
        if (cPatternCache.size() < 500) {
            DateTimeFormatter dateTimeFormatter2 = (DateTimeFormatter) cPatternCache.putIfAbsent(str, formatter);
            if (dateTimeFormatter2 != null) {
                return dateTimeFormatter2;
            }
        }
        return formatter;
    }
}
