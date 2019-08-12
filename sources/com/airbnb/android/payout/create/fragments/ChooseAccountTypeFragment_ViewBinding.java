package com.airbnb.android.payout.create.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.payout.C7552R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedFlowActionAdvanceFooter;

public class ChooseAccountTypeFragment_ViewBinding implements Unbinder {
    private ChooseAccountTypeFragment target;

    public ChooseAccountTypeFragment_ViewBinding(ChooseAccountTypeFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C7552R.C7554id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C7552R.C7554id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.advanceFooter = (FixedFlowActionAdvanceFooter) Utils.findRequiredViewAsType(source, C7552R.C7554id.advance_footer, "field 'advanceFooter'", FixedFlowActionAdvanceFooter.class);
    }

    public void unbind() {
        ChooseAccountTypeFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.advanceFooter = null;
    }
}
