package com.airbnb.android.hostcalendar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.fragments.AirFragment;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.hostcalendar.C6418R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.primitives.AirTextView;

public class BlankCalendarFragment extends AirFragment {
    private static final String ARG_DESCRIPTIVE_TEXT = "descriptive_text";
    private static final String ARG_NO_ELIGIBLE_LISTINGS = "no_eligible_listings";
    @BindView
    AirTextView descriptiveText;
    private boolean trackNoListingsPage = false;

    public static BlankCalendarFragment blankCalendarFragment() {
        return (BlankCalendarFragment) ((FragmentBundleBuilder) FragmentBundler.make(new BlankCalendarFragment()).putString(ARG_DESCRIPTIVE_TEXT, "")).build();
    }

    public static BlankCalendarFragment noListingsFragment(String descriptiveText2) {
        return (BlankCalendarFragment) ((FragmentBundleBuilder) ((FragmentBundleBuilder) FragmentBundler.make(new BlankCalendarFragment()).putString(ARG_DESCRIPTIVE_TEXT, descriptiveText2)).putBoolean(ARG_NO_ELIGIBLE_LISTINGS, true)).build();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C6418R.layout.fragment_host_calendar_blank, container, false);
        bindViews(view);
        this.descriptiveText.setText(getArguments().getString(ARG_DESCRIPTIVE_TEXT));
        this.trackNoListingsPage = getArguments().getBoolean(ARG_NO_ELIGIBLE_LISTINGS);
        return view;
    }

    public NavigationTag getNavigationTrackingTag() {
        if (this.trackNoListingsPage) {
            return NavigationTag.CalendarNoListings;
        }
        return NavigationTag.Ignore;
    }
}
