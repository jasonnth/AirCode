package com.airbnb.android.hostcalendar.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.HostCalendarIntents.ArgumentConstants;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.hostcalendar.fragments.SingleCalendarFragment;
import p032rx.Observer;

public class HostSingleCalendarActivity extends AirActivity implements ArgumentConstants {
    final RequestListener<ListingResponse> listingRequestListener = new C0699RL().onResponse(HostSingleCalendarActivity$$Lambda$1.lambdaFactory$(this)).build();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6418R.layout.activity_host_calendar_blank);
        if (savedInstanceState == null) {
            showSingleCalendarFragment();
        }
    }

    private void showSingleCalendarFragment() {
        Fragment fragment;
        Intent intent = getIntent();
        long listingId = intent.getLongExtra("listing_id", -1);
        String listingName = intent.getStringExtra("listing_name");
        AirDate startDate = (AirDate) intent.getParcelableExtra("target_start_date");
        AirDate endDate = (AirDate) intent.getParcelableExtra("target_end_date");
        Check.state(listingId != -1);
        if (listingName == null) {
            fetchListing(listingId);
            return;
        }
        if (startDate == null) {
            fragment = SingleCalendarFragment.newInstance(listingId, listingName);
        } else {
            fragment = SingleCalendarFragment.calendarForDates(listingId, listingName, startDate, endDate);
        }
        showFragment(fragment, C6418R.C6420id.content_container, FragmentTransitionType.SlideInFromSide, false);
    }

    private void fetchListing(long listingId) {
        ListingRequest.forListingName(listingId).withListener((Observer) this.listingRequestListener).doubleResponse(true).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$0(HostSingleCalendarActivity hostSingleCalendarActivity, ListingResponse listingResponse) {
        Listing listing = listingResponse.listing;
        hostSingleCalendarActivity.showFragment(SingleCalendarFragment.newInstance(listing.getId(), listing.getName()), C6418R.C6420id.content_container, FragmentTransitionType.SlideInFromSide, false);
    }
}
