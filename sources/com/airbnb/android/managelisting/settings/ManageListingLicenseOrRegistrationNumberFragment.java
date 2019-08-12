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
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.LicenseOrRegistrationNumberAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingLicenseOrRegistrationNumberFragment extends ManageListingBaseFragment {
    private LicenseOrRegistrationNumberAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingLicenseRequestListener = new C0699RL().onResponse(ManageListingLicenseOrRegistrationNumberFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingLicenseOrRegistrationNumberFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment, SimpleListingResponse response) {
        manageListingLicenseOrRegistrationNumberFragment.controller.setListing(response.listing);
        manageListingLicenseOrRegistrationNumberFragment.saveButton.setState(State.Success);
        manageListingLicenseOrRegistrationNumberFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingLicenseOrRegistrationNumberFragment manageListingLicenseOrRegistrationNumberFragment, AirRequestNetworkException error) {
        manageListingLicenseOrRegistrationNumberFragment.saveButton.setState(State.Normal);
        manageListingLicenseOrRegistrationNumberFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingLicenseOrRegistrationNumberFragment.recyclerView, error);
    }

    public static ManageListingLicenseOrRegistrationNumberFragment create() {
        return new ManageListingLicenseOrRegistrationNumberFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new LicenseOrRegistrationNumberAdapter(this.controller.getListing().getLicense(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing());
    }

    @OnClick
    public void onSave() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        String license = this.adapter.getLicense();
        this.adapter.setInputEnabled(false);
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forLicenseField(this.controller.getListing().getId(), license).withListener((Observer) this.updateListingLicenseRequestListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingLicenseOrRegistrationNumber;
    }
}
