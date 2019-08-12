package com.airbnb.android.booking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.booking.controller.CreditCardNavigationController;
import com.airbnb.android.core.activities.SheetFlowActivity;
import com.airbnb.android.core.activities.SheetFlowActivity.SheetTheme;
import com.airbnb.android.core.models.payments.CreditCardDetails;
import com.airbnb.android.core.utils.ParcelStrap;
import icepick.State;

public class CreditCardActivity extends SheetFlowActivity {
    private static final String EXTRA_ANALYTICS_DATA = "extra_analytics_data";
    private static final String EXTRA_COUNTRY_CODE = "extra_country_code";
    private static final String EXTRA_FLOW = "extra_flow";
    private static final int REQUEST_CANCEL = 1;
    public static final int REQUEST_CODE_ADD_CARD = 11001;
    public static final int REQUEST_CODE_POSTAL_RETRY = 11002;
    private static final int REQUEST_OK = 2;
    public static final String RESULT_EXTRA_CREDIT_CARD = "result_extra_credit_card";
    public static final String RESULT_EXTRA_POSTAL_CODE = "result_extra_postal_code";
    @State
    ParcelStrap analyticsData;
    @State
    CreditCardDetails creditCardDetails;
    @State
    Flow flow;
    private CreditCardNavigationController navigationController;

    public enum Flow {
        PostalCodeRetry,
        AddForBooking,
        AddForQuickPay,
        AddForGiftCardRedeem
    }

    public static Intent intentForAdding(Context context, String countryCode, ParcelStrap analyticsData2) {
        return new Intent(context, CreditCardActivity.class).putExtra(EXTRA_ANALYTICS_DATA, analyticsData2).putExtra("extra_country_code", countryCode).putExtra("extra_flow", Flow.AddForBooking.ordinal());
    }

    public static Intent intentForPostalCodeRetry(Context context, ParcelStrap analyticsData2) {
        return new Intent(context, CreditCardActivity.class).putExtra(EXTRA_ANALYTICS_DATA, analyticsData2).putExtra("extra_flow", Flow.PostalCodeRetry.ordinal());
    }

    public static Intent intentForQuickPay(Context context, String countryCode) {
        return new Intent(context, CreditCardActivity.class).putExtra("extra_country_code", countryCode).putExtra("extra_flow", Flow.AddForQuickPay.ordinal());
    }

    public static Intent intentForGiftCardRedeem(Context context, String countryCode) {
        return new Intent(context, CreditCardActivity.class).putExtra("extra_country_code", countryCode).putExtra("extra_flow", Flow.AddForGiftCardRedeem.ordinal());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.navigationController = new CreditCardNavigationController(this, getSupportFragmentManager(), savedInstanceState);
        if (savedInstanceState == null) {
            this.creditCardDetails = CreditCardDetails.builder().countryCode(getIntent().getStringExtra("extra_country_code")).build();
            this.analyticsData = (ParcelStrap) getIntent().getParcelableExtra(EXTRA_ANALYTICS_DATA);
            this.flow = Flow.values()[getIntent().getIntExtra("extra_flow", -1)];
            this.navigationController.initializeFlow(this.flow);
        }
        this.progressBar.setVisibility(8);
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.navigationController.onSaveInstanceState(outState);
    }

    public SheetTheme getDefaultTheme() {
        return SheetTheme.JELLYFISH;
    }

    public void updateCardNumber(String cardNumber) {
        this.creditCardDetails = this.creditCardDetails.toBuilder().cardNumber(cardNumber).build();
        this.navigationController.doneWithCardNumber();
    }

    public void updateExpirationDate(String expirationMonth, String expirationYear) {
        this.creditCardDetails = this.creditCardDetails.toBuilder().expirationMonth(expirationMonth).expirationYear(expirationYear).build();
        this.navigationController.doneWithExpirationDate();
    }

    public void updateSecurityCode(String cvv) {
        this.creditCardDetails = this.creditCardDetails.toBuilder().cvv(cvv).build();
        this.navigationController.doneWithSecurityCode();
    }

    public void updatePostalCode(String postalCode) {
        this.creditCardDetails = this.creditCardDetails.toBuilder().postalCode(postalCode).build();
        Intent resultData = new Intent();
        switch (this.flow) {
            case AddForBooking:
            case AddForQuickPay:
            case AddForGiftCardRedeem:
                resultData.putExtra("result_extra_credit_card", this.creditCardDetails.toCreditCard());
                setResult(-1, resultData);
                this.navigationController.doneWithPostalCode();
                return;
            case PostalCodeRetry:
                resultData.putExtra(RESULT_EXTRA_POSTAL_CODE, this.creditCardDetails.postalCode());
                setResult(-1, resultData);
                this.navigationController.doneWithPostalCode();
                return;
            default:
                return;
        }
    }

    public ParcelStrap getAnalyticsData() {
        return this.analyticsData;
    }

    public CreditCardDetails getCreditCardDetails() {
        return this.creditCardDetails;
    }

    public boolean isQuickPay() {
        return this.flow.equals(Flow.AddForQuickPay);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                setResult(0, new Intent());
                this.navigationController.doneWithPostalCode();
                return;
            case 2:
                Intent resultData = new Intent();
                resultData.putExtra("result_extra_credit_card", this.creditCardDetails.toCreditCard());
                setResult(-1, resultData);
                this.navigationController.doneWithPostalCode();
                return;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                return;
        }
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }
}
