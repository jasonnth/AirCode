package com.airbnb.android.thread.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.thread.C1729R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;

public class ThreadBlockInfoFragment_ViewBinding implements Unbinder {
    private ThreadBlockInfoFragment target;

    public ThreadBlockInfoFragment_ViewBinding(ThreadBlockInfoFragment target2, View source) {
        this.target = target2;
        target2.marquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C1729R.C1731id.thread_block_info_marquee, "field 'marquee'", DocumentMarquee.class);
        target2.footer = (FixedDualActionFooter) Utils.findRequiredViewAsType(source, C1729R.C1731id.thread_block_info_footer, "field 'footer'", FixedDualActionFooter.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1729R.C1731id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        ThreadBlockInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        target2.footer = null;
        target2.toolbar = null;
    }
}
