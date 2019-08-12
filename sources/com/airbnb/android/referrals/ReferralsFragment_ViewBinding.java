package com.airbnb.android.referrals;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.flipboard.bottomsheet.BottomSheetLayout;

public class ReferralsFragment_ViewBinding implements Unbinder {
    private ReferralsFragment target;

    public ReferralsFragment_ViewBinding(ReferralsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C1532R.C1534id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.rootBottomSheetLayout = (BottomSheetLayout) Utils.findRequiredViewAsType(source, C1532R.C1534id.bottom_sheet, "field 'rootBottomSheetLayout'", BottomSheetLayout.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1532R.C1534id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        ReferralsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.rootBottomSheetLayout = null;
        target2.toolbar = null;
    }
}
