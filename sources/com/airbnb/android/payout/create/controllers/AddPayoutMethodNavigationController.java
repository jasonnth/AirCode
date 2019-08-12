package com.airbnb.android.payout.create.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.utils.NavigationUtils;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.fragments.AddPayoutAccountInfoFragment;
import com.airbnb.android.payout.create.fragments.AddPayoutAddressFragment;
import com.airbnb.android.payout.create.fragments.AddPayoutCompleteFragment;
import com.airbnb.android.payout.create.fragments.AddPayoutConfirmationFragment;
import com.airbnb.android.payout.create.fragments.AddPayoutMethodHelpFragment;
import com.airbnb.android.payout.create.fragments.ChooseAccountTypeFragment;
import com.airbnb.android.payout.create.fragments.ChoosePayoutAddressFragment;
import com.airbnb.android.payout.create.fragments.ChoosePayoutMethodFragment;
import com.airbnb.android.payout.create.fragments.PayoutMethodInfoFragment;
import com.airbnb.android.payout.create.fragments.SelectPayoutCountryFragment;
import com.airbnb.android.payout.models.PayoutInfoForm;

public class AddPayoutMethodNavigationController {
    private final Activity activity;
    private final FragmentManager fragmentManager;

    public AddPayoutMethodNavigationController(Activity activity2, FragmentManager fragmentManager2, Bundle savedState) {
        IcepickWrapper.restoreInstanceState(this, savedState);
        this.activity = activity2;
        this.fragmentManager = fragmentManager2;
    }

    public void navigateToPayoutMethodInfo(PayoutInfoForm payoutInfoForm) {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, PayoutMethodInfoFragment.withSelectedPayoutMethod(payoutInfoForm), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void doneWithPayoutMethodInfo(PayoutInfoForm selectedPayoutInfoForm) {
        if (selectedPayoutInfoForm.isBankDeposit() && selectedPayoutInfoForm.shouldShowAccountType()) {
            navigateToChooseAccountType();
        } else if (selectedPayoutInfoForm.isURLRedirect()) {
            navigateToChoosePayoutAdress();
        } else {
            navigateToAddDetailedAccountInfo();
        }
    }

    public void navigateToChooseAccountType() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, ChooseAccountTypeFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void navigateToAddDetailedAccountInfo() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, AddPayoutAccountInfoFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void showCountrySelectionFragment(SelectPayoutCountryFragment fragment) {
        NavigationUtils.showModal(this.fragmentManager, this.activity, fragment, C7552R.C7554id.content_container, C7552R.C7554id.modal_container, true);
    }

    public void showHelpContentFragment(AddPayoutMethodHelpFragment addPayoutMethodHelpFragment) {
        NavigationUtils.showModal(this.fragmentManager, this.activity, addPayoutMethodHelpFragment, C7552R.C7554id.content_container, C7552R.C7554id.modal_container, true);
    }

    public void navigateToChoosePayoutAdress() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, ChoosePayoutAddressFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void doneWithAddPayoutAddress() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, AddPayoutConfirmationFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void doneWithAddPayoutAddress(String redirectUrl) {
        this.activity.startActivityForResult(PayoutActivityIntents.forPayoutRedirectWebview(this.activity, redirectUrl), PayoutActivityIntents.REQUEST_CODE_PAYONEER_REDIRECT);
    }

    public void navigateToAddNewAddress() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, AddPayoutAddressFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void navigateToAddPayoutFinish() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, AddPayoutCompleteFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }

    public void startFlow() {
        NavigationUtils.showFragment(this.fragmentManager, this.activity, ChoosePayoutMethodFragment.newInstance(), C7552R.C7554id.content_container, FragmentTransitionType.SlideInFromSide, true);
    }
}
