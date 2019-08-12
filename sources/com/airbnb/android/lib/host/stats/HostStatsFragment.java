package com.airbnb.android.lib.host.stats;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.airbnb.airrequest.BaseRequestV2;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NonResubscribableRequestListener;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline;
import com.airbnb.android.core.analytics.PerformanceLoggerTimeline.Event;
import com.airbnb.android.core.authentication.AirbnbAccountManager;
import com.airbnb.android.core.controllers.BottomBarController;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.erf.PricingFeatureToggles;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.host.stats.HostStatsJitneyLogger;
import com.airbnb.android.core.intents.ReactNativeIntents;
import com.airbnb.android.core.intents.WebViewIntentBuilder;
import com.airbnb.android.core.models.CohostingStandard;
import com.airbnb.android.core.models.CurrencyAmount;
import com.airbnb.android.core.models.HostEarnings;
import com.airbnb.android.core.models.HostStandardMetrics;
import com.airbnb.android.core.models.HostStandardMetrics.MetricState;
import com.airbnb.android.core.models.Insight;
import com.airbnb.android.core.models.Listing;
import com.airbnb.android.core.models.ParcelableBigDecimal;
import com.airbnb.android.core.models.SuperhostData;
import com.airbnb.android.core.requests.AirBatchRequest;
import com.airbnb.android.core.requests.CohostingStandardRequest;
import com.airbnb.android.core.requests.InsightsRequest;
import com.airbnb.android.core.requests.ListingRequest;
import com.airbnb.android.core.requests.SuperhostRequest;
import com.airbnb.android.core.responses.AirBatchResponse;
import com.airbnb.android.core.responses.CohostingStandardResponse;
import com.airbnb.android.core.responses.InsightsResponse;
import com.airbnb.android.core.responses.ListingResponse;
import com.airbnb.android.core.responses.SuperhostResponse;
import com.airbnb.android.core.utils.PercentageUtils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.insights.InsightsActivity;
import com.airbnb.android.insights.adapters.ListingInsightsAdapter;
import com.airbnb.android.insights.adapters.ListingInsightsAdapter.ListingInsightClickListener;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.views.SuperhostStatusView;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.BarRow;
import com.airbnb.p027n2.components.BigNumberRow;
import com.airbnb.p027n2.components.DocumentCarouselMarquee;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.components.StandardRow;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.google.common.collect.FluentIterable;
import icepick.State;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import p032rx.Observer;

