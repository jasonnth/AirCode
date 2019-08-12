package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class ManageGuestIdentityInfoFragment_ViewBinding implements Unbinder {
    private ManageGuestIdentityInfoFragment target;

    public ManageGuestIdentityInfoFragment_ViewBinding(ManageGuestIdentityInfoFragment target2, View source) {
        this.target = target2;
        target2.identitiesRecycler = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.identities_recycler, "field 'identitiesRecycler'", RecyclerView.class);
    }

    public void unbind() {
        ManageGuestIdentityInfoFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.identitiesRecycler = null;
    }
}
