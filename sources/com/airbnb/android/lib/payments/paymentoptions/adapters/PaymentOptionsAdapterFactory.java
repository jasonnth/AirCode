package com.airbnb.android.lib.payments.paymentoptions.adapters;

import android.content.Context;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.viewcomponents.models.DocumentMarqueeEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.LoadingRowEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentMethodEpoxyModel_;
import com.airbnb.android.core.viewcomponents.models.PaymentOptionEpoxyModel_;
import com.airbnb.android.lib.C0880R;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.Builder;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.PaymentOptionsAdapterListener;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class PaymentOptionsAdapterFactory {
    private final Context context;

    public PaymentOptionsAdapterFactory(Context context2) {
        this.context = context2;
    }

    public PaymentOptionsAdapter createAdapter(List<PaymentOption> paymentOptions, PaymentOption selectedPaymentOption, PaymentOptionsAdapterListener listener) {
        List<PaymentOption> existingPaymentOptions = PaymentUtils.getExistingPaymentOptions(paymentOptions);
        return new Builder().marqueeModel(createMarqueeModel()).loadingModel(new LoadingRowEpoxyModel_()).paymentOptionModels(createPaymentOptionModels(existingPaymentOptions, selectedPaymentOption, listener)).paymentMethodModel(createPaymentMethodModel(listener)).paymentOptions(existingPaymentOptions).selectedPaymentOption(selectedPaymentOption).build();
    }

    private DocumentMarqueeEpoxyModel_ createMarqueeModel() {
        return new DocumentMarqueeEpoxyModel_().titleRes(C0880R.string.select_payment_option_marquee_title);
    }

    private List<PaymentOptionEpoxyModel_> createPaymentOptionModels(List<PaymentOption> paymentOptions, PaymentOption selectedPaymentOption, PaymentOptionsAdapterListener listener) {
        return FluentIterable.from((Iterable<E>) paymentOptions).transform(PaymentOptionsAdapterFactory$$Lambda$1.lambdaFactory$(this, selectedPaymentOption, listener)).toList();
    }

    private PaymentMethodEpoxyModel_ createPaymentMethodModel(PaymentOptionsAdapterListener listener) {
        return new PaymentMethodEpoxyModel_().drawableRes(C0880R.C0881drawable.add_card_icon).title(this.context.getString(C0880R.string.select_payment_option_add_new_method)).onClickListener(PaymentOptionsAdapterFactory$$Lambda$2.lambdaFactory$(listener));
    }
}