public class HostStatsFragment extends AirFragment implements ListingInsightClickListener {
    private static final int INSIGHTS_REQUEST_CODE = 700;
    private static final int LISTINGS_FETCH_COUNT = 10;
    AirbnbAccountManager accountManager;
    @BindView
    BarRow averageRatingBarRow;
    final RequestListener<HostStatsAverageRatingResponse> averageRatingListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$2.lambdaFactory$(this)).build();
    public final NonResubscribableRequestListener<AirBatchResponse> batchResponseRequestListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$10.lambdaFactory$(this)).onError(HostStatsFragment$$Lambda$11.lambdaFactory$(this)).buildWithoutResubscription();
    BottomBarController bottomBarController;
    @BindView
    BarRow cohostingResponseRateBarRow;
    /* access modifiers changed from: 0000 */
    @State
    public CohostingStandard cohostingStandard;
    @BindView
    SectionHeader cohostingStandardsHeader;
    @BindView
    View cohostingStandardsLayout;
    final RequestListener<CohostingStandardResponse> cohostingStandardsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    StandardRow cohostingStandardsSummary;
    @BindView
    BarRow commitmentRateBarRow;
    @BindView
    ViewGroup contentLayout;
    @State
    double currentUserAverageRating;
    @BindView
    DocumentMarquee defaultMarquee;
    final RequestListener<DemandCountsResponse> demandCountsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$7.lambdaFactory$(this)).build();
    @BindView
    SectionHeader demandSectionHeader;
    @BindView
    View earningsEmptyStateView;
    /* access modifiers changed from: 0000 */
    @State
    public HostEarnings earningsForThisMonth;
    @BindView
    View earningsLayout;
    @BindView
    SectionHeader earningsSectionHeader;
    private DateFormat earningsSectionHeaderDateFormat;
    /* access modifiers changed from: 0000 */
    @State
    public HostStandardMetrics hostStandardMetrics;
    final RequestListener<HostStandardsResponse> hostStandardsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$5.lambdaFactory$(this)).build();
    @BindView
    SectionHeader hostingStandardsHeader;
    @BindView
    View hostingStandardsLayout;
    @State
    ArrayList<Insight> insights;
    final RequestListener<InsightsResponse> insightsMetaDataRequestListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$9.lambdaFactory$(this)).build();
    private final DecimalFormat integerFormatter = new DecimalFormat("###,###,###");
    @BindView
    DocumentCarouselMarquee listingInsightsMarquee;
    @BindView
    View listingViewsEmptyStateView;
    @BindView
    BigNumberRow listingViewsRow;
    @State
    ArrayList<Listing> listings;
    final RequestListener<ListingResponse> listingsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$1.lambdaFactory$(this)).build();
    @BindView
    ViewGroup montlyEarningsLayout;
    @State
    int newBookings;
    @BindView
    BigNumberRow newBookingsRow;
    private final DecimalFormat ratingFormatter = new DecimalFormat("0.0");
    @BindView
    View ratingsEmptyStateView;
    @BindView
    BigNumberRow ratingsRow;
    @BindView
    SectionHeader ratingsSectionHeader;
    @BindView
    RefreshLoader refreshLoader;
    @BindView
    BarRow responseRateBarRow;
    final RequestListener<HostEarningsResponse> revenueDetailsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    VerboseScrollView scrollView;
    @State
    Double similarHostAverageRating;
    HostStatsJitneyLogger statsJitneyLogger;
    /* access modifiers changed from: 0000 */
    @State
    public SuperhostData superhostData;
    final RequestListener<SuperhostResponse> superhostListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    SuperhostStatusView superhostStatusView;
    @BindView
    AirSwipeRefreshLayout swipeRefreshLayout;
    @State
    int totalListingViewsCount;
    @State
    CurrencyAmount totalPaidOutThisYear;
    @BindView
    LinkActionRow viewTransactionsRow;
    @BindView
    StandardRow yearlyPaidOutRow;
    final RequestListener<HostEarningsResponse> yearlyRevenueDetailsListener = new C0699RL().onResponse(HostStatsFragment$$Lambda$8.lambdaFactory$(this)).build();

    public static Fragment newInstance() {
        return new HostStatsFragment();
    }

    @SuppressLint({"SimpleDateFormat"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_host_stats, container, false);
        ((AirbnbGraph) AirbnbApplication.instance().component()).inject(this);
        ButterKnife.bind(this, view);
        this.earningsSectionHeader.setButtonOnClickListener(HostStatsFragment$$Lambda$12.lambdaFactory$(this));
        this.ratingsSectionHeader.setButtonOnClickListener(HostStatsFragment$$Lambda$13.lambdaFactory$(this));
        this.demandSectionHeader.setButtonOnClickListener(HostStatsFragment$$Lambda$14.lambdaFactory$(this));
        this.averageRatingBarRow.showDivider(false);
        this.earningsSectionHeaderDateFormat = new SimpleDateFormat(getString(C0880R.string.full_month_format));
        this.swipeRefreshLayout.setScrollableChild(this.scrollView);
        this.swipeRefreshLayout.setOnRefreshListener(HostStatsFragment$$Lambda$15.lambdaFactory$(this));
        if (savedInstanceState == null) {
            this.refreshLoader.setVisibility(0);
            loadDataFromNetwork();
        } else if (isLoadingFromNetwork()) {
            this.refreshLoader.setVisibility(0);
        } else {
            loadViews();
        }
        loadInsights();
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$3(HostStatsFragment hostStatsFragment) {
        if (!hostStatsFragment.isLoadingFromNetwork()) {
            hostStatsFragment.swipeRefreshLayout.setRefreshing(true);
            hostStatsFragment.loadDataFromNetwork();
        }
    }

    public void onStart() {
        super.onStart();
        this.bottomBarController.setShowBottomBar(true, true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 700) {
            this.swipeRefreshLayout.setRefreshing(true);
            this.listingInsightsMarquee.setVisibility(8);
            this.defaultMarquee.setVisibility(0);
            fetchInsights();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private boolean isLoadingFromNetwork() {
        return this.requestManager.hasRequests(this.batchResponseRequestListener);
    }

    private void showStatsFragment(Class<? extends Fragment> fragmentClass) {
        showStatsFragment(fragmentClass, null);
    }

    /* access modifiers changed from: private */
    public void showStatsFragment(Class<? extends Fragment> fragmentClass, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        startActivity(HostStatsActivity.getIntentForFragment(getContext(), fragmentClass, this.listings, bundle, HostListingSelectorFragment.getBundle(this.listings, this.totalListingViewsCount, this.currentUserAverageRating)));
    }

    private void loadDataFromNetwork() {
        long userId = this.accountManager.getCurrentUserId();
        List<BaseRequestV2<?>> requests = new ArrayList<>();
        requests.add(ListingRequest.forHostStatsPicker(userId, 0, 10, this.listingsListener));
        requests.add(HostMonthlyEarningsRequest.forCurrentUser().withListener((Observer) this.revenueDetailsListener));
        requests.add(HostYearlyEarningsRequest.forCurrentUser().withListener((Observer) this.yearlyRevenueDetailsListener));
        requests.add(SuperhostRequest.forHostStats(userId).withListener((Observer) this.superhostListener));
        requests.add(DemandCountsRequest.forUserId(userId).withListener((Observer) this.demandCountsListener));
        requests.add(HostStandardsRequest.forUserId(userId).withListener((Observer) this.hostStandardsListener));
        requests.add(HostStatsAverageRatingRequest.forUserId(userId).withListener((Observer) this.averageRatingListener));
        if (FeatureToggles.showCohostingStandards()) {
            requests.add(CohostingStandardRequest.forUser(userId).withListener((Observer) this.cohostingStandardsListener));
        }
        new AirBatchRequest(requests, this.batchResponseRequestListener).execute(this.requestManager);
    }

    static /* synthetic */ void lambda$new$4(HostStatsFragment hostStatsFragment, ListingResponse response) {
        hostStatsFragment.listings = response.getListingsArrayList();
        hostStatsFragment.fetchInsights();
    }

    private void fetchInsights() {
        if (PricingFeatureToggles.showDLSInsights()) {
            this.defaultMarquee.setVisibility(8);
            this.listingInsightsMarquee.setVisibility(0);
            this.listingInsightsMarquee.setLoading(true);
            InsightsRequest.forMetaData().withListener((Observer) this.insightsMetaDataRequestListener).execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$5(HostStatsFragment hostStatsFragment, HostStatsAverageRatingResponse response) {
        hostStatsFragment.currentUserAverageRating = response.getAverageRating().doubleValue();
        hostStatsFragment.similarHostAverageRating = response.getSimilarHostAverageRating();
    }

    static /* synthetic */ void lambda$new$10(HostStatsFragment hostStatsFragment, DemandCountsResponse response) {
        hostStatsFragment.totalListingViewsCount = response.getPageViews();
        hostStatsFragment.newBookings = response.getNewBookings();
    }

    static /* synthetic */ void lambda$new$11(HostStatsFragment hostStatsFragment, HostEarningsResponse response) {
        hostStatsFragment.totalPaidOutThisYear = response.getEarnings() == null ? null : response.getEarnings().getPaidOutCombined();
    }

    static /* synthetic */ void lambda$new$12(HostStatsFragment hostStatsFragment, InsightsResponse response) {
        hostStatsFragment.insights = new ArrayList<>(response.stories);
        hostStatsFragment.loadInsights();
    }

    /* access modifiers changed from: private */
    public void loadViews() {
        loadEarnings();
        loadRatings();
        loadHostingStandards();
        loadSuperhost();
        loadCohostingStandards();
        loadDemand();
        this.swipeRefreshLayout.setRefreshing(false);
        this.refreshLoader.setVisibility(8);
        this.contentLayout.setVisibility(0);
        PerformanceLoggerTimeline.logTimeToInteraction(Event.HOST_STATS_SUMMARY);
    }

    private void loadDemand() {
        if (!checkDemandEmptyStateAndUpdateViews()) {
            this.listingViewsRow.setTitle((CharSequence) this.integerFormatter.format((long) this.totalListingViewsCount));
            this.demandSectionHeader.setDescription((CharSequence) getString(C0880R.string.host_stats_listing_views_past_x_days, Integer.valueOf(30)));
            this.newBookingsRow.setTitle((CharSequence) this.integerFormatter.format((long) this.newBookings));
        }
    }

    private void loadInsights() {
        boolean hasInsights;
        if (!ListUtils.isEmpty((Collection<?>) this.insights)) {
            hasInsights = true;
        } else {
            hasInsights = false;
        }
        ViewLibUtils.setVisibleIf(this.listingInsightsMarquee, hasInsights);
        ViewLibUtils.setGoneIf(this.defaultMarquee, hasInsights);
        this.swipeRefreshLayout.setRefreshing(false);
        this.listingInsightsMarquee.setLoading(false);
        if (hasInsights) {
            this.listingInsightsMarquee.setAdapter(new ListingInsightsAdapter(this.listings, this.insights, this, getResources()));
        }
    }

    private boolean checkDemandEmptyStateAndUpdateViews() {
        boolean isEmpty;
        boolean z = true;
        if (this.totalListingViewsCount == 0) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        ViewLibUtils.setVisibleIf(this.listingViewsEmptyStateView, isEmpty);
        ViewLibUtils.setGoneIf(this.listingViewsRow, isEmpty);
        ViewLibUtils.setGoneIf(this.newBookingsRow, isEmpty);
        SectionHeader sectionHeader = this.demandSectionHeader;
        if (isEmpty) {
            z = false;
        }
        sectionHeader.setButtonVisible(z);
        return isEmpty;
    }

    private void loadSuperhost() {
        ViewUtils.setVisibleIf((View) this.superhostStatusView, this.superhostData != null && this.superhostData.isEnabled());
        if (this.superhostData != null) {
            this.superhostStatusView.setSuperhostData(this.superhostData);
            this.superhostStatusView.setLearnMoreClickListener(HostStatsFragment$$Lambda$16.lambdaFactory$(this));
            if (!this.superhostData.isInEligibilityAssessmentWindow()) {
                this.superhostStatusView.setOnClickListener(HostStatsFragment$$Lambda$17.lambdaFactory$(this));
            }
        }
    }

    static /* synthetic */ void lambda$loadSuperhost$15(HostStatsFragment hostStatsFragment, View v) {
        hostStatsFragment.statsJitneyLogger.logSuperhostLearnMoreClickEvent();
        WebViewIntentBuilder.startMobileWebActivity(hostStatsFragment.getContext(), hostStatsFragment.getString(C0880R.string.superhost_help_url));
    }

    private void loadCohostingStandards() {
        if (!FeatureToggles.showCohostingStandards()) {
            this.cohostingStandardsLayout.setVisibility(8);
            return;
        }
        this.cohostingStandardsHeader.setButtonOnClickListener(HostStatsFragment$$Lambda$18.lambdaFactory$(this));
        setCohostingStat();
    }

    private void setCohostingStat() {
        if (this.cohostingStandard.noStats()) {
            this.cohostingStandardsSummary.setTitle(C0880R.string.cohosting_stats_summary_no_stats_title);
            this.cohostingStandardsSummary.setSubtitleText(C0880R.string.cohosting_stats_summary_no_stats_subtitle);
        } else if (this.cohostingStandard.needsImprovement()) {
            this.cohostingStandardsSummary.setTitle(C0880R.string.cohosting_stats_summary_improvement_title);
            this.cohostingStandardsSummary.setSubtitleText(C0880R.string.cohosting_stats_summary_improvement_subtitle);
            this.cohostingStandardsSummary.setRowDrawableRes(C0880R.C0881drawable.n2_icon_exclamation);
            setCohostingResponseRateBarRow();
        } else {
            this.cohostingStandardsSummary.setTitle(C0880R.string.cohosting_stats_summary_welldone_title);
            this.cohostingStandardsSummary.setSubtitleText(C0880R.string.cohosting_stats_summary_welldone_subtitle);
            this.cohostingStandardsSummary.setRowDrawableRes(C0880R.C0881drawable.n2_ic_check_babu);
        }
    }

    private void setCohostingResponseRateBarRow() {
        this.cohostingResponseRateBarRow.setVisibility(0);
        this.cohostingResponseRateBarRow.setFilledSectionColor(this.cohostingStandard.getColorResByResponseRate());
        this.cohostingResponseRateBarRow.setValue(this.cohostingStandard.getCurrentRatioOfResponseRate());
        this.cohostingResponseRateBarRow.setThreshold(this.cohostingStandard.getThresholdRatioOfResponseRate());
        this.cohostingResponseRateBarRow.setProgressLabel(PercentageUtils.localizePercentage((int) (this.cohostingStandard.getCurrentRatioOfResponseRate() * 100.0f)));
    }

    private void loadHostingStandards() {
        boolean emptyStandardMetrics;
        if (this.hostStandardMetrics == null) {
            emptyStandardMetrics = true;
        } else {
            emptyStandardMetrics = false;
        }
        ViewLibUtils.setGoneIf(this.hostingStandardsLayout, emptyStandardMetrics);
        if (!emptyStandardMetrics) {
            AirDate lastUpdated = this.hostStandardMetrics.getMetricsLastUpdated();
            if (lastUpdated == null) {
                this.hostingStandardsHeader.setDescription((CharSequence) null);
            } else {
                DateFormat format = new SimpleDateFormat(getString(C0880R.string.mdy_format_shorter));
                this.hostingStandardsHeader.setDescription((CharSequence) getString(C0880R.string.hostings_standards_subtitle_last_updated, lastUpdated.formatDate(format)));
            }
            loadCommitmentRateHostingStandard();
            loadResponseRateHostingStandard();
            loadAverageRatingHostingStandard();
        }
    }

    private void loadCommitmentRateHostingStandard() {
        String percentageString;
        if (this.hostStandardMetrics != null) {
            this.commitmentRateBarRow.setValue(this.hostStandardMetrics.getCommitmentRate());
            this.commitmentRateBarRow.setThreshold(this.hostStandardMetrics.getCommitmentRateThreshold());
            this.commitmentRateBarRow.setFilledSectionColor(this.hostStandardMetrics.getCommitmentRateMetricState().getColorRes());
            this.commitmentRateBarRow.setThresholdIndicatorVisible(MetricState.Success != this.hostStandardMetrics.getCommitmentRateMetricState());
            this.commitmentRateBarRow.setSubtitle(this.hostStandardMetrics.getCommitmentRateStandardText());
            BarRow barRow = this.commitmentRateBarRow;
            if (this.hostStandardMetrics.getReservationsCount() == 0) {
                percentageString = getString(C0880R.string.host_stats_hosting_standard_metric_not_applicable);
            } else {
                percentageString = getPercentageString(this.hostStandardMetrics.getCommitmentRate());
            }
            barRow.setProgressLabel(percentageString);
        }
    }

    private void loadResponseRateHostingStandard() {
        String percentageString;
        if (this.hostStandardMetrics != null) {
            this.responseRateBarRow.setValue(this.hostStandardMetrics.getResponseRate());
            this.responseRateBarRow.setThreshold(this.hostStandardMetrics.getResponseRateThreshold());
            this.responseRateBarRow.setFilledSectionColor(this.hostStandardMetrics.getResponseRateMetricState().getColorRes());
            this.responseRateBarRow.setThresholdIndicatorVisible(MetricState.Success != this.hostStandardMetrics.getResponseRateMetricState());
            this.responseRateBarRow.setSubtitle(this.hostStandardMetrics.getResponseRateStandardText());
            BarRow barRow = this.responseRateBarRow;
            if (this.hostStandardMetrics.getInquiriesCount() == 0) {
                percentageString = getString(C0880R.string.host_stats_hosting_standard_metric_not_applicable);
            } else {
                percentageString = getPercentageString(this.hostStandardMetrics.getResponseRate());
            }
            barRow.setProgressLabel(percentageString);
        }
    }

    private void loadAverageRatingHostingStandard() {
        String string;
        if (this.hostStandardMetrics != null) {
            this.averageRatingBarRow.setValue(this.hostStandardMetrics.getAverageOverallRating() / 5.0f);
            this.averageRatingBarRow.setThreshold(this.hostStandardMetrics.getAverageOverallRatingThreshold() / 5.0f);
            this.averageRatingBarRow.setThresholdIndicatorVisible(MetricState.Success != this.hostStandardMetrics.getOverallRatingMetricState());
            this.averageRatingBarRow.setFilledSectionColor(this.hostStandardMetrics.getOverallRatingMetricState().getColorRes());
            this.averageRatingBarRow.setSubtitle(this.hostStandardMetrics.getAverageOverallRatingStandardText());
            BarRow barRow = this.averageRatingBarRow;
            if (this.hostStandardMetrics.getAverageOverallRating() > 0.0f) {
                string = this.ratingFormatter.format((double) this.hostStandardMetrics.getAverageOverallRating());
            } else {
                string = getString(C0880R.string.host_stats_hosting_standard_metric_not_applicable);
            }
            barRow.setProgressLabel(string);
        }
    }

    private String getPercentageString(float input) {
        return getString(C0880R.string.n2_percentage, String.valueOf((int) (100.0f * input)));
    }

    private void loadRatings() {
        String subtitle;
        if (!checkRatingsEmptyStateAndUpdateViewVisibility()) {
            this.ratingsRow.setTitle((CharSequence) this.ratingFormatter.format(this.currentUserAverageRating));
            this.ratingsRow.setTitleDrawableRight(C0880R.C0881drawable.n2_ic_babu_star_large, C0880R.dimen.host_stats_ratings_row_star_icon_padding);
            if (this.similarHostAverageRating == null) {
                subtitle = null;
            } else {
                subtitle = getString(C0880R.string.host_stats_similar_hosts_average_rating, this.ratingFormatter.format(this.similarHostAverageRating));
            }
            this.ratingsRow.setPrimarySubtitle((CharSequence) subtitle);
        }
    }

    private boolean showRatingsSection() {
        return this.currentUserAverageRating > 0.0d;
    }

    private boolean checkRatingsEmptyStateAndUpdateViewVisibility() {
        boolean isEmpty;
        boolean z = true;
        if (!showRatingsSection()) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        ViewLibUtils.setVisibleIf(this.ratingsEmptyStateView, isEmpty);
        ViewLibUtils.setGoneIf(this.ratingsRow, isEmpty);
        SectionHeader sectionHeader = this.ratingsSectionHeader;
        if (isEmpty) {
            z = false;
        }
        sectionHeader.setButtonVisible(z);
        return isEmpty;
    }

    private void loadEarnings() {
        CurrencyAmount paidOutForMonth;
        String subtitle;
        this.earningsSectionHeader.setTitle((CharSequence) getString(C0880R.string.host_stats_earnings_section_title, AirDate.today().formatDate(this.earningsSectionHeaderDateFormat)));
        this.montlyEarningsLayout.removeAllViews();
        if (!checkEarningsEmptyStateAndUpdateViews()) {
            for (int i = 0; i < this.earningsForThisMonth.getTotal().size(); i++) {
                CurrencyAmount totalPayout = (CurrencyAmount) this.earningsForThisMonth.getTotal().get(i);
                List<CurrencyAmount> paymentsForMonth = this.earningsForThisMonth.getPaidOut();
                if (ListUtils.isEmpty((Collection<?>) paymentsForMonth)) {
                    paidOutForMonth = null;
                } else {
                    paidOutForMonth = (CurrencyAmount) FluentIterable.from((Iterable<E>) paymentsForMonth).firstMatch(HostStatsFragment$$Lambda$19.lambdaFactory$(totalPayout)).orNull();
                }
                ParcelableBigDecimal paidOutAmount = paidOutForMonth == null ? null : paidOutForMonth.getAmount();
                if (paidOutAmount == null || paidOutAmount.doubleValue() == 0.0d) {
                    subtitle = getString(C0880R.string.host_stats_no_payouts_yet);
                } else {
                    subtitle = getString(C0880R.string.host_stats_amount_paid_out, paidOutForMonth.formattedForDisplay());
                }
                BigNumberRow row = new BigNumberRow(getContext());
                row.setLayoutParams(new MarginLayoutParams(-1, -2));
                row.setTitle((CharSequence) totalPayout.formattedForDisplay());
                row.setPrimarySubtitle((CharSequence) subtitle);
                this.montlyEarningsLayout.addView(row);
            }
            setupTotalEarningsCell();
        }
    }

    private void setupTotalEarningsCell() {
        boolean z;
        String title;
        ViewLibUtils.setGoneIf(this.yearlyPaidOutRow, this.totalPaidOutThisYear == null);
        LinkActionRow linkActionRow = this.viewTransactionsRow;
        if (this.totalPaidOutThisYear == null) {
            z = true;
        } else {
            z = false;
        }
        ViewLibUtils.setGoneIf(linkActionRow, z);
        if (this.totalPaidOutThisYear != null) {
            if (this.totalPaidOutThisYear.getAmount() == null || this.totalPaidOutThisYear.getAmount().doubleValue() == 0.0d) {
                title = getString(C0880R.string.host_stats_no_payouts_yet);
            } else {
                title = getString(C0880R.string.host_stats_total_yearly_paid_out, this.totalPaidOutThisYear.formattedForDisplay(), AirDate.today().formatDate("yyyy"));
            }
            this.yearlyPaidOutRow.setTitle((CharSequence) title);
            this.viewTransactionsRow.setText(C0880R.string.host_stats_view_transactions);
            this.viewTransactionsRow.setOnClickListener(HostStatsFragment$$Lambda$20.lambdaFactory$(this));
        }
    }

    private boolean checkEarningsEmptyStateAndUpdateViews() {
        boolean isEmpty;
        boolean z = true;
        CurrencyAmount total = this.earningsForThisMonth == null ? null : (CurrencyAmount) FluentIterable.from((Iterable<E>) this.earningsForThisMonth.getTotal()).first().orNull();
        if (total == null || total.isZero()) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        ViewLibUtils.setVisibleIf(this.earningsEmptyStateView, isEmpty);
        SectionHeader sectionHeader = this.earningsSectionHeader;
        if (isEmpty) {
            z = false;
        }
        sectionHeader.setButtonVisible(z);
        return isEmpty;
    }

    @OnClick
    public void showEarningDetailsFragment() {
        if (this.earningsForThisMonth != null && !ListUtils.isEmpty((Collection<?>) this.earningsForThisMonth.getTotal())) {
            this.statsJitneyLogger.logEarningsDetailsButtonClickEvent();
            startActivity(ReactNativeIntents.intentForHostStatsEarnings(getContext()));
        }
    }

    @OnClick
    public void showReviewDetailsFragment() {
        Bundle bundle;
        if (showRatingsSection()) {
            this.statsJitneyLogger.logRatingDetailsButtonClickEvent();
            List<Listing> listedListings = FluentIterable.from((Iterable<E>) this.listings).filter(HostStatsFragment$$Lambda$21.lambdaFactory$()).toList();
            if (listedListings.size() == 1) {
                bundle = HostReviewDetailsFragment.getBundle(this.similarHostAverageRating, (Listing) listedListings.get(0));
            } else {
                bundle = HostReviewDetailsFragment.getBundle(this.similarHostAverageRating);
            }
            showStatsFragment(HostReviewDetailsFragment.class, bundle);
        }
    }

    @OnClick
    public void showDemandDetailsFragment() {
        if (this.totalListingViewsCount != 0) {
            this.statsJitneyLogger.logListingViewsDetailsClickEvent();
            showStatsFragment(HostDemandsDetailFragment.class);
        }
    }

    @OnClick
    public void hostingStandardsLearnMoreClick() {
        this.statsJitneyLogger.logHostingStandardsLearnMoreButtonClickEvent();
        WebViewIntentBuilder.startMobileWebActivity(getContext(), getString(C0880R.string.hosting_standards_help_url));
    }

    /* access modifiers changed from: private */
    public void showSuperhostProgressFragment() {
        this.statsJitneyLogger.logSuperhostDetailsButtonClickEvent();
        startActivity(ReactNativeIntents.intentForHostStatsSuperhostProgress(getContext()));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.HostStatsTopLevel;
    }

    public void onGoToCardsClick(Listing listing) {
        Iterator it = this.listings.iterator();
        while (it.hasNext()) {
            ((Listing) it.next()).setListingNativeCurrency(this.totalPaidOutThisYear.getCurrency());
        }
        startActivityForResult(InsightsActivity.intentForFragment(getActivity(), listing, new ArrayList<>(FluentIterable.from((Iterable<E>) this.listings).filter(HostStatsFragment$$Lambda$23.lambdaFactory$(FluentIterable.from((Iterable<E>) this.insights).transform(HostStatsFragment$$Lambda$22.lambdaFactory$()).toSet())).toList())), 700);
    }
}
