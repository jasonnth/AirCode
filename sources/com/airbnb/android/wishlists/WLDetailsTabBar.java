package com.airbnb.android.wishlists;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.p027n2.interfaces.DividerView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class WLDetailsTabBar extends FrameLayout implements DividerView {
    @BindView
    View divider;
    private List<WLTab> lastTabs = Collections.emptyList();
    @BindView
    TabLayout tabLayout;

    public WLDetailsTabBar(Context context) {
        super(context);
        init();
    }

    public WLDetailsTabBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WLDetailsTabBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        inflate(getContext(), C1758R.layout.wl_details_tab_bar, this);
        ButterKnife.bind((View) this);
    }

    public void bind(List<WLTab> tabs, int selectedPosition, OnTabSelectedListener onTabSelectedListener) {
        this.tabLayout.clearOnTabSelectedListeners();
        if (!this.lastTabs.equals(tabs)) {
            setNewTabs(tabs);
        }
        if (selectedPosition != this.tabLayout.getSelectedTabPosition()) {
            this.tabLayout.getTabAt(selectedPosition).select();
        }
        this.tabLayout.addOnTabSelectedListener(onTabSelectedListener);
        this.lastTabs = ImmutableList.copyOf((Collection<? extends E>) tabs);
    }

    private void setNewTabs(List<WLTab> tabs) {
        this.tabLayout.removeAllTabs();
        for (WLTab tab : tabs) {
            this.tabLayout.addTab(this.tabLayout.newTab().setCustomView(C1758R.layout.wl_details_tab_bar_item).setText(tab.titleRes));
        }
    }

    public void showDivider(boolean showDivider) {
        ViewLibUtils.setVisibleIf(this.divider, showDivider);
    }
}
