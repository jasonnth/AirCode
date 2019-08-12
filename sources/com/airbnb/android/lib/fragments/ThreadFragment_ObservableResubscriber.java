package com.airbnb.android.lib.fragments;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ThreadFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public ThreadFragment_ObservableResubscriber(ThreadFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.reservationsRequestListener, "ThreadFragment_reservationsRequestListener");
        group.resubscribeAll(target.reservationsRequestListener);
        setTag((AutoTaggableObserver) target.loadThreadRequestListener, "ThreadFragment_loadThreadRequestListener");
        group.resubscribeAll(target.loadThreadRequestListener);
        setTag((AutoTaggableObserver) target.userFlagListener, "ThreadFragment_userFlagListener");
        group.resubscribeAll(target.userFlagListener);
        setTag((AutoTaggableObserver) target.messageTranslationResponseRequestListener, "ThreadFragment_messageTranslationResponseRequestListener");
        group.resubscribeAll(target.messageTranslationResponseRequestListener);
        setTag((AutoTaggableObserver) target.archiveListener, "ThreadFragment_archiveListener");
        group.resubscribeAll(target.archiveListener);
        setTag((AutoTaggableObserver) target.updateUserBlockListener, "ThreadFragment_updateUserBlockListener");
        group.resubscribeAll(target.updateUserBlockListener);
    }
}
