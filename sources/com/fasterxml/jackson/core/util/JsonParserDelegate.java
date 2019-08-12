package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.FormatSchema;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonParser.NumberType;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.ObjectCodec;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class JsonParserDelegate extends JsonParser {
    protected JsonParser delegate;

    public JsonParserDelegate(JsonParser d) {
        this.delegate = d;
    }

    public void setCurrentValue(Object v) {
        this.delegate.setCurrentValue(v);
    }

    public ObjectCodec getCodec() {
        return this.delegate.getCodec();
    }

    public JsonParser enable(Feature f) {
        this.delegate.enable(f);
        return this;
    }

    public boolean isEnabled(Feature f) {
        return this.delegate.isEnabled(f);
    }

    @Deprecated
    public JsonParser setFeatureMask(int mask) {
        this.delegate.setFeatureMask(mask);
        return this;
    }

    public JsonParser overrideStdFeatures(int values, int mask) {
        this.delegate.overrideStdFeatures(values, mask);
        return this;
    }

    public JsonParser overrideFormatFeatures(int values, int mask) {
        this.delegate.overrideFormatFeatures(values, mask);
        return this;
    }

    public void setSchema(FormatSchema schema) {
        this.delegate.setSchema(schema);
    }

    public boolean requiresCustomCodec() {
        return this.delegate.requiresCustomCodec();
    }

    public void close() throws IOException {
        this.delegate.close();
    }

    public JsonToken currentToken() {
        return this.delegate.currentToken();
    }

    public JsonToken getCurrentToken() {
        return this.delegate.getCurrentToken();
    }

    public int getCurrentTokenId() {
        return this.delegate.getCurrentTokenId();
    }

    public boolean hasCurrentToken() {
        return this.delegate.hasCurrentToken();
    }

    public boolean hasTokenId(int id) {
        return this.delegate.hasTokenId(id);
    }

    public boolean hasToken(JsonToken t) {
        return this.delegate.hasToken(t);
    }

    public String getCurrentName() throws IOException {
        return this.delegate.getCurrentName();
    }

    public JsonLocation getCurrentLocation() {
        return this.delegate.getCurrentLocation();
    }

    public JsonStreamContext getParsingContext() {
        return this.delegate.getParsingContext();
    }

    public boolean isExpectedStartArrayToken() {
        return this.delegate.isExpectedStartArrayToken();
    }

    public boolean isExpectedStartObjectToken() {
        return this.delegate.isExpectedStartObjectToken();
    }

    public void clearCurrentToken() {
        this.delegate.clearCurrentToken();
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

    public JsonToken nextToken() throws IOException {
        return this.delegate.nextToken();
    }

    public JsonToken nextValue() throws IOException {
        return this.delegate.nextValue();
    }

    public JsonParser skipChildren() throws IOException {
        this.delegate.skipChildren();
        return this;
    }

    public boolean canReadObjectId() {
        return this.delegate.canReadObjectId();
    }

    public boolean canReadTypeId() {
        return this.delegate.canReadTypeId();
    }

    public Object getObjectId() throws IOException {
        return this.delegate.getObjectId();
    }

    public Object getTypeId() throws IOException {
        return this.delegate.getTypeId();
    }
}
