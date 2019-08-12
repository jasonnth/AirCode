package com.airbnb.android.payout.create.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.payout.C7552R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;

public class SelectPayoutCountryFragment_ViewBinding implements Unbinder {
    private SelectPayoutCountryFragment target;

    public SelectPayoutCountryFragment_ViewBinding(SelectPayoutCountryFragment target2, View source) {
        this.target = target2;
        target2.countryCodeSelectionSheetPresenter = (CountryCodeSelectionView) Utils.findRequiredViewAsType(source, C7552R.C7554id.selection_view, "field 'countryCodeSelectionSheetPresenter'", CountryCodeSelectionView.class);
        target2.inputMarquee = (InputMarquee) Utils.findRequiredViewAsType(source, C7552R.C7554id.input_marquee, "field 'inputMarquee'", InputMarquee.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7552R.C7554id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        SelectPayoutCountryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.countryCodeSelectionSheetPresenter = null;
        target2.inputMarquee = null;
        target2.toolbar = null;
    }
}
