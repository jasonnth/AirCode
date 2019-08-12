package com.airbnb.android.lib.businesstravel;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.businesstravel.BusinessTravelJitneyLogger;
import com.airbnb.android.core.businesstravel.WorkEmailLaunchSource;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.lib.AirbnbApplication;
import com.airbnb.android.lib.AirbnbGraph;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.DocumentMarquee;
import com.airbnb.p027n2.primitives.AirButton;
import icepick.State;

public class VerifyWorkEmailFragment extends AirFragment {
    private static final String ARG_WORK_EMAIL = "arg_work_email";
    private static final String ARG_WORK_EMAIL_VERIFICATION_STATUS = "arg_work_email_verification_status";
    BusinessTravelJitneyLogger businessTravelJitneyLogger;
    @BindView
    DocumentMarquee documentMarquee;
    @BindView
    AirButton gotItButton;
    @BindView
    AirButton resendEmailButton;
    private VerifyWorkEmailListener verifyWorkEmailListener;
    @State
    String workEmail;
    private WorkEmailDataController workEmailDataController;
    @State
    WorkEmailLaunchSource workEmailLaunchSource;
    @State
    VerificationStatus workEmailStatus;

    public enum VerificationStatus {
        NOT_VERIFIED("not_verified"),
        PENDING_VERIFICATION("pending_verification");
        
        private final String serverKey;

        private VerificationStatus(String serverKey2) {
            this.serverKey = serverKey2;
        }

        public String getServerKey() {
            return this.serverKey;
        }
    }

    public interface VerifyWorkEmailListener {
        void goToAddWorkEmail();
    }

    public static VerifyWorkEmailFragment forWorkEmailStatus(String email, VerificationStatus status) {
        return (VerifyWorkEmailFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new VerifyWorkEmailFragment()).putString(ARG_WORK_EMAIL, email)).putSerializable(ARG_WORK_EMAIL_VERIFICATION_STATUS, status)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AirbnbGraph) AirbnbApplication.instance(getActivity()).component()).inject(this);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C0880R.layout.fragment_verify_work_email, container, false);
        bindViews(view);
        this.workEmail = getArguments().getString(ARG_WORK_EMAIL);
        this.workEmailStatus = (VerificationStatus) getArguments().getSerializable(ARG_WORK_EMAIL_VERIFICATION_STATUS);
        this.workEmailLaunchSource = this.workEmailDataController.getLaunchSource();
        setupUI();
        return view;
    }

    private void setupUI() {
        switch (this.workEmailStatus) {
            case NOT_VERIFIED:
                setupUIForNewVerification();
                return;
            case PENDING_VERIFICATION:
                setupUIForNewVerification();
                setupResendEmailButton();
                return;
            default:
                return;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Check.state(context instanceof VerifyWorkEmailListener, "activity must implement VerifyWorkEmailListener");
        Check.state(context instanceof WorkEmailDataController, "activity must implement WorkEmailDataController");
        this.verifyWorkEmailListener = (VerifyWorkEmailListener) context;
        this.workEmailDataController = (WorkEmailDataController) context;
    }

    public void onDetach() {
        this.verifyWorkEmailListener = null;
        this.workEmailDataController = null;
        super.onDetach();
    }

    private void setupUIForNewVerification() {
        this.documentMarquee.setTitle(C0880R.string.bt_verify_work_email_title);
        this.documentMarquee.setCaption((CharSequence) getString(C0880R.string.bt_verify_work_email_subtitle, this.workEmail));
        this.gotItButton.setOnClickListener(VerifyWorkEmailFragment$$Lambda$1.lambdaFactory$(this));
    }

    private void setupResendEmailButton() {
        this.resendEmailButton.setVisibility(0);
        this.resendEmailButton.setOnClickListener(VerifyWorkEmailFragment$$Lambda$2.lambdaFactory$(this));
    }

    static /* synthetic */ void lambda$setupResendEmailButton$1(VerifyWorkEmailFragment verifyWorkEmailFragment, View v) {
        verifyWorkEmailFragment.businessTravelJitneyLogger.logPendingEmailVerificationClickResend(verifyWorkEmailFragment.workEmailLaunchSource);
        verifyWorkEmailFragment.verifyWorkEmailListener.goToAddWorkEmail();
    }

    /* access modifiers changed from: private */
    public void finishWorkEmailVerification() {
        if (this.workEmailStatus.equals(VerificationStatus.NOT_VERIFIED)) {
            this.businessTravelJitneyLogger.logEmailVerificationClickGotIt(this.workEmailLaunchSource);
        } else if (this.workEmailStatus.equals(VerificationStatus.PENDING_VERIFICATION)) {
            this.businessTravelJitneyLogger.logPendingEmailVerificationClickGotIt(this.workEmailLaunchSource);
        }
        getActivity().finish();
    }
}
