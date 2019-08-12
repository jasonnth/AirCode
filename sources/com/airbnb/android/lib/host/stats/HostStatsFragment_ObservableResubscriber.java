package com.airbnb.android.lib.host.stats;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class HostStatsFragment_ObservableResubscriber extends BaseObservableResubscriber {
    public HostStatsFragment_ObservableResubscriber(HostStatsFragment target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.listingsListener, "HostStatsFragment_listingsListener");
        group.resubscribeAll(target.listingsListener);
        setTag((AutoTaggableObserver) target.averageRatingListener, "HostStatsFragment_averageRatingListener");
        group.resubscribeAll(target.averageRatingListener);
        setTag((AutoTaggableObserver) target.revenueDetailsListener, "HostStatsFragment_revenueDetailsListener");
        group.resubscribeAll(target.revenueDetailsListener);
        setTag((AutoTaggableObserver) target.superhostListener, "HostStatsFragment_superhostListener");
        group.resubscribeAll(target.superhostListener);
        setTag((AutoTaggableObserver) target.hostStandardsListener, "HostStatsFragment_hostStandardsListener");
        group.resubscribeAll(target.hostStandardsListener);
        setTag((AutoTaggableObserver) target.cohostingStandardsListener, "HostStatsFragment_cohostingStandardsListener");
        group.resubscribeAll(target.cohostingStandardsListener);
        setTag((AutoTaggableObserver) target.demandCountsListener, "HostStatsFragment_demandCountsListener");
        group.resubscribeAll(target.demandCountsListener);
        setTag((AutoTaggableObserver) target.yearlyRevenueDetailsListener, "HostStatsFragment_yearlyRevenueDetailsListener");
        group.resubscribeAll(target.yearlyRevenueDetailsListener);
        setTag((AutoTaggableObserver) target.insightsMetaDataRequestListener, "HostStatsFragment_insightsMetaDataRequestListener");
        group.resubscribeAll(target.insightsMetaDataRequestListener);
    }
}
