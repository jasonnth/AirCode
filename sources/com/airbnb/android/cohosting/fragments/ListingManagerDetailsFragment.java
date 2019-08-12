package com.airbnb.android.cohosting.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.cohosting.C5658R;
import com.airbnb.android.cohosting.adapters.ListingManagerDetailsAdapter;
import com.airbnb.android.cohosting.utils.CohostingConstants;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.components.AirToolbar;

public class ListingManagerDetailsFragment extends CohostManagementBaseFragment {
    private ListingManagerDetailsAdapter adapter;
    private String managerId;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ListingManagerDetailsFragment create(String managerId2) {
        return (ListingManagerDetailsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ListingManagerDetailsFragment()).putString(CohostingConstants.LISTING_MANAGER_ID_FIELD, managerId2)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C5658R.layout.fragment_recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.managerId = getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD);
        if (this.controller.getListingManager(this.managerId) != null) {
            this.adapter = new ListingManagerDetailsAdapter(getContext(), this.controller, this.managerId);
            this.recyclerView.setAdapter(this.adapter);
        }
        return view;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public void dataUpdated() {
        super.dataUpdated();
        this.managerId = getArguments().getString(CohostingConstants.LISTING_MANAGER_ID_FIELD);
        if (this.controller.getListingManager(this.managerId) == null) {
            getFragmentManager().popBackStack();
        }
    }
}
