package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class GroupedCell_ViewBinding implements Unbinder {
    private GroupedCell target;

    public GroupedCell_ViewBinding(GroupedCell target2) {
        this(target2, target2);
    }

    public GroupedCell_ViewBinding(GroupedCell target2, View source) {
        this.target = target2;
        target2.mTitle = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_cell_title, "field 'mTitle'", AirTextView.class);
        target2.mTooltip = (GroupedTooltip) Utils.findOptionalViewAsType(source, C0880R.C0882id.grouped_cell_tooltip, "field 'mTooltip'", GroupedTooltip.class);
        target2.mContent = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_cell_content, "field 'mContent'", AirTextView.class);
        target2.mTopBorder = Utils.findRequiredView(source, C0880R.C0882id.grouped_cell_top_border, "field 'mTopBorder'");
        target2.mNextArrow = (ColorizedIconView) Utils.findOptionalViewAsType(source, C0880R.C0882id.next_arrow, "field 'mNextArrow'", ColorizedIconView.class);
    }

    public void unbind() {
        GroupedCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mTitle = null;
        target2.mTooltip = null;
        target2.mContent = null;
        target2.mTopBorder = null;
        target2.mNextArrow = null;
    }
}
