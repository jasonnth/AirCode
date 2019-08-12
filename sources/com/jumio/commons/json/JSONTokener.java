package com.jumio.commons.json;

import com.facebook.appevents.AppEventsConstants;
import java.util.Locale;
import org.jmrtd.lds.LDSFile;
import org.json.JSONException;
import org.spongycastle.asn1.eac.EACTags;

public class JSONTokener {

    /* renamed from: in */
    private final StringBuilder f3198in;
    private int pos;

    public JSONTokener(StringBuilder in) {
        if (in != null && in.charAt(0) == 65279) {
            in = in.deleteCharAt(0);
        }
        this.f3198in = in;
    }

    public Object nextValue() throws JSONException {
        int c = nextCleanInternal();
        switch (c) {
            case -1:
                throw syntaxError("End of input");
            case 34:
            case 39:
                return nextString((char) c);
            case 91:
                return readArray();
            case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
                return readObject();
            default:
                this.pos--;
                return readLiteral();
        }
    }

    private int nextCleanInternal() throws JSONException {
        while (this.pos < this.f3198in.length()) {
            StringBuilder sb = this.f3198in;
            int i = this.pos;
            this.pos = i + 1;
            int c = sb.charAt(i);
            switch (c) {
                case 9:
                case 10:
                case 13:
                case 32:
                    break;
                case 35:
                    skipToEndOfLine();
                    break;
                case 47:
                    if (this.pos == this.f3198in.length()) {
                        return c;
                    }
                    switch (this.f3198in.charAt(this.pos)) {
                        case '*':
                            this.pos++;
                            int commentEnd = this.f3198in.indexOf("*/", this.pos);
                            if (commentEnd != -1) {
                                this.pos = commentEnd + 2;
                                break;
                            } else {
                                throw syntaxError("Unterminated comment");
                            }
                        case '/':
                            this.pos++;
                            skipToEndOfLine();
                            break;
                        default:
                            return c;
                    }
                default:
                    return c;
            }
        }
        return -1;
    }

    private void skipToEndOfLine() {
        while (this.pos < this.f3198in.length()) {
            char c = this.f3198in.charAt(this.pos);
            if (c == 13 || c == 10) {
                this.pos++;
                return;
            }
            this.pos++;
        }
    }

    public StringBuilder nextString(char quote) throws JSONException {
        StringBuilder builder = null;
        int start = this.pos;
        while (this.pos < this.f3198in.length()) {
            StringBuilder sb = this.f3198in;
            int i = this.pos;
            this.pos = i + 1;
            int c = sb.charAt(i);
            if (c == quote) {
                if (builder == null) {
                    StringBuilder builder2 = new StringBuilder();
                    builder2.append(this.f3198in, start, this.pos - 1);
                    return builder2;
                }
                builder.append(this.f3198in, start, this.pos - 1);
                return builder;
            } else if (c == 92) {
                if (this.pos == this.f3198in.length()) {
                    throw syntaxError("Unterminated escape sequence");
                }
                if (builder == null) {
                    builder = new StringBuilder();
                }
                builder.append(this.f3198in, start, this.pos - 1);
                builder.append(readEscapeCharacter());
                start = this.pos;
            }
        }
        throw syntaxError("Unterminated string");
    }

    private char readEscapeCharacter() throws JSONException {
        StringBuilder sb = this.f3198in;
        int i = this.pos;
        this.pos = i + 1;
        char escaped = sb.charAt(i);
        switch (escaped) {
            case 'b':
                return 8;
            case 'f':
                return 12;
            case 'n':
                return 10;
            case 'r':
                return 13;
            case 't':
                return 9;
            case LDSFile.EF_DG2_TAG /*117*/:
                if (this.pos + 4 > this.f3198in.length()) {
                    throw syntaxError("Unterminated escape sequence");
                }
                String hex = this.f3198in.substring(this.pos, this.pos + 4);
                this.pos += 4;
                return (char) Integer.parseInt(hex, 16);
            default:
                return escaped;
        }
    }

    private boolean equalsIgnoreCase(StringBuilder reference, String value) {
        if (reference.length() != value.length()) {
            return false;
        }
        String upperCase = value.toUpperCase(Locale.GERMAN);
        String lowerCase = value.toLowerCase(Locale.GERMAN);
        for (int i = 0; i < value.length(); i++) {
            char original = reference.charAt(i);
            if (upperCase.charAt(i) != original && lowerCase.charAt(i) != original) {
                return false;
            }
        }
        return true;
    }

