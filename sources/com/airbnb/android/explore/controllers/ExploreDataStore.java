package com.airbnb.android.explore.controllers;

import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.models.ExplorePlaylist;
import com.airbnb.android.core.p008mt.data.TabMap;

public class ExploreDataStore implements ComponentCallbacks {
    private ExplorePlaylist currentPlaylist;
    private TabMap seeAllTabs;
    private TabMap tabs;

    public ExploreDataStore() {
        CoreApplication.appContext().registerComponentCallbacks(this);
    }

    public void saveTabs(TabMap tabs2) {
        this.tabs = tabs2;
    }

    public void saveSeeAllTabs(TabMap seeAllTabs2) {
        this.seeAllTabs = seeAllTabs2;
    }

    public void saveCurrentPlaylist(ExplorePlaylist currentPlaylist2) {
        this.currentPlaylist = currentPlaylist2;
    }

    public TabMap restoreTabs() {
        return this.tabs != null ? this.tabs : new TabMap();
    }

    public TabMap restoreSeeAllTabs() {
        return this.seeAllTabs != null ? this.seeAllTabs : new TabMap();
    }

    public ExplorePlaylist restoreCurrentPlaylist() {
        return this.currentPlaylist;
    }

    public void onConfigurationChanged(Configuration newConfig) {
    }

    public void onLowMemory() {
        this.tabs = null;
        this.seeAllTabs = null;
        this.currentPlaylist = null;
    }
}
