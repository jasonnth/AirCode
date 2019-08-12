package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.PrimaryButton;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.primitives.imaging.HaloImageView;

public class ContactHostFragment_ViewBinding implements Unbinder {
    private ContactHostFragment target;
    private View view2131755199;
    private View view2131755517;
    private View view2131755518;
    private View view2131755519;
    private View view2131755521;

    public ContactHostFragment_ViewBinding(final ContactHostFragment target2, View source) {
        this.target = target2;
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C0716R.C0718id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        View view = Utils.findRequiredView(source, C0716R.C0718id.dates, "field 'datesRow' and method 'showDatesFragment'");
        target2.datesRow = (StandardRow) Utils.castView(view, C0716R.C0718id.dates, "field 'datesRow'", StandardRow.class);
        this.view2131755517 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showDatesFragment();
            }
        });
        View view2 = Utils.findRequiredView(source, C0716R.C0718id.guests, "field 'guestsRow' and method 'showGuestsFragment'");
        target2.guestsRow = (StandardRow) Utils.castView(view2, C0716R.C0718id.guests, "field 'guestsRow'", StandardRow.class);
        this.view2131755518 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showGuestsFragment();
            }
        });
        View view3 = Utils.findRequiredView(source, C0716R.C0718id.message, "field 'yourMessageRow' and method 'showMessageComposeFragment'");
        target2.yourMessageRow = (StandardRow) Utils.castView(view3, C0716R.C0718id.message, "field 'yourMessageRow'", StandardRow.class);
        this.view2131755519 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showMessageComposeFragment();
            }
        });
        View view4 = Utils.findRequiredView(source, C0716R.C0718id.button, "field 'sendButton' and method 'contactHost'");
        target2.sendButton = (PrimaryButton) Utils.castView(view4, C0716R.C0718id.button, "field 'sendButton'", PrimaryButton.class);
        this.view2131755199 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.contactHost();
            }
        });
        View view5 = Utils.findRequiredView(source, C0716R.C0718id.step_through_button, "field 'stepThroughButton' and method 'goToNextStep'");
        target2.stepThroughButton = (AirButton) Utils.castView(view5, C0716R.C0718id.step_through_button, "field 'stepThroughButton'", AirButton.class);
        this.view2131755521 = view5;
        view5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.goToNextStep();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0716R.C0718id.marquee, "field 'marquee'", DocumentMarquee.class);
        target2.hostPhoto = (HaloImageView) Utils.findRequiredViewAsType(source, C0716R.C0718id.host_photo, "field 'hostPhoto'", HaloImageView.class);
        target2.chinaTermsView = (AirTextView) Utils.findRequiredViewAsType(source, C0716R.C0718id.china_disclaimer, "field 'chinaTermsView'", AirTextView.class);
    }

    public void unbind() {
        ContactHostFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.scrollView = null;
        target2.datesRow = null;
        target2.guestsRow = null;
        target2.yourMessageRow = null;
        target2.sendButton = null;
        target2.stepThroughButton = null;
        target2.toolbar = null;
        target2.marquee = null;
        target2.hostPhoto = null;
        target2.chinaTermsView = null;
        this.view2131755517.setOnClickListener(null);
        this.view2131755517 = null;
        this.view2131755518.setOnClickListener(null);
        this.view2131755518 = null;
        this.view2131755519.setOnClickListener(null);
        this.view2131755519 = null;
        this.view2131755199.setOnClickListener(null);
        this.view2131755199 = null;
        this.view2131755521.setOnClickListener(null);
        this.view2131755521 = null;
    }
}
