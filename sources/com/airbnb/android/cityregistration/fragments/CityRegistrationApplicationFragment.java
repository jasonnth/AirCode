package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.adapters.CityRegistrationSubmissionAdapter;
import com.airbnb.android.cityregistration.adapters.CityRegistrationSubmissionAdapter.Listener;

public class CityRegistrationApplicationFragment extends CityRegistrationBaseSubmissionFragment {
    private CityRegistrationSubmissionAdapter adapter;
    private final Listener editInputGroupListener = CityRegistrationApplicationFragment$$Lambda$1.lambdaFactory$(this);

    public static CityRegistrationApplicationFragment create() {
        return new CityRegistrationApplicationFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateAdapter();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.controller.isLYS() ? C5630R.layout.fragment_city_registration_lys : C5630R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setHasFixedSize(true);
        if (this.controller.getListingRegistrationProcess().getContent().getTermsAndConditions() == null) {
            this.saveButton.setText(C5630R.string.submit);
        } else {
            this.saveButton.setText(C5630R.string.next);
        }
        return view;
    }

    public void updateAdapter() {
        this.adapter = new CityRegistrationSubmissionAdapter(this.listingRegistrationProcess, this.editInputGroupListener);
    }

    @OnClick
    public void onSubmit() {
        if (this.controller.getListingRegistrationProcess().getContent().getTermsAndConditions() != null) {
            this.controller.getActionExecutor().cityRegistrationTermsAndConditions();
        } else {
            super.onSubmit();
        }
    }
}
