package com.airbnb.android.react;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;
import com.facebook.react.bridge.ReadableMap;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReactExposedActivityParams {
    private static final Class<Bundle> DEFAULT_CLASS = Bundle.class;
    private final Class<? extends Parcelable> argumentType;
    private final String key;
    private final Class<? extends Activity> klass;
    private final ObjectMapper objectMapper;

    public ReactExposedActivityParams(ObjectMapper objectMapper2, String key2, Class<? extends Activity> klass2) {
        this(objectMapper2, key2, klass2, DEFAULT_CLASS);
    }

    public ReactExposedActivityParams(ObjectMapper objectMapper2, String key2, Class<? extends Activity> klass2, Class<? extends Parcelable> argumentType2) {
        this.objectMapper = objectMapper2;
        this.key = key2;
        this.klass = klass2;
        this.argumentType = argumentType2;
    }

    /* access modifiers changed from: 0000 */
    public Intent toIntent(Context context, ReadableMap arguments) {
        Intent intent = new Intent(context, this.klass);
        if (this.argumentType.equals(DEFAULT_CLASS)) {
            intent.putExtras(ConversionUtil.toBundle(arguments));
        } else {
            intent.putExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT, (Parcelable) ConversionUtil.toType(this.objectMapper, arguments, this.argumentType));
        }
        return intent;
    }

    /* access modifiers changed from: 0000 */
    public String key() {
        return this.key;
    }
}
