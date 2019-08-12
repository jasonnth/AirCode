package com.airbnb.android.listyourspacedls.fragments;

import android.view.View;
import android.widget.Space;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.ImpactMarquee;
import com.airbnb.p027n2.components.StandardButtonRow;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSLandingFragment_ViewBinding implements Unbinder {
    private LYSLandingFragment target;
    private View view2131755577;
    private View view2131755582;
    private View view2131755583;
    private View view2131755584;
    private View view2131755586;

    public LYSLandingFragment_ViewBinding(final LYSLandingFragment target2, View source) {
        this.target = target2;
        target2.landingMarquee = (ImpactMarquee) Utils.findRequiredViewAsType(source, C7251R.C7253id.landing_marquee, "field 'landingMarquee'", ImpactMarquee.class);
        View view = Utils.findRequiredView(source, C7251R.C7253id.landing_basics, "field 'landingBasicsRow' and method 'onClickBasicsRow'");
        target2.landingBasicsRow = (StandardButtonRow) Utils.castView(view, C7251R.C7253id.landing_basics, "field 'landingBasicsRow'", StandardButtonRow.class);
        this.view2131755582 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickBasicsRow();
            }
        });
        View view2 = Utils.findRequiredView(source, C7251R.C7253id.landing_marketing, "field 'landingMarketingRow' and method 'onClickMarketingRow'");
        target2.landingMarketingRow = (StandardButtonRow) Utils.castView(view2, C7251R.C7253id.landing_marketing, "field 'landingMarketingRow'", StandardButtonRow.class);
        this.view2131755583 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickMarketingRow();
            }
        });
        View view3 = Utils.findRequiredView(source, C7251R.C7253id.landing_booking, "field 'landingBookingRow' and method 'onClickBookingRow'");
        target2.landingBookingRow = (StandardButtonRow) Utils.castView(view3, C7251R.C7253id.landing_booking, "field 'landingBookingRow'", StandardButtonRow.class);
        this.view2131755584 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickBookingRow();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7251R.C7253id.toolbar, "field 'toolbar'", AirToolbar.class);
        View view4 = Utils.findRequiredView(source, C7251R.C7253id.preview, "field 'preview' and method 'onClickPreview'");
        target2.preview = (AirButton) Utils.castView(view4, C7251R.C7253id.preview, "field 'preview'", AirButton.class);
        this.view2131755577 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickPreview();
            }
        });
        View view5 = Utils.findRequiredView(source, C7251R.C7253id.publish, "field 'publish' and method 'onClickPublish'");
        target2.publish = (AirButton) Utils.castView(view5, C7251R.C7253id.publish, "field 'publish'", AirButton.class);
        this.view2131755586 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickPublish();
            }
        });
        target2.space = (Space) Utils.findRequiredViewAsType(source, C7251R.C7253id.space_for_preview, "field 'space'", Space.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7251R.C7253id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
    }

    public void unbind() {
        LYSLandingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.landingMarquee = null;
        target2.landingBasicsRow = null;
        target2.landingMarketingRow = null;
        target2.landingBookingRow = null;
        target2.toolbar = null;
        target2.preview = null;
        target2.publish = null;
        target2.space = null;
        target2.scrollView = null;
        this.view2131755582.setOnClickListener(null);
        this.view2131755582 = null;
        this.view2131755583.setOnClickListener(null);
        this.view2131755583 = null;
        this.view2131755584.setOnClickListener(null);
        this.view2131755584 = null;
        this.view2131755577.setOnClickListener(null);
        this.view2131755577 = null;
        this.view2131755586.setOnClickListener(null);
        this.view2131755586 = null;
    }
}
