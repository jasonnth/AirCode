package com.airbnb.android.core.intents;

import android.content.Context;
import android.content.Intent;
import com.airbnb.android.core.Activities;
import com.airbnb.android.core.models.Price;
import com.airbnb.android.core.models.Reservation;
import com.airbnb.android.core.payments.models.CartItem;
import com.airbnb.android.core.payments.models.QuickPayArguments;
import com.airbnb.android.core.payments.models.QuickPayClientType;
import com.airbnb.android.core.react.ReactExposedActivityParamsConstants;

public class QuickPayActivityIntents {
    private static final String ARG_RESERVATION = "key_reservation";
    public static final String RESULT_EXTRA_BILL = "result_extra_bill";
    public static final String RESULT_EXTRA_BILL_PRICE_QUOTE = "result_extra_bill_price_quote";
    public static final String RESULT_EXTRA_RESERVATION = "result_extra_reservation";

    public static Intent intentForTrip(Context context, CartItem cartItem, Price price) {
        return new Intent(context, Activities.quickPay()).putExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT, buildQuickPayArguments(cartItem, price, QuickPayClientType.Trip));
    }

    public static Intent intentForPaidAmenity(Context context, CartItem cartItem, Price price) {
        return new Intent(context, Activities.quickPay()).putExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT, buildQuickPayArguments(cartItem, price, QuickPayClientType.PaidAmenity));
    }

    public static Intent intentForResyReservation(Context context, CartItem cartItem, Price price) {
        return new Intent(context, Activities.quickPay()).putExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT, buildQuickPayArguments(cartItem, price, QuickPayClientType.ResyReservation));
    }

    public static Intent intentForHomes(Context context, CartItem cartItem, Reservation reservation) {
        return new Intent(context, Activities.quickPay()).putExtra(ReactExposedActivityParamsConstants.KEY_ARGUMENT, buildQuickPayArguments(cartItem, null, QuickPayClientType.Homes)).putExtra("key_reservation", reservation);
    }

    private static QuickPayArguments buildQuickPayArguments(CartItem cartItem, Price price, QuickPayClientType clientType) {
        return QuickPayArguments.builder().cartItem(cartItem).price(price).clientType(clientType).build();
    }
}
