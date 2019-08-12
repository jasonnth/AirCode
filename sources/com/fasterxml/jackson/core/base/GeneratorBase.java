package com.fasterxml.jackson.core.base;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonGenerator.Feature;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.json.DupDetector;
import com.fasterxml.jackson.core.json.JsonWriteContext;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import org.spongycastle.asn1.eac.CertificateBody;

public abstract class GeneratorBase extends JsonGenerator {
    protected static final int DERIVED_FEATURES_MASK = ((Feature.WRITE_NUMBERS_AS_STRINGS.getMask() | Feature.ESCAPE_NON_ASCII.getMask()) | Feature.STRICT_DUPLICATE_DETECTION.getMask());
    protected boolean _cfgNumbersAsStrings;
    protected boolean _closed;
    protected int _features;
    protected ObjectCodec _objectCodec;
    protected JsonWriteContext _writeContext;

    /* access modifiers changed from: protected */
    public abstract void _releaseBuffers();

    /* access modifiers changed from: protected */
    public abstract void _verifyValueWrite(String str) throws IOException;

    protected GeneratorBase(int features, ObjectCodec codec) {
        this._features = features;
        this._objectCodec = codec;
        this._writeContext = JsonWriteContext.createRootContext(Feature.STRICT_DUPLICATE_DETECTION.enabledIn(features) ? DupDetector.rootDetector((JsonGenerator) this) : null);
        this._cfgNumbersAsStrings = Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(features);
    }

    public void setCurrentValue(Object v) {
        this._writeContext.setCurrentValue(v);
    }

    public final boolean isEnabled(Feature f) {
        return (this._features & f.getMask()) != 0;
    }

    public int getFeatureMask() {
        return this._features;
    }

    public JsonGenerator disable(Feature f) {
        int mask = f.getMask();
        this._features &= mask ^ -1;
        if ((DERIVED_FEATURES_MASK & mask) != 0) {
            if (f == Feature.WRITE_NUMBERS_AS_STRINGS) {
                this._cfgNumbersAsStrings = false;
            } else if (f == Feature.ESCAPE_NON_ASCII) {
                setHighestNonEscapedChar(0);
            } else if (f == Feature.STRICT_DUPLICATE_DETECTION) {
                this._writeContext = this._writeContext.withDupDetector(null);
            }
        }
        return this;
    }

    @Deprecated
    public JsonGenerator setFeatureMask(int newMask) {
        int changed = newMask ^ this._features;
        this._features = newMask;
        if (changed != 0) {
            _checkStdFeatureChanges(newMask, changed);
        }
        return this;
    }

    public JsonGenerator overrideStdFeatures(int values, int mask) {
        int oldState = this._features;
        int newState = ((mask ^ -1) & oldState) | (values & mask);
        int changed = oldState ^ newState;
        if (changed != 0) {
            this._features = newState;
            _checkStdFeatureChanges(newState, changed);
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public void _checkStdFeatureChanges(int newFeatureFlags, int changedFeatures) {
        if ((DERIVED_FEATURES_MASK & changedFeatures) != 0) {
            this._cfgNumbersAsStrings = Feature.WRITE_NUMBERS_AS_STRINGS.enabledIn(newFeatureFlags);
            if (Feature.ESCAPE_NON_ASCII.enabledIn(changedFeatures)) {
                if (Feature.ESCAPE_NON_ASCII.enabledIn(newFeatureFlags)) {
                    setHighestNonEscapedChar(CertificateBody.profileType);
                } else {
                    setHighestNonEscapedChar(0);
                }
            }
            if (!Feature.STRICT_DUPLICATE_DETECTION.enabledIn(changedFeatures)) {
                return;
            }
            if (!Feature.STRICT_DUPLICATE_DETECTION.enabledIn(newFeatureFlags)) {
                this._writeContext = this._writeContext.withDupDetector(null);
            } else if (this._writeContext.getDupDetector() == null) {
                this._writeContext = this._writeContext.withDupDetector(DupDetector.rootDetector((JsonGenerator) this));
            }
        }
    }

    public JsonStreamContext getOutputContext() {
        return this._writeContext;
    }

    public void writeStartObject(Object forValue) throws IOException {
        writeStartObject();
        if (!(this._writeContext == null || forValue == null)) {
            this._writeContext.setCurrentValue(forValue);
        }
        setCurrentValue(forValue);
    }

    public void writeFieldName(SerializableString name) throws IOException {
        writeFieldName(name.getValue());
    }

    public void writeString(SerializableString text) throws IOException {
        writeString(text.getValue());
    }

    public void writeRawValue(String text) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(text);
    }

    public void writeRawValue(SerializableString text) throws IOException {
        _verifyValueWrite("write raw value");
        writeRaw(text);
    }

    public int writeBinary(Base64Variant b64variant, InputStream data, int dataLength) throws IOException {
        _reportUnsupportedOperation();
        return 0;
    }

    public void writeObject(Object value) throws IOException {
        if (value == null) {
            writeNull();
        } else if (this._objectCodec != null) {
            this._objectCodec.writeValue(this, value);
        } else {
            _writeSimpleObject(value);
        }
    }

    public void close() throws IOException {
        this._closed = true;
    }

    /* access modifiers changed from: protected */
    public String _asString(BigDecimal value) throws IOException {
        if (!Feature.WRITE_BIGDECIMAL_AS_PLAIN.enabledIn(this._features)) {
            return value.toString();
        }
        int scale = value.scale();
        if (scale < -9999 || scale > 9999) {
            _reportError(String.format("Attempt to write plain `java.math.BigDecimal` (see JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN) with illegal scale (%d): needs to be between [-%d, %d]", new Object[]{Integer.valueOf(scale), Integer.valueOf(9999), Integer.valueOf(9999)}));
        }
        return value.toPlainString();
    }

    /* access modifiers changed from: protected */
    public final int _decodeSurrogate(int surr1, int surr2) throws IOException {
        if (surr2 < 56320 || surr2 > 57343) {
            _reportError("Incomplete surrogate pair: first char 0x" + Integer.toHexString(surr1) + ", second 0x" + Integer.toHexString(surr2));
        }
        return 65536 + ((surr1 - 55296) << 10) + (surr2 - 56320);
    }
}
