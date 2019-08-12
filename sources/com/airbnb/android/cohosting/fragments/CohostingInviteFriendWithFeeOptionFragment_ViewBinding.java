package com.airbnb.android.cohosting.fragments;

import android.support.p002v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CohostingInviteFriendWithFeeOptionFragment_ViewBinding implements Unbinder {
    private CohostingInviteFriendWithFeeOptionFragment target;
    private View view2131755479;

    public CohostingInviteFriendWithFeeOptionFragment_ViewBinding(final CohostingInviteFriendWithFeeOptionFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.recyclerView = (RecyclerView) Utils.findRequiredViewAsType(source, C5658R.C5660id.recycler_view, "field 'recyclerView'", RecyclerView.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.invite_button, "field 'inviteButton' and method 'sendInvitation'");
        target2.inviteButton = (AirButton) Utils.castView(view, C5658R.C5660id.invite_button, "field 'inviteButton'", AirButton.class);
        this.view2131755479 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.sendInvitation();
            }
        });
    }

    public void unbind() {
        CohostingInviteFriendWithFeeOptionFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.recyclerView = null;
        target2.inviteButton = null;
        this.view2131755479.setOnClickListener(null);
        this.view2131755479 = null;
    }
}