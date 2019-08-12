package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class GroupedCounter_ViewBinding implements Unbinder {
    private GroupedCounter target;

    public GroupedCounter_ViewBinding(GroupedCounter target2) {
        this(target2, target2);
    }

    public GroupedCounter_ViewBinding(GroupedCounter target2, View source) {
        this.target = target2;
        target2.minusButton = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_minus, "field 'minusButton'", ImageView.class);
        target2.plusButton = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.btn_plus, "field 'plusButton'", ImageView.class);
        target2.text = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.txt_counter, "field 'text'", TextView.class);
        target2.topBorder = source.findViewById(C0880R.C0882id.grouped_counter_top_border);
    }

    public void unbind() {
        GroupedCounter target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.minusButton = null;
        target2.plusButton = null;
        target2.text = null;
        target2.topBorder = null;
    }
}
