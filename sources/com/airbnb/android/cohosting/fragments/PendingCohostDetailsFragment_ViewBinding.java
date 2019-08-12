package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.components.UserDetailsActionRow;

public class PendingCohostDetailsFragment_ViewBinding implements Unbinder {
    private PendingCohostDetailsFragment target;
    private View view2131755549;
    private View view2131755550;

    public PendingCohostDetailsFragment_ViewBinding(final PendingCohostDetailsFragment target2, View source) {
        this.target = target2;
        target2.toolbar = (AirToolbar) Utils.findRequiredViewAsType(source, C5658R.C5660id.toolbar, "field 'toolbar'", AirToolbar.class);
        target2.pendingCohostRow = (UserDetailsActionRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.pending_cohost_row, "field 'pendingCohostRow'", UserDetailsActionRow.class);
        target2.sharedEarningsRow = (StandardRow) Utils.findRequiredViewAsType(source, C5658R.C5660id.shared_earnings, "field 'sharedEarningsRow'", StandardRow.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C5658R.C5660id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.resend_invite_link, "field 'resendInviteLink' and method 'resendInvite'");
        target2.resendInviteLink = (LinkActionRow) Utils.castView(view, C5658R.C5660id.resend_invite_link, "field 'resendInviteLink'", LinkActionRow.class);
        this.view2131755549 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.resendInvite();
            }
        });
        View view2 = Utils.findRequiredView(source, C5658R.C5660id.cancel_invite_link, "field 'cancelInviteLink' and method 'cancelInvite'");
        target2.cancelInviteLink = (LinkActionRow) Utils.castView(view2, C5658R.C5660id.cancel_invite_link, "field 'cancelInviteLink'", LinkActionRow.class);
        this.view2131755550 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.cancelInvite();
            }
        });
    }

    public void unbind() {
        PendingCohostDetailsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.toolbar = null;
        target2.pendingCohostRow = null;
        target2.sharedEarningsRow = null;
        target2.refreshLoader = null;
        target2.resendInviteLink = null;
        target2.cancelInviteLink = null;
        this.view2131755549.setOnClickListener(null);
        this.view2131755549 = null;
        this.view2131755550.setOnClickListener(null);
        this.view2131755550 = null;
    }
}
