package com.airbnb.android.lib.postbooking;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class MTBasePostHomeBookingFragment_ViewBinding implements Unbinder {
    private MTBasePostHomeBookingFragment target;

    public MTBasePostHomeBookingFragment_ViewBinding(MTBasePostHomeBookingFragment target2, View source) {
        this.target = target2;
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C0880R.C0882id.footer, "field 'footer'", FixedDualActionFooter.class);
    }

    public void unbind() {
        MTBasePostHomeBookingFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.footer = null;
    }
}
