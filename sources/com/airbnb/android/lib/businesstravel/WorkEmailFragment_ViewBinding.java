package com.airbnb.android.lib.businesstravel;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class WorkEmailFragment_ViewBinding implements Unbinder {
    private WorkEmailFragment target;

    public WorkEmailFragment_ViewBinding(WorkEmailFragment target2, View source) {
        this.target = target2;
        target2.addWorkEmailButton = (FixedActionFooter) Utils.findRequiredViewAsType(source, C0880R.C0882id.footer_button, "field 'addWorkEmailButton'", FixedActionFooter.class);
        target2.bottomActionBar = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C0880R.C0882id.bottom_bar, "field 'bottomActionBar'", FixedDualActionFooter.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        WorkEmailFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.addWorkEmailButton = null;
        target2.bottomActionBar = null;
        target2.recyclerView = null;
        target2.toolbar = null;
    }
}
