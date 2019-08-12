package com.airbnb.android.managelisting.settings;

import com.airbnb.android.core.responses.CalendarPricingSettingsResponse;
import com.airbnb.android.core.utils.PercentageUtils;
import p032rx.functions.Action1;

final /* synthetic */ class ManageListingDiscountsSettingsFragment$$Lambda$1 implements Action1 {
    private final ManageListingDiscountsSettingsFragment arg$1;

    private ManageListingDiscountsSettingsFragment$$Lambda$1(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        this.arg$1 = manageListingDiscountsSettingsFragment;
    }

    public static Action1 lambdaFactory$(ManageListingDiscountsSettingsFragment manageListingDiscountsSettingsFragment) {
        return new ManageListingDiscountsSettingsFragment$$Lambda$1(manageListingDiscountsSettingsFragment);
    }

    public void call(Object obj) {
        this.arg$1.epoxyController.initLengthOfStayRules(((CalendarPricingSettingsResponse) obj).calendarPriceSettings.getLengthOfStayRules(), Integer.valueOf(PercentageUtils.discountedDoubleToDiscountInt(this.arg$1.controller.getListing().getAutoWeeklyFactor())), Integer.valueOf(PercentageUtils.discountedDoubleToDiscountInt(this.arg$1.controller.getListing().getAutoMonthlyFactor())));
    }
}
