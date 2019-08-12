package com.airbnb.android.referrals;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.p027n2.components.AirToolbar;

public class SentReferralsFragment_ViewBinding implements Unbinder {
    private SentReferralsFragment target;

    public SentReferralsFragment_ViewBinding(SentReferralsFragment target2, View source) {
        this.target = target2;
        target2.root = (ViewGroup) Utils.findRequiredViewAsType(source, C1532R.C1534id.root, "field 'root'", ViewGroup.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C1532R.C1534id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C1532R.C1534id.toolbar, "field 'toolbar'", AirToolbar.class);
    }

    public void unbind() {
        SentReferralsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.root = null;
        target2.recyclerView = null;
        target2.toolbar = null;
    }
}
