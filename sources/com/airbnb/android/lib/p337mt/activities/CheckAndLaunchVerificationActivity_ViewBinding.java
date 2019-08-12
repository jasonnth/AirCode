package com.airbnb.android.lib.p337mt.activities;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;
import com.airbnb.android.lib.C0880R;

/* renamed from: com.airbnb.android.lib.mt.activities.CheckAndLaunchVerificationActivity_ViewBinding */
public class CheckAndLaunchVerificationActivity_ViewBinding implements Unbinder {
    private CheckAndLaunchVerificationActivity target;

    public CheckAndLaunchVerificationActivity_ViewBinding(CheckAndLaunchVerificationActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public CheckAndLaunchVerificationActivity_ViewBinding(CheckAndLaunchVerificationActivity target2, View source) {
        this.target = target2;
        target2.loaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0880R.C0882id.loader_frame, "field 'loaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        CheckAndLaunchVerificationActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.loaderFrame = null;
    }
}
