package com.airbnb.android.lib.fragments;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import com.airbnb.android.core.intents.UserProfileIntents;
import com.airbnb.android.core.models.User;

final /* synthetic */ class HostsInfoDialog$HostsAdapter$AdditionalHostViewHolder$$Lambda$1 implements OnClickListener {
    private final Context arg$1;
    private final User arg$2;

    private HostsInfoDialog$HostsAdapter$AdditionalHostViewHolder$$Lambda$1(Context context, User user) {
        this.arg$1 = context;
        this.arg$2 = user;
    }

    public static OnClickListener lambdaFactory$(Context context, User user) {
        return new HostsInfoDialog$HostsAdapter$AdditionalHostViewHolder$$Lambda$1(context, user);
    }

    public void onClick(View view) {
        this.arg$1.startActivity(UserProfileIntents.intentForUserId(this.arg$1, this.arg$2.getId()));
    }
}
