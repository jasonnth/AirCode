package com.airbnb.p027n2.components;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;
import com.airbnb.p027n2.primitives.AirTextView;

/* renamed from: com.airbnb.n2.components.StatusBanner_ViewBinding */
public class StatusBanner_ViewBinding implements Unbinder {
    private StatusBanner target;

    public StatusBanner_ViewBinding(StatusBanner target2, View source) {
        this.target = target2;
        target2.statusLeft = (AirTextView) Utils.findRequiredViewAsType(source, R.id.left_status, "field 'statusLeft'", AirTextView.class);
        target2.statusRight = (AirTextView) Utils.findRequiredViewAsType(source, R.id.right_status, "field 'statusRight'", AirTextView.class);
    }

    public void unbind() {
        StatusBanner target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.statusLeft = null;
        target2.statusRight = null;
    }
}
