package com.airbnb.android.lib.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.lib.C0880R;

public class HostsInfoDialog_ViewBinding implements Unbinder {
    private HostsInfoDialog target;

    public HostsInfoDialog_ViewBinding(HostsInfoDialog target2, View source) {
        this.target = target2;
        target2.hostsList = (RecyclerView) Utils.findRequiredViewAsType(source, C0880R.C0882id.hosts_list, "field 'hostsList'", RecyclerView.class);
    }

    public void unbind() {
        HostsInfoDialog target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.hostsList = null;
    }
}
