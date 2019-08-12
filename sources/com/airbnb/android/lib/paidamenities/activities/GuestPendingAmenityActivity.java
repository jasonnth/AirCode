package com.airbnb.android.lib.paidamenities.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.PaidAmenityIntents;
import com.airbnb.android.core.models.PaidAmenityOrder;
import com.airbnb.android.core.models.Thread;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.fragments.pending.BasePendingAmenityFragment;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderDetailFragment.Listener;
import com.airbnb.android.lib.paidamenities.fragments.pending.PendingAmenityOrderListFragment;
import icepick.State;

public class GuestPendingAmenityActivity extends AirActivity implements Listener {
    private static final String EXTRA_CONFIRMATION_CODE = "confirmation_code";
    private static final String EXTRA_LISTING_ID = "listing_id";
    private static final String EXTRA_THREAD_ID = "thread_id";
    @State
    String confirmationCode;
    @State
    long listingId;
    @State
    long threadId;

    public static Intent intent(Context context, String confirmationCode2, long threadId2, long listingId2) {
        return new Intent(context, GuestPendingAmenityActivity.class).putExtra("confirmation_code", confirmationCode2).putExtra("thread_id", threadId2).putExtra("listing_id", listingId2);
    }

    public static Intent intentForThread(Context context, Thread thread) {
        return intent(context, thread.getReservationConfirmationCode(), thread.getId(), thread.getListing().getId());
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            this.confirmationCode = intent.getStringExtra("confirmation_code");
            this.threadId = intent.getLongExtra("thread_id", -1);
            this.listingId = intent.getLongExtra("listing_id", -1);
            Check.state((this.confirmationCode.isEmpty() || this.threadId == -1 || this.listingId == -1) ? false : true);
            showFragment(PendingAmenityOrderListFragment.newInstanceAsGuest(this.confirmationCode), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
        }
    }

    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public void goToRequestAnotherService() {
        startActivity(PaidAmenityIntents.purchaseAmenities(this, this.confirmationCode, this.threadId, this.listingId));
        finish();
    }

    public void goToPendingAmenityOrderDetail(PaidAmenityOrder paidAmenityOrder) {
        showFragment(PendingAmenityOrderDetailFragment.newInstanceAsGuest(paidAmenityOrder), C0880R.C0882id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void onPaidAmenityOrderStatusUpdated() {
        ((BasePendingAmenityFragment) getSupportFragmentManager().findFragmentById(C0880R.C0882id.content_container)).updatePaidAmenityOrderStatus();
    }
}