    private int indexOf(StringBuilder reference, char value) {
        for (int i = 0; i < reference.length(); i++) {
            if (reference.charAt(i) == value) {
                return i;
            }
        }
        return -1;
    }

    private boolean isNumber(StringBuilder reference) {
        for (int i = 0; i < reference.length(); i++) {
            char value = reference.charAt(i);
            if ((value < '0' || value > '9') && value != '.' && value != 'E' && value != '-') {
                return false;
            }
        }
        return true;
    }

    private Object readLiteral() throws JSONException {
        StringBuilder literal = nextToInternal("{}[]/\\:,=;# \t\f");
        if (literal.length() == 0) {
            throw syntaxError("Expected literal value");
        } else if (equalsIgnoreCase(literal, "null")) {
            return JumioJSONObject.NULL;
        } else {
            if (equalsIgnoreCase(literal, "true")) {
                return Boolean.TRUE;
            }
            if (equalsIgnoreCase(literal, InternalLogger.EVENT_PARAM_EXTRAS_FALSE)) {
                return Boolean.FALSE;
            }
            if (isNumber(literal)) {
                if (indexOf(literal, '.') == -1) {
                    int base = 10;
                    String number = literal.toString();
                    if (number.startsWith("0x") || number.startsWith("0X")) {
                        number = number.substring(2);
                        base = 16;
                    } else if (number.startsWith(AppEventsConstants.EVENT_PARAM_VALUE_NO) && number.length() > 1) {
                        number = number.substring(1);
                        base = 8;
                    }
                    try {
                        long longValue = Long.parseLong(number, base);
                        if (longValue > 2147483647L || longValue < -2147483648L) {
                            return Long.valueOf(longValue);
                        }
                        return Integer.valueOf((int) longValue);
                    } catch (NumberFormatException e) {
                    }
                }
                try {
                    return Double.valueOf(literal.toString());
                } catch (NumberFormatException e2) {
                }
            }
            return new StringBuilder(literal);
        }
    }

