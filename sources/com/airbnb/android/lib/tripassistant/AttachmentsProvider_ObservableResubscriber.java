package com.airbnb.android.lib.tripassistant;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class AttachmentsProvider_ObservableResubscriber extends BaseObservableResubscriber {
    public AttachmentsProvider_ObservableResubscriber(AttachmentsProvider target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.attachmentListener, "AttachmentsProvider_attachmentListener");
        group.resubscribeAll(target.attachmentListener);
    }
}
