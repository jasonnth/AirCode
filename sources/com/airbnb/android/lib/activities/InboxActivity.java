package com.airbnb.android.lib.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.android.core.C0715L;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.intents.InboxActivityIntents;
import com.airbnb.android.core.intents.ThreadFragmentIntents;
import com.airbnb.android.core.models.InboxType;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.DeepLinkUtils;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.fragments.InboxContainerFragment;

public class InboxActivity extends AirActivity {
    private static final String TAG = InboxActivity.class.getSimpleName();
    @BindView
    View fragmentContainer;
    private InboxType inboxType;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(this).component()).inject(this);
        Intent intent = getIntent();
        if (DeepLinkUtils.isDeepLink(intent)) {
            startActivity(createDeepLinkIntent(intent));
            finish();
            return;
        }
        setContentView(C0880R.layout.activity_inbox);
        ButterKnife.bind((Activity) this);
        this.inboxType = (InboxType) Check.notNull((InboxType) intent.getSerializableExtra(InboxActivityIntents.KEY_INBOX_TYPE));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(C0880R.C0882id.fragment_container, InboxContainerFragment.newInstance(this.inboxType)).commit();
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    private Intent createDeepLinkIntent(Intent startingIntent) {
        long threadId = DeepLinkUtils.getParamAsId(startingIntent, "id");
        InboxType inboxType2 = InboxType.inboxFromKey(DeepLinkUtils.getParamAsString(startingIntent, "role"));
        if (threadId == -1) {
            if (inboxType2 == null) {
                C0715L.m1198w(TAG, "Attempting to load inbox but don't know which inbox to load, defaulting to load mode");
                inboxType2 = this.sharedPrefsHelper.isGuestMode() ? InboxType.Guest : InboxType.Host;
            }
            switch (inboxType2) {
                case Host:
                    return HomeActivityIntents.intentForHostHome(this);
                case Guest:
                    return HomeActivityIntents.intentForTravelInbox(this);
                case ExperienceHost:
                    return HomeActivityIntents.intentForTripHostInbox(this);
                default:
                    return InboxActivityIntents.intentForInbox(this, inboxType2);
            }
        } else if (inboxType2 != null) {
            return ThreadFragmentIntents.newIntent(this, threadId, inboxType2);
        } else {
            C0715L.m1198w(TAG, "Unable to load thread directly since we cannot decode the inbox type");
            return MessageThreadWebLinkActivity.forThreadId(this, threadId);
        }
    }
}
