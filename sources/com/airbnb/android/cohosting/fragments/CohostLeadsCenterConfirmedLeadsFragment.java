package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.controllers.CohostLeadsCenterConfirmedLeadsEpoxyController;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class CohostLeadsCenterConfirmedLeadsFragment extends CohostLeadsCenterBaseFragment {
    private CohostLeadsCenterConfirmedLeadsEpoxyController epoxyController;
    @BindView
    AirRecyclerView recyclerView;

    public static CohostLeadsCenterConfirmedLeadsFragment newInstance() {
        return new CohostLeadsCenterConfirmedLeadsFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.epoxyController = new CohostLeadsCenterConfirmedLeadsEpoxyController(getContext(), this.controller, savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_only, container, false);
        bindViews(view);
        this.recyclerView.setEpoxyController(this.epoxyController);
        return view;
    }
}
