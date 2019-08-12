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
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingCancellationPolicyFragment extends ManageListingBaseFragment {
    private CancellationPolicyAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingCancellationPolicyFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingCancellationPolicyFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingCancellationPolicyFragment create() {
        return new ManageListingCancellationPolicyFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new CancellationPolicyAdapter(this.controller.getListing().getCancellationPolicyKey(), this.controller.getListing().getAvailableCancellationPolicies(), savedInstanceState);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.adapter.hasChanged(this.controller.getListing().getCancellationPolicyKey());
    }

    @OnClick
    public void onSave() {
        this.adapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_CANCELLATION_KEY, this.adapter.getCancellationPolicyName()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCancellationPolicy;
    }

    static /* synthetic */ void lambda$new$0(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment, SimpleListingResponse response) {
        manageListingCancellationPolicyFragment.controller.setListing(response.listing);
        manageListingCancellationPolicyFragment.saveButton.setState(State.Success);
        manageListingCancellationPolicyFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingCancellationPolicyFragment manageListingCancellationPolicyFragment, AirRequestNetworkException error) {
        manageListingCancellationPolicyFragment.adapter.setInputEnabled(true);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingCancellationPolicyFragment.getView(), error);
        manageListingCancellationPolicyFragment.saveButton.setState(State.Normal);
    }
}
