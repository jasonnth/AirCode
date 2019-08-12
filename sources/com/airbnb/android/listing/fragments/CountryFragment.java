package com.airbnb.android.listing.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.activities.ModalActivity;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.Country;
import com.airbnb.android.core.requests.CountriesRequest;
import com.airbnb.android.core.responses.CountriesResponse;
import com.airbnb.android.listing.C7213R;
import com.airbnb.android.listing.adapters.ListingCountryAdapter;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.google.common.collect.FluentIterable;
import p032rx.Observer;

public class CountryFragment extends AirFragment {
    private static final String ARG_CURRENT_COUNTRY_CODE = "arg_current_country_code";
    public static final String EXTRA_COUNTRY = "country";
    public static final String EXTRA_NAVIGATION_TAG = "navigation_tag";
    private ListingCountryAdapter adapter;
    final RequestListener<CountriesResponse> countriesListener = new C0699RL().onResponse(CountryFragment$$Lambda$1.lambdaFactory$(this)).onError(CountryFragment$$Lambda$2.lambdaFactory$(this)).build();
    private String currentCountryCode;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static Intent createIntent(Context context, String currentCountryCode2, NavigationTag tag) {
        return ModalActivity.intentForFragment(context, create(currentCountryCode2, tag));
    }

    public static CountryFragment create(String currentCountryCode2, NavigationTag tag) {
        return (CountryFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CountryFragment()).putString(ARG_CURRENT_COUNTRY_CODE, currentCountryCode2)).putSerializable("navigation_tag", tag)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.currentCountryCode = getArguments().getString(ARG_CURRENT_COUNTRY_CODE);
        this.adapter = new ListingCountryAdapter(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7213R.layout.fragment_listing_recycler_view_with_save_and_x, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        if (savedInstanceState == null) {
            makeCountriesRequest();
        }
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: private */
    public void makeCountriesRequest() {
        CountriesRequest.forAllCountries().withListener((Observer) this.countriesListener).execute(this.requestManager);
    }

    @OnClick
    public void onSaveClicked() {
        Country currentCountry = this.adapter.getCurrentCountry();
        if (currentCountry == null) {
            getActivity().setResult(0);
        } else {
            getActivity().setResult(-1, new Intent().putExtra("country", currentCountry));
        }
        getActivity().finish();
        getFragmentManager().popBackStack();
    }

    public NavigationTag getNavigationTrackingTag() {
        return (NavigationTag) getArguments().getSerializable("navigation_tag");
    }

    static /* synthetic */ void lambda$new$1(CountryFragment countryFragment, CountriesResponse response) {
        countryFragment.adapter.setCurrentCountry((Country) FluentIterable.from((Iterable<E>) response.countries).filter(CountryFragment$$Lambda$4.lambdaFactory$(countryFragment)).first().orNull());
        countryFragment.adapter.setCountryOptions(response.countries);
    }
}
