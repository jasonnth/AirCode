package com.airbnb.android.explore.fragments;

import android.graphics.Rect;
import android.location.Location;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.text.Editable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina.LocationTypeaheadFilterForChinaOnCompleteListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.location.LocationClientFacade.Factory;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.requests.AutocompleteRequest;
import com.airbnb.android.core.requests.GetSavedSearchesRequest;
import com.airbnb.android.core.requests.TravelDestinationsRequest;
import com.airbnb.android.core.responses.GetSavedSearchesResponse;
import com.airbnb.android.core.responses.TravelDestinationsResponse;
import com.airbnb.android.core.utils.AppLaunchUtils;
import com.airbnb.android.core.utils.DebugSettings;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.android.core.utils.geocoder.GeocoderRequest;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResponse;
import com.airbnb.android.explore.C0857R;
import com.airbnb.android.explore.ExploreComponent.Builder;
import com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController;
import com.airbnb.android.explore.adapters.SearchSuggestionsEpoxyController.ExploreLandingListener;
import com.airbnb.android.explore.controllers.SearchSuggestionUtils;
import com.airbnb.android.explore.controllers.SearchSuggestionsDataController;
import com.airbnb.android.explore.controllers.SearchSuggestionsDataController.SuggestionsContentType;
import com.airbnb.android.explore.requests.SatoriAutocompleteRequest;
import com.airbnb.android.explore.requests.SatoriAutocompleteResponse;
import com.airbnb.android.lib.ExploreBindings;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.LanguageUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InputMarquee;
import com.airbnb.p027n2.components.jellyfish.JellyfishView;
import com.airbnb.p027n2.utils.ViewLibUtils;
import com.airbnb.rxgroups.RequestSubscription;
import com.google.common.base.Stopwatch;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

