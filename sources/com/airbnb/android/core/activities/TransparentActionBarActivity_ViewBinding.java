package com.airbnb.android.core.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.p027n2.components.AirToolbar;

public class TransparentActionBarActivity_ViewBinding implements Unbinder {
    private TransparentActionBarActivity target;

    public TransparentActionBarActivity_ViewBinding(TransparentActionBarActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public TransparentActionBarActivity_ViewBinding(TransparentActionBarActivity target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0716R.C0718id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        TransparentActionBarActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
    }
}