package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class InboxActivity_ViewBinding implements Unbinder {
    private InboxActivity target;

    public InboxActivity_ViewBinding(InboxActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public InboxActivity_ViewBinding(InboxActivity target2, View source) {
        this.target = target2;
        target2.fragmentContainer = Utils.findRequiredView(source, C0880R.C0882id.fragment_container, "field 'fragmentContainer'");
    }

    public void unbind() {
        InboxActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.fragmentContainer = null;
    }
}
