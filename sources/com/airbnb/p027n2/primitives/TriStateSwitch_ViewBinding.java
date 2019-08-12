package com.airbnb.p027n2.primitives;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.primitives.TriStateSwitch_ViewBinding */
public class TriStateSwitch_ViewBinding implements Unbinder {
    private TriStateSwitch target;

    public TriStateSwitch_ViewBinding(TriStateSwitch target2, View source) {
        this.target = target2;
        target2.leftX = (TriStateSwitchHalf) Utils.findRequiredViewAsType(source, R.id.left_x, "field 'leftX'", TriStateSwitchHalf.class);
        target2.rightCheck = (TriStateSwitchHalf) Utils.findRequiredViewAsType(source, R.id.right_check, "field 'rightCheck'", TriStateSwitchHalf.class);
    }

    public void unbind() {
        TriStateSwitch target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.leftX = null;
        target2.rightCheck = null;
    }
}
