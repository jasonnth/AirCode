package com.airbnb.android.lib.paidamenities.fragments.create;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.components.SwitchRow;

public class AddAmenityPriceFragment_ViewBinding implements Unbinder {
    private AddAmenityPriceFragment target;
    private View view2131756035;

    public AddAmenityPriceFragment_ViewBinding(final AddAmenityPriceFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.complimentarySwitchRow = (SwitchRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.complimentary_switch_row, "field 'complimentarySwitchRow'", SwitchRow.class);
        target2.priceInputRow = (InlineInputRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.price_input_row, "field 'priceInputRow'", InlineInputRow.class);
        View view = Utils.findRequiredView(source, C0880R.C0882id.next_button, "method 'onClickNextButton'");
        this.view2131756035 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickNextButton();
            }
        });
    }

    public void unbind() {
        AddAmenityPriceFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.complimentarySwitchRow = null;
        target2.priceInputRow = null;
        this.view2131756035.setOnClickListener(null);
        this.view2131756035 = null;
    }
}
