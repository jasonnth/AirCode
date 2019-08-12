package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.Base64Variants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.p307io.CharTypes;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class TextNode extends ValueNode {
    static final TextNode EMPTY_STRING_NODE = new TextNode("");
    protected final String _value;

    public TextNode(String v) {
        this._value = v;
    }

    public static TextNode valueOf(String v) {
        if (v == null) {
            return null;
        }
        if (v.length() == 0) {
            return EMPTY_STRING_NODE;
        }
        return new TextNode(v);
    }

    public JsonNodeType getNodeType() {
        return JsonNodeType.STRING;
    }

    public JsonToken asToken() {
        return JsonToken.VALUE_STRING;
    }

    public String textValue() {
        return this._value;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0028, code lost:
        if (r0 >= 0) goto L_0x002e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x002a, code lost:
        _reportInvalidBase64(r13, r2, 0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002e, code lost:
        r3 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002f, code lost:
        if (r6 < r4) goto L_0x0034;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0031, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0034, code lost:
        r5 = r6 + 1;
        r2 = r7.charAt(r6);
        r0 = r13.decodeBase64Char(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003e, code lost:
        if (r0 >= 0) goto L_0x0044;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0040, code lost:
        _reportInvalidBase64(r13, r2, 1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0044, code lost:
        r3 = (r3 << 6) | r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0048, code lost:
        if (r5 < r4) goto L_0x0059;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x004e, code lost:
        if (r13.usesPadding() != false) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0050, code lost:
        r1.append(r3 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0059, code lost:
        r6 = r5 + 1;
        r2 = r7.charAt(r5);
        r0 = r13.decodeBase64Char(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0063, code lost:
        if (r0 >= 0) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0065, code lost:
        if (r0 == -2) goto L_0x006b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0067, code lost:
        _reportInvalidBase64(r13, r2, 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x006b, code lost:
        if (r6 < r4) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006d, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0070, code lost:
        r5 = r6 + 1;
        r2 = r7.charAt(r6);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x007a, code lost:
        if (r13.usesPaddingChar(r2) != false) goto L_0x009e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x007c, code lost:
        _reportInvalidBase64(r13, r2, 3, "expected padding character '" + r13.getPaddingChar() + "'");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x009e, code lost:
        r1.append(r3 >> 4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a5, code lost:
        r3 = (r3 << 6) | r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00a9, code lost:
        if (r6 < r4) goto L_0x00bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00af, code lost:
        if (r13.usesPadding() != false) goto L_0x00b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00b1, code lost:
        r1.appendTwoBytes(r3 >> 2);
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x00b9, code lost:
        _reportBase64EOF();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00bc, code lost:
        r5 = r6 + 1;
        r2 = r7.charAt(r6);
        r0 = r13.decodeBase64Char(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x00c6, code lost:
        if (r0 >= 0) goto L_0x00d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x00c8, code lost:
        if (r0 == -2) goto L_0x00cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00ca, code lost:
        _reportInvalidBase64(r13, r2, 3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00cd, code lost:
        r1.appendTwoBytes(r3 >> 2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d4, code lost:
        r1.appendThreeBytes((r3 << 6) | r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0024, code lost:
        r0 = r13.decodeBase64Char(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public byte[] getBinaryValue(com.fasterxml.jackson.core.Base64Variant r13) throws java.io.IOException {
        /*
            r12 = this;
            r11 = 3
            r10 = -2
            com.fasterxml.jackson.core.util.ByteArrayBuilder r1 = new com.fasterxml.jackson.core.util.ByteArrayBuilder
            r8 = 100
            r1.<init>(r8)
            java.lang.String r7 = r12._value
            r5 = 0
            int r4 = r7.length()
        L_0x0010:
            if (r5 >= r4) goto L_0x001b
        L_0x0012:
            int r6 = r5 + 1
            char r2 = r7.charAt(r5)
            if (r6 < r4) goto L_0x0020
            r5 = r6
        L_0x001b:
            byte[] r8 = r1.toByteArray()
            return r8
        L_0x0020:
            r8 = 32
            if (r2 <= r8) goto L_0x00dd
            int r0 = r13.decodeBase64Char(r2)
            if (r0 >= 0) goto L_0x002e
            r8 = 0
            r12._reportInvalidBase64(r13, r2, r8)
        L_0x002e:
            r3 = r0
            if (r6 < r4) goto L_0x0034
            r12._reportBase64EOF()
        L_0x0034:
            int r5 = r6 + 1
            char r2 = r7.charAt(r6)
            int r0 = r13.decodeBase64Char(r2)
            if (r0 >= 0) goto L_0x0044
            r8 = 1
            r12._reportInvalidBase64(r13, r2, r8)
        L_0x0044:
            int r8 = r3 << 6
            r3 = r8 | r0
            if (r5 < r4) goto L_0x0059
            boolean r8 = r13.usesPadding()
            if (r8 != 0) goto L_0x0056
            int r3 = r3 >> 4
            r1.append(r3)
            goto L_0x001b
        L_0x0056:
            r12._reportBase64EOF()
        L_0x0059:
            int r6 = r5 + 1
            char r2 = r7.charAt(r5)
            int r0 = r13.decodeBase64Char(r2)
            if (r0 >= 0) goto L_0x00a5
            if (r0 == r10) goto L_0x006b
            r8 = 2
            r12._reportInvalidBase64(r13, r2, r8)
        L_0x006b:
            if (r6 < r4) goto L_0x0070
            r12._reportBase64EOF()
        L_0x0070:
            int r5 = r6 + 1
            char r2 = r7.charAt(r6)
            boolean r8 = r13.usesPaddingChar(r2)
            if (r8 != 0) goto L_0x009e
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            r8.<init>()
            java.lang.String r9 = "expected padding character '"
            java.lang.StringBuilder r8 = r8.append(r9)
            char r9 = r13.getPaddingChar()
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r9 = "'"
            java.lang.StringBuilder r8 = r8.append(r9)
            java.lang.String r8 = r8.toString()
            r12._reportInvalidBase64(r13, r2, r11, r8)
        L_0x009e:
            int r3 = r3 >> 4
            r1.append(r3)
            goto L_0x0010
        L_0x00a5:
            int r8 = r3 << 6
            r3 = r8 | r0
            if (r6 < r4) goto L_0x00bc
            boolean r8 = r13.usesPadding()
            if (r8 != 0) goto L_0x00b9
            int r3 = r3 >> 2
            r1.appendTwoBytes(r3)
            r5 = r6
            goto L_0x001b
        L_0x00b9:
            r12._reportBase64EOF()
        L_0x00bc:
            int r5 = r6 + 1
            char r2 = r7.charAt(r6)
            int r0 = r13.decodeBase64Char(r2)
            if (r0 >= 0) goto L_0x00d4
            if (r0 == r10) goto L_0x00cd
            r12._reportInvalidBase64(r13, r2, r11)
        L_0x00cd:
            int r3 = r3 >> 2
            r1.appendTwoBytes(r3)
            goto L_0x0010
        L_0x00d4:
            int r8 = r3 << 6
            r3 = r8 | r0
            r1.appendThreeBytes(r3)
            goto L_0x0010
        L_0x00dd:
            r5 = r6
            goto L_0x0012
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.node.TextNode.getBinaryValue(com.fasterxml.jackson.core.Base64Variant):byte[]");
    }

    public byte[] binaryValue() throws IOException {
        return getBinaryValue(Base64Variants.getDefaultVariant());
    }

    public String asText() {
        return this._value;
    }

    public final void serialize(JsonGenerator jg, SerializerProvider provider) throws IOException {
        if (this._value == null) {
            jg.writeNull();
        } else {
            jg.writeString(this._value);
        }
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || !(o instanceof TextNode)) {
            return false;
        }
        return ((TextNode) o)._value.equals(this._value);
    }

    public int hashCode() {
        return this._value.hashCode();
    }

    public String toString() {
        int len = this._value.length();
        StringBuilder sb = new StringBuilder(len + 2 + (len >> 4));
        appendQuoted(sb, this._value);
        return sb.toString();
    }

    protected static void appendQuoted(StringBuilder sb, String content) {
        sb.append('\"');
        CharTypes.appendQuoted(sb, content);
        sb.append('\"');
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant b64variant, char ch, int bindex) throws JsonParseException {
        _reportInvalidBase64(b64variant, ch, bindex, null);
    }

    /* access modifiers changed from: protected */
    public void _reportInvalidBase64(Base64Variant b64variant, char ch, int bindex, String msg) throws JsonParseException {
        String base;
        if (ch <= ' ') {
            base = "Illegal white space character (code 0x" + Integer.toHexString(ch) + ") as character #" + (bindex + 1) + " of 4-char base64 unit: can only used between units";
        } else if (b64variant.usesPaddingChar(ch)) {
            base = "Unexpected padding character ('" + b64variant.getPaddingChar() + "') as character #" + (bindex + 1) + " of 4-char base64 unit: padding only legal as 3rd or 4th character";
        } else if (!Character.isDefined(ch) || Character.isISOControl(ch)) {
            base = "Illegal character (code 0x" + Integer.toHexString(ch) + ") in base64 content";
        } else {
            base = "Illegal character '" + ch + "' (code 0x" + Integer.toHexString(ch) + ") in base64 content";
        }
        if (msg != null) {
            base = base + ": " + msg;
        }
        throw new JsonParseException((JsonParser) null, base, JsonLocation.f3137NA);
    }

    /* access modifiers changed from: protected */
    public void _reportBase64EOF() throws JsonParseException {
        throw new JsonParseException(null, "Unexpected end-of-String when base64 content");
    }
}
