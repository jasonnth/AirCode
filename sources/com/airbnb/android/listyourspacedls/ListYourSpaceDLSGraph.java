package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.BaseGraph;
import com.airbnb.android.listyourspacedls.fragments.LYSAddressFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSBasePriceFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCalendarFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSCharacterCountMarqueeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSDiscountsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSGuestBookFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSHostingFrequencyFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLandingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSLocalLawsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoDetailFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoManagerFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPhotoStartFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSPublishFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRTBChecklistFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRentHistoryFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSRoomsAndGuestsFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSelectPricingTypeFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSmartPricingFragment;
import com.airbnb.android.listyourspacedls.fragments.LYSSpaceTypeFragment;

public interface ListYourSpaceDLSGraph extends BaseGraph {
    void inject(LYSDataController lYSDataController);

    void inject(LYSHomeActivity lYSHomeActivity);

    void inject(LYSAddressFragment lYSAddressFragment);

    void inject(LYSBasePriceFragment lYSBasePriceFragment);

    void inject(LYSCalendarFragment lYSCalendarFragment);

    void inject(LYSCharacterCountMarqueeFragment lYSCharacterCountMarqueeFragment);

    void inject(LYSDiscountsFragment lYSDiscountsFragment);

    void inject(LYSGuestBookFragment lYSGuestBookFragment);

    void inject(LYSHostingFrequencyFragment lYSHostingFrequencyFragment);

    void inject(LYSLandingFragment lYSLandingFragment);

    void inject(LYSLocalLawsFragment lYSLocalLawsFragment);

    void inject(LYSPhotoDetailFragment lYSPhotoDetailFragment);

    void inject(LYSPhotoManagerFragment lYSPhotoManagerFragment);

    void inject(LYSPhotoStartFragment lYSPhotoStartFragment);

    void inject(LYSPublishFragment lYSPublishFragment);

    void inject(LYSRTBChecklistFragment lYSRTBChecklistFragment);

    void inject(LYSRentHistoryFragment lYSRentHistoryFragment);

    void inject(LYSRoomsAndGuestsFragment lYSRoomsAndGuestsFragment);

    void inject(LYSSelectPricingTypeFragment lYSSelectPricingTypeFragment);

    void inject(LYSSmartPricingFragment lYSSmartPricingFragment);

    void inject(LYSSpaceTypeFragment lYSSpaceTypeFragment);
}
