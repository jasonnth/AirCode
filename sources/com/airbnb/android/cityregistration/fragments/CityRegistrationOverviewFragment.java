package com.airbnb.android.cityregistration.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.cityregistration.C5630R;
import com.airbnb.android.cityregistration.adapters.CityRegistrationOverviewAdapter;
import com.airbnb.android.cityregistration.executor.CityRegistrationActionExecutor;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationOverviewFragment extends CityRegistrationBaseFragment {
    private CityRegistrationOverviewAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    public static CityRegistrationOverviewFragment create() {
        return new CityRegistrationOverviewFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListingRegistrationContent content = this.listingRegistrationProcess.getContent();
        CityRegistrationActionExecutor actionExecutor = this.controller.getActionExecutor();
        actionExecutor.getClass();
        this.adapter = new CityRegistrationOverviewAdapter(content, CityRegistrationOverviewFragment$$Lambda$1.lambdaFactory$(actionExecutor), getContext());
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(this.controller.isLYS() ? C5630R.layout.fragment_city_registration_lys : C5630R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.saveButton.setText(C5630R.string.apply);
        return view;
    }

    @OnClick
    public void onApply() {
        this.controller.getActionExecutor().cityRegistrationInputGroup(0);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationOverview;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean onSaveActionPressed() {
        return true;
    }
}
