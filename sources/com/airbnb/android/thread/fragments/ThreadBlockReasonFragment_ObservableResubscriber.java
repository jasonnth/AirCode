package com.airbnb.android.thread.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ThreadBlockReasonFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ThreadBlockReasonFragment_ObservableResubscriber(ThreadBlockReasonFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.userFlagDetailsRequestListener, "ThreadBlockReasonFragment_userFlagDetailsRequestListener");
        group.resubscribeAll(target.userFlagDetailsRequestListener);
        setTag((AutoTaggableObserver) target.createUserFlagListener, "ThreadBlockReasonFragment_createUserFlagListener");
        group.resubscribeAll(target.createUserFlagListener);
    }
}
