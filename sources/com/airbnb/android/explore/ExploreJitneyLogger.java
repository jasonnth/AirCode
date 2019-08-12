package com.airbnb.android.explore;

import android.content.res.Resources;
import android.support.p002v7.widget.LinearLayoutManager;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.LayoutManager;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.android.airdate.AirDate;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.JitneyPublisher;
import com.airbnb.android.core.LoggingContextFactory;
import com.airbnb.android.core.analytics.BaseLogger;
import com.airbnb.android.core.enums.FilterRemovalSuggestionType;
import com.airbnb.android.core.enums.UrgencyMessageType;
import com.airbnb.android.core.models.Amenity;
import com.airbnb.android.core.models.C6120RoomType;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.FilterRemovalSuggestionItem;
import com.airbnb.android.core.models.FilterSuggestionFilters;
import com.airbnb.android.core.models.FilterSuggestionItem;
import com.airbnb.android.core.models.GuestDetails;
import com.airbnb.android.core.models.InlineSearchFeedFilterItem;
import com.airbnb.android.core.models.SearchMetaData;
import com.airbnb.android.core.models.SearchUrgencyCommitment;
import com.airbnb.android.core.models.find.MapBounds;
import com.airbnb.android.core.models.find.SearchFilters;
import com.airbnb.android.core.models.find.TopLevelSearchParams;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.SearchJitneyUtils;
import com.airbnb.android.core.viewcomponents.models.UrgencyEpoxyModel_;
import com.airbnb.android.explore.controllers.ExploreDataController;
import com.airbnb.android.explore.controllers.ExploreNavigationController;
import com.airbnb.android.explore.data.AutocompleteData;
import com.airbnb.android.explore.requests.ExploreSubtabClickLoggingRequest;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterRemovalSuggestionCardEpoxyModel_;
import com.airbnb.android.explore.viewcomponents.viewmodels.FilterSuggestionCardEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.AutocompletionTuple.p040v3.C1803AutocompletionTuple;
import com.airbnb.jitney.event.logging.ExpansionMethod.p089v1.C1990ExpansionMethod;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickListingExperienceEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickListingGenericEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickListingHomeEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickListingPlaceEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickSearchDatesEvent.Builder;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickSearchGuestsEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreClickSearchLocationEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFilterPaneClickSearchEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneClickCloseEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneClickDatepickerEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneClickResetEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneClickSeeAllEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneDatepickerClickBackEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneImpressionEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneResetDatesEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneSelectGuestEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneToggleIbEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneTogglePetsEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneToggleRoomTypesEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneUpdateAmenitiesEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneUpdateDatesEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneUpdatePriceEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreFiltersPaneUpdateSizeEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreListClickFilterEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreListClickGoldenTicketEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreListClickMapButtonEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreListGoldenTicketImpressionEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreListScrollEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreMapClickFilterEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreMapClickListButtonEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreMapClickMapPinEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreMapClickRedoSearchEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreMapSwipeListingCarouselEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreSectionImpressionEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreSelectSearchDatesEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreSelectSearchGuestsEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreSelectSearchLocationEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreSwipeCarouselEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreToggleSearchEvent;
import com.airbnb.jitney.event.logging.Explore.p095v1.ExploreViewMtPdpEvent;
import com.airbnb.jitney.event.logging.Explore.p097v3.ExploreClickSubtabEvent;
import com.airbnb.jitney.event.logging.ExploreSubtab.p098v1.C2139ExploreSubtab;
import com.airbnb.jitney.event.logging.FilterSection.p100v1.C2141FilterSection;
import com.airbnb.jitney.event.logging.FilterType.p101v1.C2142FilterType;
import com.airbnb.jitney.event.logging.LatLngBox.p132v1.C2366LatLngBox;
import com.airbnb.jitney.event.logging.LatLngPair.p133v1.C2368LatLngPair;
import com.airbnb.jitney.event.logging.MtPdpReferrer.p157v1.C2443MtPdpReferrer;
import com.airbnb.jitney.event.logging.MtProduct.p158v1.C2444MtProduct;
import com.airbnb.jitney.event.logging.Operation.p165v1.C2451Operation;
import com.airbnb.jitney.event.logging.RefineFilterSuggestion.p220v1.RefineFilterSuggestionFilterSuggestionEvent;
import com.airbnb.jitney.event.logging.RefineFilterSuggestion.p221v2.RefineFilterSuggestionFilterRemovalBubbleEvent;
import com.airbnb.jitney.event.logging.RoomType.p239v1.C2680RoomType;
import com.airbnb.jitney.event.logging.Search.p248v3.SearchLocationAutocompleteImpressionEvent;
import com.airbnb.jitney.event.logging.SearchContext.p249v1.C2731SearchContext;
import com.airbnb.jitney.event.logging.ToggleMethod.p268v1.C2759ToggleMethod;
import com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1.ImpressionData;
import com.airbnb.jitney.event.logging.UrgencyCommitment.p278v1.UrgencyCommitmentEvent;
import com.airbnb.jitney.event.logging.core.HelperMessage.p299v1.C2833HelperMessage;
import com.airbnb.jitney.event.logging.core.SearchView.p303v1.SearchViewEvent;
import com.airbnb.jitney.event.logging.core.context.p025v2.Context;
import com.airbnb.p027n2.utils.ScrollDirectionListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.google.gson.jpush.JsonParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;
import p032rx.Observer;

