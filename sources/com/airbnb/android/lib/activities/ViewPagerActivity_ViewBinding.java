package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.FtueClickableViewPager;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.airbnb.p027n2.primitives.DotsCounter;

public class ViewPagerActivity_ViewBinding implements Unbinder {
    private ViewPagerActivity target;
    private View view2131755480;

    public ViewPagerActivity_ViewBinding(ViewPagerActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ViewPagerActivity_ViewBinding(final ViewPagerActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.clickable_view_pager, "field 'viewPager' and method 'onClick'");
        target2.viewPager = (FtueClickableViewPager) Utils.castView(view, C0880R.C0882id.clickable_view_pager, "field 'viewPager'", FtueClickableViewPager.class);
        this.view2131755480 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClick();
            }
        });
        target2.dotsCounter = (DotsCounter) Utils.findRequiredViewAsType(source, C0880R.C0882id.dots_counter, "field 'dotsCounter'", DotsCounter.class);
        target2.actionFooter = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C0880R.C0882id.action_footer, "field 'actionFooter'", FixedDualActionFooter.class);
    }

    public void unbind() {
        ViewPagerActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.viewPager = null;
        target2.dotsCounter = null;
        target2.actionFooter = null;
        this.view2131755480.setOnClickListener(null);
        this.view2131755480 = null;
    }
}
