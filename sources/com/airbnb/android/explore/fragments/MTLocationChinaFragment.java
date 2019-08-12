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
import butterknife.BindView;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.airrequest.SimpleRequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.adapters.find.C5809SearchInputType;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina;
import com.airbnb.android.core.filters.LocationTypeaheadFilterForChina.LocationTypeaheadFilterForChinaOnCompleteListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.location.LocationClientFacade;
import com.airbnb.android.core.location.LocationClientFacade.Factory;
import com.airbnb.android.core.location.LocationClientFacade.LocationClientCallbacks;
import com.airbnb.android.core.models.LocationTypeaheadSuggestionItemForChina;
import com.airbnb.android.core.models.PopularDestinationResponse;
import com.airbnb.android.core.models.SavedSearch;
import com.airbnb.android.core.requests.AutocompleteRequest;
import com.airbnb.android.core.requests.GetSavedSearchesRequest;
import com.airbnb.android.core.requests.PopularDestinationsChinaRequest;
import com.airbnb.android.core.responses.GetSavedSearchesResponse;
import com.airbnb.android.core.utils.geocoder.GeocoderRequest;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResponse;
import com.airbnb.android.core.viewcomponents.models.InputSuggestionActionRowChinaEpoxyModel;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
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
import com.airbnb.android.utils.ListUtils;
import com.airbnb.android.utils.SimpleTextWatcher;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirEditTextView;
import com.airbnb.rxgroups.RequestSubscription;
import com.google.common.base.Stopwatch;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;
import p032rx.Observer;

public class MTLocationChinaFragment extends BaseExploreFragment implements LocationTypeaheadFilterForChinaOnCompleteListener, ExploreLandingListener {
    private static final int DELAY_AUTOCOMPLETE_MS = 400;
    private static final int MIN_AUTOCOMPLETE_CHARS_CJK = 1;
    private static final int MIN_AUTOCOMPLETE_CHARS_DEFAULT = 3;
    private RequestSubscription autoCompleteRequest;
    private Runnable autoCompleteRunnable;
    public final RequestListener<AutocompleteResponse> autocompleteRequestListener = new C0699RL().onResponse(MTLocationChinaFragment$$Lambda$1.lambdaFactory$(this)).onError(MTLocationChinaFragment$$Lambda$2.lambdaFactory$(this)).build();
    private SearchSuggestionsEpoxyController epoxyController;
    /* access modifiers changed from: private */
    public boolean isAutocompleteLoading;
    private final LoadingRowEpoxyModel_ loadingRowEpoxyModel = new LoadingRowEpoxyModel_();
    /* access modifiers changed from: private */
    public LocationClientFacade locationClient;
    private final LocationClientCallbacks locationClientCallbacks = new LocationClientCallbacks() {
        public void onConnected() {
            if (MTLocationChinaFragment.this.getActivity() != null) {
                MTLocationChinaFragment.this.locationClient.requestLocationUpdates();
            } else {
                MTLocationChinaFragment.this.locationClient.disconnectLocationClient();
            }
        }

        public void onLocationUpdated(Location location) {
            MTLocationChinaFragment.this.locationClient.disconnectLocationClient();
            GeocoderRequest.getFromLocation(MTLocationChinaFragment.this.getContext(), location).withListener(new SimpleRequestListener<GeocoderResponse>() {
                public void onResponse(GeocoderResponse response) {
                    MTLocationChinaFragment.this.suggestionsDataController.setGpsResolvedLocation(response);
                    MTLocationChinaFragment.this.updateAdapterLocationsAndSavedSearches();
                }
            }).execute(MTLocationChinaFragment.this.requestManager);
        }
    };
    private LocationTypeaheadFilterForChina locationTypeaheadFilterForChina;
    private int minAutoCompleteChars = 3;
    private final SimpleTextWatcher onTextChangedListener = new SimpleTextWatcher() {
        public void afterTextChanged(Editable s) {
            MTLocationChinaFragment.this.onSearchQueryChanged(s.toString());
        }
    };
    public final RequestListener<PopularDestinationResponse> popularDestinationRequestListener = new C0699RL().onResponse(MTLocationChinaFragment$$Lambda$6.lambdaFactory$(this)).build();
    private final Runnable requestFocusRunnable = new Runnable() {
        public void run() {
            MTLocationChinaFragment.this.searchEditText.requestFocus();
            KeyboardUtils.showSoftKeyboard(MTLocationChinaFragment.this.searchEditText);
        }
    };
    private Stopwatch requestLatencyTimer;
    private MenuItem resetMenuItem;
    public final RequestListener<SatoriAutocompleteResponse> satoriRequestListener = new C0699RL().onResponse(MTLocationChinaFragment$$Lambda$3.lambdaFactory$(this)).onError(MTLocationChinaFragment$$Lambda$4.lambdaFactory$(this)).build();
    public final RequestListener<GetSavedSearchesResponse> savedSearchesRequestListener = new C0699RL().onResponse(MTLocationChinaFragment$$Lambda$5.lambdaFactory$(this)).build();
    @BindView
    AirEditTextView searchEditText;
    @BindView
    RecyclerView searchOptionsList;
    private boolean serverResultsShown;
    /* access modifiers changed from: private */
    public final SearchSuggestionsDataController suggestionsDataController = new SearchSuggestionsDataController();
    @BindView
    AirToolbar toolbar;

