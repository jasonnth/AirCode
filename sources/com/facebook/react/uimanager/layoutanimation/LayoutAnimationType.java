package com.facebook.react.uimanager.layoutanimation;

import com.airbnb.android.core.analytics.BaseAnalytics;

enum LayoutAnimationType {
    CREATE("create"),
    UPDATE(BaseAnalytics.UPDATE),
    DELETE("delete");
    
    private final String mName;

    private LayoutAnimationType(String name) {
        this.mName = name;
    }

    public String toString() {
        return this.mName;
    }
}
