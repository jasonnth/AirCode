package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterPendingLeadsEpoxyController;
import com.airbnb.android.core.models.CohostInquiry;
import com.airbnb.p027n2.collections.AirRecyclerView;
import icepick.State;
import java.util.ArrayList;

public class CohostLeadsCenterPendingLeadsFragment extends CohostLeadsCenterBaseFragment {
    private CohostLeadsCenterPendingLeadsEpoxyController epoxyController;
    @BindView
    AirRecyclerView recyclerView;
    @State
    ArrayList<CohostInquiry> respondedInquiries;

    public static CohostLeadsCenterPendingLeadsFragment newInstance() {
        return new CohostLeadsCenterPendingLeadsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.respondedInquiries = this.controller.getRespondedInquiries();
        this.epoxyController = new CohostLeadsCenterPendingLeadsEpoxyController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_only, container, false);
        bindViews(view);
        this.recyclerView.setEpoxyController(this.epoxyController);
        this.epoxyController.setData(this.respondedInquiries);
        return view;
    }
}
