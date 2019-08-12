package com.airbnb.android.booking.p336n2;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.core.enums.PaymentMethod;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.booking.n2.PaymentMethodSelectionView */
public class PaymentMethodSelectionView extends BaseSelectionView<SimpleSelectionViewItem> {
    public PaymentMethodSelectionView(Context context) {
        super(context);
    }

    public PaymentMethodSelectionView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PaymentMethodSelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public PaymentMethod getSelectedPaymentMethod() {
        return (PaymentMethod) ((SimpleSelectionViewItem) getSelectedItem()).getData();
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        setItems(FluentIterable.from((Iterable<E>) paymentMethods).transform(PaymentMethodSelectionView$$Lambda$1.lambdaFactory$(this)).toList());
    }

    static /* synthetic */ SimpleSelectionViewItem lambda$setPaymentMethods$0(PaymentMethodSelectionView paymentMethodSelectionView, PaymentMethod paymentMethod) {
        return new SimpleSelectionViewItem(paymentMethodSelectionView.getResources().getString(paymentMethod.getNameResource()), paymentMethod);
    }
}
