package com.airbnb.android.managelisting.settings;

import android.content.Context;
import android.support.p000v4.app.Fragment;
import android.support.p000v4.app.FragmentManager;
import com.airbnb.android.core.UnhandledStateException;
import com.airbnb.android.core.adapters.BaseTabFragmentPager;
import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.managelisting.C7368R;
import com.airbnb.android.managelisting.settings.ManageListingSettingsFragment.ManageListingPage;
import java.util.Arrays;

class ManageListingFragmentPager extends BaseTabFragmentPager {
    private static final ManageListingPage[] VIEW_TYPES = {ManageListingPage.DetailsSettings, ManageListingPage.BookingSettings};

    ManageListingFragmentPager(Context context, FragmentManager fragmentManager) {
        super(context, fragmentManager);
    }

    public int getPageIconId(int position) {
        return 0;
    }

    public int getPageTitleId(int position) {
        ManageListingPage page = VIEW_TYPES[position];
        switch (page) {
            case DetailsSettings:
                return C7368R.string.manage_listing_details_settings_tab_title;
            case BookingSettings:
                return C7368R.string.manage_listing_booking_settings_tab_title;
            default:
                throw new UnhandledStateException(page);
        }
    }

    public boolean isSwipePagingEnabled(int position) {
        return true;
    }

    public Fragment getItem(int position) {
        ManageListingPage page = VIEW_TYPES[position];
        switch (page) {
            case DetailsSettings:
                return ManageListingSettingsFragment.createDetailsSettings();
            case BookingSettings:
                return ManageListingSettingsFragment.createBookingSettings();
            default:
                throw new UnhandledStateException(page);
        }
    }

    public int getCount() {
        return VIEW_TYPES.length;
    }

    public int getPagePosition(ManageListingPage page) {
        return Arrays.asList(VIEW_TYPES).indexOf(page);
    }

    public NavigationTag getPageAnalyticsTag(int position) {
        ManageListingPage page = VIEW_TYPES[position];
        switch (page) {
            case DetailsSettings:
                return NavigationTag.ManageListingDetailsSettings;
            case BookingSettings:
                return NavigationTag.ManageListingBookingSettings;
            default:
                throw new UnhandledStateException(page);
        }
    }

    public ManageListingPage getPageType(int position) {
        return VIEW_TYPES[position];
    }
}
