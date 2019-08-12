package com.airbnb.android.thread.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ThreadBlockInfoFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ThreadBlockInfoFragment_ObservableResubscriber(ThreadBlockInfoFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createUserBlockListener, "ThreadBlockInfoFragment_createUserBlockListener");
        group.resubscribeAll(target.createUserBlockListener);
    }
}
