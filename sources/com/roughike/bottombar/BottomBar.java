package com.roughike.bottombar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.CoordinatorLayout;
import android.support.p000v4.content.ContextCompat;
import android.support.p000v4.view.ViewCompat;
import android.support.p000v4.view.ViewPropertyAnimatorListenerAdapter;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.roughike.bottombar.BottomBarTab.Config;
import com.roughike.bottombar.BottomBarTab.Config.Builder;
import java.util.List;

public class BottomBar extends LinearLayout implements OnClickListener, OnLongClickListener {
    private int activeShiftingItemWidth;
    private float activeTabAlpha;
    private int activeTabColor;
    /* access modifiers changed from: private */
    public View backgroundOverlay;
    private int badgeBackgroundColor;
    private int behaviors;
    private int currentBackgroundColor;
    private int currentTabPosition;
    private int defaultBackgroundColor = -1;
    private boolean ignoreTabReselectionListener;
    private int inActiveShiftingItemWidth;
    private float inActiveTabAlpha;
    private int inActiveTabColor;
    private boolean isComingFromRestoredState;
    private boolean isTabletMode;
    private int maxFixedItemWidth;
    private boolean navBarAccountedHeightCalculated;
    private int nineDp;
    private OnTabReselectListener onTabReselectListener;
    private OnTabSelectListener onTabSelectListener;
    /* access modifiers changed from: private */
    public ViewGroup outerContainer;
    private int primaryColor;
    private int screenWidth;
    private View shadowView;
    private boolean showShadow;
    private boolean shyHeightAlreadyCalculated;
    private ViewGroup tabContainer;
    private int tabXmlResource;
    private int titleTextAppearance;
    private Typeface titleTypeFace;

    public BottomBar(Context context) {
        super(context);
        init(context, null);
    }

