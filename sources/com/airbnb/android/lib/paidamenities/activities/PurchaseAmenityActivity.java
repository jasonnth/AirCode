package com.airbnb.android.lib.paidamenities.activities;

import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.paidamenities.controllers.PurchaseAmenityNavigationController;
import icepick.State;

public class PurchaseAmenityActivity extends AirActivity {
    @State
    String confirmationCode;
    @State
    long listingId;
    private PurchaseAmenityNavigationController navigationController;
    @State
    long threadId;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        initPurchaseAmenityNavigationController(savedInstanceState);
        if (savedInstanceState == null) {
            Intent intent = getIntent();
            this.confirmationCode = intent.getStringExtra("confirmation_code");
            this.threadId = intent.getLongExtra("thread_id", -1);
            this.listingId = intent.getLongExtra("listing_id", -1);
            Check.state((this.confirmationCode.isEmpty() || this.threadId == -1 || this.listingId == -1) ? false : true);
            this.navigationController.initializeFlow();
        }
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.navigationController.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    public PurchaseAmenityNavigationController getNavigationController() {
        return this.navigationController;
    }

    public long getListingId() {
        return this.listingId;
    }

    public long getThreadId() {
        return this.threadId;
    }

    public String getConfirmationCode() {
        return this.confirmationCode;
    }

    private void initPurchaseAmenityNavigationController(Bundle savedInstanceState) {
        this.navigationController = new PurchaseAmenityNavigationController(this, getSupportFragmentManager(), savedInstanceState);
    }
}
