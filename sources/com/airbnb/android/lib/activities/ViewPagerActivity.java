package com.airbnb.android.lib.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import android.support.p000v4.app.FragmentStatePagerAdapter;
import android.support.p000v4.view.ViewPager.SimpleOnPageChangeListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.FtueClickableViewPager;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.primitives.DotsCounter;
import icepick.State;

public abstract class ViewPagerActivity extends AirActivity {
    @BindView
    FixedDualActionFooter actionFooter;
    @State
    int currentPage;
    @BindView
    DotsCounter dotsCounter;
    @BindView
    AirToolbar toolbar;
    @BindView
    FtueClickableViewPager viewPager;

    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public Fragment getItem(int position) {
            return ViewPagerActivity.this.forPage(position);
        }

        public int getCount() {
            return ViewPagerActivity.this.getNumPages();
        }
    }

    public abstract Fragment forPage(int i);

    public abstract int getNumPages();

    public abstract int getPrimaryButtonText();

    public abstract int getSecondaryButtonText();

    public abstract void onPrimaryClicked();

    public abstract void onSecondaryClicked();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.currentPage = 0;
        }
        setContentView(C0880R.layout.activity_view_pager);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        this.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        this.viewPager.setOffscreenPageLimit(getNumPages() - 1);
        setupDotsCounter();
        setupActionFooter();
        if (useModalTransition()) {
            FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
            overridePendingTransition(type.enter, type.exit);
        }
        this.toolbar.setNavigationIcon(getNavigationIcon());
    }

    public void finish() {
        super.finish();
        if (useModalTransition()) {
            FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
            overridePendingTransition(type.popEnter, type.popExit);
        }
    }

    @OnClick
    public void onClick() {
        int nextPage = this.currentPage + 1;
        if (nextPage != getNumPages()) {
            this.viewPager.setCurrentItem(nextPage);
        }
    }

    private void setupDotsCounter() {
        int numDots = getNumPages();
        if (numDots > 1) {
            this.dotsCounter.setVisibility(0);
            this.dotsCounter.setNumDots(numDots);
            this.viewPager.addOnPageChangeListener(new SimpleOnPageChangeListener() {
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    ViewPagerActivity.this.dotsCounter.setSelectedDot(position);
                    ViewPagerActivity.this.currentPage = position;
                    if (position == ViewPagerActivity.this.getNumPages() - 1) {
                        int duration = ViewPagerActivity.this.getResources().getInteger(17694722);
                        ViewPagerActivity.this.showNavBarWithAnimation(duration);
                        ViewPagerActivity.this.hideDotsCounterWithAnimation(duration);
                        return;
                    }
                    ViewPagerActivity.this.showDotsCounter();
                    ViewPagerActivity.this.hideNavBar();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void showDotsCounter() {
        this.dotsCounter.animate().cancel();
        this.dotsCounter.setVisibility(0);
        this.dotsCounter.setAlpha(1.0f);
    }

    private void setupActionFooter() {
        this.actionFooter.setButtonText(getPrimaryButtonText());
        this.actionFooter.setButtonOnClickListener(ViewPagerActivity$$Lambda$1.lambdaFactory$(this));
        this.actionFooter.setSecondaryButtonText(getSecondaryButtonText());
        this.actionFooter.setSecondaryButtonOnClickListener(ViewPagerActivity$$Lambda$2.lambdaFactory$(this));
    }

    /* access modifiers changed from: private */
    public void hideNavBar() {
        this.actionFooter.animate().cancel();
        this.actionFooter.setVisibility(8);
        this.actionFooter.setAlpha(0.0f);
    }

    /* access modifiers changed from: private */
    public void showNavBarWithAnimation(int duration) {
        this.actionFooter.setAlpha(0.0f);
        this.actionFooter.setVisibility(0);
        this.actionFooter.animate().alpha(1.0f).setDuration((long) duration).setListener(null);
    }

    /* access modifiers changed from: private */
    public void hideDotsCounterWithAnimation(int duration) {
        this.dotsCounter.animate().alpha(0.0f).setDuration((long) duration).setListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ViewPagerActivity.this.dotsCounter.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: protected */
    public int getNavigationIcon() {
        return 2;
    }

    /* access modifiers changed from: protected */
    public boolean useModalTransition() {
        return false;
    }
}
