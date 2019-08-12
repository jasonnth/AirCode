package com.airbnb.android.lib.payments.paymentoptions.adapters;

import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.core.viewcomponents.models.PaymentOptionEpoxyModel_;
import com.airbnb.android.lib.payments.paymentoptions.adapters.PaymentOptionsAdapter.PaymentOptionsAdapterListener;
import com.airbnb.android.lib.payments.utils.PaymentImageUtils;
import com.google.common.base.Function;

final /* synthetic */ class PaymentOptionsAdapterFactory$$Lambda$1 implements Function {
    private final PaymentOptionsAdapterFactory arg$1;
    private final PaymentOption arg$2;
    private final PaymentOptionsAdapterListener arg$3;

    private PaymentOptionsAdapterFactory$$Lambda$1(PaymentOptionsAdapterFactory paymentOptionsAdapterFactory, PaymentOption paymentOption, PaymentOptionsAdapterListener paymentOptionsAdapterListener) {
        this.arg$1 = paymentOptionsAdapterFactory;
        this.arg$2 = paymentOption;
        this.arg$3 = paymentOptionsAdapterListener;
    }

    public static Function lambdaFactory$(PaymentOptionsAdapterFactory paymentOptionsAdapterFactory, PaymentOption paymentOption, PaymentOptionsAdapterListener paymentOptionsAdapterListener) {
        return new PaymentOptionsAdapterFactory$$Lambda$1(paymentOptionsAdapterFactory, paymentOption, paymentOptionsAdapterListener);
    }

    public Object apply(Object obj) {
        return new PaymentOptionEpoxyModel_().title(((PaymentOption) obj).getDisplayName(this.arg$1.context)).drawableRes(PaymentImageUtils.getPaymentImageRes((PaymentOption) obj)).isChecked(this.arg$2.equals((PaymentOption) obj)).onClickListener(PaymentOptionsAdapterFactory$$Lambda$3.lambdaFactory$(this.arg$3, (PaymentOption) obj));
    }
}
