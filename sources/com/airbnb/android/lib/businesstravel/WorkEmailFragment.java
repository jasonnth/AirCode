package com.airbnb.android.lib.businesstravel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.p000v4.app.Fragment;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.airrequest.C0699RL;
import com.airbnb.airrequest.NetworkException;
import com.airbnb.airrequest.RequestListener;
import com.airbnb.android.core.analytics.BaseAnalytics;
import com.airbnb.android.core.businesstravel.BusinessTravelAccountManager;
import com.airbnb.android.core.businesstravel.BusinessTravelAnalytics;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.businesstravel.models.BusinessTravelEmployee;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.ZenDialog;
import com.airbnb.android.core.intents.BusinessTravelIntents;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.core.utils.ErrorUtils;
import com.airbnb.android.core.utils.NetworkUtil;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.businesstravel.network.AddWorkEmailRequest;
import com.airbnb.android.lib.businesstravel.network.AddWorkEmailResponse;
import com.airbnb.android.lib.businesstravel.network.RemoveWorkEmailRequest;
import com.airbnb.android.utils.EmailUtils;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.components.fixed_footers.FixedActionFooter;
import com.airbnb.p027n2.components.fixed_footers.FixedDualActionFooter;
import icepick.State;
import p032rx.Observer;

public class WorkEmailFragment extends AirFragment {
    private static final String ARG_WORK_EMAIL = "arg_work_email";
    private static final int REQUEST_CODE_CANCEL = 0;
    private static final int REQUEST_CODE_REMOVE_EMAIL = 371;
    @BindView
    FixedActionFooter addWorkEmailButton;
    final RequestListener<AddWorkEmailResponse> addWorkEmailListener = new C0699RL().onResponse(WorkEmailFragment$$Lambda$1.lambdaFactory$(this)).onError(WorkEmailFragment$$Lambda$2.lambdaFactory$(this)).build();
    @BindView
    FixedDualActionFooter bottomActionBar;
    BusinessTravelAccountManager businessTravelAccountManager;
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @State
    String confirmationCode;
    @State
    String email;
    @State
    WorkEmailLaunchSource launchSource;
    @BindView
    RecyclerView recyclerView;
    final RequestListener<Object> removeWorkEmailListener = new C0699RL().onResponse(WorkEmailFragment$$Lambda$3.lambdaFactory$(this)).onError(WorkEmailFragment$$Lambda$4.lambdaFactory$(this)).build();
    @BindView
    AirToolbar toolbar;
    private WorkEmailDataController workEmailDataController;
    private WorkEmailEpoxyController workEmailEpoxyController;
    private WorkEmailListener workEmailListener;

    public interface WorkEmailListener {
        void doneWithAddWorkEmail(BusinessTravelEmployee businessTravelEmployee);
    }

