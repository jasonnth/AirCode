package com.fasterxml.jackson.databind.util;

import com.fasterxml.jackson.core.p307io.NumberInput;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StdDateFormat extends DateFormat {
    protected static final String[] ALL_FORMATS = {"yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", "EEE, dd MMM yyyy HH:mm:ss zzz", "yyyy-MM-dd"};
    protected static final DateFormat DATE_FORMAT_ISO8601 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_ISO8601_Z = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_PLAIN = new SimpleDateFormat("yyyy-MM-dd", DEFAULT_LOCALE);
    protected static final DateFormat DATE_FORMAT_RFC1123 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", DEFAULT_LOCALE);
    private static final Locale DEFAULT_LOCALE = Locale.US;
    private static final TimeZone DEFAULT_TIMEZONE = TimeZone.getTimeZone("UTC");
    public static final StdDateFormat instance = new StdDateFormat();
    protected transient DateFormat _formatISO8601;
    protected transient DateFormat _formatISO8601_z;
    protected transient DateFormat _formatPlain;
    protected transient DateFormat _formatRFC1123;
    protected Boolean _lenient;
    protected final Locale _locale;
    protected transient TimeZone _timezone;

    static {
        DATE_FORMAT_RFC1123.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_ISO8601_Z.setTimeZone(DEFAULT_TIMEZONE);
        DATE_FORMAT_PLAIN.setTimeZone(DEFAULT_TIMEZONE);
    }

    public StdDateFormat() {
        this._locale = DEFAULT_LOCALE;
    }

    protected StdDateFormat(TimeZone tz, Locale loc, Boolean lenient) {
        this._timezone = tz;
        this._locale = loc;
        this._lenient = lenient;
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public StdDateFormat withTimeZone(TimeZone tz) {
        if (tz == null) {
            tz = DEFAULT_TIMEZONE;
        }
        return (tz == this._timezone || tz.equals(this._timezone)) ? this : new StdDateFormat(tz, this._locale, this._lenient);
    }

    /* Debug info: failed to restart local var, previous not found, register: 3 */
    public StdDateFormat withLocale(Locale loc) {
        return loc.equals(this._locale) ? this : new StdDateFormat(this._timezone, loc, this._lenient);
    }

    public StdDateFormat clone() {
        return new StdDateFormat(this._timezone, this._locale, this._lenient);
    }

    public TimeZone getTimeZone() {
        return this._timezone;
    }

    public void setTimeZone(TimeZone tz) {
        if (!tz.equals(this._timezone)) {
            _clearFormats();
            this._timezone = tz;
        }
    }

    public void setLenient(boolean enabled) {
        Boolean newValue = Boolean.valueOf(enabled);
        if (this._lenient != newValue) {
            this._lenient = newValue;
            _clearFormats();
        }
    }

    public boolean isLenient() {
        if (this._lenient == null) {
            return true;
        }
        return this._lenient.booleanValue();
    }

    public Date parse(String dateStr) throws ParseException {
        Date dt;
        String[] arr$;
        String dateStr2 = dateStr.trim();
        ParsePosition pos = new ParsePosition(0);
        if (looksLikeISO8601(dateStr2)) {
            dt = parseAsISO8601(dateStr2, pos, true);
        } else {
            int i = dateStr2.length();
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                char ch = dateStr2.charAt(i);
                if ((ch < '0' || ch > '9') && (i > 0 || ch != '-')) {
                    break;
                }
            }
            if (i >= 0 || (dateStr2.charAt(0) != '-' && !NumberInput.inLongRange(dateStr2, false))) {
                dt = parseAsRFC1123(dateStr2, pos);
            } else {
                dt = new Date(Long.parseLong(dateStr2));
            }
        }
        if (dt != null) {
            return dt;
        }
        StringBuilder sb = new StringBuilder();
        for (String f : ALL_FORMATS) {
            if (sb.length() > 0) {
                sb.append("\", \"");
            } else {
                sb.append('\"');
            }
            sb.append(f);
        }
        sb.append('\"');
        throw new ParseException(String.format("Can not parse date \"%s\": not compatible with any of standard forms (%s)", new Object[]{dateStr2, sb.toString()}), pos.getErrorIndex());
    }

    public Date parse(String dateStr, ParsePosition pos) {
        if (looksLikeISO8601(dateStr)) {
            try {
                return parseAsISO8601(dateStr, pos, false);
            } catch (ParseException e) {
                return null;
            }
        } else {
            int i = dateStr.length();
            while (true) {
                i--;
                if (i < 0) {
                    break;
                }
                char ch = dateStr.charAt(i);
                if ((ch < '0' || ch > '9') && (i > 0 || ch != '-')) {
                    break;
                }
            }
            if (i >= 0 || (dateStr.charAt(0) != '-' && !NumberInput.inLongRange(dateStr, false))) {
                return parseAsRFC1123(dateStr, pos);
            }
            return new Date(Long.parseLong(dateStr));
        }
    }

    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        if (this._formatISO8601 == null) {
            this._formatISO8601 = _cloneFormat(DATE_FORMAT_ISO8601, "yyyy-MM-dd'T'HH:mm:ss.SSSZ", this._timezone, this._locale, this._lenient);
        }
        return this._formatISO8601.format(date, toAppendTo, fieldPosition);
    }

    public String toString() {
        String str = "DateFormat " + getClass().getName();
        TimeZone tz = this._timezone;
        if (tz != null) {
            str = str + " (timezone: " + tz + ")";
        }
        return str + "(locale: " + this._locale + ")";
    }

    public boolean equals(Object o) {
        return o == this;
    }

    public int hashCode() {
        return System.identityHashCode(this);
    }

    /* access modifiers changed from: protected */
    public boolean looksLikeISO8601(String dateStr) {
        if (dateStr.length() < 5 || !Character.isDigit(dateStr.charAt(0)) || !Character.isDigit(dateStr.charAt(3)) || dateStr.charAt(4) != '-') {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0162, code lost:
        r6.append('0');
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0167, code lost:
        r6.append('0');
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Date parseAsISO8601(java.lang.String r14, java.text.ParsePosition r15, boolean r16) throws java.text.ParseException {
        /*
            r13 = this;
            int r4 = r14.length()
            int r8 = r4 + -1
            char r0 = r14.charAt(r8)
            r8 = 10
            if (r4 > r8) goto L_0x004e
            boolean r8 = java.lang.Character.isDigit(r0)
            if (r8 == 0) goto L_0x004e
            java.text.DateFormat r1 = r13._formatPlain
            java.lang.String r3 = "yyyy-MM-dd"
            if (r1 != 0) goto L_0x0029
            java.text.DateFormat r8 = DATE_FORMAT_PLAIN
            java.util.TimeZone r9 = r13._timezone
            java.util.Locale r10 = r13._locale
            java.lang.Boolean r11 = r13._lenient
            java.text.DateFormat r1 = _cloneFormat(r8, r3, r9, r10, r11)
            r13._formatPlain = r1
        L_0x0029:
            java.util.Date r2 = r1.parse(r14, r15)
            if (r2 != 0) goto L_0x016d
            java.text.ParseException r8 = new java.text.ParseException
            java.lang.String r9 = "Can not parse date \"%s\": while it seems to fit format '%s', parsing fails (leniency? %s)"
            r10 = 3
            java.lang.Object[] r10 = new java.lang.Object[r10]
            r11 = 0
            r10[r11] = r14
            r11 = 1
            r10[r11] = r3
            r11 = 2
            java.lang.Boolean r12 = r13._lenient
            r10[r11] = r12
            java.lang.String r9 = java.lang.String.format(r9, r10)
            int r10 = r15.getErrorIndex()
            r8.<init>(r9, r10)
            throw r8
        L_0x004e:
            r8 = 90
            if (r0 != r8) goto L_0x0083
            java.text.DateFormat r1 = r13._formatISO8601_z
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            if (r1 != 0) goto L_0x0067
            java.text.DateFormat r8 = DATE_FORMAT_ISO8601_Z
            java.util.TimeZone r9 = r13._timezone
            java.util.Locale r10 = r13._locale
            java.lang.Boolean r11 = r13._lenient
            java.text.DateFormat r1 = _cloneFormat(r8, r3, r9, r10, r11)
            r13._formatISO8601_z = r1
        L_0x0067:
            int r8 = r4 + -4
            char r8 = r14.charAt(r8)
            r9 = 58
            if (r8 != r9) goto L_0x0029
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r14)
            int r8 = r4 + -1
            java.lang.String r9 = ".000"
            r6.insert(r8, r9)
            java.lang.String r14 = r6.toString()
            goto L_0x0029
        L_0x0083:
            boolean r8 = hasTimeZone(r14)
            if (r8 == 0) goto L_0x0121
            int r8 = r4 + -3
            char r0 = r14.charAt(r8)
            r8 = 58
            if (r0 != r8) goto L_0x00dc
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r14)
            int r8 = r4 + -3
            int r9 = r4 + -2
            r6.delete(r8, r9)
            java.lang.String r14 = r6.toString()
        L_0x00a3:
            int r4 = r14.length()
            r8 = 84
            int r8 = r14.lastIndexOf(r8)
            int r8 = r4 - r8
            int r7 = r8 + -6
            r8 = 12
            if (r7 >= r8) goto L_0x00c3
            int r5 = r4 + -5
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r14)
            switch(r7) {
                case 5: goto L_0x011a;
                case 6: goto L_0x0114;
                case 7: goto L_0x00bf;
                case 8: goto L_0x010d;
                case 9: goto L_0x0106;
                case 10: goto L_0x00ff;
                case 11: goto L_0x00f9;
                default: goto L_0x00bf;
            }
        L_0x00bf:
            java.lang.String r14 = r6.toString()
        L_0x00c3:
            java.text.DateFormat r1 = r13._formatISO8601
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"
            java.text.DateFormat r8 = r13._formatISO8601
            if (r8 != 0) goto L_0x0029
            java.text.DateFormat r8 = DATE_FORMAT_ISO8601
            java.util.TimeZone r9 = r13._timezone
            java.util.Locale r10 = r13._locale
            java.lang.Boolean r11 = r13._lenient
            java.text.DateFormat r1 = _cloneFormat(r8, r3, r9, r10, r11)
            r13._formatISO8601 = r1
            goto L_0x0029
        L_0x00dc:
            r8 = 43
            if (r0 == r8) goto L_0x00e4
            r8 = 45
            if (r0 != r8) goto L_0x00a3
        L_0x00e4:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.StringBuilder r8 = r8.append(r14)
            java.lang.String r9 = "00"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r14 = r8.toString()
            goto L_0x00a3
        L_0x00f9:
            r8 = 48
            r6.insert(r5, r8)
            goto L_0x00bf
        L_0x00ff:
            java.lang.String r8 = "00"
            r6.insert(r5, r8)
            goto L_0x00bf
        L_0x0106:
            java.lang.String r8 = "000"
            r6.insert(r5, r8)
            goto L_0x00bf
        L_0x010d:
            java.lang.String r8 = ".000"
            r6.insert(r5, r8)
            goto L_0x00bf
        L_0x0114:
            java.lang.String r8 = "00.000"
            r6.insert(r5, r8)
        L_0x011a:
            java.lang.String r8 = ":00.000"
            r6.insert(r5, r8)
            goto L_0x00bf
        L_0x0121:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>(r14)
            r8 = 84
            int r8 = r14.lastIndexOf(r8)
            int r8 = r4 - r8
            int r7 = r8 + -1
            r8 = 12
            if (r7 >= r8) goto L_0x013d
            switch(r7) {
                case 9: goto L_0x0167;
                case 10: goto L_0x0162;
                case 11: goto L_0x015d;
                default: goto L_0x0137;
            }
        L_0x0137:
            java.lang.String r8 = ".000"
            r6.append(r8)
        L_0x013d:
            r8 = 90
            r6.append(r8)
            java.lang.String r14 = r6.toString()
            java.text.DateFormat r1 = r13._formatISO8601_z
            java.lang.String r3 = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
            if (r1 != 0) goto L_0x0029
            java.text.DateFormat r8 = DATE_FORMAT_ISO8601_Z
            java.util.TimeZone r9 = r13._timezone
            java.util.Locale r10 = r13._locale
            java.lang.Boolean r11 = r13._lenient
            java.text.DateFormat r1 = _cloneFormat(r8, r3, r9, r10, r11)
            r13._formatISO8601_z = r1
            goto L_0x0029
        L_0x015d:
            r8 = 48
            r6.append(r8)
        L_0x0162:
            r8 = 48
            r6.append(r8)
        L_0x0167:
            r8 = 48
            r6.append(r8)
            goto L_0x013d
        L_0x016d:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.util.StdDateFormat.parseAsISO8601(java.lang.String, java.text.ParsePosition, boolean):java.util.Date");
    }

    /* access modifiers changed from: protected */
    public Date parseAsRFC1123(String dateStr, ParsePosition pos) {
        if (this._formatRFC1123 == null) {
            this._formatRFC1123 = _cloneFormat(DATE_FORMAT_RFC1123, "EEE, dd MMM yyyy HH:mm:ss zzz", this._timezone, this._locale, this._lenient);
        }
        return this._formatRFC1123.parse(dateStr, pos);
    }

    private static final boolean hasTimeZone(String str) {
        int len = str.length();
        if (len >= 6) {
            char c = str.charAt(len - 6);
            if (c == '+' || c == '-') {
                return true;
            }
            char c2 = str.charAt(len - 5);
            if (c2 == '+' || c2 == '-') {
                return true;
            }
            char c3 = str.charAt(len - 3);
            if (c3 == '+' || c3 == '-') {
                return true;
            }
        }
        return false;
    }

    private static final DateFormat _cloneFormat(DateFormat df, String format, TimeZone tz, Locale loc, Boolean lenient) {
        DateFormat df2;
        if (!loc.equals(DEFAULT_LOCALE)) {
            df2 = new SimpleDateFormat(format, loc);
            if (tz == null) {
                tz = DEFAULT_TIMEZONE;
            }
            df2.setTimeZone(tz);
        } else {
            df2 = (DateFormat) df.clone();
            if (tz != null) {
                df2.setTimeZone(tz);
            }
        }
        if (lenient != null) {
            df2.setLenient(lenient.booleanValue());
        }
        return df2;
    }

    /* access modifiers changed from: protected */
    public void _clearFormats() {
        this._formatRFC1123 = null;
        this._formatISO8601 = null;
        this._formatISO8601_z = null;
        this._formatPlain = null;
    }
}
