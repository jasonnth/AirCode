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
import com.airbnb.android.core.requests.constants.ListingRequestConstants;
import com.airbnb.android.core.responses.SimpleListingResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.analytics.UnlistAnalytics;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import p032rx.Observer;

public class ManageListingUnlistOtherReasonFragment extends ManageListingBaseFragment {
    @BindView
    AirEditTextPageView editTextPage;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;
    final RequestListener<SimpleListingResponse> updateListingListener = new C0699RL().onResponse(ManageListingUnlistOtherReasonFragment$$Lambda$1.lambdaFactory$(this)).onError(ManageListingUnlistOtherReasonFragment$$Lambda$2.lambdaFactory$(this)).build();

    public static ManageListingUnlistOtherReasonFragment create() {
        return new ManageListingUnlistOtherReasonFragment();
    }

    static /* synthetic */ void lambda$new$0(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment, SimpleListingResponse response) {
        manageListingUnlistOtherReasonFragment.saveButton.setState(State.Success);
        manageListingUnlistOtherReasonFragment.controller.setListing(response.listing);
        manageListingUnlistOtherReasonFragment.controller.actionExecutor.popToFragment(ManageListingStatusSettingFragment.class);
    }

    static /* synthetic */ void lambda$new$1(ManageListingUnlistOtherReasonFragment manageListingUnlistOtherReasonFragment, AirRequestNetworkException e) {
        manageListingUnlistOtherReasonFragment.editTextPage.setEnabled(true);
        manageListingUnlistOtherReasonFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(manageListingUnlistOtherReasonFragment.getView(), e);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_setting_text, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(1);
        this.editTextPage.setTitle(C7368R.string.manage_listing_unlist_reason_other_reason_title);
        this.editTextPage.setHint(C7368R.string.manage_listing_unlist_reason_other_reason_hint);
        this.saveButton.setText(C7368R.string.manage_listing_unlist_reason_other_reason_button);
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void onResume() {
        super.onResume();
        this.editTextPage.requestFocusAndKeyboard();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.editTextPage.setEnabled(false);
        this.saveButton.setState(State.Loading);
        UnlistAnalytics.trackSubmitUnlistWithOtherReason(this.controller.getListing(), this.editTextPage.getText().toString());
        UpdateListingRequest.forField(this.controller.getListing().getId(), ListingRequestConstants.JSON_HAS_AVAILABILITY, Boolean.valueOf(false)).withListener((Observer) this.updateListingListener).execute(this.requestManager);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingUnlistOtherReason;
    }
}
