package com.airbnb.android.thread.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.thread.C1729R;
import com.airbnb.p027n2.collections.AirRecyclerView;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class ThreadBlockReasonFragment_ViewBinding implements Unbinder {
    private ThreadBlockReasonFragment target;

    public ThreadBlockReasonFragment_ViewBinding(ThreadBlockReasonFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (AirRecyclerView) Utils.findRequiredViewAsType(source, C1729R.C1731id.recycler_view, "field 'recyclerView'", AirRecyclerView.class);
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C1729R.C1731id.thread_block_info_footer, "field 'footer'", FixedDualActionFooter.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1729R.C1731id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        ThreadBlockReasonFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
        target2.footer = null;
        target2.toolbar = null;
    }
}
