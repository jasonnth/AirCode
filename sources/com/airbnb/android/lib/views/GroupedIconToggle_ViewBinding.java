package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.primitives.AirTextView;

public class GroupedIconToggle_ViewBinding implements Unbinder {
    private GroupedIconToggle target;

    public GroupedIconToggle_ViewBinding(GroupedIconToggle target2) {
        this(target2, target2);
    }

    public GroupedIconToggle_ViewBinding(GroupedIconToggle target2, View source) {
        this.target = target2;
        target2.title = (AirTextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_icon_toggle_title, "field 'title'", AirTextView.class);
        target2.iconToggles = (LinearLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_icon_toggles, "field 'iconToggles'", LinearLayout.class);
        target2.topBorder = Utils.findRequiredView(source, C0880R.C0882id.grouped_icon_toggle_top_border, "field 'topBorder'");
    }

    public void unbind() {
        GroupedIconToggle target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.iconToggles = null;
        target2.topBorder = null;
    }
}
