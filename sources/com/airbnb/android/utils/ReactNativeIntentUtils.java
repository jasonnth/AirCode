package com.airbnb.android.utils;

import android.os.Bundle;

public final class ReactNativeIntentUtils {
    public static final String REACT_MODULE_NAME = "REACT_MODULE_NAME";
    public static final String REACT_NAVIGATION_TAG = "navigationTag";
    public static final String REACT_PROPS = "REACT_PROPS";

    private ReactNativeIntentUtils() {
    }

    public static Bundle intentExtras(String moduleName, Bundle props) {
        return intentExtras(moduleName, props, null);
    }

    public static Bundle intentExtras(String moduleName, Bundle props, Bundle options) {
        return ((BundleBuilder) ((BundleBuilder) ((BundleBuilder) new BundleBuilder().putString(REACT_MODULE_NAME, moduleName)).putString(REACT_NAVIGATION_TAG, options == null ? null : options.getString(REACT_NAVIGATION_TAG))).putBundle(REACT_PROPS, props)).toBundle();
    }

    public static Bundle optionsForNavigationTag(String navigationTag) {
        return ((BundleBuilder) new BundleBuilder().putString(REACT_NAVIGATION_TAG, navigationTag)).toBundle();
    }
}
