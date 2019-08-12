package com.apollographql.apollo.internal.json;

import com.apollographql.apollo.internal.json.JsonReader.Token;
import com.apollographql.apollo.json.JsonDataException;
import com.apollographql.apollo.json.JsonEncodingException;
import java.io.EOFException;
import java.io.IOException;
import net.p318sf.scuba.smartcards.ISO7816;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.jmrtd.lds.LDSFile;
import org.spongycastle.asn1.eac.EACTags;

public final class BufferedSourceJsonReader extends JsonReader {
    private static final ByteString DOUBLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("\"\\");
    private static final ByteString LINEFEED_OR_CARRIAGE_RETURN = ByteString.encodeUtf8("\n\r");
    private static final ByteString SINGLE_QUOTE_OR_SLASH = ByteString.encodeUtf8("'\\");
    private static final ByteString UNQUOTED_STRING_TERMINALS = ByteString.encodeUtf8("{}[]:, \n\t\r\f/\\;#=");
    private final Buffer buffer;
    private boolean failOnUnknown = false;
    private boolean lenient = false;
    private final int[] pathIndices;
    private final String[] pathNames;
    private int peeked = 0;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private final BufferedSource source;
    private final int[] stack = new int[32];
    private int stackSize = 0;

    public BufferedSourceJsonReader(BufferedSource source2) {
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (source2 == null) {
            throw new NullPointerException("source == null");
        }
        this.source = source2;
        this.buffer = source2.buffer();
    }

