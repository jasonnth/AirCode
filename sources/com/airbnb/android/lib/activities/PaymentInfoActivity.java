package com.airbnb.android.lib.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.booking.fragments.SelectCountryFragment;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.controller.PaymentInfoNavigationController;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutAddressFragment;
import icepick.State;
import java.util.ArrayList;
import java.util.List;

public class PaymentInfoActivity extends AirActivity {
    public static final int REQUEST_CODE_SELECT_COUNTRY = 11011;
    @State
    AirAddress address;
    @State
    String name;
    private PaymentInfoNavigationController navigationController;
    @State
    String payoutCurrency;
    @State
    ArrayList<PayoutInfoType> payoutInfoTypes;

    public static Intent intent(Context context) {
        return new Intent(context, PaymentInfoActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0880R.layout.activity_simple_fragment);
        this.navigationController = new PaymentInfoNavigationController(this, getSupportFragmentManager());
        if (savedInstanceState == null) {
            this.navigationController.initializeFlow();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            switch (requestCode) {
                case REQUEST_CODE_SELECT_COUNTRY /*11011*/:
                    ((PayoutAddressFragment) this.navigationController.getCurrentFragment()).setCountryCode(data.getStringExtra(SelectCountryFragment.EXTRA_RESULT_COUNTRY_CODE));
                    return;
                default:
                    super.onActivityResult(requestCode, resultCode, data);
                    return;
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public PaymentInfoNavigationController getNavigationController() {
        return this.navigationController;
    }

    public void setAddress(AirAddress address2) {
        this.address = address2;
    }

    public AirAddress getAddress() {
        return this.address;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getPayoutCurrency() {
        return this.payoutCurrency;
    }

    public void setPayoutCurrency(String payoutCurrency2) {
        this.payoutCurrency = payoutCurrency2;
    }

    public ArrayList<PayoutInfoType> getPayoutInfoTypes() {
        return this.payoutInfoTypes;
    }

    public void setPayoutInfoTypes(List<PayoutInfoType> payoutInfoTypes2) {
        this.payoutInfoTypes = new ArrayList<>(payoutInfoTypes2);
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
