package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.android.core.Fragments;
import com.airbnb.android.core.activities.AutoAirActivity;
import com.airbnb.android.core.enums.ROLaunchSource;
import com.airbnb.android.core.models.InboxType;

public final class ThreadFragmentIntents {
    public static final String ARG_INBOX_TYPE = "inbox_type";
    public static final String ARG_LAUNCH_SOURCE = "launch_source";
    public static final String ARG_SCROLL_TO_POST_ID = "post_id";
    public static final String ARG_THREAD_ID = "thread_id";

    private ThreadFragmentIntents() {
    }

    private static Bundle createBundle(long threadId, InboxType inboxType, Long scrollToPostId, ROLaunchSource source) {
        Bundle args = new Bundle();
        args.putLong("thread_id", threadId);
        args.putSerializable(ARG_INBOX_TYPE, inboxType);
        if (scrollToPostId != null) {
            args.putLong("post_id", scrollToPostId.longValue());
        }
        if (source != null) {
            args.putSerializable("launch_source", source);
        }
        return args;
    }

    public static Fragment newInstance(long threadId, InboxType inboxType, Long scrollToPostId) {
        Fragment fragment = Fragments.konaReservationMessageThread();
        fragment.setArguments(createBundle(threadId, inboxType, scrollToPostId, null));
        return fragment;
    }

    public static Intent newIntent(Context context, long threadId, InboxType inboxType) {
        return newIntent(context, threadId, inboxType, null, null);
    }

    public static Intent newIntent(Context context, long threadId, InboxType inboxType, Long scrollToPostId, ROLaunchSource source) {
        Fragment fragment = Fragments.konaReservationMessageThread();
        return AutoAirActivity.intentForFragment(context, fragment.getClass(), createBundle(threadId, inboxType, scrollToPostId, source));
    }
}
