package com.airbnb.android.lib.views;

import android.view.View;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.ColorizedIconView;
import com.airbnb.android.lib.C0880R;

public class GroupedStatusCell_ViewBinding extends GroupedCell_ViewBinding {
    private GroupedStatusCell target;

    public GroupedStatusCell_ViewBinding(GroupedStatusCell target2) {
        this(target2, target2);
    }

    public GroupedStatusCell_ViewBinding(GroupedStatusCell target2, View source) {
        super(target2, source);
        this.target = target2;
        target2.statusIcon = (ColorizedIconView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_selected_check, "field 'statusIcon'", ColorizedIconView.class);
    }

    public void unbind() {
        GroupedStatusCell target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.statusIcon = null;
        super.unbind();
    }
}
