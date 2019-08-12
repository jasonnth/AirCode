package com.airbnb.android.explore.adapters;

import android.app.Activity;
import android.support.p002v7.widget.RecyclerView.RecycledViewPool;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.DisplayOptions.DisplayType;
import com.airbnb.android.core.enums.InlineSearchFeedFilterItemType;
import com.airbnb.android.core.models.EmptyStateMetadata;
import com.airbnb.android.core.models.ExploreSection;
import com.airbnb.android.core.models.ExploreSection.ResultType;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.InlineSearchFeedItem;
import com.airbnb.android.core.models.SearchGeography;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.SearchUrgencyCommitment;
import com.airbnb.android.core.models.TripTemplate;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.PricingDisclaimerModelUtils;
import com.airbnb.android.core.utils.SearchPricingUtil;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MapInterstitialEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.UrgencyEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.fragments.MTExploreFragment;
import com.airbnb.android.explore.presenters.ExploreFeedItemPresenter;
import com.airbnb.android.explore.viewcomponents.viewmodels.ExploreEmptyStateEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.FiltersSpacerEpoxyModel_;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.utils.TextUtil;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.EpoxyViewHolder;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.p027n2.components.MicroRow;
import com.airbnb.p027n2.epoxy.AirEpoxyModel;
import com.airbnb.p027n2.utils.LatLng;
import com.airbnb.p027n2.utils.MapOptions;
import com.airbnb.p027n2.utils.MapOptions.MarkerOptions;
import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MTSectionsAdapter extends BaseExploreAdapter {
    private static final int MAP_CARD_ZOOM_LEVEL = 12;
    private static final int URGENCY_MODEL_ID = Integer.MIN_VALUE;
    private final LoadingRowEpoxyModel_ emptyLoadingModel = new LoadingRowEpoxyModel_();
    private MicroRowEpoxyModel_ errorModel;
    private final FiltersSpacerEpoxyModel_ filtersSpacerEpoxyModel = new FiltersSpacerEpoxyModel_();
    private final LoadingRowEpoxyModel_ paginationLoadingModel = new LoadingRowEpoxyModel_();
    private final EpoxyModel<?> pricingDisclaimerModel = PricingDisclaimerModelUtils.buildPricingDisclaimerEpoxyModel();
    private final String tabId;

    public MTSectionsAdapter(String tabId2, Activity activity, DebugSettings debugSettings, ExploreDataController dataController, ExploreNavigationController navController, ExploreJitneyLogger logger, RecycledViewPool recycledViewPool) {
        super(activity, debugSettings, dataController, navController, logger, recycledViewPool);
        this.tabId = tabId2;
        ((Builder) ((ExploreBindings) CoreApplication.instance(activity).componentProvider()).exploreComponentProvider().get()).build().inject((BaseExploreAdapter) this);
    }

    private void showEmptyStateIfNeeded(ExploreTab tab) {
        if (tab == null) {
            this.models.add(new ExploreEmptyStateEpoxyModel_().m5845id((CharSequence) "empty_state").titleRes(C0857R.string.error_request).showDivider(false));
            return;
        }
        EmptyStateMetadata emptyStateMetadata = tab.getEmptyStateMetadata();
        if (emptyStateMetadata != null && !this.dataController.isTabSectionLoading(tab)) {
            this.models.add(new ExploreEmptyStateEpoxyModel_().m5845id((CharSequence) "empty_state").title(emptyStateMetadata.getDescription()).button(emptyStateMetadata.getCta()).showDivider(tab.hasResults()).clickListener(MTSectionsAdapter$$Lambda$1.lambdaFactory$(this)));
        }
    }

    static /* synthetic */ void lambda$showEmptyStateIfNeeded$0(MTSectionsAdapter mTSectionsAdapter, View v) {
        mTSectionsAdapter.dataController.resetFiltersForTab(mTSectionsAdapter.tabId);
        mTSectionsAdapter.dataController.fetchTab(mTSectionsAdapter.tabId);
    }

    private AirEpoxyModel<MicroRow> buildErrorModel(String tabId2) {
        if (this.errorModel == null) {
            this.errorModel = new MicroRowEpoxyModel_().text(TextUtil.fromHtmlSafe(this.activity.getString(C0857R.string.explore_network_error))).showDivider(false);
        }
        return this.errorModel.clickListener(MTSectionsAdapter$$Lambda$2.lambdaFactory$(this, tabId2));
    }

    private UrgencyEpoxyModel_ buildUrgencyModel(SearchMetaData homesMetadata) {
        if (homesMetadata != null) {
            SearchUrgencyCommitment urgencyData = homesMetadata.getUrgencyCommitment();
            if (this.dataController.showUrgencyMessage(urgencyData)) {
                return new UrgencyEpoxyModel_(-2147483648L).title(urgencyData.getTitle()).subtitle(urgencyData.getSubtitle()).type(urgencyData.getMessageType()).contextualMessage(urgencyData.getContexualMessage());
            }
        }
        return null;
    }

    private boolean needsPricingDisclaimer() {
        return SearchPricingUtil.isTotalPricingEnabled() || SearchPricingUtil.isIncludingServiceFeeRequired();
    }

    private void updatePricingDisclaimer(EpoxyModel<?> pricingDisclaimerModel2, boolean hasDates) {
        if (SearchPricingUtil.isTotalPricingEnabled()) {
            PricingDisclaimerModelUtils.updateTotalPricingDisclaimerEpoxyModel(pricingDisclaimerModel2, hasDates);
        } else if (SearchPricingUtil.isIncludingServiceFeeRequired()) {
            PricingDisclaimerModelUtils.updateServiceFeeDisclaimerEpoxyModel(pricingDisclaimerModel2, hasDates);
        }
    }

    public String toString() {
        return "MTSectionsAdapter{tabId='" + this.tabId + '\'' + ", super=" + super.toString() + '}';
    }

    public void syncWithDataController() {
        ExploreTab tab = (ExploreTab) this.dataController.getTabs().get(this.tabId);
        this.models.clear();
        boolean hasResults = tab != null && tab.hasResults();
        if (tab == null || !hasResults) {
            if (this.dataController.isTabSectionLoading(this.tabId)) {
                this.models.add(this.emptyLoadingModel);
            } else if (tab != null && tab.hasError()) {
                this.models.add(buildErrorModel(this.tabId));
            }
            showEmptyStateIfNeeded(tab);
            notifyModelsChanged();
            return;
        }
        populateWithSections(FluentIterable.from((Iterable<E>) tab.getSections()).filter(MTSectionsAdapter$$Lambda$3.lambdaFactory$(this)).toList(), Tab.HOME.isSameAs(this.tabId));
        if (!tab.hasError() && (this.dataController.isTabSectionLoading(tab) || tab.getPaginationMetadata().hasNextPage())) {
            this.models.add(this.paginationLoadingModel);
        }
        if (MTExploreFragment.TABS_WITH_FILTERS.contains(this.tabId)) {
            this.models.add(this.filtersSpacerEpoxyModel.large(Tab.PLACES.isSameAs(this.tabId)));
        }
        notifyModelsChanged();
    }

    static /* synthetic */ boolean lambda$syncWithDataController$2(MTSectionsAdapter mTSectionsAdapter, ExploreSection section) {
        return section.getResultType() != ResultType.GiftCardPromotions || mTSectionsAdapter.isGiftCardPromoEnabled();
    }

    /* access modifiers changed from: protected */
    public boolean shouldShowHeader(ExploreSection section) {
        ExploreTab tab = (ExploreTab) this.dataController.getTabs().get(this.tabId);
        if (tab == null || tab.getSections().isEmpty()) {
            return true;
        }
        if (TextUtils.isEmpty(section.getTitle())) {
            return false;
        }
        if (!section.equals(tab.getSections().get(0)) || !section.getTitle().equals(tab.getTabName())) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public List<EpoxyModel<?>> postProcessListings(ExploreSection section, ExploreSection previousSection, int listingOffset, int sectionIndex, DisplayType displayType, List<EpoxyModel<?>> homeCardItems) {
        SearchMetaData homeTabMetadata = this.dataController.findTabForId(this.tabId).getHomeTabMetadata();
        if (homeTabMetadata != null && previousSection == null) {
            addSearchFeedItemModels(homeCardItems, homeTabMetadata.getSearchFeedItems(), listingOffset);
        }
        List<EpoxyModel<?>> homesListItems = new ArrayList<>();
        if (this.businessTravelAccountManager.isBTRFilterApplied() && Tab.HOME.isSameAs(this.tabId)) {
            String sectionTitle = getBusinessTravelPostProcessListingSectionTitle();
            if (!sectionTitle.isEmpty()) {
                section.setTitle(sectionTitle);
            }
        }
        homesListItems.addAll(transformItemsForDisplayType(homeCardItems, section, previousSection, sectionIndex));
        UrgencyEpoxyModel_ urgencyModel = buildUrgencyModel(homeTabMetadata);
        boolean isHeadOfSection = !isSectionAContinuation(section, previousSection);
        if (shouldShowMapCard(isHeadOfSection)) {
            homesListItems.add(0, getMapCardModel());
        }
        if (isHeadOfSection && sectionIndex == 0) {
            if (urgencyModel != null) {
                homesListItems.add(0, urgencyModel);
            }
            if (Tab.HOME.isSameAs(this.tabId) && needsPricingDisclaimer()) {
                updatePricingDisclaimer(this.pricingDisclaimerModel, this.dataController.getTopLevelSearchParams().hasDates());
                homesListItems.add(urgencyModel == null ? 0 : 1, this.pricingDisclaimerModel);
            }
        }
        return homesListItems;
    }

    /* access modifiers changed from: private */
    public void onInlineFilterItemClicked(InlineSearchFeedFilterItem item) {
        if (item.getType() == InlineSearchFeedFilterItemType.Amenity) {
            this.dataController.onAmenityInlineFilterItemClicked(item);
        } else if (item.getType() == InlineSearchFeedFilterItemType.FamilyFriendlyRoomType) {
            this.dataController.onFamilyFriendlyRoomTypeFilterItemClicked();
        } else if (item.getType() == InlineSearchFeedFilterItemType.LongTermStay) {
            this.dataController.onLongTermStayFilterItemClicked();
        } else {
            this.navController.onNonAmenityInlineFilterItemClicked(item);
        }
    }

    private void addSearchFeedItemModels(List<EpoxyModel<?>> homeCardItems, List<InlineSearchFeedItem> searchFeedItems, int listingOffset) {
        Queue<InlineSearchFeedItem> searchFeedItemsQueue = new LinkedList<>(searchFeedItems);
        int size = homeCardItems.size();
        if (isMultiSpan()) {
            ExploreFeedItemPresenter.adjustPositionsForGrid(searchFeedItems, size);
        }
        for (int i = 0; i < size; i++) {
            Optional<EpoxyModel<?>> searchFeedModel = ExploreFeedItemPresenter.buildModelForListPosition(searchFeedItemsQueue, listingOffset + i, MTSectionsAdapter$$Lambda$4.lambdaFactory$(this), this.logger);
            if (searchFeedModel.isPresent()) {
                homeCardItems.add(i, searchFeedModel.get());
            }
        }
    }

    private boolean shouldShowMapCard(boolean isHeadOfSection) {
        return Tab.HOME.isSameAs(this.tabId) && isHeadOfSection && this.dataController.hasHomesMetadata() && this.dataController.getHomesMetadata().hasGeography() && Experiments.showMapCardOnP2();
    }

    private EpoxyModel<?> getMapCardModel() {
        SearchGeography geo = this.dataController.getHomesMetadata().getGeography();
        LatLng latLng = LatLng.builder().lat(geo.getLat()).lng(geo.getLng()).build();
        return new MapInterstitialEpoxyModel_().clickListener(MTSectionsAdapter$$Lambda$5.lambdaFactory$(this)).shortCard(true).hideText(true).mapOptions(MapOptions.builder(AppLaunchUtils.isUserInChina()).center(latLng).marker(MarkerOptions.create(latLng)).zoom(12).build());
    }

    /* access modifiers changed from: protected */
    public void onModelBound(EpoxyViewHolder holder, EpoxyModel<?> model, int position, List<Object> payloads) {
        super.onModelBound(holder, model, position, payloads);
        if (model == this.paginationLoadingModel && !this.dataController.isTabSectionLoading(this.tabId)) {
            this.dataController.fetchNextPageForTab(this.tabId, ((ExploreTab) this.dataController.getTabs().get(this.tabId)).getPaginationMetadata());
        }
    }

    private boolean isGiftCardPromoEnabled() {
        return Trebuchet.launch(TrebuchetKeys.KEY_GIFT_CARDS_P1_PROMO);
    }

    private String getBusinessTravelPostProcessListingSectionTitle() {
        int listingsCount = this.dataController.getListingsCount();
        if (listingsCount == -1) {
            return "";
        }
        if (listingsCount > 300) {
            return this.activity.getString(C0857R.string.view_x_business_travel_ready_listings_max, new Object[]{Integer.valueOf(300)});
        }
        return this.activity.getResources().getQuantityString(C0857R.plurals.view_x_business_travel_ready_listings, listingsCount, new Object[]{NumberFormat.getIntegerInstance().format((long) listingsCount)});
    }

    /* access modifiers changed from: protected */
    public void getPosterCardClickListener(View view, TripTemplate template) {
        this.logger.experienceClick(template.getId());
        this.navController.launchTemplate(view, template, this.dataController.getTopLevelSearchParams(), this.logger, C2443MtPdpReferrer.ExploreP2Card, template.getId());
    }
}
