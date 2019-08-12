package com.airbnb.android.cohosting.activities;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.fragments.CohostUpsellFragment;
import com.airbnb.android.cohosting.fragments.CohostingInviteFriendConfirmationFragment;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AutoFragmentActivity;
import com.airbnb.android.core.activities.AutoFragmentActivity.Builder;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ListingManager;
import icepick.State;
import java.util.ArrayList;
import java.util.Arrays;

public class CohostUpsellActivity extends CohostingBaseActivity {
    @State
    Listing listing;
    @State
    ListingManager manager;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance(this).component()).inject(this);
        this.listing = (Listing) getIntent().getParcelableExtra("listing");
        this.manager = (ListingManager) getIntent().getParcelableExtra(CohostingIntents.INTENT_EXTRA_LISTING_MANAGER);
        super.onCreate(savedInstanceState);
        setContentView(C5658R.layout.activity_simple_fragment);
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.enter, type.exit);
        showFragment(CohostUpsellFragment.newInstance(this.listing, this.manager));
    }

    public void finish() {
        super.finish();
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.popEnter, type.popExit);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 1002) {
            startActivity(((Builder) ((Builder) ((Builder) AutoFragmentActivity.create(this, CohostingInviteFriendConfirmationFragment.class).putCharSequence("email", ((CohostInvitation) data.getParcelableExtra(CohostingIntents.INTENT_EXTRA_INVITATION)).getInviteeEmail())).putParcelable("listing", this.listing)).putParcelableArrayList(CohostingIntents.INTENT_EXTRA_LISTING_MANAGERS, new ArrayList(Arrays.asList(new ListingManager[]{this.manager})))).build());
            finish();
        }
    }
}
