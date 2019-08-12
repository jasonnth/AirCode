package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.CurrenciesResponse;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.ListingCurrencyAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingCurrencyFragment extends ManageListingBaseFragment {
    private ListingCurrencyAdapter adapter;
    final RequestListener<CurrenciesResponse> currenciesListener = new C0699RL().onResponse(ManageListingCurrencyFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingCurrencyFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingCurrencyFragment$$Lambda$3.lambdaFactory$(this)).onError(ManageListingCurrencyFragment$$Lambda$4.lambdaFactory$(this)).build();

    public static ManageListingCurrencyFragment newInstance() {
        return new ManageListingCurrencyFragment();
    }

    static /* synthetic */ void lambda$new$3(ManageListingCurrencyFragment manageListingCurrencyFragment, SimpleListingResponse response) {
        manageListingCurrencyFragment.saveButton.setState(State.Success);
        manageListingCurrencyFragment.controller.setListing(response.listing);
        manageListingCurrencyFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$4(ManageListingCurrencyFragment manageListingCurrencyFragment, AirRequestNetworkException e) {
        NetworkUtil.tryShowErrorWithSnackbar(manageListingCurrencyFragment.getView(), e);
        manageListingCurrencyFragment.saveButton.setState(State.Normal);
        manageListingCurrencyFragment.adapter.setRowsEnabled(true);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ListingCurrencyAdapter(getContext(), this.controller.getListing().getListingNativeCurrency(), true, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        if (savedInstanceState == null) {
            makeCurrenciesRequest();
        }
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing().getListingNativeCurrency());
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setRowsEnabled(false);
        if (canSaveChanges()) {
            this.saveButton.setState(State.Loading);
            UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_CURRENCY_KEY, this.adapter.getCurrentCurrencyCode()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
            return;
        }
        this.saveButton.setState(State.Success);
        getFragmentManager().popBackStack();
    }

    /* access modifiers changed from: private */
    public void makeCurrenciesRequest() {
        new CurrenciesRequest(getContext()).withListener((Observer) this.currenciesListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCurrency;
    }
}
