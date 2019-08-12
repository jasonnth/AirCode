package com.airbnb.android.lib.fragments.reviews;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;

public class ReservationReviewsFragment_ViewBinding implements Unbinder {
    private ReservationReviewsFragment target;

    public ReservationReviewsFragment_ViewBinding(ReservationReviewsFragment target2, View source) {
        this.target = target2;
        target2.airRecyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'airRecyclerView'", AirRecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        ReservationReviewsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.airRecyclerView = null;
        target2.toolbar = null;
    }
}
