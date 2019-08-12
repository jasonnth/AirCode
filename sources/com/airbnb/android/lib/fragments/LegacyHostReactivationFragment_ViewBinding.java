package com.airbnb.android.lib.fragments;

import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirButton;

public class LegacyHostReactivationFragment_ViewBinding implements Unbinder {
    private LegacyHostReactivationFragment target;
    private View view2131756370;
    private View view2131756372;

    public LegacyHostReactivationFragment_ViewBinding(final LegacyHostReactivationFragment target2, View source) {
        this.target = target2;
        target2.educationTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.reactivation_body_text, "field 'educationTextView'", TextView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.reactivation_info_link, "field 'helpcenterLinkTextView' and method 'onReactivationInfoLinkClick'");
        target2.helpcenterLinkTextView = (TextView) Utils.castView(view, C0880R.C0882id.reactivation_info_link, "field 'helpcenterLinkTextView'", TextView.class);
        this.view2131756372 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onReactivationInfoLinkClick();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.reactivate_button, "field 'reactivateButton' and method 'onReactivateButtonClick'");
        target2.reactivateButton = (AirButton) Utils.castView(view2, C0880R.C0882id.reactivate_button, "field 'reactivateButton'", AirButton.class);
        this.view2131756370 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onReactivateButtonClick(p0);
            }
        });
    }

    public void unbind() {
        LegacyHostReactivationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.educationTextView = null;
        target2.helpcenterLinkTextView = null;
        target2.reactivateButton = null;
        this.view2131756372.setOnClickListener(null);
        this.view2131756372 = null;
        this.view2131756370.setOnClickListener(null);
        this.view2131756370 = null;
    }
}
