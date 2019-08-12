package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.views.MovablePinMap;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSExactLocationFragment extends LYSBaseFragment {
    @BindView
    MovablePinMap movablePinMap;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSExactLocationFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSExactLocationFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSExactLocationFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static Fragment newInstance() {
        return new LYSExactLocationFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSExactLocationFragment lYSExactLocationFragment, SimpleListingResponse response) {
        lYSExactLocationFragment.controller.setListing(response.listing);
        lYSExactLocationFragment.navigateInFlow(LYSStep.ExactLocation);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_exact_location, parent, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        this.movablePinMap.initialize(getChildFragmentManager(), this.controller.getListing().getAndroidLatLng());
        return view;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.ExactLocation);
        } else {
            updateLagLng();
        }
    }

    @OnClick
    public void onClickNext() {
        this.userAction = UserAction.GoToNext;
        if (!canSaveChanges()) {
            navigateInFlow(LYSStep.ExactLocation);
        } else {
            updateLagLng();
        }
    }

    private void updateLagLng() {
        setLoading(null);
        UpdateListingRequest.forUserDefinedLatLngLYS(this.controller.getListing().getId(), this.movablePinMap.getCurrentLocation(), this.controller.getMaxReachedStep().stepId).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.movablePinMap.pinHasBeenMoved(this.controller.getListing().getAndroidLatLng());
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSExactLocation;
    }
}
