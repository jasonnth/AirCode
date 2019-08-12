package com.airbnb.android.lib.host.stats.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.StarBar;

public class ReviewStarBreakdownView_ViewBinding implements Unbinder {
    private ReviewStarBreakdownView target;
    private View view2131757630;
    private View view2131757631;
    private View view2131757632;
    private View view2131757633;
    private View view2131757634;

    public ReviewStarBreakdownView_ViewBinding(ReviewStarBreakdownView target2) {
        this(target2, target2);
    }

    public ReviewStarBreakdownView_ViewBinding(final ReviewStarBreakdownView target2, View source) {
        this.target = target2;
        View view = Utils.findRequiredView(source, C0880R.C0882id.one_stars_row, "field 'oneStarbar' and method 'onStarSectionClicked'");
        target2.oneStarbar = (StarBar) Utils.castView(view, C0880R.C0882id.one_stars_row, "field 'oneStarbar'", StarBar.class);
        this.view2131757634 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStarSectionClicked((StarBar) Utils.castParam(p0, "doClick", 0, "onStarSectionClicked", 0));
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.two_stars_row, "field 'twoStarbar' and method 'onStarSectionClicked'");
        target2.twoStarbar = (StarBar) Utils.castView(view2, C0880R.C0882id.two_stars_row, "field 'twoStarbar'", StarBar.class);
        this.view2131757633 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStarSectionClicked((StarBar) Utils.castParam(p0, "doClick", 0, "onStarSectionClicked", 0));
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.three_stars_row, "field 'threeStarbar' and method 'onStarSectionClicked'");
        target2.threeStarbar = (StarBar) Utils.castView(view3, C0880R.C0882id.three_stars_row, "field 'threeStarbar'", StarBar.class);
        this.view2131757632 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStarSectionClicked((StarBar) Utils.castParam(p0, "doClick", 0, "onStarSectionClicked", 0));
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.four_stars_row, "field 'fourStarbar' and method 'onStarSectionClicked'");
        target2.fourStarbar = (StarBar) Utils.castView(view4, C0880R.C0882id.four_stars_row, "field 'fourStarbar'", StarBar.class);
        this.view2131757631 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStarSectionClicked((StarBar) Utils.castParam(p0, "doClick", 0, "onStarSectionClicked", 0));
            }
        });
        View view5 = Utils.findRequiredView(source, C0880R.C0882id.five_stars_row, "field 'fiveStarbar' and method 'onStarSectionClicked'");
        target2.fiveStarbar = (StarBar) Utils.castView(view5, C0880R.C0882id.five_stars_row, "field 'fiveStarbar'", StarBar.class);
        this.view2131757630 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onStarSectionClicked((StarBar) Utils.castParam(p0, "doClick", 0, "onStarSectionClicked", 0));
            }
        });
        target2.divider = Utils.findRequiredView(source, C0880R.C0882id.divider, "field 'divider'");
    }

    public void unbind() {
        ReviewStarBreakdownView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.oneStarbar = null;
        target2.twoStarbar = null;
        target2.threeStarbar = null;
        target2.fourStarbar = null;
        target2.fiveStarbar = null;
        target2.divider = null;
        this.view2131757634.setOnClickListener(null);
        this.view2131757634 = null;
        this.view2131757633.setOnClickListener(null);
        this.view2131757633 = null;
        this.view2131757632.setOnClickListener(null);
        this.view2131757632 = null;
        this.view2131757631.setOnClickListener(null);
        this.view2131757631 = null;
        this.view2131757630.setOnClickListener(null);
        this.view2131757630 = null;
    }
}
