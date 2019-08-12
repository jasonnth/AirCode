package com.airbnb.android.lib.controller;

import android.app.Activity;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.booking.fragments.SelectCountryFragment;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.controllers.NavigationController;
import com.airbnb.android.core.models.PayoutInfoType;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.activities.PaymentInfoActivity;
import com.airbnb.android.lib.fragments.paymentinfo.payout.AddPayoutLandingFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.BankTransferInfoFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.BankTransferNameFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.DirectDepositInfoFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.DirectDepositNameFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.DirectDepositOptionFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayPalPayoutFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutAddedFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutAddressFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutCurrencyFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutInfoTypesFragment;
import com.airbnb.android.lib.fragments.paymentinfo.payout.PayoutSummaryFragment;

public class PaymentInfoNavigationController extends NavigationController {
    public PaymentInfoNavigationController(Activity activity, FragmentManager fragmentManager) {
        super(activity, fragmentManager);
    }

    public void initializeFlow() {
        transitionTo(PayoutSummaryFragment.newInstance());
    }

    public void onAddPayout() {
        transitionTo(AddPayoutLandingFragment.newInstance());
    }

    public void onPayoutRowClicked() {
        transitionTo(AddPayoutLandingFragment.newInstance());
    }

    public void doneWithLanding() {
        transitionTo(PayoutAddressFragment.newInstance());
    }

    public void onSelectCountry(String countryCode) {
        this.activity.startActivityForResult(ModalActivity.intentForFragment((PaymentInfoActivity) this.activity, SelectCountryFragment.forAddPayout(C0880R.string.payout_address_select_payment_country_title, countryCode)), PaymentInfoActivity.REQUEST_CODE_SELECT_COUNTRY);
    }

    public void doneWithAddress() {
        transitionTo(PayoutInfoTypesFragment.forPayoutTypes(((PaymentInfoActivity) this.activity).getPayoutInfoTypes()));
    }

    public void doneWithSelectPayoutInfoType(PayoutInfoType payoutInfoType) {
        transitionTo(PayoutCurrencyFragment.forPayoutInfoType(payoutInfoType));
    }

    public void doneWithSelectPayoutCurrency(PayoutInfoType payoutInfoType) {
        switch (payoutInfoType.getInfoType()) {
            case ACH:
                transitionTo(DirectDepositOptionFragment.newInstance());
                return;
            case PayPal:
                transitionTo(PayPalPayoutFragment.newInstance());
                return;
            case BankTransfer:
                transitionTo(BankTransferNameFragment.newInstance());
                return;
            default:
                throw new IllegalStateException("Unhandled payout option.");
        }
    }

    public void doneWithSelectAccountType() {
        transitionTo(DirectDepositNameFragment.newInstance());
    }

    public void doneWithDirectDepositName() {
        transitionTo(DirectDepositInfoFragment.newInstance());
    }

    public void doneWithDirectDepositInfo() {
        transitionTo(PayoutAddedFragment.newInstance());
    }

    public void doneWithPayPalInfo() {
        transitionTo(PayoutAddedFragment.newInstance());
    }

    public void doneWithBankTransferName() {
        transitionTo(BankTransferInfoFragment.newInstance());
    }

    public void doneWithBankTransferInfo() {
        transitionTo(PayoutAddedFragment.newInstance());
    }

    public void doneAddingPayout() {
        this.fragmentManager.popBackStack(this.fragmentManager.getBackStackEntryAt(0).getId(), 1);
    }
}
