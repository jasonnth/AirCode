package com.airbnb.android.lib.views;

import android.view.View;
import android.view.ViewStub;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GroupedCompoundButton_ViewBinding implements Unbinder {
    private GroupedCompoundButton target;

    public GroupedCompoundButton_ViewBinding(GroupedCompoundButton target2) {
        this(target2, target2);
    }

    public GroupedCompoundButton_ViewBinding(GroupedCompoundButton target2, View source) {
        this.target = target2;
        target2.title = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_btn_title, "field 'title'", TextView.class);
        target2.subtitle = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_btn_content, "field 'subtitle'", TextView.class);
        target2.tooltip = (GroupedTooltip) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_btn_tooltip, "field 'tooltip'", GroupedTooltip.class);
        target2.compoundButtonStub = (ViewStub) Utils.findRequiredViewAsType(source, C0880R.C0882id.grouped_btn_compound_button, "field 'compoundButtonStub'", ViewStub.class);
        target2.topBorder = Utils.findRequiredView(source, C0880R.C0882id.grouped_btn_top_border, "field 'topBorder'");
    }

    public void unbind() {
        GroupedCompoundButton target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.title = null;
        target2.subtitle = null;
        target2.tooltip = null;
        target2.compoundButtonStub = null;
        target2.topBorder = null;
    }
}
