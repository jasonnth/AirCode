package com.airbnb.android.explore.fragments;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.IcepickWrapper;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.BusinessTravelUtils;
import com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.ExploreTab;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.SearchFacetWithIntKey;
import com.airbnb.android.core.models.SearchFacets;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.SearchFilters.OnSearchFiltersChangedListener;
import com.airbnb.android.core.viewcomponents.AirEpoxyAdapter;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.StepperRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SubsectionDividerEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SwitchRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.ToolbarSpacerEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.viewcomponents.viewmodels.AmenityToggleRowModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.ExploreInlineFiltersToggleRowEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.ExplorePriceHistogramRowEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.PriceFilterButtonsEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.SeeMoreSeeLessRowModel_;
import com.airbnb.android.explore.views.ExploreBaseRangeSeekBar;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.android.utils.ViewUtils;
import com.airbnb.epoxy.EpoxyModel;
import com.airbnb.epoxy.SimpleEpoxyModel;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.FilterSection.p100v1.C2141FilterSection;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.FindInlineFiltersToggleRow;
import com.airbnb.p027n2.components.SwitchStyle;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.interfaces.SwitchRowInterface;
import icepick.State;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ExploreHomesFiltersFragment extends BaseExploreFragment implements OnSearchFiltersChangedListener {
    private static final String ARG_GO_TO_NAVIGABLE_FILTER = "arg_go_to_navigable_filter";
    private static final String ARG_SOURCE_TAG = "arg_source_tag";
    /* access modifiers changed from: private */
    public FiltersAdapter adapter;
    @State
    boolean amenitiesExpanded;
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @State
    boolean facilitiesExpanded;
    @BindString
    String filterAddString;
    @BindString
    String filterChangeString;
    @BindString
    String filterNoPreferenceString;
    @State
    boolean hasClickedSearchButton;
    @State
    boolean hasToggledBusinessTravelReadyFilter;
    @State
    boolean houseRulesExpanded;
    @BindView
    RecyclerView recyclerView;
    @BindView
    FixedActionFooter searchButton;
    @State
    SearchFilters searchFilters;
    @State
    boolean selectedWheelchairAccessible;
    @BindView
    AirToolbar toolbar;

    public static class ExploreHomesFiltersFragmentBuilder {
        private NavigableFilter navigableFilter;
        private String sourceTag;

        public ExploreHomesFiltersFragmentBuilder setSourceTag(String sourceTag2) {
            this.sourceTag = sourceTag2;
            return this;
        }

        public ExploreHomesFiltersFragmentBuilder setNavigableFilter(NavigableFilter navigableFilter2) {
            this.navigableFilter = navigableFilter2;
            return this;
        }

        public ExploreHomesFiltersFragment build() {
            return (ExploreHomesFiltersFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(ExploreHomesFiltersFragment.newInstance()).putString(ExploreHomesFiltersFragment.ARG_GO_TO_NAVIGABLE_FILTER, this.navigableFilter != null ? this.navigableFilter.name() : null)).putString(ExploreHomesFiltersFragment.ARG_SOURCE_TAG, this.sourceTag)).build();
        }
    }

    private final class FiltersAdapter extends AirEpoxyAdapter {
        private static final int AMENITIES_ABOVE_FOLD = 3;
        private static final int MIN_AMENITIES_TO_COLLAPSE = 6;
        private final StepperRowEpoxyModel_ bathroomCountModel = new StepperRowEpoxyModel_().pluralsRes(C0857R.plurals.generic_count_or_greater).textRes(C0857R.string.listing_bathrooms).maxValueRes(C0857R.integer.max_num_bathrooms).value(0).valueChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$8.lambdaFactory$(this)).showDivider(false);
        private final StepperRowEpoxyModel_ bedCountModel = new StepperRowEpoxyModel_().pluralsRes(C0857R.plurals.generic_count_or_greater).textRes(C0857R.string.listing_beds).maxValueRes(C0857R.integer.max_num_beds).value(0).valueChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$6.lambdaFactory$(this)).showDivider(false);
        private final StepperRowEpoxyModel_ bedroomCountModel = new StepperRowEpoxyModel_().pluralsRes(C0857R.plurals.generic_count_or_greater).textRes(C0857R.string.listing_bedrooms).maxValueRes(C0857R.integer.max_num_bedrooms).value(0).valueChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$7.lambdaFactory$(this)).showDivider(false);
        private final SwitchRowEpoxyModel_ businessTravelReadyModel = new SwitchRowEpoxyModel_().titleRes(C0857R.string.filter_business_travel_ready_title).showDivider(true).descriptionRes(C0857R.string.filter_business_travel_ready_subtitle).checkedChangeListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$1.lambdaFactory$(this)).style(SwitchStyle.Outlined).hide();
        private final MicroSectionHeaderEpoxyModel_ categorizedAmenitiesHeaderModel = new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.amenities).showDivider(false);
        /* access modifiers changed from: private */
        public final SeeMoreSeeLessRowModel_ categorizedAmenitiesSeeMoreModel = new SeeMoreSeeLessRowModel_().expandedText(C0857R.string.amenities_see_less).collapsedText(C0857R.string.amenities_see_all).clickListener((OnClickListener) new OnClickListener() {
            public void onClick(View view) {
                FiltersAdapter.this.expandOtherAmenities(!FiltersAdapter.this.categorizedAmenitiesSeeMoreModel.expanded(), true);
            }
        });
        private final SubsectionDividerEpoxyModel_ categorizedFacilitiesAmenitiesHeaderDividerModel = new SubsectionDividerEpoxyModel_();
        private final MicroSectionHeaderEpoxyModel_ categorizedFacilitiesAmenitiesHeaderModel = new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.amenities_facilities).showDivider(false);
        /* access modifiers changed from: private */
        public final SeeMoreSeeLessRowModel_ categorizedFacilitiesAmenitiesSeeMoreModel = new SeeMoreSeeLessRowModel_().expandedText(C0857R.string.amenities_see_less).collapsedText(C0857R.string.amenities_see_all_facilities).clickListener((OnClickListener) new OnClickListener() {
            public void onClick(View view) {
                FiltersAdapter.this.expandFacilitiesAmenities(!FiltersAdapter.this.categorizedFacilitiesAmenitiesSeeMoreModel.expanded(), true);
            }
        });
        private final SubsectionDividerEpoxyModel_ categorizedHouseRulesAmenitiesHeaderDividerModel = new SubsectionDividerEpoxyModel_();
        private final MicroSectionHeaderEpoxyModel_ categorizedHouseRulesAmenitiesHeaderModel = new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.amenities_house_rules).showDivider(false);
        /* access modifiers changed from: private */
        public final SeeMoreSeeLessRowModel_ categorizedHouseRulesAmenitiesSeeMoreModel = new SeeMoreSeeLessRowModel_().expandedText(C0857R.string.amenities_see_less).collapsedText(C0857R.string.amenities_see_all_house_rules).clickListener((OnClickListener) new OnClickListener() {
            public void onClick(View view) {
                FiltersAdapter.this.expandHouseRulesAmenities(!FiltersAdapter.this.categorizedHouseRulesAmenitiesSeeMoreModel.expanded(), true);
            }
        });
        private final ExploreInlineFiltersToggleRowEpoxyModel_ entireHomeModel = new ExploreInlineFiltersToggleRowEpoxyModel_().titleRes(C0857R.string.entire_place).subtitleRes(C0857R.string.filter_room_type_entire_home_title).checkChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$3.lambdaFactory$(this)).showDivider(false);
        private final List<AmenityToggleRowModel_> facilitiesAmenitiesModels = new ArrayList();
        private final List<AmenityToggleRowModel_> houseRulesAmenitiesModels = new ArrayList();
        private final SwitchRowEpoxyModel_ instantBookModel = new SwitchRowEpoxyModel_().showDivider(false).titleRes(C0857R.string.instant_book).descriptionRes(C0857R.string.filter_instant_book_row_subtitle).checkedChangeListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$2.lambdaFactory$(this)).style(SwitchStyle.Outlined);
        private int numberOfTopOtherAmenities;
        private final List<AmenityToggleRowModel_> otherAmenitiesModels = new ArrayList();
        private final PriceFilterButtonsEpoxyModel_ priceButtonsModel = new PriceFilterButtonsEpoxyModel_().button1ClickListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$9.lambdaFactory$(this)).button2ClickListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$10.lambdaFactory$(this)).button3ClickListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$11.lambdaFactory$(this)).show(Experiments.showNewPriceRangeButtons());
        private final ExplorePriceHistogramRowEpoxyModel_ priceModel = new ExplorePriceHistogramRowEpoxyModel_().rangeChangeListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$12.lambdaFactory$(this));
        private final ExploreInlineFiltersToggleRowEpoxyModel_ privateRoomModel = new ExploreInlineFiltersToggleRowEpoxyModel_().titleRes(C0857R.string.private_room).subtitleRes(C0857R.string.filter_room_type_private_room_title).checked(ExploreHomesFiltersFragment.this.searchFilters.hasRoomType(C6120RoomType.PrivateRoom)).checkChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$4.lambdaFactory$(this)).showDivider(false);
        private final ExploreInlineFiltersToggleRowEpoxyModel_ sharedRoomModel = new ExploreInlineFiltersToggleRowEpoxyModel_().titleRes(C0857R.string.shared_room).subtitleRes(C0857R.string.filter_room_type_shared_room_title).checked(ExploreHomesFiltersFragment.this.searchFilters.hasRoomType(C6120RoomType.SharedRoom)).checkChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$5.lambdaFactory$(this)).showDivider(false);

        static /* synthetic */ void lambda$new$0(FiltersAdapter filtersAdapter, SwitchRowInterface switchView, boolean isChecked) {
            if (!ExploreHomesFiltersFragment.this.hasToggledBusinessTravelReadyFilter) {
                ExploreHomesFiltersFragment.this.hasToggledBusinessTravelReadyFilter = true;
            }
            BusinessTravelReadyFilterCriteria btrCriteria = ExploreHomesFiltersFragment.this.dataController.getBusinessTravelReadyFilterCriteria();
            if (btrCriteria != null) {
                ExploreHomesFiltersFragment.this.searchFilters.setBusinessTravelReady(isChecked, btrCriteria);
            } else {
                BugsnagWrapper.throwOrNotify(new RuntimeException("Business travel ready filter criteria should not be null"));
            }
            ExploreHomesFiltersFragment.this.businessTravelAccountManager.setUsingBTRFilter(isChecked);
            ExploreHomesFiltersFragment.this.businessTravelJitneyLogger.logBTRFilterToggle(isChecked);
        }

        static /* synthetic */ void lambda$new$1(FiltersAdapter filtersAdapter, SwitchRowInterface switchView, boolean isChecked) {
            ExploreHomesFiltersFragment.this.searchFilters.setIsInstantBookOnly(isChecked);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneToggleIB(isChecked);
        }

        static /* synthetic */ void lambda$new$2(FiltersAdapter filtersAdapter, FindInlineFiltersToggleRow toggleRow, boolean checked) {
            ExploreHomesFiltersFragment.this.searchFilters.setHasRoomType(C6120RoomType.EntireHome, checked);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneToggleRoomTypes(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes());
        }

        static /* synthetic */ void lambda$new$3(FiltersAdapter filtersAdapter, FindInlineFiltersToggleRow toggleRow, boolean checked) {
            ExploreHomesFiltersFragment.this.searchFilters.setHasRoomType(C6120RoomType.PrivateRoom, checked);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneToggleRoomTypes(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes());
        }

        static /* synthetic */ void lambda$new$4(FiltersAdapter filtersAdapter, FindInlineFiltersToggleRow toggleRow, boolean checked) {
            ExploreHomesFiltersFragment.this.searchFilters.setHasRoomType(C6120RoomType.SharedRoom, checked);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneToggleRoomTypes(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes());
        }

        static /* synthetic */ void lambda$new$5(FiltersAdapter filtersAdapter, int oldValue, int value) {
            filtersAdapter.bedCountModel.value(value);
            ExploreHomesFiltersFragment.this.searchFilters.setNumBeds(value);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneUpdateSize((long) ExploreHomesFiltersFragment.this.searchFilters.getNumBeds(), (long) ExploreHomesFiltersFragment.this.searchFilters.getNumBedrooms(), (double) ExploreHomesFiltersFragment.this.searchFilters.getNumBathrooms());
        }

        static /* synthetic */ void lambda$new$6(FiltersAdapter filtersAdapter, int oldValue, int value) {
            filtersAdapter.bedroomCountModel.value(value);
            ExploreHomesFiltersFragment.this.searchFilters.setNumBedrooms(value);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneUpdateSize((long) ExploreHomesFiltersFragment.this.searchFilters.getNumBeds(), (long) ExploreHomesFiltersFragment.this.searchFilters.getNumBedrooms(), (double) ExploreHomesFiltersFragment.this.searchFilters.getNumBathrooms());
        }

        static /* synthetic */ void lambda$new$7(FiltersAdapter filtersAdapter, int oldValue, int value) {
            filtersAdapter.bathroomCountModel.value(value);
            ExploreHomesFiltersFragment.this.searchFilters.setNumBathrooms(value);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneUpdateSize((long) ExploreHomesFiltersFragment.this.searchFilters.getNumBeds(), (long) ExploreHomesFiltersFragment.this.searchFilters.getNumBedrooms(), (double) ExploreHomesFiltersFragment.this.searchFilters.getNumBathrooms());
        }

        static /* synthetic */ void lambda$new$11(FiltersAdapter filtersAdapter, ExploreBaseRangeSeekBar bar, Integer minValue, Integer maxValue, boolean dragging) {
            SearchMetaData homeTabMetadata = ExploreHomesFiltersFragment.this.dataController.getHomesMetadata();
            if (!(homeTabMetadata == null || homeTabMetadata.getSearch() == null)) {
                if (minValue.intValue() == homeTabMetadata.getSearch().getMinFilterPrice()) {
                    minValue = Integer.valueOf(0);
                }
                if (maxValue.intValue() == homeTabMetadata.getSearch().getMaxFilterPrice()) {
                    maxValue = Integer.valueOf(0);
                }
            }
            ExploreHomesFiltersFragment.this.searchFilters.setMinPrice(minValue.intValue());
            ExploreHomesFiltersFragment.this.searchFilters.setMaxPrice(maxValue.intValue());
            ExploreHomesFiltersFragment.this.searchFilters.setPriceFilterSelection(0);
            filtersAdapter.priceButtonsModel.selection(0);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneUpdatePrice((long) minValue.intValue(), (long) maxValue.intValue());
        }

        public FiltersAdapter() {
            enableDiffing();
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{new ToolbarSpacerEpoxyModel_(), new SimpleEpoxyModel(C0857R.layout.view_holder_full_width_divider), this.businessTravelReadyModel, this.instantBookModel, new SubsectionDividerEpoxyModel_(), new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.price_range).isBold(true), this.priceButtonsModel, this.priceModel, new SubsectionDividerEpoxyModel_(), new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.filter_room_type).isBold(true), this.entireHomeModel, this.privateRoomModel, this.sharedRoomModel, new SubsectionDividerEpoxyModel_(), new MicroSectionHeaderEpoxyModel_().titleRes(C0857R.string.filter_rooms_and_beds).isBold(true).showDivider(false), this.bedCountModel, this.bedroomCountModel, this.bathroomCountModel, new SubsectionDividerEpoxyModel_()});
            addModels((EpoxyModel<?>[]) new EpoxyModel[]{this.categorizedAmenitiesHeaderModel, this.categorizedAmenitiesSeeMoreModel, this.categorizedFacilitiesAmenitiesHeaderDividerModel, this.categorizedFacilitiesAmenitiesHeaderModel, this.categorizedFacilitiesAmenitiesSeeMoreModel, this.categorizedHouseRulesAmenitiesHeaderDividerModel, this.categorizedHouseRulesAmenitiesHeaderModel, this.categorizedHouseRulesAmenitiesSeeMoreModel});
            ExploreHomesFiltersFragment.this.hasToggledBusinessTravelReadyFilter = ExploreHomesFiltersFragment.this.businessTravelAccountManager.isBTRFilterApplied();
            if (ExploreHomesFiltersFragment.this.hasToggledBusinessTravelReadyFilter || BusinessTravelUtils.shouldShowBusinessTravelReadyFilter(ExploreHomesFiltersFragment.this.businessTravelAccountManager, ExploreHomesFiltersFragment.this.dataController.getBusinessTravelReadyFilterCriteria(), ExploreHomesFiltersFragment.this.dataController.getBusinessTravelReadyListingCount())) {
                this.businessTravelReadyModel.show();
                ExploreHomesFiltersFragment.this.businessTravelJitneyLogger.logBTRFilterImpression();
            }
            expandOtherAmenities(ExploreHomesFiltersFragment.this.amenitiesExpanded, false);
            expandFacilitiesAmenities(ExploreHomesFiltersFragment.this.facilitiesExpanded, false);
            expandHouseRulesAmenities(ExploreHomesFiltersFragment.this.houseRulesExpanded, false);
        }

        /* access modifiers changed from: 0000 */
        public int getNavigableFilterPosition(NavigableFilter navigableFilter) {
            switch (navigableFilter) {
                case InstantBook:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.instantBookModel);
                case Price:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.priceModel);
                case RoomType:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.entireHomeModel);
                case BedCount:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.bedCountModel);
                case BedroomCount:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.bedroomCountModel);
                case BathroomCount:
                    return ExploreHomesFiltersFragment.this.adapter.getModelPosition(this.bathroomCountModel);
                default:
                    return 0;
            }
        }

        /* access modifiers changed from: 0000 */
        public void updateFromSearchFilters() {
            this.businessTravelReadyModel.checked(ExploreHomesFiltersFragment.this.searchFilters.isBusinessTravelReady());
            ExploreHomesFiltersFragment.this.businessTravelAccountManager.setUsingBTRFilter(this.businessTravelReadyModel.checked());
            this.instantBookModel.checked(ExploreHomesFiltersFragment.this.searchFilters.isInstantBookOnly());
            this.entireHomeModel.checked(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes().contains(C6120RoomType.EntireHome));
            this.privateRoomModel.checked(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes().contains(C6120RoomType.PrivateRoom));
            this.sharedRoomModel.checked(ExploreHomesFiltersFragment.this.searchFilters.getRoomTypes().contains(C6120RoomType.SharedRoom));
            SearchMetaData homeTabMetadata = ExploreHomesFiltersFragment.this.dataController.getHomesMetadata();
            if (homeTabMetadata != null && homeTabMetadata.hasFacet()) {
                SearchFacets facets = homeTabMetadata.getFacets();
                this.entireHomeModel.show(facets.facetGreaterThanZero(C6120RoomType.EntireHome));
                this.privateRoomModel.show(facets.facetGreaterThanZero(C6120RoomType.PrivateRoom));
                this.sharedRoomModel.show(facets.facetGreaterThanZero(C6120RoomType.SharedRoom));
            }
            this.priceModel.minPrice(ExploreHomesFiltersFragment.this.searchFilters.getMinPrice()).maxPrice(ExploreHomesFiltersFragment.this.searchFilters.getMaxPrice());
            if (homeTabMetadata == null) {
                this.priceModel.histogram(null).metadata(null);
            } else {
                this.priceModel.histogram(homeTabMetadata.getPriceHistogram()).metadata(homeTabMetadata.getSearch());
            }
            this.priceButtonsModel.selection(ExploreHomesFiltersFragment.this.searchFilters.getPriceFilterSelection());
            this.bedCountModel.value(ExploreHomesFiltersFragment.this.searchFilters.getNumBeds());
            this.bedroomCountModel.value(ExploreHomesFiltersFragment.this.searchFilters.getNumBedrooms());
            this.bathroomCountModel.value(ExploreHomesFiltersFragment.this.searchFilters.getNumBathrooms());
            addCategorizedAmenitiesIfNeeded();
            updateOtherAmenitiesModels();
            updateFacilitiesAmenitiesModels();
            updateHouseRulesAmenitiesModels();
            notifyModelsChanged();
        }

        private void addCategorizedAmenitiesIfNeeded() {
            if (this.otherAmenitiesModels.isEmpty() && this.facilitiesAmenitiesModels.isEmpty() && this.houseRulesAmenitiesModels.isEmpty()) {
                SearchMetaData homeTabMetadata = ExploreHomesFiltersFragment.this.dataController.getHomesMetadata();
                if (homeTabMetadata != null && homeTabMetadata.getFacets() != null) {
                    List<SearchFacetWithIntKey> topOtherAmenities = homeTabMetadata.getFacets().getTopOtherAmenities();
                    List<SearchFacetWithIntKey> notTopOtherAmenities = homeTabMetadata.getFacets().getOtherAmenities();
                    List<SearchFacetWithIntKey> otherAmenities = null;
                    if (!(topOtherAmenities == null || notTopOtherAmenities == null)) {
                        otherAmenities = new ArrayList<>(topOtherAmenities.size() + notTopOtherAmenities.size());
                        Set<Integer> topOtherAmenityIds = new HashSet<>();
                        for (SearchFacetWithIntKey a : topOtherAmenities) {
                            if (Amenity.forId(a.getKey()) != null) {
                                otherAmenities.add(a);
                                topOtherAmenityIds.add(Integer.valueOf(a.getKey()));
                            }
                        }
                        this.numberOfTopOtherAmenities = topOtherAmenityIds.size();
                        for (SearchFacetWithIntKey a2 : notTopOtherAmenities) {
                            if (!topOtherAmenityIds.contains(Integer.valueOf(a2.getKey()))) {
                                otherAmenities.add(a2);
                            }
                        }
                    }
                    addAmenityModels(otherAmenities, this.otherAmenitiesModels, this.categorizedAmenitiesSeeMoreModel);
                    addAmenityModels(homeTabMetadata.getFacets().getFacilitiesAmenities(), this.facilitiesAmenitiesModels, this.categorizedFacilitiesAmenitiesSeeMoreModel);
                    addAmenityModels(homeTabMetadata.getFacets().getHouseRulesAmenities(), this.houseRulesAmenitiesModels, this.categorizedHouseRulesAmenitiesSeeMoreModel);
                    if (this.otherAmenitiesModels.size() < 6) {
                        this.categorizedAmenitiesSeeMoreModel.expanded(true);
                    }
                    if (this.facilitiesAmenitiesModels.size() < 6) {
                        this.categorizedFacilitiesAmenitiesSeeMoreModel.expanded(true);
                    }
                    if (this.houseRulesAmenitiesModels.size() < 6) {
                        this.categorizedHouseRulesAmenitiesSeeMoreModel.expanded(true);
                    }
                }
            }
        }

        private void addAmenityModels(List<SearchFacetWithIntKey> amenities, List<AmenityToggleRowModel_> modelsList, EpoxyModel<?> modelToInsertBefore) {
            if (amenities != null) {
                for (SearchFacetWithIntKey amenityFacet : amenities) {
                    Amenity amenity = Amenity.forId(amenityFacet.getKey());
                    if (amenity != null) {
                        AmenityToggleRowModel_ model = buildAmenityEpoxyModel(amenity);
                        modelsList.add(model);
                        int modelPosition = getModelPosition(modelToInsertBefore);
                        if (modelPosition != -1) {
                            this.models.add(modelPosition, model);
                        }
                    }
                }
            }
        }

        /* access modifiers changed from: private */
        public void expandOtherAmenities(boolean expanded, boolean fromUser) {
            this.categorizedAmenitiesSeeMoreModel.expanded(expanded);
            updateOtherAmenitiesModels();
            ExploreHomesFiltersFragment.this.amenitiesExpanded = expanded;
            if (fromUser) {
                if (!this.categorizedAmenitiesSeeMoreModel.expanded()) {
                    scrollToHeaderWithExtraPadding(this.categorizedAmenitiesHeaderModel);
                }
                ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneClickSeeAllAmenities(C2141FilterSection.Amenities, this.categorizedAmenitiesSeeMoreModel.expanded());
            }
        }

        private void updateOtherAmenitiesModels() {
            updateCategorizedAmenitySection(null, this.categorizedAmenitiesHeaderModel, this.categorizedAmenitiesSeeMoreModel, this.otherAmenitiesModels, this.numberOfTopOtherAmenities);
        }

        /* access modifiers changed from: private */
        public void expandFacilitiesAmenities(boolean expanded, boolean fromUser) {
            this.categorizedFacilitiesAmenitiesSeeMoreModel.expanded(expanded);
            updateFacilitiesAmenitiesModels();
            ExploreHomesFiltersFragment.this.facilitiesExpanded = expanded;
            if (fromUser) {
                if (!this.categorizedFacilitiesAmenitiesSeeMoreModel.expanded()) {
                    scrollToHeaderWithExtraPadding(this.categorizedFacilitiesAmenitiesHeaderModel);
                }
                ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneClickSeeAllAmenities(C2141FilterSection.Facilities, this.categorizedFacilitiesAmenitiesSeeMoreModel.expanded());
            }
        }

        private void updateFacilitiesAmenitiesModels() {
            updateCategorizedAmenitySection(this.categorizedFacilitiesAmenitiesHeaderDividerModel, this.categorizedFacilitiesAmenitiesHeaderModel, this.categorizedFacilitiesAmenitiesSeeMoreModel, this.facilitiesAmenitiesModels, 3);
        }

        /* access modifiers changed from: private */
        public void expandHouseRulesAmenities(boolean expanded, boolean fromUser) {
            this.categorizedHouseRulesAmenitiesSeeMoreModel.expanded(expanded);
            updateHouseRulesAmenitiesModels();
            ExploreHomesFiltersFragment.this.houseRulesExpanded = expanded;
            if (fromUser) {
                if (!this.categorizedHouseRulesAmenitiesSeeMoreModel.expanded()) {
                    scrollToHeaderWithExtraPadding(this.categorizedHouseRulesAmenitiesHeaderModel);
                }
                ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneClickSeeAllAmenities(C2141FilterSection.HouseRules, this.categorizedHouseRulesAmenitiesSeeMoreModel.expanded());
            }
        }

        private void updateHouseRulesAmenitiesModels() {
            updateCategorizedAmenitySection(this.categorizedHouseRulesAmenitiesHeaderDividerModel, this.categorizedHouseRulesAmenitiesHeaderModel, this.categorizedHouseRulesAmenitiesSeeMoreModel, this.houseRulesAmenitiesModels, 3);
        }

        private void scrollToHeaderWithExtraPadding(MicroSectionHeaderEpoxyModel_ headerModel) {
            int modelPosition = getModelPosition(headerModel);
            if (modelPosition != -1) {
                ExploreHomesFiltersFragment.this.recyclerView.scrollToPosition(modelPosition - 2);
            }
        }

        private void updateCategorizedAmenitySection(EpoxyModel<?> dividerModel, MicroSectionHeaderEpoxyModel_ headerModel, SeeMoreSeeLessRowModel_ seeMoreModel, List<AmenityToggleRowModel_> amenityModels, int foldThreshold) {
            boolean hasAmenities;
            boolean z;
            boolean z2 = true;
            boolean areAllAmenitiesSelected = true;
            for (int i = 0; i < amenityModels.size(); i++) {
                AmenityToggleRowModel_ model = (AmenityToggleRowModel_) amenityModels.get(i);
                boolean isSelected = ExploreHomesFiltersFragment.this.searchFilters.getAmenities().contains(model.amenity());
                if (i < foldThreshold || seeMoreModel.expanded() || isSelected) {
                    z = true;
                } else {
                    z = false;
                }
                model.show(z).checked(isSelected);
                if (!isSelected) {
                    areAllAmenitiesSelected = false;
                }
            }
            if (!ListUtils.isEmpty((Collection<?>) amenityModels)) {
                hasAmenities = true;
            } else {
                hasAmenities = false;
            }
            if (dividerModel != null) {
                dividerModel.show(hasAmenities);
            }
            headerModel.show(hasAmenities);
            if (amenityModels.size() < 6 || areAllAmenitiesSelected) {
                z2 = false;
            }
            seeMoreModel.show(z2);
            notifyModelsChanged();
        }

        private AmenityToggleRowModel_ buildAmenityEpoxyModel(Amenity amenity) {
            return new AmenityToggleRowModel_().amenity(amenity).checked(ExploreHomesFiltersFragment.this.searchFilters.getAmenities().contains(amenity)).checkChangedListener(ExploreHomesFiltersFragment$FiltersAdapter$$Lambda$13.lambdaFactory$(this, amenity)).showDivider(false);
        }

        static /* synthetic */ void lambda$buildAmenityEpoxyModel$12(FiltersAdapter filtersAdapter, Amenity amenity, FindInlineFiltersToggleRow toggleRow, boolean checked) {
            ExploreHomesFiltersFragment.this.searchFilters.setHasAmenity(amenity, checked);
            ExploreHomesFiltersFragment.this.exploreJitneyLogger.filtersPaneUpdateAmenities(ExploreHomesFiltersFragment.this.searchFilters.getAmenities());
        }

        /* access modifiers changed from: private */
        public void setPriceFilterButtonSelection(int selection) {
            SearchMetaData homesMetadata = ExploreHomesFiltersFragment.this.dataController.getHomesMetadata();
            if (homesMetadata != null && homesMetadata.getSearch() != null && homesMetadata.getPriceHistogram() != null && !ListUtils.isEmpty((Collection<?>) homesMetadata.getPriceHistogram().getSymbolRanges()) && homesMetadata.getPriceHistogram().getSymbolRanges().size() == 2) {
                this.priceButtonsModel.selection(selection);
                ExploreHomesFiltersFragment.this.searchFilters.setPriceFilterSelection(selection);
                int minPriceLow = ((Integer) homesMetadata.getPriceHistogram().getSymbolRanges().get(0)).intValue();
                int midPriceHigh = ((Integer) homesMetadata.getPriceHistogram().getSymbolRanges().get(1)).intValue();
                switch (selection) {
                    case 1:
                        ExploreHomesFiltersFragment.this.searchFilters.setMinPrice(0);
                        ExploreHomesFiltersFragment.this.searchFilters.setMaxPrice(minPriceLow);
                        return;
                    case 2:
                        ExploreHomesFiltersFragment.this.searchFilters.setMinPrice(minPriceLow);
                        ExploreHomesFiltersFragment.this.searchFilters.setMaxPrice(midPriceHigh);
                        return;
                    case 3:
                        ExploreHomesFiltersFragment.this.searchFilters.setMinPrice(midPriceHigh);
                        ExploreHomesFiltersFragment.this.searchFilters.setMaxPrice(0);
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public enum NavigableFilter {
        InstantBook,
        Price,
        RoomType,
        BedCount,
        BedroomCount,
        BathroomCount
    }

    public static ExploreHomesFiltersFragment newInstance() {
        return new ExploreHomesFiltersFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_explore_inline_filters, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        IcepickWrapper.restoreInstanceState(this, savedInstanceState);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        IcepickWrapper.saveInstanceState(this, outState);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (this.searchFilters == null) {
            this.searchFilters = SearchFilters.cloneFrom(this.dataController.getHomesSearchFilters(), false);
        }
        this.selectedWheelchairAccessible = this.searchFilters.getAmenities().contains(Amenity.HandicapAccessible);
        this.adapter = new FiltersAdapter();
        this.recyclerView.setAdapter(this.adapter);
        String navigableFilterName = getArguments() != null ? getArguments().getString(ARG_GO_TO_NAVIGABLE_FILTER) : null;
        if (!TextUtils.isEmpty(navigableFilterName)) {
            ((LinearLayoutManager) this.recyclerView.getLayoutManager()).scrollToPositionWithOffset(this.adapter.getNavigableFilterPosition(NavigableFilter.valueOf(navigableFilterName)), ViewUtils.getActionBarHeight(getContext()));
        }
        this.exploreJitneyLogger.homeFilterImpression(this.exploreNavigationController.isMapMode());
    }

    public Strap getNavigationTrackingParams() {
        String sourceTag = getArguments() != null ? getArguments().getString(ARG_SOURCE_TAG) : null;
        if (!TextUtils.isEmpty(sourceTag)) {
            return super.getNavigationTrackingParams().mo11639kv(BaseAnalytics.FROM, sourceTag);
        }
        return super.getNavigationTrackingParams();
    }

    public void onStart() {
        super.onStart();
        updateSearchButton();
        this.adapter.updateFromSearchFilters();
        this.searchFilters.addChangeListener(this);
    }

    public void onStop() {
        this.searchFilters.removeChangeListener(this);
        super.onStop();
    }

    public void onDestroy() {
        if (!this.hasClickedSearchButton && !getActivity().isChangingConfigurations()) {
            this.exploreJitneyLogger.filtersPaneClickClose();
        }
        super.onDestroy();
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.reset_all) {
            return super.onOptionsItemSelected(item);
        }
        this.exploreJitneyLogger.filtersPaneClickReset();
        resetSearchFilters();
        return true;
    }

    @OnClick
    public void onViewListingsClicked() {
        this.hasClickedSearchButton = true;
        this.exploreJitneyLogger.clickResultsOnFilters();
        this.dataController.cancelMetadataRequest();
        this.dataController.setHomesSearchFiltersAndFetchTab(this.searchFilters);
        this.exploreNavigationController.popBackStack();
    }

    /* access modifiers changed from: protected */
    public void resetSearchFilters() {
        this.searchFilters.resetToDefaults();
    }

    public void onSearchParamsUpdated() {
        postUpdateSearchButtonAndFilters();
    }

    public void onSearchFiltersChanged(int filterType) {
        this.dataController.fetchHomeTabMedataDebounced(this.searchFilters);
        postUpdateSearchButtonAndFilters();
        boolean containsWheelchairAccessible = this.searchFilters.getAmenities().contains(Amenity.HandicapAccessible);
        if (!this.selectedWheelchairAccessible && containsWheelchairAccessible) {
            this.exploreNavigationController.showWheelchairAccessibleNotification();
        }
        this.selectedWheelchairAccessible = containsWheelchairAccessible;
    }

    public void onTabMetadataUpdated(String tabId) {
        if (Tab.HOME.getTabId().equals(tabId)) {
            postUpdateSearchButtonAndFilters();
        }
    }

    private void postUpdateSearchButtonAndFilters() {
        if (this.recyclerView != null) {
            this.recyclerView.post(ExploreHomesFiltersFragment$$Lambda$1.lambdaFactory$(this));
        }
    }

    static /* synthetic */ void lambda$postUpdateSearchButtonAndFilters$0(ExploreHomesFiltersFragment exploreHomesFiltersFragment) {
        if (exploreHomesFiltersFragment.recyclerView != null) {
            exploreHomesFiltersFragment.updateSearchButton();
            exploreHomesFiltersFragment.adapter.updateFromSearchFilters();
        }
    }

    private Rect getTweenRowRect(View row) {
        if (this.recyclerView == null) {
            return null;
        }
        return new Rect(row.getLeft(), row.getTop(), row.getRight(), Math.min(row.getBottom(), this.searchButton.getTop()));
    }

    private void updateSearchButton() {
        this.searchButton.setButtonEnabled(true);
        if (this.dataController.isFetchingTabMetaData()) {
            this.searchButton.setButtonLoading(true);
            return;
        }
        ExploreTab tab = this.dataController.findTab(Tab.HOME);
        if (tab == null || tab.hasError()) {
            if (tab == null) {
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Home tab is gone!"));
            }
            this.searchButton.setButtonLoading(false);
            this.searchButton.setButtonText(C0857R.string.explore_network_error_homes_filter_primary_button);
            return;
        }
        String homesCountText = getString(C0857R.string.view_homes);
        int listingsCount = this.dataController.getListingsCount();
        if (listingsCount != -1) {
            if (listingsCount > 300) {
                homesCountText = getString(C0857R.string.view_x_listings_max, Integer.valueOf(300));
            } else {
                String formattedListingsCount = NumberFormat.getIntegerInstance().format((long) listingsCount);
                homesCountText = getResources().getQuantityString(C0857R.plurals.view_x_listings, listingsCount, new Object[]{formattedListingsCount});
            }
        }
        this.searchButton.setButtonLoading(false);
        this.searchButton.setButtonText((CharSequence) homesCountText);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ExploreFilters;
    }
}
