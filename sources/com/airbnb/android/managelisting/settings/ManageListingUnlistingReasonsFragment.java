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

public class ManageListingUnlistingReasonsFragment extends ManageListingBaseFragment {
    private UnlistingReasonsAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingUnlistingReasonsFragment create() {
        return new ManageListingUnlistingReasonsFragment();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.adapter = new UnlistingReasonsAdapter(getContext(), this.controller);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingUnlistReasons;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