public class MTLocationFragment extends BaseExploreFragment implements LocationTypeaheadFilterForChinaOnCompleteListener, ExploreLandingListener {
    private static final int DELAY_AUTOCOMPLETE_MS = 400;
    private static final int MIN_AUTOCOMPLETE_CHARS_CJK = 1;
    private static final int MIN_AUTOCOMPLETE_CHARS_DEFAULT = 3;
    private static final int MIN_AUTOCOMPLETE_CHARS_SATORI = 1;
    private RequestSubscription autoCompleteRequest;
    private Runnable autoCompleteRunnable;
    final RequestListener<AutocompleteResponse> autocompleteRequestListener = new C0699RL().onResponse(MTLocationFragment$$Lambda$1.lambdaFactory$(this)).build();
    private SearchSuggestionsEpoxyController epoxyController;
    @BindView
    View inputDivider;
    @BindView
    InputMarquee inputMarquee;
    @BindView
    JellyfishView jellyfishView;
    /* access modifiers changed from: private */
    public LocationClientFacade locationClient;
    private final LocationClientCallbacks locationClientCallbacks = new LocationClientCallbacks() {
        public void onConnected() {
            if (MTLocationFragment.this.getActivity() != null) {
                MTLocationFragment.this.locationClient.requestLocationUpdates();
            } else {
                MTLocationFragment.this.locationClient.disconnectLocationClient();
            }
        }

        public void onLocationUpdated(Location location) {
            MTLocationFragment.this.locationClient.disconnectLocationClient();
            GeocoderRequest.getFromLocation(MTLocationFragment.this.getContext(), location).withListener(new SimpleRequestListener<GeocoderResponse>() {
                public void onResponse(GeocoderResponse response) {
                    MTLocationFragment.this.suggestionsDataController.setGpsResolvedLocation(response);
                    MTLocationFragment.this.updateAdapterLocationsAndSavedSearches();
                }
            }).execute(MTLocationFragment.this.requestManager);
        }
    };
    private LocationTypeaheadFilterForChina locationTypeaheadFilterForChina;
    private int minAutoCompleteChars = 3;
    private final SimpleTextWatcher onTextChangedListener = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            MTLocationFragment.this.onSearchQueryChanged(s.toString());
        }
    };
    final RequestListener<TravelDestinationsResponse> recommendedLocationsRequestListener = new C0699RL().onResponse(MTLocationFragment$$Lambda$4.lambdaFactory$(this)).build();
    private final Runnable requestFocusRunnable = new Runnable() {
        public void run() {
            MTLocationFragment.this.inputMarquee.requestFocus();
        }
    };
    private Stopwatch requestLatencyTimer;
    private MenuItem resetMenuItem;
    final RequestListener<SatoriAutocompleteResponse> satoriRequestListener = new C0699RL().onResponse(MTLocationFragment$$Lambda$2.lambdaFactory$(this)).build();
    final RequestListener<GetSavedSearchesResponse> savedSearchesRequestListener = new C0699RL().onResponse(MTLocationFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    RecyclerView searchOptionsList;
    private boolean serverResultsShown;
    /* access modifiers changed from: private */
    public final SearchSuggestionsDataController suggestionsDataController = new SearchSuggestionsDataController();
    @BindView
    AirToolbar toolbar;

    public static MTLocationFragment newInstance(Rect rect) {
        return (MTLocationFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTLocationFragment()).putParcelable("animate_rect", rect)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
        MTLocationFragmentPermissionsDispatcher.setupAndConnectLocationClientWithCheck(this);
        if (AppLaunchUtils.isUserInChina()) {
            this.locationTypeaheadFilterForChina = new LocationTypeaheadFilterForChina(getActivity(), this);
        }
        if (LanguageUtils.isUsingCJKLanguage()) {
            this.minAutoCompleteChars = 1;
        }
        this.requestLatencyTimer = Stopwatch.createUnstarted();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.locationClient != null) {
            this.locationClient.disconnectLocationClient();
        }
    }

    /* access modifiers changed from: 0000 */
    public void setupAndConnectLocationClient() {
        this.locationClient = Factory.get(getContext(), this.locationClientCallbacks);
        this.locationClient.connectLocationClient();
    }

    /* access modifiers changed from: 0000 */
    public void onLocationPermissionsDenied() {
        this.locationClient = Factory.get(getActivity(), this.locationClientCallbacks);
        this.suggestionsDataController.setHasLocationPermission(false);
        updateAdapterLocationsAndSavedSearches();
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions2, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions2, grantResults);
        MTLocationFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_mt_location, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.searchOptionsList.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    KeyboardUtils.dismissSoftKeyboard((View) MTLocationFragment.this.searchOptionsList);
                }
            }
        });
        fetchLandingPageRecommendations();
        OnEditorActionListener enterKeySearchListener = MTLocationFragment$$Lambda$5.lambdaFactory$(this);
        this.inputMarquee.invertColors(true);
        this.inputMarquee.requestFocus();
        this.inputMarquee.setShowKeyboardOnFocus(true);
        this.inputMarquee.setHint(C0857R.string.search_where_to);
        this.inputMarquee.setOnEditorActionListener(enterKeySearchListener);
        this.inputMarquee.addTextWatcher(this.onTextChangedListener);
        this.inputMarquee.setForSearch(true);
        ViewLibUtils.setVisibleIf(this.inputDivider, FeatureToggles.showAutocompleteVerticalOptions());
        this.inputMarquee.postDelayed(this.requestFocusRunnable, 400);
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(MTLocationFragment mTLocationFragment, TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        mTLocationFragment.onKeyboardEnterPressed(searchQuery);
        return true;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        String searchTerm;
        super.onActivityCreated(savedInstanceState);
        this.epoxyController = new SearchSuggestionsEpoxyController(getContext(), 0, this, this.suggestionsDataController, this.exploreJitneyLogger);
        this.searchOptionsList.setAdapter(this.epoxyController.getAdapter());
        if (this.dataController.getTopLevelSearchParams() == null) {
            searchTerm = null;
        } else {
            searchTerm = this.dataController.getTopLevelSearchParams().searchTerm();
        }
        this.inputMarquee.setText((CharSequence) searchTerm);
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0857R.C0860menu.reset_text, menu);
        this.resetMenuItem = menu.findItem(C0857R.C0859id.reset);
        this.resetMenuItem.setVisible(this.dataController.hasSearchTerm());
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C0857R.C0859id.reset) {
            return super.onOptionsItemSelected(item);
        }
        this.searchOptionsList.removeCallbacks(this.autoCompleteRunnable);
        if (this.autoCompleteRequest != null) {
            this.autoCompleteRequest.cancel();
            this.autoCompleteRequest = null;
        }
        this.inputMarquee.setText((CharSequence) null);
        updateAdapterLocationsAndSavedSearches();
        return true;
    }

    public void onDestroyView() {
        this.searchOptionsList.removeCallbacks(this.autoCompleteRunnable);
        this.inputMarquee.removeCallbacks(this.requestFocusRunnable);
        if (this.locationTypeaheadFilterForChina != null) {
            this.locationTypeaheadFilterForChina.disconnect();
        }
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void onSearchQueryChanged(String searchQuery) {
        int i = 1;
        if (isResumed()) {
            this.resetMenuItem.setVisible(!TextUtils.isEmpty(searchQuery));
            this.searchOptionsList.removeCallbacks(this.autoCompleteRunnable);
            if (TextUtils.isEmpty(searchQuery)) {
                updateAdapterLocationsAndSavedSearches();
                return;
            }
            if (this.locationTypeaheadFilterForChina != null) {
                this.suggestionsDataController.setChinaLocalTypeaheadSuggestionItems(null, this.inputMarquee.getText());
                this.locationTypeaheadFilterForChina.filter(searchQuery);
                if (!Experiments.enableAirbnbProxyForAutoComplete()) {
                    return;
                }
            }
            boolean useSatori = useSatoriAutocomplete();
            int length = searchQuery.length();
            if (!useSatori) {
                i = this.minAutoCompleteChars;
            }
            if (length >= i) {
                this.serverResultsShown = false;
                this.autoCompleteRunnable = MTLocationFragment$$Lambda$6.lambdaFactory$(this, useSatori, searchQuery);
                this.searchOptionsList.postDelayed(this.autoCompleteRunnable, 400);
            }
        }
    }

    static /* synthetic */ void lambda$onSearchQueryChanged$1(MTLocationFragment mTLocationFragment, boolean useSatori, String searchQuery) {
        if (useSatori) {
            mTLocationFragment.autoCompleteRequest = SatoriAutocompleteRequest.forSearch(searchQuery, mTLocationFragment.suggestionsDataController.getUserMarket()).withListener(mTLocationFragment.satoriRequestListener).execute(mTLocationFragment.requestManager);
        } else {
            mTLocationFragment.autoCompleteRequest = AutocompleteRequest.forGeocode(searchQuery, mTLocationFragment.getContext()).withListener(mTLocationFragment.autocompleteRequestListener).execute(mTLocationFragment.requestManager);
        }
        mTLocationFragment.requestLatencyTimer.reset();
        mTLocationFragment.requestLatencyTimer.start();
    }

    private boolean useSatoriAutocomplete() {
        if (!Trebuchet.launch(TrebuchetKeys.SATORI_AUTOCOMPLETE_FORCE)) {
            CoreApplication.instance().component().debugSettings();
            if (!DebugSettings.USE_SATORI.isEnabled() && (!SearchSuggestionUtils.useSatoriAutocomplete(this.suggestionsDataController.getUserMarket()) || !Experiments.useSatoriAutocomplete())) {
                return false;
            }
        }
        return true;
    }

    static /* synthetic */ void lambda$new$2(MTLocationFragment mTLocationFragment, AutocompleteResponse response) {
        if (mTLocationFragment.requestLatencyTimer.isRunning()) {
            mTLocationFragment.requestLatencyTimer.stop();
        }
        mTLocationFragment.serverResultsShown = true;
        mTLocationFragment.suggestionsDataController.setAutocompleteItems(response.getPredictions(), mTLocationFragment.inputMarquee.getText(), mTLocationFragment.requestLatencyTimer.elapsed(TimeUnit.MILLISECONDS));
        mTLocationFragment.epoxyController.requestModelBuild();
    }

    static /* synthetic */ void lambda$new$3(MTLocationFragment mTLocationFragment, SatoriAutocompleteResponse response) {
        if (mTLocationFragment.requestLatencyTimer.isRunning()) {
            mTLocationFragment.requestLatencyTimer.stop();
        }
        mTLocationFragment.serverResultsShown = true;
        mTLocationFragment.suggestionsDataController.setSatoriAutocompleteItems(response, mTLocationFragment.inputMarquee.getText(), mTLocationFragment.requestLatencyTimer.elapsed(TimeUnit.MILLISECONDS));
        mTLocationFragment.epoxyController.requestModelBuild();
    }

    public void onSearchSuggestionClicked(String queryString, String autocompletePlaceId, SavedSearch savedSearch, C5809SearchInputType inputType, String selectedVerticalId) {
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
        if (savedSearch != null) {
            submitSavedSearch(inputType, savedSearch);
        } else {
            submitSearchTerm(inputType, queryString, autocompletePlaceId, selectedVerticalId);
        }
    }

    private void submitSavedSearch(C5809SearchInputType inputType, SavedSearch savedSearch) {
        this.exploreNavigationController.closeModal();
        this.dataController.updateFromSavedSearch(inputType, savedSearch);
    }

    public void onKeyboardEnterPressed(String queryString) {
        submitSearchTerm(C5809SearchInputType.Manual, queryString, null, null);
    }

    private void submitSearchTerm(C5809SearchInputType inputType, String queryString, String autocompletePlaceId, String selectedVerticalId) {
        this.exploreNavigationController.closeModal();
        this.exploreJitneyLogger.selectLocation(queryString, this.dataController.getTopLevelSearchParams().searchTerm());
        this.dataController.setSearchTerm(inputType, queryString, autocompletePlaceId, selectedVerticalId);
        if (inputType == C5809SearchInputType.Manual) {
            this.exploreJitneyLogger.clickAutocompleteLocation(null, this.epoxyController.getAutocompleteEntries(), queryString, this.suggestionsDataController.getUserMarket(), this.suggestionsDataController.getRequestLatency());
        }
    }

    private void fetchLandingPageRecommendations() {
        new TravelDestinationsRequest().withListener((Observer) this.recommendedLocationsRequestListener).doubleResponse().execute(this.requestManager);
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            new GetSavedSearchesRequest().withListener((Observer) this.savedSearchesRequestListener).doubleResponse().execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$4(MTLocationFragment mTLocationFragment, GetSavedSearchesResponse response) {
        response.dedupeSearches();
        response.removeSearchesWithEmptyLocation();
        mTLocationFragment.suggestionsDataController.setSavedSearches(response.searches);
        mTLocationFragment.suggestionsDataController.setUserMarket(response.getUserMarket());
        if (!response.searches.isEmpty()) {
            mTLocationFragment.updateAdapterLocationsAndSavedSearches();
        }
    }

    static /* synthetic */ void lambda$new$5(MTLocationFragment mTLocationFragment, TravelDestinationsResponse response) {
        mTLocationFragment.suggestionsDataController.handleTravelDestinationsResponse(response);
        if (!response.destinations.isEmpty()) {
            mTLocationFragment.updateAdapterLocationsAndSavedSearches();
        }
    }

    /* access modifiers changed from: private */
    public void updateAdapterLocationsAndSavedSearches() {
        if (this.epoxyController != null) {
            this.suggestionsDataController.setContentType(SuggestionsContentType.RecentSearchesAndPopular);
            this.epoxyController.requestModelBuild();
        }
    }

    public void onChinaAutocompleteCompleted(List<LocationTypeaheadSuggestionItemForChina> results) {
        if (!this.serverResultsShown) {
            this.suggestionsDataController.setChinaLocalTypeaheadSuggestionItems(results, this.inputMarquee.getText());
            this.epoxyController.requestModelBuild();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Search;
    }
}