    public void beginArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 3) {
            push(1);
            this.pathIndices[this.stackSize - 1] = 0;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void endArray() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 4) {
            this.stackSize--;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_ARRAY but was " + peek() + " at path " + getPath());
    }

    public void beginObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 1) {
            push(3);
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected BEGIN_OBJECT but was " + peek() + " at path " + getPath());
    }

    public void endObject() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 2) {
            this.stackSize--;
            this.pathNames[this.stackSize] = null;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            this.peeked = 0;
            return;
        }
        throw new JsonDataException("Expected END_OBJECT but was " + peek() + " at path " + getPath());
    }

    public boolean hasNext() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        return (p == 2 || p == 4) ? false : true;
    }

    public Token peek() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        switch (p) {
            case 1:
                return Token.BEGIN_OBJECT;
            case 2:
                return Token.END_OBJECT;
            case 3:
                return Token.BEGIN_ARRAY;
            case 4:
                return Token.END_ARRAY;
            case 5:
            case 6:
                return Token.BOOLEAN;
            case 7:
                return Token.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return Token.STRING;
            case 12:
            case 13:
            case 14:
                return Token.NAME;
            case 15:
            case 16:
                return Token.NUMBER;
            case 17:
                return Token.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    private int doPeek() throws IOException {
        int peekStack = this.stack[this.stackSize - 1];
        if (peekStack == 1) {
            this.stack[this.stackSize - 1] = 2;
        } else if (peekStack == 2) {
            int c = nextNonWhitespace(true);
            this.buffer.readByte();
            switch (c) {
                case 44:
                    break;
                case 59:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else if (peekStack == 3 || peekStack == 5) {
            this.stack[this.stackSize - 1] = 4;
            if (peekStack == 5) {
                int c2 = nextNonWhitespace(true);
                this.buffer.readByte();
                switch (c2) {
                    case 44:
                        break;
                    case 59:
                        checkLenient();
                        break;
                    case 125:
                        this.peeked = 2;
                        return 2;
                    default:
                        throw syntaxError("Unterminated object");
                }
            }
            int c3 = nextNonWhitespace(true);
            switch (c3) {
                case 34:
                    this.buffer.readByte();
                    this.peeked = 13;
                    return 13;
                case 39:
                    this.buffer.readByte();
                    checkLenient();
                    this.peeked = 12;
                    return 12;
                case 125:
                    if (peekStack != 5) {
                        this.buffer.readByte();
                        this.peeked = 2;
                        return 2;
                    }
                    throw syntaxError("Expected name");
                default:
                    checkLenient();
                    if (isLiteral((char) c3)) {
                        this.peeked = 14;
                        return 14;
                    }
                    throw syntaxError("Expected name");
            }
        } else if (peekStack == 4) {
            this.stack[this.stackSize - 1] = 5;
            int c4 = nextNonWhitespace(true);
            this.buffer.readByte();
            switch (c4) {
                case 58:
                    break;
                case 61:
                    checkLenient();
                    if (this.source.request(1) && this.buffer.getByte(0) == 62) {
                        this.buffer.readByte();
                        break;
                    }
                default:
                    throw syntaxError("Expected ':'");
            }
        } else if (peekStack == 6) {
            this.stack[this.stackSize - 1] = 7;
        } else if (peekStack == 7) {
            if (nextNonWhitespace(false) == -1) {
                this.peeked = 17;
                return 17;
            }
            checkLenient();
        } else if (peekStack == 8) {
            throw new IllegalStateException("JsonReader is closed");
        }
        switch (nextNonWhitespace(true)) {
            case 34:
                this.buffer.readByte();
                this.peeked = 9;
                return 9;
            case 39:
                checkLenient();
                this.buffer.readByte();
                this.peeked = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.buffer.readByte();
                this.peeked = 3;
                return 3;
            case 93:
                if (peekStack == 1) {
                    this.buffer.readByte();
                    this.peeked = 4;
                    return 4;
                }
                break;
            case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
                this.buffer.readByte();
                this.peeked = 1;
                return 1;
            default:
                int result = peekKeyword();
                if (result != 0) {
                    return result;
                }
                int result2 = peekNumber();
                if (result2 != 0) {
                    return result2;
                }
                if (!isLiteral(this.buffer.getByte(0))) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
        }
        if (peekStack == 1 || peekStack == 2) {
            checkLenient();
            this.peeked = 7;
            return 7;
        }
        throw syntaxError("Unexpected value");
    }

    private int peekKeyword() throws IOException {
        int peeking;
        String keywordUpper;
        String keyword;
        byte c = this.buffer.getByte(0);
        if (c == 116 || c == 84) {
            keyword = "true";
            keywordUpper = "TRUE";
            peeking = 5;
        } else if (c == 102 || c == 70) {
            keyword = InternalLogger.EVENT_PARAM_EXTRAS_FALSE;
            keywordUpper = "FALSE";
            peeking = 6;
        } else if (c != 110 && c != 78) {
            return 0;
        } else {
            keyword = "null";
            keywordUpper = "NULL";
            peeking = 7;
        }
        int length = keyword.length();
        for (int i = 1; i < length; i++) {
            if (!this.source.request((long) (i + 1))) {
                return 0;
            }
            byte c2 = this.buffer.getByte((long) i);
            if (c2 != keyword.charAt(i) && c2 != keywordUpper.charAt(i)) {
                return 0;
            }
        }
        if (this.source.request((long) (length + 1)) && isLiteral(this.buffer.getByte((long) length))) {
            return 0;
        }
        this.buffer.skip((long) length);
        this.peeked = peeking;
        return peeking;
    }

    private int peekNumber() throws IOException {
        byte c;
        long value = 0;
        boolean negative = false;
        boolean fitsInLong = true;
        int last = 0;
        int i = 0;
        while (true) {
            if (this.source.request((long) (i + 1))) {
                c = this.buffer.getByte((long) i);
                switch (c) {
                    case 43:
                        if (last != 5) {
                            return 0;
                        }
                        last = 6;
                        continue;
                    case 45:
                        if (last == 0) {
                            negative = true;
                            last = 1;
                            continue;
                        } else if (last == 5) {
                            last = 6;
                            break;
                        } else {
                            return 0;
                        }
                    case 46:
                        if (last != 2) {
                            return 0;
                        }
                        last = 3;
                        continue;
                    case 69:
                    case 101:
                        if (last != 2 && last != 4) {
                            return 0;
                        }
                        last = 5;
                        continue;
                    default:
                        if (c >= 48 && c <= 57) {
                            if (last != 1 && last != 0) {
                                if (last != 2) {
                                    if (last != 3) {
                                        if (last != 5 && last != 6) {
                                            break;
                                        } else {
                                            last = 7;
                                            break;
                                        }
                                    } else {
                                        last = 4;
                                        break;
                                    }
                                } else if (value != 0) {
                                    long newValue = (10 * value) - ((long) (c + ISO7816.INS_WRITE_BINARY));
                                    fitsInLong &= value > -922337203685477580L || (value == -922337203685477580L && newValue < value);
                                    value = newValue;
                                    break;
                                } else {
                                    return 0;
                                }
                            } else {
                                value = (long) (-(c + ISO7816.INS_WRITE_BINARY));
                                last = 2;
                                continue;
                            }
                        } else {
                            break;
                        }
                        break;
                }
            }
            i++;
        }
        if (isLiteral(c)) {
            return 0;
        }
        if (last == 2 && fitsInLong && (value != Long.MIN_VALUE || negative)) {
            if (!negative) {
                value = -value;
            }
            this.peekedLong = value;
            this.buffer.skip((long) i);
            this.peeked = 15;
            return 15;
        } else if (last != 2 && last != 4 && last != 7) {
            return 0;
        } else {
            this.peekedNumberLength = i;
            this.peeked = 16;
            return 16;
        }
    }

    private boolean isLiteral(int c) throws IOException {
        switch (c) {
            case 9:
            case 10:
            case 12:
            case 13:
            case 32:
            case 44:
            case 58:
            case 91:
            case 93:
            case EACTags.SECURITY_ENVIRONMENT_TEMPLATE /*123*/:
            case 125:
                break;
            case 35:
            case 47:
            case 59:
            case 61:
            case 92:
                checkLenient();
                break;
            default:
                return true;
        }
        return false;
    }

    public String nextName() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 14) {
            result = nextUnquotedValue();
        } else if (p == 13) {
            result = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (p == 12) {
            result = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else {
            throw new JsonDataException("Expected a name but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = result;
        return result;
    }

    public String nextString() throws IOException {
        String result;
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 10) {
            result = nextUnquotedValue();
        } else if (p == 9) {
            result = nextQuotedValue(DOUBLE_QUOTE_OR_SLASH);
        } else if (p == 8) {
            result = nextQuotedValue(SINGLE_QUOTE_OR_SLASH);
        } else if (p == 11) {
            result = this.peekedString;
            this.peekedString = null;
        } else if (p == 15) {
            result = Long.toString(this.peekedLong);
        } else if (p == 16) {
            result = this.buffer.readUtf8((long) this.peekedNumberLength);
        } else {
            throw new JsonDataException("Expected a string but was " + peek() + " at path " + getPath());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return result;
    }

    public boolean nextBoolean() throws IOException {
        int p = this.peeked;
        if (p == 0) {
            p = doPeek();
        }
        if (p == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (p == 6) {
            this.peeked = 0;
            int[] iArr2 = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new JsonDataException("Expected a boolean but was " + peek() + " at path " + getPath());
        }
    }

    private String nextQuotedValue(ByteString runTerminator) throws IOException {
        StringBuilder builder = null;
        while (true) {
            long index = this.source.indexOfElement(runTerminator);
            if (index == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(index) == 92) {
                if (builder == null) {
                    builder = new StringBuilder();
                }
                builder.append(this.buffer.readUtf8(index));
                this.buffer.readByte();
                builder.append(readEscapeCharacter());
            } else if (builder == null) {
                String result = this.buffer.readUtf8(index);
                this.buffer.readByte();
                return result;
            } else {
                builder.append(this.buffer.readUtf8(index));
                this.buffer.readByte();
                return builder.toString();
            }
        }
    }

    private String nextUnquotedValue() throws IOException {
        long i = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        return i != -1 ? this.buffer.readUtf8(i) : this.buffer.readUtf8();
    }

    private void skipQuotedValue(ByteString runTerminator) throws IOException {
        while (true) {
            long index = this.source.indexOfElement(runTerminator);
            if (index == -1) {
                throw syntaxError("Unterminated string");
            } else if (this.buffer.getByte(index) == 92) {
                this.buffer.skip(index + 1);
                readEscapeCharacter();
            } else {
                this.buffer.skip(index + 1);
                return;
            }
        }
    }

    private void skipUnquotedValue() throws IOException {
        long i = this.source.indexOfElement(UNQUOTED_STRING_TERMINALS);
        Buffer buffer2 = this.buffer;
        if (i == -1) {
            i = this.buffer.size();
        }
        buffer2.skip(i);
    }

    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.buffer.clear();
        this.source.close();
    }

    public void skipValue() throws IOException {
        if (this.failOnUnknown) {
            throw new JsonDataException("Cannot skip unexpected " + peek() + " at " + getPath());
        }
        int count = 0;
        do {
            int p = this.peeked;
            if (p == 0) {
                p = doPeek();
            }
            if (p == 3) {
                push(1);
                count++;
            } else if (p == 1) {
                push(3);
                count++;
            } else if (p == 4) {
                this.stackSize--;
                count--;
            } else if (p == 2) {
                this.stackSize--;
                count--;
            } else if (p == 14 || p == 10) {
                skipUnquotedValue();
            } else if (p == 9 || p == 13) {
                skipQuotedValue(DOUBLE_QUOTE_OR_SLASH);
            } else if (p == 8 || p == 12) {
                skipQuotedValue(SINGLE_QUOTE_OR_SLASH);
            } else if (p == 16) {
                this.buffer.skip((long) this.peekedNumberLength);
            }
            this.peeked = 0;
        } while (count != 0);
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.pathNames[this.stackSize - 1] = "null";
    }

    private void push(int newTop) {
        if (this.stackSize == this.stack.length) {
            throw new JsonDataException("Nesting too deep at " + getPath());
        }
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = newTop;
    }

    private int nextNonWhitespace(boolean throwOnEof) throws IOException {
        int p = 0;
        while (this.source.request((long) (p + 1))) {
            int p2 = p + 1;
            int c = this.buffer.getByte((long) p);
            if (c == 10 || c == 32 || c == 13) {
                p = p2;
            } else if (c == 9) {
                p = p2;
            } else {
                this.buffer.skip((long) (p2 - 1));
                if (c == 47) {
                    if (!this.source.request(2)) {
                        int i = p2;
                        return c;
                    }
                    checkLenient();
                    switch (this.buffer.getByte(1)) {
                        case 42:
                            this.buffer.readByte();
                            this.buffer.readByte();
                            if (skipTo("*/")) {
                                this.buffer.readByte();
                                this.buffer.readByte();
                                p = 0;
                                break;
                            } else {
                                throw syntaxError("Unterminated comment");
                            }
                        case 47:
                            this.buffer.readByte();
                            this.buffer.readByte();
                            skipToEndOfLine();
                            p = 0;
                            break;
                        default:
                            int i2 = p2;
                            return c;
                    }
                } else if (c == 35) {
                    checkLenient();
                    skipToEndOfLine();
                    p = 0;
                } else {
                    return c;
                }
            }
        }
        if (!throwOnEof) {
            return -1;
        }
        throw new EOFException("End of input");
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void skipToEndOfLine() throws IOException {
        long index = this.source.indexOfElement(LINEFEED_OR_CARRIAGE_RETURN);
        this.buffer.skip(index != -1 ? 1 + index : this.buffer.size());
    }

    private boolean skipTo(String toFind) throws IOException {
        while (this.source.request((long) toFind.length())) {
            int c = 0;
            while (c < toFind.length()) {
                if (this.buffer.getByte((long) c) != toFind.charAt(c)) {
                    this.buffer.readByte();
                } else {
                    c++;
                }
            }
            return true;
        }
        return false;
    }

    public String toString() {
        return "JsonReader(" + this.source + ")";
    }

    public String getPath() {
        return JsonScope.getPath(this.stackSize, this.stack, this.pathNames, this.pathIndices);
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        if (!this.source.request(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        byte escaped = this.buffer.readByte();
        switch (escaped) {
            case 10:
            case 34:
            case 39:
            case 47:
            case 92:
                return (char) escaped;
            case 98:
                return 8;
            case 102:
                return 12;
            case 110:
                return 10;
            case 114:
                return 13;
            case 116:
                return 9;
            case LDSFile.EF_DG2_TAG /*117*/:
                if (!this.source.request(4)) {
                    throw new EOFException("Unterminated escape sequence at path " + getPath());
                }
                char result = 0;
                int end = 0 + 4;
                for (int i2 = 0; i2 < end; i2++) {
                    byte c = this.buffer.getByte((long) i2);
                    char result2 = (char) (result << 4);
                    if (c >= 48 && c <= 57) {
                        i = c + ISO7816.INS_WRITE_BINARY;
                    } else if (c >= 97 && c <= 102) {
                        i = (c - 97) + 10;
                    } else if (c < 65 || c > 70) {
                        throw syntaxError("\\u" + this.buffer.readUtf8(4));
                    } else {
                        i = (c - 65) + 10;
                    }
                    result = (char) (i + result2);
                }
                this.buffer.skip(4);
                return result;
            default:
                if (this.lenient) {
                    return (char) escaped;
                }
                throw syntaxError("Invalid escape sequence: \\" + ((char) escaped));
        }
    }

    private JsonEncodingException syntaxError(String message) throws JsonEncodingException {
        throw new JsonEncodingException(message + " at path " + getPath());
    }
}
