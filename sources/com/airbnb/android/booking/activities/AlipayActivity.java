package com.airbnb.android.booking.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.airbnb.android.booking.C0704R;
import com.airbnb.android.booking.controller.AlipayNavigationController;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.models.payments.OldPaymentInstrument;
import com.airbnb.android.core.utils.ParcelStrap;
import icepick.State;

public class AlipayActivity extends AirActivity {
    private static final String EXTRA_ANALYTICS_DATA = "analytics_data";
    private static final String EXTRA_COUNTRY_CODE = "extra_country_code";
    private static final String EXTRA_QUICK_PAY = "extra_quick_pay";
    public static final String RESULT_EXTRA_ALIPAY_PAYMENT_INSTRUMENT = "result_code_alipay_payment_instrument";
    @State
    String alipayId;
    @State
    ParcelStrap analyticsData;
    @State
    String countryCode;
    @State
    long gibraltarInstrumentId;
    @State
    boolean isQuickPay;
    @State
    String nationalId;
    private AlipayNavigationController navigationController;
    @State
    OldPaymentInstrument paymentInstrument;
    @State
    String phoneNumber;

    public static Intent intentForCountryCode(Context context, String countryCode2, ParcelStrap analyticsData2) {
        return new Intent(context, AlipayActivity.class).putExtra("extra_country_code", countryCode2).putExtra(EXTRA_ANALYTICS_DATA, analyticsData2);
    }

    public static Intent intentForQuickPay(Context context, String countryCode2) {
        return new Intent(context, AlipayActivity.class).putExtra("extra_country_code", countryCode2).putExtra(EXTRA_QUICK_PAY, true);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C0704R.layout.activity_simple_fragment);
        if (savedInstanceState == null) {
            this.countryCode = getIntent().getStringExtra("extra_country_code");
            this.isQuickPay = getIntent().hasExtra(EXTRA_QUICK_PAY);
        }
        this.navigationController = new AlipayNavigationController(this, getSupportFragmentManager(), getAnalyticsData());
        if (savedInstanceState == null) {
            this.navigationController.initializeFlow();
        }
    }

    public void finishWithPaymentInstrument(OldPaymentInstrument paymentInstrument2) {
        this.paymentInstrument = paymentInstrument2;
        finishWithIntent(-1);
    }

    public void setAlipayId(String alipayId2) {
        this.alipayId = alipayId2;
    }

    public String getAlipayId() {
        return this.alipayId;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId2) {
        this.nationalId = nationalId2;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber2) {
        this.phoneNumber = phoneNumber2;
    }

    public long getGibraltarInstrumentId() {
        return this.gibraltarInstrumentId;
    }

    public void setGibraltarInstrumentId(long gibraltarInstrumentId2) {
        this.gibraltarInstrumentId = gibraltarInstrumentId2;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public ParcelStrap getAnalyticsData() {
        if (this.isQuickPay) {
            this.analyticsData = ParcelStrap.make().mo9947kv("is_quickpay", true);
        } else if (this.analyticsData == null) {
            this.analyticsData = (ParcelStrap) getIntent().getParcelableExtra(EXTRA_ANALYTICS_DATA);
        }
        return this.analyticsData;
    }

    public AlipayNavigationController getNavigationController() {
        return this.navigationController;
    }

    public boolean isQuickPay() {
        return this.isQuickPay;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    private void finishWithIntent(int resultCode) {
        Intent intent = new Intent();
        if (resultCode == -1) {
            intent.putExtra("result_code_alipay_payment_instrument", this.paymentInstrument);
        }
        setResult(resultCode, intent);
        this.navigationController.doneWithVerification();
    }
}
