package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ExpandableSwitchView_ViewBinding implements Unbinder {
    private ExpandableSwitchView target;

    public ExpandableSwitchView_ViewBinding(ExpandableSwitchView target2) {
        this(target2, target2);
    }

    public ExpandableSwitchView_ViewBinding(ExpandableSwitchView target2, View source) {
        this.target = target2;
        target2.checkBox = (CheckBox) Utils.findRequiredViewAsType(source, C0880R.C0882id.check_box, "field 'checkBox'", CheckBox.class);
        target2.expandableView = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.content_layout, "field 'expandableView'", FrameLayout.class);
        target2.titleTextView = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.title_text_view, "field 'titleTextView'", TextView.class);
        target2.topBorder = Utils.findRequiredView(source, C0880R.C0882id.top_border, "field 'topBorder'");
    }

    public void unbind() {
        ExpandableSwitchView target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.checkBox = null;
        target2.expandableView = null;
        target2.titleTextView = null;
        target2.topBorder = null;
    }
}
