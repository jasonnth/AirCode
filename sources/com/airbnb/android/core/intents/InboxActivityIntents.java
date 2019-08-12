package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.utils.Check;
import java.io.Serializable;

public final class InboxActivityIntents {
    public static final String KEY_INBOX_TYPE = "travel_mode";

    private InboxActivityIntents() {
    }

    public static Intent intentForInbox(Context context, InboxType inboxType) {
        return new Intent(context, Activities.inbox()).putExtra(KEY_INBOX_TYPE, (Serializable) Check.notNull(inboxType));
    }
}
