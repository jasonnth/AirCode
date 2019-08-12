package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.models.ThreadInboxInformation;
import com.airbnb.android.core.requests.ThreadInboxInformationRequest;
import com.airbnb.android.core.responses.ThreadInboxInformationResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.C0880R;
import p032rx.Observer;

public class MessageThreadWebLinkActivity extends AirActivity {
    private static final String EXTRA_THREAD_ID = "thread_id";
    final RequestListener<ThreadInboxInformationResponse> threadInboxTypeRequest = new C0699RL().onResponse(MessageThreadWebLinkActivity$$Lambda$1.lambdaFactory$(this)).onError(MessageThreadWebLinkActivity$$Lambda$2.lambdaFactory$(this)).build();

    public static Intent forThreadId(Context context, long threadId) {
        return new Intent(context, MessageThreadWebLinkActivity.class).putExtra("thread_id", Check.validId(threadId));
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_loading);
        if (savedInstanceState == null) {
            new ThreadInboxInformationRequest(getIntent().getExtras().getLong("thread_id")).withListener((Observer) this.threadInboxTypeRequest).execute(this.requestManager);
        }
    }

    /* access modifiers changed from: private */
    public void handleResponse(ThreadInboxInformation inboxInformation) {
        String roleKey = inboxInformation.getRole();
        long threadId = inboxInformation.getThreadId();
        InboxType inboxType = InboxType.inboxFromKey(roleKey);
        if (inboxType == null) {
            BugsnagWrapper.throwOrNotify((RuntimeException) new IllegalStateException("Cannot determine correct inbox for thread " + threadId + " got " + roleKey));
        } else {
            startActivity(ThreadFragmentIntents.newIntent(this, threadId, inboxType));
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void handleFetchError(NetworkException e) {
        NetworkUtil.toastNetworkError((Context) this, e);
        finish();
    }
}
