package com.airbnb.android.cohosting.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;

public class ConfirmInvitationAcceptedFragment_ViewBinding implements Unbinder {
    private ConfirmInvitationAcceptedFragment target;

    public ConfirmInvitationAcceptedFragment_ViewBinding(ConfirmInvitationAcceptedFragment target2, View source) {
        this.target = target2;
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", RecyclerView.class);
    }

    public void unbind() {
        ConfirmInvitationAcceptedFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.recyclerView = null;
    }
}
