package com.airbnb.android.explore.views;

import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.LinearInterpolator;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.views.OptionalSwipingViewPager;
import com.airbnb.android.core.views.OptionalSwipingViewPager.ViewPagerScrollInterface;
import com.airbnb.android.utils.AndroidVersion;
import com.airbnb.android.utils.RecyclerViewUtils;
import com.airbnb.p027n2.utils.ViewLibUtils;

public class ExploreScrollController {
    /* access modifiers changed from: private */
    public int barOnlyHeight;
    private final View container;
    /* access modifiers changed from: private */
    public final MTExploreMarquee exploreMarquee;
    private boolean layoutParamsAdjusted;
    private RecyclerView recyclerView;
    private final OnScrollListener recyclerViewScrollListener = new OnScrollListener() {
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (ExploreScrollController.this.viewPager != null && newState == 0) {
                ExploreScrollController.this.onScrollCompletion(0.0f);
            }
        }
    };
    /* access modifiers changed from: private */
    public int tabFadeThreshold;
    /* access modifiers changed from: private */
    public OptionalSwipingViewPager viewPager;
    private final ViewPagerScrollInterface viewPagerScrollInterface = new ViewPagerScrollInterface() {
        public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
            if (ExploreScrollController.this.viewPager == null) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("onNestedPreScroll called but viewPager is null"));
                return;
            }
            ExploreMarqueeState state = ExploreScrollController.this.getState();
            if (shouldTranslateBetweenOneBoxAndExpanded(dy, state)) {
                translateBetweenOneBoxAndExpanded(dy, consumed);
            } else if (shouldTranslateBetweenOneBoxAndTabsOnly(dy, state)) {
                translateBetweenOneBoxAndTabsOnly(dy, consumed);
            }
        }

        public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
            return ExploreScrollController.this.onScrollCompletion(velocityY);
        }

        public void onGestureComplete() {
            ExploreScrollController.this.onScrollCompletion(0.0f);
        }

        private boolean shouldTranslateBetweenOneBoxAndTabsOnly(int dy, ExploreMarqueeState state) {
            if (ExploreScrollController.this.hasNoTabs()) {
                return false;
            }
            if (state == ExploreMarqueeState.ONE_BOX && dy > 0) {
                return true;
            }
            if (state == ExploreMarqueeState.BETWEEN_TABS_AND_ONE_BOX) {
                return true;
            }
            if (state != ExploreMarqueeState.TABS || dy >= 0) {
                return false;
            }
            return ExploreScrollController.this.isRecyclerViewAtTop();
        }

        private void translateBetweenOneBoxAndTabsOnly(int dy, int[] consumed) {
            float translationY = ExploreScrollController.this.viewPager.getTranslationY() - ((float) dy);
            float clampedTranslationY = ViewLibUtils.clamp(translationY, (float) (-ExploreScrollController.this.barOnlyHeight), 0.0f);
            ExploreScrollController.this.viewPager.setTranslationY(clampedTranslationY);
            ExploreScrollController.this.exploreMarquee.setTranslationY(clampedTranslationY);
            if (dy > 0 && clampedTranslationY < ((float) ExploreScrollController.this.tabFadeThreshold)) {
                ExploreScrollController.this.exploreMarquee.setBabuMode(false);
            }
            consumed[1] = dy - ((int) (clampedTranslationY - translationY));
        }

        private boolean shouldTranslateBetweenOneBoxAndExpanded(int dy, ExploreMarqueeState state) {
            if ((state == ExploreMarqueeState.ONE_BOX && dy < 0 && ExploreScrollController.this.isRecyclerViewAtTop()) || state == ExploreMarqueeState.BETWEEN_ONE_BOX_AND_EXPANDED) {
                return true;
            }
            if (state != ExploreMarqueeState.EXPANDED || dy <= 0) {
                return false;
            }
            return true;
        }

        private void translateBetweenOneBoxAndExpanded(int dy, int[] consumed) {
            if (ExploreScrollController.this.isRecyclerViewAtTop()) {
                float translationY = ExploreScrollController.this.viewPager.getTranslationY() - ((float) dy);
                float clampedTranslationY = ViewLibUtils.clamp(translationY, 0.0f, (float) (ExploreScrollController.this.exploreMarquee.getSearchBarExpandedHeight() - ExploreScrollController.this.exploreMarquee.getSearchBarCollapsedHeight()));
                int newHeight = ExploreScrollController.this.exploreMarquee.getSearchBarCollapsedHeight() + ((int) clampedTranslationY);
                ExploreScrollController.this.viewPager.setTranslationY(clampedTranslationY);
                ExploreScrollController.this.exploreMarquee.setSearchBarExpandHeight(newHeight);
                consumed[1] = dy - ((int) (clampedTranslationY - translationY));
                return;
            }
            ExploreScrollController.this.exploreMarquee.setSearchBarExpandHeight(ViewLibUtils.clamp(ExploreScrollController.this.exploreMarquee.getSearchBarCurrentHeight() - dy, ExploreScrollController.this.exploreMarquee.getSearchBarCollapsedHeight(), ExploreScrollController.this.exploreMarquee.getSearchBarExpandedHeight()));
        }
    };

    private enum ExploreMarqueeState {
        TABS,
        BETWEEN_TABS_AND_ONE_BOX,
        ONE_BOX,
        BETWEEN_ONE_BOX_AND_EXPANDED,
        EXPANDED
    }

    public ExploreScrollController(MTExploreMarquee exploreMarquee2, View container2) {
        this.exploreMarquee = exploreMarquee2;
        this.container = container2;
    }

    /* access modifiers changed from: private */
    public ExploreMarqueeState getState() {
        if (isTablet()) {
            return ExploreMarqueeState.ONE_BOX;
        }
        if (this.exploreMarquee.isExpanded()) {
            return ExploreMarqueeState.EXPANDED;
        }
        if (!this.exploreMarquee.isCollapsed()) {
            return ExploreMarqueeState.BETWEEN_ONE_BOX_AND_EXPANDED;
        }
        float translationY = this.exploreMarquee.getTranslationY();
        if (translationY >= 0.0f) {
            return ExploreMarqueeState.ONE_BOX;
        }
        if (translationY <= ((float) (-this.barOnlyHeight))) {
            return ExploreMarqueeState.TABS;
        }
        return ExploreMarqueeState.BETWEEN_TABS_AND_ONE_BOX;
    }

    /* access modifiers changed from: private */
    public boolean onScrollCompletion(float velocity) {
        boolean shouldExpand;
        boolean shouldExpand2;
        adjustForExploreMarquee();
        if (velocity == 0.0f && this.exploreMarquee.isAnimating()) {
            return false;
        }
        ExploreMarqueeState state = getState();
        if (state == ExploreMarqueeState.BETWEEN_ONE_BOX_AND_EXPANDED) {
            if (velocity == 0.0f) {
                int collapsedHeight = this.exploreMarquee.getSearchBarCollapsedHeight();
                shouldExpand2 = ((float) this.exploreMarquee.getSearchBarCurrentHeight()) > ((float) (((this.exploreMarquee.getSearchBarExpandedHeight() - collapsedHeight) / 2) + collapsedHeight));
            } else {
                shouldExpand2 = velocity < 0.0f;
            }
            if (shouldExpand2) {
                this.exploreMarquee.expand();
                return false;
            }
            this.exploreMarquee.collapse();
            return true;
        } else if (state == ExploreMarqueeState.BETWEEN_TABS_AND_ONE_BOX) {
            if (hasNoTabs()) {
                shouldExpand = true;
            } else if (velocity == 0.0f) {
                shouldExpand = this.viewPager.getTranslationY() > ((float) ((-this.barOnlyHeight) / 2));
            } else {
                shouldExpand = velocity < 0.0f;
            }
            animateBetweenOneBoxAndTabs(shouldExpand);
            return false;
        } else if (state == ExploreMarqueeState.ONE_BOX) {
            if (velocity < 0.0f && isRecyclerViewAtTop()) {
                this.exploreMarquee.expand();
                return false;
            } else if (velocity <= 0.0f || hasNoTabs()) {
                return false;
            } else {
                animateBetweenOneBoxAndTabs(false);
                return false;
            }
        } else if (state == ExploreMarqueeState.TABS && velocity < 0.0f) {
            animateBetweenOneBoxAndTabs(true);
            return false;
        } else if (state != ExploreMarqueeState.EXPANDED || velocity <= 0.0f) {
            return false;
        } else {
            this.exploreMarquee.collapse();
            return true;
        }
    }

    public void setRecyclerView(RecyclerView recyclerView2) {
        if (this.recyclerView != recyclerView2) {
            if (this.recyclerView != null) {
                this.recyclerView.removeOnScrollListener(this.recyclerViewScrollListener);
            }
            this.recyclerView = recyclerView2;
            if (recyclerView2 != null) {
                recyclerView2.addOnScrollListener(this.recyclerViewScrollListener);
            }
        }
    }

    public void adjustForExploreMarquee() {
        adjustLayoutParams();
        if (this.viewPager != null) {
            if (this.container.getPaddingTop() != this.exploreMarquee.getCollapsedHeight()) {
                ViewLibUtils.setPaddingTop(this.container, this.exploreMarquee.getCollapsedHeight());
            }
            float value = ((float) (this.exploreMarquee.getHeight() - this.exploreMarquee.getCollapsedHeight())) + this.exploreMarquee.getTranslationY();
            boolean notScrolling = this.recyclerView == null || this.recyclerView.getScrollState() == 0;
            if (this.viewPager.getTranslationY() > value || (notScrolling && isRecyclerViewAtTop())) {
                this.viewPager.setTranslationY(value);
                return;
            }
            return;
        }
        ExploreMarqueeState state = getState();
        if ((state == ExploreMarqueeState.ONE_BOX || state == ExploreMarqueeState.EXPANDED) && this.container.getPaddingTop() != this.exploreMarquee.getCollapsedHeight()) {
            ViewLibUtils.setPaddingTop(this.container, this.exploreMarquee.getCollapsedHeight());
        } else if (state == ExploreMarqueeState.TABS && this.container.getPaddingTop() != this.exploreMarquee.getTabsHeight()) {
            ViewLibUtils.setPaddingTop(this.container, this.exploreMarquee.getTabsHeight());
        }
    }

    public void setViewPager(OptionalSwipingViewPager viewPager2) {
        clearViewPager();
        if (!isTablet()) {
            this.viewPager = viewPager2;
            viewPager2.setViewPagerScrollInterface(this.viewPagerScrollInterface);
        }
    }

    public void clearViewPager() {
        if (this.viewPager != null) {
            this.viewPager.setViewPagerScrollInterface(null);
        }
        this.viewPager = null;
        setRecyclerView(null);
    }

    private void adjustLayoutParams() {
        if (hasNoTabs()) {
            unAdjustLayoutParams();
            if (getState() == ExploreMarqueeState.TABS && this.viewPager != null) {
                jumpBetweenOneBoxAndTabs(true);
            }
        } else if (!this.layoutParamsAdjusted) {
            this.barOnlyHeight = this.exploreMarquee.getSearchBarHeight();
            this.tabFadeThreshold = (-this.barOnlyHeight) / 2;
            this.layoutParamsAdjusted = true;
            setCustomLayoutMargins(this.barOnlyHeight);
        }
    }

    private void unAdjustLayoutParams() {
        if (this.layoutParamsAdjusted) {
            this.layoutParamsAdjusted = false;
            setCustomLayoutMargins(0);
        }
    }

    private void setCustomLayoutMargins(int amount) {
        if (AndroidVersion.isAtLeastLollipop()) {
            MarginLayoutParams lp = (MarginLayoutParams) this.container.getLayoutParams();
            lp.topMargin = -amount;
            this.container.setLayoutParams(lp);
            if (this.viewPager != null) {
                MarginLayoutParams lp2 = (MarginLayoutParams) this.viewPager.getLayoutParams();
                lp2.topMargin = amount;
                lp2.bottomMargin = -amount;
                this.viewPager.setLayoutParams(lp2);
            }
        }
    }

    /* access modifiers changed from: private */
    public boolean isRecyclerViewAtTop() {
        return RecyclerViewUtils.isAtTop(this.recyclerView);
    }

    private void animateBetweenOneBoxAndTabs(boolean expand) {
        float targetTranslationY = getTargetTranslationY(expand);
        this.viewPager.animate().setInterpolator(new LinearInterpolator()).setDuration(200).translationY(targetTranslationY);
        this.exploreMarquee.animate().setInterpolator(new LinearInterpolator()).setDuration(200).translationY(targetTranslationY);
        if (!expand) {
            this.exploreMarquee.setBabuMode(false);
        }
    }

    private void jumpBetweenOneBoxAndTabs(boolean expand) {
        float targetTranslationY = getTargetTranslationY(expand);
        this.viewPager.setTranslationY(targetTranslationY);
        this.exploreMarquee.setTranslationY(targetTranslationY);
    }

    private float getTargetTranslationY(boolean expand) {
        if (expand) {
            return 0.0f;
        }
        return (float) (-this.barOnlyHeight);
    }

    /* access modifiers changed from: private */
    public boolean hasNoTabs() {
        return this.viewPager == null || this.viewPager.getAdapter().getCount() <= 1 || !ViewLibUtils.isVisible(this.exploreMarquee.getTabLayout());
    }

    private boolean isTablet() {
        return this.exploreMarquee.getSearchBar() instanceof MTTripsTabletSearchView;
    }
}
