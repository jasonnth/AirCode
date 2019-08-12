package com.airbnb.rxgroups;

public interface AutoTaggableObserver<T> extends TaggedObserver<T> {
    void setTag(String str);
}
