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
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingFeesFragment extends ManageListingBaseFragment {
    private ManageListingFeesAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingFeesFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingFeesFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingFeesFragment create() {
        return new ManageListingFeesFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingFeesFragment manageListingFeesFragment, SimpleListingResponse response) {
        manageListingFeesFragment.saveButton.setState(State.Success);
        manageListingFeesFragment.controller.setListing(response.listing);
        manageListingFeesFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingFeesFragment manageListingFeesFragment, AirRequestNetworkException e) {
        manageListingFeesFragment.adapter.setEnabled(true);
        manageListingFeesFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingFeesFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingFeesAdapter(getContext(), this.controller.getListing(), savedInstanceState);
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
        return this.adapter.hasChanged();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.adapter.setEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forFields(this.controller.getListing().getId(), this.adapter.getFeeSettings()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingFees;
    }
}
