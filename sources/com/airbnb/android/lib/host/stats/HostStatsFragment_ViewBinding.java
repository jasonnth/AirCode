package com.airbnb.android.lib.host.stats;

import android.view.View;
import android.view.ViewGroup;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.airbnb.android.core.views.AirSwipeRefreshLayout;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.host.stats.views.SuperhostStatusView;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.BarRow;
import com.airbnb.p027n2.components.BigNumberRow;
import com.airbnb.p027n2.components.DocumentCarouselMarquee;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.LinkActionRow;
import com.airbnb.p027n2.components.RefreshLoader;
import com.airbnb.p027n2.components.SectionHeader;
import com.airbnb.p027n2.components.StandardRow;

public class HostStatsFragment_ViewBinding implements Unbinder {
    private HostStatsFragment target;
    private View view2131756376;
    private View view2131756382;
    private View view2131756386;
    private View view2131756396;

    public HostStatsFragment_ViewBinding(final HostStatsFragment target2, View source) {
        this.target = target2;
        target2.listingInsightsMarquee = (DocumentCarouselMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_carousel_marquee, "field 'listingInsightsMarquee'", DocumentCarouselMarquee.class);
        target2.defaultMarquee = (DocumentMarquee) Utils.findRequiredViewAsType(source, C0880R.C0882id.document_marquee, "field 'defaultMarquee'", DocumentMarquee.class);
        target2.ratingsSectionHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.ratings_section_header, "field 'ratingsSectionHeader'", SectionHeader.class);
        target2.earningsSectionHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.earnings_section_header, "field 'earningsSectionHeader'", SectionHeader.class);
        target2.demandSectionHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_views_section_header, "field 'demandSectionHeader'", SectionHeader.class);
        target2.listingViewsRow = (BigNumberRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.listing_views_row, "field 'listingViewsRow'", BigNumberRow.class);
        target2.newBookingsRow = (BigNumberRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.new_bookings_row, "field 'newBookingsRow'", BigNumberRow.class);
        target2.listingViewsEmptyStateView = Utils.findRequiredView(source, C0880R.C0882id.listing_views_empty_state_view, "field 'listingViewsEmptyStateView'");
        View view = Utils.findRequiredView(source, C0880R.C0882id.earnings_layout, "field 'earningsLayout' and method 'showEarningDetailsFragment'");
        target2.earningsLayout = view;
        this.view2131756376 = view;
        view.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showEarningDetailsFragment();
            }
        });
        target2.earningsEmptyStateView = Utils.findRequiredView(source, C0880R.C0882id.earnings_empty_state_view, "field 'earningsEmptyStateView'");
        target2.montlyEarningsLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.monthly_earnings_layout, "field 'montlyEarningsLayout'", ViewGroup.class);
        target2.yearlyPaidOutRow = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.paid_out_yearly_row, "field 'yearlyPaidOutRow'", StandardRow.class);
        target2.viewTransactionsRow = (LinkActionRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.view_transactions_row, "field 'viewTransactionsRow'", LinkActionRow.class);
        target2.ratingsRow = (BigNumberRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.ratings_row, "field 'ratingsRow'", BigNumberRow.class);
        target2.ratingsEmptyStateView = Utils.findRequiredView(source, C0880R.C0882id.ratings_empty_state_view, "field 'ratingsEmptyStateView'");
        target2.commitmentRateBarRow = (BarRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.commitment_rate_standards_bar, "field 'commitmentRateBarRow'", BarRow.class);
        target2.responseRateBarRow = (BarRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.response_rate_standards_bar, "field 'responseRateBarRow'", BarRow.class);
        target2.averageRatingBarRow = (BarRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.five_star_rating_rate_standards_bar, "field 'averageRatingBarRow'", BarRow.class);
        target2.superhostStatusView = (SuperhostStatusView) Utils.findRequiredViewAsType(source, C0880R.C0882id.superhost_status, "field 'superhostStatusView'", SuperhostStatusView.class);
        target2.hostingStandardsHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.hosting_standards_header, "field 'hostingStandardsHeader'", SectionHeader.class);
        target2.hostingStandardsLayout = Utils.findRequiredView(source, C0880R.C0882id.hosting_standards_layout, "field 'hostingStandardsLayout'");
        target2.cohostingStandardsLayout = Utils.findRequiredView(source, C0880R.C0882id.cohosting_standards_layout, "field 'cohostingStandardsLayout'");
        target2.cohostingStandardsHeader = (SectionHeader) Utils.findRequiredViewAsType(source, C0880R.C0882id.cohosting_standards_header, "field 'cohostingStandardsHeader'", SectionHeader.class);
        target2.cohostingStandardsSummary = (StandardRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.cohosting_standards_summary, "field 'cohostingStandardsSummary'", StandardRow.class);
        target2.cohostingResponseRateBarRow = (BarRow) Utils.findRequiredViewAsType(source, C0880R.C0882id.cohosting_response_rate_standards_bar, "field 'cohostingResponseRateBarRow'", BarRow.class);
        target2.swipeRefreshLayout = (AirSwipeRefreshLayout) Utils.findRequiredViewAsType(source, C0880R.C0882id.swipe_refresh_layout, "field 'swipeRefreshLayout'", AirSwipeRefreshLayout.class);
        target2.scrollView = (VerboseScrollView) Utils.findRequiredViewAsType(source, C0880R.C0882id.scroll_view, "field 'scrollView'", VerboseScrollView.class);
        target2.refreshLoader = (RefreshLoader) Utils.findRequiredViewAsType(source, C0880R.C0882id.loading_row, "field 'refreshLoader'", RefreshLoader.class);
        target2.contentLayout = (ViewGroup) Utils.findRequiredViewAsType(source, C0880R.C0882id.content_layout, "field 'contentLayout'", ViewGroup.class);
        View view2 = Utils.findRequiredView(source, C0880R.C0882id.ratings_layout, "method 'showReviewDetailsFragment'");
        this.view2131756382 = view2;
        view2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showReviewDetailsFragment();
            }
        });
        View view3 = Utils.findRequiredView(source, C0880R.C0882id.listing_views_layout, "method 'showDemandDetailsFragment'");
        this.view2131756386 = view3;
        view3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.showDemandDetailsFragment();
            }
        });
        View view4 = Utils.findRequiredView(source, C0880R.C0882id.hosting_standards_learn_more_link, "method 'hostingStandardsLearnMoreClick'");
        this.view2131756396 = view4;
        view4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View p0) {
                target2.hostingStandardsLearnMoreClick();
            }
        });
    }

    public void unbind() {
        HostStatsFragment target2 = this.target;
        if (target2 == null) {
            throw new IllegalStateException("Bindings already cleared.");
        }
        this.target = null;
        target2.listingInsightsMarquee = null;
        target2.defaultMarquee = null;
        target2.ratingsSectionHeader = null;
        target2.earningsSectionHeader = null;
        target2.demandSectionHeader = null;
        target2.listingViewsRow = null;
        target2.newBookingsRow = null;
        target2.listingViewsEmptyStateView = null;
        target2.earningsLayout = null;
        target2.earningsEmptyStateView = null;
        target2.montlyEarningsLayout = null;
        target2.yearlyPaidOutRow = null;
        target2.viewTransactionsRow = null;
        target2.ratingsRow = null;
        target2.ratingsEmptyStateView = null;
        target2.commitmentRateBarRow = null;
        target2.responseRateBarRow = null;
        target2.averageRatingBarRow = null;
        target2.superhostStatusView = null;
        target2.hostingStandardsHeader = null;
        target2.hostingStandardsLayout = null;
        target2.cohostingStandardsLayout = null;
        target2.cohostingStandardsHeader = null;
        target2.cohostingStandardsSummary = null;
        target2.cohostingResponseRateBarRow = null;
        target2.swipeRefreshLayout = null;
        target2.scrollView = null;
        target2.refreshLoader = null;
        target2.contentLayout = null;
        this.view2131756376.setOnClickListener(null);
        this.view2131756376 = null;
        this.view2131756382.setOnClickListener(null);
        this.view2131756382 = null;
        this.view2131756386.setOnClickListener(null);
        this.view2131756386 = null;
        this.view2131756396.setOnClickListener(null);
        this.view2131756396 = null;
    }
}
