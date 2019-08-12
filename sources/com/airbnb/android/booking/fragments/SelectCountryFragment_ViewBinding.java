package com.airbnb.android.booking.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.core.views.CountryCodeSelectionView;

public class SelectCountryFragment_ViewBinding implements Unbinder {
    private SelectCountryFragment target;
    private View view2131755509;

    public SelectCountryFragment_ViewBinding(final SelectCountryFragment target2, View source) {
        this.target = target2;
        target2.selectionSheetPresenter = (CountryCodeSelectionView) Utils.findRequiredViewAsType(source, C0704R.C0706id.selection_view, "field 'selectionSheetPresenter'", CountryCodeSelectionView.class);
        View view = Utils.findRequiredView(source, C0704R.C0706id.continue_button, "method 'onContinueClicked'");
        this.view2131755509 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClicked();
            }
        });
    }

    public void unbind() {
        SelectCountryFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.selectionSheetPresenter = null;
        this.view2131755509.setOnClickListener(null);
        this.view2131755509 = null;
    }
}
