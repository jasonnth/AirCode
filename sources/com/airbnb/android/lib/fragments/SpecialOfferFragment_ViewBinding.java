package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.EntryMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.primitives.AirButton;

public class SpecialOfferFragment_ViewBinding implements Unbinder {
    private SpecialOfferFragment target;
    private View view2131756071;
    private View view2131756798;
    private View view2131756799;
    private View view2131756801;

    public SpecialOfferFragment_ViewBinding(final SpecialOfferFragment target2, View source) {
        this.target = target2;
        target2.marquee = (EntryMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.marquee, "field 'marquee'", EntryMarquee.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.listing_row, "field 'listingRow' and method 'onClickListingRow'");
        target2.listingRow = (StandardRow) Utils.castView(view, C0880R.C0882id.listing_row, "field 'listingRow'", StandardRow.class);
        this.view2131756798 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickListingRow();
            }
        });
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.dates_row, "field 'datesRow' and method 'onClickDatesRow'");
        target2.datesRow = (StandardRow) Utils.castView(view2, C0880R.C0882id.dates_row, "field 'datesRow'", StandardRow.class);
        this.view2131756799 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickDatesRow();
            }
        });
        target2.priceRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.price_row, "field 'priceRow'", InlineInputRow.class);
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.guests_row, "field 'guestsRow' and method 'onClickGuestsRow'");
        target2.guestsRow = (StandardRow) Utils.castView(view3, C0880R.C0882id.guests_row, "field 'guestsRow'", StandardRow.class);
        this.view2131756801 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickGuestsRow();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.submit_button, "field 'submitButton' and method 'onSubmit'");
        target2.submitButton = (AirButton) Utils.castView(view4, C0880R.C0882id.submit_button, "field 'submitButton'", AirButton.class);
        this.view2131756071 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onSubmit();
            }
        });
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        SpecialOfferFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.listingRow = null;
        target2.datesRow = null;
        target2.priceRow = null;
        target2.guestsRow = null;
        target2.submitButton = null;
        target2.toolbar = null;
        this.view2131756798.setOnClickListener(null);
        this.view2131756798 = null;
        this.view2131756799.setOnClickListener(null);
        this.view2131756799 = null;
        this.view2131756801.setOnClickListener(null);
        this.view2131756801 = null;
        this.view2131756071.setOnClickListener(null);
        this.view2131756071 = null;
    }
}
