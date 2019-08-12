package com.airbnb.android.react;

import android.graphics.Rect;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.interfaces.Scrollable.ScrollViewOnScrollListener;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.views.scroll.ReactScrollView;

public class AirbnbReactScrollView extends ReactScrollView implements Scrollable<AirbnbReactScrollView> {
    private boolean ignoreScrollDelta = true;
    private ScrollViewOnScrollListener scrollListener;

    public AirbnbReactScrollView(ReactContext context) {
        super(context);
    }

    public AirbnbReactScrollView getView() {
        return this;
    }

    public void setOnScrollListener(ScrollViewOnScrollListener listener) {
        this.scrollListener = listener;
    }

    /* access modifiers changed from: protected */
    public int computeScrollDeltaToGetChildRectOnScreen(Rect rect) {
        if (this.ignoreScrollDelta) {
            return 0;
        }
        return super.computeScrollDeltaToGetChildRectOnScreen(rect);
    }

    public void removeOnScrollListener(ScrollViewOnScrollListener listener) {
        if (this.scrollListener == listener) {
            this.scrollListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.scrollListener != null) {
            this.scrollListener.onScroll(l, t, oldl, oldt);
        }
    }

    public void setIgnoreScrollDelta(boolean ignoreScrollDelta2) {
        this.ignoreScrollDelta = ignoreScrollDelta2;
    }
}
