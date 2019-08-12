package com.airbnb.android.host_referrals.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.host_referrals.C6405R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.flipboard.bottomsheet.BottomSheetLayout;

public class HostReferralsFragment_ViewBinding implements Unbinder {
    private HostReferralsFragment target;

    public HostReferralsFragment_ViewBinding(HostReferralsFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C6405R.C6407id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.rootBottomSheetLayout = (BottomSheetLayout) Utils.findRequiredViewAsType(source, C6405R.C6407id.root, "field 'rootBottomSheetLayout'", BottomSheetLayout.class);
    }

    public void unbind() {
        HostReferralsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.rootBottomSheetLayout = null;
    }
}
