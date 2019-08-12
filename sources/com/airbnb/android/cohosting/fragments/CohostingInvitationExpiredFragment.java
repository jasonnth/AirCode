package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.CohostingGraph;
import com.airbnb.android.cohosting.epoxycontrollers.CohostingInvitationExpiredEpoxyController;
import com.airbnb.android.core.CoreApplication;
import com.airbnb.android.core.analytics.CohostingInvitationJitneyLogger;
import com.airbnb.android.core.interfaces.OnBackListener;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;

public class CohostingInvitationExpiredFragment extends CohostInvitationBaseFragment implements OnBackListener {
    private CohostingInvitationExpiredEpoxyController epoxyController;
    CohostingInvitationJitneyLogger logger;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static CohostingInvitationExpiredFragment newInstance() {
        return (CohostingInvitationExpiredFragment) FragmentBundler.make(new CohostingInvitationExpiredFragment()).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ((CohostingGraph) CoreApplication.instance().component()).inject(this);
        View view = inflater.inflate(C5658R.layout.fragment_cohosting_invitation_expired, container, false);
        bindViews(view);
        this.epoxyController = new CohostingInvitationExpiredEpoxyController(getContext(), this.controller.getCohostInvitation());
        this.recyclerView.setAdapter(this.epoxyController.getAdapter());
        getAirActivity().setOnBackPressedListener(this);
        setToolbar(this.toolbar);
        this.logger.logCohostingInvitationExpirationImpression(this.mAccountManager.getCurrentUserId());
        return view;
    }

    public boolean onBackPressed() {
        getActivity().finish();
        return true;
    }
}
