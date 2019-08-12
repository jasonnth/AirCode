package com.airbnb.android.lib.identity.psb;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.CountryCodeSelectionView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class IdentificationNationalityFragment_ViewBinding implements Unbinder {
    private IdentificationNationalityFragment target;
    private View view2131756036;
    private View view2131756104;

    public IdentificationNationalityFragment_ViewBinding(final IdentificationNationalityFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0880R.C0882id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.countrySelectionView = (CountryCodeSelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'countrySelectionView'", CountryCodeSelectionView.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.continue_button, "field 'nextButton' and method 'onContinueClicked'");
        target2.nextButton = view;
        this.view2131756104 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onContinueClicked();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.booking_next_button, "field 'bookingNextButton' and method 'onNextClicked'");
        target2.bookingNextButton = view2;
        this.view2131756036 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onNextClicked();
            }
        });
    }

    public void unbind() {
        IdentificationNationalityFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.countrySelectionView = null;
        target2.nextButton = null;
        target2.bookingNextButton = null;
        this.view2131756104.setOnClickListener(null);
        this.view2131756104 = null;
        this.view2131756036.setOnClickListener(null);
        this.view2131756036 = null;
    }
}
