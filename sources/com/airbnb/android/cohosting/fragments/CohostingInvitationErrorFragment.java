package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInvitationErrorEpoxyController;
import com.airbnb.android.core.BugsnagWrapper;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class CohostingInvitationErrorFragment extends CohostInvitationBaseFragment implements OnBackListener {
    private static final String ARG_ERROR_TYPE = "error_type";
    private CohostingInvitationErrorEpoxyController epoxyController;
    CohostingInvitationJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public enum ErrorType {
        EmailMismatch(C5658R.string.cohosting_invitation_error_email_mismatch),
        SelfInvitation(C5658R.string.cohosting_invitation_error_self_invitation),
        InvalidInvitation(C5658R.string.cohosting_invitation_error_invalid);
        
        public final int stringRes;

        private ErrorType(int i) {
            this.stringRes = i;
        }
    }

    public static CohostingInvitationErrorFragment newInstanceForEmailMismatch() {
        return newInstance(ErrorType.EmailMismatch);
    }

    public static CohostingInvitationErrorFragment newInstanceForSelfInvitation() {
        return newInstance(ErrorType.SelfInvitation);
    }

    public static CohostingInvitationErrorFragment newInstanceForInvalidInvitation() {
        return newInstance(ErrorType.InvalidInvitation);
    }

    public static CohostingInvitationErrorFragment newInstance(ErrorType type) {
        return (CohostingInvitationErrorFragment) ((FragmentBundleBuilder) FragmentBundler.make(new CohostingInvitationErrorFragment()).putSerializable("error_type", type)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_invitation_error, container, false);
        bindViews(view);
        ErrorType type = (ErrorType) getArguments().getSerializable("error_type");
        this.epoxyController = new CohostingInvitationErrorEpoxyController(getContext(), type);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        getAirActivity().setOnBackPressedListener(this);
        setToolbar(this.toolbar);
        logError(type);
        return view;
    }

    private void logError(ErrorType type) {
        long userId = this.mAccountManager.getCurrentUserId();
        switch (type) {
            case EmailMismatch:
                this.logger.logCohostingInvitationEmailMismatchImpression(userId);
                return;
            case SelfInvitation:
                this.logger.logCohostingInvitationInviteSelfImpression(userId);
                return;
            case InvalidInvitation:
                this.logger.logCohostingInvitationInvalidInviteImpression(userId);
                return;
            default:
                BugsnagWrapper.notify((Throwable) new IllegalStateException("Started Cohosting Invitation Error screen with no ErrorType"));
                return;
        }
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }
}
