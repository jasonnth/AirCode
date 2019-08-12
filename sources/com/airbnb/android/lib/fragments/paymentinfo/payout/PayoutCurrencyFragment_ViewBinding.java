package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.presenters.p338n2.paymentinfo.PayoutCurrencySelectionView;
import com.airbnb.p027n2.components.AirToolbar;

public class PayoutCurrencyFragment_ViewBinding implements Unbinder {
    private PayoutCurrencyFragment target;

    public PayoutCurrencyFragment_ViewBinding(PayoutCurrencyFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.selectionView = (PayoutCurrencySelectionView) Utils.findRequiredViewAsType(source, C0880R.C0882id.selection_view, "field 'selectionView'", PayoutCurrencySelectionView.class);
    }

    public void unbind() {
        PayoutCurrencyFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.selectionView = null;
    }
}
