package com.airbnb.android.referrals;

import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import com.flipboard.bottomsheet.BottomSheetLayout;

public class ReferralsOneClickFragment_ViewBinding implements Unbinder {
    private ReferralsOneClickFragment target;

    public ReferralsOneClickFragment_ViewBinding(ReferralsOneClickFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1532R.C1534id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C1532R.C1534id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.rootBottomSheetLayout = (BottomSheetLayout) Utils.findRequiredViewAsType(source, C1532R.C1534id.bottom_sheet, "field 'rootBottomSheetLayout'", BottomSheetLayout.class);
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C1532R.C1534id.footer, "field 'footer'", FixedDualActionFooter.class);
        target2.coordinatorLayout = (CoordinatorLayout) Utils.findRequiredViewAsType(source, C1532R.C1534id.coordinator_layout, "field 'coordinatorLayout'", CoordinatorLayout.class);
    }

    public void unbind() {
        ReferralsOneClickFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.rootBottomSheetLayout = null;
        target2.footer = null;
        target2.coordinatorLayout = null;
    }
}
