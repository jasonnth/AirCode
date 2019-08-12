package com.airbnb.android.listyourspacedls.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.adapters.CityRegistrationOverviewAdapter;
import com.airbnb.android.listyourspacedls.C7251R;
import com.airbnb.android.listyourspacedls.LYSDataController;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class LYSCityRegistrationOverviewFragment extends LYSBaseFragment {
    private CityRegistrationOverviewAdapter adapter;
    @BindView
    AirButton nextButton;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static LYSCityRegistrationOverviewFragment newInstance() {
        return new LYSCityRegistrationOverviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ListingRegistrationContent content = this.controller.getListingRegistrationProcess().getContent();
        LYSDataController lYSDataController = this.controller;
        lYSDataController.getClass();
        this.adapter = new CityRegistrationOverviewAdapter(content, LYSCityRegistrationOverviewFragment$$Lambda$1.lambdaFactory$(lYSDataController));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7251R.layout.lys_dls_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        if (this.nextButton != null) {
            this.nextButton.setText(C7251R.string.apply);
        }
        return view;
    }

    @OnClick
    public void onApply() {
        this.controller.showCityRegistrationInputGroup(0);
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public void onSaveActionPressed() {
        navigateInFlow(LYSStep.CityRegistration);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationOverview;
    }
}
