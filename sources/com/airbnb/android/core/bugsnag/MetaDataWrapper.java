package com.airbnb.android.core.bugsnag;

import android.text.TextUtils;
import com.bugsnag.android.MetaData;

public class MetaDataWrapper extends MetaData {
    private String groupingHash;

    public void setGroupingHash(String groupingHash2) {
        this.groupingHash = groupingHash2;
    }

    public String getGroupingHash() {
        return this.groupingHash;
    }

    public boolean hasGroupingHash() {
        return !TextUtils.isEmpty(this.groupingHash);
    }
}
