package com.airbnb.android.explore.controllers;

import com.airbnb.rxgroups.AutoTaggableObserver;
import com.airbnb.rxgroups.BaseObservableResubscriber;
import com.airbnb.rxgroups.ObservableGroup;

public class ExploreDataRepositoryImpl_ObservableResubscriber extends BaseObservableResubscriber {
    public ExploreDataRepositoryImpl_ObservableResubscriber(ExploreDataRepositoryImpl target, ObservableGroup group) {
        setTag((AutoTaggableObserver) target.exploreTabsListener, "ExploreDataRepositoryImpl_exploreTabsListener");
        group.resubscribeAll(target.exploreTabsListener);
        setTag((AutoTaggableObserver) target.exploreSpecificTabListener, "ExploreDataRepositoryImpl_exploreSpecificTabListener");
        group.resubscribeAll(target.exploreSpecificTabListener);
        setTag((AutoTaggableObserver) target.playlistRequestListener, "ExploreDataRepositoryImpl_playlistRequestListener");
        group.resubscribeAll(target.playlistRequestListener);
        setTag((AutoTaggableObserver) target.tabMetaDataRequestListener, "ExploreDataRepositoryImpl_tabMetaDataRequestListener");
        group.resubscribeAll(target.tabMetaDataRequestListener);
    }
}
