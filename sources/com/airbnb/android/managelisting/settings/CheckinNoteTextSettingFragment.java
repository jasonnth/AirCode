package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.airrequest.AirRequestNetworkException;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInStep;
import com.airbnb.android.core.requests.CreateCheckInStepRequest;
import com.airbnb.android.core.requests.DeleteCheckInStepRequest;
import com.airbnb.android.core.requests.GetCheckInGuideRequest;
import com.airbnb.android.core.requests.UpdateCheckInStepRequest;
import com.airbnb.android.core.responses.CheckInGuideResponse;
import com.airbnb.android.core.responses.CheckInStepResponse;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.core.utils.listing.CheckinDisplay;
import com.airbnb.android.core.views.AirEditTextPageView;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.ManageListingGraph;
import com.airbnb.android.managelisting.analytics.CheckInJitneyLogger;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.android.utils.LocaleUtil;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;
import com.airbnb.p027n2.primitives.AirButton.State;
import com.google.common.base.Objects;
import p032rx.Observer;

public class CheckinNoteTextSettingFragment extends ManageListingBaseFragment {
    private static final String PARAM_STEP_ID = "step_id";
    private static final String PARAM_STEP_NUMBER = "step_number";
    final RequestListener<CheckInStepResponse> createStepListener = new C0699RL().onResponse(CheckinNoteTextSettingFragment$$Lambda$1.lambdaFactory$(this)).onError(CheckinNoteTextSettingFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    AirEditTextPageView editTextPage;
    CheckInJitneyLogger jitneyLogger;
    final RequestListener<CheckInGuideResponse> refreshGuideListener = new C0699RL().onResponse(CheckinNoteTextSettingFragment$$Lambda$5.lambdaFactory$(this)).onError(CheckinNoteTextSettingFragment$$Lambda$6.lambdaFactory$(this)).build();
    @BindView
    AirButton saveButton;
    private String startingNote;
    private CheckInStep startingStep;
    private long stepId;
    @BindView
    AirToolbar toolbar;
    final RequestListener<CheckInStepResponse> updateStepListener = new C0699RL().onResponse(CheckinNoteTextSettingFragment$$Lambda$3.lambdaFactory$(this)).onError(CheckinNoteTextSettingFragment$$Lambda$4.lambdaFactory$(this)).build();

    public static CheckinNoteTextSettingFragment forStep(int stepNum, long stepId2) {
        return (CheckinNoteTextSettingFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new CheckinNoteTextSettingFragment()).putInt(PARAM_STEP_NUMBER, stepNum)).putLong(PARAM_STEP_ID, stepId2)).build();
    }

    static /* synthetic */ void lambda$new$1(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment, AirRequestNetworkException e) {
        checkinNoteTextSettingFragment.editTextPage.setEnabled(true);
        checkinNoteTextSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(checkinNoteTextSettingFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$3(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment, AirRequestNetworkException e) {
        checkinNoteTextSettingFragment.editTextPage.setEnabled(true);
        checkinNoteTextSettingFragment.saveButton.setState(State.Normal);
        NetworkUtil.tryShowErrorWithSnackbar(checkinNoteTextSettingFragment.getView(), e);
    }

    static /* synthetic */ void lambda$new$4(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment, CheckInGuideResponse response) {
        checkinNoteTextSettingFragment.controller.setCheckInGuide(response.guide);
        checkinNoteTextSettingFragment.getFragmentManager().popBackStack();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((ManageListingGraph) CoreApplication.instance(getContext()).component()).inject(this);
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_setting_text, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        setHasOptionsMenu(true);
        int stepNum = getArguments().getInt(PARAM_STEP_NUMBER);
        this.stepId = getArguments().getLong(PARAM_STEP_ID);
        this.startingStep = this.controller.getCheckInStepById(this.stepId);
        this.startingNote = this.startingStep == null ? "" : this.startingStep.getNote();
        this.editTextPage.setTitle(CheckinDisplay.getCheckInEditNotePageTitleRes(this.startingNote));
        this.editTextPage.setHint(CheckinDisplay.getCheckInStepInstructions(stepNum));
        this.editTextPage.setListener(CheckinNoteTextSettingFragment$$Lambda$7.lambdaFactory$(this));
        this.editTextPage.setMinLength(1);
        if (savedInstanceState == null) {
            this.editTextPage.setText(this.startingNote);
        }
        updateSaveButton(this.editTextPage.isValid());
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.editTextPage.isEmpty()) {
            this.editTextPage.requestFocusAndKeyboard();
        }
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return !Objects.equal(getUserInput(), this.startingNote) && this.editTextPage.isValid();
    }

    /* access modifiers changed from: protected */
    @OnClick
    public void saveClicked() {
        this.editTextPage.setEnabled(false);
        if (!canSaveChanges()) {
            this.saveButton.setState(State.Success);
            getFragmentManager().popBackStack();
            return;
        }
        this.saveButton.setState(State.Loading);
        if (this.controller.getCheckInStepById(this.stepId) != null) {
            UpdateCheckInStepRequest.forNote(this.stepId, getUserInput()).withListener((Observer) this.updateStepListener).execute(this.requestManager);
            this.jitneyLogger.logCheckinGuideUpdateStepNoteEvent(this.stepId, getListingId());
            return;
        }
        CreateCheckInStepRequest.forNote(this.controller.getCheckInGuide().getId(), getUserInput()).withListener((Observer) this.createStepListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideCreateStepNoteEvent(getListingId());
    }

    /* access modifiers changed from: private */
    public void refreshGuide() {
        GetCheckInGuideRequest.forListingId(this.controller.getListingId(), LocaleUtil.getDeviceLanguage(getContext())).withListener((Observer) this.refreshGuideListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideFetchEvent(getListingId());
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (this.startingStep != null) {
            inflater.inflate(C7368R.C7371menu.checkin_edit_text_setting, menu);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() != C7368R.C7370id.menu_remove_setting) {
            return super.onOptionsItemSelected(item);
        }
        this.saveButton.setState(State.Loading);
        this.editTextPage.setEnabled(false);
        new DeleteCheckInStepRequest(this.stepId).withListener((Observer) this.updateStepListener).execute(this.requestManager);
        this.jitneyLogger.logCheckinGuideDeleteStepEvent(this.stepId, getListingId());
        return true;
    }

    private String getUserInput() {
        return this.editTextPage.getText().toString();
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingEditCheckinGuideNote;
    }

    /* access modifiers changed from: private */
    public void updateSaveButton(boolean enabled) {
        this.saveButton.setEnabled(enabled);
    }
}