public class ExploreJitneyLogger extends BaseLogger {
    private static final String AIRBNB_PICKS_SEARCH_TERM = "Airbnb Picks";
    private static final String LIST = "list";
    private static final String LOCATION_BELOW_TITLE = "p2_below_title";
    private static final String MAP = "map";
    private final ExploreDataController dataController;
    private final ExploreNavigationController navigationController;
    private final OnScrollListener onScrollListener = new ScrollDirectionListener() {
        /* access modifiers changed from: protected */
        public void onScrollEnd(RecyclerView recyclerView, String scrollType) {
            ExploreJitneyLogger.this.trackOnScroll(scrollType, recyclerView);
        }
    };
    private final Map<C6120RoomType, C2680RoomType> roomTypesMap = new HashMap();
    private final boolean sendLogImmediately;
    private final Map<String, C2139ExploreSubtab> subtabsMap = new HashMap();

    public ExploreJitneyLogger(LoggingContextFactory loggingContextFactory, ExploreDataController dataController2, ExploreNavigationController navigationController2) {
        super(loggingContextFactory);
        this.dataController = dataController2;
        this.navigationController = navigationController2;
        this.sendLogImmediately = Experiments.sendLogImmediately();
        createLoggingEnumMaps();
    }

    private void createLoggingEnumMaps() {
        createSubtabsMap();
        createRoomTypesMap();
    }

    private void createSubtabsMap() {
        this.subtabsMap.put(Tab.HOME.getTabId(), C2139ExploreSubtab.Homes);
        this.subtabsMap.put(Tab.EXPERIENCE.getTabId(), C2139ExploreSubtab.Experiences);
        this.subtabsMap.put(Tab.ALL.getTabId(), C2139ExploreSubtab.All);
        this.subtabsMap.put(Tab.PLACES.getTabId(), C2139ExploreSubtab.Places);
    }

    private void createRoomTypesMap() {
        Check.state(C6120RoomType.values().length == C2680RoomType.values().length, "Mismatched number of RoomType values");
        this.roomTypesMap.put(C6120RoomType.EntireHome, C2680RoomType.EntireHome);
        this.roomTypesMap.put(C6120RoomType.PrivateRoom, C2680RoomType.PrivateRoom);
        this.roomTypesMap.put(C6120RoomType.SharedRoom, C2680RoomType.SharedRoom);
    }

    private C2139ExploreSubtab subtab() {
        String activeTabId = this.navigationController.getActiveTabId();
        if (activeTabId == null) {
            return C2139ExploreSubtab.Unknown;
        }
        return (C2139ExploreSubtab) this.subtabsMap.get(activeTabId);
    }

