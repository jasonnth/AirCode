package com.airbnb.android.listing.fragments;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.support.p002v7.widget.RecyclerView.OnScrollListener;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import butterknife.BindView;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.AirAddress;
import com.airbnb.android.core.requests.AutocompleteRequest;
import com.airbnb.android.core.requests.PlaceDetailsRequest;
import com.airbnb.android.core.utils.SanitizeUtils;
import com.airbnb.android.core.utils.geocoder.models.AutocompletePrediction;
import com.airbnb.android.core.utils.geocoder.models.AutocompleteResponse;
import com.airbnb.android.core.utils.geocoder.models.GeocoderResult;
import com.airbnb.android.core.utils.geocoder.models.PlaceDetailsResponse;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.ListingGraph;
import com.airbnb.android.listing.adapters.AddressAutoCompleteAdapter;
import com.airbnb.android.listing.adapters.AddressAutoCompleteAdapter.Listener;
import com.airbnb.android.listing.logging.LYSAddressAutoCompleteLogger;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.InlineInputRow;
import com.airbnb.p027n2.utils.ObjectAnimatorFactory;

public class AddressAutoCompleteFragment extends AirFragment {
    public static final String ARG_COUNTRY_CODE = "country_code";
    public static final String ARG_STREET = "street";
    private static final int AUTOCOMPLETE_MIN_CHAR = 1;
    public static final String EXTRA_ADDRESS = "address";
    public static final String EXTRA_AUTOCOMPLETE_CITY_ONLY = "autocomplete_city_only";
    public static final String EXTRA_LISTING_ID = "listing_id";
    public static final String EXTRA_NAVIGATION_TAG = "navigation_tag";
    public static final String EXTRA_STREET = "street";
    private AddressAutoCompleteAdapter adapter;
    @BindView
    InlineInputRow addressInput;
    private Runnable autoCompleteRunnable;
    final RequestListener<AutocompleteResponse> autocompleteRequestListener = new C0699RL().onResponse(AddressAutoCompleteFragment$$Lambda$1.lambdaFactory$(this)).build();
    private String countryCode;
    public final Listener listener = AddressAutoCompleteFragment$$Lambda$4.lambdaFactory$(this);
    private long listingId;
    @BindView
    View loadingOverlay;
    LYSAddressAutoCompleteLogger lysAddressAutoCompleteLogger;
    final RequestListener<PlaceDetailsResponse> placeDetailsRequestListener = new C0699RL().onResponse(AddressAutoCompleteFragment$$Lambda$2.lambdaFactory$(this)).onError(AddressAutoCompleteFragment$$Lambda$3.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    private String street;
    @BindView
    AirToolbar toolbar;

    public static class Builder {
        private boolean cityOnly;
        private final Context context;
        private String countryCode;
        private long listingId;
        private final NavigationTag navigationTag;
        private String street;

        public Builder(Context context2, NavigationTag navigationTag2) {
            this.context = context2;
            this.navigationTag = navigationTag2;
        }

        public Builder setCountryAndStreet(AirAddress address) {
            return setCountryAndStreet(address.countryCode(), address.streetAddressOne());
        }

        public Builder setCountryAndStreet(String countryCode2, String street2) {
            this.countryCode = countryCode2;
            this.street = street2;
            return this;
        }

        public Builder setCityOnly() {
            this.cityOnly = true;
            return this;
        }

        public Builder setListingId(long listingId2) {
            this.listingId = listingId2;
            return this;
        }

        public Intent build() {
            return ModalActivity.intentForFragment(this.context, (AddressAutoCompleteFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new AddressAutoCompleteFragment()).putString("country_code", this.countryCode)).putString("street", this.street)).putSerializable("navigation_tag", this.navigationTag)).putBoolean(AddressAutoCompleteFragment.EXTRA_AUTOCOMPLETE_CITY_ONLY, this.cityOnly)).putLong("listing_id", this.listingId)).build());
        }
    }

    static /* synthetic */ void lambda$new$1(AddressAutoCompleteFragment addressAutoCompleteFragment, PlaceDetailsResponse response) {
        addressAutoCompleteFragment.showLoadingOverlay(false);
        GeocoderResult result = response.getResult();
        if (result == null) {
            addressAutoCompleteFragment.submitSearchQuery(addressAutoCompleteFragment.addressInput.getInputText());
            return;
        }
        addressAutoCompleteFragment.getActivity().setResult(-1, new Intent().putExtra("address", result.toAirAddress(addressAutoCompleteFragment.getContext())));
        addressAutoCompleteFragment.getActivity().finish();
    }

    static /* synthetic */ void lambda$new$2(AddressAutoCompleteFragment addressAutoCompleteFragment, AirRequestNetworkException error) {
        addressAutoCompleteFragment.showLoadingOverlay(false);
        addressAutoCompleteFragment.submitSearchQuery(addressAutoCompleteFragment.addressInput.getInputText());
    }

    static /* synthetic */ void lambda$new$3(AddressAutoCompleteFragment addressAutoCompleteFragment, AutocompletePrediction autocompletePrediction) {
        addressAutoCompleteFragment.showLoadingOverlay(true);
        if (addressAutoCompleteFragment.getNavigationTrackingTag() == NavigationTag.LYSLocationAddressAutoComplete) {
            addressAutoCompleteFragment.lysAddressAutoCompleteLogger.logSelectAutocompleteAddress(Long.valueOf(addressAutoCompleteFragment.listingId));
        }
        PlaceDetailsRequest.forPlaceId(addressAutoCompleteFragment.getContext(), autocompletePrediction.getPlaceId()).withListener(addressAutoCompleteFragment.placeDetailsRequestListener).execute(addressAutoCompleteFragment.requestManager);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new AddressAutoCompleteAdapter(this.listener);
        this.countryCode = getArguments().getString("country_code");
        this.street = getArguments().getString("street");
        this.listingId = getArguments().getLong("listing_id");
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_address_auto_complete, container, false);
        bindViews(view);
        ((ListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 1) {
                    KeyboardUtils.dismissSoftKeyboard((View) recyclerView);
                }
            }
        });
        OnEditorActionListener onEditorActionListener = AddressAutoCompleteFragment$$Lambda$5.lambdaFactory$(this);
        this.addressInput.requestFocus();
        this.addressInput.setInputText((CharSequence) SanitizeUtils.emptyIfNull(this.street));
        this.addressInput.setOnInputChangedListener(AddressAutoCompleteFragment$$Lambda$6.lambdaFactory$(this));
        this.addressInput.setOnEditorActionListener(onEditorActionListener);
        this.addressInput.setDoneAction();
        this.addressInput.setTitle(isCityOnly() ? C7213R.string.city : C7213R.string.street);
        return view;
    }

    static /* synthetic */ boolean lambda$onCreateView$4(AddressAutoCompleteFragment addressAutoCompleteFragment, TextView textView, int actionId, KeyEvent keyEvent) {
        if (!KeyboardUtils.isDoneAction(actionId)) {
            return false;
        }
        addressAutoCompleteFragment.submitSearchQuery(addressAutoCompleteFragment.addressInput.getInputText());
        return true;
    }

    private void submitSearchQuery(String searchQuery) {
        getActivity().setResult(-1, new Intent().putExtra("street", searchQuery));
        getActivity().finish();
    }

    public void onDestroyView() {
        this.recyclerView.removeCallbacks(this.autoCompleteRunnable);
        super.onDestroyView();
    }

    public NavigationTag getNavigationTrackingTag() {
        return (NavigationTag) getArguments().getSerializable("navigation_tag");
    }

    /* access modifiers changed from: private */
    public void onSearchQueryChanged(String searchQuery) {
        AutocompleteRequest request;
        this.recyclerView.removeCallbacks(this.autoCompleteRunnable);
        if (TextUtils.isEmpty(searchQuery)) {
            this.adapter.clearModels();
        } else if (searchQuery.length() >= 1) {
            if (isCityOnly()) {
                request = AutocompleteRequest.forCity(searchQuery, this.countryCode, getContext());
            } else {
                request = AutocompleteRequest.forCountryCode(searchQuery, this.countryCode, getContext());
            }
            this.autoCompleteRunnable = AddressAutoCompleteFragment$$Lambda$7.lambdaFactory$(this, request);
            this.recyclerView.post(this.autoCompleteRunnable);
        }
    }

    private void showLoadingOverlay(boolean show) {
        ObjectAnimatorFactory.fade(this.loadingOverlay, show).onStartStep(AddressAutoCompleteFragment$$Lambda$8.lambdaFactory$(this)).onEndStep(AddressAutoCompleteFragment$$Lambda$9.lambdaFactory$(this, show)).setDuration(150).buildAndStart();
    }

    static /* synthetic */ void lambda$showLoadingOverlay$6(AddressAutoCompleteFragment addressAutoCompleteFragment, Animator animator) {
        if (addressAutoCompleteFragment.loadingOverlay != null) {
            addressAutoCompleteFragment.loadingOverlay.setVisibility(0);
        }
    }

    static /* synthetic */ void lambda$showLoadingOverlay$7(AddressAutoCompleteFragment addressAutoCompleteFragment, boolean show, Animator animator) {
        if (addressAutoCompleteFragment.loadingOverlay != null) {
            addressAutoCompleteFragment.loadingOverlay.setVisibility(show ? 0 : 8);
        }
    }

    private boolean isCityOnly() {
        return getArguments().getBoolean(EXTRA_AUTOCOMPLETE_CITY_ONLY, false);
    }
}
