package com.airbnb.android.explore.views;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.FloatEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.views.SlidingTabLayout;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.views.MTTripsSearchView.OnProgressChangedListener;
import com.airbnb.android.utils.animation.SimpleAnimatorListener;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class MTExploreMarquee extends RelativeLayout {
    public static final float COLLAPSED_JELLYFISH_TIME_SCALE = 0.25f;
    static final int COLLAPSE_DURATION = 200;
    static final int COLOR_ANIMATION_DURATION = 300;
    static final int EXPAND_DURATION = 400;
    static final int TAB_TRANSLATION_DURATION = 200;
    @BindColor
    int babuColor;
    private float babuTransitionValue;
    @BindView
    View bottomDivider;
    /* access modifiers changed from: private */
    public ValueAnimator colorAnimator;
    private boolean currentBabuMode;
    @BindDimen
    int exploreMarqueeNegativeMargin;
    @BindColor
    int foggyColor;
    @BindView
    JellyfishView jellyfishView;
    /* access modifiers changed from: private */
    public ExploreJitneyLogger jitneyLogger;
    @BindView
    MTTripsSearchInterface searchBar;
    private final OnProgressChangedListener searchBarProgressChangedListener;
    @BindView
    SlidingTabLayout tabLayout;
    @BindColor
    int white80color;
    @BindColor
    int whiteColor;

    public interface ExploreMarqueeChildListener {
        void setBabuMode(boolean z);
    }

    public MTExploreMarquee(Context context) {
        this(context, null);
    }

    public MTExploreMarquee(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MTExploreMarquee(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.searchBarProgressChangedListener = new OnProgressChangedListener() {
            public void onProgressChanged(float progress) {
                MTExploreMarquee.this.jellyfishView.setTimeScale(MTExploreMarquee.this.isCollapsed() ? 0.25f : 1.0f);
                if (MTExploreMarquee.this.jitneyLogger == null) {
                    return;
                }
                if (progress == 0.0f) {
                    MTExploreMarquee.this.jitneyLogger.toggleSearch(false);
                } else if (progress == 1.0f) {
                    MTExploreMarquee.this.jitneyLogger.toggleSearch(true);
                }
            }
        };
        inflate(context, C0857R.layout.mt_explore_marquee, this);
        ButterKnife.bind((View) this);
        this.tabLayout.setSelectedIndicatorColors(this.babuColor);
        this.tabLayout.changeTabTextColor(this.babuColor, this.foggyColor);
        this.tabLayout.setCustomTabView(C0857R.layout.mt_explore_tabview_text);
        this.searchBar.showBottomDivider(false);
        this.searchBar.setExploreMarqueeChildListener(MTExploreMarquee$$Lambda$1.lambdaFactory$(this));
        this.searchBar.setProgressChangeListener(this.searchBarProgressChangedListener);
        setBabuMode(true, false);
    }

    public void setBabuMode(boolean babuMode) {
        setBabuMode(babuMode, true);
    }

    public void setBabuMode(boolean babuMode, boolean animate) {
        if (this.currentBabuMode != babuMode) {
            if (this.colorAnimator != null) {
                this.colorAnimator.cancel();
            }
            this.currentBabuMode = babuMode;
            float[] fArr = new float[2];
            fArr[0] = this.babuTransitionValue;
            fArr[1] = babuMode ? 1.0f : 0.0f;
            this.colorAnimator = ValueAnimator.ofFloat(fArr).setDuration(animate ? 300 : 0);
            this.colorAnimator.addUpdateListener(MTExploreMarquee$$Lambda$2.lambdaFactory$(this));
            this.colorAnimator.addListener(new SimpleAnimatorListener() {
                public void onAnimationEnd(Animator animation) {
                    MTExploreMarquee.this.colorAnimator = null;
                }

                public void onAnimationCancel(Animator animation) {
                    MTExploreMarquee.this.colorAnimator = null;
                }
            });
            this.colorAnimator.start();
        }
    }

    public int getCollapsedHeight() {
        if (this.tabLayout.getVisibility() == 8) {
            return this.searchBar.getCollapsedHeight();
        }
        return this.searchBar.getCollapsedHeight() + this.tabLayout.getHeight() + this.exploreMarqueeNegativeMargin;
    }

    public int getTabsHeight() {
        return this.tabLayout.getHeight();
    }

    public SlidingTabLayout getTabLayout() {
        return this.tabLayout;
    }

    public MTTripsSearchInterface getSearchBar() {
        return this.searchBar;
    }

    public void collapse() {
        this.searchBar.collapse();
    }

    public void expand() {
        this.searchBar.expand();
        setBabuMode(true);
    }

    /* access modifiers changed from: private */
    public void onBabuModeUpdate(float value) {
        this.babuTransitionValue = value;
        int foggyToWhite80 = ((Integer) new ArgbEvaluator().evaluate(value, Integer.valueOf(this.foggyColor), Integer.valueOf(this.white80color))).intValue();
        int babuToWhite = ((Integer) new ArgbEvaluator().evaluate(value, Integer.valueOf(this.babuColor), Integer.valueOf(this.whiteColor))).intValue();
        float dividerAlpha = new FloatEvaluator().evaluate(value, Double.valueOf(0.15d), Integer.valueOf(0)).floatValue();
        this.tabLayout.setSelectedIndicatorColors(babuToWhite);
        this.tabLayout.changeTabTextColor(babuToWhite, foggyToWhite80);
        this.jellyfishView.setAlpha(value);
        this.searchBar.setColorAnimatedProgress(value);
        this.bottomDivider.setAlpha(dividerAlpha);
    }

    public boolean isCollapsed() {
        return this.searchBar.isCollapsed();
    }

    public boolean isExpanded() {
        return this.searchBar.isExpanded();
    }

    public boolean isAnimating() {
        return this.searchBar.isAnimating();
    }

    public void setSearchBarExpandHeight(int height) {
        this.searchBar.setHeight(height);
    }

    public int getSearchBarCurrentHeight() {
        return this.searchBar.getHeight();
    }

    public int getSearchBarExpandedHeight() {
        return this.searchBar.getExpandedHeight();
    }

    public int getSearchBarCollapsedHeight() {
        return this.searchBar.getCollapsedHeight();
    }

    public float getProgress() {
        return this.searchBar.getProgress();
    }

    public int getSearchBarHeight() {
        return this.searchBar.getCollapsedHeight() + this.exploreMarqueeNegativeMargin;
    }

    public void setJitneyLogger(ExploreJitneyLogger jitneyLogger2) {
        this.jitneyLogger = jitneyLogger2;
    }
}
