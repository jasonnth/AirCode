package com.airbnb.android.lib.payments.addpayment.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class SelectBillingCountryFragment_ViewBinding implements Unbinder {
    private SelectBillingCountryFragment target;
    private View view2131756104;

    public SelectBillingCountryFragment_ViewBinding(final SelectBillingCountryFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.selectionSheetPresenter = (CountryCodeSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionSheetPresenter'", CountryCodeSelectionView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "field 'continueButton' and method 'onContinueClicked'");
        target2.continueButton = (AirButton) Utils.castView(view, C0880R.C0882id.continue_button, "field 'continueButton'", AirButton.class);
        this.view2131756104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClicked();
            }
        });
    }

    public void unbind() {
        SelectBillingCountryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.selectionSheetPresenter = null;
        target2.continueButton = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
    }
}
