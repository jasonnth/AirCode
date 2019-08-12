package com.airbnb.android.referrals;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.LoaderFrame;

public class ReferralsActivity_ViewBinding implements Unbinder {
    private ReferralsActivity target;

    public ReferralsActivity_ViewBinding(ReferralsActivity target2) {
        this(target2, target2.getWindow().getDecorView());
    }

    public ReferralsActivity_ViewBinding(ReferralsActivity target2, View source) {
        this.target = target2;
        target2.root = (ViewGroup) Utils.findRequiredViewAsType(source, C1532R.C1534id.root, "field 'root'", ViewGroup.class);
        target2.loader = (LoaderFrame) Utils.findRequiredViewAsType(source, C1532R.C1534id.loader_frame, "field 'loader'", LoaderFrame.class);
    }

    public void unbind() {
        ReferralsActivity target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.root = null;
        target2.loader = null;
    }
}
