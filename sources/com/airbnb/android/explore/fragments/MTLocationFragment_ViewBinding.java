package com.airbnb.android.explore.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.explore.C0857R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;

public class MTLocationFragment_ViewBinding implements Unbinder {
    private MTLocationFragment target;

    public MTLocationFragment_ViewBinding(MTLocationFragment target2, View source) {
        this.target = target2;
        target2.jellyfishView = (JellyfishView) Utils.findRequiredViewAsType(source, C0857R.C0859id.jellyfish_view, "field 'jellyfishView'", JellyfishView.class);
        target2.inputMarquee = (InputMarquee) Utils.findRequiredViewAsType(source, C0857R.C0859id.input_marquee, "field 'inputMarquee'", InputMarquee.class);
        target2.inputDivider = Utils.findRequiredView(source, C0857R.C0859id.input_divider, "field 'inputDivider'");
        target2.searchOptionsList = (RecyclerView) Utils.findRequiredViewAsType(source, C0857R.C0859id.recycler_view, "field 'searchOptionsList'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0857R.C0859id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        MTLocationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.jellyfishView = null;
        target2.inputMarquee = null;
        target2.inputDivider = null;
        target2.searchOptionsList = null;
        target2.toolbar = null;
    }
}
