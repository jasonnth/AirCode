package com.airbnb.android.payout.manage;

import android.app.Activity;
import android.os.Bundle;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.enums.FragmentTransitionType;
import com.airbnb.android.core.intents.PayoutActivityIntents;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.presenters.CountryCodeItem;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.responses.CountriesResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.PayoutGraph;
import com.airbnb.android.payout.create.fragments.SelectPayoutCountryFragment;
import com.airbnb.android.payout.create.fragments.SelectPayoutCountryFragment.CountrySelectedListener;
import com.airbnb.android.payout.logging.AddPayoutMethodJitneyLogger;
import com.airbnb.jitney.event.logging.PayoutMethodSelectAction.p192v1.C2547PayoutMethodSelectAction;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import icepick.State;
import java.util.ArrayList;
import p032rx.Observer;

public class SelectPayoutCountryActivity extends AirActivity implements CountrySelectedListener {
    AddPayoutMethodJitneyLogger addPayoutMethodJitneyLogger;
    final RequestListener<CountriesResponse> countriesListener = new C0699RL().onResponse(SelectPayoutCountryActivity$$Lambda$1.lambdaFactory$(this)).build();
    @State
    CountryCodeItem countryCodeItem;
    @State
    ArrayList<Country> newPayoutSupportingCountries;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PayoutGraph) CoreApplication.instance(this).component()).inject(this);
        setContentView(C7552R.layout.activity_simple_payout_fragment);
        ButterKnife.bind((Activity) this);
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.enter, type.exit);
        if (this.newPayoutSupportingCountries == null) {
            fetchNewPayoutCountries();
        }
        if (savedInstanceState == null) {
            this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.CountryPickerImpression);
            showFragment(SelectPayoutCountryFragment.newInstanceWithDefaultCountry(this.accountManager.getCurrentUser().getDefaultCountryOfResidence()), C7552R.C7554id.content_container, FragmentTransitionType.SlideFromBottom, true);
        }
    }

    public void onCountrySelected(CountryCodeItem item) {
        if (this.newPayoutSupportingCountries != null) {
            if (!Trebuchet.launch(TrebuchetKeys.SHOW_NEW_PAYOUT_FLOW, false) || !supportNewPayout(item.getCountryCode())) {
                startActivity(PayoutActivityIntents.legacyPayoutIntent(this, item.getCountryCode()));
            } else {
                startActivity(PayoutActivityIntents.forAddPayoutMethod(this, item.getCountryCode()));
            }
            this.addPayoutMethodJitneyLogger.payoutMethodSelect(C2547PayoutMethodSelectAction.CountryPickerNext);
            finish();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void finish() {
        super.finish();
        FragmentTransitionType type = FragmentTransitionType.SlideFromBottom;
        overridePendingTransition(type.popEnter, type.popExit);
    }

    private void fetchNewPayoutCountries() {
        CountriesRequest.forNewPayoutSupportingCountries().withListener((Observer) this.countriesListener).skipCache().execute(this.requestManager);
    }

    private boolean supportNewPayout(String countryCode) {
        Check.notNull(this.newPayoutSupportingCountries);
        return FluentIterable.from((Iterable<E>) this.newPayoutSupportingCountries).anyMatch(SelectPayoutCountryActivity$$Lambda$2.lambdaFactory$(countryCode));
    }

    static /* synthetic */ void lambda$new$1(SelectPayoutCountryActivity selectPayoutCountryActivity, CountriesResponse response) {
        selectPayoutCountryActivity.newPayoutSupportingCountries = Lists.newArrayList((Iterable<? extends E>) response.countries);
        if (selectPayoutCountryActivity.countryCodeItem != null) {
            selectPayoutCountryActivity.onCountrySelected(selectPayoutCountryActivity.countryCodeItem);
        }
    }
}
