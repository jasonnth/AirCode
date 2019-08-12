package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.CurrenciesRequest;
import com.airbnb.android.core.responses.CurrenciesResponse;
import com.airbnb.android.listing.adapters.ListingCurrencyAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSCurrencyFragment extends LYSBaseFragment {
    private static final String ARG_CURRENT_CURRENCY = "arg_currency";
    private ListingCurrencyAdapter adapter;
    final RequestListener<CurrenciesResponse> currenciesListener = new C0699RL().onResponse(LYSCurrencyFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSCurrencyFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSCurrencyFragment newInstance(String currentCurrency) {
        return (LYSCurrencyFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSCurrencyFragment()).putString(ARG_CURRENT_CURRENCY, currentCurrency)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ListingCurrencyAdapter(getContext(), getArguments().getString(ARG_CURRENT_CURRENCY), false, savedInstanceState);
        makeCurrenciesRequest();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(C7251R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(v);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setAdapter(this.adapter);
        return v;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: private */
    public void makeCurrenciesRequest() {
        new CurrenciesRequest(getContext()).withListener((Observer) this.currenciesListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        handleOnSavePressed();
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(getArguments().getString(ARG_CURRENT_CURRENCY));
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSCurrency;
    }

    @OnClick
    public void handleOnSavePressed() {
        if (canSaveChanges()) {
            this.controller.setCurrencyCode(this.adapter.getCurrentCurrencyCode());
        }
        this.controller.popFragment();
    }
}
