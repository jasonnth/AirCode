package com.airbnb.android.core.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class CountryCodeSelectionFragment_ViewBinding implements Unbinder {
    private CountryCodeSelectionFragment target;

    public CountryCodeSelectionFragment_ViewBinding(CountryCodeSelectionFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0716R.C0718id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.countryCodeSelectionSheetPresenter = (CountryCodeSelectionView) Utils.findRequiredViewAsType(source, C0716R.C0718id.selection_view, "field 'countryCodeSelectionSheetPresenter'", CountryCodeSelectionView.class);
    }

    public void unbind() {
        CountryCodeSelectionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.countryCodeSelectionSheetPresenter = null;
    }
}