    public static MTLocationChinaFragment newInstance(Rect rect) {
        return (MTLocationChinaFragment) ((FragmentBundleBuilder) FragmentBundler.make(new MTLocationChinaFragment()).putParcelable("animate_rect", rect)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((Builder) ((ExploreBindings) CoreApplication.instance(getContext()).componentProvider()).exploreComponentProvider().get()).build().inject(this);
        MTLocationChinaFragmentPermissionsDispatcher.setupAndConnectLocationClientWithCheck(this);
        this.locationTypeaheadFilterForChina = new LocationTypeaheadFilterForChina(getActivity(), this);
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
        MTLocationChinaFragmentPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0857R.layout.fragment_mt_location_china, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.searchOptionsList.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == 1) {
                    KeyboardUtils.dismissSoftKeyboard((View) MTLocationChinaFragment.this.searchOptionsList);
                }
            }
        });
        fetchPopularDestinations();
        fetchSavedSearches();
        this.searchEditText.setOnEditorActionListener(MTLocationChinaFragment$$Lambda$7.lambdaFactory$(this));
        this.searchEditText.setImeOptions(3);
        this.searchEditText.addTextChangedListener(this.onTextChangedListener);
        this.searchEditText.postDelayed(this.requestFocusRunnable, 400);
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$0(MTLocationChinaFragment mTLocationChinaFragment, TextView v, int actionId, KeyEvent event) {
        String searchQuery = v.getText().toString().trim();
        if (!KeyboardUtils.isEnterOrSearch(actionId, event)) {
            return false;
        }
        KeyboardUtils.dismissSoftKeyboard((View) v);
        mTLocationChinaFragment.onKeyboardEnterPressed(searchQuery);
        return true;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        String searchTerm;
        super.onActivityCreated(savedInstanceState);
        this.epoxyController = new SearchSuggestionsEpoxyController(getContext(), 1, this, this.suggestionsDataController, this.exploreJitneyLogger);
        this.epoxyController.addInterceptor(MTLocationChinaFragment$$Lambda$8.lambdaFactory$(this));
        this.searchOptionsList.setAdapter(this.epoxyController.getAdapter());
        if (this.dataController.getTopLevelSearchParams() == null) {
            searchTerm = null;
        } else {
            searchTerm = this.dataController.getTopLevelSearchParams().searchTerm();
        }
        this.searchEditText.setText(searchTerm);
        this.searchEditText.setSelection(searchTerm == null ? 0 : searchTerm.length());
    }

    static /* synthetic */ void lambda$onActivityCreated$1(MTLocationChinaFragment mTLocationChinaFragment, List models) {
        boolean isAutocomplete = false;
        if (ListUtils.isEmpty((Collection<?>) models) || (models.get(0) instanceof InputSuggestionActionRowChinaEpoxyModel)) {
            isAutocomplete = true;
        }
        if (mTLocationChinaFragment.isAutocompleteLoading && isAutocomplete) {
            models.add(mTLocationChinaFragment.loadingRowEpoxyModel);
        }
    }

    public void onPause() {
        super.onPause();
        KeyboardUtils.hideSoftKeyboardForCurrentlyFocusedView(getActivity());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(C0857R.C0860menu.reset_text_china, menu);
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
        this.searchEditText.setText(null);
        updateAdapterLocationsAndSavedSearches();
        return true;
    }

    public void onDestroyView() {
        this.searchOptionsList.removeCallbacks(this.autoCompleteRunnable);
        this.searchEditText.removeCallbacks(this.requestFocusRunnable);
        super.onDestroyView();
    }

    /* access modifiers changed from: private */
    public void onSearchQueryChanged(String searchQuery) {
        if (isResumed()) {
            this.resetMenuItem.setVisible(!TextUtils.isEmpty(searchQuery));
            this.searchOptionsList.removeCallbacks(this.autoCompleteRunnable);
            if (TextUtils.isEmpty(searchQuery)) {
                updateAdapterLocationsAndSavedSearches();
                return;
            }
            this.suggestionsDataController.setChinaLocalTypeaheadSuggestionItems(null, this.searchEditText.getText().toString());
            this.locationTypeaheadFilterForChina.filter(searchQuery);
            if (searchQuery.length() >= this.minAutoCompleteChars) {
                this.isAutocompleteLoading = true;
                this.serverResultsShown = false;
                this.autoCompleteRunnable = MTLocationChinaFragment$$Lambda$9.lambdaFactory$(this, searchQuery);
                this.searchOptionsList.postDelayed(this.autoCompleteRunnable, 400);
            }
        }
    }

    static /* synthetic */ void lambda$onSearchQueryChanged$2(MTLocationChinaFragment mTLocationChinaFragment, String searchQuery) {
        if (SearchSuggestionUtils.useSatoriAutocomplete(null)) {
            mTLocationChinaFragment.autoCompleteRequest = SatoriAutocompleteRequest.forSearch(searchQuery, null).withListener(mTLocationChinaFragment.satoriRequestListener).execute(mTLocationChinaFragment.requestManager);
        } else {
            mTLocationChinaFragment.autoCompleteRequest = AutocompleteRequest.forGeocode(searchQuery, mTLocationChinaFragment.getContext()).withListener(mTLocationChinaFragment.autocompleteRequestListener).execute(mTLocationChinaFragment.requestManager);
        }
        mTLocationChinaFragment.requestLatencyTimer.reset();
        mTLocationChinaFragment.requestLatencyTimer.start();
    }

    static /* synthetic */ void lambda$new$3(MTLocationChinaFragment mTLocationChinaFragment, AutocompleteResponse response) {
        mTLocationChinaFragment.isAutocompleteLoading = false;
        mTLocationChinaFragment.requestLatencyTimer.stop();
        mTLocationChinaFragment.serverResultsShown = true;
        mTLocationChinaFragment.suggestionsDataController.setAutocompleteItems(response.getPredictions(), mTLocationChinaFragment.searchEditText.getText().toString(), mTLocationChinaFragment.requestLatencyTimer.elapsed(TimeUnit.MILLISECONDS));
        mTLocationChinaFragment.epoxyController.requestModelBuild();
    }

    static /* synthetic */ void lambda$new$5(MTLocationChinaFragment mTLocationChinaFragment, SatoriAutocompleteResponse response) {
        mTLocationChinaFragment.isAutocompleteLoading = false;
        mTLocationChinaFragment.requestLatencyTimer.stop();
        mTLocationChinaFragment.serverResultsShown = true;
        mTLocationChinaFragment.suggestionsDataController.setSatoriAutocompleteItems(response, mTLocationChinaFragment.searchEditText.getText().toString(), mTLocationChinaFragment.requestLatencyTimer.elapsed(TimeUnit.MILLISECONDS));
        mTLocationChinaFragment.epoxyController.requestModelBuild();
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

    private void fetchPopularDestinations() {
        new PopularDestinationsChinaRequest().withListener((Observer) this.popularDestinationRequestListener).doubleResponse().execute(this.requestManager);
    }

    private void fetchSavedSearches() {
        if (this.mAccountManager.isCurrentUserAuthorized()) {
            new GetSavedSearchesRequest().withListener((Observer) this.savedSearchesRequestListener).doubleResponse().execute(this.requestManager);
        }
    }

    static /* synthetic */ void lambda$new$7(MTLocationChinaFragment mTLocationChinaFragment, GetSavedSearchesResponse response) {
        response.dedupeSearches();
        response.removeSearchesWithEmptyLocation();
        mTLocationChinaFragment.suggestionsDataController.setSavedSearches(response.searches);
        if (!response.searches.isEmpty()) {
            mTLocationChinaFragment.updateAdapterLocationsAndSavedSearches();
        }
    }

    static /* synthetic */ void lambda$new$8(MTLocationChinaFragment mTLocationChinaFragment, PopularDestinationResponse response) {
        if (response != null && !ListUtils.isEmpty((Collection<?>) response.getHotDestinations())) {
            mTLocationChinaFragment.suggestionsDataController.setPopularDestinationsChina(response.getHotDestinations());
            mTLocationChinaFragment.updateAdapterLocationsAndSavedSearches();
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
            this.suggestionsDataController.setChinaLocalTypeaheadSuggestionItems(results, this.searchEditText.getText().toString());
            this.epoxyController.requestModelBuild();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Search;
    }
}
