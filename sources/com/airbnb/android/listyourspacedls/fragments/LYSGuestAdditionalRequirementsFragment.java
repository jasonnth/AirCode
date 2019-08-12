package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.adapters.GuestAdditionalRequirementsAdapter;
import com.airbnb.android.listing.utils.AdditionalRequirementsHelper;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.erf.Experiments;
import com.airbnb.p027n2.collections.VerboseScrollView;
import com.airbnb.p027n2.components.AirToolbar;
import p032rx.Observer;

public class LYSGuestAdditionalRequirementsFragment extends LYSBaseFragment {
    private GuestAdditionalRequirementsAdapter additionalRequirementsAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    VerboseScrollView scrollView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSGuestAdditionalRequirementsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSGuestAdditionalRequirementsFragment$$Lambda$4.lambdaFactory$(this)).onComplete(LYSGuestAdditionalRequirementsFragment$$Lambda$5.lambdaFactory$(this)).build();

    public static Fragment newInstance() {
        return new LYSGuestAdditionalRequirementsFragment();
    }

    static /* synthetic */ void lambda$new$0(LYSGuestAdditionalRequirementsFragment lYSGuestAdditionalRequirementsFragment, SimpleListingResponse response) {
        lYSGuestAdditionalRequirementsFragment.controller.setListing(response.listing);
        lYSGuestAdditionalRequirementsFragment.controller.popFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.additionalRequirementsAdapter = new GuestAdditionalRequirementsAdapter(this.controller.getListing(), savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_guest_additional_requirements, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (this.comingFromBackstack) {
            this.additionalRequirementsAdapter = new GuestAdditionalRequirementsAdapter(this.controller.getListing(), null);
        }
        this.recyclerView.setAdapter(this.additionalRequirementsAdapter);
        showTip(Experiments.showUhpAdditionalRequirementsTip() ? C7251R.string.lys_additional_requirements_tip_uhp : C7251R.string.lys_additional_requirements_tip, null);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.additionalRequirementsAdapter.onSaveInstanceState(outState);
    }

    @OnClick
    public void clickSave() {
        this.userAction = UserAction.GoToNext;
        checkChangesAndUpdate(canSaveChanges());
    }

    private void updateAdditionalGuestRequirements() {
        setLoading(this.additionalRequirementsAdapter);
        UpdateListingRequest.forFieldLYS(this.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, this.additionalRequirementsAdapter.getInstantBookingAllowedCategory().serverKey).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return AdditionalRequirementsHelper.getInstantBookingAllowedCategory(this.controller.getListing()) != this.additionalRequirementsAdapter.getInstantBookingAllowedCategory();
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        checkChangesAndUpdate(canSaveChanges());
    }

    private void checkChangesAndUpdate(boolean hasChanges) {
        if (hasChanges) {
            updateAdditionalGuestRequirements();
        } else {
            this.controller.popFragment();
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSGuestAdditionalRequirements;
    }
}
