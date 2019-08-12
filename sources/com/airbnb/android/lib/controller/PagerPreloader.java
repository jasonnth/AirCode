package com.airbnb.android.lib.controller;

import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import com.bumptech.glide.ListPreloader;
import com.bumptech.glide.ListPreloader.PreloadModelProvider;
import com.bumptech.glide.ListPreloader.PreloadSizeProvider;

class PagerPreloader<T> implements OnPageChangeListener {
    private static final int UNKNOWN_SCROLL_STATE = Integer.MIN_VALUE;
    private final ListPreloader<T> listPreloader;
    private final int totalItemCount;
    private final int visibleItemCount;

    PagerPreloader(int visibleItemCount2, int totalItemCount2, PreloadModelProvider<T> preloadModelProvider, PreloadSizeProvider<T> preloadDimensionProvider, int maxPreload) {
        this.listPreloader = new ListPreloader<>(preloadModelProvider, preloadDimensionProvider, maxPreload);
        this.visibleItemCount = visibleItemCount2;
        this.totalItemCount = totalItemCount2;
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.listPreloader.onScroll(null, position, this.visibleItemCount, this.totalItemCount);
    }

    public void onPageSelected(int position) {
    }

    public void onPageScrollStateChanged(int state) {
        int listViewState;
        switch (state) {
            case 0:
                listViewState = 0;
                break;
            case 1:
                listViewState = 1;
                break;
            case 2:
                listViewState = 2;
                break;
            default:
                listViewState = Integer.MIN_VALUE;
                break;
        }
        this.listPreloader.onScrollStateChanged(null, listViewState);
    }
}
