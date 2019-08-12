package com.airbnb.android.lib.activities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.BottomBarBanner;
import com.roughike.bottombar.BottomBar;

public class HomeActivity_ViewBinding implements Unbinder {
    private HomeActivity target;
    private View view2131755435;

    public HomeActivity_ViewBinding(HomeActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public HomeActivity_ViewBinding(final HomeActivity target2, View source) {
        this.target = target2;
        target2.modeTransitionLayout = (AppModeTransitionLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.mode_transition_layout, "field 'modeTransitionLayout'", AppModeTransitionLayout.class);
        target2.bottomBarContainer = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.bottom_bar_container, "field 'bottomBarContainer'", ViewGroup.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.bottom_bar_banner, "field 'bottomBarBanner' and method 'onBottomBarBannerClicked'");
        target2.bottomBarBanner = (BottomBarBanner) Utils.castView(view, C0880R.C0882id.bottom_bar_banner, "field 'bottomBarBanner'", BottomBarBanner.class);
        this.view2131755435 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onBottomBarBannerClicked();
            }
        });
        target2.bottomBar = (BottomBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.bottom_bar, "field 'bottomBar'", BottomBar.class);
        target2.container = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.container, "field 'container'", FrameLayout.class);
    }

    public void unbind() {
        HomeActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.modeTransitionLayout = null;
        target2.bottomBarContainer = null;
        target2.bottomBarBanner = null;
        target2.bottomBar = null;
        target2.container = null;
        this.view2131755435.setOnClickListener(null);
        this.view2131755435 = null;
    }
}
