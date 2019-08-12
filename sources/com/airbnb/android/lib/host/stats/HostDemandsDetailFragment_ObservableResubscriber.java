package com.airbnb.android.lib.host.stats;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostDemandsDetailFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostDemandsDetailFragment_ObservableResubscriber(HostDemandsDetailFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.similarListingsListener, "HostDemandsDetailFragment_similarListingsListener");
        group.resubscribeAll(target.similarListingsListener);
        setTag((AutoTaggableObserver) target.demandDetailsListener, "HostDemandsDetailFragment_demandDetailsListener");
        group.resubscribeAll(target.demandDetailsListener);
    }
}
