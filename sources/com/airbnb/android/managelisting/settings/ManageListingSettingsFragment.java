package com.airbnb.android.managelisting.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.core.utils.Check;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.utils.FragmentBundler;
import com.airbnb.android.utils.FragmentBundler.FragmentBundleBuilder;
import com.airbnb.p027n2.collections.AirRecyclerView;

public class ManageListingSettingsFragment extends ManageListingBaseFragment {
    private static final String PAGE_TYPE_KEY = "page_type";
    private AirRecyclerViewAttachable adapter;
    @BindView
    AirRecyclerView recyclerView;

    interface AirRecyclerViewAttachable {
        void attachToAirRecyclerView(AirRecyclerView airRecyclerView);
    }

    public enum ManageListingPage {
        DetailsSettings,
        BookingSettings
    }

    /* access modifiers changed from: protected */
    public boolean canSaveChanges() {
        return false;
    }

    public static ManageListingSettingsFragment createDetailsSettings() {
        return (ManageListingSettingsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingSettingsFragment()).putSerializable("page_type", ManageListingPage.DetailsSettings)).build();
    }

    public static ManageListingSettingsFragment createBookingSettings() {
        return (ManageListingSettingsFragment) ((FragmentBundleBuilder) FragmentBundler.make(new ManageListingSettingsFragment()).putSerializable("page_type", ManageListingPage.BookingSettings)).build();
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.adapter = createAdapter((ManageListingPage) Check.notNull((ManageListingPage) getArguments().getSerializable("page_type")), this.controller);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(C7368R.layout.fragment_recycler_view_only, container, false);
        bindViews(view);
        this.adapter.attachToAirRecyclerView(this.recyclerView);
        return view;
    }

    private AirRecyclerViewAttachable createAdapter(ManageListingPage page, ManageListingDataController controller) {
        switch (page) {
            case DetailsSettings:
                return new ManageListingDetailsEpoxyController(getContext(), controller, this.mAccountManager.getCurrentUser().getFirstName());
            case BookingSettings:
                return new ManageListingBookingsAdapter(getContext(), controller);
            default:
                throw new UnhandledStateException(page);
        }
    }

    public NavigationTag getNavigationTrackingTag() {
        return NavigationTag.Ignore;
    }
}
