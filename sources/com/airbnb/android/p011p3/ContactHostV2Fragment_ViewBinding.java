package com.airbnb.android.p011p3;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.AutoResizableButtonBar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

/* renamed from: com.airbnb.android.p3.ContactHostV2Fragment_ViewBinding */
public class ContactHostV2Fragment_ViewBinding implements Unbinder {
    private ContactHostV2Fragment target;
    private View view2131755500;
    private View view2131755501;
    private View view2131755502;
    private View view2131755504;

    public ContactHostV2Fragment_ViewBinding(final ContactHostV2Fragment target2, View source) {
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C7532R.C7534id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        View view = Utils.findRequiredView(source, C7532R.C7534id.dates, "field 'datesRow' and method 'showDatesFragment'");
        target2.datesRow = (StandardRow) Utils.castView(view, C7532R.C7534id.dates, "field 'datesRow'", StandardRow.class);
        this.view2131755500 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showDatesFragment();
            }
        });
        View view2 = Utils.findRequiredView(source, C7532R.C7534id.guests, "field 'guestsRow' and method 'showGuestsFragment'");
        target2.guestsRow = (StandardRow) Utils.castView(view2, C7532R.C7534id.guests, "field 'guestsRow'", StandardRow.class);
        this.view2131755501 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showGuestsFragment();
            }
        });
        View view3 = Utils.findRequiredView(source, C7532R.C7534id.message, "field 'yourMessageRow' and method 'showMessageComposeFragment'");
        target2.yourMessageRow = (StandardRow) Utils.castView(view3, C7532R.C7534id.message, "field 'yourMessageRow'", StandardRow.class);
        this.view2131755502 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showMessageComposeFragment();
            }
        });
        target2.submitBar = (AutoResizableButtonBar) Utils.findRequiredViewAsType(source, C7532R.C7534id.contact_host_resizable_button, "field 'submitBar'", AutoResizableButtonBar.class);
        View view4 = Utils.findRequiredView(source, C7532R.C7534id.step_through_button, "field 'stepThroughButton' and method 'goToNextStep'");
        target2.stepThroughButton = (FixedActionFooter) Utils.castView(view4, C7532R.C7534id.step_through_button, "field 'stepThroughButton'", FixedActionFooter.class);
        this.view2131755504 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.goToNextStep();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7532R.C7534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7532R.C7534id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.hostPhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C7532R.C7534id.host_photo, "field 'hostPhoto'", HaloImageView.class);
        target2.chinaTermsView = (AirTextView) Utils.findRequiredViewAsType(source, C7532R.C7534id.china_disclaimer, "field 'chinaTermsView'", AirTextView.class);
    }

    public void unbind() {
        ContactHostV2Fragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.datesRow = null;
        target2.guestsRow = null;
        target2.yourMessageRow = null;
        target2.submitBar = null;
        target2.stepThroughButton = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.hostPhoto = null;
        target2.chinaTermsView = null;
        this.view2131755500.setOnClickListener(null);
        this.view2131755500 = null;
        this.view2131755501.setOnClickListener(null);
        this.view2131755501 = null;
        this.view2131755502.setOnClickListener(null);
        this.view2131755502 = null;
        this.view2131755504.setOnClickListener(null);
        this.view2131755504 = null;
    }
}
