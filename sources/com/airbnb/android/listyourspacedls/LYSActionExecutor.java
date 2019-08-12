package com.airbnb.android.listyourspacedls;

import com.airbnb.android.core.fragments.NavigationTag;
import com.airbnb.android.listing.LYSStep;
import com.airbnb.android.listing.utils.TextSetting;

public interface LYSActionExecutor {
    void photo(long j);

    void popFragment();

    void returnToLanding();

    void showAvailabilityModal();

    void showCityRegistrationExemption();

    void showCityRegistrationInputGroup(int i);

    void showCityRegistrationNextSteps();

    void showCurrencyModal(String str);

    void showDiscountsModal();

    void showGuestAdditionalRequirementsModal();

    void showGuestExpectations();

    void showGuestRequirementsModal();

    void showHouseRulesLegalInfo();

    void showHouseRulesModal();

    void showLoadingOverlay(boolean z);

    void showOpaqueLoader(boolean z);

    void showPublishStep();

    void showRequestToBookChecklistModal();

    void showRoomBedDetails(int i);

    void showSetPriceModal();

    void showStep(LYSStep lYSStep);

    void showTextSettingModal(TextSetting textSetting);

    void showTipModal(int i, int i2, NavigationTag navigationTag);

    void showTipModal(int i, CharSequence charSequence, NavigationTag navigationTag);
}
