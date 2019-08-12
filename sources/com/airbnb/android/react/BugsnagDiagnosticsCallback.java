package com.airbnb.android.react;

import com.airbnb.android.core.arguments.P3Arguments;
import com.bugsnag.android.Callback;
import com.bugsnag.android.MetaData;
import com.bugsnag.android.Report;
import com.bugsnag.android.Severity;
import com.facebook.places.model.PlaceFields;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import java.util.HashMap;
import java.util.Map;

class BugsnagDiagnosticsCallback implements Callback {
    static final String NOTIFIER_NAME = "Bugsnag for React Native";
    static final String NOTIFIER_URL = "https://github.com/bugsnag/bugsnag-react-native";
    private final String bugsnagAndroidVersion;
    private final String context;
    private final String groupingHash;
    private final Map<String, Object> metadata;
    private final Severity severity;

    BugsnagDiagnosticsCallback(String bugsnagAndroidVersion2, ReadableMap payload) {
        this.bugsnagAndroidVersion = bugsnagAndroidVersion2;
        this.severity = parseSeverity(payload.getString("severity"));
        this.metadata = readObjectMap(payload.getMap("metadata"));
        if (payload.hasKey(PlaceFields.CONTEXT)) {
            this.context = payload.getString(PlaceFields.CONTEXT);
        } else {
            this.context = null;
        }
        if (payload.hasKey("groupingHash")) {
            this.groupingHash = payload.getString("groupingHash");
        } else {
            this.groupingHash = null;
        }
    }

    /* access modifiers changed from: 0000 */
    public Severity parseSeverity(String value) {
        char c = 65535;
        switch (value.hashCode()) {
            case 3237038:
                if (value.equals("info")) {
                    c = 1;
                    break;
                }
                break;
            case 96784904:
                if (value.equals("error")) {
                    c = 0;
                    break;
                }
                break;
            case 1124446108:
                if (value.equals("warning")) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return Severity.ERROR;
            case 1:
                return Severity.INFO;
            default:
                return Severity.WARNING;
        }
    }

    static Map<String, Object> readObjectMap(ReadableMap map) {
        Map output = new HashMap();
        ReadableMapKeySetIterator iterator = map.keySetIterator();
        while (iterator.hasNextKey()) {
            String key = iterator.nextKey();
            ReadableMap pair = map.getMap(key);
            String string = pair.getString("type");
            char c = 65535;
            switch (string.hashCode()) {
                case -1034364087:
                    if (string.equals("number")) {
                        c = 1;
                        break;
                    }
                    break;
                case -891985903:
                    if (string.equals("string")) {
                        c = 2;
                        break;
                    }
                    break;
                case 107868:
                    if (string.equals(P3Arguments.FROM_MAP)) {
                        c = 3;
                        break;
                    }
                    break;
                case 64711720:
                    if (string.equals("boolean")) {
                        c = 0;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    output.put(key, Boolean.valueOf(pair.getBoolean("value")));
                    break;
                case 1:
                    output.put(key, Double.valueOf(pair.getDouble("value")));
                    break;
                case 2:
                    output.put(key, pair.getString("value"));
                    break;
                case 3:
                    output.put(key, readObjectMap(pair.getMap("value")));
                    break;
            }
        }
        return output;
    }

    public void beforeNotify(Report report) {
        report.setNotifierName(NOTIFIER_NAME);
        report.setNotifierURL(NOTIFIER_URL);
        report.setNotifierVersion(String.format("react-native (Android %s)", new Object[]{this.bugsnagAndroidVersion}));
        report.getError().setSeverity(this.severity);
        if (this.groupingHash != null && this.groupingHash.length() > 0) {
            report.getError().setGroupingHash(this.groupingHash);
        }
        if (this.context != null && this.context.length() > 0) {
            report.getError().setContext(this.context);
        }
        if (this.metadata != null) {
            MetaData reportMetadata = report.getError().getMetaData();
            for (String tab : this.metadata.keySet()) {
                Object value = this.metadata.get(tab);
                if (value instanceof Map) {
                    Map<String, Object> values = (Map) value;
                    for (String key : values.keySet()) {
                        reportMetadata.addToTab(tab, key, values.get(key));
                    }
                }
            }
        }
    }
}
