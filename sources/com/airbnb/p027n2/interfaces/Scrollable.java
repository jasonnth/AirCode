package com.airbnb.p027n2.interfaces;

import android.view.View;

/* renamed from: com.airbnb.n2.interfaces.Scrollable */
public interface Scrollable<V extends View> {

    /* renamed from: com.airbnb.n2.interfaces.Scrollable$ScrollViewOnScrollListener */
    public interface ScrollViewOnScrollListener {
        void onScroll(int i, int i2, int i3, int i4);
    }

    V getView();

    void removeOnScrollListener(ScrollViewOnScrollListener scrollViewOnScrollListener);

    void setOnScrollListener(ScrollViewOnScrollListener scrollViewOnScrollListener);
}
