package com.airbnb.android.listyourspacedls.fragments;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.BindView;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.arguments.P3Arguments;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.intents.ManageListingIntents;
import com.airbnb.android.core.intents.P3ActivityIntents;
import com.airbnb.android.core.requests.UpdateListingRequest;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.InputAdapter;
import com.airbnb.android.listing.views.TipView;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.airbnb.android.listyourspacedls.LYSDataController.UpdateListener;
import com.airbnb.android.listyourspacedls.utils.LYSLastSeenStepUtil;
import com.airbnb.android.utils.KeyboardUtils;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public abstract class LYSBaseFragment extends AirFragment implements UpdateListener {
    protected static final String ARG_STANDALONE_PAGE = "within_flow";
    @State
    boolean comingFromBackstack;
    protected LYSDataController controller;
    @BindView
    AirButton nextButton;
    @BindView
    AirButton previewButton;
    MenuItem saveAndExitMenuItem;
    protected boolean showSaveAndExit = true;
    @BindView
    TipView tipView;
    @State
    UserAction userAction;

    protected enum UserAction {
        GoToNext,
        SaveAndPrevious,
        SaveAndExit,
        UpdateOnScreen,
        Preview
    }

    /* access modifiers changed from: protected */
    public abstract boolean canSaveChanges();

    /* access modifiers changed from: protected */
    public abstract void onSaveActionPressed();

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.comingFromBackstack = false;
        getAirActivity().setOnBackPressedListener(LYSBaseFragment$$Lambda$1.lambdaFactory$(this));
        if (this.nextButton != null && isStandalonePage()) {
            this.nextButton.setText(C7251R.string.ml_save_changes_save);
            this.nextButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            this.nextButton.setCompoundDrawablePadding(0);
        }
        if (this.tipView != null) {
            this.tipView.initKeyboardHiding(getAirActivity(), view);
        }
    }

    public void onResume() {
        super.onResume();
        this.controller.addListener(this);
        if (this.previewButton != null) {
            this.previewButton.setState(AirButton.State.Normal);
        }
    }

    public void onPause() {
        super.onPause();
        this.controller.removeListener(this);
    }

    public void onDestroyView() {
        KeyboardUtils.dismissSoftKeyboard((Activity) getActivity());
        getAirActivity().setOnBackPressedListener(null);
        this.controller.showLoadingOverlay(false);
        this.controller.showOpaqueLoader(false);
        super.onDestroyView();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (!isStandalonePage() && this.showSaveAndExit && this.controller.isListingCreated()) {
            inflater.inflate(C7251R.C7254menu.fragment_save_and_exit, menu);
            this.saveAndExitMenuItem = menu.findItem(C7251R.C7253id.done);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7251R.C7253id.done) {
            return super.onOptionsItemSelected(item);
        }
        this.userAction = UserAction.SaveAndExit;
        onSaveActionPressed();
        return true;
    }

    public void dataUpdated() {
    }

    public void dataLoading(boolean loading) {
    }

    public void setController(LYSDataController controller2) {
        this.controller = controller2;
    }

    /* access modifiers changed from: protected */
    public void navigateInFlow(LYSStep currentStep) {
        switch (this.userAction) {
            case GoToNext:
                if (isStandalonePage()) {
                    this.controller.popFragment();
                    return;
                }
                this.comingFromBackstack = true;
                this.controller.nextStep(currentStep);
                return;
            case SaveAndPrevious:
                this.controller.popFragment();
                return;
            case SaveAndExit:
                this.controller.returnToLanding();
                return;
            case Preview:
                startActivity(P3ActivityIntents.withListing(getActivity(), this.controller.getListing(), P3Arguments.FROM_MANAGE_LISTING_OR_LYS));
                return;
            default:
                throw new UnhandledStateException(this.userAction);
        }
    }

    /* access modifiers changed from: protected */
    public void setLoading(InputAdapter adapter) {
        this.controller.setLoading(true);
        if (adapter != null) {
            adapter.setInputEnabled(false);
        }
        if (this.saveAndExitMenuItem != null) {
            this.saveAndExitMenuItem.setEnabled(false);
        }
        if (this.tipView != null) {
            this.tipView.setEnabled(false);
        }
        switch (this.userAction) {
            case GoToNext:
                ((AirButton) Check.notNull(this.nextButton)).setState(AirButton.State.Loading);
                if (this.previewButton != null) {
                    this.previewButton.setEnabled(false);
                    return;
                }
                return;
            case SaveAndPrevious:
            case SaveAndExit:
            case UpdateOnScreen:
                this.controller.showLoadingOverlay(true);
                return;
            case Preview:
                ((AirButton) Check.notNull(this.previewButton)).setState(AirButton.State.Loading);
                if (this.nextButton != null) {
                    this.nextButton.setEnabled(false);
                    return;
                }
                return;
            default:
                throw new UnhandledStateException(this.userAction);
        }
    }

    /* access modifiers changed from: protected */
    public void setLoadingFinished(boolean success, InputAdapter adapter) {
        this.controller.setLoading(false);
        if (adapter != null) {
            adapter.setInputEnabled(true);
        }
        if (this.saveAndExitMenuItem != null) {
            this.saveAndExitMenuItem.setEnabled(true);
        }
        if (this.tipView != null) {
            this.tipView.setEnabled(true);
        }
        switch (this.userAction) {
            case GoToNext:
                ((AirButton) Check.notNull(this.nextButton)).setState(success ? AirButton.State.Success : AirButton.State.Normal);
                if (this.previewButton != null) {
                    this.previewButton.setEnabled(true);
                    return;
                }
                return;
            case SaveAndPrevious:
            case SaveAndExit:
            case UpdateOnScreen:
                this.controller.showLoadingOverlay(false);
                return;
            case Preview:
                ((AirButton) Check.notNull(this.previewButton)).setState(success ? AirButton.State.Success : AirButton.State.Normal);
                if (this.nextButton != null) {
                    this.nextButton.setEnabled(true);
                    return;
                }
                return;
            default:
                throw new UnhandledStateException(this.userAction);
        }
    }

    /* access modifiers changed from: protected */
    public boolean onBackPressed() {
        if (!canSaveChanges()) {
            return false;
        }
        new Builder(getContext(), C7251R.C7256style.Theme_Airbnb_Dialog_Babu).setTitle(C7251R.string.listing_unsaved_changes_dialog_title).setMessage(C7251R.string.listing_unsaved_changes_dialog_message).setPositiveButton(C7251R.string.listing_unsaved_changes_dialog_confirm_button, LYSBaseFragment$$Lambda$2.lambdaFactory$(this)).setNegativeButton(C7251R.string.listing_unsaved_changes_dialog_save_button, LYSBaseFragment$$Lambda$3.lambdaFactory$(this)).show();
        return true;
    }

    static /* synthetic */ void lambda$onBackPressed$1(LYSBaseFragment lYSBaseFragment, DialogInterface dialog, int which) {
        lYSBaseFragment.userAction = UserAction.SaveAndPrevious;
        lYSBaseFragment.onSaveActionPressed();
    }

    /* access modifiers changed from: protected */
    public void onDiscard() {
        this.controller.popFragment();
    }

    /* access modifiers changed from: protected */
    public void updateLastStepId(LYSStep currentStep) {
        LYSStep maxReachedStep = this.controller.getMaxReachedStep();
        if (LYSLastSeenStepUtil.shouldUpdateLastFinishedStepId(maxReachedStep.stepId, this.controller.getListing().getListYourSpaceLastFinishedStepId())) {
            UpdateListingRequest.forStepIdLYS(this.controller.getListing().getId(), this.controller.getMaxReachedStep().stepId).execute(NetworkUtil.singleFireExecutor());
        }
        navigateInFlow(currentStep);
    }

    /* access modifiers changed from: protected */
    public void showTip(int textRes, OnClickListener clickListener) {
        Check.notNull(this.tipView);
        this.tipView.setVisibility(0);
        this.tipView.setTipTextRes(textRes);
        this.tipView.setTipClickListener(clickListener);
    }

    private boolean isStandalonePage() {
        Bundle args = getArguments();
        if (args == null || !args.getBoolean(ARG_STANDALONE_PAGE, false)) {
            return false;
        }
        return true;
    }

    public Strap getNavigationTrackingParams() {
        return Strap.make().mo11638kv("listing_id", this.controller.getListing().getId()).mo11639kv(ManageListingIntents.INTENT_EXTRA_SESSION_ID, this.controller.getSessionId());
    }
}
