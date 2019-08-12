package com.airbnb.android.lib.payments.presenters;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.android.core.models.PaymentOption;
import com.airbnb.android.lib.payments.utils.PaymentUtils;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

public class PaymentOptionSelectionView extends BaseSelectionView<SimpleSelectionViewItem> {
    private List<PaymentOption> paymentOptions;

    public PaymentOptionSelectionView(Context context) {
        this(context, null);
    }

    public PaymentOptionSelectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaymentOptionSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setSelectedPaymentOption(PaymentOption paymentOption) {
        for (int i = 0; i < this.paymentOptions.size(); i++) {
            if (((PaymentOption) this.paymentOptions.get(i)).getLegacyInstrumentId() == paymentOption.getLegacyInstrumentId()) {
                setSelectedItem(i);
            }
        }
    }

    public void setPaymentOptions(List<PaymentOption> unfilteredPaymentOptions) {
        this.paymentOptions = PaymentUtils.getExistingPaymentOptions(unfilteredPaymentOptions);
        setItems(FluentIterable.from((Iterable<E>) this.paymentOptions).transform(PaymentOptionSelectionView$$Lambda$1.lambdaFactory$(this)).toList());
    }

    static /* synthetic */ SimpleSelectionViewItem lambda$setPaymentOptions$0(PaymentOptionSelectionView paymentOptionSelectionView, PaymentOption paymentOption) {
        return new SimpleSelectionViewItem(paymentOption.getDisplayName(paymentOptionSelectionView.getContext()), paymentOption);
    }
}
