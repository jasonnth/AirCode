package com.airbnb.android.explore.controllers;

import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.core.models.PopularDestinationGroup;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.TravelDestination;
import com.airbnb.android.core.models.TravelDestinationsMetaData;
import com.airbnb.android.core.responses.TravelDestinationsResponse;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResponse;
import java.util.List;

public class SearchSuggestionsDataController {
    private boolean arePopularLocationsPersonalized;
    private List<? extends Autocompletable> autocompleteItems;
    private List<LocationTypeaheadSuggestionItemForChina> chinaLocalTypeaheadSuggestionItems;
    private SuggestionsContentType contentType;
    private GeocoderResponse gpsResolvedLocation;
    private boolean hasLocationPermission = true;
    private String inputText;
    private String ipResolvedLocation;
    private List<PopularDestinationGroup> popularDestinationsChina;
    private List<TravelDestination> popularLocations;
    private long requestLatency;
    private List<SavedSearch> savedSearches;
    private String userMarket;

    public enum SuggestionsContentType {
        RecentSearchesAndPopular,
        GoogleAutocomplete,
        SatoriAutocomplete,
        ChinaAutocomplete
    }

    public void handleTravelDestinationsResponse(TravelDestinationsResponse response) {
        TravelDestinationsMetaData metaData = response.metaData;
        if (metaData != null) {
            this.ipResolvedLocation = metaData.getUserResolvedLocation();
            this.arePopularLocationsPersonalized = !metaData.isGlobalFallback();
        }
        this.popularLocations = response.destinations;
        setContentType(SuggestionsContentType.RecentSearchesAndPopular);
    }

    public void setPopularDestinationsChina(List<PopularDestinationGroup> destinations) {
        this.popularDestinationsChina = destinations;
        setContentType(SuggestionsContentType.RecentSearchesAndPopular);
    }

    public void setSavedSearches(List<SavedSearch> savedSearches2) {
        this.savedSearches = savedSearches2;
        setContentType(SuggestionsContentType.RecentSearchesAndPopular);
    }

    public void setAutocompleteItems(List<? extends Autocompletable> autocompleteItems2, String inputText2, long latencyMills) {
        this.autocompleteItems = autocompleteItems2;
        setContentType(SuggestionsContentType.GoogleAutocomplete);
        this.inputText = inputText2;
        this.requestLatency = latencyMills;
    }

    public void setSatoriAutocompleteItems(List<? extends Autocompletable> autocompleteItems2, String inputText2, long latencyMills) {
        this.autocompleteItems = autocompleteItems2;
        setContentType(SuggestionsContentType.SatoriAutocomplete);
        this.inputText = inputText2;
        this.requestLatency = latencyMills;
    }

    public void setChinaLocalTypeaheadSuggestionItems(List<LocationTypeaheadSuggestionItemForChina> chinaLocalTypeaheadSuggestionItems2, String inputText2) {
        this.chinaLocalTypeaheadSuggestionItems = chinaLocalTypeaheadSuggestionItems2;
        setContentType(SuggestionsContentType.ChinaAutocomplete);
        this.inputText = inputText2;
    }

    public void setUserMarket(String userMarket2) {
        this.userMarket = userMarket2;
    }

    public String getUserMarket() {
        return this.userMarket;
    }

    public long getRequestLatency() {
        return this.requestLatency;
    }

    public List<SavedSearch> getSavedSearches() {
        return this.savedSearches;
    }

    public List<TravelDestination> getPopularLocations() {
        return this.popularLocations;
    }

    public List<? extends Autocompletable> getAutocompleteItems() {
        return this.autocompleteItems;
    }

    public List<LocationTypeaheadSuggestionItemForChina> getChinaLocalTypeaheadSuggestionItems() {
        return this.chinaLocalTypeaheadSuggestionItems;
    }

    public boolean hasChinaLocalTypeaheadSuggestionItems() {
        return this.chinaLocalTypeaheadSuggestionItems != null;
    }

    public void setGpsResolvedLocation(GeocoderResponse gpsResolvedLocation2) {
        this.gpsResolvedLocation = gpsResolvedLocation2;
    }

    public List<PopularDestinationGroup> getPopularDestinationsChina() {
        return this.popularDestinationsChina;
    }

    public String getCurrentLocation() {
        String gpsLocation = this.gpsResolvedLocation != null ? this.gpsResolvedLocation.getCityStateCountry() : null;
        return gpsLocation != null ? gpsLocation : this.ipResolvedLocation;
    }

    public String getCityName() {
        if (this.gpsResolvedLocation != null) {
            return this.gpsResolvedLocation.getCityName();
        }
        return null;
    }

    public boolean arePopularLocationsPersonalized() {
        return this.arePopularLocationsPersonalized;
    }

    public void setHasLocationPermission(boolean hasLocationPermission2) {
        this.hasLocationPermission = hasLocationPermission2;
    }

    public boolean hasLocationPermission() {
        return this.hasLocationPermission;
    }

    public SuggestionsContentType getContentType() {
        return this.contentType;
    }

    public String getInputText() {
        return this.inputText;
    }

    public void setContentType(SuggestionsContentType contentType2) {
        this.contentType = contentType2;
    }
}
