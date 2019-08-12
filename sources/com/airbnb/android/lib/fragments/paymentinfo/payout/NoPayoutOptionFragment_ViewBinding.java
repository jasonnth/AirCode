package com.airbnb.android.lib.fragments.paymentinfo.payout;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class NoPayoutOptionFragment_ViewBinding implements Unbinder {
    private NoPayoutOptionFragment target;

    public NoPayoutOptionFragment_ViewBinding(NoPayoutOptionFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        NoPayoutOptionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
    }
}
