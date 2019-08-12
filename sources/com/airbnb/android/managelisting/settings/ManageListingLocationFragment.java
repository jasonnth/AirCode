package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingLocationFragment extends ManageListingBaseFragment {
    private final Listener listener = new Listener() {
        public void address() {
            ManageListingLocationFragment.this.controller.actionExecutor.address();
        }

        public void exactLocationMap() {
            ManageListingLocationFragment.this.controller.actionExecutor.exactLocation();
        }
    };
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingLocationFragment create() {
        return new ManageListingLocationFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_manage_listing_toolbar_and_recycler_view, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.recyclerView.setAdapter(new LocationAdapter(this.controller.getListing(), this.listener, getContext()));
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingLocation;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
