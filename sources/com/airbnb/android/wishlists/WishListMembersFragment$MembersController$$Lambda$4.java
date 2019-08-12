package com.airbnb.android.wishlists;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.models.User;

final /* synthetic */ class WishListMembersFragment$MembersController$$Lambda$4 implements OnClickListener {
    private final MembersController arg$1;
    private final User arg$2;

    private WishListMembersFragment$MembersController$$Lambda$4(MembersController membersController, User user) {
        this.arg$1 = membersController;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(MembersController membersController, User user) {
        return new WishListMembersFragment$MembersController$$Lambda$4(membersController, user);
    }

    public void onClick(View view) {
        WishListMembersFragment.this.onRemoveMemberClicked(this.arg$2);
    }
}
