package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.erf.FeatureToggles;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingGuestRequirementsFragment extends ManageListingBaseFragment {
    private ManageListingGuestRequirementsAdapter guestRequirementsAdapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingGuestRequirementsFragment create() {
        return (ManageListingGuestRequirementsFragment) FragmentBundler.make(new ManageListingGuestRequirementsFragment()).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(FeatureToggles.showBetterFirstMessage() ? C7368R.layout.fragment_listing_recycler_view_only : C7368R.layout.fragment_manage_listing_guest_requirements, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.guestRequirementsAdapter = new ManageListingGuestRequirementsAdapter(this.controller, this.controller.getListing(), getContext());
        if (this.recyclerView != null) {
            this.recyclerView.setAdapter(this.guestRequirementsAdapter);
        }
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingGuestRequirements;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
