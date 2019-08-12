package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.OnClick;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.ListingRegistrationContent;
import com.airbnb.android.listing.adapters.CityRegistrationOverviewAdapter;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class CityRegistrationOverviewFragment extends ManageListingBaseFragment {
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
        ListingRegistrationContent content = this.controller.getListingRegistrationProcess().getContent();
        ManageListingActionExecutor manageListingActionExecutor = this.controller.actionExecutor;
        manageListingActionExecutor.getClass();
        this.adapter = new CityRegistrationOverviewAdapter(content, CityRegistrationOverviewFragment$$Lambda$1.lambdaFactory$(manageListingActionExecutor));
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(this.adapter);
        this.saveButton.setText(C7368R.string.apply);
        return view;
    }

    @OnClick
    public void onApply() {
        this.controller.actionExecutor.cityRegistrationInputGroup(0);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.CityRegistrationOverview;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
