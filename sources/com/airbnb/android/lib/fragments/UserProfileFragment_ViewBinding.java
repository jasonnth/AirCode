package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;
import com.airbnb.p027n2.components.AirToolbar;

public class UserProfileFragment_ViewBinding implements Unbinder {
    private UserProfileFragment target;

    public UserProfileFragment_ViewBinding(UserProfileFragment target2, View source) {
        this.target = target2;
        target2.root = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.root, "field 'root'", ViewGroup.class);
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C0880R.C0882id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        UserProfileFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.root = null;
        target2.toolbar = null;
        target2.recyclerView = null;
    }
}
