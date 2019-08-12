package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.IOException;

public class StackTraceElementDeserializer extends StdScalarDeserializer<StackTraceElement> {
    public StackTraceElementDeserializer() {
        super(StackTraceElement.class);
    }

    public StackTraceElement deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.START_OBJECT) {
            String className = "";
            String methodName = "";
            String fileName = "";
            String moduleName = null;
            String moduleVersion = null;
            int lineNumber = -1;
            while (true) {
                JsonToken t2 = p.nextValue();
                if (t2 == JsonToken.END_OBJECT) {
                    return constructValue(ctxt, className, methodName, fileName, lineNumber, moduleName, moduleVersion);
                }
                String propName = p.getCurrentName();
                if ("className".equals(propName)) {
                    className = p.getText();
                } else if ("fileName".equals(propName)) {
                    fileName = p.getText();
                } else if ("lineNumber".equals(propName)) {
                    if (t2.isNumeric()) {
                        lineNumber = p.getIntValue();
                    } else {
                        return (StackTraceElement) ctxt.handleUnexpectedToken(handledType(), t2, p, "Non-numeric token (%s) for property 'lineNumber'", t2);
                    }
                } else if ("methodName".equals(propName)) {
                    methodName = p.getText();
                } else if (!"nativeMethod".equals(propName)) {
                    if ("moduleName".equals(propName)) {
                        moduleName = p.getText();
                    } else if ("moduleVersion".equals(propName)) {
                        moduleVersion = p.getText();
                    } else {
                        handleUnknownProperty(p, ctxt, this._valueClass, propName);
                    }
                }
            }
        } else if (t != JsonToken.START_ARRAY || !ctxt.isEnabled(DeserializationFeature.UNWRAP_SINGLE_VALUE_ARRAYS)) {
            return (StackTraceElement) ctxt.handleUnexpectedToken(this._valueClass, p);
        } else {
            p.nextToken();
            StackTraceElement value = deserialize(p, ctxt);
            if (p.nextToken() != JsonToken.END_ARRAY) {
                handleMissingEndArrayForSingle(p, ctxt);
            }
            return value;
        }
    }

    /* access modifiers changed from: protected */
    public StackTraceElement constructValue(DeserializationContext ctxt, String className, String methodName, String fileName, int lineNumber, String moduleName, String moduleVersion) {
        return new StackTraceElement(className, methodName, fileName, lineNumber);
    }
}
