package com.airbnb.android.nestedlistings.fragments;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.models.NestedListing;
import com.airbnb.android.nestedlistings.C7496R;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseParentAdapter;
import com.airbnb.android.nestedlistings.epoxycontrollers.NestedListingsChooseParentAdapter.NestedListingsChooseParentListener;
import com.airbnb.android.utils.Strap;
import com.airbnb.p027n2.components.AirToolbar;

public class NestedListingsChooseParentFragment extends NestedListingsBaseFragment {
    private NestedListingsChooseParentListener actionListener = NestedListingsChooseParentFragment$$Lambda$1.lambdaFactory$(this);
    private NestedListingsChooseParentAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static NestedListingsChooseParentFragment create() {
        return new NestedListingsChooseParentFragment();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = new NestedListingsChooseParentAdapter(getContext(), NestedListing.getUnlinkedListings(this.controller.getNestedListingsById().values()), this.actionListener);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7496R.layout.fragment_recycler_view_with_toolbar_dark_foreground, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.toolbar.setNavigationIcon(2);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.NestedListingsChooseParent;
    }

    public Strap getNavigationTrackingParams() {
        return super.getNavigationTrackingParams().mo11638kv("user_id", this.mAccountManager.getCurrentUserId());
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
