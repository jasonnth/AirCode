package com.airbnb.android.core.fragments;

import android.support.design.widget.TabLayout;
import android.support.p000v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LottieNuxViewPagerFragment_ViewBinding implements Unbinder {
    private LottieNuxViewPagerFragment target;

    public LottieNuxViewPagerFragment_ViewBinding(LottieNuxViewPagerFragment target2, View source) {
        this.target = target2;
        target2.animationView = (LottieAnimationView) Utils.findRequiredViewAsType(source, C0716R.C0718id.lottie_animation_view, "field 'animationView'", LottieAnimationView.class);
        target2.viewPager = (ViewPager) Utils.findRequiredViewAsType(source, C0716R.C0718id.view_pager, "field 'viewPager'", ViewPager.class);
        target2.dotsCounter = (TabLayout) Utils.findRequiredViewAsType(source, C0716R.C0718id.dots_counter, "field 'dotsCounter'", TabLayout.class);
        target2.nextButton = (AirButton) Utils.findRequiredViewAsType(source, C0716R.C0718id.next_button, "field 'nextButton'", AirButton.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        LottieNuxViewPagerFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.animationView = null;
        target2.viewPager = null;
        target2.dotsCounter = null;
        target2.nextButton = null;
        target2.toolbar = null;
    }
}
