package com.airbnb.deeplinkdispatch;

import com.facebook.common.util.UriUtil;
import java.net.IDN;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import okio.Buffer;

public final class DeepLinkUri {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final String fragment;
    /* access modifiers changed from: private */
    public final String host;
    private final String password;
    private final List<String> pathSegments;
    /* access modifiers changed from: private */
    public final int port;
    private final List<String> queryNamesAndValues;
    /* access modifiers changed from: private */
    public final String scheme;
    private final String url;
    private final String username;

    /* renamed from: com.airbnb.deeplinkdispatch.DeepLinkUri$1 */
    static /* synthetic */ class C17841 {

        /* renamed from: $SwitchMap$com$airbnb$deeplinkdispatch$DeepLinkUri$Builder$ParseResult */
        static final /* synthetic */ int[] f2682x4aa470fa = new int[ParseResult.values().length];

        static {
            try {
                f2682x4aa470fa[ParseResult.SUCCESS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2682x4aa470fa[ParseResult.INVALID_HOST.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2682x4aa470fa[ParseResult.UNSUPPORTED_SCHEME.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                f2682x4aa470fa[ParseResult.MISSING_SCHEME.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                f2682x4aa470fa[ParseResult.INVALID_PORT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    static final class Builder {
        String encodedFragment;
        String encodedPassword = "";
        final List<String> encodedPathSegments = new ArrayList();
        List<String> encodedQueryNamesAndValues;
        String encodedUsername = "";
        String host;
        int port = -1;
        String scheme;

        enum ParseResult {
            SUCCESS,
            MISSING_SCHEME,
            UNSUPPORTED_SCHEME,
            INVALID_PORT,
            INVALID_HOST
        }

        Builder() {
            this.encodedPathSegments.add("");
        }

        /* access modifiers changed from: 0000 */
        public int effectivePort() {
            return this.port != -1 ? this.port : DeepLinkUri.defaultPort(this.scheme);
        }

        /* access modifiers changed from: 0000 */
        public Builder encodedQuery(String encodedQuery) {
            this.encodedQueryNamesAndValues = encodedQuery != null ? DeepLinkUri.queryStringToNamesAndValues(DeepLinkUri.canonicalize(encodedQuery, " \"'<>#", true, true)) : null;
            return this;
        }

        /* access modifiers changed from: 0000 */
        public DeepLinkUri build() {
            if (this.scheme == null) {
                throw new IllegalStateException("scheme == null");
            } else if (this.host != null) {
                return new DeepLinkUri(this, null);
            } else {
                throw new IllegalStateException("host == null");
            }
        }

        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(this.scheme);
            result.append("://");
            if (!this.encodedUsername.isEmpty() || !this.encodedPassword.isEmpty()) {
                result.append(this.encodedUsername);
                if (!this.encodedPassword.isEmpty()) {
                    result.append(':');
                    result.append(this.encodedPassword);
                }
                result.append('@');
            }
            if (this.host.indexOf(58) != -1) {
                result.append('[');
                result.append(this.host);
                result.append(']');
            } else {
                result.append(this.host);
            }
            int effectivePort = effectivePort();
            if (effectivePort != DeepLinkUri.defaultPort(this.scheme)) {
                result.append(':');
                result.append(effectivePort);
            }
            DeepLinkUri.pathSegmentsToString(result, this.encodedPathSegments);
            if (this.encodedQueryNamesAndValues != null) {
                result.append('?');
                DeepLinkUri.namesAndValuesToQueryString(result, this.encodedQueryNamesAndValues);
            }
            if (this.encodedFragment != null) {
                result.append('#');
                result.append(this.encodedFragment);
            }
            return result.toString();
        }

        /* access modifiers changed from: 0000 */
        public ParseResult parse(DeepLinkUri base, String input) {
            int pos = skipLeadingAsciiWhitespace(input, 0, input.length());
            int limit = skipTrailingAsciiWhitespace(input, pos, input.length());
            int schemeDelimiterOffset = schemeDelimiterOffset(input, pos, limit);
            if (schemeDelimiterOffset != -1) {
                if (input.regionMatches(true, pos, "https:", 0, 6)) {
                    this.scheme = UriUtil.HTTPS_SCHEME;
                    pos += "https:".length();
                } else {
                    if (input.regionMatches(true, pos, "http:", 0, 5)) {
                        this.scheme = UriUtil.HTTP_SCHEME;
                        pos += "http:".length();
                    } else {
                        this.scheme = input.substring(pos, schemeDelimiterOffset);
                        pos += this.scheme.length() + 1;
                    }
                }
            } else if (base == null) {
                return ParseResult.MISSING_SCHEME;
            } else {
                this.scheme = base.scheme;
            }
            boolean hasUsername = false;
            boolean hasPassword = false;
            int slashCount = slashCount(input, pos, limit);
            if (slashCount >= 2 || base == null || !base.scheme.equals(this.scheme)) {
                int pos2 = pos + slashCount;
                while (true) {
                    int componentDelimiterOffset = DeepLinkUri.delimiterOffset(input, pos2, limit, "@/\\?#");
                    switch (componentDelimiterOffset != limit ? input.charAt(componentDelimiterOffset) : -1) {
                        case -1:
                        case 35:
                        case 47:
                        case 63:
                        case 92:
                            int portColonOffset = portColonOffset(input, pos2, componentDelimiterOffset);
                            if (portColonOffset + 1 < componentDelimiterOffset) {
                                this.host = canonicalizeHost(input, pos2, portColonOffset);
                                this.port = parsePort(input, portColonOffset + 1, componentDelimiterOffset);
                                if (this.port == -1) {
                                    return ParseResult.INVALID_PORT;
                                }
                            } else {
                                this.host = canonicalizeHost(input, pos2, portColonOffset);
                                this.port = DeepLinkUri.defaultPort(this.scheme);
                            }
                            if (this.host != null) {
                                pos = componentDelimiterOffset;
                                break;
                            } else {
                                return ParseResult.INVALID_HOST;
                            }
                        case 64:
                            if (!hasPassword) {
                                int passwordColonOffset = DeepLinkUri.delimiterOffset(input, pos2, componentDelimiterOffset, ":");
                                String canonicalUsername = DeepLinkUri.canonicalize(input, pos2, passwordColonOffset, " \"':;<=>@[]^`{}|/\\?#", true, false);
                                if (hasUsername) {
                                    canonicalUsername = this.encodedUsername + "%40" + canonicalUsername;
                                }
                                this.encodedUsername = canonicalUsername;
                                if (passwordColonOffset != componentDelimiterOffset) {
                                    hasPassword = true;
                                    this.encodedPassword = DeepLinkUri.canonicalize(input, passwordColonOffset + 1, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false);
                                }
                                hasUsername = true;
                            } else {
                                this.encodedPassword += "%40" + DeepLinkUri.canonicalize(input, pos2, componentDelimiterOffset, " \"':;<=>@[]^`{}|/\\?#", true, false);
                            }
                            pos2 = componentDelimiterOffset + 1;
                            continue;
                    }
                }
            } else {
                this.encodedUsername = base.encodedUsername();
                this.encodedPassword = base.encodedPassword();
                this.host = base.host;
                this.port = base.port;
                this.encodedPathSegments.clear();
                this.encodedPathSegments.addAll(base.encodedPathSegments());
                if (pos == limit || input.charAt(pos) == '#') {
                    encodedQuery(base.encodedQuery());
                }
            }
            int pathDelimiterOffset = DeepLinkUri.delimiterOffset(input, pos, limit, "?#");
            resolvePath(input, pos, pathDelimiterOffset);
            int pos3 = pathDelimiterOffset;
            if (pos3 < limit && input.charAt(pos3) == '?') {
                int queryDelimiterOffset = DeepLinkUri.delimiterOffset(input, pos3, limit, "#");
                this.encodedQueryNamesAndValues = DeepLinkUri.queryStringToNamesAndValues(DeepLinkUri.canonicalize(input, pos3 + 1, queryDelimiterOffset, " \"'<>#", true, true));
                pos3 = queryDelimiterOffset;
            }
            if (pos3 < limit && input.charAt(pos3) == '#') {
                this.encodedFragment = DeepLinkUri.canonicalize(input, pos3 + 1, limit, "", true, false);
            }
            return ParseResult.SUCCESS;
        }

        private void resolvePath(String input, int pos, int limit) {
            if (pos != limit) {
                char c = input.charAt(pos);
                if (c == '/' || c == '\\') {
                    this.encodedPathSegments.clear();
                    this.encodedPathSegments.add("");
                    pos++;
                } else {
                    this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
                }
                int i = pos;
                while (i < limit) {
                    int pathSegmentDelimiterOffset = DeepLinkUri.delimiterOffset(input, i, limit, "/\\");
                    boolean segmentHasTrailingSlash = pathSegmentDelimiterOffset < limit;
                    push(input, i, pathSegmentDelimiterOffset, segmentHasTrailingSlash, true);
                    i = pathSegmentDelimiterOffset;
                    if (segmentHasTrailingSlash) {
                        i++;
                    }
                }
            }
        }

        private void push(String input, int pos, int limit, boolean addTrailingSlash, boolean alreadyEncoded) {
            String segment = DeepLinkUri.canonicalize(input, pos, limit, " \"<>^`{}|/\\?#", alreadyEncoded, false);
            if (!isDot(segment)) {
                if (isDotDot(segment)) {
                    pop();
                    return;
                }
                if (((String) this.encodedPathSegments.get(this.encodedPathSegments.size() - 1)).isEmpty()) {
                    this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, segment);
                } else {
                    this.encodedPathSegments.add(segment);
                }
                if (addTrailingSlash) {
                    this.encodedPathSegments.add("");
                }
            }
        }

        private boolean isDot(String input) {
            return input.equals(".") || input.equalsIgnoreCase("%2e");
        }

        private boolean isDotDot(String input) {
            return input.equals("..") || input.equalsIgnoreCase("%2e.") || input.equalsIgnoreCase(".%2e") || input.equalsIgnoreCase("%2e%2e");
        }

        private void pop() {
            if (!((String) this.encodedPathSegments.remove(this.encodedPathSegments.size() - 1)).isEmpty() || this.encodedPathSegments.isEmpty()) {
                this.encodedPathSegments.add("");
            } else {
                this.encodedPathSegments.set(this.encodedPathSegments.size() - 1, "");
            }
        }

        private int skipLeadingAsciiWhitespace(String input, int pos, int limit) {
            int i = pos;
            while (i < limit) {
                switch (input.charAt(i)) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        i++;
                    default:
                        return i;
                }
            }
            return limit;
        }

        private int skipTrailingAsciiWhitespace(String input, int pos, int limit) {
            int i = limit - 1;
            while (i >= pos) {
                switch (input.charAt(i)) {
                    case 9:
                    case 10:
                    case 12:
                    case 13:
                    case ' ':
                        i--;
                    default:
                        return i + 1;
                }
            }
            return pos;
        }

        private static int schemeDelimiterOffset(String input, int pos, int limit) {
            if (limit - pos < 2) {
                return -1;
            }
            char c0 = input.charAt(pos);
            if ((c0 < 'a' || c0 > 'z') && (c0 < 'A' || c0 > 'Z')) {
                return -1;
            }
            int i = pos + 1;
            while (i < limit) {
                char c = input.charAt(i);
                if ((c >= 'a' && c <= 'z') || ((c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '+' || c == '-' || c == '.'))) {
                    i++;
                } else if (c != ':') {
                    return -1;
                } else {
                    return i;
                }
            }
            return -1;
        }

        private static int slashCount(String input, int pos, int limit) {
            int slashCount = 0;
            while (pos < limit) {
                char c = input.charAt(pos);
                if (c != '\\' && c != '/') {
                    break;
                }
                slashCount++;
                pos++;
            }
            return slashCount;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:6:0x000f, code lost:
            if (r0 >= r5) goto L_0x000a;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static int portColonOffset(java.lang.String r3, int r4, int r5) {
            /*
                r0 = r4
            L_0x0001:
                if (r0 >= r5) goto L_0x001a
                char r1 = r3.charAt(r0)
                switch(r1) {
                    case 58: goto L_0x001b;
                    case 91: goto L_0x000d;
                    default: goto L_0x000a;
                }
            L_0x000a:
                int r0 = r0 + 1
                goto L_0x0001
            L_0x000d:
                int r0 = r0 + 1
                if (r0 >= r5) goto L_0x000a
                char r1 = r3.charAt(r0)
                r2 = 93
                if (r1 != r2) goto L_0x000d
                goto L_0x000a
            L_0x001a:
                r0 = r5
            L_0x001b:
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.deeplinkdispatch.DeepLinkUri.Builder.portColonOffset(java.lang.String, int, int):int");
        }

        private static String canonicalizeHost(String input, int pos, int limit) {
            String percentDecoded = DeepLinkUri.percentDecode(input, pos, limit);
            if (!percentDecoded.startsWith("[") || !percentDecoded.endsWith("]")) {
                return domainToAscii(percentDecoded);
            }
            InetAddress inetAddress = decodeIpv6(percentDecoded, 1, percentDecoded.length() - 1);
            if (inetAddress == null) {
                return null;
            }
            byte[] address = inetAddress.getAddress();
            if (address.length == 16) {
                return inet6AddressToAscii(address);
            }
            throw new AssertionError();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
            return null;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static java.net.InetAddress decodeIpv6(java.lang.String r15, int r16, int r17) {
            /*
                r12 = 16
                byte[] r1 = new byte[r12]
                r2 = 0
                r5 = -1
                r8 = -1
                r10 = r16
            L_0x0009:
                r0 = r17
                if (r10 >= r0) goto L_0x0031
                int r12 = r1.length
                if (r2 != r12) goto L_0x0012
                r12 = 0
            L_0x0011:
                return r12
            L_0x0012:
                int r12 = r10 + 2
                r0 = r17
                if (r12 > r0) goto L_0x0039
                java.lang.String r12 = "::"
                r13 = 0
                r14 = 2
                boolean r12 = r15.regionMatches(r10, r12, r13, r14)
                if (r12 == 0) goto L_0x0039
                r12 = -1
                if (r5 == r12) goto L_0x0028
                r12 = 0
                goto L_0x0011
            L_0x0028:
                int r10 = r10 + 2
                int r2 = r2 + 2
                r5 = r2
                r0 = r17
                if (r10 != r0) goto L_0x0048
            L_0x0031:
                int r12 = r1.length
                if (r2 == r12) goto L_0x00a7
                r12 = -1
                if (r5 != r12) goto L_0x0097
                r12 = 0
                goto L_0x0011
            L_0x0039:
                if (r2 == 0) goto L_0x0048
                java.lang.String r12 = ":"
                r13 = 0
                r14 = 1
                boolean r12 = r15.regionMatches(r10, r12, r13, r14)
                if (r12 == 0) goto L_0x0062
                int r10 = r10 + 1
            L_0x0048:
                r11 = 0
                r8 = r10
            L_0x004a:
                r0 = r17
                if (r10 >= r0) goto L_0x0059
                char r4 = r15.charAt(r10)
                int r9 = com.airbnb.deeplinkdispatch.DeepLinkUri.decodeHexDigit(r4)
                r12 = -1
                if (r9 != r12) goto L_0x007e
            L_0x0059:
                int r7 = r10 - r8
                if (r7 == 0) goto L_0x0060
                r12 = 4
                if (r7 <= r12) goto L_0x0085
            L_0x0060:
                r12 = 0
                goto L_0x0011
            L_0x0062:
                java.lang.String r12 = "."
                r13 = 0
                r14 = 1
                boolean r12 = r15.regionMatches(r10, r12, r13, r14)
                if (r12 == 0) goto L_0x007c
                int r12 = r2 + -2
                r0 = r17
                boolean r12 = decodeIpv4Suffix(r15, r8, r0, r1, r12)
                if (r12 != 0) goto L_0x0079
                r12 = 0
                goto L_0x0011
            L_0x0079:
                int r2 = r2 + 2
                goto L_0x0031
            L_0x007c:
                r12 = 0
                goto L_0x0011
            L_0x007e:
                int r12 = r11 << 4
                int r11 = r12 + r9
                int r10 = r10 + 1
                goto L_0x004a
            L_0x0085:
                int r3 = r2 + 1
                int r12 = r11 >>> 8
                r12 = r12 & 255(0xff, float:3.57E-43)
                byte r12 = (byte) r12
                r1[r2] = r12
                int r2 = r3 + 1
                r12 = r11 & 255(0xff, float:3.57E-43)
                byte r12 = (byte) r12
                r1[r3] = r12
                goto L_0x0009
            L_0x0097:
                int r12 = r1.length
                int r13 = r2 - r5
                int r12 = r12 - r13
                int r13 = r2 - r5
                java.lang.System.arraycopy(r1, r5, r1, r12, r13)
                int r12 = r1.length
                int r12 = r12 - r2
                int r12 = r12 + r5
                r13 = 0
                java.util.Arrays.fill(r1, r5, r12, r13)
            L_0x00a7:
                java.net.InetAddress r12 = java.net.InetAddress.getByAddress(r1)     // Catch:{ UnknownHostException -> 0x00ad }
                goto L_0x0011
            L_0x00ad:
                r6 = move-exception
                java.lang.AssertionError r12 = new java.lang.AssertionError
                r12.<init>()
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.airbnb.deeplinkdispatch.DeepLinkUri.Builder.decodeIpv6(java.lang.String, int, int):java.net.InetAddress");
        }

        private static boolean decodeIpv4Suffix(String input, int pos, int limit, byte[] address, int addressOffset) {
            int i = pos;
            int b = addressOffset;
            while (i < limit) {
                if (b == address.length) {
                    return false;
                }
                if (b != addressOffset) {
                    if (input.charAt(i) != '.') {
                        return false;
                    }
                    i++;
                }
                int value = 0;
                int groupOffset = i;
                while (i < limit) {
                    char c = input.charAt(i);
                    if (c < '0' || c > '9') {
                        break;
                    } else if (value == 0 && groupOffset != i) {
                        return false;
                    } else {
                        value = ((value * 10) + c) - 48;
                        if (value > 255) {
                            return false;
                        }
                        i++;
                    }
                }
                if (i - groupOffset == 0) {
                    return false;
                }
                int b2 = b + 1;
                address[b] = (byte) value;
                b = b2;
            }
            if (b == addressOffset + 4) {
                return true;
            }
            return false;
        }

        private static String domainToAscii(String input) {
            try {
                String result = IDN.toASCII(input).toLowerCase(Locale.US);
                if (result.isEmpty()) {
                    return null;
                }
                if (result == null) {
                    return null;
                }
                if (containsInvalidHostnameAsciiCodes(result)) {
                    return null;
                }
                return result;
            } catch (IllegalArgumentException e) {
                return null;
            }
        }

        private static boolean containsInvalidHostnameAsciiCodes(String hostnameAscii) {
            for (int i = 0; i < hostnameAscii.length(); i++) {
                char c = hostnameAscii.charAt(i);
                if (c <= 31 || c >= 127 || " #%/:?@[\\]".indexOf(c) != -1) {
                    return true;
                }
            }
            return false;
        }

        private static String inet6AddressToAscii(byte[] address) {
            int longestRunOffset = -1;
            int longestRunLength = 0;
            int i = 0;
            while (i < address.length) {
                int currentRunOffset = i;
                while (i < 16 && address[i] == 0 && address[i + 1] == 0) {
                    i += 2;
                }
                int currentRunLength = i - currentRunOffset;
                if (currentRunLength > longestRunLength) {
                    longestRunOffset = currentRunOffset;
                    longestRunLength = currentRunLength;
                }
                i += 2;
            }
            Buffer result = new Buffer();
            int i2 = 0;
            while (i2 < address.length) {
                if (i2 == longestRunOffset) {
                    result.writeByte(58);
                    i2 += longestRunLength;
                    if (i2 == 16) {
                        result.writeByte(58);
                    }
                } else {
                    if (i2 > 0) {
                        result.writeByte(58);
                    }
                    result.writeHexadecimalUnsignedLong((long) (((address[i2] & 255) << 8) | (address[i2 + 1] & 255)));
                    i2 += 2;
                }
            }
            return result.readUtf8();
        }

        private static int parsePort(String input, int pos, int limit) {
            try {
                int i = Integer.parseInt(DeepLinkUri.canonicalize(input, pos, limit, "", false, false));
                if (i <= 0 || i > 65535) {
                    return -1;
                }
                return i;
            } catch (NumberFormatException e) {
                return -1;
            }
        }
    }

    /* synthetic */ DeepLinkUri(Builder x0, C17841 x1) {
        this(x0);
    }

    private DeepLinkUri(Builder builder) {
        String str = null;
        this.scheme = builder.scheme;
        this.username = percentDecode(builder.encodedUsername);
        this.password = percentDecode(builder.encodedPassword);
        this.host = builder.host;
        this.port = builder.effectivePort();
        this.pathSegments = percentDecode(builder.encodedPathSegments);
        this.queryNamesAndValues = builder.encodedQueryNamesAndValues != null ? percentDecode(builder.encodedQueryNamesAndValues) : null;
        if (builder.encodedFragment != null) {
            str = percentDecode(builder.encodedFragment);
        }
        this.fragment = str;
        this.url = builder.toString();
    }

    /* access modifiers changed from: 0000 */
    public String scheme() {
        return this.scheme;
    }

    /* access modifiers changed from: 0000 */
    public String encodedUsername() {
        if (this.username.isEmpty()) {
            return "";
        }
        int usernameStart = this.scheme.length() + 3;
        return this.url.substring(usernameStart, delimiterOffset(this.url, usernameStart, this.url.length(), ":@"));
    }

    /* access modifiers changed from: 0000 */
    public String encodedPassword() {
        if (this.password.isEmpty()) {
            return "";
        }
        return this.url.substring(this.url.indexOf(58, this.scheme.length() + 3) + 1, this.url.indexOf(64));
    }

    /* access modifiers changed from: 0000 */
    public String encodedHost() {
        return canonicalize(this.host, "^`{}|\\", true, true);
    }

    static int defaultPort(String scheme2) {
        if (scheme2.equals(UriUtil.HTTP_SCHEME)) {
            return 80;
        }
        if (scheme2.equals(UriUtil.HTTPS_SCHEME)) {
            return 443;
        }
        return -1;
    }

    /* access modifiers changed from: 0000 */
    public String encodedPath() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        return this.url.substring(pathStart, delimiterOffset(this.url, pathStart, this.url.length(), "?#"));
    }

    static void pathSegmentsToString(StringBuilder out, List<String> pathSegments2) {
        int size = pathSegments2.size();
        for (int i = 0; i < size; i++) {
            out.append('/');
            out.append((String) pathSegments2.get(i));
        }
    }

    /* access modifiers changed from: 0000 */
    public List<String> encodedPathSegments() {
        int pathStart = this.url.indexOf(47, this.scheme.length() + 3);
        int pathEnd = delimiterOffset(this.url, pathStart, this.url.length(), "?#");
        List<String> result = new ArrayList<>();
        int i = pathStart;
        while (i < pathEnd) {
            int i2 = i + 1;
            int segmentEnd = delimiterOffset(this.url, i2, pathEnd, "/");
            result.add(this.url.substring(i2, segmentEnd));
            i = segmentEnd;
        }
        return result;
    }

    /* access modifiers changed from: 0000 */
    public String encodedQuery() {
        if (this.queryNamesAndValues == null) {
            return null;
        }
        int queryStart = this.url.indexOf(63) + 1;
        return this.url.substring(queryStart, delimiterOffset(this.url, queryStart + 1, this.url.length(), "#"));
    }

    static void namesAndValuesToQueryString(StringBuilder out, List<String> namesAndValues) {
        int size = namesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            String name = (String) namesAndValues.get(i);
            String value = (String) namesAndValues.get(i + 1);
            if (i > 0) {
                out.append('&');
            }
            out.append(name);
            if (value != null) {
                out.append('=');
                out.append(value);
            }
        }
    }

    static List<String> queryStringToNamesAndValues(String encodedQuery) {
        List<String> result = new ArrayList<>();
        int pos = 0;
        while (pos <= encodedQuery.length()) {
            int ampersandOffset = encodedQuery.indexOf(38, pos);
            if (ampersandOffset == -1) {
                ampersandOffset = encodedQuery.length();
            }
            int equalsOffset = encodedQuery.indexOf(61, pos);
            if (equalsOffset == -1 || equalsOffset > ampersandOffset) {
                result.add(encodedQuery.substring(pos, ampersandOffset));
                result.add(null);
            } else {
                result.add(encodedQuery.substring(pos, equalsOffset));
                result.add(encodedQuery.substring(equalsOffset + 1, ampersandOffset));
            }
            pos = ampersandOffset + 1;
        }
        return result;
    }

    public Set<String> queryParameterNames() {
        if (this.queryNamesAndValues == null) {
            return Collections.emptySet();
        }
        Set<String> result = new LinkedHashSet<>();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            result.add(this.queryNamesAndValues.get(i));
        }
        return Collections.unmodifiableSet(result);
    }

    public List<String> queryParameterValues(String name) {
        if (this.queryNamesAndValues == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();
        int size = this.queryNamesAndValues.size();
        for (int i = 0; i < size; i += 2) {
            if (name.equals(this.queryNamesAndValues.get(i))) {
                result.add(this.queryNamesAndValues.get(i + 1));
            }
        }
        return Collections.unmodifiableList(result);
    }

    public static DeepLinkUri parse(String url2) {
        Builder builder = new Builder();
        if (builder.parse(null, url2) == ParseResult.SUCCESS) {
            return builder.build();
        }
        return null;
    }

    public boolean equals(Object o) {
        return (o instanceof DeepLinkUri) && ((DeepLinkUri) o).url.equals(this.url);
    }

    public int hashCode() {
        return this.url.hashCode();
    }

    public String toString() {
        return this.url;
    }

    /* access modifiers changed from: private */
    public static int delimiterOffset(String input, int pos, int limit, String delimiters) {
        for (int i = pos; i < limit; i++) {
            if (delimiters.indexOf(input.charAt(i)) != -1) {
                return i;
            }
        }
        return limit;
    }

    static String percentDecode(String encoded) {
        return percentDecode(encoded, 0, encoded.length());
    }

    private List<String> percentDecode(List<String> list) {
        List<String> result = new ArrayList<>(list.size());
        for (String s : list) {
            result.add(s != null ? percentDecode(s) : null);
        }
        return Collections.unmodifiableList(result);
    }

    static String percentDecode(String encoded, int pos, int limit) {
        for (int i = pos; i < limit; i++) {
            if (encoded.charAt(i) == '%') {
                Buffer out = new Buffer();
                out.writeUtf8(encoded, pos, i);
                percentDecode(out, encoded, i, limit);
                return out.readUtf8();
            }
        }
        return encoded.substring(pos, limit);
    }

    static void percentDecode(Buffer out, String encoded, int pos, int limit) {
        int i = pos;
        while (i < limit) {
            int codePoint = encoded.codePointAt(i);
            if (codePoint == 37 && i + 2 < limit) {
                int d1 = decodeHexDigit(encoded.charAt(i + 1));
                int d2 = decodeHexDigit(encoded.charAt(i + 2));
                if (!(d1 == -1 || d2 == -1)) {
                    out.writeByte((d1 << 4) + d2);
                    i += 2;
                    i += Character.charCount(codePoint);
                }
            }
            out.writeUtf8CodePoint(codePoint);
            i += Character.charCount(codePoint);
        }
    }

    static int decodeHexDigit(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    static String canonicalize(String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean query) {
        int i = pos;
        while (i < limit) {
            int codePoint = input.codePointAt(i);
            if (codePoint < 32 || codePoint >= 127 || encodeSet.indexOf(codePoint) != -1 || ((codePoint == 37 && !alreadyEncoded) || (query && codePoint == 43))) {
                Buffer out = new Buffer();
                out.writeUtf8(input, pos, i);
                canonicalize(out, input, i, limit, encodeSet, alreadyEncoded, query);
                return out.readUtf8();
            }
            i += Character.charCount(codePoint);
        }
        return input.substring(pos, limit);
    }

    static void canonicalize(Buffer out, String input, int pos, int limit, String encodeSet, boolean alreadyEncoded, boolean query) {
        Buffer utf8Buffer = null;
        int i = pos;
        while (i < limit) {
            int codePoint = input.codePointAt(i);
            if (!alreadyEncoded || !(codePoint == 9 || codePoint == 10 || codePoint == 12 || codePoint == 13)) {
                if (query && codePoint == 43) {
                    out.writeUtf8(alreadyEncoded ? "%20" : "%2B");
                } else if (codePoint < 32 || codePoint >= 127 || encodeSet.indexOf(codePoint) != -1 || (codePoint == 37 && !alreadyEncoded)) {
                    if (utf8Buffer == null) {
                        utf8Buffer = new Buffer();
                    }
                    utf8Buffer.writeUtf8CodePoint(codePoint);
                    while (!utf8Buffer.exhausted()) {
                        int b = utf8Buffer.readByte() & 255;
                        out.writeByte(37);
                        out.writeByte((int) HEX_DIGITS[(b >> 4) & 15]);
                        out.writeByte((int) HEX_DIGITS[b & 15]);
                    }
                } else {
                    out.writeUtf8CodePoint(codePoint);
                }
            }
            i += Character.charCount(codePoint);
        }
    }

    static String canonicalize(String input, String encodeSet, boolean alreadyEncoded, boolean query) {
        return canonicalize(input, 0, input.length(), encodeSet, alreadyEncoded, query);
    }
}
