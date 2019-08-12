package com.airbnb.p027n2.components;

import android.support.p000v4.widget.Space;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.n2.R;

/* renamed from: com.airbnb.n2.components.ListSpacer_ViewBinding */
public class ListSpacer_ViewBinding implements Unbinder {
    private ListSpacer target;

    public ListSpacer_ViewBinding(ListSpacer target2, View source) {
        this.target = target2;
        target2.space = (Space) Utils.findRequiredViewAsType(source, R.id.space, "field 'space'", Space.class);
    }

    public void unbind() {
        ListSpacer target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.space = null;
    }
}
