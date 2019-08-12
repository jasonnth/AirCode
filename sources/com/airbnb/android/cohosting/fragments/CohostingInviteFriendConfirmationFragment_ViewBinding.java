package com.airbnb.android.cohosting.fragments;

import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.p027n2.components.SheetMarquee;

public class CohostingInviteFriendConfirmationFragment_ViewBinding implements Unbinder {
    private CohostingInviteFriendConfirmationFragment target;
    private View view2131755481;

    public CohostingInviteFriendConfirmationFragment_ViewBinding(final CohostingInviteFriendConfirmationFragment target2, View source) {
        this.target = target2;
        target2.marquee = (SheetMarquee) Utils.findRequiredViewAsType(source, C5658R.C5660id.confirmation_marquee, "field 'marquee'", SheetMarquee.class);
        View view = Utils.findRequiredView(source, C5658R.C5660id.okay_button, "method 'onClickOkay'");
        this.view2131755481 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.onClickOkay();
            }
        });
    }

    public void unbind() {
        CohostingInviteFriendConfirmationFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.marquee = null;
        this.view2131755481.setOnClickListener(null);
        this.view2131755481 = null;
    }
}
