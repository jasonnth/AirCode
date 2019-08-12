package com.airbnb.android.lib.fragments.inbox.saved_messages;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CreateNewSavedMessageFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CreateNewSavedMessageFragment_ObservableResubscriber(CreateNewSavedMessageFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.addSavedMessagesRequestListener, "CreateNewSavedMessageFragment_addSavedMessagesRequestListener");
        group.resubscribeAll(target.addSavedMessagesRequestListener);
        setTag((AutoTaggableObserver) target.updateSavedMessagesRequestListener, "CreateNewSavedMessageFragment_updateSavedMessagesRequestListener");
        group.resubscribeAll(target.updateSavedMessagesRequestListener);
    }
}
