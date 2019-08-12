package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate extends JsonParserDelegate {
    protected boolean _allowMultipleMatches;
    protected JsonToken _currToken;
    protected TokenFilterContext _exposedContext;
    protected TokenFilterContext _headContext;
    @Deprecated
    protected boolean _includeImmediateParent;
    protected boolean _includePath;
    protected TokenFilter _itemFilter;
    protected JsonToken _lastClearedToken;
    protected TokenFilter rootFilter;

    public FilteringParserDelegate(JsonParser p, TokenFilter f, boolean includePath, boolean allowMultipleMatches) {
        super(p);
        this.rootFilter = f;
        this._itemFilter = f;
        this._headContext = TokenFilterContext.createRootContext(f);
        this._includePath = includePath;
        this._allowMultipleMatches = allowMultipleMatches;
    }

    public JsonToken getCurrentToken() {
        return this._currToken;
    }

    public JsonToken currentToken() {
        return this._currToken;
    }

    public final int getCurrentTokenId() {
        JsonToken t = this._currToken;
        if (t == null) {
            return 0;
        }
        return t.mo34327id();
    }

    public boolean hasCurrentToken() {
        return this._currToken != null;
    }

    public boolean hasTokenId(int id) {
        JsonToken t = this._currToken;
        if (t == null) {
            if (id == 0) {
                return true;
            }
            return false;
        } else if (t.mo34327id() != id) {
            return false;
        } else {
            return true;
        }
    }

    public final boolean hasToken(JsonToken t) {
        return this._currToken == t;
    }

    public boolean isExpectedStartArrayToken() {
        return this._currToken == JsonToken.START_ARRAY;
    }

    public boolean isExpectedStartObjectToken() {
        return this._currToken == JsonToken.START_OBJECT;
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public JsonStreamContext getParsingContext() {
        return _filterContext();
    }

    public String getCurrentName() throws IOException {
        JsonStreamContext ctxt = _filterContext();
        if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY) {
            return ctxt.getCurrentName();
        }
        JsonStreamContext parent = ctxt.getParent();
        if (parent == null) {
            return null;
        }
        return parent.getCurrentName();
    }

    public void clearCurrentToken() {
        if (this._currToken != null) {
            this._lastClearedToken = this._currToken;
            this._currToken = null;
        }
    }

    public JsonToken nextToken() throws IOException {
        if (!this._allowMultipleMatches && this._currToken != null && this._exposedContext == null) {
            if (this._currToken.isStructEnd() && this._headContext.isStartHandled()) {
                this._currToken = null;
                return null;
            } else if (this._currToken.isScalarValue() && !this._headContext.isStartHandled() && !this._includePath && this._itemFilter == TokenFilter.INCLUDE_ALL) {
                this._currToken = null;
                return null;
            }
        }
        TokenFilterContext ctxt = this._exposedContext;
        if (ctxt != null) {
            do {
                JsonToken t = ctxt.nextTokenToRead();
                if (t != null) {
                    this._currToken = t;
                    return t;
                } else if (ctxt == this._headContext) {
                    this._exposedContext = null;
                    if (ctxt.inArray()) {
                        JsonToken t2 = this.delegate.getCurrentToken();
                        this._currToken = t2;
                        return t2;
                    }
                } else {
                    ctxt = this._headContext.findChildOf(ctxt);
                    this._exposedContext = ctxt;
                }
            } while (ctxt != null);
            throw _constructError("Unexpected problem: chain of filtered context broken");
        }
        JsonToken t3 = this.delegate.nextToken();
        if (t3 == null) {
            this._currToken = t3;
            return t3;
        }
        switch (t3.mo34327id()) {
            case 1:
                TokenFilter f = this._itemFilter;
                if (f == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildObjectContext(f, true);
                    this._currToken = t3;
                    return t3;
                } else if (f == null) {
                    this.delegate.skipChildren();
                    break;
                } else {
                    TokenFilter f2 = this._headContext.checkValue(f);
                    if (f2 == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        if (f2 != TokenFilter.INCLUDE_ALL) {
                            f2 = f2.filterStartObject();
                        }
                        this._itemFilter = f2;
                        if (f2 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildObjectContext(f2, true);
                            this._currToken = t3;
                            return t3;
                        }
                        this._headContext = this._headContext.createChildObjectContext(f2, false);
                        if (this._includePath) {
                            JsonToken t4 = _nextTokenWithBuffering(this._headContext);
                            if (t4 != null) {
                                this._currToken = t4;
                                return t4;
                            }
                        }
                    }
                }
                break;
            case 2:
            case 4:
                boolean returnEnd = this._headContext.isStartHandled();
                TokenFilter f3 = this._headContext.getFilter();
                if (!(f3 == null || f3 == TokenFilter.INCLUDE_ALL)) {
                    f3.filterFinishArray();
                }
                this._headContext = this._headContext.getParent();
                this._itemFilter = this._headContext.getFilter();
                if (returnEnd) {
                    this._currToken = t3;
                    return t3;
                }
                break;
            case 3:
                TokenFilter f4 = this._itemFilter;
                if (f4 == TokenFilter.INCLUDE_ALL) {
                    this._headContext = this._headContext.createChildArrayContext(f4, true);
                    this._currToken = t3;
                    return t3;
                } else if (f4 == null) {
                    this.delegate.skipChildren();
                    break;
                } else {
                    TokenFilter f5 = this._headContext.checkValue(f4);
                    if (f5 == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        if (f5 != TokenFilter.INCLUDE_ALL) {
                            f5 = f5.filterStartArray();
                        }
                        this._itemFilter = f5;
                        if (f5 == TokenFilter.INCLUDE_ALL) {
                            this._headContext = this._headContext.createChildArrayContext(f5, true);
                            this._currToken = t3;
                            return t3;
                        }
                        this._headContext = this._headContext.createChildArrayContext(f5, false);
                        if (this._includePath) {
                            JsonToken t5 = _nextTokenWithBuffering(this._headContext);
                            if (t5 != null) {
                                this._currToken = t5;
                                return t5;
                            }
                        }
                    }
                }
                break;
            case 5:
                String name = this.delegate.getCurrentName();
                TokenFilter f6 = this._headContext.setFieldName(name);
                if (f6 == TokenFilter.INCLUDE_ALL) {
                    this._itemFilter = f6;
                    if (!this._includePath && this._includeImmediateParent && !this._headContext.isStartHandled()) {
                        t3 = this._headContext.nextTokenToRead();
                        this._exposedContext = this._headContext;
                    }
                    this._currToken = t3;
                    return t3;
                } else if (f6 == null) {
                    this.delegate.nextToken();
                    this.delegate.skipChildren();
                    break;
                } else {
                    TokenFilter f7 = f6.includeProperty(name);
                    if (f7 == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                        break;
                    } else {
                        this._itemFilter = f7;
                        if (f7 == TokenFilter.INCLUDE_ALL && this._includePath) {
                            this._currToken = t3;
                            return t3;
                        } else if (this._includePath) {
                            JsonToken t6 = _nextTokenWithBuffering(this._headContext);
                            if (t6 != null) {
                                this._currToken = t6;
                                return t6;
                            }
                        }
                    }
                }
                break;
            default:
                TokenFilter f8 = this._itemFilter;
                if (f8 == TokenFilter.INCLUDE_ALL) {
                    this._currToken = t3;
                    return t3;
                } else if (f8 != null) {
                    TokenFilter f9 = this._headContext.checkValue(f8);
                    if (f9 == TokenFilter.INCLUDE_ALL || (f9 != null && f9.includeValue(this.delegate))) {
                        this._currToken = t3;
                        return t3;
                    }
                }
                break;
        }
        return _nextToken2();
    }

    /* access modifiers changed from: protected */
    public final JsonToken _nextToken2() throws IOException {
        JsonToken t;
        while (true) {
            t = this.delegate.nextToken();
            if (t == null) {
                this._currToken = t;
                return t;
            }
            switch (t.mo34327id()) {
                case 1:
                    TokenFilter f = this._itemFilter;
                    if (f == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildObjectContext(f, true);
                        this._currToken = t;
                        return t;
                    } else if (f == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        TokenFilter f2 = this._headContext.checkValue(f);
                        if (f2 == null) {
                            this.delegate.skipChildren();
                            break;
                        } else {
                            if (f2 != TokenFilter.INCLUDE_ALL) {
                                f2 = f2.filterStartObject();
                            }
                            this._itemFilter = f2;
                            if (f2 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildObjectContext(f2, true);
                                this._currToken = t;
                                return t;
                            }
                            this._headContext = this._headContext.createChildObjectContext(f2, false);
                            if (this._includePath) {
                                JsonToken t2 = _nextTokenWithBuffering(this._headContext);
                                if (t2 == null) {
                                    break;
                                } else {
                                    this._currToken = t2;
                                    return t2;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                case 2:
                case 4:
                    boolean returnEnd = this._headContext.isStartHandled();
                    TokenFilter f3 = this._headContext.getFilter();
                    if (!(f3 == null || f3 == TokenFilter.INCLUDE_ALL)) {
                        f3.filterFinishArray();
                    }
                    this._headContext = this._headContext.getParent();
                    this._itemFilter = this._headContext.getFilter();
                    if (!returnEnd) {
                        break;
                    } else {
                        this._currToken = t;
                        return t;
                    }
                case 3:
                    TokenFilter f4 = this._itemFilter;
                    if (f4 == TokenFilter.INCLUDE_ALL) {
                        this._headContext = this._headContext.createChildArrayContext(f4, true);
                        this._currToken = t;
                        return t;
                    } else if (f4 == null) {
                        this.delegate.skipChildren();
                        break;
                    } else {
                        TokenFilter f5 = this._headContext.checkValue(f4);
                        if (f5 == null) {
                            this.delegate.skipChildren();
                            break;
                        } else {
                            if (f5 != TokenFilter.INCLUDE_ALL) {
                                f5 = f5.filterStartArray();
                            }
                            this._itemFilter = f5;
                            if (f5 == TokenFilter.INCLUDE_ALL) {
                                this._headContext = this._headContext.createChildArrayContext(f5, true);
                                this._currToken = t;
                                return t;
                            }
                            this._headContext = this._headContext.createChildArrayContext(f5, false);
                            if (this._includePath) {
                                JsonToken t3 = _nextTokenWithBuffering(this._headContext);
                                if (t3 == null) {
                                    break;
                                } else {
                                    this._currToken = t3;
                                    return t3;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                case 5:
                    String name = this.delegate.getCurrentName();
                    TokenFilter f6 = this._headContext.setFieldName(name);
                    if (f6 == TokenFilter.INCLUDE_ALL) {
                        this._itemFilter = f6;
                        this._currToken = t;
                        return t;
                    } else if (f6 == null) {
                        this.delegate.nextToken();
                        this.delegate.skipChildren();
                        break;
                    } else {
                        TokenFilter f7 = f6.includeProperty(name);
                        if (f7 == null) {
                            this.delegate.nextToken();
                            this.delegate.skipChildren();
                            break;
                        } else {
                            this._itemFilter = f7;
                            if (f7 == TokenFilter.INCLUDE_ALL) {
                                if (!this._includePath) {
                                    break;
                                } else {
                                    this._currToken = t;
                                    return t;
                                }
                            } else if (this._includePath) {
                                JsonToken t4 = _nextTokenWithBuffering(this._headContext);
                                if (t4 == null) {
                                    break;
                                } else {
                                    this._currToken = t4;
                                    return t4;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                default:
                    TokenFilter f8 = this._itemFilter;
                    if (f8 == TokenFilter.INCLUDE_ALL) {
                        this._currToken = t;
                        return t;
                    } else if (f8 != null) {
                        TokenFilter f9 = this._headContext.checkValue(f8);
                        if (f9 == TokenFilter.INCLUDE_ALL || (f9 != null && f9.includeValue(this.delegate))) {
                            this._currToken = t;
                            break;
                        }
                    } else {
                        continue;
                    }
            }
        }
        this._currToken = t;
        return t;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:103:?, code lost:
        return _nextBuffered(r10);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.fasterxml.jackson.core.JsonToken _nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext r10) throws java.io.IOException {
        /*
            r9 = this;
            r6 = 0
            r5 = 1
        L_0x0002:
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            com.fasterxml.jackson.core.JsonToken r4 = r7.nextToken()
            if (r4 != 0) goto L_0x000b
        L_0x000a:
            return r4
        L_0x000b:
            int r7 = r4.mo34327id()
            switch(r7) {
                case 1: goto L_0x0051;
                case 2: goto L_0x009c;
                case 3: goto L_0x001d;
                case 4: goto L_0x009c;
                case 5: goto L_0x00da;
                default: goto L_0x0012;
            }
        L_0x0012:
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r9._itemFilter
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x011e
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        L_0x001d:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r8 = r9._itemFilter
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r7.checkValue(r8)
            if (r0 != 0) goto L_0x002d
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.skipChildren()
            goto L_0x0002
        L_0x002d:
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 == r7) goto L_0x0035
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r0.filterStartArray()
        L_0x0035:
            r9._itemFilter = r0
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x0048
            com.fasterxml.jackson.core.filter.TokenFilterContext r6 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r5 = r6.createChildArrayContext(r0, r5)
            r9._headContext = r5
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        L_0x0048:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r7.createChildArrayContext(r0, r6)
            r9._headContext = r7
            goto L_0x0002
        L_0x0051:
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r9._itemFilter
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x0060
            com.fasterxml.jackson.core.filter.TokenFilterContext r6 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r5 = r6.createChildObjectContext(r0, r5)
            r9._headContext = r5
            goto L_0x000a
        L_0x0060:
            if (r0 != 0) goto L_0x0068
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.skipChildren()
            goto L_0x0002
        L_0x0068:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r7.checkValue(r0)
            if (r0 != 0) goto L_0x0076
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.skipChildren()
            goto L_0x0002
        L_0x0076:
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 == r7) goto L_0x007e
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r0.filterStartObject()
        L_0x007e:
            r9._itemFilter = r0
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x0092
            com.fasterxml.jackson.core.filter.TokenFilterContext r6 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r5 = r6.createChildObjectContext(r0, r5)
            r9._headContext = r5
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        L_0x0092:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r7.createChildObjectContext(r0, r6)
            r9._headContext = r7
            goto L_0x0002
        L_0x009c:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r7.getFilter()
            if (r0 == 0) goto L_0x00ab
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 == r7) goto L_0x00ab
            r0.filterFinishArray()
        L_0x00ab:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            if (r7 != r10) goto L_0x00d6
            r1 = r5
        L_0x00b0:
            if (r1 == 0) goto L_0x00d8
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            boolean r7 = r7.isStartHandled()
            if (r7 == 0) goto L_0x00d8
            r3 = r5
        L_0x00bb:
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r7.getParent()
            r9._headContext = r7
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r7 = r7.getFilter()
            r9._itemFilter = r7
            if (r3 != 0) goto L_0x000a
            if (r1 != 0) goto L_0x00d3
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            if (r7 != r10) goto L_0x0002
        L_0x00d3:
            r4 = 0
            goto L_0x000a
        L_0x00d6:
            r1 = r6
            goto L_0x00b0
        L_0x00d8:
            r3 = r6
            goto L_0x00bb
        L_0x00da:
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            java.lang.String r2 = r7.getCurrentName()
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r7.setFieldName(r2)
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x00f2
            r9._itemFilter = r0
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        L_0x00f2:
            if (r0 != 0) goto L_0x0100
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.nextToken()
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.skipChildren()
            goto L_0x0002
        L_0x0100:
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r0.includeProperty(r2)
            if (r0 != 0) goto L_0x0112
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.nextToken()
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            r7.skipChildren()
            goto L_0x0002
        L_0x0112:
            r9._itemFilter = r0
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 != r7) goto L_0x0002
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        L_0x011e:
            if (r0 == 0) goto L_0x0002
            com.fasterxml.jackson.core.filter.TokenFilterContext r7 = r9._headContext
            com.fasterxml.jackson.core.filter.TokenFilter r0 = r7.checkValue(r0)
            com.fasterxml.jackson.core.filter.TokenFilter r7 = com.fasterxml.jackson.core.filter.TokenFilter.INCLUDE_ALL
            if (r0 == r7) goto L_0x0134
            if (r0 == 0) goto L_0x0002
            com.fasterxml.jackson.core.JsonParser r7 = r9.delegate
            boolean r7 = r0.includeValue(r7)
            if (r7 == 0) goto L_0x0002
        L_0x0134:
            com.fasterxml.jackson.core.JsonToken r4 = r9._nextBuffered(r10)
            goto L_0x000a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.core.filter.FilteringParserDelegate._nextTokenWithBuffering(com.fasterxml.jackson.core.filter.TokenFilterContext):com.fasterxml.jackson.core.JsonToken");
    }

    private JsonToken _nextBuffered(TokenFilterContext buffRoot) throws IOException {
        this._exposedContext = buffRoot;
        TokenFilterContext ctxt = buffRoot;
        JsonToken t = ctxt.nextTokenToRead();
        if (t != null) {
            return t;
        }
        while (ctxt != this._headContext) {
            ctxt = this._exposedContext.findChildOf(ctxt);
            this._exposedContext = ctxt;
            if (ctxt == null) {
                throw _constructError("Unexpected problem: chain of filtered context broken");
            }
            JsonToken t2 = this._exposedContext.nextTokenToRead();
            if (t2 != null) {
                return t2;
            }
        }
        throw _constructError("Internal error: failed to locate expected buffered tokens");
    }

    public JsonToken nextValue() throws IOException {
        JsonToken t = nextToken();
        if (t == JsonToken.FIELD_NAME) {
            return nextToken();
        }
        return t;
    }

    public JsonParser skipChildren() throws IOException {
        if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
            int open = 1;
            while (true) {
                JsonToken t = nextToken();
                if (t == null) {
                    break;
                } else if (t.isStructStart()) {
                    open++;
                } else if (t.isStructEnd()) {
                    open--;
                    if (open == 0) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return this;
    }

    public String getText() throws IOException {
        return this.delegate.getText();
    }

    public boolean hasTextCharacters() {
        return this.delegate.hasTextCharacters();
    }

    public char[] getTextCharacters() throws IOException {
        return this.delegate.getTextCharacters();
    }

    public int getTextLength() throws IOException {
        return this.delegate.getTextLength();
    }

    public int getTextOffset() throws IOException {
        return this.delegate.getTextOffset();
    }

    public BigInteger getBigIntegerValue() throws IOException {
        return this.delegate.getBigIntegerValue();
    }

    public byte getByteValue() throws IOException {
        return this.delegate.getByteValue();
    }

    public short getShortValue() throws IOException {
        return this.delegate.getShortValue();
    }

    public BigDecimal getDecimalValue() throws IOException {
        return this.delegate.getDecimalValue();
    }

    public double getDoubleValue() throws IOException {
        return this.delegate.getDoubleValue();
    }

    public float getFloatValue() throws IOException {
        return this.delegate.getFloatValue();
    }

    public int getIntValue() throws IOException {
        return this.delegate.getIntValue();
    }

    public long getLongValue() throws IOException {
        return this.delegate.getLongValue();
    }

    public NumberType getNumberType() throws IOException {
        return this.delegate.getNumberType();
    }

    public Number getNumberValue() throws IOException {
        return this.delegate.getNumberValue();
    }

    public int getValueAsInt() throws IOException {
        return this.delegate.getValueAsInt();
    }

    public int getValueAsInt(int defaultValue) throws IOException {
        return this.delegate.getValueAsInt(defaultValue);
    }

    public long getValueAsLong() throws IOException {
        return this.delegate.getValueAsLong();
    }

    public long getValueAsLong(long defaultValue) throws IOException {
        return this.delegate.getValueAsLong(defaultValue);
    }

    public String getValueAsString() throws IOException {
        return this.delegate.getValueAsString();
    }

    public String getValueAsString(String defaultValue) throws IOException {
        return this.delegate.getValueAsString(defaultValue);
    }

    public Object getEmbeddedObject() throws IOException {
        return this.delegate.getEmbeddedObject();
    }

    public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
        return this.delegate.getBinaryValue(b64variant);
    }

    public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
        return this.delegate.readBinaryValue(b64variant, out);
    }

    public JsonLocation getTokenLocation() {
        return this.delegate.getTokenLocation();
    }

    /* access modifiers changed from: protected */
    public JsonStreamContext _filterContext() {
        if (this._exposedContext != null) {
            return this._exposedContext;
        }
        return this._headContext;
    }
}
