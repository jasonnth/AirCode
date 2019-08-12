package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.support.p002v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.p027n2.components.AirToolbar;

public class ManageListingCalendarSettingsFragment extends ManageListingBaseFragment {
    private CalendarRulesAdapter adapter;
    @BindView
    RecyclerView recyclerView;
    @BindView
    AirToolbar toolbar;

    public static ManageListingCalendarSettingsFragment create() {
        return (ManageListingCalendarSettingsFragment) FragmentBundler.make(new ManageListingCalendarSettingsFragment()).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_listing_recycler_view_only, container, false);
        bindViews(view);
        setToolbar(this.toolbar);
        this.adapter = new CalendarRulesAdapter(getContext(), this.controller.getListing(), this.controller.calendarRule, this.controller.actionExecutor, this.controller.nestedListingsById);
        this.recyclerView.setAdapter(this.adapter);
        return view;
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.adapter.onSaveInstanceState(outState);
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.ManageListingCalendarSettings;
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }
}
