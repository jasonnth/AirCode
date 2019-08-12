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

public class ManageListingCheckInOutFragment extends ManageListingBaseFragment {
    private ManageListingCheckInOutAdapter adapter;
    final RequestListener<SimpleListingResponse> listingUpdateListener = new C0699RL().onResponse(ManageListingCheckInOutFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingCheckInOutFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    public static ManageListingCheckInOutFragment create() {
        return new ManageListingCheckInOutFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingCheckInOutFragment manageListingCheckInOutFragment, SimpleListingResponse response) {
        manageListingCheckInOutFragment.saveButton.setState(State.Success);
        manageListingCheckInOutFragment.controller.setListing(response.listing);
        manageListingCheckInOutFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingCheckInOutFragment manageListingCheckInOutFragment, AirRequestNetworkException e) {
        manageListingCheckInOutFragment.adapter.setEnabled(true);
        manageListingCheckInOutFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingCheckInOutFragment.getView(), e);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new ManageListingCheckInOutAdapter(getContext(), this.controller.getListing(), this.controller.getCheckInTimeOptions(), savedInstanceState, this.controller.getCheckInInformation());
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
        UpdateListingRequest.forFields(this.controller.getListing().getId(), this.adapter.getCheckInSettings()).withListener((Observer) this.listingUpdateListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCheckInOut;
    }
}
