package com.airbnb.android.cohosting.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingManagementJitneyLogger;
import com.airbnb.android.core.events.RemoveCohostEvent;
import com.airbnb.android.core.intents.CohostingIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.ListingManager;
import com.airbnb.android.core.requests.RemoveCohostRequest;
import com.airbnb.android.core.responses.RemoveCohostResponse;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.erf.Experiments;
import com.airbnb.jitney.event.logging.CohostingContext.p062v1.C1951CohostingContext;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.components.SimpleTextRow;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirTextView;
import com.airbnb.p027n2.utils.SnackbarWrapper;
import icepick.State;
import p032rx.Observer;

public class RemoveCohostFragment extends CohostManagementBaseFragment implements OnBackListener {
    private static final int REQUEST_CODE_REMOVE_REASONS = 42;
    @BindView
    AirButton confirmButton;
    @BindView
    SimpleTextRow currentHostingExplanation;
    @BindView
    AirTextView explanationText;
    @State
    ListingManager listingManager;
    CohostingManagementJitneyLogger logger;
    final RequestListener<RemoveCohostResponse> removeCohostListener = new C0699RL().onResponse(RemoveCohostFragment$$Lambda$1.lambdaFactory$(this)).onError(RemoveCohostFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    SimpleTextRow responsiblityText;
    @BindView
    DocumentMarquee titleMarquee;
    @BindView
    AirToolbar toolbar;

    public static RemoveCohostFragment create(String listManagerId) {
        return (RemoveCohostFragment) ((FragmentBundleBuilder) FragmentBundler.make(new RemoveCohostFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, listManagerId)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        boolean z;
        ((CohostingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_remove_cohost, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        if (savedInstanceState == null) {
            this.listingManager = this.controller.getListingManager(getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD));
        }
        if (this.listingManager != null) {
            z = true;
        } else {
            z = false;
        }
        Check.state(z, "Listing manager can not be null");
        setupTexts();
        view.setBackgroundColor(-1);
        C1951CohostingContext loggingContext = this.controller.getCohostingContext();
        if (this.controller.isCurrentUserListingAdmin()) {
            this.logger.logRemoveCohostModalImpression(loggingContext, this.listingManager);
        } else {
            this.logger.logCohostResignModalImpression(loggingContext, this.listingManager);
        }
        this.toolbar.setNavigationOnClickListener(RemoveCohostFragment$$Lambda$3.lambdaFactory$(this));
        if (Experiments.showRemoveFlow()) {
            this.confirmButton.setText(C5658R.string.cohosting_reasons_next_button_text);
        }
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$2(RemoveCohostFragment removeCohostFragment, View v) {
        removeCohostFragment.onBackPressed();
        removeCohostFragment.getFragmentManager().popBackStack();
    }

    @OnClick
    public void removeCohost() {
        Intent intentForRemoveSelfReasons;
        this.logger.logRemoveCohostButtonClicked(this.controller.getCohostingContext(), this.listingManager);
        if (Experiments.showRemoveFlow()) {
            if (this.controller.isCurrentUserListingAdmin()) {
                intentForRemoveSelfReasons = CohostingIntents.intentForRemoveCohostReasons(getContext(), this.listingManager, this.controller.getListing());
            } else {
                intentForRemoveSelfReasons = CohostingIntents.intentForRemoveSelfReasons(getContext(), this.controller.getListingAdmin(), this.controller.getListing());
            }
            startActivityForResult(intentForRemoveSelfReasons, 42);
            return;
        }
        this.confirmButton.setState(AirButton.State.Loading);
        new RemoveCohostRequest(this.listingManager.getId()).withListener((Observer) this.removeCohostListener).execute(this.requestManager);
    }

    private void setupTexts() {
        String explanation;
        String responsibilityText;
        boolean isListingAdmin = this.controller.isCurrentUserListingAdmin();
        String name = isListingAdmin ? this.listingManager.getUser().getFirstName() : getString(C5658R.string.yourself_text);
        this.titleMarquee.setTitle((CharSequence) getString(C5658R.string.cohosting_remove_cohost_title, name));
        String listingName = this.controller.getListing().getName();
        String listingLocation = this.controller.getListing().getLocation(getContext());
        if (isListingAdmin) {
            explanation = getString(C5658R.string.cohosting_remove_cohost_explanation, name);
        } else {
            explanation = getString(C5658R.string.cohosting_cohost_remove_self_explanation, listingName, listingLocation);
        }
        this.explanationText.setText(explanation);
        if (isListingAdmin) {
            responsibilityText = getString(C5658R.string.cohosting_remove_cohost_responsibility, name);
        } else {
            responsibilityText = getString(C5658R.string.cohosting_cohost_how_to_add_back_after_remove);
        }
        this.responsiblityText.setText((CharSequence) responsibilityText);
        this.responsiblityText.showDivider(isListingAdmin);
        this.currentHostingExplanation.setVisibility(isListingAdmin ? 0 : 8);
        if (isListingAdmin) {
            this.currentHostingExplanation.setText((CharSequence) getString(C5658R.string.cohosting_remove_cohost_explanation_if_currently_hosting, name));
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public boolean onBackPressed() {
        this.logger.logRemoveCohostModalDismissed(this.controller.getCohostingContext(), this.listingManager);
        return super.onBackPressed();
    }

    /* access modifiers changed from: private */
    public void finishRemoval() {
        this.confirmButton.setState(AirButton.State.Success);
        this.controller.removeCohost(this.listingManager);
        if (this.controller.isCurrentUserListingAdmin()) {
            getFragmentManager().popBackStack();
            return;
        }
        this.mBus.post(new RemoveCohostEvent());
        getActivity().setResult(-1);
        getActivity().finish();
    }

    private void showConfirmationToast() {
        new SnackbarWrapper().view(getView()).body(getConfirmationText()).duration(0).buildAndShow();
    }

    private String getConfirmationText() {
        if (this.controller.isCurrentUserListingAdmin()) {
            return getString(C5658R.string.cohosting_remove_confirmation_text, this.listingManager.getUser().getFirstName(), this.controller.getListing().getName());
        }
        return getString(C5658R.string.cohosting_remove_self_confirmation_text, this.controller.getListing().getName());
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == -1 && requestCode == 42) {
            showConfirmationToast();
            finishRemoval();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