    public BottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        addItems(this.tabXmlResource);
    }

    private void init(Context context, AttributeSet attrs) {
        populateAttributes(context, attrs);
        initializeViews();
        determineInitialBackgroundColor();
    }

    private void populateAttributes(Context context, AttributeSet attrs) {
        int defaultInActiveColor;
        int defaultActiveColor = -1;
        float f = 1.0f;
        this.primaryColor = MiscUtils.getColor(getContext(), R.attr.colorPrimary);
        this.screenWidth = MiscUtils.getScreenWidth(getContext());
        this.nineDp = MiscUtils.dpToPixel(getContext(), 9.0f);
        this.maxFixedItemWidth = MiscUtils.dpToPixel(getContext(), 168.0f);
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.BottomBar, 0, 0);
        try {
            this.tabXmlResource = ta.getResourceId(R.styleable.BottomBar_bb_tabXmlResource, 0);
            this.isTabletMode = ta.getBoolean(R.styleable.BottomBar_bb_tabletMode, false);
            this.behaviors = ta.getInteger(R.styleable.BottomBar_bb_behavior, 0);
            int i = R.styleable.BottomBar_bb_inActiveTabAlpha;
            if (isShiftingMode()) {
                f = 0.6f;
            }
            this.inActiveTabAlpha = ta.getFloat(i, f);
            this.activeTabAlpha = ta.getFloat(R.styleable.BottomBar_bb_activeTabAlpha, 1.0f);
            if (isShiftingMode()) {
                defaultInActiveColor = -1;
            } else {
                defaultInActiveColor = ContextCompat.getColor(context, R.color.bb_inActiveBottomBarItemColor);
            }
            if (!isShiftingMode()) {
                defaultActiveColor = this.primaryColor;
            }
            this.inActiveTabColor = ta.getColor(R.styleable.BottomBar_bb_inActiveTabColor, defaultInActiveColor);
            this.activeTabColor = ta.getColor(R.styleable.BottomBar_bb_activeTabColor, defaultActiveColor);
            this.badgeBackgroundColor = ta.getColor(R.styleable.BottomBar_bb_badgeBackgroundColor, -65536);
            this.titleTextAppearance = ta.getResourceId(R.styleable.BottomBar_bb_titleTextAppearance, 0);
            this.titleTypeFace = getTypeFaceFromAsset(ta.getString(R.styleable.BottomBar_bb_titleTypeFace));
            this.showShadow = ta.getBoolean(R.styleable.BottomBar_bb_showShadow, true);
        } finally {
            ta.recycle();
        }
    }

    private boolean isShiftingMode() {
        return !this.isTabletMode && hasBehavior(1);
    }

    private boolean noLabels() {
        return !this.isTabletMode && hasBehavior(8);
    }

    private boolean drawUnderNav() {
        return !this.isTabletMode && hasBehavior(4) && NavbarUtils.shouldDrawBehindNavbar(getContext());
    }

    private boolean isShy() {
        return !this.isTabletMode && hasBehavior(2);
    }

    private boolean hasBehavior(int behavior) {
        return (this.behaviors | behavior) == this.behaviors;
    }

    private Typeface getTypeFaceFromAsset(String fontPath) {
        if (fontPath != null) {
            return Typeface.createFromAsset(getContext().getAssets(), fontPath);
        }
        return null;
    }

    private void initializeViews() {
        int width;
        int height;
        if (this.isTabletMode) {
            width = -2;
        } else {
            width = -1;
        }
        if (this.isTabletMode) {
            height = -1;
        } else {
            height = -2;
        }
        LayoutParams params = new LayoutParams(width, height);
        setLayoutParams(params);
        setOrientation(this.isTabletMode ? 0 : 1);
        ViewCompat.setElevation(this, (float) MiscUtils.dpToPixel(getContext(), 8.0f));
        View rootView = inflate(getContext(), this.isTabletMode ? R.layout.bb_bottom_bar_item_container_tablet : R.layout.bb_bottom_bar_item_container, this);
        rootView.setLayoutParams(params);
        this.backgroundOverlay = rootView.findViewById(R.id.bb_bottom_bar_background_overlay);
        this.outerContainer = (ViewGroup) rootView.findViewById(R.id.bb_bottom_bar_outer_container);
        this.tabContainer = (ViewGroup) rootView.findViewById(R.id.bb_bottom_bar_item_container);
        this.shadowView = rootView.findViewById(R.id.bb_bottom_bar_shadow);
        if (!this.showShadow) {
            this.shadowView.setVisibility(8);
        }
    }

    private void determineInitialBackgroundColor() {
        boolean userHasDefinedBackgroundColor;
        if (isShiftingMode()) {
            this.defaultBackgroundColor = this.primaryColor;
        }
        Drawable userDefinedBackground = getBackground();
        if (userDefinedBackground == null || !(userDefinedBackground instanceof ColorDrawable)) {
            userHasDefinedBackgroundColor = false;
        } else {
            userHasDefinedBackgroundColor = true;
        }
        if (userHasDefinedBackgroundColor) {
            this.defaultBackgroundColor = ((ColorDrawable) userDefinedBackground).getColor();
            setBackgroundColor(0);
        }
    }

    public void setItems(int xmlRes) {
        this.tabContainer.removeAllViews();
        addItems(xmlRes);
    }

    public void addItems(int xmlRes) {
        addItems(xmlRes, null);
    }

    public void addItems(int xmlRes, Config defaultTabConfig) {
        if (xmlRes == 0) {
            throw new RuntimeException("No items specified for the BottomBar!");
        }
        if (defaultTabConfig == null) {
            defaultTabConfig = getTabConfig();
        }
        updateItems(new TabParser(getContext(), defaultTabConfig, xmlRes).getTabs());
    }

    private Config getTabConfig() {
        return new Builder().inActiveTabAlpha(this.inActiveTabAlpha).activeTabAlpha(this.activeTabAlpha).inActiveTabColor(this.inActiveTabColor).activeTabColor(this.activeTabColor).barColorWhenSelected(this.defaultBackgroundColor).badgeBackgroundColor(this.badgeBackgroundColor).titleTextAppearance(this.titleTextAppearance).titleTypeFace(this.titleTypeFace).build();
    }

    private void updateItems(List<BottomBarTab> bottomBarItems) {
        Type type;
        int index = 0;
        int biggestWidth = 0;
        BottomBarTab[] viewsToAdd = new BottomBarTab[bottomBarItems.size()];
        for (BottomBarTab bottomBarTab : bottomBarItems) {
            if (isShiftingMode()) {
                type = Type.SHIFTING;
            } else if (this.isTabletMode) {
                type = Type.TABLET;
            } else if (noLabels()) {
                type = Type.NO_LABELS;
            } else {
                type = Type.FIXED;
            }
            bottomBarTab.setType(type);
            bottomBarTab.prepareLayout();
            if (index == this.currentTabPosition) {
                bottomBarTab.select(false);
                handleBackgroundColorChange(bottomBarTab, false);
            } else {
                bottomBarTab.deselect(false);
            }
            if (!this.isTabletMode) {
                if (bottomBarTab.getWidth() > biggestWidth) {
                    biggestWidth = bottomBarTab.getWidth();
                }
                viewsToAdd[index] = bottomBarTab;
            } else {
                this.tabContainer.addView(bottomBarTab);
            }
            bottomBarTab.setOnClickListener(this);
            bottomBarTab.setOnLongClickListener(this);
            index++;
        }
        if (!this.isTabletMode) {
            resizeTabsToCorrectSizes(bottomBarItems, viewsToAdd);
        }
    }

    private void resizeTabsToCorrectSizes(List<BottomBarTab> bottomBarItems, BottomBarTab[] viewsToAdd) {
        LayoutParams params;
        int proposedItemWidth = Math.min(MiscUtils.dpToPixel(getContext(), (float) (this.screenWidth / bottomBarItems.size())), this.maxFixedItemWidth);
        this.inActiveShiftingItemWidth = (int) (((double) proposedItemWidth) * 0.9d);
        this.activeShiftingItemWidth = (int) (((double) proposedItemWidth) + (((double) proposedItemWidth) * ((double) bottomBarItems.size()) * 0.1d));
        int height = Math.round(getContext().getResources().getDimension(R.dimen.bb_height));
        for (BottomBarTab bottomBarTab : viewsToAdd) {
            if (!isShiftingMode()) {
                params = new LayoutParams(proposedItemWidth, height);
            } else if (bottomBarTab.isActive()) {
                params = new LayoutParams(this.activeShiftingItemWidth, height);
            } else {
                params = new LayoutParams(this.inActiveShiftingItemWidth, height);
            }
            bottomBarTab.setLayoutParams(params);
            this.tabContainer.addView(bottomBarTab);
        }
    }

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        setOnTabSelectListener(true, listener);
    }

    public void setOnTabSelectListener(boolean selectTab, OnTabSelectListener listener) {
        this.onTabSelectListener = listener;
        if (selectTab && this.onTabSelectListener != null && getTabCount() > 0) {
            listener.onTabSelected(getCurrentTabId());
        }
    }

    public void setOnTabReselectListener(OnTabReselectListener listener) {
        this.onTabReselectListener = listener;
    }

    public void setDefaultTab(int defaultTabId) {
        setDefaultTabPosition(findPositionForTabWithId(defaultTabId));
    }

    public void setDefaultTabPosition(int defaultTabPosition) {
        if (!this.isComingFromRestoredState) {
            selectTabAtPosition(defaultTabPosition);
        }
    }

    public void selectTabWithId(int tabResId) {
        selectTabAtPosition(findPositionForTabWithId(tabResId));
    }

    public void selectTabAtPosition(int position) {
        if (position > getTabCount() - 1 || position < 0) {
            throw new IndexOutOfBoundsException("Can't select tab at position " + position + ". This BottomBar has no items at that position.");
        }
        selectTabAtPosition(position, false);
    }

    public int getTabCount() {
        return this.tabContainer.getChildCount();
    }

    public BottomBarTab getCurrentTab() {
        return getTabAtPosition(getCurrentTabPosition());
    }

    public BottomBarTab getTabAtPosition(int position) {
        View child = this.tabContainer.getChildAt(position);
        if (child instanceof FrameLayout) {
            return findTabInLayout((FrameLayout) child);
        }
        return (BottomBarTab) child;
    }

    public int getCurrentTabId() {
        return getCurrentTab().getId();
    }

    public int getCurrentTabPosition() {
        return this.currentTabPosition;
    }

    public int findPositionForTabWithId(int tabId) {
        return getTabWithId(tabId).getIndexInTabContainer();
    }

    public BottomBarTab getTabWithId(int tabId) {
        return (BottomBarTab) this.tabContainer.findViewById(tabId);
    }

    public void setInActiveTabAlpha(float alpha) {
        this.inActiveTabAlpha = alpha;
        refreshTabs();
    }

    public void setActiveTabAlpha(float alpha) {
        this.activeTabAlpha = alpha;
        refreshTabs();
    }

    public void setInActiveTabColor(int color) {
        this.inActiveTabColor = color;
        refreshTabs();
    }

    public void setActiveTabColor(int color) {
        this.activeTabColor = color;
        refreshTabs();
    }

    public void setBadgeBackgroundColor(int color) {
        this.badgeBackgroundColor = color;
        refreshTabs();
    }

    public void setTabTitleTextAppearance(int textAppearance) {
        this.titleTextAppearance = textAppearance;
        refreshTabs();
    }

    public void setTabTitleTypeface(String fontPath) {
        setTabTitleTypeface(getTypeFaceFromAsset(fontPath));
    }

    public void setTabTitleTypeface(Typeface typeface) {
        this.titleTypeFace = typeface;
        refreshTabs();
    }

    private void refreshTabs() {
        if (getTabCount() > 0) {
            Config newConfig = getTabConfig();
            for (int i = 0; i < getTabCount(); i++) {
                getTabAtPosition(i).setConfig(newConfig);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            updateTitleBottomPadding();
            if (isShy()) {
                initializeShyBehavior();
            }
            if (drawUnderNav()) {
                resizeForDrawingUnderNavbar();
            }
        }
    }

    private void updateTitleBottomPadding() {
        if (this.tabContainer != null) {
            int childCount = getTabCount();
            for (int i = 0; i < childCount; i++) {
                TextView title = (TextView) this.tabContainer.getChildAt(i).findViewById(R.id.bb_bottom_bar_title);
                if (title != null) {
                    int missingPadding = this.nineDp - (title.getHeight() - title.getBaseline());
                    if (missingPadding > 0) {
                        title.setPadding(title.getPaddingLeft(), title.getPaddingTop(), title.getPaddingRight(), title.getPaddingBottom() + missingPadding);
                    }
                }
            }
        }
    }

    private void initializeShyBehavior() {
        ViewParent parent = getParent();
        if (!(parent != null && (parent instanceof CoordinatorLayout))) {
            throw new RuntimeException("In order to have shy behavior, the BottomBar must be a direct child of a CoordinatorLayout.");
        } else if (!this.shyHeightAlreadyCalculated) {
            int height = getHeight();
            if (height != 0) {
                updateShyHeight(height);
                this.shyHeightAlreadyCalculated = true;
            }
        }
    }

    private void updateShyHeight(int height) {
        ((CoordinatorLayout.LayoutParams) getLayoutParams()).setBehavior(new BottomNavigationBehavior(height, 0, false));
    }

    private void resizeForDrawingUnderNavbar() {
        if (VERSION.SDK_INT >= 19) {
            int currentHeight = getHeight();
            if (currentHeight != 0 && !this.navBarAccountedHeightCalculated) {
                this.navBarAccountedHeightCalculated = true;
                this.tabContainer.getLayoutParams().height = currentHeight;
                int finalHeight = currentHeight + NavbarUtils.getNavbarHeight(getContext());
                getLayoutParams().height = finalHeight;
                if (isShy()) {
                    updateShyHeight(finalHeight);
                }
            }
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = saveState();
        bundle.putParcelable("superstate", super.onSaveInstanceState());
        return bundle;
    }

    /* access modifiers changed from: 0000 */
    public Bundle saveState() {
        Bundle outState = new Bundle();
        outState.putInt("STATE_CURRENT_SELECTED_TAB", this.currentTabPosition);
        return outState;
    }

    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            restoreState(bundle);
            state = bundle.getParcelable("superstate");
        }
        super.onRestoreInstanceState(state);
    }

    /* access modifiers changed from: 0000 */
    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            this.isComingFromRestoredState = true;
            this.ignoreTabReselectionListener = true;
            selectTabAtPosition(savedInstanceState.getInt("STATE_CURRENT_SELECTED_TAB", this.currentTabPosition), false);
        }
    }

    public void onClick(View v) {
        handleClick(v);
    }

    public boolean onLongClick(View v) {
        return handleLongClick(v);
    }

    private BottomBarTab findTabInLayout(ViewGroup child) {
        for (int i = 0; i < child.getChildCount(); i++) {
            View candidate = child.getChildAt(i);
            if (candidate instanceof BottomBarTab) {
                return (BottomBarTab) candidate;
            }
        }
        return null;
    }

    private void handleClick(View v) {
        BottomBarTab oldTab = getCurrentTab();
        BottomBarTab newTab = (BottomBarTab) v;
        oldTab.deselect(true);
        newTab.select(true);
        shiftingMagic(oldTab, newTab, true);
        handleBackgroundColorChange(newTab, true);
        updateSelectedTab(newTab.getIndexInTabContainer());
    }

    private boolean handleLongClick(View v) {
        if (v instanceof BottomBarTab) {
            BottomBarTab longClickedTab = (BottomBarTab) v;
            if ((isShiftingMode() || this.isTabletMode) && !longClickedTab.isActive()) {
                Toast.makeText(getContext(), longClickedTab.getTitle(), 0).show();
            }
        }
        return true;
    }

    private void selectTabAtPosition(int position, boolean animate) {
        BottomBarTab oldTab = getCurrentTab();
        BottomBarTab newTab = getTabAtPosition(position);
        if (oldTab != null) {
            oldTab.deselect(animate);
        }
        newTab.select(animate);
        updateSelectedTab(position);
        shiftingMagic(oldTab, newTab, animate);
        handleBackgroundColorChange(newTab, false);
    }

    private void updateSelectedTab(int newPosition) {
        int newTabId = getTabAtPosition(newPosition).getId();
        if (newPosition != this.currentTabPosition) {
            if (this.onTabSelectListener != null) {
                this.onTabSelectListener.onTabSelected(newTabId);
            }
        } else if (this.onTabReselectListener != null && !this.ignoreTabReselectionListener) {
            this.onTabReselectListener.onTabReSelected(newTabId);
        }
        this.currentTabPosition = newPosition;
        if (this.ignoreTabReselectionListener) {
            this.ignoreTabReselectionListener = false;
        }
    }

    private void shiftingMagic(BottomBarTab oldTab, BottomBarTab newTab, boolean animate) {
        if (isShiftingMode()) {
            oldTab.updateWidth((float) this.inActiveShiftingItemWidth, animate);
            newTab.updateWidth((float) this.activeShiftingItemWidth, animate);
        }
    }

    private void handleBackgroundColorChange(BottomBarTab tab, boolean animate) {
        int newColor = tab.getBarColorWhenSelected();
        if (this.currentBackgroundColor != newColor) {
            if (!animate) {
                this.outerContainer.setBackgroundColor(newColor);
                return;
            }
            View clickedView = tab;
            if (tab.hasActiveBadge()) {
                clickedView = tab.getOuterView();
            }
            animateBGColorChange(clickedView, newColor);
            this.currentBackgroundColor = newColor;
        }
    }

    private void animateBGColorChange(View clickedView, int newColor) {
        prepareForBackgroundColorAnimation(newColor);
        if (VERSION.SDK_INT < 21) {
            backgroundCrossfadeAnimation(newColor);
        } else if (this.outerContainer.isAttachedToWindow()) {
            backgroundCircularRevealAnimation(clickedView, newColor);
        }
    }

    private void prepareForBackgroundColorAnimation(int newColor) {
        this.outerContainer.clearAnimation();
        this.backgroundOverlay.clearAnimation();
        this.backgroundOverlay.setBackgroundColor(newColor);
        this.backgroundOverlay.setVisibility(0);
    }

    @TargetApi(21)
    private void backgroundCircularRevealAnimation(View clickedView, final int newColor) {
        Animator animator = ViewAnimationUtils.createCircularReveal(this.backgroundOverlay, (int) (ViewCompat.getX(clickedView) + ((float) (clickedView.getMeasuredWidth() / 2))), (this.isTabletMode ? (int) ViewCompat.getY(clickedView) : 0) + (clickedView.getMeasuredHeight() / 2), (float) 0, (float) (this.isTabletMode ? this.outerContainer.getHeight() : this.outerContainer.getWidth()));
        if (this.isTabletMode) {
            animator.setDuration(500);
        }
        animator.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animation) {
                onEnd();
            }

            public void onAnimationCancel(Animator animation) {
                onEnd();
            }

            private void onEnd() {
                BottomBar.this.outerContainer.setBackgroundColor(newColor);
                BottomBar.this.backgroundOverlay.setVisibility(4);
                ViewCompat.setAlpha(BottomBar.this.backgroundOverlay, 1.0f);
            }
        });
        animator.start();
    }

    private void backgroundCrossfadeAnimation(final int newColor) {
        ViewCompat.setAlpha(this.backgroundOverlay, 0.0f);
        ViewCompat.animate(this.backgroundOverlay).alpha(1.0f).setListener(new ViewPropertyAnimatorListenerAdapter() {
            public void onAnimationEnd(View view) {
                onEnd();
            }

            public void onAnimationCancel(View view) {
                onEnd();
            }

            private void onEnd() {
                BottomBar.this.outerContainer.setBackgroundColor(newColor);
                BottomBar.this.backgroundOverlay.setVisibility(4);
                ViewCompat.setAlpha(BottomBar.this.backgroundOverlay, 1.0f);
            }
        }).start();
    }
}
