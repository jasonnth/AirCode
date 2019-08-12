package com.airbnb.android.listing.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.listing.C7213R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.MicroSectionHeader;

public class HouseRulesLegalInfoFragment_ViewBinding implements Unbinder {
    private HouseRulesLegalInfoFragment target;
    private View view2131755489;
    private View view2131755492;

    public HouseRulesLegalInfoFragment_ViewBinding(final HouseRulesLegalInfoFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7213R.C7215id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.petsTitle = (MicroSectionHeader) Utils.findRequiredViewAsType(source, C7213R.C7215id.pets_title, "field 'petsTitle'", MicroSectionHeader.class);
        target2.infantsTitle = (MicroSectionHeader) Utils.findRequiredViewAsType(source, C7213R.C7215id.infants_title, "field 'infantsTitle'", MicroSectionHeader.class);
        View view = Utils.findRequiredView(source, C7213R.C7215id.infants_learn_more_link, "method 'onInfantsLearnMoreClicked'");
        this.view2131755489 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onInfantsLearnMoreClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C7213R.C7215id.pets_learn_more_link, "method 'onPetsLearnMoreClicked'");
        this.view2131755492 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onPetsLearnMoreClicked();
            }
        });
    }

    public void unbind() {
        HouseRulesLegalInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.petsTitle = null;
        target2.infantsTitle = null;
        this.view2131755489.setOnClickListener(null);
        this.view2131755489 = null;
        this.view2131755492.setOnClickListener(null);
        this.view2131755492 = null;
    }
}