    private StringBuilder nextToInternal(String excluded) {
        int start = this.pos;
        while (this.pos < this.f3198in.length()) {
            char c = this.f3198in.charAt(this.pos);
            if (c == 13 || c == 10 || excluded.indexOf(c) != -1) {
                return new StringBuilder().append(this.f3198in, start, this.pos);
            }
            this.pos++;
        }
        return new StringBuilder().append(this.f3198in, start, this.f3198in.length());
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0017 A[LOOP_START] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.jumio.commons.json.JumioJSONObject readObject() throws org.json.JSONException {
        /*
            r6 = this;
            com.jumio.commons.json.JumioJSONObject r2 = new com.jumio.commons.json.JumioJSONObject
            r2.<init>()
            int r0 = r6.nextCleanInternal()
            r4 = 125(0x7d, float:1.75E-43)
            if (r0 != r4) goto L_0x000e
        L_0x000d:
            return r2
        L_0x000e:
            r4 = -1
            if (r0 == r4) goto L_0x0017
            int r4 = r6.pos
            int r4 = r4 + -1
            r6.pos = r4
        L_0x0017:
            java.lang.Object r1 = r6.nextValue()
            boolean r4 = r1 instanceof java.lang.StringBuilder
            if (r4 != 0) goto L_0x0055
            if (r1 != 0) goto L_0x0029
            java.lang.String r4 = "Names cannot be null"
            org.json.JSONException r4 = r6.syntaxError(r4)
            throw r4
        L_0x0029:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Names must be strings, but "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r5 = " is of type "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.Class r5 = r1.getClass()
            java.lang.String r5 = r5.getName()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            org.json.JSONException r4 = r6.syntaxError(r4)
            throw r4
        L_0x0055:
            int r3 = r6.nextCleanInternal()
            r4 = 58
            if (r3 == r4) goto L_0x007a
            r4 = 61
            if (r3 == r4) goto L_0x007a
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Expected ':' after "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r1)
            java.lang.String r4 = r4.toString()
            org.json.JSONException r4 = r6.syntaxError(r4)
            throw r4
        L_0x007a:
            int r4 = r6.pos
            java.lang.StringBuilder r5 = r6.f3198in
            int r5 = r5.length()
            if (r4 >= r5) goto L_0x0096
            java.lang.StringBuilder r4 = r6.f3198in
            int r5 = r6.pos
            char r4 = r4.charAt(r5)
            r5 = 62
            if (r4 != r5) goto L_0x0096
            int r4 = r6.pos
            int r4 = r4 + 1
            r6.pos = r4
        L_0x0096:
            java.lang.StringBuilder r1 = (java.lang.StringBuilder) r1
            java.lang.String r4 = r1.toString()
            java.lang.Object r5 = r6.nextValue()
            r2.put(r4, r5)
            int r4 = r6.nextCleanInternal()
            switch(r4) {
                case 44: goto L_0x0017;
                case 59: goto L_0x0017;
                case 125: goto L_0x000d;
                default: goto L_0x00aa;
            }
        L_0x00aa:
            java.lang.String r4 = "Unterminated object"
            org.json.JSONException r4 = r6.syntaxError(r4)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.jumio.commons.json.JSONTokener.readObject():com.jumio.commons.json.JumioJSONObject");
    }

    private JumioJSONArray readArray() throws JSONException {
        JumioJSONArray result = new JumioJSONArray();
        boolean hasTrailingSeparator = false;
        while (true) {
            switch (nextCleanInternal()) {
                case -1:
                    throw syntaxError("Unterminated array");
                case 44:
                case 59:
                    result.put(null);
                    hasTrailingSeparator = true;
                    continue;
                case 93:
                    if (hasTrailingSeparator) {
                        result.put(null);
                        break;
                    }
                    break;
                default:
                    this.pos--;
                    result.put(nextValue());
                    switch (nextCleanInternal()) {
                        case 44:
                        case 59:
                            hasTrailingSeparator = true;
                            continue;
                            continue;
                        case 93:
                            break;
                        default:
                            throw syntaxError("Unterminated array");
                    }
            }
        }
        return result;
    }

    public JSONException syntaxError(String message) {
        return new JSONException(message + this);
    }

    public boolean more() {
        return this.pos < this.f3198in.length();
    }

    public char next() {
        if (this.pos >= this.f3198in.length()) {
            return 0;
        }
        StringBuilder sb = this.f3198in;
        int i = this.pos;
        this.pos = i + 1;
        return sb.charAt(i);
    }

    public char next(char c) throws JSONException {
        char result = next();
        if (result == c) {
            return result;
        }
        throw syntaxError("Expected " + c + " but was " + result);
    }

    public char nextClean() throws JSONException {
        int nextCleanInt = nextCleanInternal();
        if (nextCleanInt == -1) {
            return 0;
        }
        return (char) nextCleanInt;
    }

    public StringBuilder next(int length) throws JSONException {
        if (this.pos + length > this.f3198in.length()) {
            throw syntaxError(length + " is out of bounds");
        }
        StringBuilder result = new StringBuilder();
        result.append(this.f3198in, this.pos, this.pos + length);
        this.pos += length;
        return result;
    }

    public StringBuilder nextTo(String excluded) {
        if (excluded != null) {
            return nextToInternal(excluded);
        }
        throw new NullPointerException();
    }

    public StringBuilder nextTo(char excluded) {
        return nextToInternal(String.valueOf(excluded));
    }

    public void skipPast(String thru) {
        int thruStart = this.f3198in.indexOf(thru, this.pos);
        this.pos = thruStart == -1 ? this.f3198in.length() : thru.length() + thruStart;
    }

    public char skipTo(char to) {
        int index = this.f3198in.indexOf(String.valueOf(to), this.pos);
        if (index == -1) {
            return 0;
        }
        this.pos = index;
        return to;
    }

    public void back() {
        int i = this.pos - 1;
        this.pos = i;
        if (i == -1) {
            this.pos = 0;
        }
    }

    public static int dehexchar(char hex) {
        if (hex >= '0' && hex <= '9') {
            return hex - '0';
        }
        if (hex >= 'A' && hex <= 'F') {
            return (hex - 'A') + 10;
        }
        if (hex < 'a' || hex > 'f') {
            return -1;
        }
        return (hex - 'a') + 10;
    }

    public void clear() {
        if (this.f3198in != null) {
            for (int i = 0; i < this.f3198in.length(); i++) {
                this.f3198in.setCharAt(i, 0);
            }
        }
    }
}
