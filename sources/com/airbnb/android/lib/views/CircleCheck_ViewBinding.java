package com.airbnb.android.lib.views;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class CircleCheck_ViewBinding implements Unbinder {
    private CircleCheck target;

    public CircleCheck_ViewBinding(CircleCheck target2) {
        this(target2, target2);
    }

    public CircleCheck_ViewBinding(CircleCheck target2, View source) {
        this.target = target2;
        target2.text = (TextView) Utils.findRequiredViewAsType(source, C0880R.C0882id.text, "field 'text'", TextView.class);
        target2.checkImage = (ImageView) Utils.findRequiredViewAsType(source, C0880R.C0882id.image, "field 'checkImage'", ImageView.class);
    }

    public void unbind() {
        CircleCheck target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.text = null;
        target2.checkImage = null;
    }
}
