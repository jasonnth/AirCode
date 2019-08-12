package com.airbnb.airrequest;

import com.airbnb.rxgroups.AutoTaggableObserver;

public abstract class RequestListener<T> extends BaseRequestListener<T> implements AutoTaggableObserver<AirResponse<T>> {
    private String tag;

    public final String getTag() {
        return this.tag;
    }

    public final void setTag(String tag2) {
        this.tag = tag2;
    }
}
