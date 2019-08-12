package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.CheckInInformation;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingSelfCheckInMethodsController.Listener;
import com.airbnb.p027n2.components.AirToolbar;
import com.airbnb.p027n2.primitives.AirButton;

public class ManageListingSelfCheckinMethodsFragment extends ManageListingBaseFragment {
    private Listener listener = new Listener() {
        public void methodSelected(CheckInInformation checkinType) {
            ManageListingSelfCheckinMethodsFragment.this.controller.actionExecutor.editCheckinType(checkinType);
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirButton saveButton;
    @BindView
    AirToolbar toolbar;

    public static ManageListingSelfCheckinMethodsFragment create() {
        return new ManageListingSelfCheckinMethodsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_with_save, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.saveButton.setVisibility(8);
        ManageListingSelfCheckInMethodsController epoxyController = new ManageListingSelfCheckInMethodsController(getContext(), this.listener);
        this.recyclerView.setAdapter(epoxyController.getAdapter());
        epoxyController.setData(CheckInInformation.selfCheckinMethods(this.controller.getCheckInInformation()));
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingSelfCheckInMethod;
    }
}
