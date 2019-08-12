package com.airbnb.android.listyourspacedls.fragments;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.enums.InstantBookingAllowedCategory;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.adapters.LYSGuestRequirementsAdapter;
import com.airbnb.android.listyourspacedls.adapters.LYSGuestRequirementsAdapter.Controller;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import p032rx.Observer;

public class LYSGuestRequirementsFragment extends LYSBaseFragment implements Controller {
    private LYSGuestRequirementsAdapter guestRequirementsAdapter;
    @BindView
    AirButton nextButton;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(LYSGuestRequirementsFragment$$Lambda$1.lambdaFactory$(this)).onError(LYSGuestRequirementsFragment$$Lambda$2.lambdaFactory$(this)).onComplete(LYSGuestRequirementsFragment$$Lambda$3.lambdaFactory$(this)).build();

    public static LYSGuestRequirementsFragment newInstance(boolean isStandalone) {
        return (LYSGuestRequirementsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new LYSGuestRequirementsFragment()).putBoolean("within_flow", isStandalone)).build();
    }

    static /* synthetic */ void lambda$new$2(LYSGuestRequirementsFragment lYSGuestRequirementsFragment, Boolean success) {
        lYSGuestRequirementsFragment.controller.showLoadingOverlay(false);
        if (success.booleanValue()) {
            lYSGuestRequirementsFragment.controller.showGuestAdditionalRequirements();
        }
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.guestRequirementsAdapter = new LYSGuestRequirementsAdapter(this, this.controller.getListing(), getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        if (this.comingFromBackstack) {
            this.guestRequirementsAdapter = new LYSGuestRequirementsAdapter(this, this.controller.getListing(), getContext());
        }
        this.recyclerView.setAdapter(this.guestRequirementsAdapter);
        return view;
    }

    public void dataUpdated() {
        this.guestRequirementsAdapter.requirementsUpdated(this.controller.getListing(), getContext());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.GuestRequirementsStep);
    }

    @OnClick
    public void clickNext() {
        this.userAction = UserAction.GoToNext;
        navigateInFlow(LYSStep.GuestRequirementsStep);
    }

    public void addAdditionalRequirements() {
        if (this.controller.isInstantBook()) {
            this.controller.showGuestAdditionalRequirements();
        } else {
            new Builder(getContext(), C7251R.C7256style.Theme_Airbnb_Dialog_Babu).setTitle(C7251R.string.lys_additional_requirements_turn_on_ib).setMessage(C7251R.string.lys_additional_requirements_turn_on_ib_description).setPositiveButton(C7251R.string.lys_additional_requirements_turn_on_ib_ok, LYSGuestRequirementsFragment$$Lambda$4.lambdaFactory$(this)).setNegativeButton(C7251R.string.lys_additional_requirements_turn_on_ib_cancel, LYSGuestRequirementsFragment$$Lambda$5.lambdaFactory$()).show();
        }
    }

    static /* synthetic */ void lambda$addAdditionalRequirements$3(LYSGuestRequirementsFragment lYSGuestRequirementsFragment, DialogInterface dialog, int which) {
        lYSGuestRequirementsFragment.controller.showLoadingOverlay(true);
        UpdateListingRequest.forFieldLYS(lYSGuestRequirementsFragment.controller.getListing().getId(), ListingRequestConstants.JSON_INSTANT_BOOK_VISIBILITY_KEY, InstantBookingAllowedCategory.EveryOne.serverKey).withListener((Observer) lYSGuestRequirementsFragment.updateListingListener).execute(lYSGuestRequirementsFragment.requestManager);
    }

    static /* synthetic */ void lambda$addAdditionalRequirements$4(DialogInterface dialog, int which) {
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.LYSGuestRequirements;
    }
}
