package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterBrowseLeadsEpoxyController;
import com.airbnb.android.core.models.CohostInquiry;
import com.airbnb.p027n2.collections.AirRecyclerView;
import icepick.State;
import java.util.ArrayList;

public class CohostLeadsCenterBrowseLeadsFragment extends CohostLeadsCenterBaseFragment {
    @State
    ArrayList<CohostInquiry> cohostInquiries;
    private CohostLeadsCenterBrowseLeadsEpoxyController epoxyController;
    @BindView
    AirRecyclerView recyclerView;

    public static CohostLeadsCenterBrowseLeadsFragment newInstance() {
        return new CohostLeadsCenterBrowseLeadsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.cohostInquiries = this.controller.getInquiries();
        this.epoxyController = new CohostLeadsCenterBrowseLeadsEpoxyController();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_only, container, false);
        bindViews(view);
        this.recyclerView.setEpoxyController(this.epoxyController);
        this.epoxyController.setData(this.cohostInquiries);
        return view;
    }
}
