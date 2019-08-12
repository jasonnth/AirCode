package com.airbnb.android.core.businesstravel;

import com.airbnb.android.core.businesstravel.models.BusinessTravelReadyFilterCriteria;
import com.airbnb.android.core.models.User;
import com.airbnb.android.core.utils.Trebuchet;
import com.airbnb.android.core.utils.TrebuchetKeys;
import com.airbnb.erf.Experiments;

public final class BusinessTravelUtils {
    public static final int MINIMUM_BTR_FACETS_COUNT = 18;

    public static boolean shouldShowTravelForWork(User user) {
        return Experiments.showTravelForWorkAndAddOrVerifyWorkEmail() && user.isShowTravelForWork();
    }

    public static boolean shouldSendIntentPredictionRequest(BusinessTravelAccountManager businessTravelAccountManager) {
        return !businessTravelAccountManager.isVerifiedBusinessTraveler() && Experiments.shouldSendBusinessTravelP5PromoIntentPredictionRequest();
    }

    public static boolean shouldShowBusinessTravelReadyFilter(BusinessTravelAccountManager businessTravelAccountManager, BusinessTravelReadyFilterCriteria businessTravelReadyFilterCriteria, int businessTravelReadyListingCount) {
        return Trebuchet.launch(TrebuchetKeys.SHOW_BUSINESS_TRAVEL_READY_FILTER) && Trebuchet.launch(TrebuchetKeys.HAS_BUSINESS_TRAVEL_READY_FILTER_DATA) && businessTravelReadyFilterCriteria != null && businessTravelAccountManager.isVerifiedBusinessTraveler() && businessTravelReadyListingCount >= 18;
    }

    public static boolean shouldShowAddWorkEmailPromoText() {
        return Trebuchet.launch(TrebuchetKeys.BT_WORK_EMAIL_PROMO_TEXT) && Experiments.useAddWorkEmailPromoText();
    }
}