    public static WorkEmailFragment instanceForEmail(String email2) {
        return (WorkEmailFragment) ((FragmentBundleBuilder) FragmentBundler.make(new WorkEmailFragment()).putString(ARG_WORK_EMAIL, email2)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
        if (savedInstanceState == null) {
            this.email = getArguments().getString(ARG_WORK_EMAIL);
            this.launchSource = this.workEmailDataController.getLaunchSource();
            this.confirmationCode = this.workEmailDataController.getReservationConfirmationCode();
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_work_email, container, false);
        ButterKnife.bind(this, view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationOnClickListener(WorkEmailFragment$$Lambda$5.lambdaFactory$(this));
        this.workEmailEpoxyController = new WorkEmailEpoxyController(this.email, this.businessTravelAccountManager.getWorkEmailStatus());
        this.workEmailEpoxyController.requestModelBuild();
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setAdapter(this.workEmailEpoxyController.getAdapter());
        configureButtons();
        this.businessTravelJitneyLogger.logWorkEmailInputFocus(this.launchSource);
        return view;
    }

    static /* synthetic */ void lambda$onCreateView$4(WorkEmailFragment workEmailFragment, View v) {
        workEmailFragment.trackModalClose();
        workEmailFragment.getActivity().onBackPressed();
    }

    private void configureButtons() {
        switch (this.businessTravelAccountManager.getWorkEmailStatus()) {
            case None:
                this.addWorkEmailButton.setVisibility(0);
                this.bottomActionBar.setVisibility(8);
                this.addWorkEmailButton.setButtonOnClickListener(WorkEmailFragment$$Lambda$6.lambdaFactory$(this));
                return;
            case Pending:
            case Verified:
                this.addWorkEmailButton.setVisibility(8);
                this.bottomActionBar.setVisibility(0);
                this.bottomActionBar.setSecondaryButtonText(C0880R.string.bt_remove_work_email_button_text);
                this.bottomActionBar.setSecondaryButtonOnClickListener(WorkEmailFragment$$Lambda$7.lambdaFactory$(this));
                this.bottomActionBar.setButtonText(C0880R.string.bt_add_work_email_button_text);
                this.bottomActionBar.setButtonOnClickListener(WorkEmailFragment$$Lambda$8.lambdaFactory$(this));
                return;
            default:
                return;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.state(context instanceof WorkEmailListener, "activity must implement WorkEmailListener");
        Check.state(context instanceof WorkEmailDataController, "activity must implement WorkEmailDataController");
        this.workEmailListener = (WorkEmailListener) context;
        this.workEmailDataController = (WorkEmailDataController) context;
    }

    public void onDetach() {
        this.workEmailListener = null;
        this.workEmailDataController = null;
        super.onDetach();
    }

    /* access modifiers changed from: private */
    public void addWorkEmail() {
        this.email = this.workEmailEpoxyController.getEmail();
        if (EmailUtils.isValidEmail(this.email)) {
            logAddWorkEmail();
            setWorkEmailButtonLoading(true);
            setWorkEmailButtonEnabled(false);
            AddWorkEmailRequest.forWorkEmail(this.mAccountManager.getCurrentUserId(), this.email).withListener((Observer) this.addWorkEmailListener).execute(this.requestManager);
            return;
        }
        ErrorUtils.showErrorUsingSnackbar(getView(), C0880R.string.bt_work_email_invalid_error, C0880R.string.bt_work_email_invalid_error_text);
    }

    /* access modifiers changed from: private */
    public void showRemoveWorkEmailDialog() {
        logRemoveWorkEmail("click");
        ZenDialog.builder().withTitle(C0880R.string.bt_remove_work_email_warning_title_text).withBodyText(C0880R.string.bt_remove_work_email_warning_body_text).withDualButton(C0880R.string.cancel, 0, C0880R.string.remove, (int) REQUEST_CODE_REMOVE_EMAIL, (Fragment) this).create().show(getFragmentManager(), getClass().getCanonicalName());
    }

    private void removeWorkEmail() {
        RemoveWorkEmailRequest.forBTEmployeeId(this.businessTravelAccountManager.getBusinessTravelEmployee().getId()).withListener((Observer) this.removeWorkEmailListener).execute(this.requestManager);
        setWorkEmailButtonEnabled(false);
        setRemoveWorkEmailButtonLoading(true);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != -1) {
            super.onActivityResult(requestCode, resultCode, data);
        } else if (requestCode == 0) {
            logRemoveWorkEmail(BaseAnalytics.CANCEL);
        } else if (requestCode == REQUEST_CODE_REMOVE_EMAIL) {
            logRemoveWorkEmail("confirm");
            removeWorkEmail();
        }
    }

    public void onAddWorkEmailError(NetworkException error) {
        logAddWorkEmailError(error);
        onNetworkError(error);
    }

    /* access modifiers changed from: private */
    public void onRemoveWorkEmailSuccess() {
        logRemoveWorkEmail("sent");
        getActivity().setResult(-1, getUpdateWorkEmailResult());
        getActivity().finish();
    }

    /* access modifiers changed from: private */
    public void onRemoveWorkEmailError(NetworkException error) {
        logRemoveWorkEmailError(error);
        onNetworkError(error);
    }

    private void onNetworkError(NetworkException error) {
        setWorkEmailButtonLoading(false);
        setRemoveWorkEmailButtonLoading(false);
        setWorkEmailButtonEnabled(true);
        ErrorUtils.showErrorUsingSnackbar(getView(), NetworkUtil.errorMessage(error));
    }

    public void onAddWorkEmailSuccess(AddWorkEmailResponse response) {
        logAddWorkEmailSuccess();
        setWorkEmailButtonLoading(false);
        this.businessTravelAccountManager.setBusinessTravelEmployee(response.businessTravelEmployee);
        getActivity().setResult(-1, getUpdateWorkEmailResult());
        this.workEmailListener.doneWithAddWorkEmail(this.businessTravelAccountManager.getBusinessTravelEmployee());
    }

    private void logAddWorkEmailSuccess() {
        BusinessTravelAnalytics.trackAddWorkEmailEvent(BaseAnalytics.SUBMIT, "add_work_email_success", BusinessTravelAnalytics.additionalParams().user(this.mAccountManager).email(this.email).create());
        this.businessTravelJitneyLogger.logUpdateWorkEmailSuccess(this.launchSource, this.email);
    }

    private void logAddWorkEmailError(NetworkException error) {
        BusinessTravelAnalytics.trackAddWorkEmailEvent("error", "add_work_email_error", BusinessTravelAnalytics.additionalParams().user(this.mAccountManager).email(this.email).errorCode(NetworkUtil.errorCode(error).intValue()).create());
        this.businessTravelJitneyLogger.logUpdateWorkEmailError(this.launchSource, NetworkUtil.errorDetails(error), this.email);
    }

    private void logRemoveWorkEmail(String operation) {
        BusinessTravelAnalytics.trackRemoveWorkEmailEvent(operation, BusinessTravelAnalytics.additionalParams().email(this.email).create());
    }

    private void logRemoveWorkEmailError(NetworkException error) {
        BusinessTravelAnalytics.trackRemoveWorkEmailEvent("error", BusinessTravelAnalytics.additionalParams().errorMessage(NetworkUtil.errorMessage(error)).create());
    }

    private void trackModalClose() {
        BusinessTravelAnalytics.trackAddWorkEmailEvent("click", "close_button", BusinessTravelAnalytics.additionalParams().user(this.mAccountManager).create());
        this.businessTravelJitneyLogger.logExitAddWorkEmail(this.launchSource);
    }

    private void logAddWorkEmail() {
        BusinessTravelAnalytics.trackAddWorkEmailEvent("click", "add_button", BusinessTravelAnalytics.additionalParams().user(this.mAccountManager).email(this.email).create());
        this.businessTravelJitneyLogger.logClickAddWorkEmail(this.launchSource, this.email);
    }

    private void setWorkEmailButtonLoading(boolean isLoading) {
        switch (this.businessTravelAccountManager.getWorkEmailStatus()) {
            case None:
                this.addWorkEmailButton.setButtonLoading(isLoading);
                return;
            case Pending:
            case Verified:
                this.bottomActionBar.setButtonLoading(isLoading);
                return;
            default:
                return;
        }
    }

    private void setRemoveWorkEmailButtonLoading(boolean isLoading) {
        this.bottomActionBar.setSecondaryButtonLoading(isLoading);
    }

    private void setWorkEmailButtonEnabled(boolean isEnabled) {
        switch (this.businessTravelAccountManager.getWorkEmailStatus()) {
            case None:
                this.addWorkEmailButton.setButtonEnabled(isEnabled);
                return;
            case Pending:
            case Verified:
                this.bottomActionBar.setButtonEnabled(isEnabled);
                this.bottomActionBar.setSecondaryButtonEnabled(isEnabled);
                return;
            default:
                return;
        }
    }

    private Intent getUpdateWorkEmailResult() {
        Intent data = new Intent();
        data.putExtra(BusinessTravelIntents.RESULT_UPDATE_WORK_EMAIL, true);
        return data;
    }
}
