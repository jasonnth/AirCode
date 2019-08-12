package com.airbnb.p027n2.collections;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import com.airbnb.n2.R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.interfaces.Scrollable;
import com.airbnb.p027n2.interfaces.Scrollable.ScrollViewOnScrollListener;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.p027n2.utils.ViewOffsetHelper;

/* renamed from: com.airbnb.n2.collections.ToolbarCoordinator */
public class ToolbarCoordinator {
    private ValueAnimator animator;
    /* access modifiers changed from: private */
    public int animatorEndColor;
    private final AnimatorUpdateListener animatorListener;
    /* access modifiers changed from: private */
    public int animatorStartColor;
    /* access modifiers changed from: private */
    public final ArgbEvaluator argbEvaluator;
    private View cachedToolbarPusher;
    boolean firstAnimation;
    private int foregoundColorDark;
    private boolean isThemeColors;
    private final int[] locs;
    private int opaqueBackgroundColor;
    private final OnLayoutChangeListener recyclerViewLayoutListener;
    private final OnScrollListener recyclerViewScrollListener;
    private final ScrollViewOnScrollListener scrollViewScrollListener;
    private View scrollableView;
    /* access modifiers changed from: private */
    public AirToolbar toolbar;
    private OnLayoutChangeListener toolbarLayoutListener;
    private ViewOffsetHelper viewOffsetHelper;

    public static ToolbarCoordinator attachToRecyclerView(AirToolbar toolbar2, RecyclerView recyclerView) {
        return new ToolbarCoordinator(toolbar2, recyclerView);
    }

    public static ToolbarCoordinator attachToScrollable(AirToolbar toolbar2, Scrollable<?> scrollable) {
        return new ToolbarCoordinator(toolbar2, scrollable);
    }

