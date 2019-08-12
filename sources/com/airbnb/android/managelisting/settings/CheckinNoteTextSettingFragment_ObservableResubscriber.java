package com.airbnb.android.managelisting.settings;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class CheckinNoteTextSettingFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public CheckinNoteTextSettingFragment_ObservableResubscriber(CheckinNoteTextSettingFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.createStepListener, "CheckinNoteTextSettingFragment_createStepListener");
        group.resubscribeAll(target.createStepListener);
        setTag((AutoTaggableObserver) target.updateStepListener, "CheckinNoteTextSettingFragment_updateStepListener");
        group.resubscribeAll(target.updateStepListener);
        setTag((AutoTaggableObserver) target.refreshGuideListener, "CheckinNoteTextSettingFragment_refreshGuideListener");
        group.resubscribeAll(target.refreshGuideListener);
    }
}
