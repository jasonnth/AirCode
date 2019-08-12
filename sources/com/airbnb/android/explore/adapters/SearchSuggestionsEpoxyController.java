package com.airbnb.android.explore.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.analytics.FindTweenAnalytics;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.models.ExploreTab.Tab;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.core.models.PopularDestinationGroup;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.models.SearchParams;
import com.airbnb.android.core.models.TravelDestination;
import com.airbnb.android.core.utils.geocoder.models.Autocompletable;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteTerm;
import com.airbnb.android.core.viewcomponents.models.InputSuggestionActionRowChinaEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.InputSuggestionActionRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.MicroSectionHeaderEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SearchSuggestionNearbyChinaEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SearchSuggestionPopularChinaEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.SearchSuggestionRecentChinaEpoxyModel_;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreJitneyLogger;
import com.airbnb.android.explore.controllers.SearchSuggestionUtils;
import com.airbnb.android.explore.controllers.SearchSuggestionsDataController;
import com.airbnb.android.explore.controllers.SearchSuggestionsDataController.SuggestionsContentType;
import com.airbnb.android.explore.data.AutocompleteData;
import com.airbnb.android.explore.viewcomponents.viewmodels.InputSuggestionSubRowEpoxyModel_;
import com.airbnb.android.utils.ListUtils;
import com.airbnb.p027n2.epoxy.AirEpoxyController;
import com.google.common.collect.FluentIterable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SearchSuggestionsEpoxyController extends AirEpoxyController {
    public static final int CHINA = 1;
    public static final int GLOBAL = 0;
    private static final int MAX_MATCHED_ENTRIES = 5;
    /* access modifiers changed from: private */
    public List<AutocompleteData> autocompleteEntries = new ArrayList();
    /* access modifiers changed from: private */
    public final Context context;
    /* access modifiers changed from: private */
    public final ExploreJitneyLogger jitneyLogger;
    private final SuggestionModelBuilder modelBuilder;
    private final ExploreLandingListener searchLandingListener;
    /* access modifiers changed from: private */
    public final SearchSuggestionsDataController searchSuggestionsController;

    enum AutocompleteSource {
        Google("google"),
        CuratedChina("curated_china"),
        VerticalFromGoogle("vertical_from_google"),
        Satori("satori"),
        VerticalFromSatori("vertical_from_satori");
        
        private final String name;

        private AutocompleteSource(String name2) {
            this.name = name2;
        }

        public String getName() {
            return this.name;
        }
    }

    private class ChinaSuggestionBuilder implements SuggestionModelBuilder {
        private static final int MAX_RECENT_SEARCHES_COUNT = 3;

        private ChinaSuggestionBuilder() {
        }

        public void buildRecentAndPopular() {
            List<SavedSearch> savedSearches = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getSavedSearches();
            String currentLocation = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getCurrentLocation();
            String cityName = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getCityName();
            SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
            new SearchSuggestionNearbyChinaEpoxyModel_().showNearBy(!TextUtils.isEmpty(currentLocation)).cityName(cityName).nearbyClickListener(C6324x9855d97e.lambdaFactory$(this, currentLocation)).cityClickListener(C6325x9855d97f.lambdaFactory$(this, cityName)).anywhereClickListener(C6326x9855d980.lambdaFactory$(this)).m5520id((CharSequence) FindTweenAnalytics.SEARCH_TYPE_CURRENT_LOCATION).addTo(SearchSuggestionsEpoxyController.this);
            if (!ListUtils.isEmpty((Collection<?>) savedSearches)) {
                new SearchSuggestionRecentChinaEpoxyModel_().savedSearches(savedSearches).itemClickListener(C6327x9855d981.lambdaFactory$(this)).m5544id((CharSequence) "recent_search").addTo(SearchSuggestionsEpoxyController.this);
            }
            List<PopularDestinationGroup> popularDestinations = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getPopularDestinationsChina();
            if (!ListUtils.isEmpty((Collection<?>) popularDestinations)) {
                new SearchSuggestionPopularChinaEpoxyModel_().popularDestinations(popularDestinations).itemClickListener(C6328x9855d982.lambdaFactory$(this)).m5532id((CharSequence) "popular_destinations").addTo(SearchSuggestionsEpoxyController.this);
            }
        }

        public void buildAutocomplete(String inputText, AutocompleteSource source) {
            if (!TextUtils.isEmpty(inputText)) {
                SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
                buildAutocompleteSavedSearches(inputText);
                List<? extends Autocompletable> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getAutocompleteItems();
                if (!ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                    for (Autocompletable autocompletePrediction : autocompletePredictions) {
                        addAutoCompleteEpoxyModel(autocompletePrediction, inputText, source);
                    }
                    SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
                }
            } else if (SearchSuggestionsEpoxyController.this.autocompleteEntries.isEmpty()) {
                buildRecentAndPopular();
            }
        }

        public void buildAutocompleteAppend(String inputText, AutocompleteSource source) {
            if (!TextUtils.isEmpty(inputText)) {
                int numCuratedLocations = SearchSuggestionsEpoxyController.this.autocompleteEntries.size();
                List<? extends Autocompletable> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getAutocompleteItems();
                if (numCuratedLocations < 5 && !ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                    int i = numCuratedLocations;
                    for (Autocompletable autocompletePrediction : autocompletePredictions) {
                        if (!SearchSuggestionsEpoxyController.this.hasEntry(autocompletePrediction)) {
                            addAutoCompleteEpoxyModel(autocompletePrediction, inputText, source);
                            i++;
                            if (i >= 5) {
                                break;
                            }
                        }
                    }
                    SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
                }
            }
        }

        public void buildChinaAutocomplete(String inputText) {
            if (!TextUtils.isEmpty(inputText)) {
                SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
                buildAutocompleteSavedSearches(inputText);
                List<LocationTypeaheadSuggestionItemForChina> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getChinaLocalTypeaheadSuggestionItems();
                if (!ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                    for (LocationTypeaheadSuggestionItemForChina autocompletePrediction : autocompletePredictions) {
                        new InputSuggestionActionRowChinaEpoxyModel_().title(autocompletePrediction.getTitle()).subtitle(autocompletePrediction.getDescription()).onClickListener(C6329x9855d983.lambdaFactory$(this, SearchSuggestionsEpoxyController.this.addAutocompleteData(autocompletePrediction.getPlaceId(), autocompletePrediction.getTitle(), AutocompleteSource.CuratedChina, null), inputText, autocompletePrediction)).m4944id((CharSequence) autocompletePrediction.getTitle() + autocompletePrediction.getPlaceId()).addTo(SearchSuggestionsEpoxyController.this);
                    }
                    SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
                }
            } else if (SearchSuggestionsEpoxyController.this.autocompleteEntries.isEmpty()) {
                buildRecentAndPopular();
            }
        }

        static /* synthetic */ void lambda$buildChinaAutocomplete$5(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteEntry, String inputText, LocationTypeaheadSuggestionItemForChina autocompletePrediction, View v) {
            SearchSuggestionsEpoxyController.this.jitneyLogger.clickAutocompleteLocation(autocompleteEntry, SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.AutoComplete, autocompletePrediction.getTextForSearch());
        }

        private void buildAutocompleteSavedSearches(String inputText) {
            List<SavedSearch> savedSearches = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getSavedSearches();
            if (!ListUtils.isEmpty((Collection<?>) savedSearches)) {
                List<SavedSearch> matchedSearches = FluentIterable.from((Iterable<E>) savedSearches).filter(C6330x9855d984.lambdaFactory$(inputText)).toList();
                int i = 0;
                while (i < 3 && i < matchedSearches.size()) {
                    SavedSearch savedSearch = (SavedSearch) matchedSearches.get(i);
                    String location = savedSearch.getSearchParams().getLocation();
                    new InputSuggestionActionRowChinaEpoxyModel_().title(location).subtitle(SearchSuggestionsEpoxyController.this.getSavedSearchesSubtitle(savedSearch)).label(SearchSuggestionsEpoxyController.this.context.getString(C0857R.string.search_suggestion_recent_search_title)).onClickListener(C6331x9855d985.lambdaFactory$(this, location, savedSearch)).m4944id((CharSequence) savedSearch.getSavedSearchId()).addTo(SearchSuggestionsEpoxyController.this);
                    i++;
                }
            }
        }

        static /* synthetic */ boolean lambda$buildAutocompleteSavedSearches$6(String inputText, SavedSearch item) {
            return item.getSearchParams().getLocation() != null && item.getSearchParams().getLocation().contains(inputText);
        }

        private void addAutoCompleteEpoxyModel(Autocompletable input, String inputQuery, AutocompleteSource source) {
            String title;
            String subtitle;
            List<AutocompleteTerm> terms = input.getTerms();
            String query = input.getDescription();
            if (terms.size() > 1) {
                title = ((AutocompleteTerm) terms.get(0)).getValue();
                subtitle = query.substring(((AutocompleteTerm) terms.get(1)).getOffset());
            } else {
                title = query;
                subtitle = null;
            }
            new InputSuggestionActionRowChinaEpoxyModel_().title(title).subtitle(subtitle).label(input.getLocationTag(SearchSuggestionsEpoxyController.this.context)).onClickListener(C6332x9855d986.lambdaFactory$(this, SearchSuggestionsEpoxyController.this.addAutocompleteData(input.getPlaceId(), query, source, null), inputQuery, query, input)).m4944id((CharSequence) "google_autocomplete" + input.getPlaceId()).addTo(SearchSuggestionsEpoxyController.this);
        }

        static /* synthetic */ void lambda$addAutoCompleteEpoxyModel$8(ChinaSuggestionBuilder chinaSuggestionBuilder, AutocompleteData autocompleteEntry, String inputQuery, String query, Autocompletable input, View v) {
            SearchSuggestionsEpoxyController.this.jitneyLogger.clickAutocompleteLocation(autocompleteEntry, SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputQuery, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.AutoComplete, query, input.getPlaceId(), null);
        }
    }

    public interface ExploreLandingListener {
        void onKeyboardEnterPressed(String str);

        void onSearchSuggestionClicked(String str, String str2, SavedSearch savedSearch, C5809SearchInputType searchInputType, String str3);
    }

    private class GlobalSuggestionBuilder implements SuggestionModelBuilder {
        private GlobalSuggestionBuilder() {
        }

        public void buildRecentAndPopular() {
            List<TravelDestination> popularLocations = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getPopularLocations();
            boolean arePopularLocationsPersonalized = SearchSuggestionsEpoxyController.this.searchSuggestionsController.arePopularLocationsPersonalized();
            List<SavedSearch> savedSearches = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getSavedSearches();
            String currentLocation = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getCurrentLocation();
            boolean hasLocationPermission = SearchSuggestionsEpoxyController.this.searchSuggestionsController.hasLocationPermission();
            SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
            if (hasLocationPermission) {
                InputSuggestionActionRowEpoxyModel_ currentLocationModel = new InputSuggestionActionRowEpoxyModel_().titleRes(C0857R.string.nearby).withInverseLayout().iconRes(C0857R.C0858drawable.ic_location).m4954id((long) (FindTweenAnalytics.SEARCH_TYPE_CURRENT_LOCATION + currentLocation).hashCode());
                if (currentLocation != null) {
                    currentLocationModel.subtitle(currentLocation).onClickListener(C6333x8ced951e.lambdaFactory$(this, currentLocation));
                } else {
                    currentLocationModel.subtitleRes(C0857R.string.loading);
                }
                currentLocationModel.addTo(SearchSuggestionsEpoxyController.this);
            }
            addAnywhereLocation();
            if (!ListUtils.isEmpty((Collection<?>) savedSearches)) {
                addHeader(C0857R.string.recent_searches_cluster_title);
                for (SavedSearch savedSearch : savedSearches) {
                    String location = savedSearch.getSearchParams().getLocation();
                    new InputSuggestionActionRowEpoxyModel_().title(location).subtitle(SearchSuggestionsEpoxyController.this.getSavedSearchesSubtitle(savedSearch)).label(SearchSuggestionsEpoxyController.this.getDetailedSearchFiltersText(savedSearch)).onClickListener(C6334x8ced9521.lambdaFactory$(this, location, savedSearch)).withInverseLayout().m4956id((CharSequence) savedSearch.getSavedSearchId()).addTo(SearchSuggestionsEpoxyController.this);
                }
            }
            addPopularLocations(popularLocations, arePopularLocationsPersonalized);
        }

        public void buildAutocomplete(String inputText, AutocompleteSource source) {
            SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
            List<? extends Autocompletable> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getAutocompleteItems();
            if (!ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                if (!FeatureToggles.showAutocompleteVerticalOptions()) {
                    addHeader(C0857R.string.suggestions);
                }
                int rowIndex = 0;
                for (Autocompletable autocompletePrediction : autocompletePredictions) {
                    boolean showVerticals = rowIndex == 0 && SearchSuggestionUtils.hasTripVerticals(autocompletePrediction) && FeatureToggles.showAutocompleteVerticalOptions();
                    addAutoCompleteEpoxyModel(autocompletePrediction, inputText, source, showVerticals);
                    if (showVerticals) {
                        addVerticalSubRows(autocompletePrediction, inputText, source);
                    }
                    rowIndex++;
                }
                SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            }
        }

        public void buildAutocompleteAppend(String inputText, AutocompleteSource source) {
            int numCuratedLocations = SearchSuggestionsEpoxyController.this.autocompleteEntries.size();
            if (numCuratedLocations == 0) {
                addHeader(C0857R.string.suggestions);
            }
            List<? extends Autocompletable> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getAutocompleteItems();
            if (numCuratedLocations < 5 && !ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                int i = numCuratedLocations;
                for (Autocompletable autocompletePrediction : autocompletePredictions) {
                    if (!SearchSuggestionsEpoxyController.this.hasEntry(autocompletePrediction)) {
                        addAutoCompleteEpoxyModel(autocompletePrediction, inputText, source, false);
                        i++;
                        if (i >= 5) {
                            break;
                        }
                    }
                }
                SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            }
        }

        public void buildChinaAutocomplete(String inputText) {
            List<LocationTypeaheadSuggestionItemForChina> autocompletePredictions = SearchSuggestionsEpoxyController.this.searchSuggestionsController.getChinaLocalTypeaheadSuggestionItems();
            SearchSuggestionsEpoxyController.this.autocompleteEntries.clear();
            if (!ListUtils.isEmpty((Collection<?>) autocompletePredictions)) {
                addHeader(C0857R.string.suggestions);
                for (LocationTypeaheadSuggestionItemForChina autocompletePrediction : autocompletePredictions) {
                    new InputSuggestionActionRowEpoxyModel_().title(autocompletePrediction.getTitle()).subtitle(autocompletePrediction.getDescription()).onClickListener(C6335x8ced9522.lambdaFactory$(this, SearchSuggestionsEpoxyController.this.addAutocompleteData(autocompletePrediction.getPlaceId(), autocompletePrediction.getTitle(), AutocompleteSource.CuratedChina, null), inputText, autocompletePrediction)).withInverseLayout().m4956id((CharSequence) autocompletePrediction.getTitle() + autocompletePrediction.getPlaceId()).addTo(SearchSuggestionsEpoxyController.this);
                }
                SearchSuggestionsEpoxyController.this.jitneyLogger.autocompleteLocationsImpression(SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            }
        }

        static /* synthetic */ void lambda$buildChinaAutocomplete$2(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteEntry, String inputText, LocationTypeaheadSuggestionItemForChina autocompletePrediction, View v) {
            SearchSuggestionsEpoxyController.this.jitneyLogger.clickAutocompleteLocation(autocompleteEntry, SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputText, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.AutoComplete, autocompletePrediction.getTextForSearch());
        }

        private void addHeader(int headerStringRes) {
            new MicroSectionHeaderEpoxyModel_().titleRes(headerStringRes).invertColors(true).m5170id((long) headerStringRes).addTo(SearchSuggestionsEpoxyController.this);
        }

        private void addAnywhereLocation() {
            new InputSuggestionActionRowEpoxyModel_().titleRes(C0857R.string.explore_anywhere).m4954id((long) C0857R.string.explore_anywhere).onClickListener(C6336x8ced9523.lambdaFactory$(this)).withInverseLayout().addTo(SearchSuggestionsEpoxyController.this);
        }

        private void addPopularLocations(List<TravelDestination> popularLocations, boolean arePopularLocationsPersonalized) {
            if (!ListUtils.isEmpty((Collection<?>) popularLocations)) {
                addHeader(arePopularLocationsPersonalized ? C0857R.string.popular_cluster_title_personalized : C0857R.string.popular_cluster_title);
                for (TravelDestination popularLocation : popularLocations) {
                    new InputSuggestionActionRowEpoxyModel_().title(popularLocation.getTitle()).onClickListener(C6337x8ced9524.lambdaFactory$(this, popularLocation)).withInverseLayout().m4956id((CharSequence) FindTweenAnalytics.SEARCH_TYPE_POPULAR_DESTINATION + popularLocation.getSearchParams().getLocation()).addTo(SearchSuggestionsEpoxyController.this);
                }
            }
        }

        private void addVerticalSubRows(Autocompletable entry, String inputQuery, AutocompleteSource source) {
            String locationShortName;
            boolean z = false;
            List<AutocompleteTerm> terms = entry.getTerms();
            String query = entry.getDescription();
            if (terms.size() > 1) {
                locationShortName = ((AutocompleteTerm) terms.get(0)).getValue();
            } else {
                locationShortName = query;
            }
            boolean hasPlaces = SearchSuggestionUtils.hasPlaces(entry);
            addVerticalOptionModel(Tab.HOME.getTabId(), C0857R.string.search_autocomplete_vertical_homes, inputQuery, source, entry, locationShortName, false);
            String tabId = Tab.EXPERIENCE.getTabId();
            int i = C0857R.string.search_autocomplete_vertical_experiences;
            if (!hasPlaces) {
                z = true;
            }
            addVerticalOptionModel(tabId, i, inputQuery, source, entry, locationShortName, z);
            if (hasPlaces) {
                addVerticalOptionModel(Tab.PLACES.getTabId(), C0857R.string.search_autocomplete_vertical_places, inputQuery, source, entry, locationShortName, true);
            }
        }

        private void addVerticalOptionModel(String verticalId, int verticalRes, String inputQuery, AutocompleteSource source, Autocompletable autocompletePrediction, String location, boolean lastSubrow) {
            AutocompleteData autocompleteEntry = SearchSuggestionsEpoxyController.this.addAutocompleteData(autocompletePrediction.getPlaceId(), inputQuery, source == AutocompleteSource.Satori ? AutocompleteSource.VerticalFromSatori : AutocompleteSource.VerticalFromGoogle, verticalId);
            Resources res = SearchSuggestionsEpoxyController.this.context.getResources();
            String vertical = res.getString(verticalRes);
            new InputSuggestionSubRowEpoxyModel_().title(res.getString(C0857R.string.search_autocomplete_vertical_entry, new Object[]{vertical, location})).boldText(vertical).onClickListener(C6338x8ced9525.lambdaFactory$(this, autocompleteEntry, inputQuery, autocompletePrediction, verticalId)).invertColors(true).lastSubRow(lastSubrow).m5953id((CharSequence) verticalId + autocompletePrediction.getPlaceId()).addTo(SearchSuggestionsEpoxyController.this);
        }

        static /* synthetic */ void lambda$addVerticalOptionModel$5(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteEntry, String inputQuery, Autocompletable autocompletePrediction, String verticalId, View v) {
            SearchSuggestionsEpoxyController.this.jitneyLogger.clickAutocompleteLocation(autocompleteEntry, SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputQuery, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.AutoComplete, autocompletePrediction.getDescription(), autocompletePrediction.getPlaceId(), verticalId);
        }

        private void addAutoCompleteEpoxyModel(Autocompletable input, String inputQuery, AutocompleteSource source, boolean showVerticals) {
            String title;
            String subtitle;
            List<AutocompleteTerm> terms = input.getTerms();
            String query = input.getDescription();
            boolean inVerticalsExperiment = FeatureToggles.showAutocompleteVerticalOptions();
            if (terms.size() <= 1) {
                title = query;
                subtitle = null;
            } else if (inVerticalsExperiment) {
                AutocompleteTerm secondTerm = (AutocompleteTerm) terms.get(1);
                title = query.substring(0, secondTerm.getOffset() + secondTerm.getValue().length());
                subtitle = null;
            } else {
                title = ((AutocompleteTerm) terms.get(0)).getValue();
                subtitle = query.substring(((AutocompleteTerm) terms.get(1)).getOffset());
            }
            InputSuggestionActionRowEpoxyModel_ model = new InputSuggestionActionRowEpoxyModel_().title(title).onClickListener(C6339x8ced9526.lambdaFactory$(this, SearchSuggestionsEpoxyController.this.addAutocompleteData(input.getPlaceId(), query, source, null), inputQuery, query, input, inVerticalsExperiment)).withInverseLayout().m4956id((CharSequence) "google_autocomplete" + input.getPlaceId());
            if (inVerticalsExperiment) {
                model.hasSubRows(showVerticals);
            } else {
                model.subtitle(subtitle).label(input.getLocationTag(SearchSuggestionsEpoxyController.this.context));
            }
            model.addTo(SearchSuggestionsEpoxyController.this);
        }

        static /* synthetic */ void lambda$addAutoCompleteEpoxyModel$6(GlobalSuggestionBuilder globalSuggestionBuilder, AutocompleteData autocompleteEntry, String inputQuery, String query, Autocompletable input, boolean inVerticalsExperiment, View v) {
            SearchSuggestionsEpoxyController.this.jitneyLogger.clickAutocompleteLocation(autocompleteEntry, SearchSuggestionsEpoxyController.this.getAutocompleteEntries(), inputQuery, SearchSuggestionsEpoxyController.this.searchSuggestionsController.getUserMarket(), SearchSuggestionsEpoxyController.this.searchSuggestionsController.getRequestLatency());
            SearchSuggestionsEpoxyController.this.handleSuggestionClicked(C5809SearchInputType.AutoComplete, query, input.getPlaceId(), inVerticalsExperiment ? Tab.ALL.getTabId() : null);
        }
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private interface SuggestionModelBuilder {
        void buildAutocomplete(String str, AutocompleteSource autocompleteSource);

        void buildAutocompleteAppend(String str, AutocompleteSource autocompleteSource);

        void buildChinaAutocomplete(String str);

        void buildRecentAndPopular();
    }

    public SearchSuggestionsEpoxyController(Context context2, int mode, ExploreLandingListener searchLandingListener2, SearchSuggestionsDataController suggestionsDataController, ExploreJitneyLogger jitneyLogger2) {
        this.searchLandingListener = searchLandingListener2;
        this.context = context2;
        this.searchSuggestionsController = suggestionsDataController;
        this.jitneyLogger = jitneyLogger2;
        switch (mode) {
            case 1:
                this.modelBuilder = new ChinaSuggestionBuilder();
                return;
            default:
                this.modelBuilder = new GlobalSuggestionBuilder();
                return;
        }
    }

    public Collection<AutocompleteData> getAutocompleteEntries() {
        return this.autocompleteEntries;
    }

    /* access modifiers changed from: protected */
    public void buildModels() {
        SuggestionsContentType contentType = this.searchSuggestionsController.getContentType();
        if (contentType != null) {
            String inputText = this.searchSuggestionsController.getInputText();
            switch (contentType) {
                case RecentSearchesAndPopular:
                    this.modelBuilder.buildRecentAndPopular();
                    return;
                case GoogleAutocomplete:
                case SatoriAutocomplete:
                    AutocompleteSource source = contentType == SuggestionsContentType.SatoriAutocomplete ? AutocompleteSource.Satori : AutocompleteSource.Google;
                    if (this.searchSuggestionsController.hasChinaLocalTypeaheadSuggestionItems()) {
                        this.modelBuilder.buildChinaAutocomplete(inputText);
                        this.modelBuilder.buildAutocompleteAppend(inputText, source);
                        return;
                    }
                    this.modelBuilder.buildAutocomplete(inputText, source);
                    return;
                case ChinaAutocomplete:
                    this.modelBuilder.buildChinaAutocomplete(inputText);
                    return;
                default:
                    return;
            }
        }
    }

    /* access modifiers changed from: private */
    public AutocompleteData addAutocompleteData(String placeId, String location, AutocompleteSource source, String filterValue) {
        AutocompleteData data = AutocompleteData.create(location, placeId, source.getName(), this.autocompleteEntries.size() + 1, filterValue);
        this.autocompleteEntries.add(data);
        return data;
    }

    /* access modifiers changed from: private */
    public boolean hasEntry(Autocompletable autocompletePrediction) {
        for (AutocompleteData entry : this.autocompleteEntries) {
            String placeId = entry.getPlaceId();
            if (!TextUtils.isEmpty(placeId) && placeId.contains(autocompletePrediction.getPlaceId())) {
                return true;
            }
        }
        return false;
    }

    private void handleSuggestionClicked(C5809SearchInputType searchInputType, String query, String autocompletePlaceId, SavedSearch savedSearch, String selectedVerticalId) {
        FindTweenAnalytics.trackOnSearch(searchInputType, query);
        this.searchLandingListener.onSearchSuggestionClicked(query, autocompletePlaceId, savedSearch, searchInputType, selectedVerticalId);
    }

    /* access modifiers changed from: private */
    public void handleSuggestionClicked(C5809SearchInputType searchInputType, String query, SavedSearch savedSearch) {
        handleSuggestionClicked(searchInputType, query, null, savedSearch, null);
    }

    private void handleSuggestionClicked(C5809SearchInputType searchInputType, String query, String autocompletePlaceId) {
        handleSuggestionClicked(searchInputType, query, autocompletePlaceId, null, null);
    }

    /* access modifiers changed from: private */
    public void handleSuggestionClicked(C5809SearchInputType searchInputType, String query, String autocompletePlaceId, String selectedVerticalId) {
        handleSuggestionClicked(searchInputType, query, autocompletePlaceId, null, selectedVerticalId);
    }

    /* access modifiers changed from: private */
    public void handleSuggestionClicked(C5809SearchInputType searchInputType, String query) {
        handleSuggestionClicked(searchInputType, query, null, null, null);
    }

    /* access modifiers changed from: private */
    public void handleSuggestionClicked(C5809SearchInputType searchInputType) {
        handleSuggestionClicked(searchInputType, null, null, null);
    }

    /* access modifiers changed from: private */
    public String getSavedSearchesSubtitle(SavedSearch search) {
        String formattedDate;
        Resources res = this.context.getResources();
        SearchParams searchParams = search.getSearchParams();
        if (searchParams.getCheckin() == null) {
            formattedDate = this.context.getResources().getString(C0857R.string.explore_anytime);
        } else {
            formattedDate = searchParams.getCheckin().getDateSpanString(this.context, searchParams.getCheckout());
        }
        int numGuests = searchParams.getGuests();
        String formattedGuests = res.getQuantityString(C0857R.plurals.x_guests, numGuests, new Object[]{Integer.valueOf(numGuests)});
        List<String> subtitleStringList = new ArrayList<>();
        if (!TextUtils.isEmpty(formattedDate)) {
            subtitleStringList.add(formattedDate);
        }
        if (numGuests != 0) {
            subtitleStringList.add(formattedGuests);
        }
        return TextUtils.join(res.getString(C0857R.string.room_type_comma), subtitleStringList);
    }

    /* access modifiers changed from: private */
    public String getDetailedSearchFiltersText(SavedSearch search) {
        int numOtherFilters = search.getSearchParams().getNumOtherFilters();
        if (numOtherFilters == 0) {
            return "";
        }
        return this.context.getResources().getQuantityString(C0857R.plurals.plus_x_filters, numOtherFilters, new Object[]{Integer.valueOf(numOtherFilters)});
    }
}
