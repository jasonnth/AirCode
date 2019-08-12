package com.airbnb.p027n2.collections;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.interfaces.Scrollable.ScrollViewOnScrollListener;

/* renamed from: com.airbnb.n2.collections.VerboseScrollView */
public class VerboseScrollView extends ScrollView implements Scrollable<VerboseScrollView> {
    private ScrollViewOnScrollListener mScrollListener;

    public VerboseScrollView getView() {
        return this;
    }

    public VerboseScrollView(Context context) {
        super(context);
    }

    public VerboseScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VerboseScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setOnScrollListener(ScrollViewOnScrollListener listener) {
        this.mScrollListener = listener;
    }

    public void removeOnScrollListener(ScrollViewOnScrollListener listener) {
        if (this.mScrollListener == listener) {
            this.mScrollListener = null;
        }
    }

    /* access modifiers changed from: protected */
    public void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (this.mScrollListener != null) {
            this.mScrollListener.onScroll(l, t, oldl, oldt);
        }
    }
}
