package com.airbnb.android.payout.create.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.payout.C7552R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;

public class AddPayoutAddressFragment_ViewBinding implements Unbinder {
    private AddPayoutAddressFragment target;

    public AddPayoutAddressFragment_ViewBinding(AddPayoutAddressFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7552R.C7554id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.documentMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C7552R.C7554id.document_marquee, "field 'documentMarquee'", DocumentMarquee.class);
        target2.streetAddressOneInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.street_address_one, "field 'streetAddressOneInputRow'", InlineInputRow.class);
        target2.streetAddressTwoInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.street_address_two, "field 'streetAddressTwoInputRow'", InlineInputRow.class);
        target2.cityInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.city, "field 'cityInputRow'", InlineInputRow.class);
        target2.stateInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.state, "field 'stateInputRow'", InlineInputRow.class);
        target2.zipCodeInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.zip_code, "field 'zipCodeInputRow'", InlineInputRow.class);
        target2.countryInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C7552R.C7554id.country, "field 'countryInputRow'", InlineInputRow.class);
        target2.advanceFooter = (FixedFlowActionAdvanceFooter) Utils.findRequiredViewAsType(source, C7552R.C7554id.advance_footer, "field 'advanceFooter'", FixedFlowActionAdvanceFooter.class);
    }

    public void unbind() {
        AddPayoutAddressFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.documentMarquee = null;
        target2.streetAddressOneInputRow = null;
        target2.streetAddressTwoInputRow = null;
        target2.cityInputRow = null;
        target2.stateInputRow = null;
        target2.zipCodeInputRow = null;
        target2.countryInputRow = null;
        target2.advanceFooter = null;
    }
}
