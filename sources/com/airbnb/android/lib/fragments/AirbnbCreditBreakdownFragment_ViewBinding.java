package com.airbnb.android.lib.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.LinearListView;

public class AirbnbCreditBreakdownFragment_ViewBinding implements Unbinder {
    private AirbnbCreditBreakdownFragment target;

    public AirbnbCreditBreakdownFragment_ViewBinding(AirbnbCreditBreakdownFragment target2, View source) {
        this.target = target2;
        target2.mCreditsListView = (LinearListView) Utils.findRequiredViewAsType(source, C0880R.C0882id.list_credits, "field 'mCreditsListView'", LinearListView.class);
    }

    public void unbind() {
        AirbnbCreditBreakdownFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mCreditsListView = null;
    }
}
