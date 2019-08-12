package com.airbnb.android.core.views;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Parcelable;
import android.support.p000v4.view.PagerAdapter;
import android.support.p000v4.view.ViewPager;
import android.support.p000v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.p027n2.primitives.AirTextView;
import icepick.State;

public class SlidingTabLayout extends HorizontalScrollView {
    private static final int TAB_VIEW_PADDING_DIPS = 16;
    private static final int TAB_VIEW_TEXT_SIZE_SP = 14;
    private static final int TITLE_OFFSET_DIPS = 24;
    @State
    int oldPosition;
    protected OnTabClickedListener onTabClickedListener;
    @State
    Integer tabSelectedTextColor;
    protected final SlidingTabStrip tabStrip;
    @State
    Integer tabUnselectedTextColor;
    protected int tabViewLayoutId;
    private final int titleOffset;
    protected ViewPager viewPager;
    /* access modifiers changed from: private */
    public OnPageChangeListener viewPagerPageChangeListener;

    private class InternalViewPagerListener implements OnPageChangeListener {
        private int mScrollState;

        private InternalViewPagerListener() {
        }

        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            int extraOffset;
            int tabStripChildCount = SlidingTabLayout.this.tabStrip.getChildCount();
            if (tabStripChildCount != 0 && position >= 0 && position < tabStripChildCount) {
                SlidingTabLayout.this.tabStrip.onViewPagerPageChanged(position, positionOffset);
                View selectedTitle = SlidingTabLayout.this.tabStrip.getChildAt(position);
                if (selectedTitle != null) {
                    extraOffset = (int) (((float) selectedTitle.getWidth()) * positionOffset);
                } else {
                    extraOffset = 0;
                }
                SlidingTabLayout.this.scrollToTab(position, extraOffset);
                View oldSelectedTitle = SlidingTabLayout.this.tabStrip.getChildAt(SlidingTabLayout.this.oldPosition);
                if (oldSelectedTitle != null) {
                    oldSelectedTitle.setSelected(false);
                }
                selectedTitle.setSelected(true);
                SlidingTabLayout.this.oldPosition = position;
                if (SlidingTabLayout.this.viewPagerPageChangeListener != null) {
                    SlidingTabLayout.this.viewPagerPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }
            }
        }

        public void onPageScrollStateChanged(int state) {
            this.mScrollState = state;
            if (SlidingTabLayout.this.viewPagerPageChangeListener != null) {
                SlidingTabLayout.this.viewPagerPageChangeListener.onPageScrollStateChanged(state);
            }
        }

