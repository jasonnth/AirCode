package com.airbnb.android.p011p3;

import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

/* renamed from: com.airbnb.android.p3.ContactHostV2Fragment$$Lambda$1 */
final /* synthetic */ class ContactHostV2Fragment$$Lambda$1 implements OnClickListener {
    private final ContactHostV2Fragment arg$1;
    private final User arg$2;

    private ContactHostV2Fragment$$Lambda$1(ContactHostV2Fragment contactHostV2Fragment, User user) {
        this.arg$1 = contactHostV2Fragment;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(ContactHostV2Fragment contactHostV2Fragment, User user) {
        return new ContactHostV2Fragment$$Lambda$1(contactHostV2Fragment, user);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1.getContext(), this.arg$2.getId()));
    }
}
