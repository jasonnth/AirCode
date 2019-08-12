package com.airbnb.p027n2.internal;

import android.support.p002v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.airbnb.p027n2.internal.ComponentVisitor.OnComponentDisplayListener;
import com.google.common.collect.ImmutableSet;

/* renamed from: com.airbnb.n2.internal.ComponentManager */
public class ComponentManager {
    private static final ImmutableSet<String> CONTAINERS = ImmutableSet.m1301of("com.airbnb.android.core.views.AirbnbSlidingTabLayout", "com.airbnb.android.core.views.AirSwipeRefreshLayout", "com.airbnb.android.core.views.AirWebView", "com.airbnb.android.core.views.ClickableViewPager", "com.airbnb.android.core.views.guestpicker.GuestsPickerSheetWithButtonView", "com.airbnb.android.core.views.LoopingViewPager", "com.airbnb.android.core.views.OptionalSwipingViewPager");
    private static final ImmutableSet<String> IGNORED_NAMESPACES = ImmutableSet.m1299of("com.facebook.react.");
    private ViewGroup contentView;
    private final OnGlobalLayoutListener globalLayoutListener = ComponentManager$$Lambda$1.lambdaFactory$(this);
    private final OnScrollChangedListener scrollChangedListener = ComponentManager$$Lambda$2.lambdaFactory$(this);
    private ComponentVisitor visitor;

    public void onCreate(AppCompatActivity activity, OnComponentDisplayListener listener) {
        this.visitor = new ComponentVisitor(activity);
        this.visitor.setWhitelistedContainers(CONTAINERS);
        this.visitor.setIgnoredNamespaces(IGNORED_NAMESPACES);
        this.visitor.setOnComponentDisplayListener(listener);
        this.contentView = (ViewGroup) activity.findViewById(16908290);
        this.contentView.getViewTreeObserver().addOnGlobalLayoutListener(this.globalLayoutListener);
        this.contentView.getViewTreeObserver().addOnScrollChangedListener(this.scrollChangedListener);
    }

    public void onDestroy(AppCompatActivity activity) {
        if (this.contentView != null && this.contentView.getViewTreeObserver().isAlive()) {
            this.contentView.getViewTreeObserver().removeOnGlobalLayoutListener(this.globalLayoutListener);
            this.contentView.getViewTreeObserver().removeOnScrollChangedListener(this.scrollChangedListener);
        }
        if (this.visitor != null) {
            this.visitor.destroy();
        }
    }

    /* access modifiers changed from: private */
    public void startTraversalDebounced() {
        this.visitor.startTraversalDebounced(this.contentView, 1000);
    }
}
