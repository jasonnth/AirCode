package com.airbnb.android.core.identity;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.C0716R;
import com.airbnb.android.core.views.LoaderFrame;

public class ReplaceVerifiedIdWithIdentityActivity_ViewBinding implements Unbinder {
    private ReplaceVerifiedIdWithIdentityActivity target;

    public ReplaceVerifiedIdWithIdentityActivity_ViewBinding(ReplaceVerifiedIdWithIdentityActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ReplaceVerifiedIdWithIdentityActivity_ViewBinding(ReplaceVerifiedIdWithIdentityActivity target2, View source) {
        this.target = target2;
        target2.mLoaderFrame = (LoaderFrame) Utils.findRequiredViewAsType(source, C0716R.C0718id.loader_frame, "field 'mLoaderFrame'", LoaderFrame.class);
    }

    public void unbind() {
        ReplaceVerifiedIdWithIdentityActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.mLoaderFrame = null;
    }
}
