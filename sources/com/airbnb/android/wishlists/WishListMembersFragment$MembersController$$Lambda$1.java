package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;

final /* synthetic */ class WishListMembersFragment$MembersController$$Lambda$1 implements OnClickListener {
    private final MembersController arg$1;

    private WishListMembersFragment$MembersController$$Lambda$1(MembersController membersController) {
        this.arg$1 = membersController;
    }

    public static OnClickListener lambdaFactory$(MembersController membersController) {
        return new WishListMembersFragment$MembersController$$Lambda$1(membersController);
    }

    public void onClick(View view) {
        WishListMembersFragment.this.parentFragment.onInviteFriendClicked();
    }
}
