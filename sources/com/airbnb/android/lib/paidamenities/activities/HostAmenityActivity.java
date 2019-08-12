package com.airbnb.android.lib.paidamenities.activities;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment;
import com.airbnb.android.lib.paidamenities.fragments.hostservice.HostAmenityListFragment.Listener;
import icepick.State;

public class HostAmenityActivity extends AirActivity implements Listener {
    @State
    long listingId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.listingId = getIntent().getLongExtra("listing_id", -1);
            Check.state(this.listingId != -1);
            showFragment(HostAmenityListFragment.newInstanceWithListingId(this.listingId), C0880R.C0882id.content_container, FragmentTransitionType.SlideFromBottom, true);
        }
    }

    public void onExit(boolean hasZeroPaidAmenities) {
        setResult(-1, new Intent().putExtra(PaidAmenityIntents.EXTRA_HAS_ZERO_PAID_AMENITY, hasZeroPaidAmenities));
        finish();
    }
}
