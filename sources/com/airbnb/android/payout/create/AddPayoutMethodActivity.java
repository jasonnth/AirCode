package com.airbnb.android.payout.create;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodDataController;
import com.airbnb.android.payout.create.controllers.AddPayoutMethodNavigationController;
import com.airbnb.android.payout.create.fragments.SelectPayoutCountryFragment.CountrySelectedListener;
import com.airbnb.android.payout.create.interfaces.AddPayoutMethodControllerInterface;

public class AddPayoutMethodActivity extends AirActivity implements CountrySelectedListener, AddPayoutMethodControllerInterface {
    private AddPayoutMethodDataController dataController;
    private AddPayoutMethodNavigationController navigationController;

    public static Intent newIntent(Context context) {
        return new Intent(context, AddPayoutMethodActivity.class);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C7552R.layout.activity_simple_payout_fragment);
        ButterKnife.bind((Activity) this);
        this.navigationController = new AddPayoutMethodNavigationController(this, getSupportFragmentManager(), savedInstanceState);
        this.dataController = new AddPayoutMethodDataController(this.requestManager, this.accountManager, savedInstanceState);
        this.dataController.fetchPayoutInfoForms(getIntent().getStringExtra("extra_country_code"));
        if (savedInstanceState == null) {
            this.navigationController.startFlow();
        }
    }

    public AddPayoutMethodNavigationController getNavigationController() {
        return this.navigationController;
    }

    public AddPayoutMethodDataController getDataController() {
        return this.dataController;
    }

    /* access modifiers changed from: protected */
    public boolean homeActionPopsFragmentStack() {
        return true;
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
    }

    public void onCountrySelected(CountryCodeItem item) {
        this.dataController.fetchPayoutInfoForms(item.getCountryCode());
        getSupportFragmentManager().popBackStack();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 281) {
            this.navigationController.navigateToAddPayoutFinish();
        }
    }
}