    private ToolbarCoordinator(AirToolbar toolbar2, RecyclerView recyclerView) {
        this.recyclerViewLayoutListener = ToolbarCoordinator$$Lambda$1.lambdaFactory$(this);
        this.scrollViewScrollListener = ToolbarCoordinator$$Lambda$2.lambdaFactory$(this);
        this.recyclerViewScrollListener = new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ToolbarCoordinator.this.updateOffset(dy);
            }
        };
        this.locs = new int[2];
        this.argbEvaluator = new ArgbEvaluator();
        this.firstAnimation = true;
        this.isThemeColors = true;
        this.animatorListener = new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                ToolbarCoordinator.this.toolbar.setBackgroundColor(((Integer) ToolbarCoordinator.this.argbEvaluator.evaluate(animation.getAnimatedFraction(), Integer.valueOf(ToolbarCoordinator.this.animatorStartColor), Integer.valueOf(ToolbarCoordinator.this.animatorEndColor))).intValue());
            }
        };
        recyclerView.addOnScrollListener(this.recyclerViewScrollListener);
        init(toolbar2, recyclerView);
    }

    private ToolbarCoordinator(AirToolbar toolbar2, Scrollable<?> scrollable) {
        this.recyclerViewLayoutListener = ToolbarCoordinator$$Lambda$3.lambdaFactory$(this);
        this.scrollViewScrollListener = ToolbarCoordinator$$Lambda$4.lambdaFactory$(this);
        this.recyclerViewScrollListener = new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                ToolbarCoordinator.this.updateOffset(dy);
            }
        };
        this.locs = new int[2];
        this.argbEvaluator = new ArgbEvaluator();
        this.firstAnimation = true;
        this.isThemeColors = true;
        this.animatorListener = new AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                ToolbarCoordinator.this.toolbar.setBackgroundColor(((Integer) ToolbarCoordinator.this.argbEvaluator.evaluate(animation.getAnimatedFraction(), Integer.valueOf(ToolbarCoordinator.this.animatorStartColor), Integer.valueOf(ToolbarCoordinator.this.animatorEndColor))).intValue());
            }
        };
        scrollable.setOnScrollListener(this.scrollViewScrollListener);
        init(toolbar2, scrollable.getView());
    }

    private void init(AirToolbar toolbar2, View scrollableView2) {
        this.toolbar = toolbar2;
        this.scrollableView = scrollableView2;
        this.viewOffsetHelper = new ViewOffsetHelper(toolbar2);
        scrollableView2.addOnLayoutChangeListener(this.recyclerViewLayoutListener);
        this.toolbarLayoutListener = ToolbarCoordinator$$Lambda$5.lambdaFactory$(this);
        toolbar2.addOnLayoutChangeListener(this.toolbarLayoutListener);
        this.foregoundColorDark = ContextCompat.getColor(toolbar2.getContext(), R.color.n2_action_bar_foreground_dark);
        this.opaqueBackgroundColor = ContextCompat.getColor(toolbar2.getContext(), R.color.n2_action_bar_opaque_background);
    }

    static /* synthetic */ void lambda$init$2(ToolbarCoordinator toolbarCoordinator, View v, int l, int t, int r, int b, int ol, int ot, int or, int ob) {
        toolbarCoordinator.viewOffsetHelper.onViewLayout();
        toolbarCoordinator.updateOffset(0);
    }

    public void stop() {
        if (this.animator != null) {
            this.animator.cancel();
        }
        this.viewOffsetHelper.setVerticalOffset(0);
        this.scrollableView.removeOnLayoutChangeListener(this.recyclerViewLayoutListener);
        if (this.scrollableView instanceof RecyclerView) {
            ((RecyclerView) this.scrollableView).removeOnScrollListener(this.recyclerViewScrollListener);
        } else if (this.scrollableView instanceof Scrollable) {
            ((Scrollable) this.scrollableView).removeOnScrollListener(this.scrollViewScrollListener);
        }
        this.toolbar.removeOnLayoutChangeListener(this.toolbarLayoutListener);
    }

    public void notifyThemeChanged() {
        animateIfNeeded();
    }

    /* access modifiers changed from: private */
    public void updateOffset(int dy) {
        if (!ViewCompat.isAttachedToWindow(this.scrollableView)) {
            throw new IllegalStateException("You should call stop() before the scrollable view is detached from the window!");
        }
        View toolbarPusher = getToolbarPusher();
        if (toolbarPusher == null) {
            updateOffsetWithoutToolbarPusher(dy);
        } else {
            updateOffsetWithToolbarPusher(dy, toolbarPusher);
        }
    }

    private void updateOffsetWithoutToolbarPusher(int dy) {
        this.viewOffsetHelper.setVerticalOffset(ViewLibUtils.clamp(this.viewOffsetHelper.getVerticalOffset() - dy, -this.toolbar.getHeight(), 0));
        setIsThemeColors(false);
    }

    private void updateOffsetWithToolbarPusher(int dy, View toolbarPusher) {
        boolean z = true;
        toolbarPusher.getLocationOnScreen(this.locs);
        int toolbarPusherTop = this.locs[1];
        this.toolbar.getLocationOnScreen(this.locs);
        int toolbarBottom = this.locs[1] + this.toolbar.getHeight();
        int offset = this.viewOffsetHelper.getVerticalOffset();
        int height = this.toolbar.getHeight();
        if (dy > 0 && toolbarPusherTop <= toolbarBottom) {
            int offset2 = ViewLibUtils.clamp(this.viewOffsetHelper.getVerticalOffset() + Math.max(toolbarPusherTop - toolbarBottom, -dy), -height, 0);
            this.viewOffsetHelper.setVerticalOffset(offset2);
            toolbarBottom += offset2;
        } else if (dy < 0) {
            if (toolbarPusherTop > toolbarBottom) {
                dy = toolbarBottom - toolbarPusherTop;
            }
            this.viewOffsetHelper.setVerticalOffset(Math.min(offset - dy, 0));
            toolbarBottom -= dy;
        }
        if (toolbarPusherTop < toolbarBottom) {
            z = false;
        }
        setIsThemeColors(z);
    }

    private void setIsThemeColors(boolean isThemeColors2) {
        this.isThemeColors = isThemeColors2;
        animateIfNeeded();
    }

    private void animateIfNeeded() {
        this.toolbar.setForegroundColor(this.isThemeColors ? this.toolbar.getThemeForegroundColor() : this.foregoundColorDark);
        int endBackgroundColor = this.isThemeColors ? this.toolbar.getThemeBackgroundColor() : this.opaqueBackgroundColor;
        if (this.toolbar.getBackgroundColor() != endBackgroundColor && endBackgroundColor != this.animatorEndColor) {
            if (this.animator != null && this.animator.isRunning()) {
                this.animator.cancel();
            }
            this.animatorEndColor = endBackgroundColor;
            this.animatorStartColor = this.toolbar.getBackgroundColor();
            this.animator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration((this.firstAnimation || !this.isThemeColors) ? 0 : 300);
            this.animator.addUpdateListener(this.animatorListener);
            this.animator.start();
            this.firstAnimation = false;
        }
    }

    private View getToolbarPusher() {
        if (this.cachedToolbarPusher != null && !ViewCompat.isAttachedToWindow(this.cachedToolbarPusher)) {
            this.cachedToolbarPusher = null;
        }
        if (this.cachedToolbarPusher == null) {
            this.cachedToolbarPusher = this.scrollableView.findViewById(R.id.n2_toolbar_pusher);
        }
        return this.cachedToolbarPusher;
    }
}
