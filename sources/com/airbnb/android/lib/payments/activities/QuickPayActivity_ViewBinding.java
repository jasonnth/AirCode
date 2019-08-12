package com.airbnb.android.lib.payments.activities;

import android.view.View;
import android.widget.FrameLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class QuickPayActivity_ViewBinding implements Unbinder {
    private QuickPayActivity target;

    public QuickPayActivity_ViewBinding(QuickPayActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public QuickPayActivity_ViewBinding(QuickPayActivity target2, View source) {
        this.target = target2;
        target2.container = (FrameLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.content_container, "field 'container'", FrameLayout.class);
    }

    public void unbind() {
        QuickPayActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.container = null;
    }
}