    public void clickDates() {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext()));
    }

    public void clickLocation() {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreClickSearchLocationEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext()));
    }

    public void clickGuests() {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreClickSearchGuestsEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext()));
    }

    public void selectLocation(String newSearchTerm, String oldSearchTerm) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreSelectSearchLocationEvent.Builder(context(), SanitizeUtils.emptyIfNull(newSearchTerm), SanitizeUtils.emptyIfNull(oldSearchTerm), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext()));
    }

    public void selectDates(AirDate oldStart, AirDate oldEnd, AirDate newStart, AirDate newEnd) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreSelectSearchDatesEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(newStart), formatDate(newEnd)}), Arrays.asList(new String[]{formatDate(oldStart), formatDate(oldEnd)}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext()));
    }

    public void selectGuests(GuestDetails newGuests, GuestDetails oldGuests) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreSelectSearchGuestsEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) newGuests.totalGuestCount()), Long.valueOf((long) oldGuests.totalGuestCount()), subtab(), searchContext()));
    }

    public void homeClick(long listingId) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreClickListingHomeEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), Long.valueOf(listingId), subtab(), searchContext()));
    }

    public void experienceClick(long templateId) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreClickListingExperienceEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), Long.valueOf(templateId), subtab(), searchContext()));
    }

    public void recommendationItemClick(long id, String itemType, String sectionId) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        List<String> dates = Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())});
        ExploreClickListingGenericEvent.Builder builder = new ExploreClickListingGenericEvent.Builder(context(), Long.valueOf(id), itemType, sectionId, searchContext());
        builder.guests(Long.valueOf((long) data.guestDetails().totalGuestCount()));
        builder.location(data.searchTerm());
        builder.dates(dates);
        publish(builder);
    }

    public void placesClick(long placeId) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreClickListingPlaceEvent.Builder(context(), SanitizeUtils.emptyIfNull(data.searchTerm()), Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), Long.valueOf(placeId), subtab(), searchContext()));
    }

    public void toggleSearch(boolean toggle) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        publish(new ExploreToggleSearchEvent.Builder(context(), data.searchTerm() != null ? data.searchTerm() : AIRBNB_PICKS_SEARCH_TERM, Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())}), Long.valueOf((long) data.guestDetails().totalGuestCount()), subtab(), searchContext(), toggle ? "toggle" : "untoggle"));
    }

    public void toggleToMap() {
        publish(new ExploreListClickMapButtonEvent.Builder(context(), subtab(), searchContext()));
    }

    public void toggleToList() {
        publish(new ExploreMapClickListButtonEvent.Builder(context(), subtab(), searchContext()));
    }

    public void clickFiltersOnMap() {
        publish(new ExploreMapClickFilterEvent.Builder(context(), subtab(), searchContext()));
    }

    public void clickFiltersOnList() {
        publish(new ExploreListClickFilterEvent.Builder(context(), subtab(), searchContext()));
    }

    public void clickResultsOnFilters() {
        TopLevelSearchParams searchParams = this.dataController.getTopLevelSearchParams();
        SearchFilters searchFilters = this.dataController.getHomesSearchFilters();
        publish(new ExploreFilterPaneClickSearchEvent.Builder(context(), subtab(), searchContext(), Arrays.asList(new String[]{formatDate(searchParams.checkInDate()), formatDate(searchParams.checkOutDate())}), Long.valueOf((long) searchParams.guestDetails().totalGuestCount()), Boolean.valueOf(searchParams.guestDetails().isBringingPets()), Boolean.valueOf(searchFilters.isInstantBookOnly()), Arrays.asList(new Long[]{Long.valueOf((long) searchFilters.getMinPrice()), Long.valueOf((long) searchFilters.getMaxPrice())}), formatCollection(searchFilters.getRoomTypes(), ExploreJitneyLogger$$Lambda$1.lambdaFactory$(this)), Long.valueOf((long) searchFilters.getNumBeds()), Long.valueOf((long) searchFilters.getNumBedrooms()), Double.valueOf((double) searchFilters.getNumBathrooms()), formatCollection(searchFilters.getAmenities(), ExploreJitneyLogger$$Lambda$2.lambdaFactory$()), Long.valueOf((long) this.dataController.getHomesCount())));
    }

    static /* synthetic */ C2680RoomType lambda$clickResultsOnFilters$0(ExploreJitneyLogger exploreJitneyLogger, C6120RoomType roomType) {
        return (C2680RoomType) exploreJitneyLogger.roomTypesMap.get(roomType);
    }

    public void clickMapRedoSearch(LatLng latLngNE, LatLng latLngSW) {
        publish(new ExploreMapClickRedoSearchEvent.Builder(context(), subtab(), searchContext(), new C2366LatLngBox.Builder(new C2368LatLngPair.Builder(Double.valueOf(latLngNE.latitude), Double.valueOf(latLngNE.longitude)).build(), new C2368LatLngPair.Builder(Double.valueOf(latLngSW.latitude), Double.valueOf(latLngSW.longitude)).build()).build()));
    }

    public void tapMapMarker(long listingId) {
        publish(new ExploreMapClickMapPinEvent.Builder(context(), subtab(), searchContext(), Long.valueOf(listingId)));
    }

    public void swipeListingCarousel(String scrollType, long listingId, int position) {
        publish(new ExploreMapSwipeListingCarouselEvent.Builder(context(), SearchJitneyUtils.getJitneyDirectionForScrollType(scrollType), subtab(), searchContext(), Long.valueOf(listingId), Long.valueOf((long) position)));
    }

    public void goldenTicketImpression(InlineSearchFeedFilterItem item) {
        publish(new ExploreListGoldenTicketImpressionEvent.Builder(context(), subtab(), searchContext(), item.getType().type));
    }

    public void p2UrgencyImpression(UrgencyEpoxyModel_ item) {
        TopLevelSearchParams searchParams = this.dataController.getTopLevelSearchParams();
        publish(new UrgencyCommitmentEvent.Builder(context()).operation("impression").page("explore_flow_page").checkin_date(formatDate(searchParams.checkInDate())).checkout_date(formatDate(searchParams.checkOutDate())).guests(Long.valueOf((long) searchParams.guestDetails().totalGuestCount())).impression_data(new ImpressionData.Builder().message_type(item.type().getServerKey()).headline_variation(item.title()).body_variation(item.subtitle()).context_variation(item.contextualMessage()).build()));
    }

    public void clickGoldenTicket(InlineSearchFeedFilterItem item) {
        publish(new ExploreListClickGoldenTicketEvent.Builder(context(), subtab(), searchContext(), item.getType().type));
    }

    public void homeFilterImpression(boolean isMap) {
        List<Long> price;
        long numBeds;
        long numBedrooms;
        double numBathrooms;
        TopLevelSearchParams searchParams = this.dataController.getTopLevelSearchParams();
        SearchFilters searchFilters = this.dataController.getHomesSearchFilters();
        long guestCount = searchParams.guestDetails() == null ? 0 : (long) searchParams.guestDetails().totalGuestCount();
        if (searchFilters == null) {
            price = ImmutableList.m1286of(Long.valueOf(0), Long.valueOf(0));
        } else {
            price = ImmutableList.m1286of(Long.valueOf((long) searchFilters.getMinPrice()), Long.valueOf((long) searchFilters.getMaxPrice()));
        }
        Context context = context();
        String str = isMap ? "map" : LIST;
        C2139ExploreSubtab subtab = subtab();
        C2731SearchContext searchContext = searchContext();
        List formatDates = formatDates();
        Long valueOf = Long.valueOf(guestCount);
        Boolean valueOf2 = Boolean.valueOf(searchFilters != null && searchParams.guestDetails().isBringingPets());
        Boolean valueOf3 = Boolean.valueOf(searchFilters != null && searchFilters.isInstantBookOnly());
        List formatCollection = searchFilters == null ? Collections.emptyList() : formatCollection(searchFilters.getRoomTypes(), ExploreJitneyLogger$$Lambda$3.lambdaFactory$(this));
        if (searchFilters == null) {
            numBeds = 0;
        } else {
            numBeds = (long) searchFilters.getNumBeds();
        }
        Long valueOf4 = Long.valueOf(numBeds);
        if (searchFilters == null) {
            numBedrooms = 0;
        } else {
            numBedrooms = (long) searchFilters.getNumBedrooms();
        }
        Long valueOf5 = Long.valueOf(numBedrooms);
        if (searchFilters == null) {
            numBathrooms = 0.0d;
        } else {
            numBathrooms = (double) searchFilters.getNumBathrooms();
        }
        publish(new ExploreFiltersPaneImpressionEvent.Builder(context, str, subtab, searchContext, formatDates, valueOf, valueOf2, valueOf3, price, formatCollection, valueOf4, valueOf5, Double.valueOf(numBathrooms), searchFilters == null ? Collections.emptyList() : formatCollection(searchFilters.getAmenities(), ExploreJitneyLogger$$Lambda$4.lambdaFactory$())));
    }

    static /* synthetic */ C2680RoomType lambda$homeFilterImpression$2(ExploreJitneyLogger exploreJitneyLogger, C6120RoomType roomType) {
        return (C2680RoomType) exploreJitneyLogger.roomTypesMap.get(roomType);
    }

    public void filtersPaneClickDatepicker() {
        publish(new ExploreFiltersPaneClickDatepickerEvent.Builder(context(), subtab(), searchContext()));
    }

    public void filtersPaneUpdateDates(AirDate checkin, AirDate checkout) {
        TopLevelSearchParams searchParams = this.dataController.getTopLevelSearchParams();
        publish(new ExploreFiltersPaneUpdateDatesEvent.Builder(context(), subtab(), searchContext(), Arrays.asList(new String[]{formatDate(searchParams.checkInDate()), formatDate(searchParams.checkOutDate())})));
    }

    public void filtersPaneResetDates() {
        publish(new ExploreFiltersPaneResetDatesEvent.Builder(context(), subtab(), searchContext()));
    }

    public void filtersPaneDatepickerClickBack() {
        publish(new ExploreFiltersPaneDatepickerClickBackEvent.Builder(context(), subtab(), searchContext()));
    }

    public void filtersPaneSelectGuest(long guests) {
        publish(new ExploreFiltersPaneSelectGuestEvent.Builder(context(), subtab(), searchContext(), Long.valueOf(guests)));
    }

    public void filtersPaneTogglePets(boolean hasPets) {
        publish(new ExploreFiltersPaneTogglePetsEvent.Builder(context(), toggleMethodFor(hasPets), subtab(), searchContext(), Boolean.valueOf(hasPets)));
    }

    public void filtersPaneToggleIB(boolean isIB) {
        publish(new ExploreFiltersPaneToggleIbEvent.Builder(context(), toggleMethodFor(isIB), subtab(), searchContext(), Boolean.valueOf(isIB)));
    }

    public void filtersPaneUpdatePrice(long minPrice, long maxPrice) {
        publish(new ExploreFiltersPaneUpdatePriceEvent.Builder(context(), subtab(), searchContext(), ImmutableList.m1286of(Long.valueOf(minPrice), Long.valueOf(maxPrice))));
    }

    public void filtersPaneToggleRoomTypes(Set<C6120RoomType> roomTypes) {
        publish(new ExploreFiltersPaneToggleRoomTypesEvent.Builder(context(), subtab(), searchContext(), formatCollection(roomTypes, ExploreJitneyLogger$$Lambda$5.lambdaFactory$(this))));
    }

    static /* synthetic */ C2680RoomType lambda$filtersPaneToggleRoomTypes$4(ExploreJitneyLogger exploreJitneyLogger, C6120RoomType roomType) {
        return (C2680RoomType) exploreJitneyLogger.roomTypesMap.get(roomType);
    }

    public void filtersPaneUpdateSize(long beds, long bedrooms, double bathrooms) {
        publish(new ExploreFiltersPaneUpdateSizeEvent.Builder(context(), subtab(), searchContext(), Long.valueOf(beds), Long.valueOf(bedrooms), Double.valueOf(bathrooms)));
    }

    public void filtersPaneUpdateAmenities(List<Amenity> amenities) {
        publish(new ExploreFiltersPaneUpdateAmenitiesEvent.Builder(context(), subtab(), searchContext(), FluentIterable.from((Iterable<E>) amenities).transform(ExploreJitneyLogger$$Lambda$6.lambdaFactory$()).toList()));
    }

    public void filtersPaneClickSeeAllAmenities(C2141FilterSection filterSection, boolean isExpanded) {
        publish(new ExploreFiltersPaneClickSeeAllEvent.Builder(context(), filterSection, isExpanded ? C1990ExpansionMethod.Expand : C1990ExpansionMethod.Collapse, C2139ExploreSubtab.Homes, searchContext()));
    }

    public void homeResultsImpression() {
        String searchId = searchContext().search_id;
        if (this.dataController.hasHomesMetadata() && !TextUtils.isEmpty(searchId)) {
            SearchMetaData metaData = this.dataController.getHomesMetadata();
            SearchFilters searchFilters = this.dataController.getHomesSearchFilters();
            TopLevelSearchParams topLevelSearchParams = this.dataController.getTopLevelSearchParams();
            long listingsCount = (long) metaData.getListingsCount();
            List<C2833HelperMessage> helperMessages = getUrgencyHelperMessage(metaData.getUrgencyCommitment());
            String rawLocation = topLevelSearchParams.searchTerm();
            if (TextUtils.isEmpty(rawLocation)) {
                rawLocation = AIRBNB_PICKS_SEARCH_TERM;
            }
            SearchViewEvent.Builder eventBuilder = new SearchViewEvent.Builder(searchId, Long.valueOf((long) topLevelSearchParams.guestDetails().totalGuestCount()), Long.valueOf(listingsCount), rawLocation, context()).guests(Long.valueOf((long) topLevelSearchParams.guestDetails().totalGuestCount())).checkin_date(formatDate(topLevelSearchParams.checkInDate())).checkout_date(formatDate(topLevelSearchParams.checkOutDate())).n_results(Long.valueOf(listingsCount)).price_min(Long.valueOf((long) searchFilters.getMinPrice())).price_max(Long.valueOf((long) searchFilters.getMaxPrice())).room_types(formatCollection(searchFilters.getRoomTypes(), ExploreJitneyLogger$$Lambda$7.lambdaFactory$())).amenities(formatCollection(searchFilters.getAmenities(), ExploreJitneyLogger$$Lambda$8.lambdaFactory$())).languages(formatCollection(searchFilters.getLanguages(), ExploreJitneyLogger$$Lambda$9.lambdaFactory$())).bedrooms(Long.valueOf((long) searchFilters.getNumBedrooms())).bathrooms(Long.valueOf((long) searchFilters.getNumBathrooms())).beds(Long.valueOf((long) searchFilters.getNumBeds())).helper_messages(helperMessages);
            if (topLevelSearchParams.hasMapBounds()) {
                MapBounds mapBounds = topLevelSearchParams.mapBounds();
                LatLng swLatLng = mapBounds.latLngSW();
                LatLng neLatLng = mapBounds.latLngNE();
                eventBuilder.sw_lat(Double.valueOf(swLatLng.latitude));
                eventBuilder.sw_lng(Double.valueOf(swLatLng.longitude));
                eventBuilder.ne_lat(Double.valueOf(neLatLng.latitude));
                eventBuilder.ne_lng(Double.valueOf(neLatLng.longitude));
            }
            publish(eventBuilder);
        }
    }

    public void filtersPaneClickClose() {
        publish(new ExploreFiltersPaneClickCloseEvent.Builder(context(), subtab(), searchContext()));
    }

    public void filtersPaneClickReset() {
        publish(new ExploreFiltersPaneClickResetEvent.Builder(context(), subtab(), searchContext()));
    }

    public void clickAutocompleteLocation(AutocompleteData currEntry, Collection<AutocompleteData> autocompleteEntries, String inputQuery, String userMarket, long latency) {
        SearchLocationAutocompleteImpressionEvent.Builder builder = new SearchLocationAutocompleteImpressionEvent.Builder(context(), C2451Operation.Click, inputQuery, formatCollection(autocompleteEntries, ExploreJitneyLogger$$Lambda$10.lambdaFactory$(this))).user_market(userMarket).latency_ms(Long.valueOf(latency));
        if (currEntry != null) {
            builder.autocomplete_suggestion_clicked(createTuple(currEntry));
        }
        publish(builder);
    }

    public void autocompleteLocationsImpression(Collection<AutocompleteData> autocompleteEntries, String inputQuery, String userMarket, long latency) {
        publish(new SearchLocationAutocompleteImpressionEvent.Builder(context(), C2451Operation.Impression, inputQuery, formatCollection(autocompleteEntries, ExploreJitneyLogger$$Lambda$11.lambdaFactory$(this))).user_market(userMarket).latency_ms(Long.valueOf(latency)));
    }

    /* access modifiers changed from: private */
    public C1803AutocompletionTuple createTuple(AutocompleteData data) {
        return new C1803AutocompletionTuple.Builder(data.getLocation(), data.getSource(), Long.valueOf((long) data.getPosition())).api_place_id(data.getPlaceId()).filter_value(data.getFilterValue()).build();
    }

    public void clickSubtab(String newTabId) {
        clickSubtabOrSeeAll(newTabId, "subtab", false, null);
    }

    public void clickSeeAll(String newTabId, boolean isNewQuery, String newLocation) {
        clickSubtabOrSeeAll(newTabId, "see_all", isNewQuery, newLocation);
    }

    private void clickSubtabOrSeeAll(String newTabId, String target, boolean isNewQuery, String newLocation) {
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        C2731SearchContext searchContext = searchContext();
        C2139ExploreSubtab currSubtab = (C2139ExploreSubtab) this.subtabsMap.get(newTabId);
        C2139ExploreSubtab prevSubtab = subtab();
        ExploreClickSubtabEvent.Builder builder = new ExploreClickSubtabEvent.Builder(context(), target, currSubtab, prevSubtab, searchContext, Boolean.valueOf(isNewQuery)).location(data.searchTerm()).dates(Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())})).guests(Long.valueOf((long) data.guestDetails().totalGuestCount())).location_next(newLocation);
        if (this.sendLogImmediately) {
            JitneyPublisher.publishImmediately(builder);
        } else {
            publish(builder);
        }
        new ExploreSubtabClickLoggingRequest(data, searchContext.mobile_search_session_id, searchContext.search_id, currSubtab, prevSubtab, target, isNewQuery, newLocation).withListener((Observer) new C0699RL().onError(ExploreJitneyLogger$$Lambda$12.lambdaFactory$(this, target, currSubtab, prevSubtab, searchContext, isNewQuery, data, newLocation)).buildWithoutResubscription()).execute(NetworkUtil.singleFireExecutor());
    }

    public void trackOnCarouselScroll(String scrollType, RecyclerView recyclerView, int sectionOffset, String carouselTitle) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            String str = carouselTitle;
            ExploreSwipeCarouselEvent.Builder builder = new ExploreSwipeCarouselEvent.Builder(context(), str, Long.valueOf((long) sectionOffset), Long.valueOf((long) ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition()), SearchJitneyUtils.getJitneyDirectionForScrollType(scrollType), subtab(), searchContext());
            TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
            builder.location(SanitizeUtils.emptyIfNull(data.searchTerm())).dates(Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())})).guests(Long.valueOf((long) data.guestDetails().totalGuestCount()));
            publish(builder);
        }
    }

    public void sectionImpression(String sectionId) {
        ExploreSectionImpressionEvent.Builder builder = new ExploreSectionImpressionEvent.Builder(context(), sectionId, subtab(), searchContext());
        TopLevelSearchParams data = this.dataController.getTopLevelSearchParams();
        List<String> dates = Arrays.asList(new String[]{formatDate(data.checkInDate()), formatDate(data.checkOutDate())});
        builder.guests(Long.valueOf((long) data.guestDetails().totalGuestCount()));
        builder.location(data.searchTerm());
        builder.dates(dates);
        publish(builder);
    }

    public void playlistImpression(long playlistId, C2443MtPdpReferrer referrer) {
        ExploreViewMtPdpEvent.Builder builder = new ExploreViewMtPdpEvent.Builder(context(), Long.valueOf(playlistId), C2444MtProduct.Playlist, referrer);
        builder.search_context(searchContext());
        publish(builder);
    }

    public void amenityRemovalSuggestionClick(Amenity amenity, Resources res) {
        publish(new RefineFilterSuggestionFilterRemovalBubbleEvent.Builder(context(), C2142FilterType.Amenity, res.getString(amenity.stringRes), C2451Operation.Click, searchContext()).amenity_filter_id(Long.valueOf((long) amenity.f8471id)));
    }

    public void instantBookRemovalSuggestionClick(Resources res) {
        publish(new RefineFilterSuggestionFilterRemovalBubbleEvent.Builder(context(), C2142FilterType.InstantBook, res.getString(C0857R.string.instant_book), C2451Operation.Click, searchContext()));
    }

    public void filterRemovalSuggestionsImpression(FilterRemovalSuggestionCardEpoxyModel_ model, Resources res) {
        for (FilterRemovalSuggestionItem item : model.items()) {
            if (item.getType() == FilterRemovalSuggestionType.Amenity) {
                Amenity amenity = Amenity.forId(item.getId());
                if (amenity != null) {
                    publish(new RefineFilterSuggestionFilterRemovalBubbleEvent.Builder(context(), C2142FilterType.Amenity, res.getString(amenity.stringRes), C2451Operation.Impression, searchContext()).amenity_filter_id(Long.valueOf((long) amenity.f8471id)));
                }
            } else {
                publish(new RefineFilterSuggestionFilterRemovalBubbleEvent.Builder(context(), C2142FilterType.InstantBook, res.getString(C0857R.string.instant_book), C2451Operation.Impression, searchContext()));
            }
        }
    }

    public void filterSuggestionImpression(FilterSuggestionCardEpoxyModel_ model) {
        for (FilterSuggestionItem item : model.items()) {
            filterSuggestionOperation(item, C2451Operation.Impression);
        }
    }

    public void filterSuggestionClick(FilterSuggestionItem item) {
        filterSuggestionOperation(item, C2451Operation.Click);
    }

    private void filterSuggestionOperation(FilterSuggestionItem item, C2451Operation operation) {
        C2142FilterType filterType;
        FilterSuggestionFilters filters = item.getFilters();
        if (filters.hasRoomTypes()) {
            filterType = C2142FilterType.RoomTypes;
        } else if (filters.hasMinPrice() || filters.hasMaxPrice()) {
            filterType = C2142FilterType.PriceRange;
        } else if (filters.hasNumBeds()) {
            filterType = C2142FilterType.Beds;
        } else if (filters.hasNumBedrooms()) {
            filterType = C2142FilterType.Bedrooms;
        } else {
            filterType = C2142FilterType.Amenity;
        }
        publish(new RefineFilterSuggestionFilterSuggestionEvent.Builder(context(), filterType, item.getDisplayText(), operation, searchContext()));
    }

    /* access modifiers changed from: private */
    public void trackOnScroll(String scrollType, RecyclerView recyclerView) {
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            publish(new ExploreListScrollEvent.Builder(context(), SearchJitneyUtils.getJitneyDirectionForScrollType(scrollType), subtab(), searchContext(), Long.valueOf((long) ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition())));
        }
    }

    public void registerRecyclerView(RecyclerView recyclerView) {
        recyclerView.addOnScrollListener(this.onScrollListener);
    }

    public void unregisterRecyclerView(RecyclerView recyclerView) {
        recyclerView.removeOnScrollListener(this.onScrollListener);
    }

    private String formatDate(AirDate date) {
        return date == null ? "" : date.getIsoDateString();
    }

    private List<String> formatDates() {
        TopLevelSearchParams searchParams = this.dataController.getTopLevelSearchParams();
        return Arrays.asList(new String[]{formatDate(searchParams.checkInDate()), formatDate(searchParams.checkOutDate())});
    }

    private static <E, T> List<T> formatCollection(Collection<E> collection, Function<? super E, T> function) {
        if (ListUtils.isEmpty(collection)) {
            return Collections.emptyList();
        }
        return FluentIterable.from((Iterable<E>) collection).transform(function).toList();
    }

    public C2731SearchContext searchContext() {
        return SearchJitneyUtils.searchContext(this.dataController.getSearchId(), this.dataController.getSearchSessionId());
    }

    private C2759ToggleMethod toggleMethodFor(boolean value) {
        return value ? C2759ToggleMethod.Toggle : C2759ToggleMethod.Untoggle;
    }

    public List<C2833HelperMessage> getUrgencyHelperMessage(SearchUrgencyCommitment uc) {
        boolean displayed = this.dataController.showUrgencyMessage(uc);
        ArrayList<C2833HelperMessage> helperMessage = new ArrayList<>(1);
        JSONObject ucJson = new JSONObject();
        JSONObject messageJson = new JSONObject();
        if (!(uc == null || uc.getMessageType() == UrgencyMessageType.Unknown)) {
            try {
                messageJson.put("headline", uc.getTitle());
                messageJson.put("body", uc.getSubtitle());
                messageJson.put("contextual_message", uc.getContexualMessage());
                ucJson.put("message", messageJson);
                ucJson.put("message_type", uc.getMessageType().toString());
                helperMessage.add(new C2833HelperMessage.Builder(uc.getMessageType().toString(), LOCATION_BELOW_TITLE, Boolean.valueOf(displayed), ucJson.toString()).build());
                return helperMessage;
            } catch (JSONException e) {
                BugsnagWrapper.throwOrNotify((RuntimeException) new JsonParseException(e.getMessage()));
            }
        }
        return null;
    }
}
