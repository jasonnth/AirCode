package com.airbnb.android.lib.presenters.p338n2.paymentinfo;

import android.content.Context;
import android.util.AttributeSet;
import com.airbnb.android.booking.p336n2.SimpleSelectionViewItem;
import com.airbnb.p027n2.collections.BaseSelectionView;
import com.google.common.collect.FluentIterable;
import java.util.List;

/* renamed from: com.airbnb.android.lib.presenters.n2.paymentinfo.PayoutCurrencySelectionView */
public class PayoutCurrencySelectionView extends BaseSelectionView<SimpleSelectionViewItem> {
    public List<String> payoutCurrencies;

    public PayoutCurrencySelectionView(Context context) {
        this(context, null);
    }

    public PayoutCurrencySelectionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PayoutCurrencySelectionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setPayoutCurrencies(List<String> payoutCurrencies2) {
        this.payoutCurrencies = payoutCurrencies2;
        setItems(FluentIterable.from((Iterable<E>) payoutCurrencies2).transform(PayoutCurrencySelectionView$$Lambda$1.lambdaFactory$()).toList());
    }

    static /* synthetic */ SimpleSelectionViewItem lambda$setPayoutCurrencies$0(String payoutCurrency) {
        return new SimpleSelectionViewItem(payoutCurrency, payoutCurrency);
    }
}
