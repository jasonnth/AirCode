package com.airbnb.android.lib.postbooking;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class MTPostHomeBookingListFragment_ViewBinding extends MTBasePostHomeBookingFragment_ViewBinding {
    private MTPostHomeBookingListFragment target;

    public MTPostHomeBookingListFragment_ViewBinding(MTPostHomeBookingListFragment target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        MTPostHomeBookingListFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        super.unbind();
    }
}
