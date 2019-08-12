package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.TextView;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GroupedCounterRound_ViewBinding extends GroupedCounter_ViewBinding {
    private GroupedCounterRound target;

    public GroupedCounterRound_ViewBinding(GroupedCounterRound target2) {
        this(target2, target2);
    }

    public GroupedCounterRound_ViewBinding(GroupedCounterRound target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.facetCountText = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_facet_count, "field 'facetCountText'", TextView.class);
    }

    public void unbind() {
        GroupedCounterRound target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.facetCountText = null;
        super.unbind();
    }
}
