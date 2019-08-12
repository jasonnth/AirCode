package com.airbnb.android.payout.create;

import android.content.Context;
import com.airbnb.android.payout.C7552R;
import com.airbnb.android.payout.models.PayoutInfoForm;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Lists;
import java.util.List;

public class AddPayoutMethodUtils {
    public static String getPayoutMethodTimelinessText(Context context, PayoutInfoForm payoutInfoForm) {
        StringBuilder timelinessStringBuilder = new StringBuilder();
        timelinessStringBuilder.append(payoutInfoForm.timelinessInfo());
        timelinessStringBuilder.append("\n\n");
        timelinessStringBuilder.append(context.getResources().getString(C7552R.string.add_payout_choose_payout_method_payout_release_timing));
        return timelinessStringBuilder.toString();
    }

    public static List<String> getSupportedCurrencies(List<PayoutInfoForm> availablePayoutInfoForms) {
        return Lists.newArrayList((Iterable<? extends E>) FluentIterable.from((Iterable<E>) availablePayoutInfoForms).transformAndConcat(AddPayoutMethodUtils$$Lambda$1.lambdaFactory$()).toSet());
    }
}
