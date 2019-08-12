package com.airbnb.android.managelisting;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.managelisting.picker.ManageListingPickerFragment;
import com.airbnb.android.managelisting.settings.CheckinNoteTextSettingFragment;
import com.airbnb.android.managelisting.settings.DlsManageListingActivity;
import com.airbnb.android.managelisting.settings.ManageListingAllCheckinMethodsFragment;
import com.airbnb.android.managelisting.settings.ManageListingBookingsAdapter;
import com.airbnb.android.managelisting.settings.ManageListingCheckInGuideFragment;
import com.airbnb.android.managelisting.settings.ManageListingCheckinTypeTextSettingFragment;
import com.airbnb.android.managelisting.settings.ManageListingDataController;
import com.airbnb.android.managelisting.settings.ManageListingDetailsEpoxyController;
import com.airbnb.android.managelisting.settings.ManageListingDiscountsSettingsFragment;
import com.airbnb.android.managelisting.settings.ManageListingEarlyBirdDiscountFragment;
import com.airbnb.android.managelisting.settings.ManageListingLastMinuteDiscountFragment;
import com.airbnb.android.managelisting.settings.ManageListingLocalLawsFragment;
import com.airbnb.android.managelisting.settings.ManageListingNightlyPriceSettingsFragment;
import com.airbnb.android.managelisting.settings.ManageListingSettingsTabFragment;
import com.airbnb.android.managelisting.settings.photos.ManagePhotosFragment;

public interface ManageListingGraph extends BaseGraph {
    void inject(ManageListingPickerFragment manageListingPickerFragment);

    void inject(CheckinNoteTextSettingFragment checkinNoteTextSettingFragment);

    void inject(DlsManageListingActivity dlsManageListingActivity);

    void inject(ManageListingAllCheckinMethodsFragment manageListingAllCheckinMethodsFragment);

    void inject(ManageListingBookingsAdapter manageListingBookingsAdapter);

    void inject(ManageListingCheckInGuideFragment manageListingCheckInGuideFragment);

    void inject(ManageListingCheckinTypeTextSettingFragment manageListingCheckinTypeTextSettingFragment);

    void inject(ManageListingDataController manageListingDataController);

    void inject(ManageListingDetailsEpoxyController manageListingDetailsEpoxyController);

    void inject(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment);

    void inject(ManageListingEarlyBirdDiscountFragment manageListingEarlyBirdDiscountFragment);

    void inject(ManageListingLastMinuteDiscountFragment manageListingLastMinuteDiscountFragment);

    void inject(ManageListingLocalLawsFragment manageListingLocalLawsFragment);

    void inject(ManageListingNightlyPriceSettingsFragment manageListingNightlyPriceSettingsFragment);

    void inject(ManageListingSettingsTabFragment manageListingSettingsTabFragment);

    void inject(ManagePhotosFragment managePhotosFragment);
}
