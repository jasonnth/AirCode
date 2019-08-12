package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;

public class CohostLeadsCenterSendQuoteFragment_ViewBinding implements Unbinder {
    private CohostLeadsCenterSendQuoteFragment target;

    public CohostLeadsCenterSendQuoteFragment_ViewBinding(CohostLeadsCenterSendQuoteFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.button = (FixedActionFooter) Utils.findRequiredViewAsType(source, C5658R.C5660id.button, "field 'button'", FixedActionFooter.class);
    }

    public void unbind() {
        CohostLeadsCenterSendQuoteFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.button = null;
    }
}
