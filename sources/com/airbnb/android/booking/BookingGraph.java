package com.airbnb.android.booking;

import com.airbnb.android.booking.fragments.BookingSummaryFragment;
import com.airbnb.android.booking.fragments.CreditCardBaseFragment;
import com.airbnb.android.booking.fragments.SelectCountryFragment;
import com.airbnb.android.booking.fragments.SelectPaymentMethodFragment;
import com.airbnb.android.booking.fragments.alipay.BaseAlipayFragment;
import com.airbnb.android.core.BaseGraph;

public interface BookingGraph extends BaseGraph {
    void inject(BookingSummaryFragment bookingSummaryFragment);

    void inject(CreditCardBaseFragment creditCardBaseFragment);

    void inject(SelectCountryFragment selectCountryFragment);

    void inject(SelectPaymentMethodFragment selectPaymentMethodFragment);

    void inject(BaseAlipayFragment baseAlipayFragment);
}
