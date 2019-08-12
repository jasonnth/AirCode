package com.airbnb.p027n2.components;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.HomeHighlights_ViewBinding */
public class HomeHighlights_ViewBinding implements Unbinder {
    private HomeHighlights target;

    public HomeHighlights_ViewBinding(HomeHighlights target2, View source) {
        this.target = target2;
        target2.horizontalIconContainer = (ViewGroup) Utils.findRequiredViewAsType(source, R.id.horizontal_icon_container, "field 'horizontalIconContainer'", ViewGroup.class);
        target2.divider = Utils.findRequiredView(source, R.id.divider, "field 'divider'");
    }

    public void unbind() {
        HomeHighlights target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.horizontalIconContainer = null;
        target2.divider = null;
    }
}