        public void onPageSelected(int position) {
            if (this.mScrollState == 0) {
                SlidingTabLayout.this.tabStrip.onViewPagerPageChanged(position, 0.0f);
                SlidingTabLayout.this.scrollToTab(position, 0);
            }
            SlidingTabLayout.this.updateTabColor(SlidingTabLayout.this.tabStrip.getChildAt(SlidingTabLayout.this.oldPosition), false, true);
            SlidingTabLayout.this.updateTabColor(SlidingTabLayout.this.tabStrip.getChildAt(position), true, true);
            if (SlidingTabLayout.this.viewPagerPageChangeListener != null) {
                SlidingTabLayout.this.viewPagerPageChangeListener.onPageSelected(position);
            }
        }
    }

    public interface OnTabClickedListener {
        void onTabClicked(View view, int i);
    }

    public class TabClickListener implements OnClickListener {
        public TabClickListener() {
        }

        public void onClick(View v) {
            int i = 0;
            while (i < SlidingTabLayout.this.tabStrip.getChildCount()) {
                if (v != SlidingTabLayout.this.tabStrip.getChildAt(i)) {
                    i++;
                } else if (SlidingTabLayout.this.viewPager.getCurrentItem() != i) {
                    SlidingTabLayout.this.viewPager.setCurrentItem(i);
                    if (SlidingTabLayout.this.onTabClickedListener != null) {
                        SlidingTabLayout.this.onTabClickedListener.onTabClicked(v, i);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
        }
    }

    public interface TabColorizer {
        int getDividerColor(int i);

        int getIndicatorColor(int i);
    }

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.oldPosition = 0;
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.titleOffset = (int) (24.0f * getResources().getDisplayMetrics().density);
        this.tabStrip = new SlidingTabStrip(context);
        this.tabStrip.setId(1);
        addView(this.tabStrip, -1, -1);
    }

    public Parcelable onSaveInstanceState() {
        return IcepickWrapper.saveInstanceState(this, super.onSaveInstanceState());
    }

    public void onRestoreInstanceState(Parcelable state) {
        super.onRestoreInstanceState(IcepickWrapper.restoreInstanceState(this, state));
    }

    public void setCustomTabColorizer(TabColorizer tabColorizer) {
        this.tabStrip.setCustomTabColorizer(tabColorizer);
    }

    public void setSelectedIndicatorColors(int... colors) {
        this.tabStrip.setSelectedIndicatorColors(colors);
    }

    public void setSelectedIndicatorThickness(int thickness) {
        this.tabStrip.setSelectedIndicatorThickness(thickness);
    }

    public void setDividerColors(int... colors) {
        this.tabStrip.setDividerColors(colors);
    }

    public void showBottomDivider(boolean show) {
        this.tabStrip.showBottomDivider(show);
    }

    public void setOnPageChangeListener(OnPageChangeListener listener) {
        this.viewPagerPageChangeListener = listener;
    }

    public void setOnTabClickedListener(OnTabClickedListener listener) {
        this.onTabClickedListener = listener;
    }

    public void setCustomTabView(int layoutResId) {
        this.tabViewLayoutId = layoutResId;
    }

    public void changeTabTextColor(int selectedColor, int unselectedColor) {
        this.tabSelectedTextColor = Integer.valueOf(selectedColor);
        this.tabUnselectedTextColor = Integer.valueOf(unselectedColor);
        for (int i = 0; i < this.tabStrip.getChildCount(); i++) {
            updateTabColor(this.tabStrip.getChildAt(i), i);
        }
    }

    public void setViewPager(ViewPager viewPager2) {
        this.tabStrip.removeAllViews();
        this.viewPager = viewPager2;
        if (viewPager2 != null) {
            viewPager2.setOnPageChangeListener(new InternalViewPagerListener());
            populateTabStrip();
        }
    }

    /* access modifiers changed from: protected */
    public TextView createDefaultTabView(Context context) {
        TextView textView = new TextView(context);
        textView.setLayoutParams(new LayoutParams(-2, -1));
        textView.setGravity(17);
        textView.setTextSize(2, 14.0f);
        textView.setTypeface(Typeface.DEFAULT_BOLD);
        textView.setTextColor(-1);
        if (VERSION.SDK_INT >= 11) {
            TypedValue outValue = new TypedValue();
            getContext().getTheme().resolveAttribute(16843534, outValue, true);
            textView.setBackgroundResource(outValue.resourceId);
        }
        if (VERSION.SDK_INT >= 14) {
            textView.setAllCaps(true);
        }
        int padding = (int) (16.0f * getResources().getDisplayMetrics().density);
        textView.setPadding(padding, padding, padding, padding);
        return textView;
    }

    public void populateTabStrip() {
        PagerAdapter adapter = this.viewPager.getAdapter();
        OnClickListener tabClickListener = new TabClickListener();
        for (int i = 0; i < adapter.getCount(); i++) {
            View tabView = null;
            TextView tabTitleView = null;
            if (this.tabViewLayoutId != 0) {
                tabView = LayoutInflater.from(getContext()).inflate(this.tabViewLayoutId, this.tabStrip, false);
                updateTabColor(tabView, i);
            }
            if (tabView == null) {
                tabView = createDefaultTabView(getContext());
            }
            if (0 == 0 && TextView.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView;
            }
            if (tabTitleView == null && FrameLayout.class.isInstance(tabView)) {
                tabTitleView = (TextView) tabView.findViewById(C0716R.C0718id.tab_text);
            }
            if (tabTitleView != null) {
                tabTitleView.setText(adapter.getPageTitle(i));
            }
            tabView.setOnClickListener(tabClickListener);
            this.tabStrip.addView(tabView);
        }
    }

    private void updateTabColor(View tabView, int position) {
        boolean z;
        if (position == this.oldPosition) {
            z = true;
        } else {
            z = false;
        }
        updateTabColor(tabView, z, false);
    }

    /* access modifiers changed from: private */
    public void updateTabColor(View tabView, boolean selected, boolean animate) {
        int newTextColor;
        if (tabView instanceof AirTextView) {
            AirTextView tabTextView = (AirTextView) tabView;
            if (this.tabSelectedTextColor != null && selected) {
                newTextColor = this.tabSelectedTextColor.intValue();
            } else if (this.tabUnselectedTextColor != null && !selected) {
                newTextColor = this.tabUnselectedTextColor.intValue();
            } else {
                return;
            }
            if (animate) {
                ValueAnimator colorAnimator = ObjectAnimator.ofInt(tabTextView, "textColor", new int[]{tabTextView.getCurrentTextColor(), newTextColor});
                colorAnimator.setEvaluator(new ArgbEvaluator());
                colorAnimator.setDuration((long) getResources().getInteger(17694720));
                colorAnimator.start();
                return;
            }
            tabTextView.setTextColor(newTextColor);
        }
    }

    public void updateTabIcons() {
        this.tabStrip.removeAllViews();
        populateTabStrip();
        if (this.oldPosition >= this.tabStrip.getChildCount()) {
            this.oldPosition = 0;
        }
        this.tabStrip.getChildAt(this.oldPosition).setSelected(true);
    }

    public View getChildViewAtIndex(int tabIndex) {
        return this.tabStrip.getChildAt(tabIndex);
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.viewPager != null) {
            scrollToTab(this.viewPager.getCurrentItem(), 0);
        }
    }

    /* access modifiers changed from: private */
    public void scrollToTab(int tabIndex, int positionOffset) {
        int tabStripChildCount = this.tabStrip.getChildCount();
        if (tabStripChildCount != 0 && tabIndex >= 0 && tabIndex < tabStripChildCount) {
            View selectedChild = this.tabStrip.getChildAt(tabIndex);
            if (selectedChild != null) {
                int targetScrollX = selectedChild.getLeft() + positionOffset;
                if (tabIndex > 0 || positionOffset > 0) {
                    targetScrollX -= this.titleOffset;
                }
                scrollTo(targetScrollX, 0);
            }
        }
    }
}
