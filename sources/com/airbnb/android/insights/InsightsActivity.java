package com.airbnb.android.insights;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.BaseRequestListener;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.AirActivity;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.enums.ManageListingPriceType;
import com.airbnb.android.core.interfaces.OnEditPriceDoneListener;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.PriceFactor;
import com.airbnb.android.core.requests.DemandBasedPricingRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.DemandBasedPricingResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.hostcalendar.adapters.MultiDayPriceTipsEpoxyController.OnPriceTipsDisclaimerClickedListener;
import com.airbnb.android.insights.fragments.InsightsParentFragment;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.LoadingView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import java.util.ArrayList;
import java.util.Arrays;
import p032rx.Observer;

public class InsightsActivity extends AirActivity implements OnEditPriceDoneListener, OnPriceTipsDisclaimerClickedListener {
    protected static final String ALL_LISTINGS = "all_listings";
    protected static final String LISTING = "listing";
    protected static final String LISTING_ID = "listing_id";
    protected static final String SINGLE_INSIGHT_ONLY = "single_insight";
    protected static final String STORY_ID = "story_id";
    private InsightsDataController dataController;
    private InsightsParentFragment fragment;
    private InsightsAnalytics insightsAnalytics;
    public final RequestListener<ListingResponse> listingListener = new C0699RL().onResponse(InsightsActivity$$Lambda$5.lambdaFactory$(this)).onError(InsightsActivity$$Lambda$6.lambdaFactory$(this)).onComplete(InsightsActivity$$Lambda$7.lambdaFactory$(this)).build();
    @BindView
    LoadingView loadingView;
    final RequestListener<DemandBasedPricingResponse> minPriceListener = new C0699RL().onResponse(InsightsActivity$$Lambda$1.lambdaFactory$(this)).onError(InsightsActivity$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateLongTermPricingRequestListener = new C0699RL().onResponse(InsightsActivity$$Lambda$3.lambdaFactory$(this)).onError(InsightsActivity$$Lambda$4.lambdaFactory$(this)).build();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C6552R.layout.activity_insights);
        ButterKnife.bind((Activity) this);
        setToolbar(this.toolbar);
        Intent intent = getIntent();
        this.insightsAnalytics = new InsightsAnalytics(this.accountManager.getCurrentUserId());
        boolean singleInsightOnly = intent.getBooleanExtra(SINGLE_INSIGHT_ONLY, false);
        this.dataController = new InsightsDataController(this, savedInstanceState, this.insightsAnalytics);
        this.dataController.setSingleInsightOnly(singleInsightOnly);
        if (savedInstanceState != null) {
            this.fragment = (InsightsParentFragment) getSupportFragmentManager().findFragmentById(C6552R.C6554id.content_container);
        } else if (singleInsightOnly) {
            fetchSingleInsightInformation(intent.getLongExtra("listing_id", -1));
        } else {
            ArrayList<Listing> allListings = intent.getParcelableArrayListExtra(ALL_LISTINGS);
            Listing firstListing = (Listing) intent.getParcelableExtra("listing");
            this.dataController.setAllListings(allListings);
            this.dataController.setFirstListingPosition(allListings.indexOf(firstListing));
            this.fragment = InsightsParentFragment.newInstance();
        }
        if (this.fragment != null) {
            showFragment();
        }
    }

    public InsightsDataController getDataController() {
        return this.dataController;
    }

    private void showFragment() {
        getSupportFragmentManager().beginTransaction().replace(C6552R.C6554id.content_container, this.fragment).commit();
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.dataController.onSaveInstanceState(outState);
    }

    public void onBackPressed() {
        if (!isRequestFiring()) {
            super.onBackPressed();
        }
    }

    /* access modifiers changed from: protected */
    public void onHomeActionPressed() {
        if (!isRequestFiring()) {
            super.onHomeActionPressed();
        }
    }

    private boolean isRequestFiring() {
        return this.requestManager.hasRequest((BaseRequestListener<T>) this.updateLongTermPricingRequestListener, UpdateListingRequest.class) || this.requestManager.hasRequest((BaseRequestListener<T>) this.minPriceListener, DemandBasedPricingRequest.class);
    }

    public void setActivityToolbar() {
        setToolbar(this.toolbar);
    }

    public AirToolbar getToolbar() {
        return this.toolbar;
    }

    public void setToolbarVisible(boolean visible) {
        ViewLibUtils.setVisibleIf(this.toolbar, visible);
    }

    public static Intent intentForFragment(Context context, Listing listing, ArrayList<Listing> allListings) {
        return new Intent(context, InsightsActivity.class).putExtra(ALL_LISTINGS, allListings).putExtra("listing", listing);
    }

    public static Intent intentForPushNotification(Context context, long listingId, String storyId) {
        return new Intent(context, InsightsActivity.class).putExtra("listing_id", listingId).putExtra(STORY_ID, storyId).putExtra(SINGLE_INSIGHT_ONLY, true);
    }

    private void fetchSingleInsightInformation(long listingId) {
        toggleLoading(true);
        ListingRequest.forSingleInsight(AirbnbAccountManager.currentUserId(), listingId).withListener((Observer) this.listingListener).execute(this.requestManager);
    }

    public void toggleLoading(boolean isLoading) {
        ViewLibUtils.setVisibleIf(this.loadingView, isLoading);
    }

    public void onEditPriceDone(ManageListingPriceType priceType, int newPrice) {
        if (newPrice != 0) {
            this.insightsAnalytics.trackImplicitConversion(this.fragment.getInsight(), newPrice);
            DemandBasedPricingRequest.updateMinPrice(newPrice, this.fragment.getListing().getId()).withListener((Observer) this.minPriceListener).execute(this.requestManager);
            return;
        }
        this.fragment.returnToCarousel();
    }

    static /* synthetic */ void lambda$new$1(InsightsActivity insightsActivity, AirRequestNetworkException e) {
        insightsActivity.fragment.returnToCarousel();
        insightsActivity.dataController.notifyNetworkError(e);
    }

    public InsightsAnalytics getInsightsAnalytics() {
        return this.insightsAnalytics;
    }

    public void setLongTermPricing(PriceFactor weeklyFactor, PriceFactor monthlyFactor) {
        this.insightsAnalytics.trackImplicitConversion(this.fragment.getInsight(), weeklyFactor);
        UpdateListingRequest.forFields(this.fragment.getListing().getId(), Strap.make().mo11635kv("weekly_price_factor", weeklyFactor.getFactorValue().doubleValue()).mo11635kv("monthly_price_factor", monthlyFactor.getFactorValue().doubleValue())).withListener((Observer) this.updateLongTermPricingRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$2(InsightsActivity insightsActivity, SimpleListingResponse response) {
        insightsActivity.fragment.getListing().setWeeklyPriceFactor(response.listing.getWeeklyPriceFactor());
        insightsActivity.fragment.getListing().setMonthlyPriceFactor(response.listing.getMonthlyPriceFactor());
        insightsActivity.fragment.returnToCarousel();
    }

    static /* synthetic */ void lambda$new$3(InsightsActivity insightsActivity, AirRequestNetworkException e) {
        insightsActivity.fragment.returnToCarousel();
        insightsActivity.dataController.notifyNetworkError(e);
    }

    public void onDisclaimerClicked() {
        this.fragment.showPriceTipsDisclaimer();
    }

    static /* synthetic */ void lambda$new$4(InsightsActivity insightsActivity, ListingResponse response) {
        insightsActivity.dataController.setAllListings(new ArrayList(Arrays.asList(new Listing[]{response.listing})));
        insightsActivity.dataController.setFirstListingPosition(0);
        insightsActivity.fragment = InsightsParentFragment.newInstance(insightsActivity.getIntent().getStringExtra(STORY_ID));
        insightsActivity.showFragment();
    }
}
