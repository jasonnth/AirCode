package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.ConfirmCohostInvitationEpoxyController;
import com.airbnb.android.cohosting.epoxycontrollers.ConfirmCohostInvitationEpoxyController.Listener;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import com.airbnb.android.core.deeplinks.HomeActivityIntents;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.core.models.CohostInvitation;
import com.airbnb.android.utils.FragmentBundler;
import icepick.State;

public class ConfirmInvitationAcceptedFragment extends CohostInvitationBaseFragment implements OnBackListener {
    private ConfirmCohostInvitationEpoxyController epoxyController;
    @State
    CohostInvitation invitation;
    private final Listener listener = ConfirmInvitationAcceptedFragment$$Lambda$1.lambdaFactory$(this);
    CohostingInvitationJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;

    public static ConfirmInvitationAcceptedFragment create() {
        return (ConfirmInvitationAcceptedFragment) FragmentBundler.make(new ConfirmInvitationAcceptedFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.invitation = this.controller.getCohostInvitation();
        this.epoxyController = new ConfirmCohostInvitationEpoxyController(getContext(), this.listener, this.invitation);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_confirm_cohost_invitation, container, false);
        bindViews(view);
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        getAirActivity().setOnBackPressedListener(this);
        this.logger.logCohostingInvitationConfirmationImpression(this.mAccountManager.getCurrentUserId());
        return view;
    }

    public void transitionToManageListingPickerPage() {
        getActivity().finish();
        startActivity(HomeActivityIntents.intentForListings(getContext()));
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }
}
