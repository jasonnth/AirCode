package com.airbnb.android.lib.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.views.DotsProgressBar;

public class VerificationsActivity_ViewBinding implements Unbinder {
    private VerificationsActivity target;

    public VerificationsActivity_ViewBinding(VerificationsActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public VerificationsActivity_ViewBinding(VerificationsActivity target2, View source) {
        this.target = target2;
        target2.dotsProgressBar = (DotsProgressBar) Utils.findRequiredViewAsType(source, C0880R.C0882id.dots_progress_bar, "field 'dotsProgressBar'", DotsProgressBar.class);
    }

    public void unbind() {
        VerificationsActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.dotsProgressBar = null;
    }
}
