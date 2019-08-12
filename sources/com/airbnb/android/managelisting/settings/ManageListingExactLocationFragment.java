package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
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
import com.airbnb.android.core.views.MovablePinMap;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingExactLocationFragment extends ManageListingBaseFragment {
    private static final int MAX_ZOOM = 18;
    private static final int MIN_ZOOM = 16;
    private static final double SCROLL_LIMIT = 0.005d;
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    MovablePinMap movablePinMap;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingExactLocationFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingExactLocationFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingExactLocationFragment manageListingExactLocationFragment, SimpleListingResponse response) {
        manageListingExactLocationFragment.controller.setListing(response.listing);
        manageListingExactLocationFragment.saveButton.setState(State.Success);
        manageListingExactLocationFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$1(ManageListingExactLocationFragment manageListingExactLocationFragment, AirRequestNetworkException error) {
        manageListingExactLocationFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingExactLocationFragment.getView(), error);
    }

    public static ManageListingExactLocationFragment create() {
        return new ManageListingExactLocationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_exact_location, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.documentMarquee.setTitle(C7368R.string.exact_location_title);
        this.documentMarquee.setCaption(C7368R.string.exact_location_description);
        this.movablePinMap.initialize(getChildFragmentManager(), this.controller.getListing().getAndroidLatLng(), 16, 18, SCROLL_LIMIT);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.movablePinMap.pinHasBeenMoved(this.controller.getListing().getAndroidLatLng());
    }

    @OnClick
    public void onSave() {
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forUserDefinedLatLng(this.controller.getListing().getId(), this.movablePinMap.getCurrentLocation()).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingExactLocation;
    }
}
