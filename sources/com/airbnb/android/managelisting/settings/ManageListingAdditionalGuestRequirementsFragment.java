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
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.adapters.GuestAdditionalRequirementsAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingAdditionalGuestRequirementsFragment extends ManageListingBaseFragment {
    private GuestAdditionalRequirementsAdapter additionalRequirementsAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    public final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingAdditionalGuestRequirementsFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingAdditionalGuestRequirementsFragment$$Lambda$2.lambdaFactory$(this)).build();

    static /* synthetic */ void lambda$new$0(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment, SimpleListingResponse response) {
        manageListingAdditionalGuestRequirementsFragment.saveButton.setState(State.Success);
        manageListingAdditionalGuestRequirementsFragment.controller.setListing(response.listing);
        manageListingAdditionalGuestRequirementsFragment.getFragmentManager().popBackStack();
    }

    static /* synthetic */ void lambda$new$2(ManageListingAdditionalGuestRequirementsFragment manageListingAdditionalGuestRequirementsFragment, AirRequestNetworkException e) {
        manageListingAdditionalGuestRequirementsFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowRetryableErrorWithSnackbar(manageListingAdditionalGuestRequirementsFragment.getView(), (NetworkException) e, ManageListingAdditionalGuestRequirementsFragment$$Lambda$3.lambdaFactory$(manageListingAdditionalGuestRequirementsFragment));
        manageListingAdditionalGuestRequirementsFragment.additionalRequirementsAdapter.setInputEnabled(true);
    }

    public static ManageListingAdditionalGuestRequirementsFragment create() {
        return (ManageListingAdditionalGuestRequirementsFragment) FragmentBundler.make(new ManageListingAdditionalGuestRequirementsFragment()).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.additionalRequirementsAdapter = new GuestAdditionalRequirementsAdapter(this.controller.getListing(), savedInstanceState);
        this.recyclerView.setAdapter(this.additionalRequirementsAdapter);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingGuestAdditionalRequirements;
    }

    @OnClick
    public void onSave() {
        this.additionalRequirementsAdapter.setInputEnabled(false);
        if (!canSaveChanges()) {
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, this.additionalRequirementsAdapter.getInstantBookingAllowedCategory().serverKey).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return this.additionalRequirementsAdapter.hasChanged(this.controller.getListing());
    }
}
